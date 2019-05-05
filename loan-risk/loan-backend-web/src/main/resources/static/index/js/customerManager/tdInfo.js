layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
			layer = layui.layer, // 获取当前窗口的layer对象
			form = layui.form(), element = layui.element();
		});

$(function() {
	 var platformCustomerId = getQueryString("id");//获取页面传过来的平台用户编号
	 var applicationId = getQueryString("applicationId");//获取页面传过来的申请单编号
	$.ajax({
				url : getCtxPath() + "/backend/td/getReport.do",
				method : "post",
				dataType : "json",
				data : {
					//platformCustomerId : "fca2e635ae144b5ea2c7bfcca8633f53"
					platformCustomerId : platformCustomerId,
					applicationId : applicationId
				},
				success : function(msg) {
					if(msg.code == '200' && msg.list.successed){
						var data = msg.list;
						var tdAscriptionAnalysis = data.tdAscriptionAnalysis[0];//归属地数据
						var tdAuditReport = data.tdAuditReport[0];//报告
						var tdRiskCheck = data.tdRiskCheck;//检查项数据
						var tdRiskCheckOne = data.tdRiskCheck[0];//检查项数据
						var tdRiskCheckDetail = data.tdRiskCheckDetail;//检查明细数据
						var tdRiskCourt = data.tdRiskCourt;//法院数据
						
						var tdRiskCheckByUserinfo = data.tdRiskCheckByUserinfo;//个人基本信息核查
						var tdRiskCheckByBlxx = data.tdRiskCheckByBlxx;//风险信息扫描
						var tdRiskCheckByDpt = data.tdRiskCheckByDpt;//多平台借贷申请检测
						var tdRiskCheckByKhxw = data.tdRiskCheckByKhxw;//客户行为检测

						//报告编号
						$("#report_id").html(tdAuditReport.reportId);
						$("#app_id").html(tdAuditReport.useId);
						$("#repord_date").html(tdAuditReport.reportTime);
						 
						
						var riskLevel='';
						var level = tdRiskCheckOne==undefined?'low':tdRiskCheckOne.riskLevel;
						if(level==='low'){
							riskLevel='低'
						}
						if(level==='medium'){
							riskLevel='中'
						}
						if(level==='high'){
							riskLevel='高'
						}
						var proposal ='';
						var tdproposal= tdAuditReport.proposal;
						if(tdproposal === 'Accept'){
							proposal ='建议通过';
						}
						if(tdproposal === 'Review'){
							proposal ='建议审核';
						}
						if(tdproposal === 'Reject'){
							proposal ='建议拒绝';
						}
						//审核分数建议等级
						$("#user_score font").eq(0).html(tdAuditReport.score);
						$("#user_score font").eq(1).html(riskLevel);
						//$("#user_score font").eq(1).html(tdRiskCheckOne.riskLevel==null?'低':tdRiskCheckOne.riskLevel);
						//$("#user_score font").eq(2).html(tdAuditReport.proposal);
						$("#user_score font").eq(2).html(proposal);
	
						//账户基本信息
						$("#user_custinfo font").eq(0).html(tdAuditReport.loanDate);
						$("#user_custinfo font").eq(1).html(tdAuditReport.name);
						$("#user_custinfo font").eq(2).html(tdAuditReport.phoneNum);
						$("#user_custinfo font").eq(3).html(tdAuditReport.idCard);
						
						//归属地解析
						$("#user_address font").eq(0).html(tdAscriptionAnalysis.idCardAddress);
						$("#user_address font").eq(1).html(tdAscriptionAnalysis.mobileAddress);
						$("#user_address font").eq(2).html(tdAscriptionAnalysis.trueIpAddress);
						$("#user_address font").eq(3).html(tdAscriptionAnalysis.wifiAddress);
						$("#user_address font").eq(4).html(tdAscriptionAnalysis.cellAddress);
						$("#user_address font").eq(5).html(tdAscriptionAnalysis.bankCardAddress);
						
						//贷前风险情况-个人基本信息核查 
						for(var i=0;i<tdRiskCheckByUserinfo.length;i++){
							var tdriskLevel='';
							var tdlevel = tdRiskCheckByUserinfo[i].riskLevel;
							if(tdlevel==='low'){
								tdlevel='低'
							}
							if(tdlevel==='medium'){
								tdlevel='中'
							}
							if(tdlevel==='high'){
								tdlevel='高'
							}
							var str='';
							str+="<tr>";
							str+="<td width='300px'>"+tdRiskCheckByUserinfo[i].itemName+"</td>";
							str+="<td width='50px'>"+tdlevel+"</td>";
							str+="<td width='450px'><li><font color='red'>风险类型：</font>"+tdRiskCheckByUserinfo[i].fraudType+"</li>" +
									"<li><font color='red'>匹配字段：</font>"+tdRiskCheckByUserinfo[i].hitTypeDisplayName+"<li></td>";
							str+="</tr>";
							$("#loan_userinfo").append(str);
						}
						
						//贷前风险情况-不良信息扫描
						for(var i=0;i<tdRiskCheckByBlxx.length;i++){
							var risriskLevel='';
							var rislevel = tdRiskCheckByBlxx[i].riskLevel;
							if(rislevel==='low'){
								risriskLevel='低'
							}
							if(rislevel==='medium'){
								risriskLevel='中'
							}
							if(rislevel==='high'){
								risriskLevel='高'
							}
							var str='';
							str+="<tr>";
							str+="<td width='300px'>"+tdRiskCheckByBlxx[i].itemName+"</td>";
							str+="<td width='50px'>"+risriskLevel+"</td>";
							str+="<td width='450px'><li><font color='red'>风险类型：</font>"+tdRiskCheckByBlxx[i].fraudType+"</li>" +
									"<li><font color='red'>匹配字段：</font>"+tdRiskCheckByBlxx[i].hitTypeDisplayName+"<li></td>";
							str+="</tr>";
							$("#loan_blxx").append(str);
						}
						
						//贷前风险情况-多平台借贷申请检测
						for(var i=0;i<tdRiskCheckByDpt.length;i++){
							var  kLevel='';
							var trislevel = tdRiskCheckByDpt[i].riskLevel;
							if(trislevel==='low'){
								kLevel='低'
							}
							if(trislevel==='medium'){
								kLevel='中'
							}
							if(trislevel==='high'){
								kLevel='高'
							}
							var str='';
							str+="<tr>";
							str+="<td width='300px'>"+tdRiskCheckByDpt[i].itemName+"</td>";
							str+="<td width='50px'>"+kLevel+"</td>";
							str+="<td width='450px' <li><font color='red'>"+tdRiskCheckByDpt[i].description+"</font><li>\n\r"+
										tdRiskCheckByDpt[i].fraudType +"</td>";
							str+="</tr>";
							$("#loan_dpt").append(str);
						}
						
						//贷前风险情况-客户行为检测
						for(var i=0;i<tdRiskCheckByKhxw.length;i++){
							var  tkLevel='';
							var tdrislevel = tdRiskCheckByKhxw[i].riskLevel;
							if(tdrislevel==='low'){
								tkLevel='低'
							}
							if(tdrislevel==='medium'){
								tkLevel='中'
							}
							if(tdrislevel==='high'){
								tkLevel='高'
							}
							var str='';
							str+="<tr>";
							str+="<td width='300px'>"+tdRiskCheckByKhxw[i].itemName+"</td>";
							str+="<td width='50px'>"+tkLevel+"</td>";
							str+="<td width='450px'>"+tdRiskCheckByKhxw[i].remark+"</td>";
							str+="</tr>";
							$("#loan_khxw").append(str);
						}
						
						//法院数据
						var courtInfoHtml = "";
						for(var i=0;i<tdRiskCourt.length;i++){
							courtInfoHtml += "<legend  style=\"margin-left:40%\">附件：法院详情 - "+tdRiskCourt[i].fraudType+"名单</legend>";
							courtInfoHtml += "<table class=\"layui-table\"> ";
							courtInfoHtml += "<tbody>";
							//被执行人姓名
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">被执行人姓名</td>";
							courtInfoHtml +="<td>"+tdRiskCourt[i].name+"</td>"
							courtInfoHtml += "</tr>";
							
							//年龄
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">年龄</td>";
							courtInfoHtml +="<td>"+tdRiskCourt[i].age+"</td>";
							courtInfoHtml += "</tr>";
							
							//性别
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">性别</td>";
							courtInfoHtml +="<td>"+tdRiskCourt[i].gender+"</td>";
							courtInfoHtml += "</tr>";
							
							//省份
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">省份</td>";
							courtInfoHtml +="<td>"+tdRiskCourt[i].province+"</td>";
							courtInfoHtml += "</tr>";
							
							//立案时间
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">立案时间</td>";
							courtInfoHtml +="<td>"+tdRiskCourt[i].filingTime+"</td>";
							courtInfoHtml += "</tr>";
							
							//执行法院
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">执行法院</td>";
							courtInfoHtml +="<td>"+tdRiskCourt[i].courtName+"</td>";
							courtInfoHtml += "</tr>";
							
							//做出执行依据单位
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">做出执行依据单位</td>";
							courtInfoHtml +="<td>"+tdRiskCourt[i].executionDepartment+"</td>";
							courtInfoHtml += "</tr>";
							
							//生效法律文书确定的义务
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">生效法律文书确定的义务</td>";
							courtInfoHtml +="<td>"+tdRiskCourt[i].duty+"</td>";
							courtInfoHtml += "</tr>";
							
							//被执行人的履行情况
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">被执行人的履行情况</td>";
							courtInfoHtml +="<td>"+tdRiskCourt[i].situation+"</td>";
							courtInfoHtml += "</tr>";
							
							//失信被执行人行为具体情形
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">失信被执行人行为具体情形</td>";
							courtInfoHtml +="<td>"+tdRiskCourt[i].discreditDetail+"</td>";
							courtInfoHtml += "</tr>";
							
							//执行依据文号
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">执行依据文号</td>";
							courtInfoHtml +="<td>"+tdRiskCourt[i].executionBase+"</td>";
							courtInfoHtml += "</tr>";
							
							//案号
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">案号</td>";
							courtInfoHtml +="<td>"+tdRiskCourt[i].caseNumber+"</td>";
							
							//执行标的
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">执行标的</td>";
							courtInfoHtml +="<td>"+tdRiskCourt[i].executionNumber+"</td>";
							
							//执行状态
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">执行状态</td>";
							courtInfoHtml +="<td>"+tdRiskCourt[i].executionStatus+"</td>";
							
							courtInfoHtml += "</tbody>";
							courtInfoHtml += "</table>";
						}
						$("#dev_court").html(courtInfoHtml);
					}
				},
				error : function() {
					alert("获取用户信息失败,请尝试重新获取！");
				}
			});
});


/**
 * 2017-04-28 00:00:00.0 -> 2017-04-28 00:00:00
 */
function getDateStrFormat(s){
	if(s!==undefined && s.length>19){
		return s.substring(0,19);
	}
	return s
}