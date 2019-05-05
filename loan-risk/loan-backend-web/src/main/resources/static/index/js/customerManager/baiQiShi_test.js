layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
			layer = layui.layer, // 获取当前窗口的layer对象
			form = layui.form(), element = layui.element();
		});

// 初始化加载
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
$(function() { 
	 var applicationId = getQueryString("applicationId");// 获取页面传过来的申请单编号
	 var platformId = getQueryString("id");
	$.ajax({
		//url:"http://shandai-real.oss-cn-shanghai.aliyuncs.com/bqsqz/20170829/5848a4c23260171627e24c7a70dc3260.json",
		url:getCtxPath() + "/backend/mongo/getMongoDisplay.do?v=" + new Date().getTime(),
		method: "post",
		dataType: "json",
		data: { 
			applicationID:applicationId,
			taskType: "08001",
			platformId:platformId
		},
		success: function(obj){
					
					if(obj.list===undefined)
				    {
				    	return ;
				    }
					$("#resultCode").html(obj.list.data.resultCode);// 结果码，参见ressultCode附录码表
					$("#flowNo").html(obj.list.data.flowNo);// 本次请求的流水号，用于事后案件调查
					var code =  obj.list.data.finalDecision;
					if(code == 'Review'){
						code ='审核,风险评估决策为低风险建议人工审核';
					};
					if(code == 'Reject'){
						code ='拒绝 ,风险评估决策为高风险建议拒绝';
					};
					if(code == 'Accept'){
						code ='通过,无风险';
					};
					$("#finalDecision").html(code);// 决策结果码，参见decision码表
				if(obj.list.data.strategySet != null){  
                    var clmxidTable = $("#clmxid").html();
                    // 获取RiskCode对象
                    var clCode = obj.list.data.strategySet;
                    var clInfoHtml = clmxidTable + "<tr style='width: 150px;'>" +
                        "<th rowspan='" + (clCode.length + 1) + "' style=\"width: 170px;\">白骑士反欺诈策略明细列表</th>" +
                        "<td align='center' style='white-space: nowrap;'>策略名称</td>" +  
                        "<td align='center' style='white-space: nowrap;'>策略风险类型</td>" +
                        "<td align='center' style='white-space: nowrap;'>策略击中话术提示</td>" + 
                        "</tr>";
                    if(obj.list.data.strategySet != null && obj.list.data.strategySet != ''){
                        $.each(obj.list.data.strategySet, function(k, v){ 
                        	var type = v.riskType;
                        	if('botAction' == type){
                        		type ='机器行为';
                        	};
                        	if('fakeRegister' == type){
                        		type ='伪冒申请';
                        	};
                        	if('dynamicCode' == type){
                        		type ='动码攻击';
                        	};
                        	if('suspiciousAction' == type){
                        		type ='异常行为';
                        	};
                        	if('bruteForce' == type){
                        		type ='暴力破解';
                        	};
                        	if('userFraud' == type){
                        		type ='本人欺诈';
                        	};
                        	if('accountTakeover' == type){
                        		type ='账户盗用';
                        	};
                        	if('garbageRegister' == type){
                        		type ='垃圾注册';
                        	};
                        	if('creditRisk' == type){
                        		type ='失信风险';
                        	};
                        	if('agencyCash' == type){
                        		type ='中介套现';
                        	};
                        	clInfoHtml += "<tr>" + 
                                "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.strategyName) + "</td>" +    
                                "<td align='center' style='white-space: nowrap;'>" + convertToNull(type) + "</td>" + 
                                "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.tips) + "</td>" + 
                                "</tr>";
                        });
                        $("#clmxid").html(clInfoHtml);
                    } 
				}else{
                 $("#clmxid").html(clInfoHtml);
                 clInfoHtml= $("#clmxid").html() +'<tr style="width: 150px;">'
                 + '<th rowspan="2" style="width: 170px;" >白骑士反欺诈策略明细列表</th>'
                 +"<td align='center'>策略名称</td>"  
                 +"<td align='center'>策略风险类型</td>"
                 +"<td align='center'>策略击中话术提示</td>"
	             + '</tr>'
	             + '<tr>' 
	             + '<td align="center"></td>'
	             + '<td align="center"></td>'
	             + '<td align="center"></td>' 
	             + '</tr>';
	             $("#clmxid").html(clInfoHtml);
			}
                    var qzTable = $("#qzmxid").html();
                    // 获取RiskCode对象
                    if(obj.list.data.strategySet != null && obj.list.data.strategySet != ''){
                    var row=0;
                    $.each(obj.list.data.strategySet, function(k, t){ 
                    	row+=t.hitRules.length;
                    });
                    var qzHtml = qzTable + "<tr style='width: 150px;'>" +
                        "<th rowspan='" + (row + 1) + "' style=\"width: 170px;\">白骑士反欺诈规则明细列表</th>" +
                        "<td align='center' style='white-space: nowrap;'>规则名称</td>" + 
                        "<td align='center' style='white-space: nowrap;'>规则风险系数</td>" +
                        "<td align='center' style='white-space: nowrap;'>规则决策</td>" +
                        "<td align='center' style='white-space: nowrap;'>规则击中信息备注</td>" + 
                        "</tr>";
                    	$.each(obj.list.data.strategySet, function(k, t){ 
	                        $.each(t.hitRules, function(k, v){ 
	                        	var dec = v.decision;
	                        	if(dec == 'Review'){
	                        		dec ='审核,风险 评估决策为低风险建议人工审核';
	        					};
	        					if(dec == 'Reject'){
	        						dec ='拒绝,风险评估决策为高风险建议';
	        					};
	        					if(dec == 'Accept'){
	        						dec ='通过,无风险';
	        					};
	                        	qzHtml += "<tr>" + 
	                                "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.ruleName) + "</td>" + 
	                                "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.score) + "</td>" +
	                                "<td align='center' style='white-space: nowrap;'>" + convertToNull(dec) + "</td>" +
	                                "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.memo) + "</td>" + 
	                                "</tr>";
	                        });
                    	});
                        $("#qzmxid").html(qzHtml);
                    	
				} else{
                    $("#qzmxid").html(qzHtml);
                    qzHtml= $("#qzmxid").html() +'<tr style="width: 150px;">'
                    + '<th rowspan="2" style="width: 170px;" >白骑士反欺诈规则明细列表</th>'
                    +"<td align='center'>规则名称</td>"  
                    +"<td align='center'>规则决策</td>"
                    +"<td align='center'>规则击中信息备注</td>"
                + '</tr>'
                + '<tr>' 
                + '<td align="center"></td>'
                + '<td align="center"></td>'
                + '<td align="center"></td>' 
                + '</tr>';
                $("#qzmxid").html(qzHtml);
				}
		
		}	
	});
});