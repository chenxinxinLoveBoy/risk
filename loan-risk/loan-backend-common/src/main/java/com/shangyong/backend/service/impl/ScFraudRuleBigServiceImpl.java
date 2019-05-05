package com.shangyong.backend.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shangyong.backend.bo.UUserBo;
import com.shangyong.backend.common.shiroToken.TokenManager;
import com.shangyong.backend.dao.ScFraudRuleBigDao;
import com.shangyong.backend.entity.ScFraudRule;
import com.shangyong.backend.entity.ScFraudRuleBig;
import com.shangyong.backend.entity.ScFraudRuleBigHis;
import com.shangyong.backend.service.BaseService;
import com.shangyong.backend.utils.DateUtils;

@Service
public class ScFraudRuleBigServiceImpl implements BaseService<ScFraudRuleBig> {
	
	@Autowired
	private ScFraudRuleBigDao scFraudRuleBigDao;
	
	@Autowired
	private ScFraudRuleBigHisServiceImpl scFraudRuleBigHisService;
	
	@Autowired
	private ScFraudRuleServiceImpl scFraudRuleService;

	@Override
	public List<ScFraudRuleBig> findAll(ScFraudRuleBig scFraudRuleBig) {
		return scFraudRuleBigDao.findAll(scFraudRuleBig);
	}

	@Override
	public ScFraudRuleBig getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScFraudRuleBig getEntityById(Integer fraudRuleBigId) {
		return scFraudRuleBigDao.getEntityById(fraudRuleBigId);
	}

	@Override
	public ScFraudRuleBig getEntityById(ScFraudRuleBig scFraudRuleBig) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean saveEntity(ScFraudRuleBig scFraudRuleBig) {
        scFraudRuleBig.setVersion(1);// 新增时默认为1
		scFraudRuleBig.setCreateTime(DateUtils.getDate(new Date()));
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scFraudRuleBig.setCreateMan(user.getId().toString());
			scFraudRuleBig.setCreateName(user.getNickName());
		} else {
			scFraudRuleBig.setCreateMan("");
			scFraudRuleBig.setCreateName("");
		}
		scFraudRuleBig.setModifyTime(DateUtils.getDate(new Date()));
		boolean flag = scFraudRuleBigDao.saveEntity(scFraudRuleBig);
		ScFraudRuleBigHis scFraudRuleBigHis = new ScFraudRuleBigHis();
		BeanUtils.copyProperties(scFraudRuleBig, scFraudRuleBigHis);
		scFraudRuleBigHisService.saveEntity(scFraudRuleBigHis);
		return flag;
	}

	@Override
	@Transactional
	public boolean updateEntity(ScFraudRuleBig scFraudRuleBig) {
		UUserBo user = TokenManager.getUser();
		if (user != null) {
			scFraudRuleBig.setModifyMan(user.getId().toString());
			scFraudRuleBig.setModifyName(user.getNickName());
		} else {
			scFraudRuleBig.setModifyMan("");
			scFraudRuleBig.setModifyName("");
		}
		scFraudRuleBig.setModifyTime(DateUtils.getDate(new Date()));
		scFraudRuleBig.setVersion(scFraudRuleBig.getVersion() + 1);// 修改是版本号自增1
		boolean flag = scFraudRuleBigDao.updateEntity(scFraudRuleBig);

		// 修改大类下对应小类的的状态
		if ("1".equals(scFraudRuleBig.getUpdateFraudRule())) {
			ScFraudRule fraudRule = new ScFraudRule();
			fraudRule.setFraudRuleBigId(scFraudRuleBig.getFraudRuleBigId());// 大类序号
			fraudRule.setState("02");// 置为失效
			boolean fraudRuleFlag = scFraudRuleService.updateFraudRuleByBigId(fraudRule);
			// 如小类修改失败，回滚
			if (!fraudRuleFlag) {
				throw new RuntimeException("类：ScFraudRuleBigServiceImpl，方法名：updateEntity（），修改小类状态异常！！！！！");
			}
		}
		ScFraudRuleBigHis scFraudRuleBigHis = new ScFraudRuleBigHis();
		BeanUtils.copyProperties(scFraudRuleBig, scFraudRuleBigHis);
		scFraudRuleBigHisService.saveEntity(scFraudRuleBigHis);
		return flag;
	}

	@Override
	public boolean deleteEntity(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEntity(ScFraudRuleBig scFraudRuleBig) {
		// TODO Auto-generated method stub
		return false;
	}

	public int listAllCount(ScFraudRuleBig scFraudRuleBig) {
		// TODO Auto-generated method stub
		return scFraudRuleBigDao.listAllCount(scFraudRuleBig);
	}

	public ScFraudRuleBig queryByFraudRuleBigCode(String fraudRuleBigCode) {
		// TODO Auto-generated method stub
		return scFraudRuleBigDao.queryByFraudRuleBigCode(fraudRuleBigCode);
	}

}
