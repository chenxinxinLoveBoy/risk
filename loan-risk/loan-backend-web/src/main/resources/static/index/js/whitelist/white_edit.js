			layui.config({
				base: '../../js/'
			});
		
			// 当前菜单名称
			var menuNames = "白名单列表";
			$(function(){
 				var id = getQueryString("id"); 	 
 				var edit = getQueryString("edit"); 
				// 回显
				if(id !='' && id != undefined && edit == 1){   
					$.ajax({
						url : hostIp+ "/whiterlist/getObjectById.do",
						method: "post",
						dataType:"json",
						data: {
							whiteListId : id
						},
						success: function(data){ 
							$("#certCode").val(data.list.srwObject.certCode);  
							$("#name").val(data.list.srwObject.name); 
							$("#phoneNum").val(data.list.srwObject.phoneNum); 
							$("#appName").val(data.list.srwObject.appName);
							$("#state").val(data.list.srwObject.state);
							$("#certType").val(data.list.srwObject.certType);
						},
						error: function(){
							parent.layer.msg("网络请求异常,请尝试重新登录" , {icon: 2}); 
						},
						complete: function(){ 
						} 
					});	
				}
			});
			  
			 			 
			// 关闭
			function closeIfrname(){ 
				var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(frameindex);
			};
			
			// 保存
			function save(){
				var id = getQueryString("id"); 
				var certCode = $("#certCode").val().trim(); 
				var name = $("#name").val().trim(); 
				var phoneNum = $("#phoneNum").val().trim(); 
				var appName = $("#appName").val().trim();
				var state = $("#state").val().trim(); 
				var certType = $("#certType").val().trim();
				
				if(name == '' || name === undefined){ 
					 parent.layer.msg("用户姓名不能为空!" , {icon: 2});
						return false;  
				 };
				 if(name != '' && name != undefined){
						if(!(/^[A-Za-z0-9\u4e00-\u9fa5]+$/.test(name))){ 
							parent.layer.msg("用户名不能包含特殊字符!" , {icon: 2});
					        return false; 
					    }
					};
				 if(certCode == '' || certCode == undefined){ 
					 parent.layer.msg("用户身份证号不能为空!" , {icon: 2});
						return false;  
				 };
				 if(phoneNum == '' || phoneNum == undefined){ 
					 parent.layer.msg("手机号码不能为空!" , {icon: 2});
						return false;  
				 };
				 
				 if(certCode != '' && certCode != undefined){ 
					 var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
					   if(reg.test(certCode) === false){  
						   parent.layer.msg("身份证号输入不正确" , {icon: 2});
							return false;  
					   }  
				 };
				 
				 if(phoneNum != '' && phoneNum != null){
						if(!(/^1[34578]\d{9}$/.test(phoneNum))){ 
							   parent.layer.msg("手机号码有误，请重填" , {icon: 2}); 
					        return false; 
					    }
				};
				var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				$.ajax({
					url : hostIp+ "/whiterlist/save.do",
					method : 'post',
					dataType : 'json',
					data: {
						whiteListId : id,
						certCode : certCode, // 传到后台的参数，子窗口的form表单
						name : name,  
						phoneNum : phoneNum,
						appName : appName,
						state  : state,
						certType : certType,
						menuNames : menuNames,
						functionNames: (id != ''  ? "修改白名单" : "新增白名单") // 按钮功能名称
					},
					success : function(data) {  
						if('102' == data.code){
							parent.layer.msg("用户已存在白名单内！");
							return false;
						}if('2001' == data.code){ 
							parent.layer.msg("白名单保存成功！" , {icon: 1});
							closeIfrname(); 
							window.parent.getList();
						} else{
							parent.layer.msg("白名单保存失败!！" , {icon: 1});
							closeIfrname(); 
							window.parent.getList();
						}
						
					},
					  
					error: function(){
						parent.layer.msg("网络请求异常，请尝试重新登陆！" , {icon: 2});//提示信息
					},
					complete: function(){  
					} 
				}); 
			}