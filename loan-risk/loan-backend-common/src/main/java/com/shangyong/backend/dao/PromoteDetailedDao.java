package com.shangyong.backend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shangyong.backend.entity.PromoteDetailed;


@Mapper
public interface PromoteDetailedDao {

	public int saveEntity(PromoteDetailed promoteDetailed);
	
	public PromoteDetailed getInfoByCidAndTaskType(PromoteDetailed promoteDetailed);
	
	public List<PromoteDetailed> getPushMsgInfo();
	
	public int updateJsonPathByCid(PromoteDetailed promoteDetailed);
	
	/**
	 * add: xiajiyun
	 * 修改对象信息
	 * @param promoteDetailed
	 * @return
	 */
	public boolean updateEntity(PromoteDetailed promoteDetailed);
	
	/**
	 * add: xiajiyun
	 * 根据id查询对象信息
	 * @param promoteDetailedId
	 * @return
	 */
	public PromoteDetailed getEntityById(String promoteDetailedId);
	
	
	/**
	 * add: CG
	 * 根据条件手工推送提额消息至APP
	 * @param promoteDetailedId
	 * @return
	 */
	public PromoteDetailed getEntityPushAppById(String promoteDetailedId);
	
	
	public int updatePushSateById(PromoteDetailed promoteDetailed);
	
	/**
	 * 获取是否有公积金/社保
	 * @param promoteDetailed
	 * @return
	 */
	public PromoteDetailed getPromoteDetailed(PromoteDetailed promoteDetailed);
	
}
