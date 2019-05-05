			layui.config({
				base: '../../js/'
			});
			// 当前菜单名称
			var menuNames = "信用评分模板配置管理";
			
			$(function(){  
				 $("#state2").attr("checked",true);
				//我们强烈推荐你在代码最外层把需要用到的模块先加载
				layui.use(['layer', 'form', 'element','laydate'], function(){
					 var layer = layui.layer
					  ,form = layui.form()
					  ,element = layui.element();
					  form.render(); //刷新全部 
					  
					  // 监听radio单选
					  form.on('radio(radio_filter1)', function(data){
						  form.render(); //重新渲染
					  }); 
					  form.on('radio(radio_filter)', function(data){ 
						  form.render(); //重新渲染
					  }); 
					// 监听submit提交事件
					  form.on('submit(save)', function(data){
		 				  // 保存
						  save(data);
						  return false;// 阻止跳转页面，只有保存成功时才跳转
					  });
					  //开始时间
					  var start = {
							    format: 'YYYY-MM-DD hh:mm:ss'
							    ,istime: true
							    ,start: laydate.now(0,'YYYY-MM-DD 00:00:00')
							    ,choose: function(datas){
							      end.min = datas; //开始日选好后，重置结束日的最小日期
							      end.start = datas //将结束日的初始值设定为开始日
							    }
							};
						  //结束时间
							var end = {
							    format: 'YYYY-MM-DD hh:mm:ss'
							    ,istime: true
							    ,choose: function(datas){
							      start.max = datas; //结束日选好后，重置开始日的最大日期
							    }
							};
						  
						  $("#startTime").on("click", function(){
						    start.elem = this;
						    laydate(start);
						    if(laydate.now(0,'YYYY-MM-DD hh:mm:ss') == $(this).val().trim()){
							    $(this).val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
						    }
						  });
						  
						  $("#endTime").on("click", function(){
						    end.elem = this
						    laydate(end);
						  });
						  
					   
						var scoreTplId = getQueryString("scoreTplId"); 
						if (scoreTplId != '' && scoreTplId != undefined) {
							$("#scoreTplId").val(scoreTplId);
							$.ajax({
								url : getCtxPath() + "/backend/scScoreTpl/getEntityById.do?v=" + new Date().getTime(),
								method: "post",
								dataType:"json",
								data: {
									scoreTplId: scoreTplId
								},
								success: function(data){ 
									var stateval =data.list.scScoreTplObject.state;//回显单选框
					 				 
									if(stateval == 01){ 
			 							$("input[name='state']").eq(0).attr("checked","checked");
									}else{
										$("input[name='state']").eq(1).attr("checked","checked");
			 						} 
									var executeType =data.list.scScoreTplObject.executeType;//回显单选框 
									if(executeType == '1'){ 
			 							$("input[name='executeType']").eq(0).attr("checked","checked");
									}else{
										$("input[name='executeType']").eq(1).attr("checked","checked");
			 						}
//									$("#level").val(data.list.scScoreTplObject.level); 
									$("#scoreTplName").val(data.list.scScoreTplObject.scoreTplName);
									$("#scoreTplCode").val(data.list.scScoreTplObject.scoreTplCode);
									$("#startTime").val(data.list.scScoreTplObject.startTime.substring(0, 19));
									$("#endTime").val(data.list.scScoreTplObject.endTime.substring(0, 19)); 
//									$("#tplPercent").val(parseFloat((data.list.scScoreTplObject.tplPercent*100).toPrecision(12))); 
									
									$("#version").val(data.list.scScoreTplObject.version); 
 								},
								error: function(){  
									layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
								},
								complete: function(){
									form.render(); //刷新全部
								} 
							});
						} else{
							$("#state").attr("disabled","disabled");
							form.render(); //刷新全部
						}
				}); 
			}) 
			
			// 保存
			function save(form){ 
				var scoreTplName = $("#scoreTplName").val(); 
				var scoreTplId = getQueryString("scoreTplId");
				var scoreTplCode = $("#scoreTplCode").val(); 
				var startTime = $("#startTime").val(); 
				var endTime = $("#endTime").val();
				var state = form.field.state;//保存单选框的选中的值 
//				var tplPercent = $("#tplPercent").val();
				var version = $("#version").val();
//				var level = $("#level").val();
				var executeType = form.field.executeType;
 				
			 
//				if(level == '' || level === undefined){ 
//					 parent.layer.msg("优先级不能为空!" , {icon: 2});
//						return false;  
//				 };
				 if(executeType == '' || executeType === undefined){ 
					 parent.layer.msg("执行类型不能为空!" , {icon: 2});
						return false;  
				 };
				 
				if(scoreTplName == '' || scoreTplName === undefined){ 
					 parent.layer.msg("模板名称不能为空!" , {icon: 2});
						return false;  
				 };
				 
				 if(scoreTplCode == '' || scoreTplCode === undefined){ 
					 parent.layer.msg("模板业务编号不能为空!" , {icon: 2});
						return false;  
				 };
				 
				 if(scoreTplCode.length>9){ 
					 parent.layer.msg("模板业务编号不能超过9位数!" , {icon: 2});
						return false;  
				 };
				 
				 
				 if(startTime == '' || startTime === undefined){ 
					 parent.layer.msg("模板开始生效时间不能为空!" , {icon: 2});
						return false;  
				 };
				 if(endTime == '' || endTime === undefined){ 
					 parent.layer.msg("模板失效时间不能为空!" , {icon: 2});
						return false;  
				 };
				 if(state == '' || state === undefined){ 
					 parent.layer.msg("模板状态不能为空!" , {icon: 2});
						return false;  
				 }; 
				 
//				 if(tplPercent == '' || tplPercent === undefined){
//					 parent.layer.msg("模板权重不能为空!" , {icon: 2});
//						return false;  
//				 }else if (tplPercent<1 || tplPercent>100){
//					 parent.layer.msg("模板权重请输入1到100之间的正整数!" , {icon: 2});
//					    return false;  
//					};
						 
				 
				$.ajax({
					url : hostIp+ "/scScoreTpl/saveScScoreTpl.do",
					method : 'post',
					dataType : 'json',
					data: { 
						scoreTplId : scoreTplId,
						scoreTplName : scoreTplName, 
						scoreTplCode : scoreTplCode,  
						startTime : startTime,
						endTime : endTime,
						state  : state, 
//						tplPercent : parseInt(tplPercent)/100,
						version : version,
//						level : level,
						executeType : executeType,
						menuNames: menuNames, // 菜单名称
						functionNames: (scoreTplId.length > 0 ? "修改" : "新增") // 按钮功能名称
 					},
					success : function(data) {  
  						 if('2001' == data.code){ 
							parent.layer.msg("模版保存成功！" , {icon: 1}); 
							closeWindows();
							window.parent.getList();
 						}else if('102' == data.code){ 
							parent.layer.msg("模版已存在！" , {icon: 2}); 
							closeWindows();
 						}else if(data.code == "6005" ){
 							parent.layer.msg(data.message , {icon: 2}); 
 							closeWindows();
 						}else if(data.code == "6007" ){
 							parent.layer.msg(data.message , {icon: 2}); 
 							closeWindows();
 						}else if(data.code == "8001" ){
 							parent.layer.msg(data.message , {icon: 2}); 
 							closeWindows();
 						}else if(data.code == "8004" ){
 							parent.layer.msg(data.message , {icon: 2}); 
 							closeWindows();
 						}else{
							parent.layer.msg("模版保存失败！" , {icon: 2});
							closeWindows(); 
 						} 
					}, 
					error: function(){
 							layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
						 },
				}); 
			} 
			 
			function closeWindows() {
				//parent.window.location.reload();
				 var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		         parent.layer.close(index);
		         window.location = getCtxPath() + "/index/views/scScoreTpl/scScoreTpl.html";
				};