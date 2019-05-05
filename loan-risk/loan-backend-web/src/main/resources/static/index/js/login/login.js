			layui.config({
				base: 'js/'
			});
		
			// 初始化
			$(function(){
				getList();
			})

			function getList(){
				layui.use(['layer','upload','paging', 'form'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form();
					
					//自定义验证规则
					  form.verify({
						  userName: function(value){
					      if(value.length < 1){
					        return '请填写登录帐号';
					      }
					    },
					    password: function(value){
					    	if(value.length < 1){
						        return '请输入密码';
						     }
					    }
					  });
					// 后台判断帐号或密码错误，重定向带过来的参数
//					var isError = getQueryString("isError");  
//					if(isError == "1"){
//						layer.msg("帐号或密码不正确" , {icon: 2});//失败提示信息
//					}else if(isError == "2"){
//						layer.msg("帐号已被禁止登录，请联系管理员" , {icon: 2});//帐号被禁止提示信息
//					}else if(isError == "3"){
//						layer.msg("帐号已被冻结，请联系管理员" , {icon: 5});//帐号被冻结提示信息
//					}
//					
//					form.on('submit(login)', function(data){
					  form.on('submit(login)', function(data){
//				        var password2 = encode64($("#password").val().trim());   //对数据加密  
//				        $("#password").val(password2);
						  console.log(getCtxPath());
						var url = getCtxPath() + "/backend/login/ajaxLogin.do";
//						$("#form_id").attr("action", url);
//						$("#form_id").submit();
						$.ajax({
							url: url,
							method: "post",
							async: false,// 同步
							data: {
								userName: data.field.userName,
								password: data.field.password
							 
							},
							success:function(obj){
								if(obj.code == "200"){
									layer.msg("登录成功" , {icon: 1});//成功提示信息
									$("#login_bt").attr("disabled", "false");
									// 延迟一秒后跳转页面
									setTimeout(function(){
										window.location.href = getCtxPath() + "/index/index.html";// 跳转页面
									},1000)
								}
								if(obj.isError == "1"){
									layer.msg("帐号或密码不正确" , {icon: 2});//失败提示信息
									return false;// 阻止跳转页面，只有保存成功时才跳转
								}
								if(obj.isError == "2"){
									layer.msg("帐号已被禁止登录，请联系管理员" , {icon: 2});//帐号被禁止提示信息
									return false;// 阻止跳转页面，只有保存成功时才跳转
								}
								if(obj.isError == "3"){
									layer.msg("帐号已被冻结，请联系管理员" , {icon: 5});//帐号被冻结提示信息
									return false;// 阻止跳转页面，只有保存成功时才跳转
								}
							},
							error: function(){
								layer.msg("网络请求异常，请稍后再试！" , {icon: 2});//失败提示信息
							}
						});
						return false;// 阻止跳转页面，只有登录成功时才跳转
					});
				});
			}	 
			
 			