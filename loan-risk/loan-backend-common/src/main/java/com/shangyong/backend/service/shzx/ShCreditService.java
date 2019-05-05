package com.shangyong.backend.service.shzx;

import java.util.List;

import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.sh.ShCreditGuarantee;
import com.shangyong.backend.entity.sh.ShCreditLoans;
import com.shangyong.backend.entity.sh.ShCreditSpecialDeal;
import com.shangyong.backend.entity.sh.ShCreditTheme;
/**
 * 上海资信
 * @author Smk
 *
 */
public interface ShCreditService {

	/**
	 * 上海资信数据落地
	 * @param application
	 * @return
	 */
	public RuleResult queryShCredit(Application application);
	
	/**
	 * 获取上海资信数据
	 * @param applicationId
	 * @return
	 */
	public ShCreditTheme queryByApplicationId(String applicationId);
	
	/**
	 * 获取上海资信贷款详情记录
	 * @param applicationId
	 * @return
	 */
	public List<ShCreditLoans> queryAllDealsByApplicationId(String applicationId);
	
	/**
	 * 获取上海资信担保记录
	 * @param applicationId
	 * @return
	 */
	public List<ShCreditGuarantee> getAllGuranteeByApplicationId(String applicationId);

	/**
	 * 获取上海资信特殊交易记录
	 * @param applicationId
	 * @return
	 */
	public List<ShCreditSpecialDeal> getAllSpecialDealByApplicationId(String applicationId);
}
