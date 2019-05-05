layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
				function() {
					var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
					layer = layui.layer, // 获取当前窗口的layer对象
					form = layui.form(), element = layui.element();
				});
var applicationId ="";
var platformId ="";
$(function() {
	applicationId= getQueryString("applicationId");//获取页面传过来的申请单编号
	getMysqlData();
})
function guaranteeHtml(guarantee){
	var html=[];
	$.each(guarantee, function(k, v){
			html.push('<tr>');
				html.push('<td>'+v.guaranteeProject+'</td>');
				html.push('<td>'+v.guaranteeDate+'</td>');
				html.push('<td>'+v.guaranteeMoney+'</td>');
				html.push('<td>'+v.guaranteeRelation+'</td>');
				html.push('<td>'+v.guaranteeTime+'</td>');
				html.push('</tr>');
	});
	$("#guarantee").html(html.join(''));
}
function getMysqlData(){
	$.ajax({
		url : getCtxPath() + "/backend/shCredit/getAllGuranteeByApplicationId.do",
		method : "post",
		dataType : "json",
		data : {
			applicationId : applicationId,
		},
		success : function(data) {
			console.log(data);
			var guarantee = data.list.data;
			guaranteeHtml(guarantee);
		},
		error : function() {
			alert("获取全部担保信息失败,请尝试重新获取！");
		}
	});	
}