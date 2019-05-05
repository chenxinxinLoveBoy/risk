package com.shangyong.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangyong.backend.dao.ScBanControlTplHisDao;
import com.shangyong.backend.entity.ScBanControlTplHis;
import com.shangyong.backend.service.BaseService; 

/**
 * 禁止项模版历史service实现
 * @author hc
 *
 */
@Service
public class ScTemplateHistServiceImpl implements BaseService<ScBanControlTplHis> {
	
	@Autowired
	private ScBanControlTplHisDao  scBanControlTplHisDao;

	@Override
	public List<ScBanControlTplHis> findAll(ScBanControlTplHis scBanControlTplHis) { 
		return scBanControlTplHisDao.findAll(scBanControlTplHis);
	}

	@Override
	public ScBanControlTplHis getEntityById(String id) { 
		return null;
	}

	@Override
	public ScBanControlTplHis getEntityById(Integer id) { 
		return scBanControlTplHisDao.getEntityById(id);
	}

	@Override
	public ScBanControlTplHis getEntityById(ScBanControlTplHis t) { 
		return null;
	}

	@Override
	public boolean saveEntity(ScBanControlTplHis t) { 
		return false;
	}

	@Override
	public boolean updateEntity(ScBanControlTplHis t) { 
		return false;
	}

	@Override
	public boolean deleteEntity(String id) { 
		return false;
	}

	@Override
	public boolean deleteEntity(ScBanControlTplHis t) { 
		return false;
	}
	
	public int listAllCount(ScBanControlTplHis scBanControlTplHis) { 
		int count = scBanControlTplHisDao.listAllCount(scBanControlTplHis);
		return count;
	} 
}
