package com.shangyong.backend.utils;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.shangyong.backend.entity.SysParam;

/**
 *  * User: kenzhao
 *  * Date: 2017/8/14
 *  * Time: 19:33
 *  * PROJECT_NAME: risk-parent_2.3.3
 *  * PACKAGE_NAME: com.honglu.backend.utils
 *  * DESC:
 *  * Version: v1.0.0
 *  
 */
public class OssClientUtils {
    private static OSSClient ossClien;

    public static OSSClient getOssClienSingle(SysParam sysParam) {
        return getOSSClient(sysParam);
    }

    private static OSSClient getOSSClient(SysParam sysParam){
        if(ossClien == null){
            synchronized (OssClientUtils.class){
                if(ossClien == null){
                    if(sysParam == null){
                        return null;
                    }
                    // 获取参数值
                    String endpoint = sysParam.getParamValueOne().trim();
                    String accessKeyId = sysParam.getParamValueTwo().trim();
                    String accessKeySecret = sysParam.getParamValueThree().trim();
                    // 创建ClientConfiguration实例
                    ClientConfiguration conf = new ClientConfiguration();
                    // 设置OSSClient使用的最大连接数，默认1024
                    conf.setMaxConnections(500);
                    // 设置请求超时时间，默认50秒
                    conf.setSocketTimeout(50000);
                    // 设置失败请求重试次数，默认3次
                    conf.setMaxErrorRetry(5);
                    OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
                    ossClien = ossClient;
                }
            }
        }
        return  ossClien;
    }
}
