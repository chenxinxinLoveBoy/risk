layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
			layer = layui.layer, // 获取当前窗口的layer对象
			form = layui.form(), element = layui.element();
			var flag = getQueryString("flag");
			var buApplicationId = getQueryString("applicationId");
			var platformId = getQueryString("id");//获取页面传过来的平台用户编号
			// 页面初始化加载		start
			//var buApplicationId = '3de4f32408b04c339e568329e2e1baec';
			
			if(flag == 0){
				$("#jsonPg").show();
				$("#dataBasePg").hide();
				$.ajax({/*宜信风险项记录*/
					url : getCtxPath() + "/backend/yxReport/getYxJsonReport.do?v=" + new Date().getTime(),
					method : "post",
					dataType : "json",
					data : {
						platformId : platformId,
						buApplicationId : buApplicationId
					},
					success : function(data) {
						if(data.list.data == null ) return;	
						if(data.list.data.jsonInfo.errorCode == "0001"){
							var str="";
							str+="<tr id=\"show_tab_tr\" style=\"height:400px\"><td colspan='6' align='center' style=\"font-size:25px;color:#8F8F8F\">查询成功无结果</td></tr>"
							$("#content").html(str);
						}
						if(data.list.data.jsonInfo.errorCode == "0000"){
							var mainData=data.list.data.jsonInfo.params.data;
							var riskResults=mainData.riskResults == undefined?new Array():mainData.riskResults;//宜信风险项记录
							var str="";
							var count=riskResults.length;
							for (var int = 0; int < riskResults.length; int++) {
								var riskItemTypeCode=riskResults[int].riskItemTypeCode;
								var riskTypeCode=riskResults[int].riskTypeCode;
								if(riskItemTypeCode=='101010') riskItemTypeCode='证件号码';
								if(riskTypeCode=='11'){
									riskTypeCode='伪冒类';
								}else if(riskTypeCode=='12'){
									riskTypeCode='资料虚假类';
								}else if(riskTypeCode=='10'){
									riskTypeCode='丧失还款类';
								}else if(riskTypeCode=='13'){
									riskTypeCode='用途虚假类';
								}else if(riskTypeCode=='19'){
									riskTypeCode='其他';
								}
								str+="<tr id=\"show_tab_tr\"><td>"+riskResults[int].orgName+"</td>" +
										"<td>" +riskItemTypeCode+"</td>"+
										"<td>" +riskResults[int].riskItemValue+"</td>"+
										"<td>" +riskTypeCode+"</td>"+
										"<td>" +riskResults[int].riskDetail+"</td>"+
										"<td>" +riskResults[int].riskTime+"</td>"+
										"</tr>"
							}
							if(count == 0){
								str+="<tr id=\"show_tab_tr\" style=\"height:400px\"><td colspan='6' align='center' style=\"font-size:25px;color:#8F8F8F\">无记录</td></tr>"
								$("#page").html("<font color='red'>"+count+"</font>");
							}
							$("#content").html(str);
							getPage(count);
						}
						
						
					},
					error : function() {
						//alert("获取宜信信息失败,请尝试重新获取！");
					}
				});
			}else if(flag == 1){
				$("#jsonPg").hide();
				$("#dataBasePg").show();
				paging.init({/*宜信风险项记录*/
	                openWait: true,
					url : getCtxPath() + "/backend/yxReport/getYxRiskResultsListById.do?v=" + new Date().getTime(),// 请求服务器的url
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
					success: function() { //渲染成功的回调
					},
					fail: function(msg) { //获取数据失败的回调
						layer.msg("获取数据失败！");
					},
					complate: function() { //完成的回调 
					}
					
	            });
			}else if(flag == 2){
				$("#jsonPg").show();
				$("#dataBasePg").hide();
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
						if(data.code == 200){
							if(data.data == null ) return;
							if(data.data.info == null ) return;
							var tempJson=eval('('+data.data.info+')');
							if(tempJson.errorCode == "0001"){
								var str="";
								str+="<tr id=\"show_tab_tr\" style=\"height:400px\"><td colspan='6' align='center' style=\"font-size:25px;color:#8F8F8F\">查询成功无结果</td></tr>"
								$("#content").html(str);
							}
							if(tempJson.errorCode == "0000"){
								var mainData=tempJson.params.data;
								var riskResults=mainData.riskResults == undefined?new Array():mainData.riskResults;//宜信风险项记录
								var str="";
								var count=riskResults.length;
								for (var int = 0; int < riskResults.length; int++) {
									var riskItemTypeCode=riskResults[int].riskItemTypeCode;
									var riskTypeCode=riskResults[int].riskTypeCode;
									if(riskItemTypeCode=='101010') riskItemTypeCode='证件号码';
									if(riskTypeCode=='11'){
										riskTypeCode='伪冒类';
									}else if(riskTypeCode=='12'){
										riskTypeCode='资料虚假类';
									}else if(riskTypeCode=='10'){
										riskTypeCode='丧失还款类';
									}else if(riskTypeCode=='13'){
										riskTypeCode='用途虚假类';
									}else if(riskTypeCode=='19'){
										riskTypeCode='其他';
									}
									str+="<tr id=\"show_tab_tr\"><td>"+riskResults[int].orgName+"</td>" +
											"<td>" +riskItemTypeCode+"</td>"+
											"<td>" +riskResults[int].riskItemValue+"</td>"+
											"<td>" +riskTypeCode+"</td>"+
											"<td>" +riskResults[int].riskDetail+"</td>"+
											"<td>" +riskResults[int].riskTime+"</td>"+
											"</tr>"
								}
								if(count == 0){
									str+="<tr id=\"show_tab_tr\" style=\"height:400px\"><td colspan='6' align='center' style=\"font-size:25px;color:#8F8F8F\">无记录</td></tr>"
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