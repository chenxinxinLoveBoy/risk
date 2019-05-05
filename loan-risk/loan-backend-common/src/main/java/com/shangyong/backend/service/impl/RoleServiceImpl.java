package com.shangyong.backend.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.bo.RoleBo;
import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.bo.ZTree;
import com.shangyong.backend.dao.RoleDao;
import com.shangyong.backend.dao.UUserDao;
import com.shangyong.backend.dao.UserRoleDao;
import com.shangyong.backend.service.BaseService;

/**
 * @author xiajiyun
 *
 */
@Service
public class RoleServiceImpl implements BaseService<RoleBo> {

	@Autowired
	private RoleDao roleDao;// 角色dao
	
	@Autowired
	private UUserDao userDao;// 用户dao
	
	@Autowired
	private UserRoleDao userRoleDao;// 用户角色dao
	

	@Override
	public List<RoleBo> findAll(RoleBo roleBo) {
		return roleDao.findAll(roleBo);
	}

	@Override
	public RoleBo getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleBo getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleBo getEntityById(RoleBo roleBo) {
		return roleDao.getEntityById(roleBo);
	}

	@Override
	public boolean saveEntity(RoleBo roleBo) {
		//待续， 需要做了登录加进来
		roleBo.setCreateMan("");//创建人
		roleBo.setModifyMan("");// 修改人
		roleBo.setState(1);// 默认1可用
		return roleDao.saveEntity(roleBo);
	}

	@Override
	public boolean updateEntity(RoleBo roleBo) {
		return roleDao.updateEntity(roleBo);
	}

	@Override
	public boolean deleteEntity(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean deleteEntity(RoleBo roleBo) {
		// 管理员角色不能删除
		if(roleBo.getRoleId() != null && roleBo.getRoleId() == 13){
			return false;
		}
		
		// 将中间表关联关系的也删除（sc_user_role）
		userRoleDao.deleteById(roleBo.getRoleId());
		return roleDao.deleteEntity(roleBo);
	}

	/**
	 * 统计
	 * @param uUserBo
	 * @return
	 */
	public int findAllCount(RoleBo role){
		return roleDao.findAllCount(role);
	}
	
	/**
	 * 统计角色名是否有重复 
	 * @param role
	 * @return
	 */
	public int findRoleNameAllCount(RoleBo role){
		return roleDao.findRoleNameAllCount(role);
	}
	
	
	/**
	 * 加载菜单列表树
	 * @param menu
	 * @return
	 */
	public List<ZTree> getTree(RoleBo role){
		return roleDao.getTree(role);
	}
	
	/**
	 * 加载用户列表树
	 * @param user
	 * @return
	 */
	public List<ZTree> getUserTree(UUserBo user){
		return userDao.getUserTree(user);
	}
	
	
	/**
	 * 保存树所有选择的节点
	 * @param roleBo
	 * @return
	 */
	@Transactional
	public boolean saveTree(RoleBo roleBo){
		
		// 先删除当前角色拥有的菜单权限，再添加
		if(roleBo != null && roleBo.getRoleId() != null){
			// 删除
			roleDao.deleteTree(roleBo);
			
			Map<String, Object> hashMap = new HashMap<String, Object>();
			for (int i = 0; i < roleBo.getNoteVal().length; i++) {
				hashMap.put("roleId", roleBo.getRoleId()); // 角色id
				hashMap.put("menuId", roleBo.getNoteVal()[i]);// 菜单id
				if(StringUtils.isNotBlank(roleBo.getNoteVal()[i])){
					roleDao.saveTree(hashMap);// 保存到role_menu表
				}
			}
		}
		return true;
	}
	
}
