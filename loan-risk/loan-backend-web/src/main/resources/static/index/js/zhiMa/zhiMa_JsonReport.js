layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
			layer = layui.layer, // 获取当前窗口的layer对象
			form = layui.form(), element = layui.element();
		});


var tableTemp="";
var czcwInfoTableTemp="";
var extendInfoTableTemp="";
// 初始化加载
$(function() {
//	var sURL = window.document.URL;  
//	alert(sURL);
	var platformCustomerId = getQueryString("id");// 平台用户编号
	var applicationId = getQueryString("applicationId");// 申请单编号
	if(platformCustomerId == "" && applicationId == ""){
		platformCustomerId= "-1";
		applicationId= "-1";
	}
	//platformCustomerId="0655dde098bd47fdbbeda5c8d70186f9";
	tableTemp = $("#tbody_id").html();
	extendInfoTableTemp= $("#extendInfo").html();
	czcwInfoTableTemp=$("#czcwInfo").html();
	//applicationId="0258da4988ff42569e2cc9debe4ab3c3";
	//var scoreURL="http://shandai-real.oss-cn-shanghai.aliyuncs.com/zmxypf/20170830/c667a24656036c1ddbd89e3314b53343.json";//01001 - 芝麻信用评分
	//var lieURL="http://shandai-real.oss-cn-shanghai.aliyuncs.com/zmxyqzqd/20170830/c1797f411681a51ec91d82128fb70841.json";//02001 - 芝麻信用欺诈清单
	//var watchURL="http://h5.xnsudai5.com/zmxyhygzqd/20170823/3366a39061631d6cec4aebe323c354b7.json";//40004- 芝麻信用行业关注名单
	$.ajax({//芝麻信用评分
		url: getCtxPath() + "/backend/zm/getZmScoreJsonReport.do?v=" + new Date().getTime(),
		method: "post",
		dataType: "json",
		data : {
			platformId : platformCustomerId,
			buApplicationId : applicationId
		},
		success: function(data){
			if(data.code == 200){
				if(data.list.data == null ) return;	
				var data2=data.list.data.jsonInfo;
				getScoreData(data2);
			}
		},
		error: function(){
			layer.msg("请求网络异常，请尝试重新登录！", {icon: 2});
		}
		
	});
	$.ajax({//芝麻信用欺诈清单
		url: getCtxPath() + "/backend/zm/getZmLieJsonReport.do?v=" + new Date().getTime(),
		method: "post",
		dataType: "json",
		data : {
			platformId : platformCustomerId,
			buApplicationId : applicationId
		},
		success: function(data){
			if(data.code == 200){
				var riskCodeJson;
				// 请求json文件
				$.ajax({//
					url: getCtxPath() + "/index/js/zhiMa/riskCodeJson.json",//getCtxPath() + "/backend/zm/getZMScore.do",
					method: "get",
					dataType: "json",
					success: function(riskCodeJson){
						if(data.code == 200){
							if(data.list.data == null ) return;	
							var data2=data.list.data.jsonInfo;
							getLieData(data2,riskCodeJson.riskCodeObject);
						}
						
					},
					error: function(){
						layer.msg("请求网络异常，请尝试重新登录！", {icon: 2});
					}
					
				});
				
			}
			
		},
		error: function(){
			layer.msg("请求网络异常，请尝试重新登录！", {icon: 2});
		}
		
	});
	$.ajax({//芝麻信用行业关注名单
		url: getCtxPath() + "/backend/zm/getZmWatchJsonReport.do?v=" + new Date().getTime(),
		method: "post",
		dataType: "json",
		data : {
			platformId : platformCustomerId,
			buApplicationId : applicationId
		},
		success: function(watchData){
			if(watchData.code == 200){
				// 请求json文件
				$.ajax({//
					url: getCtxPath() + "/index/js/zhiMa/udcCodeJson.json",//getCtxPath() + "/backend/zm/getZMScore.do",
					method: "get",
					dataType: "json",
					success: function(data){
						if(watchData.list.data == null ) return;	
						var watchData2=watchData.list.data.jsonInfo
						getWatchData(watchData2,data.udcObject);
					},
					error: function(){
						layer.msg("请求网络异常，请尝试重新登录！", {icon: 2});
					}
					
				});
			}
			
		},
		error: function(){
			layer.msg("请求网络异常，请尝试重新登录！", {icon: 2});
		}
		
	});
//	if(data.code == "200"){
//		
		var table = "";
//		
		var html ="";
//		
	function getLieData(data,riskCodeJson){
		// 欺诈关注清单
		if(data != null && data != undefined){
			// 拿到页面信息
			table = $("#tbody_id").html();
			var riskCode = "";
			// 获取RiskCode对象
			var listRiskCode = data.riskCode;
			html = table + "<tr style='width: 150px;'>" +
			"<th rowspan='" + (listRiskCode.length + 1) + "' style=\"width: 170px;\" >风险说明列表</th>" +
			"<td align='center'>RiskCode</td>" +
			"<td align='center'>风险说明</td>" +
			"<td align='center'>风险等级</td>" +
			"</tr>";
			for (var i = 0; i < riskCodeJson.length; i++) {
				if(listRiskCode != null){
					for (var int = 0; int < listRiskCode.length; int++) {
						if(listRiskCode[int]==riskCodeJson[i].riskCode){
							html += "<tr>" +
							"<td align='center'>" + riskCodeJson[i].riskCode + "</td>" +
							"<td align='center'>"+riskCodeJson[i].remark+"</td>" +
							"<td align='center'>"+riskCodeJson[i].suggestType+"</td>" +
							"</tr>";
						}
						
					}
				}
			}
			$("#tbody_id").html(html);
			$("#zmBizNo_qd").html(data.bizNo);// 欺诈关注清单返回的业务号
//			$("#state").html(data.data.bill.state == '1' ? "yes" : "no");// 是否命中
			$("#state").html(data.hit);
		}else{
			html= $("#tbody_id").html() +'<tr style="width: 150px;">'
			+ '<th rowspan="2" style="width: 170px;">风险说明列表</th>'
			+ '<td align="center">RiskCode</td>'
			+ '<td align="center">风险说明</td>'
			+ '<td align="center">风险等级</td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td align="center"></td>'
			+ '<td align="center"></td>'
			+ '<td align="center"></td>'
			+ '</tr>';
			$("#tbody_id").html(html);
		}
	}		
	function getScoreData(scoreData){
		// 芝麻信用评分
		if(scoreData != undefined && scoreData != null){
			$("#zmBizNo_pf").html(scoreData.bizNo);// 芝麻评分返回的业务号
			$("#zmScore").html(scoreData.zmScore);// 芝麻分数
		}
	}

    //  芝麻信用行业关注名单详细信息表
	function getWatchData(watchData,data){
		// 获取芝麻信用行业关注名单主表信息
		if(watchData != null && watchData != undefined){
			$("#isMatched").html(watchData.isMatched == false ? "false" : watchData.isMatched); //true=命中 在关注名单中 false=未命中
			$("#bizNo").html(watchData.bizNo); //芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账
			// $("#applicationId").html(data.data.listInfo.applicationId);//申请单编号
			// $("#openId").html(data.data.listInfo.openId);//openId
			// $("#transactionId").html(data.data.listInfo.transactionId);//芝麻信用行业关注名单编码，每次调用接口的UUID流水号，24小时内重复调用不收费
			if(watchData.details != null){
				var czcwInfoTable = $("#czcwInfo").html();
				// 获取RiskCode对象
				var czcwInfoCode = watchData.details;
				var czcwInfoHtml = czcwInfoTable + "<tr style='width: 150px;'>" +
				"<th rowspan='" + (czcwInfoCode.length + 1) + "' style=\"width: 170px;\">行业关注名单风险列表</th>" +
				"<td align='center'>结清状态</td>" +
				"<td align='center'>风险信息行业编码</td>" +
				"<td align='center'>风险信息行业说明</td>" +
				"<td align='center'>行业名单数据类型</td>" +
				"<td align='center'>行业名单风险类型</td>" +
				"<td align='center'>行业名单风险类型说明</td>" +
				"<td align='center'>风险编码</td>" +
				"<td align='center'>风险编码说明</td>" +
				"<td align='center'>数据刷新时间</td>" +
				"<td align='center'>异议处理流程的状态</td>" +
				"<td align='center'>异议处理完毕声明</td>" +
				"</tr>";
				if(watchData.details != null && watchData.details != ''){
					var bizExplain="";
					var typeExplain="";
					var codeExplain="";
					$.each(watchData.details, function(k, v){
						for (var int = 0; int < data.length; int++) {
							if(v.bizCode == data[int].udc_code){
								bizExplain=data[int].udc_name;
							}
							if(v.type == data[int].udc_code){
								typeExplain=data[int].udc_name;
							}
							if(v.code == data[int].udc_code){
								codeExplain=data[int].udc_name;
							}
						}
						var settlement = (v.settlement == false ? "false" : v.settlement);
						czcwInfoHtml += "<tr>" +
						"<td align='center'>" + settlement + "</td>" +
						"<td align='center'>" + v.bizCode + "</td>" +
						"<td align='center'>" + bizExplain + "</td>" +
						"<td align='center'>" + v.level + "</td>" +
						"<td align='center'>" + v.type + "</td>" +
						"<td align='center'>" + typeExplain + "</td>" +
						"<td align='center'>" + v.code + "</td>" +
						"<td align='center'>" + codeExplain + "</td>" +
						"<td align='center'>" + cTime(v.refreshTime.time) + "</td>" +
						"<td align='center'>" + v.status + "</td>" +
						"<td align='center'>" + v.statement + "</td>" +
						"</tr>";
					});
					$("#czcwInfo").html(czcwInfoHtml);
				}
			} else{
				czcwInfoHtml= $("#czcwInfo").html() +'<tr style="width: 150px;">'
				+ '<th rowspan="2" style="width: 170px;" >行业关注名单风险列表</th>'
				+"<td align='center'>结清状态</td>"
				+ '<td align="center">行业名单数据类型</td>'
				+ '<td align="center">行业名单风险类型</td>'
				+ "<td align='center'>风险编码</td>"
				+"<td align='center'>数据刷新时间</td>"
				+ '<td align="center">风险信息行业编码</td>'
				+"<td align='center'>异议处理流程的状态</td>"
				+"<td align='center'>异议处理完毕声明</td>"
				+ '</tr>'
				+ '<tr>'
				+ '<td align="center"></td>'
				+ '<td align="center"></td>'
				+ '<td align="center"></td>'
				+ '<td align="center"></td>'
				+ '<td align="center"></td>'
				+ '<td align="center"></td>'
				+ '<td align="center"></td>'
				+ '<td align="center"></td>'
				+ '</tr>';
				$("#czcwInfo").html(czcwInfoHtml);
			}
		}
		for (var int = 0; int < watchData.details.length; int++) {
	//  芝麻信用行业关注名单扩展信息表
        if(watchData.details[int].extendInfo != null && watchData.details[int].extendInfo != ''){
    		var extendInfoTable = $("#extendInfo").html();
        	// 获取RiskCode对象
            var extendInfoCode = watchData.details[int].extendInfo;
            var extendInfoHtml = extendInfoTable + "<tr style='width: 150px;'>" +
                "<th rowspan='" + (extendInfoCode.length + 1) + "' style=\"width: 170px;\" >行业关注名单风险扩展列表</th>" +
                "<td align='center'>补充信息字段的英文编码</td>" +
                "<td align='center'>补充信息字段的中文描述</td>" +
                "<td align='center'>补充信息字段的信息内容  </td>" +
                "<td align='center'>补充信息字段的信息内容说明</td>" +
                "</tr>";
            if(watchData.details[int].extendInfo != null){
                $.each(watchData.details[int].extendInfo, function(k, v){
                    extendInfoHtml += "<tr>" +
                        "<td align='center'>" + v.key + "</td>" +
                        "<td align='center'>" + v.description +"</td>" +
                        "<td align='center'>" + v.value + "</td>" +
                        "<td align='center'></td>" +
                        "</tr>";
                });
                $("#extendInfo").html(extendInfoHtml);
            }
	        } else{
	            extendInfoHtml= $("#extendInfo").html() +'<tr style="width: 170px;">'
	                + '<th rowspan="2" style="width: 170px;">行业关注名单风险扩展列表</th>'
	                + '<td align="center">补充信息字段的英文编码</td>'
	                + '<td align="center">补充信息字段的信息内容</td>'
	                + '<td align="center">补充信息字段的中文描述</td>'
	                + '</tr>'
	                + '<tr>'
	                + '<td align="center"></td>'
	                + '<td align="center"></td>'
	                + '<td align="center"></td>'
	                + '</tr>';
	            $("#extendInfo").html(extendInfoHtml);
	        }
		}
	}
	
	function cTime(time) {
		var timestamp = time;
		var newDate = new Date();
		newDate.setTime(timestamp);
		return newDate.format('yyyy-MM-dd h:m:s');
	}
	Date.prototype.format = function(format) {
	    var date = {
	       "M+": this.getMonth() + 1,
	       "d+": this.getDate(),
	       "h+": this.getHours(),
	       "m+": this.getMinutes(),
	       "s+": this.getSeconds(),
	       "q+": Math.floor((this.getMonth() + 3) / 3),
	       "S+": this.getMilliseconds()
	    };
	    if (/(y+)/i.test(format)) {
	       format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
	    }
	    for (var k in date) {
	       if (new RegExp("(" + k + ")").test(format)) {
	           format = format.replace(RegExp.$1, RegExp.$1.length == 1
	              ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
	       }
	    }
	    return format;
	}
});

/*****************************************************************************************************************************************************/
function showDataBase(){
	var platformCustomerId = getQueryString("id");// 平台用户编号
	var applicationId = getQueryString("applicationId");// 申请单编号
	if(platformCustomerId == "" && platformCustomerId == ""){
		platformCustomerId= "-1";
		applicationId= "-1";
	}
	
	// 请求后台
	$.ajax({
		url: getCtxPath() + "/backend/zm/getZMScore.do?v=" + new Date().getTime(),
		method: "post",
		dataType: "json",
		data: {
			platformCustomerId: platformCustomerId,
			applicationId: applicationId
		},
		success: function(data){
			if(data.code == "200"){
				
				var table = "";
				
				var html ="";
				// 欺诈关注清单
				if(data.data.bill != null ){
					// 拿到页面信息
//					table = $("#tbody_id").html();
			/*
					var tempStr="<tr><th style=\"width: 170px;\">欺诈关注清单业务号</th>" +
							"<td colspan=\"3\"><font id=\"zmBizNo_qd\">"+data.data.bill.zmBizNo+"</font></td>" +
							"</tr>" +
							"<tr>" +
							"<th>芝麻关注清单是否命中</th>" +
							"<td colspan=\"3\"><font id=\"state\">"+data.data.bill.zmHit+"</font></td></tr>";*/
					var riskCode = "";
					// 获取RiskCode对象
					var listRiskCode = data.data.bill.listRiskCode;
					
					html = tableTemp + "<tr style='width: 150px;'>" +
					"<th rowspan='" + (listRiskCode.length + 1) + "' style=\"width: 170px;\" >风险说明列表</th>" +
					"<td align='center'>RiskCode</td>" +
					"<td align='center'>风险说明</td>" +
					"<td align='center'>风险等级</td>" +
					"</tr>";
					if(data.data.bill.listRiskCode != null){
						$.each(data.data.bill.listRiskCode, function(k, v){
//							riskCode += v.remark;
							html += "<tr>" +
							"<td align='center'>" + v.code + "</td>" +
							"<td align='center'>" + v.remark + "</td>" +
							"<td align='center'>" + v.suggestType + "</td>" +
							"</tr>";
						});
					}
					$("#tbody_id").html(html);
					$("#zmBizNo_qd").html(data.data.bill.zmBizNo);// 欺诈关注清单返回的业务号
//					$("#state").html(data.data.bill.state == '1' ? "yes" : "no");// 是否命中
					$("#state").html(data.data.bill.zmHit);
				}else{
					html= tableTemp +'<tr style="width: 150px;">'
					+ '<th rowspan="2" style="width: 170px;">风险说明列表</th>'
					+ '<td align="center">RiskCode</td>'
					+ '<td align="center">风险说明</td>'
					+ '<td align="center">风险等级</td>'
					+ '</tr>'
					+ '<tr>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '</tr>';
					$("#tbody_id").html(html);
				}
				
				// 芝麻信用评分
				if(data.data.credit != null){
					$("#zmBizNo_pf").html(data.data.credit.zmBizNo);// 芝麻评分返回的业务号
					$("#zmScore").html(data.data.credit.zmScore);// 芝麻分数
				}


				// 获取芝麻信用行业关注名单主表信息
				if(data.data.listInfo != null){
					$("#isMatched").html(data.data.listInfo.isMatched == false ? "false" : data.data.listInfo.isMatched); //true=命中 在关注名单中 false=未命中
					$("#bizNo").html(data.data.listInfo.bizNo); //芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账
					// $("#applicationId").html(data.data.listInfo.applicationId);//申请单编号
					// $("#openId").html(data.data.listInfo.openId);//openId
					// $("#transactionId").html(data.data.listInfo.transactionId);//芝麻信用行业关注名单编码，每次调用接口的UUID流水号，24小时内重复调用不收费
				}



                //  芝麻信用行业关注名单详细信息表
                if(data.data.czcwInfo != null){
//                    var czcwInfoTable = $("#czcwInfo").html();
                    // 获取RiskCode对象
                    var czcwInfoCode = data.data.czcwInfo;
                    var czcwInfoHtml = czcwInfoTableTemp + "<tr style='width: 150px;'>" +
                        "<th rowspan='" + (czcwInfoCode.length + 1) + "' style=\"width: 170px;\">行业关注名单风险列表</th>" +
                        "<td align='center'>结清状态</td>" +
                        "<td align='center'>风险信息行业编码</td>" +
                        "<td align='center'>风险信息行业说明</td>" +
                        "<td align='center'>行业名单数据类型</td>" +
                        "<td align='center'>行业名单风险类型</td>" +
                        "<td align='center'>行业名单风险类型说明</td>" +
                        "<td align='center'>风险编码</td>" +
                        "<td align='center'>风险编码说明</td>" +
                        "<td align='center'>数据刷新时间</td>" +
                        "<td align='center'>异议处理流程的状态</td>" +
                        "<td align='center'>异议处理完毕声明</td>" +
                        "</tr>";
                    if(data.data.czcwInfo != null && data.data.czcwInfo != ''){
                        $.each(data.data.czcwInfo, function(k, v){
                        	var settlement = (v.settlement == false ? "false" : v.settlement);
                            czcwInfoHtml += "<tr>" +
                                "<td align='center'>" + settlement + "</td>" +
                                "<td align='center'>" + v.bizCode + "</td>" +
                                "<td align='center'>" + v.bizExplain + "</td>" +
                                "<td align='center'>" + v.level + "</td>" +
                                "<td align='center'>" + v.type + "</td>" +
                                "<td align='center'>" + v.typeExplain + "</td>" +
                                "<td align='center'>" + v.code + "</td>" +
                                "<td align='center'>" + v.codeExplain + "</td>" +
                                "<td align='center'>" + v.refreshTime + "</td>" +
                                "<td align='center'>" + v.status + "</td>" +
                                "<td align='center'>" + v.statement + "</td>" +
                                "</tr>";
                        });
                        $("#czcwInfo").html(czcwInfoHtml);
                    }
                } else{
                    czcwInfoHtml= czcwInfoTableTemp +'<tr style="width: 150px;">'
                        + '<th rowspan="2" style="width: 170px;" >行业关注名单风险列表</th>'
                        +"<td align='center'>结清状态</td>"
                        + '<td align="center">行业名单数据类型</td>'
                        + '<td align="center">行业名单风险类型</td>'
                    	+ "<td align='center'>风险编码</td>"
                        +"<td align='center'>数据刷新时间</td>"
                        + '<td align="center">风险信息行业编码</td>'
                        +"<td align='center'>异议处理流程的状态</td>"
                        +"<td align='center'>异议处理完毕声明</td>"
                    + '</tr>'
                    + '<tr>'
                    + '<td align="center"></td>'
                    + '<td align="center"></td>'
                    + '<td align="center"></td>'
                    + '<td align="center"></td>'
                    + '<td align="center"></td>'
                    + '<td align="center"></td>'
                    + '<td align="center"></td>'
                    + '<td align="center"></td>'
                    + '</tr>';
                    $("#czcwInfo").html(czcwInfoHtml);
                }



                //  芝麻信用行业关注名单扩展信息表
                if(data.data.extendInfo != null && data.data.extendInfo != ''){
//                    var extendInfoTable = $("#extendInfo").html();
                    // 获取RiskCode对象
                    var extendInfoCode = data.data.extendInfo;
                    var extendInfoHtml = extendInfoTableTemp + "<tr style='width: 150px;'>" +
                        "<th rowspan='" + (extendInfoCode.length + 1) + "' style=\"width: 170px;\" >行业关注名单风险扩展列表</th>" +
                        "<td align='center'>补充信息字段的英文编码</td>" +
                        "<td align='center'>补充信息字段的中文描述</td>" +
                        "<td align='center'>补充信息字段的信息内容  </td>" +
                        "<td align='center'>补充信息字段的信息内容说明</td>" +
                        "</tr>";
                    if(data.data.extendInfo != null){
                        $.each(data.data.extendInfo, function(k, v){
                            extendInfoHtml += "<tr>" +
                                "<td align='center'>" + v.keyinfo + "</td>" +
                                "<td align='center'>" + v.description +"</td>" +
                                "<td align='center'>" + v.value + "</td>" +
                                "<td align='center'>" + v.valueExplain + "</td>" +
                                "</tr>";
                        });
                        $("#extendInfo").html(extendInfoHtml);
                    }
                } else{
                    extendInfoHtml= extendInfoTableTemp +'<tr style="width: 170px;">'
                        + '<th rowspan="2" style="width: 170px;">行业关注名单风险扩展列表</th>'
                        + '<td align="center">补充信息字段的英文编码</td>'
                        + '<td align="center">补充信息字段的信息内容</td>'
                        + '<td align="center">补充信息字段的中文描述</td>'
                        + '</tr>'
                        + '<tr>'
                        + '<td align="center"></td>'
                        + '<td align="center"></td>'
                        + '<td align="center"></td>'
                        + '</tr>';
                    $("#extendInfo").html(extendInfoHtml);
                }

			}else{
				layer.msg(data.message, {icon: 2});
			}
			
		},
		error: function(){
			layer.msg("请求网络异常，请尝试重新登录！", {icon: 2});
		}
		
	});
}
/********************************************************************************************************************************************************/
function showAliyun(){
	 var applicationId = getQueryString("applicationId");//获取页面传过来的申请单编号  
	  var platformCustomerId = getQueryString("id");//获取页面传过来的平台用户编号
	  if(applicationId == undefined){
		  applicationId='00';
	  }
	  if(platformCustomerId == undefined){
		  platformCustomerId='00';
	  }
	  //applicationId='067a4abb76274790863f3810a34c5d3e';
	 $.ajax({//芝麻信用评分
			url : getCtxPath() + "/backend/queryAliyunDataInfo/getInfo.do?v=" + new Date().getTime(),
			method : "post",
			dataType : "json",
			data : { 
				taskType : '01001',
				applicationId : applicationId,
				platformId:platformCustomerId
			},
			success : function(data) {
				if(data.code == 200){
					if(data.data.info == null ) return;	
					var tempJson=eval('('+data.data.info+')');
					getScoreData(tempJson);
				}
			},
			error : function() {
				layer.msg("获取阿里云数据信息失败,请尝试重新获取！");
			}
		});
	 $.ajax({//芝麻信用欺诈清单
			url : getCtxPath() + "/backend/queryAliyunDataInfo/getInfo.do?v=" + new Date().getTime(),
			method : "post",
			dataType : "json",
			data : { 
				taskType : '02001',
				applicationId : applicationId,
				platformId:platformCustomerId
			},
			success : function(data) {
				if(data.code == 200){
					var riskCodeJson;
					// 请求json文件
					$.ajax({//
						url: getCtxPath() + "/index/js/zhiMa/riskCodeJson.json",//getCtxPath() + "/backend/zm/getZMScore.do",
						method: "get",
						dataType: "json",
						success: function(riskCodeJson){
							if(data.code == 200){
								if(data.data.info == null ) return;	
								var tempJson=eval('('+data.data.info+')');
								console.log(tempJson);
								getLieData(tempJson,riskCodeJson.riskCodeObject);
							}
						},
						error: function(){
							layer.msg("请求网络异常，请尝试重新登录！", {icon: 2});
						}
						
					});
				}
			},
			error : function() {
				layer.msg("获取阿里云数据信息失败,请尝试重新获取！");
			}
		});
	 $.ajax({//芝麻行业关注名单
			url : getCtxPath() + "/backend/queryAliyunDataInfo/getInfo.do?v=" + new Date().getTime(),
			method : "post",
			dataType : "json",
			data : { 
				taskType : '40004',
				applicationId : applicationId,
				platformId:platformCustomerId
			},
			success : function(data) {
				if(data.code == 200){
					var riskCodeJson;
					// 请求json文件
					$.ajax({//
						url: getCtxPath() + "/index/js/zhiMa/riskCodeJson.json",//getCtxPath() + "/backend/zm/getZMScore.do",
						method: "get",
						dataType: "json",
						success: function(riskCodeJson){
							if(data.code == 200){
								if(data.data.info == null ) return;	
								var tempJson=eval('('+data.data.info+')');
								getWatchData(tempJson,riskCodeJson.riskCodeObject);
							}
						},
						error: function(){
							layer.msg("请求网络异常，请尝试重新登录！", {icon: 2});
						}
						
					});
				}
			},
			error : function() {
				layer.msg("获取阿里云数据信息失败,请尝试重新获取！");
			}
		});
}
var table = "";
//
var html ="";
//
function getLieData(data,riskCodeJson){
// 欺诈关注清单
	if(data != null && data != undefined){
		// 拿到页面信息
		table = $("#tbody_id").html();
		var riskCode = "";
		// 获取RiskCode对象
		var listRiskCode = data.riskCode;
		html = tableTemp + "<tr style='width: 150px;'>" +
		"<th rowspan='" + (listRiskCode.length + 1) + "' style=\"width: 170px;\" >风险说明列表</th>" +
		"<td align='center'>RiskCode</td>" +
		"<td align='center'>风险说明</td>" +
		"<td align='center'>风险等级</td>" +
		"</tr>";
		for (var i = 0; i < riskCodeJson.length; i++) {
			if(listRiskCode != null){
				for (var int = 0; int < listRiskCode.length; int++) {
					if(listRiskCode[int]==riskCodeJson[i].riskCode){
						html += "<tr>" +
						"<td align='center'>" + riskCodeJson[i].riskCode + "</td>" +
						"<td align='center'>"+riskCodeJson[i].remark+"</td>" +
						"<td align='center'>"+riskCodeJson[i].suggestType+"</td>" +
						"</tr>";
					}
					
				}
			}
		}
		$("#tbody_id").html(html);
		$("#zmBizNo_qd").html(data.bizNo);// 欺诈关注清单返回的业务号
		//	$("#state").html(data.data.bill.state == '1' ? "yes" : "no");// 是否命中
		$("#state").html(data.hit);
	}else{
			html= tableTemp +'<tr style="width: 150px;">'
			+ '<th rowspan="2" style="width: 170px;">风险说明列表</th>'
			+ '<td align="center">RiskCode</td>'
			+ '<td align="center">风险说明</td>'
			+ '<td align="center">风险等级</td>'
			+ '</tr>'
			+ '<tr>'
			+ '<td align="center"></td>'
			+ '<td align="center"></td>'
			+ '<td align="center"></td>'
			+ '</tr>';
			$("#tbody_id").html(html);
	}
}		
function getScoreData(scoreData){
	// 芝麻信用评分
	if(scoreData != undefined && scoreData != null){
		$("#zmBizNo_pf").html(scoreData.bizNo);// 芝麻评分返回的业务号
		$("#zmScore").html(scoreData.zmScore);// 芝麻分数
	}
}

//  芝麻信用行业关注名单详细信息表
	function getWatchData(watchData,data){
	// 获取芝麻信用行业关注名单主表信息
	if(watchData != null && watchData != undefined){
		$("#isMatched").html(watchData.isMatched == false ? "false" : watchData.isMatched); //true=命中 在关注名单中 false=未命中
		$("#bizNo").html(watchData.bizNo); //芝麻信用对于每一次请求返回的业务号。后续可以通过此业务号进行对账
		// $("#applicationId").html(data.data.listInfo.applicationId);//申请单编号
		// $("#openId").html(data.data.listInfo.openId);//openId
		// $("#transactionId").html(data.data.listInfo.transactionId);//芝麻信用行业关注名单编码，每次调用接口的UUID流水号，24小时内重复调用不收费
		if(watchData.details != null){
			var czcwInfoTable = $("#czcwInfo").html();
			// 获取RiskCode对象
			var czcwInfoCode = watchData.details;
			var czcwInfoHtml = czcwInfoTableTemp + "<tr style='width: 150px;'>" +
			"<th rowspan='" + (czcwInfoCode.length + 1) + "' style=\"width: 170px;\">行业关注名单风险列表</th>" +
			"<td align='center'>结清状态</td>" +
			"<td align='center'>风险信息行业编码</td>" +
			"<td align='center'>风险信息行业说明</td>" +
			"<td align='center'>行业名单数据类型</td>" +
			"<td align='center'>行业名单风险类型</td>" +
			"<td align='center'>行业名单风险类型说明</td>" +
			"<td align='center'>风险编码</td>" +
			"<td align='center'>风险编码说明</td>" +
			"<td align='center'>数据刷新时间</td>" +
			"<td align='center'>异议处理流程的状态</td>" +
			"<td align='center'>异议处理完毕声明</td>" +
			"</tr>";
			if(watchData.details != null && watchData.details != ''){
				var bizExplain="";
				var typeExplain="";
				var codeExplain="";
				$.each(watchData.details, function(k, v){
					for (var int = 0; int < data.length; int++) {
						if(v.bizCode == data[int].udc_code){
							bizExplain=data[int].udc_name;
						}
						if(v.type == data[int].udc_code){
							typeExplain=data[int].udc_name;
						}
						if(v.code == data[int].udc_code){
							codeExplain=data[int].udc_name;
						}
					}
					var settlement = (v.settlement == false ? "false" : v.settlement);
					czcwInfoHtml += "<tr>" +
					"<td align='center'>" + settlement + "</td>" +
					"<td align='center'>" + v.bizCode + "</td>" +
					"<td align='center'>" + bizExplain + "</td>" +
					"<td align='center'>" + v.level + "</td>" +
					"<td align='center'>" + v.type + "</td>" +
					"<td align='center'>" + typeExplain + "</td>" +
					"<td align='center'>" + v.code + "</td>" +
					"<td align='center'>" + codeExplain + "</td>" +
					"<td align='center'>" + cTime(v.refreshTime.time) + "</td>" +
					"<td align='center'>" + v.status + "</td>" +
					"<td align='center'>" + v.statement + "</td>" +
					"</tr>";
				});
				$("#czcwInfo").html(czcwInfoHtml);
			}
		} else{
			czcwInfoHtml= czcwInfoTableTemp +'<tr style="width: 150px;">'
			+ '<th rowspan="2" style="width: 170px;" >行业关注名单风险列表</th>'
			+"<td align='center'>结清状态</td>"
			+ '<td align="center">行业名单数据类型</td>'
			+ '<td align="center">行业名单风险类型</td>'
			+ "<td align='center'>风险编码</td>"
			+"<td align='center'>数据刷新时间</td>"
			+ '<td align="center">风险信息行业编码</td>'
			+"<td align='center'>异议处理流程的状态</td>"
			+"<td align='center'>异议处理完毕声明</td>"
			+ '</tr>'
			+ '<tr>'
			+ '<td align="center"></td>'
			+ '<td align="center"></td>'
			+ '<td align="center"></td>'
			+ '<td align="center"></td>'
			+ '<td align="center"></td>'
			+ '<td align="center"></td>'
			+ '<td align="center"></td>'
			+ '<td align="center"></td>'
			+ '</tr>';
			$("#czcwInfo").html(czcwInfoHtml);
		}
	}
	for (var int = 0; int < watchData.details.length; int++) {
	//  芝麻信用行业关注名单扩展信息表
	if(watchData.details[int].extendInfo != null && watchData.details[int].extendInfo != ''){
		var extendInfoTable = $("#extendInfo").html();
		// 获取RiskCode对象
	    var extendInfoCode = watchData.details[int].extendInfo;
	    var extendInfoHtml = extendInfoTableTemp + "<tr style='width: 150px;'>" +
	        "<th rowspan='" + (extendInfoCode.length + 1) + "' style=\"width: 170px;\" >行业关注名单风险扩展列表</th>" +
	        "<td align='center'>补充信息字段的英文编码</td>" +
	        "<td align='center'>补充信息字段的中文描述</td>" +
	        "<td align='center'>补充信息字段的信息内容  </td>" +
	        "<td align='center'>补充信息字段的信息内容说明</td>" +
	        "</tr>";
	    if(watchData.details[int].extendInfo != null){
	        $.each(watchData.details[int].extendInfo, function(k, v){
	            extendInfoHtml += "<tr>" +
	                "<td align='center'>" + v.key + "</td>" +
	                "<td align='center'>" + v.description +"</td>" +
	                "<td align='center'>" + v.value + "</td>" +
	                "<td align='center'></td>" +
	                "</tr>";
	        });
	        $("#extendInfo").html(extendInfoHtml);
	    }
	    } else{
	        extendInfoHtml= extendInfoTableTemp +'<tr style="width: 170px;">'
	            + '<th rowspan="2" style="width: 170px;">行业关注名单风险扩展列表</th>'
	            + '<td align="center">补充信息字段的英文编码</td>'
	            + '<td align="center">补充信息字段的信息内容</td>'
	            + '<td align="center">补充信息字段的中文描述</td>'
	            + '</tr>'
	            + '<tr>'
	            + '<td align="center"></td>'
	            + '<td align="center"></td>'
	            + '<td align="center"></td>'
	            + '</tr>';
	        $("#extendInfo").html(extendInfoHtml);
	    }
	}
}

	function cTime(time) {
		var timestamp = time;
		var newDate = new Date();
		newDate.setTime(timestamp);
		return newDate.format('yyyy-MM-dd h:m:s');
	}
	Date.prototype.format = function(format) {
		var date = {
		   "M+": this.getMonth() + 1,
		   "d+": this.getDate(),
		   "h+": this.getHours(),
		   "m+": this.getMinutes(),
		   "s+": this.getSeconds(),
		   "q+": Math.floor((this.getMonth() + 3) / 3),
		   "S+": this.getMilliseconds()
		};
		if (/(y+)/i.test(format)) {
		   format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
		}
		for (var k in date) {
		   if (new RegExp("(" + k + ")").test(format)) {
		       format = format.replace(RegExp.$1, RegExp.$1.length == 1
		          ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
	   }
	}
	return format;
}