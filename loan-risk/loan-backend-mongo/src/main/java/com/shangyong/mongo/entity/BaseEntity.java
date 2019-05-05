package com.shangyong.mongo.entity;

import com.shangyong.mongo.common.JacksonUtils;

import java.io.Serializable;
import java.text.DecimalFormat;

public class BaseEntity implements Serializable{

	private static final long serialVersionUID = -2742148489606189447L;
	
	/**jsonInfo文档**/
	private Object jsonInfo;
	
	/**文档大小**/
	private Double docSize;
	
	/**redisKey**/
	private String errorRedKey;
	
	/**创建时间**/
	private long createTimeLong;

	/**修改时间**/
	private long modifyTimeLong;
	
	public Object getJsonInfo() {
		return jsonInfo;
	}

	public void setJsonInfo(Object jsonInfo) {
		try {
			if(jsonInfo == null){
				throw new RuntimeException("mongo jsonInfo转Object出现异常，异常信息：jsonInfo参数不能为空");
			}
			
			String jsonInfoStr = null;
			
			if(jsonInfo instanceof String) {
				jsonInfoStr = jsonInfo.toString();
			}else{
				jsonInfoStr = JacksonUtils.ObjectToJson(jsonInfo);
			}
			
			double strLength = jsonInfoStr.getBytes().length;
			strLength = strLength / (1024 * 1024);
            String size = new DecimalFormat("#.00").format(strLength);
			this.docSize = Double.parseDouble(size);
			this.jsonInfo = jsonInfo;
		} catch (Exception e) {
			throw new RuntimeException("mongo jsonInfo转Object出现异常，异常信息：" + e.getMessage());
		}
	}
	
	public void nSetJsonInfoTo(Object jsonInfo) {
		this.jsonInfo = jsonInfo;
	}

	public Double getDocSize() {
		return docSize;
	}

	public void setDocSize(Double docSize) {
		this.docSize = docSize;
	}

	public long getCreateTimeLong() {
		return createTimeLong;
	}

	public void setCreateTimeLong(long createTimeLong) {
		this.createTimeLong = createTimeLong;
	}

	public long getModifyTimeLong() {
		return modifyTimeLong;
	}

	public void setModifyTimeLong(long modifyTimeLong) {
		this.modifyTimeLong = modifyTimeLong;
	}

	public String getErrorRedKey() {
		return errorRedKey;
	}

	public void setErrorRedKey(String errorRedKey) {
		this.errorRedKey = errorRedKey;
	}
	
	
}
