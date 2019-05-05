package com.shangyong.authcenter.dubbo;

import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.T;

import com.shangyong.entity.BaseResult;
/**
 * @author CG
 */
public interface AuthMoneyDubboService {
	/**
	 * 更新用户授信额度
	 */
	public BaseResult<T> updateAuthMoney(Map<String, String> param);

}
