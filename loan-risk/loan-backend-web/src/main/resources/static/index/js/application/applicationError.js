			layui.config({
				base: '../../js/'
			});
			
			var banCode = "", certCode = "", startTime = "", endTime = "",
			source = "", appSerialNumber = "", auditingState = "", currPage = "",
			name = "", phoneNum = "", isPushApp = "", isStep = "", errorDescription = "1", appLevel='',appChannel='',isHbaseSyn='',
			flag = 0;

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
					  $("#startTime").val(laydate.now(-3,'YYYY-MM-DD 00:00:00'));
					  $("#startTime").on("click", function(){
					    start.elem = this;
					    laydate(start);
					    if(laydate.now(0,'YYYY-MM-DD hh:mm:ss') == $(this).val().trim()){
						    $(this).val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
					    }
					  });
					  $("#endTime").val(laydate.now(0,'YYYY-MM-DD 23:59:59'));
					  $("#endTime").on("click", function(){
					    end.elem = this
					    laydate(end);
					  });
					  startTime = $("#startTime").val();
					  endTime = $("#endTime").val();
					  getList();
				}); 
			})
			
			// 搜索
			$('#search').on('click', function() {
				banCode = $("#banCode").val().trim();
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
				isPushApp = $("#isPushApp").val(); 
				if(isPushApp=='00'){
					isPushApp='';
				}
				if(appLevel=='00'){
					appLevel='';
				}
				appLevel = $("#appLevel").val(); 
				if(appLevel=='00'){
					appLevel='';
				}
				appChannel = $("#appChannel").val(); 
				isHbaseSyn = $("#isHbaseSyn").val(); //是否同步大数据
				if(isHbaseSyn=='00'){
					isHbaseSyn='';
				}
				
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
							isPushApp : isPushApp,
							errorDescription : errorDescription,
							auditingState : auditingState ,
							appLevel : appLevel, //客户标识
							appChannel : appChannel,  //app下载渠道
							isHbaseSyn : isHbaseSyn
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
							//绑定所有'申请单详情'按钮事件						
							$('#content').children('tr').each(function() {
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
										  content: ['application_manage.html?appSerialNumber='+$(this).data('id')], //iframe的url，no代表不显示滚动条
										});
								});
							});
							
						},
					});
	               
	                // 初始化加载init     end!

				});
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
			
			
 			