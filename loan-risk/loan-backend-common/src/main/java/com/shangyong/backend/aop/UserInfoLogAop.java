package com.shangyong.backend.aop;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.bo.SystemLogBo;
import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.enums.alarm.AlarmCodeEnum;
import com.shangyong.backend.common.enums.alarm.AlarmThirdPartyCreditInvestigationEnum;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.service.IScAlarm;
import com.shangyong.backend.service.impl.SystemLogServiceImpl;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.IpUtils;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import net.sf.json.JSONObject;


/**
 * 记录用户操作到日志表
 * @author xiajiyun
 *
 */
@Aspect // 定义一个切面
@Configuration //表示程序启动会被springboot加载
public class UserInfoLogAop {
	
	private static final Logger logger = LoggerFactory.getLogger(UserInfoLogAop.class);
	
	@Autowired
	private SystemLogServiceImpl systemLogServiceImpl;

	@Autowired
	private IScAlarm scAlarmImpl;
	
	// 获取具体的菜单名称
	private String functionNames = "";
	// 获取具体的操作按钮功能
	private String menuNames = "";
	
	// 定义切点Pointcut, Controller里方法名为save*, del*, update* 开头的操作记录都保存到日志表   
	@Pointcut("execution(* com.shangyong.backend.controller.*Controller.save*(..)) "
			+ "|| execution(* com.shangyong.backend.controller.*Controller.del*(..))"
			+ "|| execution(* com.shangyong.backend.controller.*Controller.update*(..))")
    public void excudeService() {
    }
	
	@Before("excudeService()")
	public void before(){
	}
    
    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
    	try {

	    	//拦截的方法参数值
	    	Object[] method_args = pjp.getArgs();  

	    	// 类的全路径
	        String class_name = pjp.getTarget().getClass().getName();
	        // 方法名称
	        String method_name = pjp.getSignature().getName();
	    	
	    	   /**
	         * 获取方法参数名称
	         */
	        String[] paramNames = getFieldsName(class_name, method_name);
	         /**
	          * 打印方法的参数名和参数值
	          */
	        String paramStr = logParam(paramNames,method_args);
	 
	 
	        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
	        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
	        HttpServletRequest request = sra.getRequest();
	        
	
	        String url = request.getRequestURL().toString();// 请求全路劲
	//        String method = request.getMethod();// 具体的方法
	//        String uri = request.getRequestURI();
	        // url后面传入的参：  url.d0?name=123&age=99
	        String queryString = request.getQueryString();
	        
	        paramStr += "url拼接参数：" + queryString + "";
	        
	        
	        if(StringUtils.isBlank(menuNames)){
    			throw new RuntimeException("菜单名称不能为空");
    		}
    		if(StringUtils.isBlank(functionNames)){
    			throw new RuntimeException("功能名称不能为空");
    		}
	        
	        UUserBo user = TokenManager.getUser();// 获取当前登录的用户信息
	        
	        if(user != null){ // 登录状态
	        	
	        	SystemLogBo systemLog = new SystemLogBo();
	        	String ip = IpUtils.getClientIp(request);
	        	systemLog.setIp(ip);//ip
	        	logger.info("request请求IP地址: " + ip);
	        	systemLog.setNickName(user.getNickName());// 昵称
	        	systemLog.setRoleName(user.getRoleName());// 角色名称
	        	systemLog.setUrl(url);
	        	systemLog.setUserId(user.getId());// 用户id
	        	systemLog.setUserName(user.getUserName());// 登录帐号
	        	systemLog.setIp(ip);// ip
	        	systemLog.setFunName(functionNames);
	        	systemLog.setMenuName(menuNames);
	        	// 操作对象
	        	systemLog.setRemark("参数列表: " + paramStr);
	        	systemLogServiceImpl.saveEntity(systemLog);// 保存到数据表中
	        	
	        	
	        	//传入丁丁报警内容
	        	StringBuilder content = new StringBuilder();
	        	content.append("用户编号:【" + user.getId() + "】,");
	        	content.append("用户登陆账号:【" + user.getUserName() + "】,");
	        	content.append("用户姓名:【" + user.getNickName() + "】,");
	        	content.append("用户手机:【" + user.getMobile() + "】,");
	        	content.append("用户当前登陆IP地址:【" + ip + "】,");
	        	content.append("系统时间:【" + DateUtils.parseToDateTimeStr(new Date()) + "】,");
	        	// 获取具体的菜单名称
	        	content.append("操作菜单:【" + menuNames + "】,");
	        	// 获取具体的操作按钮功能
	        	content.append("操作功能:【" + functionNames + "】,");
	        	content.append("操作URL:【" + url + ";");
		
	        	// 请求钉钉报警接口
				scAlarmImpl.contains(AlarmCodeEnum.BACKEND_WEB,content.toString(), AlarmThirdPartyCreditInvestigationEnum.BACKEND_WEB);
	        }else{
	        	logger.error("AOP异常，未登陆，user对象为空！");
	        	throw new RuntimeException("AOP异常，未登陆，user对象为空！");
	        }
    	} catch (Exception e) {
    		logger.error(e.getMessage(), e);
		}finally{
			   Object result = pjp.proceed();
		       return result;
		}
        
    }
    
    /**
     * 使用javassist来获取方法参数名称
     * @param class_name    类名
     * @param method_name   方法名
     * @return
     * @throws Exception
     */
    private String[] getFieldsName(String class_name, String method_name) throws Exception {
        Class<?> clazz = Class.forName(class_name);
        String clazz_name = clazz.getName();
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(clazz);
        pool.insertClassPath(classPath);

        CtClass ctClass = pool.get(clazz_name);
        CtMethod ctMethod = ctClass.getDeclaredMethod(method_name);
        MethodInfo methodInfo = ctMethod.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if(attr == null){
            return null;
        }
        String[] paramsArgsName = new String[ctMethod.getParameterTypes().length];
        int pos = Modifier.isStatic(ctMethod.getModifiers()) ? 0 : 1;
        for (int i=0;i<paramsArgsName.length;i++){
            paramsArgsName[i] = attr.variableName(i + pos);
        }
        return paramsArgsName;
    }


    /**
     * 判断是否为基本类型：包括String
     * @param clazz clazz
     * @return  true：是;     false：不是
     */
    private boolean isPrimite(Class<?> clazz){
        if (clazz.isPrimitive() || clazz == String.class){
            return true;
        }else {
            return false;
        }
    }


    /**
     * 打印方法参数值  基本类型直接打印，非基本类型需要重写toString方法
     * @param paramsArgsName    方法参数名数组
     * @param paramsArgsValue   方法参数值数组
     */
    private String logParam(String[] paramsArgsName,Object[] paramsArgsValue){
        if(ArrayUtils.isEmpty(paramsArgsName) || ArrayUtils.isEmpty(paramsArgsValue)){
            logger.info("该方法没有参数!");
            return "该方法没有参数!";
        }
        String objStr = "";
        StringBuffer buffer = new StringBuffer();
        for (int i=0;i<paramsArgsName.length;i++){
            //参数名
            String name = paramsArgsName[i];
            //参数值
            Object value = paramsArgsValue[i];
            buffer.append(name +" , ");
            if(isPrimite(value.getClass())){
                buffer.append(value + "  ,");
            }else {
                buffer.append(value.toString() + "  ,");
            }
            if(paramsArgsName.length == (i+1)){
            	JSONObject jsonObject = JSONObject.fromObject(value);
            	// 获取具体的操作按钮功能
            	if(jsonObject.get("functionNames") != null){
            		functionNames = (String) jsonObject.get("functionNames");
            	}
            	// 获取具体的菜单名称
            	if(jsonObject.get("menuNames") != null){
            		menuNames = (String) jsonObject.get("menuNames");
            	}
            	// password 不需要显示在日志中
            	if(jsonObject.get("password") != null){
            		jsonObject.remove("password");
            	}
            	
            	logger.info(jsonObject.toString());	
            	objStr = jsonObject.toString();
            }
        }
        
        logger.info("参数对象个数：【" +paramsArgsName.length + "】个; 参数对象名称为:【" + buffer.toString() + "】; 传入实体对象值：【" + objStr + "】;");
        return "传入实体对象值：【" + objStr + "】;";
    }
}
