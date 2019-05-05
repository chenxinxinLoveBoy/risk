layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
	var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
	layer = layui.layer, // 获取当前窗口的layer对象
	form = layui.form(), element = layui.element();
});
		
$(function() { 
	 var customerCheckApplyId = getQueryString("id");//获取页面传过来的数据测试单号
 	$.ajax({//查询基本信息
		    url : getCtxPath() + "/backend/cuCustomerCheckApply/getDataReportByMongo.do?v=" + new Date().getTime(),
				method: "post",
			dataType:"json",
			data: { 
				customerCheckApplyId:customerCheckApplyId,
				taskType: "11001",
			},
			success: function(obj){
				var msg=obj.list.data.list;
				    if(msg==null)
				    {
				    	return ;
				    }
 					$("#age").html(msg.user_basic.age);
					$("#gender").html(msg.user_basic.gender);
					$("#birthday").html(msg.user_basic.birthday);
					var idcardValidate="";
					if(msg.user_basic.idcard_validate==0){
						idcardValidate='未通过';
					}else if(msg.user_basic.idcard_validate==1){
						idcardValidate='通过';
					}
					$("#idcardValidate").html(idcardValidate);
					$("#idcardProvince").html(msg.user_basic.idcard_province);
					$("#idcardCity").html(msg.user_basic.idcard_city);
					$("#idcardRegion").html(msg.user_basic.idcard_region);
					$("#phoneOperator").html(msg.user_basic.phone_operator);
					$("#phoneProvince").html(msg.user_basic.phone_province);
					$("#phoneCity").html(msg.user_basic.phone_city);
					$("#recordIdcardDays").html(msg.user_basic.record_idcard_days);
					$("#recordPhoneDays").html(msg.user_basic.record_phone_days);
					$("#lastAppearIdcard").html(msg.user_basic.last_appear_idcard);
					$("#lastAppearPhone").html(msg.user_basic.last_appear_phone);
					$("#usedIdcardsCnt").html(msg.user_basic.used_idcards_cnt);
					$("#usedPhonesCnt").html(msg.user_basic.used_phones_cnt);
					
					//葫芦分
					$("#snScore").html(msg.risk_social_network.sn_score);
					$("#snOrder1ContactsCnt").html(msg.risk_social_network.sn_order1_contacts_cnt);
					$("#snOrder1BlacklistContactsCnt").html(msg.risk_social_network.sn_order1_blacklist_contacts_cnt);
					$("#snOrder2BlacklistContactsCnt").html(msg.risk_social_network.sn_order2_blacklist_contacts_cnt);
					$("#snOrder2BlacklistRoutersCnt").html(msg.risk_social_network.sn_order2_blacklist_routers_cnt);
					$("#snOrder2BlacklistRoutersPct").html(msg.risk_social_network.sn_order2_blacklist_routers_pct);
					
					
					//黑名单
					var idcardInBlacklist="";
					var phoneInBlacklist="";
					var inCourtBlacklist="";
					var inP2pBlacklist="";
					var inBankBlacklist="";
					idcardInBlacklist=msg.risk_blacklist.idcard_in_blacklist==true?"是":"否";
					phoneInBlacklist=msg.risk_blacklist.phone_in_blacklist==true?"是":"否";
					inCourtBlacklist=msg.risk_blacklist.in_court_blacklist==true?"是":"否";
					inP2pBlacklist=msg.risk_blacklist.in_p2p_blacklist==true?"是":"否";
					inBankBlacklist=msg.risk_blacklist.in_bank_blacklist==true?"是":"否";
					$("#idcardInBlacklist").html(idcardInBlacklist);
					$("#phoneInBlacklist").html(phoneInBlacklist);
					$("#inCourtBlacklist").html(inCourtBlacklist);
					$("#inP2pBlacklist").html(inP2pBlacklist);
					$("#inBankBlacklist").html(inBankBlacklist);
					$("#lastAppearIdcardInBlacklist").html(msg.risk_blacklist.last_appear_idcard_in_blacklist);
					$("#lastAppearPhoneInBlacklist").html(msg.risk_blacklist.last_appear_phone_in_blacklist);
					
					
					//历史类型
					$("#onlineInstallmentCnt").html(msg.history_org.online_installment_cnt);
					$("#offlineInstallmentCnt").html(msg.history_org.offline_installment_cnt);
					$("#creditCardRepaymentCnt").html(msg.history_org.credit_card_repayment_cnt);
					$("#paydayLoanCnt").html(msg.history_org.payday_loan_cnt);
					$("#onlineCashLoanCnt").html(msg.history_org.online_cash_loan_cnt);
					$("#offlineCashLoanCnt").html(msg.history_org.offline_cash_loan_cnt);
					$("#othersCnt").html(msg.history_org.others_cnt);
					
					//查询历史
					$("#searchCnt").html(msg.history_search.search_cnt);
					$("#searchCntRecent7Days").html(msg.history_search.search_cnt_recent_7_days);
					$("#searchCntRecent14Days").html(msg.history_search.search_cnt_recent_14_days);
					$("#searchCntRecent30Days").html(msg.history_search.search_cnt_recent_30_days);
					$("#searchCntRecent60Days").html(msg.history_search.search_cnt_recent_60_days);
					$("#searchCntRecent90Days").html(msg.history_search.search_cnt_recent_90_days);
					$("#searchCntRecent180Days").html(msg.history_search.search_cnt_recent_180_days);
					
					$("#orgCnt").html(msg.history_search.org_cnt);
					
					$("#orgCntRecent7Days").html(msg.history_search.org_cnt_recent_7_days);
					$("#orgCntRecent14Days").html(msg.history_search.org_cnt_recent_14_days);
					$("#orgCntRecent30Days").html(msg.history_search.org_cnt_recent_30_days);
					$("#orgCntRecent60Days").html(msg.history_search.org_cnt_recent_60_days);
					$("#orgCntRecent90Days").html(msg.history_search.org_cnt_recent_90_days);
					$("#orgCntRecent180Days").html(msg.history_search.org_cnt_recent_180_days);
					
				
			},
			error: function(){
					layer.msg("错误" , {icon: 2});//提示信息
			},
			complete: function(){ 
			} 
		});
});