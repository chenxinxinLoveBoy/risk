			layui.config({
				base: '../../js/' 
			});
			// 当前菜单名称
//			var menuNames = "决策树配置管理";
			// 初始化
			$(function(){
				getList();
			})
			var customerCheckApplyId = "", customerCheckCodeId = "",name = "",certCode="",phoneNum = "",banCode="";
			var codeId=getQueryString("codeId");
			if(codeId != undefined && codeId != 'undefined'){
				customerCheckCodeId=codeId;
			}
			// 条件搜索
			 function sel() {
				 codeId="";
				 customerCheckApplyId = $("#customerCheckApplyId").val().trim();
				 customerCheckCodeId = $("#customerCheckCodeId").val().trim();
				 name = $("#name").val().trim();
				 certCode=$("#certCode").val().trim();
				 phoneNum = $("#phoneNum").val().trim();
				 banCode=$("#banCode").val().trim();
				getList(1);
			};
			
			
			// 一键重发
			function sendQueues(){
				
				layer.confirm('您确定要发送吗？', {
				    btn: ['确定','取消'], //按钮
				    shade: 0.4 //显示遮罩
				}, function(index){
					var sendVal = $("#sendVal").val();
					if( sendVal== null || sendVal == ""){
						layer.msg("发送的内容为空！");//提示信息
						return;
					}
				// 一键重发
					$.ajax({
						url : getCtxPath() + "/backend/cuCustomerCheckApply/sendQueue.do?v=" + new Date().getTime(),// 请求服务器的url
						method: "post",
						dataType:"json",
						data: {
							sendVal: sendVal
						},
						success: function(data){
							if(data != null && data.list.code == 500){
								layer.msg(data.list.msg, {icon: 2});//提示信息
							}else{
								layer.msg("一键发送成功");//提示信息
							}
							// 刷新当前页面
							getList(); 
						}, 
						error: function(){
	 						layer.msg("请求错误，请刷新页面再试！" , {icon: 2});//提示信息
						},
						complete: function(){ 
						} 
					});	
				});
			}
			
			
			
			function getList(_pageIndex){
				if(codeId != ""){
					customerCheckCodeId=codeId;
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
						url: getCtxPath() + "/backend/cuCustomerCheckApply/cuCustomerCheckApplyListAll.do?v=" + new Date().getTime(),
						elem: '#content', //内容容器
						params: { //发送到服务端的参数 
							customerCheckApplyId : customerCheckApplyId,
							customerCheckCodeId : customerCheckCodeId,
							name  : name,
							certCode:certCode,
							phoneNum:phoneNum,
							banCode:banCode
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
							//绑定所有'查看报告信息'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=find]').on('click', function() {
									var href = getCtxPath() + "/index/views/cuCustomerCheckApply/cuCustomerCheckApplyInfo.html?id="+$(this).data('id');
									var data = {
										"title": "报告信息-"+$(this).attr("data-id"),
										"icon": "&#xe60c;",
										"href": href
									}
									window.parent.topTab.tabAdd(data);
								});
							});
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td').children('a[data-opt=findd]').on('click', function() {
									var href = getCtxPath() + "/index/views/cuCustomerCheckApply/cuCustomerCheckApplyInfoList.html?applyId="+$(this).attr("data-applyId");
									var data = {
										"title": "详细信息-"+$(this).attr("data-applyId"),
										"icon": "&#xe60c;",
										"href": href
									}
									window.parent.topTab.tabAdd(data);
								});
							});
							
							
							/**
							 * 加载满足一键重发的数据
							 */
							$.ajax({
								url : getCtxPath() + "/backend/cuCustomerCheckApply/cuCustomerCheckApplyListFind.do?v=" + new Date().getTime(),
								method: "post",
								dataType:"json",
								data: {
								},
								success: function(data){
//									alert(data);
									if(data != null){
										// 一键重发的key
										var sendVal = "";
										// 显示条数
										$("#sendQueue").html(data.count);
										$.each(data.data.data, function(k, v){
											//拼接内容
											sendVal += k + ",";
										});
									}
									$("#sendVal").val(sendVal);
									 
								},
								error: function(){
									 layer.msg("网络请求异常,请常识重新登录" , {icon: 2});//提示信息
								},
								complete: function(){
//									form.render(); //刷新全部
								} 
							});	
							
							
							
							
							
						},
					}); 
		
				}); 
			} 