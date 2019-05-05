layui.config({
	base : '../../js/'
});
var buApplicationId='';
var platformId='';
layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
			layer = layui.layer, // 获取当前窗口的layer对象
			form = layui.form(), element = layui.element();
			buApplicationId = getQueryString("applicationId");
			platformId = getQueryString("id");//获取页面传过来的平台用户编号
			//alert(window.document.URL);
			//var buApplicationId='3de4f32408b04c339e568329e2e1baec';
			/*if(buApplicationId == null||buApplicationId==''||buApplicationId==undefined){
            	layer.msg("错误的打开方式！请从增强版信审处查询！");
            	return;
            }*/
			$("#history").prop("src",getCtxPath()+'/index/views/customerManager/yxReport_history.html?applicationId='+buApplicationId+'&id='+platformId);
			$("#risk").prop("src",getCtxPath()+'/index/views/customerManager/yxReport_risk.html?applicationId='+buApplicationId+'&id='+platformId);
			// 页面初始化加载		start
            paging.init({/*宜信借款记录查询*/
                openWait: true,
				url : getCtxPath() + "/backend/yxReport/findListById.do?v=" + new Date().getTime(),// 请求服务器的url
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
				success: function(data) { //渲染成功的回调
					
				},
				fail: function(msg) { //获取数据失败的回调
				},
				complate: function() { //完成的回调 
				}
				
            }); 
		});

$(function() {
	 buApplicationId = getQueryString("applicationId");//获取页面传过来的申请单编号
	 platformId = getQueryString("id");//获取页面传过来的平台用户编号
	 var applicationId=buApplicationId;
	$.ajax({/*宜信主信息查询*/
				url : getCtxPath() + "/backend/yxReport/getEntityById.do",
				method : "post",
				dataType : "json",
				data : {
					platformId:platformId,
					applicationId : applicationId
				},
				success : function(data) {
					var yxData=data.list.data;
					console.log(data);
					console.log(yxData);
					if(yxData==''||yxData==null||yxData==undefined){
						return;
					} 
					$("#buApplicationId_Data").html(yxData.buApplicationId);
					$("#flowId").html(yxData.flowId);
					$("#zcCreditScore").html(yxData.zcCreditScore);
					$("#contractBreakRate").html(yxData.contractBreakRate);
					$("#timesByOtherOrg").html(yxData.timesByOtherOrg);
					$("#otherOrgCount").html(yxData.otherOrgCount);
					$("#timesByCurrentOrg").html(yxData.timesByCurrentOrg);
					$("#createTime").html(yxData.createTime.substring(0,19));
					$("#modifyTime").html(yxData.modifyTime.substring(0,19));
					$("#remark").html(yxData.remark);
				},
				error : function() {
					//alert("获取宜信信息失败,请尝试重新获取！");
				}
			});
	
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