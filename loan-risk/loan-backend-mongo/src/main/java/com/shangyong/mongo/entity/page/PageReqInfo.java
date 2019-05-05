package com.shangyong.mongo.entity.page;

import java.util.Map;

public class PageReqInfo {

	private Map<String,Object> paramMap;//查询条件
	
	private int pageSize;//查询条数
	
	private long startTime;//查询开始时间
	
	private long endTime;//查询结束时间
	
	private String pageType;//分页标识  up 上一页 dn 下一页
	
	private String lastId;//主键id

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getLastId() {
		return lastId;
	}

	public void setLastId(String lastId) {
		this.lastId = lastId;
	}

	/**
	 * 
	 * @param paramMap 查询条件（比如appname 限定是多少） null
	 * @param pageSize 每页查询条数 not null
	 * @param startTime 查询开始时间 not null
	 * @param endTime 查询结束时间 not null
	 * @param pageType 分页类型（上一页：up 下一页：dn） null
	 * @param lastId 查询主键id（分页的时候，上次最后一条记录的id，在做分页的时候，这个字段不能为空） null
	 */
	public PageReqInfo(Map<String, Object> paramMap, int pageSize, long startTime, long endTime, String pageType,
			String lastId) {
		super();
		this.paramMap = paramMap;
		this.pageSize = pageSize;
		this.startTime = startTime;
		this.endTime = endTime;
		this.pageType = pageType;
		this.lastId = lastId;
	}
	
}
