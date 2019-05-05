package com.shangyong.backend.entity.bqsrep;


/**
 * 反欺诈云bean
 * @author chengfeng.lu
 * @date Thu Dec 14 18:51:48 CST 2017
 **/
public class BqsRepAntiFraudCloud {

	/****/
	private String bqsAntiFraudCloudId;

	/****/
	private String bqsPetitionerId;

	/**是否多头申请（90天之内）**/
	private String partnerCount;

	/**手机号关联身份证个数（90天之内）**/
	private String idcCount;

	/**身份证关联手机号个数（90天之内）**/
	private String phoneCount;

	/**手机号星网模型大小（180天之内）**/
	private String starnetCount;


	public BqsRepAntiFraudCloud() {
		super();
	}
	public BqsRepAntiFraudCloud(String bqsAntiFraudCloudId,String bqsPetitionerId,String partnerCount,String idcCount,String phoneCount,String starnetCount) {
		super();
		this.bqsAntiFraudCloudId = bqsAntiFraudCloudId;
		this.bqsPetitionerId = bqsPetitionerId;
		this.partnerCount = partnerCount;
		this.idcCount = idcCount;
		this.phoneCount = phoneCount;
		this.starnetCount = starnetCount;
	}
	public void setBqsAntiFraudCloudId(String bqsAntiFraudCloudId){
		this.bqsAntiFraudCloudId = bqsAntiFraudCloudId;
	}

	public String getBqsAntiFraudCloudId(){
		return this.bqsAntiFraudCloudId;
	}

	public void setBqsPetitionerId(String bqsPetitionerId){
		this.bqsPetitionerId = bqsPetitionerId;
	}

	public String getBqsPetitionerId(){
		return this.bqsPetitionerId;
	}

	public void setPartnerCount(String partnerCount){
		this.partnerCount = partnerCount;
	}

	public String getPartnerCount(){
		return this.partnerCount;
	}

	public void setIdcCount(String idcCount){
		this.idcCount = idcCount;
	}

	public String getIdcCount(){
		return this.idcCount;
	}

	public void setPhoneCount(String phoneCount){
		this.phoneCount = phoneCount;
	}

	public String getPhoneCount(){
		return this.phoneCount;
	}

	public void setStarnetCount(String starnetCount){
		this.starnetCount = starnetCount;
	}

	public String getStarnetCount(){
		return this.starnetCount;
	}

}
