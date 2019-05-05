			layui.config({
				base: '../../js/'
			});
		  
			$(function(){  
				$("#date3").hide();
				 $("#date4").hide()
				 $("#state").attr("checked",true);
				//我们强烈推荐你在代码最外层把需要用到的模块先加载
				layui.use(['layer', 'form', 'element','laydate'], function(){
					 var layer = layui.layer
					  ,form = layui.form()
					  ,element = layui.element();
					  form.render(); //刷新全部 
					  
					  // 监听radio单选
//					  form.on('radio(radio_filter1)', function(data){
//						  if($('input:radio:checked').val()==1 ){
//							 $("#date3").hide();
//							 $("#date4").hide()
//							 $("#date").show();
//							 $("#date2").show() 
//						  }if($('input:radio:checked').val()==2){
//							  $("#date").hide();
//							  $("#date2").hide()
//							  $("#date3").show();
//							  $("#date4").show()
//						  }
//						  form.render(); //重新渲染
//					  }); 
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
						  
					   
						var banControlTplId = getQueryString("banControlTplId"); 
						if (banControlTplId != '' && banControlTplId != undefined) {
							$("#banControlTplId").val(banControlTplId);
							$.ajax({
								url : getCtxPath() + "/backend/sctemplate/getEntityById.do?v=" + new Date().getTime(),
								method: "post",
								dataType:"json",
								data: {
									banControlTplId: banControlTplId
								},
								success: function(data){ 
									var stateval =data.list.templateObject.state;//回显单选框
					 				 
									if(stateval == 01){ 
			 							$("input[name='state']").eq(0).attr("checked","checked");
									}else{
										$("input[name='state']").eq(1).attr("checked","checked");
			 						} 
									var executeType =data.list.templateObject.executeType;//回显单选框 
									if(executeType == '1'){ 
			 							$("input[name='executeType']").eq(0).attr("checked","checked");
									}else{
										$("input[name='executeType']").eq(1).attr("checked","checked");
			 						}
//									$("#level").val(data.list.templateObject.level); 
									$("#banTplName").val(data.list.templateObject.banTplName);
									$("#banTplCode").val(data.list.templateObject.banTplCode);
									$("#startTime").val(data.list.templateObject.startTime.substring(0,19));
									$("#endTime").val(data.list.templateObject.endTime.substring(0,19)); 
//									$("#tplPercent").val(parseFloat((data.list.templateObject.tplPercent*100).toPrecision(12))); 
									$("#version").val(data.list.templateObject.version); 
									$("#operation").val(data.list.templateObject.operation); 
								},
								error: function(){  
									layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
								},
								complete: function(){
									form.render(); //刷新全部
								} 
							});
						}else{
							$("#state").attr("disabled","disabled");
							form.render(); //刷新全部
						}
				}); 
			}) 
			// 当前菜单名称
			var menuNames = "禁止项规则模版配置";
			// 保存
			function save(form){ 
				var banTplName = $("#banTplName").val(); 
				var banControlTplId = getQueryString("banControlTplId");
				var banTplCode = $("#banTplCode").val(); 
				var startTime = $("#startTime").val(); 
//				var endTimehms = $("#endTimehms").val(); 
//				var startTimehms = $("#startTimehms").val(); 
				var endTime = $("#endTime").val();
				var state = form.field.state;//保存单选框的选中的值 
//				var tplPercent = $("#tplPercent").val();
				var version = $("#version").val();
//				var level = $("#level").val();
				var executeType = form.field.executeType;
				var operation = $("#operation").val(); 
				
//				if(startTime == '' || startTime === undefined){ 
//					startTime = startTimehms;
//				 };
//				 if(endTime == '' || endTime === undefined){ 
//					 endTime = endTimehms;
//				 };
				 
				 if(banTplName == '' || banTplName === undefined){ 
					 parent.layer.msg("模板名称不能为空!" , {icon: 2});
						return false;  
				 };
				 
				 if(banTplCode == '' || banTplCode === undefined){ 
					 parent.layer.msg("业务编号不能为空!" , {icon: 2});
						return false;  
				 };
				 if(executeType == '' || executeType === undefined){ 
					 parent.layer.msg("请选择执行类型!" , {icon: 2});
						return false;  
				 };
				 if(startTime == '' || startTime === undefined){ 
					 parent.layer.msg("模板开始生效日期或时间段不能为空!" , {icon: 2});
						return false;  
				 };
				 if(endTime == '' || endTime === undefined){ 
					 parent.layer.msg("模板失效日期或时间段不能为空!" , {icon: 2});
						return false;  
				 };
				 if(state == '' || state === undefined){ 
					 parent.layer.msg("请选择模板状态!" , {icon: 2});
						return false;  
				 }; 
				 
//				 if(tplPercent == '' || tplPercent === undefined){
//					 parent.layer.msg("模板权重不能为空!" , {icon: 2});
//					 return false;  
//				 }else if (isNaN(tplPercent)){
//					 parent.layer.msg("模板权重请输入数字!" , {icon: 2});
//					 return false;  
//				 }else if (tplPercent<1 || tplPercent>100){
//					 parent.layer.msg("模板权重请输入1到100之间的正整数!" , {icon: 2});
//					 return false;  
//				 };
				 
//				 if(level == '' || level === undefined){ 
//					 parent.layer.msg("优先级不能为空!" , {icon: 2});
//						return false;  
//				 }else if (isNaN(level)){
//					 parent.layer.msg("优先级请输入数字!" , {icon: 2});
//					 return false;  
//				 };

				$.ajax({
					url : getCtxPath() + "/backend/sctemplate/savetemplate.do",
					method : 'post',
					dataType : 'json',
					data: { 
						banControlTplId : banControlTplId,
						banTplName : banTplName, 
						banTplCode : banTplCode,  
						startTime : startTime,
						endTime : endTime,
						state  : state, 
//						tplPercent : parseInt(tplPercent)/100,
						version : version,
//						level : level,
						executeType : executeType,
						operation : operation,
						menuNames : menuNames,
						functionNames: (banControlTplId != '' > 0 ? "修改禁止项模版配置" : "新增禁止项模版配置") // 按钮功能名称
					},
					success : function(data) {  
						if('2001' == data.code){ 
							parent.layer.msg("禁止项模版保存成功！" , {icon: 1}); 
							// 延迟一秒后跳转页面
							setTimeout(function(){
								closeIfrname();
								window.parent.getList();
							},1000);
							return false;
						}else if('102' == data.code){ 
							parent.layer.msg("禁止项模版编号已存在！" , {icon: 2}); 
							return false;
						}else if(data.code == "8004" ){
 							parent.layer.msg(data.message , {icon: 2}); 
 							closeWindows();
 						}else{
							parent.layer.msg(data.message , {icon: 2}); 
							return false;
						} 
					}, 
					error: function(){
						parent.layer.msg("网络请求异常，请尝试重新登陆！" , {icon: 2});//提示信息
					},
				}); 
			}; 
			
			// 关闭
			function closeIfrname(){ 
				var  frameindex= parent.layer.getFrameIndex(window.name);  
				parent.layer.close(frameindex);
				
			}