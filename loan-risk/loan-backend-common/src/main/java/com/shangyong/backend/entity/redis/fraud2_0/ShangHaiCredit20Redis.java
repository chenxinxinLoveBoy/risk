package com.shangyong.backend.entity.redis.fraud2_0;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 上海资信 2.0 版本
 */
public class ShangHaiCredit20Redis implements Serializable {

    /**
     * 最小累计逾期期数（取最小值）
     * sh_min_total_overdue_number --> total_overdue_number
     */
    private String shMinTotalOverdueNumber;

    /**
     * 最后3个月包含N的个数——多笔要求和
     * sh_credit_loans  --> payment_status
     */
    private String shSumLoans24N3m;

    /**
     * 最后1个月包含1的个数——多笔要求和
     * sh_credit_loans  --> payment_status
     */
    private String shSumLoans2411m;

    /**
     * 金额 求和 sh_avg_repayment_amount_month--> repayment_amount_month
     */
    private String shAvgRepaymentAmountMonthTotalAmount;

    /**
     * sh_avg_repayment_amount_month
     * sh_credit_loans --> repayment_amount_month
     *  记录数 sh_avg_repayment_amount_month
     */
    private String shAvgRepaymentAmountMonthCount;

    /**
     * sh_avg_loans_money + sh_loans_total
     * sh_credit_loans_deal -->  loans_temps is not null
     */
    private String loansTotal;

    /**
     * sh_avg_loans_money
     * sh_credit_loans_deal --> loans_temps is not null
     */
    private String loansNumber;


    /**
     * sh_sum_total_overdue_number
     * sh_credit_loans--> total_overdue_number
     * 合计累计逾期期数——多笔要求和
     */
    private String shSumTotalOverdueNumber;


    /**
     * sh_max_actual_payment_amount
     * sh_credit_loans --> actual_payment_amount
     * 本月实际还款金额——最大值
     */
    private String shMaxActualPaymentAmount;


    /**
     * sh_latest_overdue_sum_overdue_two_month
     * sh_credit_loans --> overdue_two_month
     * 求和
     * account_status<>'结清' AND actual_payment_amount-repayment_amount_month<0
     */
    private String shLatestOverdueSumOverdueTwoMonth;

    /**
     * sh_sum_loans_24_notNC_3m
     * sh_credit_loans --> payment_status
     * 最后3个月not in('N','C')合计——多笔要求和，SELECT 3-LENGTH(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(RIGHT(payment_status,3),'1',''),'2',''),'3',''),'4',''),'5',''),'6',''),'7',''),'D',''),'Z',''),'G','')) sh_loans_24_notNC_3m
     * ,payment_status
     * FROM `sh_credit_loans`"
     *
     */
    private String shSumLoans24NotNC3m;

    public String getShMinTotalOverdueNumber() {
        return shMinTotalOverdueNumber;
    }

    public void setShMinTotalOverdueNumber(String shMinTotalOverdueNumber) {
        this.shMinTotalOverdueNumber = shMinTotalOverdueNumber;
    }

    public String getShSumLoans24N3m() {
        return shSumLoans24N3m;
    }

    public void setShSumLoans24N3m(String shSumLoans24N3m) {
        this.shSumLoans24N3m = shSumLoans24N3m;
    }

    public String getShSumLoans2411m() {
        return shSumLoans2411m;
    }

    public void setShSumLoans2411m(String shSumLoans2411m) {
        this.shSumLoans2411m = shSumLoans2411m;
    }

    public String getShAvgRepaymentAmountMonthTotalAmount() {
        return shAvgRepaymentAmountMonthTotalAmount;
    }

    public void setShAvgRepaymentAmountMonthTotalAmount(String shAvgRepaymentAmountMonthTotalAmount) {
        this.shAvgRepaymentAmountMonthTotalAmount = shAvgRepaymentAmountMonthTotalAmount;
    }

    public String getShAvgRepaymentAmountMonthCount() {
        return shAvgRepaymentAmountMonthCount;
    }

    public void setShAvgRepaymentAmountMonthCount(String shAvgRepaymentAmountMonthCount) {
        this.shAvgRepaymentAmountMonthCount = shAvgRepaymentAmountMonthCount;
    }

    public String getLoansTotal() {
        return loansTotal;
    }

    public void setLoansTotal(String loansTotal) {
        this.loansTotal = loansTotal;
    }

    public String getLoansNumber() {
        return loansNumber;
    }

    public void setLoansNumber(String loansNumber) {
        this.loansNumber = loansNumber;
    }

    public String getShSumTotalOverdueNumber() {
        return shSumTotalOverdueNumber;
    }

    public void setShSumTotalOverdueNumber(String shSumTotalOverdueNumber) {
        this.shSumTotalOverdueNumber = shSumTotalOverdueNumber;
    }

    public String getShMaxActualPaymentAmount() {
        return shMaxActualPaymentAmount;
    }

    public void setShMaxActualPaymentAmount(String shMaxActualPaymentAmount) {
        this.shMaxActualPaymentAmount = shMaxActualPaymentAmount;
    }

    public String getShLatestOverdueSumOverdueTwoMonth() {
        return shLatestOverdueSumOverdueTwoMonth;
    }

    public void setShLatestOverdueSumOverdueTwoMonth(String shLatestOverdueSumOverdueTwoMonth) {
        this.shLatestOverdueSumOverdueTwoMonth = shLatestOverdueSumOverdueTwoMonth;
    }

    public String getShSumLoans24NotNC3m() {
        return shSumLoans24NotNC3m;
    }

    public void setShSumLoans24NotNC3m(String shSumLoans24NotNC3m) {
        this.shSumLoans24NotNC3m = shSumLoans24NotNC3m;
    }

    public HashMap<String, String> toMap(){
        HashMap<String, String> map = new HashMap<>();

        map.put("shMinTotalOverdueNumber", null == this.shMinTotalOverdueNumber ? "unknow" : this.shMinTotalOverdueNumber);
        map.put("shSumLoans24N3m", null == this.shSumLoans24N3m ? "unknow" : this.shSumLoans24N3m);
        map.put("shSumLoans2411m", null == this.shSumLoans2411m ? "unknow" : this.shSumLoans2411m);
        map.put("shAvgRepaymentAmountMonthTotalAmount", null == this.shAvgRepaymentAmountMonthTotalAmount ? "unknow" : this.shAvgRepaymentAmountMonthTotalAmount);
        map.put("shAvgRepaymentAmountMonthCount", null == this.shAvgRepaymentAmountMonthCount ? "unknow" : this.shAvgRepaymentAmountMonthCount);
        map.put("loansTotal", null == this.loansTotal ? "unknow" : this.loansTotal);
        map.put("loansNumber", null == this.loansNumber ? "unknow" : this.loansNumber);
        map.put("shSumTotalOverdueNumber", null == this.shSumTotalOverdueNumber ? "unknow" : this.shSumTotalOverdueNumber);
        map.put("shMaxActualPaymentAmount", null == this.shMaxActualPaymentAmount ? "unknow" : this.shMaxActualPaymentAmount);
        map.put("shLatestOverdueSumOverdueTwoMonth", null == this.shLatestOverdueSumOverdueTwoMonth ? "unknow" : this.shLatestOverdueSumOverdueTwoMonth);
        map.put("shSumLoans24NotNC3m", null == this.shSumLoans24NotNC3m ? "unknow" : this.shSumLoans24NotNC3m);

        return map;
    }
}
