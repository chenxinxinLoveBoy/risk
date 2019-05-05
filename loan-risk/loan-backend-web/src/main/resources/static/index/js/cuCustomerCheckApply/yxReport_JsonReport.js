layui.config({
	base : '../../js/'
});
function convertToNull(value)
{
	if(value==null||typeof(value)=="undefined") 
	{
		return "";
	}
	else
	{
		return value;
	}
}
layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
			layer = layui.layer, // 获取当前窗口的layer对象
			form = layui.form(), element = layui.element();
			var customerCheckApplyId = getQueryString("id");//获取页面传过来的数据测试单号
			$("#jsonPg").show();
			$("#dataBasePg").hide();
			//$("#history").prop("src",getCtxPath()+'/index/views/customerManager/yxReport_history_JsonReport.html?applicationId='+buApplicationId+'&id='+platformId+"&flag=0");
			//$("#risk").prop("src",getCtxPath()+'/index/views/customerManager/yxReport_risk_JsonReport.html?applicationId='+buApplicationId+'&id='+platformId+"&flag=0");
			$.ajax({/*宜信主信息查询*/
				url : getCtxPath() + "/backend/cuCustomerCheckApply/getDataReportByMongo.do?v=" + new Date().getTime(),
				method : "post",
				dataType : "json",
				data : {
					customerCheckApplyId : customerCheckApplyId,
					taskType : "09001"
				},
				success : function(data) {
					if(data.code == 200 ){
						if(data.list.data == null ) return;	
						if(data.list.data.list.errorCode=="0001"){
							var str="";
							str+="<tr id=\"show_tab_tr\" style=\"height:400px\"><td colspan='13' align='center' style=\"font-size:25px;color:#8F8F8F\">查询成功无结果</td></tr>"
							$("#content").html(str);
							var mainstr="";
							mainstr+="<tr ><td colspan='7' align='center' style=\"font-size:18px;color:#8F8F8F\">查询成功无结果</td></tr>"
							$("#mainContent").html(mainstr);	
						}
						//if(data.list.data.jsonInfo.errorCode == "0000"){
							var mainData=data.list.data.list.params.data;
							//$("#buApplicationId_Data").html(data.list.data.applicationID);
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
								str+="<tr ><td>"+convertToNull(loanRecords[int].orgName)+"</td>" +
										"<td>" +convertToNull(loanRecords[int].name)+"</td>"+
										"<td>" +convertToNull(loanRecords[int].certNo)+"</td>"+
										"<td>" +convertToNull(loanRecords[int].loanDate)+"</td>"+
										"<td>" +convertToNull(loanRecords[int].periods)+"</td>"+
										"<td>" +convertToNull(loanRecords[int].loanAmount)+"</td>"+
										////"<td>" +loanRecords[int].loanStatusCode+"</td>"+
										"<td>" +convertToNull(loanStatus)+"</td>"+
										"<td>" +convertToNull(loanTypeCode)+"</td>"+
										"<td>" +convertToNull(loanRecords[int].overdueAmount)+"</td>"+
										"<td>" +convertToNull(loanRecords[int].overdueStatus)+"</td>"+
										"<td>" +convertToNull(loanRecords[int].overdueTotal)+"</td>"+
										"<td>" +convertToNull(loanRecords[int].overdueM3)+"</td>"+
										"<td>" +convertToNull(loanRecords[int].overdueM6)+"</td>"+
										"</tr>"
								
							}
							if(count == 0){
								str+="<tr id=\"show_tab_tr\" style=\"height:400px\"><td colspan='13' align='center' style=\"font-size:25px;color:#8F8F8F\">无记录</td></tr>"
								$("#page").html("<font color='red'>"+count+"</font>");
							}
							$("#content").html(str);
							getPage(count);
						}
					//}
					
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