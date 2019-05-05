	layui.config({
				base: '../../js/' 
			});
	// 当前菜单名称
	var menuNames = "芝麻信用Code配置";
	
	
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
		  	  code: function(value){
			      if(value.length < 1){
			        return '请填写信用代码';
			      }
			  },
			  explain: function(value){
			      if(value.length < 1){
			        return '请填写中文说明';
			      }
			  }
		  });
		  
		  
		  // 监听submit提交事件
		  form.on('submit(save)', function(data){
			  var find = getQueryString("find");
			  var flag = getQueryString("flag");
				var url = getCtxPath() + "/backend/zhiMaCode/save.do";
				$.ajax({
					url: url,
					method: "post",
					data: {
						udc_code: data.field.code,
						udc_name: data.field.explain,
						remarks: data.field.remark,
						u_code: $("#hidden").val(),
						find:flag,
						menuNames: menuNames, // 菜单名称
						functionNames: (data.field.code.length > 0 ? "修改" : "新增") // 按钮功能名称
					},
					success:function(obj){
						if(obj.code == "2001" || obj.code == "4001"){
							layer.msg(obj.message , {icon: 1});//成功提示信息
//							$("#save_bt").attr("style", "display:none");//按钮隐藏
//							$("#off_bt").attr("style", "display:none");//按钮隐藏
							closeIfrname(); // 关闭窗口
							window.parent.getList(); // 刷新父类list页面
						}else{
							layer.msg(obj.message , {icon: 2});//成功提示信息
						}
					},
					error: function(){
						layer.msg("网络请求异常，请尝试重新登陆！" , {icon: 2});//失败提示信息
					}
					
				});
				return false;// 阻止跳转页面，只有保存成功时才跳转
		  });
		  
		  
		  	// 预览
			var find = getQueryString("find");
			if(find == 1){  // 预览，将”保存“，”预览“2个按钮取消
				$("#save_bt").attr("style", "display:none");//保存按钮
				$("#off_bt").attr("style", "display:none");// 取消按钮
			}
			
			// 回显
			var udc_code = getQueryString("udc_code");
			// 回显填充数据
			if(udc_code != null && udc_code != '' && udc_code != undefined){	
				getfind(udc_code, form);// 回显
			} 
		  	
		  
	  });
	});
//初始化加载  end!	   
	


// 回显
function getfind(udc_id, form){
	if(udc_id != null && udc_id != '' && udc_id != undefined){
		$.ajax({
			url : getCtxPath() + "/backend/zhiMaCode/find.do",
			method: "post",
			dataType:"json",
			data: {
				udc_code: udc_id
			},
			success: function(data){
				 if(data.data.data != null){
					 // 风险代码
					 $("#code").val(data.data.data.udc_code);
					 $("#hidden").val(data.data.data.udc_code);
					 $("#explain").val(data.data.data.udc_name);
					 //中文说明
					 $("#remark").val(data.data.data.remarks);
				 }
			},
			error: function(){
				layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
			},
			complete: function(){
				form.render(); //刷新全部
			} 
		});	
	}
}



// 关闭
function closeIfrname(){
//	layer.closeAll();
	var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(frameindex);
}



 

 
