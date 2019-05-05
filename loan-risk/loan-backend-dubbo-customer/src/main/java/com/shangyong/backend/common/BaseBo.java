package com.shangyong.backend.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 公用
 * 
 * @author xiajiyun
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true) // 可以忽略掉从JSON(由于在应用中没有完全匹配的POJO)中获得的所有“多余的”属性(此处忽略从Basebo继承来的pageIndex和
											// pageSize属性)
public class BaseBo {

	// 这2个参数由layui页面初始化list表单时，传过来
	private Integer pageIndex = 0;// 当前页码
	private Integer pageSize = 10;// 当前页条数

	// 菜单名称
	private String menuNames;
	// 按钮操作功能名称
	private String functionNames;

	public BaseBo() {
	}

	public String getMenuNames() {
		return menuNames;
	}

	public void setMenuNames(String menuNames) {
		this.menuNames = menuNames;
	}

	public String getFunctionNames() {
		return functionNames;
	}

	public void setFunctionNames(String functionNames) {
		this.functionNames = functionNames;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		if (pageIndex != null && pageIndex >= 1) {// 当前页
			this.pageIndex = (pageIndex - 1) * pageSize;
		} else {
			this.pageIndex = pageIndex;
		}

	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
