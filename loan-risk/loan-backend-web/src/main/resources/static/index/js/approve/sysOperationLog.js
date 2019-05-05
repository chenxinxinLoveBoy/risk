			layui.config({
				base: '../../js/'
			});
			 
			//初始化加载startTime
			$(function(){ 
				getList();
			});
		 
			
			function getList(){ 
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
						url: getCtxPath() + "/backend/approval/getBuSpApprovalList.do?v=" + new Date().getTime(),// 请求服务器的url
						elem: '#content', //内容容器
						type: 'post',
						tempElem: '#tpl', //模块容器
						params: { //发送到服务端的参数
							approvalId : 'pageFlag'
						},
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
							/*
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td').children('a[data-opt=find]').on('click', function() {
									var href = getCtxPath() + "/index/views/customerManager/customerInfo.html?id="+$(this).data('id');
									var data = {
										"title": "用户信息-"+$(this).attr("data-name"),
										"icon": "&#xe60c;",
										"href": href
									}
									window.parent.topTab.tabAdd(data);
								});
							});*/
						}						
					});
	                 
				});
				
			}
 	 