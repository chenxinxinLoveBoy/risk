package com.shangyong.backend.controller;

import com.shangyong.backend.entity.approval.CustomerEquipmentIos;
import com.shangyong.backend.service.CuCustomerEquipmentIosService;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/backend/iosEquipmentInfo/")
public class CuCustomerEquipmentIos {
	
	private static Logger logger = Logger.getLogger(CuCustomerEquipmentIos.class);

	/**
	 * IOS设备信息接口
	 */
	@Autowired
    CuCustomerEquipmentIosService cuCustomerEquipmentIosService;
	
	
	/**
	 * 查询客户所有IOS设备信息
	 * @param request
	 * @param response
	 * @param customerId
	 */
	@RequestMapping(value = "/findListById.do", method = RequestMethod.POST)
	public void findListById(HttpServletRequest request, HttpServletResponse response, String customerId) {
		try { 
			if(StringUtils.isNotBlank(customerId)){
				int count=cuCustomerEquipmentIosService.listAllCount(customerId);
				List<CustomerEquipmentIos> list=cuCustomerEquipmentIosService.findListById(customerId);
				logger.info("==>查询IOS设备信息==>总条数："+count);
				JSONUtils.toListJSON(response, list, count);
			}else{
				logger.info("==>查询IOS设备信息失败:customerId不正确");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	/**
	 * 查询指定IOS设备的具体信息
	 * @param request
	 * @param response
	 * @param customerId
	 */
	@RequestMapping(value = "/findEntityById.do", method = RequestMethod.POST)
	public void findEntityById(HttpServletRequest request, HttpServletResponse response, String equipmentId) {
		try { 
			if(StringUtils.isNotBlank(equipmentId)){
				CustomerEquipmentIos obj=cuCustomerEquipmentIosService.findEntityById(equipmentId);
				logger.info("==>查询IOS设备的具体信息");
				JSONUtils.toJSON(response, CodeUtils.SUCCESS, obj);
			}else{
				logger.info("==>查询IOS设备具体信息失败:equipmentId不正确");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
}
