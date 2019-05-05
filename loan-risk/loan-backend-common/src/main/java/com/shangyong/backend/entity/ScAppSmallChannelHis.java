package com.shangyong.backend.entity;


/**
 * 应用市场渠道小类配置历史表bean
 * @author kenzhao
 * @date Mon Sep 18 15:22:24 CST 2017
 **/
public class ScAppSmallChannelHis {

	/**历史记录自增主键**/
	private Integer channelSmallHisId;

	/**主键**/
	private Integer channelSmallId;

	/**渠道大类主键**/
	private Integer channelBigId;

	/**渠道小类名称**/
	private String channelSmallName;

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


	public ScAppSmallChannelHis() {
		super();
	}
	public ScAppSmallChannelHis(Integer channelSmallHisId,Integer channelSmallId,Integer channelBigId,String channelSmallName,String createTime,String createMan,String modifyTime,String modifyMan,String remark,String recordNewtime) {
		super();
		this.channelSmallHisId = channelSmallHisId;
		this.channelSmallId = channelSmallId;
		this.channelBigId = channelBigId;
		this.channelSmallName = channelSmallName;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
		this.recordNewtime = recordNewtime;
	}
	public void setChannelSmallHisId(Integer channelSmallHisId){
		this.channelSmallHisId = channelSmallHisId;
	}

	public Integer getChannelSmallHisId(){
		return this.channelSmallHisId;
	}

	public void setChannelSmallId(Integer channelSmallId){
		this.channelSmallId = channelSmallId;
	}

	public Integer getChannelSmallId(){
		return this.channelSmallId;
	}

	public void setChannelBigId(Integer channelBigId){
		this.channelBigId = channelBigId;
	}

	public Integer getChannelBigId(){
		return this.channelBigId;
	}

	public void setChannelSmallName(String channelSmallName){
		this.channelSmallName = channelSmallName;
	}

	public String getChannelSmallName(){
		return this.channelSmallName;
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
