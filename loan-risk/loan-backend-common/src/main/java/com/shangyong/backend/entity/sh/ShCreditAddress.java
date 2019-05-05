package com.shangyong.backend.entity.sh;


/**
 * 上海资信用户地址表bean
 * @author kenzhao
 * @date Fri Mar 09 15:18:41 CST 2018
 **/
public class ShCreditAddress {

	/**主键自增id**/
	private String shCreditAddressId;

	/**申请单编号**/
	private String applicationId;

	/**地址序号**/
	private String addressNumber;

	/**地址明细**/
	private String addressDetail;

	/**地址信息获取时间**/
	private String addressTime;

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


	public ShCreditAddress() {
		super();
	}
	public ShCreditAddress(String shCreditAddressId,String applicationId,String addressNumber,String addressDetail,String addressTime,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.shCreditAddressId = shCreditAddressId;
		this.applicationId = applicationId;
		this.addressNumber = addressNumber;
		this.addressDetail = addressDetail;
		this.addressTime = addressTime;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setShCreditAddressId(String shCreditAddressId){
		this.shCreditAddressId = shCreditAddressId;
	}

	public String getShCreditAddressId(){
		return this.shCreditAddressId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setAddressNumber(String addressNumber){
		this.addressNumber = addressNumber;
	}

	public String getAddressNumber(){
		return this.addressNumber;
	}

	public void setAddressDetail(String addressDetail){
		this.addressDetail = addressDetail;
	}

	public String getAddressDetail(){
		return this.addressDetail;
	}

	public void setAddressTime(String addressTime){
		this.addressTime = addressTime;
	}

	public String getAddressTime(){
		return this.addressTime;
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
