/*layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
			layer = layui.layer, // 获取当前窗口的layer对象
			form = layui.form(), element = layui.element();
		});

$(function() {
	 var platformCustomerId = getQueryString("id");//获取页面传过来的平台用户编号
	 var applicationId = getQueryString("applicationId");//获取页面传过来的申请单编号
	$.ajax({
				url : getCtxPath() + "/backend/",
				method : "post",
				dataType : "json",
				data : {
					platformCustomerId : platformCustomerId,
					applicationId : applicationId
				},
				success : function(msg) {},
				error : function() {
					alert("获取用户信息失败,请尝试重新获取！");
				}
			});
});
 
*/