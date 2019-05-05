package com.shangyong.backend.entity.tdReport;


/**
 * 同盾报告各时间段通话统计（每2小时）表bean
 * @author kenzhao
 * @date Thu Mar 15 10:17:18 CST 2018
 **/
public class TdReportCall2hour {

	/**唯一标识**/
	private String call2hourId;

	/**申请单编号**/
	private String applicationId;

	/**列表(1.近3月工作日各时间段通话时长2.近3月节假日各时间段通话时长3.近6月工作日各时间段通话时长4.近6月节假日各时间段通话时长)**/
	private String call2hourType;

	/**时间段：0-2**/
	private String t0;

	/**时间段：2-4**/
	private String t1;

	/**时间段：4-6**/
	private String t2;

	/**时间段：6-8**/
	private String t3;

	/**时间段：8-10**/
	private String t4;

	/**时间段：10-12**/
	private String t5;

	/**时间段：12-14**/
	private String t6;

	/**时间段：14-16**/
	private String t7;

	/**时间段：16-18**/
	private String t8;

	/**时间段：18-20**/
	private String t9;

	/**时间段：20-22**/
	private String t10;

	/**时间段：22-24**/
	private String t11;

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


	public TdReportCall2hour() {
		super();
	}
	public TdReportCall2hour(String call2hourId,String applicationId,String call2hourType,String t0,String t1,String t2,String t3,String t4,String t5,String t6,String t7,String t8,String t9,String t10,String t11,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.call2hourId = call2hourId;
		this.applicationId = applicationId;
		this.call2hourType = call2hourType;
		this.t0 = t0;
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		this.t4 = t4;
		this.t5 = t5;
		this.t6 = t6;
		this.t7 = t7;
		this.t8 = t8;
		this.t9 = t9;
		this.t10 = t10;
		this.t11 = t11;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setCall2hourId(String call2hourId){
		this.call2hourId = call2hourId;
	}

	public String getCall2hourId(){
		return this.call2hourId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setCall2hourType(String call2hourType){
		this.call2hourType = call2hourType;
	}

	public String getCall2hourType(){
		return this.call2hourType;
	}

	public void setT0(String t0){
		this.t0 = t0;
	}

	public String getT0(){
		return this.t0;
	}

	public void setT1(String t1){
		this.t1 = t1;
	}

	public String getT1(){
		return this.t1;
	}

	public void setT2(String t2){
		this.t2 = t2;
	}

	public String getT2(){
		return this.t2;
	}

	public void setT3(String t3){
		this.t3 = t3;
	}

	public String getT3(){
		return this.t3;
	}

	public void setT4(String t4){
		this.t4 = t4;
	}

	public String getT4(){
		return this.t4;
	}

	public void setT5(String t5){
		this.t5 = t5;
	}

	public String getT5(){
		return this.t5;
	}

	public void setT6(String t6){
		this.t6 = t6;
	}

	public String getT6(){
		return this.t6;
	}

	public void setT7(String t7){
		this.t7 = t7;
	}

	public String getT7(){
		return this.t7;
	}

	public void setT8(String t8){
		this.t8 = t8;
	}

	public String getT8(){
		return this.t8;
	}

	public void setT9(String t9){
		this.t9 = t9;
	}

	public String getT9(){
		return this.t9;
	}

	public void setT10(String t10){
		this.t10 = t10;
	}

	public String getT10(){
		return this.t10;
	}

	public void setT11(String t11){
		this.t11 = t11;
	}

	public String getT11(){
		return this.t11;
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
