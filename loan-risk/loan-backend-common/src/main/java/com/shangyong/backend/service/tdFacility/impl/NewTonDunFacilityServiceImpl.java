package com.shangyong.backend.service.tdFacility.impl;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.*;
import com.shangyong.backend.common.enums.BanCodeEnum;
import com.shangyong.backend.common.enums.FraudBizEnum;
import com.shangyong.backend.common.enums.SourceEnum;
import com.shangyong.backend.dao.BuBlacklistDeviceDao;
import com.shangyong.backend.dao.tdf.*;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.BuThirdpartyReport;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.entity.redis.fraud1_8.TongDunEquFingerprint18Redis;
import com.shangyong.backend.entity.redis.fraud2_0.TongDunEquFingerprint20Redis;
import com.shangyong.backend.entity.tdf.*;
import com.shangyong.backend.entity.tdf.jackson.JsonTdFacility;
import com.shangyong.backend.entity.tdf.jackson.common.*;
import com.shangyong.backend.service.BuThirdpartyReportService;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.access.RiskRuleService;
import com.shangyong.backend.service.report.JsonReportService;
import com.shangyong.backend.service.tdFacility.NewTonDunFacilityService;
import com.shangyong.backend.utils.BanCodeUtil;
import com.shangyong.backend.utils.JacksonUtils;
import com.shangyong.utils.*;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;


/**
 * 同盾设备信息获取service类
 *
 * @author 艾李强
 */
@Service
public class NewTonDunFacilityServiceImpl implements NewTonDunFacilityService {
    /**
     * 同盾设备指纹日志
     **/
    private static Logger tdFacililtyLogger = LoggerFactory.getLogger("tdFacility");

    /**
     * 注入dao层
     **/
    @Autowired
    private TdFacilityFraudServiceDao tdFraudServiceDao;//主表
    @Autowired
    private TdFacilityAttributionCreditScoreServiceDao tdScoreServiceDao;//手机校验
    @Autowired
    private TdFacilityGeoIpServiceDao tdGeoIpServiceDao;//地理位置
    @Autowired
    private TdFacilityHitRuleServiceDao tdHitRuleServiceDao;//命中规则
    @Autowired
    private TdFacilityPolicySetServiceDao tdPolicySetServiceDao;//策略规则
    @Autowired
    private TdFacilityDeviceAndroidServiceDao tdAndroidServiceDao;//安卓物理地址表
    @Autowired
    private TdFacilityDeviceIosServiceDao tdIosServiceDao; //ios物理地址表
    @Autowired
    private SysParamRedisService sysParamRedisService;
    @Autowired
    private JsonReportService jsonReportService;
    @Autowired
    private RiskRuleService riskRuleService;
    @Autowired
    private BuThirdpartyReportService buThirdpartyReportService;
    @Autowired
    private BuBlacklistDeviceDao buBlacklistDeviceDao;

    /**
     * 高德地图url
     */
    private final static String GAO_DE_RUL = "http://restapi.amap.com/v3/geocode/regeo?parameters";

    /**
     * 高德地图key
     */
    private final static String GAO_DE_KEY = "0906ced63e396205bed5ef3ee0aaf0b2";

    @Override
    public RuleResult acquire(Application application) {
        RuleResult ruleResult = new RuleResult();
        BuThirdpartyReport buThirdpartyReport = new BuThirdpartyReport();
        List<Map<String, Object>> checkList = new ArrayList<Map<String, Object>>();
        tdFacililtyLogger.info("同盾设备指纹注册事件传入参数" + application);
        try {
            /** 从数据库获取系统配置文件**/
            SysParam tdFacility = sysParamRedisService.querySysParamByParamValueRedis(Constants.TD_FACILITY_REGISTER);

            if (tdFacility == null) {
                tdFacililtyLogger.error("读取数据库系统配置文件失败" + Constants.TD_FACILITY_REGISTER);
                throw new RuntimeException("读取数据库系统配置文件失败");
            }

            String deploy = tdFacility.getParamValueOne();
            /** 系统配置参数**/
            JsonTdFacility jsonToBean = (JsonTdFacility) JacksonUtils.JsonToBean(deploy, JsonTdFacility.class);
            /** 获取申请来源（0——Android；1——IOS）**/
            String source = application.getSource();
            Assert.hasText(source, "申请来源为空");
            /** 请求参数**/
            Map<String, String> params = new HashMap<String, String>();

            params.put("partner_code", jsonToBean.getPartner_code());
            int type = Integer.valueOf(source);
            switch (type) {
                case 0:
                    /** 加载安卓系统配置**/
                    params.put("secret_key", jsonToBean.getAndroid());
                    params.put("event_id", jsonToBean.getRegister_and());
                    break;
                case 1:
                    /** 加载IOS系统配置**/
                    params.put("secret_key", jsonToBean.getIos());
                    params.put("event_id", jsonToBean.getRegister_ios());
                    break;

                default:
                    /** 未找到相关被配置**/
                    throw new RuntimeException("申请来源错误，请仔细检查");
            }
            /** 请求参数**/
            String url = jsonToBean.getUrl();
            String name = application.getName();
            String certType = application.getCertType();
            String certCode = application.getCertCode();
            String phoneNum = application.getPhoneNum();
            String loanIp = application.getLoanIp();
            String applicationId = application.getApplicationId();
            /** 判断身份证类型**/
            Assert.hasText(certType, "证件类型不能为空");
            Assert.isTrue("1".equals(certType), "证件类型必须为身份证");
            /** 断言判断**/
            Assert.hasText(name, "客户姓名不能为空");
            Assert.hasText(certCode, "身份证不能为空");
            Assert.hasText(phoneNum, "手机号码不能为空");
            Assert.hasText(applicationId, "申请单编号不能为空");
            //获取同盾black_box
            buThirdpartyReport.setBuApplicationId(applicationId);
            buThirdpartyReport.setTaskType(TaskTypeConstants.TD_FACILITY_TYPE);
            String blackBox = buThirdpartyReportService.getTaskId(buThirdpartyReport);


            /** 获取同盾black_box**/
            Assert.hasText(blackBox, "同盾blackBox不能为空");
            params.put("black_box", blackBox);
            params.put("account_name", name);
            params.put("id_number", certCode);
            params.put("account_mobile", phoneNum);
            params.put("account_login", phoneNum);
            params.put("resp_detail_type", jsonToBean.getResp_detail_type());
            if (!StringUtils.isBlank(loanIp)) {
                params.put("ip_address", loanIp);
            }
            tdFacililtyLogger.info("同盾设备指纹注册事件调用第三方开始，参数：" + params);
            String result = RiskHttpClientUtil.doPost(url, params);
            if (result == null) {
                throw new RuntimeException("获取同盾设备指纹注册事件失败");
            }
            /** 数据落地**/
            this.saveInfo(result, application, ruleResult, checkList);

            //调用禁止项，获取用户校验结果
            ruleResult = riskRuleService.querySafeRuleApi(application, checkList);

            if (ruleResult == null) {
                throw new RuntimeException("同盾设备指纹数据报告获取-->调用taskCallBackService-->resultObj 为空");
            }

        } catch (Exception e) {
            throw new RuntimeException("请求同盾设备指纹注册事件失败" + e.getMessage(), e);
        }
        tdFacililtyLogger.info("同盾设备信息,处理完成");
        return ruleResult;
    }


    /**
     * 同盾设备信息数据落地
     *
     * @param result      同盾返回json信息
     * @param application 申请单信息
     * @param ruleResult
     * @param checkList
     */
    private void saveInfo(String result, Application application, RuleResult ruleResult, List<Map<String, Object>> checkList) {

        String applicationId = application.getApplicationId();
        tdFacililtyLogger.info("本次存入申请单号:" + applicationId);
        try {
            TdFacilityJsonIos tdFacilityJson = (TdFacilityJsonIos) JacksonUtils.JsonToBean(result, TdFacilityJsonIos.class);

            if (!"true".equals(tdFacilityJson.getSuccess())) {
                tdFacililtyLogger.error("同盾设备指纹注册事件调用第三方失败，参数：" + tdFacilityJson.getReason_code());
                throw new RuntimeException("获取同盾设备指纹注册事件失败");
            }
            if (tdFacilityJson.getDevice_info() != null) {
                if ("0".equals(application.getSource())) {
                    String carrier = tdFacilityJson.getDevice_info().getCarrier();
                    if (StringUtils.isBlank(carrier) || "-,-".equals(carrier)) {
                        //添加禁止项数据
                        BanCodeUtil.addCheckPoint(checkList, BanCodeEnum.TONG_DUN_CONFIGURATION_SAME_SHIELD_EQUIPMENT.getCode(), "No");
                    } else {
                        //添加禁止项数据
                        BanCodeUtil.addCheckPoint(checkList, BanCodeEnum.TONG_DUN_CONFIGURATION_SAME_SHIELD_EQUIPMENT.getCode(), "Yes");
                    }

                    String trueIp = tdFacilityJson.getDevice_info().getTrueIp();
                    if (StringUtils.isNotBlank(trueIp)) {
                        boolean temp = queryTrueIdRidesAndDatabase(trueIp.trim());
                        BanCodeUtil.addCheckPoint(checkList, BanCodeEnum.SHIELD_FINGERPRINT_EQUIPMENT_TRUE_IP.getCode(), temp);
                    }

                    String deviceId = tdFacilityJson.getDevice_info().getDeviceId();
                    if (StringUtils.isNotBlank(deviceId)) {
                        boolean temp = queryDeviceIdRidesAndDatabase(deviceId.trim());
                        BanCodeUtil.addCheckPoint(checkList, BanCodeEnum.SHIELD_FINGERPRINT_EQUIPMENT_DEVICE_ID.getCode(), temp);
                    }

                    String wifiMac = tdFacilityJson.getDevice_info().getWifiMac();
                    if (StringUtils.isNotBlank(wifiMac)) {
                        boolean temp = queryMacRidesAndDatabase(wifiMac.trim());
                        BanCodeUtil.addCheckPoint(checkList, BanCodeEnum.SHIELD_FINGERPRINT_EQUIPMENT_WIFI_MAC.getCode(), temp);
                    }
                }
            }
            /**创建存入数据库实例对象**/
            TdFacilityFraud tdFraud = new TdFacilityFraud();
            String dateTime = DateUtils.getDate(new Date());
            /**主表**/
            tdFraud.setTdFacilityFraudId(UUIDUtils.getUUID());//增加主键id
            tdFraud.setBuApplicationId(applicationId);//增加申请单号
            tdFraud.setSuccess(tdFacilityJson.getSuccess());//成功或失败
            BeanUtils.copyProperties(tdFacilityJson, tdFraud);
            if (!StringUtils.isBlank(tdFacilityJson.getFinal_decision())) {
                tdFraud.setFinalDecision(tdFacilityJson.getFinal_decision());
            }
            if (!StringUtils.isBlank(tdFacilityJson.getFinal_score())) {
                tdFraud.setFinalScore(tdFacilityJson.getFinal_score());
            }
            if (!StringUtils.isBlank(tdFacilityJson.getSeq_id())) {
                tdFraud.setSeqId(tdFacilityJson.getSeq_id());
            }
            if (!StringUtils.isBlank(tdFacilityJson.getRisk_type())) {
                tdFraud.setRiskType(tdFacilityJson.getRisk_type());
            }
            if (!StringUtils.isBlank(tdFacilityJson.getPolicy_set_name())) {
                tdFraud.setPolicySetName(tdFacilityJson.getPolicy_set_name());
            }
            if (!StringUtils.isBlank(tdFacilityJson.getSpend_time())) {
                tdFraud.setSpendTime(tdFacilityJson.getSpend_time());
            }
            if (!StringUtils.isBlank(tdFacilityJson.getPolicy_name())) {
                tdFraud.setPolicyName(tdFacilityJson.getPolicy_name());
            }
            /** 调用第三方方法是否命中**/
            tdFraud.setState("1");
            tdFraud.setCreateTime(dateTime);//创建时间
            tdFraud.setModifyTime(dateTime);//修改时间

            this.saveTdFraud(tdFraud);

            /**设备指纹命中表**/
            if (null != tdFacilityJson.getHit_rules() && tdFacilityJson.getHit_rules().size() > 0) {
                List<Hit_rules> hitRules = tdFacilityJson.getHit_rules();
                this.saveHitRules(hitRules, applicationId, dateTime);
            }

            /** 插入策略集内容**/
            if (null != tdFacilityJson.getPolicy_set() && tdFacilityJson.getPolicy_set().size() > 0) {
                List<Policy_set> policySets = tdFacilityJson.getPolicy_set();
                this.savePolicySet(policySets, applicationId, dateTime);
            }

            /**设备获取真实地理位置表**/
            if (null != tdFacilityJson.getDevice_info()) {
                Device_info deviceInfo = tdFacilityJson.getDevice_info();
                this.saveDeviceInfo(deviceInfo, applicationId, dateTime, application);
                String tempBrand = null;
                if (SourceEnum.ANDROID.getSourceCode().equals(application.getSource())) {
                    tempBrand = deviceInfo.getBrand();
                } else if (SourceEnum.IOS.getSourceCode().equals(application.getSource())) {
                    tempBrand = deviceInfo.getOs();
                }

                TongDunEquFingerprint18Redis tdefp18Redis = new TongDunEquFingerprint18Redis();
                tdefp18Redis.setPhoneBrandCode(tempBrand);

                String key18 = RedisConstant.buildFraudScoresKey1_8(applicationId, FraudBizEnum.TONG_DUN_FINGER_PRINT_EQU);
                RedisFraudUtils.hmset(key18, tdefp18Redis.toMap());

                //评分卡2.0
                TongDunEquFingerprint20Redis tdefp20Redis = new TongDunEquFingerprint20Redis();
                tdefp20Redis.setPhoneBrandCode(tempBrand);

                String key20 = RedisConstant.buildFraudScoresKey2_0(applicationId, FraudBizEnum.TONG_DUN_FINGER_PRINT_EQU);
                RedisFraudUtils.hmset(key20, tdefp20Redis.toMap());
            }

            /** 获取身份证、手机号归属地信息,信用分**/
            if (null != tdFacilityJson.getAttribution()) {
                Attribution attribution = tdFacilityJson.getAttribution();
                Credit_score score = tdFacilityJson.getCredit_score();
                this.saveAttributionScore(attribution, score, applicationId, dateTime);
            }
        } catch (Exception e) {
            throw new RuntimeException("同盾设备指纹入库失败:" + e.getMessage(), e);
        }
    }


    /**
     * 判断是否存在设备黑名单DeviceId信息
     */
    private boolean queryDeviceIdRidesAndDatabase(String deviceId) {
        boolean flag = false;
        if (RedisUtils.hexists(BlackListCodeRidesConstants.DEVICE_KEY, deviceId)) {
            flag = true;
        } else if (buBlacklistDeviceDao.selectCountDeviceId(deviceId) > 0) {
            //数据库中有数据数据存入rides
            RedisUtils.hset(BlackListCodeRidesConstants.DEVICE_KEY, deviceId, deviceId);
            flag = true;
        }
        return flag;
    }

    /**
     * 判断是否存在设备黑名单trueId信息
     */
    private boolean queryTrueIdRidesAndDatabase(String trueId) {
        boolean flag = false;
        if (RedisUtils.hexists(BlackListCodeRidesConstants.IP_KEY, trueId)) {
            flag = true;
        } else if (buBlacklistDeviceDao.selectCountTrueId(trueId) > 0) {
            //数据库中有数据数据存入rides
            RedisUtils.hset(BlackListCodeRidesConstants.IP_KEY, trueId, trueId);
            flag = true;
        }
        return flag;
    }

    /**
     * 判断是否存在设备黑名单Mac信息
     */
    private boolean queryMacRidesAndDatabase(String macAdress) {
        boolean flag = false;
        if (RedisUtils.hexists(BlackListCodeRidesConstants.MAC_KEY, macAdress)) {
            flag = true;
        } else if (buBlacklistDeviceDao.selectCountMacAddress(macAdress) > 0) {

            //数据库中有数据数据存入rides
            RedisUtils.hset(BlackListCodeRidesConstants.MAC_KEY, macAdress, macAdress);
            flag = true;
        }
        return flag;
    }


    /**
     * 获取身份证、手机号归属地信息,信用分
     **/
    private void saveAttributionScore(Attribution attribution, Credit_score score, String applicationId, String dateTime) {
        if (attribution == null && score == null) {
            return;
        }
        TdFacilityAttributionCreditScore tScore = new TdFacilityAttributionCreditScore();
        tScore.setTdFacilityAttributionCreditScoreId(UUIDUtils.getUUID());
        tScore.setBuApplicationId(applicationId);
        tScore.setCreateTime(dateTime);
        tScore.setModifyTime(dateTime);
        if (attribution != null) {
            BeanUtils.copyProperties(attribution, tScore);
        }
        if (score != null) {
            BeanUtils.copyProperties(score, tScore);
        }
        this.saveTdAttributionScore(tScore);
    }

    /**
     * 设备获取真实地理位置表
     *
     * @param application
     **/
    private void saveDeviceInfo(Device_info deviceInfo, String applicationId, String dateTime, Application application) {
        if (deviceInfo == null) {
            return;
        }
        String success = deviceInfo.getSuccess();
        if ("false".equals(success)) {
            throw new RuntimeException("同盾获取失败");
        }
        if ("0".equals(application.getSource())) {
            TdFacilityDeviceAndroid tdDeviceAnd = new TdFacilityDeviceAndroid();
            BeanUtils.copyProperties(deviceInfo, tdDeviceAnd);
            tdDeviceAnd.setTdFacilityDeviceAndroidId(UUIDUtils.getUUID());
            tdDeviceAnd.setBuApplicationId(applicationId);
            tdDeviceAnd.setCreateTime(dateTime);
            tdDeviceAnd.setModifyTime(dateTime);
            this.saveTdDeviceAnd(tdDeviceAnd);
        } else {
            TdFacilityDeviceIos tdDeviceIos = new TdFacilityDeviceIos();
            BeanUtils.copyProperties(deviceInfo, tdDeviceIos);
            tdDeviceIos.setTdFacilityDeviceIOSId(UUIDUtils.getUUID());
            tdDeviceIos.setBuApplicationId(applicationId);
            tdDeviceIos.setCreateTime(dateTime);
            tdDeviceIos.setModifyTime(dateTime);
            this.saveTdDeviceIos(tdDeviceIos);
        }
        GeoIp geoIp = deviceInfo.getGeoIp();
        if (geoIp != null) {
            TdFacilityGeoIp tGeoIp = new TdFacilityGeoIp();
            BeanUtils.copyProperties(geoIp, tGeoIp);
            String type = "0";

            /**
             * 获取经纬度  执行逆地理获取详细的地址进行添加
             */
            String longitude = tGeoIp.getLongitude();
            String latitude = tGeoIp.getLatitude();

            Map<String, String> saveGdMap = this.saveGd(longitude, latitude);
            //获取 省 市  详细地址 放入地理位置信息表进行添加
            String lbsAddress = saveGdMap.get("lbsAddress");
            String lbsCity = saveGdMap.get("lbsCity");
            String lbsProvince = saveGdMap.get("lbsProvince");

            tdFacililtyLogger.info("用户的LBS省份:" + lbsProvince + ",用户的LBS城市:" + lbsCity + ",用户的LBS详细地址:" + lbsAddress);

            tGeoIp.setLbsAddress(lbsAddress);
            tGeoIp.setLbsProvince(lbsProvince);
            tGeoIp.setLbsCity(lbsCity);

            this.addGeoIp(tGeoIp, type, applicationId);
        }

    }

    /**
     * 插入策略集内容
     *
     * @param policySets
     * @param applicationId
     * @param dateTime
     */
    private void savePolicySet(List<Policy_set> policySets, String applicationId, String dateTime) {
        List<TdFacilityPolicySet> tdPolicySets = new ArrayList<TdFacilityPolicySet>();
        for (Policy_set policySet : policySets) {
            TdFacilityPolicySet tdPolicySet = new TdFacilityPolicySet();
            BeanUtils.copyProperties(policySet, tdPolicySet);
            tdPolicySet.setTdFacilityPolicySetId(UUIDUtils.getUUID());
            tdPolicySet.setBuApplicationId(applicationId);
            tdPolicySet.setCreateTime(dateTime);
            tdPolicySet.setModifyTime(dateTime);
            tdPolicySets.add(tdPolicySet);
        }
        if (!CollectionUtils.isEmpty(tdPolicySets)) {
            this.saveTdPolicySets(tdPolicySets);
        }

    }

    /**
     * 设备指纹命中表
     *
     * @param hitRules
     * @param applicationId
     * @param dateTime
     */
    private void saveHitRules(List<Hit_rules> hitRules, String applicationId, String dateTime) {

        List<TdFacilityHitRule> tdHitRules = new ArrayList<TdFacilityHitRule>();

        for (Hit_rules hr : hitRules) {
            TdFacilityHitRule tdHitRule = new TdFacilityHitRule();
            BeanUtils.copyProperties(hr, tdHitRule);
            tdHitRule.setTdFacilityHitRuleId(UUIDUtils.getUUID());
            tdHitRule.setBuApplicationId(applicationId);
            tdHitRule.setCreateTime(dateTime);
            tdHitRule.setModifyTime(dateTime);
            tdHitRules.add(tdHitRule);
        }
        if (!CollectionUtils.isEmpty(tdHitRules)) {
            this.savetdHitRules(tdHitRules);
        }
    }

    /**
     * 存地址
     **/
    private void addGeoIp(TdFacilityGeoIp geoIp, String type, String applicationId) {
        if (geoIp == null) {
            return;
        }
        String dateTime = DateUtils.getDate(new Date());
        geoIp.setTdFacilityGeoIpId(UUIDUtils.getUUID());
        geoIp.setBuApplicationId(applicationId);
        geoIp.setCreateTime(dateTime);
        geoIp.setModifyTime(dateTime);
        geoIp.setType(type);
        this.saveTdGeoIp(geoIp);
    }

    /**
     * 存入数据库方法
     *
     * @param tdFraud
     */
    private void saveTdFraud(TdFacilityFraud tdFraud) {
        int result = tdFraudServiceDao.saveEntity(tdFraud);
        if (result < 1) {
            tdFacililtyLogger.error("插入主表失败:" + tdFraud);
            throw new RuntimeException("插入主表失败");
        }
    }

    private void savetdHitRules(List<TdFacilityHitRule> tdHitRules) {
        int result = this.tdHitRuleServiceDao.saveAllEntity(tdHitRules);
        if (result < 1) {
            tdFacililtyLogger.error("插入设备指纹命中失败" + tdHitRules);
            throw new RuntimeException("插入设备指纹命中失败");
        }
    }

    /**
     * 策略集内容
     *
     * @param tdPolicySets
     */
    private void saveTdPolicySets(List<TdFacilityPolicySet> tdPolicySets) {
        int result = this.tdPolicySetServiceDao.saveAllEntity(tdPolicySets);
        if (result < 1) {
            tdFacililtyLogger.error("插入策略集内容失败" + tdPolicySets);
            throw new RuntimeException("插入策略集内容失败");
        }
    }

    /**
     * 插入ios设备信息内容
     *
     * @param tdDeviceIos
     */
    private void saveTdDeviceIos(TdFacilityDeviceIos tdDeviceIos) {
        int result = this.tdIosServiceDao.saveEntity(tdDeviceIos);
        if (result < 1) {
            tdFacililtyLogger.error("插入ios设备信息内容失败" + tdDeviceIos);
            throw new RuntimeException("插入ios设备信息内容失败");
        }
    }

    /**
     * 真实地理内容失败
     *
     * @param tGeoIp
     */
    private void saveTdGeoIp(TdFacilityGeoIp tGeoIp) {
        int result = this.tdGeoIpServiceDao.saveEntity(tGeoIp);
        if (result < 1) {
            tdFacililtyLogger.error("插入真实地理内容失败" + tGeoIp);
            throw new RuntimeException("插入真实地理内容失败");
        }
    }

    /**
     * 身份证手机校验信用插入内容
     *
     * @param tScore
     */
    private void saveTdAttributionScore(TdFacilityAttributionCreditScore tScore) {
        int result = this.tdScoreServiceDao.saveEntity(tScore);
        if (result < 1) {
            tdFacililtyLogger.error("身份证手机校验信用插入内容失败" + tScore);
            throw new RuntimeException("身份证手机校验信用插入内容失败");
        }
    }

    /**
     * 插入and设备信息内容失败
     *
     * @param tdDeviceAnd
     */
    private void saveTdDeviceAnd(TdFacilityDeviceAndroid tdDeviceAnd) {
        int result = tdAndroidServiceDao.saveEntity(tdDeviceAnd);
        if (result < 1) {
            tdFacililtyLogger.error("插入and设备信息内容失败" + tdDeviceAnd);
            throw new RuntimeException("插入and设备信息内容失败");
        }
    }

    /**
     * 用户地址
     *
     * @param longitude 经度
     * @param latitude  纬度
     */
    private Map<String, String> saveGd(String longitude, String latitude) {

        Map<String, String> map = new HashMap<String, String>();

        String location = longitude + "," + latitude;

        tdFacililtyLogger.info("用户位置的经纬度" + location);

        map.put("key", GAO_DE_KEY);

        map.put("location", location);

        //gdUrl,请求逆地理位置编译

        String content = RiskHttpClientUtil.doPost(GAO_DE_RUL, map);

        if (content == null || ("").equals(content)) {
            tdFacililtyLogger.error("content不能为空" + content);
        } else {
            JSONObject returnJson = JSONObject.fromObject(content);

            //逆地理位置
            JSONObject regeocode = returnJson.getJSONObject("regeocode");

            JSONObject addressComponent = regeocode.getJSONObject("addressComponent");

            /** LBS省份*/
            String lbsProvince = addressComponent.getString("province");
            /** LBS城市*/
            String lbsCity = addressComponent.getString("city");

            String lbspc = lbsProvince + lbsCity;

            /** LBS详细地址 */
            tdFacililtyLogger.info("用户的地址:" + regeocode.getString("formatted_address"));

            String lbsAddress = regeocode.getString("formatted_address").replace(lbspc, "");

            map.put("lbsAddress", lbsAddress);
            map.put("lbsCity", lbsCity);
            map.put("lbsProvince", lbsProvince);
        }
        return map;
    }
}
