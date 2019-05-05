package com.shangyong.backend.controller;

import com.shangyong.backend.entity.ScAppSmallChannel;
import com.shangyong.backend.service.ScAppSmallChannelService;
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

@Controller
@RequestMapping(value = "/backend/appSmallChannel")
public class ScAppSmallChannelController {

	@Autowired
	private ScAppSmallChannelService scAppSmallChannelService;
	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(ScAppSmallChannelController.class);
	
	/**
	 * 根据渠道大类ID查询所有小类
	 * @param request
	 * @param response
	 * @param scAppBigChannel
	 */
	@RequestMapping(value = "/getSmallChannelByBigId.do", method = RequestMethod.POST)
	public void getSmallChannelByBigId(HttpServletRequest request, HttpServletResponse response, ScAppSmallChannel scAppSmallChannel) {
		try { 
			if(scAppSmallChannel != null ){
				int count=scAppSmallChannelService.queryAllCountByBigId(scAppSmallChannel);
				List<ScAppSmallChannel> list=scAppSmallChannelService.findAllByBigId(scAppSmallChannel);
				logger.info("app渠道小类条数："+count);
				JSONUtils.toListJSON(response, list, count);
			}else{
				logger.info("查询渠道小类失败，ScAppSmallChannel="+scAppSmallChannel);
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS,null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	/**
	 * 保存，修改渠道小类
	 * @param request
	 * @param response
	 * @param scAppBigChannel
	 */
	@RequestMapping(value = "/saveOrUpdate.do", method = RequestMethod.POST)
	public void saveOrUpdate(HttpServletRequest request, HttpServletResponse response, ScAppSmallChannel scAppSmallChannel) {
		try { 
			if(scAppSmallChannel != null ){
				//ScAppBigChannel scAppBigChannelTemp=scAppBigChannelService.getEntityById(scAppBigChannel.getChannelBigId().toString());
				//if(scAppBigChannelTemp == null){//保存
				if("forSave".equals(scAppSmallChannel.getSaveOrUpdate())){
					boolean temp=scAppSmallChannelService.saveEntity(scAppSmallChannel);
					if(temp){
						JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
						logger.info("保存渠道小类成功");
					}else{
						JSONUtils.toJSON(response, CodeUtils.NAME_FAIL);
						logger.info("保存渠道小类失败");
					}
				}else if("forUpdate".equals(scAppSmallChannel.getSaveOrUpdate())){//更新
					boolean temp=scAppSmallChannelService.updateEntity(scAppSmallChannel);
					if(temp){
						JSONUtils.toJSON(response, CodeUtils.UPDATE_SUCCESS);
						logger.info("更新渠道小类成功");
					}else{
						JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
						logger.info("更新渠道小类失败");
					}
				}
			}else{
				logger.info("查询渠道大类失败，ScAppBigChannel="+scAppSmallChannel);
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
}
