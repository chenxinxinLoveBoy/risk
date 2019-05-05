package com.shangyong.backend.entity;

/**
 * 用户角色桥接表
 * @author xiajiyun
 *
 */
public class UserRole {
    private Integer userRoleId;

    private Integer uId;

    private Integer rId;

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