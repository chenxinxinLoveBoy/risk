package com.shangyong.backend.controller;

import com.shangyong.backend.entity.ScAppBigChannel;
import com.shangyong.backend.service.ScAppBigChannelService;
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
@RequestMapping(value = "/backend/appBigChannel")
public class ScAppBigChannelController {

	@Autowired
    ScAppBigChannelService scAppBigChannelService;
	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(ScAppBigChannelController.class);
	
	/**
	 * 根据渠道大类名称查询
	 * @param request
	 * @param response
	 * @param scAppBigChannel
	 */
	@RequestMapping(value = "/getChannelByBigName.do", method = RequestMethod.POST)
	public void getChannelByBigName(HttpServletRequest request, HttpServletResponse response, ScAppBigChannel scAppBigChannel) {
		try { 
			if(scAppBigChannel != null ){
				int count=scAppBigChannelService.queryAllCountByBigName(scAppBigChannel);
				List<ScAppBigChannel> list=scAppBigChannelService.findAllByBigName(scAppBigChannel);
				logger.info("app渠道大类条数："+count);
				JSONUtils.toListJSON(response, list, count);
			}else{
				logger.info("查询渠道大类失败，ScAppBigChannel="+scAppBigChannel);
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS,null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	/**
	 * 保存，修改渠道大类
	 * @param request
	 * @param response
	 * @param scAppBigChannel
	 */
	@RequestMapping(value = "/saveOrUpdate.do", method = RequestMethod.POST)
	public void saveOrUpdate(HttpServletRequest request, HttpServletResponse response, ScAppBigChannel scAppBigChannel) {
		try { 
			if(scAppBigChannel != null ){
				//ScAppBigChannel scAppBigChannelTemp=scAppBigChannelService.getEntityById(scAppBigChannel.getChannelBigId().toString());
				//if(scAppBigChannelTemp == null){//保存
				if("forSave".equals(scAppBigChannel.getSaveOrUpdate())){
					boolean temp=scAppBigChannelService.saveEntity(scAppBigChannel);
					if(temp){
						JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
						logger.info("保存渠道大类成功");
					}else{
						JSONUtils.toJSON(response, CodeUtils.NAME_FAIL);
						logger.info("保存渠道大类失败");
					}
				}else if("forUpdate".equals(scAppBigChannel.getSaveOrUpdate())){//更新
					boolean temp=scAppBigChannelService.updateEntity(scAppBigChannel);
					if(temp){
						JSONUtils.toJSON(response, CodeUtils.UPDATE_SUCCESS);
						logger.info("更新渠道大类成功");
					}else{
						JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
						logger.info("更新渠道大类失败");
					}
				}
			}else{
				logger.info("查询渠道大类失败，ScAppBigChannel="+scAppBigChannel);
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
}
