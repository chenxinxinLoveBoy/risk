layui.config({
	base : '../../js/'
});

layui.use([ 'layer', 'element' ],
				function() {
					var $ = layui.jquery, layerTips = parent.layer === undefined ? layui.layer : parent.layer, // 获取父窗口的layer对象
					layer = layui.layer, // 获取当前窗口的layer对象
					element = layui.element();
				});

$(function() {
//	var beginTime = getQueryString("beginTime");
//	var endTime = getQueryString("endTime");
	var isStep = getQueryString("isStep");
	var source = getQueryString("source");
	var tplId = window.parent.$("#tplId").val().trim();
	var treeId = window.parent.$("#treeId").val().trim();
	var appName = window.parent.$("#appName").val().trim();
	var startTime = window.parent.$("#startTime").val().trim();
	var endTime = window.parent.$("#endTime").val().trim();
	var appLevel = window.parent.$("#appLevel").val().trim();
	if(tplId == "00"){
		tplId="";
	}
	if(treeId == "00"){
		treeId="";
	}
	if(appName == "00"){
		appName="";
	}
	if(appLevel == "00"){
		appLevel="";
	}
 	$.ajax({
		url : getCtxPath()+ "/backend/application/queryResultMapInfo.do?v=" + new Date().getTime(),
		method : "post",
		dataType : "json",
		data : {
			startTime : startTime,
			endTime : endTime,
			source: source,
			isStep : isStep,
			appName : appName,
			banControlTplId:tplId,
			decisionTreeId:treeId,
			pageIndex : 1,
			pageSize : 100,
			appLevel:appLevel
		},
		success : function(data) {
  	        // 基于准备好的dom，初始化echarts实例
	        var myChart = echarts.init(document.getElementById('main'));
	        if(data.code == 200){
	        	var builderJson = data.list.data.listInfoMap;
	        	var countMapJson = data.list.data.countMap;
	        	var certMapJson = data.list.data.certMap;
	        	var count = data.list.data.count;
	        	var certCount = data.list.data.certCount;

	        		var waterMarkText = '方天画戟';

	        		var canvas = document.createElement('canvas');
	        		var ctx = canvas.getContext('2d');
	        		canvas.width = canvas.height = 100;
	        		ctx.textAlign = 'center';
	        		ctx.textBaseline = 'middle';
	        		ctx.globalAlpha = 0.2;
	        		ctx.font = '20px Microsoft Yahei';
	        		ctx.translate(50, 50);
	        		ctx.rotate(-Math.PI / 4);
	        		ctx.fillText(waterMarkText, 0, 0);

	        		var option = {
	        		    backgroundColor: {
	        		        type: 'pattern',
	        		        image: canvas,
	        		        repeat: 'repeat'
	        		    },
		                tooltip: {
		        	        trigger: 'item',
		        	        formatter: "{b} : {c}"
	        		    },
	        		    title: [{
	        		        text: '信用审核统计列表',
	        		        x: '25%',
	        		        textAlign: 'center'
	        		    }, {
	        		        text: '总认证次数',
	        		        subtext: '总计 '+count ,
	        		        x: '80%',
	        		        textAlign: 'center'
	        		    }, {
	        		        text: '总认证人数',
	        		        subtext: '总计 ' +certCount,
	        		        x: '80%',
	        		        y: '50%',
	        		        textAlign: 'center'
	        		    }],
	        		    grid: [{
	        		        top: 50,
	        		        width: '55%',
	        		        bottom: '5%',
	        		        left: 10,
	        		        containLabel: true
	        		    }, {
	        		        top: '55%',
	        		        width: '45%',
	        		        bottom: 0,
	        		        left: 10,
	        		        containLabel: true
	        		    }],
	        		    xAxis: [{
	        		        type: 'value',
	        		        max: count,
	        		        splitLine: {
	        		            show: false
	        		        }
	        		    }],
	        		    yAxis: [{
	        		        type: 'category',
	        		        data: Object.keys(builderJson),
	        		        axisLabel: {
	        		        	interval: 'auto',  
	        		            rotate: 0
	        		        },
	        		        splitLine: {
	        		            show: false
	        		        }
	        		    }],
	        		    series: [{
	        		        type: 'bar',
	        		        stack: 'chart',
	        		        z: 3,
	        		        label: {
	        		            normal: {
	        		                position: 'right',
	        		                show: true
	        		            }
	        		        },
	        		        data: Object.keys(builderJson).map(function (key) {
	        		            return builderJson[key];
	        		        })
	        		    }, {
	        		        type: 'bar',
	        		        stack: 'chart',
	        		        silent: true,
	        		        itemStyle: {
	        		            normal: {
	        		                color: '#eee'
	        		            }
	        		        },
	        		        data: Object.keys(builderJson).map(function (key) {
	        		            return builderJson[key];
	        		        })
	        		    }, {
	        	            name: '认证次数',
	        		        type: 'pie',
	        		        radius: [0, '30%'],
	        		        center: ['80%', '25%'],
	        		        data: Object.keys(countMapJson).map(function (key) {
	        		            return {
	        		                name: key,
	        		                value: countMapJson[key],
	        		                tooltip: {
	        		        	        trigger: 'item',
	        		        	        formatter: "{b} : {c} ({d}%)"
	        	        		    }
	        		            }
	        		        })
	        		    }, {
	        	            name: '认证人数',
	        		        type: 'pie',
	        		        radius: [0, '30%'],
	        		        center: ['80%', '75%'],
	        		        data: Object.keys(certMapJson).map(function (key) {
	        		            return {
	        		                name: key,
	        		                value: certMapJson[key],
	        	        		    tooltip: {
	        		        	        trigger: 'item',
	        		        	        formatter: "{b} : {c} ({d}%)"
	        	        		    }
	        		            }
	        		        })
	        		    }]
	        		}
	    	        // 使用刚指定的配置项和数据显示图表。
	    	        myChart.setOption(option);
	        }		
 		},
		error : function() {
			alert("获取图表失败,请尝试重新获取！");
		}
	});

})

//关闭
function closeIfrname(){
	var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(frameindex);
}
