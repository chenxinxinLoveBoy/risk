			layui.config({
				base: '../../js/'
			});
			
			// 初始化
			$(function(){  
				getList();
				  
			}) 
			
			// 搜索
			function query() {   
				var certCode = $("#certCode").val().trim();
				var name = $("#name").val().trim();
				var phoneNum = $("#phoneNum").val().trim();
				var appName = $("#appName").val();
				 
				if(certCode != '' && certCode != undefined){ 
					var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
					   if(reg.test(certCode) === false){  
						   parent.layer.msg("身份证号输入不正确" , {icon: 2});
							return false;  
					   }
				 };
				 
				 if(phoneNum != '' && phoneNum != undefined){
						if(!(/^1[34578]\d{9}$/.test(phoneNum))){ 
							   parent.layer.msg("手机号码有误，请重填" , {icon: 2}); 
					        return false; 
					    }
					};
					
				if(name != '' && name != undefined){
						if(!(/^[A-Za-z0-9\u4e00-\u9fa5]+$/.test(name))){ 
							parent.layer.msg("用户名输入有误，请重新输入!" , {icon: 2});
					        return false; 
					    }
					};
				getList();
			};
			
			
			/**新增  start*/
			 function add() { 
				layer.open({
					  type: 2,
					  title: '新增白名单',// 设置false表示不显示
					  closeBtn: 1, //0：不显示关闭按钮
					  shade: 0.4, //遮罩透明度
					  area: ['840px', '615px'],
					  skin: 'layui-layer-rim', //加上边框
					  fixed: false, //不固定
					  maxmin: true, //开启最大化最小化按钮
					  anim: 2,
					  content: ['white_edit.html'], //iframe的url，no代表不显示滚动条
					});
			};
 
			function getList(){ 
				var certCode = $("#certCode").val().trim();
				var name = $("#name").val().trim();
				var phoneNum = $("#phoneNum").val().trim();
				var appName = $("#appName").val();
				if('0' == appName ){
					appName=""; 
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
						url: hostIp + "/whiterlist/index.do?v=" + new Date().getTime(),// 请求服务器的url
						elem: '#content', //内容容器
						params: { //发送到服务端的参数 
							certCode : certCode,
							name : name,
							phoneNum : phoneNum,
							appName :appName
						},
						type: 'post',
						tempElem: '#tpl', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 10 //分页大小，当前页面显示的条数
						},
						success: function() { //渲染成功的回调 
						},
						fail: function(msg) { //获取数据失败的回调
							layer.msg("获取数据失败！");
						},
						complate: function() { //完成的回调 
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
	
							//绑定所有'编辑'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=edit]').on('click', function() {
									layer.open({
										  type: 2,
										  title: '编辑',// 设置false表示不显示
										  closeBtn: 1, //不显示关闭按钮
										  shade: 0.4, //遮罩透明度
										  area: ['640px', '415px'],
										  skin: 'layui-layer-rim', //加上边框
										  fixed: false, //不固定
										  maxmin: true, // 允许屏幕最小化 
										  anim: 2,
										  content: ['white_edit.html?edit=1&id='+$(this).data('id')], //iframe的url，no代表不显示滚动条
										});
								});
							}); 
							
							
							
							//绑定所有'删除'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=del]').on('click', function() {
									// id
									var whiteListId=$(this).data('id');
									layer.confirm("您确定要删除吗？", {
									    btn: ['确定','取消'], //按钮
									    shade: 0.4 //显示遮罩
									}, function(index){
										// ajax 请求后台
										$.ajax({
											url : getCtxPath() + "/backend/whiterlist/delete.do",
											method : 'post',
											dataType : 'json',
											data: {
												whiteListId: whiteListId //根据whiteId删除
											},
											success : function(data) {
												layer.msg(data.message);// 提示信息
												getList(); //刷新, 重新加载
											},
											error: function(){
												 layer.alert("请求网络异常，请尝试重新登录！");
											},
											complete: function(){
											}
										});
										
									});
									 
								});
	
							});
						},
					}); 
				});
			} 