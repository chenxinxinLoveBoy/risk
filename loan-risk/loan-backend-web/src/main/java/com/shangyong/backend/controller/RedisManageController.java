package com.shangyong.backend.controller;

import com.shangyong.backend.service.RedisManageService;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.utils.RedisUtils;
import com.shangyong.utils.SpringUtils;
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

/**
 * redis缓存管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/backend/redisManage")
public class RedisManageController {

	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(RedisManageController.class);
	
	@Autowired
    RedisManageService redisManageService;
	/**
	 * 获取redis缓存
	 * @param request
	 * @param response
	 * @param buApplicationId
	 * @param param
	 */
	@RequestMapping(value = "/getRedis.do", method = RequestMethod.POST)
	public void getRedis(HttpServletRequest request, HttpServletResponse response, String key, String typeTemp) {
		try { 
			boolean flag= RedisUtils.exists(key);
			Map<String,Object> returnMap=new HashMap<>();
			logger.info("key是否存在："+flag);
			if(flag == true ){
				if(typeTemp.equals("hash")){
					Map<String,String> map=redisManageService.hgetAll(key);
					SpringUtils.renderJson(response, map);
				}else if(typeTemp.equals("list")){
					List<String> list=redisManageService.getList(key);
					returnMap.put("value", list);
					SpringUtils.renderJson(response, returnMap);
				}else if(typeTemp.equals("String")){
					String returnStr=redisManageService.getString(key);
					returnMap.put("value", returnStr);
					SpringUtils.renderJson(response, returnMap);
				}
			}else{
				JSONUtils.toJSON(response, CodeUtils.SUCCESS,null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
}
