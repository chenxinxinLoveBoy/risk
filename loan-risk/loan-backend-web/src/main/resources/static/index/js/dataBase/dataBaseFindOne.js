			layui.config({
				base: '../../js/' 
			});
			// 初始化
			$(function(){
  				getList()
			}) 
			  
			// 搜索
			 function sel() { 
				var tableSchema = $("#tableSchema").val().trim();
				var tableName = $("#tableName").val().trim();
			 	if(tableSchema == '' || tableSchema == null){
					parent.layer.msg("请输入数据库名称!"); 
					return false;
				} 
				getList(tableSchema,tableName); 
			};
 
			
			function getList(){ 
				var tableSchema = $("#tableSchema").val().trim();
				var tableName = $("#tableName").val().trim();
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
						url: getCtxPath() + "/backend/dataBase/findOne.do?v=" + new Date().getTime(),// 请求服务器的url
						elem: '#content', //内容容器
						params: { //发送到服务端的参数 
							tableName : tableName,
							tableSchema : tableSchema
						},
						type: 'post',
						tempElem: '#tpl', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 10 //分页大小，当前页面显示的条数
						},
						success: function() { //渲染成功的回调    
							 $("#count").show();
						},
						fail: function(msg) { //获取数据失败的回调   
							layer.msg(msg.message);   
						},
						complate: function() {
							
						}
					}); 
				});
			}	 
		 
			
	
 			