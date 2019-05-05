package com.shangyong.backend.service;

import java.util.Map;

public interface approveService {
	
	public Map<String,String> findMgUserMessage(String certCode, String registerPhone);

}
