/**
  * Copyright 2017 bejson.com 
  */
package com.shangyong.backend.entity.bqsrep.jsonbean;
import java.util.List;

/**
 * Auto-generated: 2017-12-14 18:42:48
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class MnoCommonlyUsedServices {

    private List<ServiceMonthInfos> serviceMonthInfos;
    private int connectCount;
    private String seviceType;
    private String serviceName;
    public void setServiceMonthInfos(List<ServiceMonthInfos> serviceMonthInfos) {
         this.serviceMonthInfos = serviceMonthInfos;
     }
     public List<ServiceMonthInfos> getServiceMonthInfos() {
         return serviceMonthInfos;
     }

    public void setConnectCount(int connectCount) {
         this.connectCount = connectCount;
     }
     public int getConnectCount() {
         return connectCount;
     }

    public void setSeviceType(String seviceType) {
         this.seviceType = seviceType;
     }
     public String getSeviceType() {
         return seviceType;
     }

    public void setServiceName(String serviceName) {
         this.serviceName = serviceName;
     }
     public String getServiceName() {
         return serviceName;
     }

}