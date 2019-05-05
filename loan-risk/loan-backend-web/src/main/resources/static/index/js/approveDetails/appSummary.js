layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer,
			layer = layui.layer,
			form = layui.form(), element = layui.element();
		});

var json;

function getList(list){
	var html = "<tr style='width: 100px;'><td><strong>应用程序名称</strong></td></tr>";
	if(list != null){
		$.each(list, function(k, v){
			html += "<tr><td>" + v + "</td></tr>";
		});
	}
	$("#appSummary").html(html);
}

function clickFunction(){
	$("#total").on('click', function() {
		var list = json.total.list;
		getList(list);
	});
	$("#manageMoneyMatters").on('click', function() {
		var list = json.manage_money_matters.list;
		getList(list);
	});
	$("#lottery").on('click', function() {
		var list = json.lottery.list;
		getList(list);
	});
	$("#gamble").on('click', function() {
		var list = json.gamble.list;
		getList(list);
	});
	$("#phoneShield").on('click', function() {
		var list = json.phone_shield.list;
		getList(list);
	});
	$("#loan").on('click', function() {
		var list = json.loan.list;
		getList(list);
	});
	$("#game").on('click', function() {
		var list = json.game.list;
		getList(list);
	});
}

$(function() {
	var customerId = getQueryString("customerId");
	var appName = getQueryString("appName");
	//客户app应用列表查询
	$.ajax({
		url :getCtxPath() + "/backend/summary/summaryAppInfo.do",
		method: "post",
		dataType:"json",
		data: {
			customerId:customerId,
			appName:appName
		},
		success: function(data){
			var total = 0;
			var manageMoneyMatters = 0;
			var lottery = 0;
			var gamble = 0;
			var phoneShield = 0;
			var loan = 0;
			var game = 0;
			if (data.code == 200) {
				json = data.list.data;
				if (json != null) {
					total = json.total.count;
					if (total != 0) {
						clickFunction();
						manageMoneyMatters = json.manage_money_matters.count;
						lottery = json.lottery.count;
						gamble = json.gamble.count;
						phoneShield = json.phone_shield.count;
						loan = json.loan.count;
						game = json.game.count;
						getList(json.total.list);
					}
				}
			}
			$("#total").html(total);
			$("#manageMoneyMatters").html(manageMoneyMatters);
			$("#lottery").html(lottery);
			$("#gamble").html(gamble);
			$("#phoneShield").html(phoneShield);
			$("#loan").html(loan);
			$("#game").html(game);
		},
		error: function(){
//			layer.msg("错误" , {icon: 2});//提示信息
		}
	});
});