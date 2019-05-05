package com.shangyong.backend.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.bo.SysDictionaryBo;
import com.shangyong.backend.common.Constants;
import com.shangyong.backend.dao.ScSystemDictDao;
import com.shangyong.backend.service.ScSystemDictService;
import com.shangyong.backend.utils.JsonUtil;
import com.shangyong.utils.RedisUtils;

@Service
public class ScSystemDictServiceImpl implements ScSystemDictService {
	
	@Autowired
	private ScSystemDictDao scSystemDictDao;
	
	@Autowired
	private RedisUtils redisUtils;
	
	/**
	 * 根据大类编号集合获得数据字典列表
	 * @return
	 */
	public Map list(Map params) {
		String codes = (String)params.get("codes");
		String[] codesArr = codes.split(",");
		Map<String, SysDictionaryBo> resDicList = new HashMap<String, SysDictionaryBo>();
		if (codesArr != null || codesArr.length > 0) {
			Integer flush = (Integer)params.get("flush");
			String cacheFlag = redisUtils.get(Constants.DICTIONARY_CACHE_FLAG_KEY);
			if ((flush != null && 1 == flush) || !"1".equals(cacheFlag)) {
				resDicList = this.pushDicData(codesArr);
			} else {
				resDicList = this.getDicData(codesArr);
			}
		}
		return resDicList;
	}
	
	/**
	 * 查询并cache数据字典
	 * @param codesArr
	 * @return
	 */
	private Map<String, SysDictionaryBo> pushDicData(String[] codesArr) {
		Map<String, SysDictionaryBo> resDicList = new HashMap<String, SysDictionaryBo>();
		Map pa = new HashMap();
		pa.put("codesArr", codesArr);
		List<Map> dictList = scSystemDictDao.getByCodes(pa);
		if (dictList != null && !dictList.isEmpty()) {
			redisUtils.batchDel(Constants.DICTIONARY_CACHE_PRE + "*");
			for(Map dic : dictList) {
				SysDictionaryBo sd = new SysDictionaryBo();
				sd.setCode((String)dic.get("pcode"));
				sd.setValue((String)dic.get("pvalue"));
				String subCodes = (String)dic.get("codes");//子类code
				String subValues = (String)dic.get("valuess");//子类value
				
				String[] subCodesArr = subCodes.split(",");
				String[] subValueArr = subValues.split(",");
				if (subCodesArr != null && subValueArr != null && subCodesArr.length > 0 && subCodesArr.length == subValueArr.length) {
					int len = subCodesArr.length;
					List<SysDictionaryBo> subList = new ArrayList<SysDictionaryBo>();
					for(int i=0; i<len;i++) {
						SysDictionaryBo sub = new SysDictionaryBo();
						sub.setCode(subCodesArr[i]);
						sub.setValue(subValueArr[i]);
						subList.add(sub);
					}
					sd.setSubDictList(subList);
				}
				resDicList.put(sd.getCode(), sd);
				redisUtils.set(Constants.DICTIONARY_CACHE_PRE + (String)dic.get("pcode"), JsonUtil.toJson(sd));
			}
			redisUtils.set(Constants.DICTIONARY_CACHE_PRE + "flag", "1");
		}
		return resDicList;
	}
	
	/**
	 * 从cache查询数据字典
	 * @param codesArr
	 * @return
	 */
	private Map<String, SysDictionaryBo> getDicData(String[] codesArr) {
		Map<String, SysDictionaryBo> resDicList = new HashMap<String, SysDictionaryBo>();
		for (String code : codesArr) {
			String dic = redisUtils.get(Constants.DICTIONARY_CACHE_PRE + code);
			if (StringUtils.isNotBlank(dic)) {
				resDicList.put(code, JsonUtil.toObject(dic, SysDictionaryBo.class));
			}
		}
		return resDicList;
	}
}
