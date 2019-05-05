package com.shangyong.backend.entity.td;


/**
 * 风险群体规则bean
 * @author chengfeng.lu
 * @date Wed Dec 13 11:50:11 CST 2017
 **/
public class TdSuspectTeam {

	/****/
	private String tdSuspectTeamId;

	/****/
	private String tdRiskItemsId;

	/**疑似风险群体编号**/
	private String groupId;

	/**匹配字段名称**/
	private String dimType;

	/**匹配字段值**/
	private String dimValue;

	/**成员分布**/
	private String nodeDist;

	/**风险名单分布**/
	private String fraudDist;

	/**风险名单占比**/
	private String blackRat;

	/**关注名单占比**/
	private String greyRat;

	/**一度关联节点个数**/
	private String degree;

	/**疑似风险群体成员数**/
	private String totalCnt;

	/**风险名单成员数**/
	private String blackCnt;

	/**关注名单成员数**/
	private String greyCnt;

	/**核心节点距离**/
	private String coreDst;

	/**风险节点距离**/
	private String blackDst;

	/**二度关联节点个数**/
	private String totalCntTwo;

	/**一度风险名单个数**/
	private String blackCntOne;

	/**二度风险名单个数**/
	private String blackCntTwo;

	/**一度风险名单分布**/
	private String fraudDistOne;

	/**二度风险名单分布**/
	private String fraudDistTwo;


	public TdSuspectTeam() {
		super();
	}
	public TdSuspectTeam(String tdSuspectTeamId,String tdRiskItemsId,String groupId,String dimType,String dimValue,String nodeDist,String fraudDist,String blackRat,String greyRat,String degree,String totalCnt,String blackCnt,String greyCnt,String coreDst,String blackDst,String totalCntTwo,String blackCntOne,String blackCntTwo,String fraudDistOne,String fraudDistTwo) {
		super();
		this.tdSuspectTeamId = tdSuspectTeamId;
		this.tdRiskItemsId = tdRiskItemsId;
		this.groupId = groupId;
		this.dimType = dimType;
		this.dimValue = dimValue;
		this.nodeDist = nodeDist;
		this.fraudDist = fraudDist;
		this.blackRat = blackRat;
		this.greyRat = greyRat;
		this.degree = degree;
		this.totalCnt = totalCnt;
		this.blackCnt = blackCnt;
		this.greyCnt = greyCnt;
		this.coreDst = coreDst;
		this.blackDst = blackDst;
		this.totalCntTwo = totalCntTwo;
		this.blackCntOne = blackCntOne;
		this.blackCntTwo = blackCntTwo;
		this.fraudDistOne = fraudDistOne;
		this.fraudDistTwo = fraudDistTwo;
	}
	public void setTdSuspectTeamId(String tdSuspectTeamId){
		this.tdSuspectTeamId = tdSuspectTeamId;
	}

	public String getTdSuspectTeamId(){
		return this.tdSuspectTeamId;
	}

	public void setTdRiskItemsId(String tdRiskItemsId){
		this.tdRiskItemsId = tdRiskItemsId;
	}

	public String getTdRiskItemsId(){
		return this.tdRiskItemsId;
	}

	public void setGroupId(String groupId){
		this.groupId = groupId;
	}

	public String getGroupId(){
		return this.groupId;
	}

	public void setDimType(String dimType){
		this.dimType = dimType;
	}

	public String getDimType(){
		return this.dimType;
	}

	public void setDimValue(String dimValue){
		this.dimValue = dimValue;
	}

	public String getDimValue(){
		return this.dimValue;
	}

	public void setNodeDist(String nodeDist){
		this.nodeDist = nodeDist;
	}

	public String getNodeDist(){
		return this.nodeDist;
	}

	public void setFraudDist(String fraudDist){
		this.fraudDist = fraudDist;
	}

	public String getFraudDist(){
		return this.fraudDist;
	}

	public void setBlackRat(String blackRat){
		this.blackRat = blackRat;
	}

	public String getBlackRat(){
		return this.blackRat;
	}

	public void setGreyRat(String greyRat){
		this.greyRat = greyRat;
	}

	public String getGreyRat(){
		return this.greyRat;
	}

	public void setDegree(String degree){
		this.degree = degree;
	}

	public String getDegree(){
		return this.degree;
	}

	public void setTotalCnt(String totalCnt){
		this.totalCnt = totalCnt;
	}

	public String getTotalCnt(){
		return this.totalCnt;
	}

	public void setBlackCnt(String blackCnt){
		this.blackCnt = blackCnt;
	}

	public String getBlackCnt(){
		return this.blackCnt;
	}

	public void setGreyCnt(String greyCnt){
		this.greyCnt = greyCnt;
	}

	public String getGreyCnt(){
		return this.greyCnt;
	}

	public void setCoreDst(String coreDst){
		this.coreDst = coreDst;
	}

	public String getCoreDst(){
		return this.coreDst;
	}

	public void setBlackDst(String blackDst){
		this.blackDst = blackDst;
	}

	public String getBlackDst(){
		return this.blackDst;
	}

	public void setTotalCntTwo(String totalCntTwo){
		this.totalCntTwo = totalCntTwo;
	}

	public String getTotalCntTwo(){
		return this.totalCntTwo;
	}

	public void setBlackCntOne(String blackCntOne){
		this.blackCntOne = blackCntOne;
	}

	public String getBlackCntOne(){
		return this.blackCntOne;
	}

	public void setBlackCntTwo(String blackCntTwo){
		this.blackCntTwo = blackCntTwo;
	}

	public String getBlackCntTwo(){
		return this.blackCntTwo;
	}

	public void setFraudDistOne(String fraudDistOne){
		this.fraudDistOne = fraudDistOne;
	}

	public String getFraudDistOne(){
		return this.fraudDistOne;
	}

	public void setFraudDistTwo(String fraudDistTwo){
		this.fraudDistTwo = fraudDistTwo;
	}

	public String getFraudDistTwo(){
		return this.fraudDistTwo;
	}

}
