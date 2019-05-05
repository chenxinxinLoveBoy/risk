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

function cleanData(){
	$("#pname").html("");
	$("#pcertNo").html("");
	$("#pmobile").html("");
	$("#page").html("");
	$("#pbirthAddress").html("");
	$("#pconstellation").html("");
	$("#pmobileBelongTo").html("");
	$("#pmobileMnoType").html("");
	$("#pbar").html("");
	$("#apartnerCount").html("");
	$("#aidcCount").html("");
	$("#aphoneCount").html("");
	$("#astarnetCount").html("");
	$("#webDataSources").empty();
	$("#mnoPeriodUsedInfosNew").empty();
	$("#goOutDatas").empty();
	$("#highRiskLists").empty();
	$("#emergencyContacts").empty();
	$("#crossvalidation").empty();
}
function getMongoData(){
	cleanData();
		$.ajax({
			url : getCtxPath() + "/backend/bqsReport/getbqsReportByApplicationId.do",
			method : "post",
			dataType : "json",
			data : {
				applicationId : applicationId,
				platformId:platformId
			},
			success : function(data) {
				var obj=data.list.data;
				if(obj!=null){
					//保存个人信息
					$("#pname").html(obj.petitioner.name);
					$("#pcertNo").html(obj.petitioner.certNo);
					$("#pmobile").html(obj.petitioner.mobile);
					$("#page").html(obj.petitioner.age);
					$("#pbirthAddress").html(obj.petitioner.birthAddress);
					$("#pconstellation").html(obj.petitioner.constellation);
					$("#pmobileBelongTo").html(obj.petitioner.mobileBelongTo);
					$("#pmobileMnoType").html(obj.petitioner.mobileMnoType);
					$("#pbar").html(obj.petitioner.bar);
					//数据来源
					var objWebDataSource=obj.webDataSources;
					showWebDataSources(objWebDataSource);
					//分时间段统计列表
					var objBqsRepMnoPeriodUsedNews=obj.mnoPeriodUsedInfosNew;
					mnoPeriodUsedInfosNew(objBqsRepMnoPeriodUsedNews);
					//反欺诈云分析
					var objBqsAntiFraudCloud=obj.bqsAntiFraudCloud;
					if(objBqsAntiFraudCloud!=null){
						$("#apartnerCount").html(objBqsAntiFraudCloud.partnerCount);
						$("#aidcCount").html(objBqsAntiFraudCloud.idcCount);
						$("#aphoneCount").html(objBqsAntiFraudCloud.phoneCount);
						$("#astarnetCount").html(objBqsAntiFraudCloud.starnetCount);
					}
					///出行轨迹
					var objBqsRepGoOuts=obj.goOutDatas;
					bqsRepGoOuts(objBqsRepGoOuts);
					//高风险名单
					 var objHighRiskLists=obj.bqsHighRiskList;
					 if(objHighRiskLists!=null){
						 var highRisk=objHighRiskLists.highRiskLists;
						 if(highRisk!=null){
							 highRiskLists(highRisk);
						 }
					
					 }
					//常用联系人，紧急联系人
					var objEmergencyContacts=obj.emergencyContacts;
					if(objEmergencyContacts!=null){
					emergencyContacts(objEmergencyContacts);	
					}
				}
			},
			error : function() {
				alert("获取资信云报告失败,请尝试重新获取！");
			}
		});
	}

function getMysqlData(){
	cleanData();
	$.ajax({
		url : getCtxPath() + "/backend/bqsReport/getbqsReportMysqlByApplicationId.do",
		method : "post",
		dataType : "json",
		data : {
			applicationId : applicationId,
			platformId:platformId
		},
		success : function(data) {
			var obj=data.data.bqsRepPetitionerVoObject;
			if(obj!=null){
				//保存个人信息
				$("#pname").html(obj.name);
				$("#pcertNo").html(obj.certNo);
				$("#pmobile").html(obj.mobile);
				$("#page").html(obj.age);
				$("#pbirthAddress").html(obj.birthAddress);
				$("#pconstellation").html(obj.constellation);
				$("#pmobileBelongTo").html(obj.mobileBelongTo);
				$("#pmobileMnoType").html(obj.mobileMnoType);
				$("#pbar").html(obj.bar);
				//数据来源
				var objWebDataSource=obj.bqsRepDataSources;
				showWebDataSources(objWebDataSource);
				//分时间段统计列表
				var objBqsRepMnoPeriodUsedNews=obj.bqsRepMnoPeriodUsedNews;
				mnoPeriodUsedInfosNew(objBqsRepMnoPeriodUsedNews);
				//反欺诈云分析
				if(obj.bqsRepAntiFraudCloud!=null){
					var bqsAntiFObj=obj.bqsRepAntiFraudCloud;
					$("#apartnerCount").html(bqsAntiFObj.partnerCount);
					$("#aidcCount").html(bqsAntiFObj.idcCount);
					$("#aphoneCount").html(bqsAntiFObj.phoneCount);
					$("#astarnetCount").html(bqsAntiFObj.starnetCount);
				}
				//出行轨迹
				var objBqsRepGoOuts=obj.bqsRepGoOuts;
				bqsRepGoOuts(objBqsRepGoOuts);
				//高风险名单
				var objHighRiskLists=obj.bqsRepHighRisks;
				highRiskLists(objHighRiskLists);
				//紧急联系人，常用联系人
				var objEmergencyContacts=obj.bqsRepContactsInfos;
				emergencyContacts(objEmergencyContacts);
				var objcrossvalidation=obj.bqsRepCrossValidations;
				crossvalidation(objcrossvalidation);
				
			}
		},
		error : function() {
			alert("获取资信云报告失败,请尝试重新获取！");
		}
	});
}
//查看用户行为
function crossvalidation(objcrossvalidation){
	var html='';
	$.each(objcrossvalidation, function(k, v){
		html+='<tr>'
		html+='<td>'+v.inspectionItems+'</td>';
		html+='<td>'+v.result+'</td>';
		html+='<td>'+v.evidence+'</td>';
		html+='</tr>';
		
	});
	$("#crossvalidation").html(html);
}
	//查看紧急联系人，常用联系人
	function emergencyContacts(objEmergencyContacts){
		var html='';
		$.each(objEmergencyContacts, function(k, v){
			html+='<tr>'
			html+='<td>'+v.name+'</td>';
			if(v.relation==null||v.relation==""){
				html+='<td>常用</td>';
			}else{
				html+='<td>'+v.relation+'</td>';
			}
			html+='<td>'+v.mobile+'</td>';
			html+='<td>'+v.belongTo+'</td>';
			if(v.latestConnectTime!=null){
				html+='<td>'+v.latestConnectTime+'</td>';
			}else{
				html+='<td>-</td>';
			}
			if(v.firstConnectTime!=null){
				html+='<td>'+v.firstConnectTime+'</td>';
			}else{
				html+='<td>-</td>';
			}
			html+='<td>'+v.threeDaysConnectCount+"/"+v.threeDaysConnectDuration+'</td>';
			html+='<td>'+v.sevenDaysConnectCount+"/"+v.sevenDaysConnectDuration+'</td>';
			html+='<td>'+v.thirtyDaysConnectCount+"/"+v.thirtyDaysConnectDuration+'</td>';
			html+='<td>'+v.connectCount+"/"+v.connectDuration+'</td>';
			html+='</tr>';
			
		});
		$("#emergencyContacts").html(html);
		
		
	}
	
	//查看高风险名单
	function highRiskLists(objHighRiskLists){
		var html='';
		$.each(objHighRiskLists, function(k, v){
			html+='<tr>'
			html+='<td>'+v.riskGrade+'</td>';
			html+='<td>'+v.riskIdType+'</td>';
			html+='<td>'+v.firstType+'</td>';
			html+='<td>'+v.secondType+'</td>';
			html+='</tr>';
			
		});
		$("#highRiskLists").html(html);
	}
	
	//查看出行轨迹
	function bqsRepGoOuts(objBqsRepGoOuts){
		var html='';
		$.each(objBqsRepGoOuts, function(k, v){
			html+='<tr>'
			html+='<td>'+v.departure+'</td>';
			html+='<td>'+v.destination+'</td>';
			html+='<td>'+v.beginTime+'</td>';
			html+='<td>'+v.arriveTime+'</td>';
			html+='<td>'+v.endTime+'</td>';
			html+='<td>'+v.period+'</td>';
			html+='</tr>';
			
		});
		$("#goOutDatas").html(html);
		}
	//分时间段统计列表
	function mnoPeriodUsedInfosNew(objBqsRepMnoPeriodUsedNews){
		var html='';
		$.each(objBqsRepMnoPeriodUsedNews, function(k, v){
			html+='<tr>'
			html+='<td>'+v.periodType+'</td>';
			
			html+='<td>'+v.originatingCallCount+'</td>';
			html+='<td>'+v.originatingCallTime+'</td>';
			html+='<td>'+v.terminatingCallCount+'</td>';
			html+='<td>'+v.terminatingCallTime+'</td>';
			html+='<td>'+v.msgCount+'</td>';
			html+='</tr>';
			
		});
		$("#mnoPeriodUsedInfosNew").html(html);
	}
	//保存数据来源
	function showWebDataSources(objWebDataSource){
		var html='';
		$.each(objWebDataSource, function(k, v){
			html+='<tr>'
			html+='<td>'+v.sourceType+'</td>';
			html+='<td>'+v.storeTime+'</td>';
			html+='<td>'+v.source+'</td>';
			html+='<td>'+v.sourceTime+'</td>';
			html+='<td>'+v.passRealName+'</td>';
			html+='<td>'+v.realNameInfo+'</td>';
			html+='<td>'+v.equalToPetitioner+'</td>';
			html+='</tr>';
			
		});
		$("#webDataSources").html(html);
	}
	//白骑士资信报告
	$('#bqiReport').on('click', function() {
		var partnerId = "";
		var certNo = "";
		var timeStamp = "";
		var mobile = "";
		var token = "";
		var name = "";
		var url = "";
		$.ajax({
			url : getCtxPath() + "/backend/bqsReport/bqiReport.do",
			method : "post",
			dataType : "json",
			data : {
				applicationId : applicationId,
				platformId:platformId
			},
			success : function(data) {
				var obj=data.data.bqsReportPageObject;
				if(obj!=null){
					partnerId = obj.partnerId;
					certNo = obj.certNo;
					timeStamp = obj.timeStamp;
					mobile = obj.mobile;
					token = obj.token;
					name = obj.name;
					url = obj.url;
					bqiReport();
				}
			},
			error : function() {
				alert("获取资信云报告失败,请尝试重新获取！");
			}
		});
		function bqiReport(){
			var index=layer.open({
			  type: 2,
			  title: '白骑士资信报告',// 设置false表示不显示
			  closeBtn: 1, //0：不显示关闭按钮
			  shade: 0.4, //遮罩透明度
			  area: ['1500px', '550px'],
//			  skin: 'layui-layer-rim', //加上边框
			  fixed: false, //不固定
			  maxmin: true, // 允许屏幕最小化
//			  anim: 2,
			  content: [url+'?partnerId='+partnerId+'&certNo='+certNo+'&timeStamp='+timeStamp+'&mobile='+mobile+'&token='+token+'&name='+name], //iframe的url，no代表不显示滚动条
			});
		layer.full(index);  //弹框全屏化
		}
	});
	//数据魔盒资信报告
	$('#tdReport').on('click', function() {
		var taskId = "";
		var url = "";
		$.ajax({
			url : getCtxPath() + "/backend/tdReport/tdReport.do",
			method : "post",
			dataType : "json",
			data : {
				applicationId : applicationId
			},
			success : function(data) {
				var obj=data.data.bqsReportPageObject;
				console.log(obj);
				if(obj!=null){
					url = obj.url;
					taskId = obj.taskId;
					window.open(url+taskId);
				}
			},
			error : function() {
				alert("数据魔盒资信报告失败,请尝试重新获取！");
			}
		});
	});
	
	
