package com.shangyong.backend.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.utils.UUIDUtils;

@Component
public class FileUtils {

	private static Logger logger = Logger.getLogger(FileUtils.class);

	@Autowired
	private SysParamRedisService sysParamRedisService;// 参数配置实现类

	/**
	 * 上传文件至Linux
	 * 
	 * @param file
	 *            文件上传对象
	 * @param paramValue
	 *            文件上传路径参数（系统全局常量）
	 * @return 文件存储地址
	 */
	public String uploadCSVFile(MultipartFile file, String paramValue) {
		// 获取参数值
		SysParam sysParam = sysParamRedisService
				.querySysParamByParamValueRedis(paramValue);

		if (sysParam == null
				|| StringUtils.isBlank(sysParam.getParamValueOne())) {
			logger.error("sysParam为空，或者第一个参数为空！sysParam = " + sysParam);
			return null;
		}
		String savePath = sysParam.getParamValueOne();
		File fileSaveFile = new File(savePath);
		if (!fileSaveFile.exists()) {
			fileSaveFile.mkdirs();
		}
		String str = "";
		String uid = UUIDUtils.getUUID();
		File file1 = null;
		OutputStream outputStream = null;
		if (null != file) {
			str = fileSaveFile + "/" + uid + "."
					+ file.getOriginalFilename().split("\\.")[1];
			file1 = new File(str);
			try {
				outputStream = new FileOutputStream(file1);
				outputStream.write(file.getBytes());
				outputStream.flush();
				logger.info("文件上传成功------");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return str;
	}

	/**
	 * 校验文件规范
	 * 
	 * @return
	 * @throws Exception
	 */
	public String CheckFile(MultipartFile file) throws Exception {
		String fileName = file.getOriginalFilename().split("\\.")[1];
		String fileName2 = file.getOriginalFilename().split("\\.")[0];
		if (isMessyCode(fileName2)) {
			logger.error("文件名不能有乱码");
			throw new Exception("文件名不能有乱码");
		}
		if (file.getSize() > 10 * 1024 * 1024) {
			logger.error("上传文件大小不能超过10M！");
			throw new Exception("上传文件大小不能超过10M！");
		}

		if (!("csv".equals(fileName) || "CSV".equals(fileName))) {
			logger.error("上传的文件不是csv格式");
			throw new Exception("上传的文件不是csv格式");
		}

		return "";
	}

	/**
	 * 判断字符串是否是乱码
	 *
	 * @param strName
	 *            字符串
	 * @return 是否是乱码
	 */
	public boolean isMessyCode(String strName) {
		Pattern p = Pattern.compile("\\s*|t*|r*|n*");
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = ch.length;
		float count = 0;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) {
				if (!isChinese(c)) {
					count = count + 1;
				}
			}
		}
		float result = count / chLength;
		if (result > 0.4) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	/**
	 * 从Linux读取上传的CSV文件
	 * 
	 * @param path
	 *            Linux文件路径
	 * @return list 文件内容
	 */
	public List<String> resoveCSV(String path) {
		File file = new File(path);
		BufferedReader reader = null;
		List<String> list = new ArrayList<String>();
		try {
			int line = 0;// 序号(手工添加至list中)
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "utf-8"));
			String tempString = null;
			logger.info("开始读取CSV文件-----");
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				tempString = line + "," + tempString;// 添加序号
				list.add(tempString);
				line++;
				// System.err.println(tempString);
			}
			reader.close();
			logger.info("读取CSV文件完成-----");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return list;
	}
	/**
	 * 从Linux读取上传的CSV文件,以文件的形式读取
	 * 
	 * response对象
	 * @param response
	 * Linux文件路径
	 * @param path
	 * 生成csv的文件名称
	 * @param fileName
	 */
	public void readerCSV(HttpServletResponse response, String path,String fileName) {

		File outFile = new File(path);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			fis = new FileInputStream(outFile);
			bis = new BufferedInputStream(fis);
			ServletOutputStream sos = response.getOutputStream();
			bos = new BufferedOutputStream(sos);
			String contentType = "application/vnd.ms-excel";// 定义导出文件的格式的字符串
			String recommendedName = new String(fileName.getBytes(), "iso-8859-1");// 设置文件名称的编码格式
			response.setContentType(contentType);// 设置导出文件格式
			response.setHeader("Content-Type", "application/force-download");
			response.setHeader("Content-Type", "application/vnd.ms-excel");
			response.setHeader("Content-Disposition",
					"attachment; filename="+recommendedName+".csv");//

			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bos.flush();
				bos.close();
				bis.close();
				fis.close();
			} catch (IOException e) {

			}
		}

	}

}
