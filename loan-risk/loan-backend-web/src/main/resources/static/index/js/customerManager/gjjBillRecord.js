layui.config({
	base : '../../js/'
});

$(function() {
	var platformCustomerId = getQueryString("id");//获取页面传过来的平台用户编号
	var applicationId = getQueryString("applicationId");//获取页面传过来的申请单编号
	var pageSize = 10; //每页出现的数据量
	var count = 0; // 总页数

	getCount(0,pageSize,platformCustomerId,applicationId);
	
	// 分页
	function paging() {
		layui.use(['laypage', 'layer'], function() {
			  var laypage = layui.laypage
			  ,layer = layui.layer;
			  var html = '';
			  //模拟渲染
			  var render = function(curr){
				  getList(curr,pageSize,platformCustomerId,applicationId);
			  };

			  //调用分页
			  laypage({
			    cont: 'paged1'
			    ,pages: Math.ceil(count/pageSize) //得到总页数
			    ,jump: function(obj){
			    	render(obj.curr);
			    }
			  });
		});
	}
	
	// 获取数据
	function getList(pageIndex, pageSize, platformCustomerId, applicationId) {
		$.ajax({
			url : getCtxPath() + "/backend/MhGjj/getBillRecord.do",
			method : "post",
			dataType : "json",
			data : {
				platformCustomerId : platformCustomerId,
				applicationNumber : applicationId,
				pageIndex : pageIndex,
				pageSize : pageSize
			},
			success : function(data) {
				var arr = [];
				layui.each(data.list, function(index, item){
				      arr.push('<tr>');
				      arr.push('<td>'+ item.dealTime + '</td>');
				      arr.push('<td>'+ item.desc + '</td>');
				      arr.push('<td>'+ item.income + '</td>');
				      arr.push('<td>'+ item.outcome + '</td>');
				      arr.push('<td>'+ item.balance + '</td>');
				      arr.push('<td>'+ item.corpName + '</td>');
				      arr.push('</tr>');
				    });
				$('#content1').html(arr.join(''));
			},
			error : function() {
				alert("获取用户信息失败,请尝试重新获取！");
			}
		});
	}
	
	// 获取数据总量
	function getCount(pageIndex, pageSize, platformCustomerId, applicationId) {
		$.ajax({
			url : getCtxPath() + "/backend/MhGjj/getBillRecord.do",
			method : "post",
			dataType : "json",
			data : {
				platformCustomerId : platformCustomerId,
				applicationNumber : applicationId,
				pageIndex : pageIndex,
				pageSize : pageSize
			},
			success : function(data) {
				count = data.count;
				$('#count_page_number1').text(count);
				paging();
			},
			error : function() {
				alert("获取用户信息失败,请尝试重新获取！");
			}
		});
	}
});
