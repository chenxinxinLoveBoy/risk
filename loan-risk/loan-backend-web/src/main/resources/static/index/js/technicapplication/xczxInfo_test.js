			layui.config({
				base : '../../js/'
			});

			var applicationId = ""
			
			layui.use([ 'paging', 'layer', 'form', 'element' ],
			function() {
				var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
				layer = layui.layer, // 获取当前窗口的layer对象
				form = layui.form(), element = layui.element();
			});
			
			//初始化加载startTime
			$(function(){
				//我们强烈推荐你在代码最外层把需要用到的模块先加载
				layui.use(['layer', 'form', 'element','laydate'], function(){
					  var layer = layui.layer
					  ,form = layui.form()
					  ,element = layui.element();
					  form.render(); //刷新全部
					  getJiuYiList();
				});
			});
			
			function pu(s) {
		        return s < 10 ? '0' + s: s;
		    }
			function formatDate(time)
			{ 
				var now=new Date(time); 
				var year=now.getFullYear(); 
				var month=now.getMonth()+1; 
				var date=now.getDate(); 
				var hour=now.getHours(); 
				var minute=now.getMinutes(); 
				var second=now.getSeconds(); 
				return year+"-"+pu(month)+"-"+pu(date)+" "+pu(hour)+":"+pu(minute)+":"+pu(second); 
			} 
			// 当前菜单名称
			
			function getJiuYiList(){
				$.ajax({//查询基本信息
						//url :"http://shandai-real.oss-cn-shanghai.aliyuncs.com/xczx/20170830/94500d3cc0a5926b2509ef04f64fdbe3.json",
						url:getCtxPath() + "/backend/mongo/getMongoDisplay.do?v=" + new Date().getTime(),
						method: "post",
						dataType: "json",
						data: { 
							applicationID:"fb34dcfdfd5a4a338bbc439e461bhxf5",
							taskType: "07001" 
						},
						success: function(data){
							if(data!= null){  
			                    var clmxidTable = $("#content").html();
			                    // 获取RiskCode对象
			                    var clInfoHtml = clmxidTable + "<tr>" +
			                        "<th>申请单编号</th>" +  
			                        "<th>借款申请扩展表编号</th>" +
			                        "<th>GUID</th>" +  
			                        "<th>借款类型</th>" +
			                        "<th>借款状态</th>" + 
			                        "<th>合同金额 (单位:万元)</th>" +
			                        "<th>批贷期数</th>" + 
			                        "<th>还款状态</th>" +
			                        "<th>欠款金额</th>" + 
			                        "<th>公司代码</th>" +
			                        "<th>创建时间</th>" + 
			                        "</tr>";
			                        $.each(data.list.data.loanInfos, function(k, v){
			                        	var borrowType="";
			                        	var borrowState="";
			                        	var borrowAmount="";
			                        	var repayState="";
			                        	if(v.borrowType == 0)borrowType="未知";
			                        	else if(v.borrowType == 1)borrowType="个人信贷";			                
			                        	else if(v.borrowType == 2)borrowType="个人抵押";
			                        	else if(v.borrowType == 3)borrowType="企业信贷";
			                        	else if(v.borrowType == 4)borrowType="企业抵押";
			                        	else borrowType="未知项";
			                        	
			                        	if(v.borrowState == 0)borrowState="未知";
			                        	else if(v.borrowState == 1)borrowState="拒绝";			                
			                        	else if(v.borrowState == 2)borrowState="批贷已放款";
			                        	else if(v.borrowState == 4)borrowState="借款人放弃申请";
			                        	else if(v.borrowState == 5)borrowState="审核中";
			                        	else if(v.borrowState == 6)borrowState="待还款";
			                        	else borrowState="未知项";
			                        	
			                        	if(v.borrowAmount == 0)borrowAmount="未知";
			                        	else if(v.borrowAmount == 1)borrowAmount="[1,2)";			                
			                        	else if(v.borrowAmount == 2)borrowAmount="[2,4)";
			                        	else if(v.borrowAmount == 3)borrowAmount="[4,6)";
			                        	else if(v.borrowAmount == 4)borrowAmount="[6,8)";
			                        	else if(v.borrowAmount == 5)borrowAmount="[8,10)";
			                        	else if(v.borrowAmount == -1)borrowAmount="[0.8,1)";
			                        	else if(v.borrowAmount == -2)borrowAmount="[0.6,0.8)";			                
			                        	else if(v.borrowAmount == -3)borrowAmount="[0.4,0.6)";
			                        	else if(v.borrowAmount == -4)borrowAmount="[0.3,0.4)";
			                        	else if(v.borrowAmount == -5)borrowAmount="[0.2,0.3)";
			                        	else if(v.borrowAmount == -6)borrowAmount="[0.1,0.2)";
			                        	else if(v.borrowAmount == -7)borrowAmount="[0,0.1)";
			                        	else borrowAmount="未知项";
			                        	
			                        	if(v.repayState == 0)repayState="未知";
			                        	else if(v.repayState == 1)repayState="正常";			                
			                        	else if(v.repayState == 2)repayState="M1";
			                        	else if(v.repayState == 3)repayState="M2";
			                        	else if(v.repayState == 4)repayState="M3";
			                        	else if(v.repayState == 5)repayState="M4";
			                        	else if(v.repayState == 6)repayState="M5";
			                        	else if(v.repayState ==7)repayState="M6";			                
			                        	else if(v.repayState ==8)repayState="M6+";
			                        	else if(v.repayState ==9)repayState="已还清";
			                        	else repayState="未知项";
			                        	
			                        	
			                        	
			                        	clInfoHtml += "<tr>" + 
			                                "<td align='center' style='white-space: nowrap;'>" + data.list.data.trxNo + "</td>" +    
			                                "<td align='center' style='white-space: nowrap;'>" +" "+ "</td>" + 
			                                "<td align='center' style='white-space: nowrap;'>" +" "+ "</td>" + 
			                                "<td align='center' style='white-space: nowrap;'>" + borrowType + "</td>" +    
			                                "<td align='center' style='white-space: nowrap;'>" + borrowState + "</td>" + 
			                                "<td align='center' style='white-space: nowrap;'>" + borrowAmount + "</td>" + 
			                                "<td align='center' style='white-space: nowrap;'>" + v.loanPeriod + "</td>" +    
			                                "<td align='center' style='white-space: nowrap;'>" + repayState + "</td>" + 
			                                "<td align='center' style='white-space: nowrap;'>" + v.arrearsAmount + "</td>" + 
			                                "<td align='center' style='white-space: nowrap;'>" + v.companyCode + "</td>" + 
			                                "<td align='center' style='white-space: nowrap;'>" +formatDate(v.contractDate)+ "</td>" + 
			                                "</tr>";
			                        });
			                        $("#content").html(clInfoHtml);
							}else{
			                 clInfoHtml= $("#content").html() +'<tr>'+ 
			                    "<th>申请单编号</th>" +  
		                        "<th>借款申请扩展表编号</th>" +
		                        "<th>GUID</th>" +  
		                        "<th>借款类型</th>" +
		                        "<th>借款状态</th>" + 
		                        "<th>合同金额 (单位:万元)</th>" +
		                        "<th>批贷期数</th>" + 
		                        "<th>还款状态</th>" +
		                        "<th>欠款金额</th>" + 
		                        "<th>公司代码</th>" +
		                        "<th>创建时间</th>" + 
				             + '</tr>';
				             $("#content").html(clInfoHtml);
						}
						},
						error: function(){
								layer.msg("错误" , {icon: 2});//提示信息
						},
						complete: function(){ 
						} 
					});
			}	 
			var menuNames = "增强版信息审核列表";
			function getList(_pageIndex){
				 $("#check").removeAttr("checked");//取消全选   
				//局部刷新回调获取参数,
				var isFirst = 1;
				var applicationId = getQueryString("applicationId");//获取页面传过来的申请单编号 
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
	                    url: getCtxPath() + "/backend/credit/getDataInfo.do?v=" + new Date().getTime(),// 请求服务器的url
						elem: '#content', //内容容器
						type: 'post',
						tempElem: '#tpl', //模块容器
						params: {
							buApplicationId:applicationId
						},
						pageConfig: { //分页参数配置
							elem: '#paged', //分页容器
							pageSize: 10 //分页大小，当前页面显示的条数
						},
						success: function() { //渲染成功的回调
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
						}
					});
				});
			}	 
 			