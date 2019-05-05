layui.config({
	base : '../../js/'
});
layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
			layer = layui.layer, // 获取当前窗口的layer对象
			form = layui.form(), element = layui.element();
		});

function convertToNull(value)
{
	if(value==null||typeof(value)=="undefined"||Object.prototype.toString.call(value) == "[object Object]") 
	{
		return "";
	}
	else
	{
		return value;
	}
} 
$(function() {
	var customerId = getQueryString("customerId");//客户编号
	$.ajax({
		url : getCtxPath() + "/backend/mongo/getMongoDisplayInfo.do?v=" + new Date().getTime(),
		method : "post",
		dataType : "json",
		data : {
			customerId : customerId,
			taskType:"06005"
		},
		success : function(data) {
			if(data.list===undefined)
		    {
		    	return ;
		    }
			var mhGjjBaseInfo = data.list.data.data.task_data.base_info;
			var pay_info=data.list.data.data.task_data.pay_info;
			var bill_record=data.list.data.data.task_data.bill_record;
			var loan_info=data.list.data.data.task_data.loan_info;
			var repayment=data.list.data.data.task_data.repayment;
			if(mhGjjBaseInfo!=null)
			{
				$("#custNo").text(convertToNull(mhGjjBaseInfo.cust_no));
				$("#name").text(convertToNull(mhGjjBaseInfo.name));
				$("#gender").text(convertToNull(mhGjjBaseInfo.gender));
				$("#birthday").text(convertToNull(mhGjjBaseInfo.birthday));
				$("#certNo").text(convertToNull(mhGjjBaseInfo.cert_no));
				$("#mobile").text(convertToNull(mhGjjBaseInfo.mobile));
				$("#email").text(convertToNull(mhGjjBaseInfo.email));
				$("#state").text(convertToNull(mhGjjBaseInfo.pay_status_desc));
				$("#postNo").text(convertToNull(mhGjjBaseInfo.postNo));
				$("#monthlyCorpIncome").text(convertToNull(mhGjjBaseInfo.monthly_cust_income));
				$("#monthlyCustIncome").text(convertToNull(mhGjjBaseInfo.monthly_cust_income));
				$("#monthlyTotalIncome").text(convertToNull(mhGjjBaseInfo.monthly_total_income));
				$("#balance").text(convertToNull(mhGjjBaseInfo.balance));
				$("#corpName").text(convertToNull(mhGjjBaseInfo.corp_name));
				$("#homeAddress").text(convertToNull(mhGjjBaseInfo.home_address));
			}
			if(pay_info != null && pay_info != ''){
				var clInfoHtml=$("#content").html();
				$.each(pay_info, function(k, v){ 
					clInfoHtml += "<tr>" + 
		            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.corp_name) + "</td>" +    
		            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.pay_status_desc) + "</td>" + 
		            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.pay_status) + "</td>" + 
		            "</tr>";
				});
				$("#content").html(clInfoHtml);
			}
		  if(bill_record != null && bill_record != ''){
			var bill_record_info=$("#content1").html();
			$.each(bill_record, function(k, v){ 
				bill_record_info += "<tr>" + 
	            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.deal_time) + "</td>" +    
	            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.desc) + "</td>" + 
	            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.income) + "</td>" + 
	            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.outcome) + "</td>" +    
	            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.balance) + "</td>" + 
	            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.corp_name) + "</td>" + 
	            "</tr>";
			});
			$("#content1").html(bill_record_info);
		  }
			//贷款明细
		  if(loan_info != null && loan_info != ''){
			var loan=$("#content2").html();
			$.each(loan_info, function(k, v){ 
			loan+="<tr><td align='center' style='white-space: nowrap;'>" + convertToNull(v.contract_no) + "</td>" +    
            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.status) + "</td>" + 
            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.delegate_bank) + "</td>" + 
            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.loan_amount) + "</td>" +    
            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.periods) + "</td>" + 
            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.start_date) + "</td>" + 
            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.end_date) + "</td>" +    
            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.deduct_day) + "</td>" + 
            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.repay_type_desc) + "</td>" + 
            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.loan_interest_percent) + "</td>" +    
            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.penalty_interest_percent) + "</td>" + 
            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.sign_date) + "</td>"+ 
			"<td align='center' style='white-space: nowrap;'>" + convertToNull(v.house_address) + "</td></tr>";
			});
			$("#content2").html(loan);
		  }
		  if(repayment != null && repayment != ''){
			var repaymenthtml=$("#content3").html();
			$.each(repayment, function(k, v){ 
				repaymenthtml+="<tr><td align='center' style='white-space: nowrap;'>" + convertToNull(v.accountingDate) + "</td>" +    
	            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.repayAmount) + "</td>" + 
	            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.repayCapital) + "</td>" + 
	            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.repayInterest) + "</td>" +    
	            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.repayPenalty) + "</td>" + 
	            "<td align='center' style='white-space: nowrap;'>" + convertToNull(v.repayDate) + "</td></tr>";
				});
			$("#content3").html(repaymenthtml);
		  };
//			<tr><td align='center' style='white-space: nowrap;'>" + loan_info.contract_no + "</td>" +    
//            "<td align='center' style='white-space: nowrap;'>" + loan_info.status + "</td>" + 
//            "<td align='center' style='white-space: nowrap;'>" + loan_info.delegate_bank + "</td></tr>" + 
//            "<tr><td align='center' style='white-space: nowrap;'>" + loan_info.loan_amount + "</td>" +    
//            "<td align='center' style='white-space: nowrap;'>" + loan_info.periods + "</td>" + 
//            "<td align='center' style='white-space: nowrap;'>" + loan_info.start_date + "</td></tr>" + 
//            "<tr><td align='center' style='white-space: nowrap;'>" + loan_info.end_date + "</td>" +    
//            "<td align='center' style='white-space: nowrap;'>" + loan_info.deduct_day + "</td>" + 
//            "<td align='center' style='white-space: nowrap;'>" + loan_info.repay_type_desc + "</td></tr>" + 
//            "<tr><td align='center' style='white-space: nowrap;'>" + loan_info.loan_interest_percent + "</td>" +    
//            "<td align='center' style='white-space: nowrap;'>" + loan_info.penalty_interest_percent + "</td>" + 
//            "<td align='center' style='white-space: nowrap;'>" + loan_info.sign_date + "</td></tr>"+ 
//			"<tr><td align='center' style='white-space: nowrap;'>" + loan_info.house_address + "</td></tr>";
		},
		error : function() {
			alert("获取用户信息失败,请尝试重新获取！");
		}
	});
});
