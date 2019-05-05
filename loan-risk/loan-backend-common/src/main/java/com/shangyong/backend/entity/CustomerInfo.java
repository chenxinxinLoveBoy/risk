package com.shangyong.backend.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息详情表
 * 
 * @author xuke
 *
 */
public class CustomerInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CuPlatformCustomer cuPlatformCustomer;
	private List<CuIcePerson> cuIcePersonList;
	private CuCustomerOther cuCustomerOther;
	private CuCustomerCompany cuCustomerCompany;
	private FaceRecognitionScore faceRecognitionScore;

	public CustomerInfo() {
		super();
	}

	public CustomerInfo(CuPlatformCustomer cuPlatformCustomer, List<CuIcePerson> cuIcePersonList,
			CuCustomerOther cuCustomerOther, CuCustomerCompany cuCustomerCompany,
			FaceRecognitionScore faceRecognitionScore) {
		super();
		this.cuPlatformCustomer = cuPlatformCustomer;
		this.cuIcePersonList = cuIcePersonList;
		this.cuCustomerOther = cuCustomerOther;
		this.cuCustomerCompany = cuCustomerCompany;
		this.faceRecognitionScore = faceRecognitionScore;
	}

	public CuPlatformCustomer getCuPlatformCustomer() {
		return cuPlatformCustomer;
	}

	public void setCuPlatformCustomer(CuPlatformCustomer cuPlatformCustomer) {
		this.cuPlatformCustomer = cuPlatformCustomer;
	}

	public List<CuIcePerson> getCuIcePersonList() {
		return cuIcePersonList;
	}

	public void setCuIcePersonList(List<CuIcePerson> cuIcePersonList) {
		this.cuIcePersonList = cuIcePersonList;
	}

	public CuCustomerOther getCuCustomerOther() {
		return cuCustomerOther;
	}

	public void setCuCustomerOther(CuCustomerOther cuCustomerOther) {
		this.cuCustomerOther = cuCustomerOther;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}