layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer,
			layer = layui.layer,
			form = layui.form(), element = layui.element();
		});

$(function() {
	var customerId = getQueryString("customerId");
	 var appName = getQueryString("appName");
	//短信信息汇总
	$.ajax({
		url :getCtxPath() + "/backend/summary/summaryFewMessage.do?v=" + new Date().getTime(),
		method: "post",
		dataType:"json",
		data: {
			customerId:customerId,
			appName:appName
		},
		success: function(data){
			if (data.code == 200) {
				var json = data.list.data;
				$("#count").html(json.count);
				var table = $("#dxSummary").html();
				var html = table + "<tr style='width: 100px;'>" +
				"<td align='center'><strong>短信分类</strong></td><td align='center'><strong>总数</strong></td></tr>";
				html += "<tr><td align='center'>催收</td><td align='center'>" + json.cuishou + "</td></tr>";
				html += "<tr><td align='center'>逾期</td><td align='center'>" + json.yuqi + "</td></tr>";
				html += "<tr><td align='center'>还款</td><td align='center'>" + json.huankuan + "</td></tr>";
				html += "<tr><td align='center'>欠款</td><td align='center'>" + json.qiankuan + "</td></tr>";
				html += "<tr><td align='center'>还钱</td><td align='center'>" + json.huanqian + "</td></tr>";
				html += "<tr><td align='center'>欠费</td><td align='center'>" + json.qianfei + "</td></tr>";
			}
			$("#dxSummary").html(html);
		},
		error: function(){
//			layer.msg("错误" , {icon: 2});//提示信息
		} 
	});
});