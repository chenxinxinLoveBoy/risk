package com.shangyong.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.BuPromoteDetailedDao;
import com.shangyong.backend.entity.BuPromoteDetailed;
import com.shangyong.backend.service.BuPromoteDetailedService;

@Service
public class BuPromoteDetailedServiceImpl implements BuPromoteDetailedService {

	@Autowired
	private BuPromoteDetailedDao buPromoteDetailedDao;
	
	@Autowired
	PushAppPromoteServiceImpl pushAppPromoteServiceImpl;
	
	/**
	 * 
	 * 查询所有
	 * 
	 **/
	@Override
	public List<BuPromoteDetailed> findAllByObj(BuPromoteDetailed buPromoteDetailed) {
		
		return buPromoteDetailedDao.findAllByObj(buPromoteDetailed);
	}
	
	/**
	 * 统计
	 * @return
	 */
	@Override
	public int queryAllCount(BuPromoteDetailed buPromoteDetailed) {
		
		return buPromoteDetailedDao.queryAllCount(buPromoteDetailed);
	}
	
	/**
	 * add: xiajiyun
	 * 查询所有id
	 * 
	 **/
	public Map<String, Object> findAllId ( BuPromoteDetailed buPromoteDetailed ){
		return buPromoteDetailedDao.findAllId(buPromoteDetailed);
	}
	
	/**
	 * 
	 * 查询所有异常单子
	 * 
	 **/
	public List<BuPromoteDetailed>  findAllByIsError( BuPromoteDetailed buPromoteDetailed ){
		return buPromoteDetailedDao.findAllByIsError(buPromoteDetailed);
	}
	

	@Override
	public boolean pushMqMsg(String[] ids) {
		boolean flag=false;
		try {
			for (String promoteDetailedId : ids) {
				if(StringUtils.isNotBlank(promoteDetailedId)){
					pushAppPromoteServiceImpl.pushMqMsg(promoteDetailedId);
				}
			}
			flag=true;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return flag;
	}

	@Override
	public String getJosnStoragePathBytaskTypeCustomerId(BuPromoteDetailed buPromoteDetailed) { 
		return buPromoteDetailedDao.getJosnStoragePathBytaskTypeCustomerId(buPromoteDetailed);
	}
	

}
