package com.shangyong.backend.dubbo;

import java.util.List;

import com.shangyong.backend.entity.CuCustomerCompany;
import com.shangyong.backend.entity.CuCustomerOther;
import com.shangyong.backend.entity.CuIcePerson;
import com.shangyong.backend.entity.CuPlatformCustomer;
import com.shangyong.backend.entity.FaceRecognitionScore;

public interface CustomerDubboService {

	/**
	 * 保存客户端传过来的所有客户信息
	 *
	 * @param cuCustomerOtherList
	 * @param cuPlatformCustomer
	 * @param cuCustomerCompany
	 * @param faceRecognitionScore
	 * @return
	 */
	public CuPlatformCustomer save(List<CuCustomerOther> cuCustomerOtherList,
                                   CuPlatformCustomer cuPlatformCustomer,
                                   CuCustomerCompany cuCustomerCompany,
                                   FaceRecognitionScore faceRecognitionScore);

	/**
	 * 保存紧急联系人
	 * @param applicationId
	 * @param customerId
	 * @param platformCustomerId
	 * @param cuIcePersonList
	 */
	public void saveCuIcePersonList(String applicationId, String customerId, String platformCustomerId, List<CuIcePerson> cuIcePersonList);

}
