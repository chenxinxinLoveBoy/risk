package com.shangyong.backend.service.checkapply;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.shangyong.backend.entity.CuCustomerCheckApplyExtends;

/**
 * cu_customer_check_apply_extends表的service
 * @author xiajiyun
 *
 */
public interface CuCustomerCheckApplyExtendsService {
	
	/**
	 * add xiajiyun
	 * 修改对象信息
	 */
	public boolean updateEntity(Map<String, String> map);
	
	/**
	 * 用于MQ修改cu_customer_check_apply_extends和cu_customer_check_apply2张表信息
	 * @param map
	 * @return
	 */
	public boolean updateDataBase(Map<String, String> map);
	
	/**
	 * add luoyanchong
	 * 查询单个数据测试单号所有明细
	 * @param cuCustomerCheckApplyExtends
	 * @return
	 */
	public Page<CuCustomerCheckApplyExtends> findAllByObj(CuCustomerCheckApplyExtends cuCustomerCheckApplyExtends);
	
	/**
	 * add luoyanchong
	 * 统计
	 * @param cuCustomerCheckApplyExtends
	 * @return
	 */
	public int listCountAll(CuCustomerCheckApplyExtends cuCustomerCheckApplyExtends);
	
	/**
	 * add: xiajiyun
	 * 一键重发到异常队列
	 * @param customerCheckApplyId
	 * @return
	 */
	public boolean pushMsg(String customerCheckApplyId);
	
}
