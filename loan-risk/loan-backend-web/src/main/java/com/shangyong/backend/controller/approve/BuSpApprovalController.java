package com.shangyong.backend.controller.approve;

import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.enums.BlackListCodeEnum;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.approval.BuSpApproval;
import com.shangyong.backend.service.approval.service.impl.BuSpApprovalServiceImpl;
import com.shangyong.backend.service.impl.ApplicationServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.utils.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

/**
 * 人工审批扩展表Controller
 * 
 * @author hc
 *
 */
@Controller
@RequestMapping(value = "/backend/buspapproval")
public class BuSpApprovalController {

	private static Logger logger = LoggerFactory.getLogger(BuSpApprovalController.class);

	// 人工审批扩展表service实现
	@Autowired
	private BuSpApprovalServiceImpl buSpApprovalServiceImpl;
	
	
	@Autowired
	private ApplicationServiceImpl applicationservice;

	/**
	 * 添加人工审批扩展表信息 
	 * @param request
	 * @param response
	 * @param buSpApproval
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST )
	public void save(HttpServletRequest request, HttpServletResponse response, BuSpApproval buSpApproval) {
		try {
			if(buSpApproval != null){
				BuSpApproval BuSpApprovalinfo = buSpApprovalServiceImpl.getEntityById(buSpApproval);
				if(BuSpApprovalinfo != null && BuSpApprovalinfo.getApprovalId() !=null){//更新
					buSpApproval.setApprovalId(BuSpApprovalinfo.getApprovalId());
					boolean flag = buSpApprovalServiceImpl.updateEntity(buSpApproval);
					if(flag){
						UUserBo user = TokenManager.getUser();
						Application application = new Application();
						application.setAuditingState(buSpApproval.getRgAuditingState());//人工审批状态（1-待审批、2-审批通过、3-审批未通过） 
						application.setApplicationId(buSpApproval.getApplicationId());//申请单编号
						application.setModifyTime(DateUtils.getDate(new Date()));
						String state = buSpApproval.getRgAuditingState(); 
						application.setCreditMoney(buSpApproval.getRgCreditMoney()); //人工审核额度     
						if(Constants.NOPASS_SP_STAATE.equals(state)){
							application.setBanClassCode(BlackListCodeEnum.MANUAL_REVIEW_BLACKLIST_CODE.getCode());
						}
						if( "3".equals(state) ){
							application.setCreditMoney("0.00");//人工审批未通过，授信额度值修改为0
							application.setAuditingRejectCode("3"); //人工审核拒绝
						}
						application.setModifyMan(user.getUserName());  //登陆账号
						boolean  fll = applicationservice.updateEntity(application); //更新申请单表数据
						if(fll){
							JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
							return;
						}else{
							JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
						} 
					} else{
						JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
						return;	
					}
				}else{
					buSpApproval.setApprovalId(UUIDUtils.getUUID());
					boolean flag = buSpApprovalServiceImpl.saveEntity(buSpApproval);//添加
					logger.info("人工审批扩展表信息添加" + flag);
					if (flag) {
						UUserBo user = TokenManager.getUser();
						Application application = new Application();
						application.setAuditingState(buSpApproval.getRgAuditingState());//人工审批状态（1-待审批、2-审批通过、3-审批未通过） 
						application.setApplicationId(buSpApproval.getApplicationId());//申请单编号
						application.setModifyTime(DateUtils.getDate(new Date()));
						String state = buSpApproval.getRgAuditingState(); //

						if(Constants.NOPASS_SP_STAATE.equals(state)){
							application.setBanClassCode(BlackListCodeEnum.MANUAL_REVIEW_BLACKLIST_CODE.getCode());
						}

						application.setCreditMoney(buSpApproval.getRgCreditMoney()); //人工审核额度     
						if( "3".equals(state) ){
							application.setCreditMoney("0.00");//人工审批未通过，授信额度值修改为0
							application.setAuditingRejectCode("3"); //人工审核拒绝
						} 
						application.setModifyMan(user.getUserName());  //修改人
						boolean  fl = applicationservice.updateEntity(application); //跟新申请单表数据
						if(fl){
							JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
							return;
						}else{
							JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
						}  
					} else {
						JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
						return;
					}
				}
			}
		}catch (Exception e) {
			logger.error("保存人工审批扩展表信息异常结果" + e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
		}
	}
	
	/**
	 * 根据申请单Id查询人工审核表信息
	 * @param request
	 * @param response
	 * @param buSpApproval
	 */
	@RequestMapping(value ="/queryBuSpapprovalInfo.do",method = RequestMethod.POST )
	public void queryBuSpapprovalInfo(
            HttpServletRequest request, HttpServletResponse response, BuSpApproval buSpApproval) {
		try {
			if (buSpApproval != null && buSpApproval.getApplicationId() != null) {
				BuSpApproval buSpApprovalInfo = buSpApprovalServiceImpl.getEntityById(buSpApproval);
				if (buSpApprovalInfo != null && buSpApprovalInfo.getApprovalId()!= null ) {
					JSONUtils.toJSON(response, CodeUtils.SUCCESS, buSpApprovalInfo);
					return;
				} else { 
					JSONUtils.toJSON(response, CodeUtils.NON_EXISTENT);
					return;
				}
			}  
		} catch (Exception e) {
			logger.info("异常信息"+e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.FAIL);
		}
	}
	
	/**
	 * 添加备注
	 */
	@RequestMapping(value = "/saveRgAuditingRemark.do",method = RequestMethod.POST)
	public void saveRgAuditingRemark(String applicationId, String rgAuditingRemark, HttpServletResponse response){
		logger.info("【保存备注】开始,申请单编号=" + applicationId + ",备注信息=" + rgAuditingRemark );
		
		BuSpApproval buSpApproval = new BuSpApproval();
		try{
			buSpApproval.setApplicationId(applicationId);
			//根据applicationId查询出人工审批扩展表对象
			BuSpApproval entityById = buSpApprovalServiceImpl.getEntityById(buSpApproval);
			
			logger.info("人工审批扩展表信息" + entityById);

			if(entityById != null){
				//将备注信息放入对象添加至数据库
				buSpApproval.setRgAuditingRemark(rgAuditingRemark);
				boolean saveRgAuditingRemark = buSpApprovalServiceImpl.saveRgAuditingRemark(buSpApproval);
				//添加返回值为true表示添加成功，false表示备注添加失败
				if(saveRgAuditingRemark){ 
					logger.info("【保存备注成功】,buSpApproval=" + buSpApproval );
					JSONUtils.toJSON(response, CodeUtils.SUCCESS, Collections.EMPTY_MAP);
				}else{
					logger.error("【保存备注失败】,请联系管理员");
					JSONUtils.toJSON(response, CodeUtils.FAIL, Collections.EMPTY_MAP);
				}
			}else{
				logger.error("请检查申请单编号是否正确：applicationId:" + applicationId);
				JSONUtils.toJSON(response, CodeUtils.FAIL, Collections.EMPTY_MAP);
			}
		}catch(Exception e){
			logger.error("【保存备注异常】," + e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.FAIL, Collections.EMPTY_MAP);
		}
	}
}
