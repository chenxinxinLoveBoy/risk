			layui.config({
				base: '../../js/'
			});
			// 当前菜单名称
			var menuNames = "提额配置管理";
			var channelName="",appLevel="",isAccumulationFund="",isSocialSecurity="",repaymentTimes="",creditMoney="";
			$(function(){  
 				//我们强烈推荐你在代码最外层把需要用到的模块先加载
				layui.use(['layer', 'form', 'element'], function(){
					 var layer = layui.layer
					  ,form = layui.form()
					  ,element = layui.element();
					  form.render(); //刷新全部 
					  
					  // 监听radio单选
					  form.on('radio(radio_filter)', function(data){ 
						  form.render(); //重新渲染
					  }); 
					  
					  form.on('radio(radio_filter1)', function(data){ 
						  form.render(); //重新渲染
					  }); 
					  form.on('radio(radio_filter2)', function(data){ 
						  form.render(); //重新渲染
					  }); 
					  form.on('radio(radio_filter3)', function(data){ 
						  form.render(); //重新渲染
					  }); 
					  
					  //监听select
					  form.on('select(channelName)', function(data){
						  channelName =  data.value;
					  });   
					  
					  //监听select
					  form.on('select(appLevel)', function(data){
						  appLevel =  data.value;
					  });   
					  
					//监听select
					  form.on('select(isAccumulationFund)', function(data){
						  isAccumulationFund =  data.value;
					  });  
					  
					  //监听select
					  form.on('select(isSocialSecurity)', function(data){
						  isSocialSecurity =  data.value;
					  });   
					  
					  //监听select
					  form.on('select(creditMoney)', function(data){
						  creditMoney =  data.value;
					  });
					  
					  //监听select
					  form.on('select(repaymentTimes)', function(data){
						  repaymentTimes =  data.value;
					  });   
					  
					//监听select
//					  form.on('select(todayTimes)', function(data){
//						  todayTimes =  data.value;
//					  }); 
					  
					  
					// 监听submit提交事件
					  form.on('submit(save)', function(data){
		 				  // 保存
						  save(data);
						  return false;// 阻止跳转页面，只有保存成功时才跳转
					  });
					 
						  getTplList(form);
					   
						var scLiftConfigurationId = getQueryString("scLiftConfigurationId"); 
						if (scLiftConfigurationId != '' && scLiftConfigurationId != undefined) {
							$("#scLiftConfigurationId").val(scLiftConfigurationId);
							$.ajax({
								url : getCtxPath() + "/backend/scLiftConfiguration/getEntityById.do?v=" + new Date().getTime(),
								method: "post",
								dataType:"json",
								data: {
									scLiftConfigurationId: scLiftConfigurationId
								},
								success: function(data){ 
									var stateval =data.list.scLiftConfigurationObject.state;//回显单选框
									if(stateval == 01){ 
			 							$("input[name='state']").eq(0).attr("checked","checked");
									}else{
										$("input[name='state']").eq(1).attr("checked","checked");
			 						} 
									var mateNameval =data.list.scLiftConfigurationObject.mateName;//回显单选框
									if(mateNameval == 0){ 
			 							$("input[name='mateName']").eq(0).attr("checked","checked");
									}else{
										$("input[name='mateName']").eq(1).attr("checked","checked");
			 						} 
									var matePhoneval =data.list.scLiftConfigurationObject.matePhone;//回显单选框
									if(matePhoneval == 0){ 
			 							$("input[name='matePhone']").eq(0).attr("checked","checked");
									}else{
										$("input[name='matePhone']").eq(1).attr("checked","checked");
			 						} 
									var mateCertCodeval =data.list.scLiftConfigurationObject.mateCertCode;//回显单选框
									if(mateCertCodeval == 0){ 
			 							$("input[name='mateCertCode']").eq(0).attr("checked","checked");
									}else{
										$("input[name='mateCertCode']").eq(1).attr("checked","checked");
			 						} 
									$("#todayTimes").val(data.list.scLiftConfigurationObject.todayTimes); 
									$("#percentageOfLiftMantissa").val(data.list.scLiftConfigurationObject.percentageOfLiftMantissa); 
   									$("#percentageOfLift").val(parseFloat((data.list.scLiftConfigurationObject.percentageOfLift*100).toPrecision(12))); 
									$("#version").val(data.list.scLiftConfigurationObject.version);
     								$("#select_creditMoney").find("option[value='"+data.list.scLiftConfigurationObject.creditMoney+"']").attr("selected",true);
    								$("#channelName").find("option[value='"+data.list.scLiftConfigurationObject.channelName+"']").attr("selected",true);
									$("#select_appLevel").find("option[value='"+data.list.scLiftConfigurationObject.appLevel+"']").attr("selected",true);
 									$("#select_isAccumulationFund").find("option[value='"+data.list.scLiftConfigurationObject.isAccumulationFund+"']").attr("selected",true);
									$("#select_isSocialSecurity").find("option[value='"+data.list.scLiftConfigurationObject.isSocialSecurity+"']").attr("selected",true);
									$("#select_repaymentTimes").find("option[value='"+data.list.scLiftConfigurationObject.repaymentTimes+"']").attr("selected",true);
//									$("#select_todayTimes").find("option[value='"+data.list.scLiftConfigurationObject.todayTimes+"']").attr("selected",true);
  								},
								error: function(){  
									layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
								},
								complete: function(){
									form.render(); //刷新全部
								} 
							});
						} else{
//							$("#state").attr("disabled","disabled");
							form.render(); //刷新全部
						}
				}); 
			}) 
			
			// 保存
			function save(form){ 
				var state = form.field.state;//保存单选框的选中的值 
				var mateName = form.field.mateName;//保存单选框的选中的值 
				var matePhone = form.field.matePhone;//保存单选框的选中的值 
				var mateCertCode = form.field.mateCertCode;//保存单选框的选中的值 
				var percentageOfLiftMantissa = $("#percentageOfLiftMantissa").val(); 
				var scLiftConfigurationId = getQueryString("scLiftConfigurationId");
				var percentageOfLift = $("#percentageOfLift").val(); 
    		    var version = $("#version").val();
    		    var todayTimes = $("#todayTimes").val();
   				var channelName = form.field.channelName;
 				var creditMoney = form.field.creditMoney;
				var appLevel = form.field.appLevel;
 				var isAccumulationFund = form.field.isAccumulationFund;
 			    var isSocialSecurity=form.field.isSocialSecurity;
 			    var repaymentTimes = form.field.repaymentTimes;
//			    var todayTimes=form.field.todayTimes;
				 
 			    
 			    if(mateName == '' || mateName === undefined){ 
					 parent.layer.msg("请选择是否匹配姓名！" , {icon: 2});
						return false;  
				 }; 
				 
				if(matePhone == '' || matePhone === undefined){ 
					 parent.layer.msg("请选择是否匹配手机号！" , {icon: 2});
						return false;  
				 }; 
				 
				if(mateCertCode == '' || mateCertCode === undefined){ 
					 parent.layer.msg("请选择是否匹配证件号码！" , {icon: 2});
						return false;  
				 }; 
				 
				 if(mateName != 1 && matePhone != 1 && mateCertCode != 1){ 
					 parent.layer.msg("是否匹配手机号、是否匹配证件号码、是否匹配姓名中至少有一种选择为是！" , {icon: 2});
						return false;  
				 }; 
				 
				 
 			    if(state == '' || state === undefined){ 
					 parent.layer.msg("提额配置状态不能为空!" , {icon: 2});
						return false;  
				 }; 
				 
				if(percentageOfLiftMantissa == '' || percentageOfLiftMantissa === undefined){ 
					 parent.layer.msg("提额尾数不能为空!" , {icon: 2});
						return false;  
				 };
				 
			     if(todayTimes == '' || todayTimes === undefined){ 
					 parent.layer.msg("当天借当天还次数不能为空!" , {icon: 2});
						return false;  
				 }else if (todayTimes<=0 ){
					 parent.layer.msg("当天借当天还次数请输入正整数!" , {icon: 2});
					    return false;  
					} ;
				 
				 if(percentageOfLift == '' || percentageOfLift === undefined){
					 parent.layer.msg("提额百分比不能为空!" , {icon: 2});
						return false;  
				 }else if (percentageOfLift<1 || percentageOfLift>100){
					 parent.layer.msg("提额百分比请输入1到100之间的正整数!" , {icon: 2});
					    return false;  
					};
						 
				 
				$.ajax({
					url : hostIp+ "/scLiftConfiguration/saveScLiftConfiguration.do",
					method : 'post',
					dataType : 'json',
					data: { 
						scLiftConfigurationId : scLiftConfigurationId,
						percentageOfLiftMantissa : percentageOfLiftMantissa, 
						percentageOfLift : parseInt(percentageOfLift)/100,
						version : version,
						channelName : channelName,
						appLevel : appLevel,
						state  : state, 
						mateName:mateName,
						matePhone:matePhone,
						mateCertCode:mateCertCode,
						creditMoney:creditMoney,
						isAccumulationFund:isAccumulationFund,
						isSocialSecurity:isSocialSecurity,
						repaymentTimes:repaymentTimes,
						todayTimes:todayTimes,
						menuNames: menuNames, // 菜单名称
						functionNames: (scLiftConfigurationId.length > 0 ? "修改" : "新增") // 按钮功能名称
 					},
					success : function(data) {  
  						 if('2001' == data.code){ 
							parent.layer.msg("保存成功！" , {icon: 1}); 
							closeWindows();
							window.parent.getList();
 						}else if('9001' == data.code){ 
 							parent.layer.msg(data.message , {icon: 2}); 
 							closeWindows();
 						}else if('7002' == data.code){ 
 							parent.layer.msg(data.message , {icon: 2}); 
 							closeWindows();
 						}else{
							parent.layer.msg("保存失败！" , {icon: 2});
							closeWindows(); 
 						} 
					}, 
					error: function(){
 							layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
						 },
				}); 
			} 
			 
			function closeWindows() {
 				 var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
		         parent.layer.close(index);
		         window.location = getCtxPath() + "/index/views/scLiftConfiguration/scLiftConfiguration.html";
				};
				
				
				function getTplList(form) {
					$.ajax({
						url : getCtxPath() + "/backend/appBigChannel/getChannelByBigName.do?v=" + new Date().getTime(),
						method : "post",
						dataType : "json",
						async: false,
						data : {
							pageIndex:0,
							pageSize:1000
						},
						success : function(data) {
							var arr = [];
							arr.push('<select name="channelName" id = "channelName" class="layui-input" lay-search="">');
							layui.each(data.list, function(index, item){
									if(channelName === item.channelName){
								    	arr.push('<option value="'+ item.channelBigName + '" selected>' + item.channelBigName + '</option>');
									}else{
								    	arr.push('<option value="'+ item.channelBigName + '">' + item.channelBigName + '</option>');
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
				
				
				// 关闭
				function closeIfrname(){
//					layer.closeAll();
					var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(frameindex);
				}