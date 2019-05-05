package com.shangyong.backend.entity.approval;

import java.io.Serializable;

/**
 * 客户手机通话记录表bean
 * @author xiajiyun
 * @date Thu Aug 24 16:31:50 CST 2017
 **/
public class CuCustomerCallRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2036268167255779206L;

	/**通话记录id**/
	private String customerCallRecordId;

	/**客户被收集信息汇总表序号**/
	private Long customerCollectMessageId;

	/**APP客户编号**/
	private String customerId;

	/**平台 1闪贷 2速贷**/
	private Integer appName;

	/**通话时间**/
	private String callTime;

	/**通话号码**/
	private String phone;

	/**通话人姓名**/
	private String callName;

	/**通话时长**/
	private String useTime;

	/**通话类型 1-呼入 2-呼出 3-未接**/
	private Integer callType;

	/**扩展字段**/
	private String extend;

	/**创建时间**/
	private String createTime;

	/**修改时间**/
	private String modifyTime;


	public CuCustomerCallRecord() {
		super();
	}
	public CuCustomerCallRecord(String customerCallRecordId,Long customerCollectMessageId,String customerId,Integer appName,String callTime,String phone,String callName,String useTime,Integer callType,String extend,String createTime,String modifyTime) {
		super();
		this.customerCallRecordId = customerCallRecordId;
		this.customerCollectMessageId = customerCollectMessageId;
		this.customerId = customerId;
		this.appName = appName;
		this.callTime = callTime;
		this.phone = phone;
		this.callName = callName;
		this.useTime = useTime;
		this.callType = callType;
		this.extend = extend;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}
	public void setCustomerCallRecordId(String customerCallRecordId){
		this.customerCallRecordId = customerCallRecordId;
	}

	public String getCustomerCallRecordId(){
		return this.customerCallRecordId;
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

	public void setCallTime(String callTime){
		this.callTime = callTime;
	}

	public String getCallTime(){
		return this.callTime;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setCallName(String callName){
		this.callName = callName;
	}

	public String getCallName(){
		return this.callName;
	}

	public void setUseTime(String useTime){
		this.useTime = useTime;
	}

	public String getUseTime(){
		return this.useTime;
	}

	public void setCallType(Integer callType){
		this.callType = callType;
	}

	public Integer getCallType(){
		return this.callType;
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
