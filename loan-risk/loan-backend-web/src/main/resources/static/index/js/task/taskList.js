			layui.config({
				base: '../../js/' 
			});
			
			// 初始化
			$(function() {
				getList();
				
				$('#search').on('click', function() {
					getList();
				});
				
				/**新增定时任务**/
				$('#add').on('click', function() {
					layer.open({
						type: 2,
						title: '新增定时任务',// 设置false表示不显示
						closeBtn: 1, //0：不显示关闭按钮
						shade: 0.4, //遮罩透明度
						area: ['800px', '600px'],
						skin: 'layui-layer-rim', //加上边框
						fixed: false, //不固定
						maxmin: true, // 允许屏幕最小化
//					  anim: 2,
						content: ['taskEdit.html'], //iframe的url，no代表不显示滚动条
					});
				});
				
			})

			/**
			 * 初始化参数
			 * @returns
			 */
			function initPara() {
				console.log("------------initpara");
				var searchFormPara = {};
				var jobGroup = $("#jobGroup").val().trim();
				var artifactId = $("#artifactId").val().trim();
				var beanClass = $("#beanClass").val();
				var springId = $("#springId").val();
				var methodName = $("#methodName").val();
				var jobStatus = $("#jobStatus").val();
				searchFormPara.jobGroup = jobGroup;
				searchFormPara.artifactId = artifactId;
				searchFormPara.beanClass = beanClass;
				searchFormPara.springId = springId;
				searchFormPara.methodName = methodName;
				searchFormPara.jobStatus = jobStatus;
				console.log(searchFormPara);
				return searchFormPara;
			}

			function getList(){
				layui.use(['paging', 'layer', 'form', 'element'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form(),
						element = layui.element();
	
					//局部刷新回调获取参数,
					var isFirst = 1;
					var pageIndex = $("#paged").find("em").eq(1).html()||1;
					// 页面初始化加载		start
	                paging.init({
	                    openWait: true,
	                    url : getCtxPath() + "/quartzTask/taskListByPage?v=" + new Date().getTime(), // 请求服务器的url
						elem: '#content', //内容容器
						params: initPara(),
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
							if(pageIndex != '' && pageIndex != '1' && isFirst === 1) {
								isFirst = 0;
								var totalCount = $("#count_page_number").html()||0;
								if( (parseInt(pageIndex)-1) * 10 >= parseInt(totalCount) ){
									pageIndex = 1;
								}
								paging.get({
		                            pageIndex: pageIndex,
		                            pageSize: 10
		                        });
							}

							//绑定所有'编辑'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').find('a[data-opt=edit]').on('click', function() {
									var _athis = $(this);
									layer.prompt({
										formType: 3,
										value: $(this).data('cronexp'),
										title: '请输入cron值',
										cancel: function(index){
											console.log('弹层的 index', index);
										}
									}, function(value, index, elem) {
										layer.close(index);
										updateCronExp(_athis.data('id'), value, _athis.data('artifactid'));
									});
								});
							});
							
							//绑定所有'删除'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').find('a[data-opt=delete]').on('click', function() {
									var _athis = $(this);
									layer.confirm('确认删除该定时任务吗？', {
									    btn: ['确认','取消'], //按钮
//									    shade: false //不显示遮罩
									}, function(index){
										deleteJobByJobId(_athis.data('id'), _athis.data('artifactid'));
									    layer.close(index);
									});
								});
							});
							
						},
					});
	                
				});
			}	 
			
			/**
			 * 删除定时任务
			 * @returns
			 */
			function deleteJobByJobId(jobId, artifactId) {
				var para = { 
						"jobId" : jobId
					}
				var data = {
					httpurl : artifactId +"/quartzTask/deleteJobByJobId", 
					artifactId : artifactId,
					data : JSON.stringify(para)
				};
				$.ajax({
					type : "POST",
					async : false,
					dataType : "JSON",
					cache : false,
					url : getCtxPath() + "/quartzTask/sendHttp",
					data :data,
					success : function(data) {
						console.log("-----deleteJobByJobId -------------data");
						console.log(data);
						if (data.code == '200') {
							 var data = data.list.data;
							 if (data.code != '200') {
								 layer.msg(data.message , {icon: 2});
							 } else {
								 layer.msg("操作成功", {icon: 1});
							 }
						} else {
							layer.msg("操作失败，请稍后重试！" , {icon: 2});
						}
						$("#search").click();
					}, 
					error: function(data) {
						console.log(data);
					}
				});
			}
			
			/**
			 * 更新定时任务表达式
			 * @returns
			 */
			function updateCronExp(jobId, cronExp, artifactId) {
				var para = { 
						"jobId" : jobId,
						"cronExp" : cronExp,
						"artifactId": artifactId
				}
				var data = {
						httpurl : artifactId +"/quartzTask/updateCronExp", 
						artifactId : artifactId,
						data : JSON.stringify(para)
				};
				$.ajax({
					type : "POST",
					async : false,
					dataType : "JSON",
					cache : false,
					url : getCtxPath() + "/quartzTask/sendHttp",
					data :data,
					success : function(data) {
						console.log("-----updateCronExp -------------data");
						console.log(data);
						if (data.code == '200') {
							var data = data.list.data;
							if (data.code != '200') {
								layer.msg(data.message , {icon: 2});
							} else {
								layer.msg("修改成功", {icon: 1});
								layer.closeAll('loading');
							}
						} else {
							layer.msg("操作失败，请稍后重试！" , {icon: 2});
						}
						$("#search").click();
					}, 
					error: function(data) {
						console.log(data);
					}
				});
			}
			
			function changeJobStatus(artifactId, jobId, cmd) {
				var para = { 
						"jobId" : jobId,
					    "cmd" : cmd,
					    "artifactId": artifactId
					}
				var data = {
					httpurl : artifactId +"/quartzTask/changeJobStatus", 
					artifactId : artifactId,
					data : JSON.stringify(para)
				};
				layer.load(3);
				$.ajax({
					type : "POST",
					async : false,
					dataType : "JSON",
					cache : false,
					url : getCtxPath() + "/quartzTask/sendHttp",
					data :data,
					success : function(data) {
						console.log("-----changeJobStatus -------------data");
						console.log(data);
						if (data.code = '200') {
							var httpData  = data.list;
							if(httpData.code = '200') {
								layer.msg("操作成功");
							} else {
								layer.msg("操作失败，请稍后重试!");
							}
						} else {
							layer.msg("操作失败，请稍后重试!");
						}
						
						$("#search").click();
						layer.closeAll('loading');
					}, 
					error: function(data) {
						console.log(data);
						layer.closeAll('loading');
					}
				});
			}
			
