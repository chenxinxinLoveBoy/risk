package com.shangyong.backend.entity.td;


/**
 * bean
 * @author chengfeng.lu
 * @date Fri Dec 15 21:29:05 CST 2017
 **/
public class TdPlatformDimension {

	/****/
	private String tdPlatformDimensionId;

	/****/
	private String tdPlatfromId;

	/**维度名称**/
	private String dimension;

	/**个数**/
	private String count;


	public TdPlatformDimension() {
		super();
	}
	public TdPlatformDimension(String tdPlatformDimensionId,String tdPlatfromId,String dimension,String count) {
		super();
		this.tdPlatformDimensionId = tdPlatformDimensionId;
		this.tdPlatfromId = tdPlatfromId;
		this.dimension = dimension;
		this.count = count;
	}
	public void setTdPlatformDimensionId(String tdPlatformDimensionId){
		this.tdPlatformDimensionId = tdPlatformDimensionId;
	}

	public String getTdPlatformDimensionId(){
		return this.tdPlatformDimensionId;
	}

	public void setTdPlatfromId(String tdPlatfromId){
		this.tdPlatfromId = tdPlatfromId;
	}

	public String getTdPlatfromId(){
		return this.tdPlatfromId;
	}

	public void setDimension(String dimension){
		this.dimension = dimension;
	}

	public String getDimension(){
		return this.dimension;
	}

	public void setCount(String count){
		this.count = count;
	}

	public String getCount(){
		return this.count;
	}

}
