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
			  
				var id = getQueryString("id");
				//id不存在，不需要请求初始化，新增页面使用
				if(id === undefined || id === ""){
					return false;
				}
		  		$.ajax({
					url :getCtxPath() + "/backend/sysParam/getEntityById.do",
					method: "post",
					dataType:"json",
					data: {
						sysParamId: id
					},
					success: function(data){
						var statue =data.data.sysParamObject.statue//回显单选框
		 				if(statue=="01"){
 							$("#level_id1").attr("checked", "checked");
						}else{
							$("#level_id2").attr("checked", "checked");
 		 				}
						$("#sysParamId").val(data.data.sysParamObject.sysParamId); 
						$("#paramValue").val(data.data.sysParamObject.paramValue); 	
						$("#paramName").val(data.data.sysParamObject.paramName);  
						$("#paramValueOne").val(data.data.sysParamObject.paramValueOne); 
						$("#paramNameOne").val(data.data.sysParamObject.paramNameOne); 
						$("#paramValueTwo").val(data.data.sysParamObject.paramValueTwo);  
						$("#paramNameTwo").val(data.data.sysParamObject.paramNameTwo); 
						$("#paramValueThree").val(data.data.sysParamObject.paramValueThree); 	
						$("#paramNameThree").val(data.data.sysParamObject.paramNameThree);  
						$("#paramValueFour").val(data.data.sysParamObject.paramValueFour); 
						$("#paramNameFour").val(data.data.sysParamObject.paramNameFour);  
						$("#paramValueFive").val(data.data.sysParamObject.paramValueFive);  
						$("#paramNameFive").val(data.data.sysParamObject.paramNameFive);  								
						$("#remark").val(data.data.sysParamObject.remark);  
						$("#version").val(data.data.sysParamObject.version); 
						$("#createMan").val(data.data.sysParamObject.createMan); 
						$("#createTime").val(data.data.sysParamObject.createTime.substring(0, 19)); 
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

var menuNames = "系统参数配置";
// 保存
function save(form){
 	var sysParamId = form.field.sysParamId;
 	var paramValue = form.field.paramValue;
	var paramName = form.field.paramName;
	var paramValueOne = form.field.paramValueOne;
	var paramNameOne = form.field.paramNameOne;
	var paramValueTwo = form.field.paramValueTwo;
	var paramNameTwo = form.field.paramNameTwo;
	var paramValueThree = form.field.paramValueThree;
	var paramNameThree = form.field.paramNameThree;
	var paramValueFour = form.field.paramValueFour;
	var paramNameFour = form.field.paramNameFour;
	var paramValueFive = form.field.paramValueFive;
	var paramNameFive = form.field.paramNameFive;
	var statue = form.field.level1;//保存单选框的选中的值
	var remark =form.field.remark;
	var version =form.field.version;
	var createMan =form.field.createMan;
	var createTime =form.field.createTime;
	if (paramValue == null || paramValue == '') {
		alert("请填写参数编号！");
		return;
	}
	if (statue == null || statue == '') {
		alert("请选择状态！");
		return;
	}
	 
	var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	$.ajax({
		url : getCtxPath() + "/backend/sysParam/saveSysParam.do",
		method : 'post',
		dataType : 'json',
		data: {
			sysParamId : sysParamId,
			paramValue : paramValue,
			paramName : paramName,
			paramValueOne : paramValueOne,
			paramNameOne : paramNameOne,
			paramValueTwo : paramValueTwo,
			paramNameTwo : paramNameTwo,
			paramValueThree : paramValueThree,
			paramNameThree : paramNameThree,
			paramValueFour : paramValueFour,
			paramNameFour : paramNameFour,
			paramValueFive : paramValueFive,
			paramNameFive : paramNameFive,
			statue : statue,
			remark : remark,
			version:version,
			createTime:createTime,
			createMan:createMan,
			menuNames : menuNames,//系统参数配置菜单
			functionNames: (sysParamId != ''  ? "修改系统参数" : "新增系统参数") // 按钮功能名称 不为空修改 为空 新增
		},
		success : function(obj) {
 			if(obj.code == "2001" || obj.code == "4001"){
				layer.msg(obj.message , {icon: 1});//成功提示信息
				$("#save_bt").attr("style", "display:none");//按钮隐藏
				// 延迟一秒后跳转页面
				setTimeout(function(){
					closeIfrname();
//				parent.location.href = getCtxPath() + "/index/views/sysParam/sysParam.html";// 跳转页面
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
