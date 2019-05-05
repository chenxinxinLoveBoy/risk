			layui.config({
				base: '../../js/' 
			});
			
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
				getList();
			})
			var banTplName = "", banTplCode = "",state = "";
			// 条件搜索
			 function sel() {
				 banTplName = $("#banTplName").val().trim();
				 banTplCode = $("#banTplCode").val().trim();
				 state = $("#state").val().trim();
				getList(1);
			}; 
			
			function getList(_pageIndex){
				if(state == '00'){
					state ='';
				}
				//局部刷新回调获取参数,
				var isFirst = 1;
				var pageIndex = _pageIndex || $("#paged").find("em").eq(1).html()||1;
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
						url: getCtxPath() + "/backend/ScTemplateHist/templatelisthisAll.do?v=" + new Date().getTime(),
						elem: '#content', //内容容器
						params: { //发送到服务端的参数 
							banTplName : banTplName,
							banTplCode : banTplCode,
							state  : state,
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
							if (pageIndex != '' && pageIndex != '1' && isFirst === 1) {
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
						},
						fail: function(msg) { //获取数据失败的回调
							layer.msg("获取数据失败！");
						},
						complate: function() { //完成的回调
							//重新渲染复选框
							form.render('checkbox');
							form.on('checkbox(allselector)', function(data) {
								var elem = data.elem;
								$('#content').children('tr').each(function() {
									var $that = $(this);
									//全选或反选
									$that.children('td').eq(0).children('input[type=checkbox]')[0].checked = elem.checked;
									form.render('checkbox');
								});
							}); 
							
							/*//绑定所有'预览'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=edit]').on('click', function() {
									var id= $(this).data('id');
									var name =  $(this).data('name'); 
									var level = $(this).data('le'); 
									var href = getCtxPath() + "/index/views/templateHistory/templateHistroyedit.html?banControlTplHisId="+$(this).data('id');
									var data = {
										"title": name+level+"-禁止项详情",
										"icon": "&#xe60c;",	
										"href": href
									} 
									window.parent.topTab.tabAdd(data); 
								});
							});*/
							
							//绑定所有'预览'按钮事件						
							$('#content').children('tr').each(function() {
								var id = getQueryString("id");   
								var $that = $(this); 
								$that.children('td:last-child').children('a[data-opt=find]').on('click', function() { 
									layer.open({
										  type: 2,
										  title: '预览模版历史信息',// 设置false表示不显示
										  closeBtn: 1, //不显示关闭按钮
										  shade: 0.4, //遮罩透明度
										  area: ['840px', '550px'],
										  skin: 'layui-layer-rim', //加上边框
										  fixed: false, //不固定
										  maxmin: true, // 允许屏幕最小化 
										  anim: 2,
										  content: ['templateHistroyedit.html?banControlTplHisId='+$(this).data('id')], //iframe的url，no代表不显示滚动条
										});
								});

							}); 
						},
					}); 
		
				}); 
			} 