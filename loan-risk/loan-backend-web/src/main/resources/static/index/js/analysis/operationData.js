			layui.config({
				base: '../../js/' 
			});
			
			// 初始化
			$ (function() {
				initDateFiled();
				initParams();
				getSummaryData();
				getList();
			})

			var data = {};
			
			// 搜索
			$('#search').on('click', function() {
				if (checkParam()) {
					initParams();
					getSummaryData();
					getList();
				}
			});
			
			function initDateFiled() {
				var yesterday = getFormatDateStr(new Date(new Date().getTime() - 24*60*60*1000));
				var curDate = getFormatDateStr(new Date());
				$("#startTime").val(yesterday);
				$("#endTime").val(curDate);
			}
			
			function initParams() {
				var appChannel = $("#appChannel").val();
				var calcUnit = $("#calcUnit").val();
				var startTime = $("#startTime").val();
				var endTime = $("#endTime").val();
				data.appChannel = appChannel;
				data.calcUnit = calcUnit;
				data.startTime = startTime;
				data.endTime = endTime;
			}
			
			function checkParam() {
				if ($("#startTime").val() == '') {
					layer.msg("请选择开始时间", {icon: 7});
					return false;
				}
				if ($("#endTime").val() == '') {
					layer.msg("请选择结束时间", {icon: 7});
					return false;
				}
				
				var calcUnit = $("#calcUnit").val();
				if (calcUnit == 1) { //天
					var days = getDays($("#startTime").val(), $("#endTime").val());
					if(days > 30) {
						layer.msg("按天显示时，最大时间跨度为30天", {icon: 7}); // 当前窗口弹框提示信息	
						return false;
					}
				} else if (calcUnit == 2) { //月
					var days = getDays($("#startTime").val(), $("#endTime").val());
					if(days > 365) {
						layer.msg("按月显示时，最大时间跨度为一年", {icon: 7});	
						return false; 
					}
				} else if (calcUnit == 3) { //季
					var days = getDays($("#startTime").val(), $("#endTime").val());
					if(days > 365) {
						layer.msg("按季显示时，最大时间跨度为一年", {icon: 7});	
						return false; 
					}
				} else if (calcUnit == 4) { //年
					var days = getDays($("#startTime").val(), $("#endTime").val());
					if(days > 365) {
						layer.msg("按年显示时，最大时间跨度为5年", {icon: 7});	
						return false; 
					}
				}
				return true;
			}
			
			/**
			 * 获得列表上面统计数据
			 */
			function getSummaryData(){
				console.log("--getSummaryData");
	            $.ajax({
	                type:"post",
	                data:data,
	                url: getCtxPath() + "/backend/analysis/operationData/getSummaryData?v=" + new Date().getTime(),// 请求服务器的url
	                success: function(data,status) {
	                	console.log(data);
	                	var dataMap = data.list.data.optDataBo;
	                	var arr = [
	                	  [
	                		dataMap["newRegUserNum"],
	                		dataMap["loanSumMoney"],
	                		dataMap["applayNum"]
	                	  ],
	                	  [
	                		 dataMap["overdueSumMoney"],
	                		 dataMap["loanSumCount"],
	                		 dataMap["loanRate"]
	                	  ],
	                	  [
	                		  dataMap["loanBadRate"],
	                		  dataMap["m0OverdueCount"],
	                		  dataMap["m1OverdueCount"],
	                		  dataMap["m2OverdueCount"]
	                	  ]
	                	];
	                	
						$("#summaryTabId tr").each(function(index) {
							if(index == 0) {
								if(index != 0) $(this).children().eq(1).text(arr[index][0] + "人");
								$(this).children().eq(3).text(fmoney(arr[index][1]) + "元");
								$(this).children().eq(5).text(arr[index][2] + "次");
							} else if(index == 1) {
								$(this).children().eq(1).text(fmoney(arr[index][0]) + "元");
								$(this).children().eq(3).text(arr[index][1] + "次");
								$(this).children().eq(5).text(arr[index][2]);
							} else {
								$(this).children().eq(1).text(arr[index][0]);
								$(this).children().eq(3).text(arr[index][1] + "次");
								$(this).children().eq(5).text(arr[index][2] + "次");
								$(this).children().eq(7).text(arr[index][3] + "次");
							}
				             
						});
						
						$("#summaryTotal tr").each(function(index) {
							$(this).children().eq(3).text(dataMap["loanTotalUser"] + "个");
						});
	                }
	            });
	            
	            $.ajax({
	                type:"post",
	                data:data,
	                url: getCtxPath() + "/backend/analysis/operationData/getTotalSummaryData?v=" + new Date().getTime(),// 请求服务器的url
	                success: function(data,status) {
	                	console.log("-->>getSummaryData:");
	                	console.log(data);
	                	var totalSummaryMap = data.list.data;
						$("#summaryTotal tr").each(function(index) {
							$(this).children().eq(1).text(totalSummaryMap["totalUser"] + "人");
						});
						$("#summaryTabId tr").each(function(index) {
							 if(index == 0) $(this).children().eq(1).text(totalSummaryMap["regUserNum"] + "人");
						});
	                }
	            });

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
						url: getCtxPath() + "/backend/analysis/operationDataList?v=" + new Date().getTime(),// 请求服务器的url
						elem: '#content', //内容容器
						params: data, //发送服务器参数
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
						complate: function() { //完成的回调
							
						},
					});
				});
			}