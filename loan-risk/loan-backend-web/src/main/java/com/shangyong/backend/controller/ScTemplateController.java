package com.shangyong.backend.controller;

import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.entity.ScBanControl;
import com.shangyong.backend.entity.ScBanControlTpl;
import com.shangyong.backend.entity.ScDecisionTree;
import com.shangyong.backend.service.impl.ScBanControlServiceImpl;
import com.shangyong.backend.service.impl.ScDecisionTreeServiceImpl;
import com.shangyong.backend.service.impl.ScTemplateServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.utils.SpringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping(value ="/backend/sctemplate")
public class ScTemplateController {
 
	@Autowired
	private ScTemplateServiceImpl scTemplateServiceImpl;
	
	@Autowired
	private ScBanControlServiceImpl scBanControlService;
	@Autowired
	private ScDecisionTreeServiceImpl scDecisionTreeService;
	private static Logger logger = Logger.getLogger(ScTemplateController.class);

	/**
	 * 获取禁止项模版列表
	 * @param request
	 * @param response
	 * @param scBanControlTpl
	 */
	@RequestMapping(value ="/templatelistAll.do",method = RequestMethod.POST )
	public void templatelistAll(HttpServletRequest request, HttpServletResponse response, ScBanControlTpl scBanControlTpl) {
		try {   
			int count = scTemplateServiceImpl.listAllCount(scBanControlTpl);
			logger.info("禁止项模板查询count" + count + '\t');
			List<ScBanControlTpl> list = scTemplateServiceImpl.findAll(scBanControlTpl) ;
			logger.info("禁止项模板查询list" + list + '\t');
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	
	/**
	 * 保存/更新禁止项模版
	 * @param request
	 * @param response
	 * @param scBanControlTpl 禁止项模板表
	 */
	@RequestMapping(value ="/savetemplate.do",method = RequestMethod.POST )
	public void savetemplate(HttpServletRequest request, HttpServletResponse response, ScBanControlTpl scBanControlTpl) {
		try { 
			
			if( scBanControlTpl != null && scBanControlTpl.getBanControlTplId() != null){  //更新
				logger.info("更新禁止项模版"+ scBanControlTpl.getBanControlTplId());

				// 需要先判断当前页面的版本号是否与当前数据的一致，如果不一致则表示有另一人已经修改，而当前的页面数据不是最新
				if(scBanControlTpl.getVersion() == null){ // 版本号
					JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
					return;
				}

				ScBanControlTpl info = scTemplateServiceImpl.getEntityById(scBanControlTpl);
				if(info != null){  
					if(info.getVersion() == null  || !scBanControlTpl.getVersion().toString().equals( info.getVersion().toString())){
						JSONUtils.toJSON(response, CodeUtils.VERSION_FAIL);
						return;
					}

//					double per = Double.parseDouble(scBanControlTpl.getTplPercent());
//					if(per >1 || per <=0 ){
//						JSONUtils.toJSON(response, CodeUtils.BACKEND_NOT_ALOW);
//						return;
//					}

					if(Constants.STATE_NORMAL.equals(scBanControlTpl.getState())){
							//校验百分比
//							double percent = this.scTemplateServiceImpl.queryNormalPercent(scBanControlTpl.getBanControlTplId().toString());
//							percent += Double.parseDouble(scBanControlTpl.getTplPercent());
//							if(percent > 1){
//								JSONUtils.toJSON(response, CodeUtils.BACKEND_OPT_FAIL);
//								return;	
//							}
//							
							//禁止项至少一条生效
							ScBanControl scBanControl = new ScBanControl();
							scBanControl.setBanControlTplId(scBanControlTpl.getBanControlTplId().toString());
							scBanControl.setState(Constants.STATE_NORMAL);
							int count = scBanControlService.listAllCount(scBanControl);// 统计总条数
							if(count  == 0){
								JSONUtils.toJSON(response, CodeUtils.FORBIDDEN_FAIL);
								return;	
							}
					}
					
					//优先级去重
//					ScBanControlTpl tpl = new ScBanControlTpl();
//					tpl.setLevel(scBanControlTpl.getLevel());
//					List<ScBanControlTpl> list = scTemplateServiceImpl.findAll(tpl) ;
//					if(list != null && list.size()>0){
//						if(!list.get(0).getBanControlTplId().equals(scBanControlTpl.getBanControlTplId()) || list.size()>1){
//							JSONUtils.toJSON(response, CodeUtils.BACKEND_REPEAT);
//							return;	
//						}
//					}
					
					//操作人信息
					UUserBo user = TokenManager.getUser();
					if (user != null) {
						scBanControlTpl.setModifyMan(user.getId().toString());
						scBanControlTpl.setModifyName(user.getNickName());
					}

					if (Constants.STATE_FORBIDDEN.equals(scBanControlTpl.getState())) {// 模板状态修改为无效时，其被关联的所有决策树状态必须都为无效才可以修改
						ScDecisionTree scDecisionTree = new ScDecisionTree();
						scDecisionTree.setState(Constants.STATE_NORMAL);
						scDecisionTree.setBanControlTplId(scBanControlTpl.getBanControlTplId());
						int count = scDecisionTreeService.listAllCount(scDecisionTree);
						if (count != 0) {
							JSONUtils.toJSON(response, CodeUtils.UPDATE_TPL_FAIL);
							return;
						}
					}
					
					boolean flag = scTemplateServiceImpl.updateEntity(scBanControlTpl);
					if(flag){
						JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
						return;
					}else{
						JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
						return;
					}
				}else{
					throw new RuntimeException("要修改的记录不存在");
				}
			}else{//添加
				//业务编号已存在则返回失败提示
				ScBanControlTpl info = scTemplateServiceImpl.findCode(scBanControlTpl);
				if(info != null){
					JSONUtils.toJSON(response, CodeUtils.NAME_FAIL);
					return;	
				}
				//百分比超过100%返回失败提示
//				if(Constants.STATE_NORMAL.equals(scBanControlTpl.getState())){
//					double percent = this.scTemplateServiceImpl.queryNormalPercent(null);
//					percent += Double.parseDouble(scBanControlTpl.getTplPercent());
//					if(percent > 1){
//						JSONUtils.toJSON(response, CodeUtils.BACKEND_OPT_FAIL);
//						return;	
//					}
//				}
				
//				double per = Double.parseDouble(scBanControlTpl.getTplPercent());
//				if(per >1 || per <=0 ){
//					JSONUtils.toJSON(response, CodeUtils.BACKEND_NOT_ALOW);
//					return;
//				}

				//优先级去重
//				ScBanControlTpl tpl = new ScBanControlTpl();
//				tpl.setLevel(scBanControlTpl.getLevel());
//				List<ScBanControlTpl> list = scTemplateServiceImpl.findAll(tpl) ;
//				if(list != null && list.size()>0){
//					JSONUtils.toJSON(response, CodeUtils.BACKEND_REPEAT);
//					return;	
//				}
				
				UUserBo user = TokenManager.getUser();
				if (user != null) {
					scBanControlTpl.setModifyMan(user.getId().toString());
					scBanControlTpl.setModifyName(user.getNickName());
					scBanControlTpl.setCreateMan(user.getId().toString());
					scBanControlTpl.setCreateName(user.getNickName());
				}

				boolean flag  = scTemplateServiceImpl.saveEntity(scBanControlTpl);
				logger.info("保存禁止项模版信息"+ flag);
				if(flag){
					JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
					return;
				}else{
					JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("添加禁止项模版异常" +e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
		}
		
	}
	
	/**
	 * 查询模版信息
	 * @param request
	 * @param response
	 * @param scBanControlTpl
	 */
	@RequestMapping(value ="/getEntityById.do",method = RequestMethod.POST )
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, Integer  banControlTplId) {
		try {  
			if(banControlTplId != null){ 
				ScBanControlTpl list  =  scTemplateServiceImpl.getEntityById(banControlTplId);
				logger.info("查询模版信息"+list);
				LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
				LinkedHashMap<String, Object> map1 = new LinkedHashMap<String, Object>();
				map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
				map.put(Constants.MESSAGE, CodeUtils.SUCCESSNEWS.getMessage());
				map1.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());
				
				map1.put("templateObject",list); 
				map.put(Constants.DATA, map1);
				SpringUtils.renderJson(response,map);
			}else{
				logger.error("缺少参数");
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询模版信息异常" +e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.NON_EXISTENT);
		}
		
	}
}
