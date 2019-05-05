/*layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
				function() {
					var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
					layer = layui.layer, // 获取当前窗口的layer对象
					form = layui.form(), element = layui.element();
				});

$(function() {
	 var customerId = getQueryString("id");//获取页面传过来的平台用户编号  
	 console.log(customerId);
	$.ajax({
				url : getCtxPath() + "/backend/CuCustomerDire/getEntityById.do?v=" + new Date().getTime(),// 请求服务器的url
				method : "post",
				dataType : "json",
				data : {
					customerId : customerId
				},
				success : function(data) {
					console.log(data);
					if(data.data.cuAppInfoObject.appInfoId!=null){
						$("#appName").html(data.data.customerInfoObject.cuPlatformCustomer.platformCustomerId);
						$("#name").html(data.data.customerInfoObject.cuPlatformCustomer.name);
						$("#phoneNum").html(data.data.customerInfoObject.cuPlatformCustomer.phoneNum);
					 
						 
						
						$("#age").html(data.data.customerInfoObject.cuPlatformCustomer.age);
						  
					}
  					  
				},
				error : function() { 
					layer.msg("获取客户手机应用列表信息失败,请尝试重新获取！" , {icon: 2});//提示信息
				}
			});

})
*/

			layui.config({
				base: '../../js/' 
			});
			
			// 初始化
			$(function(){
				var customerId = getQueryString("id");
				//var customerId ='cf29963e97f34b2c8744eb33e1bb4aeb';
				//console.log(customerId);
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
						url : getCtxPath() + "/backend/iosEquipmentInfo/findListById.do?v=" + new Date().getTime(),// 请求服务器的url
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							customerId : customerId
						},
						type: 'post',
						tempElem: '#tpl', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 10 //分页大小，当前页面显示的条数
						},
						success: function() { //渲染成功的回调
						},
						fail: function(msg) { //获取数据失败的回调
							layer.msg("获取数据失败！");
						},
						complate: function() { //完成的回调 
							$('#content').children('tr').each(function() {
								var $that = $(this)   
								$that.children('td:last-child').children('a[data-opt=find]').on('click', function() {									 
									layer.open({
										  type: 2,
										  title: '详情',// 设置false表示不显示
										  closeBtn: 1, //不显示关闭按钮
										  shade: 0.4, //遮罩透明度
										  area: ['840px', '615px'],
										  skin: 'layui-layer-rim', //加上边框
										  fixed: false, //不固定
										  maxmin: true, // 允许屏幕最小化 
										  anim: 2,
										  content: ['iosEquipmentInfo_detail.html?find=1&id='+$(this).data('id')],
										});
								});
	
							});			
							
							
							//绑定所有'编辑'按钮事件						
							/*$('#content').children('tr').each(function() {
								var $that = $(this); 
								$that.children('td:last-child').children('a[data-opt=edit]').on('click', function() {
									 
									layer.open({
										  type: 2,
										  title: '编辑',// 设置false表示不显示
										  closeBtn: 1, //不显示关闭按钮
										  shade: 0.4, //遮罩透明度
										  area: ['840px', '615px'],
										  skin: 'layui-layer-rim', //加上边框
										  fixed: false, //不固定
										  maxmin: true, // 允许屏幕最小化 
										  anim: 2,
										  content: ['editdictInfo.html?edit=1&id='+$(this).data('id')], //iframe的url，no代表不显示滚动条
										});
								});

							});*/
							 
						}
						
				});
			}); 
			}); 
			   
			