package com.shangyong.backend.entity.sh;


/**
 * 上海资信贷款交易信息表bean
 * @author kenzhao
 * @date Fri Mar 09 15:18:41 CST 2018
 **/
public class ShCreditLoansDeal {

	/**主键自增id**/
	private String shCreditDealId;

	/**申请单编号**/
	private String applicationId;

	/**贷款笔数**/
	private String loansNumber;

	/**首贷日**/
	private String loansTemps;

	/**最大授信额度**/
	private String maxLimit;

	/**贷款总额**/
	private String loansTotal;

	/**贷款余额**/
	private String loansBalance;

	/**协定月还款**/
	private String appointRefundMonth;

	/**当前逾期总额**/
	private String overdueAllMoney;

	/**最高逾期金额**/
	private String maxOverdueMoney;

	/**最高逾期期数**/
	private String maxOverdueNumber;

	/**创建日期**/
	private String createTime;

	/**创建人**/
	private String createMan;

	/**修改日期**/
	private String modifyTime;

	/**修改人**/
	private String modifyMan;

	/**备注**/
	private String remark;


	public ShCreditLoansDeal() {
		super();
	}
	public ShCreditLoansDeal(String shCreditDealId,String applicationId,String loansNumber,String loansTemps,String maxLimit,String loansTotal,String loansBalance,String appointRefundMonth,String overdueAllMoney,String maxOverdueMoney,String maxOverdueNumber,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.shCreditDealId = shCreditDealId;
		this.applicationId = applicationId;
		this.loansNumber = loansNumber;
		this.loansTemps = loansTemps;
		this.maxLimit = maxLimit;
		this.loansTotal = loansTotal;
		this.loansBalance = loansBalance;
		this.appointRefundMonth = appointRefundMonth;
		this.overdueAllMoney = overdueAllMoney;
		this.maxOverdueMoney = maxOverdueMoney;
		this.maxOverdueNumber = maxOverdueNumber;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setShCreditDealId(String shCreditDealId){
		this.shCreditDealId = shCreditDealId;
	}

	public String getShCreditDealId(){
		return this.shCreditDealId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setLoansNumber(String loansNumber){
		this.loansNumber = loansNumber;
	}

	public String getLoansNumber(){
		return this.loansNumber;
	}

	public void setLoansTemps(String loansTemps){
		this.loansTemps = loansTemps;
	}

	public String getLoansTemps(){
		return this.loansTemps;
	}

	public void setMaxLimit(String maxLimit){
		this.maxLimit = maxLimit;
	}

	public String getMaxLimit(){
		return this.maxLimit;
	}

	public void setLoansTotal(String loansTotal){
		this.loansTotal = loansTotal;
	}

	public String getLoansTotal(){
		return this.loansTotal;
	}

	public void setLoansBalance(String loansBalance){
		this.loansBalance = loansBalance;
	}

	public String getLoansBalance(){
		return this.loansBalance;
	}

	public void setAppointRefundMonth(String appointRefundMonth){
		this.appointRefundMonth = appointRefundMonth;
	}

	public String getAppointRefundMonth(){
		return this.appointRefundMonth;
	}

	public void setOverdueAllMoney(String overdueAllMoney){
		this.overdueAllMoney = overdueAllMoney;
	}

	public String getOverdueAllMoney(){
		return this.overdueAllMoney;
	}

	public void setMaxOverdueMoney(String maxOverdueMoney){
		this.maxOverdueMoney = maxOverdueMoney;
	}

	public String getMaxOverdueMoney(){
		return this.maxOverdueMoney;
	}

	public void setMaxOverdueNumber(String maxOverdueNumber){
		this.maxOverdueNumber = maxOverdueNumber;
	}

	public String getMaxOverdueNumber(){
		return this.maxOverdueNumber;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setCreateMan(String createMan){
		this.createMan = createMan;
	}

	public String getCreateMan(){
		return this.createMan;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}

	public void setModifyMan(String modifyMan){
		this.modifyMan = modifyMan;
	}

	public String getModifyMan(){
		return this.modifyMan;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

}
