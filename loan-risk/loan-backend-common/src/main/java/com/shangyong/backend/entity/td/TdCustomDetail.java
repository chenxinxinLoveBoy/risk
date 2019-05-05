package com.shangyong.backend.entity.td;


/**
 * bean
 * @author chengfeng.lu
 * @date Wed Dec 13 11:50:11 CST 2017
 **/
public class TdCustomDetail {

	/****/
	private String tdCustomDetailId;

	/**风险集合id**/
	private String tdRiskItemsId;

	/**列表数据**/
	private String hitListDatas;

	/**高风险区域**/
	private String highRiskAreas;


	public TdCustomDetail() {
		super();
	}
	public TdCustomDetail(String tdCustomDetailId,String tdRiskItemsId,String hitListDatas,String highRiskAreas) {
		super();
		this.tdCustomDetailId = tdCustomDetailId;
		this.tdRiskItemsId = tdRiskItemsId;
		this.hitListDatas = hitListDatas;
		this.highRiskAreas = highRiskAreas;
	}
	public void setTdCustomDetailId(String tdCustomDetailId){
		this.tdCustomDetailId = tdCustomDetailId;
	}

	public String getTdCustomDetailId(){
		return this.tdCustomDetailId;
	}

	public void setTdRiskItemsId(String tdRiskItemsId){
		this.tdRiskItemsId = tdRiskItemsId;
	}

	public String getTdRiskItemsId(){
		return this.tdRiskItemsId;
	}

	public void setHitListDatas(String hitListDatas){
		this.hitListDatas = hitListDatas;
	}

	public String getHitListDatas(){
		return this.hitListDatas;
	}

	public void setHighRiskAreas(String highRiskAreas){
		this.highRiskAreas = highRiskAreas;
	}

	public String getHighRiskAreas(){
		return this.highRiskAreas;
	}

}
