layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
			layer = layui.layer, // 获取当前窗口的layer对象
			form = layui.form(), element = layui.element();
		});



// 初始化加载
$(function() {
//	var sURL = window.document.URL;  
//	alert(sURL);
	var platformCustomerId = getQueryString("id");// 平台用户编号
	var applicationId = getQueryString("applicationId");// 申请单编号
	if(platformCustomerId == "" && platformCustomerId == ""){
		platformCustomerId= "-1";
		applicationId= "-1";
	}
	
	
	// 请求后台
	$.ajax({
		url: getCtxPath() + "/backend/zm/getZMScore.do",
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
					$("#zmBizNo_qd").html(data.data.bill.zmBizNo);// 欺诈关注清单返回的业务号
//					$("#state").html(data.data.bill.state == '1' ? "yes" : "no");// 是否命中
					$("#state").html(data.data.bill.zmHit);
					
					// 拿到页面信息
					table = $("#tbody_id").html();
					var riskCode = "";
					// 获取RiskCode对象
					var listRiskCode = data.data.bill.listRiskCode;
					
					html = table + "<tr style='width: 150px;'>" +
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
                    var czcwInfoTable = $("#czcwInfo").html();
                    // 获取RiskCode对象
                    var czcwInfoCode = data.data.czcwInfo;
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



                //  芝麻信用行业关注名单扩展信息表
                if(data.data.extendInfo != null && data.data.extendInfo != ''){
                    var extendInfoTable = $("#extendInfo").html();
                    // 获取RiskCode对象
                    var extendInfoCode = data.data.extendInfo;
                    var extendInfoHtml = extendInfoTable + "<tr style='width: 150px;'>" +
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










                //  芝麻信用行业关注名单详细信息表
              /*  if(data.data.czcwInfo != null){
                    var czcwInfoTable = $("#czcwInfo").html();
                    // 获取对象
                    var czcwInfoCode = data.data.czcwInfo;
                    var czcwInfoHtml = czcwInfoTable + "<tr style='width: 150px;'>" +
                        "<th rowspan='" + (czcwInfoCode.length + 1) + "'>行业关注名单风险列表</th>" +
                        "<td align='center'>结清状态</td>" +
                        "<td align='center'>风险信息行业编码</td>" +
                        "<td align='center'>行业名单风险类型 </td>" +
                        "<td align='center'>风险编码</td>" +
                        "<td align='center'>数据刷新时间</td>" +
                        "</tr>";
                    if(data.data.czcwInfo != null){
                        $.each(data.data.czcwInfo, function(k, v){
                        	var settlement = (v.settlement == false ? "false" : v.settlement);
                            czcwInfoHtml += "<tr>" +
                                "<td align='center'>" + settlement + "</td>" +
                                "<td align='center'>" + v.bizCode + "</td>" +
                                "<td align='center'>" + v.type + "</td>" +
                                "<td align='center'>" + v.code + "</td>" +
                                "<td align='center'>" + v.refreshTime + "</td>" +
                                "</tr>";
                        });
                        $("#czcwInfo").html(czcwInfoHtml);
                    }
                } else{
                    czcwInfoHtml= $("#czcwInfo").html() +'<tr style="width: 150px;">'
                        + '<th rowspan="2">风险说明列表</th>'
                        + '<td align="center">RiskCode</td>'
                        + '<td align="center">风险说明</td>'
                        + '<td align="center">风险等级</td>'
                        + '</tr>'
                        + '<tr>'
                        + '<td align="center"></td>'
                        + '<td align="center"></td>'
                        + '<td align="center"></td>'
                        + '</tr>';
                    $("#tbody_id").html(czcwInfoHtml);
                }*/




                //  芝麻信用行业关注名单扩展信息表
               /* if(data.data.extendInfo != null){
                    table = $("#extendInfo").html();
                    // 获取RiskCode对象
                    var extendInfoCode = data.data.extendInfo;
                    html = table + "<tr style='width: 150px;'>" +
                        "<th rowspan=' + (extendInfoCode.length + 1) + '>行业关注名单扩展信息列表</th>" +
                        "<td align='center'>补充信息字段的英文编码</td>" +
                        "<td align='center'>补充信息字段的信息内容 </td>" +
                        "<td align='center'>补充信息字段的中文描述  </td>" +
                        "</tr>";
                    if(data.data.extendInfo != null){
                        $.each(data.data.extendInfo, function(k, v){
                            html += "<tr>" +
                                "<td align='center'>" + v.keyinfo + "</td>" +
                                "<td align='center'>" + v.value + "</td>" +
                                "<td align='center'>" + v.description + "</td>" +
                                "</tr>";
                        });
                        $("#extendInfo").html(html);
                    }
                } else{
                    html= $("#extendInfo").html() +'<tr style="width: 150px;">'
                   		+ '<th rowspan=' + (extendInfoCode.length + 1) + '>行业关注名单扩展信息列表</th>'
                        + '<td align="center">补充信息字段的英文编码</td>'
                        + '<td align="center">补充信息字段的信息内容</td>'
                        + '<td align="center">补充信息字段的中文描述</td>'
                        + '</tr>'
                        + '<tr>'
                        + '<td align="center"></td>'
                        + '<td align="center"></td>'
                        + '<td align="center"></td>'
                        + '</tr>';
                    $("#extendInfo").html(html);
                }*/


			}else{
				layer.msg(data.message, {icon: 2});
			}
			
		},
		error: function(){
			layer.msg("请求网络异常，请尝试重新登录！", {icon: 2});
		}
		
	});
		
	 
});
