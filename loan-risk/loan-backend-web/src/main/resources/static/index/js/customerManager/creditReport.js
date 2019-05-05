layui.config({
	base : '../../js/'
}); 
layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer,
			layer = layui.layer,
			form = layui.form(), element = layui.element();
		});

$(function() {  
	var customerId = getQueryString("customerId"); 
	console.log(customerId);
	var taskType ='';
	 $.ajax({//查询mongodb数据展示
				url:getCtxPath() + "/backend/mongo/getMongoDisplayInfo.do?v=" + new Date().getTime(),
				method: "post",
				dataType:"json",
				data: {
					taskType : "06006",//人行个人信用
					customerId : customerId //"111111" //"159357" //平台用户编号  
				},
				success : function(resultData){//成功处理函数 
	        	if(resultData == null){
	        	return ;
	        	}
	        	if(resultData.url == null && resultData.code != 200){
		        	return ;
		        	} 
	        	var basic_info = resultData.list.data.data.task_data.basic_info; 
	        	if(basic_info != null){//基本信息 
	        		$("#credential_id").html(basic_info.credential_id);
	        		$("#credential_type").html(basic_info.credential_type);
	        		$("#marital_status").html(basic_info.marital_status);
	        		$("#name").html(basic_info.name);
	        		$("#query_time").html(basic_info.query_time);
	        		$("#report_id").html(basic_info.report_id);
	        		$("#report_time").html(basic_info.report_time); 
	        	}
	        	//信用卡信息
	        	var credit_card_info = resultData.list.data.data.task_data.credit_card_info;
	        	if(credit_card_info != null){ 
	        		var riskCode = ""; 
					var table = "";
					var tableTemp=""; 
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>发放日期</td>" +
					"<td align='center'>银行</td>" +
					"<td align='center'>卡类型</td>" +
					"<td align='center'>账户币种</td>" +
					"<td align='center'>账户状态</td>" +
					"<td align='center'>销户日期</td>" +
					"<td align='center'>截止日期</td>" +
					"<td align='center'>信用额度</td>" +
					"<td align='center'>已用额度</td>" +
					"<td align='center'>逾期金额</td>" +
					"<td align='center'>逾期状态</td>" +
					"<td align='center'>逾期月份</td>" +
					"<td align='center'>逾期超过90天月份</td>" +
					"</tr>";
					var detail= credit_card_info.credit_card_detail;
					if(detail != null){  
						$.each(detail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.originate_date + "</td>" +
							"<td align='center'>" + v.bank + "</td>" +
							"<td align='center'>" + v.card_type + "</td>" +
							"<td align='center'>" + v.currency + "</td>" +
							"<td align='center'>" + v.account_status + "</td>" +
							"<td align='center'>" + v.settle_date + "</td>" +
							"<td align='center'>" + v.by_date + "</td>" +
							"<td align='center'>" + v.credit_quota + "</td>" +
							"<td align='center'>" + v.consumed_quota + "</td>" +
							"<td align='center'>" + v.overdue_balance + "</td>" +
							"<td align='center'>" + v.overdue_status + "</td>" +
							"<td align='center'>" + v.overdue_month + "</td>" +
							"<td align='center'>" + v.overdue_over90_month + "</td>" +
							"</tr>";
						});
					}
					$("#credit_card_info").html(html); 
				}else{
					html= tableTemp +'<tr style="width: 150px;">' 
					+ '<td align="center">发放日期</td>'
					+ '<td align="center">银行</td>'
					+ '<td align="center">卡类型</td>'
					+ '<td align="center">账户币种</td>'
					+ '<td align="center">账户状态</td>'
					+ '<td align="center">销户日期</td>'
					+ '<td align="center">截止日期</td>'
					+ '<td align="center">信用额度</td>'
					+ '<td align="center">已用额度</td>'
					+ '<td align="center">逾期金额</td>'
					+ '<td align="center">逾期状态</td>'
					+ '<td align="center">逾期月份</td>'
					+ '<td align="center">逾期超过90天月份</td>' 
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
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '</tr>';
					$("#credit_card_info").html(html);
				}
	        	//购房贷款信息
	        	var house_loan_info = resultData.list.data.data.task_data.house_loan_info;
	        	if(house_loan_info != null){ 
	        		var riskCode = "";
					// 获取RiskCode对象 
					var table = "";
					var tableTemp="";
					
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>发放日期</td>" +
					"<td align='center'>银行</td>" +
					"<td align='center'>贷款总额</td>" +
					"<td align='center'>贷款类型</td>" +
					"<td align='center'>贷款币种	</td>" +
					"<td align='center'>贷款状态</td>" +
					"<td align='center'>到期日期或结清日期</td>" +
					"<td align='center'>截止日期</td>" +
					"<td align='center'>贷款余额</td>" +
					"<td align='center'>逾期金额</td>" +
					"<td align='center'>逾期状态</td>" +
					"<td align='center'>逾期月份</td>" +
					"<td align='center'>逾期超过90天月份</td>" +
					"</tr>";
					var housedetail = house_loan_info.house_loan_detail;
					if(housedetail != null){  
						$.each(housedetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.originate_date + "</td>" +
							"<td align='center'>" + v.bank + "</td>" +
							"<td align='center'>" + v.loan_amount + "</td>" +
							"<td align='center'>" + v.loan_type + "</td>" +
							"<td align='center'>" + v.currency + "</td>" +
							"<td align='center'>" + v.loan_status + "</td>" +
							"<td align='center'>" + v.settle_date + "</td>" +
							"<td align='center'>" + v.by_date + "</td>" +
							"<td align='center'>" + v.balance + "</td>" +
							"<td align='center'>" + v.overdue_balance + "</td>" +
							"<td align='center'>" + v.overdue_status + "</td>" +
							"<td align='center'>" + v.overdue_month + "</td>" +
							"<td align='center'>" + v.overdue_over90_month + "</td>" +
							"</tr>";
						});
					}
					$("#house_loan_info").html(html); 
				}else{
					html= tableTemp +'<tr style="width: 150px;">' 
					"<td align='center'>发放日期</td>" +
					"<td align='center'>银行</td>" +
					"<td align='center'>贷款总额</td>" +
					"<td align='center'>贷款类型</td>" +
					"<td align='center'>贷款币种	</td>" +
					"<td align='center'>贷款状态</td>" +
					"<td align='center'>到期日期或结清日期</td>" +
					"<td align='center'>截止日期</td>" +
					"<td align='center'>贷款余额</td>" +
					"<td align='center'>逾期金额</td>" +
					"<td align='center'>逾期状态</td>" +
					"<td align='center'>逾期月份</td>" +
					"<td align='center'>逾期超过90天月份</td>" + 
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
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '</tr>';
					$("#house_loan_info").html(html);
				}
	        	
	        	
	        	//其他贷款信息
	        	var other_loan_info = resultData.list.data.data.task_data.other_loan_info;
	        	if(other_loan_info != null){ 
	        		var riskCode = ""; 
					var table = "";
					var tableTemp=""; 
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>发放日期</td>" +
					"<td align='center'>银行</td>" +
					"<td align='center'>贷款总额</td>" +
					"<td align='center'>贷款类型</td>" +
					"<td align='center'>贷款币种	</td>" +
					"<td align='center'>贷款状态</td>" +
					"<td align='center'>到期日期或结清日期</td>" +
					"<td align='center'>截止日期</td>" +
					"<td align='center'>贷款余额</td>" +
					"<td align='center'>逾期金额</td>" +
					"<td align='center'>逾期状态</td>" +
					"<td align='center'>逾期月份</td>" +
					"<td align='center'>逾期超过90天月份</td>" +
					"</tr>";
					var otherdetail = other_loan_info.other_loan_detail;
					if(otherdetail != null){   
						$.each(otherdetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.originate_date + "</td>" +
							"<td align='center'>" + v.bank + "</td>" +
							"<td align='center'>" + v.loan_amount + "</td>" +
							"<td align='center'>" + v.loan_type + "</td>" +
							"<td align='center'>" + v.currency + "</td>" +
							"<td align='center'>" + v.loan_status + "</td>" +
							"<td align='center'>" + v.settle_date + "</td>" +
							"<td align='center'>" + v.by_date + "</td>" +
							"<td align='center'>" + v.balance + "</td>" +
							"<td align='center'>" + v.overdue_balance + "</td>" +
							"<td align='center'>" + v.overdue_status + "</td>" +
							"<td align='center'>" + v.overdue_month + "</td>" +
							"<td align='center'>" + v.overdue_over90_month + "</td>" + 
							"</tr>";
						});
					}
					$("#other_loan_info").html(html); 
				}else{
					html= tableTemp +'<tr style="width: 150px;">' 
					"<td align='center'>发放日期</td>" +
					"<td align='center'>银行</td>" +
					"<td align='center'>贷款总额</td>" +
					"<td align='center'>贷款类型</td>" +
					"<td align='center'>贷款币种	</td>" +
					"<td align='center'>贷款状态</td>" +
					"<td align='center'>到期日期或结清日期</td>" +
					"<td align='center'>截止日期</td>" +
					"<td align='center'>贷款余额</td>" +
					"<td align='center'>逾期金额</td>" +
					"<td align='center'>逾期状态</td>" +
					"<td align='center'>逾期月份</td>" +
					"<td align='center'>逾期超过90天月份</td>" + 
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
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '</tr>';
					$("#other_loan_info").html(html);
				}
	        	
	        	//资产处置信息
	        	var assets_disposal_info = resultData.list.data.data.task_data.assets_disposal_info;
	        	if(assets_disposal_info != null){ 
	        		var riskCode = ""; 
					var table = "";
					var tableTemp=""; 
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>处置日期	</td>" +
					"<td align='center'>公司</td>" +
					"<td align='center'>金额</td>" +
					"<td align='center'>最近一次还款日期	</td>" +
					"<td align='center'>余额	</td>" + 
					"</tr>";
					var assetsdetail = assets_disposal_info.assets_disposal_detail;
					if(assetsdetail != null){  
						$.each(assetsdetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.disposal_date + "</td>" +
							"<td align='center'>" + v.company + "</td>" +
							"<td align='center'>" + v.amount + "</td>" +
							"<td align='center'>" + v.last_repay_date + "</td>" +
							"<td align='center'>" + v.balance + "</td>" + 
							"</tr>";
						});
					}
					$("#assets_disposal_info").html(html); 
				}else{
					html= tableTemp +'<tr style="width: 150px;">' 
					"<td align='center'>处置日期	</td>" +
					"<td align='center'>公司</td>" +
					"<td align='center'>金额</td>" +
					"<td align='center'>最近一次还款日期	</td>" +
					"<td align='center'>余额	</td>" + 
					+ '</tr>'
					+ '<tr>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '</tr>';
					$("#assets_disposal_info").html(html);
				}
	        	
	        	//保证人代偿信息
	        	var guarantor_compensation_info = resultData.list.data.data.task_data.guarantor_compensation_info;
	        	if(guarantor_compensation_info != null){ 
	        		var riskCode = ""; 
					var table = "";
					var tableTemp=""; 
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>最近一次代偿日期</td>" +
					"<td align='center'>公司</td>" +
					"<td align='center'>累计代偿金额</td>" +
					"<td align='center'>最近一次还款日期</td>" +
					"<td align='center'>余额	</td>" +  
					"</tr>";
					var guarantordetail = guarantor_compensation_info.guarantor_compensation_detail;
					if(guarantordetail != null){   
						$.each(guarantordetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.compensation_date + "</td>" +
							"<td align='center'>" + v.company + "</td>" +
							"<td align='center'>" + v.accumulate_amount + "</td>" +
							"<td align='center'>" + v.last_repay_date + "</td>" +
							"<td align='center'>" + v.balance + "</td>" +  
							"</tr>";
						});
					}
					$("#guarantor_compensation_info").html(html); 
				}else{
					html= tableTemp +'<tr style="width: 150px;">' 
					"<td align='center'>最近一次代偿日期</td>" +
					"<td align='center'>公司</td>" +
					"<td align='center'>累计代偿金额</td>" +
					"<td align='center'>最近一次还款日期</td>" +
					"<td align='center'>余额	</td>" +  
					+ '</tr>'
					+ '<tr>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>' 
					+ '</tr>';
					$("#guarantor_compensation_info").html(html);
				}
	        	
	        	//为他人担保信息
	        	var warrantor_info = resultData.list.data.data.task_data.warrantor_info;
	        	if(warrantor_info != null){ 
	        		var riskCode = ""; 
					var table = "";
					var tableTemp=""; 
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>担保日期	</td>" +
					"<td align='center'>被担保人姓名</td>" +
					"<td align='center'>被担保人证件类型</td>" +
					"<td align='center'>被担保人证件号码</td>" +
					"<td align='center'>银行	</td>" + 
					"<td align='center'>合同金额</td>" + 
					"<td align='center'>担保金额</td>" + 
					"<td align='center'>截止日期</td>" + 
					"<td align='center'>担保贷款本金金额</td>" + 
					"</tr>";
					var warrantordetail = warrantor_info.warrantor_detail;
					if(warrantordetail != null){  
						$.each(warrantordetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.warrant_date + "</td>" +
							"<td align='center'>" + v.warrantee_name + "</td>" +
							"<td align='center'>" + v.warrantee_credential_type + "</td>" +
							"<td align='center'>" + v.warrantee_credential_id + "</td>" +
							"<td align='center'>" + v.bank + "</td>" + 
							"<td align='center'>" + v.loan_amount + "</td>" + 
							"<td align='center'>" + v.warrant_amount + "</td>" + 
							"<td align='center'>" + v.by_date + "</td>" + 
							"<td align='center'>" + v.balance + "</td>" + 
							"</tr>";
						});
					}
					$("#warrantor_info").html(html); 
				}else{ 
					html= tableTemp +'<tr style="width: 150px;">' 
					"<td align='center'>担保日期	</td>" +
					"<td align='center'>被担保人姓名</td>" +
					"<td align='center'>被担保人证件类型</td>" +
					"<td align='center'>被担保人证件号码</td>" +
					"<td align='center'>银行	</td>" + 
					"<td align='center'>合同金额</td>" + 
					"<td align='center'>担保金额</td>" + 
					"<td align='center'>截止日期</td>" + 
					"<td align='center'>担保贷款本金金额</td>" + 
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
					+ '<td align="center"></td>'
					+ '</tr>';
					$("#warrantor_info").html(html);
				}
	        	
	        	
	        	//欠税记录
	        	var tax_unpaid_info = resultData.list.data.data.task_data.tax_unpaid_info;
	        	if(tax_unpaid_info != null){ 
	        		var riskCode = ""; 
					var table = "";
					var tableTemp=""; 
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>主管税务机关	</td>" +
					"<td align='center'>欠税统计时间</td>" +
					"<td align='center'>欠税总额</td>" +
					"<td align='center'>纳税人识别号</td>" + 
					"</tr>";
					var taxunpaiddetail = tax_unpaid_info.tax_unpaid_detail;
					if(taxunpaiddetail != null){  
						$.each(taxunpaiddetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.tax_authority + "</td>" +
							"<td align='center'>" + v.census_date + "</td>" +
							"<td align='center'>" + v.total_amount + "</td>" +
							"<td align='center'>" + v.taxpayer_id + "</td>" + 
							"</tr>";
						});
					}
					$("#tax_unpaid_info").html(html); 
				}else{ 
					html= tableTemp +'<tr style="width: 150px;">' 
					"<td align='center'>主管税务机关	</td>" +
					"<td align='center'>欠税统计时间</td>" +
					"<td align='center'>欠税总额</td>" +
					"<td align='center'>纳税人识别号</td>" + 
					+ '</tr>'
					+ '<tr>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '</tr>';
					$("#tax_unpaid_info").html(html);
				}
	        	
	        	//民事判决记录
	        	var civil_judgement_info = resultData.list.data.data.task_data.civil_judgement_info;
	        	if(civil_judgement_info != null){ 
	        		var riskCode = ""; 
					var table = "";
					var tableTemp=""; 
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>立案法院	</td>" +
					"<td align='center'>案号	</td>" +
					"<td align='center'>案由</td>" +
					"<td align='center'>结案方式	</td>" + 
					"<td align='center'>立案时间	</td>" + 
					"<td align='center'>判决/调解结果</td>" + 
					"<td align='center'>判决/调解生效时间</td>" + 
					"<td align='center'>诉讼标的</td>" + 
					"<td align='center'>诉讼标的金额</td>" + 
					"</tr>";
					var civildetail = civil_judgement_info.civil_judgement_detail;
					if(civildetail != null){  
						$.each(civildetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.court + "</td>" +
							"<td align='center'>" + v.case_id + "</td>" +
							"<td align='center'>" + v.case_description + "</td>" +
							"<td align='center'>" + v.case_close + "</td>" + 
							"<td align='center'>" + v.case_date + "</td>" + 
							"<td align='center'>" + v.judgement + "</td>" + 
							"<td align='center'>" + v.judgement_date + "</td>" + 
							"<td align='center'>" + v.object + "</td>" + 
							"<td align='center'>" + v.object_amount + "</td>" + 
							"</tr>";
						});
					}
					$("#civil_judgement_info").html(html); 
				}else{ 
					html= tableTemp +'<tr style="width: 150px;">' 
					"<td align='center'>立案法院	</td>" +
					"<td align='center'>案号	</td>" +
					"<td align='center'>案由</td>" +
					"<td align='center'>结案方式	</td>" + 
					"<td align='center'>立案时间	</td>" + 
					"<td align='center'>判决/调解结果</td>" + 
					"<td align='center'>判决/调解生效时间</td>" + 
					"<td align='center'>诉讼标的</td>" + 
					"<td align='center'>诉讼标的金额</td>" + 
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
					+ '<td align="center"></td>'
					+ '</tr>';
					$("#civil_judgement_info").html(html);
				}
	        	
	        	//强制执行记录
	        	var court_enforcement_info = resultData.list.data.data.task_data.court_enforcement_info;
	        	if(court_enforcement_info != null){ 
	        		var riskCode = ""; 
					var table = "";
					var tableTemp=""; 
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>执行法院	</td>" +
					"<td align='center'>案号	</td>" +
					"<td align='center'>执行案由</td>" +
					"<td align='center'>结案方式	</td>" + 
					"<td align='center'>立案时间	</td>" + 
					"<td align='center'>案件状态	</td>" + 
					"<td align='center'>申请执行标的	</td>" + 
					"<td align='center'>申请执行标的金额</td>" + 
					"<td align='center'>已执行标的</td>" + 
					"<td align='center'>已执行标的金额</td>" + 
					"<td align='center'>结案时间</td>" + 
					"</tr>";
					var courtdetail = court_enforcement_info.court_enforcement_detail;
					if(courtdetail != null){  
						$.each(courtdetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.court + "</td>" +
							"<td align='center'>" + v.case_id + "</td>" +
							"<td align='center'>" + v.case_description + "</td>" +
							"<td align='center'>" + v.case_close + "</td>" + 
							"<td align='center'>" + v.case_date + "</td>" + 
							"<td align='center'>" + v.case_status + "</td>" + 
							"<td align='center'>" + v.apply_object + "</td>" + 
							"<td align='center'>" + v.apply_object_amount + "</td>" + 
							"<td align='center'>" + v.enforcement_object + "</td>" + 
							"<td align='center'>" + v.enforcement_object_amount + "</td>" + 
							"<td align='center'>" + v.close_date + "</td>" +  
							"</tr>";
						});
					}
					$("#court_enforcement_info").html(html); 
				}else{ 
					html= tableTemp +'<tr style="width: 150px;">' 
					"<td align='center'>执行法院	</td>" +
					"<td align='center'>案号	</td>" +
					"<td align='center'>执行案由</td>" +
					"<td align='center'>结案方式	</td>" + 
					"<td align='center'>立案时间	</td>" + 
					"<td align='center'>案件状态	</td>" + 
					"<td align='center'>申请执行标的	</td>" + 
					"<td align='center'>申请执行标的金额</td>" + 
					"<td align='center'>已执行标的</td>" + 
					"<td align='center'>已执行标的金额</td>" + 
					"<td align='center'>结案时间</td>" + 
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
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '</tr>';
					$("#court_enforcement_info").html(html);
				}
	        	
	        	
	        	//行政处罚记录
	        	var admin_penalty_info = resultData.list.data.data.task_data.admin_penalty_info;
	        	if(admin_penalty_info != null){ 
	        		var riskCode = ""; 
					var table = "";
					var tableTemp=""; 
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>处罚机构	</td>" +
					"<td align='center'>文书编号	</td>" +
					"<td align='center'>处罚内容	</td>" +
					"<td align='center'>处罚金额	</td>" + 
					"<td align='center'>处罚生效时间</td>" + 
					"<td align='center'>处罚截止时间	</td>" + 
					"<td align='center'>是否行政复议</td>" + 
					"<td align='center'>行政复议结果</td>" + 
					"</tr>";
					var admindetail = admin_penalty_info.admin_penalty_detail;
					if(admindetail != null){  
						$.each(admindetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.administration + "</td>" +
							"<td align='center'>" + v.penalty_id + "</td>" +
							"<td align='center'>" + v.penalty_description + "</td>" +
							"<td align='center'>" + v.penalty_amount + "</td>" + 
							"<td align='center'>" + v.penalty_date + "</td>" + 
							"<td align='center'>" + v.penalty_close_date + "</td>" + 
							"<td align='center'>" + v.is_admin_review + "</td>" + 
							"<td align='center'>" + v.admin_review_result + "</td>" + 
							"</tr>";
						});
					}
					$("#admin_penalty_info").html(html); 
				}else{ 
					html= tableTemp +'<tr style="width: 150px;">' 
					"<td align='center'>处罚机构	</td>" +
					"<td align='center'>文书编号	</td>" +
					"<td align='center'>处罚内容	</td>" +
					"<td align='center'>处罚金额	</td>" + 
					"<td align='center'>处罚生效时间</td>" + 
					"<td align='center'>处罚截止时间	</td>" + 
					"<td align='center'>是否行政复议</td>" + 
					"<td align='center'>行政复议结果</td>" + 
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
					$("#admin_penalty_info").html(html);
				}
	        	
	        	
	        	//电信欠费信息
	        	var telecom_arrearage_info = resultData.list.data.data.task_data.telecom_arrearage_info;
	        	if(telecom_arrearage_info != null){ 
	        		var riskCode = ""; 
					var table = "";
					var tableTemp=""; 
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>电信运营商</td>" +
					"<td align='center'>业务类型	</td>" +
					"<td align='center'>业务开通时间	</td>" +
					"<td align='center'>记账年月</td>" + 
					"<td align='center'>欠费金额</td>" + 
					"</tr>";
					var telecomdetail = telecom_arrearage_info.telecom_arrearage_detail;
					if(telecomdetail != null){  
						$.each(telecomdetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.telecom_carrier + "</td>" +
							"<td align='center'>" + v.service_type + "</td>" +
							"<td align='center'>" + v.service_open_date + "</td>" +
							"<td align='center'>" + v.bill_date + "</td>" + 
							"<td align='center'>" + v.arrearage_amount + "</td>" + 
							"</tr>";
						});
					}
					$("#telecom_arrearage_info").html(html); 
				}else{ 
					html= tableTemp +'<tr style="width: 150px;">' 
					"<td align='center'>电信运营商</td>" +
					"<td align='center'>业务类型	</td>" +
					"<td align='center'>业务开通时间	</td>" +
					"<td align='center'>记账年月</td>" + 
					"<td align='center'>欠费金额</td>" + 
					+ '</tr>'
					+ '<tr>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>' 
					+ '</tr>';
					$("#telecom_arrearage_info").html(html);
				}
	        	
	        	//机构查询记录
	        	var institution_query_info = resultData.list.data.data.task_data.institution_query_info;
	        	if(telecom_arrearage_info != null){ 
	        		var riskCode = ""; 
					var table = "";
					var tableTemp=""; 
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>编号</td>" +
					"<td align='center'>查询日期	</td>" +
					"<td align='center'>查询操作员</td>" +
					"<td align='center'>查询原因</td>" +  
					"</tr>";
					var institutiondetail = institution_query_info.institution_query_detail;
					if(institutiondetail != null){   
						$.each(institutiondetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.id + "</td>" +
							"<td align='center'>" + v.date + "</td>" +
							"<td align='center'>" + v.operator + "</td>" +
							"<td align='center'>" + v.reason + "</td>" +  
							"</tr>";
						});
					}
					$("#institution_query_info").html(html); 
				}else{ 
					html= tableTemp +'<tr style="width: 150px;">'  
					"<td align='center'>编号</td>" +
					"<td align='center'>查询日期	</td>" +
					"<td align='center'>查询操作员</td>" +
					"<td align='center'>查询原因</td>" +  
					+ '</tr>'
					+ '<tr>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>' 
					+ '</tr>';
					$("#institution_query_info").html(html);
				}
	        	
	        	//个人查询记录
	        	var individual_query_info = resultData.list.data.data.task_data.individual_query_info;
	        	if(telecom_arrearage_info != null){ 
	        		var riskCode = ""; 
					var table = "";
					var tableTemp=""; 
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>编号</td>" +
					"<td align='center'>查询日期	</td>" +
					"<td align='center'>查询操作员</td>" +
					"<td align='center'>查询原因</td>" +  
					"</tr>";
					var individualdetail = individual_query_info.individual_query_detail;
					if(individualdetail != null){  
						$.each(individualdetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.id + "</td>" +
							"<td align='center'>" + v.date + "</td>" +
							"<td align='center'>" + v.operator + "</td>" +
							"<td align='center'>" + v.reason + "</td>" +  
							"</tr>";
						});
					}
					$("#individual_query_info").html(html); 
				}else{ 
					html= tableTemp +'<tr style="width: 150px;">'  
					"<td align='center'>编号</td>" +
					"<td align='center'>查询日期	</td>" +
					"<td align='center'>查询操作员</td>" +
					"<td align='center'>查询原因</td>" +  
					+ '</tr>'
					+ '<tr>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>' 
					+ '</tr>';
					$("#individual_query_info").html(html);
				}
	        }
				
			}); 
	 
	 /*var tdURL=getCtxPath() + "/index/js/customerManager/credit.json"; 
	    $.ajax({       
	        type :"GET",//请求方式  
	        url : tdURL,//请求路径  
	        dataType : "json",//数据格式  
	        success : function(resultData){//成功处理函数
	        	console.log(resultData.data.task_data);
	        	var basic_info = resultData.data.task_data.basic_info;
	        	if(basic_info != null){//基本信息 
	        		$("#credential_id").html(basic_info.credential_id);
	        		$("#credential_type").html(basic_info.credential_type);
	        		$("#marital_status").html(basic_info.marital_status);
	        		$("#name").html(basic_info.name);
	        		$("#query_time").html(basic_info.query_time);
	        		$("#report_id").html(basic_info.report_id);
	        		$("#report_time").html(basic_info.report_time); 
	        	}
	        	var credit_card_info = resultData.data.task_data.credit_card_info;
	        	if(credit_card_info != null){ 
	        		var riskCode = "";
					// 获取RiskCode对象
					var listRiskCode = resultData.data.task_data.credit_card_info;
					var table = "";
					var tableTemp="";
					
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>发放日期</td>" +
					"<td align='center'>银行</td>" +
					"<td align='center'>卡类型</td>" +
					"<td align='center'>账户币种</td>" +
					"<td align='center'>账户状态</td>" +
					"<td align='center'>销户日期</td>" +
					"<td align='center'>截止日期</td>" +
					"<td align='center'>信用额度</td>" +
					"</tr>";
					var detail= credit_card_info.credit_card_detail;
					if(detail != null){  
						$.each(detail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.originate_date + "</td>" +
							"<td align='center'>" + v.bank + "</td>" +
							"<td align='center'>" + v.card_type + "</td>" +
							"<td align='center'>" + v.currency + "</td>" +
							"<td align='center'>" + v.account_status + "</td>" +
							"<td align='center'>" + v.settle_date + "</td>" +
							"<td align='center'>" + v.by_date + "</td>" +
							"<td align='center'>" + v.credit_quota + "</td>" +
							"</tr>";
						});
					}
					$("#credit_card_info").html(html); 
				}else{
					html= tableTemp +'<tr style="width: 150px;">' 
					+ '<td align="center">发放日期</td>'
					+ '<td align="center">银行</td>'
					+ '<td align="center">卡类型</td>'
					+ '<td align="center">账户币种</td>'
					+ '<td align="center">账户状态</td>'
					+ '<td align="center">销户日期</td>'
					+ '<td align="center">截止日期</td>'
					+ '<td align="center">信用额度</td>'
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
					$("#credit_card_info").html(html);
				}
	        	
	        	var house_loan_info = resultData.data.task_data.house_loan_info;
	        	if(house_loan_info != null){ 
	        		var riskCode = "";
					// 获取RiskCode对象
					var listRiskCode = resultData.data.task_data.house_loan_info;
					var table = "";
					var tableTemp="";
					
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>发放日期</td>" +
					"<td align='center'>银行</td>" +
					"<td align='center'>贷款总额</td>" +
					"<td align='center'>贷款类型</td>" +
					"<td align='center'>贷款币种	</td>" +
					"<td align='center'>贷款状态</td>" +
					"<td align='center'>到期日期或结清日期</td>" +
					"<td align='center'>截止日期</td>" +
					"</tr>";
					var housedetail = house_loan_info.house_loan_detail;
					if(housedetail != null){  
						$.each(housedetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.originate_date + "</td>" +
							"<td align='center'>" + v.bank + "</td>" +
							"<td align='center'>" + v.loan_amount + "</td>" +
							"<td align='center'>" + v.loan_type + "</td>" +
							"<td align='center'>" + v.currency + "</td>" +
							"<td align='center'>" + v.loan_status + "</td>" +
							"<td align='center'>" + v.settle_date + "</td>" +
							"<td align='center'>" + v.by_date + "</td>" +
							"</tr>";
						});
					}
					$("#house_loan_info").html(html); 
				}else{
					html= tableTemp +'<tr style="width: 150px;">' 
					"<td align='center'>发放日期</td>" +
					"<td align='center'>银行</td>" +
					"<td align='center'>贷款总额</td>" +
					"<td align='center'>贷款类型</td>" +
					"<td align='center'>贷款币种	</td>" +
					"<td align='center'>贷款状态</td>" +
					"<td align='center'>到期日期或结清日期</td>" +
					"<td align='center'>截止日期</td>" +
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
					$("#house_loan_info").html(html);
				}
	        	
	        	
	        	
	        	var other_loan_info = resultData.data.task_data.other_loan_info;
	        	if(other_loan_info != null){ 
	        		var riskCode = "";
					// 获取RiskCode对象
					var listRiskCode = resultData.data.task_data.other_loan_info;
					var table = "";
					var tableTemp="";
					
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>发放日期</td>" +
					"<td align='center'>银行</td>" +
					"<td align='center'>贷款总额</td>" +
					"<td align='center'>贷款类型</td>" +
					"<td align='center'>贷款币种	</td>" +
					"<td align='center'>贷款状态</td>" +
					"<td align='center'>到期日期或结清日期</td>" +
					"<td align='center'>截止日期</td>" +
					"</tr>";
					var otherdetail = other_loan_info.other_loan_detail;
					if(otherdetail != null){   
						$.each(otherdetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.originate_date + "</td>" +
							"<td align='center'>" + v.bank + "</td>" +
							"<td align='center'>" + v.loan_amount + "</td>" +
							"<td align='center'>" + v.loan_type + "</td>" +
							"<td align='center'>" + v.currency + "</td>" +
							"<td align='center'>" + v.loan_status + "</td>" +
							"<td align='center'>" + v.settle_date + "</td>" +
							"<td align='center'>" + v.by_date + "</td>" +
							"</tr>";
						});
					}
					$("#other_loan_info").html(html); 
				}else{
					html= tableTemp +'<tr style="width: 150px;">' 
					"<td align='center'>发放日期</td>" +
					"<td align='center'>银行</td>" +
					"<td align='center'>贷款总额</td>" +
					"<td align='center'>贷款类型</td>" +
					"<td align='center'>贷款币种	</td>" +
					"<td align='center'>贷款状态</td>" +
					"<td align='center'>到期日期或结清日期</td>" +
					"<td align='center'>截止日期</td>" +
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
					$("#other_loan_info").html(html);
				}
	        	
	        	
	        	var assets_disposal_info = resultData.data.task_data.assets_disposal_info;
	        	if(assets_disposal_info != null){ 
	        		var riskCode = "";
					// 获取RiskCode对象
					var listRiskCode = resultData.data.task_data.assets_disposal_info;
					var table = "";
					var tableTemp="";
					
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>处置日期	</td>" +
					"<td align='center'>公司</td>" +
					"<td align='center'>金额</td>" +
					"<td align='center'>最近一次还款日期	</td>" +
					"<td align='center'>余额	</td>" + 
					"</tr>";
					var assetsdetail = assets_disposal_info.assets_disposal_detail;
					if(assetsdetail != null){  
						$.each(assetsdetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.disposal_date + "</td>" +
							"<td align='center'>" + v.company + "</td>" +
							"<td align='center'>" + v.amount + "</td>" +
							"<td align='center'>" + v.last_repay_date + "</td>" +
							"<td align='center'>" + v.balance + "</td>" + 
							"</tr>";
						});
					}
					$("#assets_disposal_info").html(html); 
				}else{
					html= tableTemp +'<tr style="width: 150px;">' 
					"<td align='center'>处置日期	</td>" +
					"<td align='center'>公司</td>" +
					"<td align='center'>金额</td>" +
					"<td align='center'>最近一次还款日期	</td>" +
					"<td align='center'>余额	</td>" + 
					+ '</tr>'
					+ '<tr>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '</tr>';
					$("#assets_disposal_info").html(html);
				}
	        	
	        	
	        	var guarantor_compensation_info = resultData.data.task_data.guarantor_compensation_info;
	        	if(guarantor_compensation_info != null){ 
	        		var riskCode = "";
					// 获取RiskCode对象
					var listRiskCode = resultData.data.task_data.guarantor_compensation_info;
					var table = "";
					var tableTemp="";
					
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>最近一次代偿日期</td>" +
					"<td align='center'>公司</td>" +
					"<td align='center'>累计代偿金额</td>" +
					"<td align='center'>最近一次还款日期</td>" +
					"<td align='center'>余额	</td>" +  
					"</tr>";
					var guarantordetail = guarantor_compensation_info.guarantor_compensation_detail;
					if(guarantordetail != null){ 
						console.log(guarantordetail);  
						$.each(guarantordetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.compensation_date + "</td>" +
							"<td align='center'>" + v.company + "</td>" +
							"<td align='center'>" + v.accumulate_amount + "</td>" +
							"<td align='center'>" + v.last_repay_date + "</td>" +
							"<td align='center'>" + v.balance + "</td>" +  
							"</tr>";
						});
					}
					$("#guarantor_compensation_info").html(html); 
				}else{
					html= tableTemp +'<tr style="width: 150px;">' 
					"<td align='center'>最近一次代偿日期</td>" +
					"<td align='center'>公司</td>" +
					"<td align='center'>累计代偿金额</td>" +
					"<td align='center'>最近一次还款日期</td>" +
					"<td align='center'>余额	</td>" +  
					+ '</tr>'
					+ '<tr>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>' 
					+ '</tr>';
					$("#guarantor_compensation_info").html(html);
				}
	        	
	        	
	        	var warrantor_info = resultData.data.task_data.warrantor_info;
	        	if(warrantor_info != null){ 
	        		var riskCode = "";
					// 获取RiskCode对象
					var listRiskCode = resultData.data.task_data.warrantor_info;
					var table = "";
					var tableTemp="";
					
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>担保日期	</td>" +
					"<td align='center'>被担保人姓名</td>" +
					"<td align='center'>被担保人证件类型</td>" +
					"<td align='center'>被担保人证件号码</td>" +
					"<td align='center'>银行	</td>" + 
					"<td align='center'>合同金额</td>" + 
					"<td align='center'>担保金额</td>" + 
					"<td align='center'>截止日期</td>" + 
					"</tr>";
					var warrantordetail = warrantor_info.warrantor_detail;
					if(warrantordetail != null){ 
						console.log(warrantordetail);  
						$.each(warrantordetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.warrant_date + "</td>" +
							"<td align='center'>" + v.warrantee_name + "</td>" +
							"<td align='center'>" + v.warrantee_credential_type + "</td>" +
							"<td align='center'>" + v.warrantee_credential_id + "</td>" +
							"<td align='center'>" + v.bank + "</td>" + 
							"<td align='center'>" + v.loan_amount + "</td>" + 
							"<td align='center'>" + v.warrant_amount + "</td>" + 
							"<td align='center'>" + v.by_date + "</td>" + 
							"</tr>";
						});
					}
					$("#warrantor_info").html(html); 
				}else{ 
					html= tableTemp +'<tr style="width: 150px;">' 
					"<td align='center'>担保日期	</td>" +
					"<td align='center'>被担保人姓名</td>" +
					"<td align='center'>被担保人证件类型</td>" +
					"<td align='center'>被担保人证件号码</td>" +
					"<td align='center'>银行	</td>" + 
					"<td align='center'>合同金额</td>" + 
					"<td align='center'>担保金额</td>" + 
					"<td align='center'>截止日期</td>" + 
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
					$("#warrantor_info").html(html);
				}
	        	
	        	
	        	
	        	var tax_unpaid_info = resultData.data.task_data.tax_unpaid_info;
	        	if(tax_unpaid_info != null){ 
	        		var riskCode = "";
					// 获取RiskCode对象
					var listRiskCode = resultData.data.task_data.tax_unpaid_info;
					var table = "";
					var tableTemp="";
					
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>主管税务机关	</td>" +
					"<td align='center'>欠税统计时间</td>" +
					"<td align='center'>欠税总额</td>" +
					"<td align='center'>纳税人识别号</td>" + 
					"</tr>";
					var taxunpaiddetail = tax_unpaid_info.tax_unpaid_detail;
					if(taxunpaiddetail != null){ 
						console.log(taxunpaiddetail);  
						$.each(taxunpaiddetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.tax_authority + "</td>" +
							"<td align='center'>" + v.census_date + "</td>" +
							"<td align='center'>" + v.total_amount + "</td>" +
							"<td align='center'>" + v.taxpayer_id + "</td>" + 
							"</tr>";
						});
					}
					$("#tax_unpaid_info").html(html); 
				}else{ 
					html= tableTemp +'<tr style="width: 150px;">' 
					"<td align='center'>主管税务机关	</td>" +
					"<td align='center'>欠税统计时间</td>" +
					"<td align='center'>欠税总额</td>" +
					"<td align='center'>纳税人识别号</td>" + 
					+ '</tr>'
					+ '<tr>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '</tr>';
					$("#tax_unpaid_info").html(html);
				}
	        	
	        	
	        	var civil_judgement_info = resultData.data.task_data.civil_judgement_info;
	        	if(civil_judgement_info != null){ 
	        		var riskCode = "";
					// 获取RiskCode对象
					var listRiskCode = resultData.data.task_data.civil_judgement_info;
					var table = "";
					var tableTemp="";
					
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>立案法院	</td>" +
					"<td align='center'>案号	</td>" +
					"<td align='center'>案由</td>" +
					"<td align='center'>结案方式	</td>" + 
					"<td align='center'>立案时间	</td>" + 
					"<td align='center'>判决/调解结果</td>" + 
					"<td align='center'>判决/调解生效时间</td>" + 
					"<td align='center'>诉讼标的</td>" + 
					"</tr>";
					var civildetail = civil_judgement_info.civil_judgement_detail;
					if(civildetail != null){ 
						console.log(civildetail);  
						$.each(civildetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.court + "</td>" +
							"<td align='center'>" + v.case_id + "</td>" +
							"<td align='center'>" + v.case_description + "</td>" +
							"<td align='center'>" + v.case_close + "</td>" + 
							"<td align='center'>" + v.case_date + "</td>" + 
							"<td align='center'>" + v.judgement + "</td>" + 
							"<td align='center'>" + v.judgement_date + "</td>" + 
							"<td align='center'>" + v.object + "</td>" + 
							"</tr>";
						});
					}
					$("#civil_judgement_info").html(html); 
				}else{ 
					html= tableTemp +'<tr style="width: 150px;">' 
					"<td align='center'>立案法院	</td>" +
					"<td align='center'>案号	</td>" +
					"<td align='center'>案由</td>" +
					"<td align='center'>结案方式	</td>" + 
					"<td align='center'>立案时间	</td>" + 
					"<td align='center'>判决/调解结果</td>" + 
					"<td align='center'>判决/调解生效时间</td>" + 
					"<td align='center'>诉讼标的</td>" + 
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
					$("#civil_judgement_info").html(html);
				}
	        	
	        	
	        	var court_enforcement_info = resultData.data.task_data.court_enforcement_info;
	        	if(court_enforcement_info != null){ 
	        		var riskCode = "";
					// 获取RiskCode对象
					var listRiskCode = resultData.data.task_data.court_enforcement_info;
					var table = "";
					var tableTemp="";
					
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>执行法院	</td>" +
					"<td align='center'>案号	</td>" +
					"<td align='center'>执行案由</td>" +
					"<td align='center'>结案方式	</td>" + 
					"<td align='center'>立案时间	</td>" + 
					"<td align='center'>案件状态	</td>" + 
					"<td align='center'>申请执行标的	</td>" + 
					"<td align='center'>申请执行标的金额</td>" + 
					"</tr>";
					var courtdetail = court_enforcement_info.court_enforcement_detail;
					if(courtdetail != null){ 
						console.log(courtdetail);  
						$.each(courtdetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.court + "</td>" +
							"<td align='center'>" + v.case_id + "</td>" +
							"<td align='center'>" + v.case_description + "</td>" +
							"<td align='center'>" + v.case_close + "</td>" + 
							"<td align='center'>" + v.case_date + "</td>" + 
							"<td align='center'>" + v.case_status + "</td>" + 
							"<td align='center'>" + v.apply_object + "</td>" + 
							"<td align='center'>" + v.apply_object_amount + "</td>" + 
							"</tr>";
						});
					}
					$("#court_enforcement_info").html(html); 
				}else{ 
					html= tableTemp +'<tr style="width: 150px;">' 
					"<td align='center'>执行法院	</td>" +
					"<td align='center'>案号	</td>" +
					"<td align='center'>执行案由</td>" +
					"<td align='center'>结案方式	</td>" + 
					"<td align='center'>立案时间	</td>" + 
					"<td align='center'>案件状态	</td>" + 
					"<td align='center'>申请执行标的	</td>" + 
					"<td align='center'>申请执行标的金额</td>" + 
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
					$("#court_enforcement_info").html(html);
				}
	        	
	        	
	        	
	        	var admin_penalty_info = resultData.data.task_data.admin_penalty_info;
	        	if(admin_penalty_info != null){ 
	        		var riskCode = "";
					// 获取RiskCode对象
					var listRiskCode = resultData.data.task_data.admin_penalty_info;
					var table = "";
					var tableTemp="";
					
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>处罚机构	</td>" +
					"<td align='center'>文书编号	</td>" +
					"<td align='center'>处罚内容	</td>" +
					"<td align='center'>处罚金额	</td>" + 
					"<td align='center'>处罚生效时间</td>" + 
					"<td align='center'>处罚截止时间	</td>" + 
					"<td align='center'>是否行政复议</td>" + 
					"<td align='center'>行政复议结果</td>" + 
					"</tr>";
					var admindetail = admin_penalty_info.admin_penalty_detail;
					if(admindetail != null){ 
						console.log(admindetail);  
						$.each(admindetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.administration + "</td>" +
							"<td align='center'>" + v.penalty_id + "</td>" +
							"<td align='center'>" + v.penalty_description + "</td>" +
							"<td align='center'>" + v.penalty_amount + "</td>" + 
							"<td align='center'>" + v.penalty_date + "</td>" + 
							"<td align='center'>" + v.penalty_close_date + "</td>" + 
							"<td align='center'>" + v.is_admin_review + "</td>" + 
							"<td align='center'>" + v.admin_review_result + "</td>" + 
							"</tr>";
						});
					}
					$("#admin_penalty_info").html(html); 
				}else{ 
					html= tableTemp +'<tr style="width: 150px;">' 
					"<td align='center'>处罚机构	</td>" +
					"<td align='center'>文书编号	</td>" +
					"<td align='center'>处罚内容	</td>" +
					"<td align='center'>处罚金额	</td>" + 
					"<td align='center'>处罚生效时间</td>" + 
					"<td align='center'>处罚截止时间	</td>" + 
					"<td align='center'>是否行政复议</td>" + 
					"<td align='center'>行政复议结果</td>" + 
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
					$("#admin_penalty_info").html(html);
				}
	        	
	        	
	        	
	        	var telecom_arrearage_info = resultData.data.task_data.telecom_arrearage_info;
	        	if(telecom_arrearage_info != null){ 
	        		var riskCode = "";
					// 获取RiskCode对象
					var listRiskCode = resultData.data.task_data.telecom_arrearage_info;
					var table = "";
					var tableTemp="";
					
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>电信运营商</td>" +
					"<td align='center'>业务类型	</td>" +
					"<td align='center'>业务开通时间	</td>" +
					"<td align='center'>记账年月</td>" + 
					"<td align='center'>欠费金额</td>" + 
					"</tr>";
					var telecomdetail = telecom_arrearage_info.telecom_arrearage_detail;
					if(telecomdetail != null){ 
						console.log(telecomdetail);  
						$.each(telecomdetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.telecom_carrier + "</td>" +
							"<td align='center'>" + v.service_type + "</td>" +
							"<td align='center'>" + v.service_open_date + "</td>" +
							"<td align='center'>" + v.bill_date + "</td>" + 
							"<td align='center'>" + v.arrearage_amount + "</td>" + 
							"</tr>";
						});
					}
					$("#telecom_arrearage_info").html(html); 
				}else{ 
					html= tableTemp +'<tr style="width: 150px;">' 
					"<td align='center'>电信运营商</td>" +
					"<td align='center'>业务类型	</td>" +
					"<td align='center'>业务开通时间	</td>" +
					"<td align='center'>记账年月</td>" + 
					"<td align='center'>欠费金额</td>" + 
					+ '</tr>'
					+ '<tr>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>' 
					+ '</tr>';
					$("#telecom_arrearage_info").html(html);
				}
	        	
	        	
	        	var institution_query_info = resultData.data.task_data.institution_query_info;
	        	if(telecom_arrearage_info != null){ 
	        		var riskCode = "";
					// 获取RiskCode对象
					var listRiskCode = resultData.data.task_data.institution_query_info;
					var table = "";
					var tableTemp="";
					
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>编号</td>" +
					"<td align='center'>查询日期	</td>" +
					"<td align='center'>查询操作员</td>" +
					"<td align='center'>查询原因</td>" +  
					"</tr>";
					var institutiondetail = institution_query_info.institution_query_detail;
					if(institutiondetail != null){ 
						console.log(institutiondetail);  
						$.each(institutiondetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.id + "</td>" +
							"<td align='center'>" + v.date + "</td>" +
							"<td align='center'>" + v.operator + "</td>" +
							"<td align='center'>" + v.reason + "</td>" +  
							"</tr>";
						});
					}
					$("#institution_query_info").html(html); 
				}else{ 
					html= tableTemp +'<tr style="width: 150px;">'  
					"<td align='center'>编号</td>" +
					"<td align='center'>查询日期	</td>" +
					"<td align='center'>查询操作员</td>" +
					"<td align='center'>查询原因</td>" +  
					+ '</tr>'
					+ '<tr>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>' 
					+ '</tr>';
					$("#institution_query_info").html(html);
				}
	        	
	        	
	        	var individual_query_info = resultData.data.task_data.individual_query_info;
	        	if(telecom_arrearage_info != null){ 
	        		var riskCode = "";
					// 获取RiskCode对象
					var listRiskCode = resultData.data.task_data.individual_query_info;
					var table = "";
					var tableTemp="";
					
					var html ="";
					html = tableTemp + "<tr style='width: 150px;'>" + 
					"<td align='center'>编号</td>" +
					"<td align='center'>查询日期	</td>" +
					"<td align='center'>查询操作员</td>" +
					"<td align='center'>查询原因</td>" +  
					"</tr>";
					var individualdetail = individual_query_info.individual_query_detail;
					if(individualdetail != null){ 
						console.log(individualdetail);  
						$.each(individualdetail, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.id + "</td>" +
							"<td align='center'>" + v.date + "</td>" +
							"<td align='center'>" + v.operator + "</td>" +
							"<td align='center'>" + v.reason + "</td>" +  
							"</tr>";
						});
					}
					$("#individual_query_info").html(html); 
				}else{ 
					html= tableTemp +'<tr style="width: 150px;">'  
					"<td align='center'>编号</td>" +
					"<td align='center'>查询日期	</td>" +
					"<td align='center'>查询操作员</td>" +
					"<td align='center'>查询原因</td>" +  
					+ '</tr>'
					+ '<tr>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>' 
					+ '</tr>';
					$("#individual_query_info").html(html);
				}
	        }
	     
	    })*/
	
	  }); 
	 	 
	  

 
