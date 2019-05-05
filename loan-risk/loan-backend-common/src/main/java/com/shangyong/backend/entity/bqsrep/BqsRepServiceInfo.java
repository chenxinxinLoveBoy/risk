package com.shangyong.backend.entity.bqsrep;


/**
 * 常用服务bean
 * @author chengfeng.lu
 * @date Fri Dec 15 14:16:10 CST 2017
 **/
public class BqsRepServiceInfo {

	/****/
	private String bqsServiceInfoId;

	/****/
	private String bqsPetitionerId;

	/**月联系次数**/
	private String connectCount;

	/****/
	private String month;

	/**企业服务类型**/
	private String seviceType;

	/**企业服务名称**/
	private String serviceName;


	public BqsRepServiceInfo() {
		super();
	}
	public BqsRepServiceInfo(String bqsServiceInfoId,String bqsPetitionerId,String connectCount,String month,String seviceType,String serviceName) {
		super();
		this.bqsServiceInfoId = bqsServiceInfoId;
		this.bqsPetitionerId = bqsPetitionerId;
		this.connectCount = connectCount;
		this.month = month;
		this.seviceType = seviceType;
		this.serviceName = serviceName;
	}
	public void setBqsServiceInfoId(String bqsServiceInfoId){
		this.bqsServiceInfoId = bqsServiceInfoId;
	}

	public String getBqsServiceInfoId(){
		return this.bqsServiceInfoId;
	}

	public void setBqsPetitionerId(String bqsPetitionerId){
		this.bqsPetitionerId = bqsPetitionerId;
	}

	public String getBqsPetitionerId(){
		return this.bqsPetitionerId;
	}

	public void setConnectCount(String connectCount){
		this.connectCount = connectCount;
	}

	public String getConnectCount(){
		return this.connectCount;
	}

	public void setMonth(String month){
		this.month = month;
	}

	public String getMonth(){
		return this.month;
	}

	public void setSeviceType(String seviceType){
		this.seviceType = seviceType;
	}

	public String getSeviceType(){
		return this.seviceType;
	}

	public void setServiceName(String serviceName){
		this.serviceName = serviceName;
	}

	public String getServiceName(){
		return this.serviceName;
	}

}
