	layui.config({
				base: '../../js/' 
			});
	// 当前菜单名称
	var menuNames = "芝麻RiskCode配置";
	
	
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
			  info: function(value){
			      if(value.length < 1){
			        return '请填写信息项';
			      }
			  },
		  	  code: function(value){
			      if(value.length < 1){
			        return '请填写风险代码';
			      }
			  },
			  suggestType: function(value){
			      if(value.length < 1){
			        return '请填写风险类型';
			      }
			  },
			  remark: function(value){
			      if(value.length < 1){
			        return '请填写中文说明';
			      }
			  },
			  type: function(value){
			      if(value == -1){
			        return '请选择类型';
			      }
			  }

		  });
		  
		  
		  // 监听submit提交事件
		  form.on('submit(save)', function(data){
				var url = getCtxPath() + "/backend/zhiMaRiskCode/save.do";
				$.ajax({
					url: url,
					method: "post",
					data: {
						riskCodeId: data.field.riskCodeId,
						info: data.field.info, 
						code: data.field.code,
						suggestType: data.field.suggestType,
						remark: data.field.remark,
						type: data.field.type,
						menuNames: menuNames, // 菜单名称
						functionNames: (data.field.riskCodeId.length > 0 ? "修改" : "新增") // 按钮功能名称
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
			var riskCodeId = getQueryString("riskCodeId");
			
			// 回显填充数据
			if(riskCodeId != null && riskCodeId != '' && riskCodeId != undefined){
				getfind(riskCodeId, form);// 回显
			} 
		  	
		  
	  });
	});
//初始化加载  end!	   
	


// 回显
function getfind(riskCodeId, form){
	if(riskCodeId != null && riskCodeId != '' && riskCodeId != undefined){
		$.ajax({
			url : getCtxPath() + "/backend/zhiMaRiskCode/find.do",
			method: "post",
			dataType:"json",
			data: {
				riskCodeId: riskCodeId
			},
			success: function(data){
				 if(data.data.data != null){
					 $("#riskCodeId").val(data.data.data.riskCodeId);
					 // 信息项
					 $("#info").val(data.data.data.info);
					 // 风险代码
					 $("#code").val(data.data.data.code);
					 //风险类型
					 $("#suggestType").val(data.data.data.suggestType);
					 //中文说明
					 $("#remark").val(data.data.data.remark);
					 // 类型
					 $("#type").val(data.data.data.type);
				 }
				 
			},
			error: function(){
				layer.msg("网络请求异常,请常识重新登录" , {icon: 2});//提示信息
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



 

 
