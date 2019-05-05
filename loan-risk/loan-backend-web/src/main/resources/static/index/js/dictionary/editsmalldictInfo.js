			layui.config({
				base: '../../js/'
			});
		
			$(function(){
				var id = getQueryString("id");  
				var sid = getQueryString("did"); 
				var smid = $("#smsid").val(); 
				var edit = getQueryString("edit"); 
				// 回显
				if(id !='' && id != null && edit != ''){    
					$.ajax({
						url : hostIp+ "/scDicSmallist/getObjectById.do",
						method: "post",
						dataType:"json",
						data: {
							id : id
						},
						success: function(data){
							$("#dicSmallCode").val(data.list.scsObject.dicSmallCode); // 数据字典小类编号
							$("#dicSmallValue").val(data.list.scsObject.dicSmallValue);//数据字典小类中文名称
							$("#dicSmallStaues").val(data.list.scsObject.dicSmallStaues);//数据字典小类使用状态（1-正常、2-停用）	
							$("#smsid").val(data.list.scsObject.dictBigId); 
						},
						error: function(){
							parent.layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
						},
						complete: function(){ 
						} 
					});	
				};
			});     
			
			$('#content').children('tr').each(function() {
				var $that = $(this)  
				$that.children('td:last-child').children('a[data-opt=find]').on('click', function() {
					layer.open({
						  type: 2,
						  title: '详情',// 设置false表示不显示
						  closeBtn: 1, //不显示关闭按钮
						  shade: 0.4, //遮罩透明度
						  area: ['840px', '615px'],
						  skin: 'layui-layer-rim', //加上边框
						  fixed: false, //不固定
						  maxmin: true, // 允许屏幕最小化
//									  offset: 'rb', //右下角弹出
//									  time: 2000, //2秒后自动关闭
						  anim: 2,
						  content: ['editsmalldictInfo.html?find=1&id='+$(this).data('id')],
						});
				});

			});	
			
			 			 
			// 关闭
			function closeIfrname(){
				var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(frameindex);
			};
			
			var menuNames ='数据字典小类';
			// 保存smsid
			function save(){  
				var id = getQueryString("id"); 
				var smsid = $("#smsid").val();  
				var sid = getQueryString("sid");
			 
				var dicSmallCode = $("#dicSmallCode").val().trim();// 数据字典小类编号
				var dicSmallValue = $("#dicSmallValue").val().trim();// 数据字典小类中文名称
				var dicSmallStaues = $("#dicSmallStaues").val().trim();// 数据字典小类使用状态 
				if(id === '' || id === undefined){
					smsid = sid;
				}
				if(dicSmallCode === '' || dicSmallCode === undefined){
					parent.layer.msg("小类字典编号不能为空" , {icon: 2});
					return ;
				}
				
				if(dicSmallValue == '' || dicSmallValue  === undefined){
					parent.layer.msg("小类字典名称不能为空" , {icon: 2});
					return ;
				}
				
				if(dicSmallStaues === '' || dicSmallStaues  === undefined){
					parent.layer.msg("小类字典使用状态不能为空" , {icon: 2});
					return ;
				}
				 
				var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引 
				$.ajax({
					url : hostIp+ "/scDicSmallist/save.do",
					method : 'post',
					dataType : 'json',
					data: {  
						id : id,
						smsid : smsid,
						dicSmallCode: dicSmallCode, // 传到后台的参数，子窗口的form表单
						dicSmallValue: dicSmallValue,  
						dicSmallStaues: dicSmallStaues,
						menuNames : menuNames,//数据字典小类
						functionNames: (smsid != ''  ? "修改数据字典小类" : "新增数据字典小类") // 按钮功能名称 不为空修改 为空 新增
					},
					success : function(data)  { 
							if('102' == data.code ){
								parent.layer.msg("字典编号已存在！请重新输入！" , {icon: 2});//提示信息 
							} else{
								alert("小类字典保存成功！") 
								parent.location.href = hostIpHtml+ "/index/views/dictionary/dictInfoList.html?edit=1&id="+smsid; 
							}
								 
						},
					  
					error: function(){
						parent.layer.msg("网络请求异常，请尝试重新登陆！" , {icon: 2});//提示信息 
					},
					complete: function(){ 
						location.reload();
					} 
				}); 
			}
			 