package com.shangyong.backend.entity;


import java.util.Date;

/**
 * 通讯录分类bean
 * @author kenzhao
 * @date Fri Apr 20 16:05:58 CST 2018
 **/
public class ScLinkmanTypeCount {

	/**申请单编号**/
	private String applicationId;

	/**客户编号**/
	private String customerId;

	/**联系人个数**/
	private Integer linkmanCnt = 0;

	/**正常联系人个数**/
	private Integer normalLinkmanCnt = 0;

	/**异常联系人个数**/
	private Integer abnormalLinkmanCnt = 0;

	/**包含 贷 联系人个数**/
	private Integer abnormalDaiLinkmanCnt = 0;

	/**包含 信用卡 联系人个数**/
	private Integer abnormalXinyongkaLinkmanCnt = 0;

	/**包含 办卡 联系人个数**/
	private Integer abnormalBankaLinkmanCnt = 0;

	/**包含 提额 联系人个数**/
	private Integer abnormalTieLinkmanCnt = 0;

	/**包含 黑户 联系人个数**/
	private Integer abnormalHeihuLinkmanCnt = 0;

	/**包含 口子 联系人个数**/
	private Integer abnormalKouziLinkmanCnt = 0;

	/**包含 赌 联系人个数**/
	private Integer abnormalDuLinkmanCnt = 0;

	/**包含 借贷宝 联系人个数**/
	private Integer abnormalJiedaibaoLinkmanCnt = 0;

	/**包含 套现 联系人个数**/
	private Integer abnormalTaoxianLinkmanCnt = 0;

	/**包含 提现 联系人个数**/
	private Integer abnormalTixianLinkmanCnt = 0;

	/**包含 pos（不区分大小写） 联系人个数**/
	private Integer abnormalPosLinkmanCnt = 0;

	/**包含 催收 联系人个数**/
	private Integer abnormalCuishouLinkmanCnt = 0;

	/**包含 欠 联系人个数**/
	private Integer abnormalQianLinkmanCnt = 0;

	/**包含 假证 联系人个数**/
	private Integer abnormalJiazhengLinkmanCnt = 0;

	/**包含 现金 联系人个数**/
	private Integer abnormalXianjinLinkmanCnt = 0;

	/**异类联系人个数**/
	private Integer alienLinkmanCnt = 0;

	/**姓名为空联系人个数**/
	private Integer alienNullnameLinkmanCnt = 0;

	/**姓名为纯数字联系人个数**/
	private Integer alienNumnameLinkmanCnt = 0;

	/**号码为空联系人个数**/
	private Integer alienNullnumLinkmanCnt = 0;

	/**同一姓名出现3次及以上联系人个数（去重前）**/
	private Integer alienName3LinkmanCnt = 0;

	/**同一号码出现3次及以上联系人个数（去重前）**/
	private Integer alienNum3LinkmanCnt = 0;

	/**同一姓名出现3次及以上联系人个数（去重后）**/
	private Integer alienName3LinkmanCnt2 = 0;

	/**同一号码出现3次及以上联系人个数（去重后）**/
	private Integer alienNum3LinkmanCnt2 = 0;

	/**号码超过12位或少于8位联系人个数**/
	private Integer alienNum8or12LinkmanCnt = 0;

	/**创建日期**/
	private Date createTime;

	/**创建人**/
	private String createMan;

	/**修改日期**/
	private Date modifyTime;

	/**修改人**/
	private String modifyMan;

	/**备注**/
	private String remark;


	public ScLinkmanTypeCount() {
		super();
	}

	public ScLinkmanTypeCount(String applicationId, String customerId, Integer linkmanCnt, Integer normalLinkmanCnt, Integer abnormalLinkmanCnt, Integer abnormalDaiLinkmanCnt, Integer abnormalXinyongkaLinkmanCnt, Integer abnormalBankaLinkmanCnt, Integer abnormalTieLinkmanCnt, Integer abnormalHeihuLinkmanCnt, Integer abnormalKouziLinkmanCnt, Integer abnormalDuLinkmanCnt, Integer abnormalJiedaibaoLinkmanCnt, Integer abnormalTaoxianLinkmanCnt, Integer abnormalTixianLinkmanCnt, Integer abnormalPosLinkmanCnt, Integer abnormalCuishouLinkmanCnt, Integer abnormalQianLinkmanCnt, Integer abnormalJiazhengLinkmanCnt, Integer abnormalXianjinLinkmanCnt, Integer alienLinkmanCnt, Integer alienNullnameLinkmanCnt, Integer alienNumnameLinkmanCnt, Integer alienNullnumLinkmanCnt, Integer alienName3LinkmanCnt, Integer alienNum3LinkmanCnt, Integer alienName3LinkmanCnt2, Integer alienNum3LinkmanCnt2, Integer alienNum8or12LinkmanCnt, Date createTime, String createMan, Date modifyTime, String modifyMan, String remark) {
		this.applicationId = applicationId;
		this.customerId = customerId;
		this.linkmanCnt = linkmanCnt;
		this.normalLinkmanCnt = normalLinkmanCnt;
		this.abnormalLinkmanCnt = abnormalLinkmanCnt;
		this.abnormalDaiLinkmanCnt = abnormalDaiLinkmanCnt;
		this.abnormalXinyongkaLinkmanCnt = abnormalXinyongkaLinkmanCnt;
		this.abnormalBankaLinkmanCnt = abnormalBankaLinkmanCnt;
		this.abnormalTieLinkmanCnt = abnormalTieLinkmanCnt;
		this.abnormalHeihuLinkmanCnt = abnormalHeihuLinkmanCnt;
		this.abnormalKouziLinkmanCnt = abnormalKouziLinkmanCnt;
		this.abnormalDuLinkmanCnt = abnormalDuLinkmanCnt;
		this.abnormalJiedaibaoLinkmanCnt = abnormalJiedaibaoLinkmanCnt;
		this.abnormalTaoxianLinkmanCnt = abnormalTaoxianLinkmanCnt;
		this.abnormalTixianLinkmanCnt = abnormalTixianLinkmanCnt;
		this.abnormalPosLinkmanCnt = abnormalPosLinkmanCnt;
		this.abnormalCuishouLinkmanCnt = abnormalCuishouLinkmanCnt;
		this.abnormalQianLinkmanCnt = abnormalQianLinkmanCnt;
		this.abnormalJiazhengLinkmanCnt = abnormalJiazhengLinkmanCnt;
		this.abnormalXianjinLinkmanCnt = abnormalXianjinLinkmanCnt;
		this.alienLinkmanCnt = alienLinkmanCnt;
		this.alienNullnameLinkmanCnt = alienNullnameLinkmanCnt;
		this.alienNumnameLinkmanCnt = alienNumnameLinkmanCnt;
		this.alienNullnumLinkmanCnt = alienNullnumLinkmanCnt;
		this.alienName3LinkmanCnt = alienName3LinkmanCnt;
		this.alienNum3LinkmanCnt = alienNum3LinkmanCnt;
		this.alienName3LinkmanCnt2 = alienName3LinkmanCnt2;
		this.alienNum3LinkmanCnt2 = alienNum3LinkmanCnt2;
		this.alienNum8or12LinkmanCnt = alienNum8or12LinkmanCnt;
		this.createTime = createTime;
		this.createMan = createMan;
		this.modifyTime = modifyTime;
		this.modifyMan = modifyMan;
		this.remark = remark;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Integer getLinkmanCnt() {
		return linkmanCnt;
	}

	public void setLinkmanCnt(Integer linkmanCnt) {
		this.linkmanCnt = linkmanCnt;
	}

	public Integer getNormalLinkmanCnt() {
		return normalLinkmanCnt;
	}

	public void setNormalLinkmanCnt(Integer normalLinkmanCnt) {
		this.normalLinkmanCnt = normalLinkmanCnt;
	}

	public Integer getAbnormalLinkmanCnt() {
		return abnormalLinkmanCnt;
	}

	public void setAbnormalLinkmanCnt(Integer abnormalLinkmanCnt) {
		this.abnormalLinkmanCnt = abnormalLinkmanCnt;
	}

	public Integer getAbnormalDaiLinkmanCnt() {
		return abnormalDaiLinkmanCnt;
	}

	public void setAbnormalDaiLinkmanCnt(Integer abnormalDaiLinkmanCnt) {
		this.abnormalDaiLinkmanCnt = abnormalDaiLinkmanCnt;
	}

	public Integer getAbnormalXinyongkaLinkmanCnt() {
		return abnormalXinyongkaLinkmanCnt;
	}

	public void setAbnormalXinyongkaLinkmanCnt(Integer abnormalXinyongkaLinkmanCnt) {
		this.abnormalXinyongkaLinkmanCnt = abnormalXinyongkaLinkmanCnt;
	}

	public Integer getAbnormalBankaLinkmanCnt() {
		return abnormalBankaLinkmanCnt;
	}

	public void setAbnormalBankaLinkmanCnt(Integer abnormalBankaLinkmanCnt) {
		this.abnormalBankaLinkmanCnt = abnormalBankaLinkmanCnt;
	}

	public Integer getAbnormalTieLinkmanCnt() {
		return abnormalTieLinkmanCnt;
	}

	public void setAbnormalTieLinkmanCnt(Integer abnormalTieLinkmanCnt) {
		this.abnormalTieLinkmanCnt = abnormalTieLinkmanCnt;
	}

	public Integer getAbnormalHeihuLinkmanCnt() {
		return abnormalHeihuLinkmanCnt;
	}

	public void setAbnormalHeihuLinkmanCnt(Integer abnormalHeihuLinkmanCnt) {
		this.abnormalHeihuLinkmanCnt = abnormalHeihuLinkmanCnt;
	}

	public Integer getAbnormalKouziLinkmanCnt() {
		return abnormalKouziLinkmanCnt;
	}

	public void setAbnormalKouziLinkmanCnt(Integer abnormalKouziLinkmanCnt) {
		this.abnormalKouziLinkmanCnt = abnormalKouziLinkmanCnt;
	}

	public Integer getAbnormalDuLinkmanCnt() {
		return abnormalDuLinkmanCnt;
	}

	public void setAbnormalDuLinkmanCnt(Integer abnormalDuLinkmanCnt) {
		this.abnormalDuLinkmanCnt = abnormalDuLinkmanCnt;
	}

	public Integer getAbnormalJiedaibaoLinkmanCnt() {
		return abnormalJiedaibaoLinkmanCnt;
	}

	public void setAbnormalJiedaibaoLinkmanCnt(Integer abnormalJiedaibaoLinkmanCnt) {
		this.abnormalJiedaibaoLinkmanCnt = abnormalJiedaibaoLinkmanCnt;
	}

	public Integer getAbnormalTaoxianLinkmanCnt() {
		return abnormalTaoxianLinkmanCnt;
	}

	public void setAbnormalTaoxianLinkmanCnt(Integer abnormalTaoxianLinkmanCnt) {
		this.abnormalTaoxianLinkmanCnt = abnormalTaoxianLinkmanCnt;
	}

	public Integer getAbnormalTixianLinkmanCnt() {
		return abnormalTixianLinkmanCnt;
	}

	public void setAbnormalTixianLinkmanCnt(Integer abnormalTixianLinkmanCnt) {
		this.abnormalTixianLinkmanCnt = abnormalTixianLinkmanCnt;
	}

	public Integer getAbnormalPosLinkmanCnt() {
		return abnormalPosLinkmanCnt;
	}

	public void setAbnormalPosLinkmanCnt(Integer abnormalPosLinkmanCnt) {
		this.abnormalPosLinkmanCnt = abnormalPosLinkmanCnt;
	}

	public Integer getAbnormalCuishouLinkmanCnt() {
		return abnormalCuishouLinkmanCnt;
	}

	public void setAbnormalCuishouLinkmanCnt(Integer abnormalCuishouLinkmanCnt) {
		this.abnormalCuishouLinkmanCnt = abnormalCuishouLinkmanCnt;
	}

	public Integer getAbnormalQianLinkmanCnt() {
		return abnormalQianLinkmanCnt;
	}

	public void setAbnormalQianLinkmanCnt(Integer abnormalQianLinkmanCnt) {
		this.abnormalQianLinkmanCnt = abnormalQianLinkmanCnt;
	}

	public Integer getAbnormalJiazhengLinkmanCnt() {
		return abnormalJiazhengLinkmanCnt;
	}

	public void setAbnormalJiazhengLinkmanCnt(Integer abnormalJiazhengLinkmanCnt) {
		this.abnormalJiazhengLinkmanCnt = abnormalJiazhengLinkmanCnt;
	}

	public Integer getAbnormalXianjinLinkmanCnt() {
		return abnormalXianjinLinkmanCnt;
	}

	public void setAbnormalXianjinLinkmanCnt(Integer abnormalXianjinLinkmanCnt) {
		this.abnormalXianjinLinkmanCnt = abnormalXianjinLinkmanCnt;
	}

	public Integer getAlienLinkmanCnt() {
		return alienLinkmanCnt;
	}

	public void setAlienLinkmanCnt(Integer alienLinkmanCnt) {
		this.alienLinkmanCnt = alienLinkmanCnt;
	}

	public Integer getAlienNullnameLinkmanCnt() {
		return alienNullnameLinkmanCnt;
	}

	public void setAlienNullnameLinkmanCnt(Integer alienNullnameLinkmanCnt) {
		this.alienNullnameLinkmanCnt = alienNullnameLinkmanCnt;
	}

	public Integer getAlienNumnameLinkmanCnt() {
		return alienNumnameLinkmanCnt;
	}

	public void setAlienNumnameLinkmanCnt(Integer alienNumnameLinkmanCnt) {
		this.alienNumnameLinkmanCnt = alienNumnameLinkmanCnt;
	}

	public Integer getAlienNullnumLinkmanCnt() {
		return alienNullnumLinkmanCnt;
	}

	public void setAlienNullnumLinkmanCnt(Integer alienNullnumLinkmanCnt) {
		this.alienNullnumLinkmanCnt = alienNullnumLinkmanCnt;
	}

	public Integer getAlienName3LinkmanCnt() {
		return alienName3LinkmanCnt;
	}

	public void setAlienName3LinkmanCnt(Integer alienName3LinkmanCnt) {
		this.alienName3LinkmanCnt = alienName3LinkmanCnt;
	}

	public Integer getAlienNum3LinkmanCnt() {
		return alienNum3LinkmanCnt;
	}

	public void setAlienNum3LinkmanCnt(Integer alienNum3LinkmanCnt) {
		this.alienNum3LinkmanCnt = alienNum3LinkmanCnt;
	}

	public Integer getAlienName3LinkmanCnt2() {
		return alienName3LinkmanCnt2;
	}

	public void setAlienName3LinkmanCnt2(Integer alienName3LinkmanCnt2) {
		this.alienName3LinkmanCnt2 = alienName3LinkmanCnt2;
	}

	public Integer getAlienNum3LinkmanCnt2() {
		return alienNum3LinkmanCnt2;
	}

	public void setAlienNum3LinkmanCnt2(Integer alienNum3LinkmanCnt2) {
		this.alienNum3LinkmanCnt2 = alienNum3LinkmanCnt2;
	}

	public Integer getAlienNum8or12LinkmanCnt() {
		return alienNum8or12LinkmanCnt;
	}

	public void setAlienNum8or12LinkmanCnt(Integer alienNum8or12LinkmanCnt) {
		this.alienNum8or12LinkmanCnt = alienNum8or12LinkmanCnt;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateMan() {
		return createMan;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyMan() {
		return modifyMan;
	}

	public void setModifyMan(String modifyMan) {
		this.modifyMan = modifyMan;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
