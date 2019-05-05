package com.shangyong.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/backend/cuCustomerCheckApply")
public class CuCustomerCheckApplyController {

//	@Autowired
//	private CuCustomerCheckApplyServiceImpl cuCustomerCheckApplyService;
//	
//	@Autowired
//	private CuCustomerCheckApplyExtendsService cuCustomerCheckApplyExtendsService;
//	
//	
//	@Autowired
//	private MongoUtils mongoUtils;
//	private static Logger logger = Logger.getLogger(CuCustomerCheckApplyController.class);
//
//	/**
//	 * 获取列表
//	 * 
//	 * @param request
//	 * @param response
//	 */
//	@RequestMapping(value = "/cuCustomerCheckApplyListAll.do", method = RequestMethod.POST)
//	public void cuCustomerCheckApplyListAll(HttpServletRequest request, HttpServletResponse response,
//			CuCustomerCheckApply cuCustomerCheckApply) {
//		try {
//  			Page<CuCustomerCheckApply> cuCustomerCheckApplys = cuCustomerCheckApplyService.getAll(cuCustomerCheckApply);
//			// 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
//			PageInfo<CuCustomerCheckApply> cuCustomerCheckApplyInfo = new PageInfo<>(cuCustomerCheckApplys);
//			int count = (int) cuCustomerCheckApplyInfo.getTotal(); // 获取总记录数
//			List<CuCustomerCheckApply> list = cuCustomerCheckApplyInfo.getList();// 页面要显示的数据
//			JSONUtils.toListJSON(response, list, count);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
//		}
//	}
//
//	/**
//	 * 根据customerCheckApplyId和taskType从mongo里获取报告
//	 * 
//	 * @param request
//	 * @param response
//	 * @param cuCustomerCheckApply
//	 */
//	@RequestMapping(value = "/getDataReportByMongo.do", method = RequestMethod.POST)
//	public void getDataReportByMongo(HttpServletRequest request, HttpServletResponse response,
//			String customerCheckApplyId, String taskType) {
//		try {
//			if (StringUtils.isEmpty(customerCheckApplyId) && StringUtils.isEmpty(taskType)) {
//				logger.info("传入参数为空--customerCheckApplyId【" + customerCheckApplyId + "】，taskType【" + taskType + "】");
//				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
//				return;
//			}
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("customerCheckApplyId", customerCheckApplyId);
//			paramMap.put("taskType", taskType);
//			MqdsfzxDataReport report = (MqdsfzxDataReport) mongoUtils.findByClazz(paramMap, null, MqdsfzxDataReport.class);
//			JSONObject jsonObject = null;
//			if (report != null) {
//				jsonObject = report.getJsonInfo();
//			}
//			JSONUtils.toJSON(response, CodeUtils.SUCCESS, jsonObject);
//		} catch (Exception e) {
//			e.printStackTrace();
//			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
//		}
//	}
//
//	
//
//	/**
//	 * 加载满足一键重发的数据 
//	 * 
//	 * @param request
//	 * @param response
//	 */
//	@RequestMapping(value = "/cuCustomerCheckApplyListFind.do", method = RequestMethod.POST)
//	public void cuCustomerCheckApplyListFind(HttpServletRequest request, HttpServletResponse response,
//			CuCustomerCheckApply cuCustomerCheckApply) {
//		try {
//			Map<String, String> dataMap = RedisUtils.hgetAll(Constants.REDIS_MQ_DSF_ERROR);
//			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
//			LinkedHashMap<String, Object> map2 = new LinkedHashMap<String, Object>();
//			map.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getCode());
//			map.put("count", dataMap == null ? 0 :dataMap.size());
//			map2.put("data", dataMap);
//			map.put(Constants.DATAS, map2);
//			SpringUtils.renderJson(response, map);
////			JSONUtils.toJSON(response, CodeUtils.SUCCESS, map, null);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
//		}
//	}
//	
//	
//	/**
//	 * 一键重发到异常队列
//	 * @param request
//	 * @param response
//	 */
//	@RequestMapping(value = "/sendQueue.do", method = RequestMethod.POST)
//	public void sendQueue(HttpServletRequest request, HttpServletResponse response){
//		try {
//			String sendVal = request.getParameter("sendVal");
//			if(StringUtils.isNotBlank(sendVal)){
//				String[] sendValArr = sendVal.split(",");
//				// 循环调用
//				for (int i = 0; i < sendValArr.length; i++) {
//					cuCustomerCheckApplyExtendsService.pushMsg(sendValArr[i].trim());
//				}
//
//			}else{
//				logger.error("sendVal值为空！");
//				JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage(), e);
//			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
//			LinkedHashMap<String, Object> map2 = new LinkedHashMap<String, Object>();
//			map2.put(Constants.CODE, CodeUtils.FAIL.getCode());
//			map2.put(Constants.MSG, StringUtils.isBlank(e.getMessage()) ? "程序内部空指针异常！" : e.getMessage());
////			map2.put("customerCheckApplyId", request.getParameter("sendVal"));// id
//			map.put(Constants.DATA, map2);
//			SpringUtils.renderJson(response, map);
////			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
//		}
//	}
	
}
