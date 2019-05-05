package com.shangyong.backend.controller;

import com.shangyong.backend.bo.RoleBo;
import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.bo.UserRoleBo;
import com.shangyong.backend.bo.ZTree;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.service.impl.RoleServiceImpl;
import com.shangyong.backend.service.impl.UserRoleServiceImpl;
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
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 角色Controller
 * 
 * @author xiajiyun
 *
 */
@Controller
@RequestMapping(value = "/backend/role")
public class RoleController {

	private static Logger logger = Logger.getLogger(RoleController.class);

	@Autowired
	private RoleServiceImpl roleServiceImpl;

	@Autowired
	private UserRoleServiceImpl userRoleServiceImpl;// 用戶角色

	/**
	 * 页面列表List
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/findAll.do")
	public void findAll(HttpServletRequest request, HttpServletResponse response, RoleBo roleBo) {
		try {
			int count = roleServiceImpl.findAllCount(roleBo);// 统计
			List<RoleBo> list = roleServiceImpl.findAll(roleBo);
			JSONUtils.toListJSON(response, list, count);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * User页面列表填充select
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getRoleList.do")
	public void getRoleList(HttpServletRequest request, HttpServletResponse response, RoleBo roleBo) {
		try {
			List<RoleBo> list = roleServiceImpl.findAll(roleBo);// 查询角色list

			UserRoleBo userRoleBo = new UserRoleBo();
			userRoleBo.setuId(roleBo.getUserId());// 用戶id
			userRoleBo = userRoleServiceImpl.getEntityByUserId(userRoleBo);// 根据用户id查询角色

			if (userRoleBo != null && StringUtils.isNoneBlank(userRoleBo.getRoleName())) {
				for (RoleBo rb : list) {
					if (rb.getRoleId() == userRoleBo.getrId()) {
						rb.setIsCheck(1);// 页面回显选中
					}
				}
			}

			JSONUtils.toListJSON(response, list, 0);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 保存
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/save.do")
	public void save(HttpServletRequest request, HttpServletResponse response, RoleBo roleBo) {
		try {
			if (roleBo != null && roleBo.getRoleId() != null) {// 修改
				boolean flag = roleServiceImpl.updateEntity(roleBo);
				if (flag) {
					JSONUtils.toJSON(response, CodeUtils.UPDATE_SUCCESS);
					return;
				} else {
					JSONUtils.toJSON(response, CodeUtils.UPDATE_FAIL);
					return;
				}
			} else {// 新增
				int countRoleName = roleServiceImpl.findRoleNameAllCount(roleBo);
				if (countRoleName > 0) {
					JSONUtils.toJSON(response, CodeUtils.NAME_FAIL);
					return;
				}

				boolean flag = roleServiceImpl.saveEntity(roleBo);
				if (flag) {
					JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
					return;
				} else {
					JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
					return;
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
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getEntityById.do")
	public void getEntityById(HttpServletRequest request, HttpServletResponse response, RoleBo roleBo) {
		try {
			RoleBo roleBo2 = roleServiceImpl.getEntityById(roleBo);
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			LinkedHashMap<String, Object> map1 = new LinkedHashMap<String, Object>();
			map.put(Constants.CODE, CodeUtils.SUCCESS.getCode());
			map.put(Constants.MESSAGE, CodeUtils.SUCCESSNEWS.getMessage());
			map1.put(Constants.SUCCESSED, CodeUtils.SUCCESS.getFlag());

			map1.put("roleObject", roleBo2); // 传入对象
			map.put(Constants.DATA, map1);
			SpringUtils.renderJson(response, map);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @param uUserBo
	 */
	@RequestMapping(value = "delete.do", method = RequestMethod.POST)
	public void delete(HttpServletRequest request, HttpServletResponse response, RoleBo roleBo) {
		try {
			boolean flag = roleServiceImpl.deleteEntity(roleBo);
			if (flag) {
				JSONUtils.toJSON(response, CodeUtils.DELETE_SUCCESS);
				return;
			} else {
				JSONUtils.toJSON(response, CodeUtils.DELETE_FAIL);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 加载菜单zTree树
	 * 
	 * @param request
	 * @param response
	 * @param menuBo
	 */
	@RequestMapping(value = "/getTree.do")
	public void getTree(HttpServletRequest request, HttpServletResponse response, RoleBo roleBo) {
		try {
			List<ZTree> zTreeList = roleServiceImpl.getTree(roleBo);// 查詢树
			JSONUtils.toJsonZTrr(response, zTreeList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	
	
	/**
	 * 加载用户zTree树
	 * 
	 * @param request
	 * @param response
	 * @param menuBo
	 */
	@RequestMapping(value = "/getUserTree.do")
	public void getUserTree(HttpServletRequest request, HttpServletResponse response, UUserBo user) {
		try {
			List<ZTree> zTreeList = roleServiceImpl.getUserTree(user);// 查詢树
			JSONUtils.toJsonZTrr(response, zTreeList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}
	

	/**
	 * 保存/修改选中的树节点 （权限设置）
	 * 
	 * @param request
	 * @param response
	 * @param roleBo
	 */
	@RequestMapping(value = "/saveTree.do")
	public void saveTree(HttpServletRequest request, HttpServletResponse response, RoleBo roleBo) {
		try {
			boolean flag = roleServiceImpl.saveTree(roleBo);
			if (flag) {
				JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
				return;
			} else {
				JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

}
