package com.shangyong.backend.entity.sh;


/**
 * 上海资信为他人担保信息表bean
 * @author kenzhao
 * @date Fri Mar 09 15:18:41 CST 2018
 **/
public class ShCreditGuarantee {

	/**主键自增id**/
	private String guaranteeId;

	/**申请单编号**/
	private String applicationId;

	/**担保项目**/
	private String guaranteeProject;

	/**担保日期**/
	private String guaranteeDate;

	/**担保金额**/
	private String guaranteeMoney;

	/**担保关系**/
	private String guaranteeRelation;

	/**担保信息获取日期**/
	private String guaranteeTime;

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


	public ShCreditGuarantee() {
		super();
	}
	public ShCreditGuarantee(String guaranteeId,String applicationId,String guaranteeProject,String guaranteeDate,String guaranteeMoney,String guaranteeRelation,String guaranteeTime,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.guaranteeId = guaranteeId;
		this.applicationId = applicationId;
		this.guaranteeProject = guaranteeProject;
		this.guaranteeDate = guaranteeDate;
		this.guaranteeMoney = guaranteeMoney;
		this.guaranteeRelation = guaranteeRelation;
		this.guaranteeTime = guaranteeTime;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setGuaranteeId(String guaranteeId){
		this.guaranteeId = guaranteeId;
	}

	public String getGuaranteeId(){
		return this.guaranteeId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setGuaranteeProject(String guaranteeProject){
		this.guaranteeProject = guaranteeProject;
	}

	public String getGuaranteeProject(){
		return this.guaranteeProject;
	}

	public void setGuaranteeDate(String guaranteeDate){
		this.guaranteeDate = guaranteeDate;
	}

	public String getGuaranteeDate(){
		return this.guaranteeDate;
	}

	public void setGuaranteeMoney(String guaranteeMoney){
		this.guaranteeMoney = guaranteeMoney;
	}

	public String getGuaranteeMoney(){
		return this.guaranteeMoney;
	}

	public void setGuaranteeRelation(String guaranteeRelation){
		this.guaranteeRelation = guaranteeRelation;
	}

	public String getGuaranteeRelation(){
		return this.guaranteeRelation;
	}

	public void setGuaranteeTime(String guaranteeTime){
		this.guaranteeTime = guaranteeTime;
	}

	public String getGuaranteeTime(){
		return this.guaranteeTime;
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
