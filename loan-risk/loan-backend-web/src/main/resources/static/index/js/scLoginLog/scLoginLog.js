			layui.config({
				base: '../../js/' 
			});
			
			
			var url = getCtxPath() + "/backend/scLoginLog/getAllScLoginLogById.do?v=" + new Date().getTime();// 请求服务器的url
			// 初始化
			$(function(){
				getList();
			})
			
			// 条件搜索
			$('#search').on('click', function() {
				getList(1);
			});
	 
			function getList(_pageIndex){
				var id = getQueryString("id");
				//id不存在，不需要请求初始化，新增页面使用
				if(id === undefined || id === ""){
					return false;
				}
				//局部刷新回调获取参数,
				var isFirst = 1;
				var pageIndex =_pageIndex || $("#paged").find("em").eq(1).html()||1;
				
				layui.use(['paging', 'layer', 'form', 'element'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form(),
						element = layui.element();
						form.render(); //重新渲染
	
					// 页面初始化加载		start
	                paging.init({
	                    openWait: true,
						url: url,
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							t: Math.random(),
							userId:id
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
 					});
	                // 初始化加载init     end!
	                
				});
			 	 
			}
 			
 			