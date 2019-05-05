package com.shangyong.backend.controller;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.*;
import com.shangyong.backend.service.impl.*;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.utils.MD5Utils;
import com.shangyong.utils.SpringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;


@Controller
@RequestMapping(value = "/backend/scDecisionTree")
public class ScDecisionTreeController {

	@Autowired
	private ScDecisionTreeServiceImpl scDecisionTreeService;
	@Autowired
	private ScFraudRuleTplServiceImpl scFraudRuleTplService;
	@Autowired
	private ScTemplateServiceImpl scTemplateService;
	@Autowired
	private ScScoreTplServiceImpl scScoreTplService;
	private static Logger logger = LoggerFactory.getLogger(ScDecisionTreeController.class);
 	@Autowired
	private SysParamRedisServiceImpl sysParamRedisServiceImpl;
	private static Logger switchDecisionTreeLogger = (Logger) LoggerFactory.getLogger("switchDecisionTree");

	/**
	 * 获取决策树列表
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/scDecisionTreeListAll.do", method = RequestMethod.POST)
	public void scDecisionTreeListAll(HttpServletRequest request, HttpServletResponse response, ScDecisionTree scDecisionTree) {
		try {
			int count = scDecisionTreeService.listAllCount(scDecisionTree);
			List<ScDecisionTree> list = scDecisionTreeService.findAll(scDecisionTree);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 保存/更新
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/saveScDecisionTree.do", method = RequestMethod.POST)
	public void saveScDecisionTree(HttpServletRequest request, HttpServletResponse response, ScDecisionTree scDecisionTree) {
		try {
			String defaultTreeTemplateId = "1";
			SysParam sysParam = sysParamRedisServiceImpl
					.querySysParamByParamValueRedis(Constants.DEFAULT_TREE_TEMPLATE_ID_REDIS_KEY_NAME);
			defaultTreeTemplateId = sysParam.getParamValueOne(); // 默认模板ID
			String decisionTreeId = "";
			if (scDecisionTree.getDecisionTreeId() != null && !"".equals(scDecisionTree.getDecisionTreeId())) {
				decisionTreeId = scDecisionTree.getDecisionTreeId().toString();
			}
			double SumTplPercent = scDecisionTreeService.getSumTplPercent(defaultTreeTemplateId, decisionTreeId);
			String tplPercent = scDecisionTree.getTplPercent();
			if (StringUtils.isNotEmpty(tplPercent) && Constants.STATE_NORMAL.equals(scDecisionTree.getState())) {
				if (Double.parseDouble(tplPercent) + SumTplPercent > 1) {// 有效规则配置权重总和不能超过100%
					JSONUtils.toJSON(response, CodeUtils.BACKEND_OPT_FAIL);
					return;
				}
			}

			// 优先级去重
			ScDecisionTree tpl = new ScDecisionTree();
			tpl.setLevel(scDecisionTree.getLevel());
			List<ScDecisionTree> list = scDecisionTreeService.findAll(tpl);
			if (list != null && list.size() > 0) {
				if (!list.get(0).getDecisionTreeId().equals(scDecisionTree.getDecisionTreeId()) || list.size() > 1) {
					JSONUtils.toJSON(response, CodeUtils.BACKEND_REPEAT);
					return;
				}
			}

			if (scDecisionTree != null && scDecisionTree.getDecisionTreeId() != null) { // 更新
				// 需要先判断当前页面的版本号是否与当前数据的一致，如果不一致则表示有另一人已经修改，而当前的页面数据不是最新
				if (scDecisionTree.getVersion() == null) { // 版本号
					JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
					return;
				}
				ScDecisionTree info = scDecisionTreeService.getEntityById(scDecisionTree);
				if (info != null) {
					if (info.getVersion() == null
							|| !scDecisionTree.getVersion().toString().equals(info.getVersion().toString())) {
						JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
						return;
					}

					if (Constants.STATE_NORMAL.equals(scDecisionTree.getState())) {

						// 决策树下所有模板必须都要生效
						ScFraudRuleTpl scFraudRuleTpl = new ScFraudRuleTpl();
						scFraudRuleTpl.setFraudRuleTplId(scDecisionTree.getFraudRuleTplId());
						scFraudRuleTpl.setState(Constants.STATE_NORMAL);
						int count = scFraudRuleTplService.listAllCount(scFraudRuleTpl);// 统计总条数

						ScBanControlTpl scBanControlTpl = new ScBanControlTpl();
						scBanControlTpl.setBanControlTplId(scDecisionTree.getBanControlTplId());
						scBanControlTpl.setState(Constants.STATE_NORMAL);
						int count1 = scTemplateService.listAllCount(scBanControlTpl);// 统计总条数

						ScScoreTpl scScoreTpl = new ScScoreTpl();
						scScoreTpl.setScoreTplId(scDecisionTree.getScoreTplId());
						scScoreTpl.setState(Constants.STATE_NORMAL);
						int count2 = scScoreTplService.listAllCount(scScoreTpl);// 统计总条数

						if (count == 0 ||count1 == 0 || count2 == 0) {
							JSONUtils.toJSON(response, CodeUtils.TREE_FAIL);
							return;
						}
					}

					boolean flag = scDecisionTreeService.updateEntity(scDecisionTree);
					if (flag) {
						JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
						return;
					} else {
						JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
						return;
					}
				}
			} else {// 添加
				ScDecisionTree info = scDecisionTreeService.findCode(scDecisionTree);
				if (info != null) {
					String i = info.getDecisionTreeCode();
					String ii = scDecisionTree.getDecisionTreeCode();
					if (i.equals(ii)) {
						JSONUtils.toJSON(response, CodeUtils.NAME_FAIL);
						return;
					}
				}
				boolean flag = scDecisionTreeService.saveEntity(scDecisionTree);
				if (flag) {
					JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
					return;
				} else {
					JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
		}

	}

	/**
	 * 查询
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getEntityById.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, ScDecisionTree scDecisionTree) {
		try {
			if (scDecisionTree.getDecisionTreeId()!= null) {
				ScDecisionTree list = scDecisionTreeService.getEntityById(scDecisionTree);
				logger.info("查询决策树信息" + list);
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				LinkedHashMap<String, Object> map1 = new LinkedHashMap<String, Object>();
				map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
				map.put(Constants.MESSAGE, CodeUtils.SUCCESSNEWS.getMessage());
				map1.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());

				map1.put("scDecisionTreeObject", list);
				map.put(Constants.DATA, map1);
				SpringUtils.renderJson(response, map);
			} else {
				logger.error("缺少参数");
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询决策树信息异常" + e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.NON_EXISTENT);
		}

	}
	
	/**
	 * 用于大数据决策树切换
	 * @param request
	 * @param response
	 * @param accountNumber--账号
	 * @param token--加密后的秘钥
	 * @param openDecisionTreeId--打开决策树id
	 * @param closeDecisionTreeId--关闭决策树id
	 */
	@RequestMapping(value = "/switchDecisionTree.do", method = RequestMethod.POST)
	public void switchDecisionTree(HttpServletRequest request, HttpServletResponse response, String accountNumber, String token , int openDecisionTreeId, int closeDecisionTreeId ) {
		try {
			switchDecisionTreeLogger.info("当前时间：" + new Date() +"开始调用决策树切换接口,传递参数-->账号【"+accountNumber+"】,秘钥【"+token+"】,打开决策树id【"+openDecisionTreeId+"】,关闭决策树id【"+closeDecisionTreeId+"】");
			if (StringUtils.isNotEmpty(token) && StringUtils.isNotEmpty(accountNumber) && StringUtils.isNotEmpty(openDecisionTreeId + "") && StringUtils.isNotEmpty(closeDecisionTreeId + "")) {
				String token1 = MD5Utils.stringToMD5(Constants.MD5_XK+accountNumber);
				if (!token.equals(token1)) {
					switchDecisionTreeLogger.info("秘钥有误，非法访问！");
					JSONUtils.toJSON(response, CodeUtils.TOKEN_ERROR);
					return;
				}
				SysParam sysParam = sysParamRedisServiceImpl.querySysParamByParamValueRedis(Constants.DEFAULT_TREE_TEMPLATE_ID_REDIS_KEY_NAME);
				String defaultTreeTemplateId = sysParam.getParamValueOne(); // 默认模板ID
				if ((closeDecisionTreeId + "").equals(defaultTreeTemplateId)) {
					switchDecisionTreeLogger.info("默认基础决策树状态不允许修改为失效！");
					JSONUtils.toJSON(response, CodeUtils.UPDATE_TREE_FAIL);
					return;
				}
				
				// 加判断--仅需要打开的决策树状态为失效且需要关闭的决策树状态为生效时才会切换决策树
				ScDecisionTree sc1 = scDecisionTreeService.getEntityByDecisionTreeId(openDecisionTreeId + "");
				if (sc1 == null) {
					switchDecisionTreeLogger.info("需要打开的决策树不存在，决策树切换失败！");
					JSONUtils.toJSON(response, CodeUtils.OPEN_TREE_NON_EXISTENT);
					return;
				}
				if (Constants.STATE_NORMAL.equals(sc1.getState())) {
					switchDecisionTreeLogger.info("需要打开的决策树已是打开状态，决策树切换失败！");
					JSONUtils.toJSON(response, CodeUtils.OPEN_TREE_FAIL);
					return;
				}

				ScDecisionTree sc2 = scDecisionTreeService.getEntityByDecisionTreeId(closeDecisionTreeId + "");
				if (sc2 == null) {
					switchDecisionTreeLogger.info("需要关闭的决策树不存在，决策树切换失败！");
					JSONUtils.toJSON(response, CodeUtils.CLOSE_TREE_NON_EXISTENT);
					return;
				}
				if (Constants.STATE_FORBIDDEN.equals(sc2.getState())) {
					switchDecisionTreeLogger.info("需要关闭的决策树已是关闭状态，决策树切换失败！");
					JSONUtils.toJSON(response, CodeUtils.CLOSE_TREE_FAIL);
					return;
				}
					
				boolean flag = scDecisionTreeService.switchDecisionTree(openDecisionTreeId, closeDecisionTreeId);
				switchDecisionTreeLogger.info("当前时间：" + new Date() + "调用决策树切换接口结束, 调用结果response：" + flag);
				if (flag) {
					JSONUtils.toJSON(response, CodeUtils.SUCCESS);
					return;
				} else {
					JSONUtils.toJSON(response, CodeUtils.FAIL);
					return;
				}
			} else {
				switchDecisionTreeLogger.error("缺少参数，参数有空值--->账号【"+accountNumber+"】,秘钥【"+token+"】,打开决策树id【"+openDecisionTreeId+"】,关闭决策树id【"+closeDecisionTreeId+"】");
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
				return;
 			}
		} catch (Exception e) {
			e.printStackTrace();
			switchDecisionTreeLogger.error("决策树切换失败,传递参数-->账号【"+accountNumber+"】,秘钥【"+token+"】,打开决策树id【"+openDecisionTreeId+"】,关闭决策树id【"+closeDecisionTreeId+"】" + e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.FAIL);
			return;
  		}

	}
	
}
