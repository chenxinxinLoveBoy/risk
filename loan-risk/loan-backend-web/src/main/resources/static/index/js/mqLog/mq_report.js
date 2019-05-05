layui.config({
    base: '../../js/'
});

var urls = getCtxPath() + "/backend/mq/log/unusualList?v=" + new Date().getTime();
var title = "";

layui.use(['layer', 'form', 'laydate'], function () {
    var $ = layui.jquery,
        layer = layui.layer, //获取当前窗口的layer对象
        form = layui.form(),
        laydate = layui.laydate;

    initDate();

    // 搜索
    $('#search').on('click', function () {
        reload();
    });

    function reload() {
        var condition = {
            optTimeBigen: $("#optTimeBigen").val(),
            optTimeEnd: $("#optTimeEnd").val()
        };

        if ((condition.optTimeBigen != '' && condition.optTimeEnd == '') || (condition.optTimeBigen == '' && condition.optTimeEnd != '')) {
            layer.msg('创建时间的开始时间和结束时间请填写好！！！', {
                time: 2000, //2s后自动关闭
                btn: ['知道了']
            });
            return;
        }

        layer.load(1,{shade:[0.5,'#fff']});
        $.ajax({
            url: getCtxPath() + "/backend/mq/log/mqReport.htm",
            method: 'get',
            dataType: "json",
            data: condition,
            success: function (data) {
                layer.closeAll();
                if(data.code == 200){
                    $("#sendCount").text(data.data.data.sendCount);
                    $("#receiveCount").text(data.data.data.receiveCount);
                    $("#hbaseMutualCount").text(data.data.data.hbaseMutualCount);
                    $("#pendingCount").text(data.data.data.pendingCount);
                    $("#approvalOkCount").text(data.data.data.approvalOkCount);
                    $("#approvalNoCount").text(data.data.data.approvalNoCount);
                    return;
                }
                layer.msg(data.message);// 提示信息

                //reload();
            },
            error: function () {
                layer.alert("请求网络异常，请尝试重新登录！");
            },
            complete: function () {
            }
        });

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



