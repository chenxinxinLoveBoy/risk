package com.shangyong.backend.entity;

import java.io.Serializable;
import java.util.List;

public class ApplicationDubboServiceBean implements Serializable {
	
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;

	/** 申请单对象 */
	private ApplicationBean applicationBean;
	
	/** 平台客户其它账号信息bean */
	private List<CuCustomerOther> cuCustomerOtherList;
	
	/** 平台客户紧急联系人bean */
	private List<CuIcePerson> cuIcePersonList;
	
	/** 平台客户信息表bean */
	private CuPlatformCustomer cuPlatformCustomer;
	
	/** 平台客户所属公司信息bean */
	private CuCustomerCompany cuCustomerCompany;
	
	/** 平台客户客户人脸识别评分记录bean */
	private FaceRecognitionScore faceRecognitionScore;

	/**芝麻 行业信息 **/
	private List<IndustryDetails> industryDetailsList;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public List<IndustryDetails> getIndustryDetailsList() {
		return industryDetailsList;
	}

	public void setIndustryDetailsList(List<IndustryDetails> industryDetailsList) {
		this.industryDetailsList = industryDetailsList;
	}

	public ApplicationBean getApplicationBean() {
		return applicationBean;
	}

	public void setApplicationBean(ApplicationBean applicationBean) {
		this.applicationBean = applicationBean;
	}

	public List<CuCustomerOther> getCuCustomerOtherList() {
		return cuCustomerOtherList;
	}

	public void setCuCustomerOtherList(List<CuCustomerOther> cuCustomerOtherList) {
		this.cuCustomerOtherList = cuCustomerOtherList;
	}

	public List<CuIcePerson> getCuIcePersonList() {
		return cuIcePersonList;
	}

	public void setCuIcePersonList(List<CuIcePerson> cuIcePersonList) {
		this.cuIcePersonList = cuIcePersonList;
	}

	public CuPlatformCustomer getCuPlatformCustomer() {
		return cuPlatformCustomer;
	}

	public void setCuPlatformCustomer(CuPlatformCustomer cuPlatformCustomer) {
		this.cuPlatformCustomer = cuPlatformCustomer;
	}

	public CuCustomerCompany getCuCustomerCompany() {
		return cuCustomerCompany;
	}

	public void setCuCustomerCompany(CuCustomerCompany cuCustomerCompany) {
		this.cuCustomerCompany = cuCustomerCompany;
	}

	public FaceRecognitionScore getFaceRecognitionScore() {
		return faceRecognitionScore;
	}

	public void setFaceRecognitionScore(FaceRecognitionScore faceRecognitionScore) {
		this.faceRecognitionScore = faceRecognitionScore;
	}
	
}
