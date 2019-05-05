layui.config({
    base: '../../js/'
});
$(document).ready(function(){

    //由于系统用的是layui 在 select 切换是 通用 form.on 方法 来赋值
    var GLOBAL_AUDIT_STATUS_SELECT = null;

    // 我们强烈推荐你在代码最外层把需要用到的模块先加载
    layui.use(['layer', 'form', 'element', 'laydate'], function(){
        var layer = layui.layer,
            form = layui.form(),
            element = layui.element();
        form.on('radio(lay-filter-auditStatus)', function(_data){
            Case.processManualFileCreditMoney(_data.value);
        });
        form.render(); // 刷新全部
    });

    var SEX = { "1" : "男", "2" : "女" };

    var MARRIAGE = {
        "SPINSTERHOOD": "未婚",
        "MARRIED" : "已婚",
        "DIVORCED" : "离异",
        "WIDOWED" : "丧偶",
        "REMARRY" : "再婚",
        "REMARRY_FORMER" : "复婚"
    };

    var EDUCATION = { "1" : "博士",  "2" : "硕士", "3" : "本科", "4" : "专科",
        "5" : "中专", "6" : "高中", "7" : "职高",  "8" : "初中及以下"
    };

    var AUDIT_STATUS_UN_AUDIT = "1";
    var AUDIT_STATUS_SUCCESS = "2";
    var AUDIT_STATUS_FAIL = "3";
    var AUDIT_STATUS_NEED_CHECK  = "4";

    var GLOBAL_AUDIT_STATUS = { //系统审核状态
        1 : "待系统审批",
        2 : "系统审批通过",
        3 : "系统审批未通过",
        4 : "待人工确认"
    };

    var GLOBAL_RG_AUDIT_STATUS = { //人工审核状态
        1 : "待人工审批",
        2 : "人工审批通过",
        3 : "人工审批未通过",
        4 : "待人工确认"
    };

    var EDUCATION_AGE = { "1" : 24, "2" : 24, "3" : 22, "4" : 21, "5" : 18, "6" : 18, "7" : 15, "8" : 15};
    var DATA_REPORT_PAGE_SIZE = 8; //数据报告pageSize

    //生产ECharts 图表需要的参数
    var GLOBAL_ECHARTS_DATA = {
        auditStatus : null,
        auditScores : 0,
        auditRemark: null,
        auditMan:null, //人工审核人员
        rgAuditingStatus: null, //人工审核状态
        rgCreditMoney: 0
    }

    //黑名单参数
    var PARAM_BLACKLIST = {
        phoneNum: null, //手机号
        name :  null, // 姓名
        certCode : null, //身份证号
        customerId : null, //客户Id
        platformId : null,
        certType : null,
        appName : null,
        dsSource : null,
        isFailure : null,
        rejectFlag : null,
        rejectType : null,
        sNumber : null,
        remark : null,
        menuNames: null, // 菜单名称
        functionNames: null, // 按钮功能名称,
        isFailure : '1',
        rejectFlag : '0',
        rejectType : '22'
    }
    //bu_sp_approval 参数
    var PARAM_BU_SP_APPROVAL = {
        refuseCode : null,
        rgCreditMoney : null,
        refuseName : null,
        applicationId : null,
        rgAuditingState : null,
        netcheckNoAbnormal : null,
        isCallPhone : null,
        rgAuditingRemark : null,
        menuNames : null,
        functionNames : null
    }
    var CaseParam = {
        cleanParamBlacklist : function(){
            PARAM_BLACKLIST.phoneNum= null;
            PARAM_BLACKLIST.name = null;
            PARAM_BLACKLIST.certCode = null;
            PARAM_BLACKLIST.customerId = null;
            PARAM_BLACKLIST.platformId = null;
            PARAM_BLACKLIST.certType = null;
            PARAM_BLACKLIST.appName = null;
            PARAM_BLACKLIST.dsSource = null;
            PARAM_BLACKLIST.isFailure = null;
            PARAM_BLACKLIST.rejectFlag = null;
            PARAM_BLACKLIST.rejectType = null;
            PARAM_BLACKLIST.sNumber = null;
            PARAM_BLACKLIST.remark = null;
            PARAM_BLACKLIST.menuNames= null;
            PARAM_BLACKLIST.functionNames= null;
        },
        cleanBuSpApproval: function(){
            PARAM_BU_SP_APPROVAL.refuseCode = null;
            PARAM_BU_SP_APPROVAL.rgCreditMoney = null;
            PARAM_BU_SP_APPROVAL.refuseName = null;
            PARAM_BU_SP_APPROVAL.applicationId = null;
            PARAM_BU_SP_APPROVAL.rgAuditingState = null;
            PARAM_BU_SP_APPROVAL.netcheckNoAbnormal = null;
            PARAM_BU_SP_APPROVAL.isCallPhone = null;
            PARAM_BU_SP_APPROVAL.rgAuditingRemark = null;
            PARAM_BU_SP_APPROVAL.menuNames = null;
            PARAM_BU_SP_APPROVAL.functionNames = null;
        }
    }

    //以下参数是通过url 解析出来的
    var _globalOpen = getQueryString("open");
    var _globalAppSerialNumber = getQueryString("appSerialNumber");
    var _globalApplicationId = getQueryString("appId");
    var _globalMoney = getQueryString("money");
    var _globalNetCheckNoAbnormal = getQueryString("netcheckNoAbnormal");
    var _globalIsCallPhone = getQueryString("isCallPhone");
    var _globalRemark =  decodeURI(getQueryString("remark"));
    var _globalState = getQueryString("state");

    var Case = {
        getApproveDetails : function(){
            Case.processJudgePageAuditResult();
            CaseAjax.getApproveDetails(_globalAppSerialNumber, function(_data){

                GLOBAL_ECHARTS_DATA.auditScores = _data.appraiseMoney;// 系统得分
                GLOBAL_ECHARTS_DATA.rgCreditMoney = _data.creditMoney;
                GLOBAL_ECHARTS_DATA.auditStatus = _data.auditingState;
                GLOBAL_ECHARTS_DATA.auditMan = _data.auditMan;

                CaseAjax.getApplicationAuditResultInfo(_data.applicationId, CaseTpl.buildApplicationAuditResultInfo);

                CaseTpl.buildApproveDetails(_data);

                //获取基本信息
                Case.getBasicInfo(_data.platformId);

                //设备关联数案件数
                // Case.getSummaryEquipment(_data.customerId, _data.appName);

                //设备信息查询
                Case.getEquipmentInformation(_data.applicationId, _data.source)

                // 同盾多维度多投
                Case.getTongDunTuoDouReport(_data.applicationId, _data.platformId);

                // 客户最新登录时间查询
                Case.getLatestLoginTime(_data.customerId);

                // 手机号归属地身份证归属地查询 findMgUserMessage
                Case.getPhoneNoBelong(_data.certCode, _data.phoneNum);

                // 查询ip归属地 //findIPAddress
                Case.getIPAddress(_data.loanIp);

                // 手机使用时长 findPhoneUseTime
                Case.getPhoneUseTime(_data.applicationId);

                // 紧急联系人查询findIcePerson
                Case.getIcePerson(_data.applicationId);

                // 查询客户所属公司信息jobInformation
                Case.getJobInfo(_data.platformId);

                // 查询短信数getDxSum
                Case.getPhoneMessageSum(_data.customerId, _data.appName);

                // 查询app应用数 ??
                Case.getAppSum(_data.customerId, _data.appName);

                //关联分析数据查询 getList ??
                Case.getAnalysisData(_data.customerId, _data.appName);

                // 通讯录页面传参
                // directories(customerId,application.appName);

                //活体识别图片
                Case.getLivingBodyImg(_data.platformId);
            });
        },
        getBasicInfo : function(_platformId){
            CaseAjax.getBasicInfo(_platformId, CaseTpl.buildBasicInfo);
        },
        getSummaryEquipment : function(_customerId, _appName){
            CaseAjax.getSummaryEquipment(_customerId, _appName, CaseTpl.buildSummaryEquipment);
        },
        getEquipmentInformation : function(_applicationId, _appName){
            CaseAjax.getEquipmentInformation(_applicationId, _appName, CaseTpl.buildEquipmentInformation);
        },
        getTongDunTuoDouReport : function(_applicationId, _platformId){
            CaseAjax.getTongDunTuoDouReport(_applicationId, _platformId, CaseTpl.buildTongDunTuoDouReport);
        },
        getLatestLoginTime : function(_customerId){
            CaseAjax.getLatestLoginTime(_customerId, CaseTpl.buildLatestLoginTime)
        },
        getPhoneNoBelong : function(_certCode, _phoneNum){
            CaseAjax.getPhoneNoBelong(_certCode, _phoneNum, CaseTpl.buildPhoneNoBelong);
        },
        getIPAddress: function(_ip){
            CaseAjax.getIPAddress(_ip, CaseTpl.buildIPAddress);
        },
        getPhoneUseTime : function (_applicationId){
            CaseAjax.getPhoneUseTime(_applicationId, CaseTpl.buildPhoneUseTime);
        },
        getJobInfo: function(_platformId){
            CaseAjax.getJobInfo(_platformId, CaseTpl.buildJobInfo);
        },
        getPhoneMessageSum: function(_customerId, _appName){
            CaseAjax.getPhoneMessageSum(_customerId, _appName, CaseTpl.buildPhoneMessageSum);
        },
        getAppSum: function(_customerId, _appName){
            CaseAjax.getAppSum(_customerId, _appName, CaseTpl.buildAppSum);
        },
        getAnalysisData: function(_customerId, _appName){
            CaseAjax.getAnalysisData(_customerId, _appName, CaseTpl.buildAnalysisData);
        },
        getIcePerson: function(_applicationId){
            CaseAjax.getIcePerson(_applicationId, CaseTpl.buildIcePerson);
        },
        getLivingBodyImg: function(_platformId){
            CaseAjax.getLivingBodyImg(_platformId, CaseTpl.buildLivingBodyImg);
        },
        getValidateTimes: function(_customerId, _appName){
            CaseAjax.getValidateTimes(_customerId, _appName, CaseTpl.buildValidateTimes);
        },

        /**
         * 保存
         */
        processSave: function(){
            var _menuNames = "人工信审";
            if(GLOBAL_AUDIT_STATUS_SELECT == undefined || GLOBAL_AUDIT_STATUS_SELECT == null || GLOBAL_AUDIT_STATUS_SELECT ==""){
                parent.layer.msg("请选择审核结果!" , {icon: 2});
                return false;
            }

            var _applicationId = $("#js-hidden-applicationId").val().trim();// 申请单编号
            var _refuseCode = $("#js-hidden-refuse-code").val().trim(); // 已选拒绝原因码
            var _rgCreditMoney = $("#js-rgCreditMoney").val().trim();// 人工额度
            var _refuseName = $("#js-refuse-name").val().trim(); // 已选拒绝原因

            var _isCallPhone = 0;// 是否与本人电核
            var _netcheckNoAbnormal = 0;// 网查是否异常
            var _rgAuditingRemark = $("#js-rgAuditingRemark").val().trim();// 人工审核备注

            //通过
            if(GLOBAL_AUDIT_STATUS_SELECT == AUDIT_STATUS_SUCCESS){
                if(_refuseCode != '' || _refuseName != ''){
                    parent.layer.msg("通过状态下拒绝原因请设置为空!" , {icon: 2});
                    return ;
                };
                //判断 授信 额度
                if(_rgCreditMoney == '' || _rgCreditMoney === undefined || _rgCreditMoney == null){
                    parent.layer.msg("人工额度不能为空!" , {icon: 2});
                    return false;
                };

                if (parseInt(_rgCreditMoney) < 0) {
                    parent.layer.msg("人工额度不能小于零！");
                    return false;
                }
                if (parseInt(_rgCreditMoney) % 100 != 0) {
                    parent.layer.msg("人工额度必须为100的倍数！");
                    return false;
                }
                if (parseInt(_rgCreditMoney) > 1000) {
                    parent.layer.msg("人工额度不能大于1000！");
                    return false;
                }
            }
            if(GLOBAL_AUDIT_STATUS_SELECT == AUDIT_STATUS_FAIL){
                if(_refuseCode == '' || _refuseName ==  ''){
                    parent.layer.msg("拒绝原因码或拒绝原因不能为空!" , {icon: 2});
                    return ;
                };
                _rgCreditMoney = 0;
            }

            layer.confirm('您确定提交表单吗？',
                { btn: ['确定','取消'] }, // 按钮
                function(){
                    if(GLOBAL_AUDIT_STATUS_SELECT == AUDIT_STATUS_FAIL){
                        // 人工拒绝
                        // Case.processAddToBlacklist();
                        Case.saveToBuSpApproval(AUDIT_STATUS_FAIL);
                    }else if(GLOBAL_AUDIT_STATUS_SELECT == AUDIT_STATUS_SUCCESS){
                        // 人工同意
                        Case.saveToBuSpApproval(AUDIT_STATUS_SUCCESS);
                    }else{
                        parent.layer.msg("系统错误请联系管理");
                    }
                }
            );
        },

        /**
         * 根据 url 参数 判断跳转的是 [查看详情页面] 或者 [审核页面]
         */
        processJudgePageAuditResult : function(){
            if(_globalOpen != '1'){
                //进入查看详情页面
                $("#js-btn-option").hide();
                $("#js-rgCreditMoney").attr("readonly",true);
                $("#js-rgAuditingRemark").attr("readonly",true);
            }else{
                $("#js-btn-option").show();
            }
        },

        /**
         * 加入黑名单
         */
        processAddToBlacklist: function(){
            var menuNames = '个人案件详情';

            // $("#js-submit").attr("disabled",true);// 提交 按钮只读
            // $("#js-return-case-pool").unbind(); // 退回 按钮只读
            // var rgCreditMoney = $("#rgCreditMoney").val('0.00');
            // $("#rgCreditMoney").attr("readonly",true);
            // $("input[name='checkbox']").attr("disabled",false);

            var refuseCode = $("#js-refuse-name").val().trim(); // 已选拒绝原因码
            var refuseName = $("#js-hidden-refuse-code").val().trim(); // 已选拒绝原因

            if(refuseCode == '' || refuseName === ''){
                parent.layer.msg("拒绝原因不能为空!" , {icon: 2});
                return false;
            };

            var _applicationId = $("#js-hidden-applicationId").val().trim();// 申请单编号
            var _rgAuditingState = '3'; // 审核结果
            var _rgCreditMoney = '0';// 人工额度

            CaseParam.cleanParamBlacklist();

            PARAM_BLACKLIST.phoneNum = $("#js-hidden-phone").val(); //手机号
            PARAM_BLACKLIST.name = $("#js-hidden-username").val().trim(); // 姓名
            PARAM_BLACKLIST.certCode = $("#js-hidden-certCode1").val(); //身份证号
            PARAM_BLACKLIST.customerId = $("#js-hidden-customerId").val(); //客户Id
            PARAM_BLACKLIST.platformId = $("#js-hidden-platformId").val();
            PARAM_BLACKLIST.certType = $("#js-hidden-certType").val();
            PARAM_BLACKLIST.appName = $("#js-hidden-appName").val();
            PARAM_BLACKLIST.dsSource = '01';
            PARAM_BLACKLIST.isFailure = '1';
            PARAM_BLACKLIST.rejectFlag = '0';
            PARAM_BLACKLIST.rejectType = '22';
            PARAM_BLACKLIST.sNumber = _applicationId;
            PARAM_BLACKLIST.remark =  $("#js-rgAuditingRemark").val().trim();
            PARAM_BLACKLIST.menuNames= '个人案件详情'; // 菜单名称
            PARAM_BLACKLIST.functionNames = (_applicationId !=''  ? "人工信审提交数据":'')

            CaseAjax.reqAddToBlacklist(PARAM_BLACKLIST);
        },

        /**
         * 退回案件池
         */
        processReturnCasePool: function(){
            var _applicationId = $("#js-hidden-applicationId").val();
            CaseAjax.reqReturnCasePool(_applicationId, CaseTpl.buildReturnCasePool);
        },

        /**
         * 保存备注
         */
        processSaveRemark: function(){
            var _auditRemark = $("#js-rgAuditingRemark").val();
            if(null == _auditRemark || "" == _auditRemark){
                parent.layer.msg("请填写备注");
                return;
            }
            var _applicationId = $("#js-hidden-applicationId").val();
            CaseAjax.reqSaveRemark(_applicationId, _auditRemark, function(_data){
                parent.layer.msg("保存备注成功");
            });
        },

        /**
         * 保存 bu_sp_approval 表数据
         */
        saveToBuSpApproval: function(_rgAuditingState){
            CaseParam.cleanBuSpApproval();

            var _applicationId = $("#js-hidden-applicationId").val();
            var _rgCreditMoney = $("#js-rgCreditMoney").val().trim();

            PARAM_BU_SP_APPROVAL.refuseCode = $("#js-hidden-refuse-code").val();
            PARAM_BU_SP_APPROVAL.refuseName = $("#js-refuse-name").val();

            PARAM_BU_SP_APPROVAL.rgCreditMoney = (_rgCreditMoney == null ? "0" : _rgCreditMoney);
            PARAM_BU_SP_APPROVAL.applicationId = _applicationId;

            PARAM_BU_SP_APPROVAL.rgAuditingState = _rgAuditingState;

            PARAM_BU_SP_APPROVAL.netcheckNoAbnormal = 0;
            PARAM_BU_SP_APPROVAL.isCallPhone = 0 ;
            PARAM_BU_SP_APPROVAL.rgAuditingRemark = $("#js-rgAuditingRemark").val().trim();
            PARAM_BU_SP_APPROVAL.menuNames = "人工信审";
            PARAM_BU_SP_APPROVAL.functionNames = (_applicationId !=''  ? "人工信审提交数据":'');

            CaseAjax.reqAddToBuSpApproval(PARAM_BU_SP_APPROVAL);
        },

        /**
         * 人工授信额度填充
         * @param _event
         */
        processManualFileCreditMoney: function(_auditStatus){
            GLOBAL_AUDIT_STATUS_SELECT = _auditStatus;

            if(_auditStatus == AUDIT_STATUS_FAIL){

                //清空已经选中的拒绝原因
                $("#js-refuse-name").val("");
                $("#js-hidden-refuse-code").val("");

                $("#js-rgCreditMoney").val('0').attr("readonly", true);
                var objEvt = $._data($("#js-btn-refuse-reason")[0],'events');
                if(undefined == objEvt){
                    $("#js-btn-refuse-reason").on("click", Case.openRefuseReasonDialog)
                        .css({ "color": "#31B8F7", "border": "1px solid #31B8F7"});
                }
                $("p").css("background-color");
            }else if(_auditStatus == AUDIT_STATUS_SUCCESS){
                $("#js-rgCreditMoney").val(1000).attr("readonly", false);
                $("#js-refuse-name").val('');
                $("#s-hidden-refuse-code").val('');
                $("#js-btn-refuse-reason").css({ "color": "#DEDEDE", "border": "1px solid #DEDEDE" }).unbind();
            }else{
                alert("系统错误,请联系管理员!")
            }
        },

        /**
         * 数据报表列表 假分页
         * @param _event
         */
        dataReportPage: function(_event){
            //js-data-report
            var $li = $("#js-data-report li");

            var _length = $li.length;
            var _totalPage = (_length - 1) / DATA_REPORT_PAGE_SIZE + 1;

            var _currentPageNo = $("#js-data-report").data("current-page-no")

            if(_event.data == "next"){
                _currentPageNo += 1;
            }else if(_event.data == "pre"){
                _currentPageNo -= 1;
            }else{
                return;
            }
            if(_currentPageNo <= 0 || _currentPageNo > _totalPage){
                return;
            }
            $("#js-data-report").data("current-page-no", _currentPageNo);
            $("#js-data-report-page-show").html(_currentPageNo + "/" + _totalPage);

            var _showLeft = (_currentPageNo - 1) * DATA_REPORT_PAGE_SIZE;
            var _showRight = _currentPageNo * DATA_REPORT_PAGE_SIZE - 1;

            var i = 0;
            $("#js-data-report li").each(function(_index, _elem){
                if(_showLeft <= i && i <= _showRight){
                    $(_elem).show();
                }else{
                    $(_elem).hide();
                }
                i++;
            });
        },

        //信用风险评分报告
        openCreditRiskRating : function(){
            var applicationId = $("#js-hidden-applicationId").val();
            var username = $("#js-hidden-username").val();
            var href = getCtxPath() + "/index/views/customerManager/creditRiskRating.html?applicationId="+applicationId
            var data = {
                "title": username+'信用风险评分报告',
                "icon": "&#xe621;",
                "href": href
            }
            window.parent.topTab.tabAdd(data);
        },
      
        openData91: function(){
            var applicationId = $("#js-hidden-applicationId").val();
            var username = $("#js-hidden-username").val();
            var href = getCtxPath() + "/index/views/customerManager/xczxInfo.html?applicationId="+applicationId
            var data = {
                "title": username+'91征信报告',
                "icon": "&#xe621;",
                "href": href
            }
            window.parent.topTab.tabAdd(data);
        },
        openOperators: function(){
            //默认是白骑士
            var applicationId = $("#js-hidden-applicationId").val();
            var username = $("#js-hidden-username").val();
            var href = getCtxPath() + "/index/views/customerManager/bqsReport.html?applicationId="+applicationId
            var data = {
                "title": username+'白骑士报告',
                "icon": "&#xe621;",
                "href": href
            }
            window.parent.topTab.tabAdd(data);
        },
        openShangHaiCredit: function(){
            var applicationId = $("#js-hidden-applicationId").val();
            var username = $("#js-hidden-username").val();
            var href = getCtxPath() + "/index/views/customerManager/shCredit.html?"+"applicationId="+applicationId;
            var data = {
                "title": username+'上海资信报告',
                "icon": "&#xe621;",
                "href": href
            }
            window.parent.topTab.tabAdd(data);
        },
        openTongDun: function(){
            var applicationId = $("#js-hidden-applicationId").val();
            var username = $("#js-hidden-username").val();
            var href = getCtxPath() + "/index/views/customerManager/tdFqz.html?"+"applicationId="+applicationId;
            var data = {
                "title": username+'同盾报告',
                "icon": "&#xe621;",
                "href": href
            }
            window.parent.topTab.tabAdd(data);
        },
        openYiJiFu: function(){
            var applicationId = $("#js-hidden-applicationId").val();
            var username = $("#js-hidden-username").val();
            var href = getCtxPath() + "/index/views/customerManager/yjfBlackList.html?"+"applicationId="+applicationId;
            var data = {
                "title": username+'易极付报告',
                "icon": "&#xe621;",
                "href": href
            }
            window.parent.topTab.tabAdd(data);
        },
        openCallRecord: function(){
            var customerId = $("#js-hidden-customerId").val();
            var appName = $("#js-hidden-appName").val();
            var username = $("#js-hidden-username").val();
            var href = getCtxPath() + "/index/views/customerManager/callList.html?id="+customerId+"&appName="+appName;
            var data = {
                "title": username+'通话记录',
                "icon": "&#xe621;",
                "href": href
            }
            window.parent.topTab.tabAdd(data);
        },
        openAddressBook: function(){
            var customerId = $("#js-hidden-customerId").val();
            var appName = $("#js-hidden-appName").val();
            var username = $("#js-hidden-username").val();
            var href = getCtxPath() + "/index/views/customerManager/phoneAddress.html?id="+customerId+"&appName="+appName;

            var index = layer.open({
                type: 2,
                closeBtn: 1, // 0：不显示关闭按钮
                shade: 0.4, // 遮罩透明度
                area: ['840px', '615px'],
                skin: 'layui-layer-rim', // 加上边框
                fixed: false, // 不固定
                maxmin: true, // 开启最大化最小化按钮
                anim: 2,
                title: username +' - 手机通讯录',// 设置false表示不显示
                content: href
            });
        },

        openExternalSite: function(){
            // 外部网址
            var phoneNum=$("#js-hidden-applyphone").val();
            var certCode=$("#js-hidden-certCode1").val();
            var customerCompany=$("#js-hidden-companyName").val();
            var customerEmail=$("#js-hidden-customerEmail").val();
            var href = getCtxPath() + "/index/views/approveDetails/approveDetailsForSeach.html?phoneNum="+phoneNum+"&certCode="+certCode+"&customerCompany="+customerCompany+"&customerEmail="+customerEmail;
            var data = {
                "title": "客户信息查询",
                "icon": "&#xe60c;",
                "href": href
            }
            window.parent.topTab.tabAdd(data)
        },

        openSystemLog: function(){
            // 系统日志
            var applicationId=$("#js-hidden-applicationId").val();
            var href=getCtxPath() + "/index/views/approve/customerLoans.html?id="+applicationId;
            var data = {
                "title": "客户借款申请扩展明细",
                "icon": "&#xe60c;",
                "href": href
            }
            window.parent.topTab.tabAdd(data);
        },

        /**
         * 身份证 图片
         */
        openPicCertificate:function(){
            var _platformId = $("#js-hidden-platformId").val();
            layer.open({
                type: 2,
                title: '身份证图片详情',// 设置false表示不显示
                closeBtn: 1, // 不显示关闭按钮
                shade: 0.4, // 遮罩透明度
                area: ['650px', '750px'],
                skin: 'layui-layer-rim', // 加上边框
                fixed: false, // 不固定
                maxmin: true, // 允许屏幕最小化
                anim: 2,
                content: ['idcardimage.html?platformId=' + _platformId], // iframe的url，no代表不显示滚动条
            });
        },

        /**
         * 头像弹出框
         */
        openAvatar:function(_url){
            layer.open({
                type: 2,
                title: '头像图片详情',// 设置false表示不显示
                closeBtn: 1, // 不显示关闭按钮
                shade: 0.4, // 遮罩透明度
                area: ['387px', '557px'],
                skin: 'layui-layer-rim', // 加上边框
                fixed: false, // 不固定
                maxmin: true, // 允许屏幕最小化
                anim: 2,
                content: _url, // iframe的url，no代表不显示滚动条
            });
        },

        openRefuseReasonDialog: function(){
            var customerId =  $("#js-hidden-customerId").val();
            var appName = $("#js-hidden-appName").val();
            var username = $("#js-hidden-username").val();
            var _hiddenRefuseCode = $("#js-hidden-refuse-code").val();

            var href = getCtxPath() + "/index/views/approveDetails/refuseReason.html?id="+customerId+"&appName="+appName +"&refuseCode="+ _hiddenRefuseCode;
            var index = layer.open({
                type: 2,
                closeBtn: 1, //0：不显示关闭按钮
                shade: 0.4, //遮罩透明度
                area: ['900px', '700px'],
                skin: 'layui-layer-rim', //加上边框
                fixed: false, //不固定
                maxmin: true,  //开启最大化最小化按钮
                anim: 2,
                title: username + '-拒绝原因',// 设置false表示不显示
                content: href,
                btn: ['清空', '保存', '取消'], //可以无限个按钮,
                btn1: function(_index, _layero ){
                   var _contents = layer.getChildFrame('body', _index).contents();
                   _contents.find("#js-refuse-reason-selected").empty();
                   _contents.find("#js-refuse-reason-list a").removeClass("refuse_title_a_hover");

                    //$("#js-refuse-name").val("");
                    //$("#js-hidden-refuse-code").val("");

                    return false;
                },
                btn2: function(_index, layero){
                    var _contents = layer.getChildFrame('body', _index).contents();
                    var _refuseHtml = _contents.find("#js-refuse-reason-selected a");
                    if(_refuseHtml.length == 0){
                        alert("请选择拒绝原因");
                        return false;
                    }
                    var _refuseCode = [];
                    var _refuseName = [];
                    _refuseHtml.each(function(_index, _html){
                        var $tempThis = $(this);
                        _refuseCode.push($tempThis.data("refuse-code"));
                        _refuseName.push($tempThis.data("refuse-name"));
                    });

                    $("#js-refuse-name").val(_refuseName.join(","));
                    $("#js-hidden-refuse-code").val(_refuseCode.join(","));

                },
                btn3: function(_index, layero ){

                }
            });
        },

        /**
         * 发送短信消息
         */
        sendMessage: function(_mobile, _contents){
            var _mobile = $("#js-hidden-applyphone").val();
            console.info(_mobile);
            var _contents = "尊敬的用户您好，为保证您审核顺利通过，请留意接听小牛闪贷唯一官方审核电话021-61984028";
            CaseAjax.sendMessage(_mobile, _contents);
        },

    };

    var CaseBinding = {

        /**
         * 数据报告假分页
         */
        bindDataReportBtn : function(){
            $("#js-data-report-pre").on("click",null, "pre", Case.dataReportPage);
            $("#js-data-report-next").on("click",null, "next", Case.dataReportPage);
        },

        /**
         * 绑定 数据报告
         */
        bindDataReport: function(){
        	$("#js-credit_risk_rating").on("click", Case.openCreditRiskRating);
           
            $("#js-data-report-91").on("click", Case.openData91);
            $("#js-data-operators").on("click", Case.openOperators);
            $("#js-data-shanghai-credit").on("click", Case.openShangHaiCredit);
            $("#js-data-tong-dun").on("click", Case.openTongDun);
            $("#js-data-yi-ji-fu").on("click", Case.openYiJiFu);
            $("#js-data-call-record").on("click", Case.openCallRecord);
            $("#js-data-address-book").on("click", Case.openAddressBook);
        },

        bindOtherBtn: function(){
            $("#js-btn-external-site").on("click", Case.openExternalSite);
            $("#js-btn-system-log").on("click", Case.openSystemLog);
            $("#js-pic-certificate").on("click", Case.openPicCertificate);
            $("#js-sendMessage").on("click", Case.sendMessage);
            $("#js-add-blacklist").on("click", Case.processAddToBlacklist);
            if(_globalOpen == '1'){
                $("#js-btn-refuse-reason").on("click", Case.openRefuseReasonDialog);
            }else{
                $("#js-btn-refuse-reason").css({ "color": "#DEDEDE", "border": "1px solid #DEDEDE"});
            }

            $("#js-return-case-pool").on("click", Case.processReturnCasePool);

            $("input[name=auditStatus]").change(Case.processManualFileCreditMoney);

            $("#js-submit").on("click", Case.processSave);

            $("#js-rgCreditMoney").on("keyup", function(){
                if(this.value.length==1){
                    this.value=this.value.replace(/[^1-9]/g,'')
                }else{
                    this.value=this.value.replace(/\D/g,'')
                }
            })
        }
    }

    var CaseAjax = {
        getApproveDetails : function(_appSerialNumber, _callBack){
            $.ajax({
                url :getCtxPath() + "/backend/application/findApproveDetails.do",
                method: "post",
                dataType:"json",
                data: {
                    appSerialNumber: _appSerialNumber
                },
                success: function(_resp){
                    if(_resp.code == 200){
                        _callBack(_resp.list.data);
                    }else{
                        //返回错误
                    }
                }
            });
        },

        /**
         * 查询基本信息
         * @param _platformId
         * @param _callback
         */
        getBasicInfo : function(_platformId, _callback){
            $.ajax({
                url :getCtxPath() + "/backend/approve/findCustomerBasicInformation.do",
                method: "post",
                dataType:"json",
                data: {
                    platformId: _platformId
                },
                success: function(_resp){
                    if(_resp.code == 200){
                        _callback(_resp.list.data);
                    }else{
                        console.info(_resp);
                    }
                }
            });
        },

        /**
         * 设备关联数案件数
         * @param customerId
         * @param appName
         */
        getSummaryEquipment : function(_customerId, _appName, _callback){
            $.ajax({
                url :getCtxPath() + "/backend/summary/summaryEquipment.do?v=" + new Date().getTime(),
                method: "post",
                dataType:"json",
                data: {
                    customerId : _customerId,
                    appName: _appName
                },
                success: function(_resp){
                    if (_resp.code == 200) {
                        _callback(_resp.list.data);
                    }else{
                    }
                }
            });
        },

        /**
         * 设备信息查询
         * @param _applicationId
         * @param _appName
         * @param _callback
         */
        getEquipmentInformation : function(_applicationId, _appName, _callback){
            $.ajax({
                url: getCtxPath() + "/backend/approve/findEquipmentInformation.do",
                method: "post",
                dataType:"json",
                data: {
                    applicationId: _applicationId,
                    appName: _appName
                },
                success: function(_resp){
                    if (_resp.code == 200) {
                        _callback(_resp.list.data);
                    }else{
                    }
                }
            });
        },

        /**
         *  同盾多维度多投
         * @param _applicationId
         * @param _platformId
         * @param _callback
         */
        getTongDunTuoDouReport : function(_applicationId, _platformId, _callback){
            $.ajax({
                url : getCtxPath() + "/backend/tdFqz/getTdReportByApplicationId.do",
                method : "post",
                dataType : "json",
                data : {
                    applicationId : _applicationId,
                    platformId: _platformId
                },
                success : function(_resp) {
                    if(_resp.code == 200){
                        _callback(_resp.list.data);
                    }else{
                        $("#js-platformDimensionDetail").append('<tr><td colspan="3" style="color:red;">'+data.message+'</td></td>');
                        return;
                    }
                },
                error : function() {
                    alert("获取同盾反欺诈报告失败,请尝试重新获取！");
                }
            });
        },

        /**
         * 客户最新登录时间查询
         * @param _customerId
         * @param _callback
         */
        getLatestLoginTime: function(_customerId, _callback){
            $.ajax({
                url :getCtxPath() + "/backend/approve/findLatestLoginTime.do",
                method: "post",
                dataType:"json",
                data: {
                    customerId: _customerId
                },
                success: function(_resp){
                    if(_resp.code == 200){
                        _callback(_resp.list.data)
                    }else {
                        //
                    }
                }
            });
        },

        /**
         * 手机号归属地身份证归属地查询
         * @param _certCode
         * @param _phoneNum
         */
        getPhoneNoBelong: function(_certCode, _phoneNum, _callback){
            $.ajax({
                url :getCtxPath() + "/backend/approve/findMgUserMessage.do",
                method: "post",
                dataType:"json",
                data: {
                    certCode1: _certCode,
                    phone: _phoneNum
                },
                success: function(_resp){
                    if(_resp.code == 200){
                        _callback(_resp.list.data);
                    }else{
                        //
                    }
                }
            });
        },

        /**
         * ip归属地查询
         * @param _ip
         * @param _callback
         */
        getIPAddress : function(_ip, _callback){
            $.ajax({
                url :getCtxPath() + "/backend/approve/findIPAddress.do",
                method: "post",
                dataType:"json",
                data: {
                    ip: _ip
                },
                success: function(_resp){
                    _callback(_resp);
                }
            });
        },

        /**
         * 手机号码使用时长
         * @param applicationId
         * @param _callback
         */
        getPhoneUseTime(applicationId, _callback){
            $.ajax({
                url :getCtxPath() + "/backend/approve/findPhoneUseTime.do",
                method: "post",
                dataType:"json",
                data: {
                    applicationId: applicationId
                },
                success: function(_resp){

                    if(_resp.code == 200){
                        _callback(_resp.list.data);
                    }else{
                        //
                    }
                }
            });
        },

        /**
         * 客户所属公司信息查询
         * @param _platformId
         * @param _callback
         */
        getJobInfo: function(_platformId, _callback){
            $.ajax({
                url :getCtxPath() + "/backend/approve/jobInformation.do",
                method: "post",
                dataType:"json",
                data: {
                    platformId: _platformId
                },
                success: function(_resp){
                    if(_resp.code == 200){
                        _callback(_resp.list.data);
                    }else{
                        //
                    }
                }
            });
        },

        /**
         * 查询短信数
         * @param customerId
         * @param appName
         * @param _callback
         */
        getPhoneMessageSum: function(_customerId,_appName, _callback){
            $.ajax({
                url :getCtxPath() + "/backend/summary/summaryFewMessage.do?v=" + new Date().getTime(),
                method: "post",
                dataType:"json",
                data: {
                    customerId:_customerId,
                    appName:_appName
                },
                success: function(_resp){
                    if (_resp.code == 200) {
                        _callback(_resp.list.data);
                    }else{
                        //
                    }
                }
            });
        },

        /**
         * 查询app应用数
         * @param _customerId
         * @param _appName
         * @param _callback
         */
        getAppSum: function (_customerId, _appName, _callback) {
            $.ajax({
                url :getCtxPath() + "/backend/summary/summaryAppInfo.do?v=" + new Date().getTime(),
                method: "post",
                dataType:"json",
                data: {
                    customerId:_customerId,
                    appName:_appName
                },
                success: function(data){
                    if (data.code == 200) {
                        _callback(data.list.data);
                    }else{

                    }
                }
            });
        },

        /**
         * 关联分析数据查询
         * @param _customerId
         * @param _appName
         * @param _callback
         */
        getAnalysisData: function(_customerId, _appName, _callback){
            $.ajax({
                url :getCtxPath() + "/backend/approve/cognateAnalysis.do",
                method: "post",
                dataType:"json",
                data: {
                    customerId: _customerId,
                    appName: _appName
                },
                success: function(_resp){
                    if (_resp.code == 200) {
                        _callback(_resp.list.data);
                    }else{

                    }
                }
            });
        },

        /**
         *  紧急联系人查询
         * @param _applicationId
         * @param _callback
         */
        getIcePerson: function(_applicationId, _callback){
            $.ajax({
                url :getCtxPath() + "/backend/approve/findIcePerson.do",
                method: "post",
                dataType:"json",
                data: {
                    applicationId: _applicationId
                },
                success: function(data){
                    if(data.code == 200){
                        _callback( data.list.data);
                    }else{
                        //
                    }
                }
            });
        },

        /**
         * 获取活体识别的图片
         */
        getLivingBodyImg: function(_platformId, _callback){
            $.ajax({
                url :getCtxPath() + "/backend/approve/faceRecognitionScore.do",
                method: "post",
                dataType:"json",
                data: {
                    platformId: _platformId
                },
                success: function(data){
                    if (data.list.data!=null) {
                        _callback(data.list.data);
                    }else{
                    }
                },
                error: function(){
                    layer.msg("错误" , {icon: 2});//提示信息
                }
            });
        },

        /**
         * 获取用户验证时长
         * @param _customerId
         * @param _appName
         * @param _callback
         */
        getValidateTimes: function (_customerId, _appName, _callback) {
            $.ajax({
                url : getCtxPath() + "/backend/application/getTimes.do?v=" + new Date().getTime(),
                method: "post",
                dataType:"json",
                data: {
                    customerId : _customerId,
                    appName: _appName
                },
                success: function(_resp){
                    if(_resp.code == 200){
                        _callback(_resp.list.data);
                    }else{
                        console.info("getValidateTimes=" + _resp);
                    }
                }
            });
        },

        /**
         * 发送消息
         * @param _mobile
         * @param _contents
         */
        sendMessage: function(_mobile, _contents){
            $.ajax({
                method: "post",
                dataType: "json",
                data: {
                    mobile: _mobile,
                    contents:_contents
                },
                url: getCtxPath() + "/backend/approval/sendMessage.do?v=" + new Date().getTime(),// 请求服务器的url
                success: function(data,status) {
                    if(data.code==200){
                        layer.msg("发送成功！" , {icon: 1});// 提示信息
                    }
                    if(data.code==6006){
                        layer.msg("发送失败！手机号码不正确" , {icon: 2});// 提示信息
                    }
                },
                error: function(){
                    layer.msg("发送失败" , {icon: 2});// 提示信息
                },
                complete: function(){
                }
            });
        },

        /**
         * 获取案件审核结果详情数据
         */
        getApplicationAuditResultInfo: function(_applicationId, _callback){

            $.ajax({
                url :getCtxPath() + "/backend/buspapproval/queryBuSpapprovalInfo.do?v=" + new Date().getTime(),// 请求服务器的url
                method: "post",
                dataType:"json",
                data: {
                    applicationId: _applicationId
                },
                success: function(_resp){
                    _callback(_resp.list.data);
                }
            });
        },

        reqReturnCasePool: function(_applicationId, _callback){
            var _menuNames = '人工信审';
            $.ajax({
                url: getCtxPath() + "/backend/application/updatePrivateApprove.do?v=" + new Date().getTime(),
                method: "post",
                dataType: "json",
                data: {
                    applicationId: _applicationId,
                    menuNames: _menuNames, // 菜单名称
                    functionNames: (_applicationId != '' ? "退回公共案件池" : '') // 按钮功能名称
                },
                success: function (_resp) {
                    if (_resp.code == 4001) {
                        _callback(_resp);
                    }else{
                        console.info(_resp);
                    }
                }
            });
        },

        reqSaveRemark:function(_applicationId, _auditRemark, _callback){
            $.ajax({
                url: getCtxPath() + "/backend/buspapproval/saveRgAuditingRemark.do?v=" + new Date().getTime(),
                method: "post",
                dataType: "json",
                data: {
                    applicationId: _applicationId,
                    rgAuditingRemark: _auditRemark,
                },
                success: function (_resp) {
                    if (_resp.code == 200) {
                        _callback(_resp);
                    }else{
                        console.info(_resp);
                    }
                }
            });
        },

        reqAddToBlacklist: function(_param){
            $.ajax({
                url : getCtxPath() + "/backend/blacklist/save.do?v=" + new Date().getTime(),
                method: "post",
                dataType:"json",
                data: _param,
                success: function(_resp){
                    if(_resp.code == 2001){
                        Case.saveToBuSpApproval(AUDIT_STATUS_FAIL);
                    }else{
                        layer.msg(_resp.message , {icon: 2})
                    }
                }
            });
        },

        /**
         *  保存 bu_sp_approval 数据
         */
        reqAddToBuSpApproval: function(_param){
            $.ajax({
                url : getCtxPath() + "/backend/buspapproval/save.do?v=" + new Date().getTime(),
                method: "post",
                dataType:"json",
                data: _param,
                success: function(data){
                    if(data.code == 2001){
                        layer.msg(data.message , {icon: 1});
                        setTimeout(function(){
                            var tab = parent.tab;
                            tab.deleteTab(tab.getCurrentTabId());
                            window.parent.getList();
                        }, 1000);
                    }else{
                        layer.msg(data.message , {icon: 2})
                    }
                },
                fail: function(msg) {
                    layer.msg(data.message , {icon: 2});
                },
            });
        }
    };

    var CaseTpl = {

        buildApproveDetails: function(_data){
            //绘制页面
            $("#js-name").html(_data.name);// 用户姓名
            // $("#phoneNum").html(application.phoneNum);// 用户手机号
            $("#js-certCode").html(_data.certCode);// 身份证号
            $("#js-applyTime").html(_data.createTime.substring(0,19)); //提交申请时间
            $("#js-appSerialNumber").html(_data.appSerialNumber); //案件编号

            if(_data.appLevel == '1'){
                $("#js-old-customer").show();
            }

            //绘制 hidden input
            $("#js-hidden-customerId").val(_data.customerId);// APP应用客户编号
            $("#js-hidden-applicationId").val(_data.applicationId);// 申请单编号
            $("#js-hidden-platformCustomerId").val(_data.platformId);// 平台用户编号
            $("#js-hidden-platformId").val(_data.platformId);// 平台用户编号
            $("#js-hidden-certCode1").val(_data.certCode);// 身份证号
            $("#js-hidden-username").val(_data.name);// 用户姓名
            $("#js-hidden-appName").val(_data.appName);// APP名称：1-闪贷；2-速贷
            $("#js-hidden-certType").val(_data.certType);// 证件类型（1.身份证 2.护照  // 3.其他）
            $("#js-hidden-phone").val(_data.phoneNum);// 用户手机号

        },

        buildBasicInfo : function(_data){
            if(_data != null) {
                $("#name").html(_data.name);

                var genderStr = SEX[_data.gender];
                if(null != genderStr){
                    $("#js-gender").html(genderStr);
                }

                $("#js-age").html(_data.age);

                var ifMarriageStr = MARRIAGE[_data.ifMarriage]
                if(null != ifMarriageStr){
                    $("#js-ifMarriage").html(ifMarriageStr);
                }

                $("#js-certCode").html(_data.certCode);

                var educationIdStr = EDUCATION[_data.educationId];

                if(null != educationIdStr){
                    $("#js-educationId").html(educationIdStr);
                    //计算工作年限
                    var eduAge = EDUCATION_AGE[_data.educationId];
                    var workingYear = _data.age - eduAge;
                    $("#js-workingYear").html(workingYear +"年");
                }else{
                    $("#js-educationId").html("未知");

                }

                $("#js-expirationDate").html(_data.expirationDate);
                $("#js-issueInstitution").html(_data.issueInstitution);
                $("#js-registeredAddress").html(_data.registeredAddress);
                $("#js-registeredAddress").attr("title", _data.registeredAddress);
                $("#js-applyphone").html(_data.phoneNum);

                $("#js-hidden-applyphone").val(_data.phoneNum);
                $("#js-hidden-customerEmail").val(_data.email);

                if(null != _data.nation && "" != _data.nation){
                    $("#js-nation").html(_data.nation);
                }
            }
        },

        buildSummaryEquipment: function(_data){
            $("#equipmentNum").html(_data.device_id_count);
            $("#applicationNum").html(_data.customerIdCount);
        },

        buildEquipmentInformation: function(_data){
            if (_data != null) {
                $("#js-cellphon-brand").html(_data.equipmentName);
                $("#js-gprsAdress").html(_data.address);
            }
        },

        buildTongDunTuoDouReport: function(_data){
            if(_data == "APPLICATION_MONGO_NULL"){
                return;
            }
            var riskItems = _data.result_desc.ANTIFRAUD.risk_items;
            if(riskItems!=null){
                var html = [];
                $.each(riskItems, function(k, v){
                    var detail = v['risk_detail'][0];
                    if(detail != null){
                        if(detail.type == "platform_detail"){
                            var dimensions=detail.platform_detail_dimension;
                            var description=detail.description;
                            if(dimensions!=null){
                                html.push('<tr>');
                                $.each(dimensions, function(k, v){
                                    if(v.dimension!=null && v.dimension.indexOf("借款人身份证")!=-1){

                                        html.push('<td>'+v.dimension+'</td>');
                                        if(v.count!=null){
                                            html.push('<td>'+v.count+'</td>');
                                        }else{
                                            html.push('<td>-</td>');
                                        }
                                        html.push('<td>'+description+'</td>');
                                    }
                                });
                                html.push('</tr>');
                            }
                        }
                    }
                });
                $("#js-platformDimensionDetail").html(html.join(""));
            }
        },
        buildLatestLoginTime: function(_data){
            if(null == _data){
                return;
            }
            var latestLoginTime = _data.lastLoginTime;
            var appVersion = _data.appVersion;
            if (latestLoginTime != null) {
                latestLoginTime = latestLoginTime.substring(0,19);
                $("#js-latestLoginTime").html(latestLoginTime);
            }
            if (appVersion != null) {
                $("#js-appId").html(appVersion);
            }
        },
        buildPhoneNoBelong: function(_data){
            if(null != _data){
                if(null != _data.sfzAddress && "" != _data.sfzAddress){
                    $("#js-sfzAddress").html(_data.sfzAddress);
                }
                if(null != _data.phoneAddress && "" != _data.phoneAddress){
                    $("#js-phoneAddress").html(_data.phoneAddress);
                }
            }
        },
        buildIPAddress: function(_data){
            $("#js-ipAddress").html(_data.url);
        },
        buildPhoneUseTime: function(_data){
            if(null != _data && _data.length > 0){
                for(var i = 0; i < _data.length; i++){
                    if(_data[i].type == 'numberUsedLong'){
                        $("#js-sjhmsc").html(_data[i].evidence);
                    }
                    if(_data[i].type == 'openTime'){
                        var temp = _data[i].evidence.split("，");
                        $("#js-phone-net-in-time").html(temp[1]);
                    }
                }
            }
        },
        buildJobInfo: function(_data){
            if (_data != null) {
                $("#js-hidden-companyName").val(_data.companyName);

                $("#js-companyName").html(_data.companyName);
                $("#js-companyIndustry").html(_data.companyIndustry);
            }
        },
        buildPhoneMessageSum: function(_data){
            $("#dxSum").html(_data.count);
        },
        buildAppSum : function(_data){
            $("#appSum").html(_data.total.count);
        },
        buildAnalysisData: function(_data){
            if (_data != null) {
                $("#exceptionPhoneSize").html(_data.exceptionPhoneSize);
                $("#txlNum").html(_data.txlNum);
            }
        },
        buildPushedCallNum: function(_data){
            $("#bcsbdNum").html(data.list.data.length);
        },
        buildIcePerson: function(_data){
            if (_data != null && _data.length > 0) {
                var html = [];
                $.each(_data, function(k, v){
                    html.push('<tr>');
                    html.push('<td align="">' + v.relation + '</td>');
                    html.push('<td align="">' + v.name + '</td>');
                    html.push('<td align="">' + v.mobile + '</td>');
                    html.push('<td align="">' + v.belongTo + '</td>');

                    var duration =  Math.ceil(parseInt(v.connectDuration) / 60);

                    var contactCount = v.firstConnectTime + " ~ "
                        + v.latestConnectTime + " 时间段,半年内联系["
                        + v.connectCount + "]次,共计["
                        + duration + "]分钟,按半年联系次数排序第["
                        + v.connectOrder + "]名";

                    html.push('<td align="">' + contactCount + '</td>');
                    html.push('</tr>');
                });
                $("#js-linkman").html(html.join(""));
            }
        },
        buildLivingBodyImg:function(_data){
            if(null != _data && _data.faceUrl != ""){
                $("#js-avatar").attr("src", _data.faceUrl);
                $("#js-avatar").on("click", function(){
                    Case.openAvatar(_data.faceUrl);
                });
            }
        },

        buildReturnCasePool: function(_data){
            layer.msg('退回公共案件池成功!' , {icon: 1});
            setTimeout(function(){
                var tab = parent.tab;
                tab.deleteTab(tab.getCurrentTabId());

                var href = getCtxPath() + "/index/views/approve/publicApprove.html";
                var data = {
                    "title": '公共案件池',
                    "icon": "&#xe621;",
                    "href": href
                }
                window.parent.topTab.tabAdd(data);
            }, 1000);
        },

        /**
         * 人工处理结果
         */
        buildApplicationAuditResultInfo: function(_data){
            if(null != _data){
                $("#js-refuse-name").val(_data.refuseName);// 拒绝原因
                $("#js-hidden-refuse-code").val(_data.refuseCode);// 拒绝编号
                $("#js-rgAuditingRemark").val(_data.rgAuditingRemark);// 人工审核备注

                $("#js-rgCreditMoney").attr("readonly", true);
                $("#js-rgAuditingRemark").attr("readonly", true);
                $("input[name=auditStatus]").attr("disabled", true);

                var _showColor = "";
                if(_data.rgAuditingState == 3){
                    //拒绝
                    $("#js-rgCreditMoney").val('0');
                    _showColor = "red";
                }else if(_data.rgAuditingState == 2){
                    //通过
                    $("#js-rgCreditMoney").val(_data.rgCreditMoney);
                    _showColor = "blue";
                }

                $("#js-auditStatus-text").html('<span class="layui-badge layui-form-label" style="width: 100px; color: '+_showColor+'; padding-left: 0px; text-align: left;">'+GLOBAL_RG_AUDIT_STATUS[_data.rgAuditingState]+'</span>');

                GLOBAL_ECHARTS_DATA.auditRemark = _data.refuseName;
                GLOBAL_ECHARTS_DATA.rgAuditingStatus = _data.rgAuditingState;
            }else{
                if(_globalOpen != 1){
                    if(GLOBAL_ECHARTS_DATA.auditStatus == AUDIT_STATUS_NEED_CHECK){
                        $("#js-auditStatus-text").html('<span class="layui-badge layui-form-label" style="width: 100px; color: '+_showColor+'; padding-left: 0px; text-align: left;">'+GLOBAL_RG_AUDIT_STATUS[AUDIT_STATUS_NEED_CHECK]+'</span>');
                    }else{
                        $("#js-auditStatus-text").html('<span class="layui-badge layui-form-label" style="width: 100px; color: '+_showColor+'; padding-left: 0px; text-align: left;">'+GLOBAL_AUDIT_STATUS[GLOBAL_ECHARTS_DATA.auditStatus]+'</span>');
                    }
                }
            }
            CaseTpl.buildECharts();
        },

        /**
         * 构建 Echarts
         */
        buildECharts : function(){
            console.info(GLOBAL_ECHARTS_DATA);
            var _score = {
                '欺诈分' : 0,
                '系统扣分' : 100
            }
            if(GLOBAL_ECHARTS_DATA.auditStatus == "" || GLOBAL_ECHARTS_DATA.auditStatus== null){
                _score['欺诈分'] = 0;
            }
           
            if(GLOBAL_ECHARTS_DATA.auditScores != ""){
            	
                var _auditScoresFloat = parseFloat(GLOBAL_ECHARTS_DATA.auditScores);
                
                _score['欺诈分'] = _auditScoresFloat;
                
                
            	//如果欺诈分小于100
                if(_auditScoresFloat < 100 ){
                	
                	//系统扣分=总分100-欺诈分
                	_score['系统扣分'] = Math.round((100 - _auditScoresFloat) * 100000) / 100000;
                	
                //如果欺诈分大于100
                } else {
                	//总分
                    var _totalScore;   
                    
                    //计算欺诈分的十位数      parseInt()  取整函数  例:parseInt(156/10); 结果为15
                    var _tenDigits = parseInt(_auditScoresFloat / 10) - parseInt(_auditScoresFloat / 100) * 10;
                    
                	if(_tenDigits < 5 ){
                		//总分=欺诈分的百位数*100+50   例：105的百位是1  总分=1*100+50 = 150
                		_totalScore = parseInt(_auditScoresFloat / 100) * 100 + 50;
                		_score['系统扣分'] = Math.round((_totalScore - _auditScoresFloat) * 100000) / 100000;
                	
                	} else {
                		//总分=欺诈分的百位数*100+100   例：255的百位是2  总分=2*100+100 = 300
                		_totalScore = parseInt(_auditScoresFloat / 100) * 100 + 100;
                		_score['系统扣分'] = Math.round((_totalScore - _auditScoresFloat) * 100000) / 100000;
                	}
                }
            }
           
            var _text;
            if(GLOBAL_ECHARTS_DATA.rgAuditingStatus == "2"){
                _text = "人工审批通过";
            }else if(GLOBAL_ECHARTS_DATA.rgAuditingStatus == "3"){
                _text = "人工审批不通过";
            }else if(GLOBAL_ECHARTS_DATA.auditStatus == '1'){
                _text = "待系统审批";
            }else if(GLOBAL_ECHARTS_DATA.auditStatus == '2'){
                _text = "系统审批通过";
            }else if(GLOBAL_ECHARTS_DATA.auditStatus == '3'){
                _text = "系统审批不通过";
            }else if(GLOBAL_ECHARTS_DATA.auditStatus == '4'){
                _text = "待人工审批";
            }else{

            }

            option = {
                color:[ "#fff","#48B7FF", "#ffb700"],
                tooltip: {
                    trigger: 'item',
                    formatter: "{b}: {c} "
                },
                legend: {
                    orient: 'vertical',
                    x : 'center',
                    y : 'bottom',
                    data:['欺诈分'],
                    orient:'horizontal',
                    formatter: function(_name){
                        return _name + " " + _score[_name] ;
                    },
                    itemGap:30,
                    icon:'circle'
                },
                series: [
                    {
                        type: 'pie',
                        name: '',
                        radius: ['0%', '65%'],
                        data: [{
                            name: _text,
                            label: {
                                normal: {
                                    show: true,
                                    position: 'center',
                                    formatter: _text,
                                    color: '#C3C3C3',
                                    fontSize: 26,
                                    icon:'circle'
                                }
                            }
                        }]
                    },
                    {
                        name:'欺诈分',
                        type:'pie',
                        radius: ['65%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            show: false
                        },
                        data:[
                            {
                                value: _score['欺诈分'],
                                name:'欺诈分'
                            },
                            {
                                value:  _score['系统扣分'],
                                tooltip:{
                                    show:false
                                },
                                itemStyle : {
                                    normal : {
                                        label : {
                                            show : true,
                                            position : 'center',
                                            formatter : '{b}',
                                            textStyle: {
                                                baseline : 'bottom'
                                            }
                                        },
                                        labelLine : {
                                            show : false
                                        }
                                    }
                                }
                            }
                        ]
                    }
                ]
            };

            var myChart = echarts.init(document.getElementById('js-echarts'));
            myChart.title = '系统处理结果';
            if (option && typeof option === "object") {
                myChart.setOption(option, true);
            }
        }
    };

    //开始查询数据
    Case.getApproveDetails();

    // 页面绑定按钮
    CaseBinding.bindDataReportBtn();
    CaseBinding.bindDataReport();
    CaseBinding.bindOtherBtn();
});