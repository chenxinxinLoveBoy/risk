			layui.config({
				base: 'js/'
			});
		
			//当前菜单名称
			var menuNames = "用户管理";
			
			// 初始化
			$(function(){
				getList();
			})

			var userName = "";
			var userId = "";
			var mobile = "";
			var nickName = "";
			var state = ""; // 是否禁用
			var isFreeze = ""; //是否冻结
			var roleName="";
			// 搜索
			$('#search').on('click', function() {
				userName = $("#userName").val().trim();
				userId = $("#userId").val().trim();
				mobile = $("#mobile").val().trim();
				nickName  = $("#nickName").val().trim();
				state = $("#state").val();
				isFreeze = $("#isFreeze").val();
				roleName = $("#roleName").val().trim();
				getList();
//				parent.layer.alert('你点击了搜索按钮')
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
					  maxmin: true, //开启最大化最小化按钮
					  anim: 2,
					  content: ['edit/user/user_edit.html'], //iframe的url，no代表不显示滚动条
					});
			});
			/**新增     end*/
			
			function getList(_pageIndex){
				//局部刷新回调获取参数,
				var isFirst = 1;
				var pageIndex = _pageIndex || $("#paged").find("em").eq(1).html()||1;
				layui.use(['layer','upload','paging', 'form'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form();
	
					// 页面初始化加载		start
	                paging.init({
	                    openWait: true,
	//                     url: 'datas/laytpl_laypage_data.json?v=' + new Date().getTime(), //地址
						url: getCtxPath() + "/backend/uuser/listAll.do?v=" + new Date().getTime(),// 请求服务器的url
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							userName: userName,
							id: userId,
							mobile: mobile,
							nickName: nickName,
							state: state,
							isFreeze: isFreeze,
							roleName:roleName
							
						},
						type: 'post',
						tempElem: '#tpl', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 10 //分页大小，当前页面显示的条数
						},
						success: function() { //渲染成功的回调
	//						alert('渲染成功');
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
							//alert('处理完成');
							//重新渲染复选框
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
	//									  offset: 'rb', //右下角弹出
	//									  time: 2000, //2秒后自动关闭
										  anim: 2,
										  content: ['edit/user/user_edit.html?id='+$(this).data('id')], //iframe的url，no代表不显示滚动条
										});
								});
	
							});
							
							
							
							//绑定所有'删除'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=del]').on('click', function() {
									if(confirm('确定要删除吗')){ //只有当点击confirm框的确定时，该层才会关闭
									// ajax 请求后台
										$.ajax({
											url : getCtxPath() + "/backend/uuser/delete.do",
											method : 'post',
											dataType : 'json',
											data: {
												id: $(this).data('id') // 传到后台的参数，子窗口的form表单
											},
											success : function(data) {
												layerTips.msg(data.message);// 提示信息
											},
											error: function(){
												alert("获取数据失败，请尝试重新登陆！");
											},
											complete: function(){
												location.reload(); //刷新
											}
										});
									  }
									  return false; 
	//								layer.msg($(this).data('id')); // 当前窗口弹框提示信息
								});
	
							});
							
							//绑定所有'预览'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=find]').on('click', function() {
									layer.open({
										  type: 2,
										  title: '预览',// 设置false表示不显示
										  closeBtn: 1, //不显示关闭按钮
										  shade: 0.4, //遮罩透明度
										  area: ['840px', '550px'],
										  skin: 'layui-layer-rim', //加上边框
										  btn: ['取消'],
										  fixed: false, //不固定
										  maxmin: true, // 允许屏幕最小化
	//									  offset: 'rb', //右下角弹出
	//									  time: 2000, //2秒后自动关闭
										  anim: 2,
										  content: ['edit/user/user_edit.html?find=1&id='+$(this).data('id')], //iframe的url，no代表不显示滚动条
										});
								});
							});
							
							//绑定所有'登录历史'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=look]').on('click', function() {
									layer.open({
										  type: 2,
										  title: false,// 设置false表示不显示
										  closeBtn: 1, //不显示关闭按钮
										  shade: 0.4, //遮罩透明度
										  area: ['840px', '550px'],
										  skin: 'layui-layer-rim', //加上边框
										  btn: ['取消'],
										  fixed: false, //不固定
										  maxmin: true, // 允许屏幕最小化
	//									  offset: 'rb', //右下角弹出
	//									  time: 2000, //2秒后自动关闭
										  anim: 2,
										  content: ['views/scLoginLog/scLoginLog.html?id='+$(this).data('id')], //iframe的url，no代表不显示滚动条
										});
								});
							});
							
							
							//绑定所有'重置密码'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=rest]').on('click', function() {
									var idd=$(this).data('id');
									layer.confirm('您确定要重置密码吗？', {
									    btn: ['确定','取消'], //按钮
									    shade: 0.4 //显示遮罩
									}, function(index){
										// 充值密码请求
										$.ajax({
											url: getCtxPath() + "/backend/uuser/rest.do",
											method: "post",
											dataType: "json",
											data:{
												id: idd
											},
											success: function(data){
												if(data.code == 6001){
													layer.msg(data.message);//提示信息
					//								layer.alert(data.message);// 另一个弹窗风格
												}else{
													layer.msg(data.message);
												}
												
											},
											error: function(){
												layer.alert("请求网络异常，请尝试重新登录！");
											},
											complete: function(){
//												location.reload(); //刷新
											}
										});
										
									    // 提交表单的代码，需要你自己写，然后用 layer.close 关闭就可以了，取消可以省略
//									    layer.close(index);
									});
									
//									layer.msg("1222");
								});
							});
							
							
							//绑定所有'禁用'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=isDisable]').on('click', function() {
									// id
									var idd=$(this).data('id');
									// 获取账号
									var title = $(this).data("title");
									// 状态，字段注释，1：启用，0：禁用
									var state = $(this).data("state");
									state = (state == 1 ? 0 : 1 );
									var altTitle = (state == 1 ? "启用" : "禁用");
									layer.confirm("您确定要" + altTitle + "帐号【" + title+ "】吗？", {
									    btn: ['确定','取消'], //按钮
									    shade: 0.4 //显示遮罩
									}, function(index){
										// 请求
										$.ajax({
											url: getCtxPath() + "/backend/uuser/updateDisable.do",
											method: "post",
											dataType: "json",
//											async: false,// 同步
											data:{
												id: idd,
												state:  state,
												menuNames: menuNames, // 菜单名称
												functionNames: altTitle // 按钮功能名称
											},
											success: function(data){
												if(data.code == 200){
													layer.msg(altTitle + data.message);
													getList(); //刷新, 重新加载
												}else{
													layer.msg(altTitle + data.message);
												}
												
											},
											error: function(){
												layer.alert("请求网络异常，请尝试重新登录！");
											},
											complete: function(){
													
											}
										});
										
									});
									
								});
							});
							

							
							//绑定所有'接触冻结'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=freeze]').on('click', function() {
									// id
									var idd=$(this).data('id');
									// 获取账号
									var title = $(this).data("title");
									layer.confirm("您确定要解除冻结帐号【" + title+ "】吗？", {
									    btn: ['确定','取消'], //按钮
									    shade: 0.4 //显示遮罩
									}, function(index){
										// 充值密码请求
										$.ajax({
											url: getCtxPath() + "/backend/uuser/updateFreeze.do",
											method: "post",
											dataType: "json",
											data:{
												id: idd,
												userName: title,
												menuNames: menuNames, // 菜单名称
												functionNames: "解除冻结" // 按钮功能名称
											},
											success: function(data){
												if(data.code == 200){
													layer.msg("解除冻结" + data.message + "!");
													getList(); //刷新, 重新加载
												}else{
													layer.msg("解除冻结" + data.message + "!");
												}
												
											},
											error: function(){
												layer.alert("请求网络异常，请尝试重新登录！");
											},
											complete: function(){
													
											}
										});
										
									});
									
								});
							});
							
							
							
							
						},
					});
	                // 初始化加载init     end!
	                
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
						if(ids == '' || ids == null){ // 验空
							layer.msg('请勾选要删除的数据！', {
							    btn: ['知道了']
							  });
	//						layer.msg("请勾选要删除的数据！");
							return false;
						} 
						
						layer.confirm('您确定要删除吗？', {
							  btn: ['确定','取消'] //按钮
							}, function(){
						
						$.ajax({
							url: getCtxPath() + "/backend/uuser/deleteAll.do",
							method: "post",
							dataType: "json",
							data:{
								ids: ids
							},
							success: function(data){
								if(data.code == 3001){
									parent.layer.msg(data.message);//提示信息
	//								parent.layer.msg(data.message);
	//								parent.layer.alert(data.message);// 另一个弹窗风格
								}else{
									parent.layer.msg(data.message);
								}
								
							},
							error: function(){
								parent.layer.alert("请求网络异常，请尝试重新登录！");
							},
							complete: function(){
								location.reload(); //刷新
							}
						});
						
					});
						
					});
	
	//				var addBoxIndex = -1;
	//				$('#add').on('click', function() {
	//					if(addBoxIndex !== -1)
	//						return;
	//					//本表单通过ajax加载 --以模板的形式，当然你也可以直接写在页面上读取
	//					$.get('edit/user/user_edit.html', null, function(form) {
	//						addBoxIndex = layer.open({
	//							type: 1,
	//							title: '添加表单',
	//							content: form,
	//							btn: ['保存', '取消'],
	//							shade: 0.4, //遮罩透明度
	////							shade: false,
	//							offset: ['100px', '30%'],
	//							area: ['600px', '400px'],
	//							zIndex: 19950924,
	//							maxmin: true,
	//							yes: function(index) {
	//								//触发表单的提交事件
	//								$('form.layui-form').find('button[lay-filter=edit]').click();
	//							},
	//							full: function(elem) {
	//								var win = window.top === window.self ? window : parent.window;
	//								$(win).on('resize', function() {
	//									var $this = $(this);
	//									elem.width($this.width()).height($this.height()).css({
	//										top: 0,
	//										left: 0
	//									});
	//									elem.children('div.layui-layer-content').height($this.height() - 95);
	//								});
	//							},
	//							success: function(layero, index) {
	//								//弹出窗口成功后渲染表单
	//								var form = layui.form();
	//								form.render();
	//								form.on('submit(edit)', function(data) {
	//									
	//									// ajax请求后台
	//									$.ajax({
	//										url : hostIp + "/uuser/save.do",
	//										method : 'post',
	//										dataType : 'json',
	//										data: {
	//											userName: data.field.userName, // 传到后台的参数，子窗口的form表单
	//											nickName: data.field.nickName,  
	//											password: data.field.password,
	//											mobile: data.field.mobile
	//										},
	//										success : function(data) {
	//											layerTips.msg('保存成功');// 提示信息
	//// 											layerTips.close(index); 
	//											location.reload(); //刷新，关闭
	//										},
	//										error: function(){
	//											alert("获取数据失败，请尝试重新登陆！");
	//										},
	//										complete: function(){
	//										}
	//									});
	//// 									alert(data.field.nickName);
	//// 									layerTips.msg("hahahha");
	//// 									console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
	//// 									console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
	//// 									console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
	//									//调用父窗口的layer对象
	//// 									layerTips.open({
	//// 										title: '这里面是表单的信息',
	//// 										type: 1,
	//// 										content: JSON.stringify(data.field),
	//// 										area: ['500px', '300px'],
	//// 										btn: ['关闭并刷新', '关闭'],
	//// 										yes: function(index, layero) {
	//// 											layerTips.msg('你点击了关闭并刷新');
	//// 											layerTips.close(index);
	//// 											location.reload(); //刷新
	//// 										}
	//
	//// 									});
	//									//这里可以写ajax方法提交表单
	//									return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。									
	//								});
	//								//console.log(layero, index);
	//							},
	//							end: function() {
	//								addBoxIndex = -1;
	//							}
	//						});
	//					});
	//				});
					
					
					
	//				$('#import').on('click', function() {
	//					var that = this;
	//					var index = layer.tips('只想提示地精准些', that, { tips: [1, 'white'] });
	//					$('#layui-layer' + index).children('div.layui-layer-content').css('color', '#000000');
	//				});
				});
			}	 
			
 			