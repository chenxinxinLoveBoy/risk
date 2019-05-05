layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
				function() {
					var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
					layer = layui.layer, // 获取当前窗口的layer对象
					form = layui.form(), element = layui.element();
				});

function getmysqldata() {
	 var applicationId = getQueryString("applicationId");//获取页面传过来的平台用户编号
	 var platformCustomerId = getQueryString("id");//获取页面传过来的平台用户编号
	$.ajax({
				url : getCtxPath() + "/backend/yqxq/findOverdue.do",
				method : "post",
				dataType : "json",
				data : {
					platformCustomerId : platformCustomerId,
					applicationId : applicationId
				},
				success : function(data) {
					$("#bankthread").show();
					$("#netthread").show();
					//银行逾期名单
					var bankMap = data.list.data.bankMap;
					//网贷逾期名单
					var netLoanMap = data.list.data.netLoanMap;
					if (data.list.data != null) {
						if (bankMap != null) {
							var overdueList = bankMap.overdueList;
							$("#boverdueListId").html(overdueList.overdueListId);
							$("#bapplicationId").html(overdueList.applicationId);
							$("#bname").html(overdueList.name);
							$("#bmobile").html(overdueList.mobile);
							$("#bidCard").html(overdueList.idCard);
							$("#bplatformType").html(overdueList.platformType);
							$("#bmessage").html(overdueList.message);
						}
						if (netLoanMap != null) {
							var overdueList = netLoanMap.overdueList;
							$("#nloverdueListId").html(overdueList.overdueListId);
							$("#nlapplicationId").html(overdueList.applicationId);
							$("#nlname").html(overdueList.name);
							$("#nlmobile").html(overdueList.mobile);
							$("#nlidCard").html(overdueList.idCard);
							$("#nlplatformType").html(overdueList.platformType);
							$("#nlmessage").html(overdueList.message);
						}
					}
					var blist = bankMap.list;
					var nllist = netLoanMap.list;
					var listHtml = "";
					if(blist != null){
						$.each(blist, function(k, v){
							if (v!=null) {
								listHtml += "<table class=\"layui-table\"><colgroup><col width=\"200\"><col width=\"200\"><col></colgroup>" +
								"<tr><td><strong>逾期总次数</strong></td><td>" + v.cntList.overdueCnt + "</td>" +
								"<td><strong>逾期总金额</strong></td><td>" + v.cntList.overdueAmtCnt + "</td></tr><tr><td colspan=\"4\"></td></tr>";
								var bInfolist = v.overdueListInfoList;
								var html = "";
								if (bInfolist!=null) {
									listHtml += "<tr><td><strong>开始逾期时间<strong/></td><td><strong>逾期天数</strong></td><td><strong>逾期金额</strong></td><td><strong>平台类型</strong></td></tr>";
									$.each(bInfolist, function(k, s){
										html += "<tr><td>"+s.overdueStartTime+"</td><td>"+s.overdueDay+"</td><td>"+s.overdueAmt+"</td><td>"+s.PType+"</td></tr>";
									});
								}
								listHtml = listHtml + html + "</table>";
							}
						});
					};
					var bankyq = $("#bankyq").html(listHtml);
					listHtml = "";
					if(nllist != null){
						$.each(nllist, function(k, v){
							if (v!=null) {
								listHtml += "<table class=\"layui-table\"><colgroup><col width=\"200\"><col width=\"200\"><col></colgroup>" +
								"<tr><td><strong>逾期总次数</strong></td><td>" + v.cntList.overdueCnt + "</td>" +
								"<td><strong>逾期总金额</strong></td><td>" + v.cntList.overdueAmtCnt + "</td></tr><tr><td colspan=\"4\"></td></tr>";
								
								var bInfolist = v.overdueListInfoList;
								var html = "";
								if (bInfolist!=null) {
									listHtml += "<tr><td><strong>开始逾期时间<strong/></td><td><strong>逾期天数</strong></td><td><strong>逾期金额</strong></td><td><strong>平台类型</strong></td></tr>";
									$.each(bInfolist, function(k, s){
										html += "<tr><td>"+s.overdueStartTime+"</td><td>"+s.overdueDay+"</td><td>"+s.overdueAmt+"</td><td>"+s.PType+"</td></tr>";
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

};
