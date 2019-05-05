layui.config({
	base : '../../js/'
});

layui.use([ 'paging', 'layer', 'form', 'element' ],
				function() {
					var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer, // 获取父窗口的layer对象
					layer = layui.layer, // 获取当前窗口的layer对象
					form = layui.form(), element = layui.element(); 
					var hrefAttr = ["cusInfo.html","tdFqz.html","bqsReport.html","jgInfo.html","xczxInfo.html","txyFqz.html","yjfBlackList.html"];
					var platformCustomerId = getQueryString("id");//获取页面传过来的平台用户编号
					var customerId = getQueryString("customerId"); 
					var applicationId = getQueryString("applicationId");//获取页面传过来的申请单编号  
 					$("div.layui-tab-content div").eq(0).find("iframe").prop("src",hrefAttr[0]+"?id="+platformCustomerId+"&applicationId="+applicationId+"&customerId="+customerId);
					element.on('tab(filter)', function(data){
						  console.log(data.index); //得到当前Tab的所在下标
							var src = hrefAttr[data.index];
							src = src+"?id="+platformCustomerId+"&applicationId="+applicationId+"&customerId="+customerId;
							var $iframe = $("div.layui-tab-content div").eq(data.index).find("iframe");
							$iframe.prop("src",src);
						});
					
					$('#returnPrePage').on('click', function returnPrePage() {
						var preUrl = document.referrer.split('?')[0];
						var localUrl = document.location.href.split('?');
						if (localUrl.length > 1) {
							window.location.href = preUrl + '?' + localUrl[1];
						} else {
							window.location.href = document.referrer;
						}
					});
				});

