	layui.config({
				base: '../../js/' 
			});
 
	var creditType;
	var ruleComparisonType;
	var validateRule;
	
	// 回显
	$(function(){
		var bigId = getQueryString("bigId");
		var bigCode = getQueryString("bigCode");
		if(bigId !=null && bigId != ""){
			$("#fraudRuleBigId").val(bigId);
			$("#fraudRuleBigCode").val(bigCode);
		}
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
					$('#bigId').hide();
					return false;
				} else {
					$('#bigCode').hide();
				}
		  		$.ajax({
					url : getCtxPath() + "/backend/scFraudRule/getEntityById.do",
					method: "post",
					dataType:"json",
					data: {
						fraudRuleId: id
					},
					success: function(data){
						var state =data.data.scFraudRuleObject.state//回显单选框
						if(state == "01"){ 
 							$("input[name='level1']").eq(0).attr("checked","checked");
						}else{
							$("input[name='level1']").eq(1).attr("checked","checked");
 						}
						$("#score").val(data.data.scFraudRuleObject.score);
						$("#percent").val(data.data.scFraudRuleObject.percent);
						$("#fraudRuleBigId").val(data.data.scFraudRuleObject.fraudRuleBigId);
						$("#fraudRuleTplId").val(data.data.scFraudRuleObject.fraudRuleTplId); 
					    $("#fraudRuleId").val(data.data.scFraudRuleObject.fraudRuleId); 
						$("#fraudRuleCode").val(data.data.scFraudRuleObject.fraudRuleCode); 
						$("#ruleName").val(data.data.scFraudRuleObject.ruleName);
						$("#select_creditType").find("option[value='"+data.data.scFraudRuleObject.creditType+"']").attr("selected",true);
						$("#ruleDetail").val(data.data.scFraudRuleObject.ruleDetail);  
						$("#ruleComparisonValue").val(data.data.scFraudRuleObject.ruleComparisonValue); 
//						$("#select_ruleComparisonType").next(".layui-form-select").children(".layui-select-title").find("input").val(data.data.scFraudRuleObject.ruleComparisonType)
						$("#select_ruleComparisonType").find("option[value='"+data.data.scFraudRuleObject.ruleComparisonType+"']").attr("selected",true);
						$("#select_validateRule").find("option[value='"+data.data.scFraudRuleObject.validateRule+"']").attr("selected",true);
						$("#remark").val(data.data.scFraudRuleObject.remark);  
						$("#version").val(data.data.scFraudRuleObject.version); 
						$("#createMan").val(data.data.scFraudRuleObject.createMan); 
						$("#createTime").val(data.data.scFraudRuleObject.createTime.substring(0, 19));  
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
var menuNames = "欺诈项规则配置";
 

// 保存
function save(form){
	var fraudRuleBigId = form.field.fraudRuleBigId;
 	var fraudRuleId = form.field.fraudRuleId;
 	var fraudRuleTplId = form.field.fraudRuleTplId;
 	var fraudRuleCode = form.field.fraudRuleCode;
	var ruleName = form.field.ruleName;
	var creditType = form.field.creditType;
	var ruleDetail = form.field.ruleDetail;
	var ruleComparisonValue = form.field.ruleComparisonValue;
	var ruleComparisonType = form.field.ruleComparisonType;
	var validateRule = form.field.validateRule;
	var state = form.field.level1;//保存单选框的选中的值
	var score = form.field.score;//保存单选框的选中的值
	var percent = form.field.percent;
	var remark =form.field.remark;
	var version =form.field.version;
	var createMan =form.field.createMan;
	var createTime =form.field.createTime;
	if (fraudRuleCode == null || fraudRuleCode == '') {
		alert("请填写欺诈项规则对应编号！");
		return;
	}
	if (ruleName == null || ruleName == '') {
		alert("请填写欺诈项规则名称！");
		return;
	}
	if (creditType == null || creditType == '') {
		alert("请选择征信机构类型！");
		return;
	}
	if (ruleDetail == null || ruleDetail == '') {
		alert("请填写欺诈项规则明细！");
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
	
	if (score == null || score == '') {
		alert("请填写分数值！");
		return;
	}
	
	if (percent == null || percent == '') {
		alert("请填写权重值！");
		return;
	} else if (parseFloat(percent) > 1.0) {
		alert("权重值不能大于1！");
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
		url : getCtxPath() + "/backend/scFraudRule/saveScFraudRule.do",
		method : 'post',
		dataType : 'json',
		data: {
			fraudRuleId : fraudRuleId,
			fraudRuleBigId : fraudRuleBigId,
			fraudRuleCode : fraudRuleCode,
			fraudRuleTplId :fraudRuleTplId,
			ruleName : ruleName,
			creditType : creditType,
			ruleDetail : ruleDetail,
			ruleComparisonValue : ruleComparisonValue,
			ruleComparisonType : ruleComparisonType,
			validateRule : validateRule,
			score:score,
			percent:percent,
			state : state,
			remark : remark,
			version:version,
			createTime:createTime,
			createMan:createMan,
			menuNames : menuNames,
			functionNames: (fraudRuleId != ''  ? "修改欺诈项规则" : "新增欺诈项规则") // 按钮功能名称 不为空修改 为空 新增
		},
		success : function(obj) {
 			if(obj.code == "2001" || obj.code == "4001"){
				layer.msg(obj.message , {icon: 1});//成功提示信息
				$("#save_bt").attr("style", "display:none");//按钮隐藏
				// 延迟一秒后跳转页面
				setTimeout(function(){
					closeIfrname();
				var typeId = getQueryString("typeId");
					if(typeId == "1"){
						window.parent.getScFraudRule();//刷新树形图页面
					}else{
						window.parent.getList();
					}
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
