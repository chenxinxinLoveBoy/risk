package com.shangyong.backend.controller;

import com.shangyong.backend.common.controller.BaseController;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.impl.SysParamServiceImpl;
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
@RequestMapping("/backend/sysParam/")
public class SysParamController extends BaseController {
	private static Logger logger = Logger.getLogger(SysParamController.class);
	@Autowired
	private SysParamServiceImpl sysParamService;

	/**
	 * 参数配置集合
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "getAllSysParam.do", method = RequestMethod.POST)
	public void getAllSysParam(HttpServletRequest request, HttpServletResponse response, SysParam sysParam) {
		try {
			int count = sysParamService.listAllCount(sysParam);// 统计总条数
			List<SysParam> list = sysParamService.findAll(sysParam);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 根据id获取对象信息
	 * 
	 * @param request
	 * @param response
	 * @param sysParam
	 */

	@RequestMapping(value = "getEntityById.do", method = RequestMethod.POST)
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, SysParam sysParam) {
		try {
			SysParam sysParamBo = sysParamService.getEntityById(sysParam.getSysParamId());
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, sysParamBo, "sysParamObject");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 新增/修改
	 * 
	 * @param request
	 * @param response
	 * @param sysParam
	 */

	@RequestMapping(value = "saveSysParam.do")
	public void saveSysParam(HttpServletRequest request, HttpServletResponse response, SysParam sysParam) {
		try {
			if (sysParam != null && sysParam.getSysParamId() != null) {// 修改
				// 需要先判断当前页面的版本号是否与当前数据的一致，如果不一致则表示有另一人已经修改，而当前的页面数据不是最新
				if(sysParam.getVersion() == null){ // 版本号
					JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
					return;
				}
				
				// 拿到数据里的数据信息
				SysParam sysParamBo = sysParamService.getEntityById(sysParam.getSysParamId());
				if(sysParamBo == null){
					JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
					return;
				}
				// 当前页面的版本号与数据库的版本号一致
				if(sysParamBo.getVersion() != null  && sysParam.getVersion() == sysParamBo.getVersion()){
					boolean flag = sysParamService.updateEntity(sysParam);
					if (flag) {
						JSONUtils.toJSON(response, CodeUtils.UPDATE_SUCCESS);
						return;
					} else {
						JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
						return;
					}
				}else{// 不一致
					JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
					return;
				}
				
			} else {// 新增
				SysParam sysParamBo = sysParamService.queryByParamValue(sysParam.getParamValue());
				if (sysParamBo == null) {// 该参数记录不存在，执行新增操作
					boolean flag = sysParamService.saveEntity(sysParam);
					if (flag) {// 成功
						JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
						return;
					} else {// 失败
						JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
						return;
					}
				} else {// 该参数记录已存在，直接返回
					JSONUtils.toJSON(response, CodeUtils.SYS_PARAM_FAIL);
					return;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

}
