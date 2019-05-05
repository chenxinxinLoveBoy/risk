	layui.config({
				base: '../../js/' 
			});
 
	var creditType;
	var ruleComparisonType;
	var validateRule;
	var scoreTplId="";

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
			  form.on('select(scoreTplId)', function(data){
				  scoreTplId =  data.value;
			  });   
			  // 监听submit提交事件
			  form.on('submit(save)', function(data){
 				  // 保存
				  save(data);
				  return false;// 阻止跳转页面，只有保存成功时才跳转
			  });
			  getTplList(form);

			  
				var id = getQueryString("id");
				if (id == null || id == '') return;
		  		$.ajax({
					url : getCtxPath() + "/backend/scoreCredit/getEntityById.do",
					method: "post",
					dataType:"json",
					data: {
						scoreCreditId: id
					},
					success: function(data){
						var state =data.data.scoreCreditObject.state//回显单选框
 		 				 
						if(state == "01"){ 
 							$("input[name='level1']").eq(0).attr("checked","checked");
						}else{
							$("input[name='level1']").eq(1).attr("checked","checked");
 						}
					    $("#scoreCreditId").val(data.data.scoreCreditObject.scoreCreditId); 
						$("#scoreMin").val(data.data.scoreCreditObject.scoreMin); 
						$("#scoreMax").val(data.data.scoreCreditObject.scoreMax);
						$("#creditMoney").val(data.data.scoreCreditObject.creditMoney);  
						$("#promoteCreditMoney").val(data.data.scoreCreditObject.promoteCreditMoney); 
						$("#remark").val(data.data.scoreCreditObject.remark);  
						$("#version").val(data.data.scoreCreditObject.version); 
						$("#createMan").val(data.data.scoreCreditObject.createMan); 
						$("#createTime").val(data.data.scoreCreditObject.createTime.substring(0, 19));  
						$("#scoreTplId").find("option[value='"+data.data.scoreCreditObject.scoreTplId+"']").attr("selected",true);
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

var menuNames = "评分与授信额度配置";
// 保存
function save(form){
 	var scoreCreditId = form.field.scoreCreditId;
 	var scoreMin = form.field.scoreMin;
	var scoreMax = form.field.scoreMax;
	var creditMoney = form.field.creditMoney;
	var state = form.field.level1;//保存单选框的选中的值
	var remark =form.field.remark;
	var version =form.field.version;
	var createMan =form.field.createMan;
	var createTime =form.field.createTime;
	var scoreTplId = form.field.scoreTplId;
	var promoteCreditMoney=form.field.promoteCreditMoney;

	if (scoreMin == null || scoreMin == '') {
		alert("请填写分数最小值！");
		return;
	}
//	if (parseInt(scoreMin) < 0) {
//		alert("填写分数最小值不能小于零！");
//		return;
//	}
	if (scoreMax == null || scoreMax == '') {
		alert("请填写分数最大值！");
		return;
	}
//	if (parseInt(scoreMax) < 0) {
//		alert("填写分数最大值不能小于零！");
//		return;
//	}
	if (parseInt(scoreMax) <= parseInt(scoreMin)) {
		alert(" 填写分数应大于分数最小值！");
		return;
	}
	if (creditMoney == null || creditMoney == '') {
		alert("请填写授信额度值！");
		return;
	}
	var reg = /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
	  if(reg.test(creditMoney) === false){
		  alert("所填金额不正确！");
		  return;
	 }
	  
	 if (promoteCreditMoney == null || promoteCreditMoney == '') {
			alert("请填写提升授信额度值！");
			return;
		}
	 
 	 if(reg.test(promoteCreditMoney) === false){
			  alert("所填提升授信额度金额不正确！");
			  return;
		 } 
	  
	if (state == null || state == '') {
		alert("请选择状态！");
		return;
	}
	 
	var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	$.ajax({
		url : getCtxPath() + "/backend/scoreCredit/saveScoreCredit.do",
		method : 'post',
		dataType : 'json',
		data: {
			scoreCreditId : scoreCreditId,
			scoreMin : scoreMin,
			scoreMax : scoreMax,
			creditMoney : creditMoney,
			promoteCreditMoney : promoteCreditMoney,
			state : state,
			remark : remark,
			version:version,
			createTime:createTime,
			scoreTplId:scoreTplId,
			createMan:createMan,
			menuNames : menuNames,//评分与受信额度配置
			functionNames: (scoreCreditId != ''  ? "修改评分与授信额度" : "新增评分与授信") // 按钮功能名称 不为空修改 为空 新增
		},
		success : function(obj) {
 			if(obj.code == "2001" || obj.code == "4001"){
				layer.msg(obj.message , {icon: 1});//成功提示信息
				$("#save_bt").attr("style", "display:none");//按钮隐藏
				// 延迟一秒后跳转页面
				setTimeout(function(){
					closeIfrname();
//					parent.location.href = getCtxPath() + "/index/views/scoreCredit/scoreCredit.html";// 跳转页面
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
function getTplList(form) {
	$.ajax({
		url : getCtxPath() + "/backend/scScoreTpl/scScoreTplListAll.do?v=" + new Date().getTime()+"&state=01",
		method : "post",
		dataType : "json",
		async: false,
		data : {
		},
		success : function(data) {
			var arr = [];
			arr.push('<select name="scoreTplId" id = "scoreTplId" class="layui-input" lay-search="">');
			layui.each(data.list, function(index, item){
					if(scoreTplId === item.scoreTplId){
				    	arr.push('<option value="'+ item.scoreTplId + '" selected>' + item.scoreTplName + '</option>');
					}else{
				    	arr.push('<option value="'+ item.scoreTplId + '">' + item.scoreTplName + '</option>');
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
