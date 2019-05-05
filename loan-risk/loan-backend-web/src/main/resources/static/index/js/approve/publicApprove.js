 		layui.config({
				base: '../../js/'
			});
			
			var   startTime = "", endTime = "", appName = "", approveTime='', appSerialNumber = "", auditingState = "", currPage = "",
			name = "", phoneNum = "", isPushApp = "", isStep = "", fraudTplId="", scoreTplId= "", flag = 0, decisionTreeId="";
			// 当前菜单名称
			var menuNames = "公共案件池";
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
					startTime = $("#startTime").val().trim();
					endTime = $("#endTime").val().trim();
					ycNumber =$("#ycNumber").val();
					errorDescription = $("#errorDescription").val();
					getList();
				}); 
			}); 
			 
			function getList(){ 
				var startTime = $("#startTime").val();  
				var endTime = $("#endTime").val(); 
				var ycNumber =$("#ycNumber").val();
				var errorDescription = $("#errorDescription").val();
					// 页面初始化加载		start
				layui.use(['paging', 'form'], function() {  
					var $ = layui.jquery,
					paging = layui.paging(),
					layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
					layer = layui.layer, //获取当前窗口的layer对象
					form = layui.form(); 
			
					
	                paging.init({ 
	                	openWait: true,
						url: getCtxPath() + "/backend/application/findPublicApprove.do?v=" + new Date().getTime(),// 请求服务器的url
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							t: Math.random(),
							startTime : startTime,
							endTime   : endTime,
							ycNumber : ycNumber,
							errorDescription : errorDescription
						},
						type: 'post',
						tempElem: '#tpl', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 10 //分页大小，当前页面显示的条数
						},
						success: function(result) { //渲染成功的回调  
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
							 
							 var count = result.ajCount; 
							 var ajUntrCounts = result.ajUntrCount;
							 var ajClCounts = result.ajClCount;
							 var historicalSurplusCount = result.historicalSurplusCount;
							 var ycCount = result.ycCount; 
							 var ycUntrCount = result.ycUntrCount;
							 var ycClCount = result.ycClCount;
							 var ylCount = result.ylCount; 
							 var ylUntrCount = result.ylUntrCount;
							 var ylClCount = result.ylClCount;

							 $("#ajCount").html(count+" 件");
							 $("#ajClCount").html(ajClCounts+" 件");
							 $("#ajUntrCount").html(ajUntrCounts+" 件");
							 $("#historicalSurplusCount").html(historicalSurplusCount+" 件");
							 $("#ycCount").html(ycCount+" 件");
							 $("#ycUntrCount").html(ycUntrCount+" 件");
							 $("#ycClCount").html(ycClCount+" 件");
							 $("#ylCount").html(ylCount+" 件");
							 $("#ylUntrCount").html(ylUntrCount+" 件");
							 $("#ylClCount").html(ylClCount+" 件");
							 $("#sumCount").html(count+ajUntrCounts+ajClCounts);
							 $("#sumCount").html(count+ajUntrCounts+ajClCounts);
						},
						fail: function(msg) { //获取数据失败的回调
							layer.msg("获取数据失败！");
						},
						complate: function() { //完成的回调 
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=find]').on('click', function() {
									var appSerialNumber= $(this).data('appumber');  
									var ids= $(this).data('id');  
									// ajax 请求后台
										$.ajax({
											url : getCtxPath() + "/backend/application/getEntityById.do?v=" + new Date().getTime(),
											method : 'post',
											dataType : 'json',
											data: {
												appSerialNumber: appSerialNumber   
 											},
											success : function(data) {
												if(data.code == "200"){   
													var auditMan = data.list.data.auditMan;  
													if('' == auditMan || auditMan == null){
														$.ajax({
															url: getCtxPath() + "/backend/application/updateBatch.do?v=" + new Date().getTime(),
															method: "post",
															dataType: "json",
															data:{ 
																ids : ids,
																menuNames: menuNames, // 菜单名称
																functionNames: (ids !='' ? "单个提取案件" : "") // 按钮功能名称
															},
															success: function(data){
																if(data.code == 2001){  
																	parent.layer.msg(data.message)
																	getList();   
																}else{
																	parent.layer.msg(data.message);
																	 getList();
																} 
															}  
														});
													}else{
														parent.layer.msg("您所勾选的案件已被他人领取",{icon: 2}); 
														 setTimeout(function(){  
															 getList();     
												         }, 2000); 
													}
												} 
											} 
										});  
								});
	
							});  
							
						 
						},
					});
				});     
			}	 
			//获取所有选择的列
			  function getSelected() {  
				var ids = '';
				var number = '';
				$('#content').children('tr').each(function() {
					var $that = $(this);
					var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;
					if($cbx) {
						var n = $that.children('td').eq(0).children('input[type=checkbox]').data('id'); 
						var numbers = $that.children('td').eq(0).children('input[type=checkbox]').data('number'); 
						ids += n + ',';
						number=numbers;
					} 
				});
				if(ids == '' || ids == undefined){ // 验空 
					layer.msg('请勾选案件！');
					return false;
				}    
			if(confirm('您确定要选取这些案件吗？')){ 
				var menuNames = "公共案件池";
				var appSerialNumber = number;
				$.ajax({
					url: getCtxPath() + "/backend/application/getEntityById.do?v=" + new Date().getTime(),
					method: "post",
					dataType: "json",
					data:{ 
						appSerialNumber : appSerialNumber
					},
					success: function(data){
						if(data.code == 200){   
							var auditMan = data.list.data.auditMan;
							if('' == auditMan || auditMan == null){
								$.ajax({
									url: getCtxPath() + "/backend/application/updateBatch.do?v=" + new Date().getTime(),
									method: "post",
									dataType: "json",
									data:{ 
										ids : ids,
										menuNames: menuNames, // 菜单名称
										functionNames: (ids !='' ? "批量提取案件" : '') // 按钮功能名称
									},
									success: function(data){
										if(data.code == 2001){ 
											$("#check").removeAttr("checked");//取消全选   
											parent.layer.msg(data.message)
											getList();   
										}else{
											parent.layer.msg(data.message);
											 getList();
										} 
									},
									error: function(){
										parent.layer.alert("批量取件失败！"); 
									},
									complete: function(){ 
									}
								});
							}else{
								parent.layer.msg("您所勾选的案件已被他人领取",{icon: 2});
								$("#check").removeAttr("checked");//取消全选   
								 setTimeout(function(){  
									 getList();     
						         }, 2000); 
							}
						}else{
							parent.layer.alert("取件失败！"); 	 
						} 
					}, 
				}); 
			} 
			}  
			  //刷新
			  function shuaxin() { 
				  getList();
			}
			   