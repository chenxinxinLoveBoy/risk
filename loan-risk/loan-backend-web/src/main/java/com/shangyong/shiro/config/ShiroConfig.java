package com.shangyong.shiro.config;

//

import com.shangyong.backend.service.impl.MenuServiceImpl;
import com.shangyong.shiro.MyShiroRealm;
import com.shangyong.shiro.filter.KickoutSessionControlFilter;
import com.shangyong.shiro.manage.RedisCacheManager;
import com.shangyong.shiro.manage.RedisManager;
import com.shangyong.shiro.manage.RedisSessionDAO;
import com.shangyong.utils.PropertiesUtil;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * shiro 配置
 * @author xiajiyun
 *
 */
@Configuration
public class ShiroConfig {
	
	
	@Autowired
	private MenuServiceImpl menuServiceImpl;
	
	// redis配置
	private final static String host = PropertiesUtil.get("spring.redis.host");
	private final static int port = Integer.parseInt(PropertiesUtil.get("spring.redis.port"));
	private final static String password = PropertiesUtil.get("spring.redis.password");

	// 登录界面
	private static final String login = "/index/login.html";
	
	// 登录成功跳转界面
	private static final String loginSuccess = "/backend/login/index.do";
	
	// 403，权限不足跳转的界面
	private static final String error_403 ="/index/views/403/403.html";
	
	// 被踢或被挤下线的跳转URL
	private static final String KICKOUT = "/index/views/kickout/kickout.html";
	 
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl(login);
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl(loginSuccess);
		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl(error_403);


		// 拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// anon： 配置不会被shiro拦截的链接，按 顺序判断
		filterChainDefinitionMap.put("/index/css/**", "anon");
		filterChainDefinitionMap.put("/index/datas/**", "anon");
		filterChainDefinitionMap.put("/index/images/**", "anon");
		filterChainDefinitionMap.put("/index/views/403/403.html", "anon");

		filterChainDefinitionMap.put("/index/images/**", "anon");
		filterChainDefinitionMap.put("/index/jquery/**", "anon");
		filterChainDefinitionMap.put("/index/js/**", "anon");

		// app推送客户信息
		filterChainDefinitionMap.put("/backend/app/customer/**", "anon");

		filterChainDefinitionMap.put("/index/My97DatePicker/**", "anon");
		filterChainDefinitionMap.put("/index/plugins/**", "anon");

		filterChainDefinitionMap.put("/index/temp/**", "anon");
		filterChainDefinitionMap.put("/index/zTree_v3/**", "anon");
		filterChainDefinitionMap.put("/index/login.html", "anon");//
		filterChainDefinitionMap.put("/backend/login/**", "anon");// // 登录的Controller都不需要认证

		//提供给API接口，不需要认证
		filterChainDefinitionMap.put("/backend/zhima*/**", "anon");
		filterChainDefinitionMap.put("/backend/xczx*/**", "anon");
		filterChainDefinitionMap.put("/backend/credit*/**", "anon");
		filterChainDefinitionMap.put("/backend/yx*/**", "anon");

		//用于大数据决策树切换的接口，不需要认证
		filterChainDefinitionMap.put("/backend/scDecisionTree/**", "anon");

		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了, 跳转的页面
		filterChainDefinitionMap.put("/backend/logout", "logout");

		//自定义拦截器
		Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
		//限制同一帐号同时在线的个数。
		filtersMap.put("kickout", kickoutSessionControlFilter());
		shiroFilterFactoryBean.setFilters(filtersMap);



//		filterChainDefinitionMap.put("/index/views/menu/**", "authc,roles[角色1,角色2,角色3]");
		// 防止用户直接输入url地址越权访问
	//	List<MenuBo> list = menuServiceImpl.getMenuRoleAll(null);
	//	for (MenuBo menu : list) {
		//	if(StringUtils.isNotBlank(menu.getHref())){
//				if(menu.getHref().lastIndexOf("/") != -1){// perms
//					/**
//					 * 1, authc,perms[] ：需要先登录，和对应的权限才能访问， 这里逗号分开表示为 and关系
//					 * 2, 因为我们所有的html页面都放在index目录下，而数据库表中没有存"/index"，所以这里需要加上前缀，shiro过滤器截取的是“http://localhost:9080/”后面的url
//					 * 3， 特别说明，因为没有去实现一个url对应多个角色，所以先用菜单名做权限
//					 */
//					filterChainDefinitionMap.put("/index/" + (menu.getHref().substring(0, menu.getHref().lastIndexOf("/")) + "/**"), "authc,perms["+ menu.getTitle().trim() +"]");
//				}else{
				//	filterChainDefinitionMap.put("/index/" + menu.getHref(), "authc,perms["+ menu.getMenuId() +"],kickout");
//					filterChainDefinitionMap.put("/index/views/**", "authc,perms["+ menu.getTitle().trim() +"]");
//				}
		//	}
		//}

		// <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		// <!-- 'authc':所有url都必须认证通过才可以访问; 'anon':所有url都都可以匿名访问-->
//		filterChainDefinitionMap.put("/index/views/**", "authc"); // 页面权限控制, 上面做了动态加载，这里就不需要写死了

		// 页面需要登录验证
		filterChainDefinitionMap.put("/index/edit/**", "authc,kickout");
		filterChainDefinitionMap.put("/index/index.html", "authc,kickout");
		filterChainDefinitionMap.put("/index/table.html", "authc,kickout");
		// 所有Controller的后台请求，都需要登录验证
		filterChainDefinitionMap.put("/backend/**", "authc,kickout");
		// tomcat访问项目默认的index.jsp文件和根目录都需要登录验证
		filterChainDefinitionMap.put("/index.jsp", "authc,kickout");
		filterChainDefinitionMap.put("/", "authc,kickout");


		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//		shiroFilterFactoryBean.
//		System.out.println("Shiro拦截器工厂类注入成功");
		return shiroFilterFactoryBean;
	}
	
	
	@Bean
	public SecurityManager securityManager(){
		 DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
	        // 设置realm.
	        securityManager.setRealm(myShiroRealm());
	        // 自定义缓存实现 使用redis
	        securityManager.setCacheManager(cacheManager());
	        // 自定义session管理 使用redis
	        securityManager.setSessionManager(SessionManager());
		return securityManager;
	}
	
	
	/**
	 * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
	 * 
	 * @return
	 */
	@Bean
	public MyShiroRealm myShiroRealm(){
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		return myShiroRealm;
	}
	
	

	 
	/**
	  * 限制同一账号登录同时登录人数控制
	  * @return
	  */
	 public KickoutSessionControlFilter kickoutSessionControlFilter(){
	 	KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
	 	//使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
	 	//这里还是用之前shiro使用的redisManager()实现的cacheManager()缓存管理
	 	//也可以重新另写一个，重新配置缓存时间之类的自定义缓存属性
	 	kickoutSessionControlFilter.setCacheManager(cacheManager());
	 	//用于根据会话ID，获取会话进行踢出操作的；
	 	kickoutSessionControlFilter.setSessionManager(SessionManager());
	 	//是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序。
	 	kickoutSessionControlFilter.setKickoutAfter(false);
	 	//同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
	 	kickoutSessionControlFilter.setMaxSession(1);
	 	//被踢出后重定向到的地址；
	 	kickoutSessionControlFilter.setKickoutUrl(KICKOUT);
	     return kickoutSessionControlFilter;
	  }
	
	
	

    /**
     * 配置shiro redisManager
     * 
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setExpire(2700);// 配置过期时间,半小时
//        redisManager.setTimeout(timeout);// 连接超时
        redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现
     * 
     * @return
     */
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     */
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * shiro session的管理
     */
    public DefaultWebSessionManager SessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }
	
	
	public static void main(String[] args) {
//		String str = "views/customerManager/customerInfo.html";
//		System.out.println(str.lastIndexOf("/"));
//		if(str.lastIndexOf("/") != -1){
//			System.out.println(str.substring(0, str.lastIndexOf("/")) + "/**");
//		}
		
	}
	
	
}
