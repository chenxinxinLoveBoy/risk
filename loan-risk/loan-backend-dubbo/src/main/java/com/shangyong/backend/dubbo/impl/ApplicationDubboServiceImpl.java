package com.shangyong.backend.dubbo.impl;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.enums.alarm.AlarmCodeEnum;
import com.shangyong.backend.common.enums.alarm.AlarmThirdPartyCreditInvestigationEnum;
import com.shangyong.backend.dao.BuThirdpartyReportDao;
import com.shangyong.backend.dubbo.ApplicationDubboService;
import com.shangyong.backend.dubbo.response.DubboBaseResponse;
import com.shangyong.backend.entity.ApplicationDubboServiceBean;
import com.shangyong.backend.entity.BuThirdpartyReport;
import com.shangyong.backend.service.ApplicationCustomerInfoService;
import com.shangyong.backend.service.IScAlarm;
import com.shangyong.backend.service.report.JsonReportService;
import com.shangyong.mongo.entity.AppAddressBook;
import com.shangyong.mongo.entity.AppCallRecords;
import com.shangyong.mongo.entity.AppInfos;
import com.shangyong.mongo.entity.AppMessage;
import com.shangyong.utils.CodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Date;

//注册为 Dubbo 服务

/**
 * 申请单信息dubbo服务
 * 
 * @author xixinghui
 *
 */
@Service(version = "1.0.0")
public class ApplicationDubboServiceImpl implements ApplicationDubboService {

	/** 日志 */
	private static Logger logger = LoggerFactory.getLogger("applicationDubboService");

	@Autowired
	private ApplicationCustomerInfoService applicationCustomerInfoService;

	@Autowired
	private JsonReportService jsonReportService;
	
	@Autowired
	private IScAlarm scAlarmImpl;
	
	@Autowired
	private BuThirdpartyReportDao buThirdpartyReportDao;

	@Override
	public DubboBaseResponse saveEntity(ApplicationDubboServiceBean applicationDubboServiceBean) {
		String appSerialNumber = "";
		logger.info("当前时间：" + new Date() + ", 借款申请：saveEntity()" + ", 调用借款申请服务开始,param=" + JSON.toJSONString(applicationDubboServiceBean));
		DubboBaseResponse response = new DubboBaseResponse();
		try {
			//App应用请求流水号
			appSerialNumber = applicationDubboServiceBean.getApplicationBean().getAppSerialNumber();
			//保存用户申请单数据到风控系统
			applicationCustomerInfoService.saveApplicationCustomerInfo(applicationDubboServiceBean);
		
			response.setState("success");
			response.setErrorCode(CodeUtils.SUCCESS.getCode());
		} catch (Exception e) {

			logger.error("借款申请处理异常,APP请求流水号：" + appSerialNumber, e);

			response.setState("failure");
			response.setErrorCode(CodeUtils.FAIL.getCode());
			response.setErrorMsg(e.getMessage());

			// 获取 钉钉 通知URL
			scAlarmImpl.contains(AlarmCodeEnum.APP_DOCKING,"借款申请处理异常，APP请求流水号：" + appSerialNumber + "; 错误信息：" + e.getMessage(), AlarmThirdPartyCreditInvestigationEnum.APP_DOCKING);
		}
		logger.info("当前时间：" + new Date() + ", APP请求流水号：" + appSerialNumber + ", 调用结果response：" + response.toString() + ", 调用借款申请服务结束");
		return response;
	}
	
	@Override
	public DubboBaseResponse saveAppInfoForMongo(Object jsonInfo, String saveType, String appName, String customerId){
		
		DubboBaseResponse rsp = new DubboBaseResponse();
		
		try {
			
			Assert.hasText(jsonInfo.toString(), "jsonInfo信息为空");
			Assert.hasText(saveType, "saveType信息为空");
			Assert.hasText(appName, "appName信息为空");
			Assert.hasText(customerId, "customerId信息为空");
			
			if("1".equals(saveType)){
				/** 保存用户通讯录信息 **/
				try {
					//校验数据是否存在保存记录
					BuThirdpartyReport buThirdpartyReport = new BuThirdpartyReport();
					buThirdpartyReport.setTaskId(Constants.DIRECTORIES_TYPE);
					buThirdpartyReport.setBuApplicationId(customerId);
					String report = buThirdpartyReportDao.getTaskId(buThirdpartyReport);
					if (StringUtils.isBlank(report)) {
						AppAddressBook appAddressBook = new AppAddressBook();
						appAddressBook.setAppName(appName);
						appAddressBook.setCustomerId(customerId);
						appAddressBook.setJsonInfo(jsonInfo);
						appAddressBook.setCreateTimeLong(System.currentTimeMillis());
						appAddressBook.setModifyTimeLong(System.currentTimeMillis());
						jsonReportService.saveAppInfoForMongo(appAddressBook);
						logger.info("用户通讯录数据存储到Mongo CustomerId："+customerId);
						String jsonStr = jsonInfo.toString();
						//通讯录数据存储到阿里云
						jsonReportService.uploadJson(Constants.DIRECTORIES_TYPE, customerId, "directories", jsonStr, Constants.DIRECTORIES_DIR);
					}else{
						logger.info("已存在通讯录数据保存记录，BuApplicationId："+customerId);
						rsp.setErrorCode("200");
						rsp.setErrorMsg("已存在通讯录数据存储记录");
						rsp.setState("1");
						return rsp;
					}
				} catch (Exception e) {
					logger.info("通讯录数据存储异常："+e.getMessage(), e);
				}
				
			}else if("2".equals(saveType)){
				/** 保存用户通话记录信息 **/
				AppCallRecords appCallRecords = new AppCallRecords();
				appCallRecords.setAppName(appName);
				appCallRecords.setCustomerId(customerId);
				appCallRecords.setJsonInfo(jsonInfo);
				appCallRecords.setCreateTimeLong(System.currentTimeMillis());
				appCallRecords.setModifyTimeLong(System.currentTimeMillis());
				jsonReportService.saveAppInfoForMongo(appCallRecords);
				
			}else if("3".equals(saveType)){
				/** 保存用户短信信息 **/
				AppMessage appMessage = new AppMessage();
				appMessage.setAppName(appName);
				appMessage.setCustomerId(customerId);
				appMessage.setJsonInfo(jsonInfo);
				appMessage.setCreateTimeLong(System.currentTimeMillis());
				appMessage.setModifyTimeLong(System.currentTimeMillis());
				jsonReportService.saveAppInfoForMongo(appMessage);
			
			}else if("4".equals(saveType)){
				/** 保存用户应用列表信息 **/
				AppInfos appInfos = new AppInfos();
				appInfos.setAppName(appName);
				appInfos.setCustomerId(customerId);
				appInfos.setJsonInfo(jsonInfo);
				appInfos.setCreateTimeLong(System.currentTimeMillis());
				appInfos.setModifyTimeLong(System.currentTimeMillis());
				jsonReportService.saveAppInfoForMongo(appInfos);
			
			}else{
				
				rsp.setErrorCode("300");
				rsp.setErrorMsg("位置存储类型");
				rsp.setState("1");
			}
			
			rsp.setErrorCode("200");
			rsp.setErrorMsg("同步成功");
			rsp.setState("1");
			
		} catch (Exception e) {
			logger.error("saveAppInfoForMongo 异常:"+ e.getMessage(), e);

			rsp.setErrorCode("500");
			rsp.setErrorMsg(e.getMessage());
			rsp.setState("0");
		}
		
		return rsp;
	}

	/**
	 * 保存app系统传递过来的通讯录数据到数据库
	 * @param appAddressBook
	 */
//	private void saveCustomerDirectoriesDao(Object jsonInfo,String customerId,String appName) {
//			JSONArray jsonArray = JSONArray.fromObject(jsonInfo);
//			List<CustomerDirectories> directories = new ArrayList<CustomerDirectories>();
//			
//			if (jsonArray.size()<1) {
//				return;
//			}
//			int size = jsonArray.size();
//			System.err.println("数据存储："+size+"======================");
//			for(int i = 0;i < jsonArray.size() ; i ++){
//				
//				CustomerDirectories customerDirectories = new CustomerDirectories();
//				customerDirectories.setDirectoriesId(UUIDUtils.getUUID());
//				customerDirectories.setCustomerCollectMessageId(0L);
//				customerDirectories.setCustomerId(customerId);
//				customerDirectories.setAppName(Integer.valueOf(appName));
//				customerDirectories.setCreateTime(DateUtils.formatDate(new Date()));
//				JSONObject object = jsonArray.getJSONObject(i);
//				try {
//					String contactName = object.getString("contactName");
//					String contactPhone = object.getString("contactPhone");
//					int ifMobile = object.getInt("ifMobile");
//					
//					customerDirectories.setContactName(contactName);
//					customerDirectories.setContactPhone(contactPhone);
//					customerDirectories.setIfMobile(ifMobile);//1-手机号，2-固话，3-异常号码
//					directories.add(customerDirectories);
//					
//					if (i % Constants.SAVE_CUSTOMER_DIRECTORIES_SIZE == 0 || i == (size - 1)) {
//							customerDirectoriesDao.saveAllEntity(directories);
//							logger.info("用户通讯录数据存储到数据库成功customerId："+customerId + "存储数据条目数："+jsonArray.size());
//						directories.clear();
//					}
//					
//			} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		jsonReportService.uploadJson(Constants.TXY_UPLOAD_DIR, jsonInfo, TaskTypeConstants.TXY_TASK_TYPE, TaskTypeConstants.TXY_TASK_NAME, TaskTypeConstants.TXY_TASK_ISEND, application.getApplicationId(), "noext");
//	}
}
