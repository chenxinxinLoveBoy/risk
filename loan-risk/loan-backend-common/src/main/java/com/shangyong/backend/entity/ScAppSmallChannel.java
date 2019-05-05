package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 应用市场渠道小类配置表bean
 * @author kenzhao
 * @date Thu Sep 14 16:49:03 CST 2017
 **/
public class ScAppSmallChannel extends BaseBo{

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

	/**新增or更新**/
	private String saveOrUpdate;
	
	public ScAppSmallChannel() {
		super();
	}
	public ScAppSmallChannel(Integer channelSmallId,Integer channelBigId,String channelSmallName,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.channelSmallId = channelSmallId;
		this.channelBigId = channelBigId;
		this.channelSmallName = channelSmallName;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
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
	
	public String getSaveOrUpdate() {
		return saveOrUpdate;
	}
	public void setSaveOrUpdate(String saveOrUpdate) {
		this.saveOrUpdate = saveOrUpdate;
	}
	@Override
	public String toString() {
		return "ScAppSmallChannel [channelSmallId=" + channelSmallId + ", channelBigId=" + channelBigId
				+ ", channelSmallName=" + channelSmallName + ", createTime=" + createTime + ", createMan=" + createMan
				+ ", modifyTime=" + modifyTime + ", modifyMan=" + modifyMan + ", remark=" + remark + "]";
	}
	
	
}
