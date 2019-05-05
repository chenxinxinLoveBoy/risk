			layui.config({
				base: '../../js/'
			});

			//初始化加载startTime
			$(function(){
				//我们强烈推荐你在代码最外层把需要用到的模块先加载
				layui.use(['layer', 'form', 'element','laydate'], function(){
					  var layer = layui.layer
					  ,form = layui.form()
					  ,element = layui.element();
					  form.render(); //刷新全部
				});
				var scoreSmallId  = getQueryString("id").trim();
 				var startTime = decodeURI(getQueryString("startTime"));
				var endTime = decodeURI(getQueryString("endTime"));
 				getList(scoreSmallId,startTime,endTime);
 			})
			
			function getList(scoreSmallId,startTime,endTime){
				layui.use(['paging', 'form'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form();

					// 页面初始化加载		start
	                paging.init({
	                    openWait: true,
						url: getCtxPath() + "/backend/application/getApplicationFromScoreDetailSmallStatistics.do?v=" + new Date().getTime(),// 请求服务器的url
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							t: Math.random(),
							scoreSmallId : scoreSmallId,
 							startTime:startTime,
							endTime:endTime
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
							//绑定所有'用户信息跳转'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=find]').on('click', function() {
									var href = getCtxPath() + "/index/views/customerManager/customerInfo.html?id="+$(this).data('id')+'&applicationId='+$(this).data('applicationid');
									var data = {
										"title": "用户信息-"+$(this).attr("data-name"),
										"icon": "&#xe60c;",
										"href": href
									}
									window.parent.topTab.tabAdd(data);

								});
							});
							//绑定所有'申请单详情'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=detail]').on('click', function() {
									layer.open({
										  type: 2,
										  title: '申请单详情',// 设置false表示不显示
										  closeBtn: 1, //不显示关闭按钮
										  shade: 0.4, //遮罩透明度
										  area: ['840px', '550px'],
										  skin: 'layui-layer-rim', //加上边框
										  fixed: false, //不固定
										  maxmin: true, // 允许屏幕最小化
										  anim: 2,
										  content: ['../application/application_detail.html?appSerialNumber='+$(this).data('id')], //iframe的url，no代表不显示滚动条
										});
								});
							});
							
						},
					});
	               
	                // 初始化加载init     end!

				});
			}	
			
			
			
 			