	layui.config({
		base: '../../js/' 
	});
 

// 回显
$(function(){
	
	//我们强烈推荐你在代码最外层把需要用到的模块先加载
	layui.use(['tree','upload','layer', 'form', 'element'], function(){
		  var layer = layui.layer
		  ,form = layui.form()
		  ,element = layui.element();
		  form.render(); //刷新全部 
		var info = getQueryString("info");
		$.ajax({
			url : hostIp + "/checkCustomer/findInfo.do?v=" + new Date().getTime(),
			method : 'post',
			dataType : 'json',
			data: {
				info: info// 传到后台的参数，子窗口的form表单
			},
			success: function(data){
				if(data.code == 200){
					$("#info").val(data.data.data); 
				}else{
					$("#info").val(data.message); 
				}
			},
			error: function(){
				 layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
			}
			
		});
		
	}); 
	
});  

function closeIfrname(){ 
	var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(frameindex);
}

 
