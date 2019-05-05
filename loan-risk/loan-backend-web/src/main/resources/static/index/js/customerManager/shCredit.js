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
function cleanData(){
	$("#userType").html("-");
}
function shCreditHtml(shCredit){
	$("#reportNumber").html(shCredit.reportNumber);
	$("#reportTime").html(shCredit.reportTime);
	$("#name").html(shCredit.name);
	$("#certCode").html(shCredit.certCode+"("+shCredit.certType+")");
	$("#sex").html(shCredit.sex);
	$("#birthTime").html(shCredit.birthTime);
	$("#marriageDetail").html(shCredit.marriageDetail);
	$("#marriageTime").html(shCredit.marriageTime);
	$("#education").html(shCredit.education);
	$("#educationTime").html(shCredit.educationTime);
	$("#titleDetail").html(shCredit.titleDetail);
	$("#titleTime").html(shCredit.titleTime);
	$("#residencePhone").html(shCredit.residencePhone);
	$("#residenceTime").html(shCredit.residenceTime);
	$("#phoneNumber").html(shCredit.phoneNumber);
	$("#phoneTime").html(shCredit.phoneTime);
	$("#emailAddress").html(shCredit.emailAddress);
	$("#emailTime").html(shCredit.emailTime);
}
function mateHtml(shCredit){
	$("#mateName").html(shCredit.mateName);
	$(".mateWorkTime").html(shCredit.mateWorkTime);
	$("#mateCertCode").html("");
	if (shCredit.mateCertCode != "") {
		$("#mateCertCode").html(shCredit.mateCertCode+"("+shCredit.mateCertType+")");
	}
	$("#mateSex").html(shCredit.mateSex);
	$("#mateBirthTime").html(shCredit.mateBirthTime);
	$("#mateWork").html(shCredit.mateWork);
	$("#matePhone").html(shCredit.matePhone);
}
function addressHtml(address){
	var html='';
	$.each(address, function(k, v){
		html+='<tr>'
		html+='<td>'+v.addressNumber+'</td>';
		html+='<td>'+v.addressDetail+'</td>';
		html+='<td>'+v.addressTime+'</td>';
		html+='</tr>';
	});
	$("#address").html(html);
}
function worksHtml(works){
	
	var html='';
	$.each(works, function(k, v){
		html+='<tr>'
		html+='<td>'+v.workDetail+'</td>';
		html+='<td>'+v.workProfession+'</td>';
		html+='<td>'+v.workTime+'</td>';
		html+='</tr>';
	});
	$("#works").html(html);
}
function contactsHtml(contacts){
	var html1='';
	var html2='';
	$.each(contacts, function(k, v){
		if (v.contactsType == 1) {
			html1+='<tr>'
				html1+='<td>'+v.contactsName+'</td>';
				html1+='<td>'+v.contactsRelation+'</td>';
				html1+='<td>'+v.contactsNumber+'</td>';
				html1+='<td>'+v.contactsTime+'</td>';
				html1+='</tr>';
		}else{
			html2+='<tr>'
				html2+='<td>'+v.contactsName+'</td>';
				html2+='<td>'+v.contactsRelation+'</td>';
				html2+='<td>'+v.contactsNumber+'</td>';
				html2+='<td>'+v.contactsTime+'</td>';
				html2+='</tr>';
		}
	});
	$("#contact1").html(html1);
	$("#contact2").html(html2);
}
function searchHtml(searchInfor){
	var html='';
	$.each(searchInfor, function(k, v){
			html+='<tr>'
				html+='<td>'+v.querierType+'</td>';
				html+='<td>'+v.cause+'</td>';
				html+='<td>'+v.queryDate+'</td>';
				html+='</tr>';
	});
	$("#searchInformation").html(html);
}
function loansDealHtml(loansDeal){
	var html='';
	if (loansDeal != null) {
		$("#loansTemps").html(loansDeal.loansTemps);
		html+='<tr>'
			html+='<td>'+loansDeal.loansNumber+'</td>';
			html+='<td>'+loansDeal.maxLimit+'</td>';
			html+='<td>'+loansDeal.loansTotal+'</td>';
			html+='<td>'+loansDeal.loansBalance+'</td>';
			html+='<td>'+loansDeal.appointRefundMonth+'</td>';
			html+='<td>'+loansDeal.overdueAllMoney+'</td>';
			html+='<td>'+loansDeal.maxOverdueMoney+'</td>';
			html+='<td>'+loansDeal.maxOverdueNumber+'</td>';
		html+='</tr>';
	}
	$("#loansDeal").html(html);
}
function dealsHtml(deals){
	var html = [];
	$.each(deals,function(k,v){
		html.push('<tr>');
		html.push('<td colspan="7">'+ v.loansProject +'</td>');
		html.push('<tr>');
		html.push('<tr>');
			html.push('<th scope="col">账户状态</th>');
			html.push('<th scope="col">当前逾期状态</th>');
			html.push('<th scope="col">机构名称</th>');
			html.push('<th scope="col">授信额度</th>');
			html.push('<th scope="col">担保方式</th>');
			html.push('<th scope="col">开户日期</th>');
			html.push('<th scope="col">币种</th>');
		html.push('</tr>');
		html.push('<tr>');
			html.push('<td>'+v.accountStatus+'</td>');
			var a = v.actualPaymentAmount*100;//实际还款金额
			var b = v.repaymentAmountMonth*100;//本月应还款金额
			var c = a - b;
			if (c < 0) {
				html.push('<td style="color:red;">是</td>');
			}else{
				html.push('<td>否</td>');
			}
			html.push('<td>'+v.organizationName+'</td>');
			html.push('<td>'+v.limitMoney+'</td>');
			html.push('<td>'+v.guaranteeType+'</td>');
			html.push('<td>'+v.openDate+'</td>');
			html.push('<td>'+v.currency+'</td>');
		html.push('</tr>');
		html.push('<tr>');
			html.push('<th scope="col">发生地</th>');
			html.push(' <th scope="col">期末贷款余额</th>');
			html.push('<th scope="col">剩余还款月数</th>');
			html.push('<th scope="col">本月应还金额</th>');
			html.push('<th scope="col">实际还款金额</th>');
			html.push('<th scope="col">最高逾期期数</th>');
			html.push('<th scope="col">2年内还款情况</th>');
			html.push('</tr>');
			html.push('<tr>');
			html.push('<td>'+v.locality+'</td>');
			html.push('<td>'+v.endingIoanBalance+'</td>');
			html.push('<td>'+v.leftTermsLoan+'</td>');
			html.push('<td>'+v.repaymentAmountMonth+'</td>');
			html.push('<td>'+v.actualPaymentAmount+'</td>');
			html.push('<td>'+v.maxOverdueNumber+'</td>');
			html.push('<td>'+v.paymentStatus+'</td>');
		html.push('</tr>');
		html.push('<tr>');
		html.push('<td colspan="7" style="background:none; border:none;">&nbsp;</td>');
		html.push('<tr>');
		if (k == 4) {
			 return false;
		}
	});
	$("#deals").html(html.join(''));
}
function guaranteeHtml(guarantee){
	var html=[];
	$.each(guarantee, function(k, v){
			html.push('<tr>');
				html.push('<td>'+v.guaranteeProject+'</td>');
				html.push('<td>'+v.guaranteeDate+'</td>');
				html.push('<td>'+v.guaranteeMoney+'</td>');
				html.push('<td>'+v.guaranteeRelation+'</td>');
				html.push('<td>'+v.guaranteeTime+'</td>');
				html.push('</tr>');
				if (k == 4) {
					 return false;
				}
	});
	$("#guarantee").html(html.join(''));
}
function specialDealHtml(specialDeal){
	var html=[];
	$.each(specialDeal, function(k, v){
			html.push('<tr>');
				html.push('<td>'+v.recordSource+'</td>');
				html.push('<td>'+v.recordType+'</td>');
				html.push('<td>'+v.occurrenceDate+'</td>');
				html.push('<td>'+v.changeMonths+'</td>');
				html.push('<td>'+v.actualAmount+'</td>');
				html.push('<td>'+v.detail+'</td>');
				html.push('<td>'+v.specialDealTime+'</td>');
				html.push('</tr>');
				if (k == 4) {
					 return false;
				}
	});
	$("#specialDeal").html(html.join(''));
}
function promptHtml(prompt){
	var html=[];
	$.each(prompt, function(k, v){
			html.push('<tr>');
				html.push('<td>'+v.promptProject+'</td>');
				html.push('<td>'+v.promptMessage+'</td>');
				html.push('<td>'+v.promptTime+'</td>');
				html.push('</tr>');
	});
	$("#prompt").html(html.join(''));
}
function getMysqlData(){
	cleanData();
	$.ajax({
		url : getCtxPath() + "/backend/shCredit/getShCreditByApplicationId.do",
		method : "post",
		dataType : "json",
		data : {
			applicationId : applicationId,
		},
		success : function(data) {
			var obj = data.list.data;
			console.log(obj);
			var shCredit = obj.shCredit;
			if (shCredit != null) {
				shCreditHtml(shCredit);//用户信息
			}
			var address = obj.address;
			addressHtml(address);//用户地址信息
			var works = obj.works;
			worksHtml(works);//用户工作信息
			if (shCredit != null) {
				mateHtml(shCredit);//配偶信息
			}
			var contacts = obj.contacts;
			contactsHtml(contacts);//联系人信息
			var searchInfor = obj.saveSearchInformation;
			searchHtml(searchInfor);
			var loansDeal = obj.saveLoansDeal;
			loansDealHtml(loansDeal);
			var deals = obj.savaLoans;
			dealsHtml(deals);
			var guarantee = obj.saveGuarantee;
			guaranteeHtml(guarantee);
			var specialDeal = obj.saveSpecialDeal;
			specialDealHtml(specialDeal);
			var prompt = obj.saveShCreditPrompt;
			promptHtml(prompt);
		},
		error : function() {
			alert("获取上海资信报告页面,请尝试重新获取！");
		}
	});	
}
//贷款交易
$('#allDeals')[0].onclick = function(){  
	  applicationId= getQueryString("applicationId");
	  var href = getCtxPath() + "/index/views/customerManager/allDeals.html?applicationId="+applicationId;
	  var index=layer.open({
		  type: 2,
		  closeBtn: 1, //0：不显示关闭按钮
		  shade: 0.4, //遮罩透明度
		  area: ['1400px', '615px'],
		  skin: 'layui-layer-rim', //加上边框z
		  fixed: false, //不固定
		  maxmin: true, //开启最大化最小化按钮
		  anim: 2,
		  title: name+'-上海资信详细贷款记录',// 设置false表示不显示
		  content:href
	});
	};
//担保交易
$('#allGuarantee')[0].onclick = function(){  
	  applicationId= getQueryString("applicationId");
	  var href = getCtxPath() + "/index/views/customerManager/allGuarantee.html?applicationId="+applicationId;
	  var index=layer.open({
		  type: 2,
		  closeBtn: 1, //0：不显示关闭按钮
		  shade: 0.4, //遮罩透明度
		  area: ['840px', '615px'],
		  skin: 'layui-layer-rim', //加上边框
		  fixed: false, //不固定
		  maxmin: true, //开启最大化最小化按钮
		  anim: 2,
		  title: name+'-上海资信详细担保记录',// 设置false表示不显示
		  content:href
	});
	};
//特殊交易
$('#allSpecialDeal')[0].onclick = function(){  
	  applicationId= getQueryString("applicationId");
	  var href = getCtxPath() + "/index/views/customerManager/allSpecialDeal.html?applicationId="+applicationId;
	  var index=layer.open({
		  type: 2,
		  closeBtn: 1, //0：不显示关闭按钮
		  shade: 0.4, //遮罩透明度
		  area: ['840px', '615px'],
		  skin: 'layui-layer-rim', //加上边框
		  fixed: false, //不固定
		  maxmin: true, //开启最大化最小化按钮
		  anim: 2,
		  title: name+'-上海资信特殊交易记录',// 设置false表示不显示
		  content:href
	});
	};