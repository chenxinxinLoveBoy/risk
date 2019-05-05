layui.config({
	base: '../../js/' 
});
	layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
			layer = layui.layer, // 获取当前窗口的layer对象
			form = layui.form(), element = layui.element();
		});
	
function convertToNull(value)
{
		if(value==null||typeof(value)=="undefined") 
		{
			return "";
		}
		else
		{
			return value;
		}
}	
	// 初始化
	$(function(){
		var customerId = getQueryString("id");
		var appName=getQueryString("appName");
  		$.ajax({
			url :getCtxPath() + "/backend/mongo/getMongoMessageDisplay.do?v=" + new Date().getTime(),
			method: "post",
			dataType:"json",
			data: {
				customerId:customerId,
				appName:appName
			},
			success: function(data){
				if(data.list!==undefined)
				{
					map = data.list.data;
					if (map!=null) {
						var list = map.list;
						var table = $("#txl").html();
						var html = table + "<tr style='width: 100px;'><th>短信内容 </th><th>手机</th>" +
						"<th>发送时间</th><th>客户编号</th><th>app平台</th></tr>";
						if(list != null){
							$.each(list, function(k, v){
								var appName="";
								if(v.appName=="1")appName="洪澄";
								if(v.appName=="2")appName="速贷";
								html += "<tr><td>" + convertToNull(v.context) + "</td><td>" + convertToNull(v.phone) + "</td><td>" + convertToNull(v.sendTime) + "</td>" +
								"<td>" + convertToNull(v.customerId) + "</td><td>" + convertToNull(appName) + "</td></tr>";
							});
							$("#txl").html(html);
						}
						else
						{
							var html= $("#txl").html() + "<tr style='width: 100px;'><th>短信内容 </th><th>手机</th>" +
							"<th>发送时间</th><th>客户编号</th><th>app平台</th></tr>"
							+ "<tr><td></td><td></td><td></td><td></td><td></td></tr>";
							$("#txl").html(html);
						}
						
					}
				}
				else
				{
					var html= $("#txl").html() + "<tr style='width: 100px;'><th>短信内容 </th><th>手机</th>" +
					"<th>发送时间</th><th>客户编号</th><th>app平台</th></tr>"
					+ "<tr><td></td><td></td><td></td><td></td><td></td></tr>";
					$("#txl").html(html);
				}
			}
		});
	});