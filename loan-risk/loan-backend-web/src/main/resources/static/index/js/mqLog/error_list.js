layui.config({
    base: '../../js/'
});

var urls = getCtxPath() + "/backend/mq/errorlog/page?v=" + new Date().getTime();
var title = "";

layui.use(['paging', 'layer', 'form', 'element', 'laydate'], function () {
    var $ = layui.jquery,
        paging = layui.paging(),
        layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
        layer = layui.layer, //获取当前窗口的layer对象
        form = layui.form(),
        laydate = layui.laydate,
        element = layui.element();

    initSerach();
    initDate();

    //监听表单提交事件
    form.on('submit(from)', function(data){
        reload();
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    $("#showMessage").click(function () {
        var trs = returnCheckTr();
        if (trs.length == 0 || trs.length > 1) {
            layer.msg('请勾选一行需要重新查看详情的消息！', {
                time: 2000, //2s后自动关闭
                btn: ['知道了']
            });
            return;
        }
        var html = $(trs[0]).find("div[name='messageDetails']").html();
        layer.open({
            title: "消息详情",
            type: 1,
            area: '806px',
            fixed: true, //不固定
            move: false,//禁止拖动
            content: html
        });
    });

    $("#resend").click(function () {
        var trs = returnCheckTr();
        if (trs.length == 0) {
            layer.msg('请勾选一行需要重新发送的数据！', {
                time: 2000, //2s后自动关闭
                btn: ['知道了']
            });
            return;
        }
        layer.confirm("您确定要重发吗？", {
            btn: ['确定', '取消'], //按钮
            shade: 0.4 //显示遮罩
        }, function (index) {
            var hsIds = ""
            $(trs).each(function () {
                var hsId = $(this).find("td:eq(0)").children('input[type=checkbox]').attr("data-id");
                hsIds += hsId + ',';
            });
            hsIds = hsIds.substr(0, hsIds.length - 1);
            $.ajax({
                url: getCtxPath() + "/backend/mq/errorlog/resend",
                method: 'post',
                dataType: "json",
                data: {
                    hsIds: hsIds
                },
                success: function (data) {
                    layer.msg(data.message);// 提示信息
                    //reload();
                },
                error: function () {
                    layer.alert("请求网络异常，请尝试重新登录！");
                },
                complete: function () {
                }
            });
        });
    });

    // 页面初始化加载		start
    paging.init({
        openWait: true,
        //                     url: 'datas/laytpl_laypage_data.json?v=' + new Date().getTime(), //地址
        url: urls,// 请求服务器的url
        elem: '#content', //内容容器
        params: { //发送到服务端的参数
            messageBody: $("#messageBody").val(),
            messageChannelType: $("#messageChannelType").val(),
            messageType: $("#messageType").val(),
            messageStatus: $("#messageStatus").val(),
            messageLevel: $("#messageLevel").val(),
            optTimeBigen: $("#optTimeBigen").val(),
            optTimeEnd: $("#optTimeEnd").val(),
            messageId: $("#messageId").val(),
            applicationId: $("#applicationId").val()
        },
        type: 'post',
        tempElem: '#tpl', //模块容器
        pageConfig: { //分页参数配置
            elem: '#paged', //分页容器
            pageSize: 10 //分页大小，当前页面显示的条数
        },
        success: function () { //渲染成功的回调
            //						alert('渲染成功');
        },
        fail: function (msg) { //获取数据失败的回调
            layer.msg("获取数据失败！");
        },
        complate: function () { //完成的回调
            //alert('处理完成');
            //重新渲染复选框
            form.render('checkbox');
            form.on('checkbox(allselector)', function (data) {
                var elem = data.elem;
                $('#content').children('tr').each(function () {
                    var $that = $(this);
                    //全选或反选
                    $that.children('td').eq(0).children('input[type=checkbox]')[0].checked = elem.checked;
                    form.render('checkbox');
                });
            });
        }
//
    });

    function returnCheckTr() {
        var trs = new Array();
        $('#content').children('tr').each(function () {
            var $that = $(this);
            var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;
            if ($cbx) {
                trs.push($that);
            }
        });
        return trs;
    }

    function initSerach() {
        $.ajax({
            url: getCtxPath() + "/backend/mq/log/search-condition",
            method: "get",
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    data = data.list.data;
                    $("#messageChannelType").append("<option value=\"\">全部</option>");
                    $("#messageType").append("<option value=\"\">全部</option>");
                    $("#messageStatus").append("<option value=\"\">全部</option>");
                    $("#messageLevel").append("<option value=\"\">全部</option>");
                    $.each(data.messageChannelType, function (i, n) {
                        $("#messageChannelType").append("<option value=\"" + n.code + "\">" + n.name + "</option>");

                    });
                    $.each(data.messageType, function (i, n) {
                        $("#messageType").append("<option value=\"" + n.code + "\">" + n.name + "</option>");
                    })
                    $.each(data.messageStatus, function (i, n) {
                        $("#messageStatus").append("<option value=\"" + n.code + "\">" + n.name + "</option>");

                    });
                    $.each(data.messageLevel, function (i, n) {
                        $("#messageLevel").append("<option value=\"" + n.code + "\">" + n.name + "</option>");
                    })

                    form.render('select');
                }
            },
            error: function () {
                layer.msg("网络请求异常,请常识重新登录", {icon: 2});//提示信息
            },
            complete: function () {
//				form.render(); //刷新全部
            }
        });
    }

    function reload() {

        var condition = {
            messageBody: $("#messageBody").val(),
            messageChannelType: $("#messageChannelType").val(),
            messageType: $("#messageType").val(),
            messageStatus: $("#messageStatus").val(),
            messageLevel: $("#messageLevel").val(),
            optTimeBigen: $("#optTimeBigen").val(),
            optTimeEnd: $("#optTimeEnd").val(),
            messageId: $("#messageId").val(),
            applicationId: $("#applicationId").val()
        }

        paging.get(condition);
    }

    function initDate() {

        var start = {
            format: 'YYYY-MM-DD hh:mm:ss'
            , istime: true
            , choose: function (datas) {
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas //将结束日的初始值设定为开始日
            }
        };

        var end = {
            format: 'YYYY-MM-DD hh:mm:ss'
            , istime: true
            , choose: function (datas) {
                start.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };

        $("#optTimeBigen").val(laydate.now(0, 'YYYY-MM-DD 00:00:00'));
        $("#optTimeEnd").val(laydate.now(0, 'YYYY-MM-DD 23:59:59'));


        $("#optTimeBigen").on("click", function () {
            start.elem = this;
            laydate(start);
            if (laydate.now(0, 'YYYY-MM-DD hh:mm:ss') == $(this).val().trim()) {
                $(this).val(laydate.now(0, 'YYYY-MM-DD 00:00:00'));
            }
        });
        $("#optTimeEnd").on("click", function () {
            end.elem = this
            laydate(end);
        });
    }
});



