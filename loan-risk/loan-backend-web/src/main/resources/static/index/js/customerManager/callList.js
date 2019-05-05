layui.config({
	base: '../../js/' 
});
	layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
			layer = layui.layer, // 获取当前窗口的layer对象
			form = layui.form(), element = layui.element();
		});
	

		// 初始化
		$(function() {
			 var customerId = getQueryString("id");
			var appName = getQueryString("appName");
			$.ajax({
				url : getCtxPath() + "/backend/mongo/getMongoCallDisplay.do?v="
						+ new Date().getTime(),
				method : "post",
				dataType : "json",
				data : {
					customerId : customerId,
					appName : appName
				},
				success : function(data) {
					var callsDatails = data.list.data;
					if (callsDatails != null) {
						showCalls(callsDatails);
					}
				}
			});
		});
		

	function showCalls(callsDatails){
		$("#content").empty();
		$.each(callsDatails, function(k, v){
			var html='';
			html+='<tr> ';
			html+='<td>'+v.customerId+'</td>';
			if(v.callName!=null){
				html+='<td>'+v.callName+'</td>';
			}else{
				html+='<td></td>';
			}
			
			html+='<td>'+v.phone+'</td>';
			html+='<td>'+phoneType2Value(v.callType)+'</td>';
			html+='<td>'+v.useTime+'</td>';
			html+='<td>'+v.callTime+'</td>';
			html+='</tr>';
			$("#content").append(html);
		});
	}
	
	function phoneType2Value(code){
		var value="-";
		if(code=="1"){
			value="主叫"
		}else if(code=="2"){
			value="被叫"
		}else if(code="3"){
			value="未接"
		}
		return value;
	}
	
	