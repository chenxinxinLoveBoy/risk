package com.shangyong.backend.controller;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.controller.BaseController;
import com.shangyong.backend.entity.ScScoreBig;
import com.shangyong.backend.entity.ScScoreSmall;
import com.shangyong.backend.service.impl.ScScoreBigServiceImpl;
import com.shangyong.backend.service.impl.ScScoreSmallServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.utils.SpringUtils;
import org.apache.commons.collections.CollectionUtils;
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
@RequestMapping("/backend/scScoreBig/")
public class ScScoreBigController extends BaseController {
	private static Logger logger = Logger.getLogger(ScScoreBigController.class);
	//大类
	@Autowired
	private ScScoreBigServiceImpl scScoreBigService;
	
	// 小类
	@Autowired
	private ScScoreSmallServiceImpl scScoreSmallService;

	/**
	 * 规则配置集合
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getAllScScoreBig.do", method = RequestMethod.POST)
	public void getAllScScoreBig(HttpServletRequest request, HttpServletResponse response, ScScoreBig scScoreBig) {
		try {
			int count = scScoreBigService.listAllCount(scScoreBig);// 统计总条数
			List<ScScoreBig> list = scScoreBigService.findAll(scScoreBig);
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
	 * @param scScoreBig
	 */

	@RequestMapping(value = "getEntityById.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, ScScoreBig scScoreBig) {
		try {
			ScScoreBig scScoreBigBo = scScoreBigService.getEntityById(scScoreBig.getScoreBigId());
			// 根据大类的序号，查询是否有小类
			ScScoreSmall small = new ScScoreSmall();
			small.setScoreBigId(scScoreBig.getScoreBigId());
			List<ScScoreSmall> list = scScoreSmallService.findAll(small);
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			LinkedHashMap<String, Object> map2 = new LinkedHashMap<String, Object>();
			map.put(Constants.CODE, CodeUtils.SUCCESS.getClass());
			map.put(Constants.MESSAGE, CodeUtils.SUCCESS.getMessage());
			map.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());
			map2.put("scScoreBigObject", scScoreBigBo);// 大类
			map2.put("isScScoreSmall", CollectionUtils.isEmpty(list) ? false : true);// 小类
			
			map.put(Constants.DATAS, map2);
			SpringUtils.renderJson(response, map);
			
//			JSONUtils.toJSON(response, CodeUtils.SUCCESS, scScoreBigBo, "scScoreBigObject");
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
	 * @param scScoreBig
	 */

	@RequestMapping(value = "saveScScoreBig.do")
	public void saveScScoreBig(HttpServletRequest request, HttpServletResponse response, ScScoreBig scScoreBig) {
		try {
			if (scScoreBig != null && scScoreBig.getScoreBigId() != null) {// 修改
				// 需要先判断当前页面的版本号是否与当前数据的一致，如果不一致则表示有另一人已经修改，而当前的页面数据不是最新
				if(scScoreBig.getVersion() == null){ // 版本号
					JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
					return;
				}
				
				// 拿到数据里的数据信息
				ScScoreBig scScore = scScoreBigService.getEntityById(scScoreBig.getScoreBigId());
				if(scScore == null){
					JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
					return;
				}
				
				// 当前页面的版本号与数据库的版本号一致
				if(scScore.getVersion() != null  && scScoreBig.getVersion() == scScore.getVersion()){
					boolean flag = scScoreBigService.updateEntity(scScoreBig);
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
				ScScoreBig scScoreBigBo = scScoreBigService.queryByScoreBigCode(scScoreBig.getScoreBigCode());
				if (scScoreBigBo == null) {// 该参数记录不存在，执行新增操作
					boolean flag = scScoreBigService.saveEntity(scScoreBig);
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

}
