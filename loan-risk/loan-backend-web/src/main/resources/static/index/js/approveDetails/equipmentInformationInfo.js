	layui.config({
				base: '../../js/' 
			});
 
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
//					url :getCtxPath() + "/backend/approve/findEquipmentInformationInfo.do",
		  			url :getCtxPath() + "/backend/summary/summaryEquipment.do",
					method: "post",
					dataType:"json",
					data: {
						customerId : customerId,
						appName : appName
					},
					success: function(data){
						if (data.code == 200) {
							var html = "<tr style='width: 100px;'>" +
							"<td align='center'><strong>设备号</strong></td><td align='center'><strong>客户编号</strong></td></tr>";
							var json = data.list.data;
							if(json != null && json.device_id_count != 0){
								$.each(json.list_device_customer, function(k, v){
									html += "<tr>" +
									"<td align='center'>" + v.deviceId + "</td>" +
									"<td align='center'>" + customerId + "</td>" +
									"</tr>";
								});
							}
							$("#sbgl").html(html);
						}
					},
					error: function(){  
 						layer.msg("错误" , {icon: 2});//提示信息
					},
					complete: function(){
						form.render(); //刷新全部
					} 
				});
	   });
	});

// 关闭
function closeIfrname(){
	var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(frameindex);
}
