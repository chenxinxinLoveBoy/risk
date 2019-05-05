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
				url: getCtxPath() + "/backend/mongo/getMongoDisplayInfo.do?v=" + new Date().getTime(),
				method: "post",
				dataType: "json",
				data: {
					customerId : customerId,
					taskType:"06004"
				},
				success: function(msg) {
						if(msg.list===undefined)
					    {
					    	return ;
					    }
						var accidentInsurance = msg.list.data.data.task_data.accident_insurance;//工伤保险
						var endowmentInsurance = msg.list.data.data.task_data.endowment_insurance;//养老保险
						var maternityInsurance = msg.list.data.data.task_data.maternity_insurance;//生育保险
						var medicalConsumption = msg.list.data.data.task_data.medical_consumption;//医疗消费记录
						var medicalInsurance = msg.list.data.data.task_data.medical_insurance;//医疗保险
						var unemploymentInsurance = msg.list.data.data.task_data.unemployment_insurance;//失业保险
						var userInfo = msg.list.data.data.task_data.user_info;//个人基本信息
						//账户基本信息
						if(userInfo!=null)
						{
							$("#name").html(convertToNull(userInfo.name));//姓名
							$("#certificateNumber").html(convertToNull(userInfo.certificate_number));//身份证号
							$("#nation").html(convertToNull(userInfo.nation));//民族
							$("#gender").html(convertToNull(userInfo.gender));//性别
							$("#hukouType").html(convertToNull(userInfo.hukou_type));//户口性质
							$("#timeToWork").html(convertToNull(userInfo.time_to_work));//参加工作日期
							$("#companyName").html(convertToNull(userInfo.company_name));//所在单位
							$("#homeAddress").html(convertToNull(userInfo.home_address));//家庭住址
						};
						//工伤保险
						if(accidentInsurance!=null)
						{
							for(var i=0;i<accidentInsurance.length;i++){
								var str='';
								str+="<tr>";
								str+="<td>"+convertToNull(accidentInsurance[i].month)+"</td>";
								str+="<td>"+convertToNull(accidentInsurance[i].monthly_personal_income)+"</td>";
								str+="<td>"+convertToNull(accidentInsurance[i].base_number)+"</td>";
								str+="<td>"+convertToNull(accidentInsurance[i].month_count)+"</td>";
								str+="<td>"+convertToNull(accidentInsurance[i].last_pay_date)+"</td>";
								str+="</tr>";
								$("#accident").append(str);
							}
						}
						
						//养老保险
						if(endowmentInsurance!=null)
						{
							for(var i=0;i<endowmentInsurance.length;i++){
								var str='';
								str+="<tr>";
								str+="<td>"+convertToNull(endowmentInsurance[i].month)+"</td>";
								str+="<td>"+convertToNull(endowmentInsurance[i].monthly_company_income)+"</td>";
								str+="<td>"+convertToNull(endowmentInsurance[i].monthly_personal_income)+"</td>";
								str+="<td>"+convertToNull(endowmentInsurance[i].base_number)+"</td>";
								str+="<td>"+convertToNull(endowmentInsurance[i].month_count)+"</td>";
								str+="<td>"+convertToNull(endowmentInsurance[i].last_pay_date)+"</td>";
								str+="</tr>";
								$("#endowment").append(str);
							}
						}
						//生育保险
						if(maternityInsurance!=null)
						{
							for(var i=0;i<maternityInsurance.length;i++){
								var str='';
								str+="<tr>";
								str+="<td>"+convertToNull(maternityInsurance[i].month)+"</td>";
								str+="<td>"+convertToNull(maternityInsurance[i].monthly_personal_income)+"</td>";
								str+="<td>"+convertToNull(maternityInsurance[i].base_number)+"</td>";
								str+="<td>"+convertToNull(maternityInsurance[i].month_count)+"</td>";
								str+="<td>"+convertToNull(maternityInsurance[i].last_pay_date)+"</td>";
								str+="</tr>";
								$("#maternity").append(str);
							}
						}
						//医疗保险
						if(medicalInsurance!=null)
						{
							for(var i=0;i<medicalInsurance.length;i++){
								var str='';
								str+="<tr>";
								str+="<td>"+convertToNull(medicalInsurance[i].month)+"</td>";
								str+="<td>"+convertToNull(medicalInsurance[i].monthly_company_income)+"</td>";
								str+="<td>"+convertToNull(medicalInsurance[i].monthly_personal_income)+"</td>";
								str+="<td>"+convertToNull(medicalInsurance[i].base_number)+"</td>";
								str+="<td>"+convertToNull(medicalInsurance[i].month_count)+"</td>";
								str+="<td>"+convertToNull(medicalInsurance[i].last_pay_date)+"</td>";
								str+="</tr>";
								$("#medical").append(str);
							}
						}
						//失业保险
						if(unemploymentInsurance!=null)
						{
							for(var i=0;i<unemploymentInsurance.length;i++){
								var str='';
								str+="<tr>";
								str+="<td>"+convertToNull(unemploymentInsurance[i].month)+"</td>";
								str+="<td>"+convertToNull(unemploymentInsurance[i].monthly_company_income)+"</td>";
								str+="<td>"+convertToNull(unemploymentInsurance[i].monthly_personal_income)+"</td>";
								str+="<td>"+convertToNull(unemploymentInsurance[i].base_number)+"</td>";
								str+="<td>"+convertToNull(unemploymentInsurance[i].month_count)+"</td>";
								str+="<td>"+convertToNull(unemploymentInsurance[i].last_pay_date)+"</td>";
								str+="</tr>";
								$("#unemployment").append(str);
							}
						}
//						
//					}
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