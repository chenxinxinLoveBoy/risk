			layui.config({
				base: '../../js/' 
			});
			
			var urls = getCtxPath() + "/backend/systemLog/list.do?v=" + new Date().getTime();
			
			var userName = "";// 登录帐号
			var roleName = "";// 角色
			var content = "";// 昵称|URL|IP
			
			var optTimeBigen = ""; // 开始操作时间
			var optTimeEnd = "";// 结束操作时间
			
			// 初始化
			$(function(){
				getList();
			})

			
			// 搜索
			$('#search').on('click', function() {
				userName = $("#userName").val().trim();// 登录帐号
				roleName = $("#roleName").val().trim();// 角色
				content = $("#content_id").val().trim();// 昵称|URL|IP
				
				optTimeBigen = $("#optTimeBigen").val(); // 开始操作时间
				optTimeEnd = $("#optTimeEnd").val();// 结束操作时间
				getList();//查詢
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
						url: urls,// 请求服务器的url
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							userName: userName,
							roleName: roleName,
							content: content,
							optTimeBigen: optTimeBigen,
							optTimeEnd: optTimeEnd
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
								$that.children('td:last-child').children('a[data-opt=find]').on('click', function() { 
									layer.open({
										  type: 2,
										  title: '详情',// 设置false表示不显示
										  closeBtn: 1, //不显示关闭按钮
										  btn: ['取消'],
										  shade: 0.4, //遮罩透明度
										  area: ['940px', '550px'],
//										  skin: 'layui-layer-rim', //加上边框
										  fixed: false, //不固定
										  maxmin: true, // 允许屏幕最小化 
										  anim: 2,
										  content: ['systemLog.html?find=1&logId='+$(this).data('id')], //iframe的url，no代表不显示滚动条
										});
								});
							});
							
						}
					});
					
				});
			}	 
			
 			
 			