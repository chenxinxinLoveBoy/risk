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
					url : getCtxPath() + "/backend/scBanControl/getEntityById.do",
					method: "post",
					dataType:"json",
					data: {
						banControlId: id
					},
					success: function(data){
						var state =data.data.scBanControlObject.state//回显单选框
						if(state == "01"){ 
 							$("input[name='level1']").eq(0).attr("checked","checked");
						}else{
							$("input[name='level1']").eq(1).attr("checked","checked");
 						}
						
						var ifRefuse =data.data.scBanControlObject.ifRefuse//回显单选框
						if(ifRefuse == "0"){ 
 							$("input[name='level0']").eq(0).attr("checked","checked");
						}else{
							$("input[name='level0']").eq(1).attr("checked","checked");
 						}
						
					    $("#banControlTplId").val(data.data.scBanControlObject.banControlTplId); 
					    $("#banControlId").val(data.data.scBanControlObject.banControlId); 
						$("#banCode").val(data.data.scBanControlObject.banCode); 
						$("#ruleName").val(data.data.scBanControlObject.ruleName);
						$("#select_creditType").find("option[value='"+data.data.scBanControlObject.creditType+"']").attr("selected",true);
						$("#ruleDetail").val(data.data.scBanControlObject.ruleDetail);  
						$("#ruleComparisonValue").val(data.data.scBanControlObject.ruleComparisonValue); 
//						$("#select_ruleComparisonType").next(".layui-form-select").children(".layui-select-title").find("input").val(data.data.scBanControlObject.ruleComparisonType)
						$("#select_ruleComparisonType").find("option[value='"+data.data.scBanControlObject.ruleComparisonType+"']").attr("selected",true);
						$("#select_validateRule").find("option[value='"+data.data.scBanControlObject.validateRule+"']").attr("selected",true);
						$("#remark").val(data.data.scBanControlObject.remark);  
						$("#version").val(data.data.scBanControlObject.version); 
						$("#createMan").val(data.data.scBanControlObject.createMan); 
						$("#createTime").val(data.data.scBanControlObject.createTime.substring(0, 19));  
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
var menuNames = "禁止项规则配置";
 

// 保存
function save(form){
 	var banControlId = form.field.banControlId;
 	var banCode = form.field.banCode;
	var ruleName = form.field.ruleName;
	var creditType = form.field.creditType;
	var ruleDetail = form.field.ruleDetail;
	var ruleComparisonValue = form.field.ruleComparisonValue;
	var ruleComparisonType = form.field.ruleComparisonType;
	var validateRule = form.field.validateRule;
	var state = form.field.level1;//保存单选框的选中的值
	var ifRefuse = form.field.level0;//保存单选框的选中的值
	var remark =form.field.remark;
	var version =form.field.version;
	var createMan =form.field.createMan;
	var createTime =form.field.createTime;
	var banControlTplId =form.field.banControlTplId;
	
	if (banCode == null || banCode == '') {
		alert("请填写禁止项规则对应编号！");
		return;
	}
	if (ruleName == null || ruleName == '') {
		alert("请填写禁止项规则名称！");
		return;
	}
	if (creditType == null || creditType == '') {
		alert("请选择征信机构类型！");
		return;
	}
	if (ruleDetail == null || ruleDetail == '') {
		alert("请填写禁止项规则明细！");
		return;
	}
 
	if (ruleComparisonType == null || ruleComparisonType == '') {
		alert("请选择技术比对值类型！");
		return;
	}
	
	if (validateRule == null || validateRule == '') {
		alert("请选择技术校验规则！");
		return;
	}
	
	if (state == null || state == '') {
		alert("请选择状态！");
		return;
	}
	
	if (ifRefuse == null || ifRefuse == '') {
		alert("请选择是否添加拒绝名单！");
		return;
	}
	

	if (ruleComparisonValue != null || ruleComparisonValue != '') {
		if (ruleComparisonValue.endsWith("|||")) {
			alert("请检查输入的规则技术比对值是否全部为数字！");
			return;
		}
		if (ruleComparisonType == '01') {// 校验输入规则技术比对值是否为数字
			var strs = new Array(); // 定义一数组
			strs = ruleComparisonValue.split("|||"); // 字符分割
			for (i = 0; i < strs.length; i++) {
				if (isNaN(strs[i])) {
					alert("请检查输入的规则技术比对值是否全部为数字！");
					return;
				}
			}
		}
	}
	
	var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	$.ajax({
		url : getCtxPath() + "/backend/scBanControl/saveScBanControl.do",
		method : 'post',
		dataType : 'json',
		data: {
			banControlId : banControlId,
			banCode : banCode,
			ruleName : ruleName,
			creditType : creditType,
			ruleDetail : ruleDetail,
			ruleComparisonValue : ruleComparisonValue,
			ruleComparisonType : ruleComparisonType,
			validateRule : validateRule,
			ifRefuse:ifRefuse,
			state : state,
			remark : remark,
			version:version,
			createTime:createTime,
			createMan:createMan,
			banControlTplId:banControlTplId,
			menuNames : menuNames,
			functionNames: (banControlId != ''  ? "修改禁止项规则" : "新增禁止项规则") // 按钮功能名称 不为空修改 为空 新增
		},
		success : function(obj) {
 			if(obj.code == "2001" || obj.code == "4001"){
				layer.msg(obj.message , {icon: 1});//成功提示信息
				$("#save_bt").attr("style", "display:none");//按钮隐藏
				// 延迟一秒后跳转页面
				setTimeout(function(){
					closeIfrname();
//					parent.location.href = getCtxPath()  + "/index/views/scBanControl/scBanControl.html";// 跳转页面
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
//			 parent.layer.close(frameindex);
//			 location.reload(); //刷新，关闭
			 
		}
		
	});
	
	
}
