			layui.config({
				base: '../../js/' 
			});
			var fraudRuleTplId = "";

			// 初始化
			$(function(){
//				getList();
				getTplList();
			})
			var score = "";
			// 条件搜索
			 function sel() {
			// score = $("#score").val().trim(); 
				 fraudRuleTplId = $("#fraudRuleTplId").val().trim();
				getList(1);
			};
			
			
			 function add() { 
					layer.open({
						  type: 2,
						  title: '新增',// 设置false表示不显示
						  closeBtn: 1, //0：不显示关闭按钮
						  shade: 0.4, //遮罩透明度
						  area: ['840px', '550px'],
						  skin: 'layui-layer-rim', //加上边框
						  fixed: false, //不固定
						  maxmin: true, // 允许屏幕最小化 
						  content: ['threshold_add.html'], //iframe的url，no代表不显示滚动条
						});
				}; 
			
			
			function getList(_pageIndex){
				//局部刷新回调获取参数,
				var isFirst = 1;
				var pageIndex = _pageIndex || $("#paged").find("em").eq(1).html()||1;
				fraudRuleTplId = $("#fraudRuleTplId").val();

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
						url: getCtxPath() + "/backend/threshold/thresholdlistAll.do?v=" + new Date().getTime(),
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							t: Math.random(),
							//score : score ,
							fraudRuleTplId:fraudRuleTplId,
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
										  content: ['threshold_add.html?id='+$(this).data('id')], //iframe的url，no代表不显示滚动条
										});
								});
	
							});
							
							var menuNames ='欺诈分阈值';
							//绑定所有'修改状态'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=update]').on('click', function() {
									var state= $(this).data('state');
 									var str= "";
									if(state=='1'){
										str="无效"; 
										state=0;
									}else{
										 str="有效";
 										state=1;
									}
  									if(confirm('您确定将状态修改为【'+str+'】吗？')){ //只有当点击confirm框的确定时，该层才会关闭
									// ajax 请求后台
										$.ajax({
											url : getCtxPath() + "/backend/threshold/saveThreshold.do",
											method : 'post',
											dataType : 'json',
											data: {
												serialnumber: $(this).data('id'), // 传到后台的参数，子窗口的form表单
												state:state, 
												menuNames : menuNames,
												functionNames: ($(this).data('id') != ''  ? "欺诈分阈值状态修改" : "新增") // 按钮功能名称 不为空修改 为空 新增
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
			  
			
 			
			function getTplList() {
				$.ajax({
					url : getCtxPath() + "/backend/scFraudRuleTpl/scFraudRuleTplListAll.do?v=" + new Date().getTime(),
					method : "post",
					dataType : "json",
					data : {
						state:"01"
					},
					success : function(data) {
						var arr = [];
						arr.push('<select name="fraudRuleTplId" id = "fraudRuleTplId" class="layui-input" lay-search="">');
						layui.each(data.list, function(index, item){
								if(fraudRuleTplId === item.fraudRuleTplId){
							    	arr.push('<option value="'+ item.fraudRuleTplId + '" selected>' + item.fraudRuleTplName + '</option>');
								}else{
							    	arr.push('<option value="'+ item.fraudRuleTplId + '">' + item.fraudRuleTplName + '</option>');
								}
						    });
						arr.push('</select>');
						$('#tplContent').html(arr.join(''));
						getList();
						//form.render();
					},
					error : function() {
						alert("获取用户信息失败,请尝试重新获取！");
					}
				});
			}
 			