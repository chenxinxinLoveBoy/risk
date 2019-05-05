package com.shangyong.backend.controller;

import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.controller.BaseController;
import com.shangyong.backend.common.enums.alarm.AlarmCodeEnum;
import com.shangyong.backend.common.enums.alarm.AlarmThirdPartyCreditInvestigationEnum;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.entity.ScBanControl;
import com.shangyong.backend.entity.ScBanControlTpl;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.IScAlarm;
import com.shangyong.backend.service.impl.ScBanControlServiceImpl;
import com.shangyong.backend.service.impl.ScTemplateServiceImpl;
import com.shangyong.backend.service.impl.SysParamRedisServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.backend.utils.RiskHttpClientUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/backend/scBanControl/")
public class ScBanControlController extends BaseController {
	private static Logger logger = Logger.getLogger(ScBanControlController.class);
	@Autowired
	private ScBanControlServiceImpl scBanControlService;
	@Autowired
	private SysParamRedisServiceImpl sysParamRedisServiceImpl;
	@Autowired
	private IScAlarm scAlarmImpl;
	@Autowired
	private ScTemplateServiceImpl scTemplateServiceImpl;
	/**
	 * 规则配置集合
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getAllScBanControl.do", method = RequestMethod.POST)
	public void getAllScBanControl(HttpServletRequest request, HttpServletResponse response,
                                   ScBanControl scBanControl) {
		try {
			int count = scBanControlService.listAllCount(scBanControl);// 统计总条数
			List<ScBanControl> list = scBanControlService.findAll(scBanControl);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 根据id获取对象信息
	 * 
	 * @param request
	 * @param response
	 * @param scBanControl
	 */

	@RequestMapping(value = "getEntityById.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, ScBanControl scBanControl) {
		try {
			ScBanControl scBanControlBo = scBanControlService.getEntityById(scBanControl.getBanControlId());
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, scBanControlBo, "scBanControlObject");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 新增/修改
	 * 
	 * @param request
	 * @param response
	 * @param scBanControl
	 */

	@RequestMapping(value = "saveScBanControl.do")
	public void saveScBanControl(HttpServletRequest request, HttpServletResponse response, ScBanControl scBanControl) {
		try {
			if (scBanControl != null && scBanControl.getBanControlId() != null
					&& !"".equals(scBanControl.getBanControlId())) {// 修改
				
				// 需要先判断当前页面的版本号是否与当前数据的一致，如果不一致则表示有另一人已经修改，而当前的页面数据不是最新
				if(scBanControl.getVersion() == null){ // 版本号
					JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
					return;
				}
				
				// 拿到数据里的数据信息
				ScBanControl scBan = scBanControlService.getEntityById(scBanControl.getBanControlId());
				if(scBan == null){
					JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
					return;
				}
				
				// 当前页面的版本号与数据库的版本号一致
				if(scBan.getVersion() != null  && scBanControl.getVersion() == scBan.getVersion()){
					UUserBo user = TokenManager.getUser();
					if (user != null) {
						scBanControl.setModifyMan(user.getId().toString());
						scBanControl.setModifyName(user.getNickName());
					} else {
						scBanControl.setModifyMan("");
						scBanControl.setModifyName("");
					}

					//一个有效的禁止项模板下的所有禁止项至少有一条生效
					String banControlTplId=scBanControl.getBanControlTplId();
					ScBanControlTpl scBanControlTpl=new ScBanControlTpl();
					scBanControlTpl.setBanControlTplId(Integer.parseInt(banControlTplId));
					ScBanControlTpl info = scTemplateServiceImpl.getEntityById(scBanControlTpl);
					if (info != null && Constants.STATE_NORMAL.equals(info.getState())) { // 判断要修改的禁止项的所属模板是否有效
						if (Constants.STATE_FORBIDDEN.equals(scBanControl.getState())) {
							// 查询模板下有效的禁止项数量
							ScBanControl tpl = new ScBanControl();
							tpl.setBanControlTplId(banControlTplId);
							tpl.setState(Constants.STATE_NORMAL);
							List<ScBanControl> list = scBanControlService.findTemplate(tpl);
							if (list != null && list.size() != 0) {
								if (list.size() == 1) {
									if (list.get(0).getBanControlId().equals(scBanControl.getBanControlId())) {
										JSONUtils.toJSON(response, CodeUtils.FORBIDDEN_FAIL);
										return;
									}
								}
							} else {
								JSONUtils.toJSON(response, CodeUtils.FORBIDDEN_FAIL);
								return;
							}
						}
					}
 					
					boolean flag = scBanControlService.updateEntity(scBanControl);
					if (flag) {
						String banAppUrl = "";
						String banCode = scBanControl.getBanCode();
						try {
							if ("1".equals(scBanControl.getBanControlTplId())) {// 当修改禁止项模板时
								if ("0100004".equals(banCode) || "0100005".equals(banCode) || "0100006".equals(banCode)) {
									SysParam sysParam = sysParamRedisServiceImpl.querySysParamByParamValueRedis(Constants.BAN_APP_URL);
									banAppUrl = sysParam.getParamValueOne();
	 								Map<String, String> param = new HashMap<String, String>();
									param.put("creditType", banCode);
									param.put("score", scBanControl.getRuleComparisonValue());
									RiskHttpClientUtil.doGet(banAppUrl, param, RequestConfig.custom().setConnectTimeout(3000).setConnectionRequestTimeout(3000).setSocketTimeout(3000).build());
								}
	  						}
						} catch (Exception e) {
							scAlarmImpl.contains(AlarmCodeEnum.SYSTEM_CONFIG,"禁止项【编号:"+banCode+"】修改时同步到客户端异常,调用url:"+banAppUrl+";"+ e.getMessage(), AlarmThirdPartyCreditInvestigationEnum.SYSTEM_CONFIG);
//							DingdingUtil.setMessage(Constants.WEB_DD_SYS_URL_CODE, "系统时间：" + DateUtils.parseToDateTimeStr(new Date()) + ";禁止项【编号:"+banCode+"】修改时同步到客户端异常,调用url:"+banAppUrl+";"+ e.getMessage());
							logger.error("禁止项修改时调用客户端url:"+banAppUrl+"接口异常：" + e.getMessage());
							e.printStackTrace();
 						}
						
						JSONUtils.toJSON(response, CodeUtils.UPDATE_SUCCESS);
						return;
					} else {
						JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
						return;
					}
				}else{// 不一致
					JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
					return;
				}
			} else {// 新增
				ScBanControl scBanControlBo = scBanControlService.queryByBanCodeAndTplId(scBanControl.getBanCode());
				if (scBanControlBo == null) {// 该参数记录不存在，执行新增操作
					boolean flag = scBanControlService.saveEntity(scBanControl);
					if (flag) {// 成功
						JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
						return;
					} else {// 失败
						JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
						return;
					}
				} else {// 该参数记录已存在，直接返回
					JSONUtils.toJSON(response, CodeUtils.SYS_PARAM_FAIL);
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 查询所有数据不分页
	 * @param request
	 * @param response
	 * @param scBanControl
	 */
	@RequestMapping(value = "getAll.do", method = RequestMethod.POST)
	public void getAll(HttpServletRequest request, HttpServletResponse response,
                       ScBanControl scBanControl) {
		try {
			List<ScBanControl> list = scBanControlService.getAll(scBanControl);
			JSONUtils.toListJSON(response, list, list.size());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	 
	/**
	 * 查询模板信息
	 * @param request
	 * @param response
	 * @param scBanControl
	 */
	@RequestMapping(value = "getAllTemplate.do", method = RequestMethod.POST)
	public void getAllTemplate(HttpServletRequest request, HttpServletResponse response,
                               ScBanControl scBanControl) {
		try { 
			int count = scBanControlService.listAllTemplateCount(scBanControl);// 统计模板总条数
			List<ScBanControl> list = scBanControlService.findAllTemplate(scBanControl);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	
	/**
	 * 批量添加信息
	 * @param request
	 * @param response
	 * @param id 			模板id
	 * @param scBanControl
	 */
	@RequestMapping(value = "saveScBanControlBatch.do", method = RequestMethod.POST)
	public void saveScBanControlBatch(HttpServletRequest request, HttpServletResponse response, String banControlTplId , ScBanControl scBanControl) {
		try {  
				if(StringUtils.isNotBlank(banControlTplId) && scBanControl != null){
					boolean flag = scBanControlService.saveEntityBatch(scBanControl);
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
	 * 当前模板下关联的禁止项
	 * @param request
	 * @param response
	 * @param scBanControl
	 */
	@RequestMapping(value = "getTemplate.do", method = RequestMethod.POST)
	public void getTemplate(HttpServletRequest request, HttpServletResponse response,
                            ScBanControl scBanControl) {
		try { 
			int count = scBanControlService.listTemplateCount(scBanControl);// 统计当前模板总条数
			List<ScBanControl> list = scBanControlService.findTemplate(scBanControl);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
}
