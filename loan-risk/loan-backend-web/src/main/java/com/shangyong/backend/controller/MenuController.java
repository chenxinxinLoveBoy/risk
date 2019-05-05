package com.shangyong.backend.controller;

import com.shangyong.backend.bo.MenuBo;
import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.dao.MenuDao;
import com.shangyong.backend.service.impl.MenuServiceImpl;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.utils.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * 菜单Controller
 * @author xiajiyun
 *
 */
@Controller
@RequestMapping(value = "/backend/menu")
public class MenuController {
	
	private static Logger logger= Logger.getLogger(MenuController.class);
	
	@Autowired
	private MenuServiceImpl menuServiceImpl;
	
	@Autowired
	private MenuDao menuDao;
	/**
	 * 头部一级菜单
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getMenu1.do")
	public void getMenu1(HttpServletRequest request, HttpServletResponse response, MenuBo menu){
		try {
			UUserBo user = TokenManager.getUser();
			menu.setLevel(1);
			
			if(user != null && user.getId() != null){
				menu.setUserId(user.getId());
			}
			List<MenuBo> list= menuServiceImpl.getMenu1(menu);
			TreeSet<MenuBo> tree=new TreeSet<>(
					Comparator.comparing(MenuBo::getMenuId)
			);
			tree.addAll(list);
			list.clear();
			list.addAll(tree);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			if(list == null || list.size() <=0 ){
				map.put("isNotMenu", 0);
			}
			SpringUtils.renderJson(response,map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	
	/**
	 * 左侧2/3菜单
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getLeftMenu.do")
	public void getLeftMenu(HttpServletRequest request, HttpServletResponse response, MenuBo menu){
		try {
			UUserBo user = TokenManager.getUser();
			menu.setUserId(user.getId());
			List<MenuBo> list = menuServiceImpl.getLeftMenu(menu);
			TreeSet<MenuBo> tree=new TreeSet<>(
					Comparator.comparing(MenuBo::getMenuId)
			);
			tree.addAll(list);
			list.clear();
			list.addAll(tree);
			SpringUtils.renderJson(response,list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	
	
	/**
	 * 首页左侧菜单栏
	 * @param request
	 * @param response
	 */
//	@RequestMapping(value = "/listAll.do")
//	public void listAll(HttpServletRequest request, HttpServletResponse response){
//		try {
//			MenuBo menuBo = new MenuBo();
//			List<MenuBo> menuList = menuServiceImpl.getMenu(menuBo);
//			
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("sysUserName", "测试测试11111111");
//			map.put("authorityLists", menuList);
//			
//			JSONUtils.toJSON(response, CodeUtils.SUCCESS, map);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e.getMessage());
//			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
//		}
//	}
	 
	
	/**
	 * 页面列表List
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/listViewAll.do")
	public void listViewAll(HttpServletRequest request, HttpServletResponse response, MenuBo menuBo){
		try {
			if(menuBo.getMenuName()!=null&&!"".equals(menuBo.getMenuName()))
			{
				Integer parentId=menuDao.selectMenuSerialNumber(menuBo.getMenuName().trim());
				menuBo.setParentId(parentId);
			}
			int count=menuServiceImpl.listAllCount(menuBo);// 统计
			List<MenuBo> list = menuServiceImpl.listViewAll(menuBo);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	/**
	 * 页面select列表
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/listSelectAll.do")
	public void listSelectAll(HttpServletRequest request, HttpServletResponse response, MenuBo menuBo){
		try {
			menuBo.setPageSize(-1);// 标识不需要limit
			List<MenuBo> list = menuServiceImpl.listViewAll(menuBo);
			JSONUtils.toListJSON(response, list, 0);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	
	/**
	 * 保存
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/save.do")
	public void save(HttpServletRequest request, HttpServletResponse response, MenuBo menuBo){
		try {
			if(menuBo != null && StringUtils.isNoneBlank( menuBo.getMenuId())){// 修改
				boolean flag = menuServiceImpl.update(menuBo);
				if(flag){
					JSONUtils.toJSON(response, CodeUtils.UPDATE_SUCCESS);
					return;
				}else{
					JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
					return;
				}
			}else{// 新增
				boolean flag = menuServiceImpl.save(menuBo);
				if(flag){
					JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
					return;
				}else{
					JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
					return ;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	/**
	 * 查询对象信息
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getObjectById.do", method = RequestMethod.POST)
	public void getObjectById(HttpServletRequest request, HttpServletResponse response, MenuBo menuBo){
		try {                                                                                                                                      
			MenuBo menuBo2= menuServiceImpl.getObjectById(menuBo);
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			LinkedHashMap<String, Object> map1 = new LinkedHashMap<String, Object>();
			map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
			map.put(Constants.MESSAGE, CodeUtils.SUCCESSNEWS.getMessage());
			map1.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());
			
			map1.put("menuObject",menuBo2); // 传入对象
			map.put(Constants.DATA, map1);
			SpringUtils.renderJson(response,map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @param uUserBo
	 */
	@RequestMapping(value = "delete.do", method = RequestMethod.POST)
	public void delete(HttpServletRequest request, HttpServletResponse response, MenuBo menuBo){
		try {
			boolean flag = menuServiceImpl.delete(menuBo);
			if(flag){
				JSONUtils.toJSON(response, CodeUtils.DELETE_SUCCESS);
				return ;
			}else{
				JSONUtils.toJSON(response, CodeUtils.DELETE_FAIL);
				return ;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	
}
