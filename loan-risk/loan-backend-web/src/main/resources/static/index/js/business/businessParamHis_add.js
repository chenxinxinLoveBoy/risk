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
			  
			  // 监听radio单选
			  form.on('radio(radio_filter1)', function(data){
				  form.render(); //重新渲染
			  }); 
			  
			  // 监听submit提交事件
			  form.on('submit(save)', function(data){
 				  // 保存
				  save(data);
				  return false;// 阻止跳转页面，只有保存成功时才跳转
			  });
			  
				var id = getQueryString("id");
				//id不存在，不需要请求初始化，新增页面使用
				if(id === undefined || id === ""){
					return false;
				}
		  		$.ajax({
					url :getCtxPath() + "/backend/sysParamHis/getEntityById.do",
					method: "post",
					dataType:"json",
					data: {
						paramHisId: id
					},
					success: function(data){
						var statue =data.data.sysParamHisObject.statue//回显单选框
		 				if(statue=="01"){
 							$("#level_id1").attr("checked", "checked");
						}else{
							$("#level_id2").attr("checked", "checked");
 		 				}
						$("#sysParamId").val(data.data.sysParamHisObject.sysParamId); 
						$("#paramValue").val(data.data.sysParamHisObject.paramValue); 	
						$("#paramName").val(data.data.sysParamHisObject.paramName);  
						$("#paramValueOne").val(data.data.sysParamHisObject.paramValueOne); 
						$("#paramNameOne").val(data.data.sysParamHisObject.paramNameOne); 
						$("#paramValueTwo").val(data.data.sysParamHisObject.paramValueTwo);  
						$("#paramNameTwo").val(data.data.sysParamHisObject.paramNameTwo); 
						$("#paramValueThree").val(data.data.sysParamHisObject.paramValueThree); 	
						$("#paramNameThree").val(data.data.sysParamHisObject.paramNameThree);  
						$("#paramValueFour").val(data.data.sysParamHisObject.paramValueFour); 
						$("#paramNameFour").val(data.data.sysParamHisObject.paramNameFour);  
						$("#paramValueFive").val(data.data.sysParamHisObject.paramValueFive);  
						$("#paramNameFive").val(data.data.sysParamHisObject.paramNameFive);  								
						$("#remark").val(data.data.sysParamHisObject.remark);  
						$("#version").val(data.data.sysParamHisObject.version); 
						$("#createMan").val(data.data.sysParamHisObject.createMan); 
						$("#createTime").val(data.data.sysParamHisObject.createTime.substring(0, 19)); 
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
	

// 关闭
function closeIfrname(){
//	layer.closeAll();
	var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(frameindex);
}