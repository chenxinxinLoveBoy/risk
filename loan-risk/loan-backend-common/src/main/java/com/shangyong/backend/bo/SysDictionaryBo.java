package com.shangyong.backend.bo;

import java.io.Serializable;
import java.util.List;

public class SysDictionaryBo implements Serializable {

	private static final long serialVersionUID = -5862709675378687413L;

	/**
	 * 大类code
	 */
	private String code;

	/**
	 * 大类value
	 */
	private String value;

	/**
	 * 小类集合
	 */
	private List<SysDictionaryBo> subDictList;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<SysDictionaryBo> getSubDictList() {
		return subDictList;
	}

	public void setSubDictList(List<SysDictionaryBo> subDictList) {
		this.subDictList = subDictList;
	}

}
