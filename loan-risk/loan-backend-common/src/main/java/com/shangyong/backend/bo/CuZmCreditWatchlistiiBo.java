package com.shangyong.backend.bo;


/**
 * 芝麻信用行业关注名单主表bean
 * @author xiajiyun
 * @date Fri Jul 28 13:39:26 CST 2017
 **/
public class CuZmCreditWatchlistiiBo {

	/**主键自增id**/
	private Integer watchlistiiId;

	/**芝麻信用行业关注名单编码，每次调用接口的UUID流水号，24小时内重复调用不收费**/
	private String transactionId;

	/**申请单编号**/
	private String applicationId;

	/**openId**/
	private String openId;

	/**状态：1:命中，0 否**/
	private Integer state;

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
	
	/**true=命中 在关注名单中 false=未命中*/
	private Boolean isMatched;
	
	/**芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账*/
	private String bizNo;



	public CuZmCreditWatchlistiiBo() {
		super();
	}
	public CuZmCreditWatchlistiiBo(Integer watchlistiiId,String transactionId,String applicationId,String openId,Integer state,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.watchlistiiId = watchlistiiId;
		this.transactionId = transactionId;
		this.applicationId = applicationId;
		this.openId = openId;
		this.state = state;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	
	
	public Boolean getIsMatched() {
		return isMatched;
	}
	public void setIsMatched(Boolean isMatched) {
		this.isMatched = isMatched;
	}
	public String getBizNo() {
		return bizNo;
	}
	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	public void setWatchlistiiId(Integer watchlistiiId){
		this.watchlistiiId = watchlistiiId;
	}

	public Integer getWatchlistiiId(){
		return this.watchlistiiId;
	}

	public void setTransactionId(String transactionId){
		this.transactionId = transactionId;
	}

	public String getTransactionId(){
		return this.transactionId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setOpenId(String openId){
		this.openId = openId;
	}

	public String getOpenId(){
		return this.openId;
	}

	public void setState(Integer state){
		this.state = state;
	}

	public Integer getState(){
		return this.state;
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
	@Override
	public String toString() {
		return "CuZmCreditWatchlistiiBo [watchlistiiId=" + watchlistiiId + ", transactionId=" + transactionId
				+ ", applicationId=" + applicationId + ", openId=" + openId + ", state=" + state + ", createTime="
				+ createTime + ", createMan=" + createMan + ", modifyTime=" + modifyTime + ", modifyMan=" + modifyMan
				+ ", remark=" + remark + ", isMatched=" + isMatched + ", bizNo=" + bizNo + "]";
	}

	
}
