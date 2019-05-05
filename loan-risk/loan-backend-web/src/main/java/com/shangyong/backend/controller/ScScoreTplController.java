package com.shangyong.backend.controller;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.ScDecisionTree;
import com.shangyong.backend.entity.ScScoreSmall;
import com.shangyong.backend.entity.ScScoreTpl;
import com.shangyong.backend.service.impl.ScDecisionTreeServiceImpl;
import com.shangyong.backend.service.impl.ScScoreSmallServiceImpl;
import com.shangyong.backend.service.impl.ScScoreTplServiceImpl;
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
@RequestMapping(value = "/backend/scScoreTpl")
public class ScScoreTplController {

	@Autowired
	private ScScoreTplServiceImpl scScoreTplServiceImpl;
	@Autowired
	private ScScoreSmallServiceImpl scScoreSmallService;
	private static Logger logger = Logger.getLogger(ScScoreTplController.class);
//	@Autowired
//	private SysParamRedisServiceImpl sysParamRedisServiceImpl;
	@Autowired
	private ScDecisionTreeServiceImpl scDecisionTreeService;

	/**
	 * 获取评分模版列表
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/scScoreTplListAll.do", method = RequestMethod.POST)
	public void scScoreTplListAll(HttpServletRequest request, HttpServletResponse response, ScScoreTpl scScoreTpl) {
		try {
			int count = scScoreTplServiceImpl.listAllCount(scScoreTpl);
			List<ScScoreTpl> list = scScoreTplServiceImpl.findAll(scScoreTpl);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 保存/更新评分模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/saveScScoreTpl.do", method = RequestMethod.POST)
	public void saveScScoreTpl(HttpServletRequest request, HttpServletResponse response, ScScoreTpl scScoreTpl) {
		try {
//			String defaultScoreTemplateId = "1";
//			SysParam sysParam = sysParamRedisServiceImpl
//					.querySysParamByParamValueRedis(Constants.DEFAULT_SCORE_TEMPLATE_ID_REDIS_KEY_NAME);
//			defaultScoreTemplateId = sysParam.getParamValueOne(); // 默认模板ID
//			String scoreTplId = "";
//			if (scScoreTpl.getScoreTplId() != null && !"".equals(scScoreTpl.getScoreTplId())) {
//				scoreTplId = scScoreTpl.getScoreTplId().toString();
//			}
//			double SumTplPercent = scScoreTplServiceImpl.getSumTplPercent(defaultScoreTemplateId, scoreTplId);
//			String tplPercent = scScoreTpl.getTplPercent();
//			if (StringUtils.isNotEmpty(tplPercent) && Constants.STATE_NORMAL.equals(scScoreTpl.getState())) {
//				if (Double.parseDouble(tplPercent) + SumTplPercent > 1) {// 有效规则配置权重总和不能超过100%
//					JSONUtils.toJSON(response, CodeUtils.BACKEND_OPT_FAIL);
//					return;
//				}
//			}

			// 优先级去重
//			ScScoreTpl tpl = new ScScoreTpl();
//			tpl.setLevel(scScoreTpl.getLevel());
//			List<ScScoreTpl> list = scScoreTplServiceImpl.findAll(tpl);
//			if (list != null && list.size() > 0) {
//				if (!list.get(0).getScoreTplId().equals(scScoreTpl.getScoreTplId()) || list.size() > 1) {
//					JSONUtils.toJSON(response, CodeUtils.BACKEND_REPEAT);
//					return;
//				}
//			}

			if (scScoreTpl != null && scScoreTpl.getScoreTplId() != null) { // 更新
				// 需要先判断当前页面的版本号是否与当前数据的一致，如果不一致则表示有另一人已经修改，而当前的页面数据不是最新
				if (scScoreTpl.getVersion() == null) { // 版本号
					JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
					return;
				}
				ScScoreTpl info = scScoreTplServiceImpl.getEntityById(scScoreTpl);
				if (info != null) {
					if (info.getVersion() == null
							|| !scScoreTpl.getVersion().toString().equals(info.getVersion().toString())) {
						JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
						return;
					}

					if (Constants.STATE_NORMAL.equals(scScoreTpl.getState())) {

						// 评分项至少一条生效
						ScScoreSmall scScoreSmall = new ScScoreSmall();
						scScoreSmall.setScoreTplId(scScoreTpl.getScoreTplId());
						scScoreSmall.setState(Constants.STATE_NORMAL);
						int count = scScoreSmallService.listAllCount(scScoreSmall);// 统计总条数
						if (count == 0) {
							JSONUtils.toJSON(response, CodeUtils.FORBIDDEN_FAIL);
							return;
						}
					}

					if (Constants.STATE_FORBIDDEN.equals(scScoreTpl.getState())) {// 模板状态修改为无效时，其被关联的所有决策树状态必须都为无效才可以修改
						ScDecisionTree scDecisionTree = new ScDecisionTree();
						scDecisionTree.setState(Constants.STATE_NORMAL);
						scDecisionTree.setScoreTplId(scScoreTpl.getScoreTplId());
						int count = scDecisionTreeService.listAllCount(scDecisionTree);
						if (count != 0) {
							JSONUtils.toJSON(response, CodeUtils.UPDATE_TPL_FAIL);
							return;
						}
					}
					
					boolean flag = scScoreTplServiceImpl.updateEntity(scScoreTpl);
					if (flag) {
						JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
						return;
					} else {
						JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
						return;
					}
				}
			} else {// 添加
				ScScoreTpl info = scScoreTplServiceImpl.findCode(scScoreTpl);
				if (info != null) {
					String i = info.getScoreTplCode();
					String ii = scScoreTpl.getScoreTplCode();
					if (i.equals(ii)) {
						JSONUtils.toJSON(response, CodeUtils.NAME_FAIL);
						return;
					}
				}
				boolean flag = scScoreTplServiceImpl.saveEntity(scScoreTpl);
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
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, ScScoreTpl scScoreTpl) {
		try {
			if (scScoreTpl.getScoreTplId() != null) {
				ScScoreTpl list = scScoreTplServiceImpl.getEntityById(scScoreTpl);
				logger.info("查询模版信息" + list);
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				LinkedHashMap<String, Object> map1 = new LinkedHashMap<String, Object>();
				map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
				map.put(Constants.MESSAGE, CodeUtils.SUCCESSNEWS.getMessage());
				map1.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());

				map1.put("scScoreTplObject", list);
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
