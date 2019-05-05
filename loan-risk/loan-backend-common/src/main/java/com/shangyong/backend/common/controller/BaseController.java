package com.shangyong.backend.common.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.impl.SysParamRedisServiceImpl;
import com.shangyong.utils.UUIDUtils;
 
/**
 * 公用方法
 * 
 * @author xiajiyun
 *
 */
public class BaseController {

	private static Logger logger = Logger.getLogger(BaseController.class);

	@Autowired
	private SysParamRedisServiceImpl sysParamRedisServiceImpl;

	private String endpoint;

	private String accessKeyId;

	private String accessKeySecret;

	private String bucketName;

	// 文件配置目录
	private String filedir;

	// OSS对象
	private OSSClient ossClient;
	
	
	
	

	/**
	 * 图片上传, 阿里云OSS储存方式
	 * 
	 * @param request
	 * @param response
	 * @param file
	 * @return 返回图片在阿里云的绝对路劲
	 * @throws Throwable
	 */
	public String uploadFiles(HttpServletRequest request, HttpServletResponse response, MultipartFile file)
			throws Throwable {
		// 获取参数值
		SysParam sysParam = sysParamRedisServiceImpl.querySysParamByParamValueRedis(Constants.ALIYUN_OSS);

		if (sysParam == null) {
			throw new Exception("参数值对象为NULL");
		} else if (StringUtils.isBlank(sysParam.getParamValueOne())) {
			throw new Exception("第一个参数值为NULL");
		} else if (StringUtils.isBlank(sysParam.getParamValueTwo())) {
			throw new Exception("第二个参数值为NULL");
		} else if (StringUtils.isBlank(sysParam.getParamValueThree())) {
			throw new Exception("第三个参数值为NULL");
		}

		endpoint = sysParam.getParamValueOne().trim();
		accessKeyId = sysParam.getParamValueTwo().trim();
		accessKeySecret = sysParam.getParamValueThree().trim();
		bucketName = sysParam.getParamValueFour().trim();
		filedir = sysParam.getParamValueFive().trim();

		ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

		String name = uploadImg2Oss(file);
		String imgUrl = getImgUrl(name);
		return imgUrl;
	}
	
	
	/**
	 * 学信网上传图片
	 * @param base64
	 * @return
	 * @throws Throwable
	 */ 
	public String uploadFilesImg(String base64)
			throws Throwable {

		String endpoint2 = "http://oss-cn-shanghai.aliyuncs.com";

		// accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
		// 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
		// 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
		String accessKeyId2 = "LTAIWVBu27n5Vw0d";
		String accessKeySecret2 = "18i7N4uSuLMlICX3Lk7r98uVOWPz4b";

		// Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
		// Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
		String bucketNameXXW = "shandai-test-oss";

		OSSClient ossClientXXW = new OSSClient(endpoint2, accessKeyId2, accessKeySecret2);
		String imgName = "";
		try { 

			// 判断Bucket是否存在
			if (ossClientXXW.doesBucketExist(bucketNameXXW)) {
				logger.info("您已经创建Bucket：" + bucketNameXXW + "。"); 
			} else {
				logger.info("您的Bucket不存在，创建Bucket：" + bucketNameXXW + "。");  
				// 创建Bucket。详细请参看“SDK手册 > Java-SDK > 管理Bucket”。
				// 链接地址是：https://help.aliyun.com/document_detail/oss/sdk/java-sdk/manage_bucket.html?spm=5176.docoss/sdk/java-sdk/init
				ossClientXXW.createBucket(bucketNameXXW);
			}

			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();

			// Base64解码
			byte[] bs = decoder.decodeBuffer(base64);
			for (int i = 0; i < bs.length; ++i) {
				if (bs[i] < 0) {// 调整异常数据
					bs[i] += 256;
				}

			}
			ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(bs);

			imgName = UUIDUtils.getUUID() + ".jpg";

			// 上传文件流
			ossClientXXW.putObject(bucketNameXXW, "IMG_XXW/" + imgName, arrayInputStream);

		} catch (Exception e) {
			logger.error("上传图片异常!!!");
		}
		// 设置URL过期时间为10年 3600l* 1000*24*365*10
		Date expiration = new Date(System.currentTimeMillis() + 3600l * 1000 * 24 * 365 * 10);
		// 生成URL
		URL url = ossClientXXW.generatePresignedUrl(bucketNameXXW, "IMG_XXW/" + imgName, expiration);
		logger.info("生成图片URL为：" + url + "。");  
		return url.toString();
	}
	 
	

	/**
	 * 上传图片
	 *
	 * @param url
	 * @throws Exception
	 */
	public void uploadImg2Oss(String url) throws Exception {
		File fileOnServer = new File(url);
		FileInputStream fin;
		try {
			fin = new FileInputStream(fileOnServer);
			String[] split = url.split("/");
			this.uploadFile2OSS(fin, split[split.length - 1]);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
			throw new Exception("图片上传失败");
		}
	}

	public String uploadImg2Oss(MultipartFile file) throws Exception {
		if (file.getSize() > 1024 * 1024 * 1024) {
			logger.error("上传图片大小不能超过10M！");
			throw new Exception("上传图片大小不能超过10M！");
		}
		String originalFilename = file.getOriginalFilename();
		String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
		Random random = new Random();
		String name = TokenManager.getUserId() + "_" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "_"
				+ random.nextInt(10000) + System.currentTimeMillis() + substring;
		try {
			InputStream inputStream = file.getInputStream();
			this.uploadFile2OSS(inputStream, name);
			return name;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("图片上传失败");
		}
	}

	/**
	 * 获得图片路径
	 *
	 * @param fileUrl
	 * @return
	 */
	public String getImgUrl(String fileUrl) {
		if (!StringUtils.isEmpty(fileUrl)) {
			String[] split = fileUrl.split("/");
			return this.getUrl(this.filedir + split[split.length - 1]);
		}
		return null;
	}

	/**
	 * 上传到OSS服务器 如果同名文件会覆盖服务器上的
	 *
	 * @param instream
	 *            文件流
	 * @param fileName
	 *            文件名称 包括后缀名
	 * @return 出错返回"" ,唯一MD5数字签名
	 */
	public String uploadFile2OSS(InputStream instream, String fileName) {
		String ret = "";
		try {
			// 创建上传Object的Metadata
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(instream.available());
			objectMetadata.setCacheControl("no-cache");
			objectMetadata.setHeader("Pragma", "no-cache");
			objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
			objectMetadata.setContentDisposition("inline;filename=" + fileName);
			// 上传文件
			PutObjectResult putResult = ossClient.putObject(bucketName, filedir + fileName, instream, objectMetadata);
			ret = putResult.getETag();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (instream != null) {
					instream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	/**
	 * Description: 判断OSS服务文件上传时文件的contentType
	 *
	 * @param FilenameExtension
	 *            文件后缀
	 * @return String
	 */
	public static String getcontentType(String FilenameExtension) {
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
		return "image/jpeg";
	}

	/**
	 * 获得url链接
	 *
	 * @param key
	 * @return
	 */
	public String getUrl(String key) {
		// 设置URL过期时间为10年 3600l* 1000*24*365*10
		Date expiration = new Date(System.currentTimeMillis() + 3600l * 1000 * 24 * 365 * 10);
		// 生成URL
		URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
		if (url != null) {
			return url.toString();
		}
		return null;
	}

	/**
	 * 销毁
	 */
	public void destory() {
		ossClient.shutdown();
	}
	
	
	
	/** 
     * 大陆号码或香港号码均可 
     */  
    public static boolean isPhoneLegal(String str)throws PatternSyntaxException {  
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);  
    }  
  
    /** 
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 
     * 此方法中前三位格式有： 
     * 13+任意数 
     * 15+除4的任意数 
     * 18+除1和4的任意数 
     * 17+除9的任意数 
     * 147 
     */  
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {  
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    }  
  
    /** 
     * 香港手机号码8位数，5|6|8|9开头+7位任意数 
     */  
    public static boolean isHKPhoneLegal(String str)throws PatternSyntaxException {  
        String regExp = "^(5|6|8|9)\\d{7}$";  
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    }  

}
