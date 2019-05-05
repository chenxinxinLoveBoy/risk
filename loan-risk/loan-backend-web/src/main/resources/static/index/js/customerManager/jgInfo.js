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

	getJgMongoData();
	getJgMysqlData();
})

function cleanData(){

	$("#overdueScore").html("0");
	$("#violationScore").html("0");
	$("#circleScore").html("0");
	$("#finallyRiskScore").html("0");
	$("#riskDescription").html("-");
}
function getJgMongoData(){
	cleanData();

	$.ajax({
		url : getCtxPath() + "/backend/jgInfo/getJgInfoByApplicationId.do",
		method : "post",
		dataType : "json",
		data : {
			applicationId : applicationId,
			platformId:platformId
		},
		success : function(data) {
				var obj=data.list.data.data;
				if(obj!=null){
					var score=obj.score;
					$("#finallyRiskScore").html(score);
					var description=obj.description;
					scoreToResult(description);
					if(obj.hits!=null){
						$.each(obj.hits, function(k, v){ 
							if(v.name=="逾期分"){
								$("#overdueScore").html(v.score);
							}
							if(v.name=="违约分"){
								$("#violationScore").html(v.score);
							}
							if(v.name=="关系圈风险分"){
								$("#circleScore").html(v.score);
							}
						});
					}
				}
		},
		error : function() {
			alert("获取极光信息失败,请尝试重新获取！");
		}
	});
}
function getJgMysqlData(){
	cleanData();

	$.ajax({
		url : getCtxPath() + "/backend/jgInfo/getJgInfoMysqlByApplicationId.do",
		method : "post",
		dataType : "json",
		data : {
			applicationId : applicationId,
			platformId:platformId
		},
		success : function(data) {
			
			if(data.data.jgInfoObject!=null){
				var socre = data.data.jgInfoObject.riskScore;
				var description = data.data.jgInfoObject.riskDescription;
				$("#overdueScore").html(data.data.jgInfoObject.overdueScore);
				$("#violationScore").html(data.data.jgInfoObject.violationScore);
				$("#circleScore").html(data.data.jgInfoObject.circleScore);
				$("#finallyRiskScore").html(socre);
				scoreToResult(description);
				
			}
		},
		error : function() {
			alert("获取极光信息失败,请尝试重新获取！");
		}
	});
	
	
}
function scoreToResult(description){
	var value = "-";
	switch (description) {
	case "pass":
			value = "通过";
		break;
	case "review":
		value = "低概率黑名单";
		break;
	case "reject":
			value = "黑名单";
	break;

	}
	
	$("#riskDescription").html(value);
}

