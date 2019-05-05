			layui.config({
				base: '../../js/'
			});
		  
			$(function(){   
				//我们强烈推荐你在代码最外层把需要用到的模块先加载
				layui.use(['layer', 'form', 'element','laydate'], function(){
					 var layer = layui.layer
					  ,form = layui.form()
					  ,element = layui.element();
					  form.render(); //刷新全部 
					  
					  // 监听radio单选
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
					   
						var banControlTplHisId = getQueryString("banControlTplHisId");  
						if (banControlTplHisId != '' && banControlTplHisId != undefined) {
							/*$("#banTplName").attr("disabled","disabled");
							 $("#banTplCode").attr("disabled","disabled");
							 $("#tplPercent").attr("disabled","disabled");
							 $("#startTime").attr("disabled","disabled");
							 $("#endTime").attr("disabled","disabled");*/
							$.ajax({
								url : getCtxPath() + "/backend/ScTemplateHist/getEntityById.do?v=" + new Date().getTime(),
								method: "post",
								dataType:"json",
								data: {
									banControlTplHisId: banControlTplHisId
								},
								success: function(data){ 
									var stateval =data.list.templateHistObject.state;//回显单选框
								  
									if(stateval == 01){ 
			 							$("input[name='state']").eq(0).attr("checked","checked");
									}else{
										$("input[name='state']").eq(1).attr("checked","checked");
			 						} 
									var executeType =data.list.templateHistObject.executeType;//回显单选框 
									if(executeType == '1'){ 
			 							$("input[name='executeType']").eq(0).attr("checked","checked");
									}else{
										$("input[name='executeType']").eq(1).attr("checked","checked");
			 						}
									$("#level").val(data.list.templateHistObject.level); 
									$("#banTplName").val(data.list.templateHistObject.banTplName);
									$("#banTplCode").val(data.list.templateHistObject.banTplCode);
									$("#startTime").val(data.list.templateHistObject.startTime.substring(0,19));
									$("#endTime").val(data.list.templateHistObject.endTime.substring(0,19)); 
									$("#tplPercent").val(parseFloat(data.list.templateHistObject.tplPercent)*100); 
									$("#version").val(data.list.templateHistObject.version); 
									$("#operation").val(data.list.templateHistObject.operation); 
								},
								error: function(){  
									layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
								},
								complete: function(){
									form.render(); //刷新全部
								} 
							});
						} 
				}); 
			});