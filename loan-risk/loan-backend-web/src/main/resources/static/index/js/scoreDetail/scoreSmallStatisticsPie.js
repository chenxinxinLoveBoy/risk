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

//关闭
function closeIfrname(){
//	layer.closeAll();
	var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(frameindex);
}

$(function() {
//	var beginTime = getQueryString("beginTime");
//	var endTime = getQueryString("endTime");
	var beginTime = window.parent.$("#beginTime").val().trim();
	var endTime = window.parent.$("#endTime").val().trim();
	var scoreTplId = window.parent.$("#scoreTplId").val().trim();
 	$.ajax({
		url : getCtxPath()+ "/backend/scoreDetail/getSmallStatisticsPie.do",
		method : "post",
		dataType : "json",
		data : {
			beginTime : beginTime,
			endTime : endTime,
			scoreTplId:scoreTplId
		},
		success : function(data) {
  	        // 基于准备好的dom，初始化echarts实例
	        var myChart = echarts.init(document.getElementById('main'));
	        option = {
//	        	    title : {
//	        	        text: '评分小类统计饼状图',
////	        	        subtext: '纯属虚构',
//	        	        x:'center'
//	        	    },
	        	    tooltip : {
	        	        trigger: 'item',
	        	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	        	    },
//	        	    legend: {
//	        	        orient: 'vertical',
//	        	        left: 'left',
//	        	        data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
//	        	    },
	        	    series : [
	        	        {
	        	            name: '出现次数',
	        	            type: 'pie',
	        	            radius : '60%',
	        	            center: ['50%', '50%'],
	        	            data:  data.data.smallStatisticsPieObject,
//	        	            [
//	        	                {value:335, name:'直接访问'},
//	        	                {value:310, name:'邮件营销'},
//	        	                {value:234, name:'联盟广告'},
//	        	                {value:135, name:'视频广告'},
//	        	                {value:1548, name:'搜索引擎'}
//	        	            ],
	        	            itemStyle: {
	        	                emphasis: {
	        	                    shadowBlur: 10,
	        	                    shadowOffsetX: 0,
	        	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	        	                }
	        	            }
	        	        }
	        	    ]
	        	};
	        // 使用刚指定的配置项和数据显示图表。
	        myChart.setOption(option);
 		},
		error : function() {
			alert("获取饼形图失败,请尝试重新获取！");
		}
	});

})
