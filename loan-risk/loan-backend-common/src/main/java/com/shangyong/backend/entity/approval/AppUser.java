package com.shangyong.backend.entity.approval;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Lombok：通过注解自动生成getter/setter/无参构造函数/全参构造函数 的实用工具
 * <p>
 * 常用注解包括:
 *
 * @author ruihua.qin
 * @Data 全字段Getter/Setter
 * @Getter/@Setter
 * @AllArgsConstructor
 * @NoArgsConstructor 使用方式：由于IDE默认不支持Lombok，因此需要进行设置，使IDE支持它
 * 1.双击运行lombok-xxxx.jar(或者执行java -jar lombok-xxxx.jar)弹出安装界面
 * 2.选择IDE的路径即可安装完毕
 */
public class AppUser implements Serializable {
    /**
     * 用户编号
     */
    private Long id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户真名
     */
    private String realname;


    /**
     * 密码
     */
    private String password;

    /**
     * 用户编号:6226000000000000
     */
    private String userNo;

    /**
     * 身份证
     */
    private String idcard;

    /**
     * 状态:0:初始,1:待审核；2：未通过；3:审核通过.4:黑名单
     */
    private Integer status;

    /**
     * 信用评级
     */
    private Integer creditLevel;

    /**
     * 授信额度
     */
    private BigDecimal creditMoney;

    /**
     * 已用额度
     */
    private BigDecimal usedMoney;

    /**
     * 借款状态:0:不可借款;1:可借款,2:借款申请中;3:借款使用中
     */
    private Integer borrowStatus;

    /**
     * 审核通过次数
     */
    private Integer borrowCount;

    /**
     * 放款成功次数
     */
    private Integer fangCount;

    /**
     * 申请次数
     */
    private Integer applyCount;

    /**
     * 用户头像
     */
    private String profilephoto;

    /**
     * 邀请人
     */
    private Long inviteUserid;

    /**
     * 注册来源
     */
    private String regSource;

    /**
     * 请求token
     */
    private String token;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 登录时间
     */
    private Long logintime;

    /**
     * 注册时间
     */
    private Long regtime;

    /**
     * 放款id列表，逗号分隔
     */
    private String applyIds;

    /**
     * 放款id列表，逗号分隔
     */
    private String fangIds;

    /**
     * 还款id列表，逗号分隔
     */
    private String huanIds;

    /**
     * 乐观锁
     */
    private Long version;

    /**
     * 客户等级 11：A类用户 ；12：B类用户；13：C类用户；14：D类用户
     */
    private String level;

    /**
     * 用户借款利率
     */
    private BigDecimal rate;

    /**
     * 登陆次数
     */
    private Long loginCount;

    /**
     * 来源渠道
     */
    private Long promotionChannelId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreditLevel() {
        return creditLevel;
    }

    public void setCreditLevel(Integer creditLevel) {
        this.creditLevel = creditLevel;
    }

    public BigDecimal getCreditMoney() {
        return creditMoney;
    }

    public void setCreditMoney(BigDecimal creditMoney) {
        this.creditMoney = creditMoney;
    }

    public BigDecimal getUsedMoney() {
        return usedMoney;
    }

    public void setUsedMoney(BigDecimal usedMoney) {
        this.usedMoney = usedMoney;
    }

    public Integer getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(Integer borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public Integer getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(Integer borrowCount) {
        this.borrowCount = borrowCount;
    }

    public Integer getFangCount() {
        return fangCount;
    }

    public void setFangCount(Integer fangCount) {
        this.fangCount = fangCount;
    }

    public Integer getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Integer applyCount) {
        this.applyCount = applyCount;
    }

    public String getProfilephoto() {
        return profilephoto;
    }

    public void setProfilephoto(String profilephoto) {
        this.profilephoto = profilephoto;
    }

    public Long getInviteUserid() {
        return inviteUserid;
    }

    public void setInviteUserid(Long inviteUserid) {
        this.inviteUserid = inviteUserid;
    }

    public String getRegSource() {
        return regSource;
    }

    public void setRegSource(String regSource) {
        this.regSource = regSource;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getLogintime() {
        return logintime;
    }

    public void setLogintime(Long logintime) {
        this.logintime = logintime;
    }

    public Long getRegtime() {
        return regtime;
    }

    public void setRegtime(Long regtime) {
        this.regtime = regtime;
    }

    public String getApplyIds() {
        return applyIds;
    }

    public void setApplyIds(String applyIds) {
        this.applyIds = applyIds;
    }

    public String getFangIds() {
        return fangIds;
    }

    public void setFangIds(String fangIds) {
        this.fangIds = fangIds;
    }

    public String getHuanIds() {
        return huanIds;
    }

    public void setHuanIds(String huanIds) {
        this.huanIds = huanIds;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Long getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Long loginCount) {
        this.loginCount = loginCount;
    }

    public Long getPromotionChannelId() {
        return promotionChannelId;
    }

    public void setPromotionChannelId(Long promotionChannelId) {
        this.promotionChannelId = promotionChannelId;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", mobile='" + mobile + '\'' +
                ", username='" + username + '\'' +
                ", realname='" + realname + '\'' +
                ", password='" + password + '\'' +
                ", userNo='" + userNo + '\'' +
                ", idcard='" + idcard + '\'' +
                ", status=" + status +
                ", creditLevel=" + creditLevel +
                ", creditMoney=" + creditMoney +
                ", usedMoney=" + usedMoney +
                ", borrowStatus=" + borrowStatus +
                ", borrowCount=" + borrowCount +
                ", fangCount=" + fangCount +
                ", applyCount=" + applyCount +
                ", profilephoto='" + profilephoto + '\'' +
                ", inviteUserid=" + inviteUserid +
                ", regSource='" + regSource + '\'' +
                ", token='" + token + '\'' +
                ", ip='" + ip + '\'' +
                ", logintime=" + logintime +
                ", regtime=" + regtime +
                ", applyIds='" + applyIds + '\'' +
                ", fangIds='" + fangIds + '\'' +
                ", huanIds='" + huanIds + '\'' +
                ", version=" + version +
                ", level='" + level + '\'' +
                ", rate=" + rate +
                ", loginCount=" + loginCount +
                ", promotionChannelId=" + promotionChannelId +
                '}';
    }
}
