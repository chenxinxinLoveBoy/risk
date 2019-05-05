			layui.config({
				base: '../../js/'
			});

			var option = "", timeType = "", startTime = "", endTime = "";
			
			//初始化加载startTime
			$(function(){
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
					  $("#startTime").val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
					  $("#startTime").on("click", function(){
					    start.elem = this;
					    laydate(start);
					    if(laydate.now(0,'YYYY-MM-DD hh:mm:ss') == $(this).val().trim()){
						    $(this).val(laydate.now(0,'YYYY-MM-DD 00:00:00'));
					    }
					  });
					  $("#endTime").val(laydate.now(0,'YYYY-MM-DD 23:59:59'));
					  $("#endTime").on("click", function(){
					    end.elem = this
					    laydate(end);
					  });
				});
			})
			
			// 搜索
			$('#search').on('click', function() {
				timeType = $('#timeType').val();
				startTime = $('#startTime').val();
				endTime = $('#endTime').val();
				getList();
			});
			
			function getList(){
				
				$.ajax({
					url : getCtxPath() + "/backend/application/getRgApprovalRate.do?v=" + new Date().getTime(),
					method : "post",
					dataType : "json",
					data : {
						timeType : timeType,
						startTime : startTime,
						endTime : endTime,
					},
					success : function(data) {
							setOption(data);
							// 基于准备好的dom，初始化echarts实例
					        var myChart = echarts.init(document.getElementById('main'));
					        // 使用刚指定的配置项和数据显示图表。
					        myChart.setOption(option);
					},
					error : function() {
						alert("获取用户信息失败,请尝试重新获取！");
					}
				});
			}
			
			function setOption(data) {
				var list = data.list;
				var timeCol = new Array();
				var nickNameCol = new Array();
				var rateCol = new Array();
				var passPersonCountCol = new Array();
				var personCount = new Array();
				for(var i in list) {
					timeCol[i] = list[i].timeValue;
					nickNameCol[i] = list[i].nickName;
					rateCol[i] = list[i].rate;
					personCount[i] = list[i].personCount;
					passPersonCountCol[i] = list[i].passPersonCount;
				}
					console.log(nickNameCol);			
				option = {
					    title : {
					        text: '人工审批通过率趋势图',
					        subtext: ''
					    },
					    tooltip : {
					        trigger: 'axis'
					    },
					    legend: {
					    	data:['通过率','通过人数','审批人数']
					    },
					    toolbox: {
					        show : true,
					        feature : {
					            mark : {show: true},
					            dataView : {show: true, readOnly: false},
					            magicType : {show: true, type: ['line', 'bar']},
					            saveAsImage : {show: true},
					            restore : {show: true}
					            
					        }
					    },
					    calculable : true,
					    xAxis : [
					        {
					            type : 'category',
					            boundaryGap : true,
					            data : nickNameCol
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value',
					            axisLabel : {
					                formatter: '{value} （人）'
					            }
					        }
					    ],
					    series : [
							        {
							            name:'通过率',
							            type:'bar',
							            data:rateCol,
							            stack:'人数',
							            markPoint : {
							                data : [
							                    {type : 'max', name: '最大值'},
							                    {type : 'min', name: '最小值'}
							                ]
							            },
							            markLine : {
							                data : [
							                    {type : 'average', name: '平均值'}
							                ]
							            }
							        },
							        {
							            name:'通过人数',
							            type:'bar',
							            stack:'人数',
							            data:passPersonCountCol,
							            markPoint : {
							                data : [
							                    {type : 'max', name: '最大值'},
							                    {type : 'min', name: '最小值'}
							                ]
							            },
							            markLine : {
							                data : [
							                    {type : 'average', name: '平均值'}
							                ]
							            }
							        },
							        {
							            name:'审批人数',
							            type:'bar',
							            stack:'人数',
							            data:personCount,
							            markPoint : {
							                data : [
							                    {type : 'max', name: '最大值'},
							                    {type : 'min', name: '最小值'}
							                ]
							            },
							            markLine : {
							                data : [
							                    {type : 'average', name: '平均值'}
							                ]
							            }
							        }
					    ]
					};
			}