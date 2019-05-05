layui.config({
	base : 'js/'
});

 $('#detail').on('click', function() {
	var userId = $("#userId").val();
	layer.open({
		  type: 2,
		  title: '个人信息',// 设置false表示不显示
		  closeBtn: 1, //0：不显示关闭按钮
		  shade: 0.4, //遮罩透明度
		  area: ['840px', '615px'],
//		  skin: 'layui-layer-rim', //加上边框
		  fixed: false, //不固定
		  maxmin: true, //开启最大化最小化按钮
		  anim: 2,
		  content: ['edit/user/user_detail.html?id='+userId], //iframe的url，no代表不显示滚动条
		});
});
 

 $('#loginHis').on('click', function() {
	var userId = $("#userId").val();
	layer.open({
		  type: 2,
		  title: '个人登录历史',// 设置false表示不显示
		  closeBtn: 1, //0：不显示关闭按钮
		  shade: 0.4, //遮罩透明度
		  area: ['840px', '615px'],
//		  skin: 'layui-layer-rim', //加上边框
		  fixed: false, //不固定
		  maxmin: true, //开启最大化最小化按钮
		  anim: 2,
		  content: ['views/scLoginLog/scLoginLog.html?id='+userId], //iframe的url，no代表不显示滚动条
		});
});
 
// 初始化加载
$(function() {
	getUser();
});

// 加载头像和昵称
function getUser() {
	// 获取头像和昵称
	var nickName = $("#nickName").val();// 当前登录昵称
	var userPic = $("#userPic").val();// 头像
	// 加载图片和头像
	$
			.ajax({
				url : getCtxPath() + "/backend/uuser/getUser.do",
				method : "post",
				dataType : "json",
				data : {
					nickName : nickName,
					userPic : userPic
				},
				success : function(data) {
					if (data.list.user.nickName != ""
							&& data.list.user.nickName != null) {
						$("#nickName").html(data.list.user.nickName);
					}

					if (data.list.user.userPic != ""
							&& data.list.user.userPic != null) {
						$("#userPic").attr("src", data.list.user.userPic);
					} else {
						$("#userPic").attr("style", "display:none");
					}
					$("#userId").val(data.list.user.id);
				},
				error : function() {
					layer.msg("加载头像数据网络请求异常，请尝试重新登录！");
				}

			});
}

// 退出/注销
function logout() {
	location.href = getCtxPath() + "/backend/login/logout.do";
}