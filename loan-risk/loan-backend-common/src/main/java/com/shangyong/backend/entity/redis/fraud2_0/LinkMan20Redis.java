package com.shangyong.backend.entity.redis.fraud2_0;

import java.util.HashMap;

/**
 * 通讯录
 */
public class LinkMan20Redis {
	
	private String linkmanCnt;
	private String alienLinkmanCnt;
    private String alienNum8or12LinkmanCnt;

	public String getAlienLinkmanCnt() {
		return alienLinkmanCnt;
	}

	public void setAlienLinkmanCnt(String alienLinkmanCnt) {
		this.alienLinkmanCnt = alienLinkmanCnt;
	}

	public String getAlienNum8or12LinkmanCnt() {
		return alienNum8or12LinkmanCnt;
	}

	public void setAlienNum8or12LinkmanCnt(String alienNum8or12LinkmanCnt) {
		this.alienNum8or12LinkmanCnt = alienNum8or12LinkmanCnt;
	}

	public String getLinkmanCnt() {
		return linkmanCnt;
	}

	public void setLinkmanCnt(String linkmanCnt) {
		this.linkmanCnt = linkmanCnt;
	}

	public HashMap<String,String> toMap(){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("linkmanCnt", linkmanCnt == null ? "unknow" : linkmanCnt);
		map.put("alienLinkmanCnt", alienLinkmanCnt == null ? "unknow" : alienLinkmanCnt);
		map.put("alienNum8or12LinkmanCnt", alienNum8or12LinkmanCnt == null ? "unknow" : alienNum8or12LinkmanCnt);
		return map;
	}
}
