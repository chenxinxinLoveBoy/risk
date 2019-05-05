	layui.config({
				base: '../../js/' 
			});
 
	var creditType;
	var ruleComparisonType;
	var validateRule;
	  var fraudRuleTplId ="";

	// 当前菜单名称
	var menuNames = "欺诈风险阈值历史列表";
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
			  form.on('select(fraudRuleTplId)', function(data){
				  fraudRuleTplId =  data.value;
			  }); 
			// 监听submit提交事件
			  form.on('submit(save)', function(data){
 				  // 保存
				  save(data);
				  return false;// 阻止跳转页面，只有保存成功时才跳转
			  });
 			  getTplList(form);
 			  
				var id = getQueryString("id"); 
				var historySerialnumber="";
				if (id == '' || id === undefined) 
				return; 
		  		$.ajax({
					url : getCtxPath() + "/backend/thresholdHistory/getEntityById.do",
					method: "post",
					dataType:"json",
 					data: {
						historySerialnumber: id
					},
					success: function(data){ 
						
						var stateflag =data.list.thresholdHistory.state//回显单选框
		 				 
						if(stateflag == 1){ 
 							$("input[name='state']").eq(0).attr("checked","checked");
						}else{
							$("input[name='state']").eq(1).attr("checked","checked");
 						}
						 
					    $("#createMan").val(data.list.thresholdHistory.createMan); 
					    $("#mum").val(data.list.thresholdHistory.mum); 
						$("#mumType").find("option[value='"+data.list.thresholdHistory.mumType+"']").attr("selected",true);
						$("#resultsTab").find("option[value='"+data.list.thresholdHistory.resultsTab+"']").attr("selected",true);
						$("#resultsTab").val(data.list.thresholdHistory.resultsTab); 
						$("#remark").val(data.list.thresholdHistory.remark); 
						$("#fraudRuleTplId").find("option[value='"+data.list.thresholdHistory.fraudRuleTplId+"']").attr("selected",true);
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
	var  frameindex= parent.layer.getFrameIndex(window.name);  
	parent.layer.close(frameindex);
}
function getTplList(form) {
	$.ajax({
		url : getCtxPath() + "/backend/scFraudRuleTpl/scFraudRuleTplListAll.do?v=" + new Date().getTime(),
		method : "post",
		async: false,
		dataType : "json",
		data : {
			state:"01"
 		},
		success : function(data) {
			var arr = [];
			arr.push('<select name="fraudRuleTplId" id = "fraudRuleTplId" class="layui-input">');
			layui.each(data.list, function(index, item){
					if(fraudRuleTplId === item.fraudRuleTplId){
				    	arr.push('<option value="'+ item.fraudRuleTplId + '" selected>' + item.fraudRuleTplName + '</option>');
					}else{
				    	arr.push('<option value="'+ item.fraudRuleTplId + '">' + item.fraudRuleTplName + '</option>');
					}
			    });
			arr.push('</select>');
			$('#tplContent').html(arr.join(''));
			form.render(); //刷新全部
		},
		error : function() {
			alert("获取用户信息失败,请尝试重新获取！");
		}
	});
}
	