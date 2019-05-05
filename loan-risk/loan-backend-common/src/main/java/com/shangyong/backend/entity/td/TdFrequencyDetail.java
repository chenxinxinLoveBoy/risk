package com.shangyong.backend.entity.td;


/**
 * bean
 * @author chengfeng.lu
 * @date Wed Dec 13 11:50:11 CST 2017
 **/
public class TdFrequencyDetail {

	/****/
	private String tdFrequencyDetailId;

	/****/
	private String tdRiskItemsId;

	/**关联数据列表只有命中【关联从属性个数】这类频度规则才会有此字段**/
	private String data;

	/**规则命中基本详情**/
	private String detail;


	public TdFrequencyDetail() {
		super();
	}
	public TdFrequencyDetail(String tdFrequencyDetailId,String tdRiskItemsId,String data,String detail) {
		super();
		this.tdFrequencyDetailId = tdFrequencyDetailId;
		this.tdRiskItemsId = tdRiskItemsId;
		this.data = data;
		this.detail = detail;
	}
	public void setTdFrequencyDetailId(String tdFrequencyDetailId){
		this.tdFrequencyDetailId = tdFrequencyDetailId;
	}

	public String getTdFrequencyDetailId(){
		return this.tdFrequencyDetailId;
	}

	public void setTdRiskItemsId(String tdRiskItemsId){
		this.tdRiskItemsId = tdRiskItemsId;
	}

	public String getTdRiskItemsId(){
		return this.tdRiskItemsId;
	}

	public void setData(String data){
		this.data = data;
	}

	public String getData(){
		return this.data;
	}

	public void setDetail(String detail){
		this.detail = detail;
	}

	public String getDetail(){
		return this.detail;
	}

}
