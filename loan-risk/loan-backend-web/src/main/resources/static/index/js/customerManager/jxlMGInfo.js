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
				url : getCtxPath() + "/backend/miguan/getReport.do",
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
							html+="<td colspan=2>"+getDateStrFormat(details.suspUpdt)+"</td>";
							html+="<td colspan=2>"+details.suspPhone+"</td>";
							html+="<td colspan=2>"+details.suspPhoneOperator+"</td>";
							html+="<td colspan=2>"+details.suspPhoneProvince+"/"+details.suspPhoneCity+"</td>";
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

