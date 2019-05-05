package com.shangyong.shiro;


import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.bo.MenuBo;
import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.enums.alarm.AlarmCodeEnum;
import com.shangyong.backend.common.enums.alarm.AlarmThirdPartyCreditInvestigationEnum;
import com.shangyong.backend.controller.LoginController;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.IScAlarm;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.impl.MenuServiceImpl;
import com.shangyong.backend.service.impl.UUserServiceImpl;
import com.shangyong.backend.utils.RedisCons;
import com.shangyong.utils.DateUtils;
import com.shangyong.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * shiro身份校验核心类
 * 
 */

public class MyShiroRealm extends AuthorizingRealm {
	
	private static Logger logger= Logger.getLogger(MyShiroRealm.class);
	
	// 登陆失败指定输出目录
	private static com.alibaba.dubbo.common.logger.Logger loginFail = LoggerFactory.getLogger("loginFail");

	@Autowired
	private UUserServiceImpl userServiceImpl; // 用户service
	
	@Autowired
	private MenuServiceImpl menuServiceImpl; // 菜单service
	
	@Autowired
	private SysParamRedisService sysParamRedisService;// 参数配置实现类

	@Autowired
	private IScAlarm scAlarmImpl;
	

	/**
	 * 登录验证
	 * 认证信息.(身份验证) : Authentication 是用来验证用户身份
	 * 重写doGetAuthenticationInfo方法，调用数据库匹配帐号密码
	 *  
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		logger.info("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");
		ShiroToken token = (ShiroToken) authcToken;
		Map<String, Object> map = new HashMap<String, Object>();  
		map.put("userName", token.getUsername());
		map.put("password", token.getPswd());
//		UUserBo user = null;
		// 从数据库获取对应用户名密码的用户
		UUserBo user = userServiceImpl.selectByMap(map);
		if (null == user) {
			try {
				/**
				 * 处理步骤：
				 * 		1： 用户存在redis中，表示为登录失败
				 * 		2： 如果连续登录失败达到X次数，冻结账户
				 */
				// 登陆参数配置
				SysParam sysParam = getSysParam(Constants.LOGIN_COUNT_NUMBERS);
				// admin账号（超级管理员）不受冻结限制
				if(!"admin".equals(token.getUsername()) && RedisUtils.exists(RedisCons.LOGIN_FLAG_USER_CODE + token.getUsername())){
					// 获取存在redis的失败用户的登录失败次数
					Integer flagNumber = Integer.parseInt(RedisUtils.get(RedisCons.LOGIN_FLAG_USER_CODE + token.getUsername()));
					
					// 连续失败X次，用户账号状态变更为已冻结（is_freeze=1）
					if((++flagNumber) >= Integer.parseInt(sysParam.getParamValueTwo())){
//						userServiceImpl.updateIsFreeze(token.getUsername(), 1);

						// 获取钉钉参数配置
//			        	SysParam sysParamDD = getSysParam(Constants.DD_URL_CODE);
			        	StringBuilder sb = new StringBuilder();
			        	sb.append("用户登陆账号:【" + token.getUsername() + "】,");
			        	sb.append("用户当前登陆IP地址:【" + token.getHost() + "】,");
			        	sb.append("系统时间：【" + DateUtils.parseToDateTimeStr(new Date()) + "】,");
			        	sb.append("因频繁登陆失败，该账户已被冻结" + sysParam.getParamValueOne() + "分钟;");
			        	// 请求钉钉报警接口
						scAlarmImpl.contains(AlarmCodeEnum.BACKEND_WEB,sb.toString(), AlarmThirdPartyCreditInvestigationEnum.BACKEND_WEB);
//			        	String strInfo = DingdingUtil.setMessage(Constants.DD_URL_CODE, sb.toString());
//			        	logger.info("钉钉报警返回信息：" + strInfo);

					}
					
					// 将失败的用户存Redis中, 失效是时间取参数配置表
					RedisUtils.setEx(RedisCons.LOGIN_FLAG_USER_CODE + token.getUsername(), Integer.parseInt(sysParam.getParamValueOne()) * 60, flagNumber.toString());
					
				}else{// 失败将登录帐号放到redis
					
					// 将失败的用户存Redis中, 失效是时间取参数配置表, 分钟
					RedisUtils.setEx(RedisCons.LOGIN_FLAG_USER_CODE + token.getUsername(), Integer.parseInt(sysParam.getParamValueOne()) * 60, "1");
				}
				
			} catch (Throwable e) {
				logger.error("处理冻结帐号过程中异常！");
				logger.error(e.getMessage(), e);
			}
			loginFail.info("账号：【" + map.get("userName") + "】, 登陆失败,原因： 帐号或密码不正确！ ");
			throw new AccountException("帐号或密码不正确！");
		} else if(user.getState() != 1){
			/**
			 * 如果用户的status != 1 为禁用。那么就抛出<code>DisabledAccountException</code>
			 */
			loginFail.info("账号：【" + map.get("userName") + "】, 登陆失败,原因： 帐号已被禁止登录！ ");
			throw new DisabledAccountException("帐号已被禁止登录！");
		} else{
			//登陆成功删除redis的已记录失败的key
			if(RedisUtils.exists(RedisCons.LOGIN_FLAG_USER_CODE + token.getUsername())){
				RedisUtils.del(RedisCons.LOGIN_FLAG_USER_CODE + token.getUsername());
				logger.info("用户登陆成功删除redis的已记录失败的key");
			}
			
		}
		logger.info("登录成功，开始跳转页面");
		return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
	}
	
	
	

	/**
	 * 	权限验证
	* 	授权URL
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		logger.info("权限认证方法：MyShiroRealm.doGetAuthorizationInfo()");
		UUserBo token = (UUserBo) SecurityUtils.getSubject().getPrincipal();
		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
		
		
		MenuBo Menu = new MenuBo();
		Menu.setRoleId(token.getRoleId().toString());// 获取角色id
		List<MenuBo> list = menuServiceImpl.getMenuRoleAll(Menu);// 拿到权限
		Set<String> permissionSet = new HashSet<String>();
		for (MenuBo menuBo : list) {
			permissionSet.add(menuBo.getMenuId());
		}
		info.setStringPermissions(permissionSet);
		logger.info("权限认证方法：通过！");
	    return info;
	    
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
