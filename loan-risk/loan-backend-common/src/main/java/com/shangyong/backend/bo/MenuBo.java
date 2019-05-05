package com.shangyong.backend.bo;

import java.util.Date;
import java.util.List;

import com.shangyong.backend.common.baseEntityBo.BaseBo;
/**
 * 菜单
 * @author xiajiyun
 *
 */
public class MenuBo extends BaseBo{
    private String menuId;
	
	private String title;//  菜单名称
	
	private String icon;	// 图标

    private Integer parentId; // 父节点

    private Boolean spread; // 是否展开

    private Date createTime;

    private Integer state;

    private String remark;
    
    private String roleId;// 角色id
    

    private String href;//url

    private List<MenuBo> children ;// 子菜单
 
    private String parentTile;// 上级菜单名称
    
//    private String menuType;// 1: 一级菜单，		2：二级菜单
    
    private Integer level;// 菜单等级，1：一级菜单，2：二级菜单，3：三级菜单
    
    private String levelName;// 等级别名
    
    private Integer userId;// 用户id
    
    private String roleName;// 角色名
    
    private Integer rownum; //菜单顺序
    
    private String menuName; //菜单名称
    
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}


	public String getParentTile() {
		return parentTile;
	}

	public void setParentTile(String parentTile) {
		this.parentTile = parentTile;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Boolean getSpread() {
		return spread;
	}

	public void setSpread(Boolean spread) {
		this.spread = spread;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public List<MenuBo> getChildren() {
		return children;
	}

	public void setChildren(List<MenuBo> children) {
		this.children = children;
	}

	public Integer getRownum() {
		return rownum;
	}

	public void setRownum(Integer rownum) {
		this.rownum = rownum;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
    
     
}