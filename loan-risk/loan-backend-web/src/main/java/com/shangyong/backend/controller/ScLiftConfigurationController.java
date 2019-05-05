package com.shangyong.backend.controller;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.ScLiftConfiguration;
import com.shangyong.backend.service.impl.ScLiftConfigurationServiceImpl;
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
@RequestMapping(value = "/backend/scLiftConfiguration")
public class ScLiftConfigurationController {
	@Autowired
	private ScLiftConfigurationServiceImpl scLiftConfigurationService;
	private static Logger logger = Logger.getLogger(ScLiftConfigurationController.class);

	/**
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/scLiftConfigurationListAll.do", method = RequestMethod.POST)
	public void scLiftConfigurationListAll(HttpServletRequest request, HttpServletResponse response, ScLiftConfiguration scLiftConfiguration) {
		try {
			int count = scLiftConfigurationService.listAllCount(scLiftConfiguration);
			List<ScLiftConfiguration> list = scLiftConfigurationService.findAll(scLiftConfiguration);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 保存/更新
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/saveScLiftConfiguration.do", method = RequestMethod.POST)
	public void saveScLiftConfiguration(HttpServletRequest request, HttpServletResponse response,
                                        ScLiftConfiguration scLiftConfiguration) {
		try {
			if(scLiftConfiguration == null){
				JSONUtils.toJSON(response, CodeUtils.BACKEND_OBJECT_NULL);
				return;
			}else{
				String mateName=scLiftConfiguration.getMateName();
				String matePhone=scLiftConfiguration.getMatePhone();
				String mateCertCode=scLiftConfiguration.getMateCertCode();
				if(!"1".equals(mateName)&&!"1".equals(matePhone)&&!"1".equals(mateCertCode)){
					JSONUtils.toJSON(response, CodeUtils.SCLIFTCONFIGURATION_SAVE_FAIL);
					return;
				}else{
					if (scLiftConfiguration.getScLiftConfigurationId() != null) { // 更新
						// 需要先判断当前页面的版本号是否与当前数据的一致，如果不一致则表示有另一人已经修改，而当前的页面数据不是最新
						if (scLiftConfiguration.getVersion() == null) { // 版本号
							JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
							return;
						}
						ScLiftConfiguration info = scLiftConfigurationService.getEntityById(scLiftConfiguration);
						if (info != null) {
							if (info.getVersion() == null|| !scLiftConfiguration.getVersion().toString().equals(info.getVersion().toString())) {
								JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
								return;
							}
							boolean flag = scLiftConfigurationService.updateEntity(scLiftConfiguration);
							if (flag) {
								JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
								return;
							} else {
								JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
								return;
							}
						}
					} else {// 添加
		 				boolean flag = scLiftConfigurationService.saveEntity(scLiftConfiguration);
						if (flag) {
							JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
							return;
						} else {
							JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
							return;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
		}
	}

	/**
	 * 查询
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getEntityById.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, ScLiftConfiguration scLiftConfiguration) {
		try {
			if (scLiftConfiguration.getScLiftConfigurationId() != null) {
				ScLiftConfiguration list = scLiftConfigurationService.getEntityById(scLiftConfiguration);
				logger.info("查询提额配置信息" + list);
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				LinkedHashMap<String, Object> map1 = new LinkedHashMap<String, Object>();
				map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
				map.put(Constants.MESSAGE, CodeUtils.SUCCESSNEWS.getMessage());
				map1.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());

				map1.put("scLiftConfigurationObject", list);
				map.put(Constants.DATA, map1);
				SpringUtils.renderJson(response, map);
			} else {
				logger.error("缺少参数");
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询提额配置信息异常" + e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.NON_EXISTENT);
		}
	}
}
