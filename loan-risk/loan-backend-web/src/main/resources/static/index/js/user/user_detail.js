	layui.config({
		base: '../../js/' 
	});
// 用户id
var id = getQueryString("id");	
//当前菜单名称
var menuNames = "修改个人信息";

// 回显
$(function(){
	
	//我们强烈推荐你在代码最外层把需要用到的模块先加载
	layui.use(['tree','upload','layer', 'form', 'element'], function(){
		  var layer = layui.layer
		  ,form = layui.form()
		  ,element = layui.element();
		  form.render(); //刷新全部
		  
		  // 文件模块
		  layui.upload({
			    url: getCtxPath() + '/backend/uuser/upload.do'
			    ,elem: '#file_id' //指定原始元素，默认直接查找class="layui-upload-file" ,这里指定id
			    ,method: 'post' //上传接口url
			    ,success: function(res){  // 回调
			      $("#LAY_demo_upload").attr("style", "block");// 显示图片
			      LAY_demo_upload.src = res.url;
			    }
		  });
		  
		// 回显
		if(id !='' && id != null){  
			// 回显：禁止帐号修改
			$("#userName").attr("readonly", "readonly");
			
			$.ajax({
				url : getCtxPath() + "/backend/uuser/getObjectById.do",
				method: "post",
				dataType:"json",
				data: {
					id: id
				},
				success: function(data){
					$("#userName").val(data.list.userObject.userName); // 用户名
					$("#nickName").val(data.list.userObject.nickName); //昵称
					$("#mobile").val(data.list.userObject.mobile);// 手机
//					$("#password").val(data.list.userObject.password); // 密码
					$("#id").val(data.list.userObject.id);// id
					if(data.list.userObject.userPic != '' && data.list.userObject.userPic != null){
						$("#LAY_demo_upload").attr("style", "block");// 显示图片
					}
					$("#LAY_demo_upload").attr("src", data.list.userObject.userPic);// 图片url
				},
				error: function(){
					 layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
				}
			});	
		}
		
		
		// 加载角色
		getRoleName(form, id);
		  
	});
	// 预加载		end!
	
});



// 获取角色
function getRoleName(form, id){
	$.ajax({
		url : getCtxPath() + "/backend/role/getRoleList.do",
		method: "post",
		dataType:"json",
		data: {
			userId: id, // 用户id
			pageSize: -1 // 表示不分页（limit），查询所有
		},
		success: function(data){
			$.each(data.list, function(k, v){
				if(v.isCheck == 1){ // 回显选中，有角色的才显示
					$("#select_parentId").append("<option selected=selected value='"+v.roleId+"'>"+v.roleName+"</option>");
				}
			});
		},
		error: function(){
			layer.msg("网络请求异常,请尝试重新登录" , {icon: 2});//提示信息
		},
		complete: function(){
			form.render(); //刷新全部
		}
		
	});	
}



// 关闭
function closeIfrname(){
//	layer.closeAll();
	var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(frameindex);
}


// 保存
function save(){
	var userName = $("#userName").val().trim();// 登录帐号
	var nickName = $("#nickName").val().trim();// 用户昵称
	
	var mobile = $("#mobile").val().trim();// 手机号
	
	if(userName == '' || userName == null){
		parent.layer.msg("登录帐号不能为空" , {icon: 2});
		return ;
	}
	
	if(nickName == '' || nickName == null){
		parent.layer.msg("姓名不能为空" , {icon: 2});
		return ;
	}
	
	var password = $("#password").val().trim();// 密码
	
	// 如果密码不为空，则进行base64编码
	if(password != '' && password != null && password != undefined){
		password = encode64(password);
	}
	
	if(mobile == '' || mobile == null){
		parent.layer.msg("手机号不能为空" , {icon: 2});
		return ;
	}		
	
	if(mobile.length!=11) 
    { 
    	parent.layer.msg("请输入有效的11位手机号码！" , {icon: 2});
        return ; 
    } 
	
	
	
	var  frameindex= parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	var userIds = $("#id").val();
	$.ajax({
		url : getCtxPath() + "/backend/uuser/save.do",
		method : 'post',
		dataType : 'json',
		data: {
			userName: userName, // 传到后台的参数，子窗口的form表单
			nickName: nickName,  
			password: password,
			base64codes: "1", // 密码由前端转码过
			mobile: mobile, // 手机号
			id: userIds,
			roleId: $("#select_parentId").val(), // 角色id
			userPic: $("#LAY_demo_upload").attr("src"),// 图片url
			menuNames: menuNames, // 菜单名称
			functionNames: "修改个人信息" // 按钮功能名称
		},
		success : function(data) {
			$("#upload_id").attr("style", "display:none"); // 图片按钮隐藏
			if(data.code == "2001" || data.code == "4001"){
				parent.layer.msg(data.message , {icon: 1});//提示信息
				$("#save_bt").attr("style", "display:none");//按钮隐藏
				$("#closeIframe").attr("style", "display:none");//按钮隐藏
				
				// 延迟一秒后跳转页面
				setTimeout(function(){
					closeIfrname();
					parent.location.href = getCtxPath() + "/index/index.html";// 跳转页面
				},1000)
			}else if(data.code == "102"){
				parent.layer.msg("帐号已存在，请重新填写", {icon: 2});
			}else{
				parent.layer.msg(data.message , {icon: 2});//提示信息
			}
			
		},
		error: function(){
			layer.msg("网络请求异常，请尝试重新登陆！" , {icon: 2});//提示信息
		}
	}); 
}
