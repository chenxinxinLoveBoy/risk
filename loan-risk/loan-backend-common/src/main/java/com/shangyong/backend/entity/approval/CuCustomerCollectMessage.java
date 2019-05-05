package com.shangyong.backend.entity.approval;

import java.io.Serializable;

/**
 * 客户被收集信息汇总表bean
 * @author xiajiyun
 * @date Thu Aug 24 16:31:50 CST 2017
 **/
public class CuCustomerCollectMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6055800453515993431L;

	/**主键**/
	private Long customerCollectMessageId;

	/**APP客户编号**/
	private String customerId;

	/**平台 1闪贷 2速贷**/
	private Integer appName;

	/**扩展字段**/
	private String extend;

	/**创建时间**/
	private String createTime;

	/**修改时间**/
	private String modifyTime;

	public void setCustomerCollectMessageId(Long customerCollectMessageId){
		this.customerCollectMessageId = customerCollectMessageId;
	}

	public Long getCustomerCollectMessageId(){
		return this.customerCollectMessageId;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return this.customerId;
	}

	public void setAppName(Integer appName){
		this.appName = appName;
	}

	public Integer getAppName(){
		return this.appName;
	}

	public void setExtend(String extend){
		this.extend = extend;
	}

	public String getExtend(){
		return this.extend;
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
}