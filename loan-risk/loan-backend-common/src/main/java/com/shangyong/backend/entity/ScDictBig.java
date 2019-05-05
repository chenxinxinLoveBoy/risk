package com.shangyong.backend.entity;


import com.shangyong.backend.common.baseEntityBo.BaseBo;


/**
 * 数据字典大类表bean
 * @author xk
 * @date Fri Jun 02 22:44:25 CST 2017
 **/
public class ScDictBig extends BaseBo{

	/**数据字典大类序号**/
	private Integer id;

	/**数据字典大类编号**/
	private String dicBigCode;

	/**数据字典大类中文名称**/
	private String dicBigValue;

	/**数据字典大类使用状态（1-正常、2-停用）**/
	private String dicBigStaues;

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


	public ScDictBig() {
		super();
	}
	public ScDictBig(Integer id,String dicBigCode,String dicBigValue,String dicBigStaues,String createMan,String modifyMan,String createTime,String modifyTime,Integer version) {
		super();
		this.id = id;
		this.dicBigCode = dicBigCode;
		this.dicBigValue = dicBigValue;
		this.dicBigStaues = dicBigStaues;
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

	public void setDicBigCode(String dicBigCode){
		this.dicBigCode = dicBigCode;
	}

	public String getDicBigCode(){
		return this.dicBigCode;
	}

	public void setDicBigValue(String dicBigValue){
		this.dicBigValue = dicBigValue;
	}

	public String getDicBigValue(){
		return this.dicBigValue;
	}

	public void setDicBigStaues(String dicBigStaues){
		this.dicBigStaues = dicBigStaues;
	}

	public String getDicBigStaues(){
		return this.dicBigStaues;
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
