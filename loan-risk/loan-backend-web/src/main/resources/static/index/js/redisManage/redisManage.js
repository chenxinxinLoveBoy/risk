			layui.config({
				base: '../../js/' 
			});
			function sel() {
				getList(); 
			}; 
//			function del() {  
//				if(confirm('确定要删除次缓存？')){  
//					$.ajax({
//						url : hostIp+ "?v=" + new Date().getTime(),// 请求服务器的url
//						method: "post",
//						dataType:"json",
//						data: {
//							ids: ids
//						},
//						success: function(data){
//							if(data.code == 3001){ 
//								layer.msg("删除成功" , {icon: 1});
//							}else{
//								layer.msg("删除失败" , {icon: 2});
//							}
//							getList();
//						},
//						error: function(){  
//	 						layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
//						} 
//					});
//				} 
//
//			  }
			$(function(){
				layui.use(['form'], function() {
					var form = layui.form();
										 // 监听radio单选
					  form.on('radio(radio_filter0)', function(data){
						  form.render(); //重新渲染
					  }); 
				})	
			});
			function getList(){
				var key=$("#keyName").val().trim();
				var type=document.getElementsByName("type");
				var typeTemp="";
			    for(var i=0; i<type.length; i ++){
			        	if(type[i].checked){
			        		typeTemp=type[i].value;
			        	}
				}
				layui.use(['paging', 'layer', 'form', 'element','laydate'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form(),
						element = layui.element();

					  // 监听radio单选
					  form.on('radio(radio_filter0)', function(data){
						  form.render(); //重新渲染
					  }); 
					 
					// 页面初始化加载		start
					$.ajax({/*redis缓存*/
						url : getCtxPath() + "/backend/redisManage/getRedis.do?v=" + new Date().getTime(),
						method : "post",
						dataType : "json",
						data : {
							key:key,
							typeTemp:typeTemp
						},
						success : function(data) {
							//console.log(data);
							if(data.code == -200){
								$("#redisKey").html(key);
								$("#redisValue").html("无"+typeTemp+"缓存");
							}else if(JSON.stringify(data) == "{\"url\":null}"){
								$("#redisKey").html(key);
								$("#redisValue").html("无"+typeTemp+"缓存");
							}else{
								$("#redisKey").html(key);
								$("#redisValue").html(JSON.stringify(data));
							}
						},
						error : function() {
							alert("获取信息失败,请尝试重新获取！");
						}
					});
				});
			}