package com.shangyong.backend.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.dao.BuGjApplicationDao;
import com.shangyong.backend.entity.BuGjApplication;
import com.shangyong.backend.entity.CuPlatformCustomer;
import com.shangyong.backend.service.BaseService;

/**
 * 申请单信息 service实现类
 * 
 * @author xixinghui
 *
 */
@Service
public class BuGjApplicationServiceImpl implements BaseService<BuGjApplication> {

	
	@Autowired
	private BuGjApplicationDao buGjApplicationDao;
	
	@Override
	public List<BuGjApplication> findAll(BuGjApplication t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BuGjApplication getEntityById(String id) {
		return buGjApplicationDao.getEntityById(id);
	}

	@Override
	public BuGjApplication getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BuGjApplication getEntityById(BuGjApplication t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveEntity(BuGjApplication buGjApplication) {
		// 处理状态：待处理
		buGjApplication.setHandleState(Constants.DAI_CL_STAATE);
		CuPlatformCustomer cuPlatformCustomer = new CuPlatformCustomer();
		cuPlatformCustomer.setAppName("3");	
		cuPlatformCustomer.setCustomerId(buGjApplication.getCustomerId());
		return buGjApplicationDao.saveEntity(buGjApplication);
	}

	@Override
	public boolean updateEntity(BuGjApplication t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(BuGjApplication t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 根据步骤号查询待处理的审批单列表
	 * @param
	 */
	public List<Map<String, Object>> getAppLicationList(String isStep){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("handleState", Constants.DAI_CL_STAATE); // 处理状态：1、待处理
		map.put("isStep", isStep); // 步骤标识（1：同盾贷前审核、2：聚信立蜜蜂、3：聚信立蜜罐）
		return buGjApplicationDao.getGjAppLicationList(map);
	}
	
}
