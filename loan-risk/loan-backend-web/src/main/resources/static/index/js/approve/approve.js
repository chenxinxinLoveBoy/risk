 		layui.config({
				base: '../../js/'
			});
			
			var   startTime = "", endTime = "", appName = "", rgAuditingTime='',endRgAuditingTime='', appSerialNumber = "", auditingState = "", currPage = "",
			name = "", phoneNum = "", isPushApp = "", isStep = "", fraudTplId="", scoreTplId= "", flag = 0, decisionTreeId="",customerId='';

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
						var rgAuditingTime = {
							    format: 'YYYY-MM-DD hh:mm:ss'
								,istime: true
							    ,choose: function(datas){
							      end.min = datas; //开始日选好后，重置结束日的最小日期
							      end.start = datas //将结束日的初始值设定为开始日
							    }
							};
						var endRgAuditingTime = {
							    format: 'YYYY-MM-DD hh:mm:ss'
								,istime: true
							    ,choose: function(datas){
							      start.max = datas; //结束日选好后，重置开始日的最大日期
							    }
							};
						var end = {
						    format: 'YYYY-MM-DD hh:mm:ss'
							,istime: true
						    ,choose: function(datas){
						      start.max = datas; //结束日选好后，重置开始日的最大日期
						    }
						};
					  
					  var from =  getQueryString("from");;
					  if (startTime != '' || endTime != '' || from === "count") {
						  $("#startTime").val(startTime);
						  $("#endTime").val(endTime);
					  }else {
						  $("#startTime").val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
						 // $("#auditingTime").val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
						  $("#endTime").val(laydate.now(0,'YYYY-MM-DD 23:59:59'));
						  startTime = $("#startTime").val();
						  endTime = $("#endTime").val();
						  //auditingTime =$("#auditingTime").val();
					  }
					  
					  $("#startTime").on("click", function(){
					    start.elem = this;
					    laydate(start);
					    if(laydate.now(0,'YYYY-MM-DD hh:mm:ss') == $(this).val()){
						    $(this).val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
					    }
					  });
					  
					  $("#rgAuditingTime").on("click", function(){
						    start.elem = this;
						    laydate(start);
						    if(laydate.now(0,'YYYY-MM-DD hh:mm:ss') == $(this).val()){
							    $(this).val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
						    }
						  });
					  $("#endRgAuditingTime").on("click", function(){
						    end.elem = this
						    laydate(end);
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
			//重置
			function clecr(){ 
				$("#appSerialNumber").val('');
				$("#name").val('');
				$("#rgAuditingTime").val('');
				$("#endRgAuditingTime").val('');
				$("#state").val('');
				$("#phoneNum").val('');
				$("#certCode").val('');
				$("#auditMan").val('');
				$("#startTime").val(''); 
				$("#endTime").val(''); 
				$("#auditingState").val(''); 
				$("#customerId").val('')
				$("#errorDescription").val('')
			}
			
			
			function getList(){
				var ycNumber = $("#ycNumber").val();
				var appSerialNumber =$("#appSerialNumber").val();
				var name = $("#name").val();
				var auditingState = $("#auditingState").val();
				var phoneNum  = $("#phoneNum").val();;
				var certCode = $("#certCode").val();;
				var source = $("#source").val();
				var appLevel = $("#appLevel").val();
				var auditMan = $("#auditMan").val();;
				var startTime = $("#startTime").val();  
				var endTime = $("#endTime").val();
				var errorDescription = $("#errorDescription").val(); 
				var rgAuditingTime = $("#rgAuditingTime").val();
				var endRgAuditingTime = $("#endRgAuditingTime").val();
				var customerId = $("#customerId").val();
				
				if(phoneNum != '' && phoneNum != null) {
					var reg = /^\d{11}$/;  
					if(reg.test(phoneNum) === false){ 
						parent.layer.msg("用户手机号码输入不正确" , {icon: 2});
						return ;
					}
				} 
				layui.use(['paging', 'form'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form();

					// 页面初始化加载		start
	                paging.init({
	                    openWait: true,
						url: getCtxPath() + "/backend/application/findApproveInfo.do?v=" + new Date().getTime(),// 请求服务器的url
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							t: Math.random(),
							appSerialNumber : appSerialNumber,
							name : name,
							source:source,
							appLevel : appLevel,
							startTime : startTime,
							endTime : endTime,
							endRgAuditingTime : endRgAuditingTime,  
							rgAuditingTime  : rgAuditingTime,
							phoneNum : phoneNum,  
							auditingState : auditingState, 
							auditMan  : auditMan,
							certCode  : certCode,
							customerId : customerId,
							ycNumber : ycNumber,
							errorDescription :errorDescription
						},
						type: 'post',
						tempElem: '#tpl', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 10 //分页大小，当前页面显示的条数 
						},
						success: function(result) { //渲染成功的回调  
							/*form.render('checkbox');
							form.on('checkbox(allselector)', function(data) {
								var elem = data.elem;
								$('#content').children('tr').each(function() {
									var $that = $(this);
									//全选或反选
									$that.children('td').eq(0).children('input[type=checkbox]')[0].checked = elem.checked;
									form.render('checkbox');
								});
							});*/
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
								$that.children('td').children('a[data-opt=find]').on('click', function() { 
									var appId =$(this).data('aid');   
									var id = $(this).data('id');   
									var name =$(this).data('name');  
									var code =  $(this).data('rcode'); 
									var isCallPhone =  $(this).data('is'); 
									var netcheckNoAbnormal =  $(this).data('net'); 
									var state =  $(this).data('state'); 
									var money =  $(this).data('money');
									var remark =  $(this).data('remark');
									var app  =  $(this).data('app');
									var cid  =  $(this).data('cid');		
									//var href = getCtxPath() + "/index/views/approveDetails/approveDetails.html?appSerialNumber="+id+'&code='+code+'&appId='+appId;
									var href = getCtxPath() + "/index/views/approveDetails/approveDetails.html?appSerialNumber="+id+'&money='+money+'&state='+state+'&appId='+appId+'&netcheckNoAbnormal='+netcheckNoAbnormal+'&isCallPhone='+isCallPhone+'&remark='+remark+'&id='+cid+'&appName='+app; 
									var data = { 
										"title": name+"案件详情",
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
			
 			