package com.shangyong.backend.controller;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.entity.Application;
import com.shangyong.backend.entity.BuThirdpartyReport;
import com.shangyong.backend.service.BuThirdpartyReportService;
import com.shangyong.backend.service.impl.ApplicationServiceImpl;
import com.shangyong.backend.service.mq.IMqLogMessageService;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.utils.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 增强版信用审核列表Controller 技术内部使用
 *
 * @author hc
 */
@Controller
@RequestMapping(value = "/backend/technicApplication")
public class TechnicApplicationController {
    /**
     * 日志
     */
    private static Logger logger = Logger.getLogger(TechnicApplicationController.class);

    /**
     * 增强版信用审核列表service实现
     *
     * @author hc
     */
    @Autowired
    private ApplicationServiceImpl applicationServiceImpl;

    /**
     * 第三方报告表service实现
     */
    @Autowired
    private BuThirdpartyReportService buThirdpartyReportService;

    @Autowired
    private IMqLogMessageService mqLogMessageServiceImpl;

    /**
     * 查询所有信息 技术人员专用
     *
     * @param request
     * @param response
     * @param buApplication
     */
    @RequestMapping(value = "/findAll.do", method = RequestMethod.POST)
    public void findAll(HttpServletRequest request, HttpServletResponse response, Application application) {
        try {
            int count = applicationServiceImpl.jsfindAllCount(application);
            logger.info("查询所有客户借款申请记录表  仅供技术内部使用" + count);
            int personCount = applicationServiceImpl.findJsPersonCount(application);
            List<Application> list = applicationServiceImpl.jsfindAll(application);
            logger.info("查询所有客户借款申请记录表  仅供技术内部使用" + list.size());
            LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
            map.put(Constants.MSG, "获取成功");
            map.put(Constants.REL, true);
            map.put("list", list);
            map.put("count", count);// 分页用，查询的总条数
            map.put("personCount", personCount);// 查询的总人数
            SpringUtils.renderJson(response, map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }

    /**
     * 查询详情信息
     *
     * @param request
     * @param response
     * @param appSerialNumber
     */
    @RequestMapping(value = "/getEntityById.do", method = RequestMethod.POST)
    public void getEntityById(HttpServletRequest request, HttpServletResponse response, String applicationId) {
        try {
            if (StringUtils.isNotBlank(applicationId)) {
                logger.info("查询所有客户借款申请记录表  仅供技术内部使用" + applicationId);
                Application application = applicationServiceImpl.getObjectById(applicationId);
                JSONUtils.toJSON(response, CodeUtils.SUCCESS, application);
            } else {
                JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }

    /**
     * 获取第三方报告信息
     *
     * @param request
     * @param response
     * @param applicationId
     */
    @RequestMapping(value = "/getReportById.do", method = RequestMethod.POST)
    public void getReportById(HttpServletRequest request, HttpServletResponse response, String buApplicationId) {
        try {
            if (StringUtils.isNotBlank(buApplicationId)) {
                logger.info("查询第三方报告表数据  仅供技术内部使用" + buApplicationId);
                List<BuThirdpartyReport> ReportInfo = buThirdpartyReportService.getEntityById(buApplicationId);
                if (ReportInfo != null) {
                    JSONUtils.toJSON(response, CodeUtils.SUCCESS, ReportInfo);
                } else {
                    JSONUtils.toJSON(response, CodeUtils.NON_EXISTENT);
                }
            } else {
                JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }

    /**
     * 清除失败次数
     *
     * @param request
     * @param response
     * @param buApplicationId
     */
    @RequestMapping(value = "/updatefailureTimes.do", method = RequestMethod.POST)
    public void updatefailureTimes(HttpServletRequest request, HttpServletResponse response, Application application) {
        try {
            if (application != null && application.getApplicationId() != null) {
                String applicationId = application.getApplicationId();
                logger.info("单个清除失败次数  仅供技术内部使用" + "applicationId" + applicationId);
                String times = application.getFailureTimes();
                int time = Integer.parseInt(times);
                logger.info("单个清除失败次数   当前错误次数 " + "time=" + time);
                if (time > 0) {
                    application.setFailureTimes("0");
                    boolean flag = applicationServiceImpl.updatefailureTimes(application);
                    logger.info("单个清除失败次数  仅供技术内部使用" + "flag" + flag);
                    if (flag) {
                        JSONUtils.toJSON(response, CodeUtils.SUCCESS, flag);
                    } else {
                        JSONUtils.toJSON(response, CodeUtils.NON_EXISTENT);
                    }
                } else {
                    JSONUtils.toJSON(response, CodeUtils.FAILURE_TIMES);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }

    /**
     * 批量更新数据清零
     *
     * @param request
     * @param response
     * @param application
     */
    @RequestMapping(value = "/updateBatch.do", method = RequestMethod.POST)
    public void updateBatch(HttpServletRequest request, HttpServletResponse response, Application application) {
        try {
            if (application != null) {
                logger.info("仅供技术内部使用  批量更新数据清零" + application.toString());
                boolean flag = applicationServiceImpl.updateBatch(application);
                logger.info("仅供技术内部使用  批量更新数据清零返回系信息" + flag);
                if (flag) {
                    JSONUtils.toJSON(response, CodeUtils.SAVE_SUCCESS);
                    return;
                } else {
                    JSONUtils.toJSON(response, CodeUtils.SAVE_FAIL);
                    return;
                }
            } else {
                JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }

    /**
     * 客户借款申请明細
     *
     * @param request
     * @param response
     * @param buApplicationId
     */
    @RequestMapping(value = "/getReportDetail.do", method = RequestMethod.POST)
    public void index(HttpServletRequest request, HttpServletResponse response, String buApplicationId) {
        try {
            int count = buThirdpartyReportService.listAllCount(buApplicationId);
            logger.info("客户借款申请明細编号" + buApplicationId);
            List<BuThirdpartyReport> ReportInfo = buThirdpartyReportService.listViewAll(buApplicationId);
            JSONUtils.toListJSON(response, ReportInfo, count);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }

    /**
     * 根据条件批量补发消息至大数据
     *
     * @param response
     * @param application
     */
    @PostMapping("/findApplicationByDate")
    public void findApplicationByDate(HttpServletResponse response, Application application) {
        try {
            int count = mqLogMessageServiceImpl.findApplicationIdByDate(application);
            JSONUtils.toJSON(response, CodeUtils.SUCCESS, count, null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }

    /**
     * 根据条件批量重新消费，消费异常的接收类型消息
     *
     * @param response
     * @param application
     */
    @PostMapping("/byTermReConsumption")
    public void byTermReConsumption(HttpServletResponse response, Application application) {
        try {
            int count = mqLogMessageServiceImpl.byApplicationBatchConsumption(application);
            JSONUtils.toJSON(response, CodeUtils.SUCCESS, count, null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }
    
    /**
     * 根据条件批量推送审批消息数据至APP
     *
     * @param response
     * @param application
     */
    @PostMapping("/findPushAppStatusByDate")
    public void findPushAppStatusByDate(HttpServletResponse response, Application application) {
        try {
            int count = mqLogMessageServiceImpl.findPushAppStatusByDate(application);
            JSONUtils.toJSON(response, CodeUtils.SUCCESS, count, null);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
        }
    }
}
