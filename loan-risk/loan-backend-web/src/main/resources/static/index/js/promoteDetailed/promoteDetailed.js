			layui.config({
				base: '../../js/' 
			});
			
			
			// 初始化
			$(function(){
				layui.use(['laydate'], function() {
					var laydate = layui.laydate;
					var start = {
					    format: 'YYYY-MM-DD hh:mm:ss'
						,istime: true
					    ,choose: function(datas){
					      end.min = datas; //开始日选好后，重置结束日的最小日期
					      end.start = datas //将结束日的初始值设定为开始日
					    }
					};
				  
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
					$("#startTime").val(layui.laydate.now(0,'YYYY-MM-DD 00:00:00'));
					$("#endTime").val(layui.laydate.now(0,'YYYY-MM-DD 23:59:59'));
					getList();
				});
			})
			function reset(){//重置
				layui.use(['form'], function() {
					document.getElementById("taskType").options[0].selected = true;
					document.getElementById("collectState").options[0].selected = true;
					document.getElementById("pushType").options[0].selected = true;
					layui.form().render('select');
				});
				$("#customerId").val("");
				$("#appChannel").val("");
				$("#userName").val("");
				$("#phone").val("");
				$("#idCard").val("");
				$("#appSerialNumber").val("");
				$("#startTime").val(layui.laydate.now(0,'YYYY-MM-DD 00:00:00'));
				$("#endTime").val(layui.laydate.now(0,'YYYY-MM-DD 23:59:59'));
			}
			
			// 搜索
			function sel() {
				getList(); 
			}; 
			
			
			// 查询异常的单子
			function selectError(){
				getList();
			}
			
			
			function getList(){
				var customerId = $("#customerId").val().trim();
				var appSerialNumber = $("#appSerialNumber").val().trim();
				var appChannel = $("#appChannel").val().trim();
				var startTime = $("#startTime").val();
				var endTime = $("#endTime").val();
				var taskType=$("#taskType").val();
				var collectState=$("#collectState").val();
				var pushType=$("#pushType").val();
				var userName=$("#userName").val().trim();
				var phone=$("#phone").val().trim();
				var idCard=$("#idCard").val().trim();
				if('00' == taskType){
					taskType ='';
				}
				if('00' == collectState){
					collectState ='';
				}
				if('00' == pushType){
					pushType ='';
				}
				layui.use(['paging', 'layer', 'form', 'element','laydate'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form(),
						element = layui.element();
					// 页面初始化加载		start
	                paging.init({
	                    openWait: true,
						url: hostIp + "/promoteDetailed/findAllByObj.do?v=" + new Date().getTime(),// 请求服务器的url
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							customerId : customerId,
							appSerialNumber : appSerialNumber,
							appChannel : appChannel,
							startTime : startTime,
							endTime : endTime,
							taskType : taskType,
							collectState : collectState,
							pushType : pushType,
							userName : userName,
							phone : phone,
							idCard : idCard
						},
						type: 'post',
						tempElem: '#tpl', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 10 //分页大小，当前页面显示的条数
						},
						success: function() { //渲染成功的回调
							//重新渲染复选框
							form.render('checkbox');
							form.on('checkbox(allselector)', function(data) {
								var elem = data.elem;
								$('#content').children('tr').each(function() {
									var $that = $(this);
									//全选或反选
									$that.children('td').eq(0).children('input[type=checkbox]')[0].checked = elem.checked;
									form.render('checkbox'); 
								});
							});
						},
						fail: function(msg) { //获取数据失败的回调
							layer.msg("获取数据失败！");
						},
						complate: function() { //完成的回调	
							$('#getuserInfo').on('click', function() { 
								var ids = ''; 
								var temp=checkChecked();
								var customerId="";
								var pid="";
								var cname="";
								var href="";
								$('#content').children('tr').each(function() {
									var $that = $(this);
									var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;   
									if($cbx) {
										customerId = $that.children('td').eq(0).children('input[type=checkbox]').data('cid'); 
									} 
								});
								if(temp){
									$.ajax({//通过客户编号查询平台用户编号
										url : hostIp+ "/promoteDetailed/findPlatformId.do?v=" + new Date().getTime(),// 请求服务器的url
										method: "post",
										dataType:"json",
										data:{
											customerId:customerId
										},
										success: function(data){
											/*var pid="";
											var cname="";*/
											if(data.list.data != undefined && data.list.data != null ){
												pid=data.list.data.platformCustomerId;
												cname=data.list.data.name;
											}
											
											href = getCtxPath() + "/index/views/customerManager/customerInfo.html?id="+pid+"&customerId="+customerId;
											var data = {
												"title": "用户信息-"+cname,
												"icon": "&#xe60c;",
												"href": href
											}
											window.parent.topTab.tabAdd(data);
										},
										error: function(){  
					 						layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
										} 
									});
								
								}
							});
							/**
							 * 用于一键发送到队列的数据加载
							 */
							$.ajax({
								url : getCtxPath() + "/backend/promoteDetailed/findAllId.do?v=" + new Date().getTime(),// 请求服务器的url
								method: "post",
								dataType:"json",
								data: {
									customerId : customerId,
									appSerialNumber : appSerialNumber,
									appChannel : appChannel,
									startTime : startTime,
									endTime : endTime,
									taskType : taskType,
									collectState : collectState,
									pushType : pushType
								},
								success: function(data){
									if(data != null){
										// 保存一键重发的内容
										var sendVal = "";
										// 显示条数
										$("#sendQueue").html(data.count);
										$.each(data.data.data, function(k, v){
											//拼接内容
											sendVal += v.promoteDetailedId + ",";
										});
									}
									$("#sendVal").val(sendVal);
									 
								},
								error: function(){
									 layer.msg("网络请求异常,请常识重新登录" , {icon: 2});//提示信息
								},
								complete: function(){
//									form.render(); //刷新全部
								} 
							});	
							
							/**
							 * 根据条件手工推送APP
							 */
							$.ajax({
								url : getCtxPath() + "/backend/promoteDetailed/findAllIdByPush.do?v=" + new Date().getTime(),// 请求服务器的url
								method: "post",
								dataType:"json",
								data: {
									customerId : customerId,
									appSerialNumber : appSerialNumber,
									appChannel : appChannel,
									startTime : startTime,
									endTime : endTime,
									taskType : taskType,
									collectState : collectState,
									pushType : pushType
								},
								success: function(data){
									if(data != null){
										// 保存一键重发的内容
										var pushAppVal = "";
										$.each(data.data.data, function(k, v){
											//拼接内容
											pushAppVal += v.promoteDetailedId + ",";
										});
									}
									$("#pushAppVal").val(pushAppVal);
									 
								},
								error: function(){
									 layer.msg("网络请求异常,请常识重新登录" , {icon: 2});//提示信息
								},
								complete: function(){
//									form.render(); //刷新全部
								} 
							});	
							
							
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td').children('a[data-opt=find]').on('click', function() {
									var promoteDetailedId = $(this).data('id');
									$.ajax({
										url :hostIp + "/promoteDetailed/pushMqMsg.do?v=" + new Date().getTime(),// 请求服务器的url,
										method: "post",
										dataType:"json",
										data: {
											promoteDetailedId:  promoteDetailedId
										},
										success: function(data){
											if(data.list.data == true){
												
												layer.msg("成功" , {icon: 1});//提示信息
												
											}else if(data.list.data == false){
												
											}else{
												
											}
											getList(); 
										}, 
										error: function(){
					 						layer.msg("错误" , {icon: 2});//提示信息
										},
										complete: function(){ 
										} 
									});	
									/*var data = {
										"title": "用户信息-"+$(this).attr("data-name"),
										"icon": "&#xe60c;",
										"href": href
									}
									window.parent.topTab.tabAdd(data);*/
								});
							});
							
							
							// 查看异常详细信息
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td').children('a[data-opt=info]').on('click', function() {
									var info = $(this).data('id');
									//自定页
									layer.open({
									  type: 1,
									  title: '查看异常详细信息',// 设置false表示不显示
									  area: ['840px', '550px'],
//									  skin: 'layui-layer-demo', //样式类名
									  closeBtn: 0, //不显示关闭按钮
									  anim: 2,
									  shadeClose: true, //开启遮罩关闭
									  content: info
									});
								});
							});
							 
							
						}						
					});
	                 
				});
			}
			
			
			
			// 一键重发队列
			function sendQueue(){
				var sendVal = $("#sendVal").val();
				if( sendVal== null || sendVal == ""){
					layer.msg("发送的内容为空！");//提示信息
					return;
				}
				
				$.ajax({
					url : getCtxPath() + "/backend/promoteDetailed/sendQueue.do?v=" + new Date().getTime(),// 请求服务器的url
					method: "post",
					dataType:"json",
					data: {
						sendVal: sendVal
					},
					success: function(data){
//						alert(data);
						if(data != null && data.list.code == 500){
							layer.msg("promoteDetailedId = " + data.list.promoteDetailedId + data.list.msg, {icon: 2});//提示信息
						}else{
							layer.msg("一键发送成功");//提示信息
						}
						// 刷新当前页面
						getList(); 
					}, 
					error: function(){
 						layer.msg("请求错误，请刷新页面再试！" , {icon: 2});//提示信息
					},
					complete: function(){ 
					} 
				});	
				
			}
			
			function checkChecked(){//检验多选框是否多选
				var flag=0;
				var temp=true;
				$('#content').children('tr').each(function() {
					var $that = $(this);
					var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;
					if($cbx){
						flag++;
					}
				});
				if(flag==0){
					layer.msg('请勾选一条数据！');
					temp=false;
//					throw SyntaxError();
				}
				if(flag>1){
					layer.msg('请选择单个用户查询！' , {icon: 2});
					temp=false;
//					throw SyntaxError();
				}
				return temp;
			}
			
            
			 //根据条件手工推送APP
			function sendPushAppStatus(){
				var pushAppVal = $("#pushAppVal").val();
				if( pushAppVal== null || pushAppVal == ""){
					layer.msg("发送的内容为空！");//提示信息
					return;
				}
				
				$.ajax({
					url : getCtxPath() + "/backend/promoteDetailed/findPushAppStatusByTie.do?v=" + new Date().getTime(),// 请求服务器的url
					method: "post",
					dataType:"json",
					data: {
						pushAppVal: pushAppVal
					},
					success: function(data){
//						alert(data);
						if(data != null && data.list.code == 500){
							layer.msg("promoteDetailedId = " + data.list.promoteDetailedId + data.list.msg, {icon: 2});//提示信息
						}else{
							layer.msg("发送成功");//提示信息
						}
						// 刷新当前页面
						getList(); 
					}, 
					error: function(){
 						layer.msg("请求错误，请刷新页面再试！" , {icon: 2});//提示信息
					},
					complete: function(){ 
					} 
				});	
			}

			