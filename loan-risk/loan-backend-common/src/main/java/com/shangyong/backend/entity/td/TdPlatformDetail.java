package com.shangyong.backend.entity.td;


/**
 * 多平台细则表bean
 * @author chengfeng.lu
 * @date Fri Dec 15 21:29:05 CST 2017
 **/
public class TdPlatformDetail {

	/****/
	private String tdPlatformDetailId;

	/**不分维度关联多平台id**/
	private String tdPlatformId;

	/**各维度关联多平台id**/
	private String tdPlatformDimensionId;

	/**个数**/
	private String count;

	/**行业名称**/
	private String industryDisplayName;


	public TdPlatformDetail() {
		super();
	}
	public TdPlatformDetail(String tdPlatformDetailId,String tdPlatformId,String tdPlatformDimensionId,String count,String industryDisplayName) {
		super();
		this.tdPlatformDetailId = tdPlatformDetailId;
		this.tdPlatformId = tdPlatformId;
		this.tdPlatformDimensionId = tdPlatformDimensionId;
		this.count = count;
		this.industryDisplayName = industryDisplayName;
	}
	public void setTdPlatformDetailId(String tdPlatformDetailId){
		this.tdPlatformDetailId = tdPlatformDetailId;
	}

	public String getTdPlatformDetailId(){
		return this.tdPlatformDetailId;
	}

	public void setTdPlatformId(String tdPlatformId){
		this.tdPlatformId = tdPlatformId;
	}

	public String getTdPlatformId(){
		return this.tdPlatformId;
	}

	public void setTdPlatformDimensionId(String tdPlatformDimensionId){
		this.tdPlatformDimensionId = tdPlatformDimensionId;
	}

	public String getTdPlatformDimensionId(){
		return this.tdPlatformDimensionId;
	}

	public void setCount(String count){
		this.count = count;
	}

	public String getCount(){
		return this.count;
	}

	public void setIndustryDisplayName(String industryDisplayName){
		this.industryDisplayName = industryDisplayName;
	}

	public String getIndustryDisplayName(){
		return this.industryDisplayName;
	}

}
