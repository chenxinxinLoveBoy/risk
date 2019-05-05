package com.shangyong.backend.service.shzx.impl;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import com.shangyong.backend.common.*;
import com.shangyong.backend.common.enums.BanCodeEnum;
import com.shangyong.backend.common.enums.FraudBizEnum;
import com.shangyong.backend.entity.redis.ShangHaiCreditRedis;
import com.shangyong.backend.entity.redis.fraud2_0.ShangHaiCredit20Redis;
import com.shangyong.backend.service.access.RiskRuleService;
import com.shangyong.utils.*;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shangyong.backend.dao.sh.ShCreditAddressDao;
import com.shangyong.backend.dao.sh.ShCreditContactsDao;
import com.shangyong.backend.dao.sh.ShCreditDao;
import com.shangyong.backend.dao.sh.ShCreditGuaranteeDao;
import com.shangyong.backend.dao.sh.ShCreditLoansDao;
import com.shangyong.backend.dao.sh.ShCreditLoansDealDao;
import com.shangyong.backend.dao.sh.ShCreditLoansMessageDao;
import com.shangyong.backend.dao.sh.ShCreditPromptDao;
import com.shangyong.backend.dao.sh.ShCreditSearchInformationDao;
import com.shangyong.backend.dao.sh.ShCreditSpecialDealDao;
import com.shangyong.backend.dao.sh.ShCreditWorkDao;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.entity.sh.ShCredit;
import com.shangyong.backend.entity.sh.ShCreditAddress;
import com.shangyong.backend.entity.sh.ShCreditContacts;
import com.shangyong.backend.entity.sh.ShCreditGuarantee;
import com.shangyong.backend.entity.sh.ShCreditLoans;
import com.shangyong.backend.entity.sh.ShCreditLoansDeal;
import com.shangyong.backend.entity.sh.ShCreditLoansMessage;
import com.shangyong.backend.entity.sh.ShCreditPrompt;
import com.shangyong.backend.entity.sh.ShCreditSearchInformation;
import com.shangyong.backend.entity.sh.ShCreditSpecialDeal;
import com.shangyong.backend.entity.sh.ShCreditTheme;
import com.shangyong.backend.entity.sh.ShCreditWork;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.report.JsonReportService;
import com.shangyong.backend.service.shzx.ShCreditService;

@Service
public class ShCreditServiceImpl implements ShCreditService {
	
	private static Logger shCreditLogger = LoggerFactory.getLogger("shCredit");

	@Autowired
	private SysParamRedisService sysParamRedisService;
	
	static Cookie[] cookies = {};

	final static MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();

	final static HttpClient httpClient = new HttpClient(connectionManager);

	private static final BigDecimal BIGDECIMAL_ZERO = new BigDecimal(0);

	private static final List<String> LOAN_PAYMENT_STATUS_CODE = new ArrayList<String>(){{
        add("1"); add("2"); add("3"); add("4"); add("5"); add("6"); add("7");add("D"); add("Z"); add("G");add("d"); add("z"); add("g");
    }};

	@Autowired
	private JsonReportService jsonReportService;
	
	@Autowired
	private ShCreditDao shCreditDao;
	
	@Autowired
	private ShCreditAddressDao shCreditAddressDao;
	
	@Autowired
	private ShCreditContactsDao shCreditContactsDao;
	
	@Autowired
	private ShCreditGuaranteeDao shCreditGuaranteeDao;
	
	@Autowired
	private ShCreditLoansDao shCreditLoansDao;
	
	@Autowired
	private ShCreditLoansDealDao shCreditLoansDealDao;
	
	@Autowired
	private ShCreditLoansMessageDao shCreditLoansMessageDao;
	
	@Autowired
	private ShCreditSearchInformationDao shCreditSearchInformationDao;
	
	@Autowired
	private ShCreditSpecialDealDao shCreditSpecialDealDao;
	
	@Autowired
	private ShCreditWorkDao shCreditWorkDao;
	
	@Autowired
	private ShCreditPromptDao shCreditPromptDao;

	@Autowired
	private RiskRuleService riskRuleService;


	@Override
	public RuleResult queryShCredit(Application application) {
		RuleResult ruleResult = new RuleResult();
		String dateTime = DateUtils.getDate(new Date());
		SysParam shCreditParameter1 = sysParamRedisService.querySysParamByParamValueRedis(Constants.SH_CREDIT_CODE1);
		SysParam shCreditParameter2 = sysParamRedisService.querySysParamByParamValueRedis(Constants.SH_CREDIT_CODE2);
		//判断
		if (shCreditParameter1 == null) {
			shCreditLogger.error("[上海资信] 从系统参数表获取参数失败,key : SH_CREDIT_CODE1");
			throw new RuntimeException("[上海资信] 从系统参数表获取参数失败,key : SH_CREDIT_CODE1");
		}
		//判断
		if (shCreditParameter2 == null) {
			shCreditLogger.error("[上海资信] 从系统参数表获取参数失败,key : SH_CREDIT_CODE2");
			throw new RuntimeException("[上海资信] 从系统参数表获取参数失败,key : SH_CREDIT_CODE2");
		}
		// VPN用户名
		String username = shCreditParameter1.getParamValueOne();
		// VPN登录密码
		String password = shCreditParameter1.getParamValueTwo();
		// 机构号
		String orgCode = shCreditParameter1.getParamValueThree();
		// 直连密码（注意：和邮件中的平台密码是两回事，如果没有，请和资信专管员联系）
		String sSecret = shCreditParameter1.getParamValueFour();
		// 压缩密码
		String zipPassword = shCreditParameter1.getParamValueFive();
		String urlPrefix = shCreditParameter2.getParamValueOne();
		String step1URI = shCreditParameter2.getParamValueTwo();
		String step2URI = shCreditParameter2.getParamValueThree();
		String batchCreditWebServiceURI = shCreditParameter2.getParamValueFour();
		String msgSubmitWebServiceURI = shCreditParameter2.getParamValueFive();
		Assert.hasText(username,"VPN用户名不能为空");
		Assert.hasText(password,"VPN登录密码不能为空");
		Assert.hasText(orgCode,"机构号不能为空");
		Assert.hasText(sSecret,"直连密码不能为空");
		Assert.hasText(zipPassword,"压缩密码不能为空");
		Assert.hasText(urlPrefix,"URL_PREFIX地址不能为空");
		Assert.hasText(step1URI,"step1URI地址不能为空");
		Assert.hasText(step2URI,"step2URI地址不能为空");
		Assert.hasText(batchCreditWebServiceURI,"batchCreditWebServiceURI地址不能为空");
		Assert.hasText(msgSubmitWebServiceURI,"msgSubmitWebServiceURI地址不能为空");
		String queryCredit = null;
		String convertSoapToXML = null;

		try {
			//VPN登入
			HashMap<String, String> param = new HashMap<String,String>();
			param.put("username", username);
			param.put("password", password);
			param.put("step1URI", step1URI);
			param.put("step2URI", step2URI);
			loginVPN(param);
			//信用报告单笔查询接口
			Map<String,String> map = new HashMap<String,String>();
			String certType = application.getCertType();
			// 判断证件类型
			if (StringUtils.isBlank(certType) && !"1".equals(certType)) {
				// 不是身份证,报错
				throw new RuntimeException("[上海资信] 证件类型异常,请输入身份证类型:" + application.getCertType());
			}
			String certCode = application.getCertCode();
			String name = application.getName();
			String applicationId = application.getApplicationId();
			Assert.hasText(certCode, "身份证不能为空");
			Assert.hasText(name, "姓名不能为空");
			map.put("plate", "1");
			map.put("certtype", "0");
			map.put("certno", certCode);
			map.put("name", name);
			map.put("reason", "02");
			map.put("createtype", "1");
			map.put("orgcode", orgCode);
			map.put("ssecret", sSecret);
			map.put("batchCreditWebServiceURI", urlPrefix + batchCreditWebServiceURI);
			queryCredit = queryCredit(map);

			convertSoapToXML = convertSoapToXML(queryCredit);

			String key20 = RedisConstant.buildFraudScoresKey2_0(application.getApplicationId(), FraudBizEnum.SHANGHAI_CREDIT);
			ShangHaiCredit20Redis shangHaiCredit20Redis = new ShangHaiCredit20Redis();
			ShangHaiCreditRedis shangHaiCreditRedis = new ShangHaiCreditRedis();

			if ("<?xmlnull>".equals(convertSoapToXML)) {
				Document doc= DocumentHelper.parseText(queryCredit);
		        JSONObject json=new JSONObject();
		        dom4j2Json(doc.getRootElement(),json);
		        String jsonString = json.toJSONString();
				jsonString = jsonString.replaceAll("\\\\", "");
				jsonString = jsonString.replaceAll(" ", "");
				jsonString = jsonString.replace("return", "data");
				jsonString = jsonString.replace("\"[", "[");
				jsonString = jsonString.replace("]\"", "]");
				JSONObject parseObject = JSONObject.parseObject(jsonString);
				JSONArray jsonArray = parseObject.getJSONObject("Body").getJSONObject("queryCreditResponse").getJSONArray("data");
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject object = jsonArray.getJSONObject(i);
					Map<String,Object> m = object;
					int s =  (int) m.get("state");
					String state = String.valueOf(s);
					if (!"1".equals(state)) {
						String errors = (String) m.get("errors");
						shCreditLogger.error("[上海资信] 未查询到数据,用户申请单号:" + applicationId);
						ShCredit shCredit = new ShCredit();
						shCredit.setShCreditId(UUIDUtils.getUUID());
						shCredit.setApplicationId(applicationId);
						shCredit.setCreateTime(dateTime);
						shCredit.setModifyTime(dateTime);
						shCredit.setRemark(errors);
						int count = shCreditDao.saveEntity(shCredit);
						Assert.isTrue(count>0,"插入上海资信用户信息表失败");

						BeanUtils.copyProperties(shangHaiCreditRedis, shangHaiCredit20Redis);
						RedisFraudUtils.hmset(key20, shangHaiCredit20Redis.toMap());
						return ruleResult;
					}
				}
			}
			queryCredit = queryCredit.replaceAll("&amp", "");
			convertSoapToXML = convertSoapToXML(queryCredit);
			Document doc= DocumentHelper.parseText(convertSoapToXML);
	        JSONObject json=new JSONObject();
	        dom4j2Json(doc.getRootElement(),json);
	        String jsonString = json.toJSONString();

			JSONObject jsonObject = JSONObject.parseObject(jsonString);
			//用户信息
			ShCredit shCredit = saveCredit(jsonObject, applicationId, dateTime);
			//用户地址
			List<ShCreditAddress> address = saveShCreditAddress(jsonObject, applicationId, dateTime);
			//用户工作
			List<ShCreditWork> works = saveShCreditWork(jsonObject, applicationId, dateTime);
			//用户联系人
			List<ShCreditContacts> contacts = saveShCreditContacts(jsonObject, applicationId, dateTime);
			//贷款申请表
			List<ShCreditLoansMessage> saveLoansMessage = saveLoansMessage(jsonObject, applicationId, dateTime);
			//贷款交易信息
			ShCreditLoansDeal saveLoansDeal = saveLoansDeal(jsonObject, applicationId, dateTime, shangHaiCreditRedis);
			//用户贷款
			List<ShCreditLoans> saveLoans = saveLoans(jsonObject, applicationId, dateTime, shangHaiCreditRedis);
			//为他人担保
			List<ShCreditGuarantee> saveGuarantee = saveGuarantee(jsonObject, applicationId,dateTime);
			//特殊交易
			List<ShCreditSpecialDeal> saveSpecialDeal = saveSpecialDeal(jsonObject, applicationId, dateTime);
			//查询信息
			List<ShCreditSearchInformation> saveSearchInformation = saveSearchInformation(jsonObject, applicationId, dateTime);
			//资信提示
			List<ShCreditPrompt> saveShCreditPrompt = saveShCreditPrompt(jsonObject, applicationId, dateTime);
			//主表
//			ShCreditTheme shCreditTheme = new ShCreditTheme();
//			shCreditTheme.setShCredit(shCredit);
//			shCreditTheme.setAddress(address);
//			shCreditTheme.setWorks(works);
//			shCreditTheme.setContacts(contacts);
//			shCreditTheme.setSaveLoansDeal(saveLoansDeal);
//			shCreditTheme.setSavaLoans(saveLoans);
//			shCreditTheme.setSaveGuarantee(saveGuarantee);
//			shCreditTheme.setSaveSpecialDeal(saveSpecialDeal);
//			shCreditTheme.setSaveSearchInformation(saveSearchInformation);
//			shCreditTheme.setSaveLoansMessage(saveLoansMessage);
//			shCreditTheme.setSaveShCreditPrompt(saveShCreditPrompt);
//			String jsonString = JSONObject.toJSONString(shCreditTheme);
			//JSONObject parseObject = JSONObject.parseObject(jsonString);

			saveLoans = sortList(saveLoans);

            // 处理 上海资信 的禁止项
            List<Map<String, Object>> banOption = new ArrayList<>();
			processBanAccountAbnormal(banOption, saveLoans);
			processBanIsOverdue(banOption, saveLoans);
			processSingleCount(banOption, saveLoans);
			processSingleTotal(banOption, saveLoans);
			processThreeMonthHave2M1(banOption,  saveLoans);
			processSixMonthHave1M2(banOption, saveLoans);
			processSixMonthHave3M1(banOption, saveLoans);
			processTwelveMonth4M1(banOption, saveLoans);
			processSingleMaxOverdue(banOption, saveLoans);
            processOverdueTotal(banOption, saveLoans);
            processTardy(banOption, saveSpecialDeal);
            processIndividualGuaranteeIndemnity(banOption, saveSpecialDeal);

            ruleResult = riskRuleService.querySafeRuleApi(application, banOption);

            if(ruleResult == null){
                throw new RuntimeException("[上海资信] 禁止项运算结果ruleResult为空");
            }

			//存阿里云存mongo
            jsonReportService.uploadJson(Constants.SH_CREDIT_DIR, queryCredit,
                    TaskTypeConstants.SH_CREDIT_TYPE,
                    TaskTypeConstants.SH_CREDIT_NAME,
                    TaskTypeConstants.SH_CREDIT_ISEND,

                    application.getApplicationId(), "noext");

			BeanUtils.copyProperties(shangHaiCreditRedis, shangHaiCredit20Redis);
			RedisFraudUtils.hmset(key20, shangHaiCredit20Redis.toMap());
		} catch (Exception e) {
			shCreditLogger.error("[上海资信] 查询失败,返回报文:" + queryCredit, e);
			throw new RuntimeException("[上海资信] 查询失败,原因:" , e);
		}

		return ruleResult;
	}

	/**
	 * 调用WebService的前提：模拟登陆VPN
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public void loginVPN(Map<String,String> param) throws Exception {		
		// STEP1:
		HttpClientParams httparams = new HttpClientParams();
		httparams.setSoTimeout(30000);
		httpClient.setParams(httparams);

		httpClient.getHostConfiguration().setHost("vpn.shanghai-cis.com.cn", 80);

		PostMethod login = new PostMethod(param.get("step1URI"));
		login.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");

		httpClient.executeMethod(login);

		cookies = httpClient.getState().getCookies();
		
		httpClient.getState().addCookies(cookies);
		login.releaseConnection();

		// STEP2:
		PostMethod login2 = new PostMethod(param.get("step2URI"));
		login2.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		login2.addRequestHeader("Refer", param.get("step1URI"));
		login2.addRequestHeader("Accept",
				"image/jpeg, application/x-ms-application, image/gif, application/xaml+xml, image/pjpeg, application/x-ms-xbap, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");

		NameValuePair tgroup = new NameValuePair("tgroup", "");
		NameValuePair next = new NameValuePair("next", "");
		NameValuePair tgcookieset = new NameValuePair("tgcookieset", "");
		NameValuePair Login = new NameValuePair("Login", "登录");
		NameValuePair Email = new NameValuePair("username", param.get("username"));
		NameValuePair password1 = new NameValuePair("password", param.get("password"));
		// NameValuePair code = new NameValuePair( "code"
		// ,"????");//有时候需要验证码，暂时未解决

		NameValuePair[] data = { tgroup, next, tgcookieset, Login, Email, password1 };
		login2.setRequestBody(data);

		httpClient.executeMethod(login2);

		cookies = httpClient.getState().getCookies();
		httpClient.getState().addCookies(cookies);
		login2.releaseConnection();
	}

    /**
     * 信用报告单笔查询接口
     * @param map
     * @return
     * @throws Exception
     */
	public  String queryCredit(Map<String,String> map)
			throws Exception {
		String reuqest5 = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.creditreport.p2p.sino.com/\">"
  				+ "<soapenv:Header/>"
  				+ "<soapenv:Body>"
  				+ "<web:queryCredit>"
  				+ "<orgcode>"+ map.get("orgcode") +"</orgcode>"
  				+ "<secret>"+ map.get("ssecret") +"</secret>"
  				+ "<plate>"+ Integer.valueOf(map.get("plate")) +"</plate>"
  				+ "<certtype>"+ map.get("certtype") +"</certtype>"
  				+ "<certno>"+ map.get("certno") +"</certno>"
  				+ "<name>"+ map.get("name") +"</name>"
  				+ "<reason>"+ map.get("reason") +"</reason>"
  				+ "<createtype>"+ map.get("createtype") +"</createtype>"
  				+ "</web:queryCredit>"
  				+ "</soapenv:Body>"
  				+ "</soapenv:Envelope>";
		
		return callService(map.get("batchCreditWebServiceURI"), reuqest5);
	}
	
	/**
	 * 调用WebService
	 * 
	 * @param requestBody
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public  String callService(String webServiceURI, String requestBody) throws Exception {
		String result = "";
		PostMethod login = new PostMethod(webServiceURI);
		try {
			login.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			login.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			login.setRequestBody(requestBody);
			httpClient.executeMethod(login);
			result = login.getResponseBodyAsString();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			login.releaseConnection();
		}
		
		return result;
	}

	private  String convertSoapToXML(String result) {

		result = StringUtils.remove(result, "\\");
		result = StringEscapeUtils.unescapeXml(result);
		result = StringUtils.substringBetween(result, "<?xml", ">\"");
		result = "<?xml" + result + ">";

		return result;
	}

	/**
	 * xml转json
	 * @param xmlStr
	 * @return
	 * @throws DocumentException
	 */
	public  JSONObject xml2Json(String xmlStr) throws DocumentException{
		Document doc= DocumentHelper.parseText(xmlStr);
		JSONObject json=new JSONObject();
		dom4j2Json(doc.getRootElement(), json);
		return json;
	}

	/**
	 * xml转json
	 * @param element
	 * @param json
	 */
	public  void dom4j2Json(Element element,JSONObject json){
		//如果是属性
		for(Object o:element.attributes()){
			Attribute attr=(Attribute)o;
			if(!isEmpty(attr.getValue())){
				json.put("@"+attr.getName(), attr.getValue());
			}
		}
		List<Element> chdEl=element.elements();
		if(chdEl.isEmpty()&&!isEmpty(element.getText())){//如果没有子元素,只有一个值
			json.put(element.getName(), element.getText());
		}

		for(Element e:chdEl){//有子元素
			if(!e.elements().isEmpty()){//子元素也有子元素
				JSONObject chdjson=new JSONObject();
				dom4j2Json(e,chdjson);
				Object o=json.get(e.getName());
				if(o!=null){
					JSONArray jsona=null;
					if(o instanceof JSONObject){//如果此元素已存在,则转为jsonArray
						JSONObject jsono=(JSONObject)o;
						json.remove(e.getName());
						jsona=new JSONArray();
						jsona.add(jsono);
						jsona.add(chdjson);
					}
					if(o instanceof JSONArray){
						jsona=(JSONArray)o;
						jsona.add(chdjson);
					}
					json.put(e.getName(), jsona);
				}else{
					if(!chdjson.isEmpty()){
						json.put(e.getName(), chdjson);
					}
				}


			}else{//子元素没有子元素
				for(Object o:element.attributes()){
					Attribute attr=(Attribute)o;
					if(!isEmpty(attr.getValue())){
						json.put("@"+attr.getName(), attr.getValue());
					}
				}
				if(!e.getText().isEmpty()){
					json.put(e.getName(), e.getText());
				}
			}
		}
	}

	public  boolean isEmpty(String str) {

		if (str == null || str.trim().isEmpty() || "null".equals(str)) {
			return true;
		}
		return false;
	}
    /**
     * 上海资信主表
     * @param jsonObject
     * @return
     */
    public  ShCredit saveCredit(JSONObject jsonObject,String applicationId,String dateTime){
        ShCredit shCredit = new ShCredit();
        //获取报告开头
        if (jsonObject.containsKey("信用报告头")) {
            JSONObject shCreditJson = jsonObject.getJSONObject("信用报告头");
            if (shCreditJson.containsKey("查询原因")) {
                String queryReason = shCreditJson.getString("查询原因");
                shCredit.setQueryReason(queryReason);
            }
            if (shCreditJson.containsKey("报告编号")) {
                String reportNumber = shCreditJson.getString("报告编号");
                shCredit.setReportNumber(reportNumber);
            }
            if (shCreditJson.containsKey("报告时间")) {
                String reportTime = shCreditJson.getString("报告时间");
                shCredit.setReportTime(reportTime);
            }
        }
        if (jsonObject.containsKey("个人身份信息")) {
            //获取个人身份信息
            JSONObject shCreditJson1 = jsonObject.getJSONObject("个人身份信息");
            if (shCreditJson1.containsKey("姓名")) {
                String name = shCreditJson1.getString("姓名");
                shCredit.setName(name);
            }
            if (shCreditJson1.containsKey("证件类型")) {
                String certType = shCreditJson1.getString("证件类型");
                shCredit.setCertType(certType);
            }
            if (shCreditJson1.containsKey("证件号码")) {
                String certCode = shCreditJson1.getString("证件号码");
                shCredit.setCertCode(certCode);
            }
            if (shCreditJson1.containsKey("性别")) {
                String sex = shCreditJson1.getString("性别");
                shCredit.setSex(sex);
            }
            if (shCreditJson1.containsKey("出生日期")) {
                String birthTime = shCreditJson1.getString("出生日期");
                shCredit.setBirthTime(birthTime);
            }
            //获取婚姻明细
            if (shCreditJson1.containsKey("婚姻状况")) {
            JSONObject marriage = shCreditJson1.getJSONObject("婚姻状况");
            if (marriage.containsKey("婚姻明细")) {
                String marriageDetail = marriage.getString("婚姻明细");
                shCredit.setMarriageDetail(marriageDetail);
            }
            if (marriage.containsKey("信息获取日期")) {
                String marriageTime = marriage.getString("信息获取日期");
                shCredit.setMarriageTime(marriageTime);
                }
            }
            //获取最高学历
            if (shCreditJson1.containsKey("最高学历")) {
            JSONObject maxEducation = shCreditJson1.getJSONObject("最高学历");
            if (maxEducation.containsKey("学历明细")) {
                String education = maxEducation.getString("学历明细");
                shCredit.setEducation(education);
            }
            if (maxEducation.containsKey("信息获取日期")) {
                String educationTime = maxEducation.getString("信息获取日期");
                shCredit.setEducationTime(educationTime);
                }
            }
            //获取职称
            if (shCreditJson1.containsKey("职称")) {
            JSONObject title = shCreditJson1.getJSONObject("职称");
            if (title.containsKey("职称明细")) {
                String titleDetail = title.getString("职称明细");
                shCredit.setTitleDetail(titleDetail);
            }
            if (title.containsKey("信息获取日期")) {
                String titleTime = title.getString("信息获取日期");
                shCredit.setTitleTime(titleTime);
                }
            }
            //获取住宅电话
            if (shCreditJson1.containsKey("住宅电话")) {
            JSONObject residence = shCreditJson1.getJSONObject("住宅电话");
            if (residence.containsKey("住宅电话明细")) {
                String residencePhone = residence.getString("住宅电话明细");
                shCredit.setResidencePhone(residencePhone);
            }
            if (residence.containsKey("信息获取日期")) {
                String residenceTime = residence.getString("信息获取日期");
                shCredit.setResidenceTime(residenceTime);
                }
            }
            //获取手机号码
            if (shCreditJson1.containsKey("手机号码")) {
                JSONObject phone = shCreditJson1.getJSONObject("手机号码");
            if (phone.containsKey("手机号码明细")) {
                String phoneNumber = phone.getString("手机号码明细");
                shCredit.setPhoneNumber(phoneNumber);
            }
            if (phone.containsKey("信息获取日期")) {
                String phoneTime = phone.getString("信息获取日期");
                shCredit.setPhoneTime(phoneTime);
                }
            }
            //获取电子邮箱
            if (shCreditJson1.containsKey("电子邮箱")) {
            JSONObject email = shCreditJson1.getJSONObject("电子邮箱");
            if (email.containsKey("电子邮箱明细")) {
                String emailAddress = email.getString("电子邮箱明细");
                shCredit.setEmailAddress(emailAddress);
            }
            if (email.containsKey("信息获取日期")) {
                String emailTime = email.getString("信息获取日期");
                shCredit.setEmailTime(emailTime);
            }
            }
            //获取配偶信息
            if (shCreditJson1.containsKey("配偶姓名")) {
                String mateName = shCreditJson1.getString("配偶姓名");
                shCredit.setMateName(mateName);
            }
            if (shCreditJson1.containsKey("配偶证件类型")) {
                String mateCertType = shCreditJson1.getString("配偶证件类型");
                shCredit.setMateCertType(mateCertType);
            }
            if (shCreditJson1.containsKey("配偶证件号码")) {
                String mateCertCode = shCreditJson1.getString("配偶证件号码");
                shCredit.setMateCertCode(mateCertCode);
            }
            if (shCreditJson1.containsKey("配偶性别")) {
                String mateSex = shCreditJson1.getString("配偶性别");
                shCredit.setMateSex(mateSex);
            }
            if (shCreditJson1.containsKey("配偶出生日期")) {
                String mateBirthTime = shCreditJson1.getString("配偶出生日期");
                shCredit.setMateBirthTime(mateBirthTime);
            }
            //获取配偶工作单位
            if (shCreditJson1.containsKey("配偶工作单位")) {
            JSONObject mateWork1 = shCreditJson1.getJSONObject("配偶工作单位");
            if (mateWork1.containsKey("配偶工作单位明细")) {
                String mateWork = mateWork1.getString("配偶工作单位明细");
                shCredit.setMateWork(mateWork);
            }
            if (mateWork1.containsKey("信息获取日期")) {
                String mateWorkTime = mateWork1.getString("信息获取日期");
                shCredit.setMateWorkTime(mateWorkTime);
                }
            }
            //获取配偶联系电话
            if (shCreditJson1.containsKey("配偶联系电话")) {
            JSONObject matePhone1 = shCreditJson1.getJSONObject("配偶联系电话");
            if (matePhone1.containsKey("配偶联系电话明细")) {
                String matePhone = matePhone1.getString("配偶联系电话明细");
                shCredit.setMatePhone(matePhone);
            }
            if (matePhone1.containsKey("信息获取日期")) {
                String matePhoneTime = matePhone1.getString("信息获取日期");
                shCredit.setMatePhoneTime(matePhoneTime);
                }
            }

        }
        if (shCredit != null) {
            shCredit.setShCreditId(UUIDUtils.getUUID());
            shCredit.setApplicationId(applicationId);
            shCredit.setCreateTime(dateTime);
            shCredit.setModifyTime(dateTime);
            int count = shCreditDao.saveEntity(shCredit);
            Assert.isTrue(count>0,"插入上海资信用户信息表失败");
        }
        return shCredit;
    }

    /**
     * 上海资信用户地址表
     * @param jsonObject
     * @return
     */
    public  List<ShCreditAddress> saveShCreditAddress(JSONObject jsonObject,String applicationId,String dateTime){

        List<ShCreditAddress> list = new ArrayList<ShCreditAddress>();
        if (jsonObject.containsKey("个人身份信息")) {
            JSONObject shCreditJson1 = jsonObject.getJSONObject("个人身份信息");
            if (shCreditJson1.containsKey("地址")) {
                Object object2 = shCreditJson1.get("地址");
                if (object2 instanceof JSONObject) {
                    JSONObject shAddress = shCreditJson1.getJSONObject("地址");
                    ShCreditAddress shCreditAddress = new ShCreditAddress();
                    if (shAddress.containsKey("地址序号")) {
                        String addressNumber = shAddress.getString("地址序号");
                        shCreditAddress.setAddressNumber(addressNumber);
                    }
                    if (shAddress.containsKey("地址明细")) {
                        String addressDetail = shAddress.getString("地址明细");
                        shCreditAddress.setAddressDetail(addressDetail);
                    }
                    if (shAddress.containsKey("信息获取日期")) {
                        String addressTime = shAddress.getString("信息获取日期");
                        shCreditAddress.setAddressTime(addressTime);
                    }
                    if (shCreditAddress != null) {
                        shCreditAddress.setShCreditAddressId(UUIDUtils.getUUID());
                        shCreditAddress.setApplicationId(applicationId);
                        shCreditAddress.setCreateTime(dateTime);
                        shCreditAddress.setModifyTime(dateTime);
                        list.add(shCreditAddress);
                    }

                }else{
                    JSONArray jsonArray = shCreditJson1.getJSONArray("地址");
                    for (int i = 0; i < jsonArray.size(); i++) {
                        ShCreditAddress shCreditAddress = new ShCreditAddress();
                        Object object = jsonArray.get(i);
                        JSONObject shAddress = (JSONObject) JSON.toJSON(object);
                        if (shAddress.containsKey("地址序号")) {
                            String addressNumber = shAddress.getString("地址序号");
                            shCreditAddress.setAddressNumber(addressNumber);
                        }
                        if (shAddress.containsKey("地址明细")) {
                            String addressDetail = shAddress.getString("地址明细");
                            shCreditAddress.setAddressDetail(addressDetail);
                        }
                        if (shAddress.containsKey("信息获取日期")) {
                            String addressTime = shAddress.getString("信息获取日期");
                            shCreditAddress.setAddressTime(addressTime);
                        }
                        if (shCreditAddress != null) {
                            shCreditAddress.setShCreditAddressId(UUIDUtils.getUUID());
                            shCreditAddress.setApplicationId(applicationId);
                            shCreditAddress.setCreateTime(dateTime);
                            shCreditAddress.setModifyTime(dateTime);
                            list.add(shCreditAddress);
                        }

                    }
                }

            }
        }
        if (list!= null && !list.isEmpty()) {
            int count = shCreditAddressDao.saveAllEntity(list);
            Assert.isTrue(count>0,"上海资信用户地址插入失败");
        }
        return list;

    }
    /**
     * 上海资信用户工作表
     * @param jsonObject
     * @return
     */
    public  List<ShCreditWork> saveShCreditWork(JSONObject jsonObject,String applicationId,String dateTime){
        List<ShCreditWork> list = new ArrayList<ShCreditWork>();
        if (jsonObject.containsKey("个人身份信息")) {
            JSONObject shCreditJson1 = jsonObject.getJSONObject("个人身份信息");
            if (shCreditJson1.containsKey("工作单位")) {
                Object object2 = shCreditJson1.get("工作单位");
                if (object2 instanceof JSONObject) {
                    JSONObject work = shCreditJson1.getJSONObject("工作单位");
                    ShCreditWork shCreditWork = new ShCreditWork();
                    if (work.containsKey("工作序号")) {
                        String workNumber = work.getString("工作序号");
                        shCreditWork.setWorkNumber(workNumber);
                    }
                    if (work.containsKey("工作明细")) {
                        String workDetail = work.getString("工作明细");
                        shCreditWork.setWorkDetail(workDetail);
                    }
                    if (work.containsKey("职业")) {
                        String workProfession = work.getString("职业");
                        shCreditWork.setWorkProfession(workProfession);
                    }
                    if (work.containsKey("信息获取日期")) {
                        String workTime = work.getString("信息获取日期");
                        shCreditWork.setWorkTime(workTime);
                    }
                    if (shCreditWork != null) {
                        shCreditWork.setShCreditWorkId(UUIDUtils.getUUID());
                        shCreditWork.setApplicationId(applicationId);
                        shCreditWork.setCreateTime(dateTime);
                        shCreditWork.setModifyTime(dateTime);
                        list.add(shCreditWork);
                    }

                }else{
                    JSONArray jsonArray = shCreditJson1.getJSONArray("工作单位");
                    for (int i = 0; i < jsonArray.size(); i++) {
                        ShCreditWork shCreditWork = new ShCreditWork();
                        Object object = jsonArray.get(i);
                        JSONObject work = (JSONObject) JSON.toJSON(object);
                        if (work.containsKey("工作序号")) {
                            String workNumber = work.getString("工作序号");
                            shCreditWork.setWorkNumber(workNumber);
                        }
                        if (work.containsKey("工作明细")) {
                            String workDetail = work.getString("工作明细");
                            shCreditWork.setWorkDetail(workDetail);
                        }
                        if (work.containsKey("职业")) {
                            String workProfession = work.getString("职业");
                            shCreditWork.setWorkProfession(workProfession);
                        }
                        if (work.containsKey("信息获取日期")) {
                            String workTime = work.getString("信息获取日期");
                            shCreditWork.setWorkTime(workTime);
                        }
                        if (shCreditWork != null) {
                            shCreditWork.setShCreditWorkId(UUIDUtils.getUUID());
                            shCreditWork.setApplicationId(applicationId);
                            shCreditWork.setCreateTime(dateTime);
                            shCreditWork.setModifyTime(dateTime);
                            list.add(shCreditWork);
                        }

                    }
                }

            }
        }
        if (list!= null && !list.isEmpty()) {
            int count = shCreditWorkDao.saveAllEntity(list);
            Assert.isTrue(count>0, "上海资信用户工作插入失败");
        }
        return list;
    }
    /**
     * 上海资信用户联系人信息表
     * @param jsonObject
     * @return
     */
    public List<ShCreditContacts> saveShCreditContacts(JSONObject jsonObject,String applicationId,String dateTime){
        List<ShCreditContacts> list = new ArrayList<ShCreditContacts>();
        if (jsonObject.containsKey("个人身份信息")) {
            JSONObject shCreditJson1 = jsonObject.getJSONObject("个人身份信息");
            String str1 = "第一联系人信息";
            list = contactsList(shCreditJson1,list,str1,applicationId,dateTime);
            String str2 = "第二联系人信息";
            list = contactsList(shCreditJson1,list,str2,applicationId,dateTime);
        }
        if (list!= null && !list.isEmpty()) {
            int count = shCreditContactsDao.saveAllEntity(list);
            Assert.isTrue(count>0, "上海资信用户联系人信息插入失败");
        }

        return list;

    }

    /**联系人分类
     * @param shCreditJson1
     * @param list
     * @param str
     * @return
     */
    public  List<ShCreditContacts> contactsList(JSONObject shCreditJson1,List<ShCreditContacts> list,String str,String applicationId,String dateTime){
        if (shCreditJson1.containsKey(str)) {
            Object object2 = shCreditJson1.get(str);
            if (object2 instanceof JSONObject) {
                JSONObject contact = shCreditJson1.getJSONObject(str);
                ShCreditContacts contacts = new ShCreditContacts();
                if (contact.containsKey("联系人姓名")) {
                    String contactsName = contact.getString("联系人姓名");
                    contacts.setContactsName(contactsName);
                }
                if (contact.containsKey("联系人关系")) {
                    String contactsRelation = contact.getString("联系人关系");
                    contacts.setContactsRelation(contactsRelation);
                }
                if (contact.containsKey("联系电话")) {
                    String contactsNumber = contact.getString("联系电话");
                    contacts.setContactsNumber(contactsNumber);
                }
                if (contact.containsKey("信息获取日期")) {
                    String contactsTime = contact.getString("信息获取日期");
                    contacts.setContactsTime(contactsTime);
                }
                if ("第一联系人信息".equals(str)) {
                    contacts.setContactsType("1");
                }else {
                    contacts.setContactsType("2");
                }
                if (contacts != null) {
                    contacts.setShCreditContactsId(UUIDUtils.getUUID());
                    contacts.setApplicationId(applicationId);
                    contacts.setCreateTime(dateTime);
                    contacts.setModifyTime(dateTime);
                    list.add(contacts);
                }

            }else{
                JSONArray jsonArray = shCreditJson1.getJSONArray(str);
                for (int i = 0; i < jsonArray.size(); i++) {
                    ShCreditContacts contacts = new ShCreditContacts();
                    Object object = jsonArray.get(i);
                    JSONObject contact = (JSONObject) JSON.toJSON(object);
                    if (contact.containsKey("联系人姓名")) {
                        String contactsName = contact.getString("联系人姓名");
                        contacts.setContactsName(contactsName);
                    }
                    if (contact.containsKey("联系人关系")) {
                        String contactsRelation = contact.getString("联系人关系");
                        contacts.setContactsRelation(contactsRelation);
                    }
                    if (contact.containsKey("联系电话")) {
                        String contactsNumber = contact.getString("联系电话");
                        contacts.setContactsNumber(contactsNumber);
                    }
                    if (contact.containsKey("信息获取日期")) {
                        String contactsTime = contact.getString("信息获取日期");
                        contacts.setContactsTime(contactsTime);
                    }
                    if ("第一联系人信息".equals(str)) {
                        contacts.setContactsType("1");
                    }else {
                        contacts.setContactsType("2");
                    }
                    if (contacts != null) {
                        contacts.setShCreditContactsId(UUIDUtils.getUUID());
                        contacts.setApplicationId(applicationId);
                        contacts.setCreateTime(dateTime);
                        contacts.setModifyTime(dateTime);
                        list.add(contacts);
                    }

                }
            }
        }
        return list;
    }

    /**
     * 上海资信贷款申请信息表
     * @param jsonObject
     * @param applicationId
     * @param dateTime
     * @return
     */
    public List<ShCreditLoansMessage> saveLoansMessage(JSONObject jsonObject,String applicationId,String dateTime){
        List<ShCreditLoansMessage> list = new ArrayList<ShCreditLoansMessage>();
        if (jsonObject.containsKey("贷款申请信息")) {
            JSONObject jsonObject1 = jsonObject.getJSONObject("贷款申请信息");
            if (jsonObject1.containsKey("贷款申请信息记录")) {
                Object object = jsonObject1.get("贷款申请信息记录");
                //判断是“贷款”是否为单个对象
                if (object instanceof JSONObject) {
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("贷款申请信息记录");
                    list = loansMessage(jsonObject2, list,applicationId,dateTime);
                }else {
                    JSONArray jsonArray = jsonObject1.getJSONArray("贷款申请信息记录");
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        list = loansMessage(jsonObject2, list,applicationId,dateTime);
                    }
                }
            }
        }
        if (list!= null && !list.isEmpty()) {
            int count = shCreditLoansMessageDao.saveAllEntity(list);
            Assert.isTrue(count > 0, "上海资信贷款申请信息表插入失败");
        }
        return list;
    }

    public List<ShCreditLoansMessage> loansMessage(JSONObject jsonObject,List<ShCreditLoansMessage> list,String applicationId,String dateTime){
        ShCreditLoansMessage shCreditLoansMessage = new ShCreditLoansMessage();
        if (jsonObject.containsKey("申请机构")) {
            String loansName = jsonObject.getString("申请机构");
            shCreditLoansMessage.setLoansName(loansName);
        }
        if (jsonObject.containsKey("申请时间")) {
            String loansTemps = jsonObject.getString("申请时间");
            shCreditLoansMessage.setLoansTemps(loansTemps);
        }
        if (jsonObject.containsKey("申请金额")) {
            String loansMoney = jsonObject.getString("申请金额");
            shCreditLoansMessage.setLoansMoney(loansMoney);
        }
        if (jsonObject.containsKey("申请月数")) {
            String loansMonth = jsonObject.getString("申请月数");
            shCreditLoansMessage.setLoansMonth(loansMonth);
        }
        if (jsonObject.containsKey("申请类型")) {
            String loansType = jsonObject.getString("申请类型");
            shCreditLoansMessage.setLoansType(loansType);
        }
        if (jsonObject.containsKey("申请状态")) {
            String loansState = jsonObject.getString("申请状态");
            shCreditLoansMessage.setLoansState(loansState);
        }
        if (jsonObject.containsKey("信息获取日期")) {
            String loansTime = jsonObject.getString("信息获取日期");
            shCreditLoansMessage.setLoansTime(loansTime);
        }
        if (shCreditLoansMessage != null) {
            shCreditLoansMessage.setShCreditLoansId(UUIDUtils.getUUID());
            shCreditLoansMessage.setApplicationId(applicationId);
            shCreditLoansMessage.setCreateTime(dateTime);
            shCreditLoansMessage.setModifyTime(dateTime);
            list.add(shCreditLoansMessage);
        }
        return list;
    }

    /**
     * 上海资信贷款交易信息表
     * @param jsonObject
     * @return
     */
    public  ShCreditLoansDeal saveLoansDeal(JSONObject jsonObject,String applicationId,String dateTime, ShangHaiCreditRedis shangHaiCreditRedis){
        ShCreditLoansDeal shCreditLoansDeal = new ShCreditLoansDeal();
        if (jsonObject.containsKey("贷款交易信息")) {
            JSONObject jsonObject1 = jsonObject.getJSONObject("贷款交易信息");
            if (jsonObject1.containsKey("信息概要")) {
                JSONObject loansDeal = jsonObject1.getJSONObject("信息概要");
                if (loansDeal.containsKey("贷款笔数")) {
                    String loansNumber = loansDeal.getString("贷款笔数");
                    shCreditLoansDeal.setLoansNumber(loansNumber);
                }
                if (loansDeal.containsKey("首贷日")) {
                    String loansTemps = loansDeal.getString("首贷日");
                    shCreditLoansDeal.setLoansTemps(loansTemps);
                }
                if (loansDeal.containsKey("最大授信额度")) {
                    String maxLimit = loansDeal.getString("最大授信额度");
                    shCreditLoansDeal.setMaxLimit(maxLimit);
                }
                if (loansDeal.containsKey("贷款总额")) {
                    String loansTotal = loansDeal.getString("贷款总额");
                    shCreditLoansDeal.setLoansTotal(loansTotal);
                }
                if (loansDeal.containsKey("贷款余额")) {
                    String loansBalance = loansDeal.getString("贷款余额");
                    shCreditLoansDeal.setLoansBalance(loansBalance);
                }
                if (loansDeal.containsKey("协定月还款")) {
                    String appointRefundMonth = loansDeal.getString("协定月还款");
                    shCreditLoansDeal.setAppointRefundMonth(appointRefundMonth);
                }
                if (loansDeal.containsKey("当前逾期总额")) {
                    String overdueAllMoney = loansDeal.getString("当前逾期总额");
                    shCreditLoansDeal.setOverdueAllMoney(overdueAllMoney);
                }
                if (loansDeal.containsKey("最高逾期金额")) {
                    String maxOverdueMoney = loansDeal.getString("最高逾期金额");
                    shCreditLoansDeal.setMaxOverdueMoney(maxOverdueMoney);
                }
                if (loansDeal.containsKey("最高逾期期数")) {
                    String maxOverdueNumber = loansDeal.getString("最高逾期期数");
                    shCreditLoansDeal.setMaxOverdueNumber(maxOverdueNumber);
                }
                if (shCreditLoansDeal != null) {
                    shCreditLoansDeal.setShCreditDealId(UUIDUtils.getUUID());
                    shCreditLoansDeal.setApplicationId(applicationId);
                    shCreditLoansDeal.setCreateTime(dateTime);
                    shCreditLoansDeal.setModifyTime(dateTime);
                    int count = shCreditLoansDealDao.saveEntity(shCreditLoansDeal);
                    Assert.isTrue(count>0, "上海资信贷款交易信息插入失败");

                    if(StringUtils.isNotBlank(shCreditLoansDeal.getLoansTemps())){
                        String loansTotalStr = shCreditLoansDeal.getLoansTotal();
                        String loansNumberStr = shCreditLoansDeal.getLoansNumber();
                        if(StringUtils.isNotBlank(loansTotalStr)){
                            shangHaiCreditRedis.setLoansTotal(loansTotalStr);
                        }
                        if(StringUtils.isNotBlank(loansNumberStr)){
                            shangHaiCreditRedis.setLoansNumber(loansNumberStr);
                        }
                    }
                }
            }
        }
        return shCreditLoansDeal;
    }

    /**
     * 上海资信贷款表
     * @param jsonObject
     * @return
     */
    public List<ShCreditLoans> saveLoans(JSONObject jsonObject, String applicationId, String dateTime, ShangHaiCreditRedis shangHaiCreditRedis){
        List<ShCreditLoans> list = new ArrayList<ShCreditLoans>();
        if (jsonObject.containsKey("贷款交易信息")) {
            JSONObject jsonObject1 = jsonObject.getJSONObject("贷款交易信息");
            if (jsonObject1.containsKey("贷款")) {
                Object object = jsonObject1.get("贷款");
                //判断是“贷款”是否为单个对象
                if (object instanceof JSONObject) {
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("贷款");
                    list = loansList(jsonObject2, list,applicationId,dateTime);
                }else {
                    JSONArray jsonArray = jsonObject1.getJSONArray("贷款");
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        list = loansList(jsonObject2, list,applicationId, dateTime);
                    }
                }
            }
        }

		if (list!= null && !list.isEmpty()) {
			Integer totalOverdueNumberMin = null;
			int totalOverdueNumberSum = 0;
			int sumLoans24N3m = 0;
			int sumLoans2411m = 0;
			BigDecimal avgRepaymentAmountMonthAmountSum = new BigDecimal(0);
			int avgRepaymentAmountMonthCount = list.size();
			BigDecimal actualPaymentAmountMax = new BigDecimal(0);
			BigDecimal latestOverdueSumOverdueTwoMonth = new BigDecimal(0);
			int sumLoans24NotNC3m = 0;

			for(int i = 0; i < list.size(); i++){
				ShCreditLoans shCreditLoansElem = list.get(i);
				String tempStr = shCreditLoansElem.getTotalOverdueNumber();
				try{
					int temp = Integer.parseInt(tempStr);
					if(null == totalOverdueNumberMin){
						totalOverdueNumberMin = temp;
					}else{
						totalOverdueNumberMin = (temp < totalOverdueNumberMin ? temp : totalOverdueNumberMin);
					}
					totalOverdueNumberSum += temp;
				}catch (Exception e){
					shCreditLogger.error("[上海资信] [applicationId = " + applicationId + "] [totalOverdueNumber=" + tempStr + "]数据解析错误", e);
				}
				String paymentStatusTemp = shCreditLoansElem.getPaymentStatus();
				if(StringUtils.isNotBlank(paymentStatusTemp)){
					int maxPaymentStatusLen = paymentStatusTemp.length();

					int startIndex = maxPaymentStatusLen - 3;
					startIndex = (startIndex < 0 ? 0 : startIndex);
					String[] temp = paymentStatusTemp.substring(startIndex, maxPaymentStatusLen).split("");

					for(String elem : temp){
						if("N".equals(elem)){
							sumLoans24N3m ++;
						}
						if(LOAN_PAYMENT_STATUS_CODE.contains(elem)){
							sumLoans24NotNC3m ++;
						}
					}
					if("1".equals(String.valueOf(paymentStatusTemp.charAt(maxPaymentStatusLen - 1)))){
						sumLoans2411m ++;
					}
				}

				String tempRepaymentAmountMonthStr = shCreditLoansElem.getRepaymentAmountMonth();
				BigDecimal tempRepaymentAmountMonthDB = null;
				if(StringUtils.isNotBlank(tempRepaymentAmountMonthStr)){
					try {
						tempRepaymentAmountMonthDB = new BigDecimal(tempRepaymentAmountMonthStr);
						avgRepaymentAmountMonthAmountSum = avgRepaymentAmountMonthAmountSum.add(tempRepaymentAmountMonthDB);
					}catch (Exception e){
						shCreditLogger.error("[上海资信] [applicationId = " + applicationId + "] [repaymentAmountMonth="+tempRepaymentAmountMonthStr+"]数据解析错误", e);
					}
				}

				String tempActualPaymentAmountStr = shCreditLoansElem.getActualPaymentAmount();
				BigDecimal tempActualPaymentAmountDB = null;
				if(StringUtils.isNotBlank(tempActualPaymentAmountStr)){
					try{
						tempActualPaymentAmountDB = new BigDecimal(tempActualPaymentAmountStr);
						if(tempActualPaymentAmountDB.compareTo(actualPaymentAmountMax) == 1){
							actualPaymentAmountMax = tempActualPaymentAmountDB;
						}
					}catch (Exception e){
						shCreditLogger.error("[上海资信] [applicationId = " + applicationId + "] [actualPaymentAmount="+tempActualPaymentAmountStr+"]数据解析错误", e);
					}
				}
				String accountStatusStr = shCreditLoansElem.getAccountStatus();
				if(StringUtils.isNotBlank(accountStatusStr) && !"结清".equals(accountStatusStr.trim())){
					BigDecimal subResultTemp = tempActualPaymentAmountDB.subtract(tempRepaymentAmountMonthDB);
					if(subResultTemp.compareTo(BIGDECIMAL_ZERO) == -1){
						String tempOverdueTwoMonth = list.get(i).getOverdueTwoMonth();
						if(StringUtils.isNotBlank(tempOverdueTwoMonth)){
							try{
								latestOverdueSumOverdueTwoMonth = latestOverdueSumOverdueTwoMonth.add(new BigDecimal(tempOverdueTwoMonth));
							}catch (Exception e){
								shCreditLogger.error("[上海资信] [applicationId = " + applicationId + "] [overdueTwoMonth="+tempOverdueTwoMonth+"]数据解析错误", e);
							}
						}
					}
				}
			}

			shangHaiCreditRedis.setShMinTotalOverdueNumber(String.valueOf(totalOverdueNumberMin) );
			shangHaiCreditRedis.setShSumLoans24N3m(String.valueOf(sumLoans24N3m));
			shangHaiCreditRedis.setShSumLoans2411m(String.valueOf(sumLoans2411m));
			shangHaiCreditRedis.setShAvgRepaymentAmountMonthTotalAmount(avgRepaymentAmountMonthAmountSum.toString());
			shangHaiCreditRedis.setShAvgRepaymentAmountMonthCount(String.valueOf(avgRepaymentAmountMonthCount));
			shangHaiCreditRedis.setShMaxActualPaymentAmount(actualPaymentAmountMax.toString());
			shangHaiCreditRedis.setShLatestOverdueSumOverdueTwoMonth(String.valueOf(latestOverdueSumOverdueTwoMonth));
			shangHaiCreditRedis.setShSumLoans24NotNC3m(String.valueOf(sumLoans24NotNC3m));

			shangHaiCreditRedis.setShSumTotalOverdueNumber(String.valueOf(totalOverdueNumberSum));

			int count = shCreditLoansDao.saveAllEntity(list);
			Assert.isTrue(count > 0, "[上海资信] 贷款表插入失败");
		}
        return list;
    }

    /**
     * 贷款
     * @param jsonObject
     * @param list
     * @return
     */
    public  List<ShCreditLoans> loansList(JSONObject jsonObject,List<ShCreditLoans> list,String applicationId,String dateTime){
        ShCreditLoans shCreditLoans = new ShCreditLoans();
        if (jsonObject.containsKey("贷款项目")) {
            String loansProject = jsonObject.getString("贷款项目");
            shCreditLoans.setLoansProject(loansProject);
        }
        if (jsonObject.containsKey("机构名称")) {
            String organizationName = jsonObject.getString("机构名称");
            shCreditLoans.setOrganizationName(organizationName);
        }
        if (jsonObject.containsKey("授信额度")) {
            String limitMoney = jsonObject.getString("授信额度");
            shCreditLoans.setLimitMoney(limitMoney);
        }
        if (jsonObject.containsKey("担保方式")) {
            String guaranteeType = jsonObject.getString("担保方式");
            shCreditLoans.setGuaranteeType(guaranteeType);
        }
        if (jsonObject.containsKey("开户日期")) {
            String openDate = jsonObject.getString("开户日期");
            shCreditLoans.setOpenDate(openDate);
        }
        if (jsonObject.containsKey("币种")) {
            String currency = jsonObject.getString("币种");
            shCreditLoans.setCurrency(currency);
        }
        if (jsonObject.containsKey("发生地")) {
            String locality = jsonObject.getString("发生地");
            shCreditLoans.setLocality(locality);
        }
        if (jsonObject.containsKey("共享授信额度")) {
            String shareLimitMoney = jsonObject.getString("共享授信额度");
            shCreditLoans.setShareLimitMoney(shareLimitMoney);
        }
        if (jsonObject.containsKey("最大负债额")) {
            String maxLiabilitiesMoney = jsonObject.getString("最大负债额");
            shCreditLoans.setMaxLiabilitiesMoney(maxLiabilitiesMoney);
        }
        if (jsonObject.containsKey("还款频率")) {
            String repaymentFrequency = jsonObject.getString("还款频率");
            shCreditLoans.setRepaymentFrequency(repaymentFrequency);
        }
        if (jsonObject.containsKey("期末贷款余额")) {
            String endingIoanBalance = jsonObject.getString("期末贷款余额");
            shCreditLoans.setEndingIoanBalance(endingIoanBalance);
        }
        if (jsonObject.containsKey("剩余还款月数")) {
            String leftTermsLoan = jsonObject.getString("剩余还款月数");
            shCreditLoans.setLeftTermsLoan(leftTermsLoan);
        }
        if (jsonObject.containsKey("本月应还款日期")) {
            String repaymentDateMonth = jsonObject.getString("本月应还款日期");
            shCreditLoans.setRepaymentDateMonth(repaymentDateMonth);
        }
        if (jsonObject.containsKey("本月应还款金额")) {
            String repaymentAmountMonth = jsonObject.getString("本月应还款金额");
            shCreditLoans.setRepaymentAmountMonth(repaymentAmountMonth);
        }
        if (jsonObject.containsKey("帐户状态")) {
            String accountStatus = jsonObject.getString("帐户状态");
            shCreditLoans.setAccountStatus(accountStatus);
        }
        if (jsonObject.containsKey("实际还款日期")) {
            String actualDateRepayment = jsonObject.getString("实际还款日期");
            shCreditLoans.setActualDateRepayment(actualDateRepayment);
        }
        if (jsonObject.containsKey("实际还款金额")) {
            String actualPaymentAmount = jsonObject.getString("实际还款金额");
            shCreditLoans.setActualPaymentAmount(actualPaymentAmount);
        }
        if (jsonObject.containsKey("当前逾期总额")) {
            String nowOverdueLimit = jsonObject.getString("当前逾期总额");
            shCreditLoans.setNowOverdueLimit(nowOverdueLimit);
        }
        if (jsonObject.containsKey("当前逾期期数")) {
            String nowOverdueNumber = jsonObject.getString("当前逾期期数");
            shCreditLoans.setNowOverdueNumber(nowOverdueNumber);
        }
        if (jsonObject.containsKey("累计逾期期数")) {
            String totalOverdueNumber = jsonObject.getString("累计逾期期数");
            shCreditLoans.setTotalOverdueNumber(totalOverdueNumber);
        }
        if (jsonObject.containsKey("还款频率")) {
            String repaymentFrequency = jsonObject.getString("还款频率");
            shCreditLoans.setRepaymentFrequency(repaymentFrequency);
        }
        if (jsonObject.containsKey("最高逾期期数")) {
            String maxOverdueNumber = jsonObject.getString("最高逾期期数");
            shCreditLoans.setMaxOverdueNumber(maxOverdueNumber);
        }
        if (jsonObject.containsKey("二十四月内各月还款状况")) {
            String paymentStatus = jsonObject.getString("二十四月内各月还款状况");
            shCreditLoans.setPaymentStatus(paymentStatus);
        }
        if (jsonObject.containsKey("逾期31-60天未归还贷款本金")) {
            String overdueTwoMonth = jsonObject.getString("逾期31-60天未归还贷款本金");
            shCreditLoans.setOverdueTwoMonth(overdueTwoMonth);
        }
        if (jsonObject.containsKey("逾期61-90天未归还贷款本金")) {
            String overdueThreeMonth = jsonObject.getString("逾期61-90天未归还贷款本金");
            shCreditLoans.setOverdueThreeMonth(overdueThreeMonth);
        }
        if (jsonObject.containsKey("逾期91-180天未归还贷款本金")) {
            String overdueSixMonth = jsonObject.getString("逾期91-180天未归还贷款本金");
            shCreditLoans.setOverdueSixMonth(overdueSixMonth);
        }
        if (jsonObject.containsKey("逾期180天以上未归还贷款本金")) {
            String overdueYearMonth = jsonObject.getString("逾期180天以上未归还贷款本金");
            shCreditLoans.setOverdueYearMonth(overdueYearMonth);
        }
        if (jsonObject.containsKey("信息获取日期")) {
            String loansTime = jsonObject.getString("信息获取日期");
            shCreditLoans.setLoansTime(loansTime);
        }
        if (shCreditLoans != null) {
            shCreditLoans.setLoansId(UUIDUtils.getUUID());
            shCreditLoans.setApplicationId(applicationId);
            shCreditLoans.setCreateTime(dateTime);
            shCreditLoans.setModifyTime(dateTime);
            list.add(shCreditLoans);
        }
        return list;

    }
    /**
     * 上海资信为他人担保信息表
     * @param jsonObject
     * @return
     */
    public  List<ShCreditGuarantee> saveGuarantee(JSONObject jsonObject,String applicationId,String dateTime){

        List<ShCreditGuarantee> list = new ArrayList<ShCreditGuarantee>();
            if (jsonObject.containsKey("为他人担保信息")) {
                JSONObject jsonObject2 = jsonObject.getJSONObject("为他人担保信息");
                if (jsonObject2.containsKey("担保信息记录")) {
                    Object object = jsonObject2.get("担保信息记录");
                    if (object instanceof JSONObject) {
                        JSONObject jsonObject3 = jsonObject2.getJSONObject("担保信息记录");
                        list = guaranteeList(jsonObject3, list,applicationId,dateTime);
                    }else {
                        JSONArray jsonArray = jsonObject2.getJSONArray("担保信息记录");
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JSONObject jsonObject3 = jsonArray.getJSONObject(i);
                            list = guaranteeList(jsonObject3, list,applicationId,dateTime);
                        }
                    }
                }
        }
            if (list!= null && !list.isEmpty()) {
                int count = shCreditGuaranteeDao.saveAllEntity(list);
                Assert.isTrue(count>0, "上海资信为他人担保信息表插入失败");
            }

        return list;

    }

	/**
	 * 为他人担保信息
	 * @param jsonObject
	 * @param list
	 * @param applicationId
	 * @param dateTime
	 * @return
	 */
    public  List<ShCreditGuarantee> guaranteeList(JSONObject jsonObject,List<ShCreditGuarantee> list,String applicationId,String dateTime){

        ShCreditGuarantee shCreditGuarantee = new ShCreditGuarantee();

        if (jsonObject.containsKey("担保项目")) {
            String guaranteeProject = jsonObject.getString("担保项目");
            shCreditGuarantee.setGuaranteeProject(guaranteeProject);
        }
        if (jsonObject.containsKey("担保日期")) {
            String guaranteeDate = jsonObject.getString("担保日期");
            shCreditGuarantee.setGuaranteeDate(guaranteeDate);
        }
        if (jsonObject.containsKey("担保金额")) {
            String guaranteeMoney = jsonObject.getString("担保金额");
            shCreditGuarantee.setGuaranteeMoney(guaranteeMoney);
        }
        if (jsonObject.containsKey("担保关系")) {
            String guaranteeRelation = jsonObject.getString("担保关系");
            shCreditGuarantee.setGuaranteeRelation(guaranteeRelation);
        }
        if (jsonObject.containsKey("信息获取日期")) {
            String guaranteeTime = jsonObject.getString("信息获取日期");
            shCreditGuarantee.setGuaranteeTime(guaranteeTime);
        }
        if (shCreditGuarantee != null) {
            shCreditGuarantee.setGuaranteeId(UUIDUtils.getUUID());
            shCreditGuarantee.setApplicationId(applicationId);
            shCreditGuarantee.setCreateTime(dateTime);
            shCreditGuarantee.setModifyTime(dateTime);
            list.add(shCreditGuarantee);
        }
        return list;

    }
    /**
     * 上海资信特殊交易信息表
     * @param jsonObject
     * @return
     */
    public  List<ShCreditSpecialDeal> saveSpecialDeal(JSONObject jsonObject,String applicationId,String dateTime){
        List<ShCreditSpecialDeal> list = new ArrayList<ShCreditSpecialDeal>();
            if (jsonObject.containsKey("特殊交易信息")) {
                JSONObject jsonObject2 = jsonObject.getJSONObject("特殊交易信息");
                if (jsonObject2.containsKey("详细记录")) {
                    Object object = jsonObject2.get("详细记录");
                    if (object instanceof JSONObject) {
                        JSONObject jsonObject3 = jsonObject2.getJSONObject("详细记录");
                        list = specialDealList(jsonObject3, list,applicationId,dateTime);
                    }else {
                        JSONArray jsonArray = jsonObject2.getJSONArray("详细记录");
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JSONObject jsonObject3 = jsonArray.getJSONObject(i);
                            list = specialDealList(jsonObject3, list,applicationId,dateTime);
                        }
                    }
                }
        }
            if (list!= null && !list.isEmpty()) {
                int count = shCreditSpecialDealDao.saveAllEntity(list);
                Assert.isTrue(count>0, "上海资信特殊交易信息表插入失败");
            }

        return list;

    }
    /**
     * 特殊交易信息
     * @param jsonObject
     * @param list
     * @return
     */
    public  List<ShCreditSpecialDeal> specialDealList(JSONObject jsonObject,List<ShCreditSpecialDeal> list,String applicationId,String dateTime){
        ShCreditSpecialDeal shCreditSpecialDeal = new ShCreditSpecialDeal();
        if (jsonObject.containsKey("记录来源")) {
            String recordSource = jsonObject.getString("记录来源");
            shCreditSpecialDeal.setRecordSource(recordSource);
        }
        if (jsonObject.containsKey("记录类型")) {
            String recordType = jsonObject.getString("记录类型");
            shCreditSpecialDeal.setrecordType(recordType);
        }
        if (jsonObject.containsKey("发生日期")) {
            String occurrenceDate = jsonObject.getString("发生日期");
            shCreditSpecialDeal.setOccurrenceDate(occurrenceDate);
        }
        if (jsonObject.containsKey("变更月数")) {
            String changeMonths = jsonObject.getString("变更月数");
            shCreditSpecialDeal.setChangeMonths(changeMonths);
        }
        if (jsonObject.containsKey("发生金额")) {
            String actualAmount = jsonObject.getString("发生金额");
            shCreditSpecialDeal.setActualAmount(actualAmount);
        }
        if (jsonObject.containsKey("明细信息")) {
            String detail = jsonObject.getString("明细信息");
            shCreditSpecialDeal.setDetail(detail);
        }
        if (jsonObject.containsKey("信息获取日期")) {
            String specialDealTime = jsonObject.getString("信息获取日期");
            shCreditSpecialDeal.setSpecialDealTime(specialDealTime);
        }
        if (shCreditSpecialDeal != null) {
            shCreditSpecialDeal.setSpecialDealId(UUIDUtils.getUUID());
            shCreditSpecialDeal.setApplicationId(applicationId);
            shCreditSpecialDeal.setCreateTime(dateTime);
            shCreditSpecialDeal.setModifyTime(dateTime);
            list.add(shCreditSpecialDeal);
        }

        return list;

    }
    /**
     * 上海资信查询信息表
     * @param jsonObject
     * @return
     */
    public  List<ShCreditSearchInformation> saveSearchInformation(JSONObject jsonObject,String applicationId,String dateTime){
        List<ShCreditSearchInformation> list = new ArrayList<ShCreditSearchInformation>();
            if (jsonObject.containsKey("查询信息")) {
                JSONObject jsonObject2 = jsonObject.getJSONObject("查询信息");
                if (jsonObject2.containsKey("查询记录")) {
                    Object object = jsonObject2.get("查询记录");
                    if (object instanceof JSONObject) {
                        JSONObject jsonObject3 = jsonObject2.getJSONObject("查询记录");
                        list = searchInformationList(jsonObject3, list,applicationId,dateTime);
                    }else {
                        JSONArray jsonArray = jsonObject2.getJSONArray("查询记录");
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JSONObject jsonObject3 = jsonArray.getJSONObject(i);
                            list = searchInformationList(jsonObject3, list,applicationId,dateTime);
                        }
                    }
            }
        }
            if (list!= null && !list.isEmpty()) {
                int count = shCreditSearchInformationDao.saveAllEntity(list);
                Assert.isTrue(count>0, "上海资信查询信息表插入失败");
            }

        return list;

    }
    /**
     * 查询信息
     * @param jsonObject
     * @param list
     * @return
     */
    public  List<ShCreditSearchInformation> searchInformationList(JSONObject jsonObject,List<ShCreditSearchInformation> list,String applicationId,String dateTime){
        ShCreditSearchInformation shCreditSearchInformation = new ShCreditSearchInformation();
        if (jsonObject.containsKey("查询者类型")) {
            String querierType = jsonObject.getString("查询者类型");
            shCreditSearchInformation.setQuerierType(querierType);
        }
        if (jsonObject.containsKey("查询原因")) {
            String cause = jsonObject.getString("查询原因");
            shCreditSearchInformation.setCause(cause);
        }
        if (jsonObject.containsKey("查询日期")) {
            String queryDate = jsonObject.getString("查询日期");
            shCreditSearchInformation.setQueryDate(queryDate);
        }
        if (shCreditSearchInformation != null) {
            shCreditSearchInformation.setSearchInformationId(UUIDUtils.getUUID());
            shCreditSearchInformation.setApplicationId(applicationId);
            shCreditSearchInformation.setCreateTime(dateTime);
            shCreditSearchInformation.setModifyTime(dateTime);
            list.add(shCreditSearchInformation);
        }

        return list;
	
	}
    /**
     * 上海资信提示表
     * @param jsonObject
     * @param applicationId
     * @param dateTime
     * @return
     */
    public List<ShCreditPrompt> saveShCreditPrompt(JSONObject jsonObject,String applicationId,String dateTime){
        List<ShCreditPrompt> list = new ArrayList<ShCreditPrompt>();
        if (jsonObject.containsKey("资信提示信息")) {
            JSONObject jsonObject2 = jsonObject.getJSONObject("资信提示信息");
            if (jsonObject2.containsKey("资信提示")) {
                Object object = jsonObject2.get("资信提示");
                if (object instanceof JSONObject) {
                    JSONObject jsonObject3 = jsonObject2.getJSONObject("资信提示");
                    list = PromptList(jsonObject3, list,applicationId,dateTime);
                }else {
                    JSONArray jsonArray = jsonObject2.getJSONArray("资信提示");
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject jsonObject3 = jsonArray.getJSONObject(i);
                        list = PromptList(jsonObject3, list,applicationId,dateTime);
                    }
                }
            }
        }
        if (list!= null && !list.isEmpty()) {
            int count = shCreditPromptDao.saveAllEntity(list);
            Assert.isTrue(count>0, "上海资信查询信息表插入失败");
        }
        return list;
	
	}

    /**
     * 资信提示
     * @param jsonObject
     * @param list
     * @param applicationId
     * @param dateTime
     * @return
     */
    public List<ShCreditPrompt> PromptList(JSONObject jsonObject,List<ShCreditPrompt> list,String applicationId,String dateTime){
        ShCreditPrompt shCreditPrompt = new ShCreditPrompt();
        if (jsonObject.containsKey("项目")) {
            String promptProject = jsonObject.getString("项目");
            shCreditPrompt.setPromptProject(promptProject);
        }
        if (jsonObject.containsKey("提示内容")) {
            String promptMessage = jsonObject.getString("提示内容");
            shCreditPrompt.setPromptMessage(promptMessage);
        }
        if (jsonObject.containsKey("提示时间")) {
            String promptTime = jsonObject.getString("提示时间");
            shCreditPrompt.setPromptTime(promptTime);
        }
        if (shCreditPrompt != null) {
            shCreditPrompt.setShCreditPromptId(UUIDUtils.getUUID());
            shCreditPrompt.setApplicationId(applicationId);
            shCreditPrompt.setCreateTime(dateTime);
            shCreditPrompt.setModifyTime(dateTime);
            list.add(shCreditPrompt);
        }
        return list;
	
	}


    @Override
    public ShCreditTheme queryByApplicationId(String applicationId) {
        //判断申请单号是否为空
        Assert.hasText(applicationId, "申请单号为空");
        ShCredit shCredit = shCreditDao.queryByApplicationId(applicationId);
        List<ShCreditAddress> address = shCreditAddressDao.queryByApplicationId(applicationId);
        List<ShCreditWork> works = shCreditWorkDao.queryByApplicationId(applicationId);
        List<ShCreditContacts> contacts = shCreditContactsDao.queryByApplicationId(applicationId);
        ShCreditLoansDeal saveLoansDeal = shCreditLoansDealDao.queryByApplicationId(applicationId);
        List<ShCreditLoans> savaLoans = shCreditLoansDao.queryByApplicationId(applicationId);

        List<ShCreditGuarantee> saveGuarantee = getAllGuranteeByApplicationId(applicationId);
        List<ShCreditSpecialDeal> saveSpecialDeal = getAllSpecialDealByApplicationId(applicationId);
        List<ShCreditSearchInformation> saveSearchInformation = shCreditSearchInformationDao.queryByApplicationId(applicationId);
        List<ShCreditLoansMessage> saveLoansMessage = shCreditLoansMessageDao.queryByApplicationId(applicationId);
        List<ShCreditPrompt> saveShCreditPrompt = shCreditPromptDao.queryByApplicationId(applicationId);
        ShCreditTheme shCreditTheme = new ShCreditTheme();
        shCreditTheme.setShCredit(shCredit);
        shCreditTheme.setAddress(sortAdressList(address));
        shCreditTheme.setWorks(works);
        shCreditTheme.setContacts(contacts);
        shCreditTheme.setSaveLoansDeal(saveLoansDeal);
        shCreditTheme.setSavaLoans(sortList(savaLoans));
        shCreditTheme.setSaveGuarantee(saveGuarantee);
        shCreditTheme.setSaveSpecialDeal(saveSpecialDeal);
        shCreditTheme.setSaveSearchInformation(saveSearchInformation);
        shCreditTheme.setSaveLoansMessage(saveLoansMessage);
        shCreditTheme.setSaveShCreditPrompt(saveShCreditPrompt);
        return shCreditTheme;
    }


    @Override
    public List<ShCreditLoans> queryAllDealsByApplicationId(String applicationId) {

        List<ShCreditLoans> savaLoans = shCreditLoansDao.queryByApplicationId(applicationId);

        return sortList(savaLoans);
	}

    public static List<ShCreditLoans> sortList(List<ShCreditLoans> savaLoans){
        Collections.sort(savaLoans,new Comparator<ShCreditLoans>() {
            @Override
            public int compare(ShCreditLoans o1, ShCreditLoans o2) {
                String a = o1.getLoansProject();
                String b = o2.getLoansProject();
                String number1 = a.split("[.]")[0];
                Integer index1 = Integer.valueOf(number1);
                String number2 = b.split("[.]")[0];
                Integer index2 = Integer.valueOf(number2);
                return index1-index2;
            }
        });
		return savaLoans;
	
	}

    public List<ShCreditAddress> sortAdressList(List<ShCreditAddress> address){
        Collections.sort(address,new Comparator<ShCreditAddress>() {
            @Override
            public int compare(ShCreditAddress o1, ShCreditAddress o2) {
                String a = o1.getAddressNumber();
                String b = o2.getAddressNumber();
                String number1 = a.split("[.]")[0];
                Integer index1 = Integer.valueOf(number1);
                String number2 = b.split("[.]")[0];
                Integer index2 = Integer.valueOf(number2);
                return index1-index2;
            }
        });

        return address;

	}


    @Override
    public List<ShCreditGuarantee> getAllGuranteeByApplicationId(String applicationId) {
        List<ShCreditGuarantee> guarantees = shCreditGuaranteeDao.queryByApplicationId(applicationId);
        Collections.sort(guarantees,new Comparator<ShCreditGuarantee>() {
            @Override
            public int compare(ShCreditGuarantee o1, ShCreditGuarantee o2) {
                String a = o1.getGuaranteeProject();
                String b = o2.getGuaranteeProject();
                String number1 = a.split("[.]")[0];
                Integer index1 = Integer.valueOf(number1);
                String number2 = b.split("[.]")[0];
                Integer index2 = Integer.valueOf(number2);
                return index1-index2;
            }
        });
        return guarantees;
	}


    @Override
    public List<ShCreditSpecialDeal> getAllSpecialDealByApplicationId(String applicationId) {

        List<ShCreditSpecialDeal> saveSpecialDeal = shCreditSpecialDealDao.queryByApplicationId(applicationId);

        Collections.sort(saveSpecialDeal,new Comparator<ShCreditSpecialDeal>() {
            @Override
            public int compare(ShCreditSpecialDeal o1, ShCreditSpecialDeal o2) {
                String a = o1.getRecordSource();
                String b = o2.getRecordSource();
                String number1 = a.split("[.]")[0];
                Integer index1 = Integer.valueOf(number1);
                String number2 = b.split("[.]")[0];
                Integer index2 = Integer.valueOf(number2);
                return index1-index2;
            }
        });
        return saveSpecialDeal;
    }

    /**
     * 处理禁止项 --> 账户状态非正常, 账户状态非正常 账户状态异常为“关注”、“次级”、“可疑”、“损失” 表示命中 拒绝 (每一笔)
     * @return
     */
    private void processBanAccountAbnormal(List<Map<String, Object>> banOption, List<ShCreditLoans> loans) {
        Map<String, Object> accountAbnormal = new HashMap<>();
        String accountAbnormalValue = "";
        if(!CollectionUtils.isEmpty(loans)){
            for(ShCreditLoans elem : loans){
                accountAbnormalValue += (elem.getAccountStatus() + "|");
            }
        }

        accountAbnormal.put(BanCodeEnum.SHANG_HAI_CREDIT_ACCOUNT_ABNORMAL.getCode(), accountAbnormalValue);
		banOption.add(accountAbnormal);
        return;
    }

    /**
     * 处理禁止项 --> 当前逾期 (每一笔)
     */
    private void processBanIsOverdue(List<Map<String, Object>> banOption, List<ShCreditLoans> loans){
        //当前逾期 账户状态未结清，实际还款金额减去本月应还金额小于0拒绝
        if(!CollectionUtils.isEmpty(loans)){
        	for(ShCreditLoans elem : loans){
				Map<String, Object> isOverdue = new HashMap<>();
				Double tempValue = 0D;
				if(null != loans && !("结清".equals(elem.getAccountStatus()))){
					String repaymentAmountMonthStr =  elem.getRepaymentAmountMonth();
					String actualPaymentAmountStr = elem.getActualPaymentAmount();

					if(StringUtils.isBlank(repaymentAmountMonthStr)){
						repaymentAmountMonthStr = "0";
					}

					if(StringUtils.isBlank(actualPaymentAmountStr)){
						actualPaymentAmountStr = "0";
					}

					BigDecimal repaymentAmountMonthDB = new BigDecimal(repaymentAmountMonthStr);
					BigDecimal actualPaymentAmountDB = new BigDecimal(actualPaymentAmountStr);

					BigDecimal temp = actualPaymentAmountDB.subtract(repaymentAmountMonthDB);
					tempValue = temp.doubleValue();
				}
				isOverdue.put(BanCodeEnum.SHANG_HAI_CREDIT_IS_OVERDUE.getCode(), tempValue);
				banOption.add(isOverdue);
			}
		}
    }

    /**
     * 单笔贷款最高逾期期数, 最高逾期期数大于等于3拒绝 (每一笔)
     * @param banOption
     * @param loan
     */
    private void processSingleCount(List<Map<String, Object>> banOption, List<ShCreditLoans> loan){
    	if(!CollectionUtils.isEmpty(loan)){
    		for(ShCreditLoans elem : loan){
				Map<String, Object> singleCount = new HashMap<>();
				int maxOverdue = 0;
				if(null != loan && StringUtils.isNotBlank(elem.getMaxOverdueNumber())){
					maxOverdue = Integer.parseInt(elem.getMaxOverdueNumber());
				}
				singleCount.put(BanCodeEnum.SHANG_HAI_CREDIT_OVERDUE_SINGLE_COUNT.getCode(), maxOverdue);
				banOption.add(singleCount);
			}
		}
    }

    /**
     * 单笔贷款累计逾期期数,累计逾期期数大于等于5拒绝 (每一笔)
     * @param banOption
     * @param loan
     */
    private void processSingleTotal(List<Map<String, Object>> banOption, List<ShCreditLoans> loan){
    	if(!CollectionUtils.isEmpty(loan)){
    		for(ShCreditLoans elem : loan){
				Map<String, Object> overdueSingleTotal = new HashMap<>();
				int maxTotalOverdue = 0;
				if(null != loan && StringUtils.isNotBlank(elem.getTotalOverdueNumber())){
					maxTotalOverdue = Integer.parseInt(elem.getTotalOverdueNumber());
				}
				overdueSingleTotal.put(BanCodeEnum.SHANG_HAI_CREDIT_OVERDUE_SINGLE_TOTAL.getCode(), maxTotalOverdue);
				banOption.add(overdueSingleTotal);
			}
		}
    }

    /**
     * 单笔贷款近3个月有2个M1及以上,24月内各月还款状况近3个月显示有2个“1”，及以上表示命中拒绝 (每一笔)
     * @param banOption
     * @param loan
     */
    private void processThreeMonthHave2M1(List<Map<String, Object>> banOption, List<ShCreditLoans> loan){
        if(!CollectionUtils.isEmpty(loan)){
        	for(ShCreditLoans elem : loan){
				Map<String, Object> threeMonthHave2M1 = new HashMap<>();
				int m1Count = 0;
				if(null != loan && StringUtils.isNotBlank(elem.getPaymentStatus())){
					m1Count = countMForShangHaiCredit(elem.getPaymentStatus(),3, "1");
				}
				threeMonthHave2M1.put(BanCodeEnum.SHANG_HAI_CREDIT_3_MONTH_HAVE_2_M1.getCode(), m1Count);
				banOption.add(threeMonthHave2M1);
			}
		}
    }

	/**
	 * 单笔贷款近6个月有M2及以上,24月内各月还款状况近6个月显示有“2”及以上表示命中拒绝 (每一笔)
	 * @param banOption
	 * @param loans
	 */
	private void processSixMonthHave1M2(List<Map<String, Object>> banOption, List<ShCreditLoans> loans){
    	if(!CollectionUtils.isEmpty(loans)){
    		for(ShCreditLoans elem : loans){
				Map<String, Object> sixMonthHave1M1 = new HashMap<>();
				int sixMonthHave1M2Count = 0;

				if(null != elem && StringUtils.isNotBlank(elem.getPaymentStatus())){
					sixMonthHave1M2Count = countMForShangHaiCredit(elem.getPaymentStatus(),6, "2");
				}

				sixMonthHave1M1.put(BanCodeEnum.SHANG_HAI_CREDIT_6_MONTH_HAVE_1_M2.getCode(), sixMonthHave1M2Count);
				banOption.add(sixMonthHave1M1);
			}
		}
    }

    /**
     * 单笔贷款近6个月有3个M1,24月内各月还款状况近6个月显示有三个“1”及以上表示命中拒绝 (每一笔)
     * @param banOption
     * @param loan
     */
    private void processSixMonthHave3M1(List<Map<String, Object>> banOption, List<ShCreditLoans> loan){
    	if(!CollectionUtils.isEmpty(loan)){
    		for(ShCreditLoans elem : loan){
				Map<String, Object> sixMonthHave3M1 = new HashMap<>();

				int sixMontHave3M1Count = 0;
				if(null != loan){
					sixMontHave3M1Count = countMForShangHaiCredit(elem.getPaymentStatus(),6, "1");
				}

				sixMonthHave3M1.put(BanCodeEnum.SHANG_HAI_CREDIT_6_MONTH_3_M1.getCode(), sixMontHave3M1Count);
				banOption.add(sixMonthHave3M1);
			}
		}
    }

    /**
     * 单笔贷款近12个月有4个M1, 24月内各月还款状况近12个月显示有四个“1”及以上表示命中拒绝 (每一笔)
     * @param banOption
     * @param loans
     */
    private void processTwelveMonth4M1(List<Map<String, Object>> banOption, List<ShCreditLoans> loans){
    	if(!CollectionUtils.isEmpty(loans)){
    		for(ShCreditLoans elem : loans){
				Map<String, Object> twelveMonth4M1 = new HashMap<>();
				int twelveMonthHave4M1 = 0;
				if(StringUtils.isNotBlank(elem.getPaymentStatus())){
					twelveMonthHave4M1 = countMForShangHaiCredit(elem.getPaymentStatus(),12, "1");
				}
				twelveMonth4M1.put(BanCodeEnum.SHANG_HAI_CREDIT_12_MONTH_4_M1.getCode(), twelveMonthHave4M1);
				banOption.add(twelveMonth4M1);
			}
		}
    }

    /**
     * 累计逾期期数,所有贷款累计逾期期数的和大于等于6拒绝 （求和）
     * @param banOption
     * @param loans
     */
    private void processOverdueTotal(List<Map<String, Object>> banOption, List<ShCreditLoans> loans){
        Map<String, Object> overdueTotal = new HashMap<>();

        int sumOverdue = 0;
        if(!CollectionUtils.isEmpty(loans)){
        	for(ShCreditLoans elem : loans){
				if(StringUtils.isNotBlank(elem.getTotalOverdueNumber())){
					int temp = Integer.parseInt(elem.getTotalOverdueNumber());
					sumOverdue = sumOverdue + temp;
				}
			}
		}
        overdueTotal.put(BanCodeEnum.SHANG_HAI_CREDIT_OVERDUE_TOTAL.getCode(), sumOverdue);
		banOption.add(overdueTotal);
    }

    /**
     * 长期拖欠, 特殊交易信息记录类型显示“长期拖欠”表示命中拒绝 (每一笔)
	 * @param banOption
	 * @param specialLoan
	 */
	private void processTardy(List<Map<String, Object>> banOption,List<ShCreditSpecialDeal> specialLoan){
        if(!CollectionUtils.isEmpty(specialLoan)){
        	for(ShCreditSpecialDeal elem : specialLoan){
				if(StringUtils.isNotBlank(elem.getrecordType())){
					Map<String, Object> trady = new HashMap<>();
					trady.put(BanCodeEnum.SHANG_HAI_CREDIT_TARDY.getCode(), elem.getrecordType());
					banOption.add(trady);
				}
			}
		}
    }

	/**
	 * 个人担保代偿, 特殊交易信息记录类型显示“个人担保代偿”表示命中拒绝 (每一笔)
	 * @param banOption
	 * @param specialLoan
	 */
	private void processIndividualGuaranteeIndemnity(List<Map<String, Object>> banOption, List<ShCreditSpecialDeal> specialLoan){
		if(!CollectionUtils.isEmpty(specialLoan)){
			for(ShCreditSpecialDeal elem : specialLoan){
				if(StringUtils.isNotBlank(elem.getrecordType())){
					Map<String, Object> individualGuaranteeIndemnity = new HashMap<>();
					individualGuaranteeIndemnity.put(BanCodeEnum.SHANG_HAI_CREDIT_INDIVIDUAL_GUARANTEE_INDEMNITY.getCode(), elem.getrecordType());
					banOption.add(individualGuaranteeIndemnity);
				}
			}
		}
	}

    /**
     * 最高逾期期数最大值, 所有贷款中最高逾期期数的最大值大于等于3拒绝
     * @param banOption
     * @param loans
     */
    private void processSingleMaxOverdue(List<Map<String, Object>> banOption, List<ShCreditLoans> loans){
        Map<String, Object> individualGuaranteeIndemnity = new HashMap<>();

        int maxOverdueCount = 0;
        if(!CollectionUtils.isEmpty(loans)){
			for(ShCreditLoans elem : loans){
				if(StringUtils.isNotBlank(elem.getMaxOverdueNumber())){
					int temp = Integer.parseInt(elem.getMaxOverdueNumber());
					maxOverdueCount = (temp > maxOverdueCount ? temp : maxOverdueCount);
				}
			}
		}
        individualGuaranteeIndemnity.put(BanCodeEnum.SHANG_HAI_CREDIT_SINGLE_MAX_OVERDE.getCode(), maxOverdueCount);
		banOption.add(individualGuaranteeIndemnity);
    }

	/**
	 * 解析
	 *  "二十四月内各月还款状况": "////////////////*NNNNNNN",
	 * @param str
	 * @param monthCount
	 * @param equalsStr
	 * @return
	 */
	public static int countMForShangHaiCredit(String str, int monthCount, String equalsStr){
		if(StringUtils.isBlank(str)){
			return 0;
		}
		int count = 0;
		String tempStr = "";
		if(str.length() < monthCount){
			tempStr = str;
		}else{
			tempStr = str.substring(str.length() - monthCount, str.length());
		}
		String[] tempArray = tempStr.split("");
		for(String elem : tempArray){
			if(equalsStr.equals(elem)){
				count ++;
			}
		}
		return count;
	}
}

