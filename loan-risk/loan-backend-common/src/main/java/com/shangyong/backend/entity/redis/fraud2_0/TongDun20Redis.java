package com.shangyong.backend.entity.redis.fraud2_0;

import java.util.HashMap;

/**
 * 同盾多头 2.0
 */
public class TongDun20Redis {

    private String tdTotal07; //7天多头
    private String tdTotal180; //6个月多头
    private String tdTotal30; //1个月多头
    private String tdTotal90; //3个月多头
    private String tdX0180; //6个月多头类总数
    private String tdX030; //1个月多头类总数
    private String tdX130; //小额贷款公司1个月多头
    private String tdX190; //小额贷款公司3个月多头
    private String tdX10180; //第三方服务商6个月多头
    private String tdX2180; //P2P网贷6个月多头
    private String tdX230; //P2P网贷1个月多头
    private String tdX290; //P2P网贷3个月多头
    private String tdX3180; //一般消费分期平台6个月多头
    private String tdX330; //一般消费分期平台1个月多头
    private String tdX390; //一般消费分期平台3个月多头
    private String tdX590; //银行消费金融公司3个月多头
    private String tdX7180; //互联网金融门户6个月多头
    private String tdX790; //互联网金融门户3个月多头

    public String getTdTotal07() {
        return tdTotal07;
    }

    public void setTdTotal07(String tdTotal07) {
        this.tdTotal07 = tdTotal07;
    }

    public String getTdTotal180() {
        return tdTotal180;
    }

    public void setTdTotal180(String tdTotal180) {
        this.tdTotal180 = tdTotal180;
    }

    public String getTdTotal30() {
        return tdTotal30;
    }

    public void setTdTotal30(String tdTotal30) {
        this.tdTotal30 = tdTotal30;
    }

    public String getTdTotal90() {
        return tdTotal90;
    }

    public void setTdTotal90(String tdTotal90) {
        this.tdTotal90 = tdTotal90;
    }

    public String getTdX0180() {
        return tdX0180;
    }

    public void setTdX0180(String tdX0180) {
        this.tdX0180 = tdX0180;
    }

    public String getTdX030() {
        return tdX030;
    }

    public void setTdX030(String tdX030) {
        this.tdX030 = tdX030;
    }

    public String getTdX130() {
        return tdX130;
    }

    public void setTdX130(String tdX130) {
        this.tdX130 = tdX130;
    }

    public String getTdX190() {
        return tdX190;
    }

    public void setTdX190(String tdX190) {
        this.tdX190 = tdX190;
    }

    public String getTdX10180() {
        return tdX10180;
    }

    public void setTdX10180(String tdX10180) {
        this.tdX10180 = tdX10180;
    }

    public String getTdX2180() {
        return tdX2180;
    }

    public void setTdX2180(String tdX2180) {
        this.tdX2180 = tdX2180;
    }

    public String getTdX230() {
        return tdX230;
    }

    public void setTdX230(String tdX230) {
        this.tdX230 = tdX230;
    }

    public String getTdX290() {
        return tdX290;
    }

    public void setTdX290(String tdX290) {
        this.tdX290 = tdX290;
    }

    public String getTdX3180() {
        return tdX3180;
    }

    public void setTdX3180(String tdX3180) {
        this.tdX3180 = tdX3180;
    }

    public String getTdX330() {
        return tdX330;
    }

    public void setTdX330(String tdX330) {
        this.tdX330 = tdX330;
    }

    public String getTdX390() {
        return tdX390;
    }

    public void setTdX390(String tdX390) {
        this.tdX390 = tdX390;
    }

    public String getTdX590() {
        return tdX590;
    }

    public void setTdX590(String tdX590) {
        this.tdX590 = tdX590;
    }

    public String getTdX7180() {
        return tdX7180;
    }

    public void setTdX7180(String tdX7180) {
        this.tdX7180 = tdX7180;
    }

    public String getTdX790() {
        return tdX790;
    }

    public void setTdX790(String tdX790) {
        this.tdX790 = tdX790;
    }

    /**
     * 该方法谨慎调用， 只有在同盾报文返回的时候在才能执行
     */
    public void initZero(){
        this.tdTotal07 = "0";
        this.tdTotal180 = "0";
        this.tdTotal30 = "0";
        this.tdTotal90 = "0";
        this.tdX0180 = "0";
        this.tdX030 = "0";
        this.tdX130 = "0";
        this.tdX190 = "0";
        this.tdX10180 = "0";
        this.tdX2180 = "0";
        this.tdX230 = "0";
        this.tdX290 = "0";
        this.tdX3180 = "0";
        this.tdX330 = "0";
        this.tdX390 = "0";
        this.tdX590 = "0";
        this.tdX7180 = "0";
        this.tdX790 = "0";
    }

    public HashMap<String, String> toMap(){
        HashMap<String, String> map = new HashMap();
        map.put("tdTotal07", null == this.tdTotal07 ? "unknow" : this.tdTotal07);
        map.put("tdTotal180", null == this.tdTotal180 ? "unknow" : this.tdTotal180);
        map.put("tdTotal30", null == this.tdTotal30 ? "unknow" : this.tdTotal30);
        map.put("tdTotal90", null == this.tdTotal90 ? "unknow" : this.tdTotal90);
        map.put("tdX0180", null == this.tdX0180 ? "unknow" : this.tdX0180);
        map.put("tdX030", null == this.tdX030 ? "unknow" : this.tdX030);
        map.put("tdX130", null == this.tdX130 ? "unknow" : this.tdX130);
        map.put("tdX190", null == this.tdX190 ? "unknow" : this.tdX190);
        map.put("tdX10180", null == this.tdX10180 ? "unknow" : this.tdX10180);
        map.put("tdX2180", null == this.tdX2180 ? "unknow" : this.tdX2180);
        map.put("tdX230", null == this.tdX230 ? "unknow" : this.tdX230);
        map.put("tdX290", null == this.tdX290 ? "unknow" : this.tdX290);
        map.put("tdX3180", null == this.tdX3180 ? "unknow" : this.tdX3180);
        map.put("tdX330", null == this.tdX330 ? "unknow" : this.tdX330);
        map.put("tdX390", null == this.tdX390 ? "unknow" : this.tdX390);
        map.put("tdX590", null == this.tdX590 ? "unknow" : this.tdX590);
        map.put("tdX7180", null == this.tdX7180 ? "unknow" : this.tdX7180);
        map.put("tdX790", null == this.tdX790 ? "unknow" : this.tdX790);
        return map;
    }
}
