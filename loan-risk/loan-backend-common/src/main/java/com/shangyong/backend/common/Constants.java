package com.shangyong.backend.common;

/**
 * 系统全局常量类
 *
 * @author gdl
 */
public class Constants {

    public static final String CODE = "code";
    public static final String MESSAGE = "message";
    public static final String SUCCESSED = "successed";
    public static final String DATAS = "data";// 返回接口数据格式需要

    public static final String MSG = "msg";// 查询结果list页面需要用到
    public static final String REL = "rel"; // 查询结果list页面需要用到
    public static final String DATA = "list";// 查询结果list页面需要用到


    /**
     * UTF-8编码
     */
    public static final String UTF8 = "UTF-8";

    public static final String DES_PUBLIC_ENCRYPT_KEY = "CEFEH8AA"; //DES加密key
    public static final String DES_PRIVATE_ENCRYPT_KEY = "o0al4OaEWBzA1";

    public static final String FILEPATH = "files";
    public static final String PICTURE = "picture"; //生产和测试库存放图片路劲(暂时，待与运维确定)
    public static final String SYSTEMCENTER = "systemCenter"; //生产和测试库取图片路劲(暂时，待与运维确定)

    public static final String DICTIONARY_CACHE_PRE = "backend:dict:"; //后台数据字典缓存key前缀
    public static final String DICTIONARY_CACHE_FLAG_KEY = DICTIONARY_CACHE_PRE + "flag"; //后台数据字典缓存key前缀

    /**
     * 回调App审批状态URL 对应系统参数key
     */
    public static final String PUSH_APP_URL = "PUSH_APP_URL";//回调App审批状态URL

    /**
     * 同盾获取提交报告数据URL 对应系统参数key
     */
    public static final String TD_SUBMIT_URL = "TD_SUBMIT_URL";

    /**
     * 同盾获取查询报告数据URL 对应系统参数key
     */
    public static final String TD_QUUERY_URL = "TD_QUUERY_URL";

    /**
     * 同盾常量  对应系统参数key
     */
    public static final String TD_CONSTANT = "TD_CONSTANT";

    /**
     * 批量处理的最大长度(50-100相对最优)
     */
    public static final int BATCH_INSERT_SIZE = 50;

    /**
     * 批量保存通讯录信息
     */
    public static final int SAVE_CUSTOMER_DIRECTORIES_SIZE = 1000;
    /**
     * 通讯录存储路径
     */
    public static final String DIRECTORIES  = "DIRECTORIES_";
    /**
     * 通讯录数据存储类型
     */
    public static final String DIRECTORIES_TYPE  = "09001";
    /**
     * 通讯录数据阿里云OOS路径
     */
    public static final String DIRECTORIES_DIR  = "DIRECTORIES_DIR";
    /**
     * 定时任务job group前缀
     */
    public static final String JOB_PREFIX = "JOB_";

    /**
     * 风控系统定时任务
     */
    public static final String BACKEND_TASK = "BACKEND";

    /**
     * 数据有效性状态 01-生效
     */
    public static final String STATE_NORMAL = "01";

    /**
     * 数据有效性状态 02-失效
     */
    public static final String STATE_FORBIDDEN = "02";

    /**
     * 风控规则字符串分隔符 (|||)
     */
    public static final String RISK_SPLIT = "\\|\\|\\|";

    /**
     * 阿里云oss
     */
    public static final String ALIYUN_OSS = "ALIYUN_OSS";
    /**
     * 阿里云oss CSV
     */
    public static final String ALIYUN_OSS_CSV = "ALIYUN_OSS_CSV";
    /**
     * 洪澄芝麻参数配置
     */
    public static final String ZHIMA_SHANDAI = "ZHIMA_SHANDAI";

    /**
     * 白骑士反欺诈云接口参数配置
     */
    public static final String BQS_ANTIFRAUD_CODE = "BQS_ANTIFRAUD_CODE";
    
    /**
     * 白骑士运营商接口参数配置
     */
    public static final String BQS_YYS_CODE = "BQS_YYS_CODE";
    
    /**
     * 白骑士报告接口参数配置
     */
    public static final String BQS_REPORT_CODE = "BQS_REPORT_CODE";
    
    /**
     * 白骑士获取token参数配置
     */
    public static final String BQS_CLWEB_TOKEN = "BQS_CLWEB_TOKEN";
    
    /**
     * 同盾数据魔盒获取url地址
     */
    public static final String TD_REPORT_URL = "TD_REPORT_URL";
    
    /**
     * 白骑士获取url参数配置
     */
    public static final String BQS_CLWEB_PAGE = "BQS_CLWEB_PAGE";
    
    
    /**
     * 白骑士反欺诈云接口参数配置 new
     */
    public static final String BQS_ANTIFRAUD_NEW_CODE = "BQS_ANTIFRAUD_NEW_CODE";
    
    /**
     * 白骑士运营商接口参数配置new
     */
    public static final String BQS_YYS_NEW_CODE = "BQS_YYS_NEW_CODE";
    
    /**
     * 极光黑名单接口参数配置
     */
    public static final String JG_FQZ_CODE="JG_FQZ_CODE";
    
    
    
    /**
     * 腾讯云反欺诈接口参数配置
     */
    public static final String TXY_ANTIFRAUD_CODE = "TXY_ANTIFRAUD_CODE";
    
    /**
     * 易极付接口参数配置
     */
    public static final String YJF_BLACK_CODE = "YJF_BLACK_CODE";
    
    /**
     * 上海资信云接口参数配置
     */
    public static final String SH_CREDIT_CODE1 = "SH_CREDIT_CODE1";
    
    /**
     * 上海资信云接口参数配置
     */
    public static final String SH_CREDIT_CODE2 = "SH_CREDIT_CODE2";
    
    /**
     * 同盾数据魔盒接口参数配置
     */
    public static final String TD_REPORT_CODE = "TD_REPORT_CODE";
    
    /**
     * 同盾反欺诈接口参数配置
     */
    public static final String TD_ANTIFRAUD_CODE = "TD_ANTIFRAUD_CODE";
    /**
     * 上传文件路径（导入cvs）参数配置
     */
    public static final String UPLOAD_PATH = "UPLOAD_PATH";
    
    /**
     * 黑名单上传文件路径（导入CSV）参数配置
     */
    public static final String UPLOAD_PATH_BLACKLIST = "UPLOAD_PATH_BLACKLIST";
    

    /**
     * 重置密码参数配置
     */
    public static final String REST_CODE = "REST_CODE";

    /**
     * 钉钉报警接口参数配置
     */
    public static final String DD_URL_CODE = "DD_URL_CODE";

    /**
     * 钉钉task技术报警接口参数配置
     */
    public static final String TASK_URL_CODE = "TASK_URL_CODE";

    /**
     * 钉钉Dubbo服务报警：申请单信息
     */
    public static final String DUBBO_DD_APP_URL_CODE = "DUBBO_DD_APP_URL_CODE";

    /**
     * 钉钉Dubbo服务报警：催收对接
     */
    public static final String DUBBO_DD_REC_URL_CODE = "DUBBO_DD_REC_URL_CODE";

    /**
     * 钉钉Dubbo服务报警：客户信息对接
     */
    public static final String DUBBO_DD_CUS_URL_CODE = "DUBBO_DD_CUS_URL_CODE";

    /**
     * 钉钉报警：审批状态变更
     */
    public static final String TASK_ONE_DD_SP_URL_CODE = "TASK_ONE_DD_SP_URL_CODE";

    /**
     * 钉钉报警：同盾
     */
    public static final String TASK_ONE_DD_TD_URL_CODE = "TASK_ONE_DD_TD_URL_CODE";

    /**
     * 钉钉报警：芝麻信用
     */
    public static final String TASK_ONE_DD_ZM_URL_CODE = "TASK_ONE_DD_ZM_URL_CODE";

    /**
     * 钉钉报警：蜜罐钉钉告警url
     */
    public static final String TASK_TWO_DD_MG_URL_CODE = "TASK_TWO_DD_MG_URL_CODE";

    /**
     * 钉钉报警：APP状态同步告警url
     */
    public static final String TASK_TWO_DD_APP_URL_CODE = "TASK_TWO_DD_APP_URL_CODE";

    /**
     * 钉钉报警：蜜蜂钉钉告警url
     */
    public static final String TASK_TWO_DD_MF_URL_CODE = "TASK_TWO_DD_MF_URL_CODE";

    /**
     * 钉钉报警：	风控配置项
     */
    public static final String WEB_DD_FK_URL_CODE = "WEB_DD_FK_URL_CODE";

    /**
     * 钉钉报警：	风控配置项
     */
    public static final String WEB_DD_SYS_URL_CODE = "WEB_DD_SYS_URL_CODE";

    /**
     * 钉钉报警：	定时任务配置管理
     */
    public static final String WEB_DD_QUARTZ_URL_CODE = "WEB_DD_QUARTZ_URL_CODE";


    /**
     * 钉钉MQ报警，使用数据库配置的
     */
    public static final String MQ_DD_URL_CODE = "MQ_DD_URL_CODE";

    /**
     * 账号在X分钟内，连续登录失败X次将冻结账户
     */
    public static final String LOGIN_COUNT_NUMBERS = "LOGIN_COUNT_NUMBERS";


    /**
     * 速贷芝麻参数配置
     */
    public static final String ZHIMA_SUDAI = "ZHIMA_SUDAI";

    /**
     * 芝麻欺诈清单对应的产品码
     */
    public static final String ZHIMA_BILL_CODE = "w1010100003000001283";

    /**
     * 芝麻信用评分对应的产品码
     */
    public static final String ZHIMA_CREEDIT_CODE = "w1010100100000000001";

    /**
     * 芝麻信用行业关注名单对应的产品码
     */
    public static final String ZHIMA_WATCHLISTII_CODE = "w1010100100000000022";

    /**
     * 芝麻信用申请欺诈评分对应的产品吗
     */
    public static final String ZHIMA_FRAUDSCORE_CODE = "w1010100003000001100";

    /**
     * 芝麻信用欺诈信息验证对应的产品吗
     */
    public static final String ZHIMA_CHECK_CODE = "w1010100000000002859";

    /**
     * 芝麻信用： 证件类型。IDENTITY_CARD标识为身份证，目前仅支持身份证类型
     */
    public static final String ZHIMA_IDENTITY_CARD = "IDENTITY_CARD";


    /**
     * 获取最后一步标识，审批数据为最后一步修改审批通过 对应系统参数key
     */
    public static final String UPDATE_STEP_STATUS = "UPDATE_STEP_STATUS";

    /**0：（0：待处理、1：同盾设备信息、2：同盾反欺诈、3：白骑士资信云报告、4：极光黑名单、5：腾讯反欺诈、6：91征信查询（多头借贷）、7：91回调、98：规则引擎模板分发、99：潘多拉拒绝名单、100：客户授信）

	/**洪澄黑名单*/
    /**
     * 洪澄黑名单步骤号记录11
     */
	public static final String BLACK_STEP = "11";

    /**
     * 0：初始化数据
     **/
    public static final String INIT_STEP = "0";
    /**
     * 1：同盾设备信息
     **/
    public static final String TD_EQUIPMENT_STEP = "1";
    /**
     * 2：同盾反欺诈
     **/
    public static final String TD_ANTI_FRAUD_STEP = "2";
    /**
     * 3：白骑士资信云报告
     */
    public static final String BQS_ZXY_STEP = "9";
    /**
     * 4：极光黑名单
     */
    public static final String JG_BLACK_STEP = "3";
    /**
     * 5：腾讯反欺诈
     */
    public static final String TENCENT_ANTI_FRAUD_STEP = "7";
    /**
     * 6：91征信
     **/
    public static final String XC_91ZX_STEP = "4";
    /**
     * 7：91征信回调
     **/
    public static final String XC_91ZX_CALL_STEP = "5";
    /**
     * 8：易极付-黑名单回调
     **/
    public static final String YJF_BLACK_STEP = "6";
    
    /**
     * 8：上海资信
     **/
    public static final String SH_CREDIT_STEP = "8";
    
    /**
     * 9.白骑士运营商原始数据
     */
    public static final String BQS_INFO_STEP = "9";
    
    /**
     * 9.同盾数据魔盒报告
     */
    public static final String TD_REPORT_STEP = "9";
    /**
     * 12：白骑士运营商报告
     **/
    public static final String ZM_BQS_YSS_STEP = "12";
    /**
     * 准入步骤为：97
     **/
    public static final String SY_RULE_ID = "97";
    /**
     * 规则引擎分发步骤为：98
     **/
    public static final String RULE_ID_SET_STEP = "98";
    /**
     * 被潘多拉拒绝数据更新步骤为：99
     **/
    public static final String NO_PASS_STEP = "99";
    /**
     * 500洪澄黑名单信息
     */
    public static final String BLACK_STEP_NAME="洪澄黑名单";
    /**
     * 0：初始化数据
     **/
    public static final String INIT_STEP_NAME = "初始化数据";
    /**
     * 1：同盾设备信息
     **/
    public static final String TD_EQUIPMENT_NAME = "同盾设备信息";
    /**
     * 2：同盾反欺诈
     **/
    public static final String TD_ANTI_FRAUD_NAME = "同盾反欺诈";
    /**
     * 3：白骑士资信云报告
     **/
    public static final String BQS_ZXY_NAME = "白骑士资信云报告";
    /**
     * 4：极光黑名单
     **/
    public static final String JG_BLACK_NAME = "极光黑名单";
    /**
     * 5：腾讯反欺诈
     **/
    public static final String TENCENT_ANTI_FRAUD_NAME = "腾讯反欺诈";
    /**
     * 6：91征信
     **/
    public static final String XC_91ZX_NAME = "91征信";
    /**
     * 7：91征信回调
     **/
    public static final String XC_91ZXCALL_NAME= "91征信回调";
    /**
     * 8.白骑士运营商原始数据
     */
    public static final String BQS_INFO_NAME = "白骑士运营商原始数据";
    /**
     * 8.易极付黑名单
     */
    public static final String YJF_BLACK_NAME = "易极付黑名单";
    /**
     * 9.上海资信
     */
    public static final String SH_CREDIT_NAME = "上海资信";
    /**
     * 10.同盾数据魔盒报告
     */
    public static final String TD_REPORT_NAME = "同盾数据魔盒报告";
    /**
     * 20：宜信
     **/
    public static final String YI_AF_NAME = "宜信";
    /**
     * 10：小视科技
     **/
    public static final String XS_KJ_NAME = "小视科技";
    /**
     * 11：葫芦
     **/
    public static final String HL_NAME = "葫芦";
    /**
     * 12：白骑士运营商
     **/
    public static final String BQS_YYS_STEP_NAME = "白骑士运营商";
    /**
     * 被潘多拉拒绝数据更新步骤为：99
     **/
    public static final String NO_PASS_STEP_NAME = "潘多拉拒绝名单列表";

    /** * 处理状态 */

    /**
     * 审批状态：待处理
     */
    public static final String DAI_CL_STAATE = "1";
    /**
     * 审批状态 ：处理通过
     */
    public static final String PASS_CL_STAATE = "2";
    /**
     * 审批状态 ：处理失败
     */
    public static final String NOPASS_CL_STAATE = "3";

    /** * 审批状态 */

    /**
     * 审批状态：待审核
     */
    public static final String DAI_SP_STAATE = "1";
    /**
     * 审批状态 ：审核通过
     */
    public static final String PASS_SP_STAATE = "2";
    /**
     * 审批状态 ：审核不通过
     */
    public static final String NOPASS_SP_STAATE = "3";
    /**
     * 审批状态 ：待人工确认
     */
    public static final String DAIRG_SP_STAATE = "4";
    /**
     * 审批状态 ：信息重新获取
     */
    public static final String AGAIN_SP_STAATE = "5";

    /**
     * 已经发送 给消息队列, 让 task 不在扫到 相应的记录
     */
    public static final String MQ_QZF_STATE = "6";

    /**
     * 审批状态：待审核
     */
    public static final String DAI_SP_STATE_NAME = "待审核";
    /**
     * 审批状态 ：审核通过
     */
    public static final String PASS_SP_STATE_NAME = "审核通过";
    /**
     * 审批状态 ：审核不通过
     */
    public static final String NOPASS_SP_STATE_NAME = "审核不通过";
    /**
     * 审批状态 ：待人工确认
     */
    public static final String DAIRG_SP_STAATE_NAME = "待人工确认";

    /**
     * APP申请数据查询结果集的错误次数 ,配置参数变量
     */
    public static final String APP_FAILURE_COUNT = "APP_FAILURE_COUNT";

    /**
     * 芝麻鉴权失败
     */
    public static final String ZHIMA_DATA_RULE_NAME = "芝麻鉴权失败";
    /**
     * 蜜蜂采集失败
     */
    public static final String MF_DATA_RULE_NAME = "蜜蜂采集失败";


    /**
     * 是否推送App： 是
     */
    public static final String IS_PUSH_APP = "1";

    /**
     * 是否推送App： 否
     */
    public static final String NO_PUSH_APP = "0";

    /**
     * 定时任务每次调起多线程最大启动线程数
     */
    public static final String PER_TASK_MAX_THREAD = "PER_TASK_MAX_THREAD";

    /**
     * 单个线程处理记录数
     */
    public static final String PROCESS_RECORED_SIZE_PER_THREAD = "PROCESS_RECORED_SIZE_PER_THREAD";

    /**
     * 默认模版ID为1
     */
    public static final String DEFAULT_TEMPLATES = "1";// 默认模版ID为1

    /**
     * 第三方报告芝麻信用评分
     */
    public static final String THIRDPARTY_REPORT_ZM_XYPF = "01001";

    /**
     * 第三方报告芝麻信用欺诈清单
     */
    public static final String THIRDPARTY_REPORT_ZM_XYQZQD = "02001";

    /**
     * 第三方报告同盾
     */
    public static final String THIRDPARTY_REPORT_TD = "03001";

    /**
     * 第三方报告聚信立蜜蜂
     */
    public static final String THIRDPARTY_REPORT_JXL_MF = "04001";

    /**
     * 第三方报告聚信立蜜罐
     */
    public static final String THIRDPARTY_REPORT_JXL_MG = "05001";

    /**
     * 第三方报告魔盒运营商
     */
    public static final String THIRDPARTY_REPORT_MH_YYS = "06001";

    /**
     * 第三方报告魔盒学信
     */
    public static final String THIRDPARTY_REPORT_MH_XX = "06002";

    /**
     * 第三方报告[聚信立]电商
     */
    public static final String THIRDPARTY_REPORT_MH_DS = "06003";

    /**
     * 第三方报告魔盒社保
     */
    public static final String THIRDPARTY_REPORT_MH_SB = "06004";

    /**
     * 第三方报告魔盒公积金
     */
    public static final String THIRDPARTY_REPORT_MH_GJJ = "06005";
    
    
    /**
     * 第三方报告魔盒个人信用
     */
    public static final String THIRDPARTY_REPORT_MH_CREDIT = "06006";
    
    
    /**
     * 第三方报告91征信
     */
    public static final String THIRDPARTY_REPORT_CREDIT = "07001";
    
    /**
     * 第三方报告白骑士欺诈
     */
    public static final String THIRDPARTY_REPORT_BAIQI = "08001";
    
    
    /**
     * 第三方报告宜信
     */
    public static final String THIRDPARTY_REPORT_YIXIN= "09001";
    
    /**
     * 第三方报告葫芦索伦
     */
    public static final String THIRDPARTY_REPORT_SAURONINFO = "11001";
    
    /**
     * 第三方报告葫芦索伦
     */
    public static final String THIRDPARTY_REPORT_SMALLBANK = "12001";
    
    /**
     * 第三方报告葫芦索伦
     */
    public static final String THIRDPARTY_REPORT_SMALLLOAN = "12002";

    /**
     * 第三方报告禁止项模板业务编号
     */
    public static final String THIRDPARTY_REPORT_BAN_TPL_CODE = "10001";

    /**
     * 第三方报告信用评分小类模板业务编号
     */
    public static final String THIRDPARTY_REPORT_SCORE_SMALL_CODE = "10002";

    /**
     * 第三方报告欺诈评分模板业务编号
     */
    public static final String THIRDPARTY_REPORT_FRAUD_SCORE_TPL_CODE = "10003";

    /**
     * 默认基础模板业务编号key
     */
    public static final String DEFAULT_BAN_TEMPLATE_ID_REDIS_KEY_NAME = "DEFAULT_BAN_TEMPLATE";

    /**
     * 默认基础模板业务编号key
     */
    public static final String DEFAULT_FRAUD_TEMPLATE_ID_REDIS_KEY_NAME = "DEFAULT_FRAUD_TEMPLATE";

    /**
     * 默认基础模板业务编号key
     */
    public static final String DEFAULT_SCORE_TEMPLATE_ID_REDIS_KEY_NAME = "DEFAULT_SCORE_TEMPLATE";

    /**
     * 默认基础决策树key
     */
    public static final String DEFAULT_TREE_TEMPLATE_ID_REDIS_KEY_NAME = "DEFAULT_TREE_TEMPLATE";


    /**
     * 默认基础模板业务编号key 阿里云服务地址外网映射地址
     */
    public static final String FILE_OSS_DOMAIN_NAME = "FILE_OSS_DOMAIN_NAME";

    /**
     * 模板类型
     */
    /**
     * 禁止项模板
     **/
    public static final String RULE_TPL_BAN_CODE = "1";

    /**
     * 欺诈项模板
     **/
    public static final String RULE_TPL_FRAUD = "2";

    /**
     * 评分项模板
     **/
    public static final String RULE_TPL_SCORE = "3";

    /**
     * 决策树
     **/
    public static final String RULE_DECISION_TREE = "4";

    /**
     * 修改禁止项模板时，调用App接口URL传值
     */
    public static final String BAN_APP_URL = "BAN_APP_URL";

    /**
     * 91征信参数配置
     */
    public static final String XCZX_ANTIFRAUD_CODE = "XCZX_ANTIFRAUD_CODE";

    /**
     * 宜信参数配置
     */
    public static final String YX_ANTIFRAUD_CODE = "YX_ANTIFRAUD_CODE";

    /**
     * 葫芦索伦参数配置
     */
    public static final String HLSL_ANTIFRAUD_CODE = "HLSL_ANTIFRAUD_CODE";

    /**
     * 葫芦索伦客户借款申请扩展表 数据存储类型
     **/
    public static final String HLSL_TASK_TYPE = "11001";

    /**
     * 聚信立基础数据-通话记录
     */
    public static final String THIRDPARTY_REPORT_JXL_PHONE_CODE = "10001";
    /**
     * 聚信立基础数据-短信
     */
    public static final String THIRDPARTY_REPORT_JXL__CODE = "10002";
    /**
     * 聚信立基础数据-流量记录
     */
    public static final String THIRDPARTY_REPORT_JXL_FLOWS_CODE = "10003";
    /**
     * 聚信立基础数据-月账单Consume
     */
    public static final String THIRDPARTY_REPORT_JXL_CONSUME_CODE = "10004";
    
    /**
     * 用户提额MQ调用地址配置
     */
    public static final String TEMQ_ANTIFRAUD_CODE = "TEMQ_ANTIFRAUD_CODE";

    /**
     * MQ提额，异常存redis
     */
    public static final String REDIS_MQ_TE_ERROR = "MQ:TE:REDIS_MQ_TE_ERROR";
    
    /**
     * MQ提额，异常存redis, 用于页面显示
     */
    public static final String REDIS_MQ_TE_ERROR_INFO = "MQ:TE:REDIS_MQ_TE_ERROR_INFO";

    
    /**
     * MQ提额，控制异常队列消费的变量（秒）
     */
    public static final String REDIS_MQ_TE_SECOND = "MQ_TE_REDIS_SECOND";
    
    
    /**
     * MQ第三方征信，异常单子存放redis的key
     */
    public static final String REDIS_MQ_DSF_ERROR = "MQ:DSF:REDIS_ERROR";
    

    /**
     * 报告文件目录配置-文件上传地址默认参数
     */
    public static final String DEFAULT_FILE_UPLOAD_DIR = "DEFAULT_FILE_UPLOAD_DIR";
    /**
     * 报告文件目录配置-芝麻信用评分文件上传地址
     */
    public static final String ZMXYPF_FILE_UPLOAD_DIR = "ZMXYPF_FILE_UPLOAD_DIR";
    /**
     * 报告文件目录配置-芝麻信用欺诈清单文件上传地址默认参数
     */
    public static final String ZMXYQZQD_FILE_UPLOAD_DIR = "ZMXYQZQD_FILE_UPLOAD_DIR";
    /**
     * 报告文件目录配置-91征信逾期文件上传地址默认参数
     */
    public static final String XCZX_FILE_UPLOAD_DIR = "XCZX_FILE_UPLOAD_DIR";
    /**
     * 极光文件目录配置-极光返回数据上传地址
     */
    public static final String JG_UPLOAD_DIR = "JG_UPLOAD_DIR";
    /**
     * 同盾反欺诈目录配置
     */
    public static final String TD_UPLOAD_DIR = "TD_UPLOAD_DIR";
    /**
     * 白骑士目录配置-白骑士用户数据返回数据上传地址
     */
    public static final String BQS_INFO_UPLOAD_DIR = "BQS_INFO_UPLOAD_DIR";
    /**
     * 白骑士目录配置-白骑士报告返回数据上传地址
     */
    public static final String BQS_REPORT_UPLOAD_DIR = "BQS_REPORT_UPLOAD_DIR";
    /**
     * 腾讯云目录配置
     */
    public static final String TXY_UPLOAD_DIR = "TXY_UPLOAD_DIR";
    /**
     * 易极付黑名单目录配置
     */
    public static final String YJF_UPLOAD_DIR = "YJF_UPLOAD_DIR";
    /**
     * 易极付黑名单目录配置
     */
    public static final String TD_REPORT_DIR = "TD_REPORT_DIR";
    /**
     * 上海资信目录配置
     */
    public static final String SH_CREDIT_DIR = "SH_CREDIT_DIR";
    /**
     * 同盾设备指纹目录配置
     */
    public static final String TD_FACILITY_UPLOAD_DIR = "TD_FACILITY_UPLOAD_DIR";
    /**
     * 报告文件目录配置-宜信数据报告文件上传地址默认参数
     */
    public static final String YX_FILE_UPLOAD_DIR = "YX_FILE_UPLOAD_DIR";
    /**
     * 报告文件目录配置-葫芦索伦数据报告文件上传地址默认参数
     */
    public static final String HLSL_FILE_UPLOAD_DIR = "HLSL_FILE_UPLOAD_DIR";
    /**
     * 报告文件目录配置-同盾贷前审核文件上传地址默认参数
     */
    public static final String TDDQSH_FILE_UPLOAD_DIR = "TDDQSH_FILE_UPLOAD_DIR";
    /**
     * 报告文件目录配置-聚信立蜜蜂文件上传地址默认参数
     */
    public static final String JXLMF_FILE_UPLOAD_DIR = "JXLMF_FILE_UPLOAD_DIR";
    /**
     * 报告文件目录配置-聚信立蜜罐文件上传地址默认参数
     */
    public static final String JXLMG_FILE_UPLOAD_DIR = "JXLMG_FILE_UPLOAD_DIR";
    /**
     * 报告文件目录配置-聚信立基础数据文件上传地址默认参数
     */
    public static final String JXLJCSJ_FILE_UPLOAD_DIR = "JXLJCSJ_FILE_UPLOAD_DIR";
    /**
     * 报告文件目录配置-芝麻信用行业关注清单文件上传地址默认参数
     */
    public static final String ZMXYHYGZQD_FILE_UPLOAD_DIR = "ZMXYHYGZQD_FILE_UPLOAD_DIR";
    /**
     * 报告文件目录配置-【第三方报告魔盒社保】文件上传地址默认参数
     */
    public static final String SJHM_SB_UPLOAD_DIR = "SJHM_SB_UPLOAD_DIR";
    /**
     * 报告文件目录配置-【第三方报告魔盒学信】文件上传地址默认参数
     */
    public static final String SJHM_XX_UPLOAD_DIR = "SJHM_XX_UPLOAD_DIR";
    /**
     * 报告文件目录配置-【第三方报告魔盒公积金】文件上传地址默认参数
     */
    public static final String SJHM_GJJ_UPLOAD_DIR = "SJHM_GJJ_UPLOAD_DIR";
    /**
     * 报告文件目录配置-【第三方报告 聚信立 电商】文件上传地址默认参数
     */
    public static final String SJHM_DS_UPLOAD_DIR = "SJHM_DS_UPLOAD_DIR";
    /**
     * 报告文件目录配置-【第三方报告魔盒个人信用】文件上传地址默认参数
     */
    public static final String SJHM_CREDIT_UPLOAD_DIR = "SJHM_CREDIT_UPLOAD_DIR";
    /**
    
    
    /**
     * 第三方报告芝麻信用-行业关注清单
     */
    public static final String THIRDPARTY_REPORT_ZM_HYGZQD = "40004";

    /**
     * 聚信立基础数据-报文保存方式-数据库
     */
    public static final String REPORT_SAVE_TYPE_DB = "DB";
    /**
     * 聚信立基础数据-报文保存方式-JSON
     */
    public static final String REPORT_SAVE_TYPE_JSON = "JSON";


    /**
     * 人工审批参数（阈值、开关） 对应系统参数key
     */
    public static final String RG_APPROVAL_SWITCH = "RG_APPROVAL_SWITCH";


    /** task任务 参数配置**/
    /**
     * 91征信参数配置
     */
    public static final String TASK_ANTIFRAUD_CODE = "TASK_ANTIFRAUD_CODE";
    
    
    
    /**
     * 错误信息参数配置（提额5个第三方征信用）
     */
    public static final String PROMOTECONSUMER_ANTIFRAUD_CODE = "PROMOTECONSUMER_ANTIFRAUD_CODE";
    
    /**
     * 错误信息参数配置（公用）
     */
    public static final String CONSUMER_ANTIFRAUD_ERROR_CODE = "CONSUMER_ANTIFRAUD_ERROR_CODE";
    
    
    /**
     * 正常异常重发队列的有效时间配置（提额队列用）
     */
    public static final String PROMOTE_ERROR_HOUR = "PROMOTE_ERROR_HOUR";
    

    /**
     * 老用户标识
     */
    public static final String APP_LEVEL = "1";
    /**
     * 新用户标识
     */
    public static final String APP_LEVEL_NEW = "0";

    /**
     * 报告文件目录配置-聚信立基础数据文件上传地址默认参数
     */
    public static final String BQS_FILE_UPLOAD_DIR = "BQS_FILE_UPLOAD_DIR";

    /**
     * 白骑士反欺诈云
     */
    public static final String THIRDPARTY_REPORT_BQS_PHONE_CODE = "08001";
    
    /**
     * 白骑士运营商
     */
    public static final String THIRDPARTY_REPORT_BQS_YYS_CODE = "08002";

    /**
     * 大数据欺诈分析（阈值、开关） 对应系统参数key
     */
    public static final String HABASE_APPROVAL_SWITCH = "HABASE_APPROVAL_SWITCH";
    /**
     * 需要同步至大数据的标记-1是同步大数据
     */
    public static final String IS_BIG_DATA_SYN = "1";
    /**
     * 同步大数据的步骤-已同步到大数据
     */
    public static final String BIG_DATA_SYN_IS_STEP_2 = "2";
    /**
     * 同步大数据的步骤-已回调大数据
     */
    public static final String BIG_DATA_SYN_IS_STEP_3 = "3";

    /**
     * MQ项目的配置信息
     */
    public static final String BACKEND_MQ_PROJECT = "BACKEND_MQ_PROJECT";

    /**
     * 邮件报警默认参数
     */
    public static final String BACKEND_EMAIL_ALARM = "BACKEND_EMAIL_ALARM";

    /**
     * mq 向大数据发送消息的地址
     */
    public static final String BACKEND_MQ_SEND_MESSAGE_URL = "/producer/sendToHbase";
    /**
     * mq 向大数据发送消息的地址,批量
     */
    public static final String BATCH_MQ_SEND_MESSAGE_TO_HBASE_URL = "/producer/sendToHbaseForBatch";

    /**
     * mq 向风控发送接收消息的地址,批量
     */
    public static final String BATCH_MQ_SEND_MESSAGE_TO_BACKEND_URL = "/producer/sendToBackEndForBatch";


    /**
     * mq 向风控发送消息的地址
     */
    public static final String MQ_SEND_BACKEND_MESSAGE_URL = "/producer/sendToBackend";

    /**小视科技参数配置**/
    /**
     * 银行逾期名单查询参数名称
     */
    public static final String BANK_OVERDUE_LIST_INFO = "BANK_OVERDUE_LIST_INFO";
    /**
     * 网贷逾期名单查询参数名称
     */
    public static final String NET_LOAN_OVERDUE_LIST_INFO = "NET_LOAN_OVERDUE_LIST_INFO";

    /**
     * 报告文件目录配置-小视科技文件上传地址(银行逾期)
     */
    public static final String XSKJ_BANK_FILE_UPLOAD_DIR = "xskj_bank_file_upload_dir";

    /**
     * 报告文件目录配置-小视科技文件上传地址(网贷逾期)
     */
    public static final String XSKJ_NETLOAN_FILE_UPLOAD_DIR = "xskj_netloan_file_upload_dir";

    /**
     * 第三方报告小视科技(银行)
     */
    public static final String THIRDPARTY_REPORT_XSKJ_BANK = "12001";

    /**
     * 第三方报告小视科技(网贷)
     */
    public static final String THIRDPARTY_REPORT_XSKJ_NETLOAN = "12002";

    /**
     * 审批状态 ：审核通过
     */
    public static final String BIG_DATA_PASS_SP_STATE = "pass";
    /**
     * 审批状态 ：审核不通过
     */
    public static final String BIG_DATA_NOPASS_SP_STATE = "reject";

    /**
     * 第三方查询类型 1:91  2：宜信
     **/
    public static final String QUERY_LOG_TYPE_XCZX = "1";
    public static final String QUERY_LOG_TYPE_YX = "2";

    /**app推送客户信息配置**/
    /**
     * app推送客户信息入库开关配置
     */
    public static final String APPTS_SQL_SWITCH = "APPTS_SQL_SWITCH";

    ////////////////// mongodb 配置机构 ////////////////////////
    /**
     * 同盾贷前审核集合名称
     **/
    public static final String TD_DQSH_COLLECTION_NAME = "同盾贷前审核查询";
    /**
     * 小视科技 银行 集合名称
     **/
    public static final String XSKJ_BANK_COLLECTION_NAME = "小视科技银行逾期查询";
    /**
     * 小视科技 网贷 集合名称
     **/
    public static final String XSKJ_NET_COLLECTION_NAME = "小视科技网贷逾期查询";
    /**
     * 白骑士集合名称
     **/
    public static final String BQS_COLLECTION_NAME = "白骑士查询";
    /**
     * 白骑士运营商集合名称
     **/
    public static final String BQS_COLLECTION_YYS_NAME = "白骑士运营商查询";
    /**
     * 芝麻欺诈
     **/
    public static final String ZMQZ_COLLECTION_NAME = "芝麻欺诈查询";
    /**
     * 芝麻信用
     **/
    public static final String ZMXY_COLLECTION_NAME = "芝麻信用查询";
    /**
     * 芝麻行业关注清单
     **/
    public static final String ZMHY_COLLECTION_NAME = "芝麻行业关注清单查询";
    /**
     * 聚信立运营商
     **/
    public static final String JXL_YYS_COLLECTION_NAME = "聚信立运营商查询";
    /**
     * 聚信立蜜蜂
     **/
    public static final String JXL_MF_COLLECTION_NAME = "聚信立蜜蜂查询";
    /**
     * 聚信立蜜罐
     **/
    public static final String JXL_MG_COLLECTION_NAME = "聚信立蜜罐查询";
    /**
     * 91征信集合名称
     **/
    public static final String XCZX_COLLECTION_NAME = "91征信查询";
    /**
     * 葫芦索伦
     **/
    public static final String HL_SL_COLLECTION_NAME = "葫芦索伦查询";
    /**
     * 宜信
     **/
    public static final String YX_COLLECTION_NAME = "宜信查询";
    /**
     * 数聚魔盒社保
     **/
    public static final String SJHM_SB_COLLECTION_NAME = "数聚魔盒社保查询";
    /**
     * 数聚魔盒公积金
     **/
    public static final String SJHM_GJJ_COLLECTION_NAME = "数聚魔盒公积金查询";
    /**
     * 数聚魔盒电商
     **/
    public static final String SJHM_DS_COLLECTION_NAME = "聚信立电商查询";
    /**
     * 数聚魔盒学信
     **/
    public static final String SJHM_XX_COLLECTION_NAME = "数聚魔盒学信查询";
    
    /**
     * 提供给ailiqiang的队列名
     */
    public static final String SEND_SOURCE_QUEUE = "SEND_SOURCE_QUEUE";
    /**数聚魔盒个人信用**/
    public static final String SJHM_CREDIT_COLLECTION_NAME = "数聚魔盒个人信用查询";
    

    /**
     * app推送 客户信息 MQ消息钉钉报警配置参数
     **/
    public static final String APP_MQ_DINGDING_KHXX = "APP_MQ_DINGDING_KHXX";

    /**
     * app推送 客户信息 MQ消息（压测）钉钉报警配置参数
     **/
    public static final String APP_MQ_YC_DINGDING_KHXX = "APP_MQ_YC_DINGDING_KHXX";

    /**
     * MQ项目的配置信息-- getPrefetchCount
     */
    public static final String MQ_PREFETCH_COUNT = "MQ_PREFETCH_COUNT";

    /**
     * 	提现机构验证顺序-- PRESENT_ORG_CHECK_SORT
     */
    public static final String PRESENT_ORG_CHECK_SORT = "PRESENT_ORG_CHECK_SORT";

    /**
     * 风控默认钉钉报警参数编号
     */
    public static final String BACKEND_DEFAULT_DINGDING_ALARM = "BACKEND_DEFAULT_DINGDING_ALARM";

    /**
     * 扫描当天和历史xx天单子的阀值
     */
    public static final String SACN_APPLICATION_FORM = "SACN_APPLICATION_FORM";
    
    /**
     * 所有征信机构是否真正获取报告配置，对应系统参数key
     */
    public static final String ZHENGXIN_ORG_PASSONE = "ZHENGXIN_ORG_PASSONE";//征信机构是否真正获取报告配置1
    public static final String ZHENGXIN_ORG_PASSTWO = "ZHENGXIN_ORG_PASSTWO";//征信机构是否真正获取报告配置2
    public static final String ZHENGXIN_ORG_PASSTHREE = "ZHENGXIN_ORG_PASSTHREE";//征信机构是否真正获取报告配置3	
    
    /**
     * 是否启用全部流程开关
     */
    public static final String ISUSE_ALL_STEP="ISUSE_ALL_STEP";//是否需要跑完所有流程决定
    public static final String USE_FINAL_STEP_NUM="USE_FINAL_STEP_NUM";//最后流程步骤编号
    
    /**
     * 上海资信开关
     */
    public static final String SH_CREDIT_KEY = "SH_CREDIT_KEY";//是否开启上海资信征信源
    
    /**
     * 新钉钉报警配置
     */
    public static final String DINGDING_SEND_MSG="DINGDING_SEND_MSG";
    
    /**
     * APP禁止项版本号 ,配置参数变量
     */
    public static final String APP_VERSION_COUNT = "APP_VERSION_COUNT";
    
    public static final String MD5_XK = "MD5_XK";//用于大数据决策树切换MD5加
    /**
     * 征信机构是否真正获取报告配置开关
     */
    public static final String OPEN = "open";
    public static final String CLOSE = "close";

    /**
     * 大数据推送同盾数据地址
     */
    public static final String DSJ_TD_MESSAGE_URL = "DSJ_TD_MESSAGE_URL";
    
    /** 91征信在redis中的code值 **/
    public static final String XCZX_REDIS_CODE = "XCZX:DATA:";

    /**
     * 准入规则包的开关
     */
    public static final String XIAO_NIU_RULE_ON_OFF = "XIAO_NIU_RULE_ON_OFF";
    
    /**数据共享接口在redis中存储的文件夹**/
    public static final String SHARED_REDIS_CODE = "SHARED_REDIS_DATA";

    /**
     * 申请单命中黑名单的个数
     */
    public static final int APPLICATION_BLACK_NUM = 2;

    /**
     * 第三方报告小牛
     */
    public static final String THIRDPARTY_REPORT_XIAO_NIU = "13001";
    
    /**
     * 用户个人信息提交时长查询
     */
    public static final String FIND_TIMES = "FIND_TIMES";
    
    /**
     * 定时任务job group前缀
     */
    public static final String JOB_PREFIX_TH = "JOB_";

    /**
     * 风控系统定时任务
     */
    public static final String BACKEND_TASK_TH = "TH";
    /**
     * 同盾设备指纹注册事件
     * **/
    public static final String TD_FACILITY_REGISTER = "TD_FACILITY_REGISTER";

    /**
     * 系统参数
     *  反欺诈分值-老用户1.0-模板ID
     *  决定 老用户模型最后算分 决定的模板ID
     */
    public static final String SC_PARAM_HZ_OLD_USER_FRAUD_TPL_ID = "HZ_OLD_USER_FRAUD_TPL_ID";

    /**
     * 老用户 申请时间 距离 上次风控审核通过时间 >30 天 过新用户准入流程
     */
    public static final String SWITCH_OLD_USER_OVER_30 = "SWITCH_OLD_USER_OVER_30";

    /**
     * 老用户 是否匹配 黑名单
     */
    public static final String SWITCH_OLD_USER_MATCH_BLACKLIST = "SWITCH_OLD_USER_MATCH_BLACKLIST";
}
