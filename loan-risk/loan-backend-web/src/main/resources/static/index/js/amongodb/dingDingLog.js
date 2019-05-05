	layui.config({
		base: '../../js/' 
	});

	layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer,
			layer = layui.layer,
			form = layui.form(), element = layui.element();
	});

	function findMessage() {
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		$.ajax({
			url :getCtxPath() + "/backend/mongodb/findDingdingLog.do",
			method: "post",
			dataType:"json",
			data: {
				sTime : startTime,
				eTime : endTime
			},
			success: function(data){
				var html = "";
				if (data.code == 200) {
					var list = data.list.data;
					if(list != null){
						$.each(list, function(k, v){
							var msgId = v.msgId == null ? "" : v.msgId;
							var ip = v.ip == null ? "" : v.ip;
							var alarmCode = v.alarmCode == null ? "" : v.alarmCode;
							var alarmMsg = v.alarmMsg == null ? "" : v.alarmMsg;
							var alarmType = v.alarmType == 3 ? "钉钉和短信" : v.alarmType == 2 ? "邮件" : v.alarmType == 1 ? "短信" : "钉钉";
							var platform = v.thirdPartyCreditInvestigation;
							platform = platform == 1 ? "芝麻信用评分" : platform == 2 ? "芝麻信用欺诈清单" : platform == 3 ? "同盾贷前审核" : platform == 4 ? "聚信立蜜蜂" : platform == 5 ?
									"聚信立蜜罐" : platform == 6 ? "芝麻行业清单" : platform == 7 ? "白骑士" : platform == 8 ? "91征信" : platform == 9 ? "91征信回调" : platform == 10 ? 
									"索伦" : platform == 11 ? "宜信" : platform == 12 ? "小视科技" : platform == 13 ? "数聚魔盒" : platform == 83 ? "MQ其它" :platform == 84 ? 
									"JSON文件上传" : platform == 85 ? "大数据" : platform == 86 ? "客户短信" : platform == 87 ? "app应用列表推送" :platform == 88 ? "客户通讯录信息" : platform == 89 ? 
									"客户通话记录" : platform == 90 ? "风控配置项" : platform == 91 ? "系统配置项" : platform == 92 ? "定时任务配置管理" : platform == 93 ? 
									"催收对接" : platform == 94 ? "客户信息对接" : platform == 95 ? "app对接" : platform == 96 ? "审批状态变更" : platform == 97 ? 
									"APP状态同步告警" : platform == 98 ? "潘多拉后台管理报警" : "其他报警";
							/**
						     * 0-非常严重,1-严重,2-普通 sc_alarm.priority
						     */
							var priority = v.priority == 0 ? "非常严重" : v.priority == 1 ? "严重" : "普通";
							
							var time = "";
							if (v.createTime != null && v.createTime != "") {
								var date = new Date();  
							    date.setTime(v.createTime);  
							    time+= date.getFullYear();   //年  
							    time+= "-" + getMonth(date); //月   
							    time += "-" + getDay(date);   //日  
							    time+= "&nbsp;&nbsp;" + getHours(date);   //时  
							    time+= ":" + getMinutes(date);      //分
							    time+= ":" + getSeconds(date);      //分
							}
							
							html += "<tr><td>" + msgId + "</td><td>" + ip + "</td><td>" + alarmCode + "</td><td>" + time + "</td><td>" + alarmMsg + "</td>" +
									"<td>" + priority + "</td><td>" + alarmType + "</td><td>" + platform + "</td></tr>";
						});
					};
				}
				$("#content").html(html);
			}
		});
	};
	
	//返回 01-12 的月份值   
	function getMonth(date){  
	    var month = "";  
	    month = date.getMonth() + 1; //getMonth()得到的月份是0-11  
	    if(month<10){  
	        month = "0" + month;  
	    }  
	    return month;  
	}  
	//返回01-30的日期  
	function getDay(date){  
	    var day = "";  
	    day = date.getDate();  
	    if(day<10){  
	        day = "0" + day;  
	    }  
	    return day;  
	}
	//返回小时
	function getHours(date){
	    var hours = "";
	    hours = date.getHours();
	    if(hours<10){  
	        hours = "0" + hours;  
	    }  
	    return hours;  
	}
	//返回分
	function getMinutes(date){
	    var minute = "";
	    minute = date.getMinutes();
	    if(minute<10){  
	        minute = "0" + minute;  
	    }  
	    return minute;  
	}
	//返回秒
	function getSeconds(date){
	    var second = "";
	    second = date.getSeconds();
	    if(second<10){  
	        second = "0" + second;  
	    }  
	    return second;  
	}
	
	$(function() {
		//我们强烈推荐你在代码最外层把需要用到的模块先加载
		layui.use(['layer', 'form', 'element','laydate'], function(){
			  var layer = layui.layer
			  ,form = layui.form()
			  ,element = layui.element();
			  form.render(); //刷新全部

				var laydate = layui.laydate;
				var start = {
				    format: 'YYYY-MM-DD hh:mm:ss'
					,istime: true
				    ,choose: function(datas){
				      end.min = datas; //开始日选好后，重置结束日的最小日期
				      end.start = datas //将结束日的初始值设定为开始日
				    }
				};
				var end = {
					    format: 'YYYY-MM-DD hh:mm:ss'
						,istime: true
					    ,choose: function(datas){
					      start.max = datas; //结束日选好后，重置开始日的最大日期
					    }
				};
				var startTime = {
					    format: 'YYYY-MM-DD hh:mm:ss'
						,istime: true
					    ,choose: function(datas){
					      end.min = datas; //开始日选好后，重置结束日的最小日期
					      end.start = datas //将结束日的初始值设定为开始日
					    }
				};
				var endTime = {
					    format: 'YYYY-MM-DD hh:mm:ss'
						,istime: true
					    ,choose: function(datas){
					      start.max = datas; //结束日选好后，重置开始日的最大日期
					    }
				};
				startTime = decodeURI(getQueryString("startTime"));
				endTime = decodeURI(getQueryString("endTime"));
			  
			  if (startTime != '' || endTime != '') {
				  $("#startTime").val(startTime);
				  $("#endTime").val(endTime);
			  }else {
				  $("#startTime").val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
				  $("#endTime").val(laydate.now(0,'YYYY-MM-DD 23:59:59'));
				  startTime = $("#startTime").val();
				  endTime = $("#endTime").val();
			  }
			  $("#startTime").on("click", function(){
			    start.elem = this;
			    laydate(start);
			    if(laydate.now(0,'YYYY-MM-DD hh:mm:ss') == $(this).val()){
				    $(this).val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
			    }
			  });
			  $("#endTime").on("click", function(){
			    end.elem = this
			    laydate(end);
			  });
			  findMessage();
		});
	});