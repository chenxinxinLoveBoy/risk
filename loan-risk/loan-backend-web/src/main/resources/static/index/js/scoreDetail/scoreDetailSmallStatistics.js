			layui.config({
				base: '../../js/'
			});
			
			
			// 初始化
			$(function(){
				layui.use(['laydate'], function() {
					var $ = layui.jquery,
					layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
					layer = layui.layer; //获取当前窗口的layer对象
					    
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
					  
					  $("#beginTime").val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
					  $("#endTime").val(laydate.now(0,'YYYY-MM-DD 23:59:59'));
						
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
					var beginTime = $("#beginTime").val();
					var endTime = $("#endTime").val();
					getTplList(beginTime,endTime);
//					getList(beginTime,endTime);
				  });
			})
			var scoreTplId="";
			// 条件搜索
			$('#search').on('click', function() {
				var beginTime = $("#beginTime").val();
				var endTime = $("#endTime").val();
				scoreTplId = $("#scoreTplId").val().trim();
				getList(beginTime,endTime,scoreTplId);
			});
			
			/**饼状图形化  start*/
			$('#pie').on('click', function() {
				var beginTime1 = $("#beginTime").val().trim();
				var endTime1 = $("#endTime").val().trim();
				scoreTplId = $("#scoreTplId").val().trim();
				var tplContent = $("#scoreTplId option:selected").text();//获取下拉框选中的文本值
 				var index=layer.open({
					  type: 2,
					  title: tplContent+'--评分小类统计饼状图',// 设置false表示不显示
					  closeBtn: 1, //0：不显示关闭按钮
					  shade: 0.4, //遮罩透明度
					  area: ['1500px', '550px'],
//					  skin: 'layui-layer-rim', //加上边框
					  fixed: false, //不固定
					  maxmin: true, // 允许屏幕最小化
//					  anim: 2,
					  content: ['scoreSmallStatisticsPie.html?beginTime='+beginTime1+'&endTime='+endTime1+"&scoreTplId="+scoreTplId], //iframe的url，no代表不显示滚动条
					});
				layer.full(index);  //弹框全屏化
			});
			/**饼状图形化     end*/
			
			
			function getList(beginTime,endTime,scoreTplId){
				layui.use(['paging', 'form', 'element'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form(),
						element = layui.element();
					    form.render(); //刷新全部
					    
					// 页面初始化加载	 
	                paging.init({
	                    openWait: true,
						url: getCtxPath() + "/backend/scoreDetail/getSamllStatistics.do?v=" + new Date().getTime(),// 请求服务器的url
						elem: '#content2', //内容容器
						params: { //发送到服务端的参数
							beginTime : beginTime,
							endTime : endTime,
							scoreTplId:scoreTplId
						},
						type: 'post',
						tempElem: '#tpl2', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 10 //分页大小，当前页面显示的条数
						},
						success: function(data) { //渲染成功的回调
						},
						fail: function(msg) { //获取数据失败的回调
							layer.msg("获取数据失败！");
						},
						complate: function() { //完成的回调
							//绑定所有'命中客户详情'按钮事件						
							$('#content2').children('tr').each(function() {
								var $that = $(this);
 								$that.children('td:last-child').children('a[data-opt=find]').on('click', function() {
									var href = getCtxPath() + "/index/views/scoreDetail/applicationFromScoreDetailSmallStatistics.html?id="+$(this).data('id')+"&startTime="+encodeURI(beginTime)+"&endTime="+encodeURI(endTime);
 									var data = {
										"title": "评分小类命中客户列表-"+$(this).data('id'),
										"icon": "&#xe60c;",
										"href": href
									}
									window.parent.topTab.tabAdd(data);
								});
							});
						},
					});
	                
	                
	                
				});
			}	 
			
			function getTplList(beginTime,endTime) {
				$.ajax({
					url : getCtxPath() + "/backend/scScoreTpl/scScoreTplListAll.do?v=" + new Date().getTime(),
					method : "post",
					dataType : "json",
					data : {
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
						scoreTplId = $("#scoreTplId").val().trim();
 						getList(beginTime,endTime,scoreTplId);
 						form.render(); //刷新全部
					},
					error : function() {
						alert("获取用户信息失败,请尝试重新获取！");
					}
				});
			}