package com.shangyong.backend.controller;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.enums.BlackListCodeEnum;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.entity.BlacklistResult;
import com.shangyong.backend.entity.BuBlacklist;
import com.shangyong.backend.entity.BuBlacklistManage;
import com.shangyong.backend.service.BlacklistService;
import com.shangyong.backend.service.impl.BlacklistServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.FileUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.utils.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value ="/backend/blacklist")
public class BlacklistController {
	
	@Autowired
	private BlacklistService blacklistService;
	
	@Autowired
	private BlacklistServiceImpl blacklistServiceImpl;
	
	@Autowired
	private com.shangyong.backend.service.BlacklistManageService BlacklistManageService;
	
	private static Logger logger = Logger.getLogger(BlacklistController.class);
	
	/**
	 * 黑名单列表
	 * @return
	 */
	@RequestMapping(value ="/index.do",method = RequestMethod.POST )
	public void index(HttpServletRequest request, HttpServletResponse response, BuBlacklist buBlacklist) {
		try {   
			int count = blacklistService.listAllCount(buBlacklist);
			//logger.info("黑名单查询count" + count + '\t');
			List<BuBlacklist> list = blacklistService.listViewAll(buBlacklist);
			//logger.info("黑名单查询list" + list + '\t');
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 检查用户是否为黑名单
	 * @param request
	 * @param response
	 * @param buBlacklist
	 * @return
	 */
	@RequestMapping(value ="/queryBlackInfo.do",method = RequestMethod.POST )
	public void queryBlackInfo(
            HttpServletRequest request, HttpServletResponse response, BuBlacklist buBlacklist) {
		try {
			if ( buBlacklist != null) {
				int bu = blacklistService.listAllCount(buBlacklist);
				if (bu > 0) { 
					JSONUtils.toJSON(response, CodeUtils.NAME_FAIL);
					return;
				} else { 
					JSONUtils.toJSON(response, CodeUtils.NON_EXISTENT);
					return;
				}
			}else{
				logger.info("缺少参数"+buBlacklist +'\t');
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
				return;
			}  
		} catch (Exception e) {
			logger.info("异常信息"+e.getMessage());
		}

		 
	}
	
	/**
	 * 更新黑名单
	 * @param request
	 * @param response
	 * @param buBlacklist
	 * @return
	 */
	/*@RequestMapping(value ="/update.do",method = RequestMethod.POST )
	public void  update( HttpServletRequest request, HttpServletResponse response, BuBlacklist buBlacklist,String blacklistId) {
		try {
			if(buBlacklist != null && buBlacklist.getBlacklistId() != null){// 修改 
				buBlacklist.setBlacklistId(blacklistId); 
				buBlacklist.setRejectFlag("1");
				buBlacklist.setIsFailure("1");
				boolean flag = blacklistService.update(buBlacklist);
				if(flag){
					JSONUtils.toJSON(response, CodeUtils.UPDATE_SUCCESS);
					return;
				}else{
					JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
					return;
				}
			}else{ 
					JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
					return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
		}
	}
	*/	
	
	/**
	 * 添加黑名单
	 * @param request
	 * @param response
	 * @param buBlacklist
	 */
	@RequestMapping(value ="/save.do",method = RequestMethod.POST )
	public void save(HttpServletRequest request, HttpServletResponse response, BuBlacklist buBlacklist) {
		try {
			if (buBlacklist != null) {
				BlacklistResult blacklistResult = blacklistService.isInBlacklistTable(buBlacklist.getCertCode(), buBlacklist.getPhoneNum(), "");
				int count = blacklistResult.getCount();
				logger.info("查询黑名单信息--> 身份证号码：" + buBlacklist.getCertCode() + "，手机号码："+buBlacklist.getPhoneNum()+"，存在数量count="+count);
				if (count < Constants.APPLICATION_BLACK_NUM) {
					buBlacklist.setCreateMan(TokenManager.getUser().getCreateMan());
					buBlacklist.setClassCode(BlackListCodeEnum.MANUAL_REVIEW_BLACKLIST_CODE.getCode());
					boolean flag = blacklistService.saveEntity(buBlacklist);
					logger.info("保存黑名单信息结果" + flag + '\t');
					if (flag) {
						JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
						return;
					} else {
						JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
						return;
					}
				}
			} else {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
				return;
			}
		} catch (Exception e) {
			logger.error("保存黑名单信息异常结果" +e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
		}
		JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
	}
	
	/**
	 * 删除黑名单
	 * @param request
	 * @param response
	 * @param buBlacklist
	 */
	@RequestMapping(value ="/delete.do",method = RequestMethod.POST )
	public void delete(HttpServletRequest request, HttpServletResponse response, BuBlacklist buBlacklist) {
		try {
			String[] blist=buBlacklist.getIds();
			if(blist != null && blist.length>0){ 
				
				boolean flag=blacklistService.deleteObj(buBlacklist);
				if(flag==true){
					JSONUtils.toJSON(response, CodeUtils.DELETE_SUCCESS);
					logger.info("删除黑名单信息结果" + flag + '\t');
				}else{
					JSONUtils.toJSON(response, CodeUtils.DELETE_FAIL);
					logger.info("删除黑名单信息结果" + flag + '\t');
				}
			}else {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除黑名单信息异常结果" +e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.FAIL);
		}
	}
	/**
	 * 统计所有
	 */
	@RequestMapping(value ="/listAllSum.do",method = RequestMethod.POST )
	public void listAllSum(HttpServletRequest request, HttpServletResponse response) {
		try {   
			int count = blacklistService.listAllSum();
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map.put(Constants.MSG, "获取成功");
			map.put(Constants.REL, true);
			map.put("count", count);
			SpringUtils.renderJson(response, map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	/**
	 * 黑名单数据导入
	 * @param request
	 * @param response
	 * @param buBlacklistManage
	 */
	@RequestMapping(value ="/upload.do",method = RequestMethod.POST )
	public void upload(HttpServletRequest request, HttpServletResponse response,
                       BuBlacklistManage buBlacklistManage) {
		try {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multiRequest.getFile("file");
			Map<String,Object> map=blacklistService.upload(file);//上传文件
			if(map != null && map.get("list") != null && StringUtils.isNotBlank((String)map.get("codeId"))){//保存文件内的数据
				blacklistServiceImpl.saveDataInMySql(map);//保存数据到MySql和Redis
				JSONUtils.toJSON(response, CodeUtils.SUCCESS,map);
			}else{
				JSONUtils.toJSON(response, CodeUtils.FAIL,null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	/**
	 * 黑名单上传列表
	 * @param request
	 * @param response
	 * @param buBlacklistManage
	 */
	@RequestMapping(value ="/findAllByObj.do",method = RequestMethod.POST )
	public void findAllByObj(HttpServletRequest request, HttpServletResponse response, BuBlacklistManage buBlacklistManage) {
		try {   
			int count = BlacklistManageService.listAllSum(buBlacklistManage);
			logger.info("黑名单上传查询count" + count + '\t');
			List<BuBlacklistManage> list = BlacklistManageService.listAll(buBlacklistManage);
			logger.info("黑名单上传查询list" + list + '\t');
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	/**
	 * 刷新redis
	 * @param request
	 * @param response
	 * @param buBlacklist
	 */
	@RequestMapping(value ="/refreshRedis.do",method = RequestMethod.POST )
	public void refreshRedis(HttpServletRequest request, HttpServletResponse response, BuBlacklist buBlacklist) {
		try {   
			blacklistServiceImpl.refreshRedis(buBlacklist);
			JSONUtils.toJSON(response, CodeUtils.SUCCESS, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	/**
	 * 黑名单征信源下载
	 * @param request
	 * @param response
	 * @param buBlacklistManage
	 * @param path
	 */
	@RequestMapping(value ="/download.do",method = RequestMethod.GET )
	public void download(HttpServletRequest request, HttpServletResponse response,
                         BuBlacklistManage buBlacklistManage, @RequestParam(value="path") String path, @RequestParam(value="fileName") String fileName) {
			FileUtils fileUtils=new FileUtils();
			try {
				fileUtils.readerCSV(response, path,fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
        
	}
}
