package com.shangyong.backend.entity.td.vo;

import java.util.List;

import com.shangyong.backend.entity.td.TdPlatformDetail;

/**
 * 多平台规则bean
 * @author chengfeng.lu
 * @date Fri Dec 15 21:29:05 CST 2017
 **/
public class TdPlatformVo {

	/****/
	private String tdPlatformId;

	/****/
	private String tdRiskItemsId;

	/**规则描述**/
	private String description;

	/**关联多平台个数**/
	private String platformCount;
	
	/**不分维度关联多平台**/
	private List<TdPlatformDetail> tdPlatformDetails;
	
	/**各维度关联多平台**/
	private List<TdPlatformDimensionVo> tdPlatformDimensions;

	public String getTdPlatformId() {
		return tdPlatformId;
	}

	public void setTdPlatformId(String tdPlatformId) {
		this.tdPlatformId = tdPlatformId;
	}

	public String getTdRiskItemsId() {
		return tdRiskItemsId;
	}

	public void setTdRiskItemsId(String tdRiskItemsId) {
		this.tdRiskItemsId = tdRiskItemsId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlatformCount() {
		return platformCount;
	}

	public void setPlatformCount(String platformCount) {
		this.platformCount = platformCount;
	}

	public List<TdPlatformDetail> getTdPlatformDetails() {
		return tdPlatformDetails;
	}

	public void setTdPlatformDetails(List<TdPlatformDetail> tdPlatformDetails) {
		this.tdPlatformDetails = tdPlatformDetails;
	}

	public List<TdPlatformDimensionVo> getTdPlatformDimensions() {
		return tdPlatformDimensions;
	}

	public void setTdPlatformDimensions(List<TdPlatformDimensionVo> tdPlatformDimensions) {
		this.tdPlatformDimensions = tdPlatformDimensions;
	}

	@Override
	public String toString() {
		return "TdPlatformVo [tdPlatformId=" + tdPlatformId + ", tdRiskItemsId=" + tdRiskItemsId + ", description="
				+ description + ", platformCount=" + platformCount + ", tdPlatformDetails=" + tdPlatformDetails
				+ ", tdPlatformDimensions=" + tdPlatformDimensions + "]";
	}

	public TdPlatformVo(String tdPlatformId, String tdRiskItemsId, String description, String platformCount,
			List<TdPlatformDetail> tdPlatformDetails, List<TdPlatformDimensionVo> tdPlatformDimensions) {
		super();
		this.tdPlatformId = tdPlatformId;
		this.tdRiskItemsId = tdRiskItemsId;
		this.description = description;
		this.platformCount = platformCount;
		this.tdPlatformDetails = tdPlatformDetails;
		this.tdPlatformDimensions = tdPlatformDimensions;
	}

	public TdPlatformVo() {
		super();
	}


}
