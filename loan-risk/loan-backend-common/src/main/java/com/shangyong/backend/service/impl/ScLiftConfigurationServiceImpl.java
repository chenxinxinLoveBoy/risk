package com.shangyong.backend.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.dao.ScLiftConfigurationDao;
import com.shangyong.backend.dao.ScLiftConfigurationHisDao;
import com.shangyong.backend.entity.ScLiftConfiguration;
import com.shangyong.backend.service.BaseService;
import com.shangyong.backend.utils.DateUtils;

@Service
public class ScLiftConfigurationServiceImpl implements BaseService<ScLiftConfiguration> {

	Logger logger = LoggerFactory.getLogger(ScLiftConfigurationServiceImpl.class);

	@Autowired
	private ScLiftConfigurationDao scLiftConfigurationDao;

	@Autowired
	private ScLiftConfigurationHisDao scLiftConfigurationHisDao;
	
	@Override
	public List<ScLiftConfiguration> findAll(ScLiftConfiguration scLiftConfiguration) {
		return scLiftConfigurationDao.findAll(scLiftConfiguration);
	}

	@Override
	public ScLiftConfiguration getEntityById(ScLiftConfiguration scLiftConfiguration) {
		return scLiftConfigurationDao.getEntityById(scLiftConfiguration);
	}

	@Override
	@Transactional
	public boolean saveEntity(ScLiftConfiguration scLiftConfiguration) {
		boolean flag = false;
		scLiftConfiguration.setVersion(1);
		scLiftConfiguration.setCreateTime(DateUtils.getDate(new Date()));
		scLiftConfiguration.setModifyTime(DateUtils.getDate(new Date()));
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scLiftConfiguration.setModifyMan(user.getId().toString());
			scLiftConfiguration.setModifyName(user.getNickName());
			scLiftConfiguration.setCreateMan(user.getId().toString());
			scLiftConfiguration.setCreateName(user.getNickName());
		} else {
			scLiftConfiguration.setModifyMan("");
			scLiftConfiguration.setModifyName("");
			scLiftConfiguration.setCreateMan("");
			scLiftConfiguration.setCreateName("");
		}

		int count = scLiftConfigurationDao.saveEntity(scLiftConfiguration);
		scLiftConfigurationHisDao.insert(scLiftConfiguration);
		if (count > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateEntity(ScLiftConfiguration scLiftConfiguration) {
		boolean flag = false;
		scLiftConfiguration.setVersion(scLiftConfiguration.getVersion() + 1);// 修改是版本号自增1
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scLiftConfiguration.setModifyMan(user.getId().toString());
			scLiftConfiguration.setModifyName(user.getNickName());
		} else {
			scLiftConfiguration.setModifyMan("");
			scLiftConfiguration.setModifyName("");
		}
		scLiftConfiguration.setModifyTime(DateUtils.getDate(new Date()));

		ScLiftConfiguration scOld = scLiftConfigurationDao.getEntityById(scLiftConfiguration);
		if (scOld == null) {
			throw new RuntimeException("要修改的记录不存在");
		}
		int temp = scLiftConfigurationDao.updateEntity(scLiftConfiguration);
		ScLiftConfiguration scNew = scLiftConfigurationDao.getEntityById(scLiftConfiguration);
		scLiftConfigurationHisDao.insert(scNew);

		if (temp > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean deleteEntity(String id) {
		return false;
	}

	public int listAllCount(ScLiftConfiguration scLiftConfiguration) {
		int count = scLiftConfigurationDao.listAllCount(scLiftConfiguration);
		return count;
	}

	@Override
	public ScLiftConfiguration getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScLiftConfiguration getEntityById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteEntity(ScLiftConfiguration t) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 根据对象查询提额值百分比和提额尾数，并计算出提额值（额度*提额百分比+提额尾数）
	 * @param scLiftConfiguration
	 * @return
	 */
	public BigDecimal getPromotionQuota(ScLiftConfiguration scLiftConfiguration) {
		ScLiftConfiguration sc = scLiftConfigurationDao.getLift(scLiftConfiguration);
		BigDecimal promotionQuota = new BigDecimal(0);
		if (sc != null && StringUtils.isNoneEmpty(sc.getCreditMoney())
				&& StringUtils.isNoneEmpty(sc.getPercentageOfLift())
				&& StringUtils.isNotEmpty(sc.getPercentageOfLiftMantissa() + "")) {
			if ("1".equals(sc.getMateCertCode()) && "1".equals(scLiftConfiguration.getMateCertCode())) {//当页面提额规则配置的【是否匹配身份证号】为1(0:否，1:是)且老夏那边的校验结果为1(0:否，1:是)时，计算提额并返回
				promotionQuota = reckonPromotionQuota(sc);
			} else if ("1".equals(sc.getMateName()) && "1".equals(scLiftConfiguration.getMateName())) {//当页面提额规则配置的【是否匹配姓名】为1(0:否，1:是)且老夏那边的校验结果为1(0:否，1:是)时，计算提额并返回
				promotionQuota = reckonPromotionQuota(sc);
			} else if ("1".equals(sc.getMatePhone()) && "1".equals(scLiftConfiguration.getMatePhone())) {//当页面提额规则配置的【是否匹配手机号】为1(0:否，1:是)且老夏那边的校验结果为1(0:否，1:是)时，计算提额并返回
				promotionQuota = reckonPromotionQuota(sc);
			}
		}
		return promotionQuota;
	}
 
	/**
	 * 计算提额
	 * 
	 * @param sc
	 * @return
	 */
	private BigDecimal reckonPromotionQuota(ScLiftConfiguration sc) {
		BigDecimal b1, b2, b3, promotionQuota;
		b1 = new BigDecimal(sc.getCreditMoney());
		b2 = new BigDecimal(sc.getPercentageOfLift());
		b3 = new BigDecimal(sc.getPercentageOfLiftMantissa());
		promotionQuota = b1.multiply(b2).add(b3);
		return promotionQuota;
	}
	
}
