layui.config({
    base: '../../js/'
});
var treeNames = [];

//我们强烈推荐你在代码最外层把需要用到的模块先加载
layui.use(['layer', 'form', 'laydate', 'paging', 'element'], function () {
    var layer = layui.layer,
        form = layui.form(),
        laydate = layui.laydate,
        paging = layui.paging(),
        layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
        element = layui.element();

    form.render(); //刷新全部

    initDate();
    initPageing();
    initCount();

    //监听表单提交事件
    form.on('submit(from)', function (data) {
        paging.get(returnSelectCondition());
        initCount();
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

    function returnSelectCondition() {
        var condition = {
            t: Math.random(),
            startTime: $("#startTime").val(),
            endTime: $("#endTime").val(),
            appLevel: $("#appLevel").val()
        }
        return condition;
    }

    function initDate() {
        var start = {
            format: 'YYYY-MM-DD hh:mm:ss'
            , istime: true
            , start: laydate.now(0, 'YYYY-MM-DD 00:00:00')
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

        $("#startTime").val(laydate.now(0, 'YYYY-MM-DD 00:00:00'));
        $("#endTime").val(laydate.now(0, 'YYYY-MM-DD 23:59:59'));

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
    }

    function initPageing() {

        var params = returnSelectCondition();

        // 页面初始化加载		start
        paging.init({
            openWait: true,
            url: getCtxPath() + "/backend/application/txCountStepNumber.htm?v=" + new Date().getTime(),
            elem: '#content', //内容容器
            params: params,
            type: 'get',
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
            },
        });
        // 初始化加载init     end!
    }

    function initCount() {

        var params = returnSelectCondition();

        $.ajax({
            url: getCtxPath() + "/backend/application/queryTxResultCount.do",
            method: "post",
            dataType: "json",
            data: params,
            success: function (data) {
                if (data.code = 200) {
                    var list = data.list.data.list;

                    var unAuditedNum = 0, passNum = 0, notPassNum = 0, sum = 0;
                    var unAuditedCertCount = 0, passCertCount = 0, notPassCertCount = 0, certCountSum = 0;
                    for (var i in list) {
                        if (list[i].auditingState == 2) {
                            passNum += parseInt(list[i].count);
                            passCertCount += parseInt(list[i].certCount);
                        } else if (list[i].auditingState == 3) {
                            notPassNum += parseInt(list[i].count);
                            notPassCertCount += parseInt(list[i].certCount);
                        } else {
                            unAuditedNum += parseInt(list[i].count);
                            unAuditedCertCount += parseInt(list[i].certCount);
                        }
                    }
                    $("#unAudited_certcode").html(unAuditedCertCount + " 人");
                    $("#pass_certcode").html(passCertCount + " 人");
                    $("#notPass_certcode").html(notPassCertCount + " 人");
                    certCountSum = unAuditedCertCount + passCertCount + notPassCertCount;
                    $("#sum_certcode").html(certCountSum + " 人");

                    $("#unAudited").html(unAuditedNum + " ");
                    $("#pass").html(passNum + " ");
                    $("#notPass").html(notPassNum + " ");
                    sum = unAuditedNum + passNum + notPassNum;
                    $("#sum").html(sum + " ");
                    if (sum == 0 || certCountSum == 0) {
                        $("#notPass_certcode").html(0 + " 人");
                        $("#pass_certcode").html(0 + " 人");
                        $("#unAudited_certcode").html(0 + " 人");

                        $("#notPassRate").html(0 + " ");
                        $("#passRate").html(0 + " ");
                        $("#unAuditedRate").html(0 + " ");
                    } else {
                        var notPassRate = ((notPassNum / sum) * 100).toFixed(2) + '%';
                        $("#notPassRate").html(notPassRate);
                        var passRate = ((passNum / sum) * 100).toFixed(2) + '%';
                        $("#passRate").html(passRate);
                        var unAuditedRate = (100 - ((passNum / sum) * 100).toFixed(2) - ((notPassNum / sum) * 100).toFixed(2)).toFixed(2);
                        if (unAuditedRate > 0) {
                            unAuditedRate = unAuditedRate + '%';
                        } else {
                            unAuditedRate = "0.00%";
                        }
                        $("#unAuditedRate").html(unAuditedRate);
                    }

                }
            },
            error: function () {
                layer.msg("网络请求异常,请尝试重新登录", {icon: 2});//提示信息
            },
            complete: function () {
                form.render(); //刷新全部
            }
        });
    }
});

  //关闭
  function closeIfrname() {
      var frameindex = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
      parent.layer.close(frameindex);
  }

 			
 			