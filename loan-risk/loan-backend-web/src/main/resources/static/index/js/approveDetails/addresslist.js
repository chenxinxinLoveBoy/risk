	layui.config({
				base: '../../js/' 
			});

	var map;
	// 回显
	$(function(){

		//我们强烈推荐你在代码最外层把需要用到的模块先加载
		layui.use(['layer', 'form', 'element'], function(){
			 var layer = layui.layer
			  ,form = layui.form()
			  ,element = layui.element();
			  form.render(); //刷新全部
			  var customerId = getQueryString("customerId");
			  var appName = getQueryString("appName");
		  		$.ajax({
					url :getCtxPath() + "/backend/approve/cognateAnalysis.do",
					method: "post",
					dataType:"json",
					data: {
						customerId:customerId,
						appName:appName
					},
					success: function(data){
						map = data.list.data;
						if (map!=null) {
							$("#phoneSize").html("<u>" + map.phoneSize + "</u>");
							$("#errorPhoneSize").html("<u>" + map.errorPhoneSize + "</u>");
							$("#exceptionPhoneSize").html("<u>" + map.exceptionPhoneSize + "</u>");
							table("1");
						}
					},
					error: function(){  
 						//layer.msg("错误" , {icon: 2});//提示信息
					},
					complete: function(){
						form.render(); //刷新全部
					} 
				});
	   });

		$("#phoneSize").on('click', function() {
			table("1");
		});
		$("#errorPhoneSize").on('click', function() {
			table("2");
		});
		$("#exceptionPhoneSize").on('click', function() {
			table("3");
		});
		
	});

	function table(type){
		var list;
		if (type == "1") {
			list = map.nelist;
		} else if (type == "2") {
			list = map.erlist;
		} else if (type == "3") {
			list = map.exlist;
		}
		var table = $("#lxr").html();
		var html = table + "<tr style='width: 100px;'>" +
		"<td align='center'><strong>姓名</strong></td><td align='center'><strong>号码</strong></td></tr>";
		if(list != null){
			$.each(list, function(k, v){
				html += "<tr>" +
				"<td align='center'>" + v.contactName + "</td>" +
				"<td align='center'>" + v.contactPhone + "</td>" +
				"</tr>";
			});
		}
		$("#txl").html(html);
	}

// 关闭
function closeIfrname(){
	var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(frameindex);
}
