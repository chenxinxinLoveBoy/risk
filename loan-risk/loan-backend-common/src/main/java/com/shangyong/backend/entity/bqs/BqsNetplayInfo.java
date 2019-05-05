package com.shangyong.backend.entity.bqs;

import java.io.Serializable;

/**
 * bean
 * @author chengfeng.lu
 * @date Sun Dec 10 15:31:50 CST 2017
 **/
public class BqsNetplayInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/****/
	private String bqsNetplayInfoId;

	/****/
	private String bqsPersonalInfoId;

	/**对战类型**/
	private String netType;

	/**应用名称**/
	private String bizName;

	/**用户代理**/
	private String userAgent;

	/**域名**/
	private String domainName;

	/****/
	private String featInfo;

	/**客户端ip**/
	private String clientIp;

	/**访问ip**/
	private String accessIp;

	/**持续时间**/
	private String durationTime;

	/**开始时间**/
	private String beginTime;

	/**总流量**/
	private String totalTraffic;

	/**备注**/
	private String remark;

	/****/
	private String createTime;

	/****/
	private String modifyTime;

	/****/
	private String createMan;

	/****/
	private String modifyMan;


	public BqsNetplayInfo() {
		super();
	}
	public BqsNetplayInfo(String bqsNetplayInfoId,String bqsPersonalInfoId,String netType,String bizName,String userAgent,String domainName,String featInfo,String clientIp,String accessIp,String durationTime,String beginTime,String totalTraffic,String remark,String createTime,String modifyTime,String createMan,String modifyMan) {
		super();
		this.bqsNetplayInfoId = bqsNetplayInfoId;
		this.bqsPersonalInfoId = bqsPersonalInfoId;
		this.netType = netType;
		this.bizName = bizName;
		this.userAgent = userAgent;
		this.domainName = domainName;
		this.featInfo = featInfo;
		this.clientIp = clientIp;
		this.accessIp = accessIp;
		this.durationTime = durationTime;
		this.beginTime = beginTime;
		this.totalTraffic = totalTraffic;
		this.remark = remark;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createMan = createMan;
		this.modifyMan = modifyMan;
	}
	public void setBqsNetplayInfoId(String bqsNetplayInfoId){
		this.bqsNetplayInfoId = bqsNetplayInfoId;
	}

	public String getBqsNetplayInfoId(){
		return this.bqsNetplayInfoId;
	}

	public void setBqsPersonalInfoId(String bqsPersonalInfoId){
		this.bqsPersonalInfoId = bqsPersonalInfoId;
	}

	public String getBqsPersonalInfoId(){
		return this.bqsPersonalInfoId;
	}

	public void setNetType(String netType){
		this.netType = netType;
	}

	public String getNetType(){
		return this.netType;
	}

	public void setBizName(String bizName){
		this.bizName = bizName;
	}

	public String getBizName(){
		return this.bizName;
	}

	public void setUserAgent(String userAgent){
		this.userAgent = userAgent;
	}

	public String getUserAgent(){
		return this.userAgent;
	}

	public void setDomainName(String domainName){
		this.domainName = domainName;
	}

	public String getDomainName(){
		return this.domainName;
	}

	public void setFeatInfo(String featInfo){
		this.featInfo = featInfo;
	}

	public String getFeatInfo(){
		return this.featInfo;
	}

	public void setClientIp(String clientIp){
		this.clientIp = clientIp;
	}

	public String getClientIp(){
		return this.clientIp;
	}

	public void setAccessIp(String accessIp){
		this.accessIp = accessIp;
	}

	public String getAccessIp(){
		return this.accessIp;
	}

	public void setDurationTime(String durationTime){
		this.durationTime = durationTime;
	}

	public String getDurationTime(){
		return this.durationTime;
	}

	public void setBeginTime(String beginTime){
		this.beginTime = beginTime;
	}

	public String getBeginTime(){
		return this.beginTime;
	}

	public void setTotalTraffic(String totalTraffic){
		this.totalTraffic = totalTraffic;
	}

	public String getTotalTraffic(){
		return this.totalTraffic;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
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

}
