	layui.config({
				base: '../../js/' 
			});
 
	$(function(){
		
		//我们强烈推荐你在代码最外层把需要用到的模块先加载
		layui.use(['layer', 'form', 'element'], function(){
			 var layer = layui.layer
			  ,form = layui.form()
			  ,element = layui.element(); 
			  
			  // 监听radio单选
			  form.on('radio(radio_filter)', function(data){
				  form.render(); //重新渲染
			  }); 
			// 监听submit提交事件
			  form.on('submit(save)', function(data){
 				  // 保存
				  save(data);
				  return false;// 阻止跳转页面，只有保存成功时才跳转
			  });
			   
				var banControlId = getQueryString("banControlId");
				if (banControlId == '' || banControlId === undefined) 
					return;
				
					$("#banCode").attr("disabled","disabled");
					$("#ruleName").attr("disabled","disabled");
					$("#creditType").attr("disabled","disabled");
					$("#ruleDetail").attr("disabled","disabled");
					$("#ruleComparisonType").attr("disabled","disabled");
					$("#validateRule").attr("disabled","disabled");
					$("#version").attr("disabled","disabled");  
					$("#createTime").attr("disabled","disabled");  
					$("#createMan").attr("disabled","disabled");  
					$("#createName").attr("disabled","disabled");  
					$("#modifyTime").attr("disabled","disabled");  
					$("#modifyMan").attr("disabled","disabled");  
					$("#modifyName").attr("disabled","disabled");  
					$("#remark").attr("disabled","disabled");
					$("#ifRefuse").attr("disabled","disabled");  
					$("#ifRefuse2").attr("disabled","disabled");  
					$("#state").attr("disabled","disabled"); 
					$("#state2").attr("disabled","disabled"); 
					$("#validateRule").attr("disabled","disabled");  
					$("#version").attr("disabled","disabled");  
					$("#createTime").attr("disabled","disabled");  
					$("#createMan").attr("disabled","disabled");  
					$("#createName").attr("disabled","disabled");  
					$("#modifyTime").attr("disabled","disabled");  
					$("#modifyMan").attr("disabled","disabled");  
					$("#modifyName").attr("disabled","disabled");  
					$("#remark").attr("disabled","disabled");   
					
		  		$.ajax({
					url : getCtxPath() + "/backend/scBanControl/getEntityById.do?v=" + new Date().getTime(),
					method: "post",
					dataType:"json",
					data: {
						banControlId: banControlId
					},
					success: function(data){ 
						var ifRefuse =data.data.scBanControlObject.ifRefuse;
						if(ifRefuse == 0){ 
 							$("input[name='ifRefuse']").eq(0).attr("checked","checked");
						}else{
							$("input[name='ifRefuse']").eq(1).attr("checked","checked");
 						}
						 
						var st = data.data.scBanControlObject.state;
						 
						if(st == 01){ 
 							$("input[name='state']").eq(0).attr("checked","checked");
						}else{
							$("input[name='state']").eq(1).attr("checked","checked");
 						}
						$("#banCode").val(data.data.scBanControlObject.banCode);
						$("#ruleName").val(data.data.scBanControlObject.ruleName);
						$("#creditType").val(data.data.scBanControlObject.creditType);
						$("#ruleDetail").val(data.data.scBanControlObject.ruleDetail); 
						$("#ruleComparisonType").val(data.data.scBanControlObject.ruleComparisonType); 
						$("#validateRule").val(data.data.scBanControlObject.validateRule);  
						$("#version").val(data.data.scBanControlObject.version);  
						$("#createTime").val(data.data.scBanControlObject.createTime.substring(0,19) );
						$("#createMan").val(data.data.scBanControlObject.createMan);
						$("#createName").val(data.data.scBanControlObject.createName);
						$("#modifyTime").val(data.data.scBanControlObject.modifyTime.substring(0,19));
						$("#modifyMan").val(data.data.scBanControlObject.modifyMan);
						$("#modifyName").val(data.data.scBanControlObject.modifyName);
						$("#remark").val(data.data.scBanControlObject.remark); 
						 
					},
					error: function(){  
						layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
					},
					complete: function(){
						form.render(); //刷新全部
					} 
				});	
	   });
		 	
	
	});
 