package com.shangyong.backend.bo;

/**
 * 用户角色桥接Bo
 * @author xiajiyun
 *
 */
public class UserRoleBo {
    private Integer userRoleId;

    private Integer uId;

    private Integer rId;
    
    private String roleName;// 角色名称
    
    
    public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }
}