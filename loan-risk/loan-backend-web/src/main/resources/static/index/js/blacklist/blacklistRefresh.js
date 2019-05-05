			layui.config({
				base: '../../js/' 
			});
			
			// 初始化
			$(function(){
				layui.use(['laydate'], function() {
					var laydate = layui.laydate;
					var start = {
					    format: 'YYYY-MM-DD hh:mm:ss'
						,istime: true
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
					$("#startTime").val(layui.laydate.now(0,'YYYY-MM-DD 00:00:00'));
					$("#endTime").val(layui.laydate.now(0,'YYYY-MM-DD 23:59:59'));
				});
			})
			function refreshRedis(){//将MySql中的 黑名单数据刷新进Redis缓存
				layui.use(['layer'], function() {
					var layer = layui.layer;
					layer.msg("刷新中...");//提示信息
					$("#refreshStatus").html("刷新中...");
					var startTime = $("#startTime").val();
					var endTime = $("#endTime").val();
					$.ajax({
						url : hostIp+ "/blacklist/refreshRedis.do?v=" + new Date().getTime(),// 请求服务器的url
						method: "post",
						dataType:"json",
						data: {
							startTime : startTime,
							endTime : endTime
						},
						success: function(data){
							//console.log(data);
							if(data.list.data == true){
								layer.msg("刷新成功！" , {icon: 1});//提示信息
								$("#refreshStatus").html("<font style='color:green'>刷新成功！</font>");
							}else{
								layer.msg("刷新失败！" , {icon: 2});//提示信息
								$("#refreshStatus").html("<font style='color:red'>刷新失败！</font>");
							}
						},
						error: function(){  
	 						layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
						} 
					});
				});
			}
			