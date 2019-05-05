			layui.config({
				base: '../../js/' 
			});
			
			// 初始化
			$(function(){
				getList();
			})

			var roleName = "";// 参数，用于页面查询条件
			
			// 搜索
			$('#search').on('click', function() {
				roleName = $("#roleName").val();
				getList();
//				parent.layer.alert('你点击了搜索按钮')
			});
			

			/**新增  start*/
			$('#add').on('click', function() {
				layer.open({
					  type: 2,
					  title: '新增',// 设置false表示不显示
					  closeBtn: 1, //0：不显示关闭按钮
					  shade: 0.4, //遮罩透明度
					  area: ['640px', '415px'],
					  skin: 'layui-layer-rim', //加上边框
					  fixed: false, //不固定
					  maxmin: true, // 允许屏幕最小化
//					  anim: 2,
					  content: ['role_edit.html'], //iframe的url，no代表不显示滚动条
					});
			});
			/**新增     end*/
			
			
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
	//                     url: 'datas/laytpl_laypage_data.json?v=' + new Date().getTime(), //地址
						url: getCtxPath() + "/backend/role/findAll.do?v=" + new Date().getTime(),// 请求服务器的url
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							roleName: roleName
						},
						type: 'post',
						tempElem: '#tpl', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 10 //分页大小，当前页面显示的条数
						},
						success: function() { //渲染成功的回调
	//						alert('渲染成功');
						},
						fail: function(msg) { //获取数据失败的回调
							layer.msg("获取数据失败！");
						},
						complate: function() { //完成的回调
							//alert('处理完成');
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
	//									  offset: 'rb', //右下角弹出
	//									  time: 2000, //2秒后自动关闭
										  anim: 2,
										  content: ['role_edit.html?roleId='+$(this).data('id')], //iframe的url，no代表不显示滚动条
										});
								});
							});
							
							
							
							//绑定所有'删除'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=del]').on('click', function() {
									// id
									var roleId=$(this).data('id');
									layer.confirm("您确定要删除吗？", {
									    btn: ['确定','取消'], //按钮
									    shade: 0.4 //显示遮罩
									}, function(index){
										// ajax 请求后台
										$.ajax({
											url : getCtxPath() + "/backend/role/delete.do",
											method : 'post',
											dataType : 'json',
											data: {
												roleId: roleId,  // 传到后台的参数，子窗口的form表单
												menuNames: "角色管理", // 菜单名称
												functionNames: "删除" // 按钮功能名称 
											},
											success : function(data) {
												layer.msg(data.message);// 提示信息
												getList();
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
							
							
							
							//绑定所有'预览'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=find]').on('click', function() {
									layer.open({
										  type: 2,
										  title: '预览',// 设置false表示不显示
										  closeBtn: 1, //不显示关闭按钮
										  shade: 0.4, //遮罩透明度
										  area: ['640px', '415px'],
										  skin: 'layui-layer-rim', //加上边框
										  btn: ['取消'],
										  fixed: false, //不固定
										  maxmin: true, // 允许屏幕最小化
	//									  offset: 'rb', //右下角弹出
	//									  time: 2000, //2秒后自动关闭
										  anim: 2,
										  content: ['role_edit.html?find=1&roleId='+$(this).data('id')], //iframe的url，no代表不显示滚动条
										});
								});
							});
							
							// 绑定所有'权限设置'按钮事件
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=edit_zTree]').on('click', function() {
									layer.open({
										  type: 2,
										  title: '权限设置',// 设置false表示不显示
										  closeBtn: 1, //不显示关闭按钮
										  shade: 0.4, //遮罩透明度
										  area: ['486px', '550px'],
//										  skin: 'layui-layer-rim', //加上边框
										  fixed: false, //不固定
										  maxmin: true, // 允许屏幕最小化
	//									  offset: 'rb', //右下角弹出
	//									  time: 2000, //2秒后自动关闭
										  anim: 2,
										  content: ['../tree/zTree_edit.html?roleId='+$(this).data('id'), "no"], //iframe的url，no代表不显示滚动条
										});
								});
							});

							// 绑定所有'角色用户'按钮事件
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=role_user_zTree]').on('click', function() {
									layer.open({
										  type: 2,
										  title: '角色用户',// 设置false表示不显示
										  closeBtn: 1, //不显示关闭按钮
										  shade: 0.4, //遮罩透明度
										  area: ['486px', '550px'],
//										  skin: 'layui-layer-rim', //加上边框
										  fixed: false, //不固定
										  maxmin: true, // 允许屏幕最小化
	//									  offset: 'rb', //右下角弹出
	//									  time: 2000, //2秒后自动关闭
										  anim: 2,
										  content: ['../tree/role_user_zTree.html?roleId='+$(this).data('id'), "no"], //iframe的url，no代表不显示滚动条
										});
								});
							});
							
							
							
						},
					});
	                // 初始化加载init     end!
	                
					
				});
			}	 
			
 			
 			