package com.shangyong.backend.common.controller;


import com.shangyong.backend.bo.ScScheduleJobBo;
import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.enums.alarm.AlarmCodeEnum;
import com.shangyong.backend.common.enums.alarm.AlarmThirdPartyCreditInvestigationEnum;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.entity.ScScheduleJob;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.IScAlarm;
import com.shangyong.backend.service.ScheduleJobService;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.utils.*;
import com.shangyong.exception.CalfException;
import com.shangyong.utils.ResultUtils;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/quartzTask")
public class JobTaskController {
	
	private Logger logger = LoggerFactory.getLogger(JobTaskController.class);
	
	@Autowired
	private ScheduleJobService scheduleJobService;
	
	@Autowired
	private SysParamRedisService sysParamRedisService;

	@Autowired
	private IScAlarm scAlarmImpl;
	
	/**
	 * 任务列表〃
	 * @param request
	 * @return
	 */
	@RequestMapping("taskList")
	@ResponseBody
	public Object taskList(HttpServletRequest request, String jobGroup) {
		ScScheduleJob scheduleJob = new ScScheduleJob();
		if (StringUtils.isNotBlank(jobGroup)) {
			scheduleJob.setJobGroup(jobGroup);
		}
		List<ScScheduleJob> taskList = scheduleJobService.listByJob4DB(scheduleJob);
		return ResultUtils.resultSuccess(taskList);
	}
	
	/**
	 * 任务列表
	 * @param request
	 * @return
	 */
	@RequestMapping("taskListByPage")
	@ResponseBody
	public void taskPageList(HttpServletRequest request, HttpServletResponse response, ScScheduleJobBo scScheduleJobBo) {
		
//		ScScheduleJobBo scBo = new ScScheduleJobBo();
//		if (StringUtils.isNotBlank(scScheduleJobBo.getJobGroup())) {
//			scScheduleJobBo.setJobGroup(scScheduleJobBo.getJobGroup());
//		}
//		if (StringUtils.isNotBlank(scScheduleJobBo.getArtifactId())) {
//			scScheduleJobBo.setArtifactId(scScheduleJobBo.getArtifactId());
//		}
//		if (StringUtils.isNotBlank(scScheduleJobBo.getBeanClass())) {
//			scScheduleJobBo.setBeanClass(scScheduleJobBo.getBeanClass());
//		}
//		if (StringUtils.isNotBlank(scScheduleJobBo.getSpringId())) {
//			scScheduleJobBo.setSpringId(scScheduleJobBo.getSpringId());
//		}
//		if (StringUtils.isNotBlank(scScheduleJobBo.getMethodName())) {
//			scScheduleJobBo.setMethodName(scScheduleJobBo.getMethodName());
//		}
		if (StringUtils.isNotBlank(scScheduleJobBo.getJobStatus()) && !"2".equals(scScheduleJobBo.getJobStatus())) {
			scScheduleJobBo.setJobStatus(scScheduleJobBo.getJobStatus());
		}else{
			scScheduleJobBo.setJobStatus("");
		}
//		scBo.setPageIndex(scScheduleJobBo.getPageIndex());
//		scBo.setPageSize(scScheduleJobBo.getPageSize());
		Map taskListMap = scheduleJobService.listByPage(scScheduleJobBo);
		JSONUtils.toListJSON(response, taskListMap.get("list"), (Integer)taskListMap.get("count"));
	}

	/**
	 * 新增任务
	 * @param request
	 * @param scheduleJob
	 * @return
	 */
	@RequestMapping("addTask")
	@ResponseBody
	public Object addTask(HttpServletRequest request, ScScheduleJob scheduleJob) {
		try {
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
		} catch (Exception e) {
			return ResultUtils.resultFail(new CalfException(CodeUtils.BACKEND_OPT_ERROR.getCode(), "cron表达式有误，不能被解析！"));
		}
		
		Object obj = null;
		try {
			if (StringUtils.isNotBlank(scheduleJob.getSpringId())) {
				obj = SpringIocUtils.getBean(scheduleJob.getSpringId());
			} else {
				Class clazz = Class.forName(scheduleJob.getBeanClass());
				obj = clazz.newInstance();
			}
		} catch (Exception e) {
			logger.info("-->>addTask reflact class error", e);
		}
		if (obj == null) {
			return ResultUtils.resultFail(new CalfException(CodeUtils.BACKEND_OPT_ERROR.getCode(), "未找到目标类！"));
		} else {
			Class clazz = obj.getClass();
			Method method = null;
			try {
				method = clazz.getMethod(scheduleJob.getMethodName(), null);
			} catch (Exception e) {}
			if (method == null) {
				return ResultUtils.resultFail(new CalfException(CodeUtils.BACKEND_OPT_ERROR.getCode(), "未找到目标方法！"));
			}
		}
		
		try {
			scheduleJobService.saveJob2DB(scheduleJob);
		} catch (Exception e) {
			logger.info("-->>addTask error", e);
			return ResultUtils.resultFail(new CalfException(CodeUtils.BACKEND_OPT_ERROR.getCode(), "保存失败，检查 name group 组合是否有重复！"));
		}
		
		try {
			this.dingdingmsg("风控定时任务JobTaskController addTask 添加定时任务, 参数scheduleJob json:" + JsonUtil.toJson(scheduleJob));
		} catch(Exception e) {
			logger.info("-->>addTask dingdingmsg error", e);
			logger.error("-->>addTask dingdingmsg error", e);
		}
		return ResultUtils.resultSuccess(CodeUtils.SUCCESS);
	}

	/**
	 * 改变任务状态
	 * @param request
	 * @param jobId
	 * @param cmd
	 * @return
	 */
	@RequestMapping("changeJobStatus")
	@ResponseBody
	public Object changeJobStatus(HttpServletRequest request, String jobId, String cmd) {
		try {
			scheduleJobService.changeStatus(jobId, cmd);
		} catch (Exception e) {
			logger.info("-->>changeJobStatus error", e);
			this.dingdingmsg("风控定时任务JobTaskController changeJobStatus 更新任务状态失败, 参数jobId:" + jobId + ", cmd:" + cmd);
			return ResultUtils.resultFail(new CalfException(CodeUtils.BACKEND_OPT_ERROR.getCode(), "任务状态改变失败！"));
		}
		this.dingdingmsg("风控定时任务JobTaskController changeJobStatus 更新任务状态成功, 参数jobId:" + jobId + ", cmd:" + cmd);
		return ResultUtils.resultSuccess(CodeUtils.SUCCESS);
	}

	/**
	 * 更新任务cron表达式
	 * @param request
	 * @param jobId
	 * @param cron
	 * @return
	 */
	@RequestMapping("updateCronExp")
	@ResponseBody
	public Object updateCronExp(HttpServletRequest request, String jobId, String cronExp) {
		try {
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExp);
		} catch (Exception e) {
			this.dingdingmsg("风控定时任务JobTaskController updateCronExp 更新任务cron表达式失败, cron表达式有误，不能被解析, 参数jobId:" + jobId + ", cronExp:" + cronExp);
			return ResultUtils.resultFail(new CalfException(CodeUtils.BACKEND_OPT_ERROR.getCode(), "cron表达式有误，不能被解析！"));
		}
		try {
			scheduleJobService.updateCronExp(jobId, cronExp);
		} catch (Exception e) {
			this.dingdingmsg("风控定时任务JobTaskController updateCronExp 更新任务cron表达式失败, 参数jobId:" + jobId + ", cronExp:" + cronExp);
			return ResultUtils.resultFail(new CalfException(CodeUtils.BACKEND_OPT_ERROR.getCode(), "cron更新失败！"));
		}
		this.dingdingmsg("风控定时任务JobTaskController updateCronExp 更新任务cron表达式, 操作成功, 参数jobId:" + jobId + ", cronExp:" + cronExp);
		return ResultUtils.resultSuccess(CodeUtils.SUCCESS);
	}
	
	/**
	 * 更新任务cron表达式
	 * @param request
	 * @param jobId
	 * @param cron
	 * @return
	 */ 
	@RequestMapping("deleteJobByJobId")
	@ResponseBody
	public Object deleteJobByJobId(HttpServletRequest request, ScScheduleJobBo jobBo) {
		try {
			int res = scheduleJobService.deleteJobByJobId(jobBo);
		} catch (Exception e) {
			this.dingdingmsg("风控定时任务JobTaskController deleteJobByJobId 删除定时任务, 操作失败，参数jobBo:" + JsonUtil.toJson(jobBo));
			return ResultUtils.resultFail(new CalfException(CodeUtils.BACKEND_OPT_ERROR.getCode(), "删除失败！"));
		}
		this.dingdingmsg("风控定时任务JobTaskController deleteJobByJobId 删除定时任务, 操作成功，参数jobBo:" + JsonUtil.toJson(jobBo));
		return ResultUtils.resultSuccess(CodeUtils.SUCCESS);
	}
	
	@RequestMapping(value = "sendHttp")
	@ResponseBody
	public void sendHttp(HttpServletRequest request, HttpServletResponse response) {
		try {
			String httpurl = request.getParameter("httpurl");
			String data = request.getParameter("data");
			String artifactId = request.getParameter("artifactId");
			logger.info("-->>httpurl:" + httpurl + ", artifactId:" + artifactId +", data:" + data);
			if (StringUtils.isNotBlank(httpurl)) {
				Map para = null;
				if (StringUtils.isNotBlank(data)) {
					para = JsonUtil.toObject(data, Map.class);
				}
				
				SysParam sysParam = null;
				try {
					sysParam = sysParamRedisService.querySysParamByParamValueRedis(artifactId);
				} catch (Throwable e) {
					logger.info("-->>querySysParamByParamValueRedis error," + e.getMessage(), e);
				}
				
				if (sysParam != null) {
					String httpurlPrefix = sysParam.getParamValueOne();
					httpurl = httpurlPrefix + httpurl;
					logger.info("-->>-->>组装后httpurl" + httpurl);
					String httpRes = RiskHttpClientUtil.doGet(httpurl, para);
					logger.info("-->>sendHttp--httpRes:" + httpRes);
					JSONUtils.toJSON(response, CodeUtils.SUCCESS, JsonUtil.toObject(httpRes, Map.class)); 
					this.dingdingmsg("调用风控定时任务管理  JobTaskController sendHttp success, 项目名" + artifactId);
					return;
				}
				
				logger.info("-->>-->>未查询到sysParam数据 artifactId" + artifactId);
				this.dingdingmsg("调用风控定时任务管理 sendHttp方法  errorJobTaskController sendHttp error,项目名 " + artifactId);
			}
		} catch (Exception e) {
			logger.info("-->>JobTask--sendHttp--error", e);
			this.dingdingmsg("调用风控定时任务管理 sendHttp方法  error" + e.getMessage());
		}
		
		JSONUtils.toJSON(response, CodeUtils.BACKEND_OPT_ERROR);
	}
	
	/**
	 * 钉钉报警
	 * @param params
	 */
	private void dingdingmsg(String content) {  
		try {
//			UUserBo u = TokenManager.getUser();
//			scAlarmImpl.contains(AlarmCodeEnum.BACKEND_WEB,"用户编号：" + "["+u.getId()+"]" + ", 用户登录账号:" +"["+ u.getUserName()+"]" +", 用户姓名：" + "["+u.getNickName()+"]" + ", 当前操作时间："  +"["+DateUtils.parseToDateTimeStr(new Date())+"]" +  ","+ content, AlarmThirdPartyCreditInvestigationEnum.BACKEND_WEB);
//			String result = DingdingUtil.setMessage(Constants.DD_URL_CODE,  "用户编号：" + "["+u.getId()+"]" + ", 用户登录账号:" +"["+ u.getUserName()+"]" +", 用户姓名：" + "["+u.getNickName()+"]" + ", 当前操作时间："  +"["+DateUtils.parseToDateTimeStr(new Date())+"]" +  ","+ content);
//			logger.info("钉钉返回信息: "+ result );
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
