layui.config({
	base : '../../js/'
});

layui.use(['paging','tree','upload','layer', 'form', 'element'], function(){
	  var layer = layui.layer
	  ,paging = layui.paging()
	  ,form = layui.form()
	  ,element = layui.element();
	  form.render(); //刷新全部
	  
	  var applyId=getQueryString("applyId");
	  paging.init({
          openWait: true,
			url: hostIp + "/checkCustomer/findAllExtends.do?v=" + new Date().getTime(),// 请求服务器的url
			elem: '#content', //内容容器
			pageConfig: { //分页参数配置
				elem: '#paged', //分页容器
				pageSize: 10 //分页大小，当前页面显示的条数
			},
			params: { //发送到服务端的参数
				customerCheckApplyId : applyId
			},
			type: 'post',
			tempElem: '#tpl', //模块容器
			success: function() { //渲染成功的回调
			},
			fail: function(msg) { //获取数据失败的回调
				layer.msg("获取数据失败！");
				
			},
			complate: function() {
				/*$('#content').children('tr').each(function() {
					var $that = $(this);
					$that.children('td').children('a[data-opt=find]').on('click', function() {
						var href = getCtxPath() + "/index/views/cuCustomerCheckApply/cuCustomerCheckApply.html?codeId="+$(this).attr("data-codeId");
						var data = {
							"title": "详细信息-"+$(this).attr("data-codeId"),
							"icon": "&#xe60c;",
							"href": href
						}
						window.parent.topTab.tabAdd(data);
					});
				});*/
				
				
				$('#content').children('tr').each(function() {
					var $that = $(this);
					$that.children('td:nth-child(4)').children('a[data-opt=find]').on('click', function() {
						var info = $(this).data('id');
//						var taskType = $(this).data('tasktype');
						// 拼接成redis中的Key
//						var info = id + "_" + taskType;
						//自定页
						layer.open({
						  type: 1,
						  title: '查看异常详细信息',// 设置false表示不显示
						  area: ['840px', '550px'],
//						  skin: 'layui-layer-demo', //样式类名
						  closeBtn: 0, //不显示关闭按钮
						  anim: 2,
						  shadeClose: true, //开启遮罩关闭
						  content: info
						});			
						 
						
						// 多请求一次方法
//						layer.open({
//							  type: 2,
//							  title: '查看异常详细信息',// 设置false表示不显示
//							  closeBtn: 1, //不显示关闭按钮
//							  shade: 0.4, //遮罩透明度
//							  area: ['840px', '550px'],
////							  skin: 'layui-layer-rim', //加上边框
//							  btn: ['取消'],
//							  fixed: false, //不固定
//							  maxmin: true, // 允许屏幕最小化
////									  offset: 'rb', //右下角弹出
////									  time: 2000, //2秒后自动关闭
//							  anim: 2,
//							  content: ['cuCustomerCheckApplyInfoList_info.html?info='+info], //iframe的url，no代表不显示滚动条
//							});
					});
				});
				
			}
		});
});

