package com.shangyong.backend.controller;


import com.shangyong.backend.entity.InformationSchema;
import com.shangyong.backend.service.impl.DataBaseImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 查询数据库及表信息Controller
 * @author hc
 *
 */
@Controller
@RequestMapping(value = "/backend/dataBase")
public class DataBaseController {
	 
	private static Logger logger = Logger.getLogger(DataBaseController.class);
	
	@Autowired
	private DataBaseImpl dataBaseImpl;
	
	/**
	 * 查询数据库及表信息
	 * @param request
	 * @param response
	 * @param informationSchema
	 */
	@RequestMapping(value = "/findAll.do", method = RequestMethod.POST)
	public void findAll(HttpServletRequest request, HttpServletResponse response,
                        InformationSchema informationSchema) {
		try {
			if( informationSchema.getTableSchema() != null ){
				int count = dataBaseImpl.listAllCount(informationSchema);
				logger.info("查询数据库表数据条数"+count);
				List<InformationSchema> list = dataBaseImpl.findAll(informationSchema);
				logger.info("查询数据库表分页数据条数list:"+list); 
				JSONUtils.toListJSON(response, list, count);
			}
			else{
				logger.info("查询数据库条件为:"+informationSchema);
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 查询某个数据库实例下所有表或者某个表的表结构
	 * 
	 * @param request
	 * @param response
	 * @param informationSchema
	 */

	@RequestMapping(value = "/findOne.do", method = RequestMethod.POST)
	public void findOne(HttpServletRequest request, HttpServletResponse response, InformationSchema informationSchema) {
		try {
			if (informationSchema.getTableSchema() != null) {
				List<InformationSchema> list = dataBaseImpl.findOne(informationSchema);
				JSONUtils.toListJSON(response, list, 0);
			} else {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 查询某个数据库实例下所有表或者某个表的索引以及索引的类型
	 * 
	 * @param request
	 * @param response
	 * @param informationSchema
	 */
	@RequestMapping(value = "/findTwo.do", method = RequestMethod.POST)
	public void findTwo(HttpServletRequest request, HttpServletResponse response, InformationSchema informationSchema) {
		try {
			if (informationSchema.getTableSchema() != null) {
				List<InformationSchema> list = dataBaseImpl.findTwo(informationSchema);
				JSONUtils.toListJSON(response, list, 0);
			} else {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
}
