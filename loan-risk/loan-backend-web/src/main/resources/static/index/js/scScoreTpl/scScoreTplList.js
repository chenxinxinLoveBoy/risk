			layui.config({
				base: '../../js/' 
			});
			// 初始化
			$(function(){
				var look = getQueryString('look'); 
				if(look != '' && look != undefined ){
					$("#getSelected").hide();	
				}
				getList();

				// 条件搜索
				$('#search').on('click', function() {
					getList();
				});
			})
				
			var url = '' ; 
			var look = getQueryString('look'); 
				if(look != '' && look != undefined ){
					$("#getSelected").hide();
 					//当前模版信息下访问路径
					url = getCtxPath() + "/backend/scScoreSmall/getTemplate.do?v=" + new Date().getTime();
				}else{
					//查询不匹配模版信息路径
					$("#getSelected").show();
					url = getCtxPath() + "/backend/scScoreSmall/getAllTemplate.do?v=" + new Date().getTime();
				}
			
				// 当前菜单名称
				var menuNames = "信用分模版已关联小类配置";
			function getList(_pageIndex){ 
				
				//查询条件
				var scoreSmallCode = $("#scoreSmallCode").val().trim();
				var scoreRuleName = $("#scoreRuleName").val().trim();
 				var state = $("#state").val().trim();
				var scoreTplId = getQueryString('scoreTplId');//模板id
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
						params: {  
							 //发送到服务端的参数
							t: Math.random(),
							scoreSmallCode:scoreSmallCode,
							scoreRuleName:scoreRuleName,
 							state:state,
							scoreTplId : scoreTplId,
							menuNames : menuNames,
							functionNames: (scoreTplId != ''  ? "修改信用分模版已关联小类配置" : "新增信用分模版已关联小类配置") // 按钮功能名称 不为空修改 为空 新增
						},
						type: 'post',
						tempElem: '#tpl', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 10 //分页大小，当前页面显示的条数
						}, 
						success: function() { //重新渲染复选框
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
							},
						fail: function(msg) { //获取数据失败的回调
							layer.msg("获取数据失败！");
						},
						complate: function() { //完成的回调 
							//获取所有选择的列
							$('#getSelected').on('click', function() {
								var ids = ''; 
								$('#content').children('tr').each(function() {
									var $that = $(this);
									var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;
									if($cbx) {
										var n = $that.children('td:last-child').children('a[data-opt=edit]').data('id'); 
										ids += n + ',';
									} 
								});
								if(ids == '' || ids == undefined){ // 验空
									layer.msg('请勾选需关联的数据！', {
									    time: 2000, //2s后自动关闭
									    btn: ['知道了']
									  }); 
									return false;
								} 
								
								layer.confirm('您确定要关联模版吗？', {
 									  btn: ['确定','取消'] //按钮
									}, function(){ 
										
								$.ajax({
									url: getCtxPath() + "/backend/scScoreSmall/saveScFraudRuleBatch.do?v=" + new Date().getTime(),
									method: "post",
									dataType: "json",
									data:{
										scoreTplId : scoreTplId, 
										ids : ids,
										menuNames : menuNames,
										functionNames: (ids != ''  ? "批量关联信用评分模版配置" : "") // 按钮功能名称 不为空修改 为空 新增
									},
									success: function(data){
										if(data.code == 4001){
											parent.layer.msg(data.message,{
												time: 2000
											});//提示信息 
										    getList();
											location.reload(); //刷新
										}else{
											parent.layer.msg(data.message);
											getList();
										}
										
									},
									error: function(){
										parent.layer.alert("关联模版失败！");
										 getList();
 									},
									complete: function(){
//										 getList();
										location.reload(); //刷新
									}
								});
								
							});
								
							});
							
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
										  content: ["../scScoreSmall/scScoreSmall_add.html?id="+$(this).data('id')], //iframe的url，no代表不显示滚动条
										});
								});
	
							});
							
							
							//绑定所有'修改状态'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=update]').on('click', function() {
									var state= $(this).data('state');
									var version= $(this).data('version');
									var str= "";
									if(state=='01'){
										str="无效"; 
										state='02';
									}else{
										 str="有效";
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
												version: version,
												scoreTplId:$(this).data('tpl')
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