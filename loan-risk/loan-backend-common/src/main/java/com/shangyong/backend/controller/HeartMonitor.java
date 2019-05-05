package com.shangyong.backend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
@Controller
@RequestMapping("heart/")
public class HeartMonitor {

	@RequestMapping(value = "monitor.do", method = RequestMethod.GET)
	public void heartMonitor(HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONUtils.toJSON(response, CodeUtils.SUCCESS);
		} catch (Exception e) {
			JSONUtils.toJSON(response, CodeUtils.FAIL);
		}
		return;
	}
}
