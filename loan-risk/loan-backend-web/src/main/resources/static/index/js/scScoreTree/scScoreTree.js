			layui.config({
				base: '../../js/' 
			});
		
	// zTree需要的json格式
// 	[
// 		{ id:1, pId:0, name:"随意勾选 1", open:true},
// 		{ id:11, pId:1, name:"随意勾选 1-1", open:true},
// 		{ id:111, pId:11, name:"随意勾选 1-1-1"},
// 		{ id:112, pId:11, name:"随意勾选 1-1-2"},
// 		{ id:12, pId:1, name:"随意勾选 1-2", open:true},
// 		{ id:121, pId:12, name:"随意勾选 1-2-1"},
// 		{ id:122, pId:12, name:"随意勾选 1-2-2"},
// 		{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
// 		{ id:21, pId:2, name:"随意勾选 2-1"},
// 		{ id:22, pId:2, name:"随意勾选 2-2", open:true},
// 		{ id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
// 		{ id:222, pId:22, name:"随意勾选 2-2-2"},
// 		{ id:23, pId:2, name:"随意勾选 2-3"}
// 	]
	
		/**全局变量	**/
		// 存放所有选中的节点值
//		var noteVal = "";
//		var roleId = getQueryString("roleId");
		// 菜单名称
//		var menuNames = "角色管理";
 				  
		// zTree参数配置，可参考官方api： http://www.treejs.cn/v3/api.php
			
	var scoreTplId = getQueryString("scoreTplId");
 		var setting = {
			check: {
				enable: false
			},
			data: {
				simpleData: {
					enable: true
				}
			}
			,
			callback: { // 回调函数
				onClick: zTreeOnClick   // 点击事件
			}
		};
	
		  
	// 初始化加载树
		$(function(){
			$("#getScScore").hide();//隐藏小类
			$.ajax({
				url: getCtxPath() + "/backend/scScoreSmall/getScScoreTree.do",
				method: "post", 
				dateType: "json",
				data: {
					scoreTplId: scoreTplId
				},
				success: function(obj){
					$.fn.zTree.init($("#treeDemo"), setting, obj);
				},
				error: function(){
					 layer.msg("网络请求异常,请常识重新登录");//提示信息
				},
				complete: function(){
					
				}
			});
		})
		 
var id="";
 function zTreeOnClick(event, treeId, treeNode) {
//			  alert(treeNode.tId + ", "+treeNode.id + ", "+treeNode.pId+ ", "+ treeNode.name);
			  id=treeNode.id;
    		   if(id.indexOf("small") >= 0){//点击小类节点
    				  $("#getScScore").show();//显示小类
    				  $("#getScScoreBig").hide();//隐藏大类
    				  id =id.replace("small","");
    				  getScScore(id,"","","","");
				}else if(id.indexOf("big")>= 0){//点击大类节点，显示下属所有小类
					$("#getScScore").show();//显示小类
   				    $("#getScScoreBig").hide();//隐藏大类
   				   var scoreBigId =id.replace("big","");
   				getScScore("",scoreBigId,"",scoreTplId);
				}else  if(id=="000"){//点击根节点，显示所有大类
					$("#getScScore").hide();//隐藏小类
   				    $("#getScScoreBig").show();//显示大类
   				 getScScoreBig("","","")
				}else  {//点击第三方节点，显示下属所有大类
					$("#getScScore").hide();//隐藏小类
   				    $("#getScScoreBig").show();//显示大类
					var creditType=id;
					getScScoreBig("",creditType,"")
				}
 };
		
	
	function getScScoreBig(id,creditType,_pageIndex){
		//局部刷新回调获取参数,
		var isFirst = 1;
		var pageIndex = _pageIndex || $("#paged").find("em").eq(1).html()||1;
		
		layui.use(['paging', 'layer', 'form', 'element'], function() {
			var $ = layui.jquery,
				paging = layui.paging(),
				layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
				layer = layui.layer, //获取当前窗口的layer对象
				form = layui.form(),
				element = layui.element();
				form.render(); //重新渲染

			// 页面初始化加载		start
            paging.init({
                openWait: true,
				url: getCtxPath() + "/backend/scScoreBig/getAllScScoreBig.do?v=" + new Date().getTime(),
				elem: '#content', //内容容器
				params: { //发送到服务端的参数
					t: Math.random(),
					scoreBigId:id,
 					creditType:creditType
				},
				type: 'post',
				tempElem: '#tpl', //模块容器
				pageConfig: { //分页参数配置
					elem: '#paged', //分页容器
//					pageSize: 10 //分页大小，当前页面显示的条数
				}, 
				success: function() { //渲染成功的回调
					 if (pageIndex != '' && pageIndex != '1' && isFirst === 1) {
							isFirst = 0;
							var totalCount = $("#count_page_number").html()||0;
							if( (parseInt(pageIndex)-1) * 10 >= parseInt(totalCount) ){
								pageIndex = 1;
							}
							paging.get({
	                            pageIndex: pageIndex,
	                            pageSize: 10
	                        });
						}
					},
				fail: function(msg) { //获取数据失败的回调
					layer.msg("获取数据失败！");
				},
				complate: function() { //完成的回调
					//绑定所有'编辑'按钮事件						
					$('#content').children('tr').each(function() {
						var $that = $(this);
						$that.children('td:last-child').children('a[data-opt=edit]').on('click', function() {
							 
							layer.open({
								  type: 2,
								  title: '编辑',// 设置false表示不显示
								  closeBtn: 1, //不显示关闭按钮
								  shade: 0.4, //遮罩透明度
								  area: ['840px', '550px'],
								  skin: 'layui-layer-rim', //加上边框
								  fixed: false, //不固定
								  maxmin: true, // 允许屏幕最小化
								  anim: 2,
								  content: ['../scScoreBig/scScoreBig_add.html?id='+$(this).data('id')+"&typeId=1"], //iframe的url，no代表不显示滚动条
								});
						});

					});
					
					//绑定所有'新增小类'按钮事件						
					$('#content').children('tr').each(function() {
						var $that = $(this);
						$that.children('td:last-child').children('a[data-opt=add]').on('click', function() {
							 
							layer.open({
								  type: 2,
								  title: '新增小类',// 设置false表示不显示
								  closeBtn: 1, //不显示关闭按钮
								  shade: 0.4, //遮罩透明度
								  area: ['840px', '550px'],
								  skin: 'layui-layer-rim', //加上边框
								  fixed: false, //不固定
								  maxmin: true, // 允许屏幕最小化
								  anim: 2,
								  content: ['../scScoreSmall/scScoreSmall_add.html?bigId='+$(this).data('id')], //iframe的url，no代表不显示滚动条
								});
						});

					});
					
					//查看大类下面所有小类						
					$('#content').children('tr').each(function() {
						var $that = $(this);
						$that.children('td:last-child').children('a[data-opt=get]').on('click', function() {
							 
							layer.open({
								  type: 2,
								  title: '查看小类',// 设置false表示不显示
								  closeBtn: 1, //不显示关闭按钮
								  shade: 0.4, //遮罩透明度
								  area: ['940px', '550px'],
								  skin: 'layui-layer-rim', //加上边框
								  fixed: false, //不固定
								  maxmin: true, // 允许屏幕最小化
								  anim: 2,
								  content: ['../scScoreSmall/scScoreSmallList.html?scoreBigId='+$(this).data('id')], //iframe的url，no代表不显示滚动条
								});
						});

					});
				},
			});
            
		});
	 	 
	 }
		
	
	
	function getScScore(id,scoreBigId,_pageIndex,scoreTplId){
		//局部刷新回调获取参数,
		var isFirst = 1;
		var pageIndex = _pageIndex || $("#paged").find("em").eq(1).html()||1;
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
				url: getCtxPath() + "/backend/scScoreSmall/getAllScScoreSmallUnion.do?v=" + new Date().getTime(),
				elem: '#content1', //内容容器
				params: { //发送到服务端的参数
					t: Math.random(),
					scoreBigId:scoreBigId,
					scoreSmallId:id,
					scoreTplId:scoreTplId
				},
				type: 'post',
				tempElem: '#tpll', //模块容器
				pageConfig: { //分页参数配置
					elem: '#paged', //分页容器
//					pageSize: 10 //分页大小，当前页面显示的条数
				}, 
				success: function() { //渲染成功的回调
					$("#count_page_number1").html($("#count_page_number").html());//显示总条数
//					 if (pageIndex != '' && pageIndex != '1' && isFirst === 1) {
//							isFirst = 0;
//							var totalCount = $("#count_page_number").html()||0;
//							if( (parseInt(pageIndex)-1) * 10 >= parseInt(totalCount) ){
//								pageIndex = 1;
//							}
//							paging.get({
//	                            pageIndex: pageIndex,
//	                            pageSize: 10
//	                        });
//						}
					},
				fail: function(msg) { //获取数据失败的回调
					layer.msg("获取数据失败！");
				},
				complate: function() { //完成的回调
					//绑定所有'编辑'按钮事件						
					$('#content1').children('tr').each(function() {
						var $that = $(this);
						$that.children('td:last-child').children('a[data-opt=edit]').on('click', function() {
							 
							layer.open({
								  type: 2,
								  title: '编辑',// 设置false表示不显示
								  closeBtn: 1, //不显示关闭按钮
								  shade: 0.4, //遮罩透明度
								  area: ['840px', '550px'],
								  skin: 'layui-layer-rim', //加上边框
								  fixed: false, //不固定
								  maxmin: true, // 允许屏幕最小化
								  anim: 2,
								  content: ['../scScoreSmall/scScoreSmall_add.html?id='+$(this).data('id') + "&typeId=1"], //iframe的url，no代表不显示滚动条
								});
						});
						
							
					});
					
					var menuNames = '信用评分项规则配置';
					//绑定所有'修改状态'按钮事件						
					$('#content1').children('tr').each(function() {
						var $that = $(this);
						$that.children('td:last-child').children('a[data-opt=update]').on('click', function() {
							var state= $(this).data('state');
							var scoreTplId= $(this).data('tplid');
								var str="有效";
							if(state=='01'){
								str="无效";
								state='02';
							}else{
									state='01';
							}
								if(confirm('您确定将状态修改为【'+str+'】吗？')){ //只有当点击confirm框的确定时，该层才会关闭
							// ajax 请求后台
								$.ajax({
									url : getCtxPath() + "/backend/scScoreSmall/saveScScoreSmall.do",
									method : 'post',
									dataType : 'json',
									data: {
										scoreSmallId: $(this).data('id'), // 传到后台的参数，子窗口的form表单
										state:state,
										version:$(this).data('version'),
										scoreTplId: scoreTplId,
										menuNames : menuNames,//信用评分项规则配置状态
										functionNames: ($(this).data('id') != ''  ? "修改信用评分项状态" : "新增信用评分项") // 按钮功能名称 不为空修改 为空 新增
										},
									success : function(data) {
										if(data.code == "200"){
											layerTips.msg(data.message);// 提示信息
										}else{
											layerTips.msg(data.message);// 提示信息
										}
									},
									error: function(){
										parent.layer.alert("请求网络异常，请尝试重新登录！");
									},
									complete: function(){
										getScScore(); //局部刷新
									}
								});
							  }
							  return false; 
						});

					});
					
				},
			});
            // 初始化加载init     end!
			
		});
	 	 
	
	}
		 