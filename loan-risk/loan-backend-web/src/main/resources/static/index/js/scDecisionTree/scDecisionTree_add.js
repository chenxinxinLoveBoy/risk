layui.config({
    base: '../../js/'
});
// 当前菜单名称
var menuNames = "决策树配置管理";
var scoreTplId = "";
var fraudRuleTplId = "";
var banControlTplId = "";
var implementType = "";
$(function () {
    $("#state2").attr("checked", true);
    //我们强烈推荐你在代码最外层把需要用到的模块先加载
    layui.use(['layer', 'form', 'element', 'laydate'], function () {
        var layer = layui.layer
            , form = layui.form()
            , element = layui.element();
        form.render(); //刷新全部

        // 监听radio单选
        form.on('radio(radio_filter1)', function (data) {
            form.render(); //重新渲染
        });
        form.on('radio(radio_filter)', function (data) {
            form.render(); //重新渲染
        });

        //监听select
        form.on('select(scoreTplId)', function (data) {
            scoreTplId = data.value;
        });

        //监听select
        form.on('select(fraudRuleTplId)', function (data) {
            fraudRuleTplId = data.value;
        });

        //监听select
        form.on('select(tplId)', function (data) {
            banControlTplId = data.value;
        });

        //监听select
        form.on('select(implementType)', function (data) {
            implementType = data.value;
        });


        // 监听submit提交事件
        form.on('submit(save)', function (data) {
            // 保存
            save(data);
            return false;// 阻止跳转页面，只有保存成功时才跳转
        });
        //开始时间
        var start = {
            format: 'YYYY-MM-DD hh:mm:ss'
            , istime: true
            , start: laydate.now(0, 'YYYY-MM-DD 00:00:00')
            , choose: function (datas) {
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas //将结束日的初始值设定为开始日
            }
        };
        //结束时间
        var end = {
            format: 'YYYY-MM-DD hh:mm:ss'
            , istime: true
            , choose: function (datas) {
                start.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };

        $("#startTime").on("click", function () {
            start.elem = this;
            laydate(start);
            if (laydate.now(0, 'YYYY-MM-DD hh:mm:ss') == $(this).val().trim()) {
                $(this).val(laydate.now(0, 'YYYY-MM-DD 00:00:00'));
            }
        });

        $("#endTime").on("click", function () {
            end.elem = this
            laydate(end);
        });

        getTplList1(form);
        getTplList2(form);
        getTplList3(form);
        getTplList4(form);

        var decisionTreeId = getQueryString("decisionTreeId");
        if (decisionTreeId != '' && decisionTreeId != undefined) {
            $("#decisionTreeId").val(decisionTreeId);
            $.ajax({
                url: getCtxPath() + "/backend/scDecisionTree/getEntityById.do?v=" + new Date().getTime(),
                method: "post",
                dataType: "json",
                data: {
                    decisionTreeId: decisionTreeId
                },
                success: function (data) {
                    var stateval = data.list.scDecisionTreeObject.state;//回显单选框
                    if (stateval == 01) {
                        $("input[name='state']").eq(0).attr("checked", "checked");
                    } else {
                        $("input[name='state']").eq(1).attr("checked", "checked");
                    }
                    var executeType = data.list.scDecisionTreeObject.executeType;//回显单选框
                    if (executeType == '1') {
                        $("input[name='executeType']").eq(0).attr("checked", "checked");
                    } else {
                        $("input[name='executeType']").eq(1).attr("checked", "checked");
                    }

                    var implementType = data.list.scDecisionTreeObject.implementType;
                    if (implementType == '') {
                        implementType = '00';
                    }

                    $("#level").val(data.list.scDecisionTreeObject.level);
                    $("#decisionTreeName").val(data.list.scDecisionTreeObject.decisionTreeName);
                    $("#decisionTreeCode").val(data.list.scDecisionTreeObject.decisionTreeCode);
                    $("#startTime").val(data.list.scDecisionTreeObject.startTime.substring(0, 19));
                    $("#endTime").val(data.list.scDecisionTreeObject.endTime.substring(0, 19));
                    $("#tplPercent").val(parseFloat((data.list.scDecisionTreeObject.tplPercent * 100).toPrecision(12)));
                    $("#exchanges").find("option[value='" + data.list.scDecisionTreeObject.exchanges + "']").attr("selected", true);
                    $("#version").val(data.list.scDecisionTreeObject.version);
                    $("#tplId").find("option[value='" + data.list.scDecisionTreeObject.banControlTplId + "']").attr("selected", true);
                    $("#scoreTplId").find("option[value='" + data.list.scDecisionTreeObject.scoreTplId + "']").attr("selected", true);
                    $("#fraudRuleTplId").find("option[value='" + data.list.scDecisionTreeObject.fraudRuleTplId + "']").attr("selected", true);
                    $("#select_implementType").find("option[value='" + implementType + "']").attr("selected", true);
                },
                error: function () {
                    layer.msg("网络请求异常,请尝试重新登录", {icon: 2});//提示信息
                },
                complete: function () {
                    form.render(); //刷新全部
                }
            });
        } else {
            $("#state").attr("disabled", "disabled");
            form.render(); //刷新全部
        }
    });
})

function getboxValue() {
    var arr = [];
    var $checkbox = $('input[name=cheat]:checked');
    if ($checkbox != undefined) {
        $checkbox.each(function () {
            arr.push($(this).val());
        })
    }
    return arr.join(",");
}


// 保存
function save(form) {
    var chebckboxValue = getboxValue();
    var decisionTreeName = $("#decisionTreeName").val();
    var decisionTreeId = getQueryString("decisionTreeId");
    var decisionTreeCode = $("#decisionTreeCode").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var state = form.field.state;//保存单选框的选中的值
    var tplPercent = $("#tplPercent").val();
    var exchanges = form.field.exchanges;
    var version = $("#version").val();
    var level = $("#level").val();
    var executeType = form.field.executeType;
    var scoreTplId = form.field.scoreTplId;
    var fraudRuleTplId = form.field.fraudRuleTplId;
    var banControlTplId = form.field.tplId;
    var implementType = form.field.implementType;
    if (level == '' || level === undefined) {
        parent.layer.msg("优先级不能为空!", {icon: 2});
        return false;
    }
    ;
    if (executeType == '' || executeType === undefined) {
        parent.layer.msg("执行类型不能为空!", {icon: 2});
        return false;
    }
    ;

    if (decisionTreeName == '' || decisionTreeName === undefined) {
        parent.layer.msg("决策树名称不能为空!", {icon: 2});
        return false;
    }
    ;

    if (decisionTreeCode == '' || decisionTreeCode === undefined) {
        parent.layer.msg("决策树业务编号不能为空!", {icon: 2});
        return false;
    }
    ;
    if (fraudRuleTplId == '' || fraudRuleTplId === undefined) {
        parent.layer.msg("欺诈模板不能为空!", {icon: 2});
        return false;
    }
    ;

    if (decisionTreeCode.length > 9) {
        parent.layer.msg("决策树业务编号不能超过9位数!", {icon: 2});
        return false;
    }
    ;


    if (startTime == '' || startTime === undefined) {
        parent.layer.msg("决策树开始生效时间不能为空!", {icon: 2});
        return false;
    }
    ;
    if (endTime == '' || endTime === undefined) {
        parent.layer.msg("决策树失效时间不能为空!", {icon: 2});
        return false;
    }
    ;
    if (state == '' || state === undefined) {
        parent.layer.msg("决策树状态不能为空!", {icon: 2});
        return false;
    }
    ;
    if (exchanges == '' || exchanges === undefined) {
        parent.layer.msg("MQ交换机不能为空!", {icon: 2});
        return false;
    }
    ;

    if (tplPercent == '' || tplPercent === undefined) {
        parent.layer.msg("权重不能为空!", {icon: 2});
        return false;
    } else if (tplPercent < 1 || tplPercent > 100) {
        parent.layer.msg("权重请输入1到100之间的正整数!", {icon: 2});
        return false;
    }
    ;


    $.ajax({
        url: hostIp + "/scDecisionTree/saveScDecisionTree.do",
        method: 'post',
        dataType: 'json',
        data: {
            decisionTreeId: decisionTreeId,
            decisionTreeName: decisionTreeName,
            decisionTreeCode: decisionTreeCode,
            startTime: startTime,
            endTime: endTime,
            state: state,
            tplPercent: parseInt(tplPercent) / 100,
            exchanges: exchanges,
            version: version,
            level: level,
            executeType: executeType,
            banControlTplId: banControlTplId,
            scoreTplId: scoreTplId,
            fraudRuleTplId: fraudRuleTplId,
            implementType: implementType,
            menuNames: menuNames, // 菜单名称
            functionNames: (decisionTreeId.length > 0 ? "修改" : "新增") // 按钮功能名称
        },
        success: function (data) {
            if ('2001' == data.code) {
                parent.layer.msg("决策树保存成功！", {icon: 1});
                closeWindows();
                window.parent.getList();
            } else if ('102' == data.code) {
                parent.layer.msg("决策树已存在！", {icon: 2});
                closeWindows();
            } else if (data.code == "6005") {
                parent.layer.msg(data.message, {icon: 2});
                closeWindows();
            } else if (data.code == "6007") {
                parent.layer.msg(data.message, {icon: 2});
                closeWindows();
            } else if (data.code == "8003") {
                parent.layer.msg(data.message, {icon: 2});
                closeWindows();
            } else {
                parent.layer.msg("决策树保存失败！", {icon: 2});
                closeWindows();
            }
        },
        error: function () {
            layer.msg("网络请求异常,请尝试重新登录", {icon: 2});//提示信息
        },
    });
}

function closeWindows() {
    //parent.window.location.reload();
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);
    window.location = getCtxPath() + "/index/views/scDecisionTree/scDecisionTree.html";
};


function getTplList1(form) {
    $.ajax({
        url: getCtxPath() + "/backend/scScoreTpl/scScoreTplListAll.do?v=" + new Date().getTime() + "&state=01",
        method: "post",
        dataType: "json",
        async: false,
        data: {},
        success: function (data) {
            var arr = [];
            arr.push('<select name="scoreTplId" id = "scoreTplId" class="layui-input" lay-search="">');
            layui.each(data.list, function (index, item) {
                if (scoreTplId === item.scoreTplId) {
                    arr.push('<option value="' + item.scoreTplId + '" selected>' + item.scoreTplName + '</option>');
                } else {
                    arr.push('<option value="' + item.scoreTplId + '">' + item.scoreTplName + '</option>');
                }
            });
            arr.push('</select>');
            $('#tplContent1').html(arr.join(''));
            form.render(); //刷新全部
        },
        error: function () {
            alert("获取用户信息失败,请尝试重新获取！");
        }
    });
}

function getTplList2(form) {
    $.ajax({
        url: getCtxPath() + "/backend/scFraudRuleTpl/scFraudRuleTplListAll.do?v=" + new Date().getTime() + "&state=01",
        method: "post",
        async: false,
        dataType: "json",
        data: {},
        success: function (data) {
            var arr = [];
            arr.push('<select name="fraudRuleTplId" id = "fraudRuleTplId" class="layui-input" lay-search="">');
            layui.each(data.list, function (index, item) {
                if (fraudRuleTplId === item.fraudRuleTplId) {
                    arr.push('<option value="' + item.fraudRuleTplId + '" selected>' + item.fraudRuleTplName + '</option>');
                } else {
                    arr.push('<option value="' + item.fraudRuleTplId + '">' + item.fraudRuleTplName + '</option>');
                }
            });
            arr.push('</select>');
            $('#tplContent2').html(arr.join(''));
            form.render(); //刷新全部
        },
        error: function () {
            alert("获取用户信息失败,请尝试重新获取！");
        }
    });
}


function getTplList3(form) {
    $.ajax({
        url: getCtxPath() + "/backend/sctemplate/templatelistAll.do?v=" + new Date().getTime() + "&state=01",
        method: "post",
        async: false,
        dataType: "json",
        data: {
            pageIndex: 0,
            pageSize: 100
        },
        success: function (data) {
            var arr = [];
            arr.push('<select name="tplId" id = "tplId" class="layui-input" lay-search="">');
            layui.each(data.list, function (index, item) {
                if (index === 0) {
                    arr.push('<option value="' + item.banControlTplId + '" selected>' + item.banTplName + '</option>');
                } else {
                    arr.push('<option value="' + item.banControlTplId + '">' + item.banTplName + '</option>');
                }
            });
            arr.push('</select>');
            $('#tplContent3').html(arr.join(''));
            form.render(); //刷新全部
        },
        error: function () {
            alert("获取用户信息失败,请尝试重新获取！");
        }
    });
}

function getTplList4(form) {
    $.ajax({
        url: getCtxPath() + "/backend/mq/queryByexchanges.do?v=" + new Date().getTime() + "&state=01",
        method: "post",
        async: false,
        dataType: "json",
        data: {
            pageIndex: 0,
            pageSize: 100
        },
        success: function (data) {
            console.log(data);
            var arr = [];
            arr.push('<select name="exchanges" id = "exchanges" class="layui-input" lay-search="">');
            layui.each(data.list, function (index, item) {
                if (index === 0) {
                    arr.push('<option value="' + item.exchanges + '" selected>' + item.exchanges + '</option>');
                } else {
                    arr.push('<option value="' + item.exchanges + '">' + item.exchanges + '</option>');
                }
            });
            arr.push('</select>');
            $('#exchanges').html(arr.join(''));
            form.render(); //刷新全部
        },
        error: function () {
            alert("获取用户信息失败,请尝试重新获取！");
        }
    });
}


// 关闭
function closeIfrname() {
//					layer.closeAll();
    var frameindex = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
    parent.layer.close(frameindex);
}