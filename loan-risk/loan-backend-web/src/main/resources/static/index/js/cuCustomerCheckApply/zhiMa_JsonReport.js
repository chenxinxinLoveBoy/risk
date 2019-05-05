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
	var customerCheckApplyId = getQueryString("id"); 
	$.ajax({//芝麻信用评分
		url: getCtxPath() + "/backend/cuCustomerCheckApply/getDataReportByMongo.do?v=" + new Date().getTime(),
		method: "post",
		dataType: "json",
		data : {
			customerCheckApplyId : customerCheckApplyId,//'d372cc71c3b04151b4f273d747dd4241',//
			taskType: '02001'
		},
		success: function(data){   
			if(data.list.data != null){ 
				var hit = data.list.data.hit;  
				console.log(hit);
				var bizNo = data.list.data.bizNo;
				console.log(bizNo);
				if(hit != null || bizNo != null){ 
					$("#zmBizNo_qd").html(bizNo);
					$("#state").html(hit);	 
					if(hit != null){
						$.ajax({
							url: getCtxPath() + "/index/js/zhiMa/riskCodeJson.json",
							method: "get",
							dataType: "json",
							success: function(riskCodeJson){
								if(data.code == 200){
									if(data.list.data == null ) return;	
									var data2=data.list.data; 
									getLieData(data2,riskCodeJson.riskCodeObject);
								} 
							},
							error: function(){
								layer.msg("请求网络异常，请尝试重新登录！", {icon: 2});
							} 
						});
					} 
				}
			}
			else{
				$("#zmBizNo_qd").html("");
				$("#state").html("");	 
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
		},
		error: function(){
			layer.msg("获取芝麻信用报告失败！", {icon: 2});
		} 
	}); 
	
	function getLieData(data,riskCodeJson){
		// 欺诈关注清单
		if(data != null && data != undefined){/*
			$("#zmBizNo_qd").html(data.bizNo);// 欺诈关注清单返回的业务号 
			$("#state").html(data.hit); */
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
}); 