package com.shangyong.backend.service.approval.service;

import com.shangyong.backend.entity.approval.EquipmentInformation;
import com.shangyong.backend.entity.tdf.TdFacilityGeoIp;

public interface AddressingService {
	
	public EquipmentInformation queryInfo(String applicationId, String appName);


}
