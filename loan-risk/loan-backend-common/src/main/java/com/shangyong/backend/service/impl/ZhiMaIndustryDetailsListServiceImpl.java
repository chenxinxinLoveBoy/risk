package com.shangyong.backend.service.impl;

import java.util.*;

import com.shangyong.backend.entity.IndustryDetails;
import com.shangyong.backend.entity.ZhiMaIndustryDetails;
import com.shangyong.backend.entity.ZhiMaIndustryDetailsInfo;
import com.shangyong.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.dao.ZhiMaIndustryDetailsDao;
import com.shangyong.backend.service.ZhiMaIndustryDetailsListService;
import org.springframework.util.CollectionUtils;

/**
 * 芝麻行业清单serviceImpl
 * @author wqk
 * 
 */
@Service
public class ZhiMaIndustryDetailsListServiceImpl implements ZhiMaIndustryDetailsListService {
	
	@Autowired
	private ZhiMaIndustryDetailsDao zhiMaIndustryDetailsListdao;
	
	
	private static Logger zmLogger = LoggerFactory.getLogger("zhiMaIndustryDetailsList");
	
	@Override
	public List<ZhiMaIndustryDetails> getEntityById(String applicationId) {
		return zhiMaIndustryDetailsListdao.getEntityById(applicationId);
	}
	@Override
	public List<ZhiMaIndustryDetails> getCustomerId(String customerId) {
		
		return zhiMaIndustryDetailsListdao.getCustomerId(customerId);
	}
	
	@Override
	public List<ZhiMaIndustryDetails> findAll(ZhiMaIndustryDetails zhiMaIndustryDetailsList) {
		
		return zhiMaIndustryDetailsListdao.findAll(zhiMaIndustryDetailsList);
	}

	@Override
	public void saveEntity(String applicationId, String customerId, List<IndustryDetails> zhiMaIndustryDetailsList) {
		if(CollectionUtils.isEmpty(zhiMaIndustryDetailsList)){
			zmLogger.info("申请单数据[applicationId="+applicationId+"], 没有[芝麻行业]数据");
			return;
		}
		List<ZhiMaIndustryDetails> temp = new ArrayList<>();
		for(IndustryDetails elem : zhiMaIndustryDetailsList){
			ZhiMaIndustryDetails zhiMaIndustryDetails = new ZhiMaIndustryDetails();

			zhiMaIndustryDetails.setId(UUIDUtils.getUUID());

			zhiMaIndustryDetails.setApplicationId(applicationId);
			zhiMaIndustryDetails.setCustomerId(customerId);

			zhiMaIndustryDetails.setBizCode(elem.getBizCode());
			zhiMaIndustryDetails.setZhiMaLevel(elem.getLevel());
			zhiMaIndustryDetails.setZhiMaType(elem.getType());
			zhiMaIndustryDetails.setZhiMaCode(elem.getCode());
			temp.add(zhiMaIndustryDetails);
		}

		int count = zhiMaIndustryDetailsListdao.saveEntityAll(temp);
		zmLogger.info("申请单数据[芝麻行业] 入库成功, count="+ count);
	}

}
