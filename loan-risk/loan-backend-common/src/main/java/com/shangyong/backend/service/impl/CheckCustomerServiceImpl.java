package com.shangyong.backend.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.MQConstants;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.dao.CuCustomerCheckApplyDao;
import com.shangyong.backend.dao.CuCustomerCheckApplyExtendsDao;
import com.shangyong.backend.dao.CuCustomerCheckManageDao;
import com.shangyong.backend.entity.CuCustomerCheckApply;
import com.shangyong.backend.entity.CuCustomerCheckApplyExtends;
import com.shangyong.backend.entity.CuCustomerCheckManage;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.CheckCustomerService;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.ReadJsonUtils;
import com.shangyong.backend.utils.RiskHttpClientUtil;
import com.shangyong.utils.UUIDUtils;
@Service
public class CheckCustomerServiceImpl implements CheckCustomerService{
	
	@Autowired
	private SysParamRedisServiceImpl sysParamRedisServiceImpl;
	
	@Autowired
	private CuCustomerCheckManageServiceImpl cuCustomerCheckManageServiceImpl;
	
	@Autowired
	private CuCustomerCheckApplyDao cuCustomerCheckApplyDao;
	
	@Autowired
	CuCustomerCheckApplyExtendsDao cuCustomerCheckApplyExtendsDao;
	
	@Autowired
	private SysParamRedisService sysParamRedisService;// 参数配置实现类
	
	private String endpoint;

	private String accessKeyId;

	private String accessKeySecret;

	private String bucketName;

	// 文件配置目录
	private String filedir;

	// OSS对象
	private OSSClient ossClient;
	
	
//	private String codeId;//批次号
	
//	private String applyId;//数据测试单号
	
	private static Logger logger = Logger.getLogger(CheckCustomerServiceImpl.class);
	
	/**
	 * 上传文件至Linux
	 */
	@Override
	public String uploadCSVFile(HttpServletRequest request,MultipartFile file) {
		
		
		//String filePathDir = Constants.FILEPATH + "/" + new SimpleDateFormat("yyyMMddHHmmssSSS").format(new Date());
		//String fileRealPathDir = request.getSession().getServletContext().getRealPath(filePathDir); 
		//String temp2 = request.getSession().getServletContext().getRealPath("/"); 
		//String fileRealPathDirTemp="G:/";
		 // 获取参数值
        SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.UPLOAD_PATH);
        if(sysParam == null || StringUtils.isBlank(sysParam.getParamValueOne())){
        	logger.error("sysParam为空，或者第一个参数为空！sysParam = " + sysParam);
        	return null;
        }
        
		String savePath=sysParam.getParamValueOne();
		File fileSaveFile = new File(savePath);
		if (!fileSaveFile.exists()){
				fileSaveFile.mkdirs();
		}
		String str = "";
		//String fileVal="";
		String uid=UUIDUtils.getUUID();
		File file1=null;
		OutputStream outputStream = null;
		if (null != file) {
			//str = "/" + filePathDir + "/" + uid + "." + file.getOriginalFilename().split("\\.")[1];
			str = fileSaveFile + "/" + uid + "." + file.getOriginalFilename().split("\\.")[1];
			//fileVal=request.getSession().getServletContext().getRealPath("/")+str;
			//file1 = new File(fileVal);
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
		//return fileVal;
	}
	
	/**
	 * 读取CSV文件数据
	 */
	@Override
	public List<String> readCSVFile() {
		
		return null;
	}

	
	/**
	 * 文件上传, 阿里云OSS储存方式
	 * 
	 * @param request
	 * @param response
	 * @param file
	 * @return 返回文件在阿里云的绝对路劲
	 * @throws Throwable
	 */
	public String uploadFiles(HttpServletRequest request, HttpServletResponse response, MultipartFile file)
			throws Throwable {
		// 获取参数值
		SysParam sysParam = sysParamRedisServiceImpl.querySysParamByParamValueRedis(Constants.ALIYUN_OSS_CSV);
		
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
		String csvUrl = getCsvUrl(name);
		return csvUrl;
	}
	

	/**
	 * 上传文件
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
			throw new Exception("文件上传失败");
		}
	}
	
	public String uploadImg2Oss(MultipartFile file) throws Exception {
		if (file.getSize() > 1024 * 1024 * 1024) {
			logger.error("上传文件大小不能超过10M！");
			throw new Exception("上传文件大小不能超过10M！");
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
			throw new Exception("文件上传失败");
		}
	}
	
	/**
	 * 获得文件路径
	 *
	 * @param fileUrl
	 * @return
	 */
	public String getCsvUrl(String fileUrl) {
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
		if (FilenameExtension.equalsIgnoreCase("txt") || FilenameExtension.equalsIgnoreCase("csv")) {
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
		return "text/plain";
	}
	
	/**
	 * 保存上传记录
	 * @param cuCustomerCheckManage
	 * @param status
	 * @return
	 */
	@Transactional
	public String InsertCSVResult(CuCustomerCheckManage cuCustomerCheckManage,String status){
		String CheckCodeId=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());//批次号
		UUserBo userBo= TokenManager.getUser();
		String uname=userBo.getNickName();
		cuCustomerCheckManage.setCustomerCheckCodeId(CheckCodeId);
		cuCustomerCheckManage.setUploadTime(DateUtils.getDate(new Date()));
		cuCustomerCheckManage.setUploadMan(uname);
		if(status == null){
			status="文件上传成功";
			cuCustomerCheckManage.setUploadSuccess(status);
		}else{
			cuCustomerCheckManage.setUploadFailure(status);
		}
		int temp=cuCustomerCheckManageServiceImpl.saveEntity(cuCustomerCheckManage);
		if(temp>0){
			logger.info("文件上传记录已保存到MySql上传记录表-----");
		}
		
		return CheckCodeId;
	}
	
	/**
	 * 从Linux读取上传的CSV文件
	 * @param request
	 * @param path Linux文件路径
	 * @return
	 */
	public List<String> resoveCSV(String path){
		File file = new File(path);
        BufferedReader reader = null;
        List<String> list=new ArrayList<String>();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"utf-8"));
            String tempString = null;
           // int line = 0;
            logger.info("开始读取CSV文件-----");
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                //tempString=line+","+tempString;
                list.add(tempString);
               // line++;
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
		  * 获取行数
		  * @return
		  */
		 public int getRowNum(List<String> list) {
		         return list.size();
		 }
		 /**
		  * 获取列数
		  * @return
		  */
		 public int getColNum(List<String> list) {
	         if (!list.toString().equals("[]")) {
	             if (list.get(0).toString().contains(",")) {// csv为逗号分隔文件
	                 return list.get(0).toString().split(",").length;
	             } else if (list.get(0).toString().trim().length() != 0) {
	                 return 1;
	             } else {
	                 return 0;
	             }
	         } else {
	             return 0;
	         }
		 }
	 /**
	  * 获取指定行
	  * @param index
	  * @return
	  */
	 public String getRow(int index,List<String> list) {
         if (list.size() != 0) {
             return (String) list.get(index);
         } else {
             return null;
         }
	 }
	 /**
	  * 获取指定列
	  * @param index
	  * @return
	  */
	 @SuppressWarnings("rawtypes")
	 public String getCol(int index,List<String> list) {
         if (this.getColNum(list) == 0) {
                 return null;
         }
         StringBuffer sb = new StringBuffer();
         String tmp = null;
         int colnum = this.getColNum(list);
         if (colnum > 1) {
             for (String it : list) {
                 tmp = it.toString();
                 if(tmp.split(",").length>4){
                	 sb = sb.append(tmp.split(",")[index] + ",");
                        	 
                 }
             }
         } else {
             for (Iterator it = list.iterator(); it.hasNext();) {
                     tmp = it.next().toString();
                     sb = sb.append(tmp + ",");
             }
         }
         String str = new String(sb.toString());
         str = str.substring(0, str.length() - 1);
         return str;
	 }
	 /**
	  * 获取某个单元格
	  * @param row
	  * @param col
	  * @return
	  */
	 public String getString(int row, int col,List<String> list) {
         String temp = null;
         int colnum = this.getColNum(list);
         if (colnum > 1) {
                 temp = list.get(row).toString().split(",")[col];
         } else if(colnum == 1){
                 temp = list.get(row).toString();
         } else {
                 temp = null;
         }
         return temp;
	 }
	 
	 /**
	  * 将CSV文件内的内容插入到MySql
	  * @return
	  */
	 @Transactional
	 public String insertToCheckApply(String data, Map<String, String> msgMap){
		 CuCustomerCheckApply cuCustomerCheckApply=new CuCustomerCheckApply();
		 cuCustomerCheckApply.setCustomerCheckApplyId(UUIDUtils.getUUID());
		 cuCustomerCheckApply.setCustomerCheckCodeId(msgMap.get("codeId"));//批次号
		 cuCustomerCheckApply.setName(data.split(",")[1]);//姓名
		 cuCustomerCheckApply.setPhoneNum(data.split(",")[2]);//手机号
		 cuCustomerCheckApply.setCertCode(data.split(",")[3]);//身份证号
		 cuCustomerCheckApply.setCreateTime(DateUtils.getDate(new Date()));//创建时间
		 int temp=cuCustomerCheckApplyDao.saveEntity(cuCustomerCheckApply);
		 return temp > 0 ? cuCustomerCheckApply.getCustomerCheckApplyId() : "";
	 }
	 
	 /**
	  * 插入数据测试扩展表
	  * @param taskType
	  * @return
	  */
	 @Transactional
	 public boolean insertToExtends(String taskType, String customerCheckapplyId){
		 CuCustomerCheckApplyExtends CuCustomerCheckApplyExtends=new CuCustomerCheckApplyExtends();
		 CuCustomerCheckApplyExtends.setCustomerCheckApplyId(customerCheckapplyId);
		 CuCustomerCheckApplyExtends.setTaskType(taskType);
		 CuCustomerCheckApplyExtends.setCreateTime(DateUtils.getDate(new Date()));
		 CuCustomerCheckApplyExtends.setApplyState(1);
		 return cuCustomerCheckApplyExtendsDao.saveEntity(CuCustomerCheckApplyExtends);
	 }
	 /**
	  * 循环保存所有数据并放入队列
	  * @param list
	  * @return successCount 保存成功的数目
	  * @throws Exception
	  */
	 public int insertToCheckApplyAll(List<String> list,Map<String, String> msgMap,int resend) throws Exception {
		 int successCount=0;
		 int applyCount=0;
		 for (int i=(resend+1); i < list.size(); i++) {
			String data=this.getRow(i, list);
			if(data.split(",").length == 4){
				String customerCheckapplyId=insertToCheckApply(data, msgMap);
				if(StringUtils.isNotBlank(customerCheckapplyId)){//保存至MySql后放入队列
					applyCount++;
					String[] type=msgMap.get("taskTypeArray").split(",");
					for (int j = 0; j < type.length; j++) {
						
						if(StringUtils.isNotBlank(type[j])){
							//logger.info("开始插入数据扩展表-----taskType="+type[j]);
							boolean temp=insertToExtends(type[j], customerCheckapplyId);//插入到数据扩展表
							//logger.info("插入数据扩展表完成-----");
							if(temp){
								successCount++;
								//logger.info("开始发送队列-----taskType="+type[j]);
								sendQueue(data,type[j], customerCheckapplyId);//放入队列
								//logger.info("发送队列完成-----");
							}else{
								logger.error("插入扩展信息表失败，customerCheckapplyId = " + customerCheckapplyId);
							}
						}
					}
				}
			}else{
				//throw new Exception("第"+data.split(",")[0]+"条数据格式不正确！|"+data.split(",")[0]);
				logger.info("第"+data.split(",")[0]+"条数据格式不正确！");
			}
		}
		 logger.info("插入"+applyCount+"条数据至数据测试明细表");
		 return successCount;
	 }
	 
	 /**
	  * 发送队列（发送至外部）
	  * @param data
	  * @param taskType 
	  * @param customerCheckapplyId 主键Id
	  */
	 private void sendQueue(String data,String taskType, String customerCheckapplyId){
		 	SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.TEMQ_ANTIFRAUD_CODE);
			String url = sysParam.getParamValueOne(); //请求url
			Map<String,Object> param = new HashMap<String,Object>();
			String[] data2=data.split(",");
			param.put("customerCheckApplyId", customerCheckapplyId);// cu_customer_check_apply表主键id
			param.put("taskType", taskType);// 征信机构 （ 05001- 聚信立蜜罐  07001-91征信 08001-白骑士欺诈 09001-宜信  11001-葫芦索伦  12001-小视科技 银行  12002-小视科技 网贷 40004- 芝麻信用行业关注名单）
			param.put("name", data2[1]);// 身份证名称
			param.put("phoneNum", data2[2]);// 手机号
			param.put("certCode", data2[3]);// 身份证号
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("exchangeName", MQConstants.MQ_CHECKAPPLY_EXCHANG);// 交换机
			map.put("messageType", MQConstants.MQ_CHECKAPPLY_ROUTINGKEY);// routingKey
			map.put("messageBody", ReadJsonUtils.toJson(param));
			RiskHttpClientUtil.doPostJson(url, ReadJsonUtils.toJson(map));
		}
	 
	 
	 
	 /**
	  * 发送队列（自己推送用）
	  * @param uploadAddress 已上传到服务的文件绝对路径
	  * @param  taskTypeArray 页面选择的复选框类型
	  */
	 public void sendUploadQueue(String uploadAddress, String taskTypeArray,String codeId){
		 	SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.TEMQ_ANTIFRAUD_CODE);
			String url = sysParam.getParamValueOne(); //请求url
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("taskTypeArray", taskTypeArray);// 页面选择的复选框类型
			param.put("uploadAddress", uploadAddress);// 已上传到服务的文件绝对路径
			param.put("codeId", codeId);//上传记录的批次号
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("exchangeName", MQConstants.MQ_CHECKAPPLY_EXCHANG);// 交换机
			map.put("messageType", MQConstants.MQ_CHECKAPPLY_REVERT_ROUTINGKEY + MQConstants.CHECKAPPLY_UPLOAD);// routingKey, 这里指定发送到文件上传的队列
			map.put("messageBody", ReadJsonUtils.toJson(param));
			RiskHttpClientUtil.doPostJson(url, ReadJsonUtils.toJson(map));
		}
	 
	 
	 
	 /**
	  * 校验文件规范
	  * @return
	 * @throws Exception 
	  */
	public String CheckFile(MultipartFile file) throws Exception{
		String fileName=file.getOriginalFilename().split("\\.")[1];
		String fileName2=file.getOriginalFilename().split("\\.")[0];
		if(isMessyCode(fileName2)){
			logger.error("文件名不能有乱码");
			throw new Exception("文件名不能有乱码");
		}
		if(!("csv".equals(fileName) || "CSV".equals(fileName))){
			logger.error("上传的文件不是csv格式");
			throw new Exception("上传的文件不是csv格式");
		}
		if (file.getSize() > 10 * 1024 * 1024) {
			logger.error("上传文件大小不能超过10M！");
			throw new Exception("上传文件大小不能超过10M！");
		}
		
		return "";
	}
	 
	 /**
	     * 判断字符串是否是乱码
	     *
	     * @param strName 字符串
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
	   
}
