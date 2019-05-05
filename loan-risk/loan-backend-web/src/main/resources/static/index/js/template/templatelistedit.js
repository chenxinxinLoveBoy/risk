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
			  
			  
				var banControlId = getQueryString("banControlId"); 
				if (banControlId == null || banControlId == '') return;
				
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
	

// 关闭
function closeIfrname(){
//	layer.closeAll();
	var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(frameindex);
}
 
// 保存
function save(form){
	var banCode = $("#banCode").val();
	var banControlId = getQueryString("banControlId");
	var ruleName = $("#ruleName").val();
	var creditType = $("#creditType").val();
	var ruleDetail = $("#ruleDetail").val();
	var state = form.field.state;//保存单选框的选中的值 
	var ruleComparisonType = $("#ruleComparisonType").val();
	var ifRefuse = form.field.ifRefuse;//保存单选框的选中的值 
	var validateRule = $("#validateRule").val();
	var version = $("#version").val();
	var createTime = $("#createTime").val();
	var createMan = $("#createMan").val();
	var createName = $("#createName").val();
	var modifyTime = $("#modifyTime").val();
	var modifyMan = $("#modifyMan").val();
	var modifyName = $("#modifyName").val();
	
	var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	 
	  
	$.ajax({
		url : getCtxPath() + "/backend/scBanControl/saveScBanControl.do",
		method : 'post',
		dataType : 'json',
		data: {
			banCode : banCode, 
			banControlId : banControlId,
			ruleName : ruleName,  
			creditType : creditType,
			ruleDetail : ruleDetail,
			state  : state,
			ifRefuse : ifRefuse,
			validateRule : validateRule,
			version : version,
			createTime : createTime,
			createMan : createMan,
			createName : createName,
			modifyTime : modifyTime,
			modifyMan : modifyMan,
			modifyName : modifyName 
		},
		success : function(obj) {
 			if(obj.code == "2001" || obj.code == "4001"){
 				layer.msg(obj.message , {icon: 1});//成功提示信息
				$("#save_bt").attr("style", "display:none");//按钮隐藏
				// 延迟一秒后跳转页面
				setTimeout(function(){
					closeIfrname(); 
					window.parent.getList();
				},1000)
			}else{
				layer.msg(obj.message , {icon: 2});//成功提示信息
			}
		},
		error: function(){
			alert("获取数据失败，请尝试重新登陆！");
		},
		complete: function(){ 
			 
		} 
	}); 
}

