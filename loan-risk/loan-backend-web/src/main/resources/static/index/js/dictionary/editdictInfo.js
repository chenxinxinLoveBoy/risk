			layui.config({
				base: '../../js/'
			});
		
			$(function(){
 				var id = getQueryString("id"); 			
				
				// 回显
				if(id !='' && id != undefined){  
					$("#dicBigCode").attr("readonly", "readonly");// 回显禁止帐号修改
					$.ajax({
						url : hostIp+ "/scDicBiglist/getObjectById.do",
						method: "post",
						dataType:"json",
						data: {
							id: id
						},
						success: function(data){
							$("#dicBigCode").val(data.list.scObject.dicBigCode); // 数据字典大类编号
							$("#dicBigValue").val(data.list.scObject.dicBigValue);//数据字典大类中文名称
							$("#dicBigStaues").val(data.list.scObject.dicBigStaues);//数据字典大类使用状态（1-正常、2-停用）							
						},
						error: function(){
							parent.layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
						},
						complete: function(){ 
						} 
					});	
				}
			});
			 
			// 搜索
			 function search() {  
			};
			 			 
			// 关闭
			function closeIfrname(){
				var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				parent.layer.close(frameindex);
			};
			
			var menuNames = '数据字典大类';
			// 保存
			function save(){
				var id = getQueryString("id");
				var dicBigCode = $("#dicBigCode").val().trim();// 数据字典大类编号
				var dicBigValue = $("#dicBigValue").val().trim();// 数据字典大类中文名称
				var dicBigStaues = $("#dicBigStaues").val().trim();// 数据字典大类使用状态
				
				if(dicBigCode === '' || dicBigCode === undefined){
					parent.layer.msg("数据字典大类编号不能为空" , {icon: 2});
					return ;
				}
				
				if(dicBigValue === '' || dicBigValue === undefined){
					parent.layer.msg("数据字典大类中文名称不能为空" , {icon: 2});
					return ;
				}
				
				if(dicBigStaues === '' || dicBigStaues === undefined){
					parent.layer.msg("数据字典大类使用状态不能为空" , {icon: 2});
					return ;
				}
				 
				var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				$.ajax({
					url :  hostIp+ "/scDicBiglist/save.do",
					method : 'post',
					dataType : 'json',
					data: {
						id : id,
						dicBigCode: dicBigCode, // 传到后台的参数，子窗口的form表单
						dicBigValue: dicBigValue,  
						dicBigStaues: dicBigStaues,
						menuNames : menuNames,//数据字典大类
						functionNames: (id != ''  ? "修改数据字典大类" : "新增数据字典大类") // 按钮功能名称 不为空修改 为空 新增
					},
					success : function(data) {
						if('102' == data.code){
							parent.layer.msg("大类字典编号已存在！请重新输入！" , {icon: 2});//提示信息
						}else{
							alert("大类字典保存成功！")
							parent.location.href = hostIpHtml + "/index/views/dictionary/dictInfo.html"; 
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