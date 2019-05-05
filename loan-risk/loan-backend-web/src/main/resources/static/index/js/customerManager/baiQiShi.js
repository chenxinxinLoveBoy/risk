layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
			layer = layui.layer, // 获取当前窗口的layer对象
			form = layui.form(), element = layui.element();
		});

//初始化加载
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
 
function getmysqldata() { 
	 var platformCustomerId = getQueryString("id");//获取页面传过来的平台用户编号  
	 var applicationId = getQueryString("applicationId");//获取页面传过来的申请单编号 
	 //var applicationId = '02256a0db90c41c19aea71e18d88905b'; 
	// 请求后台02256a0db90c41c19aea71e18d88905b
	$.ajax({
		url: getCtxPath() + "/backend/baiQiShi/gerBaiQishiReport.do?v=" + new Date().getTime(),
		method: "post",
		dataType: "json",
		data: { 
			platformCustomerId:platformCustomerId,
			applicationId: applicationId 
		},
		success: function(data){
			var table = ""; 
			var html ="";
			$("#clmxid").html('');
			$("#qzmxid").html('');
			if(data.code == "200"){
				if(data.data.bqsAntiFraudInfo != null){
					$("#applicationId").html(data.data.bqsAntiFraudInfo.applicationId);//申请单编号'
					$("#certCode").html(data.data.bqsAntiFraudInfo.certCode);//身份证号码
					$("#certName").html(data.data.bqsAntiFraudInfo.certName);//身份证姓名
					$("#mobile").html(data.data.bqsAntiFraudInfo.mobile);//手机号 
					$("#resultCode").html(data.data.bqsAntiFraudInfo.resultCode);//结果码，参见ressultCode附录码表 
					$("#flowNo").html(data.data.bqsAntiFraudInfo.flowNo);//本次请求的流水号，用于事后案件调查
					var code =  data.data.bqsAntiFraudInfo.finalDecision
					if(code == 'Review'){
						code ='审核,风险评估决策为低风险建议人工审核';
					};
					if(code == 'Reject'){
						code ='拒绝 ,风险评估决策为高风险建议拒绝';
					};
					if(code == 'Accept'){
						code ='通过,无风险';
					};
					$("#finalDecision").html(code);//决策结果码，参见decision码表
//					$("#finalScore").html(data.data.bqsAntiFraudInfo.finalScore); //最终风险系数，只有权重策略模式下有效
					$("#eventType").html(data.data.bqsAntiFraudInfo.eventType); //事件类型，参考eventType附录码表用来标识应用下某个策略集事件 
				}
				if(data.data.bqsStrategySetInfo != null){
                    var clmxidTable = $("#clmxid").html();
                    // 获取RiskCode对象
                    var clCode = data.data.bqsStrategySetInfo;
                    var clInfoHtml = clmxidTable + "<tr style='width: 150px;'>" +
                        "<th rowspan='" + (clCode.length + 1) + "' style=\"width: 170px;\">白骑士反欺诈策略明细列表</th>" +
                        "<td align='center' style='white-space: nowrap;'>策略名称</td>" +  
                        "<td align='center' style='white-space: nowrap;'>策略风险类型</td>" +
                        "<td align='center' style='white-space: nowrap;'>策略击中话术提示</td>" + 
                        "</tr>";
                    if(data.data.bqsStrategySetInfo != null && data.data.bqsStrategySetInfo != ''){
                        $.each(data.data.bqsStrategySetInfo, function(k, v){ 
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
                                "<td align='center' style='white-space: nowrap;'>" + v.strategyName + "</td>" +    
                                "<td align='center' style='white-space: nowrap;'>" + type + "</td>" + 
                                "<td align='center' style='white-space: nowrap;'>" + v.tips + "</td>" + 
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
				
				if(data.data.bqsHitRulesList != null){ 
                    var qzTable = $("#qzmxid").html();
                    // 获取RiskCode对象
                    var qzCode = data.data.bqsHitRulesList;
                    var qzHtml = qzTable + "<tr style='width: 150px;'>" +
                        "<th rowspan='" + (qzCode.length + 1) + "' style=\"width: 170px;\">白骑士反欺诈规则明细列表</th>" +
                        "<td align='center' style='white-space: nowrap;'>规则名称</td>" + 
                        "<td align='center' style='white-space: nowrap;'>规则风险系数</td>" +
                        "<td align='center' style='white-space: nowrap;'>规则决策</td>" +
                        "<td align='center' style='white-space: nowrap;'>规则击中信息备注</td>" + 
                        "</tr>";
                    if(data.data.bqsHitRulesList != null && data.data.bqsHitRulesList != ''){
                        $.each(data.data.bqsHitRulesList, function(k, v){ 
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
                                "<td align='center' style='white-space: nowrap;'>" + v.ruleName + "</td>" + 
                                "<td align='center' style='white-space: nowrap;'>" + v.score + "</td>" +
                                "<td align='center' style='white-space: nowrap;'>" + dec + "</td>" +
                                "<td align='center' style='white-space: nowrap;'>" + v.memo + "</td>" + 
                                "</tr>";
                        });
                        $("#qzmxid").html(qzHtml);
                    }
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
			} 
		
	});     
};
//阿里云数据查询
function infoQuery(){
		  var platformCustomerId = getQueryString("id");
		  var applicationId = getQueryString("applicationId");//获取页面传过来的申请单编号  
		 $.ajax({
				url : getCtxPath() + "/backend/queryAliyunDataInfo/getInfo.do?v=" + new Date().getTime(),
				method : "post",
				dataType : "json",
				data : { 
					taskType : '08001',//白骑士欺诈 
					applicationId : applicationId,
					platformId:platformCustomerId
				},
				success : function(obj) {
					if (obj.data === undefined) {
						return;
					}
					var info=JSON.parse(obj.data.info); 
					$("#flowNo").html(info.flowNo);// 本次请求的流水号，用于事后案件调查
					var code =  info.finalDecision;
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
					if(info.strategySet != null && info.strategySet != ''){
					$("#clmxid").html("");
                    var clmxidTable = $("#clmxid").html();
                    // 获取RiskCode对象
                    var clCode = info.strategySet;
                    var clInfoHtml = clmxidTable + "<tr style='width: 150px;'>" +
                        "<th rowspan='" + (clCode.length + 1) + "' style=\"width: 170px;\">白骑士反欺诈策略明细列表</th>" +
                        "<td align='center' style='white-space: nowrap;'>策略名称</td>" +  
                        "<td align='center' style='white-space: nowrap;'>策略风险类型</td>" +
                        "<td align='center' style='white-space: nowrap;'>策略击中话术提示</td>" + 
                        "</tr>";
                        $.each(info.strategySet, function(k, v){ 
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
				}else{
	                 $("#clmxid").html("");
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
                    
                    // 获取RiskCode对象
                    if(info.strategySet != null && info.strategySet != ''){
                    $("#qzmxid").html("");
                    var qzTable = $("#qzmxid").html();
                    var row=0;
                    $.each(info.strategySet, function(k, t){ 
                    	row+=t.hitRules.length;
                    });
                    var qzHtml = qzTable + "<tr style='width: 150px;'>" +
                        "<th rowspan='" + (row + 1) + "' style=\"width: 170px;\">白骑士反欺诈规则明细列表</th>" +
                        "<td align='center' style='white-space: nowrap;'>规则名称</td>" + 
                        "<td align='center' style='white-space: nowrap;'>规则风险系数</td>" +
                        "<td align='center' style='white-space: nowrap;'>规则决策</td>" +
                        "<td align='center' style='white-space: nowrap;'>规则击中信息备注</td>" + 
                        "</tr>";
                    	$.each(info.strategySet, function(k, t){ 
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
                        qzTable="";
                    	
				} else{
                    $("#qzmxid").html("");
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
				},
				error : function() {
					layer.msg("获取阿里云数据信息失败,请尝试重新获取！");
				}
			}); 
	  	};