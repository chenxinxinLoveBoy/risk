package com.shangyong.backend.common;

/**
 *  * User: kenzhao
 *  * Date: 2017/8/16
 *  * Time: 22:03
 *  * PROJECT_NAME: risk-parent_2.4
 *  * PACKAGE_NAME: com.shangyong.backend.common
 *  * DESC:
 *  * Version: v1.0.0
 *  
 */
public class MQConstants {


    /**
     * 返回成功
     */
    public static final String RETURN_SUCCESS = "success";
    /**
     * 返回错误
     */
    public static final String RETURN_ERROR = "failure";

    /**
     * mq 服务类型 用于写日志记录
     */
    public static final String MQ_MESSAGE_SERVICE = "hbase";



    /**
     * 消息重发的路由键标记
     */
    public static final String MQ_RESEND = "_RESEND";

    /**
     * 欺诈队列名称
     */
    public static final String MQ_QUEUE_QZQD = "MQ_QUEUE_QZQD";

    /**
     * 欺诈日志队列名称
     */
    public static final String MQ_QUEUE_QZQD_LOG = "MQ_QUEUE_QZQD_LOG";
    /**
     * 欺诈重发日志队列名称
     */
    public static final String MQ_QUEUE_QZQD_RESEND_LOG = "MQ_QUEUE_QZQD_RESEND_LOG";

    /**
     * 欺诈交换机名称
     */
    public static final String MQ_EXCHANGE_QZQD = "MQ_EXCHANGE_QZQD";
    /**
     * 欺诈路由键名称
     */
    public static final String MQ_ROUTINGKEY_QZQD = "MQ_ROUTINGKEY_QZQD.#";
    /**
     * 日志路由名称
     */
    public static final String MQ_ROUTINGKEY_LOG = "MQ_ROUTINGKEY_QZQD.LOG.#";

    /**
     * 欺诈队列名称-回调
     */
    public static final String MQ_QUEUE_QZQD_BACK = "MQ_QUEUE_QZQD_BACK";
    /**
     * 欺诈交换机名称-回调
     */
    public static final String MQ_EXCHANGE_QZQD_BACK = "MQ_EXCHANGE_QZQD_BACK";
    /**
     * 欺诈路由键名称-回调
     */
    public static final String MQ_ROUTINGKEY_QZQD_BACK = "MQ_ROUTINGKEY_QZQD_BACK.#";


    /**
     * 欺诈队列名称-回调
     */
    public static final String MQ_QUEUE_QZQD_BACK_LOG = "MQ_QUEUE_QZQD_BACK_LOG";
    /**
     * 欺诈路由键名称-回调
     */
    public static final String MQ_ROUTINGKEY_QZQD_BACK_LOG = "MQ_ROUTINGKEY_QZQD_BACK.LOG";

    /**
     * 死信队列名称-回调
     */
    public static final String MQ_DEAD_QUEUE_QZQD = "MQ_DEAD_QUEUE_QZQD";

    /**
     * 死信队列名称-回调
     */
    public static final String MQ_DEAD_QUEUE_QZQD_DING = "MQ_DEAD_QUEUE_QZQD_DING";

    /**
     * 死信日志队列名称-回调
     */
    public static final String MQ_DEAD_QUEUE_QZQD_LOG = "MQ_DEAD_QUEUE_QZQD_LOG";
    /**
     * 死信交换机名称-回调
     */
    public static final String MQ_DEAD_EXCHANGE_QZQD_BACK = "MQ_DEAD_EXCHANGE_QZQD_BACK";
    /**
     * 死信路由键名称-回调
     */
    public static final String MQ_DEAD_ROUTINGKEY_QZQD_BACK = "MQ_DEAD_ROUTINGKEY_QZQD_BACK";

    public static final String MQ_DEAD_ROUTINGKEY_QZQD_BACK_DING = "MQ_DEAD_ROUTINGKEY_QZQD_BACK.#";
    
    
    /**
     * add: xiajiyun
     * 提供bu_promote_detailed数据源的队列交换机(MQ提额用)
     */
    public static final String MQ_PROMOTE_EXCHANG = "PROMOTE_EXCHANG";
    
    
    /**
     * add: xiajiyun
     * 提供bu_promote_detailed数据源的队列名称（处理'正常'数据流程的队列）
     */
    public static final String MQ_PROMOTE_QUEUE = "PROMOTE";
    
    
    /**
     * add: xiajiyun
     * MQ_QUEUE_SOURCE队列名称的路由键（处理'正常'数据流程的routingKey）
     */
    public static final String MQ_PROMOTE_ROUTINGKEY = "PROMOTE_ROUTINGKEY";
    
    
    /**
     * add: xiajiyun
     * 提供bu_promote_detailed数据源的队列名称（处理'公积金'异常数据的队列）
     */
    public static final String MQ_PROMOTE_ERROR_GJJ_QUEUE = "PROMOT_GJJ_EERROR";
    
    /**
     * add: xiajiyun
     * 路由键（处理'PROMOTE_ROUTINGKEY_ERROR.'异常数据的路由键，匹配）
     */
    public static final String MQ_PROMOTE_ROUTINGKEY_ERROR = "PROMOTE_ROUTINGKEY_ERROR_";
     
    /**
     * add: xiajiyun
     * 提供bu_promote_detailed数据源的队列名称（处理'社保'异常数据的队列）
     */
    public static final String MQ_PROMOTE_ERROR_SBAO_QUEUE = "PROMOT_SBAO_EERROR";
    
    /**
     * add: xiajiyun
     * 路由键（处理'社保'异常数据的路由键）
     */
//    public static final String MQ_PROMOTE_ROUTINGKEY_SBAO_ERROR = "PROMOTE_ROUTINGKEY_SBAO_ERROR";
     
    /**
     * add: xiajiyun
     * 提供bu_promote_detailed数据源的队列名称（处理'学信'异常数据的队列）
     */
    public static final String MQ_PROMOTE_ERROR_XUEXIN_QUEUE = "PROMOT_XUEXIN_EERROR";
    
    /**
     * add: xiajiyun
     * 路由键（处理'学信'异常数据的路由键）
     */
//    public static final String MQ_PROMOTE_ROUTINGKEY_XUEXIN_ERROR = "PROMOTE_ROUTINGKEY_XUEXIN_ERROR";
    
    /**
     * add: xiajiyun
     * 提供bu_promote_detailed数据源的队列名称（处理'人行'异常数据的队列）
     */
    public static final String MQ_PROMOTE_ERROR_RXZX_QUEUE = "PROMOT_RXZX_EERROR";
    
    /**
     * add: xiajiyun
     * 路由键（处理'人行'异常数据的路由键）
     */
//    public static final String MQ_PROMOTE_ROUTINGKEY_RXZX_ERROR = "PROMOTE_ROUTINGKEY_RXZX_ERROR";
    
    
    /**
     * add: xiajiyun
     * 提供bu_promote_detailed数据源的队列名称（处理'电商'异常数据的队列）
     */
    public static final String MQ_PROMOTE_ERROR_DS_QUEUE = "PROMOT_DS_EERROR";
    
    /**
     * add: xiajiyun
     * 路由键（处理'电商'异常数据的路由键）
     */
//    public static final String MQ_PROMOTE_ROUTINGKEY_DS_ERROR = "PROMOTE_ROUTINGKEY_DS_ERROR";
    
    
    /**
     * 死信路由键名称-回调
     */
//    public static final String MQ_DEAD_ROUTINGKEY_QZQD_BACK_LOG = "MQ_DEAD_ROUTINGKEY_QZQD_BACK.LOG";
    
    
    
    
    /**
     * 02001- 芝麻信用欺诈清单
     */
    public static final String CHECKAPPLY_ZM = "02001";
    
    /**
     * 05001- 聚信立蜜罐
     */
    public static final String CHECKAPPLY_JXLMG = "05001";
    
    /**
     * 08001-白骑士欺诈
     */
    public static final String CHECKAPPLY_BQS = "08001";
    
    /**
     * 12001-小视科技(银行)
     */
    public static final String CHECKAPPLY_XSKJ_BANK = "12001";
    
    /**
     * 12002-小视科技(网贷)
     */
    public static final String CHECKAPPLY_XSKJ_NETLOAN = "12002";
    
    /**
     * 11001-葫芦索伦
     */
    public static final String CHECKAPPLY_HLSL = "11001";
    
    /**
     * 09001-宜信
     */
    public static final String CHECKAPPLY_YX = "09001";
    
    /**
     * 07001-91征信
     */
    public static final String CHECKAPPLY_91ZX = "07001";  
    
    /**
     * 07002-91征信回调
     */
    public static final String CHECKAPPLY_91ZX_HT = "07002";  
    
    
    
    
    /**
     * 0999- 上传文件
     */
    public static final String CHECKAPPLY_UPLOAD = "0999";
    
    
    /**
     * add: xiajiyun
     * 第三方征信交换机
     */
    public static final String MQ_CHECKAPPLY_EXCHANG = "CHECKAPPLY_EXCHANG";
    
    /**
     * add: xiajiyun
     * 路由键（routingKey）
     */
    public static final String MQ_CHECKAPPLY_ROUTINGKEY = "CHECKAPPLY_ROUTINGKEY";
    
    /**
     * add: xiajiyun
     * 重发路由键（routingKey）
     */
    public static final String MQ_CHECKAPPLY_REVERT_ROUTINGKEY = "CHECKAPPLY_REVERT_ROUTINGKEY_";
    
    
    
    /**
     * add: xiajiyun
     * 用于区分是否重发
     */
    public static final String MQ_CHECKAPPLY_REVERT = "_REVERT";
    
    
    
    /**
     * add: xiajiyun
     *'接收外部消息公共'队列
     */
    public static final String MQ_CHECKAPPLY_QUEUE = "CHECKAPPLY_QUEUE";
    
    /**
     * add: xiajiyun
     *'芝麻信用关注欺诈清单'队列
     */
    public static final String MQ_CHECKAPPLY_ZMQD_QUEUE = "CHECKAPPLY_ZMQD_QUEUE";
    
    /**
     * add: xiajiyun
     *'芝麻信用关注欺诈清单'队列(重发处理异常)
     */
    public static final String MQ_CHECKAPPLY_ZMQD_REVERT_QUEUE = "CHECKAPPLY_ZMQD_REVERT_QUEUE";
    
    
    /**
     * add: xiajiyun
     *'聚信立蜜罐'队列
     */
    public static final String MQ_CHECKAPPLY_JXLMG_QUEUE = "CHECKAPPLY_JXLMG_QUEUE";
    
    /**
     * add: xiajiyun
     *'聚信立蜜罐'重发用的队列
     */
    public static final String MQ_CHECKAPPLY_JXLMG_REVERT_QUEUE = "CHECKAPPLY_JXLMG_REVERT_QUEUE";
    
    /**
     * add: xiajiyun
     *'白骑士欺诈'队列
     */
    public static final String MQ_CHECKAPPLY_BQS_QUEUE = "CHECKAPPLY_BQS_QUEUE";
    
    /**
     * add: xiajiyun
     *'白骑士欺诈'重发用到的队列
     */
    public static final String MQ_CHECKAPPLY_BQS_REVERT_QUEUE = "CHECKAPPLY_BQS_REVERT_QUEUE";
    
    /**
     * add: luoyanchong
     *'文件上传'重发用到的队列
     */
    public static final String MQ_CHECKAPPLY_UPLOAD_QUEUE = "CHECKAPPLY_UPLOAD_QUEUE";
    
    
    /**
     * add: CG
     *'接收外部消息公共'队列
     */
    public static final String MQ_CHECKCREDITORG_QUEUE = "CHECKCREDITORG_QUEUE";
    
    /**
     * add: CG
     *'小视科技 (银行)'队列
     */
    public static final String MQ_CHECKAPPLY_XSKJ_BANK_QUEUE = "CHECKAPPLY_XSKJ_BANK_QUEUE";
    /**
     * add: CG
     *'小视科技(银行) '重发队列
     */
    public static final String MQ_CHECKAPPLY_XSKJ_BANK_REVERT_QUEUE = "CHECKAPPLY_XSKJ_BANK_REVERT_QUEUE";
    
    
    /**
     * add: CG
     *'小视科技(网贷) '队列
     */
    public static final String MQ_CHECKAPPLY_XSKJ_NETLOAN_QUEUE = "CHECKAPPLY_XSKJ_NETLOAN_QUEUE";
    /**
     * add: CG
     *'小视科技(网贷) '重发队列
     */
    public static final String MQ_CHECKAPPLY_XSKJ_NETLOAN_REVERT_QUEUE = "CHECKAPPLY_XSKJ_NETLOAN_REVERT_QUEUE";
    
    
    /**
     * add: CG
     *'葫芦索伦'队列
     */
    public static final String MQ_CHECKAPPLY_HLSL_QUEUE = "CHECKAPPLY_HLSL_QUEUE";
    /**
     * add: CG
     *'葫芦索伦 '重发队列
     */
    public static final String MQ_CHECKAPPLY_HLSL_REVERT_QUEUE = "CHECKAPPLY_HLSL_REVERT_QUEUE";
    
    /**
     * add: CG
     *'宜信'队列
     */
    public static final String MQ_CHECKAPPLY_YX_QUEUE = "CHECKAPPLY_YX_QUEUE";
    /**
     * add: CG
     *'宜信 '重发队列
     */
    public static final String MQ_CHECKAPPLY_YX_REVERT_QUEUE = "CHECKAPPLY_YX_REVERT_QUEUE";
    
    /**
     * add: CG
     *'91征信'队列
     */
    public static final String MQ_CHECKAPPLY_91ZX_QUEUE = "CHECKAPPLY_91ZX_QUEUE";
    /**
     * add: CG
     *'91征信 '重发队列
     */
    public static final String MQ_CHECKAPPLY_91ZX_REVERT_QUEUE = "CHECKAPPLY_91ZX_REVERT_QUEUE";
    /**
     * add: CG
     *'91征信回调'队列
     */
    public static final String MQ_CHECKAPPLY_91ZXHT_QUEUE = "CHECKAPPLY_91ZXHT_QUEUE";
    
    /**
     * add: xiajiyun
     *'91征信回调'重发队列
     */
    public static final String MQ_CHECKAPPLY_91ZXHT_REVERT_QUEUE = "CHECKAPPLY_91ZXHT_REVERT_QUEUE";
    
    
    /**
     * JSON文件上传队列名称-回调
     */
    public static final String MQ_QUEUE_UPLOAD = "MQ_QUEUE_UPLOAD";
    /**
     * JSON文件 信息mongo-回调
     */
    public static final String MQ_QUEUE_UPLOAD_MONGO = "MQ_QUEUE_UPLOAD_MONGO";
    /**
     * JSON文件上传交换机名称-回调
     */
    public static final String MQ_EXCHANGE_UPLOAD = "MQ_EXCHANGE_UPLOAD";
    /**
     * JSON文件上传路由键名称-回调
     */
    public static final String MQ_ROUTINGKEY_UPLOAD = "MQ_ROUTINGKEY_UPLOAD.#";
    /**
     * JSON文件上传路由键名称-回调
     */
    public static final String MQ_ROUTINGKEY_UPLOAD_ALI = "MQ_ROUTINGKEY_UPLOAD";
    /**
     * JSON文件上传路由键名称-回调
     */
    public static final String MQ_ROUTINGKEY_UPLOAD_MONGO = "MQ_ROUTINGKEY_UPLOAD_MONGO";

    /**
     * 通讯录队列名称-回调
     */
    public static final String MQ_QUEUE_TXL = "MQ_QUEUE_TXL";
    /**
     * 通讯录交换机名称-回调
     */
    public static final String MQ_EXCHANGE_TXL = "MQ_EXCHANGE_TXL";
    /**
     * 通讯录路由键名称-回调
     */
    public static final String MQ_ROUTINGKEY_TXL = "MQ_ROUTINGKEY_TXL";
    
    /**
     * app队列名称
     */
    public static final String MQ_QUEUE_APP = "MQ_QUEUE_APP";
    /**
     * app交换机名称
     */
    public static final String MQ_EXCHANGE_APP = "MQ_EXCHANGE_APP";
    /**
     * app路由键名称
     */
    public static final String MQ_ROUTINGKEY_APP = "MQ_ROUTINGKEY_APP";
    
    /**
     * 审批app列表信息推送队列名称-回调
     */
    public static final String MQ_QUEUE_SPLB = "MQ_QUEUE_SPLB";
    /**
     * 审批app列表信息推送交换机名称-回调
     */
    public static final String MQ_EXCHANGE_SPLB_BACK = "MQ_EXCHANGE_SPLB_BACK";
    /**
     * 审批app列表信息推送路由键名称-回调
     */
    public static final String MQ_ROUTINGKEY_SPLB_BACK = "MQ_ROUTINGKEY_SPLB_BACK";

    /**
     * 审批通讯录信息推送队列名称-回调
     */
    public static final String MQ_QUEUE_SPTXL = "MQ_QUEUE_SPTXL";
    /**
     * 审批通讯录信息推送交换机名称-回调
     */
    public static final String MQ_EXCHANGE_SPTXL_BACK = "MQ_EXCHANGE_SPTXL_BACK";
    /**
     * 审批通讯录信息推送路由键名称-回调
     */
    public static final String MQ_ROUTINGKEY_SPTXL_BACK = "MQ_ROUTINGKEY_SPTXL_BACK";

    /**
     * 审批短信信息推送队列名称-回调
     */
    public static final String MQ_QUEUE_SPDX = "MQ_QUEUE_SPDX";
    /**
     * 审批短信信息推送交换机名称-回调
     */
    public static final String MQ_EXCHANGE_SPDX_BACK = "MQ_EXCHANGE_SPDX_BACK";
    /**
     * 审批短信信息推送路由键名称-回调
     */
    public static final String MQ_ROUTINGKEY_SPDX_BACK = "MQ_ROUTINGKEY_SPDX_BACK";

    /**
     * 审批通话记录推送队列名称-回调
     */
    public static final String MQ_QUEUE_SPTH = "MQ_QUEUE_SPTH";
    /**
     * 审批通话记录推送交换机名称-回调
     */
    public static final String MQ_EXCHANGE_SPTH_BACK = "MQ_EXCHANGE_SPTH_BACK";
    /**
     * 审批通话记录推送路由键名称-回调
     */
    public static final String MQ_ROUTINGKEY_SPTH_BACK = "MQ_ROUTINGKEY_SPTH_BACK";
    
    /*********************************app推送MQ客户信息异常队列开始*******************************************/
    /**
     * 审批app列表信息推送队列名称-回调
     */
    public static final String MQ_QUEUE_YC_SPLB = "MQ_QUEUE_YC_SPLB";
    /**
     * 审批app列表信息推送交换机名称-回调
     */
    public static final String MQ_EXCHANGE_YC_SPLB_BACK = "MQ_EXCHANGE_YC_SPLB_BACK";
    /**
     * 审批app列表信息推送路由键名称-回调
     */
    public static final String MQ_ROUTINGKEY_YC_SPLB_BACK = "MQ_ROUTINGKEY_YC_SPLB_BACK";

    /**
     * 审批通讯录信息推送队列名称-回调
     */
    public static final String MQ_QUEUE_YC_SPTXL = "MQ_QUEUE_YC_SPTXL";
    /**
     * 审批通讯录信息推送交换机名称-回调
     */
    public static final String MQ_EXCHANGE_YC_SPTXL_BACK = "MQ_EXCHANGE_YC_SPTXL_BACK";
    /**
     * 审批通讯录信息推送路由键名称-回调
     */
    public static final String MQ_ROUTINGKEY_YC_SPTXL_BACK = "MQ_ROUTINGKEY_YC_SPTXL_BACK";

    /**
     * 审批短信信息推送队列名称-回调
     */
    public static final String MQ_QUEUE_YC_SPDX = "MQ_QUEUE_YC_SPDX";
    /**
     * 审批短信信息推送交换机名称-回调
     */
    public static final String MQ_EXCHANGE_YC_SPDX_BACK = "MQ_EXCHANGE_YC_SPDX_BACK";
    /**
     * 审批短信信息推送路由键名称-回调
     */
    public static final String MQ_ROUTINGKEY_YC_SPDX_BACK = "MQ_ROUTINGKEY_YC_SPDX_BACK";

    /**
     * 审批通话记录推送队列名称-回调
     */
    public static final String MQ_QUEUE_YC_SPTH = "MQ_QUEUE_YC_SPTH";
    /**
     * 审批通话记录推送交换机名称-回调
     */
    public static final String MQ_EXCHANGE_YC_SPTH_BACK = "MQ_EXCHANGE_YC_SPTH_BACK";
    /**
     * 审批通话记录推送路由键名称-回调
     */
    public static final String MQ_ROUTINGKEY_YC_SPTH_BACK = "MQ_ROUTINGKEY_YC_SPTH_BACK";
    /*********************************app推送MQ客户信息异常队列配置结束*******************************************/
    
    /**
     * 向大数据发送消息
     */
    public static final String MQ_SENDTO_HBASE = "producer/sendToHBase";
    /**
     * 向风控后台发送消息
     */
    public static final String MQ_SENDTO_BACKEND = "producer/sendToBackend";


    /**
     * 公用的报警交换机
     */
    public static final String MQ_EXCHANGE_COMMON_ERROR = "MQ_COMMON_ERROR";
    /**
     * 公用的报警路由
     */
    public static final String MQ_ROUTING_COMMON_ERROR = "MQ_ROUTING_COMMON_ERROR";
    /**
     * 公用的报警队列
     */
    public static final String MQ_QUEUE_COMMON_ERROR = "MQ_QUEUE_COMMON_ERROR";


    /**
     * 公用的钉钉报警交换机
     */
    public static final String MQ_EXCHANGE_COMMON_ERROR_DING_HBASE = "MQ_COMMON_ERROR_DING";
    /**
     * 公用的钉钉报警路由
     */
    public static final String MQ_ROUTING_COMMON_ERROR_DING_HBASE = "MQ_ROUTING_COMMON_ERROR_DING";
    /**
     * 公用的钉钉报警队列
     */
    public static final String MQ_QUEUE_COMMON_ERROR_DING_HBASE = "MQ_QUEUE_COMMON_ERROR_DING";

    /**
     * 风控默认钉钉报警交换机
     */
    public static final String MQ_EXCHANGE_ERROR_DING_BACKEND = "MQ_BACKEND_ERROR_DING";
    /**
     * 风控默认钉钉报警路由
     */
    public static final String MQ_ROUTING_ERROR_DING_BACKEND = "MQ_ROUTING_BACKEND_ERROR_DING.#";
    /**
     * 风控默认钉钉报警队列
     */
    public static final String MQ_QUEUE_ERROR_DING_BACKEND = "MQ_QUEUE_BACKEND_ERROR_DING";
    /**
     * 风控默认钉钉报警日志队列
     */
    public static final String MQ_QUEUE_ERROR_DING_BACKEND_LOG = "MQ_QUEUE_BACKEND_ERROR_DING_LOG";



    /**
     * 提现单的MQ队列交换机
     */
    public static final String MQ_EXCHANGE_PRESENT_BACKEND = "MQ_EXCHANGE_PRESENT_BACKEND";


    /**
     * 接收提现单，解决处理顺序
     */
    public static final String MQ_QUEUE_PRESENT_INIT_BACKEND = "MQ_QUEUE_PRESENT.INIT";
    /**
     * 芝麻行业关注名单
     */
    public static final String MQ_QUEUE_PRESENT_XMHYGXQD_BACKEND = "MQ_QUEUE_PRESENT.XMHYGXQD";
    /**
     * 同盾贷前审核
     */
    public static final String MQ_QUEUE_PRESENT_TD_BACKEND = "MQ_QUEUE_PRESENT.TD";
    /**
     * 白骑士反欺诈云
     */
    public static final String MQ_QUEUE_PRESENT_BQS_BACKEND = "MQ_QUEUE_PRESENT.BQS";
    /**
     * 葫芦
     */
    public static final String MQ_QUEUE_PRESENT_HL_BACKEND = "MQ_QUEUE_PRESENT.HL";

    /**
     * 接收提现单，解决处理顺序
     */
    public static final String MQ_ROUTING_PRESENT_INIT_BACKEND = "MQ_ROUTING_PRESENT.INIT";
    /**
     * 芝麻行业关注名单
     */
    public static final String MQ_ROUTING_PRESENT_XMHYGXQD_BACKEND = "MQ_ROUTING_PRESENT.XMHYGXQD";
    /**
     * 同盾贷前审核
     */
    public static final String MQ_ROUTING_PRESENT_TD_BACKEND = "MQ_ROUTING_PRESENT.TD";
    /**
     * 白骑士反欺诈云
     */
    public static final String MQ_ROUTING_PRESENT_BQS_BACKEND = "MQ_ROUTING_PRESENT.BQS";
    /**
     * 葫芦
     */
    public static final String MQ_ROUTING_PRESENT_HL_BACKEND = "MQ_ROUTING_PRESENT.HL";


    /**
     * 提现单的MQ队列交换机
     */
    public static final String MQ_DEAD_EXCHANGE_PRESENT = "MQ_DEAD_EXCHANGE_PRESENT";
    /**
     * 葫芦
     */
    public static final String MQ_DEAD_ROUTING_PRESENT = "MQ_DEAD_ROUTING_PRESENT";
    /**
     * 白骑士反欺诈云
     */
    public static final String MQ_DEAD_QUEUE_PRESENT = "MQ_DEAD_QUEUE_PRESENT";
    
    
    /**
     * add: CG
     * 批量补发推送消息数据至APP交换机MQ_EXCHANGE_QZQD
     */
    public static final String MQ_EXCHANG_PUSHAPP = "MQ_EXCHANG_PUSHAPP";
    /**
     * add: CG
     * 批量补发推送消息数据至APP路由键（routingKey）
     */
    public static final String MQ_ROUTINGKEY_PUSHAPP = "MQ_ROUTINGKEY_PUSHAPP";
    /**
     * add: CG
     * 批量补发推送消息数据至APP队列名称
     */
    public static final String MQ_QUEUE_PUSHAPP = "MQ_QUEUE_PUSHAPP";
    
    /**
     * add: CG
     * 批量补发推送提额消息数据至APP队列交换机(MQ提额用)
     */
    public static final String MQ_EXCHANG_PUSHAPP_TE = "MQ_EXCHANG_PUSHAPP_TE";
    /**
     * add: CG
     * 批量补发推送提额消息数据至APP队列名称（处理'正常'数据流程的队列）
     */
    public static final String MQ_QUEUE_PUSHAPP_TE = "MQ_QUEUE_PUSHAPP_TE";
    /**
     * add: CG
     * 批量补发推送提额消息数据至APP路由键（处理'正常'数据流程的routingKey）
     */
    public static final String MQ_ROUTINGKEY_PUSHAPP_TE = "MQ_ROUTINGKEY_PUSHAPP_TE";

    /**
     * add: xiajiyun
     * MQ，记录消息内容体
     */
    public static final String MQ_SEND_REDIS_INFO = "MQ:SEND:REDIS_INFO";

}
