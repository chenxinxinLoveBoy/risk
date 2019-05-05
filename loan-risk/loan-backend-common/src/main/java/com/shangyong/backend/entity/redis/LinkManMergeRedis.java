package com.shangyong.backend.entity.redis;

import java.util.HashMap;

/**
 * 通讯录 总的
 */
public class LinkManMergeRedis {
	
	private String alienLinkmanCnt ;
	private String alienNum8or12LinkmanCnt ;
	private String normalLinkmanCnt ;

	private String linkmanCnt;
	
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


	public String getLinkmanCnt() {
		return linkmanCnt;
	}

	public void setLinkmanCnt(String linkmanCnt) {
		this.linkmanCnt = linkmanCnt;
	}
}
