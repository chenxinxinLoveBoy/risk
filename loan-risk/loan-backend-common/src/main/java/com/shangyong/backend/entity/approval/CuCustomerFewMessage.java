package com.shangyong.backend.entity.approval;

import java.io.Serializable;

/**
 * 客户手机短信记录表bean
 * @author xiajiyun
 * @date Thu Aug 24 16:31:50 CST 2017
 **/
public class CuCustomerFewMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3603974376846588362L;

	/**短信id**/
	private String customerFewMessageId;

	/**客户被收集信息汇总表序号**/
	private Long customerCollectMessageId;

	/**APP客户编号**/
	private String customerId;

	/**平台 1闪贷 2速贷**/
	private Integer appName;

	/**发送时间**/
	private String sendTime;

	/**发送人姓名**/
	private String callName;

	/**发送人手机号**/
	private String phone;

	/**发送内容**/
	private String context;

	/** 短信类型 1-接收 2-发送**/
	private Integer type;

	/**扩展字段**/
	private String extend;

	/**创建时间**/
	private String createTime;

	/**修改时间**/
	private String modifyTime;


	public CuCustomerFewMessage() {
		super();
	}
	public CuCustomerFewMessage(String customerFewMessageId,Long customerCollectMessageId,String customerId,Integer appName,String sendTime,String callName,String phone,String context,Integer type,String extend,String createTime,String modifyTime) {
		super();
		this.customerFewMessageId = customerFewMessageId;
		this.customerCollectMessageId = customerCollectMessageId;
		this.customerId = customerId;
		this.appName = appName;
		this.sendTime = sendTime;
		this.callName = callName;
		this.phone = phone;
		this.context = context;
		this.type = type;
		this.extend = extend;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}
	public void setCustomerFewMessageId(String customerFewMessageId){
		this.customerFewMessageId = customerFewMessageId;
	}

	public String getCustomerFewMessageId(){
		return this.customerFewMessageId;
	}

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

	public void setSendTime(String sendTime){
		this.sendTime = sendTime;
	}

	public String getSendTime(){
		return this.sendTime;
	}

	public void setCallName(String callName){
		this.callName = callName;
	}

	public String getCallName(){
		return this.callName;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setContext(String context){
		this.context = context;
	}

	public String getContext(){
		return this.context;
	}

	public void setType(Integer type){
		this.type = type;
	}

	public Integer getType(){
		return this.type;
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
