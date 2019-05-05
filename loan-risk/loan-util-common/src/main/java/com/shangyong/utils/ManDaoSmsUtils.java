package com.shangyong.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ManDaoSmsUtils {

	private static String smsServiceURL = PropertiesUtil.get("smsServiceURL");
	private static String smsSn = PropertiesUtil.get("smsSn");
	private static String smsPassword = PropertiesUtil.get("smsPassword");
//	private static String audioServiceURL = PropertiesUtil.get("audioServiceURL");
//	private static String audioSn = PropertiesUtil.get("audioSn");
//	private static String audioPassword = PropertiesUtil.get("audioPassword");
	private static String ext = PropertiesUtil.get("ext");
	public static String getMD5(String sourceStr) throws UnsupportedEncodingException {
		String resultStr = "";
		try {
			byte[] temp = sourceStr.getBytes();
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(temp);
			byte[] b = md5.digest();
			for (int i = 0; i < b.length; i++) {
				char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
						'9', 'A', 'B', 'C', 'D', 'E', 'F' };
				char[] ob = new char[2];
				ob[0] = digit[(b[i] >>> 4) & 0X0F];
				ob[1] = digit[b[i] & 0X0F];
				resultStr += new String(ob);
			}
			return resultStr;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getSms(String mobile, String content) throws IOException {
		String result = "";
		String soapAction = "http://entinfo.cn/mdsmssend";
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		xml += "<soap:Body>";
		xml += "<mdsmssend  xmlns=\"http://entinfo.cn/\">";
		xml += "<sn>" + smsSn + "</sn>";
		xml += "<pwd>" + getMD5(smsSn + smsPassword) + "</pwd>";
		xml += "<mobile>" + mobile + "</mobile>";
		xml += "<content>" + content + "</content>";
		xml += "<ext>" + ext + "</ext>";
		xml += "</mdsmssend>";
		xml += "</soap:Body>";
		xml += "</soap:Envelope>";
		ByteArrayOutputStream bout = null;
		OutputStream out = null;
		InputStreamReader isr = null;
		BufferedReader in = null;
		URL url;
		try {
			url = new URL(smsServiceURL);
			URLConnection connection = url.openConnection();
			HttpURLConnection httpconn = (HttpURLConnection) connection;
			bout = new ByteArrayOutputStream();
			bout.write(xml.getBytes());
			byte[] b = bout.toByteArray();
			httpconn.setRequestProperty("Content-Length", String
					.valueOf(b.length));
			httpconn.setRequestProperty("Content-Type",
					"text/xml; charset=gb2312");
			httpconn.setRequestProperty("SOAPAction", soapAction);
			httpconn.setRequestMethod("POST");
			httpconn.setDoInput(true);
			httpconn.setDoOutput(true);
			out = httpconn.getOutputStream();
			out.write(b);
			out.close();
			isr = new InputStreamReader(httpconn
					.getInputStream());
			in = new BufferedReader(isr);
			String inputLine;
			while (null != (inputLine = in.readLine())) {
				Pattern pattern = Pattern.compile("<mdsmssendResult>(.*)</mdsmssendResult>");
				Matcher matcher = pattern.matcher(inputLine);
				while (matcher.find()) {
					result = matcher.group(1);
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}finally {
			if (bout!=null) {
				bout.close();
			}
			if(out!=null){
				out.close();
			}
			if(isr!=null){
				isr.close();
			}
			if(in!=null){
				in.close();
			}
		}
	}
/*
	public static String getAudio (String mobile,String content) throws UnsupportedEncodingException
	{
		String result = "";
		String soapAction = "http://entinfo.cn/AudioSend";
		
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml += "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
		xml += "<soap12:Body>";
		xml += "<AudioSend xmlns=\"http://entinfo.cn/\">";
		xml += "<sn>" + audioSn + "</sn>";
		xml += "<pwd>" + getMD5(audioSn + audioPassword) + "</pwd>";	
		xml += "<mobile>" + mobile + "</mobile>";	
		xml += "<content>" + content + "</content>";
		xml += "<ext>" + ext + "</ext>";
		xml += "</AudioSend>";
		xml += "</soap12:Body>";
		xml += "</soap12:Envelope>";
		
		URL url;
		try {
			url = new URL(audioServiceURL);
			URLConnection connection = url.openConnection();
			HttpURLConnection httpconn = (HttpURLConnection) connection;
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			bout.write(xml.getBytes("gbk"));
			byte[] b = bout.toByteArray();
			httpconn.setRequestProperty("Content-Length", String
					.valueOf(b.length));
			httpconn.setRequestProperty("Content-Type",
					"text/xml; charset=gb2312");
			httpconn.setRequestProperty("SOAPAction", soapAction);
			httpconn.setRequestMethod("POST");
			httpconn.setDoInput(true);
			httpconn.setDoOutput(true);

			OutputStream out = httpconn.getOutputStream();
			out.write(b);
			out.close();

			InputStreamReader isr = new InputStreamReader(httpconn
					.getInputStream());
			BufferedReader in = new BufferedReader(isr);
			String inputLine;
			while (null != (inputLine = in.readLine())) {
				Pattern pattern = Pattern.compile("<AudioSendResult>(.*)</AudioSendResult>");
				Matcher matcher = pattern.matcher(inputLine);
				while (matcher.find()) {
					result = matcher.group(1);
				}
			}
			in.close();
			isr.close();
			bout.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	*/
	public static void main(String[] args) throws Exception {
		//短信发送
		long start = System.currentTimeMillis();
        String str = "18221584826";
        String [] phoneNums = str.split(",");
        for(String phoneNum:phoneNums)
        {
        	int rand = (int)((Math.random() * 9 + 1) * 1000);
        	String content   =   java.net.URLEncoder.encode("【小牛闪贷】您的验证码是:"+rand+",请不要把验证码泄露给其他人",  "utf-8"); 
        	String result_mt = ManDaoSmsUtils.getSms(phoneNum, content);
        	//String result_mt = ManDaoSmsUtils.getAudio(phoneNum, content);		
     		System.out.println(result_mt);
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
		/*String   content   =   java.net.URLEncoder.encode("您的验证码是:888888,请不要把验证码泄露给其他人【小牛智投】",  "utf-8");  
		String result_mt = ManDaoSmsUtils.getAudio("15821463250", content);		
		System.out.println(result_mt);*/
	}
}
