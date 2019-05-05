			layui.config({
				base: '../../js/' 
			});
			// 初始化
			$(function(){
				layui.use(['laydate'], function() {
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
					$("#startTime").on("click", function(){
					    start.elem = this;
					    laydate(start);
					    if(laydate.now(0,'YYYY-MM-DD hh:mm:ss') == $(this).val().trim()){
						    $(this).val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
					    }
					});
					$("#endTime").on("click", function(){
						end.elem = this
						laydate(end);
					});
					$("#startTime").val(layui.laydate.now(0,'YYYY-MM-DD 00:00:00'));
					$("#endTime").val(layui.laydate.now(0,'YYYY-MM-DD 23:59:59'));
					getList();
				});
				/*************************//*
				layui.use(['paging','tree','upload','layer', 'form', 'element'], function(){
					  var layer = layui.layer
					  ,paging = layui.paging()
					  ,form = layui.form()
					  ,element = layui.element();
					  form.render(); //刷新全部
					  paging.init({
		                    openWait: true,
							url: hostIp + "/checkCustomer/findAllByObj.do?v=" + new Date().getTime(),// 请求服务器的url
							elem: '#content', //内容容器
							params: { //发送到服务端的参数
								customerCheckCodeId : ""
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
							complate: function() {
								$('#content').children('tr').each(function() {
									var $that = $(this);
									$that.children('td').children('a[data-opt=find]').on('click', function() {
										var href = getCtxPath() + "/index/views/cuCustomerCheckApply/cuCustomerCheckApply.html?codeId="+$(this).attr("data-codeId");
										var data = {
											"title": "详细信息-"+$(this).attr("data-codeId"),
											"icon": "&#xe60c;",
											"href": href
										}
										window.parent.topTab.tabAdd(data);
									});
								});
							}
						});
				});*/
				
			})
			
			
			function getList(){
					var codeId=$("#codeId").val().trim();//批次号
					layui.use(['paging', 'layer', 'form', 'element','laydate'], function() {
						var $ = layui.jquery,
							paging = layui.paging(),
							layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
							layer = layui.layer, //获取当前窗口的layer对象
							form = layui.form(),
							element = layui.element();
						var startTime = $("#startTime").val();
						var endTime = $("#endTime").val();
						// 页面初始化加载		start
		                paging.init({
		                    openWait: true,
							url: hostIp + "/blacklist/findAllByObj.do?v=" + new Date().getTime(),// 请求服务器的url
							elem: '#content', //内容容器
							params: { //发送到服务端的参数
								blacklistCodeId:codeId,
								startTime:startTime,
								endTime:endTime
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
								$('#content').children('tr').each(function() {
									var $that = $(this);
									$that.children('td').children('a[data-opt=find]').on('click', function() {
										var href = getCtxPath() + "/index/views/blacklist/blacklistImportInfo.html?codeId="+$(this).attr("data-codeId");
										var data = {
											"title": "详细信息-"+$(this).attr("data-codeId"),
											"icon": "&#xe60c;",
											"href": href
										}
										window.parent.topTab.tabAdd(data);
									});
								});
								
								 $('#content').children('tr').each(function() {
									 var path="";
									 var $that = $(this); 
										$that.children('td').children('a[data-opt=file]').on('click', function() {
											location.href=getCtxPath() + "/backend/blacklist/download.do?v=" + new Date().getTime()+"&path="+$(this).data('id')+"&fileName=黑名单";	
										})
								});
											 			
							}						
						});
		                 
					});
					
				}
			
			
			var spendTimeStart;
			function uploadFiel(){
				if(document.getElementById("file_id").value==null||document.getElementById("file_id").value==""){
					layer.msg("请选择上传文件" , {icon: 2});
					return;
				}
				$("#uploadStatus").html("<font>文件导入中...</font>");
				spendTimeStart=new Date().getTime();
				/********************************************/
				var FileController=hostIp+ "/blacklist/upload.do?v=" + new Date().getTime();// 接收上传文件的后台地址   (请求地址)
			    // FormData 对象     //可以增加表单数据  
			    var form = new FormData(); 
			    var file_one = document.getElementById("file_id").files[0]; // js 获取文件对象
			    var taskType=new Array();
			    $('input[name="taskType"]:checked').each(function(){ 
					taskType.push($(this).val());
				}); 
			    form.append("file", file_one); 
			    form.append("taskType",taskType.toString());
			    var xhr=null; // XMLHttpRequest 对象  
			    if (window.XMLHttpRequest){
			    	// code for all new browsers
			    	xmlhttp=new XMLHttpRequest();
			    }
				else if (window.ActiveXObject){
					// code for IE5 and IE6
					xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
				}
			    
				if (xmlhttp!=null)
			    {
				    xmlhttp.open("post", FileController, true);  // 第3个参数表示是否异步请求 
				    xmlhttp.send(form);// 提交参数
				    xmlhttp.onreadystatechange=sendata; // 当 XMLHttpRequest 对象的状态发生改变时，会触发此函数。状态从 0 (uninitialized) 到 4 (complete) 进行变化
			    }else{
			    	alert("Your browser does not support XMLHTTP.");
			    }
				 /*****************************/
			}
			 function sendata(){
				 if (xmlhttp.readyState==4){
					 // 4 = "loaded"
					 //layer.msg("文件上传中..." , {icon: 3});
					 $("#uploadStatus").html("<font>文件导入中...</font>");
					 if (xmlhttp.status==200){ //200 = OK
						 //console.log(xmlhttp.responseText);
						 var jsons=JSON.parse(xmlhttp.responseText);// 返回的字符串转换成json格式
						 var spendTimeEnd=new Date().getTime();
						 var spendTime=(spendTimeEnd-spendTimeStart)/1000;//消耗时间
						 if(jsons.code == "200"){
							 // $("#button_save").attr("disabled", "disabled");// 提交状态，按钮不可用
							 //layer.msg("文件上传成功" , {icon: 1});
							 //console.log(jsons);
							 $("#uploadStatus").html("<font style='color:green'>文件导入成功!用时："+spendTime+"s！批次号为:"+jsons.list.data.codeId+"</font>");
						 }else{
							 //  $("#button_save").removeAttr("disabled");// 提交状态，按钮可用
							 $("#uploadStatus").html("<font style='color:red'>文件导入失败!</font>");
						 }
						 getList();
					 }else{
						 alert("Problem retrieving XML data");
						 // $("#button_save").removeAttr("disabled");// 提交状态，按钮可用
					 }
				 }
			 }
//			 function downloadFile(){
//				 var path="";
//				 $('#content').children('tr').each(function() {
//					 var $that = $(this); 
//						$that.children('td').children('a[data-opt=file]').on('click', function() {
//							path=$(this).data('id');
//							alert(path+"hh");
//							alert(path+"hh");
//						});
//					});
//				 //alert(path);
////				 $.ajax({
////						url : hostIp+ "/blacklist/download.do?v=" + new Date().getTime(),
////						method : "post",
////						dataType : "json",
////						data : {
////							path:path
////						},
////						success : function(data) {
////							
////						},
////						error : function() {
////							alert("下载失败，请重新下载");
////						}
////					});				 			
//			}
			function dowloadCSV(){
				location.href=getCtxPath() + "/index/views/blacklist/blacklist(UTF-8).csv";
			}
			