package com.shangyong.backend.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.dao.SysParamDao;
import com.shangyong.backend.dao.SysParamHisDao;
import com.shangyong.backend.entity.SysParam;
import com.shangyong.backend.service.BaseService;
import com.shangyong.backend.service.SysParamService;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.RedisCons;
import com.shangyong.backend.utils.SysParamUtils;
import com.shangyong.utils.ManDaoSmsUtils;
import com.shangyong.utils.PropertiesUtil;
import com.shangyong.utils.RedisUtils;
import com.shangyong.utils.ValidateUtils;

@Service
public class SysParamServiceImpl implements BaseService<SysParam>,SysParamService {

	@Autowired
	private SysParamDao sysParamDao;
	@Autowired
	private SysParamHisDao sysParamHisDao;
	private static Logger logger = LoggerFactory.getLogger("SysParamServiceImpl");
	private static String phoneList = PropertiesUtil.get("phoneList");// 从配置文件获取手机号组

	public SysParam queryByParamValue(String paramValue) {
		return sysParamDao.queryByParamValue(paramValue);
	}

	@Override
	public List<SysParam> findAll(SysParam sysParam) {
		// TODO Auto-generated method stub
		return sysParamDao.getAll(sysParam);
	}

	@Override
	public SysParam getEntityById(String sysParamId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SysParam getEntityById(Integer sysParamId) {
		// TODO Auto-generated method stub
		return sysParamDao.selectByPrimaryKey(sysParamId);
	}

	@Override
	public SysParam getEntityById(SysParam sysParam) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean saveEntity(SysParam sysParam) {
		sysParam.setVersion(1);// 新增时默认为1
		sysParam.setCreateTime(DateUtils.getDate(new Date()));
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			sysParam.setCreateMan(user.getId().toString());
		} else {
			sysParam.setCreateMan("");
		}
		sysParam.setModifyTime(DateUtils.getDate(new Date()));
		boolean flag = sysParamDao.insertSelective(sysParam);
		// 更新redis,放入缓存,设置失效时间1年 = 60*60*24*365 = 31536000 s
		RedisUtils.hsetEx(RedisCons.RISK_SYS_PARAM_INFO, sysParam.getParamValue(), SysParamUtils.ObjectToJson(sysParam),31536000);
		sysParamHisDao.saveEntity(sysParam);// 存入历史表
		setMessage(sysParam, 1);// 发送短信提醒
		return flag;
	}

	@Override
	@Transactional
	public boolean updateEntity(SysParam sysParam) {
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			sysParam.setModifyMan(user.getId().toString());
		} else {
			sysParam.setModifyMan("");
		}
		sysParam.setModifyTime(DateUtils.getDate(new Date()));
		sysParam.setVersion(sysParam.getVersion() + 1);// 修改是版本号自增1
		SysParam sysOld = sysParamDao.selectByPrimaryKey(sysParam.getSysParamId());
		
		if(sysOld == null){
			throw new RuntimeException("要修改的记录不存在");
		}
		
		boolean flag = sysParamDao.updateByPrimaryKeySelective(sysParam);
		
		SysParam sysNew = sysParamDao.selectByPrimaryKey(sysParam.getSysParamId());
		sysParamHisDao.saveEntity(sysNew);// 存入历史表
		
		String paramValue = sysOld.getParamValue();
		//判断redis中key是否存在，存在便删除该key
		if(RedisUtils.hexists(RedisCons.RISK_SYS_PARAM_INFO, paramValue)){
			RedisUtils.hdelEx(RedisCons.RISK_SYS_PARAM_INFO, paramValue);
		}
		// 更新redis,放入缓存,设置失效时间1年 = 60*60*24*365 = 31536000 s
		RedisUtils.hsetEx(RedisCons.RISK_SYS_PARAM_INFO, sysNew.getParamValue(), SysParamUtils.ObjectToJson(sysNew),31536000);
		setMessage(sysParam, 0);// 发送短信提醒
		return flag;
	}

	@Override
	public boolean deleteEntity(String sysParamId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(SysParam sysParam) {
		// TODO Auto-generated method stub
		return false;
	}

	public int listAllCount(SysParam sysParam) {
		// TODO Auto-generated method stub
		return sysParamDao.listAllCount(sysParam);
	}

	/**
	 * 使用漫道给配置文件中的手机号组发送短信
	 * @param sysParam
	 * @param flag
	 */
	public void setMessage(SysParam sysParam, int flag) {
		try {
			String str = "修改";
			if (flag == 1) {// 此时表示进行参数新增操作
				str = "新增";
			}
			String phoneNumInfo[] = phoneList.split(",");
			if (phoneNumInfo != null) {
				int phoneNumInfoLength = phoneNumInfo.length;
				for (int i = 0; i < phoneNumInfoLength; i++) {
					String phoneNum = phoneNumInfo[i];
					if (StringUtils.isBlank(phoneNum) || "null".equals(phoneNum)) {
						logger.error("手机号为空");
					} else {
						if (ValidateUtils.checkPhoneNumber(phoneNum)) {
							String content = java.net.URLEncoder.encode(
									"【小牛闪贷】系统参数调整：" + str + "系统参数，参数序号：" + sysParam.getSysParamId() + "，参数编号："
											+ sysParam.getParamValue() + "，参数名称：" + sysParam.getParamName() + "，请知晓。",
									"utf-8");
//							ManDaoSmsUtils.getSms(phoneNum, content);
						} else {
							logger.error("手机号不合法");
						}
					}
				}
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}

}
