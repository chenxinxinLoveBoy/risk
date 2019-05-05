package com.shangyong.backend.controller;

import com.shangyong.backend.bo.ZTree;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.controller.BaseController;
import com.shangyong.backend.entity.ScScoreSmall;
import com.shangyong.backend.entity.ScScoreTpl;
import com.shangyong.backend.service.impl.ScScoreSmallServiceImpl;
import com.shangyong.backend.service.impl.ScScoreTplServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/backend/scScoreSmall/")
public class ScScoreSmallController extends BaseController {
	private static Logger logger = Logger.getLogger(ScScoreSmallController.class);
	@Autowired
	private ScScoreSmallServiceImpl scScoreSmallService;
	@Autowired
	private ScScoreTplServiceImpl scScoreTplServiceImpl;
	/**
	 * 规则配置集合
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getAllScScoreSmall.do", method = RequestMethod.POST)
	public void getAllScScoreSmall(HttpServletRequest request, HttpServletResponse response,
                                   String scoreBigId, String scoreSmallId, String scoreSmallCode, String scoreBigCode, String scoreRuleName, String state, String scoreTplId, Integer pageIndex, Integer pageSize) {
		try {
			int count = scScoreSmallService.listAllCountUnion(scoreBigCode, scoreSmallCode, scoreRuleName, state,   scoreTplId,scoreBigId,scoreSmallId);// 统计总条数
			List<Map<String, String>> list = scScoreSmallService.findAllUnion(scoreBigCode, scoreSmallCode,
					scoreRuleName, pageIndex, pageSize, state , scoreTplId,  scoreBigId,scoreSmallId);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 规则配置集合
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getAllScScoreSmallUnion.do", method = RequestMethod.POST)
	public void getAllScScoreSmallUnion(HttpServletRequest request, HttpServletResponse response, String scoreBigCode,
                                        String scoreSmallCode, String scoreRuleName, String state, String scoreTplId, String scoreBigId, String scoreSmallId, Integer pageIndex, Integer pageSize) {
		try {
			if (pageIndex != null && pageIndex >= 1) {// 当前页
				pageIndex = (pageIndex - 1) * 10;
			}
			int count = scScoreSmallService.listAllCountUnion(scoreBigCode, scoreSmallCode, scoreRuleName, state,   scoreTplId,scoreBigId,scoreSmallId);// 统计总条数
			List<Map<String, String>> list = scScoreSmallService.findAllUnion(scoreBigCode, scoreSmallCode,
					scoreRuleName, pageIndex, pageSize, state , scoreTplId,  scoreBigId,scoreSmallId);
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
	 * @param scScoreSmall
	 */

	@RequestMapping(value = "getEntityById.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, ScScoreSmall scScoreSmall) {
		try {
			ScScoreSmall scScoreSmallBo = scScoreSmallService.getEntityById(scScoreSmall.getScoreSmallId());
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, scScoreSmallBo, "scScoreSmallObject");
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
	 * @param scScoreSmall
	 */

	@RequestMapping(value = "saveScScoreSmall.do")
	public void saveScScoreSmall(HttpServletRequest request, HttpServletResponse response, ScScoreSmall scScoreSmall) {
		try {
			if (scScoreSmall != null && scScoreSmall.getScoreSmallId() != null
					&& !"".equals(scScoreSmall.getScoreSmallId())) {// 修改
				// 需要先判断当前页面的版本号是否与当前数据的一致，如果不一致则表示有另一人已经修改，而当前的页面数据不是最新
				if (scScoreSmall.getVersion() == null) { // 版本号
					JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
					return;
				}

				// 拿到数据里的数据信息
				ScScoreSmall Small = scScoreSmallService.getEntityById(scScoreSmall.getScoreSmallId());
				if (Small == null) {
					JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
					return;
				}

				ScScoreSmall scScoreSmallBo = scScoreSmallService.queryByScoreSmallCodeAndTplId(scScoreSmall);
				if (scScoreSmallBo != null && !Small.getScoreSmallCode().equals(scScoreSmallBo.getScoreSmallCode())) {
					JSONUtils.toJSON(response, CodeUtils.SYS_PARAM_FAIL);
					return;
				}

				// 当前页面的版本号与数据库的版本号一致
				if (Small.getVersion() != null && scScoreSmall.getVersion() == Small.getVersion()) {
					//一个有效的信用评分项模板下的所有信用评分至少有一条生效
					int scoreTplId=scScoreSmall.getScoreTplId();
					ScScoreTpl scScoreTpl=new ScScoreTpl();
					scScoreTpl.setScoreTplId(scoreTplId);
					ScScoreTpl info = scScoreTplServiceImpl.getEntityById(scScoreTpl);
					if (info != null && Constants.STATE_NORMAL.equals(info.getState())) { // 判断要修改的信用评分项的所属模板是否有效
 							if (Constants.STATE_FORBIDDEN.equals(scScoreSmall.getState())) {
								// 查询模板下有效的信用评分项数量
								ScScoreSmall tpl = new ScScoreSmall();
								tpl.setScoreTplId(scoreTplId);
								tpl.setState(Constants.STATE_NORMAL);
								List<ScScoreSmall> list = scScoreSmallService.findTemplate(tpl);
								if (list != null && list.size() != 0) {
									if (list.size() == 1) {
										if (list.get(0).getScoreSmallId().equals(scScoreSmall.getScoreSmallId())) {
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

					boolean flag = scScoreSmallService.updateEntity(scScoreSmall);
					if (flag) {
						JSONUtils.toJSON(response, CodeUtils.UPDATE_SUCCESS);
						return;
					} else {
						JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
						return;
					}
				} else {// 不一致
					JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
					return;
				}

			} else {// 新增
				ScScoreSmall scScoreSmallBo = scScoreSmallService
						.queryByScoreSmallCode(scScoreSmall.getScoreSmallCode());
				if (scScoreSmallBo == null) {// 该参数记录不存在，执行新增操作
					boolean flag = scScoreSmallService.saveEntity(scScoreSmall);
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
	 * 查询模板信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getAllTemplate.do", method = RequestMethod.POST)
	public void getAllTemplate(HttpServletRequest request, HttpServletResponse response, ScScoreSmall scScoreSmall) {
		try {
			int count = scScoreSmallService.listAllTemplateCount(scScoreSmall);// 统计模板总条数
			List<ScScoreSmall> list = scScoreSmallService.findAllTemplate(scScoreSmall);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 批量添加信息
	 * 
	 * @param request
	 * @param response
	 * @param id
	 *            模板id
	 */
	@RequestMapping(value = "saveScFraudRuleBatch.do", method = RequestMethod.POST)
	public void saveScFraudRuleBatch(HttpServletRequest request, HttpServletResponse response, String scoreTplId,
                                     ScScoreSmall scScoreSmall) {
		try {
			if (StringUtils.isNotBlank(scoreTplId) && scScoreSmall != null) {
				boolean flag = scScoreSmallService.saveEntityBatch(scScoreSmall);
				if (flag) {
					JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
					return;
				} else {
					JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
					return;
				}
			} else {
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
	 * 当前模板下关联的信用评分项
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getTemplate.do", method = RequestMethod.POST)
	public void getTemplate(HttpServletRequest request, HttpServletResponse response, ScScoreSmall scScoreSmall) {
		try {
			int count = scScoreSmallService.listTemplateCount(scScoreSmall);// 统计当前模板总条数
			List<ScScoreSmall> list = scScoreSmallService.findTemplate(scScoreSmall);
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
	@RequestMapping(value = "/getScScoreTree.do")
	public void getScScoreTree(HttpServletRequest request, HttpServletResponse response, String scoreTplId) {
		try {
			List<ZTree> zTreeList = scScoreSmallService.getScScoreTree(scoreTplId);// 查詢树
			JSONUtils.toJsonZTrr(response, zTreeList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

}
