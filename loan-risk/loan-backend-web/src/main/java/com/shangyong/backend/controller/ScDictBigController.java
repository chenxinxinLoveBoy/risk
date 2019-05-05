package com.shangyong.backend.controller;


import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.ScDictBig;
import com.shangyong.backend.entity.ScDictSmall;
import com.shangyong.backend.service.ScDictBigService;
import com.shangyong.backend.service.ScDictSmallService;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.utils.SpringUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/backend/scDicBiglist")
public class ScDictBigController {
	 
	private Logger logger = Logger.getLogger(ScDictBigController.class);
	
	@Autowired
	private ScDictBigService scDictBigService;
	
	@Autowired
	private ScDictSmallService scDictSmallService;
	
	
	
	
	/**
	 * 数据字典大类列表
	 * @return
	 */
	@RequestMapping(value ="/list.do",method = RequestMethod.POST )
	public void scDicBiglist(HttpServletRequest request, HttpServletResponse response, ScDictBig scDictBig) {
			int count = scDictBigService.listAllCount();
			List<ScDictBig> list = scDictBigService.listViewAll(scDictBig);
			JSONUtils.toListJSON(response, list, count);
		
	}
	
	/**
	 * 数据字典小类查询
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value ="/scDicInfolist.do",method = RequestMethod.POST )
	public void scDicInfolist(HttpServletRequest request, HttpServletResponse response, ScDictSmall ssm) {
		try { 
			if(ssm != null){  
				List<Map> list=	scDictSmallService.findById(ssm);
				int count = scDictSmallService.listAllCount(ssm);
				JSONUtils.toListJSON(response, list, count);
			}  
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	
	
	/**
	 * 根据id获取对象信息
	 * @param request
	 * @param response
	 * @param scDictBig
	 */
	@RequestMapping(value = "/getObjectById.do", method = RequestMethod.POST)
	public void getObjectById(HttpServletRequest request, HttpServletResponse response, ScDictBig scDictBig){
		try {
			
			ScDictBig sc = scDictBigService.getObjectById(scDictBig);//
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			LinkedHashMap<String, Object> map1 = new LinkedHashMap<String, Object>();
			map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
			map.put(Constants.MESSAGE, CodeUtils.SUCCESSNEWS.getMessage());
			map1.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());
			
			map1.put("scObject",sc); 
			map.put(Constants.DATA, map1);
			SpringUtils.renderJson(response,map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	/**
	 * 保存/修改
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	public void save(HttpServletRequest request, HttpServletResponse response, ScDictBig scDictBig ){
		try {
			if(scDictBig != null && scDictBig.getId()!= null){// 修改
				boolean flag = scDictBigService.update(scDictBig);
				if(flag){
					JSONUtils.toJSON(response, CodeUtils.UPDATE_SUCCESS);
					return;
				}else{
					JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
					return;
				}
			}else{
				 int dicCount = scDictBigService.getCountDicBigCode(scDictBig);
				if(dicCount > 0){// 数据字典大类编号已存在
					JSONUtils.toJSON(response, CodeUtils.NAME_FAIL);
					return;
				}
				 
				boolean flag = scDictBigService.save(scDictBig); 
				 
				if(flag ){
					JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
					return;
				}else{
					JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
					return ;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
		}
		
	}
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @param scDictSmall
	 */
	@RequestMapping(value = "delete.do", method = RequestMethod.POST)
	public void delete(HttpServletRequest request, HttpServletResponse response, ScDictBig scDictBig){
		try {
			boolean flag = scDictBigService.delete(scDictBig);
			if(flag){
				JSONUtils.toJSON(response, CodeUtils.DELETE_SUCCESS);
				return ;
			}else{
				JSONUtils.toJSON(response, CodeUtils.DELETE_FAIL);
				return ;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
}
