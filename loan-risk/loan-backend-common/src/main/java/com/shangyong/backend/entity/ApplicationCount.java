package com.shangyong.backend.entity;

import com.shangyong.backend.common.baseEntityBo.BaseBo;

public class ApplicationCount extends BaseBo {

    /**
     * 命中次数
     */
    private String count;

    /**
     * 命中人数
     */
    private String certCount;

    /**
     * 审核结果
     */
    private String auditResult;

    /**
     * 审核步骤
     */
    private String isStep;

    /**
     * 审核状态
     */
    private String auditingState;

    /**
     * 禁止项规则对应编号
     */
    private String banCode;

    /**
     * 禁止项规则名称
     */
    private String ruleName;

    /**
     * 禁止项修改时间
     **/
    private String modifyTime;

    /**
     * 征信机构类型
     */
    private String creditType;

    /**
     * 申请来源（0——Android；1——IOS）
     */
    private String source;

    /**
     * APP名称：1-闪贷；2-速贷
     **/
    private String appName;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 时间单位（0——时；1——天；2——周；3——月）
     */
    private String timeType;

    /**
     * 时间单位值
     */
    private String timeValue;

    /**
     * 禁止项模板主键ID
     **/
    private String banControlTplId;

    /**
     * 禁止项模板中文名称
     **/
    private String banControlTplName;

    /**
     * 决策树ID
     **/
    private String decisionTreeId;

    /**
     * 客户标识(0:新客户 1：老客户)
     **/
    private String appLevel;

    /**
     * 是否同步大数据(0-否、1-是)
     **/
    private String isHbaseSyn;

    /**
     * 2：已推送大数据、3：已接收大数据消息
     */
    private String isPushApp;

    /**
     * 失败次数
     */
    private String failureTimes;
    
    /**待审批单子中的异常单子**/
    private String countDetail;
    
    public String getAppLevel() {
        return appLevel;
    }

    public void setAppLevel(String appLevel) {
        this.appLevel = appLevel;
    }

    public String getDecisionTreeId() {
        return decisionTreeId;
    }

    public void setDecisionTreeId(String decisionTreeId) {
        this.decisionTreeId = decisionTreeId;
    }

    public String getBanControlTplName() {
        return banControlTplName;
    }

    public void setBanControlTplName(String banControlTplName) {
        this.banControlTplName = banControlTplName;
    }

    public String getBanControlTplId() {
        return banControlTplId;
    }

    public void setBanControlTplId(String banControlTplId) {
        this.banControlTplId = banControlTplId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCertCount() {
        return certCount;
    }

    public void setCertCount(String certCount) {
        this.certCount = certCount;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getBanCode() {
        return banCode;
    }

    public void setBanCode(String banCode) {
        this.banCode = banCode;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(String auditResult) {
        this.auditResult = auditResult;
    }

    public String getIsStep() {
        return isStep;
    }

    public void setIsStep(String isStep) {
        this.isStep = isStep;
    }

    public String getAuditingState() {
        return auditingState;
    }

    public void setAuditingState(String auditingState) {
        this.auditingState = auditingState;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(String timeValue) {
        this.timeValue = timeValue;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getIsHbaseSyn() {
        return isHbaseSyn;
    }

    public void setIsHbaseSyn(String isHbaseSyn) {
        this.isHbaseSyn = isHbaseSyn;
    }

    public String getIsPushApp() {
        return isPushApp;
    }

    public void setIsPushApp(String isPushApp) {
        this.isPushApp = isPushApp;
    }

    public String getFailureTimes() {
        return failureTimes;
    }

    public void setFailureTimes(String failureTimes) {
        this.failureTimes = failureTimes;
    }

	public String getCountDetail() {
		return countDetail;
	}

	public void setCountDetail(String countDetail) {
		this.countDetail = countDetail;
	}
    
    
}
