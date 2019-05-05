	layui.config({
		base: '../../js/' 
	});

	layui.use([ 'paging', 'layer', 'form', 'element' ],
		function() {
			var $ = layui.jquery, paging = layui.paging(), layerTips = parent.layer === undefined ? layui.layer: parent.layer,
			layer = layui.layer,
			form = layui.form(), element = layui.element();
	});

	$(function() {
		var customerId = getQueryString("id");
		var appName = getQueryString("appName");
		//客户app应用列表查询
		$.ajax({
			url :getCtxPath() + "/backend/approve/appInfo.do",
			method: "post",
			dataType:"json",
			data: {
				customerId:customerId,
				appName:appName
			},
			success: function(data){
				console.log(data);
				var list = data.list.data;
				var table = $("#appyylb").html();
				var html = table + "<tr style='width: 100px;'>" +
				"<th>APP平台</th><th>APP应用客户编号</th><th>应用程序名称</th><th>应用程序所对应的包名</th>" +
				"<th>应用程序的versionName</th><th>应用程序的versionCode</th><th>是否是系统应用</th>" +
				"<th>应用最近一次更新时间</th><th>创建时间</th><th>修改时间</th></tr>";
				if(list != null){
					$.each(list, function(k, v){
						var isSystemApp = v.isSystemApp;
						if (isSystemApp == 1) {
							isSystemApp = "是";
						} else {
							isSystemApp = "否";
						}
						var appName = v.appName;
						if(appName == null || appName == 1){
							appName='洪澄';
						} else if(appName == 2){
							appName='速贷';
						};
						var createTime = v.createTime;
						if (createTime != null) {
							createTime = createTime.substring(0,19);
						}
						var modifyTime = v.modifyTime;
						if (modifyTime != null) {
							modifyTime = modifyTime.substring(0,19)
						}
						html += "<tr><td>" + appName + "</td><td>" + v.customerId + "</td><td>" + v.launcherName + "</td><td>" + v.packageName + "</td>" + 
						"<td>" + v.versionName + "</td><td>" + v.versionCode + "</td><td>" + isSystemApp + "</td><td>" + v.lastUpdateTime + "</td>" +
						"<td>" + createTime + "</td><td>" + modifyTime + "</td></tr>";
					});
				}
				$("#appyy").html(html);
			},
			error: function(){
				layer.msg("错误" , {icon: 2});//提示信息
			}
		});
	});
	/*// 初始化
	$(function(){
		var customerId = getQueryString("id");
		var appName = getQueryString("appName");
		 console.log(customerId);
		layui.use(['paging', 'layer', 'form', 'element'], function() {
			var $ = layui.jquery,
				paging = layui.paging(),
				layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
				layer = layui.layer, //获取当前窗口的layer对象
				form = layui.form(),
				element = layui.element();

			// 页面初始化加载		start
            paging.init({
                openWait: true,
				url:getCtxPath() + "/backend/cuAppInfo/getEntityById.do?v=" + new Date().getTime(),// 请求服务器的url
				elem: '#content', //内容容器
				params: { //发送到服务端的参数
					customerId : customerId,
					appName  : appName
				},
				type: 'post',
				tempElem: '#tpl', //模块容器
				pageConfig: { //分页参数配置
					elem: '#paged', //分页容器
					pageSize: 10 //分页大小，当前页面显示的条数
				},
				success: function() { //渲染成功的回调
				},
				fail: function(msg) { //获取数据失败的回调
					layer.msg("获取数据失败！");
				}  
		});
	}); 
	}); */
