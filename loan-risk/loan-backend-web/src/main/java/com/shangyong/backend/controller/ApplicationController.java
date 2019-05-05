package com.shangyong.backend.controller;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.enums.alarm.AlarmCodeEnum;
import com.shangyong.backend.common.enums.alarm.AlarmThirdPartyCreditInvestigationEnum;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.ApplicationCount;
import com.shangyong.backend.entity.ApplicationPersonCount;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.IScAlarm;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.impl.ApplicationServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.backend.utils.RiskHttpClientUtil;
import com.shangyong.utils.SpringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/backend/application")
public class ApplicationController {
	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(ApplicationController.class);

	@Autowired
	private ApplicationServiceImpl applicationservice;

	@Autowired
	private IScAlarm scAlarmImpl;
	
	
	/**
	 * 系统参数表相关service
	 */
	@Autowired
	private SysParamRedisService sysParamRedisService;

	/**
	 * 查询所有申请单信息
	 *
	 * @param request
	 * @param response
	 * @param Headline
	 */
	@RequestMapping(value = "/findAll.do", method = RequestMethod.POST)
	public void findAll(HttpServletRequest request, HttpServletResponse response, Application application) {
		try {
			int count = applicationservice.findAllCount(application);
			int personCount = applicationservice.findPersonCount(application);
			List<Application> list = applicationservice.findAll(application);
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map.put(Constants.MSG, "获取成功");
			map.put(Constants.REL, true);
			map.put("list", list);
			map.put("count", count);// 分页用，查询的总条数
			map.put("personCount", personCount);// 查询的总人数
			SpringUtils.renderJson(response, map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 查询申请单信息
	 *
	 * @param request
	 * @param response
	 * @param Headline
	 */
	@RequestMapping(value = "/getEntityById.do", method = RequestMethod.POST)
	public void findAll(HttpServletRequest request, HttpServletResponse response, String appSerialNumber) {
		try {
			Application application = applicationservice.getEntityById(appSerialNumber);
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, application);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 查询审核统计
	 *
	 * @param request
	 * @param response
	 * @param applicationCount
	 */
	@RequestMapping(value = "/queryResultCountInfo.do", method = RequestMethod.POST)
	public void queryResultCountInfo(HttpServletRequest request, HttpServletResponse response,
                                     ApplicationCount applicationCount) {

		try {
			int count = applicationservice.listResultCount(applicationCount);
			List<ApplicationCount> list = applicationservice.queryResultCountInfo(applicationCount);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR, e.getMessage());
		}

	}

	@GetMapping("/countStepNumber.htm")
	public void countStepNumber(HttpServletResponse response, ApplicationCount applicationCount){
		try {
			List<ApplicationCount> list = applicationservice.countStepNumber(applicationCount);
			JSONUtils.toListJSON(response, list, 0);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR, e.getMessage());
		}
	}

	@GetMapping("/countHbaseAndartificialNumber.htm")
	public void countHbaseAndartificialNumber(HttpServletResponse response, ApplicationCount applicationCount){
		try {
			Map<String,Integer> map = applicationservice.countHbaseAndartificialNumber(applicationCount);
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, map,null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR, e.getMessage());
		}
	}

	/**
	 * 查询审核结果
	 *
	 * @param request
	 * @param response
	 * @param applicationCount
	 */
	@RequestMapping(value = "/queryResultCount.do", method = RequestMethod.POST)
	public void queryResultCount(HttpServletRequest request, HttpServletResponse response,
                                 ApplicationCount applicationCount) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//审核状态统计
			List<ApplicationCount> list = applicationservice.queryResultCount(applicationCount);
			map.put("list", list);
			//总授信额度
			int totalCreditMoney = applicationservice.queryTotalCreditMoney(applicationCount);
			map.put("totalCreditMoney", totalCreditMoney);
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR, e.getMessage());
		}

	}

	/**
	 * 查询审核统计图
	 *
	 * @param request
	 * @param response
	 * @param applicationCount
	 */
	@RequestMapping(value = "/queryResultMapInfo.do", method = RequestMethod.POST)
	public void queryResultMapInfo(HttpServletRequest request, HttpServletResponse response,
                                   ApplicationCount applicationCount) {

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			// 统计明细列表
			List<ApplicationCount> listInfo = applicationservice.queryResultCountInfo(applicationCount);
			LinkedHashMap<String, Object> listInfoMap = new LinkedHashMap<String, Object>();
			LinkedHashMap<String, Object> listInfoCertMap = new LinkedHashMap<String, Object>();
			for (int i = listInfo.size() - 1; i >= 0; i--) {
				ApplicationCount app = listInfo.get(i);
				listInfoMap.put(app.getAppName()+"_"+app.getSource()+"_"+app.getBanCode() + "_" + app.getAuditResult(), Integer.parseInt(app.getCount()));
				listInfoCertMap.put(app.getAppName()+"_"+app.getSource()+"_"+app.getBanCode() + "_" + app.getAuditResult(), Integer.parseInt(app.getCertCount()));
			}
			// 统计审核状态
			List<ApplicationCount> list = applicationservice.queryResultCount(applicationCount);

			// 待审核/通过/拒绝 数量统计
			LinkedHashMap<String, Object> countMap = new LinkedHashMap<String, Object>();
			LinkedHashMap<String, Object> certMap = new LinkedHashMap<String, Object>();
			int count = 0, certCount = 0;
			for (ApplicationCount app : list) {
				switch (app.getAuditingState()) {
				case Constants.DAI_SP_STAATE:
					countMap.put(Constants.DAI_SP_STATE_NAME, app.getCount());
					certMap.put(Constants.DAI_SP_STATE_NAME, app.getCertCount());
				case Constants.PASS_SP_STAATE:
					countMap.put(Constants.PASS_SP_STATE_NAME, app.getCount());
					certMap.put(Constants.PASS_SP_STATE_NAME, app.getCertCount());
				case Constants.NOPASS_SP_STAATE:
					countMap.put(Constants.NOPASS_SP_STATE_NAME, app.getCount());
					certMap.put(Constants.NOPASS_SP_STATE_NAME, app.getCertCount());
				default:
					;
				}
				count += Integer.parseInt(app.getCount());
				certCount += Integer.parseInt(app.getCertCount());
			}
			map.put("count", count);
			map.put("certCount", certCount);
			map.put("countMap", countMap);
			map.put("certMap", certMap);
			map.put("listInfo", listInfo);
			map.put("listInfoMap", listInfoMap);
			map.put("listInfoCertMap", listInfoCertMap);

			JSONUtils.toJSON(response, CodeUtils.SUCCESS, map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR, e.getMessage());
		}
	}

	/**
	 * 查询审核统计命中用户列表
	 *
	 * @param request
	 * @param response
	 * @param application
	 */
	@RequestMapping(value = "/getAllCustomerInfo.do", method = RequestMethod.POST)
	public void getAllCustomerInfo(HttpServletRequest request, HttpServletResponse response, Application application) {

		try {
			int count = applicationservice.findAllCustomerCount(application);
			List<Application> list = applicationservice.findAllCustomer(application);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR, e.getMessage());
		}

	}

	/**
	 * 管理异常申请单
	 *
	 * @param request
	 * @param response
	 * @param application
	 */
	@RequestMapping(value = "/manageErrorApplication.do", method = RequestMethod.POST)
	public void manageErrorApplication(HttpServletRequest request, HttpServletResponse response,
                                       Application application) {

		try {
			application.setAuditMan(TokenManager.getUser().getId().toString());
			boolean flag = applicationservice.updateEntity(application);
			if (flag) {
				scAlarmImpl.contains(AlarmCodeEnum.SYSTEM_CONFIG,"人工处理异常申请单，申请单ID："
						+ application.getApplicationId() + "; 处理人用户编号：" + application.getAuditMan(), AlarmThirdPartyCreditInvestigationEnum.SYSTEM_CONFIG);
//				DingdingUtil.setMessage(Constants.WEB_DD_SYS_URL_CODE,
//						"系统时间：" + DateUtils.parseToDateTimeStr(new Date()) + "; 人工处理异常申请单，申请单ID："
//				+ application.getApplicationId() + "; 处理人用户编号：" + application.getAuditMan());

				JSONUtils.toJSON(response, CodeUtils.UPDATE_SUCCESS);
				return;
			} else {
				JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR, e.getMessage());
		}

	}

	/**
	 * 通过率查询
	 *
	 * @param request
	 * @param response
	 * @param application
	 */
	@RequestMapping(value = "/getPassingRate.do", method = RequestMethod.POST)
	public void getPassingRate(HttpServletRequest request, HttpServletResponse response,
                               ApplicationPersonCount applicationPersonCount) {

		try {
			List<ApplicationPersonCount> list = applicationservice.getPassingRate(applicationPersonCount);
			JSONUtils.toListJSON(response, list, list.size());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR, e.getMessage());
		}

	}

	/**
	 * 查询命中小类评分规则的所有申请单信息
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getApplicationFromScoreDetailSmallStatistics.do", method = RequestMethod.POST)
	public void getApplicationFromScoreDetailSmallStatistics(HttpServletRequest request, HttpServletResponse response,
                                                             Application application) {
		try {
			int count = applicationservice.getApplicationFromScoreDetailSmallStatisticsCount(application);
			List<Application> list = applicationservice.getApplicationFromScoreDetailSmallStatistics(application);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 查询命中小类欺诈分规则的所有申请单信息
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getApplicationFromFraudDetailSmallStatistics.do", method = RequestMethod.POST)
	public void getApplicationFromFraudDetailSmallStatistics(HttpServletRequest request, HttpServletResponse response,
                                                             Application application) {
		try {
			int count = applicationservice.getApplicationFromFraudDetailSmallStatisticsCount(application);
			List<Application> list = applicationservice.getApplicationFromFraudDetailSmallStatistics(application);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 系统审批[案件一览]相关方法
	 * @param request
	 * @param response
	 * @param application
	 */
	@RequestMapping(value = "/findApproveInfo.do", method = RequestMethod.POST)
	public void findApproveInfo(HttpServletRequest request, HttpServletResponse response, Application application) {
		try {
			int count = applicationservice.findApproveInfoCount(application);
			logger.info("案件一览count"+count);
			List<Map<String, Object>>  list = applicationservice.findApproveInfo(application);
			logger.info("案件一览list"+list.size());
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}


	/**
	 *系统审批案  公共案件池相关方法
	 * @param request
	 * @param response
	 * @param application
	 */
	@RequestMapping(value = "/findPublicApprove.do", method = RequestMethod.POST)
	public void findPublicApprove(HttpServletRequest request, HttpServletResponse response, Application application) {

		try { 
		    application.setStartTime(application.getStartTime());
		    application.setEndTime(application.getEndTime());
		    
		    //正常案件池
			int ajCount = applicationservice.findAjCount(application);
			logger.info("当日公共案件总数为:" + ajCount);
			int ajUntrCount = applicationservice.findAjUntrCount(application);
			logger.info("当日待人工确认的案件数为:" + ajUntrCount);
			int ajClCount = applicationservice.findAjClCount(application);
			logger.info("当日人工已确认的案件数为:" + ajClCount);
			//搜索具体的案件
			List<Application> list = applicationservice.findPublicApprove(application);
			logger.info("公共案件池信息为:" + list.size());
			
			//历史剩余案件
			ApplicationCount applicationCount = new ApplicationCount();
			
			String finYesterdayMorning = DateUtils.finLastSevenDaysMorning();
			String finYesterdayNight = DateUtils.finYesterdayNight();
			
			applicationCount.setStartTime(finYesterdayMorning);
			applicationCount.setEndTime(finYesterdayNight);
			
			int historicalSurplusCount = applicationservice.getHistoricalSurplusCount(applicationCount);
			logger.info("七天内人工未处理案件件数为:" + historicalSurplusCount);
			
			//异常案件池
			int ycCount = applicationservice.findYcCount(application);
			logger.info("当日异常案件池数为:" + ycCount);
			int ycUntrCount = applicationservice.findYcUntrCount(application);
			logger.info("当日异常未处理案件件数为:" + ycUntrCount);
			int ycClCount = applicationservice.findYcClCount(application);
			logger.info("当日异常已处理案件件数为:" + ycClCount);
			
			
			//异类案件池
			int ylCount = applicationservice.findYlCount(application);
			logger.info("当日异类案件池数为:" + ylCount);
			int ylUntrCount = applicationservice.findYlUntrCount(application);
			logger.info("当日异类未处理案件件数为:" + ylUntrCount);
			int ylClCount = applicationservice.findYlClCount(application);
			logger.info("当日异类已处理案件件数为:" + ylClCount);
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map.put(Constants.MSG, "获取成功");
			map.put(Constants.REL, true);
			map.put("list", list);
			int count = ajUntrCount+ycUntrCount+ylUntrCount;//
			map.put("count", count);// 分页用，
			
			map.put("ajCount", ajCount);// 查询申请单表公共案件总条数
			map.put("ajUntrCount", ajUntrCount);// 待人工确认的案件数
			map.put("ajClCount", ajClCount);// 人工确认的案件数
			map.put("ycCount", ycCount);// 当日异常案件池数
			map.put("ycUntrCount", ycUntrCount);//当日异常未处理案件件数
			map.put("ycClCount", ycClCount);// 当日异常已处理案件件数
			map.put("ylCount", ylCount);//当日异类案件池数
			map.put("ylUntrCount", ylUntrCount);//当日异类未处理案件件数
			map.put("ylClCount", ylClCount);//当日异类已处理案件件数
			map.put("historicalSurplusCount",historicalSurplusCount);//昨日剩余案件件数
			SpringUtils.renderJson(response, map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 个人案件
	 * @param request
	 * @param response
	 * @param application
	 */
	@RequestMapping(value = "/findPrivateApprove.do", method = RequestMethod.POST)
	public void findPrivateApprove(HttpServletRequest request, HttpServletResponse response, Application application) {

		try {  
		    application.setStartTime(application.getStartTime());
		    application.setEndTime(application.getEndTime());
			 int count = applicationservice.findPrivateApproveCount(application);
			 logger.info("个人案件count"+count);
			 List<Application> list = applicationservice.findPrivateApproveApprove(application);
			 int grCount = applicationservice.findGrCount(application);
			 int grClAjCount = applicationservice.findGrClAjCount(application); 
			 logger.info("个人案件list"+list.size());
			 LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				map.put(Constants.MSG, "获取成功");
				map.put(Constants.REL, true);
				map.put("list", list);
				map.put("count", count);// 分页用，
				map.put("grCount", grCount);//个人案件领取总条数
				map.put("grClAjCount", grClAjCount);//个人当日处理案件总条数 
				SpringUtils.renderJson(response, map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}


	/**
	 * 审批系统批量取件相关方法
	 * @param request
	 * @param response
	 * @param application
	 */
	@RequestMapping(value = "/updateBatch.do", method = RequestMethod.POST)
	public void updateBatch(HttpServletRequest request, HttpServletResponse response, Application application) {
		try {
				if(  application != null){
					logger.info("审批系统批量取件"+application.toString());
					boolean flag = applicationservice.updateBatchAuditMan(application);
					logger.info("审批系统批量取件"+flag);
					if(flag){
						JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
						return;
					}else{
						JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
						return;
					}
				}else{
					JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
					return;
				}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}


	/**
	 * 个人详情案例
	 * @param request
	 * @param response
	 * @param application
	 */
	@RequestMapping(value = "/findApproveDetails.do", method = RequestMethod.POST)
	public void findApproveDetails(HttpServletRequest request, HttpServletResponse response, Application application) {
		try {
			 Application applicationInfo = applicationservice.getEntityByIds(application);
			 logger.info("个人案件applicationInfo"+applicationInfo.toString());
			 JSONUtils.toJSON(response, CodeUtils.SUCCESS, applicationInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}


	/**
	 * 更新经办人为空
	 * @param request
	 * @param response
	 * @param application
	 */
	@RequestMapping(value = "/updatePrivateApprove.do", method = RequestMethod.POST)
	public void updatePrivateApprove(HttpServletRequest request, HttpServletResponse response, Application application) {
		try {
			application.setAuditMan("");
			logger.info("退回公共案件池更新经办人为空"  );
			boolean flag = applicationservice.updateEntity(application);
			if(flag){
				JSONUtils.toJSON(response, CodeUtils.UPDATE_SUCCESS);
				return;
			}else {
				JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
				return;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	/**
	 * 统计当前纬度审核数
	 *
	 * @param request
	 * @param response
	 * @param application
	 */
	@RequestMapping(value = "/getStatisticalAuditNumber.do", method = RequestMethod.POST)
	public void getStatisticalAuditNumber(HttpServletRequest request, HttpServletResponse response,
                                          ApplicationPersonCount applicationPersonCount) {

		try {
			List<ApplicationPersonCount> list = applicationservice.getStatisticalAuditNumber(applicationPersonCount);
			JSONUtils.toListJSON(response, list, list.size());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR, e.getMessage());
		}

	}

	/**
	 * 统计人工审核每个人的审核通过数、未通过数和通过率
	 *
	 * @param request
	 * @param response
	 * @param application
	 */
	@RequestMapping(value = "/getRgApprovalRate.do", method = RequestMethod.POST)
	public void getRgApprovalRate(HttpServletRequest request, HttpServletResponse response,
                                  ApplicationPersonCount applicationPersonCount) {

		try {
			List<ApplicationPersonCount> list = applicationservice.getRgApprovalRate(applicationPersonCount);
			JSONUtils.toListJSON(response, list, list.size());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR, e.getMessage());
		}

	}
	/**
	 * 统计提现流程每个步骤待审批的单子 
	 * @param response
	 * @param applicationCount
	 */
	@GetMapping("/txCountStepNumber.htm")
	public void txCountStepNumber(HttpServletResponse response, ApplicationCount applicationCount){
		try {
			List<ApplicationCount> list = applicationservice.txCountStepNumber(applicationCount);
			JSONUtils.toListJSON(response, list, 0);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR, e.getMessage());
		}
	}
	
	/**
	 * 提现统计查询审核结果
	 *
	 * @param request
	 * @param response
	 * @param applicationCount
	 */
	@RequestMapping(value = "/queryTxResultCount.do", method = RequestMethod.POST)
	public void queryTxResultCount(HttpServletRequest request, HttpServletResponse response,
                                   ApplicationCount applicationCount) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//审核状态统计
			List<ApplicationCount> list = applicationservice.queryTxResultCount(applicationCount);
			map.put("list", list);
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR, e.getMessage());
		}

	}
	
	/**
	 * 获取用户个人信息提交时长查询
	 * @param request
	 * @param response
	 * @param application
	 */
	@RequestMapping(value = "/getTimes.do", method = RequestMethod.POST)
	public void getTimes(HttpServletRequest request, HttpServletResponse response, Application application) {
		try { 
			SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.FIND_TIMES);
			if ( sysParam == null ) {
				logger.error("从系统参数表获取用户个人信息提交时长查询URL失败, key : FIND_TIMES");
				throw new RuntimeException("从系统参数表获取用户个人信息提交时长查询URL失败, key : FIND_TIMES");
			}
			String url = sysParam.getParamValueOne();
			logger.info("获取用户个人信息提交时长查询接口,url=" + url);

			// 设置请求参数
			Map<String, String> param = new HashMap<String, String>();
			param.put("customerId", application.getCustomerId());
			param.put("app_name", application.getAppName());
			logger.info("获取用户个人信息提交时长查询接口请求参数：" + param.toString());

			// 设置requestconfig超时时间
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000)
					.setConnectionRequestTimeout(5000)
					.setSocketTimeout(10000).build();

			// 获取提交时长数据
			Object resultString = RiskHttpClientUtil.doPost(url, param, requestConfig);
			logger.info("获取用户个人信息提交时长查询接口返回信息"+resultString);
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, resultString);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR, e.getMessage());
		}
	}
}