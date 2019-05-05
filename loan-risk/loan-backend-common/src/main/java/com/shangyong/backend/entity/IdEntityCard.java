package com.shangyong.backend.entity;


/**
 * 身份证前6位归属表bean
 * @author kenzhao
 * @date Sun Feb 11 16:33:10 CST 2018
 **/
public class IdEntityCard {

	/****/
	private String idCardSix;

	/**身份证归属地**/
	private String belongingTo;

	/**备注**/
	private String remark;

	/****/
	private String createTime;

	/****/
	private String modifyTime;

	/****/
	private String createMan;

	/****/
	private String modifyMan;


	public IdEntityCard() {
		super();
	}
	public IdEntityCard(String idCardSix,String belongingTo,String remark,String createTime,String modifyTime,String createMan,String modifyMan) {
		super();
		this.idCardSix = idCardSix;
		this.belongingTo = belongingTo;
		this.remark = remark;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
	}
	public void setIdCardSix(String idCardSix){
		this.idCardSix = idCardSix;
	}

	public String getIdCardSix(){
		return this.idCardSix;
	}

	public void setBelongingTo(String belongingTo){
		this.belongingTo = belongingTo;
	}

	public String getBelongingTo(){
		return this.belongingTo;
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

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}

	public void setCreateMan(String createMan){
		this.createMan = createMan;
	}

	public String getCreateMan(){
		return this.createMan;
	}

	public void setModifyMan(String modifyMan){
		this.modifyMan = modifyMan;
	}

	public String getModifyMan(){
		return this.modifyMan;
	}

}
