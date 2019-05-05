layui.config({
	base : '../../js/'
});

$(function() {
	var platformCustomerId = getQueryString("id");// 获取页面传过来的平台用户编号
	var applicationId = getQueryString("applicationId");// 获取页面传过来的申请单编号
	$.ajax({
		url : getCtxPath() + "/backend/MhGjj/getBaseInfo.do",
		method : "post",
		dataType : "json",
		data : {
			platformCustomerId : platformCustomerId,
			applicationNumber : applicationId
		},
		success : function(data) {
			var mhGjjBaseInfo = data.data.mhGjjBaseInfo;
			$("#custNo").text(mhGjjBaseInfo.custNo);
			$("#name").text(mhGjjBaseInfo.name);
			$("#gender").text(mhGjjBaseInfo.gender);
			$("#birthday").text(mhGjjBaseInfo.birthday);
			$("#certNo").text(mhGjjBaseInfo.certNo);
			$("#mobile").text(mhGjjBaseInfo.mobile);
			$("#email").text(mhGjjBaseInfo.email);
			$("#state").text(mhGjjBaseInfo.state);
			$("#postNo").text(mhGjjBaseInfo.postNo);
			$("#monthlyCorpIncome").text(mhGjjBaseInfo.monthlyCorpIncome);
			$("#monthlyCustIncome").text(mhGjjBaseInfo.monthlyCustIncome);
			$("#monthlyTotalIncome").text(mhGjjBaseInfo.monthlyTotalIncome);
			$("#balance").text(mhGjjBaseInfo.balance);
			$("#corpName").text(mhGjjBaseInfo.corpName);
			$("#homeAddress").text(mhGjjBaseInfo.homeAddress);
		},
		error : function() {
			alert("获取用户信息失败,请尝试重新获取！");
		}
	});

	layui.use([ 'paging', 'form' ],function() {
						var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer
								: parent.layer, // 获取父窗口的layer对象
						layer = layui.layer, // 获取当前窗口的layer对象
						form = layui.form();

						// 页面初始化加载 start
						paging
								.init({
									openWait : true,
									url : getCtxPath()
											+ "/backend/MhGjj/getPayInfo.do",// 请求服务器的url
									elem : '#content', // 内容容器
									params : { // 发送到服务端的参数
										platformCustomerId : platformCustomerId,
										applicationNumber : applicationId
									},
									type : 'post',
									tempElem : '#tpl', // 模块容器
									pageConfig : { // 分页参数配置
										elem : '#paged', // 分页容器
										pageSize : 10
									// 分页大小，当前页面显示的条数
									},
									success : function() { // 渲染成功的回调
									},
									fail : function(msg) { // 获取数据失败的回调
										layer.msg("获取数据失败！");
									},
									complate : function() { // 完成的回调

									},
								});
					});

});
