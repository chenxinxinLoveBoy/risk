			layui.config({
				base: '../../js/' 
			});
			// 当前菜单名称
			var menuNames = "提额配置管理";
			// 初始化
			$(function(){
				getList();
			})
			var channelName = "", appLevel = "",scLiftConfigurationId="",state = "";
			// 条件搜索
			 function sel() {
				 channelName = $("#channelName").val().trim();
				 appLevel = $("#appLevel").val().trim();
				 scLiftConfigurationId=$("#scLiftConfigurationId").val().trim();
				 state = $("#state").val().trim();
  				getList(1);
			};
			
			 function add() {  
				 layer.open({
					  type: 2,
					  title: '新增',// 设置false表示不显示
					  closeBtn: 1, //0：不显示关闭按钮
					  shade: 0.4, //遮罩透明度
					  area: ['740px', '550px'],
					  skin: 'layui-layer-rim', //加上边框
					  fixed: false, //不固定
					  maxmin: true, // 允许屏幕最小化
					  content: ['scLiftConfiguration_add.html'], //iframe的url，no代表不显示滚动条
					});
 				}; 
			
			function getList(_pageIndex){
				if(appLevel == '00'){
					appLevel ='';
				}
				if(state == '00'){
					state ='';
				}
				//局部刷新回调获取参数,
				var isFirst = 1;
				var pageIndex = _pageIndex || $("#paged").find("em").eq(1).html()||1;
				
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
						url: getCtxPath() + "/backend/scLiftConfiguration/scLiftConfigurationListAll.do?v=" + new Date().getTime(),
						elem: '#content', //内容容器
						params: { //发送到服务端的参数 
							channelName : channelName,
							appLevel : appLevel,
							scLiftConfigurationId:scLiftConfigurationId,
							state  : state
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
							//绑定所有'编辑'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td').children('a[data-opt=edit]').on('click', function() {
									var id = $(this).data('id'); 
									layer.open({
										  type: 2,
										  title: '编辑',// 设置false表示不显示
										  closeBtn: 1, //不显示关闭按钮
										  shade: 0.4, //遮罩透明度
										  area: ['840px', '550px'],
										  skin: 'layui-layer-rim', //加上边框
										  fixed: false, //不固定
										  maxmin: true, // 允许屏幕最小化 
										  anim: 2,
										  content: ['scLiftConfiguration_add.html?scLiftConfigurationId='+id], //iframe的url，no代表不显示滚动条
										});
								});
							});
							
							//绑定所有'修改状态'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td').children('a[data-opt=update]').on('click', function() {
									var state= $(this).data('state');
									var version= $(this).data('version'); 
  									var str= "";
									if(state=='01'){
										str="失效"; 
										state='02';
									}else{
										 str="有效";
 										state='01';
									}
  									if(confirm('您确定将状态修改为【'+str+'】吗？')){ //只有当点击confirm框的确定时，该层才会关闭
									// ajax 请求后台
										$.ajax({
											url : getCtxPath() + "/backend/scLiftConfiguration/saveScLiftConfiguration.do",
											method : 'post',
											dataType : 'json',
											data: {
												scLiftConfigurationId: $(this).data('id'), // 传到后台的参数，子窗口的form表单
												state:state,
												version : version,
												menuNames: menuNames, // 菜单名称
												functionNames: ( "修改状态") // 按钮功能名称
 											},
											success : function(data) {
												if(data.code == "200"){
													layerTips.msg(data.message);// 提示信息
												}else{
													layerTips.msg(data.message);// 提示信息
												}
											},
											error: function(){
												parent.layer.alert("请求网络异常，请尝试重新登录！");
											},
											complete: function(){
												getList(); //局部刷新
											}
										});
									  }
									  return false; 
								});
	
							});
						},
					}); 
		
				}); 
			} 