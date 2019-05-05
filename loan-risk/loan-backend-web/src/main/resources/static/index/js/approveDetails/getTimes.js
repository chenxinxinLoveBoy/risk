	layui.config({
				base: '../../js/' 
			}); 
	var map;
	// 回显
	$(function(){ 
		//我们强烈推荐你在代码最外层把需要用到的模块先加载
		layui.use(['layer', 'form', 'element'], function(){
			 var layer = layui.layer
			  ,form = layui.form()
			  ,element = layui.element();
			  form.render(); //刷新全部
			  var customerId = getQueryString("customerId");
			  var appName = getQueryString("appName");
			  console.log("customerId"+customerId+"appName"+appName);
		  		$.ajax({
					url :getCtxPath() + "/backend/application/getTimes.do?v=" + new Date().getTime(),
					method: "post",
					dataType:"json",
					data: {
						customerId : customerId,
						appName : appName
					},
					success: function(data){   
						var info = data.list.data;
						var tt=JSON.parse(info); 
						console.log(tt.list);  
						var xx = tt.list;
						console.log(xx);  
						if(xx != null){ 
							for (var i = 0; i < xx.length; i++) {
								var obi = xx[i]; 
								console.log(obi);  
								if(obi.type=='1'){
									$("#lb").html(obi.spendTime);
								}
								if(obi.type=='2'){
									$("#zl").html(obi.spendTime);
								}
								if( obi.type=='3'){  
									$("#fl").html(obi.spendTime);
								}
								if(obi.type=='4'){
									$("#xi").html(obi.spendTime);
								}
							} 
								 
						}
					}, 
					complete: function(){
						form.render(); //刷新全部
					} 
				});
	   }); 
	});
 

// 关闭
function closeIfrname(){
	var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(frameindex);
}
