package com.shangyong.backend.common;

/**
 * 91征信 
 * @author es_ai
 *
 */
public class TaskTypeConstants {
	

	/**客户借款申请扩展表 数据存储类型**/
	public static final String XCZX_TASK_TYPE = "08001";
	public static final String XCZX_TASK_NAME = "91征信-多头借贷";
	public static final String XCZX_TASK_ISEND = "1";
	/**极光反欺诈存储类型***/
	public static final String JG_TASK_TYPE = "08002";
	public static final String JG_TASK_NAME = "极光-反欺诈";
	public static final String JG_TASK_ISEND = "0";
	/**同盾反欺诈存储类型***/
	public static final String TD_TASK_TYPE = "08003";
	public static final String TD_TASK_NAME = "同盾-反欺诈";
	public static final String TD_TASK_ISEND = "0";
	/**白骑士运营商数据存储类型***/
	public static final String BQS_INFO_TASK_TYPE = "08004";
	public static final String BQS_INFO_TASK_NAME = "白骑士-运营商";
	public static final String BQS_INFO_TASK_ISEND = "0";
	/**白骑士运营商数据报告存储类型***/
	public static final String BQS_REPORT_UPLOAD_DIR = "08005";
	public static final String BQS_REPORT_UPLOAD_NAME = "白骑士-资信云";
	public static final String BQS_REPORT_UPLOAD_ISEND = "0";
	/**腾讯云反欺诈存储类型***/
	public static final String TXY_TASK_TYPE = "08006";
	public static final String TXY_TASK_NAME = "腾讯云-反欺诈";
	public static final String TXY_TASK_ISEND = "0";
	/**同盾设备指纹存储类型***/
	public static final String TD_FACILITY_TYPE = "08007";
	public static final String TD_FACILITY_NAME = "同盾-设备指纹";
	public static final String TD_FACILITY_ISEND = "0";
	/**易极付黑名单类型***/
	public static final String YJF_TASK_TYPE = "08008";
	public static final String YJF_TASK_NAME = "易极付-黑名单";
	public static final String YJF_TASK_ISEND = "0";
	/**上海资信类型***/
	public static final String SH_CREDIT_TYPE = "08009";
	public static final String SH_CREDIT_NAME = "上海资信-借贷";
	public static final String SH_CREDIT_ISEND = "0";
	/**同盾数据魔盒类型***/
	public static final String TD_REPORT_TYPE = "08010";
	public static final String TD_REPORT_NAME = "同盾报告-数据魔盒";
	public static final String TD_REPORT_ISEND = "0";
	/**91征信接口请求正常**/
	public static final String XCZX_HTTP_SUCCESS = "0000";
	
	/**91征信接口请求失败**/
	public static final String XCZX_HTTP_FAIL = "9999";
	
	/** oss 参数配置 **/
	// accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
	// 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
	// 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
	// Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
	// Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
	public static final String ENDPOINT = "http://oss-cn-shanghai.aliyuncs.com";
	
	public static final String ACCESS_KEY_ID= "LTAIWVBu27n5Vw0d";
	
	public static final String ACCESS_KEY_SECRET= "18i7N4uSuLMlICX3Lk7r98uVOWPz4b";
	
	public static final String BUCKET_NAME= "shandai-test-oss";
}
