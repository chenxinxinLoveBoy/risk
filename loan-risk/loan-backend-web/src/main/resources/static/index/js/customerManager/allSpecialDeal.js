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
function specialDealHtml(specialDeal){
	var html=[];
	$.each(specialDeal, function(k, v){
			html.push('<tr>');
				html.push('<td>'+v.recordSource+'</td>');
				html.push('<td>'+v.recordType+'</td>');
				html.push('<td>'+v.occurrenceDate+'</td>');
				html.push('<td>'+v.changeMonths+'</td>');
				html.push('<td>'+v.actualAmount+'</td>');
				html.push('<td>'+v.detail+'</td>');
				html.push('<td>'+v.specialDealTime+'</td>');
				html.push('</tr>');
	});
	$("#specialDeal").html(html.join(''));
}
function getMysqlData(){
	$.ajax({
		url : getCtxPath() + "/backend/shCredit/getAllSpecialDealByApplicationId.do",
		method : "post",
		dataType : "json",
		data : {
			applicationId : applicationId,
		},
		success : function(data) {
			var specialDeal = data.list.data;
			specialDealHtml(specialDeal);
		},
		error : function() {
			alert("获取全部担保信息失败,请尝试重新获取！");
		}
	});	
}