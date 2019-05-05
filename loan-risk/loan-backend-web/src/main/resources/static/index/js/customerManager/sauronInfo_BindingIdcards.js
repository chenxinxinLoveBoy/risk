layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
			layer = layui.layer, // 获取当前窗口的layer对象
			form = layui.form(), element = layui.element();
			var buApplicationId = getQueryString("applicationId");
			var platformId = getQueryString("platformId");
			// 页面初始化加载		start
			paging.init({
                openWait: true,
				url : getCtxPath() + "/backend/hlsl/getBindingIdcards.do?v=" + new Date().getTime(),// 请求服务器的url
				elem: '#content', //内容容器
				params: { //发送到服务端的参数
					buApplicationId : buApplicationId,
					platformId:platformId
				},
				type: 'post',
				tempElem: '#tpl', //模块容器
				pageConfig: { //分页参数配置
					skin:'#e2e2e2',
					elem: '#paged', //分页容器
					pageSize: 10 //分页大小，当前页面显示的条数
				},
				success: function(data) { //渲染成功的回调
				},
				fail: function(msg) { //获取数据失败的回调
				},
				complate: function() { //完成的回调 
				}
				
            }); 
		});

	$(function() {
	
	});
