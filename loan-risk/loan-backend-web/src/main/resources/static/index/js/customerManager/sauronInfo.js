layui.config({
	base : '../../js/'
});
var buApplicationId = "";
var platformId = "";
platformId = getQueryString("id");// 获取页面传过来的平台用户编号
buApplicationId = getQueryString("applicationId");
function getmysqldata() {
	getUserBasic();
	getRiskSocialNetwork();
	getRiskBlacklist();
	getHistoryOrg();
	getHistorySearch();
}
function getUserBasic() {
	$.ajax({// 查询基本信息
		url : getCtxPath() + "/backend/hlsl/getUserBasic.do?v="
				+ new Date().getTime(),
		method : "post",
		dataType : "json",
		data : {
			buApplicationId : buApplicationId,
			platformId : platformId
		},
		success : function(data) {
			if (data.code == 200) {
				var userBasic = data.list.data;
				if (userBasic != null) {
					$("#age").html(userBasic.age);
					$("#gender").html(userBasic.gender);
					$("#birthday").html(userBasic.birthday);
					var idcardValidate = "";
					if (userBasic.idcardValidate == 0) {
						idcardValidate = '未通过';
					} else if (userBasic.idcardValidate == 1) {
						idcardValidate = '通过';
					}
					$("#idcardValidate").html(idcardValidate);
					$("#idcardProvince").html(userBasic.idcardProvince);
					$("#idcardCity").html(userBasic.idcardCity);
					$("#idcardRegion").html(userBasic.idcardRegion);
					$("#phoneOperator").html(userBasic.phoneOperator);
					$("#phoneProvince").html(userBasic.phoneProvince);
					$("#phoneCity").html(userBasic.phoneCity);
					$("#recordIdcardDays").html(userBasic.recordIdcardDays);
					$("#recordPhoneDays").html(userBasic.recordPhoneDays);
					$("#lastAppearIdcard").html(userBasic.lastAppearIdcard);
					$("#lastAppearPhone").html(userBasic.lastAppearPhone);
					$("#usedIdcardsCnt").html(userBasic.usedIdcardsCnt);
					$("#usedPhonesCnt").html(userBasic.usedPhonesCnt);
				}
			}

		},
		error : function() {
			layer.msg("错误", {
				icon : 2
			});// 提示信息
		},
		complete : function() {
		}
	});
}
function getRiskSocialNetwork() {
	$
			.ajax({// 社交风险点
				url : getCtxPath() + "/backend/hlsl/getRiskSocialNetwork.do?v="
						+ new Date().getTime(),
				method : "post",
				dataType : "json",
				data : {
					buApplicationId : buApplicationId,
					platformId : platformId

				},
				success : function(data) {
					if (data.code == 200) {
						var HlslRiskSocialNetwork = data.list.data;
						if (HlslRiskSocialNetwork != null) {
							if (HlslRiskSocialNetwork.snScore == -99
									|| HlslRiskSocialNetwork.snScore == "-99") {
								$("#snScore").html("");
							} else {
								$("#snScore").html(
										HlslRiskSocialNetwork.snScore);
							}
							$("#snOrder1ContactsCnt").html(
									HlslRiskSocialNetwork.snOrder1ContactsCnt);
							$("#snOrder1BlacklistContactsCnt")
									.html(
											HlslRiskSocialNetwork.snOrder1BlacklistContactsCnt);
							$("#snOrder2BlacklistContactsCnt")
									.html(
											HlslRiskSocialNetwork.snOrder2BlacklistContactsCnt);
							$("#snOrder2BlacklistRoutersCnt")
									.html(
											HlslRiskSocialNetwork.snOrder2BlacklistRoutersCnt);
							$("#snOrder2BlacklistRoutersPct")
									.html(
											HlslRiskSocialNetwork.snOrder2BlacklistRoutersPct);
						}
					}
				},
				error : function() {
					layer.msg("错误", {
						icon : 2
					});// 提示信息
				},
				complete : function() {
				}
			});
}
function getRiskBlacklist() {
	$
			.ajax({// 黑名单
				url : getCtxPath() + "/backend/hlsl/getRiskBlacklist.do?v="
						+ new Date().getTime(),
				method : "post",
				dataType : "json",
				data : {
					buApplicationId : buApplicationId,
					platformId : platformId
				},
				success : function(data) {
					// console.log(data);
					if (data.code == 200) {
						var riskBlack = data.list.data;
						if (riskBlack != null) {
							var idcardInBlacklist = "";
							var phoneInBlacklist = "";
							var inCourtBlacklist = "";
							var inP2pBlacklist = "";
							var inBankBlacklist = "";
							// console.log(data);
							idcardInBlacklist = riskBlack.idcardInBlacklist == true ? "是"
									: "否";
							phoneInBlacklist = riskBlack.phoneInBlacklist == true ? "是"
									: "否";
							inCourtBlacklist = riskBlack.inCourtBlacklist == true ? "是"
									: "否";
							inP2pBlacklist = riskBlack.inP2pBlacklist == true ? "是"
									: "否";
							inBankBlacklist = riskBlack.inBankBlacklist == true ? "是"
									: "否";
							$("#idcardInBlacklist").html(idcardInBlacklist);
							$("#phoneInBlacklist").html(phoneInBlacklist);
							$("#inCourtBlacklist").html(inCourtBlacklist);
							$("#inP2pBlacklist").html(inP2pBlacklist);
							$("#inBankBlacklist").html(inBankBlacklist);
							$("#lastAppearIdcardInBlacklist").html(
									riskBlack.lastAppearIdcardInBlacklist);
							$("#lastAppearPhoneInBlacklist").html(
									riskBlack.lastAppearPhoneInBlacklist);
						}
					}
				},
				error : function() {
					layer.msg("错误", {
						icon : 2
					});// 提示信息
				},
				complete : function() {
				}
			});
}
function getHistoryOrg() {
	$.ajax({// 历史类型
		url : getCtxPath() + "/backend/hlsl/getHistoryOrg.do?v="
				+ new Date().getTime(),
		method : "post",
		dataType : "json",
		data : {
			buApplicationId : buApplicationId,
			platformId : platformId
		},
		success : function(data) {
			if (data.code == 200) {
				var historyOrg = data.list.data;
				if (historyOrg != null) {
					$("#onlineInstallmentCnt").html(
							historyOrg.onlineInstallmentCnt);
					$("#offlineInstallmentCnt").html(
							historyOrg.offlineInstallmentCnt);
					$("#creditCardRepaymentCnt").html(
							historyOrg.creditCardRepaymentCnt);
					$("#paydayLoanCnt").html(historyOrg.paydayLoanCnt);
					$("#onlineCashLoanCnt").html(historyOrg.onlineCashLoanCnt);
					$("#offlineCashLoanCnt")
							.html(historyOrg.offlineCashLoanCnt);
					$("#othersCnt").html(historyOrg.othersCnt);
				}
			}
		},
		error : function() {
			layer.msg("错误", {
				icon : 2
			});// 提示信息
		},
		complete : function() {
		}
	});
}
function getHistorySearch() {
	$.ajax({// 历史类型
		url : getCtxPath() + "/backend/hlsl/getHistorySearch.do?v="
				+ new Date().getTime(),
		method : "post",
		dataType : "json",
		data : {
			buApplicationId : buApplicationId,
			platformId : platformId
		},
		success : function(data) {
			if (data.code == 200) {
				var historySearch = data.list.data;
				if (historySearch != null) {
					$("#searchCnt").html(historySearch.searchCnt);
					$("#searchCntRecent7Days").html(
							historySearch.searchCntRecent7Days);
					$("#searchCntRecent14Days").html(
							historySearch.searchCntRecent14Days);
					$("#searchCntRecent30Days").html(
							historySearch.searchCntRecent30Days);
					$("#searchCntRecent60Days").html(
							historySearch.searchCntRecent60Days);
					$("#searchCntRecent90Days").html(
							historySearch.searchCntRecent90Days);
					$("#searchCntRecent180Days").html(
							historySearch.searchCntRecent180Days);
					$("#orgCnt").html(historySearch.orgCnt);
					$("#orgCntRecent7Days").html(
							historySearch.orgCntRecent7Days);
					$("#orgCntRecent14Days").html(
							historySearch.orgCntRecent14Days);
					$("#orgCntRecent30Days").html(
							historySearch.orgCntRecent30Days);
					$("#orgCntRecent60Days").html(
							historySearch.orgCntRecent60Days);
					$("#orgCntRecent90Days").html(
							historySearch.orgCntRecent90Days);
					$("#orgCntRecent180Days").html(
							historySearch.orgCntRecent180Days);
				}
			}
		},
		error : function() {
			layer.msg("错误", {
				icon : 2
			});// 提示信息
		},
		complete : function() {
		}
	});
}

function infoQuery() {
	$
			.ajax({// 查询基本信息
				url : getCtxPath()
						+ "/backend/queryAliyunDataInfo/getInfo.do?v="
						+ new Date().getTime(),
				method : "post",
				dataType : "json",
				data : {
					applicationId : buApplicationId,
					taskType : "11001",
					platformId : platformId
				},
				success : function(obj) {
					if (obj.data === undefined) {
						return;
					}
					var info = JSON.parse(obj.data.info);
					if (info.user_basic !== undefined) {
						$("#age").html(info.user_basic.age);
						$("#gender").html(info.user_basic.gender);
						$("#birthday").html(info.user_basic.birthday);
						var idcardValidate = "";
						if (info.user_basic.idcard_validate == 0) {
							idcardValidate = '未通过';
						} else if (info.user_basic.idcard_validate == 1) {
							idcardValidate = '通过';
						}
						$("#idcardValidate").html(idcardValidate);
						$("#idcardProvince").html(
								info.user_basic.idcard_province);
						$("#idcardCity").html(info.user_basic.idcard_city);
						$("#idcardRegion").html(info.user_basic.idcard_region);
						$("#phoneOperator")
								.html(info.user_basic.phone_operator);
						$("#phoneProvince")
								.html(info.user_basic.phone_province);
						$("#phoneCity").html(info.user_basic.phone_city);
						$("#recordIdcardDays").html(
								info.user_basic.record_idcard_days);
						$("#recordPhoneDays").html(
								info.user_basic.record_phone_days);
						$("#lastAppearIdcard").html(
								info.user_basic.last_appear_idcard);
						$("#lastAppearPhone").html(
								info.user_basic.last_appear_phone);
						$("#usedIdcardsCnt").html(
								info.user_basic.used_idcards_cnt);
						$("#usedPhonesCnt").html(
								info.user_basic.used_phones_cnt);
					}

					if (info.risk_social_network !== undefined) {
						// 葫芦分
						$("#snScore").html(info.risk_social_network.sn_score);
						$("#snOrder1ContactsCnt")
								.html(
										info.risk_social_network.sn_order1_contacts_cnt);
						$("#snOrder1BlacklistContactsCnt")
								.html(
										info.risk_social_network.sn_order1_blacklist_contacts_cnt);
						$("#snOrder2BlacklistContactsCnt")
								.html(
										info.risk_social_network.sn_order2_blacklist_contacts_cnt);
						$("#snOrder2BlacklistRoutersCnt")
								.html(
										info.risk_social_network.sn_order2_blacklist_routers_cnt);
						$("#snOrder2BlacklistRoutersPct")
								.html(
										info.risk_social_network.sn_order2_blacklist_routers_pct);
					}

					if (info.risk_blacklist !== undefined) {
						// 黑名单
						var idcardInBlacklist = "";
						var phoneInBlacklist = "";
						var inCourtBlacklist = "";
						var inP2pBlacklist = "";
						var inBankBlacklist = "";
						idcardInBlacklist = info.risk_blacklist.idcard_in_blacklist == true ? "是"
								: "否";
						phoneInBlacklist = info.risk_blacklist.phone_in_blacklist == true ? "是"
								: "否";
						inCourtBlacklist = info.risk_blacklist.in_court_blacklist == true ? "是"
								: "否";
						inP2pBlacklist = info.risk_blacklist.in_p2p_blacklist == true ? "是"
								: "否";
						inBankBlacklist = info.risk_blacklist.in_bank_blacklist == true ? "是"
								: "否";
						$("#idcardInBlacklist").html(idcardInBlacklist);
						$("#phoneInBlacklist").html(phoneInBlacklist);
						$("#inCourtBlacklist").html(inCourtBlacklist);
						$("#inP2pBlacklist").html(inP2pBlacklist);
						$("#inBankBlacklist").html(inBankBlacklist);
						$("#lastAppearIdcardInBlacklist")
								.html(
										info.risk_blacklist.last_appear_idcard_in_blacklist);
						$("#lastAppearPhoneInBlacklist")
								.html(
										info.risk_blacklist.last_appear_phone_in_blacklist);
					}

					if (info.history_org !== undefined) {
						// 历史类型
						$("#onlineInstallmentCnt").html(
								info.history_org.online_installment_cnt);
						$("#offlineInstallmentCnt").html(
								info.history_org.offline_installment_cnt);
						$("#creditCardRepaymentCnt").html(
								info.history_org.credit_card_repayment_cnt);
						$("#paydayLoanCnt").html(
								info.history_org.payday_loan_cnt);
						$("#onlineCashLoanCnt").html(
								info.history_org.online_cash_loan_cnt);
						$("#offlineCashLoanCnt").html(
								info.history_org.offline_cash_loan_cnt);
						$("#othersCnt").html(info.history_org.others_cnt);
					}

					if (info.history_search !== undefined) {
						// 查询历史
						$("#searchCnt").html(info.history_search.search_cnt);
						$("#searchCntRecent7Days").html(
								info.history_search.search_cnt_recent_7_days);
						$("#searchCntRecent14Days").html(
								info.history_search.search_cnt_recent_14_days);
						$("#searchCntRecent30Days").html(
								info.history_search.search_cnt_recent_30_days);
						$("#searchCntRecent60Days").html(
								info.history_search.search_cnt_recent_60_days);
						$("#searchCntRecent90Days").html(
								info.history_search.search_cnt_recent_90_days);
						$("#searchCntRecent180Days").html(
								info.history_search.search_cnt_recent_180_days);

						$("#orgCnt").html(info.history_search.org_cnt);

						$("#orgCntRecent7Days").html(
								info.history_search.org_cnt_recent_7_days);
						$("#orgCntRecent14Days").html(
								info.history_search.org_cnt_recent_14_days);
						$("#orgCntRecent30Days").html(
								info.history_search.org_cnt_recent_30_days);
						$("#orgCntRecent60Days").html(
								info.history_search.org_cnt_recent_60_days);
						$("#orgCntRecent90Days").html(
								info.history_search.org_cnt_recent_90_days);
						$("#orgCntRecent180Days").html(
								info.history_search.org_cnt_recent_180_days);
					}

				},
				error : function() {
					layer.msg("错误", {
						icon : 2
					});// 提示信息
				},
				complete : function() {
				}
			});
}