package com.shangyong.backend.service.xczx.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.TaskTypeConstants;
import com.shangyong.backend.dao.BuThirdpartyReportDao;
import com.shangyong.backend.entity.BuThirdpartyReport;
import com.shangyong.backend.entity.CreditDataInfo;
import com.shangyong.backend.entity.xczx.PkgHeader;
import com.shangyong.backend.service.report.JsonReportService;
import com.shangyong.backend.service.xczx.XczxApplicationDataService;
import com.shangyong.backend.service.xczx.XczxCallBackService;
import com.shangyong.mongo.common.MongoUtils;
import com.shangyong.mongo.entity.DataReport;
import com.shangyong.utils.RedisUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class XczxCallBackServiceImpl implements XczxCallBackService {

    private static Logger creditLogger = (Logger) LoggerFactory.getLogger("creditXC");

    @Autowired
    private XczxApplicationDataService xczxApplicationDataService;

    @Autowired
    private BuThirdpartyReportDao buThirdpartyReportDao;

	@Autowired
	private JsonReportService jsonReportService;// Json文件上传service

    @Transactional
    @Override
    public void dataSynchronized(PkgHeader reqPkg) throws Exception{

        String msgBody = reqPkg.getMsgBody();
        String buApplicationId = null;
        String thirdPartyReportId = null;

        if (StringUtils.isBlank(msgBody)) {
            throw new RuntimeException("91征信回调上送MsgBody对象为空");
        }

        JSONObject jsonStr = JSONObject.fromObject(msgBody);

        String trxNo = jsonStr.getString("trxNo");
        JSONArray loanArray = jsonStr.getJSONArray("loanInfos");

        //查询redis中是否有这个数据
        String xczxRsCode = Constants.XCZX_REDIS_CODE + trxNo;
        if(RedisUtils.exists(xczxRsCode)){
        	String resultStr = RedisUtils.get(xczxRsCode);
        	if(StringUtils.isNotBlank(resultStr)){
        		JSONObject jsonObj = JSONObject.fromObject(resultStr);
        		if(jsonObj != null){
        			buApplicationId = jsonObj.getString("buApplicationId");
    	        	thirdPartyReportId = jsonObj.getString("thirdpartyReportId");
    	        	creditLogger.info("91征信回调从redis中获取数据：" + jsonObj.toString());
        		}
        	}
        //如果redis中没有数据就从数据库查询
		}else{
	        BuThirdpartyReport thirdpartyReport = new BuThirdpartyReport();
	        thirdpartyReport.setTaskType(TaskTypeConstants.XCZX_TASK_TYPE);
	        thirdpartyReport.setTaskId(trxNo);
	        thirdpartyReport = this.getInfoByObj(thirdpartyReport);
	        if(thirdpartyReport != null && StringUtils.isBlank(thirdpartyReport.getJosnStoragePath())){
	        	buApplicationId = thirdpartyReport.getBuApplicationId();
	        	thirdPartyReportId = thirdpartyReport.getThirdpartyReportId();
	        	creditLogger.info("91征信回调从数据库中获取数据");
	        }
		}
        
        if (StringUtils.isNotBlank(thirdPartyReportId) && StringUtils.isNotBlank(trxNo) && StringUtils.isNotBlank(buApplicationId)) {

            xczxApplicationDataService.saveLoans(trxNo, loanArray, thirdPartyReportId, buApplicationId);//保存报告到数据库中

            //将数据上传到阿里云oss服务器 并保存MongoDB数据库
            jsonReportService.uploadJson(Constants.XCZX_FILE_UPLOAD_DIR, jsonStr, TaskTypeConstants.XCZX_TASK_TYPE, TaskTypeConstants.XCZX_TASK_NAME, TaskTypeConstants.XCZX_TASK_ISEND, buApplicationId, "noext");
            //删除redis中的数据
            //RedisUtils.del(xczxRsCode);
        }
    }

    @Override
    public BuThirdpartyReport getInfoByObj(BuThirdpartyReport obj) {

        return buThirdpartyReportDao.getXczxByApplicationId(obj);
    }
}
