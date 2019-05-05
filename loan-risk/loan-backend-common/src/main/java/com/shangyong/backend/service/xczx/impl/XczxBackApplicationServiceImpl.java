package com.shangyong.backend.service.xczx.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shangyong.backend.dao.xczx.XczxApplicationDao;
import com.shangyong.backend.entity.xczx.XczxApplication;
import com.shangyong.backend.service.xczx.XczxBackApplicationService;

@Service
public class XczxBackApplicationServiceImpl implements XczxBackApplicationService{

	@Autowired
	private XczxApplicationDao xczxApplicationDao;
	/**
	 * @author daniel
	 * 91征信共享端口返回数据
	 */
	@Override
	public List<XczxApplication> queryBackApplication(XczxApplication xczxApplication) {
		
		String companyCode = xczxApplication.getCompanyCode();
		
		xczxApplication.setCompanyCode(null);

		List<XczxApplication> list = new ArrayList<XczxApplication>();
		
		try {
			List<XczxApplication> backApplications = xczxApplicationDao.queryBackApplication(xczxApplication);
			//设置公司编码
			if (backApplications != null && backApplications.size() > 0) {
				for (XczxApplication backApplication : backApplications) {
					backApplication.setCompanyCode(companyCode);
					list.add(backApplication);
				}
			}
		} catch (Exception e) {
			list = null;
			e.printStackTrace();
		}
		
		return list;
	}

}
