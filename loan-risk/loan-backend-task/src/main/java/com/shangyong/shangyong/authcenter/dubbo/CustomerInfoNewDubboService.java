package com.shangyong.shangyong.authcenter.dubbo;

import com.shangyong.entity.BaseResult;

public interface CustomerInfoNewDubboService {
	
	/**
	 * 风控回调接口专用
	 * @author Corey
	 * @version 1.1.1
	 * @date 2018年3月20日 下午7:02:52
	 * @param customerId           用户标识ID
	 * @param applicationId        申请单号ID
	 * @param platform             平台标识
	 * @param riskStepNo           风控步骤编号
	 * @param remark               备注
	 * @return
	 */
	public BaseResult updateCustomerInfoNewStepNoFromRisk(String customerId, String applicationId, String appSerialNumber, String platform, String riskStepNo, String remark);

}
