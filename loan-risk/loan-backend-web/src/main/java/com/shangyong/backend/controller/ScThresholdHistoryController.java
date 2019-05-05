package com.shangyong.backend.controller;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.entity.ScThresholdHistory;
import com.shangyong.backend.service.impl.ScThresholdHistoryServiceImpl;
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
@RequestMapping("/backend/thresholdHistory")
public class ScThresholdHistoryController {
	private static Logger logger = Logger.getLogger(ScThresholdHistoryController.class);
	
	/**
	 *  欺诈风险阈值历史service实现
	 * @author hc
	 *
	 */
	@Autowired
	private ScThresholdHistoryServiceImpl scThresholdHistoryServiceImpl;
	
	/**
	 * 欺诈风险阈值历史列表
	 * @param request
	 * @param response
	 * @param scThresholdHistory 欺诈风险阈值历史对象信息
	 */
	@RequestMapping(value = "/HistorylistAll.do", method = RequestMethod.POST)
	public void getAllThresholdHistory(HttpServletRequest request, HttpServletResponse response, ScThresholdHistory scThresholdHistory ) {
		try {
			 int count = scThresholdHistoryServiceImpl.listAllCount(scThresholdHistory);
			 List<ScThresholdHistory> list = scThresholdHistoryServiceImpl.findAll(scThresholdHistory);
			 JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	
	/**
	 * 根据多条件获取对象信息
	 * @param request
	 * @param response
	 * @param scThresholdHistory 对象信息
	 */
	@RequestMapping(value = "/getEntityById.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, ScThresholdHistory scThresholdHistory){
		try {
			logger.info("根据多条件获取对象信息"+scThresholdHistory); 
			if( scThresholdHistory != null ){
				ScThresholdHistory thresholdHistory = scThresholdHistoryServiceImpl.getEntityById(scThresholdHistory);
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				LinkedHashMap<String, Object> map1 = new LinkedHashMap<String, Object>();
				map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
				map.put(Constants.MESSAGE, CodeUtils.SUCCESSNEWS.getMessage());
				map1.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());
				map1.put("thresholdHistory",thresholdHistory); 
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

	
	/**
	 * 修改欺诈风险阈值历史信息
	 * @param request
	 * @param response
	 * @param scThresholdHistory
	 */
	@RequestMapping(value ="/updateThresholdHistory.do",method = RequestMethod.POST )
	public void updateThresholdHistory(HttpServletRequest request, HttpServletResponse response,
                                       ScThresholdHistory scThresholdHistory ) {
		try { 
			if ( scThresholdHistory.getHistorySerialnumber() != null) {//更新
				 ScThresholdHistory HistoryInfo =  scThresholdHistoryServiceImpl.getEntityById(scThresholdHistory);
				if( HistoryInfo != null){ 
					scThresholdHistory.setModifyMan(TokenManager.getUser().getModifyMan());
					scThresholdHistory.setModifyTime(TokenManager.getUser().getModifyTime());
					scThresholdHistory.setVersion(HistoryInfo.getVersion()+1);
					boolean	temp = scThresholdHistoryServiceImpl.updateEntity(scThresholdHistory); 
					if(temp){
						 JSONUtils.toJSON(response, CodeUtils.UPDATE_SUCCESS);
						 return;
					}else{
						JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
						return;
					} 
				}
				 else{
					JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
					return;
				}  
				
			}  	  
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新欺诈风险阈值历史信息异常结果" +e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
		}
	}
	
}
