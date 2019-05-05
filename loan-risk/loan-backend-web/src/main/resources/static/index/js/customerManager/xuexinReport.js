layui.config({
	base : '../../js/'
}); 
layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer,
			layer = layui.layer,
			form = layui.form(), element = layui.element();
		}); 
$(function() { 
	 var customerId = getQueryString("customerId");
	 console.log(customerId);  
	 var val = getQueryString("1"); 
	 $("#xx").css("color","#00ABA9");
	 if(val == 1){
		 $("#xx").css("color","#00ABA9"); 
		 $("#sb").attr("style","");
		 $("#gjjs").attr("style","");
		 $("#ds").attr("style","");
		 $("#gr").attr("style","");
		 $("#xxs").hide();
		 $("#xx").hide();
		 $("#sb").hide();
		 $("#gjjs").hide();
		 $("#ds").hide();  
		 $("#gr").hide();  
	 }
	 $("#xx").on("click", function(){  
		 $("#xx").css("color","#00ABA9"); 
		 $("#sb").attr("style","");
		 $("#gjjs").attr("style","");
		 $("#ds").attr("style","");
		 $("#gr").attr("style","");
		 $("#xxs").show();
		 $("#sbs").hide();
		 $("#gjj").hide();
		 $("#dss").hide(); 
		 $("#ges").hide();  
		 $("#bindingIdcards").prop("src",getCtxPath()+"/index/views/customerManager/xuexinReport.html?customerId="+customerId+"&1="+1);
	  }); 
	 $("#sb").on("click", function(){ 
		 $("#sb").css("color","#00ABA9");
		 $("#xx").attr("style","");
		 $("#gjjs").attr("style","");
		 $("#ds").attr("style","");
		 $("#gr").attr("style","");
		 $("#sbs").show();; 
		 $("#xxs").hide(); 
		 $("#gjj").hide();
		 $("#dss").hide(); 
		 $("#ges").hide() 
		 $("#bindingIdcards").prop("src",getCtxPath()+"/index/views/customerManager/shebaoReport.html?customerId="+customerId); 
	  }); 
	  
	 $("#gjjs").on("click", function(){ 
		 $("#gjjs").css("color","#00ABA9");
		 $("#sb").attr("style","");
		 $("#xx").attr("style",""); 
		 $("#ds").attr("style","");
		 $("#gr").attr("style","");
		 $("#gjj").show();
		 $("#xxs").hide();
		 $("#sbs").hide(); 
		 $("#dss").hide(); 
		 $("#ges").hide();  
		 $("#bindingIdcards").prop("src",getCtxPath()+"/index/views/customerManager/gjjReport.html?customerId="+customerId);
		   
	  }); 
	  
	 $("#ds").on("click", function(){ 
		 $("#xx").attr("style","");
		 $("#gjjs").attr("style",""); 
		 $("#gr").attr("style","");
		 $("#ds").css("color","#00ABA9");
		 $("#xxs").hide();
		 $("#sbs").hide();
		 $("#gjj").hide();
		 $("#dss").show(); 
		 $("#ges").hide();  
		 $("#bindingIdcards").prop("src",getCtxPath()+"/index/views/customerManager/dsReport.html?id="+customerId);
		  
	  }); 
	 
	 $("#gr").on("click", function(){ 
		 $("#sb").attr("style","");
		 $("#xx").attr("style","");
		 $("#gjjs").attr("style","");
		 $("#ds").attr("style","");
		 $("#gjj").attr("style","");
		 $("#gr").css("color","#00ABA9"); 
		 $("#xxs").hide();
		 $("#sbs").hide();
		 $("#gjj").hide();
		 $("#dss").hide(); 
		 $("#ges").show();  
		 $("#bindingIdcards").prop("src",getCtxPath()+"/index/views/customerManager/creditReport.html?customerId="+customerId);
		  
	  }); 
 
	 	$.ajax({
		 url:getCtxPath() + "/backend/mongo/getMongoDisplayInfo.do?v=" + new Date().getTime(),
			method : "post",
			dataType : "json",
			data : {  
				taskType : "06002",
				customerId : customerId
			},
			success : function(resultData){//成功处理函数 
	        	if(resultData == null){
	        		return ;
	        	}if(resultData.url == null && resultData.code != 200){
	        		return ;
	        	}else{
	        		var obj = resultData.list.data.data.education_info;//学历信息
		        	var objschool = resultData.list.data.data.school_info;//学籍信息 
		        	for (var i = 0; i < obj.length; i++) { 
		        		console.log(obj[i].head_img);
		        		var img = obj[i].head_img;
		        		console.log(img)
		        		$("#Level").html(obj[i].edu_level); //学历等级
		        		$("#school").html(obj[i].school);//毕业院校
		        		$("#Infobirthday").html(obj[i].birthday); 
		        		$("#Inforealname").html(obj[i].realname);
		        		$("#Infogender").html(obj[i].gender);
		        		$("#nation").html(obj[i].nation);
		        		$("#InfoentranceDate").html(obj[i].entrance_date);
		        		$("#InfograduateDate").html(obj[i].graduate_date); 
		        		$("#Infolocation").html(obj[i].location); 
		        		$("#Infoschool").html(obj[i].school); 
		        		$("#Infomajor").html(obj[i].major); 
		        		$("#InfoeduType").html(obj[i].edu_type); 
		        		$("#InfoeduLevel").html(obj[i].edu_level); 
		        		$("#InfoeduForm").html(obj[i].edu_form);
		        		$("#InfoheadImgUrl").attr("src", img);//头像 
		        		$("#InfoeduConclusion").html(obj[i].edu_conclusion);
		        		$("#InfocertificateId").html(obj[i].certificate_id);
					}
		        	for (var i = 0; i < objschool.length; i++) {
		        		var imgs = objschool[i].head_img; 
		        		$("#sLevel").html(objschool[i].edu_level);  //学历层次
		        		$("#sschool").html(objschool[i].school);//学校名称 	 
		        		$("#Schoolrealname").html(objschool[i].realname); 
		        		$("#Schoolgender").html(objschool[i].gender); 
		        		$("#Schoolnation").html(objschool[i].nation); 
		        		$("#Schoolbirthday").html(objschool[i].birthday); 
		        		$("#SchoolcardId").html(objschool[i].card_id); 
		        		$("#SchoolexaminationId").html(objschool[i].examination_id); 
		        		$("#SchoolstudentId").html(objschool[i].student_id); 
		        		$("#Schoolschool").html(objschool[i].school); 
		        		$("#Schoolcollege").html(objschool[i].college); 
		        		$("#Schooldepartment").html(objschool[i].department); 
		        		$("#Schoolmajor").html(objschool[i].major); 
		        		$("#Schoolclassname").html(objschool[i].classname); 
		        		$("#SchooleduLevel").html(objschool[i].edu_level); 
		        		$("#SchooleduSystem").html(objschool[i].edu_system); 
		        		$("#SchooleduType").html(objschool[i].edu_type); 
		        		$("#SchooleduForm").html(objschool[i].edu_form); 
		        		$("#SchoolentranceDate").html(objschool[i].entrance_date); 
		        		$("#SchoolgraduateDate").html(objschool[i].graduate_date); 
		        		$("#SchoolStatus").html(objschool[i].status); 
		        		$("#SchoolheadImgUrl").attr("src", imgs);  
					}
	        	}
	        
	        } 
		});

});

//阿里云数据查询
function infoQuery(){ 
		  var taskType=''; 
		  var customerId = getQueryString("customerId"); 
		 $.ajax({
				url : getCtxPath() + "/backend/queryAliyunDataInfo/getMhXinXiInfo.do?v=" + new Date().getTime(),
				method : "post",
				dataType : "json",
				data : { 
					taskType : '06002',//学信  
					customerId  : customerId
				}/*,
				success : function(data) {  
					console.log(data); 
				 
				},
				error : function() {
					layer.msg("获取阿里云数据信息失败,请尝试重新获取！");
				}*/
			}); 
	  	};
