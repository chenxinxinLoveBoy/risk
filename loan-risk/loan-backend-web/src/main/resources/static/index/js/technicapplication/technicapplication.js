			layui.config({
				base: '../../js/'
			});
			
			var banCode = "", auditResult = "", certCode = "", startTime = "", endTime = "", appName = "",
			source = "", appSerialNumber = "", auditingState = "", currPage = "", name = "", phoneNum = "", isPushApp = "", isStep = "", applicationId = "",
			banCodeTplId = "", fraudTplId = "", scoreTplId = "", failureTimes ="", errorDescription= "", flag = 0, isHbaseSyn='',appChannel='',auditMan='',appLevel='',decisionTreeId="" ;

			//初始化加载startTime
			$(function(){

				$("#showTimeSelect").click(function(){
                    layer.open({
                        title : "批量消息重发",
                        type : 1,
                        area : [ '700px', '500px' ],
                        //area : [ '100%', '100%' ],
                        fixed : true, //不固定
                        move : false,//禁止拖动
                        //maxmin : true,
                        content : $("#byTimeForm")
                    });
				});

                //发送消息
                $("#batchSend").click(function(){
                    var applicationIds = '';
                    $('#content').children('tr').each(function () {
                        var $that = $(this);
                        var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;
                        if ($cbx) {
                            var applicationId = $that.children('td').eq(0).children('input[type=checkbox]').attr("data-id");
                            applicationIds += applicationId + ',';
                        }
                    })
                    if (applicationIds.length == 0) {
                        layer.msg('请选择一行数据！', {
                            time: 2000, //2s后自动关闭
                            btn: ['知道了']
                        });
                        return;
                    }
                    layer.confirm("您确定要重发吗？", {
                        btn: ['确定', '取消'], //按钮
                        shade: 0.4 //显示遮罩
                    }, function (index) {
                        applicationIds = applicationIds.substr(0, applicationIds.length - 1);
                        layer.close(index);
                        layer.load(1,{shade:[0.5,'#fff']});
                        $.ajax({
                            url: getCtxPath() + "/backend/mq/log/sendHbase",
                            method: 'post',
                            dataType: "json",
                            data: {
                                applicationIds: applicationIds
                            },
                            success: function (data) {
                                layer.closeAll();
                                layer.msg(data.message);// 提示信息
                                //reload();
                            },
                            error: function () {
                                layer.closeAll();
                                layer.alert("请求网络异常，请尝试重新登录！");
                            },
                            complete: function () {
                            }
                        });
                    });
                });

                //发送消息
                $("#send").click(function () {
                    layer.confirm("您确定要发送吗？", {
                        btn: ['确定', '取消'], //按钮
                        shade: 0.4 //显示遮罩
                    }, function (index) {
                        layer.close(index);
                        var term = returnTerm();
                        if (term.certCode != '' && term.certCode != null) {
                            var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
                            if (reg.test(term.certCode) === false) {
                                parent.layer.msg("身份证号输入不正确", {icon: 2});
                                return;
                            }
                        }
                        if (term.phoneNum != '' && term.phoneNum != null) {
                            var reg = /^\d{11}$/;
                            if (reg.test(term.phoneNum) === false) {
                                parent.layer.msg("用户手机号码输入不正确", {icon: 2});
                                return;
                            }
                        }
                        layer.load(1,{shade:[0.5,'#fff']});
                        $.ajax({
                            url: getCtxPath() + "/backend/technicApplication/findApplicationByDate",
                            method: 'post',
                            dataType: "json",
                            data: term,
                            success: function (data) {
                                layer.closeAll();
                                if (data.code == 200) {
                                    layer.msg("成功，发送消息" + data.data.data + "条！！！");// 提示信息
                                } else {
                                    layer.msg(data.message);// 提示信息
                                }
                                //reload();
                            },
                            error: function () {
                                layer.closeAll();
                                layer.alert("请求网络异常，请尝试重新登录！");
                            },
                            complete: function () {
                            }
                        });
                    });
                });

                $("#consumption").click(function(){
                    layer.confirm("您确定要消费吗？", {
                        btn: ['确定', '取消'], //按钮
                        shade: 0.4 //显示遮罩
                    }, function (index) {
                        layer.close(index);
                        var term = returnTerm();
                        if (term.certCode != '' && term.certCode != null) {
                            var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
                            if (reg.test(term.certCode) === false) {
                                parent.layer.msg("身份证号输入不正确", {icon: 2});
                                return;
                            }
                        }
                        if (term.phoneNum != '' && term.phoneNum != null) {
                            var reg = /^\d{11}$/;
                            if (reg.test(term.phoneNum) === false) {
                                parent.layer.msg("用户手机号码输入不正确", {icon: 2});
                                return;
                            }
                        }
                        layer.load(1,{shade:[0.5,'#fff']});
                        $.ajax({
                            url: getCtxPath() + "/backend/technicApplication/byTermReConsumption",
                            method: 'post',
                            dataType: "json",
                            data: term,
                            success: function (data) {
                                layer.closeAll();
                                if (data.code == 200) {
                                    layer.msg("成功，发送消息" + data.data.data + "条！！！");// 提示信息
                                } else {
                                    layer.msg(data.message);// 提示信息
                                }
                                //reload();
                            },
                            error: function () {
                                layer.closeAll();
                                layer.alert("请求网络异常，请尝试重新登录！");
                            },
                            complete: function () {
                            }
                        });
                    });
				});
                
                
                
                //根据条件手工推送APP
                $("#pushApp").click(function () {
                    layer.confirm("您确定要手工推送吗？", {
                        btn: ['确定', '取消'], //按钮
                        shade: 0.4 //显示遮罩
                    }, function (index) {
                        layer.close(index);
                        var term = returnTerm();
                        if (term.certCode != '' && term.certCode != null) {
                            var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
                            if (reg.test(term.certCode) === false) {
                                parent.layer.msg("身份证号输入不正确", {icon: 2});
                                return;
                            }
                        }
                        if (term.phoneNum != '' && term.phoneNum != null) {
                            var reg = /^\d{11}$/;
                            if (reg.test(term.phoneNum) === false) {
                                parent.layer.msg("用户手机号码输入不正确", {icon: 2});
                                return;
                            }
                        }
                        layer.load(1,{shade:[0.5,'#fff']});
                        $.ajax({
                            url: getCtxPath() + "/backend/technicApplication/findPushAppStatusByDate",
                            method: 'post',
                            dataType: "json",
                            data: term,
                            success: function (data) {
                                layer.closeAll();
                                if (data.code == 200) {
                                    layer.msg("成功，发送消息" + data.data.data + "条！！！");// 提示信息
                                } else {
                                    layer.msg(data.message);// 提示信息
                                }
                                //reload();
                            },
                            error: function () {
                                layer.closeAll();
                                layer.alert("请求网络异常，请尝试重新登录！");
                            },
                            complete: function () {
                            }
                        });
                    });
                });
            	
                function checkChecked(){//检验多选框是否多选
    				var flag=0;
    				$('#content').children('tr').each(function() {
    					var $that = $(this);
    					var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;
    					if($cbx){
    						flag++;
    					}
    				});
    				if(flag==0){
    					layer.msg('请勾选一条数据！');
    					return ;
    				}
    				if(flag>1){
    					layer.msg('请选择单个用户查询！' , {icon: 2});
    					throw SyntaxError();
    				}
    				
    			}
                //用户信息
				$('#getuserInfo').on('click', function() {
					var ids = ''; 
					checkChecked();
					$('#content').children('tr').each(function() {
						var $that = $(this);
						var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;   
						if($cbx) {
							var applicationId = $that.children('td').eq(0).children('input[type=checkbox]').data('id');
							console.log(applicationId);
							var platformId = $that.children('td').eq(0).children('input[type=checkbox]').data('pid');
							console.log(platformId);
							var customerId = $that.children('td').eq(0).children('input[type=checkbox]').data('cid'); 
							console.log(customerId);
							var name = $that.children('td').eq(0).children('input[type=checkbox]').data('name'); 
							console.log(name);  
							var href = getCtxPath() + "/index/views/customerManager/customerInfo.html?id="+platformId+'&applicationId='+applicationId+'&customerId='+customerId;
							var data = {
								"title": "用户信息-"+name,
								"icon": "&#xe60c;",
								"href": href
							}
							window.parent.topTab.tabAdd(data);
						} 
					});
				  });
				
				//客户借款申请扩展明細
				$('#xinyong').on('click', function() {
					var ids = ''; 
					checkChecked();
					$('#content').children('tr').each(function() {
						var $that = $(this);
						var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;   
						if($cbx) {
							var id = $that.children('td').eq(0).children('input[type=checkbox]').data('id');
							console.log(id); 
							var name = $that.children('td').eq(0).children('input[type=checkbox]').data('name'); 
							console.log(name);  
							var href = getCtxPath() + "/index/views/technicapplication/technicapplicationList.html?id="+id;
							var data = {
								"title": "客户借款申请详情-"+name,
								"icon": "&#xe60c;",
								"href": href
							}
							window.parent.topTab.tabAdd(data);
						} 
					});
				  });
				
				//申请单信息
				$('#getDetailInfo').on('click', function() {
					var ids = ''; 
					checkChecked();
					$('#content').children('tr').each(function() {
						var $that = $(this);
						var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;   
						if($cbx) {
							var id = $that.children('td').eq(0).children('input[type=checkbox]').data('id');
							console.log(id);
							layer.open({
								  type: 2,
								  title: '申请单详情',// 设置false表示不显示
								  closeBtn: 1, //不显示关闭按钮
								  shade: 0.4, //遮罩透明度
								  area: ['840px', '550px'],
								  skin: 'layui-layer-rim', //加上边框
								  fixed: false, //不固定
								  maxmin: true, // 允许屏幕最小化
								  anim: 2,
								  content: ['technicapplication_detail.html?applicationId='+id], //iframe的url，no代表不显示滚动条
								});
						} 
					});
				  }); 

				startTime = decodeURI(getQueryString("startTime"));
				endTime = decodeURI(getQueryString("endTime"));
				auditResult = decodeURI(getQueryString("auditResult"));
				decisionTreeId = decodeURI(getQueryString("decisionTreeId"));

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
					  } else {
						  $("#startTime").val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
						  $("#endTime").val(laydate.now(0,'YYYY-MM-DD 23:59:59'));
						  startTime = $("#startTime").val();
						  endTime = $("#endTime").val();
					  }
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
						$.ajax({
							url : getCtxPath() + "/backend/scDecisionTree/scDecisionTreeListAll.do?v=" + new Date().getTime(),
							method : "post",
							dataType : "json",
							data : {
							},
							success : function(data) {
								var arr = [];
								arr.push('<select name="treeId" id = "treeId" class="layui-input" lay-search="">');
						    	arr.push('<option value="00">全部</option>');
								layui.each(data.list, function(index, item){
										if(decisionTreeId == item.decisionTreeId){
									    	arr.push('<option value="'+ item.decisionTreeId + '" selected>' + item.decisionTreeName + '</option>');
										}else{
									    	arr.push('<option value="'+ item.decisionTreeId + '">' + item.decisionTreeName + '</option>');
										}
								    });
								arr.push('</select>');
								$('#decisionTreeId').html(arr.join(''));
								getList();
								form.render();
							},
							error : function() {
								alert("获取禁止项模板信息失败,请尝试重新获取！");
							}
						});
					  getList();
				});
				
				var certCode1 = getQueryString("certCode").trim();
				certCode = certCode1;
				source = getQueryString("source");
				if(source=='00'){
					source='';
				}
				appSerialNumber = getQueryString("appSerialNumber").trim();
				auditingState = getQueryString("auditingState");
				if(auditingState=='00'){
					auditingState='';
				}
				name = decodeURI(getQueryString("name")).trim();
				phoneNum = getQueryString("phoneNum").trim();
				isStep = getQueryString("isStep").trim() || $("#isStep").val();
				if(isStep=='00'){
					isStep='';
				}
				appName = getQueryString("appName").trim();
				banCode = getQueryString("banCode").trim();
				currPage = getQueryString("currPage");
				
				$("#certCode").val(certCode1);
				$("#appSerialNumber").val(appSerialNumber);
				$("#name").val(name);
				$("#phoneNum").val(phoneNum);
				$("#source").val(source);
				$("#auditingState").val(auditingState);
				$("#source").find("option[value="+source+"]").attr("selected",true);
				$("#isStep").find("option[value="+isStep+"]").attr("selected",true);
				$("#appName").find("option[value="+appName+"]").attr("selected",true);
				$("#auditingState").find("option[value="+auditingState+"]").attr("selected",true);
				//getBanList();
			});
			
			// 搜索
			 function sel() { 
				banCode = $("#banCode").val();
				certCode = $("#certCode").val().trim();
				startTime = $("#startTime").val();
				endTime = $("#endTime").val();
				source = $("#source").val();
				if(source=='00'){
					source='';
				}
				appSerialNumber = $("#appSerialNumber").val().trim();
				name = $("#name").val().trim();
				phoneNum = $("#phoneNum").val().trim(); 
				auditingState = $("#auditingState").val();
				if(auditingState=='00'){
					auditingState='';
				}
				isStep = $("#isStep").val();
				if(isStep=='00'){
					isStep='';
				}
				appName = $("#appName").val();
				if(appName=='00'){
					appName='';
				}
				isPushApp = $("#isPushApp").val();
				if(isPushApp=='00'){
					isPushApp='';
				}
				auditResult = "";
				applicationId = $("#applicationId").val(); 
				failureTimes =$("#failureTimes").val();
				errorDescription =$("#errorDescription").val();
				isHbaseSyn = $("#isHbaseSyn").val();//同步大数据标识
				if(isHbaseSyn=='00'){
					isHbaseSyn='';
				}
				appChannel = $("#appChannel").val();//app下载渠道
				auditMan =$("#auditMan").val().trim();//是否为人工审核
				if(auditMan=='00'){
					auditMan='';
				}
				appLevel = $("#appLevel").val();//新老客户标识
				if(appLevel=='00'){
					appLevel='';
				}
				decisionTreeId =$("#treeId").val();
				if(decisionTreeId=='00'){
					decisionTreeId='';
				}

				if(certCode != '' && certCode != null) {
					var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
					if(reg.test(certCode) === false){ 
						parent.layer.msg("身份证号输入不正确" , {icon: 2});
						return ;
					}
				}
				if(phoneNum != '' && phoneNum != null) {
					var reg = /^\d{11}$/;  
					if(reg.test(phoneNum) === false){ 
						parent.layer.msg("用户手机号码输入不正确" , {icon: 2});
						return ;
					}
				}
				getList();
			};
			// 当前菜单名称
			var menuNames = "增强版信息审核列表";
			function getList(_pageIndex){
				 $("#check").removeAttr("checked");//取消全选   
				//局部刷新回调获取参数,
				var isFirst = 1;
				var pageIndex = _pageIndex || $("#paged").find("em").eq(1).html()||1;
				layui.use(['paging', 'form'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form();

					// 页面初始化加载		start
	                paging.init({
	                    openWait: true,
						url: getCtxPath() + "/backend/technicApplication/findAll.do?v=" + new Date().getTime(),// 请求服务器的url
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							t: Math.random(),
							banCode : banCode,
							certCode : certCode,
							startTime : startTime,
							endTime : endTime,
							source : source,
							appSerialNumber : appSerialNumber,
							name : name,
							phoneNum : phoneNum,
							isStep : isStep, 
							appName : appName, 
							auditResult : auditResult, 
							isPushApp : isPushApp,
							auditingState : auditingState,
							applicationId : applicationId, 
							failureTimes : failureTimes,
							errorDescription : errorDescription,
							isHbaseSyn : isHbaseSyn,
							appChannel :appChannel,
							auditMan  : auditMan,
							decisionTreeId : decisionTreeId,
							appLevel : appLevel
							
						},
						type: 'post',
						tempElem: '#tpl', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 10 //分页大小，当前页面显示的条数
						},
						success: function(result) { //渲染成功的回调
							var personCount = result.personCount;
                            $("#personCount").html(personCount+" 人");
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
							
						/*	//绑定所有'用户信息跳转'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=xinyong]').on('click', function() {
									var id = $(this).data('id'); 
									var href = getCtxPath() + "/index/views/technicapplication/technicapplicationList.html?id="+$(this).data('id')+'&applicationId='+$(this).data('applicationid');
									var data = {
										"title": "客户借款申请详情-"+$(this).attr("data-name"),
										"icon": "&#xe60c;",
										"href": href
									}
									window.parent.topTab.tabAdd(data);

								});
							});
							//绑定所有'用户信息跳转'按钮事件	
						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=find]').on('click', function() {
									var id = $(this).data('id'); 
									var customerId = $(this).data('cid');  
									var href = getCtxPath() + "/index/views/customerManager/customerInfo.html?id="+$(this).data('id')+'&applicationId='+$(this).data('applicationid')+'&customerId='+customerId;
									var data = {
										"title": "用户信息-"+$(this).attr("data-name"),
										"icon": "&#xe60c;",
										"href": href
									}
									window.parent.topTab.tabAdd(data);

								});
							});
							//绑定所有'申请单详情'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=detail]').on('click', function() {
									var id = $(this).data('id');  
									layer.open({
										  type: 2,
										  title: '申请单详情',// 设置false表示不显示
										  closeBtn: 1, //不显示关闭按钮
										  shade: 0.4, //遮罩透明度
										  area: ['840px', '550px'],
										  skin: 'layui-layer-rim', //加上边框
										  fixed: false, //不固定
										  maxmin: true, // 允许屏幕最小化
										  anim: 2,
										  content: ['technicapplication_detail.html?applicationId='+id], //iframe的url，no代表不显示滚动条
										});
								});
							});*/
							
							//绑定所有'查询91数据报告'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=sendQuery]').on('click', function() {
									var failureTimes= $(this).data('time'); 
									var applicationId= $(this).data('id'); 
									// ajax 请求后台
										$.ajax({
											url : getCtxPath() + "/backend/credit/queryByGuid.do",
											method : 'post',
											dataType : 'json',
											data: {
												applicationId: applicationId, // 传到后台的参数，子窗口的form表单
												failureTimes:failureTimes,
												menuNames : menuNames,
												functionNames: (applicationId != '' ? "查询用户91数据报告" : " ") // 按钮功能名称
												 
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
									   
									  return false; 
								});
							});
							
							  //绑定所有'清零'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=update]').on('click', function() {
									var failureTimes= $(this).data('time'); 
									var applicationId= $(this).data('id'); 
									// ajax 请求后台
										$.ajax({
											url : getCtxPath() + "/backend/technicApplication/updatefailureTimes.do",
											method : 'post',
											dataType : 'json',
											data: {
												applicationId: applicationId, // 传到后台的参数，子窗口的form表单
												failureTimes:failureTimes,
												menuNames : menuNames,
												functionNames: (applicationId != '' ? "清空失败次数" : " ") // 按钮功能名称
												 
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
									   
									  return false; 
								});
	
							});  
							
							
						},
					});
	               
	                // 初始化加载init     end!

				});
			}	 
			//获取所有选择的列
			  function getSelected() {  
				var ids = ''; 
				$('#content').children('tr').each(function() {
					var $that = $(this);
					var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;
					if($cbx) {
						var n = $that.children('td').eq(0).children('input[type=checkbox]').data('id'); 
						ids += n + ',';
					} 
				});
				if(ids == '' || ids == undefined){ // 验空 
					layer.msg('请勾选需清零的数据！');
					return false;
				}  
			if(confirm('您确定要清零失败次数吗？')){ 	 
				$.ajax({
					url: getCtxPath() + "/backend/technicApplication/updateBatch.do?v=" + new Date().getTime(),
					method: "post",
					dataType: "json",
					data:{ 
						ids : ids,
						menuNames : menuNames,
						functionNames: (ids.length > 0  ? "批量清空失败次数" : " ") // 按钮功能名称
					},
					success: function(data){
						if(data.code == 4001){
							parent.layer.msg(data.message)
							window.parent.getList();  
							return;
						}else{
							parent.layer.msg(data.message);
							getList();
						} 
					},
					error: function(){
						parent.layer.alert("数据清零失败！");
						getList();
					},
					complete: function(){ 
					}
				});
			}
			}

			function returnTerm(){

				var term = { //发送到服务端的参数
                    t: Math.random(),
                    banCode :  $("#banCode").val()=='00'?'':$("#banCode").val(),
                    certCode : $("#certCode").val().trim()=='00'?'':$("#certCode").val().trim(),
                    startTime : $("#startTime").val(),
                    endTime : $("#endTime").val(),
                    source : $("#source").val()=='00'?'':$("#source").val(),
                    appSerialNumber : $("#appSerialNumber").val().trim(),
                    name : $("#name").val().trim(),
                    phoneNum : $("#phoneNum").val().trim(),
                    isStep : $("#isStep").val()=='00'?'':$("#isStep").val(),
                    appName :  $("#appName").val()=='00'?'':$("#appName").val(),
                    auditResult : auditResult,
                    isPushApp : $("#isPushApp").val()=='00'?'':$("#isPushApp").val(),
                    auditingState : $("#auditingState").val()=='00'?'':$("#auditingState").val(),
                    applicationId : $("#applicationId").val(), 
                    failureTimes : $("#failureTimes").val(),
                    errorDescription : $("#errorDescription").val(),
                    isHbaseSyn : $("#isHbaseSyn").val()=='00'?'':$("#isHbaseSyn").val(),
                    appChannel :$("#appChannel").val(),
                    auditMan  : $("#auditMan").val()=='00'?'':$("#auditMan").val(),
                    appLevel : $("#appLevel").val()=='00'?'':$("#appLevel").val()

                    
                }

                return term;
			}
		/*	function getBanList() {
				$.ajax({
					url : getCtxPath() + "/backend/scBanControl/getAll.do",
					method : "post",
					dataType : "json",
					data : {
					},
					success : function(data) {
						var arr = [];
						arr.push('<select name="banCode" id = "banCode" class="layui-input"><option value="">全部</option>');
						layui.each(data.list, function(index, item){
								if(banCode === item.banCode){
							    	arr.push('<option value="'+ item.banCode + '" selected>' + item.ruleName + '</option>');
								}else{
							    	arr.push('<option value="'+ item.banCode + '">' + item.ruleName + '</option>');
								}
						    });
						arr.push('</select>');
						$('#banContent').html(arr.join(''));
					},
					error : function() {
						alert("获取用户信息失败,请尝试重新获取！");
					}
				});
			}*/
			
			
 			