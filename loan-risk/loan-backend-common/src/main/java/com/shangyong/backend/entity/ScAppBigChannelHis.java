package com.shangyong.backend.entity;


/**
 * 应用市场渠道大类维护历史表bean
 * @author kenzhao
 * @date Mon Sep 18 15:22:24 CST 2017
 **/
public class ScAppBigChannelHis {

	/**历史记录自增主键**/
	private Integer channelBigHisId;

	/**主键**/
	private Integer channelBigId;

	/**渠道大类名称**/
	private String channelBigName;

	/**创建时间**/
	private String createTime;

	/**创建人**/
	private String createMan;

	/**修改时间**/
	private String modifyTime;

	/**修改人**/
	private String modifyMan;

	/**备注**/
	private String remark;

	/**记录新增时间**/
	private String recordNewtime;


	public ScAppBigChannelHis() {
		super();
	}
	public ScAppBigChannelHis(Integer channelBigHisId,Integer channelBigId,String channelBigName,String createTime,String createMan,String modifyTime,String modifyMan,String remark,String recordNewtime) {
		super();
		this.channelBigHisId = channelBigHisId;
		this.channelBigId = channelBigId;
		this.channelBigName = channelBigName;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
		this.recordNewtime = recordNewtime;
	}
	public void setChannelBigHisId(Integer channelBigHisId){
		this.channelBigHisId = channelBigHisId;
	}

	public Integer getChannelBigHisId(){
		return this.channelBigHisId;
	}

	public void setChannelBigId(Integer channelBigId){
		this.channelBigId = channelBigId;
	}

	public Integer getChannelBigId(){
		return this.channelBigId;
	}

	public void setChannelBigName(String channelBigName){
		this.channelBigName = channelBigName;
	}

	public String getChannelBigName(){
		return this.channelBigName;
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

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}

	public void setRecordNewtime(String recordNewtime){
		this.recordNewtime = recordNewtime;
	}

	public String getRecordNewtime(){
		return this.recordNewtime;
	}

}
