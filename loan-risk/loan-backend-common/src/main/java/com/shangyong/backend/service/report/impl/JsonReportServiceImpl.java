package com.shangyong.backend.service.report.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.enums.alarm.AlarmCodeEnum;
import com.shangyong.backend.common.enums.alarm.AlarmThirdPartyCreditInvestigationEnum;
import com.shangyong.backend.entity.BuThirdpartyReport;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.BuThirdpartyReportService;
import com.shangyong.backend.service.IScAlarm;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.report.JsonReportService;
import com.shangyong.backend.utils.AliyunFileUtils;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.JacksonUtils;
import com.shangyong.mongo.common.MongoUtils;
import com.shangyong.mongo.entity.BaseEntity;
import com.shangyong.mongo.entity.DataReport;
import com.shangyong.utils.StringUtil;
import com.shangyong.utils.UUIDUtils;

@Service
public class JsonReportServiceImpl implements JsonReportService {
	
    private static Logger loggerFile = LoggerFactory.getLogger("jsonUpload");
    private static Logger logger = LoggerFactory.getLogger("ThirdartyReport");
    
    @Autowired
    private SysParamRedisService sysParamRedisService;// 参数配置实现类
    
    @Autowired
    private BuThirdpartyReportService buThirdpartyReportServiceImpl;
    
    @Autowired
    private MongoUtils mongo;

    @Autowired
    private IScAlarm scAlarmImpl;
    
    /**
     * 上传文件至阿里云服务器
     * 更新相对路径至第三方报表中 bu_thirdparty_report
     * @param taskTypeDirParam 上传文件的路径配置
     * @param Object             保存信息对象
     * @param reportTaskType     报表类型
     * @param orgName     		   机构名称
     * @param isEnd              是否最后一步
     * @param applicationId      申请单号
     * @param openId             任务编号
     */
    @Override
    public void uploadJson(String taskTypeDirParam, Object obj, String reportTaskType, String orgName, String isEnd, String applicationId, String openId) {
        if(obj == null){
            throw new RuntimeException("json对象为空");
        }
        
        String jsonStr = null;
        
        if(obj instanceof String) {
        	jsonStr = obj.toString();
		}else{
			jsonStr = JacksonUtils.ObjectToJson(obj);
		}
        
        //保存数据到MongoDB
        DataReport dataReport = new DataReport();
        dataReport.setApplicationId(applicationId);
        dataReport.setTaskType(reportTaskType);
        dataReport.setOrgName(orgName);
        dataReport.setIsEnd(isEnd);
        dataReport.setJsonInfo(obj);
        dataReport.setCreateTimeLong(System.currentTimeMillis());
        dataReport.setModifyTimeLong(System.currentTimeMillis());
        
        //存储数据到MongoDB数据库中
        mongo.saveByClazz(dataReport);
        
        //存储数据到阿里云oss上
        uploadJson(reportTaskType, applicationId, openId, jsonStr, taskTypeDirParam);
    }
    
    
    @Override
	public void saveAppInfoForMongo(BaseEntity baseEntity) {
    	mongo.saveByClazz(baseEntity);
	}

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////内部方法//////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *  获取阿里云参数配置
     * @return
     * @throws Throwable
     */
    private SysParam getAliOssSysParam(){
        // 获取参数值
        SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.ALIYUN_OSS);
        
        if (sysParam == null) {
            
        	loggerFile.error("类：JsonReportServiceImpl.class ==> 阿里云参数未配置");
            scAlarmImpl.contains(AlarmCodeEnum.TASK_CONFIG,"类：JsonReportServiceImpl.class ==> 阿里云参数未配置", AlarmThirdPartyCreditInvestigationEnum.TASK_CONFIG);
        } else if (StringUtils.isBlank(sysParam.getParamValueOne())) {
           
        	loggerFile.error("类：JsonReportServiceImpl.class ==>  阿里云第一个参数endpoint值[sysParam.getParamValueOne()]为空或NULL");
            scAlarmImpl.contains(AlarmCodeEnum.TASK_CONFIG,"类：JsonReportServiceImpl.class ==>  阿里云第一个参数endpoint值[sysParam.getParamValueOne()]为空或NULL", AlarmThirdPartyCreditInvestigationEnum.TASK_CONFIG);
        } else if (StringUtils.isBlank(sysParam.getParamValueTwo())) {
           
        	loggerFile.error( "类：JsonReportServiceImpl.class ==> 阿里云第二个参数accessKeyId值[sysParam.getParamValueTwo()]为空或NULL");
            scAlarmImpl.contains(AlarmCodeEnum.TASK_CONFIG,"类：JsonReportServiceImpl.class ==> 阿里云第二个参数accessKeyId值[sysParam.getParamValueTwo()]为空或NULL", AlarmThirdPartyCreditInvestigationEnum.TASK_CONFIG);
        } else if (StringUtils.isBlank(sysParam.getParamValueThree())) {
            
        	loggerFile.error("类：JsonReportServiceImpl.class ==> 阿里云第三个参数accessKeySecret值[sysParam.getParamValueThree()]为空或NULLL");
            scAlarmImpl.contains(AlarmCodeEnum.TASK_CONFIG,"类：JsonReportServiceImpl.class ==> 阿里云第三个参数accessKeySecret值[sysParam.getParamValueThree()]为空或NULLL", AlarmThirdPartyCreditInvestigationEnum.TASK_CONFIG);
        } else if (StringUtils.isBlank(sysParam.getParamValueFour())) {
            
        	loggerFile.error("类：JsonReportServiceImpl.class ==> 阿里云第四个参数bucketName值[sysParam.getParamValueFour()]为空或NULLL");
            scAlarmImpl.contains(AlarmCodeEnum.TASK_CONFIG,"类：JsonReportServiceImpl.class ==> 阿里云第四个参数bucketName值[sysParam.getParamValueFour()]为空或NULLL", AlarmThirdPartyCreditInvestigationEnum.TASK_CONFIG);
        }
        return sysParam;
    }

    /**
     * 获取其它文件服务器参数配置
     * @param ossName oos名称标记
     * @param levelCheck 参数验证级别 如1-校验前一个参数 2-校验前两个参数
     * @return 参数配置对象
     */
    private SysParam getOssSysParam(String ossName, int levelCheck){

        // 获取参数值
        SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(ossName);
        if(sysParam != null && levelCheck > 0){
            
        	if (StringUtils.isBlank(sysParam.getParamValueOne())) {
                
            	loggerFile.error("类：JsonReportServiceImpl.class ==>  服务器[" + ossName + "]参数第一个参数值[sysParam.getParamValueOne()]为空或NULL");
                scAlarmImpl.contains(AlarmCodeEnum.TASK_CONFIG,"类：JsonReportServiceImpl.class ==>  服务器[" + ossName + "]参数第一个参数值[sysParam.getParamValueOne()]为空或NULL", AlarmThirdPartyCreditInvestigationEnum.TASK_CONFIG);
            }

            if (StringUtils.isBlank(sysParam.getParamValueTwo()) && levelCheck > 1) {
                
            	loggerFile.error("类：JsonReportServiceImpl.class ==> 服务器[" + ossName + "]参数值[sysParam.getParamValueTwo()]为空或NULL");
                scAlarmImpl.contains(AlarmCodeEnum.TASK_CONFIG,"类：JsonReportServiceImpl.class ==> 服务器[" + ossName + "]参数值[sysParam.getParamValueTwo()]为空或NULL", AlarmThirdPartyCreditInvestigationEnum.TASK_CONFIG);
            }
            if (StringUtils.isBlank(sysParam.getParamValueThree()) && levelCheck > 2) {
                
            	loggerFile.error("类：JsonReportServiceImpl.class ==> 服务器[" + ossName + "]参数值[sysParam.getParamValueThree()]为空或NULLL");
                scAlarmImpl.contains(AlarmCodeEnum.TASK_CONFIG,"类：JsonReportServiceImpl.class ==> 服务器[" + ossName + "]参数值[sysParam.getParamValueThree()]为空或NULLL", AlarmThirdPartyCreditInvestigationEnum.TASK_CONFIG);
            }
            if (StringUtils.isBlank(sysParam.getParamValueFour()) && levelCheck > 3) {
                
            	loggerFile.error("类：JsonReportServiceImpl.class ==> 服务器[" + ossName + "]参数值[sysParam.getParamValueFour()]为空或NULLL");
                scAlarmImpl.contains(AlarmCodeEnum.TASK_CONFIG,"类：JsonReportServiceImpl.class ==> 服务器[" + ossName + "]参数值[sysParam.getParamValueFour()]为空或NULLL", AlarmThirdPartyCreditInvestigationEnum.TASK_CONFIG);
            }
            if (StringUtils.isBlank(sysParam.getParamValueFive()) && levelCheck > 4) {
               
            	loggerFile.error("类：JsonReportServiceImpl.class ==> 服务器[" + ossName + "]参数值[sysParam.getParamValueFive()]为空或NULLL");
                scAlarmImpl.contains(AlarmCodeEnum.TASK_CONFIG,"类：JsonReportServiceImpl.class ==> 服务器[" + ossName + "]参数值[sysParam.getParamValueFive()]为空或NULLL", AlarmThirdPartyCreditInvestigationEnum.TASK_CONFIG);
            }
        }else{
        	
            loggerFile.error("类：JsonReportServiceImpl.class ==> 服务器[" + ossName + "]参数未配置或没有有效的验证级别");
            scAlarmImpl.contains(AlarmCodeEnum.TASK_CONFIG,"类：JsonReportServiceImpl.class ==> 服务器[" + ossName + "]参数未配置或没有有效的验证级别", AlarmThirdPartyCreditInvestigationEnum.TASK_CONFIG);
            return null;
        }
        return sysParam;
    }


    /**
     * 获取文件路径参数配置
     * @param taskType 任务名称
     * @param levelCheck 参数验证级别 如1-校验前一个参数 2-校验前两个参数
     * @return 参数配置对象
     */
    private SysParam getDirSysParam(String taskType, int levelCheck){

        // 获取参数值
        SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(taskType);
        if(sysParam != null && levelCheck > 0){
            if (StringUtils.isBlank(sysParam.getParamValueOne())) {
                loggerFile.error( "类：JsonReportServiceImpl.class ==>  目录参数配置[" + taskType + "]参数第一个参数值[sysParam.getParamValueOne()]为空或NULL");
                scAlarmImpl.contains(AlarmCodeEnum.TASK_CONFIG,"类：JsonReportServiceImpl.class ==>  目录参数配置[" + taskType + "]参数第一个参数值[sysParam.getParamValueOne()]为空或NULL", AlarmThirdPartyCreditInvestigationEnum.TASK_CONFIG);
            }

            if (StringUtils.isBlank(sysParam.getParamValueTwo()) && levelCheck > 1) {
                loggerFile.error( "类：JsonReportServiceImpl.class ==> 目录参数配置[" + taskType + "]参数值[sysParam.getParamValueTwo()]为空或NULL");
                scAlarmImpl.contains(AlarmCodeEnum.TASK_CONFIG,"类：JsonReportServiceImpl.class ==> 目录参数配置[" + taskType + "]参数值[sysParam.getParamValueTwo()]为空或NULL", AlarmThirdPartyCreditInvestigationEnum.TASK_CONFIG);
            }
            if (StringUtils.isBlank(sysParam.getParamValueThree()) && levelCheck > 2) {
                loggerFile.error( "类：JsonReportServiceImpl.class ==> 目录参数配置[" + taskType + "]参数值[sysParam.getParamValueThree()]为空或NULLL");
                scAlarmImpl.contains(AlarmCodeEnum.TASK_CONFIG,"类：JsonReportServiceImpl.class ==> 目录参数配置[" + taskType + "]参数值[sysParam.getParamValueThree()]为空或NULLL", AlarmThirdPartyCreditInvestigationEnum.TASK_CONFIG);
            }
            if (StringUtils.isBlank(sysParam.getParamValueFour()) && levelCheck > 3) {
                loggerFile.error( "类：JsonReportServiceImpl.class ==> 目录参数配置[" + taskType + "]参数值[sysParam.getParamValueFour()]为空或NULLL");
                scAlarmImpl.contains(AlarmCodeEnum.TASK_CONFIG,"类：JsonReportServiceImpl.class ==> 目录参数配置[" + taskType + "]参数值[sysParam.getParamValueFour()]为空或NULLL", AlarmThirdPartyCreditInvestigationEnum.TASK_CONFIG);
            }
            if (StringUtils.isBlank(sysParam.getParamValueFive()) && levelCheck > 4) {
                loggerFile.error( "类：JsonReportServiceImpl.class ==> 目录参数配置[" + taskType + "]参数值[sysParam.getParamValueFive()]为空或NULLL");
                scAlarmImpl.contains(AlarmCodeEnum.TASK_CONFIG,"类：JsonReportServiceImpl.class ==> 目录参数配置[" + taskType + "]参数值[sysParam.getParamValueFive()]为空或NULLL", AlarmThirdPartyCreditInvestigationEnum.TASK_CONFIG);
            }
        }else{
            loggerFile.error("类：JsonReportServiceImpl.class ==> 目录参数[" + taskType + "]未配置或没有有效的验证级别");
            scAlarmImpl.contains(AlarmCodeEnum.TASK_CONFIG,"类：JsonReportServiceImpl.class ==> 目录参数[" + taskType + "]未配置或没有有效的验证级别", AlarmThirdPartyCreditInvestigationEnum.TASK_CONFIG);
            return null;
        }
        return sysParam;
    }


    /**
     * 上传文件并更新文件路径
     * @param reportTaskType 报表类型
     * @param applicationId 申请单号
     * @param openId 任务编号
     * @param jsonStr JSON字符串
     * @param taskTypeDirParam 上传文件的路径配置
     */
    public void uploadJson(String reportTaskType, String applicationId, String openId, String jsonStr, String taskTypeDirParam) {
        
    	String fileName = applicationId + reportTaskType + openId;
        
        //添加json后缀
        
        //上传后得到完整路径(不包含域名)
        StringBuffer dir = new StringBuffer();
        
        //根目录 放在 risk_json
        SysParam sysParam = getDirSysParam(taskTypeDirParam,1);
        
        if(sysParam != null){
        	
            dir.append(sysParam.getParamValueOne());
            if(StringUtil.checkNotNull(sysParam.getParamValueTwo())){
                dir.append(sysParam.getParamValueTwo());
            }
            
            dir.append("/").append(com.shangyong.utils.DateUtils.formatDate("yyyyMMdd",new Date())).append("/");
            
        }else{
        	
            loggerFile.error("当前时间：" + DateUtils.parseToDateTimeStr(new Date()) + ", 未配置报表文件的目录地址:[ " + taskTypeDirParam + "]" );
            scAlarmImpl.contains(AlarmCodeEnum.TASK_CONFIG,"未配置报表文件的目录地址:[ " + taskTypeDirParam + "]", AlarmThirdPartyCreditInvestigationEnum.TASK_CONFIG);
        }
        String fileDir = dir.toString();
        fileName = AliyunFileUtils.uploadFile2OSS(getAliOssSysParam(),jsonStr,fileDir,fileName);

        //查看第三方报告是否存在
        String reportTaskId = buThirdpartyReportServiceImpl.getTaskIdByList(applicationId, reportTaskType);

        //路径的全路径  +域名
        SysParam fileDomainName = getOssSysParam(Constants.FILE_OSS_DOMAIN_NAME,1);
        if(fileDomainName != null){
            fileName = fileDomainName.getParamValueOne() + "/" + fileName;
        }else{
            loggerFile.error("当前时间：" + DateUtils.parseToDateTimeStr(new Date())
                    + ", 未配置报表文件的域名访问信息[" + Constants.FILE_OSS_DOMAIN_NAME + "]");
            scAlarmImpl.contains(AlarmCodeEnum.TASK_CONFIG,"未配置报表文件的域名访问信息[" + Constants.FILE_OSS_DOMAIN_NAME + "]", AlarmThirdPartyCreditInvestigationEnum.TASK_CONFIG);
        }

        //组装报告参数
        BuThirdpartyReport thirdpartyReport = new BuThirdpartyReport();
        thirdpartyReport.setBuApplicationId(applicationId);
        thirdpartyReport.setTaskType(reportTaskType);
        if(StringUtil.checkNotNull(reportTaskId)){
            //存在报告则更新路径信息
            thirdpartyReport.setJosnStoragePath(fileName);
            thirdpartyReport.setTaskId(reportTaskId);
            thirdpartyReport.setUpdateTime(DateUtils.getDate(new Date()));
            buThirdpartyReportServiceImpl.updateEntity(thirdpartyReport);
        }else{
            //不存在时，直接新建一个报告记录并记录路径信息
            thirdpartyReport.setThirdpartyReportId(UUIDUtils.getUUID());
            thirdpartyReport.setTaskId(openId);
            thirdpartyReport.setJosnStoragePath(fileName);
            if (reportTaskType.equals(Constants.DIRECTORIES_TYPE)) {
            	thirdpartyReport.setRemark("通讯录数据");
            }
            List<BuThirdpartyReport> buThirdpartyReports = new ArrayList<BuThirdpartyReport>();
            buThirdpartyReports.add(thirdpartyReport);
            buThirdpartyReportServiceImpl.saveEntityList(buThirdpartyReports);
        }
    }
    
    
    /**
     * @param reportTaskType
     */
    public String downloadossJson(String josnStoragePath) {
    	SysParam sysParam=getAliOssSysParam();
		String endpoint = sysParam.getParamValueOne().trim();
		String accessKeyId = sysParam.getParamValueTwo().trim();
		String accessKeySecret = sysParam.getParamValueThree().trim();
		String bucketName = sysParam.getParamValueFour().trim();
		long beginTime = Calendar.getInstance().getTimeInMillis();
		logger.info("==================开始从oss下载json==================");
        BufferedReader reader=null;
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		try {
			// 创建上传Object的Metadata
			OSSObject ossObject = ossClient.getObject(bucketName, josnStoragePath.substring(josnStoragePath.indexOf("/",22)+1));
			// 读Object内容
			reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
			while (true) {
				String line =reader.readLine();
			    if (line == null) break;
			    return line;
			    
			}
			
		} catch (IOException e) {
			logger.error("downloadossJson 下载出错：" + e.getMessage(), e);
		}
		finally
		{
			try {
				if(reader!=null){
					reader.close();
			}
			} catch (IOException e) {
				e.printStackTrace();
			}
			ossClient.shutdown();
		}
		long endTime = Calendar.getInstance().getTimeInMillis();
        logger.info("==================从阿里云服务器下载json至结束，用时(ms)：==================" + (endTime - beginTime));
		return null;
    }
}
