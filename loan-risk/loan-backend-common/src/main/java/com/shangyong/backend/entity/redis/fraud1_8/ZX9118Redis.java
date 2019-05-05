package com.shangyong.backend.entity.redis.fraud1_8;

import java.util.HashMap;

/**
 * @author Marvin
 *
 */
public class ZX9118Redis {

	public static final String DEFAULT_MIN_GAP_DAYS_91 = "有报告但此处缺值";

	private String lendCnt91;
	private String lendCntX191;
	private String lendCntX591;
	private String lendMoney91;
	private String minGapDays91;
	private String latestLendMoney91;

	public HashMap<String,String> toMap(){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("lendCnt91", lendCnt91==null?"unknow":lendCnt91);
		map.put("lendCntX191", lendCntX191==null?"unknow":lendCntX191);
		map.put("lendCntX591", lendCntX591==null?"unknow":lendCntX591);
		map.put("lendMoney91", lendMoney91==null?"unknow":lendMoney91);
		map.put("minGapDays91", minGapDays91==null?"unknow":minGapDays91);
		map.put("latestLendMoney91", latestLendMoney91==null?"unknow":latestLendMoney91);
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

	public String getLendCntX591() {
		return lendCntX591;
	}

	public void setLendCntX591(String lendCntX591) {
		this.lendCntX591 = lendCntX591;
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

	@Override
	public String toString() {
		return "ZX9118Redis [lendCnt91=" + lendCnt91 + ", lendCntX191=" + lendCntX191 + ", lendCntX591=" + lendCntX591
				+ ", lendMoney91=" + lendMoney91 + ", minGapDays91=" + minGapDays91 + ", latestLendMoney91="
				+ latestLendMoney91 + "]";
	}

	
}
