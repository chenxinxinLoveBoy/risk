package com.shangyong.backend.service.impl;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.bo.MenuBo;
import com.shangyong.backend.dao.MenuDao;
import com.shangyong.backend.service.MenuService;

/**
 * @author xiajiyun
 *
 */
@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Override
	public List<MenuBo> listAll(MenuBo menu) {
		return menuDao.listAll(menu);
	}
	
	/**
	 * 页面列表List
	 * @param menu
	 * @return
	 */
	public List<MenuBo> listViewAll(MenuBo menu){
		return menuDao.listViewAll(menu);
	}

	// 根据角色获取菜单
	public List<MenuBo> getMenu(MenuBo menuBo) {
		// 根据角色获取菜单
		List<MenuBo> menuList = menuDao.listAll(menuBo);

		List<MenuBo> mlist = new ArrayList<MenuBo>();

		Map<String, List<MenuBo>> authorityListMap = groupByFatherId(menuList);

		Set<String> fatherIds = authorityListMap.keySet();

		List<MenuBo> itemListOne = authorityListMap.get("0");

		for (MenuBo item : itemListOne) {
			for (String fatherId : fatherIds) {
				if (fatherId.equals(item.getMenuId())) {
					List<MenuBo> listTwoByFatherId = authorityListMap.get(fatherId);
					item.setChildren(listTwoByFatherId);  // 这里出问题了
					mlist.add(item);
					break;
				}
			}
		}
		return mlist;

	}

	/**
	 * 获取父节点
	 * @param list
	 * @return
	 */
	private Map<String, List<MenuBo>> groupByFatherId(List<MenuBo> list) {
		Map<String, List<MenuBo>> mapGroupByParent = new HashMap<String, List<MenuBo>>();
		try {
			for (MenuBo item : list) {
				List<MenuBo> listTemp = mapGroupByParent.get(item.getParentId());
				if (listTemp == null) {
					listTemp = new ArrayList<MenuBo>();
				}
				listTemp.add(item);
				mapGroupByParent.put(item.getParentId().toString(), listTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapGroupByParent;
	}

	@Override
	public boolean save(MenuBo menu) {
		if(menu != null && StringUtils.isNoneBlank(menu.getIcon()) ){// icon为空给默认值
			if(menu.getLevel() == 2){// 如果是2级菜单，默认菜单图标
				menu.setIcon("&#x1002");
			}else if(menu.getLevel() == 3){// 3级菜单，默认菜单图标
				menu.setIcon("&#x1002");
			}else{
				menu.setIcon("&#x1002");
			}
		}
		if(menu.getRownum()==null||menu.getRownum()==0)
		{
			menu.setRownum(menuDao.selectMenuMaximum(menu.getParentId())+1);
		}
		menu.setState(1);// 默认可用
		return menuDao.save(menu);
	}

	@Override
	public boolean delete(MenuBo menu) {
		return menuDao.delete(menu);
	}

	@Override
	public MenuBo getObjectById(MenuBo menu) {
		return menuDao.getObjectById(menu);
	}

	@Override
	public boolean update(MenuBo menu) {
		return menuDao.update(menu);
	}

	@Override
	public int listAllCount(MenuBo menu) {
		return menuDao.listAllCount(menu);
	}

	
	/**
	 * 获取一级菜单
	 * @param menu
	 * @return
	 */
	public List<MenuBo> getMenu1(MenuBo menu){
		return menuDao.getMenu1(menu);
	}
	
	/**
	 * 获取左边的2/3级菜单
	 * @param menu
	 * @return
	 */
	public List<MenuBo> getLeftMenu(MenuBo menu){
		menu.setLevel(2);
		List<MenuBo> listMenu2 = menuDao.getMenu1(menu);// 2级菜单
		menu.setLevel(3);
		List<MenuBo> listMenu3 = menuDao.getMenu1(menu);// 3级菜单
		TreeSet<MenuBo> tree=new TreeSet<>(
				Comparator.comparing(MenuBo::getMenuId)
		);
		tree.addAll(listMenu3);
		listMenu3.clear();
		listMenu3.addAll(tree);
		// 将2级菜单下对应的3级菜单都放到2级集合里，形成1对多的格式
		for (MenuBo menuBo2 : listMenu2) {
			for (MenuBo menuBo3 : listMenu3) {
				if(StringUtils.isNoneBlank(menuBo2.getMenuId()) && menuBo3.getParentId() != null) {
					if(Integer.parseInt(menuBo2.getMenuId()) == menuBo3.getParentId()){
						if(menuBo2.getChildren() == null ){
							menuBo2.setChildren(new ArrayList<MenuBo>());
							menuBo2.getChildren().add(menuBo3);
						}else{
							menuBo2.getChildren().add(menuBo3);
						}
//						menuBo2.setChildren(menuBo2.getChildren() == null ? new ArrayList<MenuBo>());
						
					}
				}
			}
			
		}
		
		return listMenu2;
	}
	
	
	/**
	 * 查询每个菜单对应的角色权限，用于权限初始化用
	 * @param menu
	 * @return
	 */
	public List<MenuBo> getMenuRoleAll(MenuBo menu){
		return menuDao.getMenuRoleAll(menu);
	}
	
}
