package com.shangyong.backend.controller;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.ScBanControlTplHis;
import com.shangyong.backend.service.impl.ScTemplateHistServiceImpl;
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
@RequestMapping(value ="/backend/ScTemplateHist")
public class ScTemplateHistController {
 
	@Autowired
	private ScTemplateHistServiceImpl scTemplateHistServiceImpl;
	
	private static Logger logger = Logger.getLogger(ScTemplateHistController.class);

	/**
	 * 获取禁止项模版列表
	 * @param request
	 * @param response
	 * @param scBanControlTplHis
	 */
	@RequestMapping(value ="/templatelisthisAll.do",method = RequestMethod.POST )
	public void templatelisthisAll(HttpServletRequest request, HttpServletResponse response, ScBanControlTplHis scBanControlTplHis) {
		try {   
			int count = scTemplateHistServiceImpl.listAllCount(scBanControlTplHis);
			logger.info("禁止项模板历史查询count" + count + '\t');
			List<ScBanControlTplHis> list = scTemplateHistServiceImpl.findAll(scBanControlTplHis) ;
			logger.info("禁止项模板历史查询list" + list + '\t');
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
 
	
	/**
	 * 查询模版信息
	 * @param request
	 * @param response
	 * @param scBanControlTpl
	 */
	@RequestMapping(value ="/getEntityById.do",method = RequestMethod.POST )
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, ScBanControlTplHis scBanControlTplHis) {
		try {  
			if(scBanControlTplHis != null && scBanControlTplHis.getBanControlTplHisId() != null){ 
				int id = scBanControlTplHis.getBanControlTplHisId();
				ScBanControlTplHis list  =  scTemplateHistServiceImpl.getEntityById(id);
				logger.info("查询模版信息"+list);
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				LinkedHashMap<String, Object> map1 = new LinkedHashMap<String, Object>();
				map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
				map.put(Constants.MESSAGE, CodeUtils.SUCCESSNEWS.getMessage());
				map1.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());
				
				map1.put("templateHistObject",list); 
				map.put(Constants.DATA, map1);
				SpringUtils.renderJson(response,map);
			}else{
				logger.error("缺少参数");
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询模版信息异常" +e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.NON_EXISTENT);
		}
		
	}
}
