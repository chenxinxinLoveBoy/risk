layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
				function() {
					var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
					layer = layui.layer, // 获取当前窗口的layer对象
					form = layui.form(), element = layui.element();
				});
var applicationId ="";
var platformId ="";
$(function() {
	applicationId= getQueryString("applicationId");//获取页面传过来的申请单编号
	getMysqlData();
})
function allDealsHtml(deals){
	var html = '';
	$.each(deals,function(k,v){
		html+='<tr>';
			html+='<th scope="col">贷款项目</th>';
			html+='<th scope="col">机构名称</th>';
			html+='<th scope="col">授信额度</th>';
			html+='<th scope="col">担保方式</th>';
			html+='<th scope="col">开户日期</th>';
			html+='<th scope="col">币种</th>';
			html+='<th scope="col">发生地</th>';
		html+='</tr>';
		html+='<tr>';
			html+='<td>'+v.loansProject+'</td>';
			html+='<td>'+v.organizationName+'</td>';
			html+='<td>'+v.limitMoney+'</td>';
			html+='<td>'+v.guaranteeType+'</td>';
			html+='<td>'+v.openDate+'</td>';
			html+='<td>'+v.currency+'</td>';
			html+='<td>'+v.locality+'</td>';
		html+='</tr>';
		html+='<tr>';
			html+='<th scope="col">共享授信额度</th>';
			html+=' <th scope="col">最大负债额</th>';
			html+='<th scope="col">还款频率</th>';
			html+='<th scope="col">期末贷款余额</th>';
			html+='<th scope="col">剩余还款月数</th>';
			html+='<th scope="col">本月应还款日期</th>';
			html+='<th scope="col">本月应还款金额</th>';
			html+='</tr>';
			html+='<tr>';
			html+='<td>'+v.shareLimitMoney+'</td>';
			html+='<td>'+v.maxLiabilitiesMoney+'</td>';
			html+='<td>'+v.repaymentFrequency+'</td>';
			html+='<td>'+v.endingIoanBalance+'</td>';
			html+='<td>'+v.leftTermsLoan+'</td>';
			html+='<td>'+v.repaymentDateMonth+'</td>';
			html+='<td>'+v.repaymentAmountMonth+'</td>';
		html+='</tr>';
		html+='<tr>';
			html+='<th scope="col">账户状态</th>';
			html+=' <th scope="col">实际还款日期</th>';
			html+='<th scope="col">实际还款金额</th>';
			html+='<th scope="col">当前逾期总额</th>';
			html+='<th scope="col">当前逾期期数</th>';
			html+='<th scope="col">累计逾期期数</th>';
			html+='<th scope="col">最高逾期期数</th>';
		html+='</tr>';
		html+='<tr>';
			html+='<td>'+v.accountStatus+'</td>';
			html+='<td>'+v.actualDateRepayment+'</td>';
			html+='<td>'+v.actualPaymentAmount+'</td>';
			html+='<td>'+v.nowOverdueLimit+'</td>';
			html+='<td>'+v.nowOverdueNumber+'</td>';
			html+='<td>'+v.totalOverdueNumber+'</td>';
			html+='<td>'+v.maxOverdueNumber+'</td>';
		html+='</tr>';
		html+='<tr>';
		html+='<th scope="col">24月内各月还款状态</th>';
		html+=' <th scope="col">逾期31-60天未归还贷款本金</th>';
		html+='<th scope="col">逾期61-90天未归还贷款本金</th>';
		html+='<th scope="col">逾期91-180天未归还贷款本金</th>';
		html+='<th scope="col">逾期180以上天未归还贷款本金</th>';
		html+='<th scope="col">信息获取日期</th>';
	html+='</tr>';
	html+='<tr>';
		html+='<td>'+v.paymentStatus+'</td>';
		html+='<td>'+v.overdueTwoMonth+'</td>';
		html+='<td>'+v.overdueThreeMonth+'</td>';
		html+='<td>'+v.overdueSixMonth+'</td>';
		html+='<td>'+v.overdueYearMonth+'</td>';
		html+='<td>'+v.loansTime+'</td>';
		html+='</tr>';
		html+='<tr>';
		html+='<td colspan="7" style="background:none; border:none;">&nbsp;</td>';
		html+='<tr>';
	});
	$("#deals").html(html);
}

function getMysqlData(){
	$.ajax({
		url : getCtxPath() + "/backend/shCredit/getAllDealsByApplicationId.do",
		method : "post",
		dataType : "json",
		data : {
			applicationId : applicationId,
		},
		success : function(data) {
			var obj = data.list.data;
			allDealsHtml(obj);
		},
		error : function() {
			alert("获取全部贷款信息失败,请尝试重新获取！");
		}
	});	
}