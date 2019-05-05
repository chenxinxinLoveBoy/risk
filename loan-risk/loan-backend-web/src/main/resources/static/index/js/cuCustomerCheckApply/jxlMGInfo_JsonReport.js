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
	 var customerCheckApplyId = getQueryString("id");//获取页面传过来的数据测试单号
	$.ajax({
				url : getCtxPath() + "/backend/cuCustomerCheckApply/getDataReportByMongo.do?v=" + new Date().getTime(),
				method : "post",
				dataType : "json",
				data : {
					customerCheckApplyId : customerCheckApplyId,
					taskType : "05001"
				},
				success : function(data) {
					var msg=data.list.data;
 						if(msg == null ||msg==''){
 							return;
 						}
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
								html+="<td colspan=2>"+getDateStrFormat(details.susp_updt)+"</td>";
								html+="<td colspan=2>"+details.susp_phone+"</td>";
								html+="<td colspan=2>"+details.susp_phone_operator+"</td>";
								html+="<td colspan=2>"+details.susp_phone_province+"/"+details.susp_phone_city+"</td>";
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