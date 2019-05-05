			layui.config({
				base: '../../js/' 
			});
			
			var url = getCtxPath() + "/backend/scFraudRule/getAllScFraudRule.do?v=" + new Date().getTime();// 请求服务器的url
			var bigId = "", fraudRuleTplId = "";
			// 初始化
			$(function(){
				bigId = getQueryString("bigId");
				if(bigId !=null && bigId != ""){
					$("#searchDiv").hide();
					$("#operate").hide();
				}
				getTplList();
			})
			
			// 条件搜索
			$('#search').on('click', function() {
				getList(1);
			});
	
			/**新增  start*/
			$('#add').on('click', function() {
				layer.open({
					  type: 2,
					  title: '新增',// 设置false表示不显示
					  closeBtn: 1, //0：不显示关闭按钮
					  shade: 0.4, //遮罩透明度
					  area: ['840px', '550px'],
					  skin: 'layui-layer-rim', //加上边框
					  fixed: false, //不固定
					  maxmin: true, // 允许屏幕最小化
					  content: ['scFraudRule_add.html'], //iframe的url，no代表不显示滚动条
					});
			});
			/**新增     end*/
			
			function getList(_pageIndex){
				//查询条件
				var fraudRuleCode = $("#fraudRuleCode").val().trim();
				var ruleName = $("#ruleName").val().trim();
				var creditType = $("#creditType").val().trim();
				if(creditType=='00'){
					creditType='';
				}
				var state = $("#state").val().trim();
				if(state=='00'){
					state='';
				}
				fraudRuleTplId = $("#fraudRuleTplId").val();
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
 							fraudRuleCode:fraudRuleCode,
							ruleName:ruleName,
							creditType:creditType,
							fraudRuleBigId:bigId,
							fraudRuleTplId:fraudRuleTplId,
							state:state
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
										  content: ['scFraudRule_add.html?id='+$(this).data('id')], //iframe的url，no代表不显示滚动条
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
									var menuNames ='欺诈项规则';
  									if(confirm('您确定将状态修改为【'+str+'】吗？')){ //只有当点击confirm框的确定时，该层才会关闭
									// ajax 请求后台
										$.ajax({
											url : getCtxPath() + "/backend/scFraudRule/saveScFraudRule.do",
											method : 'post',
											dataType : 'json',
											data: {
												fraudRuleId: $(this).data('id'), // 传到后台的参数，子窗口的form表单
												fraudRuleTplId: $(this).data('tplid'),
												state:state,
												version:$(this).data('version'),
												menuNames : menuNames,
												functionNames: ($(this).data('id') != ''  ? "修改欺诈项状态" : "新增") // 按钮功能名称 不为空修改 为空 新增
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
	                // 初始化加载init     end!
	                
				});
			 	 
			}
			
			function getTplList() {
				$.ajax({
					url : getCtxPath() + "/backend/scFraudRuleTpl/scFraudRuleTplListAll.do?v=" + new Date().getTime(),
					method : "post",
					dataType : "json",
					data : {
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
						form.render();
					},
					error : function() {
						alert("获取用户信息失败,请尝试重新获取！");
					}
				});
			}
 			
 			