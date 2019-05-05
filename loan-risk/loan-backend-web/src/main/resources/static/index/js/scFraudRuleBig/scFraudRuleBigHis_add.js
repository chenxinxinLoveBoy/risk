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
			  
			  //监听select
			  form.on('select(creditType)', function(data){
				  creditType =  data.value;
			  });   
			  
				var id = getQueryString("id");
 		  		$.ajax({
					url :getCtxPath() + "/backend/scFraudRuleBigHis/getEntityById.do",
					method: "post",
					dataType:"json",
					data: {
						fraudRuleBigHisId: id
					},
					success: function(data){
						var state =data.data.scFraudRuleBigHisObject.state//回显单选框
  						if(state == "01"){ 
 							$("input[name='level1']").eq(0).attr("checked","checked");
						}else{
							$("input[name='level1']").eq(1).attr("checked","checked");
 						}
					    $("#fraudRuleBigId").val(data.data.scFraudRuleBigHisObject.fraudRuleBigId); 
						$("#fraudRuleBigCode").val(data.data.scFraudRuleBigHisObject.fraudRuleBigCode); 
						
						$("#fraudRuleName").val(data.data.scFraudRuleBigHisObject.fraudRuleName);
						$("#select_creditType").find("option[value='"+data.data.scFraudRuleBigHisObject.creditType+"']").attr("selected",true);
						$("#fraudRuleDetail").val(data.data.scFraudRuleBigHisObject.fraudRuleDetail);  
						$("#remark").val(data.data.scFraudRuleBigHisObject.remark);  
						$("#version").val(data.data.scFraudRuleBigHisObject.version); 
						$("#percent").val(data.data.scFraudRuleBigHisObject.percent);
 						$("#createMan").val(data.data.scFraudRuleBigHisObject.createMan); 
						$("#createTime").val(data.data.scFraudRuleBigHisObject.createTime.substring(0, 19));  
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