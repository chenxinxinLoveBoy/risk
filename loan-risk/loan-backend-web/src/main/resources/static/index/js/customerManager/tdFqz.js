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
var platformId="";
$(function() {
	applicationId= getQueryString("applicationId");//获取页面传过来的申请单编号
	platformId=getQueryString("id");

	getMongoData();
})

function cleanTable(){
	$("#blackList").html("");
	$("#discreditCount").html("");
	$("#platformDimensionDetail").html("");
	$("#platformDetail").html("");
	$("#greyList").html("");
	$("#suspectedTeam").html("");
}

function getMongoData(){
	cleanTable();
	$.ajax({
		url : getCtxPath() + "/backend/tdFqz/getTdReportByApplicationId.do",
		method : "post",
		dataType : "json",
		data : {
			applicationId : applicationId,
			platformId:platformId
		},
		success : function(data) {
			var riskItems=data.list.data.result_desc.ANTIFRAUD.risk_items;
			if(riskItems!=null){
				$.each(riskItems, function(k, v){
					var detail=v.risk_detail[0];
					if(detail!=null){
						var riskType=detail.type;
						//黑名单
						if(riskType=="black_list"){
							blackList(detail);
						}
						//信贷逾期统计
						if(riskType=="discredit_count"){
							discreditCount(detail);
						}
						if(riskType=="platform_detail"){
							platformDetail(detail);
						}
						//关注名单规则
						if(riskType=="grey_list"){
							greyList(detail);
						}
						//风险群体规则
						if(riskType=="suspected_team"){
							suspectedTeam(detail);
						}
					}
				});
			}
		},
		error : function() {
			alert("获取同盾反欺诈报告失败,请尝试重新获取！");
		}
	});
}

function suspectedTeam(detail){
	var objSuspectTeam=detail.suspect_team_detail_list;
	if(objSuspectTeam!=null){
		var html='';
		$.each(objSuspectTeam, function(k, v){
			html+='<tr>';
			html+='<td>'+v.group_id+'</td>';
			html+='<td>'+v.dim_type+'</td>';
			html+='<td>'+v.dim_value+'</td>';
			html+='<td>'+v.node_dist+'</td>';
			html+='<td>'+v.fraud_dist+'</td>';
			html+='<td>'+v.black_rat+'</td>';
			html+='<td>'+v.grey_rat+'</td>';
			html+='<td>'+v.degree+'</td>';
			html+='<td>'+v.total_cnt+'</td>';
			html+='<td>'+v.black_cnt+'</td>';
			html+='<td>'+v.grey_cnt+'</td>';
			html+='<td>'+v.core_dst+'</td>';
			html+='<td>'+v.black_dst+'</td>';
			html+='<td>'+v.total_cnt_two+'</td>';
			html+='<td>'+v.black_cnt_one+'</td>';
			html+='<td>'+v.black_cnt_two+'</td>';
			html+='<td>'+v.fraud_dist_one+'</td>';
			html+='<td>'+v.fraud_dist_two+'</td>';
			html+='</tr>';
		});
		
		$("#suspectedTeam").append(html);
	}
}

function greyList(detail){
	var grepDetails=detail.grey_list_details;
	if(grepDetails!=null){
		var  description=detail.description;
		var html='';
		$.each(grepDetails, function(k, v){
			html+='<tr>';
			html+='<td>'+detail.hit_type_display_name+'</td>';
			html+='<td>'+v.fraud_type_display_name+'</td>';
			html+='<td>'+new Date(v.evidence_time).toLocaleString()+'</td>';
			html+='<td>'+v.risk_level+'</td>';
			html+='<td>'+v.fraud_type+'</td>';
			html+='<td>'+v.value+'</td>';
			html+='<td>'+description+'</td>';
			html+='</tr>';
		});
	}
	$("#greyList").append(html);
}

function platformDetail(detail){
	var   dimensions=detail.platform_detail_dimension;
	var   platform_details= detail.platform_detail;
	var  description=detail.description;
	if(dimensions!=null){
		platformDimensions(dimensions,description);
	}
	if(platform_details!=null){
		showPlatformDetails(platform_details,description);
	}
}
function showPlatformDetails(platform_details,description){
	var html='';
	$.each(platform_details, function(k, v){
			html+='<tr>';
			if(v.industry_display_name!=null){
			html+='<td>'+v.industry_display_name+'</td>';
			}else{
				html+='<td>-</td>';
			}
			
			if(v.count!=null){
				html+='<td>'+v.count+'</td>';
				}else{
					html+='<td>-</td>';
				}
				html+='<td>'+description+'</td>';
				
			html+='</tr>';
			
		})
		$("#platformDetail").append(html);
}
function platformDimensions(dimensions,description){
	var html='';
	$.each(dimensions, function(k, v){
			html+='<tr>';
			if(v.dimension!=null){
			html+='<td>'+v.dimension+'</td>';
			}else{
				html+='<td>-</td>';
			}
			if(v.count!=null){
				html+='<td>'+v.count+'</td>';
				}else{
					html+='<td>-</td>';
				}
				html+='<td>'+description+'</td>';
				
			html+='</tr>';
			
		})
		$("#platformDimensionDetail").append(html);
}


function discreditCount(detail){
	var overdueDetails=detail.overdue_details;
	var html='';
	if(overdueDetails!=null){
		$.each(overdueDetails, function(k, v){
			html+='<tr>';
			if(detail.description!=null){
			html+='<td>'+detail.description+'</td>';
			}else{
				html+='<td>-</td>';
			}
			if(v.overdue_time!=null){
			html+='<td>'+v.overdue_time+'</td>';
			}else{
				html+='<td>-</td>';
			}
			if(v.overdue_amount_range!=null){
			html+='<td>'+v.overdue_amount_range+'</td>';
			}else{
				html+='<td>-</td>';
			}
			if(v.overdue_day_range!=null){
			html+='<td>'+v.overdue_day_range+'</td>';
			}else{
				html+='<td>-</td>';
			}
			if(v.overdue_count!=null){
			html+='<td>'+v.overdue_count+'</td>';
			}else{
				html+='<td>-</td>';
			}
			html+='</tr>';
		});
	}
	$("#discreditCount").append(html);
}


//保存黑名单信息
function blackList(detail){
	var courtDetails=detail.court_details;
	var html='';
	if(courtDetails!=null){
		$.each(courtDetails, function(k, v){
			html+='<tr>';
			html+='<td>'+detail.hit_type_display_name+'</td>';
			html+='<td>'+v.fraud_type_display_name+'</td>';
			html+='<td>'+detail.description+'</td>';
			html+='<td>'+v.value+'</td>';
			html+='<td>'+v.fraud_type+'</td>';
			if(v.executed_name!=null){
			html+='<td>'+v.executed_name+'</td>';
			}else{
				html+='<td>-</td>';
			}
			if(v.age!=null){
			html+='<td>'+v.age+'</td>';
			}else{
				html+='<td>-</td>';
			}
			if(v.gender!=null){
			html+='<td>'+v.gender+'</td>';
			}else{
				html+='<td>-</td>';
			}
			if(v.province!=null){
			html+='<td>'+v.province+'</td>';
			}else{
				html+='<td>-</td>';
			}
			if(v.case_date!=null){
			html+='<td>'+v.case_date+'</td>';
			}else{
				html+='<td>-</td>';
			}
			if(v.execute_court!=null){
			html+='<td>'+v.execute_court+'</td>';
			}else{
				html+='<td>-</td>';
			}
			if(v.term_duty!=null){
			html+='<td>'+v.term_duty+'</td>';
			}else{
				html+='<td>-</td>';
			}
			if(v.execute_subject!=null){
			html+='<td>'+v.execute_subject+'</td>';
			}else{
				html+='<td>-</td>';
			}
			if(v.execute_subject!=null){
			html+='<td>'+v.execute_status+'</td>';
			}else{
				html+='<td>-</td>';
			}
			if(v.execute_subject!=null){
			html+='<td>'+v.evidence_court+'</td>';
			}else{
				html+='<td>-</td>';
			}
			if(v.execute_subject!=null){
			html+='<td>'+v.carry_out+'</td>';
			}else{
				html+='<td>-</td>';
			}
			if(v.execute_subject!=null){
			html+='<td>'+v.specific_circumstances+'</td>';
			}else{
				html+='<td>-</td>';
			}
			if(v.execute_subject!=null){
			html+='<td>'+v.execute_code+'</td>';
			}else{
				html+='<td>-</td>';
			}
			if(v.execute_subject!=null){
			html+='<td>'+v.case_code+'</td>';
			}else{
				html+='<td>-</td>';
			}
			if(v.execute_subject!=null){
			html+='<td>'+new Date(v.evidence_time).toLocaleString()+'</td>';
			}else{
				html+='<td>-</td>';
			}
			
			html+='</tr>';
		});
	}
	$("#blackList").append(html);
	
}