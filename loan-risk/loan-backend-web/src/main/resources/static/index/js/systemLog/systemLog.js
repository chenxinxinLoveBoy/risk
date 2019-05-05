	layui.config({
		base: '../../js/' 
	});
// 用户id
var id = getQueryString("id");	


// 回显
$(function(){
	
	//我们强烈推荐你在代码最外层把需要用到的模块先加载
	layui.use(['tree','upload','layer', 'form', 'element'], function(){
		  var layer = layui.layer
		  ,form = layui.form()
		  ,element = layui.element();
		  form.render(); //刷新全部 
		var find = getQueryString("find");
		var logId = getQueryString("logId");  
	 
		if(logId !='' && logId != undefined){  
			$.ajax({
				url : getCtxPath() + "/backend/systemLog/getRemark.do?v=" + new Date().getTime(),
				method: "post",
				dataType:"json",
				data: {
					logId: logId 
				},
				success: function(data){
					$("#remark").val(data.list.systemLogBoInfo.remark); 
					 
				},
				error: function(){
					 layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
				}
			});	
		}   
	}); 
	
});  

function closeIfrname(){ 
	var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(frameindex);
}

 
