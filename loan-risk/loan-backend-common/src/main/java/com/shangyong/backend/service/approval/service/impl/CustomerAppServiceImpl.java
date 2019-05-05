package com.shangyong.backend.service.approval.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.ApplicationDao;
import com.shangyong.backend.dao.approval.CustomerDao;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.approval.Customer;
import com.shangyong.backend.service.approval.service.CustomerAppService;
/**
 * 用户信息查询实现层
 * @author Smk
 *
 */
@Service
public class CustomerAppServiceImpl implements CustomerAppService {
	
	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private ApplicationDao applicationDao;
	
	@Override
	public Map<String,String> queryCustomerById(String customerId) {
		
		Customer customer = customerDao.getCustomerById(customerId);
		if(null == customer){
			return null;
		}

		//最后登录的时间
		String lastLoginTime = customer.getLastLoginTime();
		//app版本号
		Application appcalition = applicationDao.getByCustomerId(customerId);
		String appVersion = appcalition.getAppVersion();
		
		
		Map<String, String> map = new HashMap<String,String>();
		map.put("lastLoginTime", lastLoginTime);
		map.put("appVersion", appVersion);
		return map;
	}

}
