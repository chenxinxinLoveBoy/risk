package com.shangyong.backend.entity;

import java.io.Serializable;

/**
 * 逾期金额汇总bean
 * @author xiajiyun
 * @date Wed Aug 16 23:56:19 CST 2017
 **/
public class OverdueCnt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6893731838921993958L;

	/**逾期金额汇总序号**/
	private String overdueCntId;

	/**逾期名单序号**/
	private String overdueListId;

	/**申请单编号**/
	private String applicationId;

	/**逾期次数总计**/
	private Integer overdueCnt;

	/**逾期金额总计**/
	private String overdueAmtCnt;

	/**查询平台 1.银行 2.网贷**/
	private String platformType;

	/**创建时间**/
	private String createTime;

	/**修改时间**/
	private String modifyTime;


	public OverdueCnt() {
		super();
	}
	public OverdueCnt(String overdueCntId,String overdueListId,String applicationId,Integer overdueCnt,String overdueAmtCnt,String platformType,String createTime,String modifyTime) {
		super();
		this.overdueCntId = overdueCntId;
		this.overdueListId = overdueListId;
		this.applicationId = applicationId;
		this.overdueCnt = overdueCnt;
		this.overdueAmtCnt = overdueAmtCnt;
		this.platformType = platformType;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}
	public void setOverdueCntId(String overdueCntId){
		this.overdueCntId = overdueCntId;
	}

	public String getOverdueCntId(){
		return this.overdueCntId;
	}

	public void setOverdueListId(String overdueListId){
		this.overdueListId = overdueListId;
	}

	public String getOverdueListId(){
		return this.overdueListId;
	}

	public void setApplicationId(String applicationId){
		this.applicationId = applicationId;
	}

	public String getApplicationId(){
		return this.applicationId;
	}

	public void setOverdueCnt(Integer overdueCnt){
		this.overdueCnt = overdueCnt;
	}

	public Integer getOverdueCnt(){
		return this.overdueCnt;
	}

	public void setOverdueAmtCnt(String overdueAmtCnt){
		this.overdueAmtCnt = overdueAmtCnt;
	}

	public String getOverdueAmtCnt(){
		return this.overdueAmtCnt;
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
