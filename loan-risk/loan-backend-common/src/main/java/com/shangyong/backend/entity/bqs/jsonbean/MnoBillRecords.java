/**
  * Copyright 2017 bejson.com 
  */
package com.shangyong.backend.entity.bqs.jsonbean;
import java.util.List;

/**
 * Auto-generated: 2017-12-10 18:25:17
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class MnoBillRecords {

    private String month;
    private String allFee;
    private String deductionFee;
    private String dueFee;
    private List<MnoBillRecordDetails> mnoBillRecordDetails;
    public void setMonth(String month) {
         this.month = month;
     }
     public String getMonth() {
         return month;
     }

    public void setAllFee(String allFee) {
         this.allFee = allFee;
     }
     public String getAllFee() {
         return allFee;
     }

    public void setDeductionFee(String deductionFee) {
         this.deductionFee = deductionFee;
     }
     public String getDeductionFee() {
         return deductionFee;
     }

    public void setDueFee(String dueFee) {
         this.dueFee = dueFee;
     }
     public String getDueFee() {
         return dueFee;
     }

    public void setMnoBillRecordDetails(List<MnoBillRecordDetails> mnoBillRecordDetails) {
         this.mnoBillRecordDetails = mnoBillRecordDetails;
     }
     public List<MnoBillRecordDetails> getMnoBillRecordDetails() {
         return mnoBillRecordDetails;
     }

}