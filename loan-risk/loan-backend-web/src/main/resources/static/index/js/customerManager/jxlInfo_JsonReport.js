layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
			layer = layui.layer, // 获取当前窗口的layer对象
			form = layui.form(), element = layui.element();
		});
var  userBehaviorTemp=$("#user_behavior").html();
var  userCellBehaviorTemp=$("#user_cell_behavior").html();
var  userAreaTemp=$("#user_area").html();
var  userPhoneDataTemp=$("#user_phone_data").html();
var  userContactTemp=$("#user_contact").html();
var  userAddressTemp=$("#user_address").html();
var  userEbusinessTemp=$("#user_ebusiness").html();
var  userTripTemp=$("#user_trip").html();
$(function() {
	 var platformCustomerId = getQueryString("id");//获取页面传过来的平台用户编号
	 var applicationId = getQueryString("applicationId");//获取页面传过来的申请单编号
	 if(applicationId == "" && platformCustomerId == ""){
		 applicationId="-1";
		 platformCustomerId="-1";
	 }
	 //var url="http://h5.xnsudai5.com/jxlmf/20170823/ba6ccad176065a6206d4f34eeb294e21.json";
	 //applicationId="fb34dcfdfd5a4a338bbc439e461bhxf5";                            
	$.ajax({
				url : getCtxPath() + "/backend/jxl/getJxlJsonReport.do?v=" + new Date().getTime(),
				method : "post",
				dataType : "json",
				data : {
					platformId : platformCustomerId,
					buApplicationId : applicationId
				},
				success : function(data) {
					if(data.code == 200){
						if(data.list.data == null || data.list.data == undefined) return;
						if (data.list.data.jsonInfo.report_data != undefined){
							var msg=data.list.data.jsonInfo.report_data;
						}else{
							return;
						}
						var repid=msg._id;
						var applicationCheck = msg.application_check;//用户申请表检测
						var behaviorCheck=msg.behavior_check;//用户行为检测
						var cellBehavior=msg.cell_behavior;//运营商数据整理
						var collectionContact=msg.collection_contact;//联系人名单
						var contactList=msg.contact_list;//运营商联系人通话详情
						var contactRegion=msg.contact_region;//联系人区域汇总
						var deliverAddress=msg.deliver_address;//电商地址分析
						var ebusinessExpense=msg.ebusiness_expense;//电商月消费
						var mainService=msg.main_service;//常用服务
						var report=msg.report;//报告基本信息
						var tripInfo=msg.trip_info;//出行分析
						var userInfoCheck=msg.user_info_check;//用户信息检测
						

						//报告编号
						$("#user_useId").html(report.rpt_id);
						$("#user_date").html(cTime(getDateStrFormat(report.update_time)));
						$("#user_version").html(report.version);
						
						var userInfoHtml = "";
						var num=0;//用于记录联系人个数
						for (var int = 0; int < applicationCheck.length; int++) {
							if(applicationCheck[int].app_point=='user_name'){
								var uname=applicationCheck[int].check_points.key_value;//用户名
								//姓名
								userInfoHtml += "<tr>";
								userInfoHtml += "<td style=\"width: 340px\">姓名</td>";
								userInfoHtml +="<td>"+uname+"</td>"
								userInfoHtml += "</tr>";
								userInfoHtml += "<tr>";
							}else if(applicationCheck[int].app_point=='id_card'){
								
								var idCard=applicationCheck[int].check_points;//身份证对象
								var financialBlacklist=idCard.financial_blacklist;//金融服务类机构黑名
								var courtBlacklist=idCard.court_blacklist;//法院黑名单
								//身份证
								userInfoHtml += "<td style=\"width: 340px\">身份证</td>";
								var desc = "";
								if(financialBlacklist !== undefined && financialBlacklist == true){
									desc +="<font style=\"color:red\">在金融机构黑名单 | </font>";
								}else{
									desc +="<font>不在金融机构黑名单 | </font>";
								}
								if(courtBlacklist !== undefined && courtBlacklist == true){
									desc +="<font style=\"color:red\">在法院黑名单 | </font>";
								}else{
									desc +="<font>不在法院黑名单 | </font>";
								}
								userInfoHtml +="<td>"+idCard.key_value+"  |  "+desc+"   "
								+idCard.gender+"  |  "+idCard.age+"  |  "
								+idCard.province+"."+idCard.city+"."+idCard.region+"</td>";
								userInfoHtml += "</tr>";
							}else if(applicationCheck[int].app_point=='cell_phone'){
								var cellPhone=applicationCheck[int].check_points;//手机对象
								
								//手机号
								userInfoHtml += "<tr>";
								userInfoHtml += "<td style=\"width: 340px\" rowspan=\"2\">手机号</td>";
								userInfoHtml +="<td>"+cellPhone.key_value+"  |  "+cellPhone.reliability + " | "+getDateStrFormat(cellPhone.reg_time)
								+"</td>"
								userInfoHtml += "</tr>";
								userInfoHtml += "<tr>";
								userInfoHtml +="<td>"+cellPhone.check_name+"  |  "+cellPhone.check_idcard +"</td>";
								userInfoHtml += "</tr>";
							}else if(applicationCheck[int].app_point=='home_addr'){
								var homeAddr=applicationCheck[int].check_points;//原住址
								//居住地址
								userInfoHtml += "<tr>";
								userInfoHtml += "<td style=\"width: 340px\">居住地址</td>";
								userInfoHtml +="<td>"+homeAddr.key_value+"  |  "+homeAddr.check_addr+"</td>";
								userInfoHtml += "</tr>";
								
							}else if(applicationCheck[int].app_point=='home_phone'){
								var homePhone=applicationCheck[int].check_points;//家庭电话
								
								//家庭电话
								userInfoHtml += "<tr>";
								userInfoHtml += "<td style=\"width: 340px\">家庭电话</td>";
								userInfoHtml +="<td>"+homePhone.key_value+"  |  "+homePhone.check_mobile+"</td>";
								userInfoHtml += "</tr>";
							}else if(applicationCheck[int].app_point=='contact'){
								var contact=applicationCheck[int].check_points;//联系人
								
								//联系人【可能存在多个】
								userInfoHtml += "<tr>";
								userInfoHtml += "<td style=\"width: 340px\" rowspan=\"2\">联系人"+(num+=1)+"</td>";
								userInfoHtml +="<td>"+contact.relationship+"  |  "+contact.contact_name+"  |  "+contact.key_value+"  |  "+"</td>";
								userInfoHtml += "</tr>";
								userInfoHtml += "<tr>";
								userInfoHtml +="<td>"+contact.check_xiaohao+"  |  "+contact.check_mobile +"</td>";
								userInfoHtml += "</tr>";
								
							}
						}
						
						$("#user_check").html(userInfoHtml);
						
						var checkBlackInfo=userInfoCheck.check_black_info;//用户黑名单信息
						
						var checkSearchInfo=userInfoCheck.check_search_info;//用户查询信息
						
						//用户信息检测 联系人数据
						$("#user_check_contact font").eq(0).html(checkSearchInfo.searched_org_cnt);
						$("#user_check_contact font").eq(1).html(checkSearchInfo.searched_org_type.toString());
						$("#user_check_contact font").eq(2).html(checkSearchInfo.idcard_with_other_names.toString());
						$("#user_check_contact font").eq(3).html(checkSearchInfo.idcard_with_other_phones.toString());
						$("#user_check_contact font").eq(4).html(checkSearchInfo.phone_with_other_names.toString());
						$("#user_check_contact font").eq(5).html(checkSearchInfo.phone_with_other_idcards.toString());
						$("#user_check_contact font").eq(6).html(checkSearchInfo.register_org_cnt);
						$("#user_check_contact font").eq(7).html(checkSearchInfo.register_org_type.toString());
						$("#user_check_contact font").eq(8).html(checkSearchInfo.arised_open_web.toString());

						//用户信息检测 联系人黑名单数据
						if(checkBlackInfo.phone_gray_score<=10){
							$("#user_check_black font").eq(0).css("color","red");
						}
						$("#user_check_black font").eq(0).html(checkBlackInfo.phone_gray_score);
						$("#user_check_black font").eq(1).html(checkBlackInfo.contacts_class1_blacklist_cnt);
						$("#user_check_black font").eq(2).html(checkBlackInfo.contacts_class2_blacklist_cnt);
						$("#user_check_black font").eq(3).html(checkBlackInfo.contacts_class1_cnt);
						$("#user_check_black font").eq(4).html(checkBlackInfo.contacts_router_cnt);
						$("#user_check_black font").eq(5).html(getPerFormat(checkBlackInfo.contacts_router_ratio));
						
						//行为表现
						for(var i=0;i<behaviorCheck.length;i++){
							var str='';
							if(behaviorCheck[i].score == 2){
								str+="<tr style=\"color:red\">";
							}else{
								str+="<tr>";
							}
							str+="<td>"+behaviorCheck[i].check_point_cn+"</td>";
							str+="<td>"+behaviorCheck[i].result+"</td>";
							str+="<td>"+behaviorCheck[i].evidence+"</td>";
							str+="</tr>";
							$("#user_behavior").append(str);
						}
						
						//运营商消费数据表现
						for(var int=0;int<cellBehavior.length;int++){
							var str='';
							var behavior=cellBehavior[int].behavior;
							for (var i = 0; i < behavior.length; i++) {
								
								str+="<tr>";
								str+="<td width=\"90px\">"+behavior[i].cell_operator_zh+"</td>";
								str+="<td>"+behavior[i].cell_phone_num+"</td>";
								str+="<td>"+behavior[i].cell_loc+"</td>";
								//var month = parseInt(behavior[i].cell_mth)<10 ? ("0"+behavior[i].cell_mth):behavior[i].cell_mth;
								str+="<td>"+behavior[i].cell_mth+"</td>";
								str+="<td>"+behavior[i].call_cnt+"</td>";
								str+="<td>"+behavior[i].call_out_cnt+"</td>";
								str+="<td>"+behavior[i].call_out_time+"</td>";
								str+="<td>"+behavior[i].call_in_cnt+"</td>";
								str+="<td>"+behavior[i].call_in_time+"</td>";
								str+="<td>"+behavior[i].sms_cnt+"</td>";
								str+="<td>"+behavior[i].net_flow+"</td>";
								var phoneBill = behavior[i].total_amount;
								if(parseFloat(phoneBill)<0){
									str+="<td>无数据</td>";
								}else{
									str+="<td>"+phoneBill+"</td>";
								}
								str+="</tr>";
							}
							$("#user_cell_behavior").append(str);
						}
						
						//联系人区域汇总
						for(var i=0;i<contactRegion.length;i++){
							var str='';
							str+="<tr>";
							str+="<td>"+contactRegion[i].region_loc+"</td>";
							str+="<td>"+contactRegion[i].region_uniq_num_cnt+"</td>";
							str+="<td>"+contactRegion[i].region_call_in_cnt+"</td>";
							str+="<td>"+contactRegion[i].region_call_out_cnt+"</td>";
							str+="<td>"+contactRegion[i].region_call_in_time+"</td>";
							str+="<td>"+contactRegion[i].region_call_out_time+"</td>";
							str+="<td>"+getPerFormat(contactRegion[i].region_call_in_cnt_pct)+"</td>";
							str+="<td>"+getPerFormat(contactRegion[i].region_call_out_cnt_pct)+"</td>";
							str+="<td>"+getPerFormat(contactRegion[i].region_call_in_time_pct)+"</td>";
							str+="<td>"+getPerFormat(contactRegion[i].region_call_out_time_pct)+"</td>";
							str+="</tr>";
							$("#user_area").append(str);
						}
						
						//运营商数据分析
						for(var i=0;i<contactList.length;i++){
							var str='';
							str+="<tr>";
							str+="<td width=\"90px\">"+contactList[i].phone_num+"</td>";
							str+="<td>"+contactList[i].contact_name+"</td>";
							str+="<td>"+contactList[i].needs_type+"</td>";
							str+="<td>"+contactList[i].phone_num_loc+"</td>";
							str+="<td>"+contactList[i].call_cnt+"</td>";
							str+="<td>"+contactList[i].call_len+"</td>";
							str+="<td>"+contactList[i].call_out_cnt+"</td>";
							str+="<td>"+contactList[i].call_in_cnt+"</td>";
							str+="</tr>";
							$("#user_phone_data").append(str);
						}
						
						//联系人和地址信息
						for(var i=0;i<collectionContact.length;i++){
							var str='';
							str+="<tr>";
							str+="<td width=\"90px\">"+collectionContact[i].contact_name+"</td>";
							/*var firstTransTime, latestTransTime, linkPhone="", halfYearTimes="";
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
							}*/
							var contactDetails=collectionContact[i].contact_details;//联系人详情
							var transStart='';
							var transEnd='';
							var phoneNum='';
							var callLen='';
							for (var int = 0; int < contactDetails.length; int++) {
								phoneNum = contactDetails[int].phone_num;
								callLen = contactDetails[int].call_len;
								transStart=contactDetails[int].trans_start;
								transEnd=contactDetails[int].trans_end;
							}
							str+="<td>"+transStart+"</td>";
							str+="<td>"+transEnd+"</td>";
							str+="<td>"+phoneNum+"</td>";
							str+="<td>"+callLen+"</td>";///**************半年通话时长 待确定
							str+="</tr>";
							$("#user_contact").append(str);
						}
						//******************************************************************//
						//地址信息
//						for(var i=0;i<jxlReceiptAddress.length;i++){//*********无数据 无法看到Json属性************//
//							var str='';
//							str+="<tr>";
//							str+="<td>"+jxlReceiptAddress[i].address+"</td>";
//							str+="<td>"+jxlReceiptAddress[i].sumAmount+"</td>";
//							var receiptName="", receiptPhone = "";
//							for(var j=0;j<jxlReceiptAddressReceiver.length;j++){
//								var details = jxlReceiptAddressReceiver[j];
//								if(details.receiptAddressId == jxlReceiptAddress[i].receiptAddressId){
//									if(receiptName != ""){
//										receiptName += ",";
//									}
//									if(receiptPhone != ""){
//										receiptPhone += ",";
//									}
//									receiptName = receiptName + details.receiptName;
//									receiptPhone = receiptPhone + details.receiptPhone;
//								}
//							}
//							str+="<td>"+receiptName+"</td>";
//							str+="<td>"+receiptPhone+"</td>";
//							str+="</tr>";
//							$("#user_address").append(str);
//						}
//						
//						//电商数据分析
//						for(var i=0;i<ebusinessExpense.length;i++){//***********无数据看不到Json属性************//
//							var str='';
//							str+="<tr>";
//							var month = parseInt(jxlNetBusiData[i].month)<10 ? ("0"+jxlNetBusiData[i].month):jxlNetBusiData[i].month;
//							str+="<td>"+jxlNetBusiData[i].year+"-"+month+"</td>";
//							str+="<td>"+jxlNetBusiData[i].consumeNum+"</td>";
//							str+="<td>"+jxlNetBusiData[i].consumeSum+"</td>";
//							str+="<td>"+jxlNetBusiData[i].category+"</td>";
//							str+="</tr>";
//							$("#user_ebusiness").append(str);
//						}
						
						//出行数据分析
						for(var i=0;i<tripInfo.length;i++){
							var str='';
							str+="<tr>";
							str+="<td width=\"90px\">"+tripInfo[i].trip_type+"</td>";
							str+="<td>"+getDateStrFormat(tripInfo[i].trip_start_time)+"</td>";
							str+="<td>"+getDateStrFormat(tripInfo[i].trip_end_time)+"</td>";
							str+="<td>"+tripInfo[i].trip_leave+"</td>";
							str+="<td>"+tripInfo[i].trip_dest+"</td>";
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
function cTime(time){
	var timestamp2 = Date.parse(new Date(time));
	var newDate = new Date();
	newDate.setTime(timestamp2);
	return newDate.format('yyyy-MM-dd h:m:s');
}
Date.prototype.format = function(format) {
    var date = {
       "M+": this.getMonth() + 1,
       "d+": this.getDate(),
       "h+": this.getHours(),
       "m+": this.getMinutes(),
       "s+": this.getSeconds(),
       "q+": Math.floor((this.getMonth() + 3) / 3),
       "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
       format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
       if (new RegExp("(" + k + ")").test(format)) {
           format = format.replace(RegExp.$1, RegExp.$1.length == 1
              ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
       }
    }
    return format;
}
/*********************************************************************************************************************************************************************************************/
function showDataBase(){
	 var platformCustomerId = getQueryString("id");//获取页面传过来的平台用户编号
	 var applicationId = getQueryString("applicationId");//获取页面传过来的申请单编号
	$.ajax({
				url : getCtxPath() + "/backend/jxl/getReport.do?v=" + new Date().getTime(),
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
						$("#user_behavior").html(userBehaviorTemp);
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
						$("#user_cell_behavior").html(userCellBehaviorTemp);
						for(var i=0;i<jxlPhoneConsume.length;i++){
							var str='';
							str+="<tr>";
							str+="<td width=\"90px\">"+jxlPhoneConsume[i].cellOperatorZH+"</td>";
							str+="<td>"+jxlPhoneConsume[i].phoneNum+"</td>";
							str+="<td>"+jxlPhoneConsume[i].cityName+"</td>";
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
						$("#user_area").html(userAreaTemp);
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
						$("#user_phone_data").html(userPhoneDataTemp);
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
						$("#user_contact").html(userContactTemp);
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
						$("#user_address").html(userAddressTemp);
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
						$("#user_ebusiness").html(userEbusinessTemp);
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
						$("#user_trip").html(userTripTemp);
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
}
/*************************************************************************************************************************************************************************************/
function showAliyun(){
	 var platformCustomerId = getQueryString("id");//获取页面传过来的平台用户编号
	 var applicationId = getQueryString("applicationId");//获取页面传过来的申请单编号
	 if(applicationId == "" && platformCustomerId == ""){
		 applicationId="-1";
		 platformCustomerId="-1";
	 }
	// applicationId='fb34dcfdfd5a4a338bbc439e461bhxf5';
	 $.ajax({
			url : getCtxPath() + "/backend/queryAliyunDataInfo/getInfo.do?v=" + new Date().getTime(),
			method : "post",
			dataType : "json",
			data : { 
				taskType : '04001',
				applicationId : applicationId,
				platformId:platformCustomerId
			},
			success : function(data) {
				if(data.code == 200){
					if(data.data == null ) return;
					if(data.data.info == null ) return;	
					var tempJson=eval('('+data.data.info+')');
					var msg=tempJson.report_data;
					var repid=msg._id;
					var applicationCheck = msg.application_check;//用户申请表检测
					var behaviorCheck=msg.behavior_check;//用户行为检测
					var cellBehavior=msg.cell_behavior;//运营商数据整理
					var collectionContact=msg.collection_contact;//联系人名单
					var contactList=msg.contact_list;//运营商联系人通话详情
					var contactRegion=msg.contact_region;//联系人区域汇总
					var deliverAddress=msg.deliver_address;//电商地址分析
					var ebusinessExpense=msg.ebusiness_expense;//电商月消费
					var mainService=msg.main_service;//常用服务
					var report=msg.report;//报告基本信息
					var tripInfo=msg.trip_info;//出行分析
					var userInfoCheck=msg.user_info_check;//用户信息检测
					

					//报告编号
					$("#user_useId").html(report.rpt_id);
					$("#user_date").html(cTime(getDateStrFormat(report.update_time)));
					$("#user_version").html(report.version);
					
					var userInfoHtml = "";
					var num=0;//用于记录联系人个数
					for (var int = 0; int < applicationCheck.length; int++) {
						if(applicationCheck[int].app_point=='user_name'){
							var uname=applicationCheck[int].check_points.key_value;//用户名
							//姓名
							userInfoHtml += "<tr>";
							userInfoHtml += "<td style=\"width: 340px\">姓名</td>";
							userInfoHtml +="<td>"+uname+"</td>"
							userInfoHtml += "</tr>";
							userInfoHtml += "<tr>";
						}else if(applicationCheck[int].app_point=='id_card'){
							
							var idCard=applicationCheck[int].check_points;//身份证对象
							var financialBlacklist=idCard.financial_blacklist;//金融服务类机构黑名
							var courtBlacklist=idCard.court_blacklist;//法院黑名单
							//身份证
							userInfoHtml += "<td style=\"width: 340px\">身份证</td>";
							var desc = "";
							if(financialBlacklist !== undefined && financialBlacklist == true){
								desc +="<font style=\"color:red\">在金融机构黑名单 | </font>";
							}else{
								desc +="<font>不在金融机构黑名单 | </font>";
							}
							if(courtBlacklist !== undefined && courtBlacklist == true){
								desc +="<font style=\"color:red\">在法院黑名单 | </font>";
							}else{
								desc +="<font>不在法院黑名单 | </font>";
							}
							userInfoHtml +="<td>"+idCard.key_value+"  |  "+desc+"   "
							+idCard.gender+"  |  "+idCard.age+"  |  "
							+idCard.province+"."+idCard.city+"."+idCard.region+"</td>";
							userInfoHtml += "</tr>";
						}else if(applicationCheck[int].app_point=='cell_phone'){
							var cellPhone=applicationCheck[int].check_points;//手机对象
							
							//手机号
							userInfoHtml += "<tr>";
							userInfoHtml += "<td style=\"width: 340px\" rowspan=\"2\">手机号</td>";
							userInfoHtml +="<td>"+cellPhone.key_value+"  |  "+cellPhone.reliability + " | "+getDateStrFormat(cellPhone.reg_time)
							+"</td>"
							userInfoHtml += "</tr>";
							userInfoHtml += "<tr>";
							userInfoHtml +="<td>"+cellPhone.check_name+"  |  "+cellPhone.check_idcard +"</td>";
							userInfoHtml += "</tr>";
						}else if(applicationCheck[int].app_point=='home_addr'){
							var homeAddr=applicationCheck[int].check_points;//原住址
							//居住地址
							userInfoHtml += "<tr>";
							userInfoHtml += "<td style=\"width: 340px\">居住地址</td>";
							userInfoHtml +="<td>"+homeAddr.key_value+"  |  "+homeAddr.check_addr+"</td>";
							userInfoHtml += "</tr>";
							
						}else if(applicationCheck[int].app_point=='home_phone'){
							var homePhone=applicationCheck[int].check_points;//家庭电话
							
							//家庭电话
							userInfoHtml += "<tr>";
							userInfoHtml += "<td style=\"width: 340px\">家庭电话</td>";
							userInfoHtml +="<td>"+homePhone.key_value+"  |  "+homePhone.check_mobile+"</td>";
							userInfoHtml += "</tr>";
						}else if(applicationCheck[int].app_point=='contact'){
							var contact=applicationCheck[int].check_points;//联系人
							
							//联系人【可能存在多个】
							userInfoHtml += "<tr>";
							userInfoHtml += "<td style=\"width: 340px\" rowspan=\"2\">联系人"+(num+=1)+"</td>";
							userInfoHtml +="<td>"+contact.relationship+"  |  "+contact.contact_name+"  |  "+contact.key_value+"  |  "+"</td>";
							userInfoHtml += "</tr>";
							userInfoHtml += "<tr>";
							userInfoHtml +="<td>"+contact.check_xiaohao+"  |  "+contact.check_mobile +"</td>";
							userInfoHtml += "</tr>";
							
						}
					}
					
					$("#user_check").html(userInfoHtml);
					
					var checkBlackInfo=userInfoCheck.check_black_info;//用户黑名单信息
					
					var checkSearchInfo=userInfoCheck.check_search_info;//用户查询信息
					
					//用户信息检测 联系人数据
					$("#user_check_contact font").eq(0).html(checkSearchInfo.searched_org_cnt);
					$("#user_check_contact font").eq(1).html(checkSearchInfo.searched_org_type.toString());
					$("#user_check_contact font").eq(2).html(checkSearchInfo.idcard_with_other_names.toString());
					$("#user_check_contact font").eq(3).html(checkSearchInfo.idcard_with_other_phones.toString());
					$("#user_check_contact font").eq(4).html(checkSearchInfo.phone_with_other_names.toString());
					$("#user_check_contact font").eq(5).html(checkSearchInfo.phone_with_other_idcards.toString());
					$("#user_check_contact font").eq(6).html(checkSearchInfo.register_org_cnt);
					$("#user_check_contact font").eq(7).html(checkSearchInfo.register_org_type.toString());
					$("#user_check_contact font").eq(8).html(checkSearchInfo.arised_open_web.toString());

					//用户信息检测 联系人黑名单数据
					if(checkBlackInfo.phone_gray_score<=10){
						$("#user_check_black font").eq(0).css("color","red");
					}
					$("#user_check_black font").eq(0).html(checkBlackInfo.phone_gray_score);
					$("#user_check_black font").eq(1).html(checkBlackInfo.contacts_class1_blacklist_cnt);
					$("#user_check_black font").eq(2).html(checkBlackInfo.contacts_class2_blacklist_cnt);
					$("#user_check_black font").eq(3).html(checkBlackInfo.contacts_class1_cnt);
					$("#user_check_black font").eq(4).html(checkBlackInfo.contacts_router_cnt);
					$("#user_check_black font").eq(5).html(getPerFormat(checkBlackInfo.contacts_router_ratio));
					
					//行为表现
					$("#user_behavior").html(userBehaviorTemp);
					for(var i=0;i<behaviorCheck.length;i++){
						var str='';
						if(behaviorCheck[i].score == 2){
							str+="<tr style=\"color:red\">";
						}else{
							str+="<tr>";
						}
						str+="<td>"+behaviorCheck[i].check_point_cn+"</td>";
						str+="<td>"+behaviorCheck[i].result+"</td>";
						str+="<td>"+behaviorCheck[i].evidence+"</td>";
						str+="</tr>";
						$("#user_behavior").append(str);
					}
					
					//运营商消费数据表现
					$("#user_cell_behavior").html(userCellBehaviorTemp);
					for(var int=0;int<cellBehavior.length;int++){
						var str='';
						var behavior=cellBehavior[int].behavior;
						for (var i = 0; i < behavior.length; i++) {
							
							str+="<tr>";
							str+="<td width=\"90px\">"+behavior[i].cell_operator_zh+"</td>";
							str+="<td>"+behavior[i].cell_phone_num+"</td>";
							str+="<td>"+behavior[i].cell_loc+"</td>";
							//var month = parseInt(behavior[i].cell_mth)<10 ? ("0"+behavior[i].cell_mth):behavior[i].cell_mth;
							str+="<td>"+behavior[i].cell_mth+"</td>";
							str+="<td>"+behavior[i].call_cnt+"</td>";
							str+="<td>"+behavior[i].call_out_cnt+"</td>";
							str+="<td>"+behavior[i].call_out_time+"</td>";
							str+="<td>"+behavior[i].call_in_cnt+"</td>";
							str+="<td>"+behavior[i].call_in_time+"</td>";
							str+="<td>"+behavior[i].sms_cnt+"</td>";
							str+="<td>"+behavior[i].net_flow+"</td>";
							var phoneBill = behavior[i].total_amount;
							if(parseFloat(phoneBill)<0){
								str+="<td>无数据</td>";
							}else{
								str+="<td>"+phoneBill+"</td>";
							}
							str+="</tr>";
						}
						$("#user_cell_behavior").append(str);
					}
					
					//联系人区域汇总
					$("#user_area").html(userAreaTemp);
					for(var i=0;i<contactRegion.length;i++){
						var str='';
						str+="<tr>";
						str+="<td>"+contactRegion[i].region_loc+"</td>";
						str+="<td>"+contactRegion[i].region_uniq_num_cnt+"</td>";
						str+="<td>"+contactRegion[i].region_call_in_cnt+"</td>";
						str+="<td>"+contactRegion[i].region_call_out_cnt+"</td>";
						str+="<td>"+contactRegion[i].region_call_in_time+"</td>";
						str+="<td>"+contactRegion[i].region_call_out_time+"</td>";
						str+="<td>"+getPerFormat(contactRegion[i].region_call_in_cnt_pct)+"</td>";
						str+="<td>"+getPerFormat(contactRegion[i].region_call_out_cnt_pct)+"</td>";
						str+="<td>"+getPerFormat(contactRegion[i].region_call_in_time_pct)+"</td>";
						str+="<td>"+getPerFormat(contactRegion[i].region_call_out_time_pct)+"</td>";
						str+="</tr>";
						$("#user_area").append(str);
					}
					
					//运营商数据分析
					$("#user_phone_data").html(userPhoneDataTemp);
					for(var i=0;i<contactList.length;i++){
						var str='';
						str+="<tr>";
						str+="<td width=\"90px\">"+contactList[i].phone_num+"</td>";
						str+="<td>"+contactList[i].contact_name+"</td>";
						str+="<td>"+contactList[i].needs_type+"</td>";
						str+="<td>"+contactList[i].phone_num_loc+"</td>";
						str+="<td>"+contactList[i].call_cnt+"</td>";
						str+="<td>"+contactList[i].call_len+"</td>";
						str+="<td>"+contactList[i].call_out_cnt+"</td>";
						str+="<td>"+contactList[i].call_in_cnt+"</td>";
						str+="</tr>";
						$("#user_phone_data").append(str);
					}
					
					//联系人和地址信息
					$("#user_contact").html(userContactTemp);
					for(var i=0;i<collectionContact.length;i++){
						var str='';
						str+="<tr>";
						str+="<td width=\"90px\">"+collectionContact[i].contact_name+"</td>";
						/*var firstTransTime, latestTransTime, linkPhone="", halfYearTimes="";
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
						}*/
						var contactDetails=collectionContact[i].contact_details;//联系人详情
						var transStart='';
						var transEnd='';
						var phoneNum='';
						var callLen='';
						for (var int = 0; int < contactDetails.length; int++) {
							phoneNum = contactDetails[int].phone_num;
							callLen = contactDetails[int].call_len;
							transStart=contactDetails[int].trans_start;
							transEnd=contactDetails[int].trans_end;
						}
						str+="<td>"+transStart+"</td>";
						str+="<td>"+transEnd+"</td>";
						str+="<td>"+phoneNum+"</td>";
						str+="<td>"+callLen+"</td>";///**************半年通话时长 待确定
						str+="</tr>";
						$("#user_contact").append(str);
					}
					//******************************************************************//
					//地址信息
//					for(var i=0;i<jxlReceiptAddress.length;i++){//*********无数据 无法看到Json属性************//
//						var str='';
//						str+="<tr>";
//						str+="<td>"+jxlReceiptAddress[i].address+"</td>";
//						str+="<td>"+jxlReceiptAddress[i].sumAmount+"</td>";
//						var receiptName="", receiptPhone = "";
//						for(var j=0;j<jxlReceiptAddressReceiver.length;j++){
//							var details = jxlReceiptAddressReceiver[j];
//							if(details.receiptAddressId == jxlReceiptAddress[i].receiptAddressId){
//								if(receiptName != ""){
//									receiptName += ",";
//								}
//								if(receiptPhone != ""){
//									receiptPhone += ",";
//								}
//								receiptName = receiptName + details.receiptName;
//								receiptPhone = receiptPhone + details.receiptPhone;
//							}
//						}
//						str+="<td>"+receiptName+"</td>";
//						str+="<td>"+receiptPhone+"</td>";
//						str+="</tr>";
//						$("#user_address").append(str);
//					}
//					
//					//电商数据分析
//					for(var i=0;i<ebusinessExpense.length;i++){//***********无数据看不到Json属性************//
//						var str='';
//						str+="<tr>";
//						var month = parseInt(jxlNetBusiData[i].month)<10 ? ("0"+jxlNetBusiData[i].month):jxlNetBusiData[i].month;
//						str+="<td>"+jxlNetBusiData[i].year+"-"+month+"</td>";
//						str+="<td>"+jxlNetBusiData[i].consumeNum+"</td>";
//						str+="<td>"+jxlNetBusiData[i].consumeSum+"</td>";
//						str+="<td>"+jxlNetBusiData[i].category+"</td>";
//						str+="</tr>";
//						$("#user_ebusiness").append(str);
//					}
					
					//出行数据分析
					$("#user_trip").html(userTripTemp);
					for(var i=0;i<tripInfo.length;i++){
						var str='';
						str+="<tr>";
						str+="<td width=\"90px\">"+tripInfo[i].trip_type+"</td>";
						str+="<td>"+getDateStrFormat(tripInfo[i].trip_start_time)+"</td>";
						str+="<td>"+getDateStrFormat(tripInfo[i].trip_end_time)+"</td>";
						str+="<td>"+tripInfo[i].trip_leave+"</td>";
						str+="<td>"+tripInfo[i].trip_dest+"</td>";
						str+="</tr>";
						$("#user_trip").append(str);
					}
				}
			
				
			},
			error : function() {
				layer.msg("获取阿里云数据信息失败,请尝试重新获取！");
			}
		}); 
}