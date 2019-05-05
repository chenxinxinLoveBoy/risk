package com.shangyong.backend.bo;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

/**
 * 系统日志
 * @author xiajiyun
 *
 */
public class SystemLogBo extends BaseBo{
    private Integer logId;

    private Integer userId;

    private String userName;

    private String nickName;

    private String roleName;

    private String url;

    private String optTime;

    private String ip;

    private String remark;
    
    private String optTimeBigen; // 查询条件，操作开始日期
    
    private String optTimeEnd;// 查询条件，操作结束日期
    
    private String content; // 用户查询url，ip,昵称3个字段的模糊查询
    
    private String funName;//操作功能
    
    private String menuName;//操作菜单
    
    public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getOptTimeBigen() {
		return optTimeBigen;
	}

	public void setOptTimeBigen(String optTimeBigen) {
		this.optTimeBigen = optTimeBigen;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOptTimeEnd() {
		return optTimeEnd;
	}

	public void setOptTimeEnd(String optTimeEnd) {
		this.optTimeEnd = optTimeEnd;
	}

	public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }


    public String getOptTime() {
		return optTime;
	}

	public void setOptTime(String optTime) {
		this.optTime = optTime == null ? null : optTime.substring(0, 19);
	}

	public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}