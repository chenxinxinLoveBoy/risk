package com.shangyong.authcenter.dubbo.impl;

import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shangyong.authcenter.dubbo.AuthMoneyDubboService;
import com.shangyong.entity.BaseResult;

/**
 * @Descript 更新用户授信额度
 * @author CG
 */
@Service
public class AuthMoneyInfoService {

	@Reference(version = "1.0.0", retries = -1, timeout = 15000) // 调取dubbo接口
	private AuthMoneyDubboService authMoneyDubboService;

	public BaseResult<T> updateAuthMoney(Map<String, String> param) {
		return authMoneyDubboService.updateAuthMoney(param);
	}
}
