	layui.config({
				base: '../../js/' 
			});
 
	var creditType;
	
	var stateVal = ""; // 判断大类在关闭时用到
	var isScFraudRule = false ;// 大类下是否有小类
	// 当前菜单名称
	var menuNames = "新增欺诈大类配置";
	
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
				} else {
					$('#fraudRuleBigCode').attr('readonly',true);
				}
		  		$.ajax({
					url :getCtxPath() + "/backend/scFraudRuleBig/getEntityById.do",
					method: "post",
					dataType:"json",
					data: {
						fraudRuleBigId: id
					},
					success: function(data){
						var state =data.data.scFraudRuleBigObject.state;//回显单选框
						stateVal = state;
						if(state == "01"){ 
 							$("input[name='level1']").eq(0).attr("checked","checked");
						}else{
							$("input[name='level1']").eq(1).attr("checked","checked");
 						}
						// 如果大类下有小类，不让修改大类的编号
						if(data.data.isScFraudRule){
							isScFraudRule = true;
							$("#fraudRuleBigCode").attr("disabled", "disabled");
						}
					    $("#fraudRuleBigId").val(data.data.scFraudRuleBigObject.fraudRuleBigId); 
						$("#fraudRuleBigCode").val(data.data.scFraudRuleBigObject.fraudRuleBigCode); 
						
						$("#fraudRuleName").val(data.data.scFraudRuleBigObject.fraudRuleName);
						$("#select_creditType").find("option[value='"+data.data.scFraudRuleBigObject.creditType+"']").attr("selected",true);
						$("#fraudRuleDetail").val(data.data.scFraudRuleBigObject.fraudRuleDetail);  
						$("#remark").val(data.data.scFraudRuleBigObject.remark);  
						$("#version").val(data.data.scFraudRuleBigObject.version); 
						$("#percent").val(data.data.scFraudRuleBigObject.percent);
						$("#createMan").val(data.data.scFraudRuleBigObject.createMan); 
						$("#createTime").val(data.data.scFraudRuleBigObject.createTime.substring(0, 19));  
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
 	var fraudRuleBigId = form.field.fraudRuleBigId;
 	var fraudRuleBigCode = form.field.fraudRuleBigCode;
	var fraudRuleName = form.field.fraudRuleName;
	var creditType = form.field.creditType;
	var fraudRuleDetail = form.field.fraudRuleDetail;
	var state = form.field.level1;//保存单选框的选中的值
	var remark =form.field.remark;
	var version =form.field.version;
	var createMan =form.field.createMan;
	var createTime =form.field.createTime;
	var percent =form.field.percent;
	
	if (fraudRuleBigCode == null || fraudRuleBigCode == '') {
		alert("请填写欺诈项规则大类编号！");
		return;
	}
	if (fraudRuleName == null || fraudRuleName == '') {
		alert("请填写欺诈项名称！");
		return;
	}
	if (creditType == null || creditType == '') {
		alert("请选择征信机构类型！");
		return;
	}
	if (fraudRuleDetail == null || fraudRuleDetail == '') {
		alert("请填写欺诈项规则明细！");
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
	var isUpdate = true;
	var updateFraudRule = "";// 传到后台用，判断是否将大类下的小类状态置为失效
	// 大类下有小类， 并且由生效改为失效的时候，给出提醒
	if(isScFraudRule && stateVal == "01" && stateVal != state){
		if(confirm("当前大类下包含小类数据，确认失效, 小类下的数据也会失效，是否继续？")){
			updateFraudRule = "1";
		}else{
			isUpdate = false;
		}
	}
	
	if(isUpdate){
		var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		$.ajax({
			url : getCtxPath() + "/backend/scFraudRuleBig/saveScFraudRuleBig.do",
			method : 'post',
			dataType : 'json',
			data: {
				fraudRuleBigId : fraudRuleBigId,
				fraudRuleBigCode : fraudRuleBigCode,
				fraudRuleName : fraudRuleName,
				creditType : creditType,
				fraudRuleDetail : fraudRuleDetail,
				state : state,
				remark : remark,
				version:version,
				percent:percent,
				createTime:createTime,
				createMan:createMan,
				updateFraudRule: updateFraudRule,
				menuNames : menuNames,//新增欺诈卡大类配置
				functionNames: (fraudRuleBigId != ''  ? "修改欺诈大类" : "新增欺诈大类") // 按钮功能名称 不为空修改 为空 新增
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
							window.parent.getScFraudRuleBig();//刷新树形图页面
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
	
}
