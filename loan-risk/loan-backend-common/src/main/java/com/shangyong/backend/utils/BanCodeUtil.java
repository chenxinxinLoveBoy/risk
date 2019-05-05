package com.shangyong.backend.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 禁止项 工具类
 * @author caisheng
 * @date 2018-08-07
 */
public class BanCodeUtil {

    /**
     * 添加校验点-值 到 检查list中
     * @param 		checkList
     * @param 		key
     * @param 		value
     * @return		待检查list
     */
    public static void addCheckPoint(List<Map<String,Object>> checkList, String key, Object value){
        if(checkList == null){
            checkList = new ArrayList<Map<String,Object>>();
        }
        Map<String, Object> checkMap = new HashMap<String, Object>();
        checkMap.put(key, value);
        checkList.add(checkMap);
        return;
    }

//    /**
//     * 添加校验点-值 到 检查list中
//     * @param 		checkList
//     * @param 		key
//     * @param 		value
//     * @return		待检查list
//     */
//    private List<Map<String,Object>> aaddCheckPoint(List<Map<String,Object>> checkList, String key, Object value){
//        if(checkList == null){
//            checkList = new ArrayList<Map<String,Object>>();
//        }
//        Map<String, Object> checkMap = new HashMap<String, Object>();
//        checkMap.put(key, value);
//        checkList.add(checkMap);
//        return checkList;
//    }
//
//
//    /**
//     * 添加校验点-值 到 检查list中
//     * @param 		checkList
//     * @param 		key
//     * @param 		value
//     * @return		待检查list
//     */
//    private List<Map<String,Object>> jgaddCheckPoint(List<Map<String,Object>> checkList, String key, Object value){
//        if(checkList == null){
//            checkList = new ArrayList<Map<String,Object>>();
//        }
//        Map<String, Object> checkMap = new HashMap<String, Object>();
//        checkMap.put(key, value);
//        checkList.add(checkMap);
//        return checkList;
//    }
//
//    /**
//     * 添加校验点-值 到 检查list中
//     * @param 		checkList
//     * @param 		key
//     * @param 		value
//     * @return		待检查list
//     */
//    private List<Map<String,Object>> yjfaddCheckPoint(List<Map<String,Object>> checkList, String key, Object value){
//        if(checkList == null){
//            checkList = new ArrayList<Map<String,Object>>();
//        }
//        Map<String, Object> checkMap = new HashMap<String, Object>();
//        checkMap.put(key, value);
//        checkList.add(checkMap);
//        return checkList;
//    }
//
//
//    /**
//     * 添加校验点-值 到 检查list中
//     * @param 		checkList
//     * @param 		key
//     * @param 		value
//     * @return		待检查list
//     */
//    private List<Map<String,Object>> tdaddCheckPoint(List<Map<String,Object>> checkList, String key, Object value){
//        if(checkList == null){
//            checkList = new ArrayList<Map<String,Object>>();
//        }
//        Map<String, Object> checkMap = new HashMap<String, Object>();
//        checkMap.put(key, value);
//        checkList.add(checkMap);
//        return checkList;
//    }
//
//    /**
//     * 添加校验点-值 到 检查list中
//     * @param 		checkList
//     * @param 		key
//     * @param 		value
//     * @return		待检查list
//     */
//    private List<Map<String,Object>> tdfaaddCheckPoint(List<Map<String,Object>> checkList, String key, Object value){
//        if(checkList == null){
//            checkList = new ArrayList<Map<String,Object>>();
//        }
//        Map<String, Object> checkMap = new HashMap<String, Object>();
//        checkMap.put(key, value);
//        checkList.add(checkMap);
//        return checkList;
//    }
//
//    /**
//     * 添加校验点-值 到 检查list中
//     * @param 		checkList
//     * @param 		key
//     * @param 		value
//     * @return		待检查list
//     */
//    private List<Map<String,Object>> bqsaddCheckPoint(List<Map<String,Object>> checkList, String key, Object value){
//        if(checkList == null){
//            checkList = new ArrayList<Map<String,Object>>();
//        }
//        Map<String, Object> checkMap = new HashMap<String, Object>();
//        checkMap.put(key, value);
//        checkList.add(checkMap);
//        return checkList;
//    }
//
//
//    /**
//     * 添加校验点-值 到 检查list中
//     * @param 		checkList
//     * @param 		key
//     * @param 		value
//     * @return		待检查list
//     */
//    private List<Map<String,Object>> tdfqzaddCheckPoint(List<Map<String,Object>> checkList, String key, Object value){
//        if(checkList == null){
//            checkList = new ArrayList<Map<String,Object>>();
//        }
//        Map<String, Object> checkMap = new HashMap<String, Object>();
//        checkMap.put(key, value);
//        checkList.add(checkMap);
//        return checkList;
//    }
}

