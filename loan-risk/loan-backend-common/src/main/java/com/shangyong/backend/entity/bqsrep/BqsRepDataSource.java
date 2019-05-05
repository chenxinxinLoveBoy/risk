package com.shangyong.backend.entity.bqsrep;


/**
 * 数据来源bean
 * @author chengfeng.lu
 * @date Thu Dec 14 18:51:49 CST 2017
 **/
public class BqsRepDataSource {

	/****/
	private String bqsDataSourceId;

	/****/
	private String bqsPetitionerId;

	/**数据源**/
	private String source;

	/**数据源类型**/
	private String sourceType;

	/**原始数据获取时间**/
	private String storeTime;

	/**数据源时间跨度**/
	private String sourceTime;

	/**是否实名认证**/
	private String passRealName;

	/**实名制信息(身份证+手机号+实名制渠道) **/
	private String realNameInfo;

	/**是否本人**/
	private String equalToPetitioner;


	public BqsRepDataSource() {
		super();
	}
	public BqsRepDataSource(String bqsDataSourceId,String bqsPetitionerId,String source,String sourceType,String storeTime,String sourceTime,String passRealName,String realNameInfo,String equalToPetitioner) {
		super();
		this.bqsDataSourceId = bqsDataSourceId;
		this.bqsPetitionerId = bqsPetitionerId;
		this.source = source;
		this.sourceType = sourceType;
		this.storeTime = storeTime;
		this.sourceTime = sourceTime;
		this.passRealName = passRealName;
		this.realNameInfo = realNameInfo;
		this.equalToPetitioner = equalToPetitioner;
	}
	public void setBqsDataSourceId(String bqsDataSourceId){
		this.bqsDataSourceId = bqsDataSourceId;
	}

	public String getBqsDataSourceId(){
		return this.bqsDataSourceId;
	}

	public void setBqsPetitionerId(String bqsPetitionerId){
		this.bqsPetitionerId = bqsPetitionerId;
	}

	public String getBqsPetitionerId(){
		return this.bqsPetitionerId;
	}

	public void setSource(String source){
		this.source = source;
	}

	public String getSource(){
		return this.source;
	}

	public void setSourceType(String sourceType){
		this.sourceType = sourceType;
	}

	public String getSourceType(){
		return this.sourceType;
	}

	public void setStoreTime(String storeTime){
		this.storeTime = storeTime;
	}

	public String getStoreTime(){
		return this.storeTime;
	}

	public void setSourceTime(String sourceTime){
		this.sourceTime = sourceTime;
	}

	public String getSourceTime(){
		return this.sourceTime;
	}

	public void setPassRealName(String passRealName){
		this.passRealName = passRealName;
	}

	public String getPassRealName(){
		return this.passRealName;
	}

	public void setRealNameInfo(String realNameInfo){
		this.realNameInfo = realNameInfo;
	}

	public String getRealNameInfo(){
		return this.realNameInfo;
	}

	public void setEqualToPetitioner(String equalToPetitioner){
		this.equalToPetitioner = equalToPetitioner;
	}

	public String getEqualToPetitioner(){
		return this.equalToPetitioner;
	}

}
