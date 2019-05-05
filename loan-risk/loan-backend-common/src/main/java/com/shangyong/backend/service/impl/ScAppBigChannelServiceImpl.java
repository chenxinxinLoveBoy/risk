package com.shangyong.backend.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.dao.ScAppBigChannelDao;
import com.shangyong.backend.dao.ScAppBigChannelHisDao;
import com.shangyong.backend.entity.ScAppBigChannel;
import com.shangyong.backend.entity.ScAppBigChannelHis;
import com.shangyong.backend.service.ScAppBigChannelService;
import com.shangyong.backend.utils.DateUtils;
@Service
public class ScAppBigChannelServiceImpl implements ScAppBigChannelService {
	
	@Autowired
	private ScAppBigChannelDao scAppBigChannelDao;
	
	@Autowired
	private ScAppBigChannelHisDao scAppBigChannelHisDao;
	
	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(ScAppBigChannelServiceImpl.class); 
	/**
	 * 查询所有大类
	 */
	@Override
	public List<ScAppBigChannel> findAllByBigName(ScAppBigChannel record) {
		
		return scAppBigChannelDao.findAllByBigName(record);
	}
	/**
	 * 统计
	 */
	@Override
	public int queryAllCountByBigName(ScAppBigChannel record) {
		
		return scAppBigChannelDao.queryAllCountByBigName(record);
	}
	/**
	 * 保存渠道大类
	 */
	@Override
	@Transactional
	public boolean saveEntity(ScAppBigChannel record) {
		UUserBo userBo= TokenManager.getUser();// 直接从token取 
		String uname=userBo.getNickName();//获取创建人
		record.setCreateTime(DateUtils.getDate(new Date()));
		record.setCreateMan(uname);
		int temp=scAppBigChannelDao.saveEntity(record);
		boolean flag=temp>0?true:false;
		return flag;
	}
	/**
	 * 根据渠道大类ID获取对象
	 */
	@Override
	public ScAppBigChannel getEntityById(String channelBigId) {
		
		return scAppBigChannelDao.getEntityById(channelBigId);
	}
	/**
	 * 修改渠道大类
	 */
	@Override
	@Transactional
	public boolean updateEntity(ScAppBigChannel record) {
		UUserBo userBo= TokenManager.getUser();
		String uname=userBo.getNickName();
		ScAppBigChannel recordForHis=getEntityById(record.getChannelBigId().toString());;//用于记录历史表的对象
		ScAppBigChannelHis scAppBigChannelHis=new ScAppBigChannelHis();
		BeanUtils.copyProperties(recordForHis,scAppBigChannelHis);
		scAppBigChannelHis.setRecordNewtime(DateUtils.getDate(new Date()));
		record.setModifyTime(DateUtils.getDate(new Date()));
		record.setModifyMan(uname);
		int temp=scAppBigChannelDao.updateEntity(record);
		int saveTemp=0;
		if(temp>0){
			saveTemp=scAppBigChannelHisDao.saveEntity(scAppBigChannelHis);
			logger.info("插入渠道大类历史表:"+saveTemp);
		}
		boolean flag=saveTemp>0?true:false;
		return flag;
	}

}
