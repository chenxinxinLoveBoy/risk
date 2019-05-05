package com.shangyong.backend.dubbo;

import java.util.Map;

public interface BlacklistDubboService {

	/**
	 * 风控黑名单入库接口
	 * @param map
	 * @return
	 */
	public boolean saveEntity(Map<String, String> map);

}
