			layui.config({
				base: '../../js/' 
			});
			var scoreTplId = "";

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
//				getList();
				getTplList();
			})
			var score = "", creditMoney = "", scoreCreditId = "",startTimeInterval="",endTimeInterval="";
			// 条件搜索
			$('#search').on('click', function() {
			 	score = $("#score").val().trim();
			 	creditMoney = $("#creditMoney").val().trim();
			 	scoreCreditId = $("#scoreCreditId").val().trim();
				getList();
			});
			
			function getList(){
				scoreTplId = $("#scoreTplId").val().trim();
				  startTimeInterval =  $("#startTime").val();
				  endTimeInterval =  $("#endTime").val();
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
						url: getCtxPath() + "/backend/scoreCreditHis/listAll.do?v=" + new Date().getTime(),
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							t: Math.random(),
							score : score,
							creditMoney : creditMoney,
							scoreCreditId : scoreCreditId,
							startTimeInterval : startTimeInterval,
							endTimeInterval : endTimeInterval,
							scoreTplId:scoreTplId
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
						},
					});
	                // 初始化加载init     end!
	                
		
				});
			 	 
			}

			function getTplList() {
				$.ajax({
					url : getCtxPath() + "/backend/scScoreTpl/scScoreTplListAll.do?v=" + new Date().getTime(),
					method : "post",
					dataType : "json",
					data : {
						state:"01"
					},
					success : function(data) {
						var arr = [];
						arr.push('<select name="scoreTplId" id = "scoreTplId" class="layui-input" lay-search="">');
						layui.each(data.list, function(index, item){
								if(scoreTplId === item.scoreTplId){
							    	arr.push('<option value="'+ item.scoreTplId + '" selected>' + item.scoreTplName + '</option>');
								}else{
							    	arr.push('<option value="'+ item.scoreTplId + '">' + item.scoreTplName + '</option>');
								}
						    });
						arr.push('</select>');
						$('#tplContent').html(arr.join(''));
						getList();
						form.render('select'); //刷新全部
					},
					error : function() {
						alert("获取用户信息失败,请尝试重新获取！");
					}
				});
			}
 			