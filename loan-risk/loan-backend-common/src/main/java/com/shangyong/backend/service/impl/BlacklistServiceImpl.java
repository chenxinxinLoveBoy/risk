package com.shangyong.backend.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangyong.backend.common.*;
import com.shangyong.backend.common.consts.MongoConstants;
import org.apache.commons.lang3.StringUtils;
import org.bson.BSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.enums.BanCodeEnum;
import com.shangyong.backend.common.enums.BlackListCodeEnum;
import com.shangyong.backend.common.enums.BlackListDsSourceEnum;
import com.shangyong.backend.common.enums.ScBanControlIfRefuseEnum;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.dao.BuBlacklistDao;
import com.shangyong.backend.dao.BuBlacklistDeviceDao;
import com.shangyong.backend.dao.BuBlacklistHisDao;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.BlacklistResult;
import com.shangyong.backend.entity.BuBlacklist;
import com.shangyong.backend.entity.BuBlacklistHis;
import com.shangyong.backend.entity.BuBlacklistManage;
import com.shangyong.backend.entity.IsBlacklistResult;
import com.shangyong.backend.entity.ScBanControl;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.entity.redis.BlacklistValueRedis;
import com.shangyong.backend.service.BlacklistService;
import com.shangyong.backend.service.RiskRuleCheckService;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.FileUtils;
import com.shangyong.utils.RedisUtils;
import com.shangyong.utils.UUIDUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * 黑名单相关service实现
 * @author hc
 *
 */
@Service
public class BlacklistServiceImpl implements BlacklistService {

	private static Logger logger = LoggerFactory.getLogger(BlacklistServiceImpl.class);

	/** 黑名单redis刷新单次扫描条数*/
	public static final int BLACKLIST_REFRESH_COUNT = 1000;

	@Autowired
	private BuBlacklistDao buBlacklistDao;
	
	@Autowired
	private BuBlacklistHisDao buBlacklistHisDao;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Autowired
	private BlacklistManageServiceImpl blacklistManageServiceImpl;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	BlacklistService blacklistService;
	
	@Autowired
	private RiskRuleCheckService riskRuleCheckService;
	
	@Autowired
	private ScBanControlServiceImpl scBanControlService;
	

	@Autowired
	BuBlacklistDeviceDao buBlacklistDeviceDao;
	
	/**
	 * 禁止项查询接口
	 */
	@Autowired
	private ScBanControlRedisServiceImpl scBanControlRedisServiceImpl;
	
    @Autowired
    private SysParamRedisService sysParamRedisService;
    

	@Override
	public List<BuBlacklist> listViewAll(BuBlacklist buBlacklist) {
		return buBlacklistDao.listViewAll(buBlacklist);
	}

	/**
	 * 查询（根据idCard 查询）
	 */
	@Override
	public List<BuBlacklist> findByidCard(BuBlacklist buBlacklist) {
		return buBlacklistDao.findByidCard(buBlacklist);
	}
	/**
	 * 统计
	 */
	@Override
	public int listAllCount(BuBlacklist buBlacklist) {
		int temp = buBlacklistDao.listAllCount(buBlacklist);
		return temp;
	}
	 

	/**
	 * 更新对象信息
	 */
	@Override
	public boolean update(BuBlacklist buBlacklist) {
		buBlacklist.setModifyTime(DateUtils.getDate(new Date()));
		return buBlacklistDao.updateByPrimaryKey(buBlacklist);
	}


	@Override
	@Transactional
	public boolean saveEntity(BuBlacklist buBlacklist) {
		buBlacklist.setCreateTime(DateUtils.getDate(new Date()));
		buBlacklist.setModifyTime(DateUtils.getDate(new Date()));
		buBlacklist.setBlacklistId(UUIDUtils.getUUID());
		boolean falg = false;
		int temp = buBlacklistDao.saveEntity(buBlacklist); 
		if (temp > 0) {
			falg = true;
			BlacklistValueRedis blacklistValueRedis = new BlacklistValueRedis();
			blacklistValueRedis.setDsSource(buBlacklist.getDsSource());
			blacklistValueRedis.setRemark(buBlacklist.getRemark());
			blacklistValueRedis.setClassCode(buBlacklist.getClassCode());
			// 黑名单  更新redis,放入缓存,永久保存
			setBlacklistRedis(buBlacklist.getCertCode(),buBlacklist.getPhoneNum() ,buBlacklist.getDeviceId(), blacklistValueRedis);
			// 黑名单根据分类存入redis
			if(StringUtils.isNotBlank(buBlacklist.getClassCode())) {
				setBlacklistRedisClassify(buBlacklist.getCertCode(),buBlacklist.getPhoneNum() ,buBlacklist.getDeviceId(),buBlacklist.getClassCode(), blacklistValueRedis);
			}
		}
		return falg;
	}

	
	@Override
	public BuBlacklist queryInfoByObj(BuBlacklist buBlacklist) {
		return buBlacklistDao.queryInfoByObj(buBlacklist);
	}


	@Override
	@Transactional
	public boolean deleteObj(BuBlacklist buBlacklist) {
		UUserBo userBo= TokenManager.getUser();// 直接从token取 
		String uname=userBo.getNickName();//获取删除人员
		String[] ids = buBlacklist.getIds();
		BuBlacklist blackForSave=new BuBlacklist();
		BuBlacklist blackForSearch=new BuBlacklist();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		boolean flag = false;
		int temp=0;
		for (int i = 0; i < ids.length; i++) {
			
			blackForSearch.setBlacklistId(ids[i]);
			blackForSave = this.queryObject(blackForSearch);
			
			if(blackForSave != null){
				BuBlacklistHis blackHis=new BuBlacklistHis();
				BeanUtils.copyProperties(blackForSave,blackHis);
				blackHis.setBlacklistHisId(UUIDUtils.getUUID());
				blackHis.setDeleteMan(uname);
				blackHis.setDeleteTime(df.format(new Date()));
				int tempDel=buBlacklistDao.deleteObj(blackForSave);
				if(tempDel>0){//成功删除，插入数据到黑名单历史表
					int tempSave=buBlacklistHisDao.saveEntity(blackHis);
					if(tempSave>0){
						temp+=tempSave;
						logger.info("删除成功，已将数据保存至黑名单信息历史表");
						//删除redis里面的缓存
						delBlacklistInRedis(blackHis.getCertCode(), blackHis.getPhoneNum(), blackHis.getDeviceId());
					}else{
						logger.info("插入潘多拉黑名单信息历史表失败");
						return false;
					}
					
				}else{
					logger.info("删除潘多拉黑名单信息失败");
					return false;
				}
				
			}
		}
		flag=temp>0?true:false;		
		return flag;
	}
	
	/**
	 * 根据主键ID（多个）查询
	 */
	private BuBlacklist queryObject(BuBlacklist buBlacklist) { 
		return buBlacklistDao.queryObject(buBlacklist);
	}
	

	@Override
	public int listAllSum() {
		return buBlacklistDao.listAllSum();
	}


	@Override
	public int queryAllCount(BuBlacklist buBlacklist) {
		return buBlacklistDao.queryAllCount(buBlacklist);
	}

	@Override
	public BuBlacklist findBydsSourceAndsNumber(BuBlacklist buBlacklist) {
 		return buBlacklistDao.findBydsSourceAndsNumber(buBlacklist);
	}

	@Override
	public boolean isInBlacklist(String certCode,String phoneNum,String deviceId){
		boolean flag=false;
		if(StringUtils.isNotBlank(certCode)){
			if(RedisUtils.hexists(RedisConstant.BLACKLIST_REDIS_CERTCODE_KEY,certCode)){
				logger.info("身份证命中黑名单，身份证号："+certCode);
				flag=true;
			}
		}
		if(StringUtils.isNotBlank(phoneNum)){
			if(RedisUtils.hexists(RedisConstant.BLACKLIST_REDIS_PHONENUM_KEY,phoneNum)){
				logger.info("手机号命中黑名单，手机号："+phoneNum);
				flag=true;
			}
		}
		if(StringUtils.isNotBlank(deviceId)){
			if(RedisUtils.hexists(RedisConstant.BLACKLIST_REDIS_DEVICEID_KEY,deviceId)){
				logger.info("设备ID命中黑名单，设备ID："+deviceId);
				flag=true;
			}
		}
		return flag;
	}
	
	/**
	 * 将身份证，手机号，设备ID放入Redis缓存
	 * @param certCode
	 * @param phoneNum
	 * @param deviceId
	 */
	private void setBlacklistRedis(String certCode,String phoneNum,String deviceId,BlacklistValueRedis blacklistValueRedis){
		if(StringUtils.isBlank(blacklistValueRedis.getDsSource())) {
			blacklistValueRedis.setDsSource(BlackListDsSourceEnum.HONG_CHENG_BEFORE_AUDIT.getCode());
		}
		//缓存时长永久有效
		if(StringUtils.isNotBlank(certCode)){
			RedisUtils.hsetEx(RedisConstant.BLACKLIST_REDIS_CERTCODE_KEY,certCode,JSONObject.toJSONString(blacklistValueRedis));
		}
		if(StringUtils.isNotBlank(phoneNum)){
			RedisUtils.hsetEx(RedisConstant.BLACKLIST_REDIS_PHONENUM_KEY,phoneNum,JSONObject.toJSONString(blacklistValueRedis));
		}
		if(StringUtils.isNotBlank(deviceId)){
			RedisUtils.hsetEx(RedisConstant.BLACKLIST_REDIS_DEVICEID_KEY,deviceId,JSONObject.toJSONString(blacklistValueRedis));
		}
	}
	

	/**
	 * setBlacklistRedisClassify:根据用户classCode存入Redis
	 * @param certCode
	 * @param phoneNum
	 * @param deviceId
	 * @param classCode
	 * @param blacklistValueRedis
	 */
	private void setBlacklistRedisClassify(String certCode,String phoneNum,String deviceId,String classCode,BlacklistValueRedis blacklistValueRedis) {
		if(StringUtils.isBlank(blacklistValueRedis.getDsSource())) {
			blacklistValueRedis.setDsSource(BlackListDsSourceEnum.HONG_CHENG_BEFORE_AUDIT.getCode());
		}
		//缓存时长永久有效
		if(StringUtils.isNotBlank(certCode)){
			RedisUtils.hsetEx(RedisConstant.BLACKLIST_REDIS_CERTCODE_KEY+":"+classCode,certCode,JSONObject.toJSONString(blacklistValueRedis));
		}
		if(StringUtils.isNotBlank(phoneNum)){
			RedisUtils.hsetEx(RedisConstant.BLACKLIST_REDIS_PHONENUM_KEY+":"+classCode,phoneNum,JSONObject.toJSONString(blacklistValueRedis));
		}
		if(StringUtils.isNotBlank(deviceId)){
			RedisUtils.hsetEx(RedisConstant.BLACKLIST_REDIS_DEVICEID_KEY+":"+classCode,deviceId,JSONObject.toJSONString(blacklistValueRedis));
		}
	}

	/**
	 * 删除redis中的黑名单缓存
	 * @param certCode
	 * @param phoneNum
	 * @param deviceId
	 */
	private void delBlacklistInRedis(String certCode,String phoneNum,String deviceId){
		if(StringUtils.isNotBlank(certCode)){
			RedisUtils.hdelEx(RedisConstant.BLACKLIST_REDIS_CERTCODE_KEY,certCode);
			logger.info("删除redis身份证，身份证："+certCode);
		}
		if(StringUtils.isNotBlank(phoneNum)){
			RedisUtils.hdelEx(RedisConstant.BLACKLIST_REDIS_PHONENUM_KEY,phoneNum);
			logger.info("删除redis手机号，手机号："+phoneNum);
		}
		if(StringUtils.isNotBlank(deviceId)){
			RedisUtils.hdelEx(RedisConstant.BLACKLIST_REDIS_DEVICEID_KEY,deviceId);
			logger.info("删除redis设备ID，设备ID："+deviceId);
		}
	}
	
	@Override
	public Map<String,Object> upload(MultipartFile file){
		BuBlacklistManage buBlacklistManage=new BuBlacklistManage();
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			logger.info("开始校验文件规范----------");
			fileUtils.CheckFile(file);//校验文件规范
			logger.info("开始上传文件------");
			String fileVal = fileUtils.uploadCSVFile(file,Constants.UPLOAD_PATH_BLACKLIST);//上传文件至Linux
			buBlacklistManage.setUploadAddress(fileVal);
			String codeId=this.insertCSVResult(buBlacklistManage,null);//保存上传记录
			map.put("codeId", codeId);//批次号
			try {
				List<String> list=fileUtils.resoveCSV(fileVal);
				map.put("list", list);//csv数据
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
				return map;
			}
			return map;
		} catch (Exception e) {
			if(e.getMessage().length()>50){
				this.insertCSVResult(buBlacklistManage,e.getMessage().substring(0,50));//保存上传失败记录
			}else{
				this.insertCSVResult(buBlacklistManage,e.getMessage());//保存上传失败记录
			}
			logger.error(e.getMessage(), e);
			return map;
		}
	}
	
	/**
	 * 保存上传记录
	 * @param buBlacklistManage
	 * @param status
	 * @return
	 */
	@Transactional
	public String insertCSVResult(BuBlacklistManage buBlacklistManage, String status){
		//批次号
		String blacklistCodeId=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
		UUserBo userBo= TokenManager.getUser();
		String uname=userBo.getNickName();
		buBlacklistManage.setBlacklistCodeId(blacklistCodeId);
		buBlacklistManage.setUploadTime(DateUtils.getDate(new Date()));
		buBlacklistManage.setUploadMan(uname);
		if(status == null){
			status="文件上传成功";
			buBlacklistManage.setUploadSuccess(status);
		}else{
			buBlacklistManage.setUploadFailure(status);
		}
		int temp=blacklistManageServiceImpl.saveEntity(buBlacklistManage);
		if(temp>0){
			logger.info("文件上传记录已保存到MySql上传记录表-----");
		}
		
		return blacklistCodeId;
	}
	
	/**
	 * 把上传的数据保存到MySql
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void saveDataInMySql(Map<String,Object> map){
		BuBlacklist buBlacklist=new BuBlacklist();
		buBlacklist.setsNumber((String)map.get("codeId"));//设置流水号为批次号
		buBlacklist.setDsSource("04");//设置数据来源为手工添加
		buBlacklist.setAppName("1");
		List<String> list=(List<String>)map.get("list");
		String data="";
		for (int i = 1; i < list.size(); i++) {
			data=list.get(i);
			if(data.split(",").length == 4 ){
				buBlacklist.setCertCode(data.split(",")[1]);//身份证号
				buBlacklist.setPhoneNum(data.split(",")[2]);//手机号
				buBlacklist.setDeviceId(data.split(",")[3]);//设备ID
				boolean flag=this.saveEntity(buBlacklist);
				if(flag == false){
					logger.error("第"+data.split(",")[0]+"条数据插入失败");
				}
			}else{
				logger.error("第"+data.split(",")[0]+"条数据格式不正确");
			}
		}
	}
	
	/**
	 * 将现有MySql中的黑名单数据刷新进Redis
	 * @param buBlacklist 黑名单所有数据
	 * @return 
	 */
	public void refreshRedis(BuBlacklist buBlacklist){
		
		int totalCount = buBlacklistDao.listAllCount(buBlacklist);
		int remainder = totalCount % BLACKLIST_REFRESH_COUNT;
		int quotient = totalCount / BLACKLIST_REFRESH_COUNT;
		int totalPage = remainder > 0 ? quotient + 1 : quotient;
		logger.info("将MySql中的黑名单数据刷新进Redis----------start:共"+totalCount+"条");
		long startTime = System.currentTimeMillis();
		Jedis jedis = RedisUtils.getResource();
		Pipeline pipeline = jedis.pipelined();

		buBlacklist.setPageSize(BLACKLIST_REFRESH_COUNT);
		for(int i = 1;i <= totalPage;i++) {

			buBlacklist.setPageIndex(i);

			List<BuBlacklist> list = listViewAll(buBlacklist);
			for (BuBlacklist obj : list) {
				BlacklistValueRedis blacklistValueRedis = new BlacklistValueRedis();
				blacklistValueRedis.setDsSource(obj.getDsSource());
				blacklistValueRedis.setRemark(obj.getRemark());
				blacklistValueRedis.setClassCode(obj.getClassCode());
				//刷新进redis(不分类)
				if(StringUtils.isBlank(blacklistValueRedis.getDsSource())) {
					blacklistValueRedis.setDsSource(BlackListDsSourceEnum.HONG_CHENG_BEFORE_AUDIT.getCode());
				}
				//缓存时长永久有效
				if(StringUtils.isNotBlank(obj.getCertCode())){
					pipeline.hset(RedisConstant.BLACKLIST_REDIS_CERTCODE_KEY, obj.getCertCode(), JSONObject.toJSONString(blacklistValueRedis));
					pipeline.persist(RedisConstant.BLACKLIST_REDIS_CERTCODE_KEY);
				}
				if(StringUtils.isNotBlank(obj.getPhoneNum())){
					pipeline.hset(RedisConstant.BLACKLIST_REDIS_PHONENUM_KEY, obj.getPhoneNum(), JSONObject.toJSONString(blacklistValueRedis));
					pipeline.persist(RedisConstant.BLACKLIST_REDIS_PHONENUM_KEY);
				}
				if(StringUtils.isNotBlank(obj.getDeviceId())){
					pipeline.hset(RedisConstant.BLACKLIST_REDIS_DEVICEID_KEY,obj.getDeviceId(),JSONObject.toJSONString(blacklistValueRedis));
					pipeline.persist(RedisConstant.BLACKLIST_REDIS_DEVICEID_KEY);
				}
				// 刷新redis(分类)
				if(StringUtils.isNotBlank(obj.getClassCode())) {
					if(StringUtils.isBlank(blacklistValueRedis.getDsSource())) {
						blacklistValueRedis.setDsSource(BlackListDsSourceEnum.HONG_CHENG_BEFORE_AUDIT.getCode());
					}
					//缓存时长永久有效
					if(StringUtils.isNotBlank(obj.getCertCode())){
						pipeline.hset(RedisConstant.BLACKLIST_REDIS_CERTCODE_KEY+":"+obj.getClassCode(),obj.getCertCode(),JSONObject.toJSONString(blacklistValueRedis));
						pipeline.persist(RedisConstant.BLACKLIST_REDIS_CERTCODE_KEY+":"+obj.getClassCode());
					}
					if(StringUtils.isNotBlank(obj.getPhoneNum())){
						pipeline.hset(RedisConstant.BLACKLIST_REDIS_PHONENUM_KEY+":"+obj.getClassCode(),obj.getPhoneNum(),JSONObject.toJSONString(blacklistValueRedis));
						pipeline.persist(RedisConstant.BLACKLIST_REDIS_PHONENUM_KEY+":"+obj.getClassCode());
					}
					if(StringUtils.isNotBlank(obj.getDeviceId())){
						pipeline.hset(RedisConstant.BLACKLIST_REDIS_DEVICEID_KEY+":"+obj.getClassCode(),obj.getDeviceId(),JSONObject.toJSONString(blacklistValueRedis));
						pipeline.persist(RedisConstant.BLACKLIST_REDIS_DEVICEID_KEY+":"+obj.getClassCode());
					}
				}
			}
			logger.info("将MySql中的黑名单数据刷新进Redis----------第 " + i + " 已处理："+list.size()+" 条数据");
		}
		pipeline.sync();
		jedis.close();
		logger.info("将MySql中的黑名单数据刷新进Redis----------end 耗时："+(System.currentTimeMillis() - startTime)/(1000) + "s");
	}

	@Override
	public int queryCountByObj(BuBlacklist buBlacklist) {
		int count=buBlacklistDao.queryCountByObj(buBlacklist);
		return count;
	}
		
	@Override
	public BlacklistResult isInBlacklistTable(String idCard,String phoneNum,String deviceId){
		
		BlacklistResult blacklistResult=new BlacklistResult();
		blacklistResult.setDeviceId(false);
		blacklistResult.setIdCard(false);
		blacklistResult.setPhone(false);
		int count = 0;
		if(StringUtils.isNotBlank(idCard)){
			BuBlacklist buBlacklist=new BuBlacklist();
			buBlacklist.setCertCode(idCard);
			int temp=queryCountByObj(buBlacklist);
			if(temp>0){
				logger.info("身份证已存在！");
				count++;
				blacklistResult.setIdCard(true);
			}
		}
		if(StringUtils.isNotBlank(phoneNum)){
			BuBlacklist buBlacklist=new BuBlacklist();
			buBlacklist.setPhoneNum(phoneNum);
			int temp=queryCountByObj(buBlacklist);
			if(temp>0){
				logger.info("手机号已存在！");
				count++;
				blacklistResult.setPhone(true);
			}
		}
		if(StringUtils.isNotBlank(deviceId)){
			BuBlacklist buBlacklist=new BuBlacklist();
			buBlacklist.setDeviceId(deviceId);
			int temp=queryCountByObj(buBlacklist);
			if(temp>0){
				logger.info("设备ID已存在！");
				count++;
				blacklistResult.setDeviceId(true);
			}
		}
		blacklistResult.setCount(count);
		return blacklistResult;
	}
	

	@Override
	public BuBlacklist queryInfoByCertCode(BuBlacklist buBlacklist) {
		return buBlacklistDao.queryInfoByCertCode(buBlacklist);
	}

	@Override
	public IsBlacklistResult userIsBlacklist(String certCode, String phoneNum, String deviceId) {
		IsBlacklistResult isBlacklistResult = new IsBlacklistResult();
		//准入规则，默认为新用户
		String paramAdmittance = DictConstant.NEW_CUST_ADMITTANCE_TEMPLATE_ID_KEY_NAME;
		//获取准入规则的模块ID
		SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(paramAdmittance);
		String banCodeTplId = sysParam.getParamValueOne();
		ScBanControl scBanControl = scBanControlService.queryByBanCodeAndId(BanCodeEnum.BLACKLIST_USER.getCode(), banCodeTplId);
		if(null==scBanControl||scBanControl.getState().equals(Constants.STATE_FORBIDDEN)) {
			return isBlacklistResult;
		}
		isBlacklistResult.setBanCode(scBanControl.getBanCode());
		if(StringUtils.isNotBlank(certCode)){
			if(RedisUtils.hexists(RedisConstant.BLACKLIST_REDIS_CERTCODE_KEY,certCode)){
				logger.info("身份证命中黑名单，身份证号："+certCode);
				String value = RedisUtils.hget(RedisConstant.BLACKLIST_REDIS_CERTCODE_KEY,certCode);
				JSON jsonValue = JSON.parseObject(value);
				BlacklistValueRedis blacklistValueRedis = JSONObject.toJavaObject(jsonValue, BlacklistValueRedis.class);
				if(StringUtils.isBlank(blacklistValueRedis.getDsSource())) {
					blacklistValueRedis.setDsSource(BlackListDsSourceEnum.HONG_CHENG_BEFORE_AUDIT.getCode());
				}
				isBlacklistResult.setBlacklistFlag(true);
				isBlacklistResult.setDsSource(blacklistValueRedis.getDsSource());
				isBlacklistResult.setClassCode(blacklistValueRedis.getClassCode());
				isBlacklistResult.setRemark(blacklistValueRedis.getRemark());
				return isBlacklistResult;
			}
		}
		if(StringUtils.isNotBlank(phoneNum)){
			if(RedisUtils.hexists(RedisConstant.BLACKLIST_REDIS_PHONENUM_KEY,phoneNum)){
				logger.info("手机号命中黑名单，手机号："+phoneNum);
				String value = RedisUtils.hget(RedisConstant.BLACKLIST_REDIS_PHONENUM_KEY,phoneNum);
				JSON jsonValue = JSON.parseObject(value);
				BlacklistValueRedis blacklistValueRedis = JSONObject.toJavaObject(jsonValue, BlacklistValueRedis.class);
				if(StringUtils.isBlank(blacklistValueRedis.getDsSource())) {
					blacklistValueRedis.setDsSource(BlackListDsSourceEnum.HONG_CHENG_BEFORE_AUDIT.getCode());
				}
				isBlacklistResult.setBlacklistFlag(true);
				isBlacklistResult.setDsSource(blacklistValueRedis.getDsSource());
				isBlacklistResult.setClassCode(blacklistValueRedis.getClassCode());
				isBlacklistResult.setRemark(blacklistValueRedis.getRemark());
				return isBlacklistResult;
			}
		}
		if(StringUtils.isNotBlank(deviceId)){
			if(RedisUtils.hexists(RedisConstant.BLACKLIST_REDIS_DEVICEID_KEY,deviceId)){
				logger.info("设备ID命中黑名单，设备ID："+deviceId);
				String value = RedisUtils.hget(RedisConstant.BLACKLIST_REDIS_DEVICEID_KEY,deviceId);
				JSON jsonValue = JSON.parseObject(value);
				BlacklistValueRedis blacklistValueRedis = JSONObject.toJavaObject(jsonValue, BlacklistValueRedis.class);
				if(StringUtils.isBlank(blacklistValueRedis.getDsSource())) {
					blacklistValueRedis.setDsSource(BlackListDsSourceEnum.HONG_CHENG_BEFORE_AUDIT.getCode());
				}
				isBlacklistResult.setBlacklistFlag(true);
				isBlacklistResult.setDsSource(blacklistValueRedis.getDsSource());
				isBlacklistResult.setClassCode(blacklistValueRedis.getClassCode());
				isBlacklistResult.setRemark(blacklistValueRedis.getRemark());
				return isBlacklistResult;
			}
		}
		return isBlacklistResult;
	}

	@Override
	public IsBlacklistResult contactsIsBlacklist(BuBlacklist buBlacklist, List<BuBlacklist> contacts) {
		IsBlacklistResult isBlacklistResult = new IsBlacklistResult();
		//准入规则，默认为新用户
		String paramAdmittance = DictConstant.NEW_CUST_ADMITTANCE_TEMPLATE_ID_KEY_NAME;
		//获取准入规则的模块ID
		SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(paramAdmittance);
		String banCodeTplId = sysParam.getParamValueOne();
		ScBanControl scBanControl = scBanControlService.queryByBanCodeAndId(BanCodeEnum.BLACKLIST_EMERGENCY_CONTACT.getCode(), banCodeTplId);
		if(scBanControl==null) {
			isBlacklistResult.setBlacklistFlag(false);
		}
		/**禁止项规则技术比对值**/
		String ruleValue = scBanControl.getRuleComparisonValue();
		/**技术比对值类型（01-数值、02-字符、03-集合）**/
		String ruleComparisonType = scBanControl.getRuleComparisonType();
		/**技术校验规则 **/
		String validateRule = scBanControl.getValidateRule();
		/**是否添加黑名单*/
		String ifRefuse = scBanControl.getIfRefuse();
		/** 通过查询禁止项*/
		List<String> classCodeList = new ArrayList<String>();
		for(BlackListCodeEnum codeEnum : BlackListCodeEnum.values()) {
			boolean checkResult = riskRuleCheckService.ruleCheck(ruleComparisonType, validateRule, ruleValue, codeEnum.getCode());
			if(checkResult==true) {
				classCodeList.add(codeEnum.getCode());
			}
		}
		for(BuBlacklist contact:contacts) {
			for(int i=0;i<classCodeList.size();i++) {
				String classCode = classCodeList.get(i);
				String keyTemp = null;
				if(StringUtils.isNotBlank(contact.getCertCode())){
					keyTemp = RedisConstant.buildBlackListCertClassKey(classCode);
					if(RedisUtils.hexists(keyTemp, contact.getCertCode())){
						String value = RedisUtils.hget(keyTemp, contact.getCertCode());
						BlacklistValueRedis blacklistValueRedis = JSON.parseObject(value, BlacklistValueRedis.class);
						if(StringUtils.isBlank(blacklistValueRedis.getDsSource())) {
							blacklistValueRedis.setDsSource(BlackListDsSourceEnum.HONG_CHENG_BEFORE_AUDIT.getCode());
						}
						isBlacklistResult.setBlacklistFlag(true);
						isBlacklistResult.setClassCode(classCode);
						isBlacklistResult.setDsSource(blacklistValueRedis.getDsSource());
						isBlacklistResult.setRemark(blacklistValueRedis.getRemark());
						logger.info("[匹配黑名单] 身份证命中黑名单，身份证号："+contact.getCertCode());
						break;
					}
				}
				if(StringUtils.isNotBlank(contact.getPhoneNum())){
					keyTemp =  RedisConstant.buildBlackListPhoneClassKey(classCode);
					if(RedisUtils.hexists(keyTemp ,contact.getPhoneNum())){
						String value = RedisUtils.hget(keyTemp, contact.getPhoneNum());
						BlacklistValueRedis blacklistValueRedis = JSON.parseObject(value, BlacklistValueRedis.class);
						if(StringUtils.isBlank(blacklistValueRedis.getDsSource())) {
							blacklistValueRedis.setDsSource(BlackListDsSourceEnum.HONG_CHENG_BEFORE_AUDIT.getCode());
						}
						isBlacklistResult.setBlacklistFlag(true);
						isBlacklistResult.setClassCode(classCode);
						isBlacklistResult.setDsSource(blacklistValueRedis.getDsSource());
						isBlacklistResult.setRemark(blacklistValueRedis.getRemark());
						logger.info("[匹配黑名单] 手机号命中黑名单，手机号："+contact.getPhoneNum());
						break;
					}
				}
				if(StringUtils.isNotBlank(contact.getDeviceId())){
					keyTemp =  RedisConstant.buildBlackListDeviceIdClassKey(classCode);
					if(RedisUtils.hexists(keyTemp, contact.getDeviceId())){
						String value = RedisUtils.hget(keyTemp, contact.getDeviceId());
						BlacklistValueRedis blacklistValueRedis = JSON.parseObject(value, BlacklistValueRedis.class);
						if(StringUtils.isBlank(blacklistValueRedis.getDsSource())) {
							blacklistValueRedis.setDsSource(BlackListDsSourceEnum.HONG_CHENG_BEFORE_AUDIT.getCode());
						}
						isBlacklistResult.setBlacklistFlag(true);
						isBlacklistResult.setClassCode(classCode);
						isBlacklistResult.setDsSource(blacklistValueRedis.getDsSource());
						isBlacklistResult.setRemark(blacklistValueRedis.getRemark());
						logger.info("[匹配黑名单] 设备ID命中黑名单，设备ID：" + contact.getDeviceId());
						break;
					}
				}
			}
			if(isBlacklistResult.isBlacklistFlag()) {
				isBlacklistResult.setBanCode(scBanControl.getBanCode());
				isBlacklistResult.setRemark(scBanControl.getRemark());
				break;
			}
		}
		if(isBlacklistResult.isBlacklistFlag()) {
			if(ScBanControlIfRefuseEnum.ADD.getCode().equals(ifRefuse)) {
				boolean isInBlackList = isInBlacklist(buBlacklist.getCertCode(), buBlacklist.getBlacklistId(), buBlacklist.getDeviceId());
				if(isInBlackList) {
					buBlacklist.setRejectType(scBanControl.getCreditType());
					buBlacklist.setBanCode(scBanControl.getBanCode());
					buBlacklist.setRemark(scBanControl.getRemark());
					buBlacklist.setClassCode(isBlacklistResult.getClassCode());
					saveEntity(buBlacklist);
				}
			}
		}
		return isBlacklistResult;
	}
}