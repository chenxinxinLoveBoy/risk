	layui.config({
		base: '../../js/' 
	});

	layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer,
			layer = layui.layer,
			form = layui.form(), element = layui.element();
	});

	function findMessageInfo(id,type){
		$.ajax({
			url :getCtxPath() + "/backend/mongodb/findExceptionCustomerMessageInfo.do",
			method: "post",
			dataType:"text",
			data: {
				id:id,
				type:type
			},
			success: function(data){
				$("#content"+id).html(data);
				var contentText = $("#messageInfo"+id).css("display");
				if (contentText == "none") {
					$("#messageInfo"+id).removeAttr("style","display:block;");
				} else {
					$("#messageInfo"+id).attr("style","display:none;");
				}
			},
			error: function(){
			} 
		});
	}

	function agSaveMessage(id, type){
		$.ajax({
			url :getCtxPath() + "/backend/mongodb/saveCustomerMessageInfo.do",
			method: "post",
			dataType:"json",
			data: {
				id:id,
				type:type
			},
			success: function(data){
				if (data != null && data.code == 200) {
					layer.msg("重发成功！" , {icon: 1});//成功提示信息
				}
				findMessage(type);
			},
			error: function(){} 
		});
	};

	function findMessage(type) {
		$.ajax({
			url :getCtxPath() + "/backend/mongodb/findExceptionCustomerMessage.do",
			method: "post",
			dataType:"json",
			data: {
				type:type
			},
			success: function(data){
				var list = data.list.data;
				var table = $("#customerMessage").html();
				var html = "";
				if(list != null){
					$.each(list, function(k, v){
						html += "<tr onclick=\"findMessageInfo('" + v.id + "','" + type + "');\"><td >" + v.id + "</td><td>" + v.createTime + "</td><td>" + 
						"<a href='javascript:;' class='layui-btn layui-btn-small' onclick=\"agSaveMessage('" + v.id + "','" + type + "')\"> 重发 </a></td></tr>"
						+ "<tr id='messageInfo" + v.id + "' style='display:none;'><td colspan='3' id='content" + v.id + "'> </td></tr>";
					});
				};
				$("#content").html(html);
			}
		});
	};

	$(function() {
		findMessage("app");
	});