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
				url : getCtxPath() + "/backend/jxl/getReport.do",
				method : "post",
				dataType : "json",
				data : {
					platformCustomerId : platformCustomerId,
					applicationId : applicationId
				},
				success : function(msg) {
					if(msg.code == '200' && msg.list.successed){
						var data = msg.list;
						var jxlAuditReport = data.jxlAuditReport;//报告
						var jxlBehaviorData = data.jxlBehaviorData;//行为表现
						var jxlBlackData = data.jxlBlackData[0];//黑名单数据
						var jxlCallData = data.jxlCallData;//通话记录数据
						var jxlCellPhoneCheck = data.jxlCellPhoneCheck[0];//电话号码检测
						var jxlContactCheck = data.jxlContactCheck;	//紧急联系人检测
						var jxlHomeAddressCheck = data.jxlHomeAddressCheck[0];//家庭地址检测
						var jxlHomePhoneCheck = data.jxlHomePhoneCheck[0];//家庭电话检测
						var jxlIdCardCheck = data.jxlIdCardCheck[0];//身份证检测
						var jxlLinkmanArea = data.jxlLinkmanArea;//联系人区域检测
						var jxlLinkmanData = data.jxlLinkmanData;//联系人检测
						var jxlLinkmanDataDetails = data.jxlLinkmanDataDetails;//联系详情
						var jxlMainService = data.jxlMainService;//服务
						var jxlMainServiceDetails = data.jxlMainServiceDetails;//服务详情
						var jxlMonitorData = data.jxlMonitorData[0];//
						var jxlNetAccount = data.jxlNetAccount;//购物账号
						var jxlNetBusiData = data.jxlNetBusiData;//购物信息
						var jxlPhoneConsume = data.jxlPhoneConsume;//电话消费记录
						var jxlReceiptAddress = data.jxlReceiptAddress;//收获地址
						var jxlReceiptAddressReceiver = data.jxlReceiptAddressReceiver;//收货地址收货人列表
						var jxlTravelData = data.jxlTravelData;//出行数据

						//报告编号
						$("#user_useId").html(jxlAuditReport.useId);
						$("#user_date").html(getDateStrFormat(jxlAuditReport.reportTime));
						$("#user_version").html(jxlAuditReport.version);
						
						var userInfoHtml = "";
						//姓名
						userInfoHtml += "<tr>";
						userInfoHtml += "<td style=\"width: 340px\">姓名</td>";
						userInfoHtml +="<td>"+jxlAuditReport.name+"</td>"
						userInfoHtml += "</tr>";
						userInfoHtml += "<tr>";
						//身份证
						userInfoHtml += "<td style=\"width: 340px\">身份证</td>";
						var desc = "";
						if(jxlIdCardCheck.financialBlacklist !== undefined && jxlIdCardCheck.financialBlacklist.length>0){
							desc +="<font style=\"color:red\">在金融机构黑名单 | </font>";
						}else{
							desc +="<font>不在金融机构黑名单 | </font>";
						}
						if(jxlIdCardCheck.courtBlacklist !== undefined && jxlIdCardCheck.courtBlacklist.length>0){
							desc +="<font style=\"color:red\">在法院黑名单 | </font>";
						}else{
							desc +="<font>不在法院黑名单 | </font>";
						}
						userInfoHtml +="<td>"+jxlIdCardCheck.keyValue+"  |  "+desc+"   "
						+jxlIdCardCheck.sex+"  |  "+jxlIdCardCheck.age+"  |  "
						+jxlIdCardCheck.provinceName+"."+jxlIdCardCheck.cityName+"."+jxlIdCardCheck.cityAreaName+"</td>";
						userInfoHtml += "</tr>";
						//手机号
						userInfoHtml += "<tr>";
						userInfoHtml += "<td style=\"width: 340px\" rowspan=\"2\">手机号</td>";
						userInfoHtml +="<td>"+jxlCellPhoneCheck.keyValue+"  |  "+jxlCellPhoneCheck.reliability + " | "+getDateStrFormat(jxlCellPhoneCheck.regTime)
						+"</td>"
						userInfoHtml += "</tr>";
						userInfoHtml += "<tr>";
						userInfoHtml +="<td>"+jxlCellPhoneCheck.checkName+"  |  "+jxlCellPhoneCheck.checkIdcard +"</td>";
						userInfoHtml += "</tr>";
						//居住地址
						userInfoHtml += "<tr>";
						userInfoHtml += "<td style=\"width: 340px\">居住地址</td>";
						userInfoHtml +="<td>"+jxlHomeAddressCheck.keyValue+"  |  "+jxlHomeAddressCheck.checkAddr+"</td>";
						userInfoHtml += "</tr>";
						//家庭电话
						userInfoHtml += "<tr>";
						userInfoHtml += "<td style=\"width: 340px\">家庭电话</td>";
						userInfoHtml +="<td>"+jxlHomePhoneCheck.keyValue+"  |  "+jxlHomePhoneCheck.checkMobile+"</td>";
						userInfoHtml += "</tr>";
						//联系人【可能存在多个】
						for(var i=0;i<jxlContactCheck.length;i++){
							userInfoHtml += "<tr>";
							userInfoHtml += "<td style=\"width: 340px\" rowspan=\"2\">联系人"+(i+1)+"</td>";
							userInfoHtml +="<td>"+jxlContactCheck[i].relationship+"  |  "+jxlContactCheck[i].contactName+"  |  "+jxlContactCheck[i].keyValue+"  |  "+"</td>";
							userInfoHtml += "</tr>";
							userInfoHtml += "<tr>";
							userInfoHtml +="<td>"+jxlContactCheck[i].checkXiaohao+"  |  "+jxlContactCheck[i].checkMobile +"</td>";
							userInfoHtml += "</tr>";
						}
						$("#user_check").html(userInfoHtml);

						//用户信息检测 联系人数据
						$("#user_check_contact font").eq(0).html(jxlMonitorData.searchedOrgNum);
						$("#user_check_contact font").eq(1).html(jxlMonitorData.searchedOrgType);
						$("#user_check_contact font").eq(2).html(jxlMonitorData.idcardWithOtherNames);
						$("#user_check_contact font").eq(3).html(jxlMonitorData.usedTel);
						$("#user_check_contact font").eq(4).html(jxlMonitorData.usedName);
						$("#user_check_contact font").eq(5).html(jxlMonitorData.useIdCardNo);
						$("#user_check_contact font").eq(6).html(jxlMonitorData.enterpriseNum);
						$("#user_check_contact font").eq(7).html(jxlMonitorData.enterpriseType);
						$("#user_check_contact font").eq(8).html(jxlMonitorData.publicWebsite);

						//用户信息检测 联系人黑名单数据
						if(jxlBlackData.blackScore<=10){
							$("#user_check_black font").eq(0).css("color","red");
						}
						$("#user_check_black font").eq(0).html(jxlBlackData.blackScore);
						$("#user_check_black font").eq(1).html(jxlBlackData.linkmanBlackNum);
						$("#user_check_black font").eq(2).html(jxlBlackData.jianBlackNum);
						$("#user_check_black font").eq(3).html(jxlBlackData.directLinkmanNum);
						$("#user_check_black font").eq(4).html(jxlBlackData.directBlackNum);
						$("#user_check_black font").eq(5).html(getPerFormat(jxlBlackData.directScaleNum));
						
						//行为表现
						for(var i=0;i<jxlBehaviorData.length;i++){
							var str='';
							if(jxlBehaviorData[i].score == 2){
								str+="<tr style=\"color:red\">";
							}else{
								str+="<tr>";
							}
							str+="<td>"+jxlBehaviorData[i].checklistName+"</td>";
							str+="<td>"+jxlBehaviorData[i].result+"</td>";
							str+="<td>"+jxlBehaviorData[i].basis+"</td>";
							str+="</tr>";
							$("#user_behavior").append(str);
						}
						
						//运营商消费数据表现
						for(var i=0;i<jxlPhoneConsume.length;i++){
							var str='';
							str+="<tr>";
							str+="<td width=\"90px\">"+jxlPhoneConsume[i].cityName+"</td>";
							str+="<td>"+jxlPhoneConsume[i].phoneNum+"</td>";
							str+="<td>"+jxlPhoneConsume[i].cellOperatorZH+"</td>";
							var month = parseInt(jxlPhoneConsume[i].month)<10 ? ("0"+jxlPhoneConsume[i].month):jxlPhoneConsume[i].month;
							str+="<td>"+jxlPhoneConsume[i].year+"-"+month+"</td>";
							str+="<td>"+jxlPhoneConsume[i].callNum+"</td>";
							str+="<td>"+jxlPhoneConsume[i].dialingNum+"</td>";
							str+="<td>"+jxlPhoneConsume[i].dialingTimes+"</td>";
							str+="<td>"+jxlPhoneConsume[i].calledNum+"</td>";
							str+="<td>"+jxlPhoneConsume[i].calledTimes+"</td>";
							str+="<td>"+jxlPhoneConsume[i].smsNum+"</td>";
							str+="<td>"+jxlPhoneConsume[i].calledFlow+"</td>";
							var phoneBill = jxlPhoneConsume[i].phoneBill;
							if(parseFloat(phoneBill)<0){
								str+="<td>无数据</td>";
							}else{
								str+="<td>"+phoneBill+"</td>";
							}
							str+="</tr>";
							$("#user_cell_behavior").append(str);
						}
						
						//联系人区域汇总
						for(var i=0;i<jxlLinkmanArea.length;i++){
							var str='';
							str+="<tr>";
							str+="<td>"+jxlLinkmanArea[i].attributionName+"</td>";
							str+="<td>"+jxlLinkmanArea[i].codeNum+"</td>";
							str+="<td>"+jxlLinkmanArea[i].callinNum+"</td>";
							str+="<td>"+jxlLinkmanArea[i].calloutNum+"</td>";
							str+="<td>"+jxlLinkmanArea[i].callinTimes+"</td>";
							str+="<td>"+jxlLinkmanArea[i].calloutTimes+"</td>";
							str+="<td>"+getPerFormat(jxlLinkmanArea[i].callinNumPercent)+"</td>";
							str+="<td>"+getPerFormat(jxlLinkmanArea[i].calloutNumPercent)+"</td>";
							str+="<td>"+getPerFormat(jxlLinkmanArea[i].callinTimesPercent)+"</td>";
							str+="<td>"+getPerFormat(jxlLinkmanArea[i].calloutTimesPercent)+"</td>";
							str+="</tr>";
							$("#user_area").append(str);
						}
				
						//运营商数据分析
						for(var i=0;i<jxlCallData.length;i++){
							var str='';
							str+="<tr>";
							str+="<td width=\"90px\">"+jxlCallData[i].callNumber+"</td>";
							str+="<td>"+jxlCallData[i].intMarkingName+"</td>";
							str+="<td>"+jxlCallData[i].requestTypeName+"</td>";
							str+="<td>"+jxlCallData[i].attributionName+"</td>";
							str+="<td>"+jxlCallData[i].linkNum+"</td>";
							str+="<td>"+jxlCallData[i].linkTimes+"</td>";
							str+="<td>"+jxlCallData[i].dialingNum+"</td>";
							str+="<td>"+jxlCallData[i].calledNum+"</td>";
							str+="</tr>";
							$("#user_phone_data").append(str);
						}
						
						//联系人和地址信息
						for(var i=0;i<jxlLinkmanData.length;i++){
							var str='';
							str+="<tr>";
							str+="<td width=\"90px\">"+jxlLinkmanData[i].linkmanName+"</td>";
							var firstTransTime, latestTransTime, linkPhone="", halfYearTimes="";
							for(var j=0;j<jxlLinkmanDataDetails.length;j++){
								var details = jxlLinkmanDataDetails[j];
								if(details.linkmanDataId == jxlLinkmanData[i].linkmanDataId){
									firstTransTime = getDateStrFormat(details.firstTransTime);
									latestTransTime = getDateStrFormat(details.latestTransTime);
									if(linkPhone != ""){
										linkPhone += ",";
									}
									linkPhone = linkPhone+details.linkPhone+" "+details.cityName;
									halfYearTimes = details.halfYearTimes;
								}
							}
							str+="<td>"+firstTransTime+"</td>";
							str+="<td>"+latestTransTime+"</td>";
							str+="<td>"+linkPhone+"</td>";
							str+="<td>"+halfYearTimes+"</td>";
							str+="</tr>";
							$("#user_contact").append(str);
						}
						
						//地址信息
						for(var i=0;i<jxlReceiptAddress.length;i++){
							var str='';
							str+="<tr>";
							str+="<td>"+jxlReceiptAddress[i].address+"</td>";
							str+="<td>"+jxlReceiptAddress[i].sumAmount+"</td>";
							var receiptName="", receiptPhone = "";
							for(var j=0;j<jxlReceiptAddressReceiver.length;j++){
								var details = jxlReceiptAddressReceiver[j];
								if(details.receiptAddressId == jxlReceiptAddress[i].receiptAddressId){
									if(receiptName != ""){
										receiptName += ",";
									}
									if(receiptPhone != ""){
										receiptPhone += ",";
									}
									receiptName = receiptName + details.receiptName;
									receiptPhone = receiptPhone + details.receiptPhone;
								}
							}
							str+="<td>"+receiptName+"</td>";
							str+="<td>"+receiptPhone+"</td>";
							str+="</tr>";
							$("#user_address").append(str);
						}
						
						//电商数据分析
						for(var i=0;i<jxlNetBusiData.length;i++){
							var str='';
							str+="<tr>";
							var month = parseInt(jxlNetBusiData[i].month)<10 ? ("0"+jxlNetBusiData[i].month):jxlNetBusiData[i].month;
							str+="<td>"+jxlNetBusiData[i].year+"-"+month+"</td>";
							str+="<td>"+jxlNetBusiData[i].consumeNum+"</td>";
							str+="<td>"+jxlNetBusiData[i].consumeSum+"</td>";
							str+="<td>"+jxlNetBusiData[i].category+"</td>";
							str+="</tr>";
							$("#user_ebusiness").append(str);
						}
						
						//出行数据分析
						for(var i=0;i<jxlTravelData.length;i++){
							var str='';
							str+="<tr>";
							str+="<td width=\"90px\">"+jxlTravelData[i].period+"</td>";
							str+="<td>"+getDateStrFormat(jxlTravelData[i].outTime)+"</td>";
							str+="<td>"+getDateStrFormat(jxlTravelData[i].inTime)+"</td>";
							str+="<td>"+jxlTravelData[i].outPlace+"</td>";
							str+="<td>"+jxlTravelData[i].inOut+"</td>";
							str+="</tr>";
							$("#user_trip").append(str);
						}
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

/**
 * 0.0234 -> 2.34%
 * @param s
 * @returns
 */
function getPerFormat(s){
	var baseNum = 0;
	try {
       baseNum += s.toString().split(".")[1].length;
    } catch (e) {
    }
    var rs = s. toString().replace(".", "")*100/Math.pow(10, baseNum);
    if(rs.toString().indexOf(".") == -1) return rs+".00%";
	return rs+"%";
}

