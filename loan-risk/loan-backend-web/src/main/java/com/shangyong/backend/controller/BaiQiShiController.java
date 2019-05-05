package com.shangyong.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 白骑士Controller
 * @author hc
 *
 */
@Controller
@RequestMapping(value ="/backend/baiQiShi")
public class BaiQiShiController {
	
//	private static Logger logger = Logger.getLogger(BaiQiShiController.class);
//	
//	/**
//	 *  白骑士反欺诈风险决策Servive
//	 */
//	@Autowired
//	private BqsAntiFraudDataServiceImpl bqsAntiFraudDataServiceImpl; 
//	
//	/**
//	 * 白骑士反欺诈规则service
//	 */
//	@Autowired
//	private BqsHitRulesService bqsHitRulesService;
//	
//	@Autowired
//	private ApplicationServiceImpl applicationServiceImpl;
//	/**
//	 * 白骑士反欺诈策略service
//	 */
//	@Autowired
//	private BqsStrategySetService bqsStrategySetService;
//	
////	@Autowired
////	private MongoUtils mongoUtils; 
//	
//	@RequestMapping(value = "/gerBaiQishiReport.do", method = RequestMethod.POST)
//	public void gerBaiQishiReport(HttpServletRequest request, HttpServletResponse response, BqsAntiFraud bqsAntiFraud,String platformCustomerId) {
//		try {  
//			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
//			LinkedHashMap<String, Object> map2 = new LinkedHashMap<String, Object>(); 
//			logger.info("获取页面传过来的申请单编号"+bqsAntiFraud.getApplicationId());
//			if(!"".equals(bqsAntiFraud.getApplicationId())){
//				logger.info("信审入口");
//			}else if(!"".equals(platformCustomerId) && "".equals(bqsAntiFraud.getApplicationId())){
//				logger.info("客户信息入口");
//				Application application=applicationServiceImpl.getApplicationIdByPlatformId(platformCustomerId);
//				bqsAntiFraud.setApplicationId(application.getApplicationId());
//			}
//			BqsHitRules bqsHitRules = new BqsHitRules();
//			//白骑士反欺诈风险决策主表Bo
//			BqsAntiFraud  bqsAntiFraudInfo  = bqsAntiFraudDataServiceImpl.getEntityById(bqsAntiFraud);
//			logger.info("根据申请单编号查询的白骑士反欺诈风险决策主表信息"+bqsAntiFraud.toString());
//			if(bqsAntiFraudInfo != null ){//白骑士反欺诈风险决策主表信息不为空时
//				
//				map2.put("bqsAntiFraudInfo", bqsAntiFraudInfo); //白骑士反欺诈风险决策主表
//				//白骑士反欺诈策略表bean
//				BqsStrategySet bqsStrategySet = new BqsStrategySet();
//				bqsStrategySet.setAntiFraudId(bqsAntiFraudInfo.getAntiFraudId());
//				//白骑士反欺诈策略表
//				List<BqsStrategySet>  bqsStrategySetInfo = bqsStrategySetService.getEntityById(bqsStrategySet);
//				logger.info("根据白骑士反欺诈规则表ID查询白骑士反欺诈策略表信息"+bqsStrategySetInfo.toString());
//				List<BqsHitRules> bqsHitRulesList = new ArrayList<BqsHitRules>();//白骑士反欺诈规则表bean根据主表id查询
//				if(CollectionUtils.isNotEmpty(bqsStrategySetInfo)){
//					map2.put("bqsStrategySetInfo", bqsStrategySetInfo);// 麻信用行业关注名单详细信息     
//					for (BqsStrategySet BqsStrategySet2 : bqsStrategySetInfo) {
//						bqsHitRules.setStrategySetId(BqsStrategySet2.getStrategySetId());//麻信用行业关注名单扩展信息表信息
//						List<BqsHitRules> info = bqsHitRulesService.getEntityById(bqsHitRules); 
//						bqsHitRulesList.addAll(info);
//						}
//					map2.put("bqsHitRulesList", bqsHitRulesList);
//				} 
//					
//			}    
//			map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
//			map.put(Constants.MESSAGE, CodeUtils.SUCCESS.getMessage());
//			map.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());
//			map.put(Constants.DATAS, map2);
//			SpringUtils.renderJson(response, map);	   
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage(), e);
//			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
//		}
//	}
}
