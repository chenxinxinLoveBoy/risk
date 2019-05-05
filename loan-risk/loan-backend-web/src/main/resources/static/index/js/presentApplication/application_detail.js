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
			  
				var appSerialNumber = getQueryString("appSerialNumber");
		  		$.ajax({
					url :getCtxPath() + "/backend/application/getEntityById.do",
					method: "post",
					dataType:"json",
					data: {
						appSerialNumber: appSerialNumber
					},
					success: function(data){
						if(data.code == 200){
							var application = data.list.data;
							$("input").eq(0).val(application.applicationId);//申请单编号
							$("input").eq(1).val(application.applicationBuId);//申请单业务编号
							$("input").eq(2).val(application.platformId);//平台用户编号
							$("input").eq(3).val(application.appName == 1 ? '洪澄' : '速贷');//APP名称：1-洪澄；2-速贷
							$("input").eq(4).val(application.appSerialNumber);//APP应用请求流水号 
							$("input").eq(5).val(application.name);//客户姓名
							$("input").eq(6).val(application.phoneNum);//手机号
							$("input").eq(7).val(application.certCode);//证件号码
							$("input").eq(8).val(application.certType);//证件类型（1.身份证 2.护照 3.其他）
							$("input").eq(9).val(application.appraiseMoney);//评估分数值
							$("input").eq(10).val(application.creditMoney);//授信额度值
							$("input").eq(11).val(application.auditingState);//审批状态（1-待审批、2-审批通过、3-审批未通过、4-待人工确认）
							$("textarea").eq(0).val(application.auditResult);//审批结果描述
							$("input").eq(13).val(application.auditingTime.substring(0,19));//审核时间
							$("input").eq(14).val(application.auditMan);//审批人用户编号
							$("input").eq(15).val(application.isStep);//步骤标识（1：芝麻信用评分、2：芝麻信用欺诈清单、3：同盾贷前审核、4：聚信立蜜蜂、5：聚信立蜜罐）
							$("input").eq(16).val(application.isPushApp);//是否通知App（0-否、1-是）
							$("input").eq(17).val(application.loanIp);//借款用户公网IP
							$("input").eq(18).val(application.source);//申请来源（0——Android；1——IOS）
							$("textarea").eq(1).val(application.tdBlackBox);//同盾black_box
							$("input").eq(20).val(application.jxlUseToken);//聚信立用户token
							$("input").eq(21).val(application.reportId);//同盾reportId
							$("input").eq(22).val(application.openId);//芝麻用户标识：芝麻会员在商户端的身份标识
							$("input").eq(23).val(application.createTime.substring(0,19));//创建时间
							$("input").eq(24).val(application.modifyTime.substring(0,19));//修改时间
							$("input").eq(25).val(application.banCode);//禁止项规则对应编号
							$("input").eq(26).val(application.remark);//备注
							$("input").eq(27).val(application.productQuota);//预期借款额度
							$("input").eq(28).val(application.days);//周期 
							$("input").eq(29).val(application.decisionTreeId);//决策树ID 
							$("input").eq(30).val(application.banCodeTplId);//禁止项模板主键ID 
							$("input").eq(31).val(application.fraudTplId);//欺诈项模板主键ID
							$("input").eq(32).val(application.scoreTplId);//评分项模板主键ID 
							/*$("input").eq(32).val(application.scoreSmallId);//评分小类编号 
							$("input").eq(33).val(application.hlUserToken);//葫芦Token 
*/							var level = application.appLevel;
							if(level == 0){
								level='新客户'; 
							}else{
								level = '老客户';
							}
							$("input").eq(33).val(level);//客户标识(0:新客户 1：老客户)
							$("input").eq(34).val(application.appChannel);//app渠道标识 
							$("input").eq(35).val(application.failureTimes);//失败次数
							$("input").eq(36).val(application.distributionTime.substring(0,19));//模板分发时间
							$("textarea").eq(2).val(application.errorDescription);//步骤处理异常描述
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
