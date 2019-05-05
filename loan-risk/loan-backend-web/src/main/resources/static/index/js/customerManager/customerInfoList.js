			layui.config({
				base: '../../js/' 
			});
		var  startTime = "", endTime = "";
			var url = getCtxPath() + "/backend/customer/getAllCustomerInfo.do?v=" + new Date().getTime();// 请求服务器的url
			// 初始化
			$(function(){ 
				//我们强烈推荐你在代码最外层把需要用到的模块先加载
				layui.use(['layer', 'form', 'element','laydate'], function(){
					  var layer = layui.layer
					  ,form = layui.form()
					  ,element = layui.element();
					  form.render(); //刷新全部
					  
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
					    if(laydate.now(0,'YYYY-MM-DD hh:mm:ss') == $(this).val()){
						    $(this).val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
					    }
					  });
					 
					  $("#endTime").on("click", function(){
					    end.elem = this
					    laydate(end);
					  });

					  $("#startTime").val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
					  $("#endTime").val(laydate.now(0,'YYYY-MM-DD 23:59:59'));
					  
					  getList();
				});
			});
			function checkChecked(){//检验多选框是否多选
				var flag=0;
				$('#content').children('tr').each(function() {
					var $that = $(this);
					var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;
					if($cbx){
						flag++;
					}
				});
				if(flag==0){
					layer.msg('请勾选一条数据！');
					return ;
				}
				if(flag>1){
					layer.msg('请选择单个用户查询！' , {icon: 2});
					throw SyntaxError();
				}
				
			}

			// 条件搜索
			$('#search').on('click', function() {
			 	getList();
			});
			
			function getList(){ 
				var platformCustomerId = $("#platformCustomerId").val().trim();
				var customerId = $("#customerId").val().trim();
				var name = $("#name").val().trim();
				var  phoneNum = $("#phoneNum").val().trim();
				var certCode = $("#certCode").val().trim();
				var startTime = $("#startTime").val();  
				var endTime = $("#endTime").val();
				var appcuName = $("#appName").val().replace(/\ +/g,"");
				var appName="";
				if(appcuName == '洪澄')appName="1";
				if(appcuName == '速贷')appName="2";	
				var appName = $("#appName").val();
				if(appName=='00'){
					appName='';
				}
				layui.use(['paging', 'layer', 'form', 'element'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form(),
						element = layui.element();
					
					// 页面初始化加载		start
	                paging.init({
	                    openWait: true,
						url: url,
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							t: Math.random(),
//							state: types == 1 ? "" :state,
							platformCustomerId:platformCustomerId,
							customerId:customerId,
							name:name,
							phoneNum:phoneNum,
							certCode:certCode,
							startTime : startTime,
							endTime : endTime,
							appName:appName
							
 						},
						type: 'post',
						tempElem: '#tpl', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 10 //分页大小，当前页面显示的条数
						}, 
						success: function() { //渲染成功的回调
	//						alert('渲染成功');
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
							//获取所有选择的列
							//用户信息
							
							$('#getuserInfo').on('click', function() { 
								var ids = ''; 
								checkChecked();
								$('#content').children('tr').each(function() {
									var $that = $(this);
									var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;   
									if($cbx) {
										var n = $that.children('td').eq(0).children('input[type=checkbox]').data('id');
										var customerId = $that.children('td').eq(0).children('input[type=checkbox]').data('cid'); 
										var name = $that.children('td').eq(0).children('input[type=checkbox]').data('name'); 
										ids = n ;  
										var href = getCtxPath() + "/index/views/customerManager/customerInfo.html?id="+ids+"&customerId="+customerId;
										var data = {
											"title": "用户信息-"+name,
											"icon": "&#xe60c;",
											"href": href
										}
										window.parent.topTab.tabAdd(data);
									} 
								});
							  });
							
							//手机应用列表
							$('#phoneList').on('click', function() {
								var ids = ''; 
								checkChecked();
								$('#content').children('tr').each(function() {
									var $that = $(this);
									var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;   
									if($cbx) {
										var n = $that.children('td').eq(0).children('input[type=checkbox]').data('cid');  
										console.log(n+'customerId');  
										var name = $that.children('td').eq(0).children('input[type=checkbox]').data('name'); 
										var appName = $that.children('td').eq(0).children('input[type=checkbox]').data('app');  
										ids = n ;  
										var href = getCtxPath() + "/index/views/customerManager/phoneList.html?id="+ids+'&appName='+appName;
										var data = {
											"title": name+"-手机应用列表",
											"icon": "&#xe60c;",
											"href": href
										}
										window.parent.topTab.tabAdd(data);
									} 
								});
							  });
							
							//手机通讯录
							
							$('#phoneAddress')[0].onclick = function() {
								var ids = ''; 
								checkChecked();
								$('#content').children('tr').each(function() {
									var $that = $(this);
									var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;   
									if($cbx) {
										var n = $that.children('td').eq(0).children('input[type=checkbox]').data('cid'); 
										var name = $that.children('td').eq(0).children('input[type=checkbox]').data('name'); 
										var appName = $that.children('td').eq(0).children('input[type=checkbox]').data('app');  
										ids = n ; 
										var href = getCtxPath() + "/index/views/customerManager/phoneAddress.html?id="+ids+'&appName='+appName;
//										var data = {
//											"title": name+"-手机通讯录",
//											"icon": "&#xe60c;",
//											"href": href
//										}
										//window.parent.topTab.tabAdd(data);
										var index=layer.open({
											  type: 2,
											  closeBtn: 1, //0：不显示关闭按钮
											  shade: 0.4, //遮罩透明度
											  area: ['840px', '615px'],
											  skin: 'layui-layer-rim', //加上边框
											  fixed: false, //不固定
											  maxmin: true, //开启最大化最小化按钮
											  anim: 2,
											  title: name+'-手机通讯录',// 设置false表示不显示
											  content:href
										});
									} 
								});
							  };
							
							//通话记录
							$('#callList').on('click', function() {
								var ids = ''; 
								checkChecked();
								$('#content').children('tr').each(function() {
									var $that = $(this);
									var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;   
									if($cbx) {
										var n = $that.children('td').eq(0).children('input[type=checkbox]').data('cid'); 
										var name = $that.children('td').eq(0).children('input[type=checkbox]').data('name'); 
										var appName = $that.children('td').eq(0).children('input[type=checkbox]').data('app');  
										ids =n; 
										var href = getCtxPath() + "/index/views/customerManager/callList.html?id="+ids+'&appName='+appName;
										var data = {
											"title": name+"-通话记录",
											"icon": "&#xe60c;",
											"href": href
										}
										window.parent.topTab.tabAdd(data);
									} 
								});
							  });
							
							//短信记录
							$('#messageList').on('click', function() {
								var ids = ''; 
								checkChecked();
								$('#content').children('tr').each(function() {
									var $that = $(this);
									var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;   
									if($cbx) {
										var n = $that.children('td').eq(0).children('input[type=checkbox]').data('cid'); 
										var name = $that.children('td').eq(0).children('input[type=checkbox]').data('name'); 
										var appName = $that.children('td').eq(0).children('input[type=checkbox]').data('app');  
										ids = n ; 
										var href = getCtxPath() + "/index/views/customerManager/messageList.html?id="+ids+'&appName='+appName;;
										var data = {
											"title": name+"-短信记录",
											"icon": "&#xe60c;",
											"href": href
										}
										window.parent.topTab.tabAdd(data);
									} 
								});
							  });
							
							/****************lyc*******************/
							$('#IosEquipmentInfo').on('click', function() {
								var ids = ''; 
								checkChecked();
								$('#content').children('tr').each(function() {
									
									var $that = $(this);
									var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;
									if($cbx) {
										var n = $that.children('td').eq(0).children('input[type=checkbox]').data('cid'); 
										var name = $that.children('td').eq(0).children('input[type=checkbox]').data('name');
										ids = n ; 
										var href = getCtxPath() + "/index/views/customerManager/iosEquipmentInfo.html?id="+ids;
										var data = {
											"title": name+"-IOS设备信息",
											"icon": "&#xe60c;",
											"href": href
										}
										window.parent.topTab.tabAdd(data);
									} 
								});
							  });
							
							$('#androidEquipmentInfo').on('click', function() {
								var ids = ''; 
								checkChecked();
								$('#content').children('tr').each(function() {
									var $that = $(this);
									var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;   
									if($cbx) {
										var n = $that.children('td').eq(0).children('input[type=checkbox]').data('cid'); 
										var name = $that.children('td').eq(0).children('input[type=checkbox]').data('name');
										ids = n ; 
										var href = getCtxPath() + "/index/views/customerManager/androidEquipmentInfo.html?id="+ids;
										var data = {
											"title": name+"-安卓设备信息",
											"icon": "&#xe60c;",
											"href": href
										}
										window.parent.topTab.tabAdd(data);
									} 
								});
							  });
							/*******************lyc-end*************************/
							
						}
					});
	                // 初始化加载init     end!
	                 
				});
				}
			 
 			
 			