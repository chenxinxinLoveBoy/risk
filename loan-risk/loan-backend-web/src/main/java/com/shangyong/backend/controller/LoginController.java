package com.shangyong.backend.controller;


import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.entity.ScLoginLog;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.impl.ScLoginLogServiceImpl;
import com.shangyong.backend.service.impl.UUserServiceImpl;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.IpUtils;
import com.shangyong.backend.utils.MyDES;
import com.shangyong.backend.utils.RedisCons;
import com.shangyong.shiro.ShiroToken;
import com.shangyong.utils.RedisUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 * 
 * @author xiajiyun
 *
 */
@Controller
@RequestMapping(value = "/backend/login")
public class LoginController {

	@Autowired
	private UUserServiceImpl userServiceImpl; // 用户service

	private static Logger logger = Logger.getLogger(LoginController.class);
	
	// 登陆失败指定输出目录
	private static com.alibaba.dubbo.common.logger.Logger loginFail = LoggerFactory.getLogger("loginFail");
	
	@Autowired
	private ScLoginLogServiceImpl loginLogServiceImpl;// 登录登出日志service

	@Autowired
	private SysParamRedisService sysParamRedisService;// 参数配置实现类
	
	/**
	 * 跳转到首页界面
	 * @return
	 */
	@RequestMapping(value = "/index.do")
	public String index() {
		return "redirect:/index/index.html";

	}
	
	

//	/**
//	 * ajax请求登录
//	 * 
//	 * @param request
//	 * @param response
//	 */
//	@RequestMapping(value = "/ajaxLogin.do")
//	public void submitLogin(HttpServletRequest request, HttpServletResponse response, UUserBo userBo) {
//		try {
//			ShiroToken shiroToken = new ShiroToken(userBo.getUserName(), MyDES.encryptBasedDes(userBo.getPassword()));
//			SecurityUtils.getSubject().login(shiroToken);
//
//			String ip = TokenManager.getSession().getHost();// ip地址
//			logger.info("修改最后登录信息成功，返回到页面提示登录成功，1秒后跳转页面！");
//			logger.info("===================Session:" + TokenManager.getSession().getId());
//			JSONUtils.toJSON(response, CodeUtils.SUCCESS);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.info(e.getMessage());
//			JSONUtils.toJSON(response, CodeUtils.FAIL);
//		}
//
//	}
  
	/**
	 * submit请求登录
	 * 
	 * @param request
	 * @param response
	 * @throws Throwable 
	 */
	@RequestMapping(value = "/ajaxLogin.do")
	@ResponseBody
	public Map<String,String> submitLogin(HttpServletRequest request, HttpServletResponse response, UUserBo userBo) throws Throwable {
		logger.info("请求到后台ajaxLogin()登录方法！");
		Map<String,String> map = new HashMap<String,String>();
		try {
			SysParam sysParam = null;
			// 帐号是否被冻结
			if(RedisUtils.exists(RedisCons.LOGIN_FLAG_USER_CODE + userBo.getUserName())){
				// 获取登陆参数配置
				sysParam = getSysParam(Constants.LOGIN_COUNT_NUMBERS);
				// 获取存在redis的失败用户的登录失败次数
				Integer flagNumber = Integer.parseInt(RedisUtils.get(RedisCons.LOGIN_FLAG_USER_CODE + userBo.getUserName()));
				if(flagNumber >= Integer.parseInt(sysParam.getParamValueTwo())){
					loginFail.info("账号：【" + userBo.getUserName() + "】,登录失败，原因：  已被冻结！");
					throw new ExcessiveAttemptsException("帐号已冻结！");
				}
			}
			
			
			// BASE64解密前端的密码
//			String password = new String(Base64Encode.decode(userBo.getPassword()));
//			ShiroToken shiroToken = new ShiroToken(userBo.getUserName(), MyDES.encryptBasedDes(password), IpUtils.getClientIp(request));
			ShiroToken shiroToken = new ShiroToken(userBo.getUserName(), MyDES.encryptBasedDes(userBo.getPassword()), IpUtils.getClientIp(request));
			SecurityUtils.getSubject().login(shiroToken);

			logger.info("===================Session:" + TokenManager.getSession().getId());
			TokenManager.getSession().setTimeout(2700000);
			UUserBo u = TokenManager.getUser();
			logger.info("获取用户登录对象信息： " + u);
			if(u != null && StringUtils.isNotBlank(u.getMobile())){
				// 更新登录信息
				UUserBo userBo2 = new UUserBo();
				userBo2.setId(u.getId());
				// ip地址
				userBo2.setIp(IpUtils.getClientIp(request));
				// 登录时间
				userBo2.setLastLoginTime(DateUtils.parseToDateTimeStr(new Date()));
				// 更新到数据库表
				userServiceImpl.updateEntityById(userBo2);
				
				// 加入登录日志
				loginLog(request, u, 1);
				logger.info("获取用户手机号： " + u.getMobile());
				// 登录成功发送短信
				/*String content = java.net.URLEncoder.encode("【小牛洪澄】您的账号" + u.getUserName() + "成功登录小牛洪澄风控后台，如非本人操作，请及时联系管理员。",  "utf-8");
				String smsStr = ManDaoSmsUtils.getSms(u.getMobile(), content);
				logger.info("发送短信成功！");
				logger.info("发送短信返回信息：" + smsStr);*/
			}
			map.put("code", "200");
			return map;
		} catch (DisabledAccountException e) {// 帐号禁止
			logger.info(e.getMessage(), e);
			map.put("isError", "2");
			return map;
		} catch (ExcessiveAttemptsException e) {
			
			logger.info(e.getMessage(), e); // 帐号被冻结
			map.put("isError", "3");
			return map;
		} catch (AccountException e) { // 帐号密码错误
			logger.info(e.getMessage(), e);
			map.put("isError", "1");
			return map;
		}

	}
	
	
	/**
	 * 退出
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		try {
			// 退出
			SecurityUtils.getSubject().logout();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return "redirect:/index/login.html";
		}
		return "redirect:/index/login.html";
	}
	
	/**
	 *  登录 日志
	 * @param request
	 * @param u 登录成功的用户对象
	 * @param type 1:登录，2：退出
	 */
	public void loginLog(HttpServletRequest request, UUserBo u, Integer type){
		// 加入登录/退出，日志表
		ScLoginLog loginLog = new ScLoginLog();
		// 当前登录帐号id
		loginLog.setUserId(u.getId());
		// ip 地址
		loginLog.setIp(IpUtils.getClientIp(request));
		// type 1:登录，2：退出
		loginLog.setType(type);
		if(type == 1){
			// 登录时间
			loginLog.setLoginTime(DateUtils.parseToDateTimeStr(new Date()));
		}else{
			// 退出时间
			loginLog.setLoginOutTime(DateUtils.parseToDateTimeStr(new Date()));
		}
		
		boolean flag = loginLogServiceImpl.saveEntity(loginLog);
		if(flag){
			logger.info("插入日志登录/登出表成功！");
		}else {
			logger.error("插入日志登录表失败！");
			JSONObject jsonObject= JSONObject.fromObject(loginLog);
			logger.error("loginLog信息：" + jsonObject.toString());
		}
	}
	
	
	/**
	 * 获取登陆和钉钉的配置参数值
	 * @return
	 * @param code 
	 * @throws Throwable
	 */
	public SysParam getSysParam(final String code) throws Throwable{
		// 获取参数值
		SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(code);

		if (sysParam == null) {
			logger.error(LoginController.class);
			logger.error("sysParam参数值对象为NULL");
		} else if (StringUtils.isBlank(sysParam.getParamValueOne())) {
			logger.error(LoginController.class);
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
	
	

}
