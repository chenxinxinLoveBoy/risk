package com.shangyong.backend.controller.mongodb;

import com.alibaba.fastjson.JSON;
import com.mongodb.BasicDBObjectBuilder;
import com.shangyong.backend.utils.CodeUtils;
import com.shangyong.backend.utils.DateUtils;
import com.shangyong.backend.utils.JSONUtils;
import com.shangyong.mongo.common.MongoUtils;
import com.shangyong.utils.SpringUtils;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/backend/findMongodb")
public class FindMongodbController {
	@Autowired
    MongoUtils mongoUtils;

	/**
	 * 根据条件动态查询mongodb
	 */
	@RequestMapping(value = "/findMongodbInfoList.do")
	public void findMongodbInfoList(String mongodbName, String keys, String values, String startTime, String endTime,
                                    String timeType, HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(mongodbName)) {
				if (StringUtils.isNotBlank(keys) && StringUtils.isNotBlank(values)) {
					String[] keyArr = new String[10];
					String[] valueArr = new String[10];
					keyArr = keys.split(",");
					valueArr = values.split(",");
					for (int i = 0; i < keyArr.length; i++) {
						String key = keyArr[i].trim();
						String value = valueArr[i].trim();
						paramMap.put(key, value);
					}
				}
				if (StringUtils.isNotBlank(timeType) && StringUtils.isNotBlank(startTime)&& StringUtils.isNotBlank(endTime)) {
					if ("01".equals(timeType)) {// 转为Date类型查询
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						paramMap.put("createTime", BasicDBObjectBuilder.start("$gte", sdf.parse(startTime)).add("$lt", sdf.parse(endTime)).get());
					} else if ("02".equals(timeType)) {// 转为Long类型查询
						long stime = org.apache.commons.lang3.time.DateUtils.parseDate(startTime, DateUtils.dateTimePattern).getTime();
						long etime = org.apache.commons.lang3.time.DateUtils.parseDate(endTime, DateUtils.dateTimePattern).getTime();
						paramMap.put("createTime", BasicDBObjectBuilder.start("$gte", stime).add("$lt", etime).get());
					}
				}
				Class c = getClassByString(mongodbName);
				List list = mongoUtils.findListByClazz(paramMap, null, c);
				if (CollectionUtils.isNotEmpty(list)) {
					JSONUtils.toJSON(response, CodeUtils.SUCCESS, JSON.toJSON(list));
				} else {
					JSONUtils.toJSON(response, CodeUtils.NON_EXISTENT);
				}
			}
		} catch (Exception e) {
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/** 通过id查询mongodb数据信息 **/
	@RequestMapping(value = "/findMongodbInfo.do", method = RequestMethod.POST)
	public void findMongodbInfo(String id, String mongodbName, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (StringUtils.isBlank(id)) {
				JSONUtils.toJSON(response, CodeUtils.BACKEND_PRA_MISS);
				return;
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("_id", id);
			Class c = getClassByString(mongodbName);
			Object obj = mongoUtils.findByClazz(paramMap, null, c);
			if (obj != null) {
				JSONObject jsonObject = JSONObject.fromObject(obj);
				if (jsonObject != null) {
					JSONObject jsonInfo = jsonObject.getJSONObject("jsonInfo");
					if (jsonInfo != null) {
						SpringUtils.renderJsonArray(response, jsonInfo.get("list"));
					}
				}
			}
		} catch (Exception e) {
			JSONUtils.toJSON(response, CodeUtils.BACKEND_ERROR);
		}
	}

	/**
	 * 根据反射获取字符串相应的类名称
	 * 
	 * @param mongodbName
	 * @return
	 */
	private Class getClassByString(String mongodbName) {
		mongodbName = mongodbName.replace(mongodbName.substring(0, 1), mongodbName.substring(0, 1).toUpperCase());
		mongodbName = "com.honglu.mongo.entity." + mongodbName;// 拼接上全路劲
		Class c = null;
		try {
			c = Class.forName(mongodbName);// 获取类
		} catch (ClassNotFoundException e) {
 			e.printStackTrace();
		} 
		return c;
	}

}
