package com.shangyong.backend.entity.tdf;


/**
 * 同盾设备指纹身份证手机校验表bean
 * @author mingke.shi
 * @date Wed Dec 13 20:35:23 CST 2017
 **/
public class TdFacilityAttributionCreditScore {

	/**主键id**/
	private String tdFacilityAttributionCreditScoreId;

	/**申请单编号**/
	private String buApplicationId;

	/**身份证所属省**/
	private String idCardProvince;

	/**身份证所属市**/
	private String idCardCity;

	/**身份证所属区**/
	private String county;

	/**手机号所属省**/
	private String mobileAddressProvince;

	/**手机号所属市**/
	private String mobileAddressCity;

	/**身份证或手机号信用分数,0代表查不到对应的信用分数，-1代表接口内部查询信用分数超时了**/
	private String idNumberMobileScore;

	/**手机号信用分数**/
	private String mobileScore;

	/**身份证信用分数**/
	private String idNumberScore;

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


	public TdFacilityAttributionCreditScore() {
		super();
	}
	public TdFacilityAttributionCreditScore(String tdFacilityAttributionCreditScoreId,String buApplicationId,String idCardProvince,String idCardCity,String county,String mobileAddressProvince,String mobileAddressCity,String idNumberMobileScore,String mobileScore,String idNumberScore,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.tdFacilityAttributionCreditScoreId = tdFacilityAttributionCreditScoreId;
		this.buApplicationId = buApplicationId;
		this.idCardProvince = idCardProvince;
		this.idCardCity = idCardCity;
		this.county = county;
		this.mobileAddressProvince = mobileAddressProvince;
		this.mobileAddressCity = mobileAddressCity;
		this.idNumberMobileScore = idNumberMobileScore;
		this.mobileScore = mobileScore;
		this.idNumberScore = idNumberScore;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setTdFacilityAttributionCreditScoreId(String tdFacilityAttributionCreditScoreId){
		this.tdFacilityAttributionCreditScoreId = tdFacilityAttributionCreditScoreId;
	}

	public String getTdFacilityAttributionCreditScoreId(){
		return this.tdFacilityAttributionCreditScoreId;
	}

	public void setBuApplicationId(String buApplicationId){
		this.buApplicationId = buApplicationId;
	}

	public String getBuApplicationId(){
		return this.buApplicationId;
	}

	public void setIdCardProvince(String idCardProvince){
		this.idCardProvince = idCardProvince;
	}

	public String getIdCardProvince(){
		return this.idCardProvince;
	}

	public void setIdCardCity(String idCardCity){
		this.idCardCity = idCardCity;
	}

	public String getIdCardCity(){
		return this.idCardCity;
	}

	public void setCounty(String county){
		this.county = county;
	}

	public String getCounty(){
		return this.county;
	}

	public void setMobileAddressProvince(String mobileAddressProvince){
		this.mobileAddressProvince = mobileAddressProvince;
	}

	public String getMobileAddressProvince(){
		return this.mobileAddressProvince;
	}

	public void setMobileAddressCity(String mobileAddressCity){
		this.mobileAddressCity = mobileAddressCity;
	}

	public String getMobileAddressCity(){
		return this.mobileAddressCity;
	}

	public void setIdNumberMobileScore(String idNumberMobileScore){
		this.idNumberMobileScore = idNumberMobileScore;
	}

	public String getIdNumberMobileScore(){
		return this.idNumberMobileScore;
	}

	public void setMobileScore(String mobileScore){
		this.mobileScore = mobileScore;
	}

	public String getMobileScore(){
		return this.mobileScore;
	}

	public void setIdNumberScore(String idNumberScore){
		this.idNumberScore = idNumberScore;
	}

	public String getIdNumberScore(){
		return this.idNumberScore;
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
		return "TdFacilityAttributionCreditScore [tdFacilityAttributionCreditScoreId="
				+ tdFacilityAttributionCreditScoreId + ", buApplicationId=" + buApplicationId + ", idCardProvince="
				+ idCardProvince + ", idCardCity=" + idCardCity + ", county=" + county + ", mobileAddressProvince="
				+ mobileAddressProvince + ", mobileAddressCity=" + mobileAddressCity + ", idNumberMobileScore="
				+ idNumberMobileScore + ", mobileScore=" + mobileScore + ", idNumberScore=" + idNumberScore
				+ ", createTime=" + createTime + ", createMan=" + createMan + ", modifyTime=" + modifyTime
				+ ", modifyMan=" + modifyMan + ", remark=" + remark + "]";
	}
	

}
