package com.shangyong.backend.bo;

import com.shangyong.backend.common.baseEntityBo.BaseBo;


/**
 * 芝麻信用风险代码明细表Bo
 * @author xiajiyun
 * @date Wed Jun 28 17:38:22 CST 2017
 **/
public class CuZmCodeDetailBo extends BaseBo{

	/**主键自增id**/
	private Integer detailId;

	/**芝麻信用的流水号,芝麻在一段时间内（一般为1天）返回首次查询结果, 一天内重复查询不重新计费**/
	private String transactionId;

	/**风险代码**/
	private String zmCode;

	/**创建日期**/
	private String createTime;

	/**创建人**/
	private String createMan;

	/**修改日期**/
	private String modifyTime;

	/**修改人**/
	private String modifyMan;

	/**状态，默认1有效，0无效**/
	private Integer state;


	public CuZmCodeDetailBo() {
		super();
	}
	public CuZmCodeDetailBo(Integer detailId,String transactionId,String zmCode,String createTime,String createMan,String modifyTime,String modifyMan,Integer state) {
		super();
		this.detailId = detailId;
		this.transactionId = transactionId;
		this.zmCode = zmCode;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.state = state;
	}
	public void setDetailId(Integer detailId){
		this.detailId = detailId;
	}

	public Integer getDetailId(){
		return this.detailId;
	}

	public void setTransactionId(String transactionId){
		this.transactionId = transactionId;
	}

	public String getTransactionId(){
		return this.transactionId;
	}

	public void setZmCode(String zmCode){
		this.zmCode = zmCode;
	}

	public String getZmCode(){
		return this.zmCode;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setCreateMan(String createMan){
		this.createMan = createMan;
	}

	public String getCreateMan(){
		return this.createMan;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}

	public void setModifyMan(String modifyMan){
		this.modifyMan = modifyMan;
	}

	public String getModifyMan(){
		return this.modifyMan;
	}

	public void setState(Integer state){
		this.state = state;
	}

	public Integer getState(){
		return this.state;
	}

}
