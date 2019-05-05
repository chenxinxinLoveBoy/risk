layui.config({
    base: '../../js/'
});

var urls = getCtxPath() + "/backend/alarm/page.do?v=" + new Date().getTime();
var title = "";

layui.use(['paging', 'layer', 'form', 'element'], function () {
    var $ = layui.jquery,
        paging = layui.paging(),
        layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
        layer = layui.layer, //获取当前窗口的layer对象
        form = layui.form(),
        element = layui.element();

    initSerach();


    // 搜索
    $('#search').on('click', function () {
        //paging.reload(true,{})
        paging.get({
            priority: $("#priority").val(),
            alarmType: $("#alarmType").val(),
            alarmCode: $("#alarmCode").val(),
            thirdPartyCreditInvestigation: $("#thirdPartyCreditInvestigation").val()
        });
    });


    /**新增  start*/
    $('#add').on('click', function () {
        layer.open({
            type: 2,
            title: '新增',// 设置false表示不显示
            closeBtn: 1, //0：不显示关闭按钮
            shade: 0.4, //遮罩透明度
            area: ['800px', '650px'],
            skin: 'layui-layer-rim', //加上边框
            fixed: false, //不固定
            maxmin: true, // 允许屏幕最小化
            anim: 2,
            content: ['alarm_edit.html'], //iframe的url，no代表不显示滚动条
            end : function() {
                paging.get({
                    priority: $("#priority").val(),
                    alarmType: $("#alarmType").val(),
                    alarmCode: $("#alarmCode").val()
                });
            }
        });
    });
    /**新增     end*/

    // 页面初始化加载		start
    paging.init({
        openWait: true,
        //                     url: 'datas/laytpl_laypage_data.json?v=' + new Date().getTime(), //地址
        url: urls,// 请求服务器的url
        elem: '#content', //内容容器
        params: { //发送到服务端的参数
            priority: $("#priority").val(),
            alarmType: $("#alarmType").val(),
            alarmCode: $("#alarmCode").val()
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

            //绑定所有'编辑'按钮事件
            $('#content').children('tr').each(function () {
                var $that = $(this);
                $that.children('td:last-child').children('a[data-opt=edit]').on('click', function () {
                    layer.open({
                        type: 2,
                        title: '编辑',// 设置false表示不显示
                        closeBtn: 1, //不显示关闭按钮
                        shade: 0.4, //遮罩透明度
                        area: ['800px', '650px'],
                        skin: 'layui-layer-rim', //加上边框
                        fixed: false, //不固定
                        maxmin: true, // 允许屏幕最小化
                        //									  offset: 'rb', //右下角弹出
                        //									  time: 2000, //2秒后自动关闭
                        anim: 2,
                        content: ['alarm_edit.html?alarmId=' + $(this).data('id'), 'no'], //iframe的url，no代表不显示滚动条
                        end : function() {
                            paging.get({
                                priority: $("#priority").val(),
                                alarmType: $("#alarmType").val(),
                                alarmCode: $("#alarmCode").val()
                            });
                        }
                    });
                });

            });


            //绑定所有'删除'按钮事件
            $('#content').children('tr').each(function () {
                var $that = $(this);
                $that.children('td:last-child').children('a[data-opt=del]').on('click', function () {
                    // id
                    var alarmId = $(this).data('id');
                    layer.confirm("您确定要删除吗？", {
                        btn: ['确定', '取消'], //按钮
                        shade: 0.4 //显示遮罩
                    }, function (index) {
                        // ajax 请求后台
                        $.ajax({
                            url: getCtxPath() + "/backend/alarm/delete/" + alarmId,
                            method: 'get',
                            success: function (data) {
                                layer.msg(data.message);// 提示信息
                                reload();
                            },
                            error: function () {
                                layer.alert("请求网络异常，请尝试重新登录！");
                            },
                            complete: function () {
                            }
                        });

                    });

                });

            });


            //绑定所有'预览'按钮事件
            $('#content').children('tr').each(function () {
                var $that = $(this);
                $that.children('td:last-child').children('a[data-opt=find]').on('click', function () {
                    layer.open({
                        type: 2,
                        title: '预览',// 设置false表示不显示
                        closeBtn: 1, //不显示关闭按钮
                        shade: 0.4, //遮罩透明度
                        area: ['800px', '650px'],
                        skin: 'layui-layer-rim', //加上边框
                        btn: ['取消'],
                        fixed: false, //不固定
                        maxmin: true, // 允许屏幕最小化
                        //									  offset: 'rb', //右下角弹出
                        //									  time: 2000, //2秒后自动关闭
                        anim: 2,
                        content: ['alarm_edit.html?find=1&alarmId=' + $(this).data('id'), 'no'], //iframe的url，no代表不显示滚动条
                    });
                });

            });

        }
//
    });
    // 初始化加载init     end!

    //获取所有选择的列 （多选删除）
    $('#getSelected').on('click', function () {
        var ids = '';
        $('#content').children('tr').each(function () {
            var $that = $(this);
            var $cbx = $that.children('td').eq(0).children('input[type=checkbox]')[0].checked;
            if ($cbx) {
                var n = $that.children('td:last-child').children('a[data-opt=edit]').data('id');
                ids += n + ',';
            }
        });
        if (ids == '' || ids == null) { // 验空
            layer.msg('请勾选要删除的数据！', {
                time: 2000, //2s后自动关闭
                btn: ['知道了']
            });
            //						layer.msg("请勾选要删除的数据！");
            return false;
        }

        layer.confirm('您确定要删除吗？', {
            btn: ['确定', '取消'] //按钮
        }, function () {

            $.ajax({
                url: getCtxPath() + "/backend/uuser/deleteAll.do",
                method: "post",
                dataType: "json",
                data: {
                    ids: ids
                },
                success: function (data) {
                    if (data.code == 3001) {
                        parent.layer.msg(data.message);//提示信息
                    } else {
                        parent.layer.msg(data.message);
                    }

                },
                error: function () {
                    parent.layer.alert("请求网络异常，请尝试重新登录！");
                },
                complete: function () {
                    location.reload(); //刷新
                }
            });

        });

    });

    function initSerach() {
        $.ajax({
            url: getCtxPath() + "/backend/alarm/getAlarmEnums",
            method: "get",
            dataType: "json",
            success: function (data) {
                if (data.code == 200) {
                    data = data.list.data;
                    $.each(data.prioritys, function (i, n) {
                        $("#priority").append("<option value=\"" + n.code + "\">" + n.name + "</option>");

                    });
                    $.each(data.types, function (i, n) {
                        $("#alarmType").append("<option value=\"" + n.code + "\">" + n.name + "</option>");
                    })
                    $.each(data.credits, function (i, n) {
                        $("#thirdPartyCreditInvestigation").append("<option value=\"" + n.code + "\">" + n.name + "</option>");
                    });

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

    function reload(){
        paging.get({
            priority: $("#priority").val(),
            alarmType: $("#alarmType").val(),
            alarmCode: $("#alarmCode").val(),
            thirdPartyCreditInvestigation: $("#thirdPartyCreditInvestigation").val()
        });
    }
});


// // 初始化
// $(function(){
//     // 搜索
//     $('#search').on('click', function() {
//         title = $("#title").val();
//         //getList();
// //				parent.layer.alert('你点击了搜索按钮')
//     });
//
//
//     /**新增  start*/
//     $('#add').on('click', function() {
//         layer.open({
//             type: 2,
//             title: '新增',// 设置false表示不显示
//             closeBtn: 1, //0：不显示关闭按钮
//             shade: 0.4, //遮罩透明度
//             area: ['740px', '515px'],
//             skin: 'layui-layer-rim', //加上边框
//             fixed: false, //不固定
//             maxmin: true, // 允许屏幕最小化
//             anim: 2,
//             content: ['alarm_edit.html'] //iframe的url，no代表不显示滚动条
//         });
//     });
//     /**新增     end*/
// })



