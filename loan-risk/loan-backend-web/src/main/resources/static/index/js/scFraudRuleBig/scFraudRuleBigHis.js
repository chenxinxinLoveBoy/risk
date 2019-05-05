			layui.config({
				base: '../../js/' 
			});
		
			var url = getCtxPath() + "/backend/scFraudRuleBigHis/getAllScFraudRuleBigHis.do?v=" + new Date().getTime();// 请求服务器的url
			// 初始化
			$(function(){
 				getList("","","");
			})
			
			// 条件搜索
			$('#search').on('click', function() {
			 	var fraudRuleName = $("#fraudRuleName").val().trim();
			 	var fraudRuleBigCode = $("#fraudRuleBigCode").val().trim();
				var creditType = $("#creditType").val().trim();
				if(creditType=='00'){
					creditType='';
				}
 				getList(fraudRuleName,fraudRuleBigCode,creditType);
			});
			
			
			function getList(fraudRuleName, fraudRuleBigCode,creditType){
				var creditType = $("#creditType").val().trim();
				if(creditType=='00'){
					creditType='';
				}
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
							fraudRuleName:fraudRuleName,
							fraudRuleBigCode:fraudRuleBigCode,
 							creditType:creditType
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
							//绑定所有'预览'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=edit]').on('click', function() {
									 
									layer.open({
										  type: 2,
										  title: '预览',// 设置false表示不显示
										  closeBtn: 1, //不显示关闭按钮
										  shade: 0.4, //遮罩透明度
										  area: ['840px', '550px'],
										  skin: 'layui-layer-rim', //加上边框
										  fixed: false, //不固定
										  maxmin: true, // 允许屏幕最小化
	//									  offset: 'rb', //右下角弹出
	//									  time: 2000, //2秒后自动关闭
										  anim: 2,
										  content: ['scFraudRuleBigHis_add.html?id='+$(this).data('id')], //iframe的url，no代表不显示滚动条
										});
								});
	
							});
							
						},
					});
	                // 初始化加载init     end!
	                
				});
			 	 
			}
 			
 			