			layui.config({
				base: '../../js/' 
			});
			// 当前菜单名称
//			var menuNames = "决策树配置管理";
			// 初始化
			$(function(){
				getList();
			})
			var blacklistCodeId = "",deviceId = "",certCode="",phoneNum = "";
			var codeId=getQueryString("codeId");
			if(codeId != undefined && codeId != 'undefined'){
				blacklistCodeId=codeId;
			}
			// 条件搜索
			 function sel() {
				 codeId="";
				 blacklistCodeId=$("#blacklistCodeId").val().trim();
				 deviceId = $("#deviceId").val().trim();
				 certCode=$("#certCode").val().trim();
				 phoneNum = $("#phoneNum").val().trim();
				getList(1);
			};
			
			function getList(_pageIndex){
				if(codeId != ""){
					blacklistCodeId=codeId;
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
						url: getCtxPath() + "/backend/blacklist/index.do?v=" + new Date().getTime(),
						elem: '#content', //内容容器
						params: { //发送到服务端的参数 
							sNumber : blacklistCodeId,
							certCode:certCode,
							phoneNum:phoneNum,
							deviceId:deviceId
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
						},
					}); 
		
				}); 
			} 