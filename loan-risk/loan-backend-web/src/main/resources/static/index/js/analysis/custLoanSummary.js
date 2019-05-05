			layui.config({
				base: '../../js/' 
			});
			
			// 初始化
			$ (function() {
				initDateFiled();
				initParams();
				getList();
			})
			
			var data = {};
			
			// 搜索
			$('#search').on('click', function() {
				initParams();
				getList();
//				parent.layer.alert('你点击了搜索按钮');
			});
			
			function initDateFiled() {
				var yesterday = getFormatDateStr(new Date(new Date().getTime() - 24*60*60*1000));
				var curDate = getFormatDateStr(new Date());
				$("#startTime").val(yesterday);
				$("#endTime").val(curDate);
			}
			
			function initParams() {
				data = {};
				var appChannel = $("#appChannel").val();
				var idCard = $("#idCard").val();
				var startTime = $("#startTime").val();
				var endTime = $("#endTime").val();
				data.appChannel = appChannel;
				data.idCard = idCard;
				data.startTime = startTime;
				data.endTime = endTime;
			}
			
			function getList(){
				layui.use(['paging', 'layer', 'form', 'element'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form(),
						element = layui.element();
	                paging.init({
	                    openWait: true,
						url: getCtxPath() + "/backend/analysis/custLoanSummaryList?v=" + new Date().getTime(),// 请求服务器的url
						elem: '#content', //内容容器
						params:  data, //发送服务器参数
						type: 'post',
						tempElem: '#tpl', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 10 //分页大小，当前页面显示的条数
						},
						success: function() { //渲染成功的回调
							//alert('渲染成功');
						},
						fail: function(msg) { //获取数据失败的回调
							layer.msg("获取数据失败！");
						},
						complate: function(ddd) { //完成的回调
							console.log("-->>compleate");
							console.log(ddd);
							//alert('处理完成');
							//绑定所有'查看详情'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=del]').on('click', function() {
//									layer.msg($(this).data('id')); // 当前窗口弹框提示信息
									$(location).attr("href", hostIpHtml + "/index/views/customerManager/customerInfo.html?id=" + $(this).data('id'));
									return false; 
								});
							});
							
						},
					});
				});
			}	 
			
 			
 			