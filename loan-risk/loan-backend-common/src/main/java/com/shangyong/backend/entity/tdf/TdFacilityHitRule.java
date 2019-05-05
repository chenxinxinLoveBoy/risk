package com.shangyong.backend.entity.tdf;


/**
 * 同盾设备指纹命中规则表bean
 * @author mingke.shi
 * @date Wed Dec 13 20:35:23 CST 2017
 **/
public class TdFacilityHitRule {

	/**主键id**/
	private String tdFacilityHitRuleId;

	/**申请单编号**/
	private String buApplicationId;

	/**该条规则的决策结果，Accept/Review/Reject**/
	private String decision;

	/**该条规则的风险分数，只有在权重模式下有效**/
	private String score;

	/**规则名称**/
	private String name;

	/**规则唯一编码**/
	private String uuid;

	/**规则的父规则uuid**/
	private String parentUuid;

	/**请求的唯一序列号**/
	private String id;

	/**创建日期**/
	private String createTime;

	/**创建人**/
	private String createMan;

	/**修改日期**/
	private String modifyTime;

	/**修改人**/
	private String modifyMan;

	/**备注**/
	private String remark;


	public TdFacilityHitRule() {
		super();
	}
	public TdFacilityHitRule(String tdFacilityHitRuleId,String buApplicationId,String decision,String score,String name,String uuid,String parentUuid,String id,String createTime,String createMan,String modifyTime,String modifyMan,String remark) {
		super();
		this.tdFacilityHitRuleId = tdFacilityHitRuleId;
		this.buApplicationId = buApplicationId;
		this.decision = decision;
		this.score = score;
		this.name = name;
		this.uuid = uuid;
		this.parentUuid = parentUuid;
		this.id = id;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}
	public void setTdFacilityHitRuleId(String tdFacilityHitRuleId){
		this.tdFacilityHitRuleId = tdFacilityHitRuleId;
	}

	public String getTdFacilityHitRuleId(){
		return this.tdFacilityHitRuleId;
	}

	public void setBuApplicationId(String buApplicationId){
		this.buApplicationId = buApplicationId;
	}

	public String getBuApplicationId(){
		return this.buApplicationId;
	}

	public void setDecision(String decision){
		this.decision = decision;
	}

	public String getDecision(){
		return this.decision;
	}

	public void setScore(String score){
		this.score = score;
	}

	public String getScore(){
		return this.score;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setUuid(String uuid){
		this.uuid = uuid;
	}

	public String getUuid(){
		return this.uuid;
	}

	public void setParentUuid(String parentUuid){
		this.parentUuid = parentUuid;
	}

	public String getParentUuid(){
		return this.parentUuid;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return this.id;
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
	@Override
	public String toString() {
		return "TdFacilityHitRule [tdFacilityHitRuleId=" + tdFacilityHitRuleId + ", buApplicationId=" + buApplicationId
				+ ", decision=" + decision + ", score=" + score + ", name=" + name + ", uuid=" + uuid + ", parentUuid="
				+ parentUuid + ", id=" + id + ", createTime=" + createTime + ", createMan=" + createMan
				+ ", modifyTime=" + modifyTime + ", modifyMan=" + modifyMan + ", remark=" + remark + "]";
	}

}
