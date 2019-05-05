layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
			layer = layui.layer, // 获取当前窗口的layer对象
			form = layui.form(), element = layui.element();
		});
var loanUserinfoTemp=$("#loan_userinfo").html();
var loanBlxxTemp=$("#loan_blxx").html();
var loanDptTemp=$("#loan_dpt").html();
var loanKhxwTemp=$("#loan_khxw").html();
$(function() {
	 var platformCustomerId = getQueryString("id");//获取页面传过来的平台用户编号
	 var applicationId = getQueryString("applicationId");//获取页面传过来的申请单编号
	 if(applicationId == "" && platformCustomerId == ""){
		 applicationId="-1";
		 platformCustomerId="-1";
	}
	 //applicationId="192bb9eab72a40ec8aebeaf18ef251c5";
	 //var tdURL=getCtxPath() + "/index/js/customerManager/tdTestJson.json";
	 		$.ajax({
				url : getCtxPath() + "/backend/td/getTdJsonReport.do?v=" + new Date().getTime(),
				method : "post",
				dataType : "json",
				data : {
					platformId : platformCustomerId,
					applicationId : applicationId
				},
				success : function(data) {
					if(data.code == 200 ){
					if(data.list.data == null ) return;	
					if(data.list.data.jsonInfo.success == true){
						var msg=data.list.data.jsonInfo;
						var tdRiskItems = msg.risk_items;//检查项数据
						var tdAddressDetect=msg.address_detect;//归属地信息
						var applyTime=cTime(msg.apply_time);//申请日期
						var reportTime=cTime(msg.report_time);
						$("#report_id").html(msg.report_id);//报告编号
						$("#app_id").html(msg.application_id);//申请单编号
						$("#repord_date").html(reportTime);//报告时间
						var badInfo=new Array();//不良信息扫描
						var personalInfo=new Array();//个人基本信息核查
						var relationPersonInfo=new Array();//关联人信息扫描
						var platformInfo=new Array();//多平台申请检测
						var customerActionInfo=new Array();//客户行为检测
						var riskInfo=new Array();//风险信息扫描
						for (var it = 0; it < tdRiskItems.length; it++) {
							if(tdRiskItems[it].group=="不良信息扫描"){
								badInfo.push(tdRiskItems[it]);
							}else if(tdRiskItems[it].group=="个人基本信息核查"){
								personalInfo.push(tdRiskItems[it]);
							}else if(tdRiskItems[it].group=="关联人信息扫描"){
								relationPersonInfo.push(tdRiskItems[it]);
							}else if(tdRiskItems[it].group=="多平台借贷申请检测"){
								platformInfo.push(tdRiskItems[it]);
							}else if(tdRiskItems[it].group=="客户行为检测"){
								customerActionInfo.push(tdRiskItems[it]);
							}else if(tdRiskItems[it].group=="风险信息扫描"){
								riskInfo.push(tdRiskItems[it]);
							}
						}
						var proposal ='';
						var tdproposal= msg.final_decision;//审核建议
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
						$("#user_score font").eq(0).html(msg.final_score);
						//$("#user_score font").eq(1).html(riskLevel);
						$("#user_score font").eq(1).html(proposal);
	
						//账户基本信息
//						$("#user_custinfo font").eq(0).html(tdAuditReport.loanDate);
//						$("#user_custinfo font").eq(1).html(tdAuditReport.name);
//						$("#user_custinfo font").eq(2).html(tdAuditReport.phoneNum);
//						$("#user_custinfo font").eq(3).html(tdAuditReport.idCard);
						
//						//归属地解析
						var idCardAddress=tdAddressDetect.id_card_address == undefined?"-":tdAddressDetect.id_card_address;//身份证地址
						var mobileAddress=tdAddressDetect.mobile_address == undefined?"-":tdAddressDetect.mobile_address;//手机号地址
						var trueIpAddress=tdAddressDetect.true_ip_address == undefined?"-":tdAddressDetect.true_ip_address;//IP地址
						var wifiAddress=tdAddressDetect.wifi_address == undefined?"-":tdAddressDetect.wifi_address;//wifi地址
						var cellAddress=tdAddressDetect.cell_address == undefined?"-":tdAddressDetect.cell_address;//基站地址
						var bankCardAddress=tdAddressDetect.bank_card_address == undefined?"-":tdAddressDetect.bank_card_address;//银行卡地址
						$("#user_address font").eq(0).html(idCardAddress);
						$("#user_address font").eq(1).html(mobileAddress);
						$("#user_address font").eq(2).html(trueIpAddress);
						$("#user_address font").eq(3).html(wifiAddress);
						$("#user_address font").eq(4).html(cellAddress);
						$("#user_address font").eq(5).html(bankCardAddress);
						//贷前风险情况-个人基本信息核查 
						for(var i=0;i<personalInfo.length;i++){
							var tdriskLevel='';
							var tdlevel = personalInfo[i].risk_level;//风险等级
							var itemDetail ;//详细信息
							if(personalInfo[i].item_detail != undefined && personalInfo[i].item_detail !='' &&  personalInfo[i].item_detail != null){
								itemDetail=personalInfo[i].item_detail;
							}
							var namelistHitDetails=new Array();//命中名单详情列表
							
							var fuzzyDetailHits;//命中模糊名单详情
							if(itemDetail != undefined){
								if(itemDetail.namelist_hit_details != undefined && itemDetail.namelist_hit_details != null){
									namelistHitDetails=itemDetail.namelist_hit_details;//取得取命中名单详情列表	
									//namelistHitDetails.push(itemDetail.namelist_hit_details);//取得取命中名单详情列表
									for (var nhd = 0; nhd < namelistHitDetails.length; nhd++) {
										if(namelistHitDetails[nhd].fuzzy_detail_hits != undefined &&namelistHitDetails[nhd].fuzzy_detail_hits !=null ){
											fuzzyDetailHits=namelistHitDetails[nhd].fuzzy_detail_hits;//取得命中模糊名单详情
										}
									}
								}
							}
							if(tdlevel==='low'){
								tdriskLevel='低'
							}
							if(tdlevel==='medium'){
								tdriskLevel='中'
							}
							if(tdlevel==='high'){
								tdriskLevel='高'
							}
							var str='';
							/*var fraudType='';
							var hitTypeDisplayName='';
							for (var int = 0; int < namelistHitDetails.length; int++) {//获取风险类型和匹配字段
								fraudType+=namelistHitDetails[int].fraud_type;
								hitTypeDisplayName=namelistHitDetails[int].hit_type_displayname;
							}*/
							if(itemDetail != undefined && itemDetail != "" && itemDetail != null){
								for (var int = 0; int < namelistHitDetails.length; int++) {
									if(namelistHitDetails[int].type=="fuzzy_list"){//模糊名单规则
										str+="<tr>";
										str+="<td width='300px'>"+personalInfo[i].item_name+"</td>";
										str+="<td width='50px'>"+tdriskLevel+"</td>";
										str+="<td width='450px'>";
										for (var fi = 0; fi < fuzzyDetailHits.length; fi++) {
											str+="<li><font color='red'>风险类型：</font>"+fuzzyDetailHits[fi].fraud_type+"</li>" ;
											str+="<li><font color='red'>匹配字段：</font>"+fuzzyDetailHits[fi].fuzzy_name+":"+fuzzyDetailHits[fi].fuzzy_id_number+"</li>";
										}
										str+="</td></tr>";
									}else if(namelistHitDetails[int].type=="grey_list"){//关注名单规则
										str+="<tr>";
										str+="<td width='300px'>"+personalInfo[i].item_name+"</td>";
										str+="<td width='50px'>"+tdriskLevel+"</td>";
										str+="<td width='450px'><li><font color='red'>风险类型：</font>"+namelistHitDetails[int].fraud_type+"</li>" +
										"<li><font color='red'>匹配字段：</font>"+namelistHitDetails[int].hit_type_displayname+"<li></td>";
										str+="</tr>";
										
									}else if(namelistHitDetails[int].type=="black_list"){//风险名单规则
										str+="<tr>";
										str+="<td width='300px'>"+personalInfo[i].item_name+"</td>";
										str+="<td width='50px'>"+tdriskLevel+"</td>";
										str+="<td width='450px'><li><font color='red'>风险类型：</font>"+namelistHitDetails[int].fraud_type+"</li>" +
										"<li><font color='red'>匹配字段：</font>"+namelistHitDetails[int].hit_type_displayname+"<li></td>";
										str+="</tr>";
									}
								}
								if(itemDetail.type=="custom_list"){//自定义列表规则
									str+="<tr>";
									str+="<td width='300px'>"+personalInfo[i].item_name+"</td>";
									str+="<td width='50px'>"+tdriskLevel+"</td>";
									str+="<td width='450px'><li><font color='red'>详情地址</font>:"+itemDetail.high_risk_areas.toString()+"</li></td>";
									str+="</tr>";
								}
							}else{
								str+="<tr>";
								str+="<td width='300px'>"+personalInfo[i].item_name+"</td>";
								str+="<td width='50px'>"+tdriskLevel+"</td>";
								str+="<td width='450px'></td>";
								str+="</tr>";
							}
							$("#loan_userinfo").append(str);
						}
						
						//贷前风险情况-不良信息扫描
						for(var i=0;i<badInfo.length;i++){
							var riskiskLevel='';
							var rislevel = badInfo[i].risk_level;
							if(rislevel==='low'){
								riskiskLevel='低'
							}
							if(rislevel==='medium'){
								riskiskLevel='中'
							}
							if(rislevel==='high'){
								riskiskLevel='高'
							}
							var itemDetail;//详细信息
							var overdueDetails=new Array();//逾期详情
							var str="";
							var namelistHitDetails=new Array();//不良信息中的命中名单详情列表
							var fuzzyDetailHits=new Array();//命中模糊名单详情
							if(badInfo[i].item_detail != undefined && badInfo[i].item_detail != null){
								itemDetail=badInfo[i].item_detail;//取得详细信息
								if(itemDetail != undefined && itemDetail != null){
									if(itemDetail.overdue_details != undefined && itemDetail.overdue_details != "" ){
										overdueDetails=itemDetail.overdue_details;//取得逾期详情
									}
									if(itemDetail.namelist_hit_details != undefined && itemDetail.namelist_hit_details != null){
										namelistHitDetails=itemDetail.namelist_hit_details;//取得取命中名单详情列表
										for (var nhd = 0; nhd < namelistHitDetails.length; nhd++) {
											if(namelistHitDetails[nhd].fuzzy_detail_hits != undefined &&namelistHitDetails[nhd].fuzzy_detail_hits !=null ){
												fuzzyDetailHits=namelistHitDetails[nhd];//取得命中模糊名单详情
											}
										}
									}
								}
							}
							/////////***********/////////
							if(itemDetail != undefined && itemDetail != "" && itemDetail != null){
								for (var t = 0; t < namelistHitDetails.length; t++) {
										var temp2=namelistHitDetails[t];
										if(temp2.type=="fuzzy_list"){//模糊名单规则
											str+="<tr>";
											str+="<td width='300px'>"+badInfo[i].item_name+"</td>";
											str+="<td width='50px'>"+riskiskLevel+"</td>";
											str+="<td width='450px'>";
											for (var fi = 0; fi < fuzzyDetailHits.length; fi++) {
												str+="<li><font color='red'>风险类型：</font>"+fuzzyDetailHits[fi].fraud_type+"</li>" +
												"<li><font color='red'>匹配字段：</font>"+fuzzyDetailHits[fi].fuzzy_name+":"+fuzzyDetailHits[fi].fuzzy_id_number+"<li>";
											}
											str+="</td></tr>";
										}else if(temp2.type=="grey_list"){//关注名单规则
											var displayname="";
											for (var htdl = 0; htdl < namelistHitDetails.length; htdl++) {
												displayname+=namelistHitDetails[htdl].hit_type_displayname;
											}
											str+="<tr>";
											str+="<td width='300px'>"+badInfo[i].item_name+"</td>";
											str+="<td width='50px'>"+riskiskLevel+"</td>";
											str+="<td width='450px'><li><font color='red'>风险类型：</font>"+temp2.fraud_type+"</li>" +
											"<li><font color='red'>匹配字段：</font>"+displayname+"<li></td>";
											str+="</tr>";
											
										}else if(temp2.type=="black_list "){//风险名单规则
											var displayname="";
											for (var htdl = 0; htdl < namelistHitDetails.length; htdl++) {
												displayname+=namelistHitDetails[htdl].hit_type_displayname;
											}
											str+="<tr>";
											str+="<td width='300px'>"+badInfo[i].item_name+"</td>";
											str+="<td width='50px'>"+riskiskLevel+"</td>";
											str+="<td width='450px'><li><font color='red'>风险类型：</font>"+temp2.fraud_type+"</li>" +
											"<li><font color='red'>匹配字段：</font>"+displayname+"<li></td>";
											str+="</tr>";
										}
								}
								if(itemDetail.type=="custom_list"){//自定义列表规则
									str+="<tr>";
									str+="<td width='300px'>"+badInfo[i].item_name+"</td>";
									str+="<td width='50px'>"+riskiskLevel+"</td>";
									str+="<td width='450px'><li><font color='red'>详情地址</font>:"+itemDetail.high_risk_areas.toString()+"</li>" +
									"<li></td>";
									str+="</tr>";
								}
							}else{
								str+="<tr>";
								str+="<td width='300px'>"+badInfo[i].item_name+"</td>";
								str+="<td width='50px'>"+riskiskLevel+"</td>";
								str+="<td width='450px'></td>";
								str+="</tr>";
							}
							/**************************///////////
							
							
							//var discreditTimes=itemDetail.discredit_times;//逾期次数
							if(overdueDetails != undefined && overdueDetails.length >0){
								var overStr="";
								for (var int = 0; int < overdueDetails.length; int++) {
									overStr+="<li><font >逾期金额：</font>"+overdueDetails[int].overdue_amount+"</li>";
									overStr+="<li><font >逾期次数：</font>"+overdueDetails[int].overdue_count+"</li>";
									overStr+="<li><font >逾期天数：</font>"+overdueDetails[int].overdue_day+"</li>";
								}
								var detailstr='';
								str+="<tr>";
								str+="<td width='300px'>"+badInfo[i].item_name+"</td>";
								str+="<td width='50px'>"+riskiskLevel+"</td>";
								if(overdueDetails != undefined && overdueDetails != ""){
									str+="<td width='450px'><li><font color='red'>信贷逾期次数：</font>"+itemDetail.discredit_times+"</li>" +
									overStr+"</td>";
								}else{
									detailstr+="<td width='450px'></td>";
								}
								str+="</tr>";
							}
							$("#loan_blxx").append(str);
						}
//						//贷前风险情况-多平台借贷申请检测
						for(var i=0;i<platformInfo.length;i++){
							var  kLevel='';
							var trislevel = platformInfo[i].risk_level;
							if(trislevel==='low'){
								kLevel='低'
							}
							if(trislevel==='medium'){
								kLevel='中'
							}
							if(trislevel==='high'){
								kLevel='高'
							}
							var itemDetail=platformInfo[i].item_detail;//多平台申请详情对象
							
							var platformDetailDimension=itemDetail.platform_detail_dimension;//各维度多头详情
							
							var platformStr="";//各个维度多头借贷详情
							
							for (var int = 0; int < platformDetailDimension.length; int++) {
								platformStr+=platformDetailDimension[int].dimension+":"
								+platformDetailDimension[int].count+", [ "+platformDetailDimension[int].detail.toString()+" ] ";
							}
							
							
							var str='';
							str+="<tr>";
							str+="<td width='300px'>"+platformInfo[i].item_name+"</td>";
							str+="<td width='50px'>"+kLevel+"</td>";
							str+="<td width='450px' <li><font color='red'>总个数:"+itemDetail.platform_count+" [ "+itemDetail.platform_detail.toString()+" ] </font><li>\n\r"+
								platformStr+"</td>";
							str+="</tr>";
							$("#loan_dpt").append(str);
						}
//						
//						//贷前风险情况-客户行为检测
						for(var i=0;i<customerActionInfo.length;i++){
							var  tkLevel='';
							var tdrislevel = customerActionInfo[i].risk_level;
							if(tdrislevel==='low'){
								tkLevel='低'
							}
							if(tdrislevel==='medium'){
								tkLevel='中'
							}
							if(tdrislevel==='high'){
								tkLevel='高'
							}
							var itemDetail=customerActionInfo[i].item_detail;//客户行为检测详情
							
							var freStr="";
							if(itemDetail != undefined){
								var frequencyDetailList=itemDetail.frequency_detail_list;
								
								for (var int = 0; int < frequencyDetailList.length; int++) {
									if(frequencyDetailList[int].data != undefined && frequencyDetailList[int].data != ""){
										freStr+="<li>"+frequencyDetailList[int].detail+" [ "+frequencyDetailList[int].data.toString()+" ] </li>"
									}else{
										freStr+="<li>"+frequencyDetailList[int].detail+"  </li>"
									}
								}
							}else{
								freStr+="无详情";
							}
							var str='';
							str+="<tr>";
							str+="<td width='300px'>"+customerActionInfo[i].item_name+"</td>";
							str+="<td width='50px'>"+tkLevel+"</td>";
							str+="<td width='450px'>"+freStr+"</td>";
							str+="</tr>";
							$("#loan_khxw").append(str);
						}
						
//						//法院数据
						var courtInfoHtml = "";
						var courtDetails =new Array();//法院详情
						for (var it = 0; it < tdRiskItems.length; it++) {
							if(tdRiskItems[it].item_detail != undefined && tdRiskItems[it].item_detail != ""){
								if(tdRiskItems[it].item_detail.namelist_hit_details != undefined && tdRiskItems[it].item_detail.namelist_hit_details != ""){
									for (var co = 0; co < tdRiskItems[it].item_detail.namelist_hit_details.length; co++) {
										if(tdRiskItems[it].item_detail.namelist_hit_details[co].court_details != undefined && tdRiskItems[it].item_detail.namelist_hit_details[co].court_details != ""){
											courtDetails.push(tdRiskItems[it].item_detail.namelist_hit_details[co].court_details);
										}
									}
								}
							}
						}
						for(var i=0;i<courtDetails.length;i++){
							courtInfoHtml += "<legend  style=\"margin-left:40%\">附件：法院详情 - "+courtDetails[i][0].fraud_type+"名单</legend>";
							courtInfoHtml += "<table class=\"layui-table\"> ";
							courtInfoHtml += "<tbody>";
							//被执行人姓名
							var name=courtDetails[i][0].name != undefined?courtDetails[i][0].name:"-";
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">被执行人姓名</td>";
							courtInfoHtml +="<td>"+name+"</td>"
							courtInfoHtml += "</tr>";
							
							//年龄
							var age =courtDetails[i][0].age != undefined?courtDetails[i][0].age:"-";
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">年龄</td>";
							courtInfoHtml +="<td>"+age+"</td>";
							courtInfoHtml += "</tr>";
							
							//性别
							var gender=courtDetails[i][0].gender != undefined?courtDetails[i][0].gender:"-"
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">性别</td>";
							courtInfoHtml +="<td>"+gender+"</td>";
							courtInfoHtml += "</tr>";
							
							//省份
							var province=courtDetails[i][0].province != undefined?courtDetails[i][0].province:"-";
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">省份</td>";
							courtInfoHtml +="<td>"+province+"</td>";
							courtInfoHtml += "</tr>";
							
							//立案时间
							var filing_time=courtDetails[i][0].filing_time != undefined?courtDetails[i][0].filing_time:"-";
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">立案时间</td>";
							courtInfoHtml +="<td>"+filing_time+"</td>";
							courtInfoHtml += "</tr>";
							
							//执行法院
							var court_name=courtDetails[i][0].court_name != undefined?courtDetails[i][0].court_name:"-";
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">执行法院</td>";
							courtInfoHtml +="<td>"+court_name+"</td>";
							courtInfoHtml += "</tr>";
							
							//做出执行依据单位
							var execution_department=courtDetails[i][0].execution_department != undefined?courtDetails[i][0].execution_department:"-";
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">做出执行依据单位</td>";
							courtInfoHtml +="<td>"+execution_department+"</td>";
							courtInfoHtml += "</tr>";
							
							//生效法律文书确定的义务
							var duty=courtDetails[i][0].duty != undefined?courtDetails[i][0].duty:"-";
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">生效法律文书确定的义务</td>";
							courtInfoHtml +="<td>"+duty+"</td>";
							courtInfoHtml += "</tr>";
							
							//被执行人的履行情况
							var situation=courtDetails[i][0].situation != undefined?courtDetails[i][0].situation:"-";
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">被执行人的履行情况</td>";
							courtInfoHtml +="<td>"+situation+"</td>";
							courtInfoHtml += "</tr>";
							
							//失信被执行人行为具体情形
							var discredit_detail=courtDetails[i][0].discredit_detail != undefined?courtDetails[i][0].discredit_detail:"-";
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">失信被执行人行为具体情形</td>";
							courtInfoHtml +="<td>"+discredit_detail+"</td>";
							courtInfoHtml += "</tr>";
							
							//执行依据文号
							var execution_base=courtDetails[i][0].execution_base != undefined?courtDetails[i][0].execution_base:"-";
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">执行依据文号</td>";
							courtInfoHtml +="<td>"+execution_base+"</td>";
							courtInfoHtml += "</tr>";
							
							//案号
							var case_number=courtDetails[i][0].case_number != undefined?courtDetails[i][0].case_number:"-";
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">案号</td>";
							courtInfoHtml +="<td>"+case_number+"</td>";
							
							//执行标的
							var execution_number=courtDetails[i][0].execution_number != undefined?courtDetails[i][0].execution_number:"-";
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">执行标的</td>";
							courtInfoHtml +="<td>"+execution_number+"</td>";
							
							//执行状态
							var execution_status=courtDetails[i][0].execution_status != undefined?courtDetails[i][0].execution_status:"-";
							courtInfoHtml += "<tr>";
							courtInfoHtml += "<td style=\"width: 340px\">执行状态</td>";
							courtInfoHtml +="<td>"+execution_status+"</td>";
							
							courtInfoHtml += "</tbody>";
							courtInfoHtml += "</table>";
						}
						$("#dev_court").html(courtInfoHtml);
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
function cTime(time) {
	var timestamp = time;//1403058804
	var newDate = new Date();
	newDate.setTime(timestamp);
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

function getDateStrFormat(s){
	if(s!==undefined && s.length>19){
		return s.substring(0,19);
	}
	return s
}
/********************************************************************************************************************************************************/
function showDataBase(){
	var platformCustomerId = getQueryString("id");//获取页面传过来的平台用户编号
	 var applicationId = getQueryString("applicationId");//获取页面传过来的申请单编号
	 //applicationId="c77a7418b7ba4f8fa609c6bbc54e35b0";
	$.ajax({
				url : getCtxPath() + "/backend/td/getReport.do?v=" + new Date().getTime(),
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
						//$("#user_score font").eq(1).html(riskLevel);
						//$("#user_score font").eq(1).html(tdRiskCheckOne.riskLevel==null?'低':tdRiskCheckOne.riskLevel);
						//$("#user_score font").eq(2).html(tdAuditReport.proposal);
						$("#user_score font").eq(1).html(proposal);
	
						//账户基本信息
//						$("#user_custinfo font").eq(0).html(tdAuditReport.loanDate);
//						$("#user_custinfo font").eq(1).html(tdAuditReport.name);
//						$("#user_custinfo font").eq(2).html(tdAuditReport.phoneNum);
//						$("#user_custinfo font").eq(3).html(tdAuditReport.idCard);
						
						//归属地解析
						$("#user_address font").eq(0).html(tdAscriptionAnalysis.idCardAddress);
						$("#user_address font").eq(1).html(tdAscriptionAnalysis.mobileAddress);
						$("#user_address font").eq(2).html(tdAscriptionAnalysis.trueIpAddress);
						$("#user_address font").eq(3).html(tdAscriptionAnalysis.wifiAddress);
						$("#user_address font").eq(4).html(tdAscriptionAnalysis.cellAddress);
						$("#user_address font").eq(5).html(tdAscriptionAnalysis.bankCardAddress);
						
						//贷前风险情况-个人基本信息核查 
						$("#loan_userinfo").html(loanUserinfoTemp);
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
							//str+=loanUserinfoTemp;
							str+="<tr>";
							str+="<td width='300px'>"+tdRiskCheckByUserinfo[i].itemName+"</td>";
							str+="<td width='50px'>"+tdlevel+"</td>";
							str+="<td width='450px'><li><font color='red'>风险类型：</font>"+tdRiskCheckByUserinfo[i].fraudType+"</li>" +
									"<li><font color='red'>匹配字段：</font>"+tdRiskCheckByUserinfo[i].hitTypeDisplayName+"<li></td>";
							str+="</tr>";
							$("#loan_userinfo").append(str);
						}
						
						//贷前风险情况-不良信息扫描
						$("#loan_blxx").html(loanBlxxTemp);
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
							//str+=loanBlxxTemp;
							str+="<tr>";
							str+="<td width='300px'>"+tdRiskCheckByBlxx[i].itemName+"</td>";
							str+="<td width='50px'>"+risriskLevel+"</td>";
							str+="<td width='450px'><li><font color='red'>风险类型：</font>"+tdRiskCheckByBlxx[i].fraudType+"</li>" +
									"<li><font color='red'>匹配字段：</font>"+tdRiskCheckByBlxx[i].hitTypeDisplayName+"<li></td>";
							str+="</tr>";
							$("#loan_blxx").append(str);
						}
						
						//贷前风险情况-多平台借贷申请检测
						$("#loan_dpt").html(loanDptTemp);
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
							//str+=loanDptTemp;
							str+="<tr>";
							str+="<td width='300px'>"+tdRiskCheckByDpt[i].itemName+"</td>";
							str+="<td width='50px'>"+kLevel+"</td>";
							str+="<td width='450px' <li><font color='red'>"+tdRiskCheckByDpt[i].description+"</font><li>\n\r"+
										tdRiskCheckByDpt[i].fraudType +"</td>";
							str+="</tr>";
							$("#loan_dpt").append(str);
						}
						
						//贷前风险情况-客户行为检测
						$("#loan_khxw").html(loanKhxwTemp);
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
							//str+=loanKhxwTemp;
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
}
/**********************************************************************************************************************************************************/
function shouwAliyun(){
	 
	  var applicationId = getQueryString("applicationId");//获取页面传过来的申请单编号  
	  var platformCustomerId = getQueryString("id");//获取页面传过来的平台用户编号
	  if(applicationId == undefined){
		  applicationId='00';
	  }
	  if(platformCustomerId == undefined){
		  platformCustomerId='00';
	  }
	  //applicationId='e77d909b202a453383e2cf235a8bc113';
	 $.ajax({
			url : getCtxPath() + "/backend/queryAliyunDataInfo/getInfo.do?v=" + new Date().getTime(),
			method : "post",
			dataType : "json",
			data : { 
				taskType : '03001',
				applicationId : applicationId,
				platformId:platformCustomerId
			},
			success : function(data) { 
				/******************/

				if(data.code == 200 ){
				if(data.data.info == null ) return;	
				var tempJson=eval('('+data.data.info+')');
				if(tempJson.success == true){
					var msg=tempJson;
					var tdRiskItems = msg.risk_items;//检查项数据
					var tdAddressDetect=msg.address_detect;//归属地信息
					var applyTime=cTime(msg.apply_time);//申请日期
					var reportTime=cTime(msg.report_time);
					$("#report_id").html(msg.report_id);//报告编号
					$("#app_id").html(msg.application_id);//申请单编号
					$("#repord_date").html(reportTime);//报告时间
					var badInfo=new Array();//不良信息扫描
					var personalInfo=new Array();//个人基本信息核查
					var relationPersonInfo=new Array();//关联人信息扫描
					var platformInfo=new Array();//多平台申请检测
					var customerActionInfo=new Array();//客户行为检测
					var riskInfo=new Array();//风险信息扫描
					for (var it = 0; it < tdRiskItems.length; it++) {
						if(tdRiskItems[it].group=="不良信息扫描"){
							badInfo.push(tdRiskItems[it]);
						}else if(tdRiskItems[it].group=="个人基本信息核查"){
							personalInfo.push(tdRiskItems[it]);
						}else if(tdRiskItems[it].group=="关联人信息扫描"){
							relationPersonInfo.push(tdRiskItems[it]);
						}else if(tdRiskItems[it].group=="多平台借贷申请检测"){
							platformInfo.push(tdRiskItems[it]);
						}else if(tdRiskItems[it].group=="客户行为检测"){
							customerActionInfo.push(tdRiskItems[it]);
						}else if(tdRiskItems[it].group=="风险信息扫描"){
							riskInfo.push(tdRiskItems[it]);
						}
					}
					var proposal ='';
					var tdproposal= msg.final_decision;//审核建议
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
					$("#user_score font").eq(0).html(msg.final_score);
					//$("#user_score font").eq(1).html(riskLevel);
					$("#user_score font").eq(1).html(proposal);

					//账户基本信息
//					$("#user_custinfo font").eq(0).html(tdAuditReport.loanDate);
//					$("#user_custinfo font").eq(1).html(tdAuditReport.name);
//					$("#user_custinfo font").eq(2).html(tdAuditReport.phoneNum);
//					$("#user_custinfo font").eq(3).html(tdAuditReport.idCard);
					
//					//归属地解析
					var idCardAddress=tdAddressDetect.id_card_address == undefined?"-":tdAddressDetect.id_card_address;//身份证地址
					var mobileAddress=tdAddressDetect.mobile_address == undefined?"-":tdAddressDetect.mobile_address;//手机号地址
					var trueIpAddress=tdAddressDetect.true_ip_address == undefined?"-":tdAddressDetect.true_ip_address;//IP地址
					var wifiAddress=tdAddressDetect.wifi_address == undefined?"-":tdAddressDetect.wifi_address;//wifi地址
					var cellAddress=tdAddressDetect.cell_address == undefined?"-":tdAddressDetect.cell_address;//基站地址
					var bankCardAddress=tdAddressDetect.bank_card_address == undefined?"-":tdAddressDetect.bank_card_address;//银行卡地址
					$("#user_address font").eq(0).html(idCardAddress);
					$("#user_address font").eq(1).html(mobileAddress);
					$("#user_address font").eq(2).html(trueIpAddress);
					$("#user_address font").eq(3).html(wifiAddress);
					$("#user_address font").eq(4).html(cellAddress);
					$("#user_address font").eq(5).html(bankCardAddress);
					//贷前风险情况-个人基本信息核查 
					$("#loan_userinfo").html(loanUserinfoTemp);
					for(var i=0;i<personalInfo.length;i++){
						var tdriskLevel='';
						var tdlevel = personalInfo[i].risk_level;//风险等级
						var itemDetail ;//详细信息
						if(personalInfo[i].item_detail != undefined && personalInfo[i].item_detail !='' &&  personalInfo[i].item_detail != null){
							itemDetail=personalInfo[i].item_detail;
						}
						var namelistHitDetails=new Array();//命中名单详情列表
						
						var fuzzyDetailHits;//命中模糊名单详情
						if(itemDetail != undefined){
							if(itemDetail.namelist_hit_details != undefined && itemDetail.namelist_hit_details != null){
								namelistHitDetails=itemDetail.namelist_hit_details;//取得取命中名单详情列表	
								//namelistHitDetails.push(itemDetail.namelist_hit_details);//取得取命中名单详情列表
								for (var nhd = 0; nhd < namelistHitDetails.length; nhd++) {
									if(namelistHitDetails[nhd].fuzzy_detail_hits != undefined &&namelistHitDetails[nhd].fuzzy_detail_hits !=null ){
										fuzzyDetailHits=namelistHitDetails[nhd].fuzzy_detail_hits;//取得命中模糊名单详情
									}
								}
							}
						}
						if(tdlevel==='low'){
							tdriskLevel='低'
						}
						if(tdlevel==='medium'){
							tdriskLevel='中'
						}
						if(tdlevel==='high'){
							tdriskLevel='高'
						}
						var str='';
						/*var fraudType='';
						var hitTypeDisplayName='';
						for (var int = 0; int < namelistHitDetails.length; int++) {//获取风险类型和匹配字段
							fraudType+=namelistHitDetails[int].fraud_type;
							hitTypeDisplayName=namelistHitDetails[int].hit_type_displayname;
						}*/
						if(itemDetail != undefined && itemDetail != "" && itemDetail != null){
							for (var int = 0; int < namelistHitDetails.length; int++) {
								if(namelistHitDetails[int].type=="fuzzy_list"){//模糊名单规则
									str+="<tr>";
									str+="<td width='300px'>"+personalInfo[i].item_name+"</td>";
									str+="<td width='50px'>"+tdriskLevel+"</td>";
									str+="<td width='450px'>";
									for (var fi = 0; fi < fuzzyDetailHits.length; fi++) {
										str+="<li><font color='red'>风险类型：</font>"+fuzzyDetailHits[fi].fraud_type+"</li>" ;
										str+="<li><font color='red'>匹配字段：</font>"+fuzzyDetailHits[fi].fuzzy_name+":"+fuzzyDetailHits[fi].fuzzy_id_number+"</li>";
									}
									str+="</td></tr>";
								}else if(namelistHitDetails[int].type=="grey_list"){//关注名单规则
									str+="<tr>";
									str+="<td width='300px'>"+personalInfo[i].item_name+"</td>";
									str+="<td width='50px'>"+tdriskLevel+"</td>";
									str+="<td width='450px'><li><font color='red'>风险类型：</font>"+namelistHitDetails[int].fraud_type+"</li>" +
									"<li><font color='red'>匹配字段：</font>"+namelistHitDetails[int].hit_type_displayname+"<li></td>";
									str+="</tr>";
									
								}else if(namelistHitDetails[int].type=="black_list"){//风险名单规则
									str+="<tr>";
									str+="<td width='300px'>"+personalInfo[i].item_name+"</td>";
									str+="<td width='50px'>"+tdriskLevel+"</td>";
									str+="<td width='450px'><li><font color='red'>风险类型：</font>"+namelistHitDetails[int].fraud_type+"</li>" +
									"<li><font color='red'>匹配字段：</font>"+namelistHitDetails[int].hit_type_displayname+"<li></td>";
									str+="</tr>";
								}
							}
							if(itemDetail.type=="custom_list"){//自定义列表规则
								str+="<tr>";
								str+="<td width='300px'>"+personalInfo[i].item_name+"</td>";
								str+="<td width='50px'>"+tdriskLevel+"</td>";
								str+="<td width='450px'><li><font color='red'>详情地址</font>:"+itemDetail.high_risk_areas.toString()+"</li></td>";
								str+="</tr>";
							}
						}else{
							str+="<tr>";
							str+="<td width='300px'>"+personalInfo[i].item_name+"</td>";
							str+="<td width='50px'>"+tdriskLevel+"</td>";
							str+="<td width='450px'></td>";
							str+="</tr>";
						}
						$("#loan_userinfo").append(str);
					}
					
					//贷前风险情况-不良信息扫描
					$("#loan_blxx").html(loanBlxxTemp);
					for(var i=0;i<badInfo.length;i++){
						var riskiskLevel='';
						var rislevel = badInfo[i].risk_level;
						if(rislevel==='low'){
							riskiskLevel='低'
						}
						if(rislevel==='medium'){
							riskiskLevel='中'
						}
						if(rislevel==='high'){
							riskiskLevel='高'
						}
						var itemDetail;//详细信息
						var overdueDetails=new Array();//逾期详情
						var str="";
						var namelistHitDetails=new Array();//不良信息中的命中名单详情列表
						var fuzzyDetailHits=new Array();//命中模糊名单详情
						if(badInfo[i].item_detail != undefined && badInfo[i].item_detail != null){
							itemDetail=badInfo[i].item_detail;//取得详细信息
							if(itemDetail != undefined && itemDetail != null){
								if(itemDetail.overdue_details != undefined && itemDetail.overdue_details != "" ){
									overdueDetails=itemDetail.overdue_details;//取得逾期详情
								}
								if(itemDetail.namelist_hit_details != undefined && itemDetail.namelist_hit_details != null){
									namelistHitDetails=itemDetail.namelist_hit_details;//取得取命中名单详情列表
									for (var nhd = 0; nhd < namelistHitDetails.length; nhd++) {
										if(namelistHitDetails[nhd].fuzzy_detail_hits != undefined &&namelistHitDetails[nhd].fuzzy_detail_hits !=null ){
											fuzzyDetailHits=namelistHitDetails[nhd];//取得命中模糊名单详情
										}
									}
								}
							}
						}
						/////////***********/////////
						if(itemDetail != undefined && itemDetail != "" && itemDetail != null){
							for (var t = 0; t < namelistHitDetails.length; t++) {
									var temp2=namelistHitDetails[t];
									if(temp2.type=="fuzzy_list"){//模糊名单规则
										str+="<tr>";
										str+="<td width='300px'>"+badInfo[i].item_name+"</td>";
										str+="<td width='50px'>"+riskiskLevel+"</td>";
										str+="<td width='450px'>";
										for (var fi = 0; fi < fuzzyDetailHits.length; fi++) {
											str+="<li><font color='red'>风险类型：</font>"+fuzzyDetailHits[fi].fraud_type+"</li>" +
											"<li><font color='red'>匹配字段：</font>"+fuzzyDetailHits[fi].fuzzy_name+":"+fuzzyDetailHits[fi].fuzzy_id_number+"<li>";
										}
										str+="</td></tr>";
									}else if(temp2.type=="grey_list"){//关注名单规则
										var displayname="";
										for (var htdl = 0; htdl < namelistHitDetails.length; htdl++) {
											displayname+=namelistHitDetails[htdl].hit_type_displayname;
										}
										str+="<tr>";
										str+="<td width='300px'>"+badInfo[i].item_name+"</td>";
										str+="<td width='50px'>"+riskiskLevel+"</td>";
										str+="<td width='450px'><li><font color='red'>风险类型：</font>"+temp2.fraud_type+"</li>" +
										"<li><font color='red'>匹配字段：</font>"+displayname+"<li></td>";
										str+="</tr>";
										
									}else if(temp2.type=="black_list "){//风险名单规则
										var displayname="";
										for (var htdl = 0; htdl < namelistHitDetails.length; htdl++) {
											displayname+=namelistHitDetails[htdl].hit_type_displayname;
										}
										str+="<tr>";
										str+="<td width='300px'>"+badInfo[i].item_name+"</td>";
										str+="<td width='50px'>"+riskiskLevel+"</td>";
										str+="<td width='450px'><li><font color='red'>风险类型：</font>"+temp2.fraud_type+"</li>" +
										"<li><font color='red'>匹配字段：</font>"+displayname+"<li></td>";
										str+="</tr>";
									}
							}
							if(itemDetail.type=="custom_list"){//自定义列表规则
								str+="<tr>";
								str+="<td width='300px'>"+badInfo[i].item_name+"</td>";
								str+="<td width='50px'>"+riskiskLevel+"</td>";
								str+="<td width='450px'><li><font color='red'>详情地址</font>:"+itemDetail.high_risk_areas.toString()+"</li>" +
								"<li></td>";
								str+="</tr>";
							}
						}else{
							str+="<tr>";
							str+="<td width='300px'>"+badInfo[i].item_name+"</td>";
							str+="<td width='50px'>"+riskiskLevel+"</td>";
							str+="<td width='450px'></td>";
							str+="</tr>";
						}
						/**************************///////////
						
						
						//var discreditTimes=itemDetail.discredit_times;//逾期次数
						if(overdueDetails != undefined && overdueDetails.length >0){
							var overStr="";
							for (var int = 0; int < overdueDetails.length; int++) {
								overStr+="<li><font >逾期金额：</font>"+overdueDetails[int].overdue_amount+"</li>";
								overStr+="<li><font >逾期次数：</font>"+overdueDetails[int].overdue_count+"</li>";
								overStr+="<li><font >逾期天数：</font>"+overdueDetails[int].overdue_day+"</li>";
							}
							var detailstr='';
							str+="<tr>";
							str+="<td width='300px'>"+badInfo[i].item_name+"</td>";
							str+="<td width='50px'>"+riskiskLevel+"</td>";
							if(overdueDetails != undefined && overdueDetails != ""){
								str+="<td width='450px'><li><font color='red'>信贷逾期次数：</font>"+itemDetail.discredit_times+"</li>" +
								overStr+"</td>";
							}else{
								detailstr+="<td width='450px'></td>";
							}
							str+="</tr>";
						}
						$("#loan_blxx").append(str);
					}
//					//贷前风险情况-多平台借贷申请检测
					$("#loan_dpt").html(loanDptTemp);
					for(var i=0;i<platformInfo.length;i++){
						var  kLevel='';
						var trislevel = platformInfo[i].risk_level;
						if(trislevel==='low'){
							kLevel='低'
						}
						if(trislevel==='medium'){
							kLevel='中'
						}
						if(trislevel==='high'){
							kLevel='高'
						}
						var itemDetail=platformInfo[i].item_detail;//多平台申请详情对象
						
						var platformDetailDimension=itemDetail.platform_detail_dimension;//各维度多头详情
						
						var platformStr="";//各个维度多头借贷详情
						
						for (var int = 0; int < platformDetailDimension.length; int++) {
							platformStr+=platformDetailDimension[int].dimension+":"
							+platformDetailDimension[int].count+", [ "+platformDetailDimension[int].detail.toString()+" ] ";
						}
						
						
						var str='';
						str+="<tr>";
						str+="<td width='300px'>"+platformInfo[i].item_name+"</td>";
						str+="<td width='50px'>"+kLevel+"</td>";
						str+="<td width='450px' <li><font color='red'>总个数:"+itemDetail.platform_count+" [ "+itemDetail.platform_detail.toString()+" ] </font><li>\n\r"+
							platformStr+"</td>";
						str+="</tr>";
						$("#loan_dpt").append(str);
					}
//					
//					//贷前风险情况-客户行为检测
					$("#loan_khxw").html(loanKhxwTemp);
					for(var i=0;i<customerActionInfo.length;i++){
						var  tkLevel='';
						var tdrislevel = customerActionInfo[i].risk_level;
						if(tdrislevel==='low'){
							tkLevel='低'
						}
						if(tdrislevel==='medium'){
							tkLevel='中'
						}
						if(tdrislevel==='high'){
							tkLevel='高'
						}
						var itemDetail=customerActionInfo[i].item_detail;//客户行为检测详情
						
						var freStr="";
						if(itemDetail != undefined){
							var frequencyDetailList=itemDetail.frequency_detail_list;
							
							for (var int = 0; int < frequencyDetailList.length; int++) {
								if(frequencyDetailList[int].data != undefined && frequencyDetailList[int].data != ""){
									freStr+="<li>"+frequencyDetailList[int].detail+" [ "+frequencyDetailList[int].data.toString()+" ] </li>"
								}else{
									freStr+="<li>"+frequencyDetailList[int].detail+"  </li>"
								}
							}
						}else{
							freStr+="无详情";
						}
						var str='';
						str+="<tr>";
						str+="<td width='300px'>"+customerActionInfo[i].item_name+"</td>";
						str+="<td width='50px'>"+tkLevel+"</td>";
						str+="<td width='450px'>"+freStr+"</td>";
						str+="</tr>";
						$("#loan_khxw").append(str);
					}
					
//					//法院数据
					var courtInfoHtml = "";
					var courtDetails =new Array();//法院详情
					for (var it = 0; it < tdRiskItems.length; it++) {
						if(tdRiskItems[it].item_detail != undefined && tdRiskItems[it].item_detail != ""){
							if(tdRiskItems[it].item_detail.namelist_hit_details != undefined && tdRiskItems[it].item_detail.namelist_hit_details != ""){
								for (var co = 0; co < tdRiskItems[it].item_detail.namelist_hit_details.length; co++) {
									if(tdRiskItems[it].item_detail.namelist_hit_details[co].court_details != undefined && tdRiskItems[it].item_detail.namelist_hit_details[co].court_details != ""){
										courtDetails.push(tdRiskItems[it].item_detail.namelist_hit_details[co].court_details);
									}
								}
							}
						}
					}
					for(var i=0;i<courtDetails.length;i++){
						courtInfoHtml += "<legend  style=\"margin-left:40%\">附件：法院详情 - "+courtDetails[i][0].fraud_type+"名单</legend>";
						courtInfoHtml += "<table class=\"layui-table\"> ";
						courtInfoHtml += "<tbody>";
						//被执行人姓名
						var name=courtDetails[i][0].name != undefined?courtDetails[i][0].name:"-";
						courtInfoHtml += "<tr>";
						courtInfoHtml += "<td style=\"width: 340px\">被执行人姓名</td>";
						courtInfoHtml +="<td>"+name+"</td>"
						courtInfoHtml += "</tr>";
						
						//年龄
						var age =courtDetails[i][0].age != undefined?courtDetails[i][0].age:"-";
						courtInfoHtml += "<tr>";
						courtInfoHtml += "<td style=\"width: 340px\">年龄</td>";
						courtInfoHtml +="<td>"+age+"</td>";
						courtInfoHtml += "</tr>";
						
						//性别
						var gender=courtDetails[i][0].gender != undefined?courtDetails[i][0].gender:"-"
						courtInfoHtml += "<tr>";
						courtInfoHtml += "<td style=\"width: 340px\">性别</td>";
						courtInfoHtml +="<td>"+gender+"</td>";
						courtInfoHtml += "</tr>";
						
						//省份
						var province=courtDetails[i][0].province != undefined?courtDetails[i][0].province:"-";
						courtInfoHtml += "<tr>";
						courtInfoHtml += "<td style=\"width: 340px\">省份</td>";
						courtInfoHtml +="<td>"+province+"</td>";
						courtInfoHtml += "</tr>";
						
						//立案时间
						var filing_time=courtDetails[i][0].filing_time != undefined?courtDetails[i][0].filing_time:"-";
						courtInfoHtml += "<tr>";
						courtInfoHtml += "<td style=\"width: 340px\">立案时间</td>";
						courtInfoHtml +="<td>"+filing_time+"</td>";
						courtInfoHtml += "</tr>";
						
						//执行法院
						var court_name=courtDetails[i][0].court_name != undefined?courtDetails[i][0].court_name:"-";
						courtInfoHtml += "<tr>";
						courtInfoHtml += "<td style=\"width: 340px\">执行法院</td>";
						courtInfoHtml +="<td>"+court_name+"</td>";
						courtInfoHtml += "</tr>";
						
						//做出执行依据单位
						var execution_department=courtDetails[i][0].execution_department != undefined?courtDetails[i][0].execution_department:"-";
						courtInfoHtml += "<tr>";
						courtInfoHtml += "<td style=\"width: 340px\">做出执行依据单位</td>";
						courtInfoHtml +="<td>"+execution_department+"</td>";
						courtInfoHtml += "</tr>";
						
						//生效法律文书确定的义务
						var duty=courtDetails[i][0].duty != undefined?courtDetails[i][0].duty:"-";
						courtInfoHtml += "<tr>";
						courtInfoHtml += "<td style=\"width: 340px\">生效法律文书确定的义务</td>";
						courtInfoHtml +="<td>"+duty+"</td>";
						courtInfoHtml += "</tr>";
						
						//被执行人的履行情况
						var situation=courtDetails[i][0].situation != undefined?courtDetails[i][0].situation:"-";
						courtInfoHtml += "<tr>";
						courtInfoHtml += "<td style=\"width: 340px\">被执行人的履行情况</td>";
						courtInfoHtml +="<td>"+situation+"</td>";
						courtInfoHtml += "</tr>";
						
						//失信被执行人行为具体情形
						var discredit_detail=courtDetails[i][0].discredit_detail != undefined?courtDetails[i][0].discredit_detail:"-";
						courtInfoHtml += "<tr>";
						courtInfoHtml += "<td style=\"width: 340px\">失信被执行人行为具体情形</td>";
						courtInfoHtml +="<td>"+discredit_detail+"</td>";
						courtInfoHtml += "</tr>";
						
						//执行依据文号
						var execution_base=courtDetails[i][0].execution_base != undefined?courtDetails[i][0].execution_base:"-";
						courtInfoHtml += "<tr>";
						courtInfoHtml += "<td style=\"width: 340px\">执行依据文号</td>";
						courtInfoHtml +="<td>"+execution_base+"</td>";
						courtInfoHtml += "</tr>";
						
						//案号
						var case_number=courtDetails[i][0].case_number != undefined?courtDetails[i][0].case_number:"-";
						courtInfoHtml += "<tr>";
						courtInfoHtml += "<td style=\"width: 340px\">案号</td>";
						courtInfoHtml +="<td>"+case_number+"</td>";
						
						//执行标的
						var execution_number=courtDetails[i][0].execution_number != undefined?courtDetails[i][0].execution_number:"-";
						courtInfoHtml += "<tr>";
						courtInfoHtml += "<td style=\"width: 340px\">执行标的</td>";
						courtInfoHtml +="<td>"+execution_number+"</td>";
						
						//执行状态
						var execution_status=courtDetails[i][0].execution_status != undefined?courtDetails[i][0].execution_status:"-";
						courtInfoHtml += "<tr>";
						courtInfoHtml += "<td style=\"width: 340px\">执行状态</td>";
						courtInfoHtml +="<td>"+execution_status+"</td>";
						
						courtInfoHtml += "</tbody>";
						courtInfoHtml += "</table>";
					}
					$("#dev_court").html(courtInfoHtml);
				}
				}
			
				/****************/
			},
			error : function() {
				layer.msg("获取阿里云数据信息失败,请尝试重新获取！");
			}
		}); 
}