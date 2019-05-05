			layui.config({
				base: '../../js/' 
			});
			
			var url = getCtxPath() + "/backend/scBanControl/getTemplate.do?v=" + new Date().getTime();// 请求服务器的url
			// 初始化
			$(function(){
					getList();
					// 条件搜索
					$('#search').on('click', function() {
						getList();
					});
			})
			
	
			function getList(_pageIndex){
				//查询条件
				var banCode = $("#banCode").val().trim();
				var ruleName = $("#ruleName").val().trim();
				var creditType = $("#creditType").val().trim();
				var ifRefuse = $("#ifRefuse").val().trim();
				var state = $("#state").val().trim();
				var banControlTplId = getQueryString("banControlTplId"); 
				
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
 							banCode:banCode,
							ruleName:ruleName,
							creditType:creditType,
							ifRefuse:ifRefuse,
							banControlTplId:banControlTplId,
							state:state
						},
						type: 'post',
						tempElem: '#tpl', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged',//分页容器
						}, 
						success: function() { //渲染成功的回调

						},
						fail: function(msg) { //获取数据失败的回调
							layer.msg("获取数据失败！");
						},
						complate: function() { //完成的回调
							//绑定所有'编辑'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=edit]').on('click', function() {
									 
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
										  content: [getCtxPath() + '/index/views/scBanControl/scBanControl_add.html?id='+$(this).data('id')], //iframe的url，no代表不显示滚动条
										});
								});
	
							});
							
							//绑定所有'修改状态'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=update]').on('click', function() {
									var state= $(this).data('state');
 									var str="有效";
									if(state=='01'){
										str="无效";
										state='02';
									}else{
 										state='01';
									}
									var menuNames ='禁止项规则';
  									if(confirm('您确定将状态修改为【'+str+'】吗？')){ //只有当点击confirm框的确定时，该层才会关闭
									// ajax 请求后台
										$.ajax({
											url : getCtxPath() + "/backend/scBanControl/saveScBanControl.do",
											method : 'post',
											dataType : 'json',
											data: {
												banControlId: $(this).data('id'), // 传到后台的参数，子窗口的form表单
												state:state,
												version:$(this).data('version'),
												banControlTplId:$(this).data('tpl'),
												menuNames : menuNames,
												functionNames: ($(this).data('id') != ''  ? "修改禁止项状态" : "新增") // 按钮功能名称 不为空修改 为空 新增
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
						}
					});
	                // 初始化加载init     end!
	                
				});
			 	 
			}
 			
 			