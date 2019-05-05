package com.shangyong.backend.controller;

import com.shangyong.backend.bo.SystemLogBo;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.service.impl.SystemLogServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.utils.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 
 * @author xiajiyun
 *
 */
@Controller
@RequestMapping(value = "/backend/systemLog")
public class SystemLogController {

	public static Logger logger = LoggerFactory.getLogger(SystemLogController.class);
	
	@Autowired
	private SystemLogServiceImpl systemLogServiceImpl;
	
	/**
	 * 查询list列表
	 * @param request
	 * @param response
	 * @param systemLog
	 */
	@RequestMapping(value = "/list.do", method = RequestMethod.POST)
	public void list(HttpServletRequest request, HttpServletResponse response, SystemLogBo systemLog){
		try{
			//结束日期，格式拼接为： yyyy-MM-dd 23:59:59
			if(null != systemLog && StringUtils.isNotBlank(systemLog.getOptTimeBigen())){
				 systemLog.setOptTimeBigen(systemLog.getOptTimeBigen()+" 00:00:00");
			}
			int count=systemLogServiceImpl.listAllCount(systemLog);// 统计
			List<SystemLogBo> list = systemLogServiceImpl.findAll(systemLog);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
		
	}
	
	/**
	 * 查询备注字段
	 * @param request
	 * @param response
	 * @param systemLog
	 */
	@RequestMapping(value = "/getRemark.do", method = RequestMethod.POST)
	public void getRemark(HttpServletRequest request, HttpServletResponse response, SystemLogBo systemLog){
		try { 
			if( systemLog != null ){ 
				SystemLogBo systemLogBoInfo = systemLogServiceImpl.getEntityById(systemLog);
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				LinkedHashMap<String, Object> map1 = new LinkedHashMap<String, Object>();
				map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
				map.put(Constants.MESSAGE, CodeUtils.SUCCESSNEWS.getMessage());
				map1.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());
				map1.put("systemLogBoInfo",systemLogBoInfo); 
				map.put(Constants.DATA, map1);
				SpringUtils.renderJson(response,map);
			}else{
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

}
