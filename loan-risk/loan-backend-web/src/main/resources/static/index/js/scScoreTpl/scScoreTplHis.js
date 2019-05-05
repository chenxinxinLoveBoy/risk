layui.config({
	base : '../../js/'
});

// 初始化
$(function() {
	getList();
})
var scoreTplName = "", scoreTplCode = "", state = "", beginTime1="" ,endTime1="";
// 条件搜索
function sel() {
	 scoreTplName = $("#scoreTplName").val().trim();
	 scoreTplCode = $("#scoreTplCode").val().trim();
	 beginTime1 = $("#beginTime").val().trim();
	 endTime1 = $("#endTime").val().trim();
	state = $("#state").val().trim();
	getList(1);
};

function getList(_pageIndex) {
	if (state == '00') {
		state = '';
	}
	// 局部刷新回调获取参数,
	var isFirst = 1;
	var pageIndex = _pageIndex || $("#paged").find("em").eq(1).html() || 1;

	layui.use([ 'paging', 'layer', 'form', 'element','laydate' ],
			function() {
		                var $ = layui.jquery, 
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
						layer = layui.layer, // 获取当前窗口的layer对象
						form = layui.form(), element = layui.element();

		                
		                //开始时间
						  var start = {
								    format: 'YYYY-MM-DD hh:mm:ss'
								    ,istime: true
								    ,start: laydate.now(0,'YYYY-MM-DD 00:00:00')
								    ,choose: function(datas){
								      end.min = datas; //开始日选好后，重置结束日的最小日期
								      end.start = datas //将结束日的初始值设定为开始日
								    }
								};
							  //结束时间
								var end = {
								    format: 'YYYY-MM-DD hh:mm:ss'
								    ,istime: true
								    ,choose: function(datas){
								      start.max = datas; //结束日选好后，重置开始日的最大日期
								    }
								};
							  
							  $("#beginTime").on("click", function(){
							    start.elem = this;
							    laydate(start);
							    if(laydate.now(0,'YYYY-MM-DD hh:mm:ss') == $(this).val().trim()){
								    $(this).val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
							    }
							  });
							  
							  $("#endTime").on("click", function(){
							    end.elem = this
							    laydate(end);
							  });
						// 页面初始化加载 start
						paging.init({
									openWait : true,
									url : getCtxPath() + "/backend/scScoreTplHis/scScoreTplHisListAll.do?v=" + new Date().getTime(),
									elem : '#content', // 内容容器
									params : { // 发送到服务端的参数
										scoreTplName : scoreTplName,
										scoreTplCode : scoreTplCode,
										state : state,
										beginTime1:beginTime1,
										endTime1:endTime1
									},
									type : 'post',
									tempElem : '#tpl', // 模块容器
									pageConfig : { // 分页参数配置
										elem : '#paged', // 分页容器
										pageSize : 10 // 分页大小，当前页面显示的条数
									},
									success : function() { // 渲染成功的回调
										if (pageIndex != '' && pageIndex != '1' && isFirst === 1) {
											isFirst = 0;
											var totalCount = $("#count_page_number").html() || 0;
											if ((parseInt(pageIndex) - 1) * 10 >= parseInt(totalCount)) {
												pageIndex = 1;
											}
											paging.get({
												pageIndex : pageIndex,
												pageSize : 10
											});
										}
									},
									fail : function(msg) { // 获取数据失败的回调
										layer.msg("获取数据失败！");
									},
									complate : function() { // 完成的回调
									},
								});
					});
}