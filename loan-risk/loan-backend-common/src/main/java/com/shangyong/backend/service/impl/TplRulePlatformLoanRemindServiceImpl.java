package com.shangyong.backend.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.service.TplRulePlatformLoanRemindService;

@Service
public class TplRulePlatformLoanRemindServiceImpl implements TplRulePlatformLoanRemindService {
	
	Logger logger = LoggerFactory.getLogger(TplRulePlatformLoanRemindServiceImpl.class);
	
	@Autowired
	private ScTemplateServiceImpl scTemplateService;
	
	@Autowired
	private ScFraudRuleTplServiceImpl scFraudRuleTplService;
	
	@Autowired
	private ScScoreTplServiceImpl scScoreTplServiceImpl;
	
	@Autowired
	private ScDecisionTreeServiceImpl scDecisionTreeServiceImpl;
	
	/**
	 * 获取目标分配结果
	 * @param recordSize
	 * @param tplType
	 * @return { "001":10, "002":20}
	 * 
	 */
	@Override
	public Map<String,Object> getValidTmpl(Integer recordSize, String tplType) {
		
	    logger.info("-->>模板引擎分配，处理开始，共处理条数：" + recordSize);
	    
	    if (recordSize <= 0){
			return null; 
		}

	    switch(tplType){
	    	//禁止项模板分配
	    	case Constants.RULE_TPL_BAN_CODE : 
	    		return scTemplateService.getBanCodeTmpl(recordSize);
	    	//欺诈项模板分配
	    	case Constants.RULE_TPL_FRAUD : 
	    		return scFraudRuleTplService.getFraudRuleTmpl(recordSize);
	    	//信用评分分配
	    	case Constants.RULE_TPL_SCORE : 
	    		return scScoreTplServiceImpl.getFraudRuleTmpl(recordSize);
	    	//决策树分配
	    	case Constants.RULE_DECISION_TREE : 
	    		return scDecisionTreeServiceImpl.getDecisionTreeTmpl(recordSize);
	    	default: 
	    		break;
	    }
	    return null;
	}

}
