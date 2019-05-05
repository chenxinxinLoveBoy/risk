package com.shangyong.backend.dubbo;

import com.shangyong.backend.dubbo.response.DubboBaseResponse;
import com.shangyong.backend.entity.ApplicationDubboServiceBean;

public interface ApplicationDubboService {

	/**
	 * 保存用户借款申请详情
	 * @param applicationDubboServiceBean
	 * @return
	 */
	public DubboBaseResponse saveEntity(ApplicationDubboServiceBean applicationDubboServiceBean);

	/**
	 * 保存用户通讯录、通话记录、短信、应用列表信息
	 * @param jsonInfo json信息
	 * @param saveType 保存类型（1：通讯录 2：通话记录 3：短信 4：应用列表）
	 * @param appName @see com.shangyong.backend.common.enums.AppNameEnum
	 * @param customerId 用户编号
	 * @return
	 */
	public DubboBaseResponse saveAppInfoForMongo(Object jsonInfo, String saveType, String appName, String customerId);
}
