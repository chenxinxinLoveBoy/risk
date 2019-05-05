layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
			layer = layui.layer, // 获取当前窗口的layer对象
			form = layui.form(), element = layui.element();
			var buApplicationId = getQueryString("applicationId");
			var platformId = getQueryString("id");//获取页面传过来的平台用户编号
			//alert("宜信风险项记录:appid="+buApplicationId);
			// 页面初始化加载		start
            paging.init({/*宜信风险项记录*/
                openWait: true,
				url : getCtxPath() + "/backend/yxReport/getYxRiskResultsListById.do?v=" + new Date().getTime(),// 请求服务器的url
				elem: '#content', //内容容器
				params: { //发送到服务端的参数
					platformId:platformId,
					buApplicationId : buApplicationId
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
				}
				
            });
		});

$(function() {
});


/**
 * 2017-04-28 00:00:00.0 -> 2017-04-28 00:00:00
 */
function getDateStrFormat(s){
	if(s!==undefined && s.length>19){
		return s.substring(0,19);
	}
	return s
}