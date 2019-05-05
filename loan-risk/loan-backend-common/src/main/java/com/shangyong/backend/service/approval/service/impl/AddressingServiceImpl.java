package com.shangyong.backend.service.approval.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.dao.tdf.TdFacilityDeviceAndroidServiceDao;
import com.shangyong.backend.dao.tdf.TdFacilityDeviceIosServiceDao;
import com.shangyong.backend.dao.tdf.TdFacilityGeoIpServiceDao;
import com.shangyong.backend.entity.approval.EquipmentInformation;
import com.shangyong.backend.entity.tdf.TdFacilityGeoIp;
import com.shangyong.backend.service.approval.service.AddressingService;

@Service
public class AddressingServiceImpl implements AddressingService {
	
	@Autowired
	private TdFacilityDeviceAndroidServiceDao androidServiceDao;
	
	@Autowired
	private TdFacilityDeviceIosServiceDao iosServiceDao;
	      
	@Autowired
	private TdFacilityGeoIpServiceDao gpServiceDao;
	
	//日志
	private static Logger logger = LoggerFactory.getLogger("lbs");
	
	//高德地图url
	private final static String gdUrl = "http://restapi.amap.com/v3/geocode/regeo?parameters";
	//高德地图key
	private final static String gdKey = "0906ced63e396205bed5ef3ee0aaf0b2";

	@Override
	public EquipmentInformation queryInfo(String applicationId, String source) {
		EquipmentInformation equipmentInformation = new EquipmentInformation();
		try {
			if ("0".equals(source)) {
				//android查询手机品牌   
				String androidBrand = androidServiceDao.queryByAid(applicationId);							
				if(StringUtils.isNotBlank(androidBrand)){			
					equipmentInformation.setEquipmentName(androidBrand.toLowerCase());
				}else{
					equipmentInformation.setEquipmentName("未知型号");
				}
			}else{							
				//ios查询手机品牌
				String iosBrand = iosServiceDao.queryByBuApplicationId(applicationId);
				if(StringUtils.isNotBlank(iosBrand)){			
					equipmentInformation.setEquipmentName(iosBrand.toLowerCase());
				}else{												
					equipmentInformation.setEquipmentName("未知型号");
				}
			}
			
			TdFacilityGeoIp gp = gpServiceDao.querybyId(applicationId);
			/** 获取lbs省份lbsProvince、城市lbsCity、详细地址lbsAddress */
			String lbsAddress = gp.getLbsAddress();
			String lbsCity = gp.getLbsCity();
			String lbsProvince = gp.getLbsProvince();
			
			if (StringUtils.isBlank(lbsAddress) || StringUtils.isBlank(lbsProvince)) {
				logger.info("用户LBS地址获取失败");
			}
			String formatted_address = lbsProvince+lbsCity+lbsAddress;
			logger.info("用户的位置是:"+ formatted_address);
			equipmentInformation.setAddress(formatted_address);
		} catch (Exception e) {
			throw new RuntimeException("内部错误:"+e.getMessage(), e);
		}
		return equipmentInformation;
	}

}