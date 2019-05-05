package com.shangyong.backend.bo;


/**
 * 芝麻信用行业关注名单详细信息表bean
 * @author xiajiyun
 * @date Fri Jul 28 13:39:26 CST 2017
 **/
public class CuZmCreditWatchlistiiDetailBo {

	/**主键自增id**/
	private Integer detailId;

	/**请参考cu_zm_credit_watchlistii.watchlistii_id**/
	private Integer watchlistiiId;

	/**风险信息行业编码 **/
	private String bizCode;

	/**行业名单数据类型，当前为保留字段只返回0，请忽略**/
	private Long level;

	/**行业名单风险类型**/
	private String type;

	/**风险编码**/
	private String code;

	/**数据刷新时间**/
	private String refreshTime;

	/**结清状态 true/false**/
	private Boolean settlement;

	/**用户本人对该条负面记录有异议时，表示该异议处理流程的状态 **/
	private String status;

	/**当用户进行异议处理，并核查完毕之后，仍有异议时，给出的声明**/
	private String statement;

	/**状态：默认1有效**/
	private Integer state;

	/**备注**/
	private String remark;
	
    /**风险信息行业说明  **/
	private String bizExplain;
	
	/**行业名单风险类型说明  **/
	private String typeExplain;
	
	/**风险编码说明  **/
	private String codeExplain;

	public CuZmCreditWatchlistiiDetailBo() {
		super();
	}
	 
	public void setDetailId(Integer detailId){
		this.detailId = detailId;
	}

	public Integer getDetailId(){
		return this.detailId;
	}

	public void setWatchlistiiId(Integer watchlistiiId){
		this.watchlistiiId = watchlistiiId;
	}

	public Integer getWatchlistiiId(){
		return this.watchlistiiId;
	}

	public void setBizCode(String bizCode){
		this.bizCode = bizCode;
	}

	public String getBizCode(){
		return this.bizCode;
	}


	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return this.type;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return this.code;
	}

	public void setRefreshTime(String refreshTime){
		this.refreshTime = refreshTime;
	}

	public String getRefreshTime(){
		return this.refreshTime;
	}

	public Boolean getSettlement() {
		return settlement;
	}

	public void setSettlement(Boolean settlement) {
		this.settlement = settlement;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return this.status;
	}

	public void setStatement(String statement){
		this.statement = statement;
	}

	public String getStatement(){
		return this.statement;
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
    
	public String getBizExplain() {
		return bizExplain;
	}

	public void setBizExplain(String bizExplain) {
		this.bizExplain = bizExplain;
	}

	public String getTypeExplain() {
		return typeExplain;
	}

	public void setTypeExplain(String typeExplain) {
		this.typeExplain = typeExplain;
	}

	public String getCodeExplain() {
		return codeExplain;
	}

	public void setCodeExplain(String codeExplain) {
		this.codeExplain = codeExplain;
	}

	@Override
	public String toString() {
		return "CuZmCreditWatchlistiiDetailBo [detailId=" + detailId + ", watchlistiiId=" + watchlistiiId + ", bizCode="
				+ bizCode + ", level=" + level + ", type=" + type + ", code=" + code + ", refreshTime=" + refreshTime
				+ ", settlement=" + settlement + ", status=" + status + ", statement=" + statement + ", state=" + state
				+ ", remark=" + remark + "]";
	}

	
}
