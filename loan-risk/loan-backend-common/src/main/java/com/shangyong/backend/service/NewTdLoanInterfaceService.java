package com.shangyong.backend.service;

import com.alibaba.dubbo.common.logger.Logger;
import com.shangyong.backend.common.RuleResult;
import com.shangyong.backend.entity.Application;

import java.util.List;
import java.util.Map;

/**
 *
 * @author
 * @date 2018-08-12
 */
public interface NewTdLoanInterfaceService {

    /**
     * 根据步骤号查询待处理的审批单列表
     *
     * @param isStep
     * @return
     */
    public List<Map<String, Object>> getAppLicationList(String isStep);

    /**
     *
     * @param isStep
     * @param state
     * @return
     */
    public List<Map<String, Object>> getAppLicationList(String isStep, String state);

    /**
     * 根据借款申请表平台用户编号查询用户表的信息列表
     *
     * @param dataMap
     * @return
     */
    public Map<String, Object> getCustApplicationList(Map<String, Object> dataMap);

    /**
     * 更新步骤标识、审批状态、审批结果描述
     *
     * @param map
     * @return
     */
    public int updateApplicationByStep(Map<String, Object> map);

    /**
     * 审批拒绝的信息，添加黑名单表
     *
     * @param map
     * @return
     */
    public int insertBlackById(Map<String, Object> map) ;

    /**
     * 查询借款申请表中不是【待审核】状态，且步骤标识不为0，且未推送通知给App的数据
     *
     * @return
     */
    public List<Application> getAppStatusByPush();

    /**
     * 查询借款申请表、待人工确认状态且审批人编号为空的数据
     *
     * @return
     */
    public int getRgApprovalCount() ;

    /**
     * 成功通知App后，修改借款申请表的【是否通知App】字段is_push_app：0-否、1-是
     */
    public int updateIsPushApp(String param);

    /**
     * 更新审批单信息
     *
     * @param result        报告入库
     * @param applicationId 申请单编号
     * @param currentStep   当前step步骤
     * @param nextStepType  step更新至当前处理步骤
     * @param stepName      当前处理步骤名称
     * @param logger        日志文件
     * @param appLevel
     */
    public void updateApplicationByRuleResult(RuleResult result,
                                              String applicationId,
                                              String currentStep,
                                              String nextStepType,
                                              String stepName,
                                              String appLevel,
                                              Logger logger);

    /**
     * 修改申请单是否需要进行人工信审
     * @param applicationId 申请单号
     * @param nextStepType 最后一步步骤号
     * @param appLevel 用户标识 1：老用户 0：新用户
     * @param currentStep
     *
     */
    public void updateSpStatus(String applicationId, String nextStepType, String appLevel, String currentStep);
}
