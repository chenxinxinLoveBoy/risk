			layui.config({
				base: '../../js/'
			});

			var option = "", timeType = "", startTime = "", endTime = "", treeId = "",source="";
			var treeNames = [];
			
			//初始化加载startTime
			$(function(){
				$.ajax({
					url : getCtxPath() + "/backend/scDecisionTree/scDecisionTreeListAll.do?v=" + new Date().getTime(),
					method : "post",
					dataType : "json",
					data : {
						pageIndex: 0,
						pageSize: 100
					},
					success : function(data) {
						var arr = [];
						arr.push('<select name="treeId" id = "treeId" class="layui-input" lay-search="">');
				    	arr.push('<option value="00">全部</option>');
						layui.each(data.list, function(index, item){
						    	arr.push('<option value="'+ item.decisionTreeId + '">' + item.decisionTreeName + '</option>');
						    	treeNames[item.decisionTreeId] = item.decisionTreeName ;
					    });
						arr.push('</select>');
						$('#decisionTreeId').html(arr.join(''));
						
						getList();
						form.render(); //刷新全部
					},
					error : function() {
						alert("获取禁止项模板信息失败,请尝试重新获取！");
					}
				});
				
				
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
				/*treeId = $("#treeId").val().trim();
				source=$("#source").val().trim();
				if(source=='00'){
					source='';
				}
				if(treeId=='00'){
					treeId='';
				}*/
				getList();
			});
			
			function getList(){
				
				$.ajax({
					url : getCtxPath() + "/backend/application/getPassingRate.do",
					method : "post",
					dataType : "json",
					data : {
						timeType : timeType,
						startTime : startTime,
						endTime : endTime,
						/*treeId : treeId,
						source:source*/
					},
					success : function(data) {
							setOption(data);
							// 基于准备好的dom，初始化echarts实例
					        var myChart = echarts.init(document.getElementById('main'));
					        // 使用刚指定的配置项和数据显示图表。
					        myChart.setOption(option,true);
					},
					error : function() {
						alert("获取用户信息失败,请尝试重新获取！");
					}
				});
			}
			
			
			function setOption(data) {
				var list = data.list;
				var timeCol = new Array();
				var rateCol = new Array();
				var passPersonCountCol = new Array();
				var personCount = new Array();
				for(var i in list) {
					timeCol[i] = list[i].timeValue;
					rateCol[i] = list[i].rate;
					personCount[i] = list[i].personCount;
					passPersonCountCol[i] = list[i].passPersonCount;
				}
				option = {
					    title : {
					        text: '通过率趋势图',
					        subtext: ''
					    },
					    tooltip : {
					        trigger: 'axis'
					    },
					    legend: {
					        data:['通过率','通过人数','申请人数']
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
					            boundaryGap : false,
					            data : timeCol
					        }
					    ],
					    yAxis : [
					        {
					            type : 'value',
					            axisLabel : {
					                formatter: '{value} （人）'
					            }
					        },
					        {
					            type : 'value',
					            axisLabel : {
					                formatter: '{value} %'
					            },
					        	axisLine : {    // 轴线
				                show: true,
					                lineStyle: {
					                    color: 'red',
					                    width: 2
					                }
					        	}
					        }
					    ],
					    series : [
					        {
					            name:'通过率',
					            type:'line',
					            yAxisIndex: 1,
					            data:rateCol,
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
					            type:'line',
//					            stack:'人数',
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
					            name:'申请人数',
					            type:'line',
//					            stack:'人数',
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
