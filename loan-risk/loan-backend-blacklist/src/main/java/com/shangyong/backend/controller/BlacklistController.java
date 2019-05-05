
package com.shangyong.backend.controller;

import com.alibaba.fastjson.JSON;
import com.shangyong.backend.entity.BuBlacklist;
import com.shangyong.backend.entity.IsBlacklistResult;
import com.shangyong.backend.form.BlacklistForm;
import com.shangyong.backend.service.BlacklistService;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.entity.BaseResult;
import com.shangyong.utils.SpringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author lz
 * @Description
 * @date 2018年7月30日
 */

@Controller
@RequestMapping("/blacklist")
public class BlacklistController {

    private static Logger logger = Logger.getLogger(BlacklistController.class);

    @Autowired
    private BlacklistService blacklistService;

    /**
     * 将用户保存到黑名单中
     *
     * @param blacklistForm
     * @param request
     * @param response
     */
    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    public void insertBlacklist(HttpServletRequest request, HttpServletResponse response, @RequestBody BlacklistForm blacklistForm) {
        try {
            if (null == blacklistForm) {
                throw new RuntimeException("传入blacklistForm 为空,请重新检查参数");
            }
            blacklistForm.validateParam();

            BuBlacklist buBlacklist = blacklistForm.parse();

            buBlacklist.setCertCode(blacklistForm.getCertCode());
            buBlacklist.setPhoneNum(blacklistForm.getPhoneNum());
            boolean isInBlacklist = blacklistService.isInBlacklist(blacklistForm.getCertCode(), blacklistForm.getPhoneNum(), blacklistForm.getDeviceId());
            Object msg = null;
            if (isInBlacklist) {
                logger.info("当前用户已存在黑名单中 ,无需再次添加");
                msg = "当前用户已存在黑名单中 ,无需再次添加";
            } else {
                buBlacklist.setAppName(buBlacklist.getAppName());
                buBlacklist.setCertCode(buBlacklist.getCertCode());
                //证件类型 ： 1.身份证 2.护照 3.其他'
                buBlacklist.setCertType(buBlacklist.getCertType());
                buBlacklist.setCustomerId(buBlacklist.getCustomerId());
                buBlacklist.setPhoneNum(buBlacklist.getPhoneNum());
                buBlacklist.setName(buBlacklist.getName());
                //被拒绝平台类型跟APPNAME传一样
                buBlacklist.setRejectType(buBlacklist.getRejectType());
                // 列入黑名单原因
                buBlacklist.setRemark(buBlacklist.getRemark());
                buBlacklist.setDeviceId(buBlacklist.getDeviceId());
                buBlacklist.setDsSource(buBlacklist.getDsSource());
                buBlacklist.setClassCode(buBlacklist.getClassCode());

                blacklistService.saveEntity(buBlacklist);
                msg = "增加成功";
            }

            JSONUtils.toJSON(response, CodeUtils.SUCCESS, msg);
        } catch (Exception e) {
            logger.error("黑名单加入错误 [param=" + JSON.toJSONString(blacklistForm) + "]", e);
            JSONUtils.toJSON(response, CodeUtils.FAIL, "黑名单加入错误：" + e.getMessage());
        }
    }

    @RequestMapping(value = "/isBlacklist.do", method = RequestMethod.POST)
    public void isBlacklistClassify(HttpServletRequest request, HttpServletResponse response, @RequestBody List<BuBlacklist> buBlacklists) {
        BaseResult<IsBlacklistResult> result = new BaseResult<IsBlacklistResult>();
        /** list第一个元素为用户本人，其他元素是紧急联系人*/
        BuBlacklist userBlacklist = buBlacklists.get(0);
        buBlacklists.remove(0);
        try {
            IsBlacklistResult isBlacklistResult = blacklistService.userIsBlacklist(userBlacklist.getCertCode(), userBlacklist.getPhoneNum(), userBlacklist.getDeviceId());
            if (!isBlacklistResult.isBlacklistFlag()) {
                isBlacklistResult = blacklistService.contactsIsBlacklist(userBlacklist, buBlacklists);
            }

            result.setData(isBlacklistResult);
            result.setCode(CodeUtils.SUCCESS.getCode());
            result.setMessage(CodeUtils.SUCCESS.getMessage());
            SpringUtils.renderJson(response, result);
        } catch (Exception e) {
            result.setCode(CodeUtils.FAIL.getCode());
            result.setMessage("系统内部错误，请联系管理员：" + e);
            SpringUtils.renderJson(response, result);
        }
    }
}

