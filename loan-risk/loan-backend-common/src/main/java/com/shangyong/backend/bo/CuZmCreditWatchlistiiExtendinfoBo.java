package com.shangyong.backend.bo;


/**
 * 芝麻信用行业关注名单扩展信息表bean
 * @author xiajiyun
 * @date Fri Jul 28 13:39:26 CST 2017
 **/
public class CuZmCreditWatchlistiiExtendinfoBo {

	/**主键自增id**/
	private Integer extendInfoId;

	/**cu_zm_credit_watchlistii_detail.detail_id**/
	private Integer detailId;

	/**补充信息字段的英文编码**/
	private String keyinfo;

	/**补充信息字段的信息内容**/
	private String value;

	/**补充信息字段的中文描述**/
	private String description;

	/**状态：默认1有效**/
	private Integer state;

	/**备注**/
	private String remark;
	
	/**补充信息字段的信息内容説明**/
	private String valueExplain;
	

	public CuZmCreditWatchlistiiExtendinfoBo() {
		super();
	}
 
	public void setExtendInfoId(Integer extendInfoId){
		this.extendInfoId = extendInfoId;
	}

	public Integer getExtendInfoId(){
		return this.extendInfoId;
	}

	public void setDetailId(Integer detailId){
		this.detailId = detailId;
	}

	public Integer getDetailId(){
		return this.detailId;
	}


	public String getKeyinfo() {
		return keyinfo;
	}

	public void setKeyinfo(String keyinfo) {
		this.keyinfo = keyinfo;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return this.value;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return this.description;
	}

	public void setState(Integer state){
		this.state = state;
	}

	public Integer getState(){
		return this.state;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return this.remark;
	}
    
	
	public String getValueExplain() {
		return valueExplain;
	}

	public void setValueExplain(String valueExplain) {
		this.valueExplain = valueExplain;
	}

	@Override
	public String toString() {
		return "CuZmCreditWatchlistiiExtendinfoBo [extendInfoId=" + extendInfoId + ", detailId=" + detailId
				+ ", keyinfo=" + keyinfo + ", value=" + value + ", description=" + description + ", state=" + state
				+ ", remark=" + remark + "]";
	}

	
}
