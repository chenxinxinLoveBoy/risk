package com.shangyong.test;
//package com.honglu.test;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import com.honglu.AccountApplication;
//import com.honglu.backend.common.RuleResult;
//import com.honglu.backend.entity.Application;
//import com.honglu.backend.service.MhSheBaoService;
//import com.honglu.backend.service.impl.ApplicationServiceImpl;
//import com.honglu.backend.service.impl.TdLoanInterfaceServicesImpl;
//import com.honglu.backend.utils.DateUtils;
//
///**
// * @author xiangxianjin
// */
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = AccountApplication.class)
//@WebAppConfiguration
//public class MhSheBaoTest {
//	@Autowired
//	private MhSheBaoService mhSheBaoServiceImpl;
//	@Autowired
//	private TdLoanInterfaceServicesImpl tdLoanInterfaceServicesImpl;
//	@Autowired
//	private ApplicationServiceImpl applicationServiceImpl;
//	
//
////	@Test
//	public void mhSheBaoReport(){
//		RuleResult result;
//		try {
//			Map<String, Object> params = new HashMap<String, Object>();
//			params.put("applicationNumber","e8a4537363c54a52ba8b09316b8630ea"); // 申请编号
//		  	params.put("platformCustomerId",  "b3e6756c682c4d9b9086e122025510c6"); // 平台用户编号
//	        
//		  	String applicationNumber = (String)params.get("applicationNumber");
//			String platformCustomerId = (String)params.get("platformCustomerId");
//			String taskId = "TASKSHE_BAO340100201706262201311520039";
//			result = this.mhSheBaoServiceImpl.saveMhSheBaoReportDataApi(applicationNumber, platformCustomerId, taskId);
//			Assert.assertTrue(result.getIsblack());
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/**
//	 * 同盾报告
//	 */
//	@Test
//	public void tdReport(){
//		RuleResult result;
//		try {
//			Map<String, Object> params = new HashMap<String, Object>();
//			params.put("applicationNumber","e8a4537363c54a52ba8b09316b8630ea"); // 申请编号
//		  	params.put("loan_amount",  "1000"); // 申请借款金额
//	        params.put("loan_term", "7"); // 申请借款期限
//	        params.put("loan_term_unit", "DAY"); // 期限单位
//	        params.put("name", "季澄光"); // 姓名
//	        params.put("id_number", "370404199006301915"); // 身份证号
//	        params.put("mobile", "13062802206"); // 手机号
//	        params.put("card_number", "321283199107050015"); // 银行卡
//	        params.put("work_phone", ""); // 单位座机
//	        params.put("email", "lanfu168@126.com"); // 电子邮箱
//	        params.put("home_address", "张家口市宣化区东土关街5号楼1单元401号"); // 家庭地址
//	        params.put("reportId", ""); //report_id
//	        params.put("platformId", "b3e6756c682c4d9b9086e122025510c6"); //平台用户编号
//	        params.put("source", "1"); //申请来源
//	        params.put("appName", "1"); //appname
//	        params.put("reportId", "ER2017062716000014744572"); //report_id
//	        
//			Application application = new Application();
//			application.setApplicationId((String)params.get("applicationNumber"));
//			application.setApplicationBuId((String)params.get("applicationBuId"));
//			application.setAppSerialNumber((String)params.get("appSerialNumber"));
//			application.setAppName((String)params.get("appName"));
//			application.setCertCode((String)params.get("id_number"));
//			application.setCertType((String)params.get("certType"));
//			application.setCustomerId((String)params.get("customerId"));
//			application.setPlatformId((String)params.get("platformId"));
//			application.setPhoneNum((String)params.get("phoneNum"));
//			application.setName((String)params.get("name"));
//			application.setDays((Integer)params.get("loanIp"));
//			application.setLoanIp((String)params.get("loanIp"));
//			application.setProductQuota((String)params.get("productQuota"));
//			application.setSource((String)params.get("source"));
//			result = this.tdLoanInterfaceServicesImpl.saveUserReportDataApi(params,application,"");
//			Assert.assertTrue(result.getIsblack());
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
////	@Test
//	public void test(){
//		try {
//			for(int i=0;i<1000;i++){
//				Application application = new Application();
//				application.setAppName("1");
//				application.setCustomerId("80158e1513db44a39596a73b121ae711");
//				application.setAppSerialNumber("SD201707052045129881"+i);
//				application.setPlatformId("77fd856cccc34db5b20cb260b27cb918");
//				application.setSource("1");
//				application.setName("刘宇");
//				application.setCertCode("412702199306226513");
//				application.setCertType("1");
//				application.setPhoneNum("13393919815");
//				application.setAuditingState("1");
//				boolean a = this.applicationServiceImpl.saveEntity(application);
//				System.out.println(a);
//			}
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void main(String[] args) {
//		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//		try {
//			System.out.println(DateUtils.parseToDateTimeStr(df1.parse("2017-05-31T10:14:18.000Z")));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}