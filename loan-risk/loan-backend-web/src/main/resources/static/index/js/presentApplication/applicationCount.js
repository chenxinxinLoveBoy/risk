			layui.config({
				base: '../../js/' 
			});
			
			var banTplNames = [];
			var treeNames = [];
			//初始化加载startTime
			$(function(){
				
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
						    ,start: laydate.now(0,'YYYY-MM-DD 00:00:00')
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
					$("#startTime").val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
					startTime = $("#startTime").val().trim();
					//模板下拉框
					$.ajax({
						url : getCtxPath() + "/backend/sctemplate/templatelistAll.do?v=" + new Date().getTime(),
						method : "post",
						dataType : "json",
						data : {
							pageIndex: 0,
							pageSize: 100
						},
						success : function(data) {
							var arr = [];
							arr.push('<select name="tplId" id = "tplId" class="layui-input">');
					    	arr.push('<option value="">全部</option>');
							layui.each(data.list, function(index, item){
							    	arr.push('<option value="'+ item.banControlTplId + '">' + item.banTplName + '</option>');
							    	banTplNames[item.banControlTplId] = item.banTplName ;
							});
							arr.push('</select>');
							$('#tplIdContent').html(arr.join(''));
						},
						error : function() {
							alert("获取用户信息失败,请尝试重新获取！");
						}
					});
					$.ajax({
						url : getCtxPath() + "/backend/scDecisionTree/scDecisionTreeListAll.do?v=" + new Date().getTime(),
						method : "post",
						dataType : "json",
						data : {
							pageIndex: 0,
							pageSize: 100
						},
						success : function(data) {
							var arr = [];
							arr.push('<select name="treeId" id = "treeId" class="layui-input">');
					    	arr.push('<option value="">全部</option>');
							layui.each(data.list, function(index, item){
							    	arr.push('<option value="'+ item.decisionTreeId + '">' + item.decisionTreeName + '</option>');
							    	treeNames[item.decisionTreeId] = item.decisionTreeName ;
						    });
							arr.push('</select>');
							$('#decisionTreeId').html(arr.join(''));
							getList();
						},
						error : function() {
							alert("获取禁止项模板信息失败,请尝试重新获取！");
						}
					});

				})
			});
			
			var startTime = "", endTime = "";
			// 条件搜索
			$('#search').on('click', function() {
				startTime = $("#startTime").val().trim();
				endTime = $("#endTime").val().trim();
				getList();
			});
			
			/**图形化  start*/
			$('#statics').on('click', function() {
				var isStep = $("#isStep").val().trim();
				var source = $("#source").val().trim();
				var index = layer.open({
					  type: 2,
					  title: '信用审核统计图',// 设置false表示不显示
					  closeBtn: 1, //0：不显示关闭按钮
					  shade: 0.4, //遮罩透明度
					  area: ['1500px', '600px'],
					  fixed: false, //不固定
					  maxmin: true, // 允许屏幕最小化
					  content: ['applicationStatistics.html?isStep='+isStep+'&source='+source], //iframe的url，no代表不显示滚动条
					});
					layer.full(index);  //弹框全屏化
			});
			/**图形化     end*/

			function getList(){
				layui.use(['paging', 'layer', 'form', 'element'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form(),
						element = layui.element();
	
					var 	isStep = $("#isStep").val().trim();
					var source = $("#source").val().trim();
					var appName = $("#appName").val().trim();
					var tplId = $("#tplId").val().trim();
					var treeId = $("#treeId").val().trim();
					var appLevel = $("#appLevel").val().trim();
					// 页面初始化加载		start
	                paging.init({
	                    openWait: true,
						url: getCtxPath() + "/backend/application/queryResultCountInfo.do?v=" + new Date().getTime(),
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							t: Math.random(),
							startTime:startTime,
							endTime:endTime,
							source:source,
							appName:appName,
							appLevel:appLevel,
							banControlTplId:tplId,
							decisionTreeId:treeId,
							isStep:isStep
						},
						type: 'post',
						tempElem: '#tpl', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 10 //分页大小，当前页面显示的条数
						}, 
						success: function() { //渲染成功的回调
	//						alert('渲染成功');
						},
						fail: function(msg) { //获取数据失败的回调
							layer.msg("获取数据失败！");
						},
						complate: function() { //完成的回调
							//绑定所有'详情'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=find]').on('click', function() {
									var href = getCtxPath() + "/index/views/application/application.html?auditingState=3&source="+$(this).data('id')
										+"&startTime="+startTime+"&endTime="+endTime+"&from=count"+"&isStep="+$(this).attr("data-step")+"&banCodeTplId="+$(this).attr("data-tplId")
										+"&decisionTreeId="+$(this).attr("data-tree")+"&banCode="+$(this).attr("data-rule")
										+"&appName="+$(this).attr("data-appName")+"&appLevel="+appLevel+"&auditResult="+encodeURI($(this).attr("data-result"));
									var data = {
										"title": "信用审核列表_"+$(this).attr("data-rule"),
										"icon": "&#xe60c;",
										"href": href
									}
									window.parent.topTab.tabAdd(data);
								});
							});
							//绑定所有'同盾命中项'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=time]').on('click', function() {
									$("#startTime").val($(this).data('id'));
									$("#search").click();
								});
							});
							//绑定所有'同盾命中项'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=td]').on('click', function() {
									var href = getCtxPath() + "/index/views/statistic/tdList.html?auditingState=3"+"&startTime="+startTime+"&endTime="+endTime+"&from=count"
										+"&banCode="+$(this).attr("data-rule")+"&auditResult="+encodeURI($(this).attr("data-result"));
									var data = {
										"title": "同盾命中项统计_"+$(this).attr("data-rule"),
										"icon": "&#xe60c;",
										"href": href
									}
									window.parent.topTab.tabAdd(data);
								});
							});
						},
					});
	                // 初始化加载init     end!
	                
	                $.ajax({
						url :getCtxPath() + "/backend/application/queryResultCount.do",
						method: "post",
						dataType:"json",
						data: {
							startTime:startTime,
							endTime:endTime,
							appName:appName,
							appLevel:appLevel,
							source:source,
							banControlTplId:tplId,
							decisionTreeId:treeId,
							isStep:isStep
						},
						success: function(data){
							if(data.code = 200){
								var list = data.list.data.list;
								var totalCreditMoney = data.list.data.totalCreditMoney;
								
								var unAuditedNum = 0, passNum = 0, notPassNum = 0, sum = 0;
								var unAuditedCertCount=0,  passCertCount=0, notPassCertCount=0,  certCountSum = 0;
								for (var i in list) {
									if (list[i].auditingState == 2) {
										passNum += parseInt(list[i].count);
										passCertCount += parseInt(list[i].certCount);
									} else if (list[i].auditingState == 3) {
										notPassNum += parseInt(list[i].count);
										notPassCertCount += parseInt(list[i].certCount);
									} else {
										unAuditedNum += parseInt(list[i].count);
										unAuditedCertCount += parseInt(list[i].certCount);
									}
								}
								$("#unAudited_certcode").html(unAuditedCertCount+" 人");
								$("#pass_certcode").html(passCertCount+" 人");
								$("#notPass_certcode").html(notPassCertCount+" 人");
								certCountSum = unAuditedCertCount + passCertCount + notPassCertCount;
								$("#sum_certcode").html(certCountSum+" 人");
								
								$("#unAudited").html(unAuditedNum+" ");
								$("#pass").html(passNum+" ");
								$("#notPass").html(notPassNum+" ");
								sum = unAuditedNum + passNum + notPassNum;
								$("#sum").html(sum+" ");
								if (sum == 0 || certCountSum == 0) {
									$("#notPass_certcode").html(0+" 人");
									$("#pass_certcode").html(0+" 人");
									$("#unAudited_certcode").html(0+" 人");

									$("#notPassRate").html(0+" ");
									$("#passRate").html(0+" ");
									$("#unAuditedRate").html(0+" ");
									$("#realTimeRate").html(0+" ");
								}else{
									var notPassRate = ((notPassNum/sum)*100).toFixed(2) + '%';
									$("#notPassRate").html(notPassRate);
									var passRate = ((passNum/sum)*100).toFixed(2) + '%';
									$("#passRate").html(passRate);
									var unAuditedRate = (100 - ((passNum/sum)*100).toFixed(2) - ((notPassNum/sum)*100).toFixed(2)).toFixed(2);
									if(unAuditedRate>0){
										unAuditedRate = unAuditedRate+ '%';
									}else{
										unAuditedRate = "0.00%";
									}
									$("#unAuditedRate").html(unAuditedRate);
									if(passNum ==0){
										$("#realTimeRate").html("0.00%");
									}else{
										var realTimeRate = ((passNum/(notPassNum + passNum))*100).toFixed(2) + '%';
										$("#realTimeRate").html(realTimeRate);
									}
								}

								//总授信额度
								$("#totalCreditMoney").html(fmoney(parseFloat(totalCreditMoney),2)+"元");
								
							}
						},
						error: function(){  
	 						layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
						},
						complete: function(){
							form.render(); //刷新全部
						} 
					});	

				});
		 	 
			}

			//关闭
			function closeIfrname(){
				var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(frameindex);
			}

 			
 			