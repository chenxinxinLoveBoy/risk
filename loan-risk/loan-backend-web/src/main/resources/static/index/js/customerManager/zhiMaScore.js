layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
				function() {
					var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
					layer = layui.layer, // 获取当前窗口的layer对象
					form = layui.form(), element = layui.element();
				});
var applicationId ="";
$(function() {
	applicationId= getQueryString("applicationId");//获取页面传过来的申请单编号
	getZhiMaMysqlData();
})
function cleanData(){
	$("#zhiMaScore").html("");
	$("#bizCode").html("");
	$("#zhiMaType").html("");
	$("#zhiMaLevel").html("");
}

function getZhiMaMysqlData(){
	cleanData();
	
	$.ajax({
		url : getCtxPath() + "/backend/zhimaInfo/getZhiMaMysqlInfoByApplicationId.do",
		method : "post",
		dataType : "json",
		data : {
			applicationId : applicationId,
		},
		success : function(data) {
			
				var obj=data.data.zhiMaIndustryDetailsInfo;
				
				if(obj!=null){
					//芝麻分
					
					$("#zhiMaScore").html(obj.application.zhiMaScore);
					//行业类型
					var html='';
					if(obj.bizCode=='AA'){
						html='金融信贷类';
					}else if(obj.bizCode=='AB'){
						html='公检法';
					}else if(obj.bizCode=='AC'){
						html='支付行业';
					}else if(obj.bizCode=='AD'){
						html='出行行业';
					}else if(obj.bizCode=='AE'){
						html='酒店行业';
					}else if(obj.bizCode=='AF'){
						html='电商行业';
					}else if(obj.bizCode=='AG'){
						html='租房行业';
					}else if(obj.bizCode=='AH'){
						html='运营商行业';
					}else if(obj.bizCode=='AI'){
						html='保险行业';
					}else if(obj.bizCode=='AK'){
						html='租赁行业';
					}
					$("#bizCode").html(html);
					//风险类型
					var html2='';
					if(obj.zhiMaType=='AA001'){
						html2='逾期未还款';
					}else if(obj.zhiMaType=='AA002'){
						html2='套现';
					}else if(obj.zhiMaType=='AB001'){
						html2='被执行人';
					}else if(obj.zhiMaType=='AC001'){
						html2='盗卡者/盗卡者同伙';
					}else if(obj.zhiMaType=='AC002'){
						html2='欺诈者/欺诈同伙';
					}else if(obj.zhiMaType=='AC003'){
						html2='盗用操作/盗用者同伙';
					}else if(obj.zhiMaType=='AC004'){
						html2='盗用支出/盗用者同伙';
					}else if(obj.zhiMaType=='AC005'){
						html2='骗赔';
					}else if(obj.zhiMaType=='AC007'){
						html2='案件库黑名单';
					}else if(obj.zhiMaType=='AD001'){
						html2='汽车租赁逾期未付款';
					}else if(obj.zhiMaType=='AD002'){
						html2='汽车租赁逾期未还车';
					}else if(obj.zhiMaType=='AD003'){
						html2='汽车租赁违章逾期未付款';
					}else if(obj.zhiMaType=='AD004'){
						html2='汽车租赁其他逾期未付款';
					}else if(obj.zhiMaType=='AD005'){
						html2='单车租赁逾期未付款';
					}else if(obj.zhiMaType=='AD006'){
						html2='单车租赁逾期未还车';
					}else if(obj.zhiMaType=='AD007'){
						html2='交通工具乘用费逾期未付款';
					}else if(obj.zhiMaType=='AD008'){
						html2='路桥费逾期未付款';
					}else if(obj.zhiMaType=='AD009'){
						html2='停车费逾期未付款';
					}else if(obj.zhiMaType=='AD010'){
						html2='相关违约';
					}else if(obj.zhiMaType=='AE001'){
						html2='逾期未付款';
					}else if(obj.zhiMaType=='AF001'){
						html2='虚假交易';
					}else if(obj.zhiMaType=='AF002'){
						html2='恶意差评';
					}else if(obj.zhiMaType=='AF003'){
						html2='涉嫌售假';
					}else if(obj.zhiMaType=='AF004'){
						html2='卖家套现';
					}else if(obj.zhiMaType=='AF005'){
						html2='逾期未付款';
					}else if(obj.zhiMaType=='AG001'){
						html2='房租逾期未付款';
					}else if(obj.zhiMaType=='AG002'){
						html2='杂费逾期未付款';
					}else if(obj.zhiMaType=='AG003'){
						html2='其他违约未付款';
					}else if(obj.zhiMaType=='AG004'){
						html2='租客其他违约';
					}else if(obj.zhiMaType=='AG005'){
						html2='非正常解约';
					}else if(obj.zhiMaType=='AH001'){
						html2='逾期未付款';
					}else if(obj.zhiMaType=='AI003'){
						html2='保证保险出险';
					}else if(obj.zhiMaType=='AK001'){
						html2='逾期未付款';
					}else if(obj.zhiMaType=='AK002'){
						html2='逾期未归还';
					}
					$("#zhiMaType").html(html2);
					//芝麻风险等级
					var html3='';
					if(obj.zhiMaLevel=='1'){
						html3='低风险';
					}else if(obj.zhiMaLevel=='2'){
						html3='中风险';
					}else if(obj.zhiMaLevel=='3'){
						html3='高风险';
					}
					$("#zhiMaLevel").html(html3);
				}
		},
		error : function() {
			alert("获取芝麻风险报告失败,请尝试重新获取！");
		}
	});
}

