package com.shangyong.backend.entity.jg;


/**
 * bean
 * @author chengfeng.lu
 * @date Sat Dec 09 19:37:46 CST 2017
 **/
public class JgInfoCheck {

	/****/
	private String jgInfoId;

	/**申请单编号**/
	private String buApplicationId;

	/**姓名**/
	private String name;

	/**身份证号**/
	private String idNumber;

	/**手机号**/
	private String phone;

	/**逾期分数（满足=100，不满足=0）**/
	private Integer overdueScore = 0;

	/**违约分（满足=100，不满足=0）**/
	private Integer violationScore = 0;

	/**关系风险分数**/
	private Double circleScore = 0.0;

	/**最终风险系数得分（越高风险越大）**/
	private Double riskScore;

	/**风险描述（pass=低风险，review=有风险,reject=高风险）**/
	private String riskDescription;

	/****/
	private String jgReturnId;

	/**是否命中,1命中0未命中**/
	private String state;

	/**备注**/
	private String remark;

	/**创建时间**/
	private String createTime;

	/****/
	private String createMan;

	/****/
	private String modifyTime;

	/**最后修改时间**/
	private String modifyMan;


	public JgInfoCheck() {
		super();
	}
	public JgInfoCheck(String jgInfoId,String buApplicationId,String name,String idNumber,String phone,Integer overdueScore,Integer violationScore,Double circleScore,Double riskScore,String riskDescription,String jgReturnId,String state,String remark,String createTime,String createMan,String modifyTime,String modifyMan) {
		super();
		this.jgInfoId = jgInfoId;
		this.buApplicationId = buApplicationId;
		this.name = name;
		this.idNumber = idNumber;
		this.phone = phone;
		this.overdueScore = overdueScore;
		this.violationScore = violationScore;
		this.circleScore = circleScore;
		this.riskScore = riskScore;
		this.riskDescription = riskDescription;
		this.jgReturnId = jgReturnId;
		this.state = state;
		this.remark = remark;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
	}
	public void setJgInfoId(String jgInfoId){
		this.jgInfoId = jgInfoId;
	}

	public String getJgInfoId(){
		return this.jgInfoId;
	}

	public void setBuApplicationId(String buApplicationId){
		this.buApplicationId = buApplicationId;
	}

	public String getBuApplicationId(){
		return this.buApplicationId;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setIdNumber(String idNumber){
		this.idNumber = idNumber;
	}

	public String getIdNumber(){
		return this.idNumber;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setOverdueScore(Integer overdueScore){
		this.overdueScore = overdueScore;
	}

	public Integer getOverdueScore(){
		return this.overdueScore;
	}

	public void setViolationScore(Integer violationScore){
		this.violationScore = violationScore;
	}

	public Integer getViolationScore(){
		return this.violationScore;
	}

	public void setCircleScore(Double circleScore){
		this.circleScore = circleScore;
	}

	public Double getCircleScore(){
		return this.circleScore;
	}

	public void setRiskScore(Double riskScore){
		this.riskScore = riskScore;
	}

	public Double getRiskScore(){
		return this.riskScore;
	}

	public void setRiskDescription(String riskDescription){
		this.riskDescription = riskDescription;
	}

	public String getRiskDescription(){
		return this.riskDescription;
	}

	public void setJgReturnId(String jgReturnId){
		this.jgReturnId = jgReturnId;
	}

	public String getJgReturnId(){
		return this.jgReturnId;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return this.state;
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
	@Override
	public String toString() {
		return "JgInfoCheck [jgInfoId=" + jgInfoId + ", buApplicationId=" + buApplicationId + ", name=" + name
				+ ", idNumber=" + idNumber + ", phone=" + phone + ", overdueScore=" + overdueScore + ", violationScore="
				+ violationScore + ", circleScore=" + circleScore + ", riskScore=" + riskScore + ", riskDescription="
				+ riskDescription + ", jgReturnId=" + jgReturnId + ", state=" + state + ", remark=" + remark
				+ ", createTime=" + createTime + ", createMan=" + createMan + ", modifyTime=" + modifyTime
				+ ", modifyMan=" + modifyMan + "]";
	}
	

}
