package com.shangyong.backend.entity;

import java.io.Serializable;

/**
 * 逾期名单详情bean
 * @author xiajiyun
 * @date Wed Aug 16 23:56:19 CST 2017
 **/
public class OverdueListInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3388011497173700548L;

	/**逾期名单详情序号**/
	private String overdueListInfoId;

	/**逾期金额汇总序号**/
	private String overdueCntId;

	/**申请单编号**/
	private String applicationId;

	/**平台类型**/
	private String pType;

	/**开始逾期时间**/
	private String overdueStartTime;

	/**逾期天数**/
	private Integer overdueDay;

	/**逾期金额（如果没有金额，则值为金额不明）**/
	private String overdueAmt;

	/**查询平台 1.银行 2.网贷**/
	private String platformType;

	/**创建时间**/
	private String createTime;

	/**修改时间**/
	private String modifyTime;


	public OverdueListInfo() {
		super();
	}
	public OverdueListInfo(String overdueListInfoId,String overdueCntId,String applicationId,String pType,String overdueStartTime,Integer overdueDay,String overdueAmt,String platformType,String createTime,String modifyTime) {
		super();
		this.overdueListInfoId = overdueListInfoId;
		this.overdueCntId = overdueCntId;
		this.applicationId = applicationId;
		this.pType = pType;
		this.overdueStartTime = overdueStartTime;
		this.overdueDay = overdueDay;
		this.overdueAmt = overdueAmt;
		this.platformType = platformType;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}
	public void setOverdueListInfoId(String overdueListInfoId){
		this.overdueListInfoId = overdueListInfoId;
	}

	public String getOverdueListInfoId(){
		return this.overdueListInfoId;
	}

	public void setOverdueCntId(String overdueCntId){
		this.overdueCntId = overdueCntId;
	}

	public String getOverdueCntId(){
		return this.overdueCntId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setPType(String pType){
		this.pType = pType;
	}

	public String getPType(){
		return this.pType;
	}

	public void setOverdueStartTime(String overdueStartTime){
		this.overdueStartTime = overdueStartTime;
	}

	public String getOverdueStartTime(){
		return this.overdueStartTime;
	}

	public void setOverdueDay(Integer overdueDay){
		this.overdueDay = overdueDay;
	}

	public Integer getOverdueDay(){
		return this.overdueDay;
	}

	public void setOverdueAmt(String overdueAmt){
		this.overdueAmt = overdueAmt;
	}

	public String getOverdueAmt(){
		return this.overdueAmt;
	}

	public void setPlatformType(String platformType){
		this.platformType = platformType;
	}

	public String getPlatformType(){
		return this.platformType;
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

}
