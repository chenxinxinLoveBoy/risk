	layui.config({
				base: '../../js/' 
			});
 
	var creditType;
	var ruleComparisonType;
	var validateRule;
	
	// 回显
	$(function(){
		//我们强烈推荐你在代码最外层把需要用到的模块先加载
		layui.use(['layer', 'form', 'element'], function(){
			 var layer = layui.layer
			  ,form = layui.form()
			  ,element = layui.element();
			  form.render(); //刷新全部
			  
			  // 监听radio单选
			  form.on('radio(radio_filter0)', function(data){
				  form.render(); //重新渲染
			  }); 
			  
			  // 监听radio单选
			  form.on('radio(radio_filter1)', function(data){
				  form.render(); //重新渲染
			  }); 
			  
			  //监听select
			  form.on('select(creditType)', function(data){
				  creditType =  data.value;
			  });   
			  
			  //监听select
			  form.on('select(ruleComparisonType)', function(data){
				  ruleComparisonType =  data.value;
			  });   
			  
			  //监听select
			  form.on('select(validateRule)', function(data){
				  validateRule =  data.value;
			  });   
			  
				var id = getQueryString("id");
 		  		$.ajax({
					url : getCtxPath() + "/backend/scBanControlHis/getEntityById.do",
					method: "post",
					dataType:"json",
					data: {
						controlHisId: id
					},
					success: function(data){
						var s =data.data.scBanControlHisObject.state//回显单选框
 						if(s == "01"){ 
 							$("input[name='level1']").eq(0).attr("checked","checked");
						}else{
							$("input[name='level1']").eq(1).attr("checked","checked");
 						}
						
						var ifRefuse =data.data.scBanControlHisObject.ifRefuse//回显单选框
						if(ifRefuse == "0"){ 
 							$("input[name='level0']").eq(0).attr("checked","checked");
						}else{
							$("input[name='level0']").eq(1).attr("checked","checked");
 						}
						
					    $("#banControlId").val(data.data.scBanControlHisObject.banControlId); 
						$("#banCode").val(data.data.scBanControlHisObject.banCode); 
						$("#ruleName").val(data.data.scBanControlHisObject.ruleName);
						$("#select_creditType").find("option[value='"+data.data.scBanControlHisObject.creditType+"']").attr("selected",true);
						$("#ruleDetail").val(data.data.scBanControlHisObject.ruleDetail);  
						$("#ruleComparisonValue").val(data.data.scBanControlHisObject.ruleComparisonValue); 
 						$("#select_ruleComparisonType").find("option[value='"+data.data.scBanControlHisObject.ruleComparisonType+"']").attr("selected",true);
						$("#select_validateRule").find("option[value='"+data.data.scBanControlHisObject.validateRule+"']").attr("selected",true);
						$("#remark").val(data.data.scBanControlHisObject.remark);  
						$("#version").val(data.data.scBanControlHisObject.version); 
						$("#createMan").val(data.data.scBanControlHisObject.createMan); 
						$("#createTime").val(data.data.scBanControlHisObject.createTime.substring(0, 19));  
					},
					error: function(){  
						layer.msg("网络请求异常,请尝试重新登录");//提示信息
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
