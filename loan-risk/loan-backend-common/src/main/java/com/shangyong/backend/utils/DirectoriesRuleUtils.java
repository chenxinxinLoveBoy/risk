package com.shangyong.backend.utils;

import com.shangyong.backend.entity.CuCustomerDirectories;
import com.shangyong.backend.entity.approval.CustomerDirectories;
import com.shangyong.utils.StringUtil;
import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * 通讯录规则常量类
 * 
 * @author hxf
 *
 */
public class DirectoriesRuleUtils {
	
	/**异类号码逻辑**/
	/**姓名为空**/
	public static final String DIRECTORIES_NAME_IS_NULL = "姓名为空";
	/**姓名为纯数字**/
	public static final String DIRECTORIES_NAME_IS_NUM = "姓名为纯数字";
	/**号码为空**/
	public static final String DIRECTORIES_PHONE_IS_NULL = "号码为空";
	/**同一姓名出现3次及以上**/
	public static final String DIRECTORIES_NAME_IS_MORE = "同一姓名出现3次及以上";
	/**号码超过12位和号码少与8位**/
	public static final String DIRECTORIES_PHONE_IS_IRRATIONAL = "号码超过12位和号码少与8位";
	/**同一姓名出现3次及以上**/
	public static final Integer NAME_IS_MORE_COUNT = 3;


	/**
	 * 将 存在 Mongodb 中的 json 格式的数据转换成 CustomerDirectories
	 * @param jsonArray
	 * @return
	 */
	public static List<CustomerDirectories> paresToCustomerDirectories(JSONArray jsonArray){
		List<CustomerDirectories> directories = new ArrayList<CustomerDirectories>();
		for (int i = 0; i < jsonArray.size(); i++) {
			CustomerDirectories customerDirectories = new CustomerDirectories();
			if (jsonArray.getJSONObject(i).containsKey("contactName")) {
				customerDirectories.setContactName(jsonArray.getJSONObject(i).getString("contactName") != null?jsonArray.getJSONObject(i).getString("contactName") : "");
			}else{
				customerDirectories.setContactPhone("");
			}

			if (jsonArray.getJSONObject(i).containsKey("contactPhone")) {
				String phone = jsonArray.getJSONObject(i).getString("contactPhone");
				String contactPhone = phone.replace(" ", "").trim();
				customerDirectories.setContactPhone(contactPhone);
			}else{
				customerDirectories.setContactPhone("");
			}
			directories.add(customerDirectories);
		}
		return directories;
	}
}
