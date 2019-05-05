package com.shangyong.backend.service.approval.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.shangyong.backend.dao.ScLinkmanTypeCountDao;
import com.shangyong.backend.dao.approval.CustomerDirectoriesDao;
import com.shangyong.backend.entity.ScLinkmanTypeCount;
import com.shangyong.backend.entity.approval.CustomerDirectories;
import com.shangyong.backend.service.approval.service.CustomerDirectoriesService;
import com.shangyong.backend.common.enums.DirectoriesCodeEnum;
import com.shangyong.backend.utils.DirectoriesRuleUtils;
import com.shangyong.utils.StringUtil;

@Service
public class CustomerDirectoriesServiceImpl implements CustomerDirectoriesService {

	private static Logger logger = LoggerFactory.getLogger("CustomerDirectoriesServiceImpl");
	
	@Autowired
	private CustomerDirectoriesDao customerDirectoriesDao;

	@Autowired
	private ScLinkmanTypeCountDao scLinkmanTypeCountDao;

	private static final int PHONE_ILLEGAL_LEN_13 = 13; 
		
	@Override
	public LinkedHashMap<String, Object> findAllcustomerDirectories(String customerId,String appName) {
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		//根据客户编号查询所有的通讯录信息
		List<CustomerDirectories> list = customerDirectoriesDao.findAll(customerId,appName);
		int txlNum = list.size();
		//异常号码个数
		int exceptionPhoneSize = 0;
		//异类号码个数
		int errorPhoneSize = 0;
		//正常号码个数
		int size = 0;
		if (CollectionUtils.isNotEmpty(list)) {
			//根据联系人状态进行号码数据个数汇总
			for (CustomerDirectories customerDirectories : list) {
				if (customerDirectories.getDirectoriesState() == 4) { //异常异类号码
					exceptionPhoneSize++;
					errorPhoneSize++;
				} else if (customerDirectories.getDirectoriesState() == 3) { //异常号码
					exceptionPhoneSize++;
				} else if (customerDirectories.getDirectoriesState() == 2) { //异类号码
					errorPhoneSize++;
				} else if (customerDirectories.getDirectoriesState() == 1) {
					size++;
				}
			}
		}
		map.put("exceptionPhoneSize", exceptionPhoneSize);
		map.put("errorPhoneSize", errorPhoneSize);
		map.put("phoneSize", size);
		map.put("txlNum", txlNum);
		map.put("list", list);
		return map;
	}

	@Override
	public LinkedHashMap<String, Object> regulateDirectories(List<CustomerDirectories> directorieslist) {

		List<CustomerDirectories> erlist = new ArrayList<CustomerDirectories>();//异类号码
		List<CustomerDirectories> exlist = new ArrayList<CustomerDirectories>();//异常号码
		List<CustomerDirectories> nelist = new ArrayList<CustomerDirectories>();//正常号码
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		List<CustomerDirectories> list = checkNameAndPhone(directorieslist);
		//异常号码个数
		if (CollectionUtils.isNotEmpty(list)) {
			//根据联系人状态进行号码数据个数汇总
			for (CustomerDirectories customerDirectories : list) {
				if (customerDirectories.getDirectoriesState() != null) {
					if (customerDirectories.getDirectoriesState() == 4) { //异常异类号码
						erlist.add(customerDirectories);
						exlist.add(customerDirectories);
					} else if (customerDirectories.getDirectoriesState() == 3) { //异常号码
						exlist.add(customerDirectories);
					} else if (customerDirectories.getDirectoriesState() == 2) { //异类号码
						erlist.add(customerDirectories);
					} else if (customerDirectories.getDirectoriesState() == 1) {
						nelist.add(customerDirectories);
					}
				}
			}
		}
		map.put("exceptionPhoneSize", exlist.size());	//异常号码数量
		map.put("errorPhoneSize", erlist.size());		//异类号码数量
		map.put("phoneSize", nelist.size());			//正常号码数量
		map.put("txlNum", list.size());					//总联系人数量
		map.put("list", list);							//所有联系人
		map.put("erlist", erlist);						//异类号码联系人
		map.put("exlist", exlist);						//异常号码联系人
		map.put("nelist", nelist);						//正常号码联系人
		return map;
	}


	@Override
	public int regulateDirectoriesNelist(List<CustomerDirectories> directories) {
		Map<String,Integer> contactNameList = checkNormalContactName(directories);
		return contactNameList.keySet().size();
	}

	@Override
	public int regulateDirectoriesByNelist(String applicationId, String customerId, List<CustomerDirectories> directories) {
		ScLinkmanTypeCount linkman = checkNormalByContactName(directories);
		linkman.setApplicationId(applicationId);
		linkman.setCustomerId(customerId);
		linkman.setCreateTime(new Date());
		scLinkmanTypeCountDao.saveEntity(linkman);
		return linkman.getNormalLinkmanCnt();
	}

	/**.
	 * 通讯录中联系人状态判断(web端)
	 * @param list
	 */
	private List<CustomerDirectories> checkNameAndPhone(List<CustomerDirectories> list){
		List<CustomerDirectories> directoriesList = new ArrayList<CustomerDirectories>();
		Map<CustomerDirectories, CustomerDirectories> map = checkContactName(list);
		for (CustomerDirectories customerDirectories : map.values()) {
			String extend = customerDirectories.getExtend();
			String name = customerDirectories.getContactName();
			String phoneNum = customerDirectories.getContactPhone();
			/**
			 * 这段逻辑是应为 app 端 在存储 用户通信录是 在前面多加了一个英文的逗号","
			 * 所以要先删除在做业务逻辑处理
			 */
			if(StringUtils.isNotBlank(phoneNum) && phoneNum.length() > 0){
				if((int)phoneNum.charAt(0) == 44){
					phoneNum = phoneNum.substring(1, phoneNum.length());
				}
			}

			phoneNum = phoneNum.replaceAll("-", "");
			phoneNum = phoneNum.replaceAll(StringUtil.SPACE_STR, "");
			List<String> phones = Arrays.asList(phoneNum.split(","));
			String errorMessage = null;

			/*异类号码逻辑处理*/
			if (!StringUtil.checkNotNull(name)) {
				if (StringUtil.checkNotNull(extend)) {
					extend += "," +DirectoriesRuleUtils.DIRECTORIES_NAME_IS_NULL;
				} else {
					extend = DirectoriesRuleUtils.DIRECTORIES_NAME_IS_NULL;
				}
			} else {
				boolean result=name.matches("[0-9]+");
				if (result) {
					if (StringUtil.checkNotNull(extend)) {
						extend += "," +DirectoriesRuleUtils.DIRECTORIES_NAME_IS_NUM;
					} else {
						extend = DirectoriesRuleUtils.DIRECTORIES_NAME_IS_NUM;
					}
				}

				/*异常号码逻辑处理*/
				for (DirectoriesCodeEnum dire : DirectoriesCodeEnum.values()) {
					if (name.indexOf(dire.getMessage())!=-1) {
						if (StringUtil.checkNotNull(errorMessage)) {
							errorMessage += "," + dire.getMessage();
						} else {
							errorMessage = dire.getMessage();
						}
					}
				}
			}
            
			//过滤重复数据
			Map<String,String> mapRepeat=new HashMap<String,String>();
			mapRepeat.put("extend", extend);
			mapRepeat.put("errorMessage", errorMessage);
			
			for (String phone : phones) {
				
				//过滤重复数据
				extend=mapRepeat.get("extend");
				errorMessage=mapRepeat.get("errorMessage");
				
				phone = phone.replace("+86", "");
				if(PHONE_ILLEGAL_LEN_13 == phone.length()){
					if(phone.startsWith("86")){				//判断开头两位是否是86     startsWith()：判断字符串是否以指定字符串为开头
						phone.substring(2, phone.length());	//如果是去除开头两位
					}
				}
				if (!StringUtil.checkNotNull(phone)) {
					if (StringUtil.checkNotNull(extend)) {
						extend += "," +DirectoriesRuleUtils.DIRECTORIES_PHONE_IS_NULL;
					} else {
						extend = DirectoriesRuleUtils.DIRECTORIES_PHONE_IS_NULL;
					}
				} else {
					if (phone.length() > 11 || phone.length() < 8) {
						if (StringUtil.checkNotNull(extend)) {
							extend += "," +DirectoriesRuleUtils.DIRECTORIES_PHONE_IS_IRRATIONAL;
						} else {
							extend = DirectoriesRuleUtils.DIRECTORIES_PHONE_IS_IRRATIONAL;
						}
					}
				}
				if (StringUtil.checkNotNull(extend)) {
					customerDirectories.setDirectoriesState(2);
					extend = "异类信息:" +extend;
				} else {
					customerDirectories.setDirectoriesState(1);
				}

				if (StringUtil.checkNotNull(errorMessage)) {
					customerDirectories.setDirectoriesState(3);
				}
				if (StringUtil.checkNotNull(errorMessage) && StringUtil.checkNotNull(extend)) {
					customerDirectories.setDirectoriesState(4);
					extend = extend + ";异常信息:" + errorMessage;
				} else if (StringUtil.checkNotNull(errorMessage)){
					extend = errorMessage;
				}
				if (phones.size()>1) {
					CustomerDirectories directories = new CustomerDirectories();
					BeanUtils.copyProperties(customerDirectories, directories);
					directories.setContactPhone(phone);
					directories.setExtend(extend);
					directoriesList.add(directories);
				}else{
					customerDirectories.setContactPhone(phone);
					customerDirectories.setExtend(extend);
					directoriesList.add(customerDirectories);
				}
			}

		}
		return directoriesList;
	}

	/**
	 * 姓名。同一姓名出现3次及以上的
	 */
	private static Map<CustomerDirectories, CustomerDirectories> checkContactName(List<CustomerDirectories> list){
		Map<CustomerDirectories, CustomerDirectories> map = new HashMap<CustomerDirectories, CustomerDirectories>();
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i), list.get(i));
		}
		for (CustomerDirectories dire : map.keySet()) {
			String name = dire.getContactName();
			int nameNum = 0;
			for (CustomerDirectories cuCustomerDirectories : list) {
				if (StringUtils.equals(name, cuCustomerDirectories.getContactName())) {
					nameNum++;
				}
			}
			if (nameNum>=3) {
				for (CustomerDirectories dir : map.keySet()) {
					if (StringUtils.equals(name, dir.getContactName())) {
						CustomerDirectories c = dir;
						String extend = DirectoriesRuleUtils.DIRECTORIES_NAME_IS_MORE;
						dir.setExtend(extend);
						map.put(c, dir);
					}
				}
			}
		}
		return map;
	}

	/**
	 * ken add to 2017/10/30 19:02 desc
	 * 姓名,正常的通讯录数量
	 * @param list
	 * @return
	 */
	private static Map<String,Integer> checkNormalContactName(List<CustomerDirectories> list){
		//已存在的姓名集合
		Map<String,Integer> extName = new HashMap<String,Integer>();

		for (CustomerDirectories cuCustomerDirectories : list) {
			String contactName = cuCustomerDirectories.getContactName();
			String phone = cuCustomerDirectories.getContactPhone();
			//姓名不为空且已存在的集合中未找到的；正常联系人个数加1
			if (StringUtil.checkNotNull(contactName)) {
				if (StringUtil.checkNotNull(phone) && phone.length()<=12 && phone.length()>=8) {

					boolean result=contactName.matches("[0-9]+");
					if (result) {
						//姓名为纯数字
						continue;
					}
					if (!extName.containsKey(contactName)) {
						//不存在，则添加到集合
						extName.put(contactName,1);
					}else{
						//正常联系人个数加1
						int nowNum = extName.get(contactName)+1;
						//同一姓名出现3次及以上，则移除集合
						if(DirectoriesRuleUtils.NAME_IS_MORE_COUNT <= nowNum){
							extName.remove(contactName);
						}else{
							extName.put(contactName,nowNum);
						}
					}
				}else {
					//异类
				}
			}
		}
		return extName;
	}


	/**
	 * ken add to 2017/10/30 19:02 desc
	 * 姓名,正常的通讯录数量(风控后台禁止项)
	 * @param list
	 * @return
	 */
	public static ScLinkmanTypeCount checkNormalByContactName(List<CustomerDirectories> list){
		int linkmanCnt = 0;
		int normalLinkmanCnt = 0; //正常联系人
		int abnormalLinkmanCnt = 0; //异常联系人个数
		int abnormalDaiLinkmanCnt = 0 ; //包含 贷 联系人个数
		int abnormalXinYongKaLinkmanCnt = 0;  // 包含 信用卡 联系人个数
		int abnormalBanKaLinkmanCnt = 0;  // 包含 办卡 联系人个数
		int abnormalTieLinkmanCnt = 0;  // 包含 提额 联系人个数
		int abnormalHeiHuLinkmanCnt = 0;  // 包含 黑户 联系人个数
		int abnormalKouZiLinkmanCnt = 0;  // 包含 口子 联系人个数
		int abnormalDuLinkmanCnt = 0;  // 包含 赌 联系人个数
		int abnormalJieDaiBaoLinkmanCnt = 0;  // 包含 借贷宝 联系人个数
		int abnormalTaoXianLinkmanCnt = 0;  // 包含 套现 联系人个数
		int abnormalTiXianLinkmanCnt = 0;  // 包含 提现 联系人个数
		int abnormalPosLinkmanCnt = 0;  // 包含 pos（不区分大小写） 联系人个数
		int abnormalCuiShouLinkmanCnt = 0;  // 包含 催收 联系人个数
		int abnormalQianLinkmanCnt = 0;  // 包含 欠 联系人个数
		int abnormalJiaZhengLinkmanCnt = 0;  // 包含 假证 联系人个数
		int abnormalXianJinLinkmanCnt = 0;  // 包含 现金 联系人个数
		int alienLinkmanCnt = 0;  // 异类联系人个数
		int alienNullNameLinkmanCnt = 0;  // 姓名为空联系人个数
		int alienNumNameLinkmanCnt = 0;  // 姓名为纯数字联系人个数
		int alienNullNumLinkmanCnt = 0;  // 号码为空联系人个数
		int alienName3LinkmanCnt = 0;  // 同一姓名出现3次及以上联系人个数（去重前）
		int alienNum3LinkmanCnt = 0;  // 同一号码出现3次及以上联系人个数（去重前）
		int alienName3LinkmanCnt2 = 0;  // 同一姓名出现3次及以上联系人个数（去重后）
		int alienNum3LinkmanCnt2 = 0;  // 同一号码出现3次及以上联系人个数（去重后）
		int alienNum8or12LinkmanCnt = 0;  // 号码超过12位或少于8位联系人个数

		ScLinkmanTypeCount scLinkmanTypeCount = new ScLinkmanTypeCount();

		//判断异类的 通讯录 剔除条异类的数据，在去判断异常的数据
		List<CustomerDirectories> newList = splitPhoneNumber(list);
		linkmanCnt = newList.size();

		Map<String, List<String>> sameName =  new HashMap<>();// key=name, value = directoriesId
		Map<String, List<String>> samePhoneNo = new HashMap<>();// key=phone, value = directoriesId

		//1.判断异类的 通讯录
		for (int i = 0; i < newList.size(); i++ ) {
			CustomerDirectories elem = newList.get(i);
			String name = elem.getContactName();
			String phone = elem.getContactPhone();

			/*异类号码逻辑处理 以下的逻辑不能随意调整，如果要调整必须经过 小雨哥 确认*/
			//1. 判断 名字为空
			if (!StringUtil.checkNotNull(name)) {
				alienLinkmanCnt++;
				alienNullNameLinkmanCnt++;
				newList.remove(i);
				--i;
				continue;
			}
			//2. 判断 号码为空联系人个数
			if(!StringUtil.checkNotNull(phone)){
				alienLinkmanCnt++;
				alienNullNumLinkmanCnt++;
				newList.remove(i);
				--i;
				continue;
			}

			//3. 判断 姓名为纯数字联系人个数
			boolean result = name.matches("[0-9]+");
			if (result) {
				alienLinkmanCnt++;
				alienNumNameLinkmanCnt++;
				newList.remove(i);
				--i;
				continue;
			}

			//4. 判断 号码超过12位或少于8位联系人个数
			if (phone.length() > 12 || phone.length() < 8) {
				alienLinkmanCnt++;
				alienNum8or12LinkmanCnt++;
				newList.remove(i);
				--i;
				continue;
			}
			// 根据 名字, 手机号的 维度 计算综合
			// 5.1相同名字
			if(null == sameName.get(elem.getContactName())){
				List<String> temp = new ArrayList<>();
				temp.add(elem.getDirectoriesId());
				sameName.put(elem.getContactName(), temp);
			}else{
				sameName.get(elem.getContactName()).add(elem.getDirectoriesId());
			}

			// 5.2 相同手机号
			if(null == samePhoneNo.get(elem.getContactPhone())){
				List<String> temp = new ArrayList<>();
				temp.add(elem.getDirectoriesId());
				samePhoneNo.put(elem.getContactPhone(), temp);
			}else{
				samePhoneNo.get(elem.getContactPhone()).add(elem.getDirectoriesId());
			}
		}
		Set<String> countSet = new HashSet<>();

		//6. 处理 相同手机号和 相同 姓名
		for(Map.Entry<String, List<String>> elem : sameName.entrySet()){
			if(null != elem.getValue() && elem.getValue().size() >= 3){
				alienName3LinkmanCnt2 ++;
				alienName3LinkmanCnt = alienName3LinkmanCnt + elem.getValue().size();
				//移除掉  姓名 重复 超过3次的数据
				for(int i = 0 ;i < newList.size(); i++){
					if(newList.get(i).getContactName().equals(elem.getKey())){
						newList.remove(i);
						--i;
					}
				}
				countSet.addAll(elem.getValue());
			}
		}

		for(Map.Entry<String, List<String>> elem : samePhoneNo.entrySet()){
			if(null != elem.getValue() && elem.getValue().size() >= 3){
				alienNum3LinkmanCnt2 ++;
				alienNum3LinkmanCnt = alienNum3LinkmanCnt + elem.getValue().size();
				//移除掉 手机号码重复 超过3次的数据
				for(int i = 0 ;i < newList.size(); i++){
					if(newList.get(i).getContactPhone().equals(elem.getKey())){
						newList.remove(i);
						--i;
					}
				}
				countSet.addAll(elem.getValue());
			}
		}

		alienLinkmanCnt = alienLinkmanCnt + countSet.size();

		//7. 判断 异常号码逻辑处理
		for (int i = 0; i < newList.size(); i++) {
			String tempName = newList.get(i).getContactName();
			boolean isNeedDel = true;
			if(tempName.indexOf(DirectoriesCodeEnum.DAI.getMessage())!=-1){
				abnormalLinkmanCnt++;
				abnormalDaiLinkmanCnt++;
			}else if(tempName.indexOf(DirectoriesCodeEnum.XINYONGKA.getMessage())!=-1){
				abnormalLinkmanCnt++;
				abnormalXinYongKaLinkmanCnt++;
			}else if(tempName.indexOf(DirectoriesCodeEnum.BANKA.getMessage())!=-1){
				abnormalLinkmanCnt++;
				abnormalBanKaLinkmanCnt++;
			}else if(tempName.indexOf(DirectoriesCodeEnum.TIE.getMessage())!=-1){
				abnormalLinkmanCnt++;
				abnormalTieLinkmanCnt++;
			}else if(tempName.indexOf(DirectoriesCodeEnum.HEIHU.getMessage())!=-1){
				abnormalLinkmanCnt++;
				abnormalHeiHuLinkmanCnt++;
			}else if(tempName.indexOf(DirectoriesCodeEnum.KOUZI.getMessage())!=-1){
				abnormalLinkmanCnt++;
				abnormalKouZiLinkmanCnt++;
			}else if(tempName.indexOf(DirectoriesCodeEnum.DU.getMessage())!=-1){
				abnormalLinkmanCnt++;
				abnormalDuLinkmanCnt++;
			}else if(tempName.indexOf(DirectoriesCodeEnum.JIEDAIBAO.getMessage())!=-1){
				abnormalLinkmanCnt++;
				abnormalJieDaiBaoLinkmanCnt++ ;
			}else if(tempName.indexOf(DirectoriesCodeEnum.TAOXIAN.getMessage())!=-1){
				abnormalLinkmanCnt++;
				abnormalTaoXianLinkmanCnt++;
			}else if(tempName.indexOf(DirectoriesCodeEnum.TIXIAN.getMessage())!=-1){
				abnormalLinkmanCnt++;
				abnormalTiXianLinkmanCnt++;
			}else if(tempName.toLowerCase().indexOf(DirectoriesCodeEnum.POSY.getMessage())!=-1){
				abnormalLinkmanCnt++;
				abnormalPosLinkmanCnt++;
			}else if(tempName.indexOf(DirectoriesCodeEnum.CUISHOU.getMessage())!=-1){
				abnormalLinkmanCnt++;
				abnormalCuiShouLinkmanCnt++;
			}else if(tempName.indexOf(DirectoriesCodeEnum.QIAN.getMessage())!=-1){
				abnormalLinkmanCnt++;
				abnormalQianLinkmanCnt++;
			}else if(tempName.indexOf(DirectoriesCodeEnum.JIAZHENG.getMessage())!=-1){
				abnormalLinkmanCnt++;
				abnormalJiaZhengLinkmanCnt++;
			}else if(tempName.indexOf(DirectoriesCodeEnum.XIANJIN.getMessage())!=-1){
				abnormalLinkmanCnt++;
				abnormalXianJinLinkmanCnt++;
			}else{
				isNeedDel = false;
			}
			if(isNeedDel){
				newList.remove(i);
				--i;
			}
		}
		//8. 经过以上的逻辑处理 剩余的就是 正常的号码
		normalLinkmanCnt = newList.size();

		scLinkmanTypeCount.setLinkmanCnt(linkmanCnt);
		scLinkmanTypeCount.setNormalLinkmanCnt(normalLinkmanCnt);
		scLinkmanTypeCount.setAbnormalLinkmanCnt(abnormalLinkmanCnt);
		scLinkmanTypeCount.setAbnormalDaiLinkmanCnt(abnormalDaiLinkmanCnt);
		scLinkmanTypeCount.setAbnormalXinyongkaLinkmanCnt(abnormalXinYongKaLinkmanCnt);
		scLinkmanTypeCount.setAbnormalBankaLinkmanCnt(abnormalBanKaLinkmanCnt);
		scLinkmanTypeCount.setAbnormalTieLinkmanCnt(abnormalTieLinkmanCnt);
		scLinkmanTypeCount.setAbnormalHeihuLinkmanCnt(abnormalHeiHuLinkmanCnt);
		scLinkmanTypeCount.setAbnormalKouziLinkmanCnt(abnormalKouZiLinkmanCnt);
		scLinkmanTypeCount.setAbnormalDuLinkmanCnt(abnormalDuLinkmanCnt);
		scLinkmanTypeCount.setAbnormalJiedaibaoLinkmanCnt(abnormalJieDaiBaoLinkmanCnt);
		scLinkmanTypeCount.setAbnormalTaoxianLinkmanCnt(abnormalTaoXianLinkmanCnt);
		scLinkmanTypeCount.setAbnormalTixianLinkmanCnt(abnormalTiXianLinkmanCnt);
		scLinkmanTypeCount.setAbnormalPosLinkmanCnt(abnormalPosLinkmanCnt);
		scLinkmanTypeCount.setAbnormalCuishouLinkmanCnt(abnormalCuiShouLinkmanCnt);
		scLinkmanTypeCount.setAbnormalQianLinkmanCnt(abnormalQianLinkmanCnt);
		scLinkmanTypeCount.setAbnormalJiazhengLinkmanCnt(abnormalJiaZhengLinkmanCnt);
		scLinkmanTypeCount.setAbnormalXianjinLinkmanCnt(abnormalXianJinLinkmanCnt);
		scLinkmanTypeCount.setAlienLinkmanCnt(alienLinkmanCnt);
		scLinkmanTypeCount.setAlienNullnameLinkmanCnt(alienNullNameLinkmanCnt);
		scLinkmanTypeCount.setAlienNumnameLinkmanCnt(alienNumNameLinkmanCnt);
		scLinkmanTypeCount.setAlienNullnumLinkmanCnt(alienNullNumLinkmanCnt);
		scLinkmanTypeCount.setAlienName3LinkmanCnt(alienName3LinkmanCnt);
		scLinkmanTypeCount.setAlienNum3LinkmanCnt(alienNum3LinkmanCnt);
		scLinkmanTypeCount.setAlienName3LinkmanCnt2(alienName3LinkmanCnt2);
		scLinkmanTypeCount.setAlienNum3LinkmanCnt2(alienNum3LinkmanCnt2);
		scLinkmanTypeCount.setAlienNum8or12LinkmanCnt(alienNum8or12LinkmanCnt);
		scLinkmanTypeCount.setNormalLinkmanCnt(normalLinkmanCnt);
		return scLinkmanTypeCount;
	}

	/**
	 * 将 通讯录中的 有用逗号拼接的数据用逗号分给，拆除多条数据,
	 *   去掉 "-", +86, " "
	 * @param orgList
	 * @return
	 */
	public static List<CustomerDirectories> splitPhoneNumber(List<CustomerDirectories> orgList){
		if(CollectionUtils.isEmpty(orgList)){
			return Collections.EMPTY_LIST;
		}
		List<CustomerDirectories> newPhoneList = new ArrayList<>();
		int tempId = 0;
		for(CustomerDirectories elem : orgList){
			String phoneNum = elem.getContactPhone();
			if(elem.getContactPhone().length() > 0
					&& elem.getContactPhone().substring(0, 1).equals(",")){
				phoneNum = phoneNum.substring(1, elem.getContactPhone().length());
			}

			phoneNum = phoneNum.replaceAll(StringUtil.SPACE_STR, "")
					.replaceAll("-", "")
					.trim();

			String[] phoneNumArray = phoneNum.split("[,]");
			
			for(String strElem : phoneNumArray){
				CustomerDirectories newPhoneNum = new CustomerDirectories();
				BeanUtils.copyProperties(elem, newPhoneNum);

				//这里设置Id 只是临时用
				newPhoneNum.setDirectoriesId(String.valueOf(tempId++));

				if(StringUtils.isNotBlank(strElem)){
					String phone = strElem.replace("+86", "").trim();
					if(PHONE_ILLEGAL_LEN_13 == phone.length()){			//去除+86后 判断手机号是否是13位 如果是13位  
						if(phone.startsWith("86")){						//判断开头两位是否是86   startsWith()：判断字符串是否以指定字符串为开头
							phone = phone.substring(2, phone.length());	//开头2位是86,去除86,获取新手机号
						}
					}
					newPhoneNum.setContactPhone(phone);
				}

				if(StringUtils.isNotBlank(newPhoneNum.getContactName())){
					newPhoneNum.setContactName(newPhoneNum.getContactName().trim());
				}
				newPhoneList.add(newPhoneNum);
			}

		}
		return newPhoneList;
	}
}
