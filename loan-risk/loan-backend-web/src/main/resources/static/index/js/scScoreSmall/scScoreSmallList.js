			layui.config({
				base: '../../js/' 
			});
			
			var url = getCtxPath() + "/backend/scScoreSmall/getAllScScoreSmall.do?v=" + new Date().getTime();// 请求服务器的url
			var scoreTplId = "";
			var scoreBigId = "";
			
			// 初始化
			$(function(){
				scoreBigId = getQueryString("scoreBigId");
				console.log(scoreBigId);
 				//getList(scoreBigId);
 				getTplList();
			})
			
			// 条件搜索
			$('#search').on('click', function() {
				getList();
			});
			
			function getList(){
				
				//条件搜索查询参数
				var scoreSmallCode = $("#scoreSmallCode").val().trim();
				var scoreRuleName = $("#scoreRuleName").val().trim();
				var state = $("#state").val().trim();
				if(state=='00'){
					state='';
				}
				scoreTplId = $("#scoreTplId").val();
				//局部刷新回调获取参数,
				
				
				layui.use(['paging', 'layer', 'form', 'element'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form(),
						element = layui.element();
					    form.render(); //刷新全部
					 
					// 页面初始化加载		start
	                paging.init({
	                    openWait: true,
						url: url,
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							t: Math.random(),
							scoreBigId:scoreBigId,
							scoreSmallCode:scoreSmallCode,
							scoreRuleName:scoreRuleName,
							state:state,
							scoreTplId:scoreTplId
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
										  content: ['scScoreSmall_add.html?id='+$(this).data('id')], //iframe的url，no代表不显示滚动条
										});
								});
	
							});
							
							var menuNames = '信用评分项规则配置';
							//绑定所有'修改状态'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=update]').on('click', function() {
									var state= $(this).data('state');
									var scoreTplId= $(this).data('tplid');
  									var str="有效";
									if(state=='01'){
										str="无效";
										state='02';
									}else{
 										state='01';
									}
  									if(confirm('您确定将状态修改为【'+str+'】吗？')){ //只有当点击confirm框的确定时，该层才会关闭
									// ajax 请求后台
										$.ajax({
											url : getCtxPath() + "/backend/scScoreSmall/saveScScoreSmall.do",
											method : 'post',
											dataType : 'json',
											data: {
												scoreSmallId: $(this).data('id'), // 传到后台的参数，子窗口的form表单
												state:state,
												version:$(this).data('version'),
												scoreTplId: scoreTplId,
												menuNames : menuNames,//信用评分项规则配置状态
												functionNames: ($(this).data('id') != ''  ? "修改信用评分项状态" : "新增信用评分项") // 按钮功能名称 不为空修改 为空 新增
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
												getList(scoreBigId); //局部刷新
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
					url : getCtxPath() + "/backend/scScoreTpl/scScoreTplListAll.do?v=" + new Date().getTime(),
					method : "post",
					dataType : "json",
					data : {
					},
					success : function(data) {
						var arr = [];
						arr.push('<select name="scoreTplId"  id = "scoreTplId" class="layui-input" lay-search="">');
						layui.each(data.list, function(index, item){
								if(scoreTplId === item.scoreTplId){
							    	arr.push('<option value="'+ item.scoreTplId + '" selected>' + item.scoreTplName + '</option>');
								}else{
							    	arr.push('<option value="'+ item.scoreTplId + '">' + item.scoreTplName + '</option>');
								}
						    });
						arr.push('</select>');
						$('#tplContent').html(arr.join(''));
						getList(scoreBigId);
						//form.render(); //刷新全部
					},
					error : function() {
						alert("获取用户信息失败,请尝试重新获取！");
					}
				});
			}
 			