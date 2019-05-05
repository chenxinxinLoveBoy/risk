			layui.config({
				base: '../../js/' 
			});
			
			var mongodbName = "", keys = "",values="",index="",startTime="",endTime="",timeType="";
			
			// 新增查询条件
			 function add() {
				 var ran=new Date().getTime();
				 $("#divId").append("<br></br> <div class='layui-inline'>"
						 +"  <label class='layui-form-label' style='width: auto;'>key：</label>"
						 +" <div class='layui-input-inline'>"
						 +"   <input name='key'   placeholder='key ' id='key"+ran+"'  autocomplete='off' class='layui-input' >"
						 +" </div>"
						 +" </div>"
						 +"<div class='layui-inline'>"
						 +"  <label class='layui-form-label' style='width: auto;'>value：</label>"
						 +" <div class='layui-input-inline'>"
						 +"   <input name='value'   placeholder='value ' id='value"+ran+"'  autocomplete='off' class='layui-input' >"
						 +" </div>"
						 +" </div>");
				 index += ran + ",";
 			};
			
			// 条件搜索
			 function sel() {
				 startTime = $("#startTime").val();
			     endTime = $("#endTime").val();
				 timeType=$("#timeType").val().trim();
				 if(timeType == '00'){
					 timeType ='';
					} 
  				 mongodbName = $("#mongodbName").val().trim();
  				 var keyArr = index.split(",");
 				 for(var i=0; i<keyArr.length; i++){
 					 var k= $("#key"+keyArr[i]).val();
 					 if(k == null || k == '' || k == undefined){
 						 break;
 					 }
  					keys+=k+ ",";
 					
 					 var j= $("#value"+keyArr[i]).val();
 					 if(j == null || j == '' || j == undefined){
 						 break;
 					 }
 					values+=j+ ",";
  				 }
     			 getList(mongodbName,keys,values,startTime,endTime,timeType);
			};
			
			function getList(mongodbName,keys,values,startTime,endTime,timeType){
				$.ajax({
					url :getCtxPath() +"/backend/findMongodb/findMongodbInfoList.do?v=" + new Date().getTime(),
					method: "post",
					dataType:"json",
					data: {
						keys : keys,
						values : values,
						mongodbName:mongodbName,
						startTime : startTime,
						endTime : endTime,
						timeType:timeType
					},
					success: function(data){
						var list = data.list.data;
 						var table = $("#customerMessage").html();
 						var html = "";
						if(list != null){
							$.each(list, function(k, v){
 								html += "<tr onclick=\"findMongodbInfo('" + v.id  + "', '" + mongodbName  + "');\"><td >" +v.id + "</td><td>" +  v.createTime + "</td></tr>"
								+ "<tr id='messageInfo" + v.id + "' style='display:none;'><td colspan='3' id='content" + v.id + "'> </td></tr>";
							});
						};
						$("#content").html(html);
					}
				});
			
			}
			
			function findMongodbInfo(id,mongodbName){
				$.ajax({
					url :getCtxPath() + "/backend/findMongodb/findMongodbInfo.do",
					method: "post",
					dataType:"text",
					data: {
						mongodbName:mongodbName,
						id:id
						},
					success: function(data){
						$("#content"+id).html(data);
						var contentText = $("#messageInfo"+id).css("display");
						if (contentText == "none") {
							$("#messageInfo"+id).removeAttr("style","display:block;");
						} else {
							$("#messageInfo"+id).attr("style","display:none;");
						}
					},
					error: function(){
					} 
				});
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
					  }
//					  else {
//						  $("#startTime").val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
//						  $("#endTime").val(laydate.now(0,'YYYY-MM-DD 23:59:59'));
//						  startTime = $("#startTime").val();
//						  endTime = $("#endTime").val();
//					  }
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
//					  findMessage();
				});
			});