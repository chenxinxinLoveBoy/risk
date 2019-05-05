layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
				function() {
					var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer
							: parent.layer, // 获取父窗口的layer对象
					layer = layui.layer, // 获取当前窗口的layer对象
					form = layui.form(), element = layui.element();
				});

$(function() {
//	var beginTime = getQueryString("beginTime");
//	var endTime = getQueryString("endTime");
	var beginTime = window.parent.$("#beginTime").val().trim();
	var endTime = window.parent.$("#endTime").val().trim();
 	$.ajax({
		url : getCtxPath()+ "/backend/scoreDetail/getBigStatisticsHistogram.do",
		method : "post",
		dataType : "json",
		data : {
			beginTime : beginTime,
			endTime : endTime
		},
		success : function(data) {
  	        // 基于准备好的dom，初始化echarts实例
	        var myChart = echarts.init(document.getElementById('main'));
			option = {
			    tooltip: {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'shadow'
			        }
			    },
			    legend: {
			        data: ['出现次数']
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis: {
			        type: 'value',
			        boundaryGap: [0, 0.1]
			    },
			    yAxis: {
			        type: 'category',
			        data: data.data.bigStatisticsHistograObject.name
			    },
			    series: [
			        {
			            name: '出现次数',
			            type: 'bar',
			            data: data.data.bigStatisticsHistograObject.count
			        }
			    ]
			};
			
	        // 使用刚指定的配置项和数据显示图表。
	        myChart.setOption(option);
 		},
		error : function() {
			alert("获取柱形图失败,请尝试重新获取！");
		}
	});

})
