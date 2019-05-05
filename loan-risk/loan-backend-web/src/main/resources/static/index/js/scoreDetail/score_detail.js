			layui.config({
				base: '../../js/'
			});
			
			$('#search').on('click', function() {
				getList();
			});
			
			function getList(){
				layui.use(['paging', 'form'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form();
					var applicationId = $("#applicationId").val().trim();
					var applicationBuId = $("#applicationBuId").val().trim();
					if(applicationId.length < 1 && applicationBuId.length < 1){
						layer.msg("请输入申请单编号或者借款编号！");
						return false;
					}
					// 页面初始化加载		start
	                paging.init({
	                    openWait: true,
						url: getCtxPath() + "/backend/scoreDetail/findAll.do?v=" + new Date().getTime(),// 请求服务器的url
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							applicationId : applicationId,
							applicationBuId : applicationBuId
						},
						type: 'post',
						tempElem: '#tpl', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 10 //分页大小，当前页面显示的条数
						},
						success: function(data) { //渲染成功的回调
						},
						fail: function(msg) { //获取数据失败的回调
							layer.msg("获取数据失败！");
						},
						complate: function() { //完成的回调
							
						},
					});
				});
			}	 
 			