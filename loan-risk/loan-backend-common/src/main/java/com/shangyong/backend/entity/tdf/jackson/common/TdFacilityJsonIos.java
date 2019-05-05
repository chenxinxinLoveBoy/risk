/**
  * Copyright 2017 bejson.com 
  */
package com.shangyong.backend.entity.tdf.jackson.common;
import java.util.List;

/**
 * Auto-generated: 2017-12-13 21:57:55
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class TdFacilityJsonIos {
	 private Attribution attribution;
	    private Device_info device_info;
	    private String final_decision;
	    private String final_score;
	    private List<Hit_rules> hit_rules;
	    private String policy_name;
	    private List<Policy_set> policy_set;
	    private String policy_set_name;
	    private String risk_type;
	    private String seq_id;
	    private String spend_time;
	    private String success;
	    private String reason_code;
	    private Credit_score credit_score;
	    private Geoip_info geoip_info;
		public Attribution getAttribution() {
			return attribution;
		}
		public void setAttribution(Attribution attribution) {
			this.attribution = attribution;
		}
		public Device_info getDevice_info() {
			return device_info;
		}
		public void setDevice_info(Device_info device_info) {
			this.device_info = device_info;
		}
		public String getFinal_decision() {
			return final_decision;
		}
		public void setFinal_decision(String final_decision) {
			this.final_decision = final_decision;
		}
		public String getFinal_score() {
			return final_score;
		}
		public void setFinal_score(String final_score) {
			this.final_score = final_score;
		}
		public List<Hit_rules> getHit_rules() {
			return hit_rules;
		}
		public void setHit_rules(List<Hit_rules> hit_rules) {
			this.hit_rules = hit_rules;
		}
		public String getPolicy_name() {
			return policy_name;
		}
		public void setPolicy_name(String policy_name) {
			this.policy_name = policy_name;
		}
		public List<Policy_set> getPolicy_set() {
			return policy_set;
		}
		public void setPolicy_set(List<Policy_set> policy_set) {
			this.policy_set = policy_set;
		}
		public String getPolicy_set_name() {
			return policy_set_name;
		}
		public void setPolicy_set_name(String policy_set_name) {
			this.policy_set_name = policy_set_name;
		}
		public String getRisk_type() {
			return risk_type;
		}
		public void setRisk_type(String risk_type) {
			this.risk_type = risk_type;
		}
		public String getSeq_id() {
			return seq_id;
		}
		public void setSeq_id(String seq_id) {
			this.seq_id = seq_id;
		}
		public String getSpend_time() {
			return spend_time;
		}
		public void setSpend_time(String spend_time) {
			this.spend_time = spend_time;
		}
		public String getSuccess() {
			return success;
		}
		public void setSuccess(String success) {
			this.success = success;
		}
		public String getReason_code() {
			return reason_code;
		}
		public void setReason_code(String reason_code) {
			this.reason_code = reason_code;
		}
		public Credit_score getCredit_score() {
			return credit_score;
		}
		public void setCredit_score(Credit_score credit_score) {
			this.credit_score = credit_score;
		}
		public Geoip_info getGeoip_info() {
			return geoip_info;
		}
		public void setGeoip_info(Geoip_info geoip_info) {
			this.geoip_info = geoip_info;
		}
		

}