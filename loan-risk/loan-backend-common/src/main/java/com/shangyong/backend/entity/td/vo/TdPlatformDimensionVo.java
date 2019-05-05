package com.shangyong.backend.entity.td.vo;

import java.util.List;

import com.shangyong.backend.entity.td.TdPlatformDetail;

/**
 * bean
 * @author chengfeng.lu
 * @date Fri Dec 15 21:29:05 CST 2017
 **/
public class TdPlatformDimensionVo {

	/****/
	private String tdPlatformDimensionId;

	/****/
	private String tdPlatfromId;

	/**维度名称**/
	private String dimension;

	/**个数**/
	private String count;
	
	/**不分维度关联多平台**/
	private List<TdPlatformDetail> tdPlatformDetails;

	public String getTdPlatformDimensionId() {
		return tdPlatformDimensionId;
	}

	public void setTdPlatformDimensionId(String tdPlatformDimensionId) {
		this.tdPlatformDimensionId = tdPlatformDimensionId;
	}

	public String getTdPlatfromId() {
		return tdPlatfromId;
	}

	public void setTdPlatfromId(String tdPlatfromId) {
		this.tdPlatfromId = tdPlatfromId;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public List<TdPlatformDetail> getTdPlatformDetails() {
		return tdPlatformDetails;
	}

	public void setTdPlatformDetails(List<TdPlatformDetail> tdPlatformDetails) {
		this.tdPlatformDetails = tdPlatformDetails;
	}

	@Override
	public String toString() {
		return "TdPlatformDimensionVo [tdPlatformDimensionId=" + tdPlatformDimensionId + ", tdPlatfromId="
				+ tdPlatfromId + ", dimension=" + dimension + ", count=" + count + ", tdPlatformDetails="
				+ tdPlatformDetails + "]";
	}

	public TdPlatformDimensionVo(String tdPlatformDimensionId, String tdPlatfromId, String dimension, String count,
			List<TdPlatformDetail> tdPlatformDetails) {
		super();
		this.tdPlatformDimensionId = tdPlatformDimensionId;
		this.tdPlatfromId = tdPlatfromId;
		this.dimension = dimension;
		this.count = count;
		this.tdPlatformDetails = tdPlatformDetails;
	}

	public TdPlatformDimensionVo() {
		super();
	}

	
}
