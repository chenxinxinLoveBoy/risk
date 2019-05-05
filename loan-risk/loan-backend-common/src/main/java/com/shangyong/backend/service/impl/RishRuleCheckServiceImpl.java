package com.shangyong.backend.service.impl;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.shangyong.backend.common.Constants;
import com.shangyong.backend.common.RuleConstants;
import com.shangyong.backend.service.RiskRuleCheckService;
import com.shangyong.backend.utils.DateUtils;

/**
 * 规则比对
 * @author xiangxianjin
 *
 */
@Service
public class RishRuleCheckServiceImpl implements RiskRuleCheckService{
	
	public static String dateTimePattern = "yyyy-MM-dd HH:mm:ss";

	@Override
	public boolean ruleCheck(String ruleComparisonType, String validateRule, String ruleValue, Object checkValue){
		boolean flag = false;
		
		/**
		 *技术校验规则 
		 *0101-（数值）小于、0102-（数值）小于等于、0103-（数值）等于、0104-（数值）大于、0105-（数值）大于等于）、0109-（数值）大于开始值小于等于结束值
		 *0201-（字符）数据一致、0202-（字符）不为空且不为null、0203-（字符）为空或为null
		 *0204-（字符）规则在结果中存在、0205-（字符）规则在结果中不存在、0206-（字符）数据不一致
		 *0301-（集合）条数大于、0302-（集合）条数大于等于、0303-（集合）条数小于、0304-（集合）条数小于等于、0305-（集合）条数等于
		 */
		switch(ruleComparisonType){
			case RuleConstants.NUM:
				flag = this.numCheck(validateRule, ruleValue, checkValue); break;//数字比对方法
			case RuleConstants.STR:
				flag = this.strCheck(validateRule, ruleValue, checkValue); break; //字符串比对
			case RuleConstants.ARR:
				flag = this.arrCheck(validateRule, ruleValue, checkValue); break; //数组比对
			case RuleConstants.TIME:
				try {
					flag = this.timeCheck(validateRule, ruleValue, checkValue);
				} catch (ParseException e) {
					e.printStackTrace();
					throw new RuntimeException("规则比对-->时间区间比对："+e.getMessage());
				}
			break; //时间区间比对
			
			default :;
		};
		return flag;
	}

	/**
	 * 数字比对
	 * @param validateRule	技术校验规则	
	 * @param ruleValue			禁止项规则技术比对值
	 * @param checkValue		需比对值
	 * @return	是否命中规则
	 */
	private boolean numCheck(String validateRule, String ruleValue, Object checkValue){
		boolean flag = false;
		String[] ruleValues = null;
		switch(validateRule){
		case RuleConstants.NUM_LOW://0101-（数值）小于
			if(Double.parseDouble(checkValue.toString()) < Double.parseDouble(ruleValue)){
				flag = true;
			}
			break;
		case RuleConstants.NUM_LOW_EQ://0102-（数值）小于等于
			if(Double.parseDouble(checkValue.toString()) <= Double.parseDouble(ruleValue)){
				flag = true;
			}
			break;
		case RuleConstants.NUM_EQ://0103-（数值）等于
			if(Double.parseDouble(checkValue.toString()) == Double.parseDouble(ruleValue)){
				flag = true;
			}
			break;
		case RuleConstants.NUM_HIGH://0104-（数值）大于
			if(Double.parseDouble(checkValue.toString()) > Double.parseDouble(ruleValue)){
				flag = true;
			}
			break;
		case RuleConstants.NUM_HIGH_EQ://0105-（数值）大于等于
			if(Double.parseDouble(checkValue.toString()) >= Double.parseDouble(ruleValue)){
				flag = true;
			}
			break;
		case RuleConstants.NUM_HIGH_LOW://0106-（数值）大于开始值小于结束值
			ruleValues = ruleValue.split(Constants.RISK_SPLIT); //通过分隔符分割多条规则
			if(ruleValues != null && ruleValues.length == 2){
				Double low = Double.parseDouble(ruleValues[0]);
				Double high = Double.parseDouble(ruleValues[1]);
				Double check = Double.parseDouble(checkValue.toString().trim());
				if(check > low && check < high){
					flag = true;
				}
			}
			break;
		case RuleConstants.NUM_HIGH_EQ_LOW_EQ://0107-（数值）大于等于开始值小于等于结束值
			ruleValues = ruleValue.split(Constants.RISK_SPLIT); //通过分隔符分割多条规则
			if(ruleValues != null && ruleValues.length == 2){
				Double low = Double.parseDouble(ruleValues[0]);
				Double high = Double.parseDouble(ruleValues[1]);
				Double check = Double.parseDouble(checkValue.toString().trim());
				if(check >= low  && check <= high){
					flag = true;
				}
			}
			break;
		case RuleConstants.NUM_HIGH_EQ_LOW://0108-（数值）大于等于开始值小于结束值
			ruleValues = ruleValue.split(Constants.RISK_SPLIT); //通过分隔符分割多条规则
			if(ruleValues != null && ruleValues.length == 2){
				Double low = Double.parseDouble(ruleValues[0]);
				Double high = Double.parseDouble(ruleValues[1]);
				Double check = Double.parseDouble(checkValue.toString().trim());
				if(check >= low  && check < high){
					flag = true;
				}
			}
			break;
		case RuleConstants.NUM_HIGH_LOW_EQ://0109-（数值）大于开始值小于等于结束值
			ruleValues = ruleValue.split(Constants.RISK_SPLIT); //通过分隔符分割多条规则
			if(ruleValues != null && ruleValues.length == 2){
				Double low = Double.parseDouble(ruleValues[0]);
				Double high = Double.parseDouble(ruleValues[1]);
				Double check = Double.parseDouble(checkValue.toString().trim());
				if(check > low  && check <= high){
					flag = true;
				}
			}
			break;
		default:;
		}
		return flag;
	}
	
	/**
	 * 字符串比对
	 * @param validateRule	技术校验规则	
	 * @param ruleValue			禁止项规则技术比对值
	 * @param checkValue		需比对值
	 * @return
	 */
	private boolean strCheck(String validateRule, String ruleValue, Object checkValue){
		boolean flag = false;
		String[] ruleValues = ruleValue.split(Constants.RISK_SPLIT); //通过分隔符分割多条规则
		for(String rule : ruleValues){
			switch(validateRule){
			case RuleConstants.STR_EQ: //0201-（字符）数据一致
				if(rule.equals(checkValue)){
					flag = true;
				}
				break;
			case RuleConstants.STR_NOT_BLANK: //0202-（字符）不为空且不为null
				if(checkValue!=null && !StringUtils.isBlank(checkValue.toString())){
					flag = true;
				}
				break;
			case RuleConstants.STR_IS_BLANK: //0203-（字符）为空或为null
				if(checkValue==null || StringUtils.isBlank(checkValue.toString())){
					flag = true;
				}
				break;
			case RuleConstants.STR_IN_RESULT: //0204-（字符）规则在结果中存在
				if(checkValue != null && checkValue.toString().indexOf(rule) > -1){
					flag = true;
				}
				break;
			case RuleConstants.STR_NOT_IN_RESULT: //0205-（字符）规则在结果中不存在
				String[] notInResult = ruleValue.split(Constants.RISK_SPLIT); //通过分隔符分割多条规则
				//特殊处理，必须一个个比较，如果存在立即返回未命中，否则命中
				for(String notIn : notInResult){
					if(checkValue.toString().indexOf(notIn)>-1){
						return false;
					}
				}
				return true;
			case RuleConstants.STR_NOT_EQ: //0206-（字符）数据不一致
				if(checkValue!=null && !checkValue.toString().equals(rule)){
					flag = true;
				}
				break;
				default:;
			}
		}
		return flag;
	}
	
	/**
	 * 数组比对
	 * @param validateRule	技术校验规则	
	 * @param ruleValue			禁止项规则技术比对值
	 * @param checkValue		需比对值
	 * 
	 * 非List对象，默认size为0'
	 * 
	 * 
	 * @return
	 */
	private boolean arrCheck(String validateRule, String ruleValue, Object checkValue){
		boolean flag = false;
		int size = 0;
		if(checkValue instanceof List){
			List ck = (List)checkValue;
			if(CollectionUtils.isNotEmpty(ck)){
				size = ck.size();
			}
		}
		switch(validateRule){
		case RuleConstants.ARR_HIGH:// 0301-（集合）条数大于
			if(size > Integer.parseInt(ruleValue)){
				flag = true;
			}
			break;
		case RuleConstants.ARR_HIGH_EQ:// 0302-（集合）条数大于等于
			if(size >= Integer.parseInt(ruleValue)){
				flag = true;
			}
			break;
		case RuleConstants.ARR_LOW:// 0303-（集合）条数小于
			if(size < Integer.parseInt(ruleValue)){
				flag = true;
			}
			break;
		case RuleConstants.ARR_LOW_EQ://0304-（集合）条数小于等于
			if(size <= Integer.parseInt(ruleValue)){
				flag = true;
			}
			break;
		case RuleConstants.ARR_EQ:// 0305-（集合）条数等于
			if(size == Integer.parseInt(ruleValue)){
				flag = true;
			}
			break;
			default:;
		}
		return flag;
	}
	

	/**
	 * 时间区间比对
	 * @param validateRule	技术校验规则	
	 * @param ruleValue			禁止项规则技术比对值
	 * @param checkValue		需比对值
	 * @return	是否命中规则
	 * @throws ParseException 
	 */
	private boolean timeCheck(String validateRule, String ruleValue, Object checkValue) throws ParseException{
		boolean flag = false;
		String[] ruleValues = null;
		
		long checkMills = DateUtils.convertStringToDate(dateTimePattern, checkValue.toString()).getTime();
		long ruleMills = 0;
		
		switch(validateRule){
		case RuleConstants.TIME_LOW://0101-（数值）小于
			ruleMills = DateUtils.convertStringToDate(dateTimePattern, ruleValue.toString()).getTime();
			if(checkMills < ruleMills){
				flag = true;
			}
			break;
		case RuleConstants.TIME_LOW_EQ://0102-（数值）小于等于
			ruleMills = DateUtils.convertStringToDate(dateTimePattern, ruleValue.toString()).getTime();
			if(checkMills <= ruleMills){
				flag = true;
			}
			break;
		case RuleConstants.TIME_EQ://0103-（数值）等于
			ruleMills = DateUtils.convertStringToDate(dateTimePattern, ruleValue.toString()).getTime();
			if(checkMills == ruleMills){
				flag = true;
			}
			break;
		case RuleConstants.TIME_HIGH://0104-（数值）大于
			ruleMills = DateUtils.convertStringToDate(dateTimePattern, ruleValue.toString()).getTime();
			if(checkMills > ruleMills){
				flag = true;
			}
			break;
		case RuleConstants.TIME_HIGH_EQ://0105-（数值）大于等于
			ruleMills = DateUtils.convertStringToDate(dateTimePattern, ruleValue.toString()).getTime();
			if(checkMills >= ruleMills){
				flag = true;
			}
			break;
		case RuleConstants.TIME_HIGH_LOW://0106-（数值）大于开始值小于结束值
			ruleValues = ruleValue.split(Constants.RISK_SPLIT); //通过分隔符分割多条规则
			if(ruleValues != null && ruleValues.length == 2){
				long low = DateUtils.convertStringToDate(dateTimePattern, ruleValues[0]).getTime();
				long high = DateUtils.convertStringToDate(dateTimePattern, ruleValues[1]).getTime();
				if(checkMills > low && checkMills < high){
					flag = true;
				}
			}
			break;
		case RuleConstants.TIME_HIGH_EQ_LOW_EQ://0107-（数值）大于等于开始值小于等于结束值
			ruleValues = ruleValue.split(Constants.RISK_SPLIT); //通过分隔符分割多条规则
			if(ruleValues != null && ruleValues.length == 2){
				long low = DateUtils.convertStringToDate(dateTimePattern, ruleValues[0]).getTime();
				long high = DateUtils.convertStringToDate(dateTimePattern, ruleValues[1]).getTime();
				if(checkMills >= low  && checkMills <= high){
					flag = true;
				}
			}
			break;
		case RuleConstants.TIME_HIGH_EQ_LOW://0108-（数值）大于等于开始值小于结束值
			ruleValues = ruleValue.split(Constants.RISK_SPLIT); //通过分隔符分割多条规则
			if(ruleValues != null && ruleValues.length == 2){
				long low = DateUtils.convertStringToDate(dateTimePattern, ruleValues[0]).getTime();
				long high = DateUtils.convertStringToDate(dateTimePattern, ruleValues[1]).getTime();
				if(checkMills >= low  && checkMills < high){
					flag = true;
				}
			}
			break;
		case RuleConstants.TIME_HIGH_LOW_EQ://0109-（数值）大于开始值小于等于结束值
			ruleValues = ruleValue.split(Constants.RISK_SPLIT); //通过分隔符分割多条规则
			if(ruleValues != null && ruleValues.length == 2){
				long low = DateUtils.convertStringToDate(dateTimePattern, ruleValues[0]).getTime();
				long high = DateUtils.convertStringToDate(dateTimePattern, ruleValues[1]).getTime();
				if(checkMills > low  && checkMills <= high){
					flag = true;
				}
			}
			break;
		default:;
		}
		return flag;
	}
	
}
