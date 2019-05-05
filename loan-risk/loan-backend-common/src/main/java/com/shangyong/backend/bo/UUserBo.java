package com.shangyong.backend.bo;

import java.io.Serializable;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

public class UUserBo extends BaseBo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer Id;

    private String userName;

    private String nickName;

    private String password;

    private String mobile;

    private Integer state;

    private String createTime;

    private String createMan;

    private String modifyTime;

    private String modifyMan;

    private String remark;
    
    /**
     * 1：已冻结，0：正常
     */
    private Integer isFreeze;

    private String[] ids;// 多选框用
    
    private String userPic;// 用户头像
    
    private Integer roleId;// 角色id
    
    private String roleName;// 角色名称
    
    private String ip;// ip地址
    
    private String lastLoginTime;// 最后登录时间
    
    private Integer isLimit;// 查询冻结账号用到，是否分页,1 不分页
    
//    private String[] noteVal;// 树选择的节点值
 
    
    
	public String getIp() {
		return ip;
	}


	public Integer getIsLimit() {
		return isLimit;
	}


	public void setIsLimit(Integer isLimit) {
		this.isLimit = isLimit;
	}


	public Integer getIsFreeze() {
		return isFreeze;
	}

	public void setIsFreeze(Integer isFreeze) {
		this.isFreeze = isFreeze;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime == null ? null : lastLoginTime.substring(0, 19);
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    

    public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime  == null ? null : createTime.substring(0, 19);// 固定格式yyyy-MM-dd HH:mm:ss 
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan == null ? null : createMan.trim();
    }

    

    public String getModifyMan() {
        return modifyMan;
    }

    public void setModifyMan(String modifyMan) {
        this.modifyMan = modifyMan == null ? null : modifyMan.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}