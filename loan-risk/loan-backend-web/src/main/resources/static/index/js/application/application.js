			layui.config({
				base: '../../js/'
			});
			
			var banCode = "", auditResult = "", banCodeTplId = "",  certCode = "",  startTime = "", endTime = "", appName = "",
			source = "", appSerialNumber = "", auditingState = "", currPage = "",
			name = "", phoneNum = "", isPushApp = "", isStep = "", fraudTplId="", scoreTplId= "", flag = 0, decisionTreeId="" ,appLevel="",appChannel='',isHbaseSyn='', auditMan='';

			//初始化加载startTime
			$(function(){
				startTime = decodeURI(getQueryString("startTime"));
				endTime = decodeURI(getQueryString("endTime"));
				auditResult = decodeURI(getQueryString("auditResult"));
				banCodeTplId = decodeURI(getQueryString("banCodeTplId"));
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
					  
					  var from =  getQueryString("from");;
					  if (startTime != '' || endTime != '' || from === "count") {
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
					//禁止项模板下拉框
						/*$.ajax({
							url : getCtxPath() + "/backend/sctemplate/templatelistAll.do?v=" + new Date().getTime(),
							method : "post",
							dataType : "json",
							data : {
							},
							success : function(data) {
								var arr = [];
								arr.push('<select name="tplId" id = "tplId" class="layui-input">');
						    	arr.push('<option value="">全部</option>');
								layui.each(data.list, function(index, item){
										if(banCodeTplId == item.banControlTplId){
									    	arr.push('<option value="'+ item.banControlTplId + '" selected>' + item.banTplName + '</option>');
										}else{
									    	arr.push('<option value="'+ item.banControlTplId + '">' + item.banTplName + '</option>');
										}
								    });
								arr.push('</select>');
								$('#tplIdContent').html(arr.join(''));
								getList();
							},
							error : function() {
								alert("获取禁止项模板信息失败,请尝试重新获取！");
							}
						});*/
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
						//欺诈项模板下拉框
					/*	$.ajax({
							url : getCtxPath() + "/backend/scFraudRuleTpl/scFraudRuleTplListAll.do?v=" + new Date().getTime(),
							method : "post",
							dataType : "json",
							data : {
							},
							success : function(data) {
								var arr = [];
								arr.push('<select name="fraudRuleTplId" id = "fraudRuleTplId" class="layui-input">');
						    	arr.push('<option value="">全部</option>');
								layui.each(data.list, function(index, item){
										if(fraudTplId == item.fraudRuleTplId){
									    	arr.push('<option value="'+ item.fraudRuleTplId + '" selected>' + item.fraudRuleTplName + '</option>');
										}else{
									    	arr.push('<option value="'+ item.fraudRuleTplId + '">' + item.fraudRuleTplName + '</option>');
										}
								    });
								arr.push('</select>');
								$('#fraudTplId').html(arr.join(''));
								getList();
							},
							error : function() {
								alert("获取欺诈项模板信息失败,请尝试重新获取！");
							}
						});*/
						//评分项模板下拉框
						/*$.ajax({
							url : getCtxPath() + "/backend/向老板?徐老板 /向老板?徐老板 .do?v=" + new Date().getTime(),
							method : "post",
							dataType : "json",
							data : {
							},
							success : function(data) {
								var arr = [];
								arr.push('<select name="scoreTplId" id = "scoreTplId" class="layui-input">');
						    	arr.push('<option value="">全部</option>');
								layui.each(data.list, function(index, item){
								    	arr.push('<option value="'+ item.  + '">' + item.  + '</option>');
								    });
								arr.push('</select>');
								$('#scoreTplId').html(arr.join(''));
								getList();
							},
							error : function() {
								alert("获取评分项模板信息失败,请尝试重新获取！");
							}
						});*/
//					  getList();
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
				appName = getQueryString("appName").trim();
				banCode = getQueryString("banCode").trim();
				currPage = getQueryString("currPage");
				appLevel = getQueryString("appLevel").trim();
				
				$("#certCode").val(certCode1);
				$("#appSerialNumber").val(appSerialNumber);
				$("#name").val(name);
				$("#phoneNum").val(phoneNum);
				$("#source").val(source);
				$("#auditingState").val(auditingState);
				$("#banCode").val(banCode);
				$("#source").find("option[value="+source+"]").attr("selected",true);
				$("#isStep").find("option[value="+isStep+"]").attr("selected",true);
				$("#appName").find("option[value="+appName+"]").attr("selected",true);
				$("#auditingState").find("option[value="+auditingState+"]").attr("selected",true);
				$("#appLevel").find("option[value="+appLevel+"]").attr("selected",true);
				//getList();
			});
			
			// 搜索
			$('#search').on('click', function() {
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
				// productQuota = $("#productQuota").val();
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
				appLevel = $("#appLevel").val();
				if(appLevel=='00'){
					appLevel='';
				}
				appChannel = $("#appChannel").val();
				isHbaseSyn = $("#isHbaseSyn").val();/**是否同步大数据, 0-否、1-是**/
				if(isHbaseSyn=='00'){
					isHbaseSyn='';
				}
				auditMan = $("#auditMan").val().trim();//是否为人工审核标识 auditMan 不为空位人工审核
				if(auditMan=='00'){
					auditMan='';
				}
				//banCodeTplId =$("#tplId").val();//禁止项模板主键ID
				//fraudTplId =$("#fraudRuleTplId").val();//欺诈项模板主键ID
				//scoreTplId =$("#scoreTplId").val();//评分项模板主键ID
				decisionTreeId =$("#treeId").val();
				if(decisionTreeId=='00'){
					decisionTreeId='';
				}

				/*if((applicationId == '' || applicationId == null) && (certCode == '' || certCode == null)){
					parent.layer.msg("借款编号、借款人身份证号不能全部为空!" , {icon: 2});
					return ;
				}*/
				
				if(phoneNum != '' && phoneNum != null) {
					var reg = /^\d{11}$/;  
					if(reg.test(phoneNum) === false){ 
						parent.layer.msg("用户手机号码输入不正确" , {icon: 2});
						return ;
					}
				}
				
				getList();
			});
			
			function getList(){
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
				appLevel = $("#appLevel").val();
				if(appLevel=='00'){
					appLevel='';
				}
				appChannel = $("#appChannel").val();
				isHbaseSyn = $("#isHbaseSyn").val();/**是否同步大数据, 0-否、1-是**/
				if(isHbaseSyn=='00'){
					isHbaseSyn='';
				}
				auditMan = $("#auditMan").val().trim();//是否为人工审核标识 auditMan 不为空位人工审核
				if(auditMan=='00'){
					auditMan='';
				} 
				decisionTreeId =$("#treeId").val();
				if(decisionTreeId=='00'){
					decisionTreeId='';
				}
 
				
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
						url: getCtxPath() + "/backend/application/findAll.do?v=" + new Date().getTime(),// 请求服务器的url
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
							//auditResult : auditResult, 
							isPushApp : isPushApp,
							banCodeTplId : banCodeTplId,//禁止项模板主键ID
							auditingState : auditingState,
							decisionTreeId : decisionTreeId,
							//fraudTplId : fraudTplId//欺诈项模板主键ID
							//scoreTplId : scoreTplId //评分项模板主键ID
							appLevel : appLevel,//客户标识（0-新客户，1-老客户）
							appChannel : appChannel,//app下载渠道
							isHbaseSyn : isHbaseSyn, //是否同步大数据, 0-否、1-是
							auditMan   : auditMan   //是否为人工审核标识
						},
						type: 'post',
						tempElem: '#tpl', //模块容器
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 10 //分页大小，当前页面显示的条数
						},
						success: function(result) { //渲染成功的回调  
							var personCount = result.personCount;
							 if (currPage != '' && currPage != '1' && flag == 0) {
								 flag = 1;
									paging.get({
			                            pageIndex: currPage,
			                            pageSize: 10
			                        });
								}
							 $("#personCount").html(personCount+" 人");
						},
						fail: function(msg) { //获取数据失败的回调
							layer.msg("获取数据失败！");
						},
						complate: function() { //完成的回调
							//绑定所有'用户信息跳转'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=find]').on('click', function() {
									var href = getCtxPath() + "/index/views/customerManager/customerInfo.html?id="+$(this).data('id')+'&applicationId='+$(this).data('applicationid')+'&customerId='+$(this).data('cid');
									var data = {
										"title": "用户信息-"+$(this).attr("data-name"),
										"icon": "&#xe60c;",
										"href": href
									}
									window.parent.topTab.tabAdd(data);

								});
							});
							//绑定所有'申请单详情'按钮事件						
							/*$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=detail]').on('click', function() {
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
										  content: ['application_detail.html?appSerialNumber='+$(this).data('id')], //iframe的url，no代表不显示滚动条
										});
								});
							});
							*/
						},
					});
	               
	                // 初始化加载init     end!

				});
			}	
			
			function getBanList() {
				$.ajax({
					url : getCtxPath() + "/backend/scBanControl/getAll.do",
					method : "post",
					dataType : "json",
					data : {
					},
					success : function(data) {
						var arr = [];
						arr.push('<select name="banCode" id = "banCode" class="layui-input" lay-search=""><option value="">全部</option>');
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
			}
			
			
 			