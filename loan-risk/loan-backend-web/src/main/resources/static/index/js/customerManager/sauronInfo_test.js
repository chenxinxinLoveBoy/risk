layui.config({
	base : '../../js/'
});
var buApplicationId =""; 
var platformId =""; 
layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
			layer = layui.layer, // 获取当前窗口的layer对象
			form = layui.form(), element = layui.element();
			//var buApplicationId='e9176076b18743be919bd87af618fa83';
			buApplicationId=getQueryString("applicationId");
			platformId = getQueryString("id");
			$("#bindingIdcards").prop("src",getCtxPath()+'/index/views/customerManager/sauronInfo_BindingIdcards.html?applicationId='+buApplicationId);
			// 页面初始化加载		start
			paging.init({//绑定手机查询
                openWait: true,
				url : getCtxPath() + "/backend/hlsl/getBindingPhones.do?v=" + new Date().getTime(),// 请求服务器的url
				elem: '#content', //内容容器
				params: { //发送到服务端的参数
					buApplicationId : buApplicationId,
					platformId:platformId
				},
				type: 'post',
				tempElem: '#tpl', //模块容器
				pageConfig: { //分页参数配置
					skin:'#e2e2e2',
					elem: '#paged', //分页容器
					pageSize: 10 //分页大小，当前页面显示的条数
				},
				success: function(data) { //渲染成功的回调
				},
				fail: function(msg) { //获取数据失败的回调
				},
				complate: function() { //完成的回调 
				}
				
            }); 
		});

	$(function() {
		getUserBasicData();//加载基本信息
		//getRiskSocialNetwork(buApplicationId);//加载社交风险点
		//getRiskBlacklist(buApplicationId);//加载黑名单
		//getHistoryOrg(buApplicationId);//加载历史类型
		//getHistorySearch(buApplicationId);//加载查询历史
	});
	function getUserBasicData(){
		buApplicationId=getQueryString("applicationId");
		platformId = getQueryString("id");
		$.ajax({//查询基本信息
				//url :"http://shandai-real.oss-cn-shanghai.aliyuncs.com/hlsl/20170829/236aabed07a3e9e2f4014285aece0799.json",
				url:getCtxPath() + "/backend/mongo/getMongoDisplay.do?v=" + new Date().getTime(),
				method: "post",
				dataType:"json",
				data: { 
					applicationID:buApplicationId,
					taskType: "11001",
					platformId:platformId
				},
				success: function(obj){
					    if(obj.list===undefined)
					    {
					    	return ;
					    }
					    if(obj.list.data.user_basic!==undefined)
					    {
							$("#age").html(obj.list.data.user_basic.age);
							$("#gender").html(obj.list.data.user_basic.gender);
							$("#birthday").html(obj.list.data.user_basic.birthday);
							var idcardValidate="";
							if(obj.list.data.user_basic.idcard_validate==0){
								idcardValidate='未通过';
							}else if(obj.list.data.user_basic.idcard_validate==1){
								idcardValidate='通过';
							}
							$("#idcardValidate").html(idcardValidate);
							$("#idcardProvince").html(obj.list.data.user_basic.idcard_province);
							$("#idcardCity").html(obj.list.data.user_basic.idcard_city);
							$("#idcardRegion").html(obj.list.data.user_basic.idcard_region);
							$("#phoneOperator").html(obj.list.data.user_basic.phone_operator);
							$("#phoneProvince").html(obj.list.data.user_basic.phone_province);
							$("#phoneCity").html(obj.list.data.user_basic.phone_city);
							$("#recordIdcardDays").html(obj.list.data.user_basic.record_idcard_days);
							$("#recordPhoneDays").html(obj.list.data.user_basic.record_phone_days);
							$("#lastAppearIdcard").html(obj.list.data.user_basic.last_appear_idcard);
							$("#lastAppearPhone").html(obj.list.data.user_basic.last_appear_phone);
							$("#usedIdcardsCnt").html(obj.list.data.user_basic.used_idcards_cnt);
							$("#usedPhonesCnt").html(obj.list.data.user_basic.used_phones_cnt);
					    }
						
					    if(obj.list.data.risk_social_network!==undefined)
					    {
							//葫芦分
							$("#snScore").html(obj.list.data.risk_social_network.sn_score);
							$("#snOrder1ContactsCnt").html(obj.list.data.risk_social_network.sn_order1_contacts_cnt);
							$("#snOrder1BlacklistContactsCnt").html(obj.list.data.risk_social_network.sn_order1_blacklist_contacts_cnt);
							$("#snOrder2BlacklistContactsCnt").html(obj.list.data.risk_social_network.sn_order2_blacklist_contacts_cnt);
							$("#snOrder2BlacklistRoutersCnt").html(obj.list.data.risk_social_network.sn_order2_blacklist_routers_cnt);
							$("#snOrder2BlacklistRoutersPct").html(obj.list.data.risk_social_network.sn_order2_blacklist_routers_pct);
					    }
						
					    if(obj.list.data.risk_blacklist!==undefined)
					    {
						//黑名单
							var idcardInBlacklist="";
							var phoneInBlacklist="";
							var inCourtBlacklist="";
							var inP2pBlacklist="";
							var inBankBlacklist="";
							idcardInBlacklist=obj.list.data.risk_blacklist.idcard_in_blacklist==true?"是":"否";
							phoneInBlacklist=obj.list.data.risk_blacklist.phone_in_blacklist==true?"是":"否";
							inCourtBlacklist=obj.list.data.risk_blacklist.in_court_blacklist==true?"是":"否";
							inP2pBlacklist=obj.list.data.risk_blacklist.in_p2p_blacklist==true?"是":"否";
							inBankBlacklist=obj.list.data.risk_blacklist.in_bank_blacklist==true?"是":"否";
							$("#idcardInBlacklist").html(idcardInBlacklist);
							$("#phoneInBlacklist").html(phoneInBlacklist);
							$("#inCourtBlacklist").html(inCourtBlacklist);
							$("#inP2pBlacklist").html(inP2pBlacklist);
							$("#inBankBlacklist").html(inBankBlacklist);
							$("#lastAppearIdcardInBlacklist").html(obj.list.data.risk_blacklist.last_appear_idcard_in_blacklist);
							$("#lastAppearPhoneInBlacklist").html(obj.list.data.risk_blacklist.last_appear_phone_in_blacklist);
					    }
					    if(obj.list.data.history_org!==undefined)
					    {
							//历史类型
							$("#onlineInstallmentCnt").html(obj.list.data.history_org.online_installment_cnt);
							$("#offlineInstallmentCnt").html(obj.list.data.history_org.offline_installment_cnt);
							$("#creditCardRepaymentCnt").html(obj.list.data.history_org.credit_card_repayment_cnt);
							$("#paydayLoanCnt").html(obj.list.data.history_org.payday_loan_cnt);
							$("#onlineCashLoanCnt").html(obj.list.data.history_org.online_cash_loan_cnt);
							$("#offlineCashLoanCnt").html(obj.list.data.history_org.offline_cash_loan_cnt);
							$("#othersCnt").html(obj.list.data.history_org.others_cnt);
					    }
					    if(obj.list.data.history_search!==undefined)
					    {
						//查询历史
							$("#searchCnt").html(obj.list.data.history_search.search_cnt);
							$("#searchCntRecent7Days").html(obj.list.data.history_search.search_cnt_recent_7_days);
							$("#searchCntRecent14Days").html(obj.list.data.history_search.search_cnt_recent_14_days);
							$("#searchCntRecent30Days").html(obj.list.data.history_search.search_cnt_recent_30_days);
							$("#searchCntRecent60Days").html(obj.list.data.history_search.search_cnt_recent_60_days);
							$("#searchCntRecent90Days").html(obj.list.data.history_search.search_cnt_recent_90_days);
							$("#searchCntRecent180Days").html(obj.list.data.history_search.search_cnt_recent_180_days);
							
							$("#orgCnt").html(obj.list.data.history_search.org_cnt);
							
							$("#orgCntRecent7Days").html(obj.list.data.history_search.org_cnt_recent_7_days);
							$("#orgCntRecent14Days").html(obj.list.data.history_search.org_cnt_recent_14_days);
							$("#orgCntRecent30Days").html(obj.list.data.history_search.org_cnt_recent_30_days);
							$("#orgCntRecent60Days").html(obj.list.data.history_search.org_cnt_recent_60_days);
							$("#orgCntRecent90Days").html(obj.list.data.history_search.org_cnt_recent_90_days);
							$("#orgCntRecent180Days").html(obj.list.data.history_search.org_cnt_recent_180_days);
					    }
						
					
				},
				error: function(){
						layer.msg("错误" , {icon: 2});//提示信息
				},
				complete: function(){ 
				} 
			});
	}
//	function getRiskSocialNetwork(){
//		//var buApplicationId='e9176076b18743be919bd87af618fa83';
//		buApplicationId=getQueryString("applicationId");
//		$.ajax({//社交风险点
//			url :getCtxPath() + "/backend/hlsl/getRiskSocialNetwork.do?v="+ new Date().getTime(),
//			method: "post",
//			dataType:"json",
//			data: {
//				buApplicationId: buApplicationId
//			},
//			success: function(data){
//				if(data.code==200){
//					var HlslRiskSocialNetwork=data.list.data;
//					$("#snScore").html(HlslRiskSocialNetwork.snScore);
//					$("#snOrder1ContactsCnt").html(HlslRiskSocialNetwork.snOrder1ContactsCnt);
//					$("#snOrder1BlacklistContactsCnt").html(HlslRiskSocialNetwork.snOrder1BlacklistContactsCnt);
//					$("#snOrder2BlacklistContactsCnt").html(HlslRiskSocialNetwork.snOrder2BlacklistContactsCnt);
//					$("#snOrder2BlacklistRoutersCnt").html(HlslRiskSocialNetwork.snOrder2BlacklistRoutersCnt);
//					$("#snOrder2BlacklistRoutersPct").html(HlslRiskSocialNetwork.snOrder2BlacklistRoutersPct);
//				}
//			},
//			error: function(){
//					layer.msg("错误" , {icon: 2});//提示信息
//			},
//			complete: function(){ 
//			} 
//		});
//	}
//	function getRiskBlacklist(){
//		//buApplicationId='e9176076b18743be919bd87af618fa83';
//		buApplicationId=getQueryString("applicationId");
//		$.ajax({//黑名单
//			url :getCtxPath() + "/backend/hlsl/getRiskBlacklist.do?v="+ new Date().getTime(),
//			method: "post",
//			dataType:"json",
//			data: {
//				buApplicationId: buApplicationId
//			},
//			success: function(data){
//				//console.log(data);
//				if(data.code==200){
//					var riskBlack=data.list.data;
//					var idcardInBlacklist="";
//					var phoneInBlacklist="";
//					var inCourtBlacklist="";
//					var inP2pBlacklist="";
//					var inBankBlacklist="";
//					//console.log(data);
//					idcardInBlacklist=riskBlack.idcardInBlacklist==true?"是":"否";
//					phoneInBlacklist=riskBlack.phoneInBlacklist==true?"是":"否";
//					inCourtBlacklist=riskBlack.inCourtBlacklist==true?"是":"否";
//					inP2pBlacklist=riskBlack.inP2pBlacklist==true?"是":"否";
//					inBankBlacklist=riskBlack.inBankBlacklist==true?"是":"否";
//					$("#idcardInBlacklist").html(idcardInBlacklist);
//					$("#phoneInBlacklist").html(phoneInBlacklist);
//					$("#inCourtBlacklist").html(inCourtBlacklist);
//					$("#inP2pBlacklist").html(inP2pBlacklist);
//					$("#inBankBlacklist").html(inBankBlacklist);
//					$("#lastAppearIdcardInBlacklist").html(riskBlack.lastAppearIdcardInBlacklist);
//					$("#lastAppearPhoneInBlacklist").html(riskBlack.lastAppearPhoneInBlacklist);
//				}
//			},
//			error: function(){
//					layer.msg("错误" , {icon: 2});//提示信息
//			},
//			complete: function(){ 
//			} 
//		});
//	}
//	function getHistoryOrg(){
//		//var buApplicationId='e9176076b18743be919bd87af618fa83';
//		buApplicationId=getQueryString("applicationId");
//		$.ajax({//历史类型
//			url :getCtxPath() + "/backend/hlsl/getHistoryOrg.do?v="+ new Date().getTime(),
//			method: "post",
//			dataType:"json",
//			data: {
//				buApplicationId: buApplicationId
//			},
//			success: function(data){
//				if(data.code==200){
//					var historyOrg=data.list.data;
//					$("#onlineInstallmentCnt").html(historyOrg.onlineInstallmentCnt);
//					$("#offlineInstallmentCnt").html(historyOrg.offlineInstallmentCnt);
//					$("#creditCardRepaymentCnt").html(historyOrg.creditCardRepaymentCnt);
//					$("#paydayLoanCnt").html(historyOrg.paydayLoanCnt);
//					$("#onlineCashLoanCnt").html(historyOrg.onlineCashLoanCnt);
//					$("#offlineCashLoanCnt").html(historyOrg.offlineCashLoanCnt);
//					$("#othersCnt").html(historyOrg.othersCnt);
//				}
//			},
//			error: function(){
//					layer.msg("错误" , {icon: 2});//提示信息
//			},
//			complete: function(){ 
//			} 
//		});
//	}
//	function getHistorySearch(){
//		//var buApplicationId='e9176076b18743be919bd87af618fa83';
//		buApplicationId=getQueryString("applicationId");
//		$.ajax({//历史类型
//			url :getCtxPath() + "/backend/hlsl/getHistorySearch.do?v="+ new Date().getTime(),
//			method: "post",
//			dataType:"json",
//			data: {
//				buApplicationId: buApplicationId
//			},
//			success: function(data){
//				if(data.code==200){
//					var historySearch=data.list.data;
//					$("#searchCnt").html(historySearch.searchCnt);
//					$("#searchCntRecent7Days").html(historySearch.searchCntRecent7Days);
//					$("#searchCntRecent14Days").html(historySearch.searchCntRecent14Days);
//					$("#searchCntRecent30Days").html(historySearch.searchCntRecent30Days);
//					$("#searchCntRecent60Days").html(historySearch.searchCntRecent60Days);
//					$("#searchCntRecent90Days").html(historySearch.searchCntRecent90Days);
//					$("#searchCntRecent180Days").html(historySearch.searchCntRecent180Days);
//					$("#orgCnt").html(historySearch.orgCnt);
//					$("#orgCntRecent7Days").html(historySearch.orgCntRecent7Days);
//					$("#orgCntRecent14Days").html(historySearch.orgCntRecent14Days);
//					$("#orgCntRecent30Days").html(historySearch.orgCntRecent30Days);
//					$("#orgCntRecent60Days").html(historySearch.orgCntRecent60Days);
//					$("#orgCntRecent90Days").html(historySearch.orgCntRecent90Days);
//					$("#orgCntRecent180Days").html(historySearch.orgCntRecent180Days);
//				}
//			},
//			error: function(){
//					layer.msg("错误" , {icon: 2});//提示信息
//			},
//			complete: function(){ 
//			} 
//		});
//	}