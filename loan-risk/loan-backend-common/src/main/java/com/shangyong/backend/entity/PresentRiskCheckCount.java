package com.shangyong.backend.entity;

import java.io.Serializable;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 风险检查项表bean
 * @author xiangxianjin
 **/
public class PresentRiskCheckCount extends BaseBo implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1223L;

	/**检查项编号**/
	private String itemId;

	/**检查项名称**/
	private String itemName;

	/**风险等级**/
	private String riskLevel;

	/**检查项分组**/
	private String group;

	/**出现次数**/
	private String count;

	/** 开始时间  */
	private String startTime;

	/** 结束时间  */
	private String endTime;

	public PresentRiskCheckCount() {
		super();
	}

	public PresentRiskCheckCount(String itemId, String itemName, String riskLevel, String group, String count,
                                 String startTime, String endTime) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.riskLevel = riskLevel;
		this.group = group;
		this.count = count;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "TdRiskCheckCount [itemId=" + itemId + ", itemName=" + itemName + ", riskLevel=" + riskLevel + ", group="
				+ group + ", count=" + count + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
	
}
