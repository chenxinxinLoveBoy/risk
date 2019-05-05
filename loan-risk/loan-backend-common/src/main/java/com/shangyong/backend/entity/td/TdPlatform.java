package com.shangyong.backend.entity.td;


/**
 * 多平台规则bean
 * @author chengfeng.lu
 * @date Fri Dec 15 21:29:05 CST 2017
 **/
public class TdPlatform {

	/****/
	private String tdPlatformId;

	/****/
	private String tdRiskItemsId;

	/**规则描述**/
	private String description;

	/**关联多平台个数**/
	private String platformCount;


	public TdPlatform() {
		super();
	}
	public TdPlatform(String tdPlatformId,String tdRiskItemsId,String description,String platformCount) {
		super();
		this.tdPlatformId = tdPlatformId;
		this.tdRiskItemsId = tdRiskItemsId;
		this.description = description;
		this.platformCount = platformCount;
	}
	public void setTdPlatformId(String tdPlatformId){
		this.tdPlatformId = tdPlatformId;
	}

	public String getTdPlatformId(){
		return this.tdPlatformId;
	}

	public void setTdRiskItemsId(String tdRiskItemsId){
		this.tdRiskItemsId = tdRiskItemsId;
	}

	public String getTdRiskItemsId(){
		return this.tdRiskItemsId;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return this.description;
	}

	public void setPlatformCount(String platformCount){
		this.platformCount = platformCount;
	}

	public String getPlatformCount(){
		return this.platformCount;
	}

}
