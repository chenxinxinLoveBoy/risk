	layui.config({
				base: '../../js/' 
			});
 
	var creditType;
	
	var stateVal = ""; // 判断大类在关闭时用到
	var isScScoreSmall = false ;// 大类下是否有小类
	// 当前菜单名称
	var menuNames = "新增评分卡大类配置";
	
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
				}
		  		$.ajax({
					url :getCtxPath() + "/backend/scScoreBig/getEntityById.do",
					method: "post",
					dataType:"json",
					data: {
						scoreBigId: id
					},
					success: function(data){
						var state =data.data.scScoreBigObject.state//回显单选框
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
					    $("#scoreBigId").val(data.data.scScoreBigObject.scoreBigId); 
						$("#scoreBigCode").val(data.data.scScoreBigObject.scoreBigCode); 
						
						$("#scoreName").val(data.data.scScoreBigObject.scoreName);
						$("#select_creditType").find("option[value='"+data.data.scScoreBigObject.creditType+"']").attr("selected",true);
						$("#scoreRuleDetail").val(data.data.scScoreBigObject.scoreRuleDetail);  
						$("#remark").val(data.data.scScoreBigObject.remark);  
						$("#version").val(data.data.scScoreBigObject.version); 
						$("#percent").val(data.data.scScoreBigObject.percent); 
						$("#createMan").val(data.data.scScoreBigObject.createMan); 
						$("#createTime").val(data.data.scScoreBigObject.createTime.substring(0, 19));  
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
 	var scoreBigId = form.field.scoreBigId;
 	var scoreBigCode = form.field.scoreBigCode;
	var scoreName = form.field.scoreName;
	var creditType = form.field.creditType;
	var scoreRuleDetail = form.field.scoreRuleDetail;
	var state = form.field.level1;//保存单选框的选中的值
	var remark =form.field.remark;
	var version =form.field.version;
	var createMan =form.field.createMan;
	var createTime =form.field.createTime;
	var percent =form.field.percent; 
	if (scoreBigCode == null || scoreBigCode == '') {
		alert("请填写评分项规则大类编号！");
		return;
	}
	if (scoreName == null || scoreName == '') {
		alert("请填写评分项名称！");
		return;
	}
	if (creditType == null || creditType == '') {
		alert("请选择征信机构类型！");
		return;
	}
	if (scoreRuleDetail == null || scoreRuleDetail == '') {
		alert("请填写评分项规则明细！");
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
	var updateScScoreSmall = "";// 传到后台用，判断是否将大类下的小类状态置为失效
	// 大类下有小类， 并且由生效改为失效的时候，给出提醒
	if(isScScoreSmall && stateVal == "01" && stateVal != state){
		if(confirm("当前大类下包含小类数据，确认失效, 小类下的数据也会失效，是否继续？")){
			updateScScoreSmall = "1";
		}else{
			isUpdate = false;
		}
	}
	
	if(isUpdate){
		var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		$.ajax({
			url : getCtxPath() + "/backend/scScoreBig/saveScScoreBig.do",
			method : 'post',
			dataType : 'json',
			data: {
				scoreBigId : scoreBigId,
				scoreBigCode : scoreBigCode,
				scoreName : scoreName,
				creditType : creditType,
				scoreRuleDetail : scoreRuleDetail,
				state : state,
				remark : remark,
				version:version,
				createTime:createTime,
				createMan:createMan,
				percent:percent,
				updateScScoreSmall: updateScScoreSmall,
				menuNames : menuNames,//新增评分卡大类配置
				functionNames: (scoreBigId != ''  ? "修改评分卡大类" : "新增评分卡大类") // 按钮功能名称 不为空修改 为空 新增
			},
			success : function(obj) {
	 			if(obj.code == "2001" || obj.code == "4001"){
					layer.msg(obj.message , {icon: 1});//成功提示信息
					$("#save_bt").attr("style", "display:none");//按钮隐藏
					// 延迟一秒后跳转页面
					setTimeout(function(){
						closeIfrname();
//						parent.location.href = getCtxPath()  + "/index/views/scScoreBig/scScoreBig.html";// 跳转页面
						var typeId = getQueryString("typeId");
						if(typeId == "1"){
							window.parent.getScScoreBig();//刷新树形图页面
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
