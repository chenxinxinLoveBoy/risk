	layui.config({
				base: '../../js/' 
			});
 
 	var ruleComparisonType;
	var validateRule;
	
	// 评分卡大类序号
	var bigId = getQueryString("bigId");
	
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
			  
			  // 监听submit提交事件
			  form.on('submit(save)', function(data){
 				  // 保存
				  save(data);
				  return false;// 阻止跳转页面，只有保存成功时才跳转
			  });
			  
			  
				var id = getQueryString("id");
 				//id不存在，不需要请求初始化，新增页面使用
//				if(id === undefined || id === ""){
//					return false;
//				}
				
		  		$.ajax({
					url : getCtxPath() + "/backend/scScoreSmall/getEntityById.do",
					method: "post",
					dataType:"json",
					data: {
						scoreSmallId: id
					},
					success: function(data){
						// 评分卡大类序号(表示从大类新增传过来的值)
						if(bigId != null && bigId != "" && bigId != undefined){
							 $("#scoreBigId").val(bigId); 
						}else{// 小类'编辑'按钮传过来的值
							$("#scoreBigId").val(data.data.scScoreSmallObject.scoreBigId); 
						}
 						var state =data.data.scScoreSmallObject.state//回显单选框
 		 				 
						if(state == "01"){
 							$("input[name='level1']").eq(0).attr("checked","checked");
						}else{
							$("input[name='level1']").eq(1).attr("checked","checked");
 						}
 						$("#scoreTplId").val(data.data.scScoreSmallObject.scoreTplId); 
 						$("#percent").val(data.data.scScoreSmallObject.percent);
						$("#scoreSmallId").val(data.data.scScoreSmallObject.scoreSmallId); 
						$("#scoreRuleName").val(data.data.scScoreSmallObject.scoreRuleName);
						$("#scoreSmallCode").val(data.data.scScoreSmallObject.scoreSmallCode);  
						$("#ruleScoreValue").val(data.data.scScoreSmallObject.ruleScoreValue); 
						$("#score").val(data.data.scScoreSmallObject.score); 
 						$("#select_ruleComparisonType").find("option[value='"+data.data.scScoreSmallObject.ruleComparisonType+"']").attr("selected",true);
						$("#select_validateRule").find("option[value='"+data.data.scScoreSmallObject.validateRule+"']").attr("selected",true);
						$("#remark").val(data.data.scScoreSmallObject.remark);  
						$("#version").val(data.data.scScoreSmallObject.version); 
						$("#createMan").val(data.data.scScoreSmallObject.createMan); 
						$("#createTime").val(data.data.scScoreSmallObject.createTime.substring(0, 19));  
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

var menuNames = "信用评分规则配置";//操作菜单
// 保存
function save(form){
 	var scoreSmallId = form.field.scoreSmallId;
 	var scoreBigId = form.field.scoreBigId;
	var scoreRuleName = form.field.scoreRuleName;
	var scoreSmallCode = form.field.scoreSmallCode;
	var ruleScoreValue = form.field.ruleScoreValue;
	var score = form.field.score;
	var ruleComparisonType = form.field.ruleComparisonType;
	var validateRule = form.field.validateRule;
	var state = form.field.level1;//保存单选框的选中的值
	var remark =form.field.remark;
	var version =form.field.version;
	var createMan =form.field.createMan;
	var createTime =form.field.createTime;
	var percent = form.field.percent;
	var scoreTplId = form.field.scoreTplId;
	
	
 	if (scoreBigId == null || scoreBigId == '') {
		alert("请填评分卡大类序号！");
		return;
	}
	if (scoreRuleName == null || scoreRuleName == '') {
		alert("请填写评分项规则名称！");
		return;
	}
	if (scoreSmallCode == null || scoreSmallCode == '') {
		alert("请填写评分项规则小类编号！");
		return;
	}
 
	if (score == null || score == '') {
		alert("请填写分值！");
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
	
	if (percent == null || percent == '') {
		alert("请填写权重值！");
		return;
	} else if (parseFloat(percent) > 1.0) {
		alert("权重值不能大于1！");
		return;
	}
	
	if (ruleScoreValue != null || ruleScoreValue != '') {
		if (ruleScoreValue.endsWith("|||")) {
			alert("请检查输入的规则技术比对值是否全部为数字！");
			return;
		}
		if (ruleComparisonType == '01') {// 校验输入规则技术比对值是否为数字
			var strs = new Array(); // 定义一数组
			strs = ruleScoreValue.split("|||"); // 字符分割
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
		url : getCtxPath() + "/backend/scScoreSmall/saveScScoreSmall.do",
		method : 'post',
		dataType : 'json',
		data: {
			scoreSmallId : scoreSmallId,
			scoreBigId : scoreBigId,
			scoreRuleName : scoreRuleName,
			scoreSmallCode : scoreSmallCode,
			ruleScoreValue : ruleScoreValue,
			score : score,
			ruleComparisonType : ruleComparisonType,
			validateRule : validateRule,
			state : state,
			remark : remark,
			version:version,
			createTime:createTime,
			createMan:createMan,
			percent:percent,
			scoreTplId:scoreTplId,
			menuNames : menuNames,// 信用评分规则
			functionNames: (scoreSmallId != ''  ? "修改信用评分卡小类" : "新增信用评分卡小类") // 按钮功能名称 不为空修改 为空 新增
		},
		success : function(obj) {
 			if(obj.code == "2001" || obj.code == "4001"){
				layer.msg(obj.message , {icon: 1});//成功提示信息
				$("#save_bt").attr("style", "display:none");//按钮隐藏
				
				var urls = "";
				if(bigId != null && bigId != '' && bigId != undefined){// 跳转到大类页面
					urls = getCtxPath()  + "/index/views/scScoreBig/scScoreBig.html";
				}else{// 跳转到小类页面
					urls = getCtxPath()  + "/index/views/scScoreSmall/scScoreSmall.html";
				}
				// 延迟一秒后跳转页面
				setTimeout(function(){
					closeIfrname(); 
					
					var typeId = getQueryString("typeId");
					if(typeId == "1"){
						window.parent.getScScore();//刷新树形图页面
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
