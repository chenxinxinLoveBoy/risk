package com.shangyong.backend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shangyong.backend.common.enums.CodeEnum;
import com.shangyong.backend.utils.JSONUtils;

/**
 * 心跳 接口
 * @author
 * @date
 */
@Controller
@RequestMapping("heart/")
public class HeartMonitorController {

	@RequestMapping(value = "monitor.do", method = RequestMethod.GET)
	public void heartMonitor(HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONUtils.toJSON(response, CodeEnum.SUCCESS);
		} catch (Exception e) {
			JSONUtils.toJSON(response, CodeEnum.FAIL);
		}
		return;
	}
}
