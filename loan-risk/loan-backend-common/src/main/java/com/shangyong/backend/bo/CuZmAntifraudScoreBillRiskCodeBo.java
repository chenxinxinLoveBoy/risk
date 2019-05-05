package com.shangyong.backend.bo;

import com.shangyong.backend.common.baseEntityBo.BaseBo;


/**
 * 芝麻信用关注清单风险代码表BO
 * @author xiajiyun
 * @date Tue Jun 27 16:06:24 CST 2017
 **/
public class CuZmAntifraudScoreBillRiskCodeBo extends BaseBo{

	/**自增主键id**/
	private Integer riskCodeId;

	/**信息项**/
	private String info;

	/**风险代码**/
	private String code;

	/**风险类型**/
	private String suggestType;

	/**中文说明**/
	private String remark;

	/**创建人**/
	private String createMan;

	/**创建日期**/
	private String createTime;

	/**修改人**/
	private String modifyMan;

	/**修改日期**/
	private String modifyTime;

	/**状态：默认1有效,0 失效**/
	private Integer state;
	
	/**
	 * 类型，1：欺诈关注清单， 2：欺诈信息验证
	 */
	private Integer type;


	public CuZmAntifraudScoreBillRiskCodeBo() {
		super();
	}
	public CuZmAntifraudScoreBillRiskCodeBo(Integer riskCodeId,String info,String code,String suggestType,String remark,String createMan,String createTime,String modifyMan,String modifyTime,Integer state) {
		super();
		this.riskCodeId = riskCodeId;
		this.info = info;
		this.code = code;
		this.suggestType = suggestType;
		this.remark = remark;
		this.createMan = createMan;
		this.createTime = createTime;
		this.modifyMan = modifyMan;
		this.modifyTime = modifyTime;
		this.state = state;
	}
	public void setRiskCodeId(Integer riskCodeId){
		this.riskCodeId = riskCodeId;
	}

	public Integer getRiskCodeId(){
		return this.riskCodeId;
	}

	public void setInfo(String info){
		this.info = info;
	}

	public String getInfo(){
		return this.info;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return this.code;
	}

	public void setSuggestType(String suggestType){
		this.suggestType = suggestType;
	}

	public String getSuggestType(){
		return this.suggestType;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setCreateMan(String createMan){
		this.createMan = createMan;
	}

	public String getCreateMan(){
		return this.createMan;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setModifyMan(String modifyMan){
		this.modifyMan = modifyMan;
	}

	public String getModifyMan(){
		return this.modifyMan;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}

	public void setState(Integer state){
		this.state = state;
	}

	public Integer getState(){
		return this.state;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
