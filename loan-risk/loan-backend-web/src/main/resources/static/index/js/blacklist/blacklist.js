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
					listAllSum();
					getList();
				});
			})
			function reset(){//重置
				layui.use(['form'], function() {
					document.getElementById("rejectType").options[0].selected = true;
					document.getElementById("dsSource").options[0].selected = true;
					layui.form().render('select');//征信机构  下拉框需要重新渲染
				});
				$("#certCode").val("");//借款人身份证号
				$("#name").val("");//借款人姓名
				$("#phoneNum").val("");//借款人手机号
				$("#raiseReason").val("");
				$("#startTime").val(layui.laydate.now(0,'YYYY-MM-DD 00:00:00'));
				$("#endTime").val(layui.laydate.now(0,'YYYY-MM-DD 23:59:59'));
			}
			
			function sel() {
				var certCode = $("#certCode").val().trim();//借款人身份证号
				var name = $("#name").val().trim();//借款人姓名
				var phoneNum = $("#phoneNum").val().trim();//借款人手机号
				var rejectType = $("#rejectType").val().trim();//征信机构 
				var certCode = $("#certCode").val();
				if(certCode != '' && certCode != undefined){ 
					var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
					if(reg.test(certCode) === false){  
						parent.layer.msg("身份证号输入不正确!" , {icon: 2});
						return false;  
					}
				}; 
				if(phoneNum != '' && phoneNum != undefined){
					if(!(/^1[34578]\d{9}$/.test(phoneNum))){ 
						parent.layer.msg("手机号码有误，请重新输入!" , {icon: 2}); 
				        return false; 
				    }
				};    
				if(name != '' && name != undefined){
					if(!(/^[A-Za-z0-9\u4e00-\u9fa5]+$/.test(name))){ 
						parent.layer.msg("用户名输入有误，请重新输入!" , {icon: 2});
				        return false; 
				    }
				};
				getList(); 
			}; 
			function listAllSum(){
				$.ajax({
					url : hostIp+ "/blacklist/listAllSum.do?v=" + new Date().getTime(),// 请求服务器的url
					method: "post",
					dataType:"json",
					success: function(data){
						if(data.msg="获取成功"){
							$("#listAllSum").html("黑名单库总数为："+data.count+"条");
						}
					},
					error: function(){  
 						layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
					} 
				});
			}
			function refreshRedis(){//将MySql中的 黑名单数据刷新进Redis缓存
				layer.msg("刷新中...");//提示信息
				$.ajax({
					url : hostIp+ "/blacklist/refreshRedis.do?v=" + new Date().getTime(),// 请求服务器的url
					method: "post",
					dataType:"json",
					data: {
						certCode: ""
					},
					success: function(data){
						//console.log(data);
						if(data.list.data == true){
							layer.msg("刷新成功！" , {icon: 1});//提示信息
						}else{
							layer.msg("刷新失败！" , {icon: 2});//提示信息
						}
					},
					error: function(){  
 						layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
					} 
				});
			}
			  function del() {  
					var ids = ''; 
					$('#content').children('tr').each(function() {
						var $that = $(this);
						var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;
						if($cbx) {
							var n = $that.children('td').eq(0).children('input[type=checkbox]').data('did'); 
							 
							ids += n + ','; 
						} 
					});
					if(ids == '' || ids == undefined){ // 验空 
						layer.msg('请选择要删除的数据！',{icon: 2});
						return false;
					}    
				if(confirm('您确定要删除数据？')){  
					$.ajax({
						url : hostIp+ "/blacklist/delete.do?v=" + new Date().getTime(),// 请求服务器的url
						method: "post",
						dataType:"json",
						data: {
							ids: ids
						},
						success: function(data){
							if(data.code == 3001){ 
								layer.msg("删除成功" , {icon: 1});
							}else{
								layer.msg("删除失败" , {icon: 2});
							}
							getList();
							listAllSum();
						},
						error: function(){  
	 						layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
						} 
					});
				} 

			  }
			function getList(){
				var certCode = $("#certCode").val().trim();//借款人身份证号
				var name = $("#name").val().trim();//借款人姓名
				var phoneNum = $("#phoneNum").val().trim();//借款人手机号
				var rejectType = $("#rejectType").val().trim();//征信机构
				var startTime = $("#startTime").val();
				var endTime = $("#endTime").val();
				var raiseReason=$("#raiseReason").val();//发生原因
				var dsSource=$("#dsSource").val().trim();
				if('00' == rejectType){
					rejectType ='';
				}
				if('00' == dsSource){
					dsSource ='';
				}
				layui.use(['paging', 'layer', 'form', 'element','laydate'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form(),
						element = layui.element();
					// 页面初始化加载		start
	                paging.init({
	                    openWait: true,
						url: hostIp + "/blacklist/index.do?v=" + new Date().getTime(),// 请求服务器的url
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							certCode : certCode,
							rejectType : rejectType,
							name : name,
							phoneNum : phoneNum,
							startTime : startTime,
							endTime : endTime,
							banCode:raiseReason,
							dsSource:dsSource
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
	
							//绑定所有'修改'按钮事件						
							/*$('#content').children('tr').each(function() { 
 								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=find]').on('click', function() {
									if(confirm('确定要修改吗？')){ //只有当点击confirm框的确定时，该层才会关闭
										$.ajax({
											url : hostIp  + "/backend/blacklist/update.do",
											method : 'post',
											dataType : 'json',
											data: {
												blacklistId : $(this).data('id')// 传到后台的参数，子窗口的form表单
											},
											success : function(data) {
												layerTips.msg(data.message);// 提示信息
											},
											error: function(){
												layerTips.msg(data.message);// 提示信息
											},
											complete: function(){
												location.reload(); //刷新
												getList();
											}
											
										});
									  }
									  return false;  
								});
	
							});*/
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td').children('a[data-opt=find]').on('click', function() {
									var href = getCtxPath() + "/index/views/customerManager/customerInfo.html?id="+$(this).data('id');
									var data = {
										"title": "用户信息-"+$(this).attr("data-name"),
										"icon": "&#xe60c;",
										"href": href
									}
									window.parent.topTab.tabAdd(data);
								});
							});
						}						
					});
	                 
				});
				
			}
			