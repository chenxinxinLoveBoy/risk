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
			  var applicationId = getQueryString("applicationId");
		  		$.ajax({
					url :getCtxPath() + "/backend/approve/findBuIcePerson.do",
					method: "post",
					dataType:"json",
					data: {
						applicationId:applicationId
					},
					success: function(data){
						console.log(data.list.data);
						var table = $("#lxr").html();
						var html = table + "<tr style='width: 100px;'>" +
						"<td align='center'><strong>联系次数</strong></td><td align='center'><strong>号码</strong></td><td align='center'><strong>机构名称</strong></td></tr>";
						if(data.list.data != null){
							$.each(data.list.data, function(k, v){
								html += "<tr>" +
								"<td align='center'>" + v.contactNum + "</td>" +
								"<td align='center'>" + v.contactMobile + "</td>" +
								"<td align='center'>" + v.orgName + "</td>" +
								"</tr>";
							});
						}
						$("#bcsbd").html(html);
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
