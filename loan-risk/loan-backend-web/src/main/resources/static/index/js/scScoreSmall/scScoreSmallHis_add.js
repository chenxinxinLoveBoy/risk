	layui.config({
				base: '../../js/' 
			});
 
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
			  form.on('radio(radio_filter1)', function(data){
				  form.render(); //重新渲染
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
					url : getCtxPath() + "/backend/scScoreSmallHis/getEntityById.do",
					method: "post",
					dataType:"json",
					data: {
						smallHisId: id
					},
					success: function(data){
						var state =data.data.scScoreSmallHisObject.state//回显单选框
						if(state == "01"){
 							$("input[name='level1']").eq(0).attr("checked","checked");
						}else{
							$("input[name='level1']").eq(1).attr("checked","checked");
 						}
						
						$("#scoreBigId").val(data.data.scScoreSmallHisObject.scoreBigId); 
						$("#scoreSmallId").val(data.data.scScoreSmallHisObject.scoreSmallId); 
						$("#scoreRuleName").val(data.data.scScoreSmallHisObject.scoreRuleName);
						$("#scoreSmallCode").val(data.data.scScoreSmallHisObject.scoreSmallCode);  
						$("#ruleScoreValue").val(data.data.scScoreSmallHisObject.ruleScoreValue); 
						$("#score").val(data.data.scScoreSmallHisObject.score); 
 						$("#select_ruleComparisonType").find("option[value='"+data.data.scScoreSmallHisObject.ruleComparisonType+"']").attr("selected",true);
						$("#select_validateRule").find("option[value='"+data.data.scScoreSmallHisObject.validateRule+"']").attr("selected",true);
						$("#remark").val(data.data.scScoreSmallHisObject.remark);  
						$("#version").val(data.data.scScoreSmallHisObject.version); 
						$("#createMan").val(data.data.scScoreSmallHisObject.createMan); 
						$("#createTime").val(data.data.scScoreSmallHisObject.createTime.substring(0, 19));  
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