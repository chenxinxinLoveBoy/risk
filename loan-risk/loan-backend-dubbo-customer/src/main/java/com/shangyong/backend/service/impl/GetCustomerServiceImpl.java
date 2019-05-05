package com.shangyong.backend.service.impl;

import java.util.Date;
import java.util.List;

import com.shangyong.backend.service.GetCustomerService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.common.DictConstant;
import com.shangyong.backend.common.enums.CertTypeEnum;
import com.shangyong.backend.common.enums.GenderEnum;
import com.shangyong.backend.common.enums.MarriageStatusEnum;
import com.shangyong.backend.common.enums.OtherAccountEnum;
import com.shangyong.backend.common.enums.ProfessionIdEnum;
import com.shangyong.backend.common.enums.RelationEnum;
import com.shangyong.backend.dao.CuCustomerCompanyDao;
import com.shangyong.backend.dao.CuCustomerOtherDao;
import com.shangyong.backend.dao.CuIcePersonDao;
import com.shangyong.backend.dao.CuPlatformCustomerDao;
import com.shangyong.backend.dao.FaceRecognitionScoreDao;
import com.shangyong.backend.entity.CuCustomerCompany;
import com.shangyong.backend.entity.CuCustomerOther;
import com.shangyong.backend.entity.CuIcePerson;
import com.shangyong.backend.entity.CuPlatformCustomer;
import com.shangyong.backend.entity.FaceRecognitionScore;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.IDCardUtil;
import com.shangyong.utils.UUIDUtils;

/**
 * 平台客户信息落库
 * 
 * @author xk
 *
 */
@Service
public class GetCustomerServiceImpl  implements GetCustomerService {

	private static Logger logger = LoggerFactory.getLogger("CustomerService");

	@Autowired
	private CuCustomerCompanyDao cuCustomerCompanyDao;

	@Autowired
	private CuCustomerOtherDao cuCustomerOtherDao;

	@Autowired
	private CuIcePersonDao cuIcePersonDao;

	@Autowired
	private CuPlatformCustomerDao cuPlatformCustomerDao;

	@Autowired
	private FaceRecognitionScoreDao faceRecognitionScoreDao;

	@Override
	public CuPlatformCustomer save(List<CuCustomerOther> cuCustomerOtherList,
								   CuPlatformCustomer cuPlatformCustomer,
								   CuCustomerCompany cuCustomerCompany,
								   FaceRecognitionScore faceRecognitionScore) {
		// 当前系统时间
		String currentTime = DateUtils.getDate(new Date());
		logger.info("当前时间：" + currentTime + ", 调取【平台客户信息落库】服务开始！");

		// 平台客户信息表 新增或修改
		cuPlatformCustomer = saveAndUpdateCuPlatformCustomer(cuPlatformCustomer, currentTime);

		// 平台客户编号
		String platformCustomerId = cuPlatformCustomer.getPlatformCustomerId();

		// 平台客户所属公司信息新增或修改
		saveAndUpdateCuCustomerCompany(platformCustomerId, currentTime, cuCustomerCompany);



		// 平台客户其它账号信息新增或修改
		saveAndUpdateCuCustomerOtherList(platformCustomerId, currentTime, cuCustomerOtherList);

		// 平台客户人脸识别评分记录信息新增或修改
		saveAndUpdateFaceRecognitionScore(platformCustomerId, currentTime, faceRecognitionScore);

		return cuPlatformCustomer;
	}


	@Override
	public void saveCuIcePersonList(String applicationId,
									String customerId,
									String platformCustomerId,
									List<CuIcePerson> cuIcePersonList) {

		// 传入平台客户紧急联系人信息为空时直接返回
		if (CollectionUtils.isEmpty(cuIcePersonList)) {
			logger.info("未上送平台客户紧急联系人信息，不做任何处理，直接退出");
			return;
		}
		Assert.hasText(applicationId, "平台客户紧急联系人信息---APP应用[applicationId]不能为空");
		Assert.hasText(customerId, "平台客户紧急联系人信息---APP应用[customerId]不能为空");

		// 清空用户的原先的紧急联系人，然后添加用户新的紧急联系人
		// 先删除
		// int count = cuIcePersonDao.delete(platformCustomerId);
		// logger.info("根据平台客户编号[" + platformCustomerId + "] 删除平台客户紧急联系人信息条数：" + count);
		// 再新增
		// CuIcePerson cuIcePerson = null;
		for (int i = 0; i < cuIcePersonList.size(); i++) {
			CuIcePerson cuIcePerson = cuIcePersonList.get(i);

			// 判断客户的紧急联系人类型
			if (cuIcePerson.getType() != null) {
				if (!RelationEnum.contain(cuIcePerson.getType())) {
					throw new RuntimeException("传入的客户紧急联系人关系类型Type不在有效范围");
				}
			}
			// 设置客户的紧急联系人并保存
			cuIcePerson.setIcePersonId(UUIDUtils.getUUID());
			cuIcePerson.setApplicationId(applicationId);
			cuIcePerson.setCustomerId(customerId);
			cuIcePerson.setPlatformCustomerId(platformCustomerId);
			//cuIcePersonDao.saveEntity(cuIcePerson);
			//logger.info("新增平台客户紧急联系人信息成功，i:" + i);
		}

		cuIcePersonDao.deleteByApplicationId(applicationId);
		boolean saveEntityAll = cuIcePersonDao.saveEntityAll(cuIcePersonList);
		if(saveEntityAll){
			logger.info("新增平台客户紧急联系人信息成功，cuIcePersonList:" + cuIcePersonList);
		}
	}

	/**
	 * 平台客户信息表 新增或修改
	 * 
	 * @param cuPlatformCustomer
	 * @param currentTime
	 * @return
	 */
	public CuPlatformCustomer saveAndUpdateCuPlatformCustomer(CuPlatformCustomer cuPlatformCustomer,
			String currentTime) {

		if (cuPlatformCustomer  == null) {
			throw new RuntimeException("上送的平台客户信息为空, 请检核上送参数");
		}

		// 断言判断参数是否正确
		Assert.hasText(cuPlatformCustomer.getCustomerId(), "平台客户信息---APP应用客户编号不能为空");
		Assert.hasText(cuPlatformCustomer.getAppName(), "平台客户信息---APP名称不能为空");
		Assert.hasText(cuPlatformCustomer.getName(), "平台客户信息---客户姓名不能为空");
		Assert.hasText(cuPlatformCustomer.getCertType(), "平台客户信息---证件类型不能为空");
		Assert.hasText(cuPlatformCustomer.getCertCode(), "平台客户信息---证件号码不能为空");
		Assert.hasText(cuPlatformCustomer.getPhoneNum(), "平台客户信息---客户手机号不能为空");
//		Assert.hasText(cuPlatformCustomer.getNation(), "平台客户信息---客户民族不能为空");
		
		// 检验传入参数app标识是否匹配枚举定义
//		if (!AppNameEnum.contain(cuPlatformCustomer.getAppName())) {
//			throw new RuntimeException("传入的APP名称AppName不在有效范围");
//		}

		// 检验传入参数性别是否匹配枚举定义
		if (cuPlatformCustomer.getGender() != null) {
			if (!GenderEnum.contain(cuPlatformCustomer.getCertType())) {
				throw new RuntimeException("传入的客户性别Gender不在有效范围");
			}
		}

		// 检验传入参数证件类型是否匹配枚举定义
		if (!CertTypeEnum.contain(cuPlatformCustomer.getCertType())) {
			throw new RuntimeException("传入的证件类型CertType不在有效范围");
		}

		// 检验传入参数学历是否匹配枚举定义
		/*if (cuPlatformCustomer.getEducationId() != null) {
			if (!EducationEnum.contain(cuPlatformCustomer.getEducationId())) {
				throw new RuntimeException("传入的客户学历EducationId不在有效范围");
			}
		}*/

		// 检验传入参数婚姻状况是否匹配枚举定义
		if (cuPlatformCustomer.getIfMarriage() != null) {
			if (!MarriageStatusEnum.contain(cuPlatformCustomer.getIfMarriage())) {
				throw new RuntimeException("传入的客户 婚姻状况IfMarriage不在有效范围");
			}
		}

		// 平台客户标识唯一id
		String platformCustomerId = "";

		boolean flag = false;

		// 根据customer_id和app_name来判断客户信息是否是唯一性存在
		CuPlatformCustomer customer = cuPlatformCustomerDao.getEntityById(cuPlatformCustomer);
		if (customer == null) {// 客户信息不存在，此时新增客户信息

			platformCustomerId = UUIDUtils.getUUID();
			cuPlatformCustomer.setPlatformCustomerId(platformCustomerId);
			cuPlatformCustomer.setModifyTime(currentTime);// 新增时增加修改时间入库
			cuPlatformCustomer.setCreateTime(currentTime);// 新增时增加创建时间入库

			// 判断是否是身份证证件
			if (DictConstant.CERT_TYPE_ONE.equals(cuPlatformCustomer.getCertType())) {
				cuPlatformCustomer.setGender(IDCardUtil.parseGender(cuPlatformCustomer.getCertCode()));// 根据客户身份证号初始化客户性别
				cuPlatformCustomer.setAge(IDCardUtil.parseAge(cuPlatformCustomer.getCertCode()));// 根据客户身份证号初始化客户年龄
			}
			logger.info("保存平台客户信息cuPlatformCustomer=" + cuPlatformCustomer);
			// 保存平台客户信息
			flag = cuPlatformCustomerDao.saveEntity(cuPlatformCustomer);

		} else {// 客户信息存在 修改客户最新信息并返回

			platformCustomerId = customer.getPlatformCustomerId();
			cuPlatformCustomer.setModifyTime(currentTime);
			cuPlatformCustomer.setPlatformCustomerId(platformCustomerId);

			if (DictConstant.CERT_TYPE_ONE.equals(cuPlatformCustomer.getCertType())) {
				cuPlatformCustomer.setGender(IDCardUtil.parseGender(customer.getCertCode()));// 根据客户身份证号初始化客户性别
				cuPlatformCustomer.setAge(IDCardUtil.parseAge(customer.getCertCode()));// 根据客户身份证号初始化客户年龄
			}
			logger.info("修改平台客户信息cuPlatformCustomer=" + cuPlatformCustomer);
			// 修改平台客户信息
			flag = cuPlatformCustomerDao.updateEntity(cuPlatformCustomer);
		}
		logger.info("平台客户信息表新增或更新结果：" + flag + "，平台客户编号：" + platformCustomerId);
		return cuPlatformCustomer;
	}

	/**
	 * 平台客户所属公司信息新增或修改
	 * 
	 * @param platformCustomerId
	 *            平台客户编号
	 * @param currentTime
	 * @param cuCustomerCompany
	 *            平台客户所属公司信息
	 */
	public void saveAndUpdateCuCustomerCompany(String platformCustomerId, String currentTime,
			CuCustomerCompany cuCustomerCompany) {
		// 传入公司信息为空时直接返回
		if (cuCustomerCompany == null) {
			logger.info("未上送平台客户所属公司信息，不做任何处理，直接退出");
			return;
		}

		// 检验传入参数职业编号是否匹配枚举定义
		if (cuCustomerCompany.getProfessionId() != null) {
			if (!ProfessionIdEnum.contain(cuCustomerCompany.getProfessionId())) {
				throw new RuntimeException("传入的客户职业编号ProfessionId不在有效范围");
			}
		}

		Assert.hasText(cuCustomerCompany.getCustomerId(), "平台客户所属公司信息---APP应用客户编号不能为空");

		boolean flag = false;
		// 客户公司编号
		String customerCompanyId = "";
		// 获取用户公司信息
		CuCustomerCompany customerCompany = cuCustomerCompanyDao.getEntityById(platformCustomerId);
		// 如果客户公司信息为空，重新进行公司保存
		if (customerCompany == null) {
			customerCompanyId = UUIDUtils.getUUID();
			cuCustomerCompany.setCustomerCompanyId(customerCompanyId);
			cuCustomerCompany.setPlatformCustomerId(platformCustomerId);
			flag = cuCustomerCompanyDao.saveEntity(cuCustomerCompany);
		} else {
			// 如果客户公司编号不为空，对客户的公司信息修改
			customerCompanyId = customerCompany.getCustomerCompanyId();
			cuCustomerCompany.setModifyTime(currentTime);
			cuCustomerCompany.setCustomerCompanyId(customerCompanyId);
			cuCustomerCompany.setPlatformCustomerId(platformCustomerId);
			flag = cuCustomerCompanyDao.updateEntity(cuCustomerCompany);
		}
		logger.info("平台客户所属公司信息表新增或更新结果：" + flag + "，客户所属公司编号：" + customerCompanyId);
	}



	/**
	 * 平台客户其它账号信息新增或修改
	 * 
	 * @param platformCustomerId
	 *            平台客户编号
	 * @param currentTime
	 * @param cuCustomerOtherList
	 *            平台客户其它账号信息
	 */
	public void saveAndUpdateCuCustomerOtherList(String platformCustomerId, String currentTime,
			List<CuCustomerOther> cuCustomerOtherList) {
		// 传入平台客户其它账号信息为空时直接返回
		if (CollectionUtils.isEmpty(cuCustomerOtherList)) {
			logger.info("未上送平台客户其它账号信息，不做任何处理，直接退出");
			return;
		}
		// 先删除用户的其他账号信息，然后在保存用户的新的其他账号信息
		int count = cuCustomerOtherDao.delete(platformCustomerId);
		logger.info("根据平台客户编号[" + platformCustomerId + "] 删除平台客户其它账号信息条数：" + count);
		
		// 保存用户新的其他账号信息
		CuCustomerOther cuCustomerOther = null;
		for (int i = 0; i < cuCustomerOtherList.size(); i++) {
			cuCustomerOther = cuCustomerOtherList.get(i);

			Assert.hasText(cuCustomerOther.getCustomerId(), "平台客户紧急联系人信息---APP应用客户编号不能为空");

			// 判断客户的其他账号信息类型是否是指定的账号信息
			if (cuCustomerOther.getOtherType() != null) {
				if (!OtherAccountEnum.contain(cuCustomerOther.getOtherType())) {
					throw new RuntimeException("传入的客户的其它账号信息类型OtherType不在有效范围");
				}
			}
			cuCustomerOther.setOtherInfoId(UUIDUtils.getUUID());
			cuCustomerOther.setPlatformCustomerId(platformCustomerId);
			cuCustomerOtherDao.saveEntity(cuCustomerOther);
			logger.info("新增平台客户其它账号信息成功，i:" + i);
		}
	}

	/**
	 * 平台客户人脸识别评分记录信息新增或修改
	 * 
	 * @param platformCustomerId
	 *            平台客户编号
	 * @param currentTime
	 * @param faceRecognitionScore
	 *            客户人脸识别评分记录信息
	 */
	public void saveAndUpdateFaceRecognitionScore(String platformCustomerId, String currentTime, FaceRecognitionScore faceRecognitionScore) {
		
		// 传入人脸识别评分记录信息为空时直接返回
		if (faceRecognitionScore == null) {
			logger.info("未上送平台客户人脸识别评分记录信息，不做任何处理，直接退出");
			return;
		}

		boolean flag = false;
		
		// 客户认证编号
		String authenticationNumber = "";
		
		// 获取客户原来的面部识别信息
		FaceRecognitionScore recognitionScore = faceRecognitionScoreDao.getEntityById(platformCustomerId);
		
		// 如果原来的面部识别评分信息为空，新增面部信息
		if (recognitionScore == null) {
			authenticationNumber = UUIDUtils.getUUID();
			faceRecognitionScore.setAuthenticationNumber(authenticationNumber);
			faceRecognitionScore.setPlatformCustomerId(platformCustomerId);
			flag = faceRecognitionScoreDao.saveEntity(faceRecognitionScore);
		} else {
			
			// 如果原来的面部识别评分信息不为空，更新客户面部评分信息
			authenticationNumber = recognitionScore.getAuthenticationNumber();
			faceRecognitionScore.setModifyTime(currentTime);
			faceRecognitionScore.setAuthenticationNumber(authenticationNumber);
			faceRecognitionScore.setPlatformCustomerId(platformCustomerId);
			flag = faceRecognitionScoreDao.updateEntity(faceRecognitionScore);
		}
		logger.info("平台客户人脸识别评分记录信息表新增或更新结果：" + flag + "，客户认证编号：" + authenticationNumber);
	}

}
