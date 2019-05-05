package com.shangyong.backend.controller;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.ScThreshold;
import com.shangyong.backend.service.impl.ScThresholdServiceImpl;
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
@RequestMapping("/backend/threshold")
public class ScThresholdController {
	private static Logger logger = Logger.getLogger(ScThresholdController.class);
	
	/**
	 *  欺诈风险阈值service实现
	 * @author hc
	 *
	 */
	@Autowired
	private ScThresholdServiceImpl scThresholdServiceImpl;
	
	/**
	 * 欺诈风险阈值列表
	 * @param request
	 * @param response
	 * @param scThreshold 欺诈风险阈值对象信息
	 */
	@RequestMapping(value = "/thresholdlistAll.do", method = RequestMethod.POST)
	public void getAllThreshold(HttpServletRequest request, HttpServletResponse response, ScThreshold scThreshold ) {
		try {
			 int count = scThresholdServiceImpl.listAllCount(scThreshold);
			 List<ScThreshold> list = scThresholdServiceImpl.findAll(scThreshold);
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
	 * @param scThreshold 对象信息
	 */
	@RequestMapping(value = "/getEntityById.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, ScThreshold scThreshold){
		try {
			logger.info("根据多条件获取对象信息"+scThreshold); 
			if( scThreshold != null ){
				 ScThreshold threshold = scThresholdServiceImpl.getEntityById(scThreshold);
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				LinkedHashMap<String, Object> map1 = new LinkedHashMap<String, Object>();
				map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
				map.put(Constants.MESSAGE, CodeUtils.SUCCESSNEWS.getMessage());
				map1.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());
				map1.put("threshold",threshold); 
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
	 * 添加 欺诈风险阈值
	 * @param request
	 * @param response
	 * @param scThreshold
	 */
	@RequestMapping(value ="/saveThreshold.do",method = RequestMethod.POST )
	public void saveThreshold(HttpServletRequest request, HttpServletResponse response,
                              ScThreshold scThreshold ) {
		try { 
			if ( scThreshold.getSerialnumber() != null ) {//更新 
				  ScThreshold scThresholdInfo =  scThresholdServiceImpl.getEntityById(scThreshold);
				if( scThresholdInfo != null){ 
					List<ScThreshold> ScThresholdlist = scThresholdServiceImpl.queryByScore(scThreshold);
					if (CollectionUtils.isEmpty(ScThresholdlist) || (ScThresholdlist.size() == 1 && ScThresholdlist.get(0).getSerialnumber().equals(scThreshold.getSerialnumber()))) {
						boolean	temp = scThresholdServiceImpl.updateEntity(scThreshold); 
						if(temp){
							JSONUtils.toJSON(response, CodeUtils.UPDATE_SUCCESS);
							return;
						}else{
							JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
							return;
						} 
					}else {// 该参数记录已存在，直接返回
						JSONUtils.toJSON(response, CodeUtils.SCORE_PARAM_FAIL);
						return;
					}
					
				}else{
					JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
					return;
				}   
				
			} else{ 
				List<ScThreshold> ScThresholdlist = scThresholdServiceImpl.queryByScore(scThreshold);
				if (CollectionUtils.isEmpty(ScThresholdlist)) {
					boolean	flag = scThresholdServiceImpl.saveEntity(scThreshold);
					if(flag){//成功
						JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
						return;
					}else{//失败
						JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
						return;
					}
				}else{// 该参数记录已存在，直接返回
					JSONUtils.toJSON(response, CodeUtils.SCORE_PARAM_FAIL);
					return;
					
				}
				
			}	 	  
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加 欺诈风险阈值异常结果" +e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
		}
	}
	
}
