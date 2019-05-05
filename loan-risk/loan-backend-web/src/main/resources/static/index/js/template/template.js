			layui.config({
				base: '../../js/' 
			});
			// 当前菜单名称
			var menuNames = "禁止项规则模版配置";
			// 初始化
			$(function(){
				getList();
			})
			var banTplName = "", banTplCode = "",state = "";
			// 条件搜索
			 function sel() {
				 banTplName = $("#banTplName").val().trim();
				 banTplCode = $("#banTplCode").val().trim();
				 state = $("#state").val().trim();
				getList(1);
			};
			
			
			 function add() {
//				 var _time = new Date().getTime();
//				 var href = getCtxPath() + "/index/views/template/template_add.html?_time="+_time;
//					var data = {
//						"title": "新增模版信息"+_time,
//						"icon": "&#xe60c;",
//						"href": href,
//						"id": "tpl-add-"+_time
//					}
//					window.parent.topTab.tabAdd(data); 
					layer.open({
						  type: 2,
						  title: '新增',// 设置false表示不显示
						  closeBtn: 1, //0：不显示关闭按钮
						  shade: 0.4, //遮罩透明度
						  area: ['740px', '500px'],
						  skin: 'layui-layer-rim', //加上边框
						  fixed: false, //不固定
						  maxmin: true, // 允许屏幕最小化
						  content: ['template_add.html'], //iframe的url，no代表不显示滚动条
						});
				}; 
			
			
			function getList(_pageIndex){
				if(state == '00'){
					state ='';
				}
				//局部刷新回调获取参数,
				var isFirst = 1;
				var pageIndex = _pageIndex || $("#paged").find("em").eq(1).html()||1;
				
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
						url: getCtxPath() + "/backend/sctemplate/templatelistAll.do?v=" + new Date().getTime(),
						elem: '#content', //内容容器
						params: { //发送到服务端的参数 
							banTplName : banTplName,
							banTplCode : banTplCode,
							state  : state,
							pageIndex:0,
							pageSize:100
						},
						type: 'post',
						tempElem: '#tpl', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 10 //分页大小，当前页面显示的条数
						}, 
						success: function() { //渲染成功的回调
							if (pageIndex != '' && pageIndex != '1' && isFirst === 1) {
								isFirst = 0;
								var totalCount = $("#count_page_number").html()||0;
								if( (parseInt(pageIndex)-1) * 10 >= parseInt(totalCount) ){
									pageIndex = 1;
								}
								paging.get({
		                            pageIndex: pageIndex,
		                            pageSize: 10
		                        });
							}
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
								$that.children('td').children('a[data-opt=edit]').on('click', function() {
									var id = $(this).data('id'); 
									layer.open({
										  type: 2,
										  title: '编辑',// 设置false表示不显示
										  closeBtn: 1, //不显示关闭按钮
										  shade: 0.4, //遮罩透明度
										  area: ['740px', '500px'],
										  skin: 'layui-layer-rim', //加上边框
										  fixed: false, //不固定
										  maxmin: true, // 允许屏幕最小化 
										  anim: 2,
										  content: ['template_add.html?banControlTplId='+id], //iframe的url，no代表不显示滚动条
										});
								});
							});
							
							//绑定所有'修改状态'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td').children('a[data-opt=update]').on('click', function() {
									var state= $(this).data('state');
									var version= $(this).data('version'); 
//									var level= $(this).data('level'); 
//									var tplPercent = $(this).data('percent');
 									var str= "";
									if(state=='01'){
										str="无效"; 
										state='02';
									}else{
										 str="有效";
 										state='01';
									}
  									if(confirm('您确定将状态修改为【'+str+'】吗？')){ //只有当点击confirm框的确定时，该层才会关闭
									// ajax 请求后台
										$.ajax({
											url : getCtxPath() + "/backend/sctemplate/savetemplate.do",
											method : 'post',
											dataType : 'json',
											data: {
												banControlTplId: $(this).data('id'), // 传到后台的参数，子窗口的form表单
												state:state,
//												level : level,
//												tplPercent:tplPercent,
												version : version,
												menuNames : menuNames,
												functionNames: ( "修改状态") // 按钮功能名称
 											},
											success : function(data) {
												if(data.code == "200"){
													layerTips.msg(data.message);// 提示信息
												}else{
													layerTips.msg(data.message);// 提示信息
												}
											},
											error: function(){
												parent.layer.alert("请求网络异常，请尝试重新登录！");
											},
											complete: function(){
												getList(); //局部刷新
											}
										});
									  }
									  return false; 
								});
	
							});
							
							//绑定所有'添加禁止项'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=find]').on('click', function() {
									var id= $(this).data('id');
									var name =  $(this).data('name'); 
									var href = getCtxPath() + "/index/views/template/templatelist.html?banControlTplId="+$(this).data('id');
									var data = {
										"title": name+"-可添加禁止项",
										"icon": "&#xe60c;",	
										"href": href
									}
									window.parent.topTab.tabAdd(data); 
								});
							});
							
							//绑定所有'已关联禁止项'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td').children('a[data-opt=look]').on('click', function() {
									var id= $(this).data('id');
									var name =  $(this).data('name'); 
									var href = getCtxPath() + "/index/views/template/cortemplatelist.html?banControlTplId="+$(this).data('id');
									var data = {
										"title": name+"-已关联禁止项",
										"icon": "&#xe60c;",	
										"href": href
									}
									window.parent.topTab.tabAdd(data); 
								});
							});
							 
						},
					}); 
		
				}); 
			} 