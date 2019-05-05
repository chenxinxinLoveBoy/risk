package com.shangyong.backend.bo;

import com.shangyong.backend.common.baseEntityBo.BaseBo;


/**
 * 芝麻信用关注清单风险代码表BO
 * @author xiajiyun
 * @date Tue Jun 27 16:06:24 CST 2017
 **/
public class CuZmAntifraudScoreBillCodeBo extends BaseBo{

	/**唯一编码**/
	private String udc_code;

	/**中文说明**/
	private String udc_name;

	/**备注**/
	private String remarks;
	
	/**
	 * 用于判断新增还是修改
	 */
	private Integer find;
	
	
	/**
	 * 用于保存修改之前的值
	 * @return
	 */
	private String u_code;
	
	public String getUdc_code() {
		return udc_code;
	}

	public void setUdc_code(String udc_code) {
		this.udc_code = udc_code;
	}

	public String getUdc_name() {
		return udc_name;
	}

	public void setUdc_name(String udc_name) {
		this.udc_name = udc_name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getFind() {
		return find;
	}

	public void setFind(Integer find) {
		this.find = find;
	}

	public String getU_code() {
		return u_code;
	}

	public void setU_code(String u_code) {
		this.u_code = u_code;
	}
	
	

}
