package com.shangyong.backend.controller;

import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.controller.BaseController;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.impl.UUserServiceImpl;
import com.shangyong.backend.utils.*;
import com.shangyong.utils.RedisUtils;
import com.shangyong.utils.SpringUtils;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * 后台用户Controller
 * @author xiajiyun
 *
 */
@Controller
@RequestMapping(value = "/backend/uuser")
public class UUserController extends BaseController {
	
	private static Logger logger= Logger.getLogger(UUserController.class);
	
	@Autowired
	private UUserServiceImpl uUserServiceImpl;  // 用戶service
	
	@Autowired
	private SysParamRedisService sysParamRedisService;// 参数配置实现类
	
//	@Autowired
//	private  UserRoleServiceImpl userRoleServiceImpl;// 用戶角色 
	
	
	/**
	 * 发送漫道短信
	 * @param userName 账号
	 * @param mobile 要发送的手机号
	 * @param typeContent 密码/手机号
	 */
	public void manDaoSms(String userName,String mobile, String monileContent, String passwordContent){
		try {
			// 手机和密码
			if(StringUtils.isNotBlank(monileContent) && StringUtils.isNotBlank(passwordContent)){
				logger.info("手机和密码都被修改，发信信息！");
				String content   =   java.net.URLEncoder.encode("【小牛洪澄】您的账号" + userName + "的手机号和密码"+ "已被修改，如非本人操作，请及时联系管理员。",  "utf-8"); 
				//ManDaoSmsUtils.getSms(mobile, content);
				return;
			// 手机	
			}else if(StringUtils.isNotBlank(monileContent)){
				logger.info("手机被修改，发信信息！");
				String content   =   java.net.URLEncoder.encode("【小牛洪澄】您的账号" + userName + "的手机号" + "已被修改，如非本人操作，请及时联系管理员。",  "utf-8"); 
				//ManDaoSmsUtils.getSms(mobile, content);
				return;
			// 密码	
			}else if(StringUtils.isNotBlank(passwordContent)){
				logger.info("密码被修改，发信信息！");
				String content   =   java.net.URLEncoder.encode("【小牛洪澄】您的账号" + userName + "的密码" + "已被修改，如非本人操作，请及时联系管理员。",  "utf-8"); 
				//ManDaoSmsUtils.getSms(mobile, content);
				return;
			}
			
		} catch (Exception e) {
			logger.error(UUserController.class + " ,调用方法manDaoSms(): 短信发送异常！");
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 获取重置密码或登陆配置的配置参数值
	 * @return
	 * @throws Throwable
	 */
	public SysParam getSysParam(final String code) throws Throwable{
		// 获取参数值
		SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(code);

		if (sysParam == null) {
			logger.error(UUserController.class);
			logger.error("sysParam参数值对象为NULL");
		} else if (StringUtils.isBlank(sysParam.getParamValueOne())) {
			logger.error(UUserController.class);
			logger.error("第一个参数值[sysParam.getParamValueOne()]为空或NULL");
		}	
		// 登陆配置有2个参数
		if(code.equals("LOGIN_COUNT_NUMBERS")){
			if (StringUtils.isBlank(sysParam.getParamValueTwo())) {
				logger.error(LoginController.class);
				logger.error("第二个参数值[sysParam.getParamValueTwo()]为空或NULL");
			}	
		}		
		return sysParam;
	}
	
	/**
	 * 解除冻结帐号
	 * @param request
	 * @param response
	 * @param user
	 */
	@RequestMapping(value = "/updateFreeze.do")
	public void updateFreeze(HttpServletRequest request, HttpServletResponse response, UUserBo user){
		logger.info("进入【解除冻结帐号】方法,updateFreeze()");
		try {
			if(user == null || user.getId() == null){
				logger.info("user对象或user.getId()为空！");
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
				return ;
			}
			
			if(StringUtils.isBlank(user.getUserName())){
				logger.info("user.getUserName()为空！");
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
				return ;
			}
			
			boolean flag = false;
			// 是否有冻结的用户
			if(RedisUtils.exists(RedisCons.LOGIN_FLAG_USER_CODE + user.getUserName())){
				// 删除redis的冻结用户的key
				RedisUtils.del(RedisCons.LOGIN_FLAG_USER_CODE + user.getUserName());
				flag = true;
			}
			
//			boolean flag = uUserServiceImpl.updateIsFreeze(user.getUserName(), 0);
			if(flag){
				JSONUtils.toJSON(response, CodeUtils.SUCCESS);
				return ;
			}else{
				JSONUtils.toJSON(response, CodeUtils.FAIL);
				return ;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	
	/**
	 * 禁用帐号
	 * @param request
	 * @param response
	 * @param user
	 */
	@RequestMapping(value = "/updateDisable.do")
	public void updateDisable(HttpServletRequest request, HttpServletResponse response, UUserBo user){
		logger.info("进入【禁用帐号】方法,updateDisable()");
		try {
			if(user == null || user.getId() == null){
				logger.info("user对象或user.getId()为空！");
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
				return ;
			}
			
			boolean flag = uUserServiceImpl.disable(user);
			if(flag){
				JSONUtils.toJSON(response, CodeUtils.SUCCESS);
				return ;
			}else{
				JSONUtils.toJSON(response, CodeUtils.FAIL);
				return ;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
		
	}
	
	/**
	 * 重置密码
	 * @param request
	 * @param response
	 *  
	 */
	@RequestMapping(value = "/rest.do", method = RequestMethod.POST)
	public void rest(HttpServletRequest request, HttpServletResponse response, UUserBo user){
		logger.info("进入【重置密码】方法,rest()");
		try {
			
			if(user == null || user.getId() == null){
				logger.info("user对象或者user.getId()为空！");
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
				return ;
			}
			// 获取重置密码的配置参数值
			SysParam sysParam = getSysParam(Constants.REST_CODE);
			
			user.setPassword(MyDES.encryptBasedDes(sysParam.getParamValueOne()));
			boolean flag = uUserServiceImpl.rest(user);
			
			if(flag){
				// 查询账号存在表里的信息
				UUserBo userBo2 = uUserServiceImpl.getObjectById(user);
				if(userBo2 != null && StringUtils.isNotBlank(userBo2.getMobile())){
					manDaoSms(userBo2.getUserName(), userBo2.getMobile(), "", "密码");
				}
				JSONUtils.toJSON(response, CodeUtils.REST_SUCCESS);
			}else{
				JSONUtils.toJSON(response, CodeUtils.REST_FALL);
			}
			
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	
	
	/**
	 * 查询所有
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/listAll.do", method = RequestMethod.POST)
	public void listAll(HttpServletRequest request, HttpServletResponse response, UUserBo uUserBo){
		logger.info("进入【查询所有】方法,listAll()");
		try {
			
			// 查询所有, 初始化加载
			if(uUserBo != null && uUserBo.getIsFreeze() == null){
				int count=uUserServiceImpl.listAllCount(uUserBo);
				List<UUserBo> list = uUserServiceImpl.listAll(uUserBo);
				for (UUserBo uUserBo2 : list) {
					// 是否有冻结的用户
					if(RedisUtils.exists(RedisCons.LOGIN_FLAG_USER_CODE + uUserBo2.getUserName())){
						// 获取登陆参数配置
						SysParam sysParam = getSysParam(Constants.LOGIN_COUNT_NUMBERS);
						// 获取存在redis的失败用户的登录失败次数
						Integer flagNumber = Integer.parseInt(RedisUtils.get(RedisCons.LOGIN_FLAG_USER_CODE + uUserBo2.getUserName()));
						if(flagNumber >= Integer.parseInt(sysParam.getParamValueTwo())){
							// 标识为已冻结
							uUserBo2.setIsFreeze(1);
						}
						
					}
				}
				JSONUtils.toListJSON(response, list, count);
				return;
			}
			
			// 页面查询条件：是否冻结，因为走的是redis，跟数据库表字段没关系
			if(uUserBo != null && uUserBo.getIsFreeze() != null ){
				// 已冻结的用户
				if(uUserBo.getIsFreeze() == 1){
					uUserBo.setIsLimit(0);// 不分页
					List<UUserBo> list = uUserServiceImpl.listAll(uUserBo);
					
					// 统计
					int num = 0;
					// 页面显示的用户
					List<UUserBo> listView = new ArrayList<UUserBo>();
					
					for (UUserBo uUserBo2 : list) {
						// 是否有冻结的用户
						if(RedisUtils.exists(RedisCons.LOGIN_FLAG_USER_CODE + uUserBo2.getUserName())){
							// 获取登陆参数配置
							SysParam sysParam = getSysParam(Constants.LOGIN_COUNT_NUMBERS);
							// 获取存在redis的失败用户的登录失败次数
							Integer flagNumber = Integer.parseInt(RedisUtils.get(RedisCons.LOGIN_FLAG_USER_CODE + uUserBo2.getUserName()));
							if(flagNumber >= Integer.parseInt(sysParam.getParamValueTwo())){
								// 标识为已冻结
								uUserBo2.setIsFreeze(1);
								listView.add(uUserBo2); 
								++num;
							}
							
						}
					}
					JSONUtils.toListJSON(response, listView, num);
					return;
				}
			}
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		} catch (Throwable e) {
			logger.error("获取登陆参数配置异常！");
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	/**
	 * 根据id获取对象信息
	 * @param request
	 * @param response
	 * @param uUserBo
	 */
	@RequestMapping(value = "/getObjectById.do", method = RequestMethod.POST)
	public void getObjectById(HttpServletRequest request, HttpServletResponse response, UUserBo uUserBo){
		logger.info("进入【根据id获取对象信息】方法,getObjectById()");
		try {
			UUserBo userBo= uUserServiceImpl.getObjectById(uUserBo);// 查詢用戶信息表
			
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			LinkedHashMap<String, Object> map1 = new LinkedHashMap<String, Object>();
			map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
			map.put(Constants.MESSAGE, CodeUtils.SUCCESSNEWS.getMessage());
			map1.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());
//			userBo.setPassword(MyDES.decryptBasedDes(userBo.getPassword()));//解密
			map1.put("userObject",userBo); // 传入对象
			map.put(Constants.DATA, map1);
			SpringUtils.renderJson(response,map);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	
	 
	/**
	 * 保存/修改
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "save.do", method = RequestMethod.POST)
	public void save(HttpServletRequest request, HttpServletResponse response, UUserBo uUserBo){
		logger.info("进入【保存/修改】方法,save()");
		try {
			
			// 判断手机号
			if(!isPhoneLegal(uUserBo.getMobile())){
				logger.info("非有效的11位手机号！");
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PHONE_MISS);
				return ;
			}
			
			if(uUserBo != null && uUserBo.getId()!= null){// 修改
				// 是否前端base64转码过，'个人信息' 转码过
				String isBase64code = request.getParameter("base64codes");
				if(StringUtils.isNotBlank(uUserBo.getPassword()) && "1".equals(isBase64code)){
					String Base64codePassword = new String(Base64Encode.decode(uUserBo.getPassword()));
					uUserBo.setPassword(Base64codePassword);
				}
				// 查询账号存在表里的信息
				UUserBo userBo2 = uUserServiceImpl.getObjectById(uUserBo);
				//修改信息
				boolean flag = uUserServiceImpl.update(uUserBo);
				
				if(flag){
					
					String monileContent = "";// 手机
					String passwordContent = ""; // 密码
					if(userBo2 != null && StringUtils.isNotBlank(userBo2.getMobile())){
						if(!userBo2.getMobile().equals(uUserBo.getMobile())){
							monileContent = "手机号";
						}
						
						// 修改密码
						if(!StringUtils.isBlank(uUserBo.getPassword())){
							passwordContent = "密码";
						}
						
						manDaoSms(userBo2.getUserName(), userBo2.getMobile(), monileContent, passwordContent);
					}
					
					JSONUtils.toJSON(response, CodeUtils.UPDATE_SUCCESS);
					return;
				}else{
					JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
					return;
				}
			}else{// 新增
				
				//验证登录帐号，不能重复
				int countUserName = uUserServiceImpl.getCountUserName(uUserBo);
				if(countUserName > 0){// 已存在
					JSONUtils.toJSON(response, CodeUtils.NAME_FAIL);
					return;
				}
				
				// 获取重置密码的配置参数值
				SysParam sysParam = getSysParam(Constants.REST_CODE);
				uUserBo.setPassword(sysParam.getParamValueOne());
				
				boolean flag = uUserServiceImpl.save(uUserBo);
				if(flag){
					JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
					return;
				}else{
					JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
					return ;
				}
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
		
	}
	
	
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @param uUserBo
	 */
	@RequestMapping(value = "delete.do", method = RequestMethod.POST)
	public void delete(HttpServletRequest request, HttpServletResponse response, UUserBo uUserBo){
		logger.info("进入【删除】方法,delete()");
		try {
			boolean flag = uUserServiceImpl.delete(uUserBo);
			if(flag){
				JSONUtils.toJSON(response, CodeUtils.DELETE_SUCCESS);
				return ;
			}else{
				JSONUtils.toJSON(response, CodeUtils.DELETE_FAIL);
				return ;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	
	/**
	 * 多选删除
	 * @param request
	 * @param response
	 * @param uUserBo
	 */
	@RequestMapping(value = "deleteAll.do", method = RequestMethod.POST)
	public void deleteAll(HttpServletRequest request, HttpServletResponse response, UUserBo uUserBo){
		logger.info("进入【多选删除】方法,deleteAll()");
		try {
			boolean flag = uUserServiceImpl.deleteAll(uUserBo);
			if(flag){
				JSONUtils.toJSON(response, CodeUtils.DELETE_SUCCESS);
				return ;
			}else{
				JSONUtils.toJSON(response, CodeUtils.DELETE_FAIL);
				return ;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	
	
	/**
	 * 获取当前登录帐号的昵称和头像
	 * @param request
	 * @param response
	 * @param uUserBo
	 */
	@RequestMapping(value = "/getUser.do", method = RequestMethod.POST)
	public void getUser(HttpServletRequest request, HttpServletResponse response, UUserBo uUserBo){
		logger.info("进入【获取当前登录帐号的昵称和头像】方法,getUser()");
		try {
			UUserBo user= TokenManager.getUser();// 直接从token取
			
			UUserBo userBo= uUserServiceImpl.getObjectById(user);// 查詢用戶信息表
			
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			LinkedHashMap<String, Object> map1 = new LinkedHashMap<String, Object>();
			map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
			map.put(Constants.MESSAGE, CodeUtils.SUCCESSNEWS.getMessage());
			map1.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());
			
			map1.put("user",userBo); // 传入对象
			map.put(Constants.DATA, map1);
			
//			map.put("user",userBo); // 传入对象
//			map.put(Constants.DATA, map);
			SpringUtils.renderJson(response,map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	
	/**
	 * 文件上传
	 * @param request
	 * @param response
	 * @param uUserBo
	 */
	@RequestMapping(value = "upload.do", method = RequestMethod.POST)
	public void upload(HttpServletRequest request, HttpServletResponse response, UUserBo uUserBo){
		logger.info("进入【文件上传】方法,upload()");
		try {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multiRequest.getFile("file");

			String fileVal = uploadFiles(request, response, file);
			
//			String fileVal = uploadFiles(multiRequest, response, file);
//			if(StringUtils.isNotEmpty(fileVal) && "error".equals(fileVal)){
//				JSONUtils.toJSON(response, CodeUtils.IMAGE_UPLOAD_FAILUE_TYPE);
//				return ;
//			}
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, fileVal);
		} catch (Throwable e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		//短信发送
		long start = System.currentTimeMillis();
        String str = "15170059765";
        String [] phoneNums = str.split(",");
        for(String phoneNum:phoneNums)
        {
        	int rand = (int)((Math.random() * 9 + 1) * 1000);
        	String content   =   java.net.URLEncoder.encode("【小牛洪澄】您的验证码是:"+rand+",请不要把验证码泄露给其他人",  "utf-8"); 
        	//String result_mt = ManDaoSmsUtils.getSms(phoneNum, content);
        	//String result_mt = ManDaoSmsUtils.getAudio(phoneNum, content);		
     		//System.out.println(result_mt);
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
	}
	 
}
