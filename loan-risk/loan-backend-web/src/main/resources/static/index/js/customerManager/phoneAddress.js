layui.config({
	base: '../../js/' 
});
	layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), 
			layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
			layer = layui.layer, // 获取当前窗口的layer对象
			form = layui.form(), element = layui.element();
		});
			// 初始化
	$(function(){
		var customerId = getQueryString("id");
		var appName = getQueryString("appName");
  		$.ajax({
			url :getCtxPath() + "/backend/approve/cognateAnalysis.do",
			method: "post",
			dataType:"json",
			data: {
				customerId:customerId,
				appName:appName
			},
			success: function(data){
				var nelist = data.list.data.nelist;//正常号码
				var exlist = data.list.data.exlist;//异常号码
				var erlist = data.list.data.erlist;//异类号码
				var phoneNum = data.list.data;
				showPhoneNum(phoneNum);
				if (nelist != null) {
					showNePhoneAddress(nelist);
				}
				if (exlist != null) {
					showExPhoneAddress(exlist);
				}
				if (erlist != null) {
					showErPhoneAddress(erlist);
				}
			}
		});
	});
	function showPhoneNum(phoneNum){
		console.log(phoneNum.phoneSize);
		$("#phoneSize").text(phoneNum.phoneSize);
		$("#errorPhoneSize").text(phoneNum.errorPhoneSize);
		$("#exceptionPhoneSize").text(phoneNum.exceptionPhoneSize);
	};
	function showNePhoneAddress(nelist){
		$("#neContent").empty();
		$.each(nelist, function(k, v){
			var html='';
			html+='<tr> ';
			html+='<td>'+v.contactName+'</td>';
			html+='<td>'+v.contactPhone+'</td>';
			html+='</tr>';
			$("#neContent").append(html);
		});
	};
	function showExPhoneAddress(exlist){
		$("#exContent").empty();
		$.each(exlist, function(k, v){
			var html='';
			html+='<tr> ';
			html+='<td>'+v.contactName+'</td>';
			html+='<td>'+v.contactPhone+'</td>';
			html+='<td>'+v.extend+'</td>';
			html+='</tr>';
			$("#erContent").append(html);
		});
	};
	function showErPhoneAddress(erlist ){
		$("#exContent").empty();
		$.each(erlist, function(k, v){
			var html='';
			html+='<tr> ';
			html+='<td>'+v.contactName+'</td>';
			html+='<td>'+v.contactPhone+'</td>';
			html+='<td>'+v.extend+'</td>';
			html+='</tr>';
			$("#exContent").append(html);
		});
	};
