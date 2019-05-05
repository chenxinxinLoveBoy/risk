package com.shangyong.backend.entity.redis.fraud2_0;

import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * @author Marvin
 *
 */
public class ZX9120Redis {
	public static final String DEFAULT_MIN_GAP_DAYS_91 = "有报告但此处缺值";

	private String lendCnt91;
	private String lendCntX191;
	private String lendMoney91;
	private String minGapDays91;
	private String latestLendMoney91;

	public HashMap<String,String> toMap(){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("lendCnt91", !StringUtils.hasText(lendCnt91)?"unknow":lendCnt91);
		map.put("lendCntX191", !StringUtils.hasText(lendCntX191)?"unknow":lendCntX191);
		map.put("lendMoney91", !StringUtils.hasText(lendMoney91)?"unknow":lendMoney91);
		map.put("minGapDays91", !StringUtils.hasText(minGapDays91)?"unknow":minGapDays91);
		map.put("latestLendMoney91", !StringUtils.hasText(latestLendMoney91)?"unknow":latestLendMoney91);
		return map;
	}
	
	public String getLendMoney91() {
		return lendMoney91;
	}

	public void setLendMoney91(String lendMoney91) {
		this.lendMoney91 = lendMoney91;
	}

	public String getLendCnt91() {
		return lendCnt91;
	}

	public void setLendCnt91(String lendCnt91) {
		this.lendCnt91 = lendCnt91;
	}

	public String getLendCntX191() {
		return lendCntX191;
	}

	public void setLendCntX191(String lendCntX191) {
		this.lendCntX191 = lendCntX191;
	}

	public String getMinGapDays91() {
		return minGapDays91;
	}

	public void setMinGapDays91(String minGapDays91) {
		this.minGapDays91 = minGapDays91;
	}

	public String getLatestLendMoney91() {
		return latestLendMoney91;
	}

	public void setLatestLendMoney91(String latestLendMoney91) {
		this.latestLendMoney91 = latestLendMoney91;
	}


}
