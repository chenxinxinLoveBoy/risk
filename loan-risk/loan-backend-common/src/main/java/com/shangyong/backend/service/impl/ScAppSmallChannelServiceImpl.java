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
import com.shangyong.backend.dao.ScAppSmallChannelDao;
import com.shangyong.backend.dao.ScAppSmallChannelHisDao;
import com.shangyong.backend.entity.ScAppBigChannel;
import com.shangyong.backend.entity.ScAppSmallChannel;
import com.shangyong.backend.entity.ScAppSmallChannelHis;
import com.shangyong.backend.service.ScAppSmallChannelService;
import com.shangyong.backend.utils.DateUtils;
@Service
public class ScAppSmallChannelServiceImpl implements ScAppSmallChannelService {
	
	@Autowired
	private ScAppSmallChannelDao scAppSmallChannelDao;
	
	@Autowired
	private ScAppSmallChannelHisDao scAppSmallChannelHisDao;
	
	@Autowired
	private ScAppBigChannelServiceImpl scAppBigChannelServiceImpl;
	/**
	 * 日志
	 */
	private static Logger logger = Logger.getLogger(ScAppSmallChannelServiceImpl.class); 
	/**
	 * 查询当前大类下的所有小类
	 */
	@Override
	public List<ScAppSmallChannel> findAllByBigId(ScAppSmallChannel record) {
		
		return scAppSmallChannelDao.findAllByBigId(record);
	}
	/**
	 * 统计
	 */
	@Override
	public int queryAllCountByBigId(ScAppSmallChannel record) {
		
		return scAppSmallChannelDao.queryAllCountByBigId(record);
	}
	/**
	 * 保存渠道小类
	 */
	@Override
	@Transactional
	public boolean saveEntity(ScAppSmallChannel record) {
		UUserBo userBo= TokenManager.getUser();// 直接从token取 
		String uname=userBo.getNickName();//获取创建人
		record.setCreateTime(DateUtils.getDate(new Date()));
		record.setCreateMan(uname);
		int temp=scAppSmallChannelDao.saveEntity(record);
		boolean flag=temp>0?true:false;
		return flag;
	}
	/**
	 * 根据渠道小类ID获取对象
	 */
	@Override
	public ScAppSmallChannel getEntityById(String channelSmallId) {
		
		return scAppSmallChannelDao.getEntityById(channelSmallId);
	}
	/**
	 * 修改渠道小类
	 */
	@Override
	@Transactional
	public boolean updateEntity(ScAppSmallChannel record) {
		UUserBo userBo= TokenManager.getUser();// 直接从token取 
		String uname=userBo.getNickName();//获取创建人
		ScAppSmallChannel forHis=getEntityById(record.getChannelSmallId().toString());
		ScAppSmallChannelHis scAppSmallChannelHis=new ScAppSmallChannelHis();
		BeanUtils.copyProperties(forHis,scAppSmallChannelHis);
		scAppSmallChannelHis.setRecordNewtime(DateUtils.getDate(new Date()));
		record.setModifyTime(DateUtils.getDate(new Date()));
		record.setModifyMan(uname);
		int temp=scAppSmallChannelDao.updateEntity(record);
		int hisTemp=0;
		if(temp>0){
			hisTemp=scAppSmallChannelHisDao.saveEntity(scAppSmallChannelHis);
			logger.info("插入渠道小类历史表:"+hisTemp);
		}		
		boolean flag=hisTemp>0?true:false;
		return flag;
	}
	/**
	 * 通过小类名称查询所属大类对象
	 */
	@Override
	public ScAppBigChannel findObjBySmallName(String channelSmallName) {
		ScAppSmallChannel scAppSmallChannel= null ;
		ScAppBigChannel scAppBigChannel=null;
		try {
			scAppSmallChannel = scAppSmallChannelDao.findObjBySmallName(channelSmallName);
			if(scAppSmallChannel != null){
				scAppBigChannel=scAppBigChannelServiceImpl.getEntityById(scAppSmallChannel.getChannelBigId().toString());
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return scAppBigChannel;
	}

}
