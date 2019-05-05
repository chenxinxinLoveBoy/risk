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
	getMongoData();
})

function cleanData(){
	$("#userType").html("-");
}
function getMongoData(){
	cleanData();
	$.ajax({
		url : getCtxPath() + "/backend/yjfBlackList/getYjfBlackByApplicationId.do",
		method : "post",
		dataType : "json",
		data : {
			applicationId : applicationId,
			platformId:platformId
		},
		success : function(data) {
				var obj=data.list.data;
				if(obj!=null){
					var type=obj.userType;
					typeToValue(type);
					var typeDesc = obj.blackListTypeDesc;
					if(typeDesc == 'none'){
						typeDesc = '未命中'
					}
					$("#blackListTypeDesc").html(typeDesc);
				}
		},
		error : function() {
			alert("获取易极付黑名单信息失败,请尝试重新获取！");
		}
	});
}
function getMysqlData(){
	cleanData();
	$.ajax({
		url : getCtxPath() + "/backend/yjfBlackList/getYjfBlackMysqlByApplicationId.do",
		method : "post",
		dataType : "json",
		data : {
			applicationId : applicationId,
			platformId:platformId
		},
		success : function(data) {
			if(data.data.yjfInfoObject!=null){
				var type = data.data.yjfInfoObject.userType;
				typeToValue(type);
				var typeDesc = data.data.yjfInfoObject.blackListTypeDesc;
				if(typeDesc == 'none'){
					typeDesc = '未命中'
				}
				$("#blackListTypeDesc").html(typeDesc);
			}
		},
		error : function() {
			alert("获取易极付黑名单信息失败,请尝试重新获取！");
		}
	});
	
	
}
function typeToValue(type){
	var value = "-";
	switch (type) {
	case "bl":
			value = "黑名单";
		break;
	case "wl":
		value = "白名单";
		break;
	case "gl":
			value = "灰名单";
	case "none":
		value = "未命中";
	break;

	}
	$("#userType").html(value);
}

