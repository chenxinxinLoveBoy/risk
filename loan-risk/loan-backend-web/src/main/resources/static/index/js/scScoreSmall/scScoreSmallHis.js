			layui.config({
				base: '../../js/' 
			});
			
			var url = getCtxPath() + "/backend/scScoreSmallHis/getAllScScoreSmallHis.do?v=" + new Date().getTime();// 请求服务器的url
			// 初始化
			$(function(){
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
					$("#startTime").val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
					startTime = $("#startTime").val().trim();  
				})
				 
				getList("","","","","");
			})
			
			// 条件搜索
			$('#search').on('click', function() {
		 	var scoreBigCode = $("#scoreBigCode").val().trim();
			var scoreSmallCode = $("#scoreSmallCode").val().trim();
			var scoreRuleName = $("#scoreRuleName").val().trim();
			var startTimeInterval =  $("#startTime").val();
			var endTimeInterval =  $("#endTime").val();
			getList(scoreBigCode,scoreSmallCode,scoreRuleName,startTimeInterval,endTimeInterval);
		});
			
			function getList(scoreBigCode,scoreSmallCode,scoreRuleName){ 
				var startTimeInterval =  $("#startTime").val();
				var endTimeInterval =  $("#endTime").val();
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
						url: url,
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							t: Math.random(),
 							scoreBigCode:scoreBigCode,
							scoreSmallCode:scoreSmallCode,
							scoreRuleName:scoreRuleName,
							startTimeInterval : startTimeInterval,
							endTimeInterval : endTimeInterval
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
							//绑定所有'预览'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=edit]').on('click', function() {
									layer.open({
										  type: 2,
										  title: '预览',// 设置false表示不显示
										  closeBtn: 1, //不显示关闭按钮
										  shade: 0.4, //遮罩透明度
										  area: ['840px', '550px'],
										  skin: 'layui-layer-rim', //加上边框
										  fixed: false, //不固定
										  maxmin: true, // 允许屏幕最小化
	//									  offset: 'rb', //右下角弹出
	//									  time: 2000, //2秒后自动关闭
										  anim: 2,
										  content: ['scScoreSmallHis_add.html?id='+$(this).data('id')], //iframe的url，no代表不显示滚动条
										});
								});
	
							});
						},
					});
	                // 初始化加载init     end!
					
				});
			 	 
			}
 			
 			