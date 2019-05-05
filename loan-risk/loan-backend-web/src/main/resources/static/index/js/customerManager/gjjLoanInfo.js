layui.config({
	base : '../../js/'
});

$(function() {
	var platformCustomerId = getQueryString("id");//获取页面传过来的平台用户编号
	var applicationId = getQueryString("applicationId");//获取页面传过来的申请单编号
	var pageSize = 10; //每页出现的数据量
	var count = 0; // 总页数
	var loanInfoId = '';
	getOrderList();
	
	layui.use('element', function(){
		  var element = layui.element();
		  
		  //一些事件监听
		  element.on('tab(demo)', function(data){
			  getCount(0);
		  });
		});
	
	// 分页
	function paging() {
		layui.use(['laypage', 'layer'], function() {
			  var laypage = layui.laypage
			  ,layer = layui.layer;
			  var html = '';
			  //模拟渲染
			  var render = function(curr){
				  getRepayList(curr);
			  };

			  //调用分页
			  laypage({
			    cont: 'paged3'
			    ,pages: Math.ceil(count/pageSize) //得到总页数
			    ,jump: function(obj){
			    	render(obj.curr);
			    }
			  });
		});
	}
	
	// 获取数据
	function getRepayList(pageIndex) {
		$.ajax({
			url : getCtxPath() + "/backend/MhGjj/getRepayRecord.do",
			method : "post",
			dataType : "json",
			data : {
				loanInfoId : loanInfoId,
				pageIndex : pageIndex,
				pageSize : pageSize
			},
			success : function(data) {
				var arr = [];
				layui.each(data.list, function(index, item){
					arr.push('<tr>');
				      arr.push('<td>'+ item.accountingDate + '</td>');
				      arr.push('<td>'+ item.repayAmount + '</td>');
				      arr.push('<td>'+ item.repayCapital + '</td>');
				      arr.push('<td>'+ item.repayInterest + '</td>');
				      arr.push('<td>'+ item.repayPenalty + '</td>');
				      arr.push('<td>'+ item.repayDate + '</td>');
				      arr.push('</tr>');
				      
				    });
				$('#content3').html(arr.join(''));
			},
			error : function() {
				alert("获取用户信息失败,请尝试重新获取！");
			}
		});
	}
	
	// 获取数据
	function getOrderList() {
		$.ajax({
			url : getCtxPath() + "/backend/MhGjj/getLoanInfo.do",
			method : "post",
			dataType : "json",
			data : {
				platformCustomerId : platformCustomerId,
				applicationNumber : applicationId,
			},
			success : function(data) {
				var arr = [];
				arr.push('<div class="layui-tab layui-tab-card" lay-filter="demo"><ul class="layui-tab-title">');
				layui.each(data.list, function(index, item){
				      if (index == 0) {
				    	  arr.push('<li class="layui-this" id="' + item.loanInfoId +'">'+ item.bankAccountName + '-' + item.delegateBank +'</li>');
				      } else {
				    	  arr.push('<li id="' + item.loanInfoId +'">'+ item.bankAccountName + '-' + item.delegateBank +'</li>');
				      }
				    });
				arr.push('</ul><div class="layui-tab-content">');
				layui.each(data.list, function(index, item){
				      if (index == 0) {
				    	  arr.push('<div class="layui-tab-item layui-show">');
				      } else {
				    	  arr.push('<div class="layui-tab-item">');
				      }
				      var repayType = '';
				      arr.push('<table class="layui-table">');
				      arr.push('<tr><th>合同号</th><td><font>' + item.contactNo + '</font></td><th>借款状态</th><td><font>' + item.status 
				    		  + '</font></td><th>委托银行</th><td><font>'  + item.delegateBank + '</font></td></tr>');
				      arr.push('<tr><th>借贷金额（元）</th><td><font>' + item.loanAmount + '</font></td><th>借款期限（年）</th><td><font>' + item.periods 
				    		  + '</font></td><th>借款放款日</th><td><font>'  + item.startDate + '</font></td></tr>');
				      arr.push('<tr><th>借款到期日</th><td><font>' + item.endDate + '</font></td><th>约定扣款日（每月）</th><td><font>' + item.deductDay 
				    		  + '</font></td><th>还款方式</th><td><font>'  + item.repayTypeDesc + '</font></td></tr>');
				      arr.push('<tr><th>借款利率（%）</th><td><font>' + item.loanInterestPercent + '</font></td><th>罚息利率（%）</th><td><font>' + item.penaltyInterestPercent 
				    		  + '</font></td><th>签约日期</th><td><font>'  + item.signDate + '</font></td></tr>');
				      arr.push('<tr><th>购房地址</th><td colspan="5"><font>' + item.houseAddress + '</font></td></tr></table></div>');
				      
				    });
				arr.push('</div></div>');
				$('#content2').html(arr.join(''));
				getCount(0);
			},
			error : function() {
				alert("获取用户信息失败,请尝试重新获取！");
			}
		});
	}
	
	// 获取数据总量
	function getCount(pageIndex) {
		loanInfoId = $('.layui-this').attr('id');
		$.ajax({
			url : getCtxPath() + "/backend/MhGjj/getRepayRecord.do",
			method : "post",
			dataType : "json",
			data : {
				loanInfoId : loanInfoId,
				pageIndex : pageIndex,
				pageSize : pageSize
			},
			success : function(data) {
				count = data.count;
				$('#count_page_number3').text(count);
				paging();
			},
			error : function() {
				alert("获取用户信息失败,请尝试重新获取！");
			}
		});
	}
});
