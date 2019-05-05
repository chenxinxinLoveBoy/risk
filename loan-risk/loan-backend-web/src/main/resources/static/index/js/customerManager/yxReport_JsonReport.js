layui.config({
	base : '../../js/'
});
var buApplicationId='';
var platformId='';
layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
			layer = layui.layer, // 获取当前窗口的layer对象
			form = layui.form(), element = layui.element();
			buApplicationId = getQueryString("applicationId");
			platformId = getQueryString("id");//获取页面传过来的平台用户编号
			if(buApplicationId == "" && platformId == ""){
				buApplicationId="-1";
				platformId="-1";
			}
			//var mainURL="http://h5.xnsudai5.com/yx/20170824/1dd446dde690c56884580b6ca1ae19b9.json";
			//var mainURL="http://h5.xnsudai5.com/yx/20170818/c8cae09077a8c21ce604688c8af13c06.json";
			$("#jsonPg").show();
			$("#dataBasePg").hide();
			$("#history").prop("src",getCtxPath()+'/index/views/customerManager/yxReport_history_JsonReport.html?applicationId='+buApplicationId+'&id='+platformId+"&flag=0");
			$("#risk").prop("src",getCtxPath()+'/index/views/customerManager/yxReport_risk_JsonReport.html?applicationId='+buApplicationId+'&id='+platformId+"&flag=0");
			$.ajax({/*宜信主信息查询*/
				url : getCtxPath() + "/backend/yxReport/getYxJsonReport.do?v=" + new Date().getTime(),
				method : "post",
				dataType : "json",
				data : {
					platformId : platformId,
					buApplicationId : buApplicationId
				},
				success : function(data) {
					if(data.code == 200 ){
						if(data.list.data == null ) return;	
						if(data.list.data.jsonInfo.errorCode=="0001"){
							var str="";
							str+="<tr id=\"show_tab_tr\" style=\"height:400px\"><td colspan='13' align='center' style=\"font-size:25px;color:#8F8F8F\">查询成功无结果</td></tr>"
							$("#content").html(str);
							var mainstr="";
							mainstr+="<tr ><td colspan='7' align='center' style=\"font-size:18px;color:#8F8F8F\">查询成功无结果</td></tr>"
							$("#mainContent").html(mainstr);	
						}
						if(data.list.data.jsonInfo.errorCode == "0000"){
							var mainData=data.list.data.jsonInfo.params.data;
							$("#buApplicationId_Data").html(data.list.data.applicationID);
							$("#flowId").html(mainData.flowId);
							$("#zcCreditScore").html(mainData.zcCreditScore);
							$("#contractBreakRate").html(mainData.contractBreakRate);
							$("#timesByOtherOrg").html(mainData.queryStatistics.timesByOtherOrg);
							$("#otherOrgCount").html(mainData.queryStatistics.otherOrgCount);
							$("#timesByCurrentOrg").html(mainData.queryStatistics.timesByCurrentOrg);
							
							var loanRecords=mainData.loanRecords == undefined?new Array():mainData.loanRecords;//宜信借款记录
							var str="";
							var count=loanRecords.length;
							for (var int = 0; int < loanRecords.length; int++) {
								var loanStatus="";
								if(loanRecords[int].loanStatusCode == 301){
									loanStatus="正常";
								}else if(loanRecords[int].loanStatusCode == 302){
									loanStatus="逾期";
								}else if(loanRecords[int].loanStatusCode == 303){
									loanStatus="结清";
								}
								var loanTypeCode="";
								if(loanRecords[int].loanTypeCode == 21){
									loanTypeCode="信用";
								}else if(loanRecords[int].loanTypeCode == 22){
									loanTypeCode="抵押";
								}else if(loanRecords[int].loanTypeCode == 23){
									loanTypeCode="担保";
								}
								str+="<tr ><td>"+loanRecords[int].orgName+"</td>" +
										"<td>" +loanRecords[int].name+"</td>"+
										"<td>" +loanRecords[int].certNo+"</td>"+
										"<td>" +loanRecords[int].loanDate+"</td>"+
										"<td>" +loanRecords[int].periods+"</td>"+
										"<td>" +loanRecords[int].loanAmount+"</td>"+
										////"<td>" +loanRecords[int].loanStatusCode+"</td>"+
										"<td>" +loanStatus+"</td>"+
										"<td>" +loanTypeCode+"</td>"+
										"<td>" +loanRecords[int].overdueAmount+"</td>"+
										"<td>" +loanRecords[int].overdueStatus+"</td>"+
										"<td>" +loanRecords[int].overdueTotal+"</td>"+
										"<td>" +loanRecords[int].overdueM3+"</td>"+
										"<td>" +loanRecords[int].overdueM6+"</td>"+
										"</tr>"
								
							}
							if(count == 0){
								str+="<tr id=\"show_tab_tr\" style=\"height:400px\"><td colspan='13' align='center' style=\"font-size:25px;color:#8F8F8F\">无记录</td></tr>"
								$("#page").html("<font color='red'>"+count+"</font>");
							}
							$("#content").html(str);
							getPage(count);
						}
					}
					
				},
				error : function() {
					//alert("获取宜信信息失败,请尝试重新获取！");
				}
			});
			
		});
function getPage(count){
	/********************************/
	var pageSize=10;  //每页显示的记录条数   
	var curPage=1;   //显示第curPage页
	var len;         //总行数
	var page;        //总页数
	if(count != 0){
		
		len =$("#show_tab tr").length-1;   //去掉表头     
		page=len % pageSize==0 ? len/pageSize : Math.floor(len/pageSize)+1;//根据记录条数，计算页数
		document.getElementById("page").value=page;
		curPage=1;
		displayPage(count);//显示第一页
	}
	$("#nextpage").click(function(){//下一页
	    if(curPage<page){
	        curPage+=1;
	    }
	    else{
	    	//提示信息
	    }
	   displayPage(count);
	   });
	$("#lastpage").click(function(){//上一页
	    if(curPage>1){
	        curPage-=1;
	    }
	    else{
	    	//提示信息
	    }
	    displayPage(count);
	    });
	$("#npage").click(function(){//跳到固定某一页
	    var npage=parseInt(document.getElementById("curPage").value);
	    if(npage>page||npage<1){
	        //提示信息
	    }
	    else{
	        curPage=npage;
	    }
	    displayPage(count);
	 });
	function displayPage(count){  
	    var begin=(curPage-1)*pageSize;//起始记录号
	    var end = begin + pageSize;
	    if(end > len ) end=len;
	    if(count == 0) return;
	    $("#show_tab tr").hide();
	    $("#show_tab tr").each(function(i){
	        if(i-1>=begin && i-1<end)//显示第page页的记录
	            {
	            $("#show_tab_one").show();
	            $(this).show();
	            	document.getElementById("curPage").value=curPage;
	            	$("#page").html("<font color='red'>"+count+"</font>");
	            }         
    });

	} 
	/********************************/
}
/**
 * 2017-04-28 00:00:00.0 -> 2017-04-28 00:00:00
 */
function getDateStrFormat(s){
	if(s!==undefined && s.length>19){
		return s.substring(0,19);
	}
	return s
}
/******************************************************************************************************************************************************************************************/
function showDataBase(){
	$("#jsonPg").hide();
	$("#dataBasePg").show();
	layui.use([ 'paging', 'layer', 'form', 'element' ],
			function() {
				var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
				layer = layui.layer, // 获取当前窗口的layer对象
				form = layui.form(), element = layui.element();
				buApplicationId = getQueryString("applicationId");
				platformId = getQueryString("id");//获取页面传过来的平台用户编号
				$("#history").prop("src",getCtxPath()+'/index/views/customerManager/yxReport_history_JsonReport.html?applicationId='+buApplicationId+'&id='+platformId+"&flag=1");
				$("#risk").prop("src",getCtxPath()+'/index/views/customerManager/yxReport_risk_JsonReport.html?applicationId='+buApplicationId+'&id='+platformId+"&flag=1");
				// 页面初始化加载		start
	            paging.init({/*宜信借款记录查询*/
	                openWait: true,
					url : getCtxPath() + "/backend/yxReport/findListById.do?v=" + new Date().getTime(),// 请求服务器的url
					elem: '#content', //内容容器
					params: { //发送到服务端的参数
						platformId:platformId,
						buApplicationId : buApplicationId
					},
					type: 'post',
					tempElem: '#tpl', //模块容器
					pageConfig: { //分页参数配置
						elem: '#paged', //分页容器
						pageSize: 10 //分页大小，当前页面显示的条数
					},
					success: function(data) { //渲染成功的回调
						
					},
					fail: function(msg) { //获取数据失败的回调
					},
					complate: function() { //完成的回调 
					}
					
	            }); 
			});
		var applicationId=buApplicationId;
		$.ajax({/*宜信主信息查询*/
					url : getCtxPath() + "/backend/yxReport/getEntityById.do?v=" + new Date().getTime(),
					method : "post",
					dataType : "json",
					data : {
						platformId:platformId,
						applicationId : applicationId
					},
					success : function(data) {
						var yxData=data.list.data;
						if(yxData==''||yxData==null||yxData==undefined){
							return;
						} 
						$("#buApplicationId_Data").html(yxData.buApplicationId);
						$("#flowId").html(yxData.flowId);
						$("#zcCreditScore").html(yxData.zcCreditScore);
						$("#contractBreakRate").html(yxData.contractBreakRate);
						$("#timesByOtherOrg").html(yxData.timesByOtherOrg);
						$("#otherOrgCount").html(yxData.otherOrgCount);
						$("#timesByCurrentOrg").html(yxData.timesByCurrentOrg);
					},
					error : function() {
						//alert("获取宜信信息失败,请尝试重新获取！");
					}
				});
		
}
/************************************************************************************************************************************************************/
function showAliyun(){
	buApplicationId = getQueryString("applicationId");
	platformId = getQueryString("id");//获取页面传过来的平台用户编号
	//buApplicationId="149a3ad9da8f4887bf8fc27f284b926b";
	if(buApplicationId == "" && platformId == ""){
		buApplicationId="-1";
		platformId="-1";
	}
	//var mainURL="http://h5.xnsudai5.com/yx/20170824/1dd446dde690c56884580b6ca1ae19b9.json";
	//var mainURL="http://h5.xnsudai5.com/yx/20170818/c8cae09077a8c21ce604688c8af13c06.json";
	$("#jsonPg").show();
	$("#dataBasePg").hide();
	$("#history").prop("src",getCtxPath()+'/index/views/customerManager/yxReport_history_JsonReport.html?applicationId='+buApplicationId+'&id='+platformId+"&flag=2");
	$("#risk").prop("src",getCtxPath()+'/index/views/customerManager/yxReport_risk_JsonReport.html?applicationId='+buApplicationId+'&id='+platformId+"&flag=2");
	$.ajax({
		url : getCtxPath() + "/backend/queryAliyunDataInfo/getInfo.do?v=" + new Date().getTime(),
		method : "post",
		dataType : "json",
		data : { 
			taskType : '09001',
			applicationId : buApplicationId,
			platformId:platformId
		},
		success : function(data) {
			if(data.code == 200 ){
				if(data.data == null ) return;
				if(data.data.info == null ) return;
				var tempJson=eval('('+data.data.info+')');
				if(tempJson.errorCode=="0001"){
					var str="";
					str+="<tr id=\"show_tab_tr\" style=\"height:400px\"><td colspan='13' align='center' style=\"font-size:25px;color:#8F8F8F\">查询成功无结果</td></tr>"
					$("#content").html(str);
					var mainstr="";
					mainstr+="<tr ><td colspan='7' align='center' style=\"font-size:18px;color:#8F8F8F\">查询成功无结果</td></tr>"
					$("#mainContent").html(mainstr);	
				}
				if(tempJson.errorCode == "0000"){
					var mainData=tempJson.params.data;
					//$("#buApplicationId_Data").html(data.data.applicationID);
					$("#flowId").html(mainData.flowId);
					$("#zcCreditScore").html(mainData.zcCreditScore);
					$("#contractBreakRate").html(mainData.contractBreakRate);
					$("#timesByOtherOrg").html(mainData.queryStatistics.timesByOtherOrg);
					$("#otherOrgCount").html(mainData.queryStatistics.otherOrgCount);
					$("#timesByCurrentOrg").html(mainData.queryStatistics.timesByCurrentOrg);
					
					var loanRecords=mainData.loanRecords == undefined?new Array():mainData.loanRecords;//宜信借款记录
					var str="";
					var count=loanRecords.length;
					for (var int = 0; int < loanRecords.length; int++) {
						var loanStatus="";
						if(loanRecords[int].loanStatusCode == 301){
							loanStatus="正常";
						}else if(loanRecords[int].loanStatusCode == 302){
							loanStatus="逾期";
						}else if(loanRecords[int].loanStatusCode == 303){
							loanStatus="结清";
						}
						var loanTypeCode="";
						if(loanRecords[int].loanTypeCode == 21){
							loanTypeCode="信用";
						}else if(loanRecords[int].loanTypeCode == 22){
							loanTypeCode="抵押";
						}else if(loanRecords[int].loanTypeCode == 23){
							loanTypeCode="担保";
						}
						str+="<tr ><td>"+loanRecords[int].orgName+"</td>" +
								"<td>" +loanRecords[int].name+"</td>"+
								"<td>" +loanRecords[int].certNo+"</td>"+
								"<td>" +loanRecords[int].loanDate+"</td>"+
								"<td>" +loanRecords[int].periods+"</td>"+
								"<td>" +loanRecords[int].loanAmount+"</td>"+
								////"<td>" +loanRecords[int].loanStatusCode+"</td>"+
								"<td>" +loanStatus+"</td>"+
								"<td>" +loanTypeCode+"</td>"+
								"<td>" +loanRecords[int].overdueAmount+"</td>"+
								"<td>" +loanRecords[int].overdueStatus+"</td>"+
								"<td>" +loanRecords[int].overdueTotal+"</td>"+
								"<td>" +loanRecords[int].overdueM3+"</td>"+
								"<td>" +loanRecords[int].overdueM6+"</td>"+
								"</tr>"
						
					}
					if(count == 0){
						str+="<tr id=\"show_tab_tr\" style=\"height:400px\"><td colspan='13' align='center' style=\"font-size:25px;color:#8F8F8F\">无记录</td></tr>"
						$("#page").html("<font color='red'>"+count+"</font>");
					}
					$("#content").html(str);
					getPage(count);
				}
			}
			
		
			
		},
		error : function() {
			layer.msg("获取阿里云数据信息失败,请尝试重新获取！");
		}
	});
}