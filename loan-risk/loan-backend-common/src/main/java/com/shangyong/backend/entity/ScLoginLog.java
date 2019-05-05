package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 登陆登出日志表bean
 * @author xk
 * @date Tue Jul 04 09:19:38 CST 2017
 **/
public class ScLoginLog  extends BaseBo{

	/**主键自增id**/
	private Integer loginLogId;

	/**请参考sc_user.id**/
	private Integer userId;

	/**登陆/退出 时间**/
	private String loginTime;

	/**登陆/退出 时间**/
	private String loginOutTime;

	/**IP地址**/
	private String ip;

	/**1：登陆，2：退出**/
	private Integer type;

	/**备注**/
	private String remark;


	public ScLoginLog() {
		super();
	}
	public ScLoginLog(Integer loginLogId,Integer userId,String loginTime,String loginOutTime,String ip,Integer type,String remark) {
		super();
		this.loginLogId = loginLogId;
		this.userId = userId;
		this.loginTime = loginTime;
		this.loginOutTime = loginOutTime;
		this.ip = ip;
		this.type = type;
		this.remark = remark;
	}
	public void setLoginLogId(Integer loginLogId){
		this.loginLogId = loginLogId;
	}

	public Integer getLoginLogId(){
		return this.loginLogId;
	}

	public void setUserId(Integer userId){
		this.userId = userId;
	}

	public Integer getUserId(){
		return this.userId;
	}

	public void setLoginTime(String loginTime){
		this.loginTime = loginTime;
	}

	public String getLoginTime(){
		return this.loginTime;
	}

	public void setLoginOutTime(String loginOutTime){
		this.loginOutTime = loginOutTime;
	}

	public String getLoginOutTime(){
		return this.loginOutTime;
	}

	public void setIp(String ip){
		this.ip = ip;
	}

	public String getIp(){
		return this.ip;
	}

	public void setType(Integer type){
		this.type = type;
	}

	public Integer getType(){
		return this.type;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

}
