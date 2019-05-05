package com.shangyong.backend.service.checkapply.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.MQConstants;
import com.shangyong.backend.dao.CuCustomerCheckApplyDao;
import com.shangyong.backend.dao.CuCustomerCheckApplyExtendsDao;
import com.shangyong.backend.entity.CuCustomerCheckApply;
import com.shangyong.backend.entity.CuCustomerCheckApplyExtends;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.SysParamRedisService;
import com.shangyong.backend.service.checkapply.CuCustomerCheckApplyExtendsService;
import com.shangyong.backend.service.checkapply.CuCustomerCheckApplyService;
import com.shangyong.backend.utils.ReadJsonUtils;
import com.shangyong.backend.utils.RiskHttpClientUtil;
import com.shangyong.utils.RedisUtils;


@Service
public class CuCustomerCheckApplyExtendsImpl implements CuCustomerCheckApplyExtendsService{
	
	
	private static Logger logger = LoggerFactory.getLogger("checkApplyRabbitMQ");
	
	@Autowired
	private CuCustomerCheckApplyExtendsDao cuCustomerCheckApplyExtendsDao;
	
	/**
	 *  cu_customer_check_apply表的service
	 */
	@Autowired
	private CuCustomerCheckApplyService cuCustomerCheckApplyService;
	
	@Autowired
	private SysParamRedisService sysParamRedisService;// 参数配置实现类
	
	@Autowired
	private CuCustomerCheckApplyDao cuCustomerCheckApplyDao;

	@Override
	@Transactional
	public boolean updateEntity(Map<String, String> map) {
		return cuCustomerCheckApplyExtendsDao.updateEntity(map);
	}

	@Transactional
	public boolean updateDataBase(Map<String, String> msgMap){
		// 修改cu_customer_check_apply_extends表信息
		boolean checkApplyExtends = updateEntity(msgMap);
		if(checkApplyExtends){
			logger.info(String.format("customerCheckApplyId = 【%s】, 修改cu_customer_check_apply_extends表信息成功！！！", msgMap.get("customerCheckApplyId")));
		}else{
			logger.error("类:CuCustomerCheckApplyExtendsImpl, 方法：updateDataBase(), map参数:" + msgMap.toString());
			logger.error(String.format("customerCheckApplyId = 【%s】, 修改cu_customer_check_apply_extends表信息【失败】！", msgMap.get("customerCheckApplyId")));
			new RuntimeException("修改cu_customer_check_apply_extends表失败！");
		}
		
		
		/**
		 * 因为没做禁止项，暂时不修改ban_description字段，可能后期会加入！
		 */
		// 修改cu_customer_check_apply表信息
//		boolean checkApply = cuCustomerCheckApplyService.updateEntity(msgMap);
//		if(!checkApply){// 失败，回滚
//			new RuntimeException("修改cu_customer_check_apply表失败，回滚！");
//		}
		return true;
	}

	@Override
	public Page<CuCustomerCheckApplyExtends> findAllByObj(CuCustomerCheckApplyExtends cuCustomerCheckApplyExtends) {
		int pageNo = cuCustomerCheckApplyExtends.getPageIndex();
		int pageSize = cuCustomerCheckApplyExtends.getPageSize();
		PageHelper.startPage(pageNo, pageSize);
		return cuCustomerCheckApplyExtendsDao.findAllByObj(cuCustomerCheckApplyExtends);
	}

	@Override
	public int listCountAll(CuCustomerCheckApplyExtends cuCustomerCheckApplyExtends) {
		return cuCustomerCheckApplyExtendsDao.listCountAll(cuCustomerCheckApplyExtends);
	}
	
	/**
	 * 一键重发到异常队列
	 */
	public boolean pushMsg(String customerCheckApplyId) {
		if(StringUtils.isBlank(customerCheckApplyId)){
			throw new RuntimeException("页面customerCheckApplyId值为空！");
		}
		
		// 格式为d372cc71c3b04151b4f273d747dd4241_05001, 拆分
		int index = customerCheckApplyId.indexOf("_");
		if(index != -1){
			// 获取id
			String id = customerCheckApplyId.substring(0, index);
			// 获取taskType
			String taskType = customerCheckApplyId.substring(index+1, customerCheckApplyId.length());
			
			CuCustomerCheckApplyExtends cuCustomerCheckApplyExtends = new CuCustomerCheckApplyExtends();
			// id
			cuCustomerCheckApplyExtends.setCustomerCheckApplyId(id);
			// 征信机构 （ 05001- 聚信立蜜罐  07001-91征信 08001-白骑士欺诈 09001-宜信  11001-葫芦索伦  12001-小视科技 银行  12002-小视科技 网贷 02001- 芝麻信用欺诈清单）
			cuCustomerCheckApplyExtends.setTaskType(taskType);
			// 处理状态（1——待处理，2——处理成功，3——处理失败）
			cuCustomerCheckApplyExtends.setApplyState(1);
			// 查询改条记录是否满足重新发送条件
			int count = cuCustomerCheckApplyExtendsDao.getCount(cuCustomerCheckApplyExtends);
			
			if(count < 1){
				throw new RuntimeException(String.format("customerCheckApplyId = 【%s】, taskType = 【%s】, 发送失败，未找到记录！", id, taskType));
			}
			// 查询单个对象信息，重发到mq，需要将3要素传过去
			CuCustomerCheckApply customerCheckApply =  cuCustomerCheckApplyDao.find(id);
			// 发送消息到异常队列
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("customerCheckApplyId", id);
			param.put("taskType", taskType);
			param.put(MQConstants.MQ_CHECKAPPLY_REVERT, "1");// 标识为发送到异常队列
			// 3要素传过去
			if(customerCheckApply == null){
				throw new RuntimeException(String.format("查询单个对象信息结果为空！customerCheckApplyId = 【%s】！", customerCheckApplyId));
			}
			
			if(StringUtils.isBlank(customerCheckApply.getCertCode())){
				throw new RuntimeException(String.format("身份证号码为空，customerCheckApplyId = 【%s】！", customerCheckApplyId));
			}
			
			if(StringUtils.isBlank(customerCheckApply.getName())){
				throw new RuntimeException(String.format("姓名为空customerCheckApplyId = 【%s】！", customerCheckApplyId));
			}
			
			if(StringUtils.isBlank(customerCheckApply.getPhoneNum())){
				throw new RuntimeException(String.format("手机号码为空customerCheckApplyId = 【%s】！", customerCheckApplyId));
			}
			
			
			param.put("certCode", customerCheckApply.getCertCode());//身份证号码
			param.put("name", customerCheckApply.getName());// 姓名
			param.put("phoneNum", customerCheckApply.getPhoneNum());// 手机号
			
	
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("exchangeName", MQConstants.MQ_CHECKAPPLY_EXCHANG);// 交换机
			map.put("messageType", MQConstants.MQ_CHECKAPPLY_ROUTINGKEY);// routingKey
			map.put("messageBody", ReadJsonUtils.toJson(param));
	
			logger.info("第三方征信MQ上送参数：" + map.toString());
			
			// 重发队列特殊处理
			if(RedisUtils.hexists(Constants.REDIS_MQ_DSF_ERROR, customerCheckApplyId)){
				// 删除redis
				RedisUtils.hdelEx(Constants.REDIS_MQ_DSF_ERROR, customerCheckApplyId);
			}
			
			SysParam sysParam = sysParamRedisService.querySysParamByParamValueRedis(Constants.TEMQ_ANTIFRAUD_CODE);
			String url = sysParam.getParamValueOne(); //请求url
			
			RiskHttpClientUtil.doPostJson(url, ReadJsonUtils.toJson(map));
			return true;
		}else{
			throw new RuntimeException(String.format("customerCheckApplyId值格式不正确, customerCheckApplyId= 【%s】!", customerCheckApplyId));
		}
		
	}
	
}
