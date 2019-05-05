package com.shangyong.backend.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * 复核用户service
 * @author Administrator
 *
 */
public interface CheckCustomerService {

	/**
	 * 上传CSV文件
	 * @return
	 */
	public String uploadCSVFile(HttpServletRequest request, MultipartFile file);
	
	/**
	 * 读取csv文件
	 * @return
	 */
	public List<String> readCSVFile();
}
