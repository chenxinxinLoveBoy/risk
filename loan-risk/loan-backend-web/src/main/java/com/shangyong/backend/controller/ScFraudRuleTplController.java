package com.shangyong.backend.controller;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.ScDecisionTree;
import com.shangyong.backend.entity.ScFraudRule;
import com.shangyong.backend.entity.ScFraudRuleTpl;
import com.shangyong.backend.service.impl.ScDecisionTreeServiceImpl;
import com.shangyong.backend.service.impl.ScFraudRuleServiceImpl;
import com.shangyong.backend.service.impl.ScFraudRuleTplServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.utils.SpringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/backend/scFraudRuleTpl")
public class ScFraudRuleTplController {

	@Autowired
	private ScFraudRuleTplServiceImpl scFraudRuleTplServiceImpl;
	@Autowired
	private ScFraudRuleServiceImpl scFraudRuleService;
	private static Logger logger = Logger.getLogger(ScFraudRuleTplController.class);
//	@Autowired
//	private SysParamRedisServiceImpl sysParamRedisServiceImpl;
	@Autowired
	private ScDecisionTreeServiceImpl scDecisionTreeService;
	/**
	 * 获取欺诈规则模版列表
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/scFraudRuleTplListAll.do", method = RequestMethod.POST)
	public void scFraudRuleTplListAll(HttpServletRequest request, HttpServletResponse response,
                                      ScFraudRuleTpl scFraudRuleTpl) {
		try {
			int count = scFraudRuleTplServiceImpl.listAllCount(scFraudRuleTpl);
			List<ScFraudRuleTpl> list = scFraudRuleTplServiceImpl.findAll(scFraudRuleTpl);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 保存/更新欺诈规则模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/saveScFraudRuleTpl.do", method = RequestMethod.POST)
	public void saveScFraudRuleTpl(HttpServletRequest request, HttpServletResponse response,
                                   ScFraudRuleTpl scFraudRuleTpl) {
		try {
//			String defaultFraudTemplateId = "1";
//			SysParam sysParam = sysParamRedisServiceImpl.querySysParamByParamValueRedis(Constants.DEFAULT_FRAUD_TEMPLATE_ID_REDIS_KEY_NAME);
//			defaultFraudTemplateId = sysParam.getParamValueOne(); // 默认模板ID
//			String fraudRuleTplId= "";
//			if(scFraudRuleTpl.getFraudRuleTplId()!=null&&!"".equals(scFraudRuleTpl.getFraudRuleTplId())){
//				fraudRuleTplId=scFraudRuleTpl.getFraudRuleTplId().toString();
//			}
//			double SumTplPercent = scFraudRuleTplServiceImpl.getSumTplPercent(defaultFraudTemplateId,fraudRuleTplId);
//			String tplPercent = scFraudRuleTpl.getTplPercent();
//			if (StringUtils.isNotEmpty(tplPercent)&&Constants.STATE_NORMAL.equals(scFraudRuleTpl.getState())) {
//				if (Double.parseDouble(tplPercent) + SumTplPercent > 1) {// 有效规则配置权重总和不能超过100%
//					JSONUtils.toJSON(response, CodeUtils.BACKEND_OPT_FAIL);
//					return;
//				}
//			}

			//优先级去重
//			ScFraudRuleTpl tpl = new ScFraudRuleTpl();
//			tpl.setLevel(scFraudRuleTpl.getLevel());
//			List<ScFraudRuleTpl> list = scFraudRuleTplServiceImpl.findAll(tpl) ;
//			if(list != null && list.size()>0){
//				if(!list.get(0).getFraudRuleTplId().equals(scFraudRuleTpl.getFraudRuleTplId()) || list.size()>1){
//					JSONUtils.toJSON(response, CodeUtils.BACKEND_REPEAT);
//					return;	
//				}
//			}
			
			if (scFraudRuleTpl != null && scFraudRuleTpl.getFraudRuleTplId() != null && !scFraudRuleTpl.getFraudRuleTplId().equals("")) { // 更新
				// 需要先判断当前页面的版本号是否与当前数据的一致，如果不一致则表示有另一人已经修改，而当前的页面数据不是最新
				if(scFraudRuleTpl.getVersion() == null){ // 版本号
					JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
					return;
				}
				ScFraudRuleTpl info = scFraudRuleTplServiceImpl.getEntityById(scFraudRuleTpl);
				if (info != null) {
					if(info.getVersion() == null  || !scFraudRuleTpl.getVersion().toString().equals( info.getVersion().toString())){
						JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
						return;
					}
					
					if (Constants.STATE_NORMAL.equals(scFraudRuleTpl.getState())) {
						// 禁止项至少一条生效
						ScFraudRule scFraudRule = new ScFraudRule();
						scFraudRule.setFraudRuleTplId(scFraudRuleTpl.getFraudRuleTplId().toString());
						scFraudRule.setState(Constants.STATE_NORMAL);
						int count = scFraudRuleService.listAllCount(scFraudRule);// 统计总条数
						if (count == 0) {
							JSONUtils.toJSON(response, CodeUtils.FORBIDDEN_FAIL);
							return;
						}
					}
					
					if (Constants.STATE_FORBIDDEN.equals(scFraudRuleTpl.getState())) {// 模板状态修改为无效时，其被关联的所有决策树状态必须都为无效才可以修改
						ScDecisionTree scDecisionTree = new ScDecisionTree();
						scDecisionTree.setState(Constants.STATE_NORMAL);
						scDecisionTree.setFraudRuleTplId(scFraudRuleTpl.getFraudRuleTplId());
						int count = scDecisionTreeService.listAllCount(scDecisionTree);
						if (count != 0) {
							JSONUtils.toJSON(response, CodeUtils.UPDATE_TPL_FAIL);
							return;
						}
					}
					
					
					boolean flag = scFraudRuleTplServiceImpl.updateEntity(scFraudRuleTpl);
					if (flag) {
						JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
						return;
					} else {
						JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
						return;
					}
				}
			} else {// 添加
				ScFraudRuleTpl info = scFraudRuleTplServiceImpl.findCode(scFraudRuleTpl);
				if (info != null) {
					int i = info.getFraudRuleTplCode();
					int ii = scFraudRuleTpl.getFraudRuleTplCode();
					if (i == ii) {
						JSONUtils.toJSON(response, CodeUtils.NAME_FAIL);
						return;
					}
				}
				boolean flag = scFraudRuleTplServiceImpl.saveEntity(scFraudRuleTpl);
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
	 * 查询模版信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getEntityById.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, ScFraudRuleTpl scFraudRuleTpl) {
		try {
			if (scFraudRuleTpl.getFraudRuleTplId() != null) {
				ScFraudRuleTpl list = scFraudRuleTplServiceImpl.getEntityById(scFraudRuleTpl);
				logger.info("查询模版信息" + list);
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				LinkedHashMap<String, Object> map1 = new LinkedHashMap<String, Object>();
				map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
				map.put(Constants.MESSAGE, CodeUtils.SUCCESSNEWS.getMessage());
				map1.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());

				map1.put("scFraudRuleTplObject", list);
				map.put(Constants.DATA, map1);
				SpringUtils.renderJson(response, map);
			} else {
				logger.error("缺少参数");
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询模版信息异常" + e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.NON_EXISTENT);
		}

	}
}
