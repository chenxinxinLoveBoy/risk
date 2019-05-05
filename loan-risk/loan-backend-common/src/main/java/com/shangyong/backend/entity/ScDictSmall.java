package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;


/**
 * 数据字典小类表bean
 * @author xk
 * @date Fri Jun 02 22:44:25 CST 2017
 **/
public class ScDictSmall extends BaseBo {

	/**数据字典小类序号**/
	private Integer id;

	/**数据字典大类序号**/
	private Integer dictBigId;

	/**数据字典小类编号**/
	private String dicSmallCode;

	/**数据字典小类中文名称**/
	private String dicSmallValue;

	/**数据字典小类使用状态 （1-正常、2-停用）**/
	private String dicSmallStaues;

	/**添加人用户编号**/
	private String createMan;

	/**修改人用户编号**/
	private String modifyMan;

	/**创建时间**/
	private String createTime;

	/**最后更新时间**/
	private String modifyTime;

	/**版本号**/
	private Integer version;


	public ScDictSmall() {
		super();
	}
	public ScDictSmall(Integer id,Integer dictBigId,String dicSmallCode,String dicSmallValue,String dicSmallStaues,String createMan,String modifyMan,String createTime,String modifyTime,Integer version) {
		super();
		this.id = id;
		this.dictBigId = dictBigId;
		this.dicSmallCode = dicSmallCode;
		this.dicSmallValue = dicSmallValue;
		this.dicSmallStaues = dicSmallStaues;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.version = version;
	}
	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setDictBigId(Integer dictBigId){
		this.dictBigId = dictBigId;
	}

	public Integer getDictBigId(){
		return this.dictBigId;
	}

	public void setDicSmallCode(String dicSmallCode){
		this.dicSmallCode = dicSmallCode;
	}

	public String getDicSmallCode(){
		return this.dicSmallCode;
	}

	public void setDicSmallValue(String dicSmallValue){
		this.dicSmallValue = dicSmallValue;
	}

	public String getDicSmallValue(){
		return this.dicSmallValue;
	}

	public void setDicSmallStaues(String dicSmallStaues){
		this.dicSmallStaues = dicSmallStaues;
	}

	public String getDicSmallStaues(){
		return this.dicSmallStaues;
	}

	public void setCreateMan(String createMan){
		this.createMan = createMan;
	}

	public String getCreateMan(){
		return this.createMan;
	}

	public void setModifyMan(String modifyMan){
		this.modifyMan = modifyMan;
	}

	public String getModifyMan(){
		return this.modifyMan;
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

	public void setVersion(Integer version){
		this.version = version;
	}

	public Integer getVersion(){
		return this.version;
	}

}
