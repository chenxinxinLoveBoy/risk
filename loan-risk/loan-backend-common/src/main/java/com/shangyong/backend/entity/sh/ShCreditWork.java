package com.shangyong.backend.entity.sh;


/**
 * 上海资信用户工作单位表bean
 * @author kenzhao
 * @date Fri Mar 09 15:18:41 CST 2018
 **/
public class ShCreditWork {

	/**主键自增id**/
	private String shCreditWorkId;

	/**申请单编号**/
	private String applicationId;

	/**工作序号**/
	private String workNumber;

	/**工作明细**/
	private String workDetail;

	/**职业**/
	private String workProfession;

	/**工作信息获取时间**/
	private String workTime;

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


	public ShCreditWork() {
		super();
	}
	public ShCreditWork(String shCreditWorkId,String applicationId,String workNumber,String workDetail,String workProfession,String workTime,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.shCreditWorkId = shCreditWorkId;
		this.applicationId = applicationId;
		this.workNumber = workNumber;
		this.workDetail = workDetail;
		this.workProfession = workProfession;
		this.workTime = workTime;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setShCreditWorkId(String shCreditWorkId){
		this.shCreditWorkId = shCreditWorkId;
	}

	public String getShCreditWorkId(){
		return this.shCreditWorkId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setWorkNumber(String workNumber){
		this.workNumber = workNumber;
	}

	public String getWorkNumber(){
		return this.workNumber;
	}

	public void setWorkDetail(String workDetail){
		this.workDetail = workDetail;
	}

	public String getWorkDetail(){
		return this.workDetail;
	}

	public void setWorkProfession(String workProfession){
		this.workProfession = workProfession;
	}

	public String getWorkProfession(){
		return this.workProfession;
	}

	public void setWorkTime(String workTime){
		this.workTime = workTime;
	}

	public String getWorkTime(){
		return this.workTime;
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
