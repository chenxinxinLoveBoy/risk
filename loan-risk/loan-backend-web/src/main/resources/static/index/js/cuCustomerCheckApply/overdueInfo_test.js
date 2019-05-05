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
	 	$.ajax({
		 		url:getCtxPath() + "/backend/cuCustomerCheckApply/getDataReportByMongo.do?v=" + new Date().getTime(),
				method: "post",
				dataType: "json",
				data: { 
					customerCheckApplyId : customerCheckApplyId,
					taskType : "12001"
				},
				success : function(data) {
					if(data.list.data==null)
				    {
				    	return ;
				    }
					$("#bankthread").hide();
					var blistResult = data.list.data.list.RESULT;
					var blistMessage = data.list.data.list.MESSAGE;
					var listHtml = "";
					$("#bmessage").html(blistMessage);
					if(blistResult == 2){
						return;
					}
					var blist = data.list.data.list.DETAIL;
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
				url:getCtxPath() + "/backend/cuCustomerCheckApply/getDataReportByMongo.do?v=" + new Date().getTime(),
				method: "post",
				dataType: "json",
				data: { 
					customerCheckApplyId :customerCheckApplyId,
					taskType: "12002",
				},
				success : function(data) {
					if(data.list.data==null)
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
					var blistResult = data.list.data.list.RESULT;
					var blistMessage = data.list.data.list.MESSAGE;
					var listHtml = "";
					$("#nlmessage").html(blistMessage);
					if(blistResult == 2){
						return;
					}
					var nllist = data.list.data.list.DETAIL;
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
