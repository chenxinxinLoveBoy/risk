package com.shangyong.backend.entity;


/**
 * bean
 * @author kenzhao
 * @date Thu Dec 21 17:26:56 CST 2017
 **/
public class BuApplicationDetail {

	/****/
	private String buApplicationDetailId;

	/****/
	private String applicationId;

	/**是否命中 1：通过 ，3：不通过**/
	private Integer state;

	/**步骤编号**/
	private Integer stepNum;

	/**禁止项规则对应编号**/
	private String banCode;

	/**描述**/
	private String descrip;

	/****/
	private String remark;

	/****/
	private String createTime;

	/****/
	private String createMan;

	/****/
	private String modifyTime;

	/****/
	private String modifyMan;


	/** 黑名单数据来源 **/
	private String blacklistDsSource;

	/** 禁止项分类 **/
	private String banClassCode;

	public BuApplicationDetail() {
		super();
	}

	public BuApplicationDetail(String buApplicationDetailId, String applicationId, Integer state, Integer stepNum,
							   String banCode, String descrip, String remark, String createTime, String createMan,
							   String modifyTime, String modifyMan, String blacklistDsSource, String banClassCode) {
		this.buApplicationDetailId = buApplicationDetailId;
		this.applicationId = applicationId;
		this.state = state;
		this.stepNum = stepNum;
		this.banCode = banCode;
		this.descrip = descrip;
		this.remark = remark;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.blacklistDsSource = blacklistDsSource;
		this.banClassCode = banClassCode;
	}

	public String getBlacklistDsSource() {
		return blacklistDsSource;
	}

	public void setBlacklistDsSource(String blacklistDsSource) {
		this.blacklistDsSource = blacklistDsSource;
	}

	public String getBanClassCode() {
		return banClassCode;
	}

	public void setBanClassCode(String banClassCode) {
		this.banClassCode = banClassCode;
	}

	public void setBuApplicationDetailId(String buApplicationDetailId){
		this.buApplicationDetailId = buApplicationDetailId;
	}

	public String getBuApplicationDetailId(){
		return this.buApplicationDetailId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setState(Integer state){
		this.state = state;
	}

	public Integer getState(){
		return this.state;
	}

	public void setStepNum(Integer stepNum){
		this.stepNum = stepNum;
	}

	public Integer getStepNum(){
		return this.stepNum;
	}

	public void setBanCode(String banCode){
		this.banCode = banCode;
	}

	public String getBanCode(){
		return this.banCode;
	}

	public void setDescrip(String descrip){
		this.descrip = descrip;
	}

	public String getDescrip(){
		return this.descrip;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
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

}
