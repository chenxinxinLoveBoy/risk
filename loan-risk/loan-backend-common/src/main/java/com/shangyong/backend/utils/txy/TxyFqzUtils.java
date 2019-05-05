package com.shangyong.backend.utils.txy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.shangyong.utils.Base64Util;

public class TxyFqzUtils {
	
	/**组装查询字符串**/
	 public static String makeQueryString(Map<String, String> args, String charset)
	    {
	        String url = "";

	        try {
				for (Map.Entry<String, String> entry : args.entrySet()){
				    url += entry.getKey() + "=" + (charset == null ? entry.getValue() : URLEncoder.encode(entry.getValue(), charset)) + "&";
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				throw new RuntimeException("腾讯云makeQueryString拼接字符串异常"+e.getMessage());
			}
	        return url.substring(0, url.length()-1);
	    }
	 /**
	  * 签名算法使用HMAC-SHA1
	  * @param key
	  * @param text
	  * @param charset
	  * @return
	  */
	 public static String hmacSHA1(String key, String text, String charset)
	    {
		 Mac mac = null;
		 byte[] b =null;
			try {
				mac = Mac.getInstance("HmacSHA1");
				mac.init(new SecretKeySpec(key.getBytes(charset), "HmacSHA1"));
				b = text.getBytes(charset);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("腾讯云签名算法加密失败"+e.getMessage());
			}
			
	        return encode(mac.doFinal(b));
	    }
	 
	  private static String encode(byte[] bstr){
	        String sp = System.getProperty("line.separator");
	        return (Base64Util.encode(bstr).replaceAll(sp, ""));
	    }
	  

}
