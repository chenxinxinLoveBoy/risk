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

	$.ajax({
				url : getCtxPath() + "/backend/customer/getEntityById.do",
				method : "post",
				dataType : "json",
				data : {
					platformCustomerId : platformCustomerId
				},
				success : function(data) {
					if(data.data.customerInfoObject.cuPlatformCustomer!=null){
						$("#platformCustomerId").html(data.data.customerInfoObject.cuPlatformCustomer.platformCustomerId);
						$("#name").html(data.data.customerInfoObject.cuPlatformCustomer.name);
						$("#phoneNum").html(data.data.customerInfoObject.cuPlatformCustomer.phoneNum);
						var ifMarriage = data.data.customerInfoObject.cuPlatformCustomer.ifMarriage;
						if (ifMarriage == "SPINSTERHOOD") {
							$("#ifMarriage").html("未婚");
						} else if (ifMarriage == "MARRIED") {
							$("#ifMarriage").html("已婚");
						} else if (ifMarriage == "DIVORCED") {
							$("#ifMarriage").html("离异");
						} else if (ifMarriage == "WIDOWED") {
							$("#ifMarriage").html("丧偶");
						} else if (ifMarriage == "REMARRY") {
							$("#ifMarriage").html("再婚");
						} else if (ifMarriage == "REMARRY_FORMER") {
							$("#ifMarriage").html("复婚");
						}else{
							$("#ifMarriage").html("");
						}
						
						var gender = data.data.customerInfoObject.cuPlatformCustomer.gender;
						if (gender == "1") {
							$("#gender").html("男");
						} else if (gender == "2") {
							$("#gender").html("女");
						}else{
							$("#gender").html("");
						}
						
						$("#age").html(data.data.customerInfoObject.cuPlatformCustomer.age);
						
						var educationId=data.data.customerInfoObject.cuPlatformCustomer.educationId;
						if(educationId === "PRE_HIGH_SCHOOL"){
							$("#educationId").html("高中以下");
						}else if(educationId === "HIGH_SCHOOL"){
							$("#educationId").html("高中/中专");
						}else if(educationId === "JUNIOR_COLLEGE"){
							$("#educationId").html("大专");
						}else if(educationId === "UNDER_GRADUATE"){
							$("#educationId").html("本科");
						}else if(educationId === "POST_GRADUATE"){
							$("#educationId").html("研究生");
						}
						
						$("#nation").html(data.data.customerInfoObject.cuPlatformCustomer.nation);
						$("#homeAddress").html(data.data.customerInfoObject.cuPlatformCustomer.homeAddress);
						$("#issueInstitution").html(data.data.customerInfoObject.cuPlatformCustomer.issueInstitution);
						$("#expirationDate").html(data.data.customerInfoObject.cuPlatformCustomer.expirationDate);
						$("#registeredAddress").html(data.data.customerInfoObject.cuPlatformCustomer.registeredAddress);
						
						
						
						
						
						var certType = data.data.customerInfoObject.cuPlatformCustomer.certType;
						if (certType == "1") {
							$("#certType").html("身份证");
						} else if (certType == "2") {
							$("#certType").html("护照");
						} else {
							$("#certType").html("其他");
						}
						$("#certCode").html(data.data.customerInfoObject.cuPlatformCustomer.certCode);
						
//						var bankName=data.data.customerInfoObject.cuPlatformCustomer.bankName;
//						var bankCard=data.data.customerInfoObject.cuPlatformCustomer.bankCard;
//						if( bankName!=null&&bankName!=""&& bankCard!=null&&bankCard!=""){
//							$("#bankCardAndBankName").html(bankName + "("+ bankCard+")");
//						}else{
//							$("#bankCardAndBankName").html("");
//						}
					}
  					
					if( data.data.customerInfoObject.cuCustomerCompany!=null) {
						$("#companyName").html(data.data.customerInfoObject.cuCustomerCompany.companyName);
						$("#companyTel").html(data.data.customerInfoObject.cuCustomerCompany.companyTel);
					}  
 					
					if(data.data.customerInfoObject.cuIcePersonList!=null){
						var type = "";
						var typeAndTrueNameAndPhoneNum = "";
 						$.each(data.data.customerInfoObject.cuIcePersonList,
										function(k, v) {
											if (v.type == "father") {
												type = "父亲|";
											} else if (v.type == "mother") {
												type = "母亲|";
											} else if (v.type == "spouse") {
												type = "配偶|";
											} else if (v.type == "child") {
												type = "子女|";
											} else if (v.type == "other_relative") {
												type = "其他亲属|";
											} else if (v.type == "friend") {
												type = "朋友|";
											} else if (v.type == "coworker") {
												type = "同事|";
											} else if (v.type == "others") {
												type = "其他|";
											}
											typeAndTrueNameAndPhoneNum = typeAndTrueNameAndPhoneNum	+ (type + v.trueName + "|" + v.phoneNum+"   &nbsp;&nbsp&nbsp&nbsp&nbsp");
										});
						$("#typeAndTrueNameAndPhoneNum").html(typeAndTrueNameAndPhoneNum);
					}
					
					if( data.data.customerInfoObject.faceRecognitionScore!=null) {
						$("#faceUrl").attr("src",data.data.customerInfoObject.faceRecognitionScore.faceUrl);
						$("#frontUrl").attr("src",data.data.customerInfoObject.faceRecognitionScore.frontUrl);
						$("#backUrl").attr("src",data.data.customerInfoObject.faceRecognitionScore.backUrl);
//						$("#frontUrl").html(data.data.customerInfoObject.faceRecognitionScore.frontUrl);
//						$("#viewFrontUrl").attr("href",data.data.customerInfoObject.faceRecognitionScore.frontUrl);
					}  
				},
				error : function() {
					alert("获取用户信息失败,请尝试重新获取！");
				}
			});

})
