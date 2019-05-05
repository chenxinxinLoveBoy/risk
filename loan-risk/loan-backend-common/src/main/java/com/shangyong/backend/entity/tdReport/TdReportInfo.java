package com.shangyong.backend.entity.tdReport;


/**
 * 同盾报告信息bean
 * @author kenzhao
 * @date Thu Mar 15 10:17:18 CST 2018
 **/
public class TdReportInfo {

	/**报告的唯一标识**/
	private String reportId;

	/**申请单编号**/
	private String applicationId;

	/**报告版本**/
	private String reportVersion;

	/**报告更新时间**/
	private String reportUpdateTime;

	/**数据获取任务id**/
	private String taskId;

	/**数据获取任务时间**/
	private String taskTime;

	/**真实姓名**/
	private String realName;

	/**身份证号码**/
	private String identityCode;

	/**年龄**/
	private String age;

	/**性别**/
	private String gender;

	/**星座**/
	private String constellation;

	/**籍贯**/
	private String hometown;

	/**邮箱**/
	private String email;

	/**家庭地址**/
	private String homeAddr;

	/**家庭电话**/
	private String homeTel;

	/**工作地址**/
	private String workAddr;

	/**工作电话**/
	private String workTel;

	/**工作单位**/
	private String companyName;

	/**近1月通话数据是否完整**/
	private String isCallDataComplete1month;

	/**近3月通话数据是否完整**/
	private String isCallDataComplete3month;

	/**近6月通话数据是否完整**/
	private String isCallDataComplete6month;

	/**近1月短信数据是否完整**/
	private String isMsgDataComplete1month;

	/**近3月短信数据是否完整**/
	private String isMsgDataComplete3month;

	/**近6月短信数据是否完整**/
	private String isMsgDataComplete6month;

	/**近1月消费数据是否完整**/
	private String isConsumeDataComplete1month;

	/**近3月消费数据是否完整**/
	private String isConsumeDataComplete3month;

	/**近6月消费数据是否完整**/
	private String isConsumeDataComplete6month;

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


	public TdReportInfo() {
		super();
	}
	public TdReportInfo(String reportId,String applicationId,String reportVersion,String reportUpdateTime,String taskId,String taskTime,String realName,String identityCode,String age,String gender,String constellation,String hometown,String email,String homeAddr,String homeTel,String workAddr,String workTel,String companyName,String isCallDataComplete1month,String isCallDataComplete3month,String isCallDataComplete6month,String isMsgDataComplete1month,String isMsgDataComplete3month,String isMsgDataComplete6month,String isConsumeDataComplete1month,String isConsumeDataComplete3month,String isConsumeDataComplete6month,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.reportId = reportId;
		this.applicationId = applicationId;
		this.reportVersion = reportVersion;
		this.reportUpdateTime = reportUpdateTime;
		this.taskId = taskId;
		this.taskTime = taskTime;
		this.realName = realName;
		this.identityCode = identityCode;
		this.age = age;
		this.gender = gender;
		this.constellation = constellation;
		this.hometown = hometown;
		this.email = email;
		this.homeAddr = homeAddr;
		this.homeTel = homeTel;
		this.workAddr = workAddr;
		this.workTel = workTel;
		this.companyName = companyName;
		this.isCallDataComplete1month = isCallDataComplete1month;
		this.isCallDataComplete3month = isCallDataComplete3month;
		this.isCallDataComplete6month = isCallDataComplete6month;
		this.isMsgDataComplete1month = isMsgDataComplete1month;
		this.isMsgDataComplete3month = isMsgDataComplete3month;
		this.isMsgDataComplete6month = isMsgDataComplete6month;
		this.isConsumeDataComplete1month = isConsumeDataComplete1month;
		this.isConsumeDataComplete3month = isConsumeDataComplete3month;
		this.isConsumeDataComplete6month = isConsumeDataComplete6month;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setReportId(String reportId){
		this.reportId = reportId;
	}

	public String getReportId(){
		return this.reportId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setReportVersion(String reportVersion){
		this.reportVersion = reportVersion;
	}

	public String getReportVersion(){
		return this.reportVersion;
	}

	public void setReportUpdateTime(String reportUpdateTime){
		this.reportUpdateTime = reportUpdateTime;
	}

	public String getReportUpdateTime(){
		return this.reportUpdateTime;
	}

	public void setTaskId(String taskId){
		this.taskId = taskId;
	}

	public String getTaskId(){
		return this.taskId;
	}

	public void setTaskTime(String taskTime){
		this.taskTime = taskTime;
	}

	public String getTaskTime(){
		return this.taskTime;
	}

	public void setRealName(String realName){
		this.realName = realName;
	}

	public String getRealName(){
		return this.realName;
	}

	public void setIdentityCode(String identityCode){
		this.identityCode = identityCode;
	}

	public String getIdentityCode(){
		return this.identityCode;
	}

	public void setAge(String age){
		this.age = age;
	}

	public String getAge(){
		return this.age;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return this.gender;
	}

	public void setConstellation(String constellation){
		this.constellation = constellation;
	}

	public String getConstellation(){
		return this.constellation;
	}

	public void setHometown(String hometown){
		this.hometown = hometown;
	}

	public String getHometown(){
		return this.hometown;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return this.email;
	}

	public void setHomeAddr(String homeAddr){
		this.homeAddr = homeAddr;
	}

	public String getHomeAddr(){
		return this.homeAddr;
	}

	public void setHomeTel(String homeTel){
		this.homeTel = homeTel;
	}

	public String getHomeTel(){
		return this.homeTel;
	}

	public void setWorkAddr(String workAddr){
		this.workAddr = workAddr;
	}

	public String getWorkAddr(){
		return this.workAddr;
	}

	public void setWorkTel(String workTel){
		this.workTel = workTel;
	}

	public String getWorkTel(){
		return this.workTel;
	}

	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}

	public String getCompanyName(){
		return this.companyName;
	}

	public void setIsCallDataComplete1month(String isCallDataComplete1month){
		this.isCallDataComplete1month = isCallDataComplete1month;
	}

	public String getIsCallDataComplete1month(){
		return this.isCallDataComplete1month;
	}

	public void setIsCallDataComplete3month(String isCallDataComplete3month){
		this.isCallDataComplete3month = isCallDataComplete3month;
	}

	public String getIsCallDataComplete3month(){
		return this.isCallDataComplete3month;
	}

	public void setIsCallDataComplete6month(String isCallDataComplete6month){
		this.isCallDataComplete6month = isCallDataComplete6month;
	}

	public String getIsCallDataComplete6month(){
		return this.isCallDataComplete6month;
	}

	public void setIsMsgDataComplete1month(String isMsgDataComplete1month){
		this.isMsgDataComplete1month = isMsgDataComplete1month;
	}

	public String getIsMsgDataComplete1month(){
		return this.isMsgDataComplete1month;
	}

	public void setIsMsgDataComplete3month(String isMsgDataComplete3month){
		this.isMsgDataComplete3month = isMsgDataComplete3month;
	}

	public String getIsMsgDataComplete3month(){
		return this.isMsgDataComplete3month;
	}

	public void setIsMsgDataComplete6month(String isMsgDataComplete6month){
		this.isMsgDataComplete6month = isMsgDataComplete6month;
	}

	public String getIsMsgDataComplete6month(){
		return this.isMsgDataComplete6month;
	}

	public void setIsConsumeDataComplete1month(String isConsumeDataComplete1month){
		this.isConsumeDataComplete1month = isConsumeDataComplete1month;
	}

	public String getIsConsumeDataComplete1month(){
		return this.isConsumeDataComplete1month;
	}

	public void setIsConsumeDataComplete3month(String isConsumeDataComplete3month){
		this.isConsumeDataComplete3month = isConsumeDataComplete3month;
	}

	public String getIsConsumeDataComplete3month(){
		return this.isConsumeDataComplete3month;
	}

	public void setIsConsumeDataComplete6month(String isConsumeDataComplete6month){
		this.isConsumeDataComplete6month = isConsumeDataComplete6month;
	}

	public String getIsConsumeDataComplete6month(){
		return this.isConsumeDataComplete6month;
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
