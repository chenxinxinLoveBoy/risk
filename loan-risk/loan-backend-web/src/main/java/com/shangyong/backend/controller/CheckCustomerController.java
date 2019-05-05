package com.shangyong.backend.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.CuCustomerCheckApplyExtends;
import com.shangyong.backend.entity.CuCustomerCheckManage;
import com.shangyong.backend.service.checkapply.impl.CuCustomerCheckApplyExtendsImpl;
import com.shangyong.backend.service.impl.CheckCustomerServiceImpl;
import com.shangyong.backend.service.impl.CuCustomerCheckManageServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value ="/backend/checkCustomer")
public class CheckCustomerController{
	
	@Autowired
	private CheckCustomerServiceImpl checkCustomerServiceImpl;
	
	@Autowired
	private CuCustomerCheckManageServiceImpl cuCustomerCheckManageServiceImpl;
	
	@Autowired
	private CuCustomerCheckApplyExtendsImpl cuCustomerCheckApplyExtendsImpl;
	
	private static Logger logger = Logger.getLogger(CheckCustomerController.class);
	/**
	 * 数据征信测试导入
	 * @return
	 */
	@RequestMapping(value ="/upload.do",method = RequestMethod.POST )
	public void upload(HttpServletRequest request, HttpServletResponse response,
                       CuCustomerCheckManage cuCustomerCheckManage) {
		try {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multiRequest.getFile("file");
			logger.info("开始校验文件规范----------");
			checkCustomerServiceImpl.CheckFile(file);//校验文件规范
			logger.info("开始上传文件------");
			String fileVal = checkCustomerServiceImpl.uploadCSVFile(request,file);//上传文件至Linux
			cuCustomerCheckManage.setUploadAddress(fileVal);
			String codeId=checkCustomerServiceImpl.InsertCSVResult(cuCustomerCheckManage,null);//保存上传记录
//			List<String> list=null;
			try {
				logger.info("开始保存CSV文件没的数据至MySql数据测试明细表-------");
				checkCustomerServiceImpl.sendUploadQueue(fileVal, cuCustomerCheckManage.getTaskType(),codeId);
			} catch (Exception e) {
				
				logger.error(e.getMessage(),e);
				
				JSONUtils.toJSON(response, CodeUtils.FAIL, e.getMessage());
			}
			Map<String,String> map=new HashMap<String,String>();
			map.put("codeId", codeId);
			JSONUtils.toJSON(response, CodeUtils.SUCCESS,map);
		} catch (Exception e) {
			if(e.getMessage().length()>50){
				checkCustomerServiceImpl.InsertCSVResult(cuCustomerCheckManage,e.getMessage().substring(0,50));//保存上传失败记录
			}else{
				checkCustomerServiceImpl.InsertCSVResult(cuCustomerCheckManage,e.getMessage());//保存上传失败记录
			}
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	/**
	 * 查询上传记录
	 * @return
	 */
	@RequestMapping(value ="/findAllByObj.do",method = RequestMethod.POST )
	public void findAllByObj(HttpServletRequest request, HttpServletResponse response, CuCustomerCheckManage cuCustomerCheckManage) {
		try {
			if(cuCustomerCheckManage != null){
				Page<CuCustomerCheckManage> page=cuCustomerCheckManageServiceImpl.findAllByObj(cuCustomerCheckManage);
				PageInfo<CuCustomerCheckManage> pageInfo=new PageInfo<>(page);
				int count=(int)pageInfo.getTotal();
				List<CuCustomerCheckManage> list=pageInfo.getList();
				JSONUtils.toListJSON(response, list, count);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 查询数据测试明细扩展
	 * @return
	 */
	@RequestMapping(value ="/findAllExtends.do",method = RequestMethod.POST )
	public void findAllExtends(HttpServletRequest request, HttpServletResponse response, CuCustomerCheckApplyExtends cuCustomerCheckApplyExtends) {
		try {
			if(cuCustomerCheckApplyExtends != null){
				Page<CuCustomerCheckApplyExtends> page=cuCustomerCheckApplyExtendsImpl.findAllByObj(cuCustomerCheckApplyExtends);
				PageInfo<CuCustomerCheckApplyExtends> pageInfo=new PageInfo<>(page);
				int count=(int)pageInfo.getTotal();
				List<CuCustomerCheckApplyExtends> list=pageInfo.getList();
				// 页面显示'待处理'的异常信息
				for (CuCustomerCheckApplyExtends checkApplyExtends : list) {
					if(RedisUtils.hexists(Constants.REDIS_MQ_DSF_ERROR, checkApplyExtends.getCustomerCheckApplyId() + "_" + checkApplyExtends.getTaskType())){
						checkApplyExtends.setErrorInfo(RedisUtils.hget(Constants.REDIS_MQ_DSF_ERROR, checkApplyExtends.getCustomerCheckApplyId()+ "_" + checkApplyExtends.getTaskType()));
					}
				}
				
				JSONUtils.toListJSON(response, list,count);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	
	/**
	 * 查看异常详细信息, 暂时用不着，因为页面直接显示了
	 * @return
	 */
	@RequestMapping(value ="/findInfo.do", method = RequestMethod.POST)
	public void findInfo(HttpServletRequest request, HttpServletResponse response) {
		String info = request.getParameter("info");
		try {
			if(StringUtils.isNoneBlank(info)){
				// 页面显示'待处理'的异常信息
				if(RedisUtils.hexists(Constants.REDIS_MQ_DSF_ERROR, info)){
					info = RedisUtils.hget(Constants.REDIS_MQ_DSF_ERROR, info);
				}		
				JSONUtils.toJSON(response, CodeUtils.SUCCESS, info, null);
			}else{
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	
	
	
}
