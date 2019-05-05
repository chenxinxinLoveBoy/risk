package com.shangyong.backend.form;

import com.shangyong.backend.common.enums.BlackListCodeEnum;
import com.shangyong.backend.common.enums.BlackListDsSourceEnum;
import com.shangyong.backend.entity.BuBlacklist;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * 保存 用户黑名单 form
 * @author caisheng
 * @date 2018-08-10
 */
public class BlacklistForm implements Serializable{

	private static final long serialVersionUID = -3555101458327606336L;
	
	private String customerId;
    private String certCode;
    private String phoneNum;
    private String certType;
    private String appName;
    private String name;
    private String rejectType;
    private String remark;
    private String deviceId;
    private String dsSource;
    private String classCode;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCertCode() {
        return certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRejectType() {
        return rejectType;
    }

    public void setRejectType(String rejectType) {
        this.rejectType = rejectType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDsSource() {
        return dsSource;
    }

    public void setDsSource(String dsSource) {
        this.dsSource = dsSource;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public void validateParam() throws Exception{
        Assert.hasText(this.customerId, "客户ID不能为空");
        Assert.hasText(this.certCode, "身份证不能为空");
        Assert.hasText(this.phoneNum, "手机号不能为空");
        Assert.hasText(this.certType, "身份证类别不能为空");
        Assert.hasText(this.name, "姓名不能为空");
        Assert.isTrue(null != BlackListDsSourceEnum.parse(this.dsSource), "dsSource不能为空");
        Assert.isTrue(null != BlackListCodeEnum.parse(this.classCode), "classCode不能为空");

        if(StringUtils.isNotBlank(this.rejectType)){
            Assert.isTrue(2 <= this.rejectType.length(), "rejectType 长度要小于2");
        }
    }

    public BuBlacklist parse(){
        BuBlacklist buBlacklist = new BuBlacklist();

        buBlacklist.setCustomerId(this.customerId) ;
        buBlacklist.setCertCode(this.certCode) ;
        buBlacklist.setPhoneNum(this.phoneNum) ;
        buBlacklist.setCertType(this.certType) ;
        buBlacklist.setAppName(this.appName) ;
        buBlacklist.setName(this.name) ;
        buBlacklist.setRejectType(this.rejectType) ;
        buBlacklist.setRemark(this.remark) ;
        buBlacklist.setDeviceId(this.deviceId) ;
        buBlacklist.setDsSource(this.dsSource) ;
        buBlacklist.setClassCode(this.classCode) ;

        return buBlacklist;
    }
}
