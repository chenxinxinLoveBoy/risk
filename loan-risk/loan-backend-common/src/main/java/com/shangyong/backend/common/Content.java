package com.shangyong.backend.common;

import com.shangyong.backend.common.enums.ContentStateEnum;

/**
 * 命中集合
 * @author Smk
 *
 */
public class Content {

	/**
	 * 是否命中:0--没有命中,1--命中
	 * 默认0，0-没有命中,1-命中
	 */
	private int state = ContentStateEnum.MISS.getCode();

	/**
	 * 命中规则code
	 * 默认为空，没有命中
	 */
	private String blackCode = "";
	
	/**
	 * 命中规则提示信息
	 * 默认提示语为空，没有命中
	 */
	private String message = "";

	/**
	 * 黑名单判断数据来源
	 * @see com.shangyong.backend.common.enums.BlackListDsSourceEnum
	 */
	private String dsSource ;

	/**
	 * 禁止项小类分类
	 * @see com.shangyong.backend.common.enums.BlackListCodeEnum
	 */
	private String banCodeClassCode;

	public String getDsSource() {
		return dsSource;
	}

	public void setDsSource(String dsSource) {
		this.dsSource = dsSource;
	}

	public String getBanCodeClassCode() {
		return banCodeClassCode;
	}

	public void setBanCodeClassCode(String banCodeClassCode) {
		this.banCodeClassCode = banCodeClassCode;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getBlackCode() {
		return blackCode;
	}

	public void setBlackCode(String blackCode) {
		this.blackCode = blackCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Content(int state, String blackCode, String message) {
		super();
		this.state = state;
		this.blackCode = blackCode;
		this.message = message;
	}

	public Content() {
		super();
	}

	@Override
	public String toString() {
		return "Content [state=" + state + ", blackCode=" + blackCode + ", message=" + message + "]";
	}
}
