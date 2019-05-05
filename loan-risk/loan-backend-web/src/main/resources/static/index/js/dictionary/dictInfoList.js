			layui.config({
				base: '../../js/'
			});
		
			// 初始化
			$(function(){ 
				var did = getQueryString('id');
				 $("#sid").val(did);
				getList();
			})  
			
			/**新增  start*/
			 function add() {
				var sid =$("#sid").val(); 
				layer.open({
					  type: 2,
					  title: '新增字典小类',// 设置false表示不显示
					  closeBtn: 1, //0：不显示关闭按钮
					  shade: 0.4, //遮罩透明度
					  area: ['840px', '615px'],
					  skin: 'layui-layer-rim', //加上边框
					  fixed: false, //不固定
					  maxmin: true, //开启最大化最小化按钮
					  anim: 2,
					  content: ['editsmalldictInfo.html?sid='+sid], //iframe的url，no代表不显示滚动条
					});
			};
			
			function getList(){ 
				var dictBigId = getQueryString("id");  
				layui.use(['layer','upload','paging', 'form'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form();
	
					// 页面初始化加载		start
	                paging.init({
	                    openWait: true,
						url: hostIp+ "/scDicBiglist/scDicInfolist.do?v=" + new Date().getTime(),// 请求服务器的url
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							dictBigId :dictBigId	
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
								var id = getQueryString("id");   
								var $that = $(this); 
								$that.children('td:last-child').children('a[data-opt=edit]').on('click', function() { 
									layer.open({
										  type: 2,
										  title: '编辑',// 设置false表示不显示
										  closeBtn: 1, //不显示关闭按钮
										  shade: 0.4, //遮罩透明度
										  area: ['840px', '615px'],
										  skin: 'layui-layer-rim', //加上边框
										  fixed: false, //不固定
										  maxmin: true, // 允许屏幕最小化 
										  anim: 2,
										  content: ['editsmalldictInfo.html?edit=1&id='+$(this).data('id')], //iframe的url，no代表不显示滚动条
										});
								});

							}); 
						},
					});            
				});
			}	 
			
 			