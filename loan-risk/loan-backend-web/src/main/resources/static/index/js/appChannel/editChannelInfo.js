			layui.config({
				base: '../../js/'
			});
		
			$(function(){
 				var id = getQueryString("id");
 				var receiveBigName = decodeURI(getQueryString("bigName")); 
 				var receiveRemark = decodeURI(getQueryString("remark")); 
 				$("#dicBigValue").val(receiveBigName);//数据字典大类中文名称
				$("#bigRemark").val(receiveRemark);//数据字典大类使用状态（1-正常、2-停用）			
			});
			 
			// 搜索
			 function search() {  
			};
			 			 
			// 关闭
			function closeIfrname(){
				var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(frameindex);
			};
			
			// 保存
			function save(){
				var id = getQueryString("id");
				var dicBigValue = $("#dicBigValue").val().trim();// 渠道大类名称
				var bigRemark = $("#bigRemark").val().trim();// 渠道大类备注
				
				if(dicBigValue === '' || dicBigValue === undefined){
					parent.layer.msg("渠道大类不能为空" , {icon: 2});
					return ;
				}
				var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				var saveOrUpdate= id == ''? "forSave":"forUpdate";
				$.ajax({
					url :  hostIp+ "/appBigChannel/saveOrUpdate.do",
					method : 'post',
					dataType : 'json',
					data: {
						channelBigId : id,
						channelBigName: dicBigValue,  
						remark:bigRemark,
						saveOrUpdate: saveOrUpdate 
					},
					success : function(data) {
						if('102' == data.code){
							parent.layer.msg("渠道大类已存在！请重新输入！" , {icon: 2});//提示信息
						}else if(data.code == '-200'){
							parent.layer.msg("渠道大类已存在！请重新输入！" , {icon: 2});//提示信息
						}else if(data.code == '2001'){
							parent.layer.msg("渠道大类保存成功！", {icon: 1});
							parent.location.href = hostIpHtml + "/index/views/appChannel/channelInfo.html"; 
						}else if(data.code == '4001'){
							parent.layer.msg("渠道大类修改成功！", {icon: 1});
							parent.location.href = hostIpHtml + "/index/views/appChannel/channelInfo.html";
						} else if(data.code == '4002'){
							parent.layer.msg("渠道大类修改失败！", {icon: 2});
							parent.location.href = hostIpHtml + "/index/views/appChannel/channelInfo.html";
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