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
	//客户app应用列表查询
	$.ajax({
		url :getCtxPath() + "/backend/approve/appInfo.do",
		method: "post",
		dataType:"json",
		data: {
			customerId:customerId,
			appName:appName
		},
		success: function(data){
			var list = data.list.data;
			var table = $("#appyylb").html();
			var html = table + "<tr style='width: 100px;'>" +
			"<td align='center'><strong>应用程序名称</strong></td><td align='center'><strong>应用程序包名</strong></td><td align='center'><strong>是否是系统应用</strong></td></tr>";
			if(list != null){
				$.each(list, function(k, v){
					var isSystemApp = "";
					if (v.isSystemApp == 1) {
						isSystemApp = "是";
					} else {
						isSystemApp = "否";
					}
					html += "<tr>" +
					"<td align='center'>" + v.launcherName + "</td>" +
					"<td align='center'>" + v.packageName + "</td>" +
					"<td align='center'>" + isSystemApp + "</td>" +
					"</tr>";
				});
			}
			$("#appyylb").html(html);
		},
		error: function(){
			layer.msg("错误" , {icon: 2});//提示信息
		} 
	});
});