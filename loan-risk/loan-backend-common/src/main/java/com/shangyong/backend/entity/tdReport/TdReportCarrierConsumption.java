package com.shangyong.backend.entity.tdReport;


/**
 * 同盾报告运营商消费统计表bean
 * @author kenzhao
 * @date Thu Mar 15 10:17:18 CST 2018
 **/
public class TdReportCarrierConsumption {

	/**唯一标识**/
	private String carrierConsumptionId;

	/**申请单编号**/
	private String applicationId;

	/**近1月消费金额**/
	private String consumeAmount1month;

	/**近3月消费金额**/
	private String consumeAmount3month;

	/**近6月消费金额**/
	private String consumeAmount6month;

	/**近1月充值次数**/
	private String rechargeCount1month;

	/**近3月充值次数**/
	private String rechargeCount3month;

	/**近6月充值次数**/
	private String rechargeCount6month;

	/**近1月充值金额**/
	private String rechargeAmount1month;

	/**近3月充值金额**/
	private String rechargeAmount3month;

	/**近6月充值金额**/
	private String rechargeAmount6month;

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


	public TdReportCarrierConsumption() {
		super();
	}
	public TdReportCarrierConsumption(String carrierConsumptionId,String applicationId,String consumeAmount1month,String consumeAmount3month,String consumeAmount6month,String rechargeCount1month,String rechargeCount3month,String rechargeCount6month,String rechargeAmount1month,String rechargeAmount3month,String rechargeAmount6month,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.carrierConsumptionId = carrierConsumptionId;
		this.applicationId = applicationId;
		this.consumeAmount1month = consumeAmount1month;
		this.consumeAmount3month = consumeAmount3month;
		this.consumeAmount6month = consumeAmount6month;
		this.rechargeCount1month = rechargeCount1month;
		this.rechargeCount3month = rechargeCount3month;
		this.rechargeCount6month = rechargeCount6month;
		this.rechargeAmount1month = rechargeAmount1month;
		this.rechargeAmount3month = rechargeAmount3month;
		this.rechargeAmount6month = rechargeAmount6month;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setCarrierConsumptionId(String carrierConsumptionId){
		this.carrierConsumptionId = carrierConsumptionId;
	}

	public String getCarrierConsumptionId(){
		return this.carrierConsumptionId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setConsumeAmount1month(String consumeAmount1month){
		this.consumeAmount1month = consumeAmount1month;
	}

	public String getConsumeAmount1month(){
		return this.consumeAmount1month;
	}

	public void setConsumeAmount3month(String consumeAmount3month){
		this.consumeAmount3month = consumeAmount3month;
	}

	public String getConsumeAmount3month(){
		return this.consumeAmount3month;
	}

	public void setConsumeAmount6month(String consumeAmount6month){
		this.consumeAmount6month = consumeAmount6month;
	}

	public String getConsumeAmount6month(){
		return this.consumeAmount6month;
	}

	public void setRechargeCount1month(String rechargeCount1month){
		this.rechargeCount1month = rechargeCount1month;
	}

	public String getRechargeCount1month(){
		return this.rechargeCount1month;
	}

	public void setRechargeCount3month(String rechargeCount3month){
		this.rechargeCount3month = rechargeCount3month;
	}

	public String getRechargeCount3month(){
		return this.rechargeCount3month;
	}

	public void setRechargeCount6month(String rechargeCount6month){
		this.rechargeCount6month = rechargeCount6month;
	}

	public String getRechargeCount6month(){
		return this.rechargeCount6month;
	}

	public void setRechargeAmount1month(String rechargeAmount1month){
		this.rechargeAmount1month = rechargeAmount1month;
	}

	public String getRechargeAmount1month(){
		return this.rechargeAmount1month;
	}

	public void setRechargeAmount3month(String rechargeAmount3month){
		this.rechargeAmount3month = rechargeAmount3month;
	}

	public String getRechargeAmount3month(){
		return this.rechargeAmount3month;
	}

	public void setRechargeAmount6month(String rechargeAmount6month){
		this.rechargeAmount6month = rechargeAmount6month;
	}

	public String getRechargeAmount6month(){
		return this.rechargeAmount6month;
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
