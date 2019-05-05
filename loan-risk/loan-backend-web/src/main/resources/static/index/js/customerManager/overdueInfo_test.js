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
	var applicationId = getQueryString("applicationId");//获取页面传过来的平台用户编号
	var platformId = getQueryString("id");
	 	$.ajax({
		 		url:getCtxPath() + "/backend/mongo/getMongoDisplay.do?v=" + new Date().getTime(),
				method: "post",
				dataType: "json",
				data: { 
					applicationID:applicationId,
					taskType: "12001",
					platformId:platformId
				},
				success : function(data) {
					if(data.url==null)
				    {
				    	return ;
				    }
					//银行逾期名单
//					var bankMap = data.list.data.bankMap;
//					//网贷逾期名单
//					if (data.list.data != null) {
//						if (bankMap != null) {
//							var overdueList = bankMap.overdueList;
//							$("#boverdueListId").html(overdueList.overdueListId);
//							$("#bapplicationId").html(overdueList.applicationId);
//							$("#bname").html(overdueList.name);
//							$("#bmobile").html(overdueList.mobile);
//							$("#bidCard").html(overdueList.idCard);
//							$("#bplatformType").html(overdueList.platformType);
//							$("#bmessage").html(overdueList.message);
//						}
//					}
					$("#bankthread").hide();
					var blistResult = data.list.data.RESULT;
					var blistMessage = data.list.data.MESSAGE;
					var listHtml = "";
					$("#bmessage").html(blistMessage);
					if(blistResult == 2){
						return;
					}
					var blist = data.list.data.DETAIL;
					if(blist != undefined && typeof(blist) != 'undefined'){
						var html = "";
						$.each(blist, function(k, v){
							if (v!=null) {
									listHtml += "<table class=\"layui-table\"><colgroup><col width=\"200\"><col width=\"200\"><col></colgroup>" +
									"<tr><td><strong>逾期总次数</strong></td><td>" + v.BankOverdueCnt.overdueCnt + "</td>" +
									"<td><strong>逾期总金额</strong></td><td>" +  v.BankOverdueCnt.overdueAmtCnt + "</td></tr><tr><td colspan=\"4\"></td></tr>";
									if (v.BankOverdueInfo!=null) {
										$.each(v.BankOverdueInfo, function(t, s){
										listHtml += "<tr><td><strong>开始逾期时间<strong/></td><td><strong>逾期天数</strong></td><td><strong>逾期金额</strong></td><td><strong>平台类型</strong></td></tr>";
										html += "<tr><td>"+s.overdueStartTime+"</td><td>"+s.overdueDay+"</td><td>"+s.overdueAmt+"</td><td>"+s.p_type+"</td></tr>";
										});
									}
							};
						});						
						listHtml = listHtml + html + "</table>";
					};
					var bankyq =$("#bankyq").html(listHtml);
				},
				error : function() {
					alert("获取用户信息失败,请尝试重新获取！");
				}
			});
	
	
			$.ajax({
				url:getCtxPath() + "/backend/mongo/getMongoDisplay.do?v=" + new Date().getTime(),
				method: "post",
				dataType: "json",
				data: { 
					applicationID:applicationId,
					taskType: "12002",
					platformId:platformId
				},
				success : function(data) {
					if(data.url==null)
				    {
				    	return ;
				    }
					//银行逾期名单
//					var bankMap = data.list.data.bankMap;
//					//网贷逾期名单
//					if (netLoanMap != null) {
//						var overdueList = netLoanMap.overdueList;
//						$("#nloverdueListId").html(overdueList.overdueListId);
//						$("#nlapplicationId").html(overdueList.applicationId);
//						$("#nlname").html(overdueList.name);
//						$("#nlmobile").html(overdueList.mobile);
//						$("#nlidCard").html(overdueList.idCard);
//						$("#nlplatformType").html(overdueList.platformType);
//						$("#nlmessage").html(overdueList.message);
//					}
					$("#netthread").hide();
					var blistResult = data.list.data.RESULT;
					var blistMessage = data.list.data.MESSAGE;
					var listHtml = "";
					$("#nlmessage").html(blistMessage);
					if(blistResult == 2){
						return;
					}
					var nllist = data.list.data.DETAIL;
					if(nllist != undefined && typeof(nllist) != 'undefined'){
						var html = "";
						$.each(nllist, function(k, v){
							if (v!=null) {
								listHtml += "<table class=\"layui-table\"><colgroup><col width=\"200\"><col width=\"200\"><col></colgroup>" +
								"<tr><td><strong>逾期总次数</strong></td><td>" + v.NetOverdueCnt.overdueCnt + "</td>" +
								"<td><strong>逾期总金额</strong></td><td>" + v.NetOverdueCnt.overdueAmtCnt + "</td></tr><tr><td colspan=\"4\"></td></tr>";
								if (v.NetOverdueInfo!=null) {
									listHtml += "<tr><td><strong>开始逾期时间<strong/></td><td><strong>逾期天数</strong></td><td><strong>逾期金额</strong></td><td><strong>平台类型</strong></td></tr>";
									$.each(v.NetOverdueInfo, function(k, s){
										html += "<tr><td>"+s.overdueStartTime+"</td><td>"+s.overdueDay+"</td><td>"+s.overdueAmt+"</td><td>"+s.p_type+"</td></tr>";
									});
								}
								listHtml = listHtml + html + "</table>";
							}
						});
					};
					var netLoanyq = $("#netLoanyq").html(listHtml);
				},
				error : function() {
					alert("获取用户信息失败,请尝试重新获取！");
				}
			});
})
