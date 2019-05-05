package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 应用市场渠道大类维护表bean
 * @author kenzhao
 * @date Thu Sep 14 16:49:03 CST 2017
 **/
public class ScAppBigChannel extends BaseBo{

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

	/**新增or更新**/
	private String saveOrUpdate;
	
	public ScAppBigChannel() {
		super();
	}
	public ScAppBigChannel(Integer channelBigId,String channelBigName,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.channelBigId = channelBigId;
		this.channelBigName = channelBigName;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
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
	
	public String getSaveOrUpdate() {
		return saveOrUpdate;
	}
	public void setSaveOrUpdate(String saveOrUpdate) {
		this.saveOrUpdate = saveOrUpdate;
	}
	@Override
	public String toString() {
		return "ScAppBigChannel [channelBigId=" + channelBigId + ", channelBigName=" + channelBigName + ", createTime="
				+ createTime + ", createMan=" + createMan + ", modifyTime=" + modifyTime + ", modifyMan=" + modifyMan
				+ ", remark=" + remark + "]";
	}
	
}
