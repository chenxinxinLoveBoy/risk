layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
	function() {
		var $ = layui.jquery,
			paging = layui.paging(),
			layerTips = parent.layer === undefined ? layui.layer: parent.layer,
			layer = layui.layer,
			form = layui.form(),
			element = layui.element();
	}
);

$(function() {
	var platformId = getQueryString("platformId");
	//客户人脸识别评分查询
	$.ajax({
		url :getCtxPath() + "/backend/approve/faceRecognitionScore.do",
		method: "post",
		dataType:"json",
		data: {
			platformId: platformId
		},
		success: function(data){
			if (data.list.data!=null) {
				$("#frontUrl").attr("src",data.list.data.frontUrl);
				$("#backUrl").attr("src",data.list.data.backUrl);
			}
		},
		error: function(){
			layer.msg("错误" , {icon: 2});//提示信息
		} 
	});
});