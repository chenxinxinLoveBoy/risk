package com.shangyong.mongo.entity.page;

import java.util.List;

public class PageRspInfo {

	private int totalRsults;//总条数
	
	private int pageTotal;//总页数
	
	private int pageLength;//当前页条数
	
	private List resultList;//返回集合对象

	public int getTotalRsults() {
		return totalRsults;
	}

	public void setTotalRsults(int totalRsults) {
		this.totalRsults = totalRsults;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getPageLength() {
		return pageLength;
	}

	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	
}
