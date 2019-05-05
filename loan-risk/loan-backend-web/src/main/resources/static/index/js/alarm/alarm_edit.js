layui.config({
    base: '../../js/'
});
// 当前菜单名称
var menuNames = "报警配置管理";

//初始化加载 start
$(function () {
    //我们强烈推荐你在代码最外层把需要用到的模块先加载
    layui.use(['layer', 'form', 'element'], function () {
        var layer = layui.layer
            , form = layui.form()
            , element = layui.element();

        init();

        form.render(); //刷新全部

        //自定义验证规则
        form.verify({
            roleName: function (value) {
                if (value.length < 1) {
                    return '角色名称至少得输入啊';
                }
            }
        });

        form.on('radio(alarmType_id)', function(data){
            console.log(data.elem); //得到radio原始DOM对象
            console.log(data.value); //被点击的radio的value值
            paramRequired(data.value);
        });

        // 监听submit提交事件	start
        form.on('submit(save)', function (data) {
            var url = getCtxPath() + "/backend/alarm/save.do";
            $.ajax({
                url: url,
                method: "post",
                data: {
                    alarmId: data.field.alarmId,// id
                    alarmCode: data.field.alarmCode,// 角色名称
                    alarmMsg: data.field.alarmMsg, // 备注
                    priority: data.field.priority,
                    alarmType: data.field.alarmType,
                    thirdPartyCreditInvestigation: data.field.thirdPartyCreditInvestigation,
                    phone: data.field.phone,
                    email: data.field.email,
                    dingdingUrl: data.field.dingdingUrl,
                    menuNames: menuNames, // 菜单名称
                    functionNames: (data.field.alarmId.length > 0 ? "修改" : "新增") // 按钮功能名称
                },
                success: function (obj) {
                    if (obj.code == "2001" || obj.code == "4001") {
                        layer.msg(obj.message, {icon: 1});//成功提示信息
                        $("#save_bt").attr("style", "display:none");//保存按钮隐藏
                        closeIfrname(); // 关闭窗口


                    } else if (obj.code == "102") {
                        layer.msg("角色名称,已存在，请更换！", {icon: 2});//失败提示信息
                    } else {
                        layer.msg(obj.message, {icon: 2});//失败提示信息
                    }
                },
                error: function () {
                    layer.msg("网络请求异常，请尝试重新登陆！", {icon: 2});//失败提示信息
                }
            });
            return false;// 阻止跳转页面，只有保存成功时才跳转
        });
        // 监听submit提交事件	   end!


        // 回显
        var alarmId = getQueryString("alarmId");
        var find = getQueryString("find");
        if (find == 1) {  // 预览，将”保存“，”预览“2个按钮取消
            $("#save_bt").attr("style", "display:none");//保存按钮
            $("#off_bt").attr("style", "display:none");// 取消按钮
        }

        if (alarmId != null && alarmId != '') {// 回显

            getfind(alarmId);// 回显
        }


        // 回显
        function getfind(alarmId) {
            if (alarmId != null && alarmId != '') {
                $.ajax({
                    url: getCtxPath() + "/backend/alarm/get/" + alarmId,
                    method: "get",
                    dataType: "json",
                    success: function (data) {
                        $("#alarmId").val(data.list.alarmObject.alarmId);
                        $("#alarmCode").val(data.list.alarmObject.alarmCode);
                        $("#alarmMsg").val(data.list.alarmObject.alarmMsg);
                        $("#priority-div").find("input").each(function (i, n) {
                            var code = data.list.alarmObject.priority;
                            if ($(n).val() == code) {
                                $("#priority_id" + code).attr("checked", "checked");
                            }
                        });

                        $("#alarmType-div").find("input").each(function (i, n) {
                            var code = data.list.alarmObject.alarmType;
                            if ($(n).val() == code) {
                                $("#alarmType_id" + code).attr("checked", "checked");
                                paramRequired(code);
                            }
                        });

                        $("#thirdPartyCreditInvestigation").val(data.list.alarmObject.thirdPartyCreditInvestigation);
                        $("#phone").val(data.list.alarmObject.phone);
                        $("#email").val(data.list.alarmObject.email);
                        $("#dingdingUrl").val(data.list.alarmObject.dingdingUrl);

                        // priority
                        // alarmType
                        // thirdPartyCreditInvestigation

                        form.render('radio');
                        form.render('select');
                    },
                    error: function () {
                        layer.msg("网络请求异常,请常识重新登录", {icon: 2});//提示信息
                    },
                    complete: function () {
//				form.render(); //刷新全部
                    }
                });
            }
        }
    });

    function init() {
        $.ajax({
            url: getCtxPath() + "/backend/alarm/getAlarmEnums",
            method: "get",
            dataType: "json",
            async:false,
            success: function (data) {
                if (data.code == 200) {
                    data = data.list.data;
                    $.each(data.prioritys, function (i, n) {
                        var checked = "";
                        if(i==0)
                            checked = "checked";
                        var html = "<input name=\"priority\" value=\"" + n.code + "\" title=\"" + n.name + "\" type=\"radio\" lay-filter=\"priority_id\" lay-verify=\"required\" id=\"priority_id" + n.code + "\" "+checked+" required/>";
                        $("#priority-div").append(html);
                    });
                    $.each(data.types, function (i, n) {
                        var checked = "";
                        if(i==0)
                            checked = "checked";
                        var html = "<input name=\"alarmType\" value=\"" + n.code + "\" title=\"" + n.name + "\" type=\"radio\" lay-filter=\"alarmType_id\" lay-verify=\"required\" id=\"alarmType_id" + n.code + "\" "+checked+" required/>";
                        $("#alarmType-div").append(html);
                    })
                    $.each(data.credits, function (i, n) {
                        $("#thirdPartyCreditInvestigation").append("<option value=\"" + n.code + "\">" + n.name + "</option>");
                    });
                    paramRequired(0);
                }
                // priority
                // alarmType
                // thirdPartyCreditInvestigation

                //form.render('radio');
                //form.render('select');
            },
            error: function () {
                layer.msg("网络请求异常,请常识重新登录", {icon: 2});//提示信息
            },
            complete: function () {
//				form.render(); //刷新全部
            }
        });
    }

    function paramRequired(data){
        $("#phone").removeAttr("lay-verify");
        $("#email").removeAttr("lay-verify");
        $("#dingdingUrl").removeAttr("lay-verify");

        if(data==0){
            $("#dingdingUrl").attr("lay-verify","required");
        }else if(data==1){
            $("#phone").attr("lay-verify","required");
        }else if(data==2){
            $("#email").attr("lay-verify","required");
        }else if(data==3){
            $("#dingdingUrl").attr("lay-verify","required");
            $("#phone").attr("lay-verify","required");
        }
    }
});

//初始化加载  end!

// 关闭
function closeIfrname() {
    var frameindex = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(frameindex);
}



