	layui.config({
				base: '../../js/' 
			});
 
	var creditType;
	var ruleComparisonType;
	var validateRule;
	  var fraudRuleTplId ="";
	// 当前菜单名称
	var menuNames = "欺诈风险阈值列表";
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
				if (id == '' || id === undefined)
					return;
		  		$.ajax({
					url : getCtxPath() + "/backend/threshold/getEntityById.do",
					method: "post",
					dataType:"json",
					data: {
						serialnumber: id
					},
					success: function(data){ 
						
						var state1 =data.list.threshold.state//回显单选框
		 				 
						if(state1 == 1){ 
 							$("input[name='state']").eq(0).attr("checked","checked");
						}else{
							$("input[name='state']").eq(1).attr("checked","checked");
 						} 
						$("#mum").val(data.list.threshold.mum); 
						$("#mumType").find("option[value='"+data.list.threshold.mumType+"']").attr("selected",true);
						$("#resultsTab").find("option[value='"+data.list.threshold.resultsTab+"']").attr("selected",true);
 						$("#fraudRuleTplId").find("option[value='"+data.list.threshold.fraudRuleTplId+"']").attr("selected",true);
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


// 保存
function save(form){ 
 	var mum = $("#mum").val();
	var mumType = $("#mumType").val();
	var state = form.field.state;//保存单选框的选中的值
	var serialnumber = getQueryString("id");
	var resultsTab = $("#resultsTab").val();
	var remark = $("#remark").val();
	var fraudRuleTplId = form.field.fraudRuleTplId;

	 
	if (mum == null || mum == '') {
		alert("请填欺诈阈值！");
		return;
	}
//	if (parseInt(maximum) <= parseInt(minimum)) {
//		alert(" 欺诈阈值最大值应大于欺诈阈值最小值！");
//		return;
//	} 
	if (state == null || state == '') {
		alert("请选择状态！");
		return;
	}
	if (resultsTab == null || resultsTab == '') {
		alert("请选择结果标签！");
		return;
	}
	
	var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	$.ajax({
		url : getCtxPath() + "/backend/threshold/saveThreshold.do",
		method : 'post',
		dataType : 'json',
		data: { 
			serialnumber: serialnumber,
			mum : mum, 
			mumType : mumType,
			state : state,
			resultsTab : resultsTab,
			remark : remark,
			fraudRuleTplId:fraudRuleTplId,
			menuNames : menuNames,//操作菜单
			functionNames: (serialnumber != ''  ? "修改欺诈风险阈值" : "新增欺诈风险阈值") // 按钮功能名称 不为空修改 为空 新增
		},
		success : function(obj) {
 			if(obj.code == "2001" || obj.code == "4001"){
				layer.msg(obj.message , {icon: 1});//成功提示信息
				 
					closeIfrname(); 
					window.parent.getList();
				 
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
			arr.push('<select name="fraudRuleTplId" id = "fraudRuleTplId" class="layui-input" lay-search="">');
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
	