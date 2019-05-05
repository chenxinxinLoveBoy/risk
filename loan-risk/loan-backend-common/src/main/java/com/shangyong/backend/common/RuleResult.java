package com.shangyong.backend.common;

import java.util.List;

/**
 * 风控校验返回对象
 * @author xiangxianjin
 *
 */
public class RuleResult {

	public static final int STATUS_YES = 1;
	public static final int STATUS_NO = 0;

	/**
	 * 是否命中
	 * //默认失败，没有命中
	 */
	private boolean isblack = false;
	
	/**
	 * 是否命中:0--没有命中,1--命中.
	 *  默认 =0
	 */
	private int state = RuleResult.STATUS_NO;
	
	/**
	 * 命中规则code
	 *  默认为空，没有命中
	 */
	private String blackCode = "";
	
	/**
	 * 命中规则提示信息
	 *  默认提示语为空，没有命中
	 */
	private String message = "";
	
	/**
	 * 命中集合
	 */
	private List<Content> contents;
	
	/**
	 * 附加消息提示
	 */
	private Object ext = null;


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



	public RuleResult() {
		super();
	}

	public RuleResult(boolean isblack, String message) {
		super();
		this.isblack = isblack;
		this.message = message;
	}


	public List<Content> getContents() {
		return contents;
	}

	public void setContents(List<Content> contents) {
		this.contents = contents;
	}

	@Override
	public String toString() {
		return "RuleResult{" +
				"isblack=" + isblack +
				", state=" + state +
				", blackCode='" + blackCode + '\'' +
				", message='" + message + '\'' +
				", contents=" + contents +
				", ext=" + ext +
				", dsSource='" + dsSource + '\'' +
				", banCodeClassCode='" + banCodeClassCode + '\'' +
				'}';
	}

	public RuleResult(boolean isblack, int state, String blackCode, String message, List<Content> contents, Object ext, String dsSource, String banCodeClassCode) {
		this.isblack = isblack;
		this.state = state;
		this.blackCode = blackCode;
		this.message = message;
		this.contents = contents;
		this.ext = ext;
		this.dsSource = dsSource;
		this.banCodeClassCode = banCodeClassCode;
	}

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

	public boolean getIsblack() {
		return isblack;
	}

	public void setIsblack(boolean isblack) {
		this.isblack = isblack;
	}

	public String getMessage() {
		return message;
	}

	public String getBlackCode() {
		return blackCode;
	}

	public void setBlackCode(String blackCode) {
		this.blackCode = blackCode;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getExt() {
		return ext;
	}

	public void setExt(Object ext) {
		this.ext = ext;
	}


}
