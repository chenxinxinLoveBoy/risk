package com.shangyong.common;

public enum UserState {
	NORMAL("正常", "1"), LOCKED("锁定", "2"), FREEZE("冻结", "3");
	private String state;
	private String index;

	private UserState(String state, String index) {
		this.state = state;
		this.index = index;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

}
