package com.shangyong.backend.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * 读取config.properties配置
 * 
 * @author xiajiyun
 *
 */
public class PropertiesBackendUtil {

	private static Properties properties = new Properties();
	static {
		InputStream in = PropertiesBackendUtil.class.getResourceAsStream("/zhimaKey.properties");
		try {
			properties.load(in);
		} catch (IOException e) {

		}
	}

	public static String get(String key) {
		return properties.getProperty(key).trim();
	}
}
