package com.shangyong.backend.service.impl;


import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.bo.UserRoleBo;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.dao.UUserDao;
import com.shangyong.backend.dao.UserRoleDao;
import com.shangyong.backend.service.UUserService;
import com.shangyong.backend.utils.MyDES;

/**
 * @author xiajiyun
 *
 */
@Service
public class UUserServiceImpl implements UUserService {

	@Autowired
	private UUserDao uUserDao;
	
	@Autowired 
	private UserRoleDao userRoleDao;
	
	/**
	 * 统计
	 * @param uUserBo
	 * @return
	 */
	public int listAllCount(UUserBo uUserBo){
		return uUserDao.listAllCount(uUserBo);
	}

	@Override
	public List<UUserBo> listAll(UUserBo uUserBo) {
		return uUserDao.listAll(uUserBo);
	}
 
 
	/**
	 * 保存
	 * @param uUserBo
	 * @return
	 */
	@Transactional
	public boolean save(UUserBo uUserBo){
		boolean b = false;
		uUserBo.setCreateMan(TokenManager.getUser().getUserName());// 创建人
		uUserBo.setModifyMan(TokenManager.getUser().getUserName());// 修改人
		uUserBo.setState(1);// 状态默认1：有效
		uUserBo.setPassword(MyDES.encryptBasedDes(uUserBo.getPassword()));;// 密码des加密
		Integer keyId = uUserDao.save(uUserBo);
		if(keyId != null){// 成功
			b = true;
			UserRoleBo userRoleBo=new UserRoleBo();// 用户角色桥接表
			userRoleBo.setuId(uUserBo.getId());// 用户id
			userRoleBo.setrId(uUserBo.getRoleId());// 角色id
			if(userRoleBo.getuId() != null && userRoleBo.getrId() != null){ // 角色和用户id并存
				userRoleDao.saveEntity(userRoleBo);
			}
			
		}
		return b;
	}
 
	/**
	 * 删除
	 * @param uUserBo
	 * @return
	 */
	public boolean delete(UUserBo uUserBo){
		// 超级管理员帐号不能删除
		if(uUserBo.getId() != null && uUserBo.getId() == 101041){
			return false;
		}
		
		return uUserDao.delete(uUserBo);
	}
	
	/**
	 * 根据id获取对象信息
	 * @param uUserBo
	 * @return
	 */
	public UUserBo getObjectById(UUserBo uUserBo){
		return uUserDao.getObjectById(uUserBo);
	}
	
	
	/**
	 * 修改
	 * @param uUserBo
	 * @return
	 */
	@Transactional
	public boolean update(UUserBo uUserBo){
		// 先删除 用户角色桥接表的记录，再重新插入一条记录，最后修改user信息
		UserRoleBo urb=new  UserRoleBo(); // 用户角色桥接表
		urb.setuId(uUserBo.getId());// 用户id
		urb.setrId(uUserBo.getRoleId());// 角色id
		
		userRoleDao.deleteEntity(urb);// 删除 
		userRoleDao.saveEntity(urb);//新增
		uUserBo.setModifyMan(TokenManager.getUser().getUserName());// 修改人
		if(StringUtils.isNotBlank(uUserBo.getPassword())){
			uUserBo.setPassword(MyDES.encryptBasedDes(uUserBo.getPassword()));//密码加密
		}
		return uUserDao.updateByObject(uUserBo);
	}
	
	/**
	 * 删除多个
	 */
	@Transactional
	public boolean deleteAll(UUserBo uUserBo){
		if(uUserBo != null && uUserBo.getIds()!= null && uUserBo.getIds().length > 0){
			for (int i = 0; i < uUserBo.getIds().length; i++) {
				if(StringUtils.isNotBlank(uUserBo.getIds()[i])){// 判空
					uUserBo.setId(Integer.parseInt(uUserBo.getIds()[i]));// 赋值给id
					delete(uUserBo);// 删除
				}
			}
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * 根据map查询
	 * @param uUserBo
	 * @return
	 */
	public UUserBo selectByMap(Map<String, Object> map){
		return uUserDao.selectByMap(map);
	}
	
	/**
	 * 统计登录帐号
	 * @param uUserBo
	 * @return
	 */
	public int getCountUserName(UUserBo uUserBo){
		return uUserDao.getCountUserName(uUserBo);
	}

	
	/**
	 * 修改对象信息
	 * @param uUserBo
	 */
	public void updateEntityById(UUserBo uUserBo){
		uUserDao.update(uUserBo);
	}
	
	/**
	 * 重置密码
	 * @return
	 */
	public boolean rest(UUserBo uUserBo){
		//修改人
		uUserBo.setModifyMan(TokenManager.getUserName());
		return uUserDao.update(uUserBo);
	}

	/**
	 * 禁用帐号
	 */
	@Override
	public boolean disable(UUserBo uUserBo) {
		return uUserDao.disable(uUserBo);
	}
	 
	/**
	 * 冻结帐号
	 * @param userName 登录帐号
	 * @param IsFreeze 1：冻结帐号，0：正常
	 * @return
	 */
	public boolean updateIsFreeze(String userName, Integer isFreeze){
		UUserBo uUserBo = new UUserBo();
		uUserBo.setUserName(userName);
		uUserBo.setIsFreeze(isFreeze);
		return uUserDao.updateIsFreeze(uUserBo);
	}
	
	
}
