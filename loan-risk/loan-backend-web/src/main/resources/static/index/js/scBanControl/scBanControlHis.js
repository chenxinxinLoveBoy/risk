			layui.config({
				base: '../../js/' 
			});
			
			var url = getCtxPath() + "/backend/scBanControlHis/getAllScBanControlHis.do?v=" + new Date().getTime();// 请求服务器的url
			// 初始化
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
				})
				
				$.ajax({
					url : getCtxPath() + "/backend/sctemplate/templatelistAll.do?v=" + new Date().getTime(),
					method : "post",
					dataType : "json",
					data : {
						pageIndex:0,
						pageSize:100
					},
					success : function(data) {
						var arr = [];
						arr.push('<select name="tplId" id = "tplId" class="layui-input" lay-search="">');
						arr.push('<option value="00">全部</option>');
						layui.each(data.list, function(index, item){
								if(index === 0){
							    	arr.push('<option value="'+ item.banControlTplId + '" selected>' + item.banTplName + '</option>');
								}else{
							    	arr.push('<option value="'+ item.banControlTplId + '">' + item.banTplName + '</option>');
								}
						    });
						arr.push('</select>');
						$('#tplIdContent').html(arr.join(''));
						getList();
						form.render('select'); //刷新全部
					},
					error : function() {
						alert("获取用户信息失败,请尝试重新获取！");
					}
				});

			})
			
			// 条件搜索
			$('#search').on('click', function() {
				getList();
			});
			
			function getList(){
			 	var banCode = $("#banCode").val().trim();
			 	var ruleName = $("#ruleName").val().trim();
				var creditType = $("#creditType").val().trim();
				if(creditType=='00'){
					creditType=''
				}
				var ifRefuse = $("#ifRefuse").val().trim();
				if(ifRefuse=='00'){
					ifRefuse=''
				}
				var state = $("#state").val().trim();
				if(state=='00'){
					state=''
				}
				var tplId = $("#tplId").val().trim(); 
				if(tplId=='00'){
					tplId=''
				}
				var startTimeInterval =  $("#startTime").val();
				var endTimeInterval =  $("#endTime").val();
				layui.use(['paging', 'layer', 'form', 'element'], function() {
					var $ = layui.jquery,
						paging = layui.paging(),
						layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
						layer = layui.layer, //获取当前窗口的layer对象
						form = layui.form(),
						element = layui.element();
						form.render(); //重新渲染
	
					// 页面初始化加载		start
	                paging.init({
	                    openWait: true,
						url: url,
						elem: '#content', //内容容器
						params: { //发送到服务端的参数
							t: Math.random(),
//							state: types == 1 ? "" :state,
							banCode:banCode,
							ruleName:ruleName,
							creditType:creditType,
							ifRefuse:ifRefuse,
							banControlTplId:tplId,
							state:state,
							startTimeInterval : startTimeInterval,
							endTimeInterval : endTimeInterval
							
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
							//绑定所有'编辑'按钮事件						
							$('#content').children('tr').each(function() {
								var $that = $(this);
								$that.children('td:last-child').children('a[data-opt=edit]').on('click', function() {
									 
									layer.open({
										  type: 2,
										  title: '预览',// 设置false表示不显示
										  closeBtn: 1, //不显示关闭按钮
										  shade: 0.4, //遮罩透明度
										  area: ['840px', '550px'],
										  skin: 'layui-layer-rim', //加上边框
										  fixed: false, //不固定
										  maxmin: true, // 允许屏幕最小化
	//									  offset: 'rb', //右下角弹出
	//									  time: 2000, //2秒后自动关闭
										  anim: 2,
										  content: ['scBanControlHis_add.html?id='+$(this).data('id')], //iframe的url，no代表不显示滚动条
										});
								});
	
							});
						},
					});
					
				});
			 	 
			}
 			
 			