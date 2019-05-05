package com.shangyong.backend.controller;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.entity.ScDictSmall;
import com.shangyong.backend.service.ScDictParamRedisService;
import com.shangyong.backend.service.ScDictSmallService;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.utils.SpringUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;

@Controller
@RequestMapping(value ="/backend/scDicSmallist")
public class ScDictSmallController {

	private Logger logger = Logger.getLogger(ScDictSmallController.class);
	
	@Autowired
	private ScDictSmallService scDictSmallService;
	
	@Autowired
	private ScDictParamRedisService scDictParamRedisService; // 小类Redis
	
	
	/**
	 * 保存/修改
	 * @param request
	 * @param response
	 */
	 
	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public void save(HttpServletRequest request, HttpServletResponse response, ScDictSmall scDictSmall, Integer smsid  ){
		try { 
			if (scDictSmall != null && scDictSmall.getId() != null) {// 修改

				int dicCount = scDictSmallService.getCountDicSmsCodde(scDictSmall);
				logger.info("修改-->查询数据字典小类是否存在" + dicCount + '\t');
				if (dicCount > 0) {// 小字典已存在
					JSONUtils.toJSON(response, CodeUtils.NAME_FAIL);
					return;
				}
				scDictSmall.setModifyTime(TokenManager.getUser().getModifyTime());
				scDictSmall.setModifyMan(TokenManager.getUser().getModifyMan());
				boolean flag = scDictSmallService.update(scDictSmall);
				if (flag) {
					logger.info("修改数据字典小类信息成功" + flag + '\t');
					JSONUtils.toJSON(response, CodeUtils.UPDATE_SUCCESS);
					return;
				} else {
					JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
					logger.info("修改数据字典小类信息失败" + flag + '\t');
					return;
				}
			}else{  
				scDictSmall.setDictBigId(smsid); 
				logger.info("保存数据字典小类开始" + scDictSmall + '\t');
				int dicCount = scDictSmallService.getCountDicSmsCodde(scDictSmall);
				if(dicCount > 0){// 小字典已存在
					logger.info("保存-->数据字典小类已经存在" + dicCount + '\t');
					JSONUtils.toJSON(response, CodeUtils.NAME_FAIL);
					return;
				} 
				 scDictSmall.setCreateTime(TokenManager.getUser().getCreateTime());
				 scDictSmall.setCreateMan(TokenManager.getUser().getCreateMan());
				 scDictSmall.setVersion(1);
				boolean flag = scDictSmallService.save(scDictSmall);
				 
				if( flag ){
					logger.info("数据字典小类保存成功" + flag  + '\t');
					JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
					return;
				}else{
					JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
					logger.info("数据字典小类保存失败" + flag + '\t');
					return ;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage()+"保存或更新数据字典小类信息异常");
			JSONUtils.toJSON(response, CodeUtils.FAIL);
		}
		
	}
	
	
	
	/**
	 * 根据id获取对象信息
	 * @param request
	 * @param response
	 * @param ScDictSmall
	 */
	@RequestMapping(value = "/getObjectById.do", method = RequestMethod.POST)
	public void getObjectById(HttpServletRequest request, HttpServletResponse response, ScDictSmall scDictSmall){
		try {		 
			ScDictSmall scs = scDictSmallService.getObjectById(scDictSmall);
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			LinkedHashMap<String, Object> map1 = new LinkedHashMap<String, Object>();
			map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
			map.put(Constants.MESSAGE, CodeUtils.SUCCESSNEWS.getMessage());
			map1.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());
			map1.put("scsObject",scs); 
			map.put(Constants.DATA, map1);
			SpringUtils.renderJson(response,map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage()+"根据id获取对象异常信息");
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	 
	/**
	 * 根据大类编号获取对应的所有小类，取redis
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getRedisScDictSmall.do", method = RequestMethod.POST)
	public void getRedisScDictSmall(HttpServletRequest request, HttpServletResponse response){
		String str = scDictParamRedisService.getRedisScDictSmall(request.getParameter("dicBigCode"));
		SpringUtils.renderJson(response,str);
	}
}
