			layui.config({
				base: '../../js/'
			});
		
			$(function(){
				var sid = getQueryString("sid"); 
				var sname = decodeURI(getQueryString("sname")); 
				var remark = decodeURI(getQueryString("remark")); 
				// 回显
				$("#smallName").val(sname); // 渠道小类名称
				$("#remark").val(remark);//备注
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
						  content: ['editSmallChannelInfo.html?find=1&id='+$(this).data('id')],
						});
				});

			});	
			
			 			 
			// 关闭
			function closeIfrname(){
				var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(frameindex);
			};
			
			// 保存sid
			function save(){  
				var bid = getQueryString("bid");
				var sid = getQueryString("sid");
				var smallName = $("#smallName").val().trim();// 数据字典小类中文名称
				var remark = $("#remark").val().trim();// 数据字典小类使用状态 
				if(smallName == '' || smallName  === undefined){
					parent.layer.msg("渠道小类名称不能为空" , {icon: 2});
					return ;
				}
				var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引 
				var saveOrUpdate= sid != ''? 'forUpdate':'forSave';
				//var saveOrUpdate= 'ssss';
				$.ajax({
					url : hostIp+ "/appSmallChannel/saveOrUpdate.do",
					method : 'post',
					dataType : 'json',
					data: {  
						channelBigId : bid,
						channelSmallId:sid,
						channelSmallName: smallName, 
						remark: remark,
						saveOrUpdate:saveOrUpdate
					},
					success : function(data)  { 
							if('102' == data.code ){
								parent.layer.msg("渠道小类已存在！请重新输入！" , {icon: 2});//提示信息 
							}else if(data.code == '-200'){
								parent.layer.msg("渠道小类已存在！请重新输入！" , {icon: 2});//提示信息
							}else if(data.code == '2001'){
								parent.layer.msg("渠道小类保存成功！", {icon: 1});
								parent.location.href = hostIpHtml+ "/index/views/appChannel/channelInfoList.html?edit=1&id="+bid;  
							}else if(data.code == '4001'){
								parent.layer.msg("渠道小类修改成功！", {icon: 1});
								parent.location.href = hostIpHtml+ "/index/views/appChannel/channelInfoList.html?edit=1&id="+bid; 
							} else if(data.code == '4002'){
								parent.layer.msg("渠道小类修改失败！", {icon: 2});
								parent.location.href = hostIpHtml+ "/index/views/appChannel/channelInfoList.html?edit=1&id="+bid; 
							}
								 
						},
					  
					error: function(){
						parent.layer.msg("网络请求异常，请尝试重新登陆！" , {icon: 2});//提示信息 
					}
					/*complete: function(){ 
						//location.reload();
					} */
				}); 
			}
			 