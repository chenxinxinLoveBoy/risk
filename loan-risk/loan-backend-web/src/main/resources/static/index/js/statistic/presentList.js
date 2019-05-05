			layui.config({
				base: '../../js/' 
			});
			
			var startTime = "", endTime = "",itemName="";

			//初始化加载startTime
			$(function(){
				startTime = decodeURI(getQueryString("startTime"));
				endTime = decodeURI(getQueryString("endTime"));

				//我们强烈推荐你在代码最外层把需要用到的模块先加载
				layui.use(['layer', 'form', 'element','laydate'], function(){
					  var layer = layui.layer
					  ,form = layui.form()
					  ,element = layui.element();
					  form.render(); //刷新全部
					  
						var laydate = layui.laydate;
						  
						var start = {
						    format: 'YYYY-MM-DD hh:mm:ss'
						    ,istime: true
						    ,start: laydate.now(0,'YYYY-MM-DD 00:00:00')
						    ,choose: function(datas){
						      end.min = datas; //开始日选好后，重置结束日的最小日期
						      end.start = datas //将结束日的初始值设定为开始日
						    }
						};
					  
						var end = {
						    format: 'YYYY-MM-DD hh:mm:ss'
						    ,istime: true
						    ,choose: function(datas){
						      start.max = datas; //结束日选好后，重置开始日的最大日期
						    }
						};
					  
					  $("#startTime").on("click", function(){
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
					  var from =  getQueryString("from");;
					  if (startTime != '' || endTime != '' || from === "count") {
						  $("#startTime").val(startTime);
						  $("#endTime").val(endTime);
					  } else {
						  $("#startTime").val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
						  $("#endTime").val(laydate.now(0,'YYYY-MM-DD 23:59:59'));
						  startTime = $("#startTime").val();
						  endTime = $("#endTime").val();
					  }

						getList();
				})
			});
			
			// 条件搜索
			$('#search').on('click', function() {
				startTime = $("#startTime").val().trim();
				endTime = $("#endTime").val().trim();
				itemName =$("#Name").val()
				getList();
			});
			
			/**柱状图形化  start*/
			$('#histogram').on('click', function() {
				var beginTime1 = $("#startTime").val().trim();
				var endTime1 = $("#endTime").val().trim(); 
					layer.open({
					  type: 2,
					  title: '提现命中项统计柱状图',// 设置false表示不显示
					  closeBtn: 1, //0：不显示关闭按钮
					  shade: 0.4, //遮罩透明度
					  area: ['840px', '550px'],
					  skin: 'layui-layer-rim', //加上边框
					  fixed: false, //不固定
					  maxmin: true, // 允许屏幕最小化
//					  anim: 2,
					  content: ['presentHistogram.html?beginTime='+beginTime1+'&endTime='+endTime1], //iframe的url，no代表不显示滚动条
					});
			});
			function getList(){
				layui.use(['paging', 'layer', 'form', 'element'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form(),
						element = layui.element();
	
					// 页面初始化加载		start
	                paging.init({
	                    openWait: true,
						url: getCtxPath() + "/backend/present/application/queryPresentResult.do?v=" + new Date().getTime(),
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							t: Math.random(),
							startTime : startTime,
							endTime : endTime,
							itemName : itemName
						},
						type: 'post',
						tempElem: '#tpl', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 10 //分页大小，当前页面显示的条数
						}, 
						success: function() { //渲染成功的回调
	//						alert('渲染成功');
						},
						fail: function(msg) { //获取数据失败的回调
							layer.msg("获取数据失败！");
						},
						complate: function() { //完成的回调
							//queryResultCount();
						}
					});
	                // 初始化加载init     end!
	                
				});
		 	 
			}

 			
 			