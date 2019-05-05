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
	 if(applicationId == "" && platformCustomerId == ""){
		 applicationId="-1";
		 platformCustomerId="-1";
		}
	 //var url="http://shandai-real.oss-cn-shanghai.aliyuncs.com/jxlmg/20170828/8c8a1369863f4b8ec6f79a2366d5d952.json";
	 //applicationId="18328ab74b6e423cb05960510fcd3f68";
	$.ajax({
				url : getCtxPath() + "/backend/miguan/getJxlMGJsonReport.do?v=" + new Date().getTime(),
				method : "post",
				dataType : "json",
				data : {
					platformId : platformCustomerId,
					buApplicationId : applicationId
				},
				success : function(data) {
					if(data.code == 200 ){
						if(data.list.data == null ) return;
						if(data.list.data.jsonInfo1.message == '获取蜜罐查询成功'){
							
							var msg=data.list.data.jsonInfo1.data;
							
							var user_basic=msg.user_basic;//用户基本信息
							var userBlacklist=msg.user_blacklist;//黑名单信息
							var userGray=msg.user_gray;//用户灰度分数信息
							var userIdcardSuspicion=msg.user_idcard_suspicion;//身份证号码存疑
							var userPhoneSuspicion=msg.user_phone_suspicion;//手机号码存疑
							var userRegisterOrgs=msg.user_register_orgs;//用户注册信息情况
							var userSearchedHistoryByOrgs=msg.user_searched_history_by_orgs;//用户被机构查询历史
							var userSearchedStatistic=msg.user_searched_statistic;//被机构查询数量统计
							
							$("#user_useId").html(msg.user_grid_id);
							var html = "";
						    html+="<tr>";
							html+="<td  rowspan=10>基本信息</td>";
						    html+="</tr>";
						    html+="<tr>";
						    html+="<td colspan=2>姓名</td>";
						    html+="<td colspan=3>"+user_basic.user_name+"</td>";
							html+="</tr>";
						    html+="<tr>";
						    html+="<td colspan=2>身份证号码有效</td>";
						    if(user_basic.user_idcard_valid == true){
							    html+="<td colspan=3>是</td>";
						    }else{
							    html+="<td colspan=3>否</td>";
						    }
							html+="</tr>";
						    html+="<tr>";
						    html+="<td colspan=2>身份证归属地</td>";
						    html+="<td colspan=3>"+user_basic.user_province+"/"+user_basic.user_city+"/"+user_basic.user_region+"</td>";
							html+="</tr>";
						    html+="<tr>";
						    html+="<td colspan=2>手机号码归属地</td>";
						    html+="<td colspan=3>"+user_basic.user_phone_province+"/"+user_basic.user_phone_city+"</td>";
							html+="</tr>";
						    html+="<tr>";
						    html+="<td colspan=2>手机所属运营商</td>";
						    html+="<td colspan=3>"+user_basic.user_phone_operator+"</td>";
							html+="</tr>";
						    html+="<tr>";
						    html+="<td colspan=2>身份证号码</td>";
							html+="<td colspan=3>"+user_basic.user_idcard+"</td>";
							html+="</tr>";
						    html+="<tr>";
						    html+="<td colspan=2>手机号码</td>";
							html+="<td colspan=3>"+user_basic.user_phone+"</td>";
							html+="</tr>";
						    html+="<tr>";
						    html+="<td colspan=2>年龄</td>";
						    html+="<td colspan=3>"+user_basic.user_age+"</td>";
							html+="</tr>";
						    html+="<tr>";
						    html+="<td colspan=2>性别</td>";
							html+="<td colspan=3>"+user_basic.user_gender+"</td>";
							html+="</tr>";
							
						    html+="<tr>";
						    html+="<td colspan=6>&nbsp;</td>";
							html+="</tr>";
							
							var len = userRegisterOrgs.register_cnt;
							if(isNaN(userRegisterOrgs.register_cnt)){
								userRegisterOrgs.register_cnt="0";
								len=0;
							}
							html+="<tr>";
							html+="<td rowspan="+(len+2)+">注册信息</td> ";
							html+="<td  colspan=2>注册App数量</td>";
							html+="<td colspan=3 >"+userRegisterOrgs.register_cnt+"</td>";
							html+="</tr>";
							html+="<tr>";
							html+="<td rowspan="+(len+1)+">注册App统计</td>";
							html+="<td colspan=2>App类型</td>"; 
							html+="<td colspan=3>注册数量</td>"; 
							html+="</tr>";
							var mgRegisterOrgsDetails=userRegisterOrgs.register_orgs_statistics;
							for(var i=0;i<mgRegisterOrgsDetails.length;i++){
								var details = mgRegisterOrgsDetails[i];
								html+="<tr>"; 
								html+="<td colspan=2>"+details.label+"</td>"; 
								html+="<td colspan=3>"+details.count+"</td>"; 
								html+="</tr>";
							}
	
						    html+="<tr>";
						    html+="<td colspan=6>&nbsp;</td>";
							html+="</tr>";
							
							
							var blacklistDetails=userBlacklist.blacklist_details;
							len = blacklistDetails.length;
							html+="<tr>";
							html+="<td rowspan="+(len+12)+">黑名单信息</td>";
							html+="<td colspan=2 >黑中介分数</td> ";
							if(userGray.phone_gray_score >10){
								html+="<td colspan=3>"+userGray.phone_gray_score+" (分数区间为0-100，10分以下为高危人群）</td>";
							}else{
								html+="<td colspan=3 style=\"color:red\">"+userGray.phone_gray_score+" (分数区间为0-100，10分以下为高危人群）</td>";
							}
							html+="</tr>";
							html+="<tr>";
							html+="<td colspan=2 >直接联系人在黑名单数量</td> ";
							html+="<td colspan=3 >"+userGray.contacts_class1_blacklist_cnt+" （直接联系人：和被查询号码有通话记录）</td>";
							html+="</tr>";
							html+="<tr>";
							html+="<td colspan=2 >直接联系人总数</td> ";
							html+="<td colspan=3 >"+userGray.contacts_class1_cnt+"（直接联系人：和被查询号码有通话记录）</td>";
							html+="</tr>";
							html+="<tr>";
							html+="<td  colspan=2>间接联系人在黑名单数量</td>";
							html+="<td colspan=3  >"+userGray.contacts_class2_blacklist_cnt+"（间接联系人：和被查询号码的直接联系人有通话记录）</td>";
							html+="</tr>";
							html+="<tr>";
							html+="<td colspan=2 >引起黑名单的直接联系人数量</td>";
							html+="<td colspan=3  >"+userGray.contacts_router_cnt+"（直接联系人有和黑名单用户的通话记录的号码数量）</td>";
							html+="</tr>";
							html+="<tr>";
							html+="<td colspan=2 >引起黑名单的直接联系人占比</td>";
							html+="<td  colspan=3 >"+getPerFormat(userGray.contacts_router_ratio)+"（直接联系人有和黑名单用户的通讯记录的号码数量在直接联系人数量中的百分比）</td>";
							html+="</tr>";
							html+="<tr>";
							html+="<td colspan=2 >被标记的黑名单分类</td>";
						
							html+="<td colspan=3  >"+userBlacklist.blacklist_category.toString()+"</td>";
							html+="</tr>";
							html+="<tr>";
							html+="<td colspan=2 >手机和姓名是否在黑名单</td>";
							if(userBlacklist.blacklist_name_with_phone == true){
								html+="<td colspan=3  style=\"color:red\">是</td>";
							}else{
								html+="<td colspan=3  >否</td>";
							}
							html+="</tr>";
							html+="<tr>";
							html+="<td colspan=2 >身份证姓名黑名单更新时间</td> ";
							html+="<td colspan=3 >"+userBlacklist.blacklist_update_time_name_idcard+"</td>";
							html+="</tr>";
							html+="<tr>";
							html+="<td colspan=2 >身份证和姓名是否在黑名单</td> ";
							if(userBlacklist.blacklist_update_time_name_idcard == true){
								html+="<td colspan=3  style=\"color:red\">是</td>";
							}else{
								html+="<td colspan=3  >否</td>";
							}
							html+="</tr>";
							html+="<tr>";
							html+="<td colspan=2 >姓名和电话黑名单更新信息</td>";
							html+="<td  colspan=3>"+userBlacklist.blacklist_update_time_name_phone+"</td>";
							html+="</tr>";
							
							html+="<tr>"; 
							html+="<td rowspan="+(len+1)+">黑名单详细信息</td>";
							html+="<td colspan=2>名称</td>";
							html+="<td colspan=2>内容</td>";
							html+="</tr>";
	
							for(var i=0;i<blacklistDetails.length;i++){
								var details = blacklistDetails[i];
								html+="<tr>"; 
								html+="<td colspan=2>"+details.details_key+"</td>"; 
								html+="<td colspan=3>"+details.details_value+"</td>"; 
								html+="</tr>";
							}
	
						    html+="<tr>";
						    html+="<td colspan=6>&nbsp;</td>";
							html+="</tr>";
							
							len = userSearchedHistoryByOrgs.length;
							html+="<tr>";
							html+="<td colspan=2>查询统计信息</td>";
							html+="<td colspan=2 >机构查询统计</td>";
							html+="<td colspan=3>"+userSearchedHistoryByOrgs.length+"（被多少机构查询过—已去重）</td>";
							html+="</tr>";
							html+="<tr>";
							html+="<td rowspan="+(len+2)+" >机构查询历史</td>";
							html+="</tr>";
							html+="<tr>";
							html+="<td colspan=2>查询日期</td>";
							html+="<td colspan=2>机构类型</td>";
							html+="<td colspan=2>是否是本机构查询</td>";
							html+="</tr>";
							for(var i=0;i<userSearchedHistoryByOrgs.length;i++){
								var details = userSearchedHistoryByOrgs[i];
								html+="<tr>";
								html+="<td colspan=2>"+getDateStrFormat(details.searched_date.substring(0,10))+"</td>";
								html+="<td colspan=2>"+details.searched_org+"</td>";
								if(details.org_self == true){
									html+="<td  colspan=2>是</td>";
								}else{
									html+="<td  colspan=2>否</td>";
								}
								html+="</tr>";
							}
	
						    html+="<tr>";
						    html+="<td colspan=6>&nbsp;</td>";
							html+="</tr>";
							
							var s1 = userPhoneSuspicion.phone_applied_in_orgs.length;
							var s2 = userPhoneSuspicion.phone_with_other_idcards.length;
							var s3 = userPhoneSuspicion.phone_with_other_names.length;
							len = s1 + s2 + s3;
							html+="<tr>";
							html+="<td rowspan="+(len+3)+">手机存疑</td> ";
							html+="<td rowspan="+(s3+1)+" >使用过此手机的其他姓名</td> ";
							html+="<td colspan=2>最后使用时间</td>";
							html+="<td colspan=2>姓名</td>";
							html+="</tr>";
							var paig=userPhoneSuspicion.phone_with_other_names;
							for(var i=0;i<paig.length;i++){
								var details = paig[i];
								html+="<tr>";
								html+="<td colspan=2>"+getDateStrFormat(details.susp_updt)+"</td>";
								html+="<td colspan=2>"+details.susp_name+"</td>";
								html+="</tr>";
							}
							
							html+="<tr>";
							html+="<td rowspan="+(s2+1)+" >使用过此手机的其他身份证</td> ";
							html+="<td colspan=2>最后使用时间</td>";
							html+="<td colspan=2>身份证号</td>";
							html+="</tr>";
							var pwoi=userPhoneSuspicion.phone_with_other_idcards;
							for(var i=0;i<pwoi.length;i++){
								var details = pwoi[i];
								html+="<tr>";
								html+="<td colspan=2>"+getDateStrFormat(details.susp_updt)+"</td>";
								html+="<td colspan=2>"+details.susp_idcard+"</td>";
								html+="</tr>";
							}
							
							var  paio=userPhoneSuspicion.phone_applied_in_orgs;
							html+="<tr>";
							html+="<td rowspan="+(s1+1)+" >提供数据的机构类型</td>"; 
							html+="<td colspan=2 >最后使用时间</td>";
							html+="<td colspan=2>机构类型</td>";
							html+="</tr>";
							
							for(var i=0;i<paio.length;i++){
								var details = paio[i];
								html+="<tr>";
								html+="<td colspan=2>"+getDateStrFormat(details.susp_updt)+"</td>";
								html+="<td colspan=2>"+details.susp_org_type+"</td>";
								html+="</tr>";
							}
							
						    html+="<tr>";
						    html+="<td colspan=6>&nbsp;</td>";
							html+="</tr>";
							
							var p1 = userIdcardSuspicion.idcard_with_other_phones.length;
							var p2 = userIdcardSuspicion.idcard_with_other_names.length;
							var p3 = userIdcardSuspicion.idcard_applied_in_orgs.length;
							len = p1 + p2 + p3
							
							html+="<tr>";
							html+="<td rowspan="+(len+3)+">身份证存疑</td> ";
							html+="<td rowspan="+(p1+1)+" >使用过此身份证的其他姓名</td> ";
							html+="<td colspan=2>最后使用时间</td>";
							html+="<td colspan=2>用户名称</td>";
							html+="</tr>";
							var iwon=userIdcardSuspicion.idcard_with_other_names;
							for(var i=0;i<iwon.length;i++){
								var details = iwon[i];
								html+="<tr>";
								html+="<td colspan=2>"+getDateStrFormat(details.susp_updt)+"</td>";
								html+="<td colspan=2>"+details.susp_name+"</td>";
								html+="</tr>";
							}
							
							html+="<tr>";
							html+="<td rowspan="+(p2+1)+" >使用过此身份证的其他手机</td> ";
							html+="<td>最后使用时间</td>";
							html+="<td >手机号码</td>";
							html+="<td >运营商名称</td>";
							html+="<td >归属地</td>";
							html+="</tr>";
							var iwop=userIdcardSuspicion.idcard_with_other_phones;
							for(var i=0;i<iwop.length;i++){
								var details = iwop[i];
								html+="<tr>";
								html+="<td colspan=1>"+getDateStrFormat(details.susp_updt)+"</td>";
								html+="<td colspan=1>"+details.susp_phone+"</td>";
								html+="<td colspan=1>"+details.susp_phone_operator+"</td>";
								html+="<td colspan=1>"+details.susp_phone_province+"/"+details.susp_phone_city+"</td>";
								html+="</tr>";
							}
							
							html+="<tr>";
							html+="<td rowspan="+(p3+1)+" >提供数据的机构类型</td> ";
							html+="<td colspan=2>最后使用时间</td>";
							html+="<td colspan=2>机构类型</td>";
							html+="</tr>";
							var iaio=userIdcardSuspicion.idcard_applied_in_orgs;
							for(var i=0;i<iaio.length;i++){
								var details = iaio[i];
								html+="<tr>";
								html+="<td colspan=2>"+getDateStrFormat(details.susp_updt)+"</td>";
								html+="<td colspan=2>"+details.susp_org_type+"</td>";
								html+="</tr>";
							}
							
							$("#report_content").html(html);
						}
					}
				},
				error : function() {
					alert("获取用户蜜罐报告失败,请尝试重新获取！");
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
/*************************************************************************************************************************************************************/
function showDataBase(){
	var platformCustomerId = getQueryString("id");//获取页面传过来的平台用户编号
	 var applicationId = getQueryString("applicationId");//获取页面传过来的申请单编号
	$.ajax({
				url : getCtxPath() + "/backend/miguan/getReport.do?v=" + new Date().getTime(),
				method : "post",
				dataType : "json",
				data : {
					platformCustomerId : platformCustomerId,
					applicationId : applicationId
				},
				success : function(msg) {
					if(msg.code == '200' && msg.list.successed){
						var data = msg.list;
						var mgUserBasic = data.mgUserBasic;
						var mgRegisterOrgs = data.mgRegisterOrgs;
						var mgRegisterOrgsDetails = data.mgRegisterOrgsDetails;
						var mgUserBlacklist = data.mgUserBlacklist;
						var mgBlacklistCategoryDetails = data.mgBlacklistCategoryDetails;
						var mgUserBlacklistDetails = data.mgUserBlacklistDetails;
						var mOgUserSearchedHistoryByOrgs = data.mOgUserSearchedHistoryByOrgs;
						var mgPhoneWithOtherIdcards = data.mgPhoneWithOtherIdcards;
						var mgPhoneWithOtherNames = data.mgPhoneWithOtherNames;
						var mgPhoneAppliedInOrgs = data.mgPhoneAppliedInOrgs;
						var mgIdcardWithOtherNames = data.mgIdcardWithOtherNames;
						var mgIdcardWithOtherPhones = data.mgIdcardWithOtherPhones;
						var mgIdcardAppliedInOrgs = data.mgIdcardAppliedInOrgs;
						
						$("#user_useId").html(mgUserBasic.userGridId);
						var html = "";
					    html+="<tr>";
						html+="<td  rowspan=10>基本信息</td>";
					    html+="</tr>";
					    html+="<tr>";
					    html+="<td colspan=2>姓名</td>";
					    html+="<td colspan=3>"+mgUserBasic.userName+"</td>";
						html+="</tr>";
					    html+="<tr>";
					    html+="<td colspan=2>身份证号码有效</td>";
					    if(mgUserBasic.userIdcardValid == 1){
						    html+="<td colspan=3>是</td>";
					    }else{
						    html+="<td colspan=3>否</td>";
					    }
						html+="</tr>";
					    html+="<tr>";
					    html+="<td colspan=2>身份证归属地</td>";
					    html+="<td colspan=3>"+mgUserBasic.userProvince+"/"+mgUserBasic.userCity+"/"+mgUserBasic.userRegion+"</td>";
						html+="</tr>";
					    html+="<tr>";
					    html+="<td colspan=2>手机号码归属地</td>";
					    html+="<td colspan=3>"+mgUserBasic.userPhoneProvince+"/"+mgUserBasic.userPhoneCity+"</td>";
						html+="</tr>";
					    html+="<tr>";
					    html+="<td colspan=2>手机所属运营商</td>";
					    html+="<td colspan=3>"+mgUserBasic.userPhoneOperator+"</td>";
						html+="</tr>";
					    html+="<tr>";
					    html+="<td colspan=2>身份证号码</td>";
						html+="<td colspan=3>"+mgUserBasic.userIdcard+"</td>";
						html+="</tr>";
					    html+="<tr>";
					    html+="<td colspan=2>手机号码</td>";
						html+="<td colspan=3>"+mgUserBasic.userPhone+"</td>";
						html+="</tr>";
					    html+="<tr>";
					    html+="<td colspan=2>年龄</td>";
					    html+="<td colspan=3>"+mgUserBasic.userAge+"</td>";
						html+="</tr>";
					    html+="<tr>";
					    html+="<td colspan=2>性别</td>";
						html+="<td colspan=3>"+mgUserBasic.userGender+"</td>";
						html+="</tr>";
						
					    html+="<tr>";
					    html+="<td colspan=6>&nbsp;</td>";
						html+="</tr>";
						
						var len = mgRegisterOrgsDetails.length;
						html+="<tr>";
						html+="<td rowspan="+(len+2)+">注册信息</td> ";
						html+="<td  colspan=2>注册App数量</td>";
						html+="<td colspan=3 >"+mgRegisterOrgs.registerCnt+"</td>";
						html+="</tr>";
						html+="<tr>";
						html+="<td rowspan="+(len+1)+">注册App统计</td>";
						html+="<td colspan=2>App类型</td>"; 
						html+="<td colspan=3>注册数量</td>"; 
						html+="</tr>";

						for(var i=0;i<mgRegisterOrgsDetails.length;i++){
							var details = mgRegisterOrgsDetails[i];
							html+="<tr>"; 
							html+="<td colspan=2>"+details.registerOrgsType+"</td>"; 
							html+="<td colspan=3>"+details.registerCnt+"</td>"; 
							html+="</tr>";
						}

					    html+="<tr>";
					    html+="<td colspan=6>&nbsp;</td>";
						html+="</tr>";
						
						len = mgUserBlacklistDetails.length;
						html+="<tr>";
						html+="<td rowspan="+(len+12)+">黑名单信息</td>";
						html+="<td colspan=2 >黑中介分数</td> ";
						if(mgUserBlacklist.phoneGrayScore >10){
							html+="<td colspan=3>"+mgUserBlacklist.phoneGrayScore+" (分数区间为0-100，10分以下为高危人群）</td>";
						}else{
							html+="<td colspan=3 style=\"color:red\">"+mgUserBlacklist.phoneGrayScore+" (分数区间为0-100，10分以下为高危人群）</td>";
						}
						html+="</tr>";
						html+="<tr>";
						html+="<td colspan=2 >直接联系人在黑名单数量</td> ";
						html+="<td colspan=3 >"+mgUserBlacklist.contactsClass1BlacklistCnt+" （直接联系人：和被查询号码有通话记录）</td>";
						html+="</tr>";
						html+="<tr>";
						html+="<td colspan=2 >直接联系人总数</td> ";
						html+="<td colspan=3 >"+mgUserBlacklist.contactsClass1Cnt+"（直接联系人：和被查询号码有通话记录）</td>";
						html+="</tr>";
						html+="<tr>";
						html+="<td  colspan=2>间接联系人在黑名单数量</td>";
						html+="<td colspan=3  >"+mgUserBlacklist.contactsClass2BlacklistCnt+"（间接联系人：和被查询号码的直接联系人有通话记录）</td>";
						html+="</tr>";
						html+="<tr>";
						html+="<td colspan=2 >引起黑名单的直接联系人数量</td>";
						html+="<td colspan=3  >"+mgUserBlacklist.contactsRouterCnt+"（直接联系人有和黑名单用户的通话记录的号码数量）</td>";
						html+="</tr>";
						html+="<tr>";
						html+="<td colspan=2 >引起黑名单的直接联系人占比</td>";
						html+="<td  colspan=3 >"+getPerFormat(mgUserBlacklist.contactsRouterRatio)+"（直接联系人有和黑名单用户的通讯记录的号码数量在直接联系人数量中的百分比）</td>";
						html+="</tr>";
						html+="<tr>";
						html+="<td colspan=2 >被标记的黑名单分类</td>";
						var blacklistCategoryDetails = "";
						for(var i=0; i<mgBlacklistCategoryDetails.length; i++){
							blacklistCategoryDetails += mgBlacklistCategoryDetails[i];
						}
						html+="<td colspan=3  >"+blacklistCategoryDetails+"</td>";
						html+="</tr>";
						html+="<tr>";
						html+="<td colspan=2 >手机和姓名是否在黑名单</td>";
						if(mgUserBlacklist.blacklistNameWithPhone == 1){
							html+="<td colspan=3  style=\"color:red\">是</td>";
						}else{
							html+="<td colspan=3  >否</td>";
						}
						html+="</tr>";
						html+="<tr>";
						html+="<td colspan=2 >身份证姓名黑名单更新时间</td> ";
						html+="<td colspan=3 >"+mgUserBlacklist.nameWithIdcardUpdateTime+"</td>";
						html+="</tr>";
						html+="<tr>";
						html+="<td colspan=2 >身份证和姓名是否在黑名单</td> ";
						if(mgUserBlacklist.blacklistNameWithIdcard == 1){
							html+="<td colspan=3  style=\"color:red\">是</td>";
						}else{
							html+="<td colspan=3  >否</td>";
						}
						html+="</tr>";
						html+="<tr>";
						html+="<td colspan=2 >姓名和电话黑名单更新信息</td>";
						html+="<td  colspan=3>"+mgUserBlacklist.nameWithPhoneUpdateTime+"</td>";
						html+="</tr>";
						
						html+="<tr>"; 
						html+="<td rowspan="+(len+1)+">黑名单详细信息</td>";
						html+="<td colspan=2>名称</td>";
						html+="<td colspan=2>内容</td>";
						html+="</tr>";

						for(var i=0;i<mgRegisterOrgsDetails.length;i++){
							var details = mgRegisterOrgsDetails[i];
							html+="<tr>"; 
							html+="<td colspan=2>"+details.registerOrgsType+"</td>"; 
							html+="<td colspan=3>"+details.registerCnt+"</td>"; 
							html+="</tr>";
						}

					    html+="<tr>";
					    html+="<td colspan=6>&nbsp;</td>";
						html+="</tr>";
						
						len = mOgUserSearchedHistoryByOrgs.length;
						html+="<tr>";
						html+="<td colspan=2>查询统计信息</td>";
						html+="<td colspan=2 >机构查询统计</td>";
						html+="<td colspan=3>"+mgUserBasic.searchedOrgCnt+"（被多少机构查询过—已去重）</td>";
						html+="</tr>";
						html+="<tr>";
						html+="<td rowspan="+(len+2)+" >机构查询历史</td>";
						html+="</tr>";
						html+="<tr>";
						html+="<td colspan=2>查询日期</td>";
						html+="<td colspan=2>机构类型</td>";
						html+="<td colspan=2>是否是本机构查询</td>";
						html+="</tr>";
						for(var i=0;i<mOgUserSearchedHistoryByOrgs.length;i++){
							var details = mOgUserSearchedHistoryByOrgs[i];
							html+="<tr>";
							html+="<td colspan=2>"+getDateStrFormat(details.searchedDate.substring(0,10))+"</td>";
							html+="<td colspan=2>"+details.orgsName+"</td>";
							if(details.orgSelf == 1){
								html+="<td  colspan=2>是</td>";
							}else{
								html+="<td  colspan=2>否</td>";
							}
							html+="</tr>";
						}

					    html+="<tr>";
					    html+="<td colspan=6>&nbsp;</td>";
						html+="</tr>";
						
						var s1 = mgPhoneWithOtherNames.length;
						var s2 = mgPhoneWithOtherIdcards.length;
						var s3 = mgPhoneAppliedInOrgs.length;
						len = s1 + s2 + s3;
						
						html+="<tr>";
						html+="<td rowspan="+(len+3)+">手机存疑</td> ";
						html+="<td rowspan="+(s1+1)+" >使用过此手机的其他姓名</td> ";
						html+="<td colspan=2>最后使用时间</td>";
						html+="<td colspan=2>姓名</td>";
						html+="</tr>";
						
						for(var i=0;i<mgPhoneWithOtherNames.length;i++){
							var details = mgPhoneWithOtherNames[i];
							html+="<tr>";
							html+="<td colspan=2>"+getDateStrFormat(details.suspUpdt)+"</td>";
							html+="<td colspan=2>"+details.suspName+"</td>";
							html+="</tr>";
						}
						
						html+="<tr>";
						html+="<td rowspan="+(s2+1)+" >使用过此手机的其他身份证</td> ";
						html+="<td colspan=2>最后使用时间</td>";
						html+="<td colspan=2>身份证号</td>";
						html+="</tr>";
						
						for(var i=0;i<mgPhoneWithOtherIdcards.length;i++){
							var details = mgPhoneWithOtherIdcards[i];
							html+="<tr>";
							html+="<td colspan=2>"+getDateStrFormat(details.suspUpdt)+"</td>";
							html+="<td colspan=2>"+details.suspIdcard+"</td>";
							html+="</tr>";
						}
						
						html+="<tr>";
						html+="<td rowspan="+(s3+1)+" >提供数据的机构类型</td>"; 
						html+="<td colspan=2 >最后使用时间</td>";
						html+="<td colspan=2>机构类型</td>";
						html+="</tr>";
						
						for(var i=0;i<mgPhoneAppliedInOrgs.length;i++){
							var details = mgPhoneAppliedInOrgs[i];
							html+="<tr>";
							html+="<td colspan=2>"+getDateStrFormat(details.suspUpdt)+"</td>";
							html+="<td colspan=2>"+details.orgsName+"</td>";
							html+="</tr>";
						}
						
					    html+="<tr>";
					    html+="<td colspan=6>&nbsp;</td>";
						html+="</tr>";
						
						var p1 = mgIdcardWithOtherNames.length;
						var p2 = mgIdcardWithOtherPhones.length;
						var p3 = mgIdcardAppliedInOrgs.length;
						len = p1 + p2 + p3
						
						html+="<tr>";
						html+="<td rowspan="+(len+3)+">身份证存疑</td> ";
						html+="<td rowspan="+(p1+1)+" >使用过此身份证的其他姓名</td> ";
						html+="<td colspan=2>最后使用时间</td>";
						html+="<td colspan=2>用户名称</td>";
						html+="</tr>";
						
						for(var i=0;i<mgIdcardWithOtherNames.length;i++){
							var details = mgIdcardWithOtherNames[i];
							html+="<tr>";
							html+="<td colspan=2>"+getDateStrFormat(details.suspUpdt)+"</td>";
							html+="<td colspan=2>"+details.suspName+"</td>";
							html+="</tr>";
						}
						
						html+="<tr>";
						html+="<td rowspan="+(p2+1)+" >使用过此身份证的其他手机</td> ";
						html+="<td>最后使用时间</td>";
						html+="<td >手机号码</td>";
						html+="<td >运营商名称</td>";
						html+="<td >归属地</td>";
						html+="</tr>";
						
						for(var i=0;i<mgIdcardWithOtherPhones.length;i++){
							var details = mgIdcardWithOtherPhones[i];
							html+="<tr>";
							html+="<td colspan=1>"+getDateStrFormat(details.suspUpdt)+"</td>";
							html+="<td colspan=1>"+details.suspPhone+"</td>";
							html+="<td colspan=1>"+details.suspPhoneOperator+"</td>";
							html+="<td colspan=1>"+details.suspPhoneProvince+"/"+details.suspPhoneCity+"</td>";
							html+="</tr>";
						}
						
						html+="<tr>";
						html+="<td rowspan="+(p3+1)+" >提供数据的机构类型</td> ";
						html+="<td colspan=2>最后使用时间</td>";
						html+="<td colspan=2>机构类型</td>";
						html+="</tr>";
						
						for(var i=0;i<mgIdcardAppliedInOrgs.length;i++){
							var details = mgIdcardAppliedInOrgs[i];
							html+="<tr>";
							html+="<td colspan=2>"+getDateStrFormat(details.suspUpdt)+"</td>";
							html+="<td colspan=2>"+details.orgsName+"</td>";
							html+="</tr>";
						}
						
						$("#report_content").html(html);
					}
				},
				error : function() {
					alert("获取用户蜜罐报告失败,请尝试重新获取！");
				}
			});
}
/***************************************************************************************************************************************************************/
function showAliyun(){
	  var applicationId = getQueryString("applicationId");//获取页面传过来的申请单编号  
	  var platformCustomerId = getQueryString("id");//获取页面传过来的平台用户编号
	  if(applicationId == undefined){
		  applicationId='00';
	  }
	  if(platformCustomerId == undefined){
		  platformCustomerId='00';
	  }
	 // applicationId='cf10249944c6489b898757b1fc38d637';
	  $.ajax({
			url : getCtxPath() + "/backend/queryAliyunDataInfo/getInfo.do?v=" + new Date().getTime(),
			method : "post",
			dataType : "json",
			data : { 
				taskType : '05001',//聚信力蜜罐
				applicationId : applicationId,
				platformId:platformCustomerId
			},
			success : function(data) {
				if(data.code == 200 ){
					if(data.data == null ) return;
					if(data.data.info == null ) return;	
					var tempJson=eval('('+data.data.info+')');
					console.log(tempJson);
					if(tempJson.message == '获取蜜罐查询成功'){
						
						var msg=tempJson.data;
						
						var user_basic=msg.user_basic;//用户基本信息
						var userBlacklist=msg.user_blacklist;//黑名单信息
						var userGray=msg.user_gray;//用户灰度分数信息
						var userIdcardSuspicion=msg.user_idcard_suspicion;//身份证号码存疑
						var userPhoneSuspicion=msg.user_phone_suspicion;//手机号码存疑
						var userRegisterOrgs=msg.user_register_orgs;//用户注册信息情况
						var userSearchedHistoryByOrgs=msg.user_searched_history_by_orgs;//用户被机构查询历史
						var userSearchedStatistic=msg.user_searched_statistic;//被机构查询数量统计
						
						$("#user_useId").html(msg.user_grid_id);
						var html = "";
					    html+="<tr>";
						html+="<td  rowspan=10>基本信息</td>";
					    html+="</tr>";
					    html+="<tr>";
					    html+="<td colspan=2>姓名</td>";
					    html+="<td colspan=3>"+user_basic.user_name+"</td>";
						html+="</tr>";
					    html+="<tr>";
					    html+="<td colspan=2>身份证号码有效</td>";
					    if(user_basic.user_idcard_valid == true){
						    html+="<td colspan=3>是</td>";
					    }else{
						    html+="<td colspan=3>否</td>";
					    }
						html+="</tr>";
					    html+="<tr>";
					    html+="<td colspan=2>身份证归属地</td>";
					    html+="<td colspan=3>"+user_basic.user_province+"/"+user_basic.user_city+"/"+user_basic.user_region+"</td>";
						html+="</tr>";
					    html+="<tr>";
					    html+="<td colspan=2>手机号码归属地</td>";
					    html+="<td colspan=3>"+user_basic.user_phone_province+"/"+user_basic.user_phone_city+"</td>";
						html+="</tr>";
					    html+="<tr>";
					    html+="<td colspan=2>手机所属运营商</td>";
					    html+="<td colspan=3>"+user_basic.user_phone_operator+"</td>";
						html+="</tr>";
					    html+="<tr>";
					    html+="<td colspan=2>身份证号码</td>";
						html+="<td colspan=3>"+user_basic.user_idcard+"</td>";
						html+="</tr>";
					    html+="<tr>";
					    html+="<td colspan=2>手机号码</td>";
						html+="<td colspan=3>"+user_basic.user_phone+"</td>";
						html+="</tr>";
					    html+="<tr>";
					    html+="<td colspan=2>年龄</td>";
					    html+="<td colspan=3>"+user_basic.user_age+"</td>";
						html+="</tr>";
					    html+="<tr>";
					    html+="<td colspan=2>性别</td>";
						html+="<td colspan=3>"+user_basic.user_gender+"</td>";
						html+="</tr>";
						
					    html+="<tr>";
					    html+="<td colspan=6>&nbsp;</td>";
						html+="</tr>";
						var len = userRegisterOrgs.register_cnt;
						if(userRegisterOrgs.register_cnt == null || userRegisterOrgs.register_cnt == undefined || isNaN(userRegisterOrgs.register_cnt)){
							userRegisterOrgs.register_cnt="0";
							len=0;
						}
						html+="<tr>";
						html+="<td rowspan="+(len+2)+">注册信息</td> ";
						html+="<td  colspan=2>注册App数量</td>";
						html+="<td colspan=3 >"+userRegisterOrgs.register_cnt+"</td>";
						html+="</tr>";
						html+="<tr>";
						html+="<td rowspan="+(len+1)+">注册App统计</td>";
						html+="<td colspan=2>App类型</td>"; 
						html+="<td colspan=3>注册数量</td>"; 
						html+="</tr>";
						var mgRegisterOrgsDetails=userRegisterOrgs.register_orgs_statistics;
						for(var i=0;i<mgRegisterOrgsDetails.length;i++){
							var details = mgRegisterOrgsDetails[i];
							html+="<tr>"; 
							html+="<td colspan=2>"+details.label+"</td>"; 
							html+="<td colspan=3>"+details.count+"</td>"; 
							html+="</tr>";
						}

					    html+="<tr>";
					    html+="<td colspan=6>&nbsp;</td>";
						html+="</tr>";
						
						
						var blacklistDetails=userBlacklist.blacklist_details;
						len = blacklistDetails.length;
						html+="<tr>";
						html+="<td rowspan="+(len+12)+">黑名单信息</td>";
						html+="<td colspan=2 >黑中介分数</td> ";
						if(userGray.phone_gray_score >10){
							html+="<td colspan=3>"+userGray.phone_gray_score+" (分数区间为0-100，10分以下为高危人群）</td>";
						}else{
							html+="<td colspan=3 style=\"color:red\">"+userGray.phone_gray_score+" (分数区间为0-100，10分以下为高危人群）</td>";
						}
						html+="</tr>";
						html+="<tr>";
						html+="<td colspan=2 >直接联系人在黑名单数量</td> ";
						html+="<td colspan=3 >"+userGray.contacts_class1_blacklist_cnt+" （直接联系人：和被查询号码有通话记录）</td>";
						html+="</tr>";
						html+="<tr>";
						html+="<td colspan=2 >直接联系人总数</td> ";
						html+="<td colspan=3 >"+userGray.contacts_class1_cnt+"（直接联系人：和被查询号码有通话记录）</td>";
						html+="</tr>";
						html+="<tr>";
						html+="<td  colspan=2>间接联系人在黑名单数量</td>";
						html+="<td colspan=3  >"+userGray.contacts_class2_blacklist_cnt+"（间接联系人：和被查询号码的直接联系人有通话记录）</td>";
						html+="</tr>";
						html+="<tr>";
						html+="<td colspan=2 >引起黑名单的直接联系人数量</td>";
						html+="<td colspan=3  >"+userGray.contacts_router_cnt+"（直接联系人有和黑名单用户的通话记录的号码数量）</td>";
						html+="</tr>";
						html+="<tr>";
						html+="<td colspan=2 >引起黑名单的直接联系人占比</td>";
						html+="<td  colspan=3 >"+getPerFormat(userGray.contacts_router_ratio)+"（直接联系人有和黑名单用户的通讯记录的号码数量在直接联系人数量中的百分比）</td>";
						html+="</tr>";
						html+="<tr>";
						html+="<td colspan=2 >被标记的黑名单分类</td>";
					
						html+="<td colspan=3  >"+userBlacklist.blacklist_category.toString()+"</td>";
						html+="</tr>";
						html+="<tr>";
						html+="<td colspan=2 >手机和姓名是否在黑名单</td>";
						if(userBlacklist.blacklist_name_with_phone == true){
							html+="<td colspan=3  style=\"color:red\">是</td>";
						}else{
							html+="<td colspan=3  >否</td>";
						}
						html+="</tr>";
						html+="<tr>";
						html+="<td colspan=2 >身份证姓名黑名单更新时间</td> ";
						html+="<td colspan=3 >"+userBlacklist.blacklist_update_time_name_idcard+"</td>";
						html+="</tr>";
						html+="<tr>";
						html+="<td colspan=2 >身份证和姓名是否在黑名单</td> ";
						if(userBlacklist.blacklist_update_time_name_idcard == true){
							html+="<td colspan=3  style=\"color:red\">是</td>";
						}else{
							html+="<td colspan=3  >否</td>";
						}
						html+="</tr>";
						html+="<tr>";
						html+="<td colspan=2 >姓名和电话黑名单更新信息</td>";
						html+="<td  colspan=3>"+userBlacklist.blacklist_update_time_name_phone+"</td>";
						html+="</tr>";
						
						html+="<tr>"; 
						html+="<td rowspan="+(len+1)+">黑名单详细信息</td>";
						html+="<td colspan=2>名称</td>";
						html+="<td colspan=2>内容</td>";
						html+="</tr>";

						for(var i=0;i<blacklistDetails.length;i++){
							var details = blacklistDetails[i];
							html+="<tr>"; 
							html+="<td colspan=2>"+details.details_key+"</td>"; 
							html+="<td colspan=3>"+details.details_value+"</td>"; 
							html+="</tr>";
						}

					    html+="<tr>";
					    html+="<td colspan=6>&nbsp;</td>";
						html+="</tr>";
						
						len = userSearchedHistoryByOrgs.length;
						html+="<tr>";
						html+="<td colspan=2>查询统计信息</td>";
						html+="<td colspan=2 >机构查询统计</td>";
						html+="<td colspan=3>"+userSearchedHistoryByOrgs.length+"（被多少机构查询过—已去重）</td>";
						html+="</tr>";
						html+="<tr>";
						html+="<td rowspan="+(len+2)+" >机构查询历史</td>";
						html+="</tr>";
						html+="<tr>";
						html+="<td colspan=2>查询日期</td>";
						html+="<td colspan=2>机构类型</td>";
						html+="<td colspan=2>是否是本机构查询</td>";
						html+="</tr>";
						for(var i=0;i<userSearchedHistoryByOrgs.length;i++){
							var details = userSearchedHistoryByOrgs[i];
							html+="<tr>";
							html+="<td colspan=2>"+getDateStrFormat(details.searched_date.substring(0,10))+"</td>";
							html+="<td colspan=2>"+details.searched_org+"</td>";
							if(details.org_self == true){
								html+="<td  colspan=2>是</td>";
							}else{
								html+="<td  colspan=2>否</td>";
							}
							html+="</tr>";
						}

					    html+="<tr>";
					    html+="<td colspan=6>&nbsp;</td>";
						html+="</tr>";
						
						var s1 = userPhoneSuspicion.phone_applied_in_orgs.length;
						var s2 = userPhoneSuspicion.phone_with_other_idcards.length;
						var s3 = userPhoneSuspicion.phone_with_other_names.length;
						len = s1 + s2 + s3;
						html+="<tr>";
						html+="<td rowspan="+(len+3)+">手机存疑</td> ";
						html+="<td rowspan="+(s3+1)+" >使用过此手机的其他姓名</td> ";
						html+="<td colspan=2>最后使用时间</td>";
						html+="<td colspan=2>姓名</td>";
						html+="</tr>";
						var paig=userPhoneSuspicion.phone_with_other_names;
						for(var i=0;i<paig.length;i++){
							var details = paig[i];
							html+="<tr>";
							html+="<td colspan=2>"+getDateStrFormat(details.susp_updt)+"</td>";
							html+="<td colspan=2>"+details.susp_name+"</td>";
							html+="</tr>";
						}
						
						html+="<tr>";
						html+="<td rowspan="+(s2+1)+" >使用过此手机的其他身份证</td> ";
						html+="<td colspan=2>最后使用时间</td>";
						html+="<td colspan=2>身份证号</td>";
						html+="</tr>";
						var pwoi=userPhoneSuspicion.phone_with_other_idcards;
						for(var i=0;i<pwoi.length;i++){
							var details = pwoi[i];
							html+="<tr>";
							html+="<td colspan=2>"+getDateStrFormat(details.susp_updt)+"</td>";
							html+="<td colspan=2>"+details.susp_idcard+"</td>";
							html+="</tr>";
						}
						
						var  paio=userPhoneSuspicion.phone_applied_in_orgs;
						html+="<tr>";
						html+="<td rowspan="+(s1+1)+" >提供数据的机构类型</td>"; 
						html+="<td colspan=2 >最后使用时间</td>";
						html+="<td colspan=2>机构类型</td>";
						html+="</tr>";
						
						for(var i=0;i<paio.length;i++){
							var details = paio[i];
							html+="<tr>";
							html+="<td colspan=2>"+getDateStrFormat(details.susp_updt)+"</td>";
							html+="<td colspan=2>"+details.susp_org_type+"</td>";
							html+="</tr>";
						}
						
					    html+="<tr>";
					    html+="<td colspan=6>&nbsp;</td>";
						html+="</tr>";
						
						var p2 = userIdcardSuspicion.idcard_with_other_phones.length;
						var p1 = userIdcardSuspicion.idcard_with_other_names.length;
						var p3 = userIdcardSuspicion.idcard_applied_in_orgs.length;
						len = p1 + p2 + p3;
						html+="<tr>";
						html+="<td rowspan="+(len+3)+">身份证存疑</td> ";
						html+="<td rowspan="+(p1+1)+" >使用过此身份证的其他姓名</td> ";
						html+="<td colspan=2>最后使用时间</td>";
						html+="<td colspan=2>用户名称</td>";
						html+="</tr>";
						var iwon=userIdcardSuspicion.idcard_with_other_names;
						for(var i=0;i<iwon.length;i++){
							var details = iwon[i];
							html+="<tr>";
							html+="<td colspan=2>"+getDateStrFormat(details.susp_updt)+"</td>";
							html+="<td colspan=2>"+details.susp_name+"</td>";
							html+="</tr>";
						}
						
						html+="<tr>";
						html+="<td rowspan="+(p2+1)+" >使用过此身份证的其他手机</td> ";
						html+="<td>最后使用时间</td>";
						html+="<td >手机号码</td>";
						html+="<td >运营商名称</td>";
						html+="<td >归属地</td>";
						html+="</tr>";
						var iwop=userIdcardSuspicion.idcard_with_other_phones;
						for(var i=0;i<iwop.length;i++){
							var details = iwop[i];
							html+="<tr>";
							html+="<td colspan=1>"+getDateStrFormat(details.susp_updt)+"</td>";
							html+="<td colspan=1>"+details.susp_phone+"</td>";
							html+="<td colspan=1>"+details.susp_phone_operator+"</td>";
							html+="<td colspan=1>"+details.susp_phone_province+"/"+details.susp_phone_city+"</td>";
							html+="</tr>";
						}
						
						html+="<tr>";
						html+="<td rowspan="+(p3+1)+" >提供数据的机构类型</td> ";
						html+="<td colspan=2>最后使用时间</td>";
						html+="<td colspan=2>机构类型</td>";
						html+="</tr>";
						var iaio=userIdcardSuspicion.idcard_applied_in_orgs;
						for(var i=0;i<iaio.length;i++){
							var details = iaio[i];
							html+="<tr>";
							html+="<td colspan=2>"+getDateStrFormat(details.susp_updt)+"</td>";
							html+="<td colspan=2>"+details.susp_org_type+"</td>";
							html+="</tr>";
						}
						
						$("#report_content").html(html);
					}
				}
			},
			error : function() {
				layer.msg("获取阿里云数据信息失败,请尝试重新获取！");
			}
		});
}