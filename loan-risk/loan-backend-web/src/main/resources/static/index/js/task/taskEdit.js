layui.config({
	base: '../../js/' 
});
	
//初始化加载 start
$(function(){
	
	//我们强烈推荐你在代码最外层把需要用到的模块先加载
	layui.use(['layer', 'form', 'element'], function(){
		  var layer = layui.layer
		  ,form = layui.form()
		  ,element = layui.element();
		  form.render(); //刷新全部
		  
		  
		 //自定义验证规则
		  form.verify({
			  jobName: function(value) {
			      if(value.length < 1 || value.trim() == ''){
			        return 'jobName不能为空';
			      }
		      },
		      jobGroup: function(value) {
		    	  if(value.length < 1 || value.trim() == ''){
		    		  return 'jobGroup不能为空';
		    	  }
		      },
		      cronExpression: function(value) {
		    	  if(value.length < 1 || value.trim() == ''){
		    		  return 'cronExpression不能为空';
		    	  }
		      },
		      beanClass: function(value) {
		    	  if(value.length < 1 || value.trim() == ''){
		    		  return 'beanClass不能为空';
		    	  }
		      },
		      springId: function(value) {
		    	  if(springId.length < 1 || value.trim() == ''){
		    		  return 'springId不能为空';
		    	  }
		      },
		      methodName: function(value) {
		    	  if(value.length < 1 || value.trim() == ''){
		    		  return 'methodName不能为空';
		    	  }
		      }
		  });
		  
		  // 监听submit提交事件	start
		  form.on('submit(save)', function(data) {
			  	var _data = {
					httpurl : data.field.artifactId +"/quartzTask/addTask?" + $("#taskForm").serialize(),
					artifactId: data.field.artifactId //项目名必传参数 根据此项目名 获取请求http前缀地址
				};
			  
				$.ajax({
					url : getCtxPath() + "/quartzTask/sendHttp",
					method : "post",
					data: _data,
					success:function(obj){
						if (obj.code == '200') {
							 var data = obj.list.data;
							 if (data.code != '200') {
								 layer.msg(data.message , {icon: 2});
							 } else {
								 parent.getList();
								 layer.msg("新增成功！", {icon: 1});
								 setTimeout(function(){
//									 layer.msg("新增成功！", {icon: 2});
								 	 closeIfrname();
						         }, 1000);
							 }
						} else {
							layer.msg("新增失败，请稍后重试！" , {icon: 2});
						}
						
						console.log("--obj");
						console.log(obj);
						return ;
					},
					error: function(){
						layer.msg("网络请求异常，稍后重试！" , {icon: 2});//失败提示信息
					}
				});
				return false;// 阻止跳转页面，只有保存成功时才跳转
		  });
   });
});

function closeIfrname(){
	var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(frameindex);
}



