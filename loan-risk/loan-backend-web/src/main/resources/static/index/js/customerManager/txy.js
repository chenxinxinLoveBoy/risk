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
	platformId=getQueryString("id");
	getTxyMongoData();
	getTxyMysqlData();
})
function cleanData(){
	$("#isFound").html("");
	$("#isIdFound").html("");
	$("#riskScore").html("");
	$("#riskInfo").empty();
}

function getTxyMongoData(){
	cleanData();
	$.ajax({
		url : getCtxPath() + "/backend/txyInfo/getTxyInfoByApplicationId.do",
		method : "post",
		dataType : "json",
		data : {
			applicationId : applicationId,
			platformId:platformId
		},
		success : function(data) {
				var obj=data.list.data;
				if(obj!=null){
					$("#isFound").html(obj.found);
					$("#isIdFound").html(obj.idFound);
					$("#riskScore").html(obj.riskScore);
					var html='';
					$.each(obj.riskInfo, function(k, v){
					    html+='<tr>';
						html+='<td><font>'+codeToValue(v.riskCode+"") +'</font></td>';
						html+='<td><font>'+codeValueToValue(v.riskCodeValue+"") +'</font></td>';
						html+='</tr>';
					});
					$("#riskInfo").html(html);
				}
		},
		error : function() {
			alert("获取腾讯云失败,请尝试重新获取！");
		}
	});
}
function getTxyMysqlData(){
	cleanData();
	$.ajax({
		url : getCtxPath() + "/backend/txyInfo/getTxyInfoMysqlByApplicationId.do",
		method : "post",
		dataType : "json",
		data : {
			applicationId : applicationId,
			platformId:platformId
		},
		success : function(data) {
			var obj=data.data.txyObject;
			if(obj!=null){
				$("#isFound").html(obj.found);
				$("#isIdFound").html(obj.idFound);
				$("#riskScore").html(obj.riskScore);
				var html='';
				$.each(obj.txyRiskInfos, function(k, v){
					html+='<tr>';
					html+='<td><font>'+codeToValue(v.riskCode)+'</font></td>';
					html+='<td><font>'+codeValueToValue(v.riskCodeValue)+'</font></td>';
					html+='</tr>';
				});
				$("#riskInfo").html(html);
			}
		},
		error : function() {
			alert("获取腾讯云失败,请尝试重新获取！");
		}
	});
}
function codeToValue(code){
	var value = "-";
	switch (code) {
	case "1":
		value = "信贷中介";
		break;
	case "2":
		value = "不法分子";
		break;
	case "3":
		value = "虚假资料";
		break;
	case "4":
		value = "羊毛党";
		break;
	case "5":
		value = "身份认证失败";
		break;
	case "6":
		value = "疑似恶意欺诈";
		break;
	case "7":
		value = "失信名单";
		break;
	case "8":
		value = "异常支付行为";
		break;
	case "301":
		value = "恶意环境";
		break;
	case "503":
		value = "其他异常行为";
		break;
	default:
		break;
	}
	return value;
}
function codeValueToValue(codeValue) {
	var value = "-";
	switch (codeValue) {
	case "1":
		value = "低风险";
		break;
	case "2":
		value = "中风险";
		break;
	case "3":
		value = "高风险";
		break;
	default:
		break;
	}
	return value;
}
