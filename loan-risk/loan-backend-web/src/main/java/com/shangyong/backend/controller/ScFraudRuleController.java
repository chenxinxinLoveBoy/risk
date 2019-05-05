package com.shangyong.backend.controller;

import com.shangyong.backend.bo.ZTree;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.controller.BaseController;
import com.shangyong.backend.entity.ScFraudRule;
import com.shangyong.backend.entity.ScFraudRuleTpl;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.impl.ScFraudRuleServiceImpl;
import com.shangyong.backend.service.impl.ScFraudRuleTplServiceImpl;
import com.shangyong.backend.service.impl.SysParamRedisServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/backend/scFraudRule/")
public class ScFraudRuleController extends BaseController {
	private static Logger logger = Logger.getLogger(ScFraudRuleController.class);
	@Autowired
	private ScFraudRuleServiceImpl scFraudRuleService;
	@Autowired
	private ScFraudRuleTplServiceImpl scFraudRuleTplServiceImpl;
	@Autowired
	private SysParamRedisServiceImpl sysParamRedisServiceImpl;

	/**
	 * 规则配置集合
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getAllScFraudRule.do", method = RequestMethod.POST)
	public void getAllScFraudRule(HttpServletRequest request, HttpServletResponse response,
                                  ScFraudRule scFraudRule) {
		try {
			int count = scFraudRuleService.listAllCount(scFraudRule);// 统计总条数
			List<ScFraudRule> list = scFraudRuleService.findAll(scFraudRule);
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
	 * @param scFraudRule
	 */

	@RequestMapping(value = "getEntityById.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, ScFraudRule scFraudRule) {
		try {
			ScFraudRule scFraudRuleBo = scFraudRuleService.getEntityById(scFraudRule.getFraudRuleId());
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, scFraudRuleBo, "scFraudRuleObject");
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
	 * @param scFraudRule
	 */

	@RequestMapping(value = "saveScFraudRule.do")
	public void saveScFraudRule(HttpServletRequest request, HttpServletResponse response, ScFraudRule scFraudRule) {
		try {
			if (scFraudRule != null && scFraudRule.getFraudRuleId() != null
					&& !"".equals(scFraudRule.getFraudRuleId())) {// 修改
				
				// 需要先判断当前页面的版本号是否与当前数据的一致，如果不一致则表示有另一人已经修改，而当前的页面数据不是最新
				if(scFraudRule.getVersion() == null){ // 版本号
					JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
					return;
				}
				
				// 拿到数据里的数据信息
				ScFraudRule scFraudRuleOld = scFraudRuleService.getEntityById(scFraudRule.getFraudRuleId());
				if(scFraudRuleOld == null){
					JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
					return;
				}
				ScFraudRule scFraudRuleBo = scFraudRuleService.queryByFraudRuleCodeAndTplId(scFraudRule);
				if (scFraudRuleBo != null && !scFraudRuleOld.getFraudRuleCode().equals(scFraudRuleBo.getFraudRuleCode())) {
					JSONUtils.toJSON(response, CodeUtils.SYS_PARAM_FAIL);
					return;
				}
				// 当前页面的版本号与数据库的版本号一致
				if(scFraudRuleOld.getVersion() != null  && scFraudRule.getVersion() == scFraudRuleOld.getVersion()){

					//一个有效的欺诈规则项模板下的所有欺诈规则项至少有一条生效
					String fraudRuleTplId=scFraudRule.getFraudRuleTplId();
					ScFraudRuleTpl scFraudRuleTpl=new ScFraudRuleTpl();
					scFraudRuleTpl.setFraudRuleTplId(fraudRuleTplId);
					ScFraudRuleTpl info = scFraudRuleTplServiceImpl.getEntityById(scFraudRuleTpl);
					if (info != null && Constants.STATE_NORMAL.equals(info.getState())) { // 判断要修改的欺诈规则的所属模板是否有效
						if(Constants.STATE_FORBIDDEN.equals(scFraudRule.getState())){
							//查询模板下有效的欺诈规则项数量
							ScFraudRule tpl = new ScFraudRule();
							tpl.setFraudRuleTplId (fraudRuleTplId);
							tpl.setState(Constants.STATE_NORMAL);
							List<ScFraudRule> list = scFraudRuleService.findTemplate(tpl);
							if(list != null && list.size() != 0){
								if(list.size() == 1){
									if(list.get(0).getFraudRuleId().equals(scFraudRule.getFraudRuleId())){
										JSONUtils.toJSON(response, CodeUtils.FORBIDDEN_FAIL);
										return;
									}
								}
							}else{
								JSONUtils.toJSON(response, CodeUtils.FORBIDDEN_FAIL);
								return;
							}
						}
 					}
					
					boolean flag = scFraudRuleService.updateEntity(scFraudRule);
					if (flag) {
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
				List<ScFraudRule> scFraudRuleList = scFraudRuleService.queryByFraudRuleCode(scFraudRule.getFraudRuleCode());
				if (CollectionUtils.isEmpty(scFraudRuleList)) {// 该参数记录不存在，执行新增操作
					if (StringUtils.isEmpty(scFraudRule.getFraudRuleTplId())) {
						String defaultFraudTemplateId = "1";
						try {
							SysParam sysParam = sysParamRedisServiceImpl
									.querySysParamByParamValueRedis(Constants.DEFAULT_FRAUD_TEMPLATE_ID_REDIS_KEY_NAME);
							defaultFraudTemplateId = sysParam.getParamValueOne(); // 默认模板ID
							scFraudRule.setFraudRuleTplId(defaultFraudTemplateId);
						} catch (Exception e) {
							logger.info("-->>get defaultFraudTemplateId from sysparamsredis error", e);
						}
					}
					boolean flag = scFraudRuleService.saveEntity(scFraudRule);
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
//
//	/**
//	 * 查询所有数据不分页
//	 * @param request
//	 * @param response
//	 * @param scFraudRule
//	 */
//	@RequestMapping(value = "getAll.do", method = RequestMethod.POST)
//	public void getAll(HttpServletRequest request, HttpServletResponse response,
//			ScFraudRule scFraudRule) {
//		try {
////			List<ScFraudRule> list = scFraudRuleService.getAll(scFraudRule);
////			JSONUtils.toListJSON(response, list, list.size());
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage(), e);
//			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
//		}
//	}
//	 
	/**
	 * 查询模板信息
	 * @param request
	 * @param response
	 * @param scFraudRule
	 */
	@RequestMapping(value = "getAllTemplate.do", method = RequestMethod.POST)
	public void getAllTemplate(HttpServletRequest request, HttpServletResponse response,
                               ScFraudRule scFraudRule) {
		try { 
			int count = scFraudRuleService.listAllTemplateCount(scFraudRule);// 统计模板总条数
			List<ScFraudRule> list = scFraudRuleService.findAllTemplate(scFraudRule);
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
	 * @param scFraudRule
	 */
	@RequestMapping(value = "saveScFraudRuleBatch.do", method = RequestMethod.POST)
	public void saveScFraudRuleBatch(HttpServletRequest request, HttpServletResponse response, String fraudRuleTplId , ScFraudRule scFraudRule) {
		try {  
				if(StringUtils.isNotBlank(fraudRuleTplId) && scFraudRule != null){
					boolean flag = scFraudRuleService.saveEntityBatch(scFraudRule);
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
	 * @param scFraudRule
	 */
	@RequestMapping(value = "getTemplate.do", method = RequestMethod.POST)
	public void getTemplate(HttpServletRequest request, HttpServletResponse response,
                            ScFraudRule scFraudRule) {
		try { 
			int count = scFraudRuleService.listTemplateCount(scFraudRule);// 统计当前模板总条数
			List<ScFraudRule> list = scFraudRuleService.findTemplate(scFraudRule);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	
	/**
	 * 加载菜单zTree树
	 * 
	 * @param request
	 * @param response
 	 */
	@RequestMapping(value = "/getScFraudRuleTree.do")
	public void getScFraudRuleTree(HttpServletRequest request, HttpServletResponse response, String  fraudRuleTplId) {
		try {
			List<ZTree> zTreeList = scFraudRuleService.getScFraudRuleTree(fraudRuleTplId);// 查詢树
			JSONUtils.toJsonZTrr(response, zTreeList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
}
