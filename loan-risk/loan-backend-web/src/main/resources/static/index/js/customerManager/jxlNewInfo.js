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
	var taskType ='06003';
	 $.ajax({//查询mongodb数据展示
				url:getCtxPath() + "/backend/mongo/getMongoDisplayInfo.do?v=" + new Date().getTime(),
				method: "post",
				dataType:"json",
				data: {
					taskType :taskType, 
					customerId : customerId //"27b69313fe9842baa8f3c321187ebe51" 向老板测试数据    
				},
				success : function(resultData){//成功处理函数  
	        	if(resultData == null){
	        	return ;
	        	}
	        	if(resultData.url == null && resultData.code != 200){
		        	return ;
		        	} 
	        	//报告基本信息
	        	var report = resultData.list.data.report_data.report; 
	        	$("#rpt_id").html(report.rpt_id); 
	        	var dateTime = new Date(report.update_time); 
	        	var time = dateTime.toLocaleString();
	        	$("#update_time").html(time);
	        	//用户申请表检测
	        	var application = resultData.list.data.report_data.application_check; 
	        	$("#name").html(application[0].check_points.key_value);
	        	$("#idCard").html(application[1].check_points.key_value);
	        	$("#gender").html(application[1].check_points.gender);
	        	$("#age").html(application[1].check_points.age);
	        	$("#city").html(application[1].check_points.province+application[1].check_points.region+application[1].check_points.city);
	        	$("#phone").html(application[2].check_points.key_value);
	        	$("#address").html(application[3].check_points.key_value);
	        	$("#home_phone").html(application[4].check_points.key_value);
	        	$("#jd").html(application[5].check_points.key_value);
	        	//用户信息检测
	        	var userInfo = resultData.list.data.report_data.user_info_check; 
	        	var count = userInfo.check_search_info.searched_org_cnt; 
	        	$("#searched_org_cnt").html(count);
	        	$("#searched_org_type").html(userInfo.check_search_info.searched_org_type); 
	        	var nameInfo = userInfo.check_search_info.idcard_with_other_names;  
	        	if(nameInfo != null){ 
	        		var unamestr='';
	        		for (var i = 0; i < nameInfo.length; i++) { 
	        			 var uname = nameInfo[i];  
	        			 unamestr += uname + "|";
					}
	        	}
	        	$("#idcard_with_other_names").html(unamestr);  
	        	
	        	var phones = userInfo.check_search_info.idcard_with_other_phones;
	        	if(phones != null){ 
	        		var phonesstr='';
	        		for (var i = 0; i < nameInfo.length; i++) { 
	        			 var phone = phones[i];  
	        			 phonesstr += phone + "|";
					}
	        	}
	        	$("#idcard_with_other_phones").html(phonesstr); 
	        	
	        	var otnerName = userInfo.check_search_info.phone_with_other_names;
	        	if(otnerName != null){
	        		var str = '';
	        		for (var i = 0; i < otnerName.length; i++) {
	        			var other = otnerName[i];
	        			str += other +"|";
					}
	        	}
	        	$("#phone_with_other_names").html(str);
	        	var idCard = userInfo.check_search_info.phone_with_other_idcards;
	        	if(idCard != null ){
	        		var idCardstr = '';
	        		for (var i = 0; i < idCard.length; i++) {
	        			var idCards = idCard[i];
	        			idCardstr += idCards +"|";
					}
	        	}
	        	$("#phone_with_other_idcards").html(idCardstr);
	        	
	        	var cnt = userInfo.check_search_info.register_org_cnt; 
	        	if(cnt != null ){
	        		var cntstr = '';
	        		for (var i = 0; i < cnt.length; i++) {
	        			var cnts = cnt[i];
	        			cntstr += cnts +"|";
					}
	        	}
	        	$("#register_org_cnt").html(cntstr);
	        	
	        	var type = userInfo.check_search_info.register_org_type; 
	        	if(type != null ){
	        		var typestr = '';
	        		for (var i = 0; i < type.length; i++) {
	        			var types = type[i];
	        			typestr += types +"|";
					}
	        	}
	        	$("#register_org_type").html(typestr);
	        	var web = userInfo.check_search_info.arised_open_web; 
	        	if(web != null ){
	        		var webstr = '';
	        		for (var i = 0; i < web.length; i++) {
	        			var webs = web[i];
	        			webstr += webs +"|";
					}
	        	}
	        	$("#arised_open_web").html(webstr);
	        	
	        	var blackInfo = resultData.list.data.report_data.user_info_check.check_black_info; 
	        	$("#phone_gray_score").html(blackInfo.phone_gray_score);
	        	$("#contacts_class1_blacklist_cnt").html(blackInfo.contacts_class1_blacklist_cnt);
	        	$("#contacts_class2_blacklist_cnt").html(blackInfo.contacts_class2_blacklist_cnt);
	        	$("#contacts_class1_cnt").html(blackInfo.contacts_class1_cnt);
	        	$("#contacts_router_cnt").html(blackInfo.contacts_router_cnt);
	        	$("#contacts_router_ratio").html(blackInfo.contacts_router_ratio); 
	        	//用户行为检测（behavior_check）
	        	var behaviorInfo = resultData.list.data.report_data.behavior_check;
		        	if(behaviorInfo != null){  
						var table = "";
						var tableTemp=""; 
						var html ="";
						html = tableTemp + "<tr style='width: 350px;'>"  
						+ '<td align="center">检查项</td>'
						+ '<td align="center">结果</td>'
						+ '<td align="center">依据</td>' 
						+ '<td align="center">标记(0:无数据, 1:通过, 2:不通过)</td>'  
						"</tr>"; 
						if(behaviorInfo != null){  
							$.each(behaviorInfo, function(k, v){  
								if( v.score == 2 ){    
									html += "<tr>" +
									"<td  align='center' style='color : red'>" + v.check_point_cn + "</td>" +
									"<td  align='center' style='color : red'>" + v.result + "</td>" +
									"<td  align='center'style='color : red' >" + v.evidence + "</td>" + 
									"<td  align='center'style='color : red'>" + v.score  + "</td>" + 
									"</tr>"; 
								}else{
									html += "<tr>" +
									"<td  align='center'>" + v.check_point_cn + "</td>" +
									"<td  align='center'>" + v.result + "</td>" +
									"<td  align='center'>" + v.evidence + "</td>" + 
									"<td  align='center'>" + v.score  + "</td>" + 
									"</tr>";
								}
							});
						}
						$("#user_behavior").html(html); 
					}else{
						html= tableTemp +'<tr style="width: 350px;">' 
						+ '<td align="center">检查项</td>'
						+ '<td align="center">结果</td>'
						+ '<td align="center">依据</td>' 
						+ '<td align="center">标记</td>'  
						+ '</tr>'
						+ '<tr>'
						+ '<td align="center"></td>'
						+ '<td align="center"></td>'
						+ '<td align="center"></td>' 
						+ '<td align="center"></td>'  
						+ '</tr>';
						$("#user_behavior").html(html);
					}
					
		        //电商月消费（ebusiness_expense）	
	        	var userebusinessInfo = resultData.list.data.report_data.ebusiness_expense; 
	        	if(behaviorInfo != null){  
					var table = "";
					var tableTemp=""; 
					var html ="";
					html = tableTemp + "<tr style='width: 350px;'>"  
					+ '<td align="center">月份</td>'
					+ '<td align="center">全部消费笔数</td>'
					+ '<td align="center">全部消费金额（元）</td>' 
					+ '<td align="center">品类分析</td>'  
					"</tr>"; 
					if(userebusinessInfo != null){  
						$.each(userebusinessInfo, function(k, v){ 
							html += "<tr>" +
							"<td align='center'>" + v.trans_mth + "</td>" +
							"<td align='center'>" + v.all_count + "</td>" +
							"<td align='center'>" + v.all_amount + "</td>" + 
							"<td align='center'>" + v.all_category + "</td>" + 
							"</tr>";
						});
					}
					$("#user_ebusiness").html(html); 
				}else{
					html= tableTemp +'<tr style="width: 350px;">' 
					+ '<td align="center">月份</td>'
					+ '<td align="center">全部消费笔数</td>'
					+ '<td align="center">全部消费金额（元）</td>' 
					+ '<td align="center">品类分析</td>'  
					+ '</tr>'
					+ '<tr>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>' 
					+ '<td align="center"></td>'  
					+ '</tr>';
					$("#user_ebusiness").html(html); 
				}
	        	//电商地址分析（deliver_address）
	        	var deliveraaddress = resultData.list.data.report_data.deliver_address; 
	        	if(deliveraaddress != null){  
					var table = "";
					var tableTemp=""; 
					var html ="";
					html = tableTemp + "<tr style='width: 350px;'>"  
					+ '<td align="center">地址</td>' 
					+ '<td align="center">消费总金额</td>' 
					+ '<td align="center">收货人姓名</td>' 
					+ '<td align="center">收货人手机</td>'  
					"</tr>"; 
					if(deliveraaddress != null){  
						$.each(deliveraaddress, function(k, v){  
							html += "<tr>" +
							"<td align='center'>" + v.address + "</td>" + 
							"<td align='center'>" + v.total_amount + "</td>" +  
							"<td align='center'>" + v.receiver[0].name + "</td>" + 
							"<td align='center'>" + v.receiver[0].phone_num_list + "</td>" +  
							"</tr>";
						});
					}
					$("#user_address").html(html); 
				}else{
					html= tableTemp +'<tr style="width: 350px;">' 
					+ '<td align="center">地址</td>' 
					+ '<td align="center">消费总金额</td>' 
					+ '<td align="center">收货人姓名</td>' 
					+ '<td align="center">收货人手机</td>'  
					+ '</tr>'
					+ '<tr>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>' 
					+ '<td align="center"></td>'   
					+ '</tr>';
					$("#user_address").html(html); 
				} 
	        	
	        	//联系人名单（collection_contact）
	        	var collectionContactInfo = resultData.list.data.report_data.collection_contact; 
	        	if(collectionContactInfo != null ){ 
					var table = "";
					var tableTemp=""; 
					var html ="";
					html = tableTemp + "<tr style='width: 350px;'>"  
					+ '<td align="center">联系人</td>' 
					+ '<td align="center">最早联系时间</td>' 
					+ '<td align="center">最晚联系时间</td>' 
					+ '<td align="center">联系电话</td>'  
					+ '<td align="center">近半年通话（分）</td>'
					"</tr>"; 
					if(collectionContactInfo != null){  
						$.each(collectionContactInfo, function(k, v){  
							html += "<tr>" +
							"<td align='center'>" + v.contact_name + "</td>" + 
							"<td align='center'>" + v.trans_start + "</td>" +  
							"<td align='center'>" + v.trans_end + "</td>" + 
							"<td align='center'>" + v.call_len + "</td>" +  
							"</tr>";
						});
					}
					$("#user_contact").html(html);  
	        	}
	        	else{
					html= tableTemp +'<tr style="width: 350px;">' 
					+ '<td align="center">联系人</td>' 
					+ '<td align="center">最早联系时间</td>' 
					+ '<td align="center">最晚联系时间</td>' 
					+ '<td align="center">联系电话</td>'  
					+ '<td align="center">近半年通话（分）</td>'
					+ '</tr>'
					+ '<tr>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>'
					+ '<td align="center"></td>' 
					+ '<td align="center"></td>'   
					+ '<td align="center"></td>'   
					+ '</tr>';
					$("#user_contact").html(html); 
				}
	        } 
			});  
	  }); 
	 	  