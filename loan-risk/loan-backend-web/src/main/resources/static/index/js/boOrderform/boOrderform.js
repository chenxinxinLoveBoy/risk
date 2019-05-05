			layui.config({
				base: '../../js/'
			});
			
			//初始化加载startTime
			$(function(){
				
				//我们强烈推荐你在代码最外层把需要用到的模块先加载
				layui.use(['layer', 'form', 'element','laydate'], function(){
					  var layer = layui.layer
					  ,form = layui.form()
					  ,element = layui.element();
					  form.render(); //刷新全部
					  
						var laydate = layui.laydate;
						  
						var start = {
						    min: '1900-01-01 00:00:00'
						    ,istime: true
						    ,format: 'YYYY-MM-DD hh:mm:ss'
						    ,max: '2099-06-16 23:59:59'
						    ,istoday: false
						    ,choose: function(datas){
						      end.min = datas; //开始日选好后，重置结束日的最小日期
						      end.start = datas //将结束日的初始值设定为开始日
						    }
						};
					  
						var end = {
						    min: '1900-01-01 00:00:00'
						    ,istime: true
						    ,format: 'YYYY-MM-DD hh:mm:ss'
						    ,max: '2099-06-16 23:59:59'
						    ,istoday: false
						    ,choose: function(datas){
						      start.max = datas; //结束日选好后，重置开始日的最大日期
						    }
						};
					  
					  $("#startTime").on("click", function(){
					    start.elem = this;
					    laydate(start);
					  });
					  $("#endTime").on("click", function(){
					    end.elem = this
					    laydate(end);
					  });
				})
			});
			
			var boOrderformId = "", certCode = "", startTime = "", endTime = "",
			appName = "", days = 0, productQuota = "", state = "", overdueMark = "",
				flag = "";
			// 初始化
			$(function(){
				flag = getQueryString("flag");
				if(flag == 1){//已逾期待还款
					overdueMark = "1";
					state = "1";
					$("#overdueMarkDiv").attr("style", "display:none");
					$("#stateDiv").attr("style", "display:none");
					$("#repayMoney").attr("style", "display:none");
					$("#inAdvance").attr("style", "display:none");
					$("#repayType").attr("style", "display:none");
					$("#title").html("已逾期待还款");
				}
				if(flag == 2){//未逾期待还款
					overdueMark = "2";
					state = "1";
					$("#overdueMarkDiv").attr("style", "display:none");
					$("#stateDiv").attr("style", "display:none");
					$("#repayMoney").attr("style", "display:none");
					$("#inAdvance").attr("style", "display:none");
					$("#repayType").attr("style", "display:none");
					$("#title").html("未逾期待还款");
				}
				if(flag == 3){// 已还款
					state = "2";
					$("#stateDiv").attr("style", "display:none");
					$("#title").html("已还款");
				}
				//getList();
			})
			
			// 搜索
			$('#search').on('click', function() {
				
				boOrderformId = $("#boOrderformId").val();
				certCode = $("#certCode").val();
				startTime = $("#startTime").val();
				endTime = $("#endTime").val();
				appName = $("#appName").val();
				days = $("#days").val();
				productQuota = $("#productQuota").val();
				state = $("#state").val();
				if((boOrderformId == "" || boOrderformId == null) && (certCode == "" || certCode == null)){
					parent.layer.msg("借款编号、借款人身份证号不能全部为空!" , {icon: 2});
					return ;
				}
				if(certCode != '' && certCode != null) {
					var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
					if(reg.test(certCode) === false){ 
						parent.layer.msg("身份证号输入不正确" , {icon: 2});
						return ;
					}
				}
				getList();
			});
			
			// 逾期条件选择
			$('#overdueMark1').on('click', function() {
				$('#overdueMark2').attr("checked", false);
				if($(this).is(":checked")){
					overdueMark = $(this).val();
				} else {
					overdueMark = "";
				}
			});
			$('#overdueMark2').on('click', function() {
				$('#overdueMark1').attr("checked", false);
				if($(this).is(":checked")){
					overdueMark = $(this).val();
				} else {
					overdueMark = "";
				}
			});
			
			function getList(){
				layui.use(['paging', 'form'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form();
	
					// 页面初始化加载		start
	                paging.init({
	                    openWait: true,
						url: hostIp + "/boOrderform/findAll.do?v=" + new Date().getTime(),// 请求服务器的url
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							t: Math.random(),
							boOrderformId : boOrderformId,
							certCode : certCode,
							startTime : startTime,
							endTime : endTime,
							appName : appName,
							days : days,
							productQuota : productQuota,
							state : state,
							overdueMark : overdueMark
						},
						type: 'post',
						tempElem: '#tpl', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 15 //分页大小，当前页面显示的条数
						},
						success: function() { //渲染成功的回调
						},
						fail: function(msg) { //获取数据失败的回调
							layer.msg("获取数据失败！");
						},
						complate: function() { //完成的回调
				
						},
					});
	                // 初始化加载init     end!

				});
			}	 
 			