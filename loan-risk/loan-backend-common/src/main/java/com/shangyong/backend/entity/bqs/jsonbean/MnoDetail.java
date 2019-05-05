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
public class MnoDetail {

    private String storeTime;
    private MnoPersonalInfo mnoPersonalInfo;
    private List<MnoCallRecords> mnoCallRecords;
    private List<MnoSmsRecords> mnoSmsRecords;
    private List<MnoBillRecords> mnoBillRecords;
    private List<MnoPaymentRecords> mnoPaymentRecords;
    private List<MnoNetPlayRecords> mnoNetPlayRecords;
    private List<MnoForwardRecords> mnoForwardRecords;
    public void setStoreTime(String storeTime) {
         this.storeTime = storeTime;
     }
     public String getStoreTime() {
         return storeTime;
     }

    public void setMnoPersonalInfo(MnoPersonalInfo mnoPersonalInfo) {
         this.mnoPersonalInfo = mnoPersonalInfo;
     }
     public MnoPersonalInfo getMnoPersonalInfo() {
         return mnoPersonalInfo;
     }

    public void setMnoCallRecords(List<MnoCallRecords> mnoCallRecords) {
         this.mnoCallRecords = mnoCallRecords;
     }
     public List<MnoCallRecords> getMnoCallRecords() {
         return mnoCallRecords;
     }

    public void setMnoSmsRecords(List<MnoSmsRecords> mnoSmsRecords) {
         this.mnoSmsRecords = mnoSmsRecords;
     }
     public List<MnoSmsRecords> getMnoSmsRecords() {
         return mnoSmsRecords;
     }

    public void setMnoBillRecords(List<MnoBillRecords> mnoBillRecords) {
         this.mnoBillRecords = mnoBillRecords;
     }
     public List<MnoBillRecords> getMnoBillRecords() {
         return mnoBillRecords;
     }

    public void setMnoPaymentRecords(List<MnoPaymentRecords> mnoPaymentRecords) {
         this.mnoPaymentRecords = mnoPaymentRecords;
     }
     public List<MnoPaymentRecords> getMnoPaymentRecords() {
         return mnoPaymentRecords;
     }

    public void setMnoNetPlayRecords(List<MnoNetPlayRecords> mnoNetPlayRecords) {
         this.mnoNetPlayRecords = mnoNetPlayRecords;
     }
     public List<MnoNetPlayRecords> getMnoNetPlayRecords() {
         return mnoNetPlayRecords;
     }

    public void setMnoForwardRecords(List<MnoForwardRecords> mnoForwardRecords) {
         this.mnoForwardRecords = mnoForwardRecords;
     }
     public List<MnoForwardRecords> getMnoForwardRecords() {
         return mnoForwardRecords;
     }

}