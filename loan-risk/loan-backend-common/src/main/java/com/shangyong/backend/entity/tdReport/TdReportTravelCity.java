package com.shangyong.backend.entity.tdReport;


/**
 * 同盾报告出行记录分析（城市）表bean
 * @author kenzhao
 * @date Thu Mar 15 10:17:18 CST 2018
 **/
public class TdReportTravelCity {

	/**唯一标识**/
	private String travelCityId;

	/**申请单编号**/
	private String applicationId;

	/**出发城市**/
	private String leaveCity;

	/**出发日期**/
	private String leaveDay;

	/**出发日类型**/
	private String leaveDayType;

	/**到达城市**/
	private String arriveCity;

	/**到达日期**/
	private String arriveDay;

	/**到达日类型**/
	private String arriveDayType;

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


	public TdReportTravelCity() {
		super();
	}
	public TdReportTravelCity(String travelCityId,String applicationId,String leaveCity,String leaveDay,String leaveDayType,String arriveCity,String arriveDay,String arriveDayType,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.travelCityId = travelCityId;
		this.applicationId = applicationId;
		this.leaveCity = leaveCity;
		this.leaveDay = leaveDay;
		this.leaveDayType = leaveDayType;
		this.arriveCity = arriveCity;
		this.arriveDay = arriveDay;
		this.arriveDayType = arriveDayType;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setTravelCityId(String travelCityId){
		this.travelCityId = travelCityId;
	}

	public String getTravelCityId(){
		return this.travelCityId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setLeaveCity(String leaveCity){
		this.leaveCity = leaveCity;
	}

	public String getLeaveCity(){
		return this.leaveCity;
	}

	public void setLeaveDay(String leaveDay){
		this.leaveDay = leaveDay;
	}

	public String getLeaveDay(){
		return this.leaveDay;
	}

	public void setLeaveDayType(String leaveDayType){
		this.leaveDayType = leaveDayType;
	}

	public String getLeaveDayType(){
		return this.leaveDayType;
	}

	public void setArriveCity(String arriveCity){
		this.arriveCity = arriveCity;
	}

	public String getArriveCity(){
		return this.arriveCity;
	}

	public void setArriveDay(String arriveDay){
		this.arriveDay = arriveDay;
	}

	public String getArriveDay(){
		return this.arriveDay;
	}

	public void setArriveDayType(String arriveDayType){
		this.arriveDayType = arriveDayType;
	}

	public String getArriveDayType(){
		return this.arriveDayType;
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
