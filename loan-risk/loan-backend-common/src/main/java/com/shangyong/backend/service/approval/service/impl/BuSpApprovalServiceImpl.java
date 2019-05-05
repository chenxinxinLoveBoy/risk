package com.shangyong.backend.service.approval.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.dao.approval.BuSpApprovalDao;
import com.shangyong.backend.entity.approval.BuSpApproval;
import com.shangyong.backend.service.approval.service.BuSpApprovalService;
import com.shangyong.backend.utils.DateUtils; 

/**
 * 人工审批扩展表Service
 * @author hc
 *
 */
@Service
public class BuSpApprovalServiceImpl implements BuSpApprovalService {

	@Autowired
	private BuSpApprovalDao buSpApprovalDao;
	
	@Override
	public List<BuSpApproval> getBuSpApprovalList(BuSpApproval buSpApproval) {
		
		return buSpApprovalDao.getBuSpApprovalList(buSpApproval);
	}
	
	@Override
	public int listAllCount(BuSpApproval buSpApproval){
		return buSpApprovalDao.listAllCount(buSpApproval);
	}
	@Override
	public BuSpApproval getEntityById(BuSpApproval buSpApproval) {  
		return buSpApprovalDao.getEntityById(buSpApproval);
	}
	/**
	 *保存对象信息
	 */
	@Transactional
	@Override
	public boolean saveEntity(BuSpApproval buSpApproval) {  
		UUserBo user = TokenManager.getUser();
		String creditMoney = buSpApproval.getCreditMoney();
		String rgCreditMoney = buSpApproval.getRgCreditMoney();
		if( "".equals(creditMoney) ||creditMoney == null){
			creditMoney="0.00";
		}
		if( "".equals(rgCreditMoney) ||rgCreditMoney == null){
			creditMoney="0.00";
		}
		buSpApproval.setReceiveName(user.getUserName()); //登陆账号
		buSpApproval.setReceiveCode(user.getId().toString());//用户编号
		buSpApproval.setCreditMoney(creditMoney);
		buSpApproval.setRgCreditMoney(rgCreditMoney); 
		buSpApproval.setCreateTime(DateUtils.getDate(new Date()));
		buSpApproval.setModifyTime(DateUtils.getDate(new Date())); 
		buSpApproval.setRgAuditingTime(DateUtils.getDate(new Date()));
		buSpApproval.setAuditingState("2");//系统审批状态（1-待审批、2-审批通过、3-审批未通过）
		buSpApproval.setModifyMan(user.getUserName());//登陆账号
		buSpApproval.setModifyNo(user.getId().toString());//用户编号
		boolean  flag =  buSpApprovalDao.saveEntity(buSpApproval);
		return flag;
	}
 
	/**
	 * 更新对象信息
	 */
	@Transactional
	@Override
	public boolean updateEntity(BuSpApproval buSpApproval) {
		UUserBo user = TokenManager.getUser();
		buSpApproval.setModifyMan(user.getUserName());
		buSpApproval.setModifyNo(user.getId().toString()); 
		buSpApproval.setModifyTime(DateUtils.getDate(new Date()));
		buSpApproval.setRgAuditingRemark(buSpApproval.getRgAuditingRemark());//添加备注
		boolean flag = buSpApprovalDao.updateEntity(buSpApproval);
		return flag;
	}
	/**
	 * 添加备注
	 */
	@Override
	public boolean saveRgAuditingRemark(BuSpApproval buSpApproval){
		boolean saveRgAuditingRemark = buSpApprovalDao.saveEntity(buSpApproval);
		if(saveRgAuditingRemark){
			return true;
		}
			return false;
	}
}
