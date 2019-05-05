package com.shangyong.backend.entity.bqs;

import java.io.Serializable;

/**
 * bean
 * @author chengfeng.lu
 * @date Sun Dec 10 15:31:49 CST 2017
 **/
public class BqsBillDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/****/
	private String bqsBillDetailsId;

	/**账单表id**/
	private String bqsBillsInfoId;

	/**账单费用明细**/
	private String integrateItem;

	/**账单费用金额**/
	private String fee;

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


	public BqsBillDetails() {
		super();
	}
	public BqsBillDetails(String bqsBillDetailsId,String bqsBillsInfoId,String integrateItem,String fee,String remark,String createTime,String modifyTime,String createMan,String modifyMan) {
		super();
		this.bqsBillDetailsId = bqsBillDetailsId;
		this.bqsBillsInfoId = bqsBillsInfoId;
		this.integrateItem = integrateItem;
		this.fee = fee;
		this.remark = remark;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
	}
	public void setBqsBillDetailsId(String bqsBillDetailsId){
		this.bqsBillDetailsId = bqsBillDetailsId;
	}

	public String getBqsBillDetailsId(){
		return this.bqsBillDetailsId;
	}

	public void setBqsBillsInfoId(String bqsBillsInfoId){
		this.bqsBillsInfoId = bqsBillsInfoId;
	}

	public String getBqsBillsInfoId(){
		return this.bqsBillsInfoId;
	}

	public void setIntegrateItem(String integrateItem){
		this.integrateItem = integrateItem;
	}

	public String getIntegrateItem(){
		return this.integrateItem;
	}

	public void setFee(String fee){
		this.fee = fee;
	}

	public String getFee(){
		return this.fee;
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
