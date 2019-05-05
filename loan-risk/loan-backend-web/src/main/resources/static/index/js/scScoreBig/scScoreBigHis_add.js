	layui.config({
				base: '../../js/' 
			});
 
	var creditType;
	
	var stateVal = ""; // 判断大类在关闭时用到
	var isScScoreSmall = false ;// 大类下是否有小类
	
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
					url :getCtxPath() + "/backend/scScoreBigHis/getEntityById.do",
					method: "post",
					dataType:"json",
					data: {
						bigHisId: id
					},
					success: function(data){
						var state =data.data.scScoreBigHisObject.state//回显单选框
						stateVal = state;
						if(state == "01"){ 
 							$("input[name='level1']").eq(0).attr("checked","checked");
						}else{
							$("input[name='level1']").eq(1).attr("checked","checked");
 						}
						// 如果大类下有小类，不让修改大类的编号
						if(data.data.isScScoreSmall){
							isScScoreSmall = true;
							$("#scoreBigCode").attr("disabled", "disabled");
						}
					    $("#scoreBigId").val(data.data.scScoreBigHisObject.scoreBigId); 
						$("#scoreBigCode").val(data.data.scScoreBigHisObject.scoreBigCode); 
						
						$("#scoreName").val(data.data.scScoreBigHisObject.scoreName);
						$("#select_creditType").find("option[value='"+data.data.scScoreBigHisObject.creditType+"']").attr("selected",true);
						$("#scoreRuleDetail").val(data.data.scScoreBigHisObject.scoreRuleDetail);  
						$("#remark").val(data.data.scScoreBigHisObject.remark);  
						$("#version").val(data.data.scScoreBigHisObject.version); 
						$("#percent").val(data.data.scScoreBigHisObject.percent); 
						$("#createMan").val(data.data.scScoreBigHisObject.createMan); 
						$("#createTime").val(data.data.scScoreBigHisObject.createTime.substring(0, 19));  
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