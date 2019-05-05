package com.shangyong.backend.entity;

import java.io.Serializable;

/**
 * 客户人脸识别评分记录bean
 * @author xiajiyun
 * @date Thu Aug 03 13:49:44 CST 2017
 **/
public class FaceRecognitionScore implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**认证编号**/
	private String authenticationNumber;

	/**平台用户编号**/
	private String platformCustomerId;

	/**人脸认证分数**/
	private String faceAuthenticationScore;

	/**人脸配置阈值分数**/
	private String faceThresholdScore;

	/**身份证正面认证分数**/
	private String frontofIdCardScore;

	/**身份证正面配置阈值分数**/
	private String frontofIdCardThresholdScore;

	/**身份证反面认证分数**/
	private String idcardNegativeScore;

	/**身份证反面配置阈值分数**/
	private String idcardNegativeThresholdScore;

	/**综合认证分数**/
	private String comprehensiveScore;

	/**综合认证配置阈值分数**/
	private String comprehensiveThresholdScore;

	/**用户活体检测视频存储URL**/
	private String aliveUrl;

	/**人脸识别图存储URL**/
	private String faceUrl;

	/**身份证反面存储URL**/
	private String backUrl;

	/**身份证正面存储URL**/
	private String frontUrl;

	/**创建时间**/
	private String createTime;

	/**修改时间**/
	private String modifyTime;


	public FaceRecognitionScore() {
		super();
	}
	public FaceRecognitionScore(String authenticationNumber,String platformCustomerId,String faceAuthenticationScore,String faceThresholdScore,String frontofIdCardScore,String frontofIdCardThresholdScore,String idcardNegativeScore,String idcardNegativeThresholdScore,String comprehensiveScore,String comprehensiveThresholdScore,String aliveUrl,String faceUrl,String backUrl,String frontUrl,String createTime,String modifyTime) {
		super();
		this.authenticationNumber = authenticationNumber;
		this.platformCustomerId = platformCustomerId;
		this.faceAuthenticationScore = faceAuthenticationScore;
		this.faceThresholdScore = faceThresholdScore;
		this.frontofIdCardScore = frontofIdCardScore;
		this.frontofIdCardThresholdScore = frontofIdCardThresholdScore;
		this.idcardNegativeScore = idcardNegativeScore;
		this.idcardNegativeThresholdScore = idcardNegativeThresholdScore;
		this.comprehensiveScore = comprehensiveScore;
		this.comprehensiveThresholdScore = comprehensiveThresholdScore;
		this.aliveUrl = aliveUrl;
		this.faceUrl = faceUrl;
		this.backUrl = backUrl;
		this.frontUrl = frontUrl;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}
	public void setAuthenticationNumber(String authenticationNumber){
		this.authenticationNumber = authenticationNumber;
	}

	public String getAuthenticationNumber(){
		return this.authenticationNumber;
	}

	public void setPlatformCustomerId(String platformCustomerId){
		this.platformCustomerId = platformCustomerId;
	}

	public String getPlatformCustomerId(){
		return this.platformCustomerId;
	}

	public void setFaceAuthenticationScore(String faceAuthenticationScore){
		this.faceAuthenticationScore = faceAuthenticationScore;
	}

	public String getFaceAuthenticationScore(){
		return this.faceAuthenticationScore;
	}

	public void setFaceThresholdScore(String faceThresholdScore){
		this.faceThresholdScore = faceThresholdScore;
	}

	public String getFaceThresholdScore(){
		return this.faceThresholdScore;
	}

	public void setFrontofIdCardScore(String frontofIdCardScore){
		this.frontofIdCardScore = frontofIdCardScore;
	}

	public String getFrontofIdCardScore(){
		return this.frontofIdCardScore;
	}

	public void setFrontofIdCardThresholdScore(String frontofIdCardThresholdScore){
		this.frontofIdCardThresholdScore = frontofIdCardThresholdScore;
	}

	public String getFrontofIdCardThresholdScore(){
		return this.frontofIdCardThresholdScore;
	}

	public void setIdcardNegativeScore(String idcardNegativeScore){
		this.idcardNegativeScore = idcardNegativeScore;
	}

	public String getIdcardNegativeScore(){
		return this.idcardNegativeScore;
	}

	public void setIdcardNegativeThresholdScore(String idcardNegativeThresholdScore){
		this.idcardNegativeThresholdScore = idcardNegativeThresholdScore;
	}

	public String getIdcardNegativeThresholdScore(){
		return this.idcardNegativeThresholdScore;
	}

	public void setComprehensiveScore(String comprehensiveScore){
		this.comprehensiveScore = comprehensiveScore;
	}

	public String getComprehensiveScore(){
		return this.comprehensiveScore;
	}

	public void setComprehensiveThresholdScore(String comprehensiveThresholdScore){
		this.comprehensiveThresholdScore = comprehensiveThresholdScore;
	}

	public String getComprehensiveThresholdScore(){
		return this.comprehensiveThresholdScore;
	}

	public void setAliveUrl(String aliveUrl){
		this.aliveUrl = aliveUrl;
	}

	public String getAliveUrl(){
		return this.aliveUrl;
	}

	public void setFaceUrl(String faceUrl){
		this.faceUrl = faceUrl;
	}

	public String getFaceUrl(){
		return this.faceUrl;
	}

	public void setBackUrl(String backUrl){
		this.backUrl = backUrl;
	}

	public String getBackUrl(){
		return this.backUrl;
	}

	public void setFrontUrl(String frontUrl){
		this.frontUrl = frontUrl;
	}

	public String getFrontUrl(){
		return this.frontUrl;
	}

	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getCreateTime(){
		return this.createTime;
	}

	public void setModifyTime(String modifyTime){
		this.modifyTime = modifyTime;
	}

	public String getModifyTime(){
		return this.modifyTime;
	}

}
