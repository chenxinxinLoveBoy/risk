package com.shangyong.backend.entity.sh;


/**
 * 上海资信贷款申请信息表bean
 * @author kenzhao
 * @date Fri Mar 09 15:18:41 CST 2018
 **/
public class ShCreditLoansMessage {

	/**主键自增id**/
	private String shCreditLoansId;

	/**申请单编号**/
	private String applicationId;

	/**申请机构**/
	private String loansName;

	/**申请时间**/
	private String loansTemps;

	/**申请金额**/
	private String loansMoney;

	/**申请月数**/
	private String loansMonth;

	/**申请类型**/
	private String loansType;

	/**申请状态**/
	private String loansState;

	/**申请信息获取时间**/
	private String loansTime;

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


	public ShCreditLoansMessage() {
		super();
	}
	public ShCreditLoansMessage(String shCreditLoansId,String applicationId,String loansName,String loansTemps,String loansMoney,String loansMonth,String loansType,String loansState,String loansTime,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.shCreditLoansId = shCreditLoansId;
		this.applicationId = applicationId;
		this.loansName = loansName;
		this.loansTemps = loansTemps;
		this.loansMoney = loansMoney;
		this.loansMonth = loansMonth;
		this.loansType = loansType;
		this.loansState = loansState;
		this.loansTime = loansTime;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setShCreditLoansId(String shCreditLoansId){
		this.shCreditLoansId = shCreditLoansId;
	}

	public String getShCreditLoansId(){
		return this.shCreditLoansId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setLoansName(String loansName){
		this.loansName = loansName;
	}

	public String getLoansName(){
		return this.loansName;
	}

	public void setLoansTemps(String loansTemps){
		this.loansTemps = loansTemps;
	}

	public String getLoansTemps(){
		return this.loansTemps;
	}

	public void setLoansMoney(String loansMoney){
		this.loansMoney = loansMoney;
	}

	public String getLoansMoney(){
		return this.loansMoney;
	}

	public void setLoansMonth(String loansMonth){
		this.loansMonth = loansMonth;
	}

	public String getLoansMonth(){
		return this.loansMonth;
	}

	public void setLoansType(String loansType){
		this.loansType = loansType;
	}

	public String getLoansType(){
		return this.loansType;
	}

	public void setLoansState(String loansState){
		this.loansState = loansState;
	}

	public String getLoansState(){
		return this.loansState;
	}

	public void setLoansTime(String loansTime){
		this.loansTime = loansTime;
	}

	public String getLoansTime(){
		return this.loansTime;
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
