package com.shangyong.backend.bo;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 角色实体Bo
 * @author xiajiyun
 *
 */
public class RoleBo extends BaseBo{
    private Integer roleId;

    private String roleName;

    private Integer state;

    private String createTime;

    private String createMan;

    private String modifyTime;

    private String modifyMan;

    private String remark;
    
    private Integer userId;// 用户id
    
    private Integer isCheck;// 用于selct回显选中
    
    private String[] noteVal;// 树选择的节点值
    
    
    public String[] getNoteVal() {
		return noteVal;
	}

	public void setNoteVal(String[] noteVal) {
		this.noteVal = noteVal;
	}

	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
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
		this.createTime = createTime == null ? null : createTime.substring(0,19);// 固定格式yyyy-MM-dd HH:mm:ss 
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