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
				url : getCtxPath() + "/backend/mhSheBao/getReport.do",
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
						var accidentInsurance = data.accidentInsurance;//工伤保险
						var endowmentInsurance = data.endowmentInsurance;//养老保险
						var maternityInsurance = data.maternityInsurance;//生育保险
						var medicalConsumption = data.medicalConsumption;//医疗消费记录
						var medicalInsurance = data.medicalInsurance;//医疗保险
						var unemploymentInsurance = data.unemploymentInsurance;//失业保险
						var userInfo = data.userInfo[0];//个人基本信息

						//账户基本信息
						$("#name").html(userInfo.name);
						$("#certificateNumber").html(userInfo.certificateNumber);
						$("#nation").html(userInfo.nation);
						$("#idCard").html(userInfo.idCard);
						$("#gender").html(userInfo.gender);
						$("#hukouType").html(userInfo.hukouType);
						$("#timeToWork").html(userInfo.timeToWork);
						$("#companyName").html(userInfo.companyName);
						$("#homeAddress").html(userInfo.homeAddress);
						
						//工伤保险
						for(var i=0;i<accidentInsurance.length;i++){
							var str='';
							str+="<tr>";
							str+="<td>"+accidentInsurance[i].month+"</td>";
							str+="<td>"+accidentInsurance[i].monthlyCompanyIncome+"</td>";
							str+="<td>"+accidentInsurance[i].baseNumber+"</td>";
							str+="<td>"+accidentInsurance[i].monthCount+"</td>";
							str+="<td>"+accidentInsurance[i].lastPayDate+"</td>";
							str+="</tr>";
							$("#accident").append(str);
						}
						
						//养老保险
						for(var i=0;i<endowmentInsurance.length;i++){
							var str='';
							str+="<tr>";
							str+="<td>"+endowmentInsurance[i].month+"</td>";
							str+="<td>"+endowmentInsurance[i].monthlyCompanyIncome+"</td>";
							str+="<td>"+endowmentInsurance[i].monthlyPersonalIncome+"</td>";
							str+="<td>"+endowmentInsurance[i].baseNumber+"</td>";
							str+="<td>"+endowmentInsurance[i].monthCount+"</td>";
							str+="<td>"+endowmentInsurance[i].lastPayDate+"</td>";
							str+="</tr>";
							$("#endowment").append(str);
						}
						
						//生育保险
						for(var i=0;i<maternityInsurance.length;i++){
							var str='';
							str+="<tr>";
							str+="<td>"+maternityInsurance[i].month+"</td>";
							str+="<td>"+maternityInsurance[i].monthlyPersonalIncome+"</td>";
							str+="<td>"+maternityInsurance[i].baseNumber+"</td>";
							str+="<td>"+maternityInsurance[i].monthCount+"</td>";
							str+="<td>"+maternityInsurance[i].lastPayDate+"</td>";
							str+="</tr>";
							$("#maternity").append(str);
						}
						
						//医疗保险
						for(var i=0;i<medicalInsurance.length;i++){
							var str='';
							str+="<tr>";
							str+="<td>"+medicalInsurance[i].month+"</td>";
							str+="<td>"+medicalInsurance[i].monthlyCompanyIncome+"</td>";
							str+="<td>"+medicalInsurance[i].monthlyPersonalIncome+"</td>";
							str+="<td>"+medicalInsurance[i].baseNumber+"</td>";
							str+="<td>"+medicalInsurance[i].monthCount+"</td>";
							str+="<td>"+medicalInsurance[i].lastPayDate+"</td>";
							str+="</tr>";
							$("#medical").append(str);
						}
						
						//失业保险
						for(var i=0;i<unemploymentInsurance.length;i++){
							var str='';
							str+="<tr>";
							str+="<td>"+unemploymentInsurance[i].month+"</td>";
							str+="<td>"+unemploymentInsurance[i].monthlyCompanyIncome+"</td>";
							str+="<td>"+unemploymentInsurance[i].monthlyPersonalIncome+"</td>";
							str+="<td>"+unemploymentInsurance[i].baseNumber+"</td>";
							str+="<td>"+unemploymentInsurance[i].monthCount+"</td>";
							str+="<td>"+unemploymentInsurance[i].lastPayDate+"</td>";
							str+="</tr>";
							$("#unemployment").append(str);
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