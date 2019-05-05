 		layui.config({
				base: '../../js/'
			});
			
			var   startTime = "", endTime = "", appName = "", approveTime='', appSerialNumber = "", auditingState = "", currPage = "",
			name = "", phoneNum = "", isPushApp = "", isStep = "", fraudTplId="", scoreTplId= "", flag = 0, decisionTreeId="";

			//初始化加载startTime
			$(function(){  
				startTime = decodeURI(getQueryString("startTime"));
				endTime = decodeURI(getQueryString("endTime"));
				//我们强烈推荐你在代码最外层把需要用到的模块先加载
				layui.use(['layer', 'form', 'element','laydate'], function(){
					  var layer = layui.layer
					  ,form = layui.form()
					  ,element = layui.element();
					  form.render(); //刷新全部
					  
						var laydate = layui.laydate;
						  
						var start = {
						    format: 'YYYY-MM-DD hh:mm:ss'
							,istime: true
						    ,choose: function(datas){
						      end.min = datas; //开始日选好后，重置结束日的最小日期
						      end.start = datas //将结束日的初始值设定为开始日
						    }
						}; 
						var end = {
						    format: 'YYYY-MM-DD hh:mm:ss'
							,istime: true
						    ,choose: function(datas){
						      start.max = datas; //结束日选好后，重置开始日的最大日期
						    }
						}; 
					  if (startTime != '' || endTime != '') {
						  $("#startTime").val(startTime);
						  $("#endTime").val(endTime);
					  }else {
						  $("#startTime").val(laydate.now(0,'YYYY-MM-DD 00:00:00')); 
						  $("#endTime").val(laydate.now(0,'YYYY-MM-DD 23:59:59'));
						  startTime = $("#startTime").val();
						  endTime = $("#endTime").val(); 
					  } 
					  $("#startTime").on("click", function(){
					    start.elem = this;
					    laydate(start);
					    if(laydate.now(0,'YYYY-MM-DD hh:mm:ss') == $(this).val()){
						    $(this).val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
					    }
					  }); 
					  $("#endTime").on("click", function(){
					    end.elem = this
					    laydate(end);
					  });
					  getList();
				});
				// 搜索
				$('#search').on('click', function() { 
					getList();
				}); 
			}); 
			function getList(){ 
				var startTime = $("#startTime").val();  
				var endTime = $("#endTime").val();  
				layui.use(['paging', 'form'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form();

						var flag = 4;
					// 页面初始化加载		start
	                paging.init({
	                    openWait: true,
						url: getCtxPath() + "/backend/application/findPrivateApprove.do?v=" + new Date().getTime(),// 请求服务器的url
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							t: Math.random(),
							startTime  : startTime,
							endTime   : endTime
						},
						type: 'post',
						tempElem: '#tpl', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 10 //分页大小，当前页面显示的条数
						},
						success: function(result) { //渲染成功的回调  
							var grClCounts = result.grCount;
							var grClAjCounts = result.grClAjCount
							//var grWClAjCounts = result.grWClAjCount
							 $("#grCount").html(grClCounts+" 件");//当日领取案件总数
							 $("#grClAjCount").html(grClAjCounts+" 件");//当日处理案件数
							// $("#grWClAjCount").html(grWClAjCounts+" 件");//当日为处理案件数
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
							var personCount = result.personCount;
							 if (currPage != '' && currPage != '1' && flag == 0) {
								 flag = 1;
									paging.get({
			                            pageIndex: currPage,
			                            pageSize: 10
			                        });
								} 
						},
						fail: function(msg) { //获取数据失败的回调
							layer.msg("获取数据失败！");
						},
						complate: function() { //完成的回调
							 		
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td').children('a[data-opt=open]').on('click', function() {  
									var appSerialNumber = $(this).data('id');
									var name =  $(this).data('name');   
									var cname =  $(this).data('name');   
									$.ajax({
										url :getCtxPath() + "/backend/application/findApproveDetails.do",
										method: "post",
										dataType:"json",
										data: {
											appSerialNumber: appSerialNumber
										},
										success: function(data){
											if(data.code == 200){ 
												var application = data.list.data;
												var rgcode = application.receiveCode;
												var rgState = application.rgAuditingState; 
												if(rgcode !=''  && rgState !='' ){ 
													layer.confirm('案件已被人工处理，请勿重复处理!', {
														  btn: ['确定','取消'] //按钮
														},  function(index){ 
															getList();  
														    layer.close(index);
														});
												}else{
													 appSerialNumber = appSerialNumber 
													 var vname =  cname; 
													var href = getCtxPath() + "/index/views/approveDetails/approveDetails.html?open=1"+'&appSerialNumber='+appSerialNumber;
													var data = {
														"title": vname+"案件详情",
														"icon": "&#xe60c;",	
														"href": href
													}
													window.parent.topTab.tabAdd(data); 
												}
											}
										}
									})
								
								});
							});

							 //个人案件退回
								
								$('#content').children('tr').each(function() {
									var $that = $(this);
									$that.children('td').children('a[data-opt=return]').on('click', function() {  
										var appSerialNumber = $(this).data('id');
										var name =  $(this).data('name');   
										var cname =  $(this).data('name');   
										$.ajax({
											url :getCtxPath() + "/backend/application/findApproveDetails.do",
											method: "post",
											dataType:"json",
											data: {
												appSerialNumber: appSerialNumber
											},
											success: function(data){
												if(data.code == 200){ 
												
													var menuNames = '退回公共案件池';
													var applicationId =  data.list.data.applicationId;
													  
													$.ajax({
														url :  getCtxPath() + "/backend/application/updatePrivateApprove.do?v=" + new Date().getTime(),
														method: "post",
														dataType:"json",
														data: {
															applicationId : applicationId,
															menuNames: menuNames, // 菜单名称
															functionNames: (applicationId !=''  ? "退回公共案件池":'') // 按钮功能名称
														},
														success: function(data){ 
															if(data.code == 4001){ 
																layer.msg('退回公共案件池成功!' , {icon: 1});  
																setTimeout(function(){ 
															 
																var href = getCtxPath() + "/index/views/approve/publicApprove.html";
																	var data = {
																		"title": '公共案件池',
																		"icon": "&#xe621;",	
																		"href": href
																	}
																	getList();	 
														        }, 1000); 
															} 
														} 
													}); 
												}
											}
										})
									});
								});
						
						},
					}); 
				});
			}	
			//刷新
			  function shuaxin() { 
				  getList();
			}
			   