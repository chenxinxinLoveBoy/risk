package com.shangyong.backend.service;

import java.util.List;
import java.util.Map;

import com.shangyong.backend.entity.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 黑名单相关service
 * @author hc
 *
 */
public interface BlacklistService {
	
	/**
	 * 查询所有黑名单 分页
	 * @param buBlacklist
	 * @return
	 */
	public List<BuBlacklist> listViewAll(BuBlacklist buBlacklist);
	
	/**
	 * 根据idCard查询黑名单
	 * @param buBlacklist
	 * @return
	 */
	public List<BuBlacklist> findByidCard(BuBlacklist buBlacklist);
	
	/**
	 * 统计
	 * @param buBlacklist
	 * @return
	 */
	public int listAllCount(BuBlacklist buBlacklist);
	 
	/**
	 * 跟新对象信息
	 * @param buBlacklist
	 * @return
	 */
	public boolean update(BuBlacklist buBlacklist); 
	
	/**
	 * 添加黑名单
	 * @param buBlacklist
	 * @return
	 */
	public boolean saveEntity(BuBlacklist buBlacklist);
	
	/**
	 * 根据身份证号，用户姓名查询黑名单数据
	 */
	public BuBlacklist queryInfoByObj(BuBlacklist buBlacklist);
	
	/**
	 * 根据身份证号查询黑名单数据
	 */
	public BuBlacklist queryInfoByCertCode(BuBlacklist buBlacklist);
	
	/**
	 * 根据主键ID删除
	 * @param buBlacklist
	 */
	public boolean deleteObj(BuBlacklist buBlacklist);

	/**
	 * 根据s_number (借款申请编号、流水编号,贷后推送流水号),ds_source '数据来源（01-闪贷贷前审核、02-闪贷贷后监控、03-APP同步、04-手工添加、05-大数据添加）',
	 * @param buBlacklist
	 * @return
	 */
	public int queryAllCount(BuBlacklist buBlacklist);
	
	/**
	 * 统计所有
	 **/
	public int listAllSum();

	/**
	 * 根据ds_source和 s_number查询黑名单数据
	 */
	public BuBlacklist findBydsSourceAndsNumber(BuBlacklist buBlacklist);

	/**
	 * 根据身份证号，手机号，设备ID，从redis缓存判断是否名中黑名单
	 * @param certCode 身份证号
	 * @param phoneNum 手机号
	 * @param deviceId 设备ID
	 * @return true 命中，false，未命中
	 */
	public boolean isInBlacklist(String certCode, String phoneNum, String deviceId);
	

	/**
	 * 黑名单上传
	 * @param file 上传文件对象
	 * @return
	 */
	public Map<String,Object> upload(MultipartFile file);
	
	/**
	 * 根据用户身份证号码，手机号码，设备ID单独去匹配是否存在
	 * @param buBlacklist
	 * @return
	 */
	public int queryCountByObj(BuBlacklist buBlacklist);

	/**
	 * MySql中三要素存在情况
	 * @param idCard 身份证号码
	 * @param phoneNum 手机号码
	 * @param deviceId 设备ID
	 * @return
	 */
	public BlacklistResult isInBlacklistTable(String idCard, String phoneNum, String deviceId);

	/**
	 * 判断用户是否是黑名单
	 * @param idCard
	 * @param phoneNum
	 * @param deviceId
	 * @return
	 * @since JDK 1.8
	 */
	public IsBlacklistResult userIsBlacklist(String idCard, String phoneNum, String deviceId);
	
	/**
	 * 判断用户紧急联系人是否是黑名单
	 * @param buBlacklist 用户自身
	 * @param contacts 紧急联系人
	 * @return
	 * @since JDK 1.8
	 */
	public IsBlacklistResult contactsIsBlacklist(BuBlacklist buBlacklist, List<BuBlacklist> contacts);
}
