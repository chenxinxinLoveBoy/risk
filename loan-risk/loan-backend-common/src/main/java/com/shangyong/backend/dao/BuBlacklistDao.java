package com.shangyong.backend.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.BuBlacklist;

/**
 * BuBlacklistDao数据库操作接口类bean
 * @author xk
 * @date Tue May 30 15:34:55 CST 2017
 **/

@Mapper
public interface BuBlacklistDao{

	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	BuBlacklist  selectByPrimaryKey(@Param("id") Long id);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(@Param("id") Long id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(BuBlacklist record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int saveEntity(BuBlacklist record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(BuBlacklist record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	boolean updateByPrimaryKey(BuBlacklist record);

	
	/**
	 * 
	 * 查询
	 * 
	 **/
	List<BuBlacklist> listViewAll(BuBlacklist buBlacklist);

	/**
	 * 
	 * 查询（根据idCard查询）
	 * 
	 **/
	List<BuBlacklist> findByidCard(BuBlacklist buBlacklist);
	 

	/**
	 * 
	 * 统计
	 * 
	 **/
	int listAllCount(BuBlacklist buBlacklist);
 

	/**
	 * 根据用户姓名，身份证号查询黑名单数据
	 */
	BuBlacklist queryInfoByObj(BuBlacklist buBlacklist);
	
	/**
	 * 根据主键ID删除对象
	 * @param buBlacklist
	 * @return
	 */
	int deleteObj(BuBlacklist buBlacklist);
	
	/**
	 * 根据主键ID查询
	 * @param buBlacklist
	 * @return
	 */
	BuBlacklist queryObject(BuBlacklist buBlacklist);

	/**
	 * 根据s_number (借款申请编号、流水编号,贷后推送流水号),ds_source '数据来源（01-闪贷贷前审核、02-闪贷贷后监控、03-APP同步、04-手工添加、05-大数据添加）',
	 * @param buBlacklist
	 * @return
	 */
	int queryAllCount(BuBlacklist buBlacklist);

	/**
	 * 
	 * 统计所有
	 * 
	 **/
	int listAllSum();

	/**
	 * 根据ds_source和 s_number查询黑名单数据
	 */
	BuBlacklist findBydsSourceAndsNumber(BuBlacklist buBlacklist);
	
	/**
	 * 根据用户身份证号码，手机号码，设备ID单独去匹配是否存在
	 * @param buBlacklist
	 * @return
	 */
	int queryCountByObj(BuBlacklist buBlacklist);
	
	/**
	 * 根据用户姓名，身份证号查询黑名单数据
	 */
	BuBlacklist queryInfoByCertCode(BuBlacklist buBlacklist);

	/**
	 * 审批拒绝的信息，添加黑名单表
	 */
	public int insertBlackById(Map<String, Object> map);
}