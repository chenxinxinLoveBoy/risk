package com.shangyong.backend.dao.bqsrep;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.shangyong.backend.entity.bqsrep.BqsRepMnoSixMobile;

/**
 * BqsRepMnoSixMobileServiceDao数据库操作接口类bean
 * @author chengfeng.lu
 * @date Thu Dec 14 21:25:35 CST 2017
 **/
@Mapper
public interface BqsRepMnoSixMobileServiceDao{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	BqsRepMnoSixMobile  selectByPrimaryKey(@Param("id") Long id);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("id") Long id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int saveEntity(BqsRepMnoSixMobile record);
	

	/**
	 * 
	 * 批量添加
	 * 
	 **/
	int saveAllEntity(List<BqsRepMnoSixMobile> list);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(BqsRepMnoSixMobile record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(BqsRepMnoSixMobile record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(BqsRepMnoSixMobile record);
	/**
	 * 查询6个月联系人数
	 * @param bqsPetitionerId
	 * @return
	 */
	int queryCountById(String bqsPetitionerId);
	/**
	 * 查询6个月联系人数,通话次数大于等于10
	 * @param bqsPetitionerId
	 * @return
	 */
	int queryCountLxById(String bqsPetitionerId);
	/**
	 * 查询6个月联系人数,呼入次数大于等于10
	 * @param bqsPetitionerId
	 * @return
	 */
	int queryCountHrById(String bqsPetitionerId);

}