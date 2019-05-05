package com.shangyong.backend.utils;

import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.utils.MD5Utils;
import com.shangyong.utils.StringUtil;

import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

/**
* @Auth: kenzhao
* @Desc: 阿里云文件服务工具类
 * 上传文件/读取文件(无授权)
* @Time: 2017/8/7 14:36
*/
@Component
public class AliyunFileUtils {

//	private static Logger logger = Logger.getLogger(AliyunFileUtils.class);
    // ken add to 2017/8/9 14:58 desc 调整调用日志
    private static com.alibaba.dubbo.common.logger.Logger loggerFile = LoggerFactory.getLogger("jsonUpload");

	/**
	 * 上传到OSS服务器 如果同名文件会覆盖服务器上的
	 * @param sysParam 阿里云服务器的配置参数
	 * @param instreamStr 文件内容，响应结果
	 * @param fileName 文件名称(不要后缀) 包括完整路径
	 * @return 出错返回"" ,唯一MD5数字签名
	 */
	public static String uploadFile2OSS(SysParam sysParam, String instreamStr, String fileDir, String fileName) {
		String ret = "";

		if(sysParam == null){
			return ret;
		}

		if(!StringUtil.checkNotNull(instreamStr)){
			return ret;
		}

		byte[] obj = new byte[0];
		try {
			obj = instreamStr.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
//			logger.error(e.getMessage(), e);
            loggerFile.error("uploadFile2OSS 转换成流对象出错", e);
		}
		InputStream instream = new ByteArrayInputStream(obj);
		// ken add to 2017/8/7 16:32 desc 为防止文件名过长采用MD5处理
		fileName = MD5Utils.md5(fileName);
		fileName = uploadFile2OSS(sysParam,instream, fileDir,fileName);
		return fileName;
	}

	/**
	 * 上传到OSS服务器 如果同名文件会覆盖服务器上的
	 * @param sysParam 阿里云服务器的配置参数
	 * @param instream 文件流
	 * @param fileName 文件名称(不要后缀) 包括完整路径
	 * @return 出错返回"" ,唯一MD5数字签名
	 */
	public static String uploadFile2OSS(SysParam sysParam, InputStream instream, String fileDir, String fileName) {
		String ret = "";

		if(sysParam == null){
			return ret;
		}
		if(fileName != null && fileName.lastIndexOf(".") < 0){
			fileName = fileName + ".json";
		}
		if(fileDir.indexOf("/") == 0){
			fileDir = fileDir.substring(1);
		}
		// 获取参数值
//		sysParamRedisServiceImpl.querySysParamByParamValueRedis(Constants.ALIYUN_OSS);
		String endpoint = sysParam.getParamValueOne().trim();
		String accessKeyId = sysParam.getParamValueTwo().trim();
		String accessKeySecret = sysParam.getParamValueThree().trim();
		String bucketName = sysParam.getParamValueFour().trim();
		long beginTime = Calendar.getInstance().getTimeInMillis();
//		logger.info("==================开始上传文件至阿里云服务器==================");
        loggerFile.info("==================开始上传文件至阿里云服务器==================");
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//		OSSClient ossClient = OssClientUtils.getOssClienSingle(sysParam);

		try {
			// 创建上传Object的Metadata
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(instream.available());
			objectMetadata.setCacheControl("no-cache");
			objectMetadata.setHeader("Pragma", "no-cache");
			objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf(".")+1)));
			objectMetadata.setContentDisposition("inline;filename=" + fileName);
			// 上传文件
			PutObjectResult putResult = ossClient.putObject(bucketName, fileDir + fileName, instream, objectMetadata);
//			ret = putResult.getETag();
			ret = fileDir + fileName;
		} catch (IOException e) {
			loggerFile.error("uploadFile2OSS 上传出错：" + e.getMessage(), e);
		} finally {
			try {
				if (instream != null) {
					instream.close();
				}
				// 关闭client
				if (ossClient != null) {
					ossClient.shutdown();
				}
			} catch (IOException e) {
//				logger.error(e.getMessage(), e);
                loggerFile.error("uploadFile2OSS 上传出错：" + e.getMessage(), e);
			}
		}

		long endTime = Calendar.getInstance().getTimeInMillis();
//		logger.info("==================上传文件至阿里云服务器结束，用时(ms)：==================" + (endTime - beginTime));
        loggerFile.info("==================上传文件至阿里云服务器结束，用时(ms)：==================" + (endTime - beginTime));
		return ret;
	}

	/**
	 * Description: 判断OSS服务文件上传时文件的contentType
	 *
	 * @param FilenameExtension
	 *            文件后缀
	 * @return String
	 */
	private static String getcontentType(String FilenameExtension) {
		if (FilenameExtension.equalsIgnoreCase("bmp")) {
			return "image/bmp";
		}
		if (FilenameExtension.equalsIgnoreCase("gif")) {
			return "image/gif";
		}
		if (FilenameExtension.equalsIgnoreCase("jpeg") || FilenameExtension.equalsIgnoreCase("jpg")
				|| FilenameExtension.equalsIgnoreCase("png")) {
			return "image/jpeg";
		}
		if (FilenameExtension.equalsIgnoreCase("html")) {
			return "text/html";
		}
		if (FilenameExtension.equalsIgnoreCase("txt")) {
			return "text/plain";
		}
		if (FilenameExtension.equalsIgnoreCase("vsd")) {
			return "application/vnd.visio";
		}
		if (FilenameExtension.equalsIgnoreCase("pptx") || FilenameExtension.equalsIgnoreCase("ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (FilenameExtension.equalsIgnoreCase("docx") || FilenameExtension.equalsIgnoreCase("doc")) {
			return "application/msword";
		}
		if (FilenameExtension.equalsIgnoreCase("xml")) {
			return "text/xml";
		}
		if (FilenameExtension.equalsIgnoreCase("json")) {
			return "text/json";
		}
		return "image/jpeg";
	}

	/**
	 * 获取Json文件内容
	 * @param filedir
	 * @param fileName
	 * @return Json格式字符串
	 * @throws OSSException
	 */
	public static String getJsonByUrl(SysParam sysParam,String filedir,String fileName) throws OSSException {
//		String url = "http://h5.xnsudai5.com/";
		long beginTime = Calendar.getInstance().getTimeInMillis();
//		logger.info("==================开始连接阿里云服务器获取文件==================" + beginTime);
		loggerFile.info("==================开始连接阿里云服务器获取文件==================" + beginTime);

		String resultStr = null;
		if(sysParam == null){
			return resultStr;
		}
		String domainName = sysParam.getParamValueOne();
		domainName = domainName + filedir + fileName;
		try {
			resultStr = RiskHttpClientUtil.doGet(domainName);
		} catch (Exception e) {
//			logger.error("获取阿里云Json文件出错：" + e.getMessage());
			loggerFile.error("获取阿里云Json文件出错：" + e.getMessage());
		}
		long endTime = Calendar.getInstance().getTimeInMillis();
//		logger.info("==================从阿里云服务器获取文件结束，用时(ms)：==================" + (endTime - beginTime));
		loggerFile.info("==================从阿里云服务器获取文件结束，用时(ms)：==================" + (endTime - beginTime));
		return resultStr;
//		System.out.println("===========================");
//		System.out.println(resultStr);
//		System.out.println("===========================");
//		String keyCode = "raw_data.members.error_msg";
//		Object str = ReadJsonUtils.getValueByJsonKey(resultStr,keyCode);
//		System.out.println("===========================\n" + str);
	}


}
