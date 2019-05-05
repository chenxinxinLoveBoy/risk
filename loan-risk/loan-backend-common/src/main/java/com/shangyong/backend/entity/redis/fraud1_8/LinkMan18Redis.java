package com.shangyong.backend.entity.redis.fraud1_8;

import java.util.HashMap;

/**
 * 通讯录 1.8
 */
public class LinkMan18Redis {
	
	private String alienLinkmanCnt ;
	private String alienNum8or12LinkmanCnt ;
	private String normalLinkmanCnt ;
	
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
	public String getNormalLinkmanCnt() {
		return normalLinkmanCnt;
	}
	public void setNormalLinkmanCnt(String normalLinkmanCnt) {
		this.normalLinkmanCnt = normalLinkmanCnt;
	}
	
	public HashMap<String,String> toMap(){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("alienLinkmanCnt", alienLinkmanCnt==null?"unknow":alienLinkmanCnt);
		map.put("alienNum8or12LinkmanCnt", alienNum8or12LinkmanCnt==null?"unknow":alienNum8or12LinkmanCnt);
		map.put("normalLinkmanCnt", normalLinkmanCnt==null?"unknow":normalLinkmanCnt);
		return map;
	}
	
	
}
