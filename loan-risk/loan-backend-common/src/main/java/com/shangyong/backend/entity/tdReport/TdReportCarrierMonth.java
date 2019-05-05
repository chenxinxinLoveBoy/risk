package com.shangyong.backend.entity.tdReport;


/**
 * 同盾报告每个月运营商消费统计表bean
 * @author kenzhao
 * @date Thu Mar 15 10:17:18 CST 2018
 **/
public class TdReportCarrierMonth {

	/**唯一标识**/
	private String carrierMonthId;

	/**申请单编号**/
	private String applicationId;

	/**月份**/
	private String month;

	/**月消费金额**/
	private String consumeAmount;

	/**月充值次数**/
	private String rechargeCount;

	/**月充值金额**/
	private String rechargeAmount;

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


	public TdReportCarrierMonth() {
		super();
	}
	public TdReportCarrierMonth(String carrierMonthId,String applicationId,String month,String consumeAmount,String rechargeCount,String rechargeAmount,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.carrierMonthId = carrierMonthId;
		this.applicationId = applicationId;
		this.month = month;
		this.consumeAmount = consumeAmount;
		this.rechargeCount = rechargeCount;
		this.rechargeAmount = rechargeAmount;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setCarrierMonthId(String carrierMonthId){
		this.carrierMonthId = carrierMonthId;
	}

	public String getCarrierMonthId(){
		return this.carrierMonthId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setMonth(String month){
		this.month = month;
	}

	public String getMonth(){
		return this.month;
	}

	public void setConsumeAmount(String consumeAmount){
		this.consumeAmount = consumeAmount;
	}

	public String getConsumeAmount(){
		return this.consumeAmount;
	}

	public void setRechargeCount(String rechargeCount){
		this.rechargeCount = rechargeCount;
	}

	public String getRechargeCount(){
		return this.rechargeCount;
	}

	public void setRechargeAmount(String rechargeAmount){
		this.rechargeAmount = rechargeAmount;
	}

	public String getRechargeAmount(){
		return this.rechargeAmount;
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
