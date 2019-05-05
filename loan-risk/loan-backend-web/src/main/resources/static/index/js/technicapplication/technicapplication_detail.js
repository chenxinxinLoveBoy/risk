	layui.config({
				base: '../../js/' 
			});
 
	// 回显
	$(function(){
		
		//我们强烈推荐你在代码最外层把需要用到的模块先加载
		layui.use(['layer', 'form', 'element'], function(){
			 var layer = layui.layer
			  ,form = layui.form()
			  ,element = layui.element();
			  form.render(); //刷新全部
			  
				var applicationId = getQueryString("applicationId"); 
		  		$.ajax({
					url :getCtxPath() + "/backend/technicApplication/getEntityById.do",
					method: "post",
					dataType:"json",
					data: {
						applicationId: applicationId
					},
					success: function(data){
						if(data.code == 200){
							var application = data.list.data;
							var buApplicationId = application.applicationId;
						/*	$.ajax({
								url :getCtxPath() + "/backend/technicApplication/getReportById.do",
								method: "post",
								dataType:"json",
								data: {
									buApplicationId : buApplicationId
								},
								success: function(data){
									if(data.code == 200){ 
										var type = data.list.data;
										var mun = type.length;
										 if(type != null && type != "" ){
											 for (var i = 0; i < mun; i++) {
												var obj = type[i]; 
												console.log(obj);
												//alert(obj['taskId'] +"||"+ obj.taskId); 
												var tastype = obj.taskType; 
												var typeId = obj.taskId;  
												var backup1 = obj.backup1;
												
												if(tastype != undefined && tastype != undefined){
													if(tastype == '01001' ){ 
														$("#taskIdZM").val(typeId); //芝麻信用taskID
													}if(tastype == '02001'){
														$("#taskIdZM").val(typeId);  
													}
													if(tastype == '03001'){
														$("#taskIdTD").val(typeId);//同盾taskID
														$("#backup1").val(backup1);  //盾报告编号
													}
													if(tastype == '04001' ){
														$("#taskIdJXL").val(typeId);  //聚信立taskID
													}
													if(tastype == '05001' ){
														$("#taskIdJXL").val(typeId);  //聚信立taskID
													} 	
												} 
												
											}
											 
										 }
									}
								}
							})*/
							$("#applicationId").val(application.applicationId);//申请单编号
							$("#applicationBuId").val(application.applicationBuId);//借款编号
							$("#platformId").val(application.platformId);//平台用户编号
							$("#customerId").val(application.customerId);//APP应用客户编号
							$("#appName").val(application.appName == 1 ? '中鸿贷' : '无忧钱包');//APP名称 
							$("#appSerialNumber").val(application.appSerialNumber);//APP申请编号
							$("#name").val(application.name);//用户姓名
							$("#phoneNum").val(application.phoneNum);//用户手机号
							$("#certCode").val(application.certCode);//身份证号
							$("#certType").val(application.certType);//证件类型
							$("#appraiseMoney").val(application.appraiseMoney);//授信分数
							$("#creditMoney").val(application.creditMoney);//授信额度
							$("#auditingState").val(application.auditingState);//审批状态
							$("#auditResult").val(application.auditResult);//审批结果
							$("#auditingTime").val(application.auditingTime.substring(0,19));//审核时间
							$("#auditMan").val(application.auditMan);//审批人用户编号
							$("#isStep").val(application.isStep);//步骤标识
							$("#modifyMan").val(application.modifyMan);//修改人
							$("#createMan").val(application.createMan);//创建人
							var apps =application.isPushApp; 
							$("#isPushApp").val(apps);//外部系统交互标识（0-未推送App、1-已推送APP、2：已推送大数据、3：已接收大数据消息）isHbaseSyn
							$("#isHbaseSyn").val(application.isHbaseSyn);//是否同步大数据, 0-否、1-是 
							$("#loanIp").val(application.loanIp);//借款用户公网IP
							$("#source").val(application.source);//申请来源
							$("#tdBlackBox").val(application.tdBlackBox);//同盾black_box
							$("#jxlUseToken").val(application.jxlUseToken);//聚信立用户token
							$("#reportId").val(application.reportId);//同盾reportId
							$("#openId").val(application.openId);//芝麻用户标识 
							$("#createTime").val(application.createTime.substring(0,19)); //提交审核时间
							$("#modifyTime").val(application.modifyTime.substring(0,19));//最新修改时间
							$("#banCode").val(application.banCode); //禁止项编号
							$("#remark").val(application.remark); //备注
							$("#productQuota").val(application.productQuota); //预期借款额度
							$("#days").val(application.days); //周期
							$("#banCodeTplId").val(application.banCodeTplId); //禁止项模板主键ID
							$("#fraudTplId").val(application.fraudTplId); //欺诈项模板主键ID
							$("#scoreTplId").val(application.scoreTplId); //评分项模板主键ID
							/*$("#scoreSmallId").val(application.scoreSmallId);//评分小类编号 
							$("#hlUserToken").val(application.hlUserToken);//葫芦Token 
*/							var level = application.appLevel;
							if(level == 0){
								level='新客户'; 
							}else{
								level = '老客户';
							}
							$("#appLevel").val(level);//客户标识(0:新客户 1：老客户)
							$("#appChannel").val(application.appChannel);//app渠道标识
							$("#decisionTreeId").val(application.decisionTreeId);//决策树ID 
							$("#failureTimes").val(application.failureTimes); //失败次数
							$("#distributionTime").val(application.distributionTime.substring(0,19));//模板分发时间
							$("#errorDescription").val(application.errorDescription);  //步骤处理异常描述
							$("#refundConut").val(application.refundConut);//用户已还款次数
							$("#overdueCount").val(application.overdueCount);//用户逾期次数
							$("#appVersion").val(application.appVersion);//App版本号
						}
					},
					error: function(){  
 						layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
					},
					complete: function(){
						form.render(); //刷新全部
					} 
				});	
	   });
	});
	

// 关闭
function closeIfrname(){
	var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(frameindex);
}
