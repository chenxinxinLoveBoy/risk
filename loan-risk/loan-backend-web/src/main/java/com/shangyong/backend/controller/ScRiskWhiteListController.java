package com.shangyong.backend.controller;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.entity.ScRiskWhiteList;
import com.shangyong.backend.service.ScRiskWhiteListService;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.utils.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("/backend/whiterlist/")
public class ScRiskWhiteListController {
	
	private static Logger logger = Logger.getLogger(ScRiskWhiteListController.class);
	
	@Autowired
	private ScRiskWhiteListService scRiskWhiteListService;
	
	/**
	 * 白名单列表
	 * @return
	 */
	@RequestMapping(value ="/index.do",method = RequestMethod.POST )
	public void index(HttpServletRequest request, HttpServletResponse response, ScRiskWhiteList scRiskWhiteList) {
		try {   
			int count = scRiskWhiteListService.findAllCount(scRiskWhiteList);
			logger.info("白名单列表count" + count + '\t');
			List<ScRiskWhiteList> list = scRiskWhiteListService.findAll(scRiskWhiteList);
			logger.info("白名单列表查询list" + list + '\t');
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	
	/**
	 * 根据id获取对象信息
	 * @param request
	 * @param response
	 * @param scRiskWhiteList
	 */
	@RequestMapping(value = "/getObjectById.do", method = RequestMethod.POST)
	public void getObjectById(HttpServletRequest request, HttpServletResponse response, ScRiskWhiteList scRiskWhiteList){
		try {
			logger.info("根据id获取对象信息"+scRiskWhiteList);
			ScRiskWhiteList srw = scRiskWhiteListService.findByid(scRiskWhiteList);
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			LinkedHashMap<String, Object> map1 = new LinkedHashMap<String, Object>();
			map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
			map.put(Constants.MESSAGE, CodeUtils.SUCCESSNEWS.getMessage());
			map1.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());
			
			map1.put("srwObject",srw); 
			map.put(Constants.DATA, map1);
			SpringUtils.renderJson(response,map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	
	/**
	 * 添加白名单
	 * @param request
	 * @param response
	 * @param scRiskWhiteList
	 */
	@RequestMapping(value ="/save.do",method = RequestMethod.POST )
	public void save(HttpServletRequest request, HttpServletResponse response,
                     ScRiskWhiteList scRiskWhiteList) {
		try {
			boolean temp = false;
			if (scRiskWhiteList != null && scRiskWhiteList.getWhiteListId() != null) {//更新
				ScRiskWhiteList srwl = scRiskWhiteListService.findByCodeAppName(scRiskWhiteList);
				if( srwl != null){
					if(srwl.getWhiteListId().equals(scRiskWhiteList.getWhiteListId())){//比较当前id是否相等
						scRiskWhiteList.setModifyMan(TokenManager.getUser().getModifyMan());
						temp = scRiskWhiteListService.updateByPrimaryKeySelective(scRiskWhiteList);
					}
				}else{
					scRiskWhiteList.setModifyMan(TokenManager.getUser().getModifyMan());
					temp = scRiskWhiteListService.updateByPrimaryKeySelective(scRiskWhiteList);
				}
				if(temp){
					JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
				}else{
					JSONUtils.toJSON(response, CodeUtils.NAME_FAIL);
				} 
				logger.info("查询白名单信息结果" + srwl ); 
				return;
			} else {//添加  
					ScRiskWhiteList srw = scRiskWhiteListService.findByCodeAppName(scRiskWhiteList);
					logger.info("根据身份证AppName 查询信息结果" + srw);
					if(srw != null){
						JSONUtils.toJSON(response, CodeUtils.NAME_FAIL);//102已存在
						return;
					}else{
						scRiskWhiteList.setCreateMan(TokenManager.getUser().getCreateMan());
						boolean flag = scRiskWhiteListService.save(scRiskWhiteList);
						logger.info("添加白名单 信息结果" + flag );
						if (flag) {
							JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
							return;
						} else {
							JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
							return;
						}
					} 	
				} 	 	  
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加白名单信息异常结果" +e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
		}
	}
	/**
	 * 根据whiteListId删除白名单
	 * @param request
	 * @param response
	 * @param whiteListId
	 */
	@RequestMapping(value ="/delete.do",method = RequestMethod.POST )
	public void delete(HttpServletRequest request, HttpServletResponse response, String whiteListId) {
		
		try {
			if (StringUtils.isBlank(whiteListId)) {
				logger.info("白名单whiteListId为空");
				JSONUtils.toJSON(response, CodeUtils.DELETE_FAIL);
				return;
			}
			ScRiskWhiteList scRiskWhiteList = new ScRiskWhiteList();
			scRiskWhiteList.setWhiteListId(Integer.valueOf(whiteListId));
			ScRiskWhiteList whiteList = scRiskWhiteListService.findByid(scRiskWhiteList);
			if (ObjectUtils.isEmpty(whiteList)) {
				logger.info("白名单:" + whiteListId + ",用户信息为空");
				JSONUtils.toJSON(response, CodeUtils.DELETE_FAIL);
				return;
			}
			//删除用户
			boolean flag = false;
			flag = scRiskWhiteListService.deleteById(scRiskWhiteList);
			if (flag) {
				JSONUtils.toJSON(response, CodeUtils.DELETE_SUCCESS);
				return;
			}else{
				JSONUtils.toJSON(response, CodeUtils.DELETE_FAIL);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除白名单信息异常结果" +e.getMessage());
		}
		
	}
	
}
