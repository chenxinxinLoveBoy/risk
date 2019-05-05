package com.shangyong.backend.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.record.formula.functions.T;

import com.google.gson.Gson;
import com.shangyong.utils.StringUtil;

/**
 *  * User: kenzhao
 *  * Date: 2017/8/3
 *  * Time: 16:41
 *  * PROJECT_NAME: risk-parent_2.1
 *  * PACKAGE_NAME: com.honglu.backend.utils
 *  * DESC: Json 数据无Model获取key值
 *  * Version: v1.0.0
 *  
 */
public class ReadJsonUtils {

    /**
     * 传入关键字获取指定JSON数据中的值
     * 目前支持三种结果 集合的返回个数/数值/字符串
     * @param json JSON字符串
     * @param keyCode 关键字，如A.B.C[].d
     * @return Object 解析的值 -99999视为异常，未解析到匹配值
     */
    public static Object getValueByJsonKey(String json, String keyCode) {
//        keyCode = "report_data.main_service[].service_details[]";
        Map map = fromJson(json,Map.class);
        if(!StringUtil.checkNotNull(keyCode)){
            return -99999;
        }
        try{

            Object obj = getObject(map,keyCode);
            if(obj == null){
                return -99999;
            }
            if(obj instanceof List){
                return obj;
            }else if (obj instanceof Integer){
                return new Integer(obj.toString());
            }else if (obj instanceof String){ 
            	if(obj.equals("null")){
                    return -99999;
            	}
                return obj;
            }
            return obj;
        }catch (Exception ex){
            return -99999;
        }
    }


    /**
     * 对象转JSON字符串
     * @param obj
     * @return String
     */
    public static String toJson(Object obj){
        if(obj == null){
            return null;
        }
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    /**
     * Json字符串转对象
     * @param json Json字符串
     * @param classOfT 对象类型
     * @param <T> 泛型，可自定义
     * @return 传入的对象类型
     */
    public static <T> T fromJson(String json, Class<T> classOfT){
        if(!StringUtil.checkNotNull(json)){
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(json,classOfT);
    }

    /**
     * 从Map中获取指定key的集合
     * @param obj Map对象
     * @param keyCode 需要解析的keyCode(有[]标记，集合; 如B[].C.name)
     * @param resultList 需要追加到的集合对象
     * @param key 本次解析的key （如 B）
     * @return
     */
    private static Object getInnerList(Map obj, String keyCode, List resultList, String key) {
        keyCode = keyCode.replace(key+".","");
        key = key.replace("[]","");
        List<T> list = (List)obj.get(key);
        resultList.addAll(list);
        resultList = getList(resultList,keyCode);
        return  resultList;
    }

    /**
     * 非集合递归，获取Map对象的key值
     * @param obj Map对象
     * @param keyCode Map的key值集(如 A.B.C.name)
     * @return 最后一级key的value
     */
    private static Object getObject(Map obj, String keyCode) {
        if(obj != null && obj.size() > 0){

            //最后得到的集合
            List resultList = new ArrayList();
            Map map = null;
            Object resultObj = null;
            //判断是否为最后一级
            int keyLength = keyCode.indexOf(".");

            //非最后一级则继续递归处理
            if(keyLength > -1) {
                String key = keyCode.substring(0,keyLength);
                if(key.indexOf("[]") > -1){
                    return getInnerList(obj, keyCode, resultList, key);
                }else{
                    keyCode = keyCode.replace(key+".","");
                    map = (Map)obj.get(key);
                    resultObj = getObject(map,keyCode);
                    return  resultObj;
                }
            }else{
                //最后一级，返回结果
                resultObj = obj.get(keyCode);
                return resultObj;
            }
        }
        return -99999;
    }

    /**
     * JSON集合递归，获取Map对象的key值
     * @param objs Map对象集合
     * @param keyCode Map的key值集(如 A.B.C[].name)
     * @return 最后一级key的value
     */
    private static List<T> getList(List<Map> objs, String keyCode) {


        if(objs != null && objs.size() > 0){
            //最后得到的集合
            List resultList = new ArrayList();

            //判断是否为最后一级
            int keyLength = keyCode.indexOf(".");

            //非最后一级则继续递归处理
            if(keyLength > -1){
                String key = keyCode.substring(0,keyLength);
                for (Map obj:objs
                        ) {
                    if(key.indexOf("[]") > -1){
                        return (List)getInnerList(obj, keyCode, resultList, key);
                    }else{
                        Map map = (Map)obj.get(key);
                        resultList.add(map);
                        keyCode = keyCode.replace(key+".","");
                        resultList = getList(resultList, keyCode);
                        return resultList;
                    }
                }

            }else{
                //最后一级则返回值
                for (Map obj:objs
                        ) {
                    if(keyCode.indexOf("[]") > -1){
                        keyCode = keyCode.replace("[]","");
                        List<T> list = (List)obj.get(keyCode);
                        resultList.addAll(list);
                    }else{
                        if(obj != null){
                            Object object = obj.get(keyCode);
                            resultList.add(object);
                        }
                    }
                }
                return resultList;

            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {

//        File file = new File("C:\\db\\test.json");
    	
		
String ss="{\"note\":\"\",\"report_data\":{\"user_info_check\":{\"check_black_info\":{\"contacts_class1_blacklist_cnt\":0,\"contacts_router_ratio\":0.48165137,\"contacts_class2_blacklist_cnt\":427,\"contacts_router_cnt\":105,\"contacts_class1_cnt\":218,\"phone_gray_score\":10}}}}";
			Object fraudNum = ReadJsonUtils.getValueByJsonKey(ss, "report_data.user_info_check.check_black_info");// 疑似欺诈人数
			//Object twoFraudNum = ReadJsonUtils.getValueByJsonKey(ss, "report_data.user_info_check.check_black_info.contacts_class2_blacklist_cnt");// 二级疑似欺诈人数


			System.out.println(fraudNum);
			//System.out.println(twoFraudNum);
    	
//        File file = new File("C:\\db\\xiang_05301700.json");
//        BufferedReader reader = null;
//        StringBuffer jsonStr = new StringBuffer();
//        try {
//            reader = new BufferedReader(new FileReader(file));
//            String tempString = null;
//            // 一次读入一行，直到读入null为文件结束
//            while ((tempString = reader.readLine()) != null) {
//                jsonStr.append(tempString);
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e1) {
//                }
//            }
//        }
//
////		System.out.println(content);
//        String testStr = "report_data.report.rpt_id";
////        String testStr = "report_data.main_service[].company_name";
////        String testStr = "report_data.main_service[].service_details[].interact_mth";
////        String testStr = "report_data.main_service[].service_details[]";
////        Map map = fromJson(jsonStr.toString(),Map.class);
//        Object obj = getValueByJsonKey(jsonStr.toString(),testStr);
//        System.out.println(toJson(obj));

//        //判断是否为最后一级
//        int keyLength = testStr.indexOf(".");
//
//        //非最后一级则继续递归处理
//        if(keyLength > -1) {
//            String key = testStr.substring(0,keyLength);
//            testStr = testStr.replaceFirst(key+".","");
//        }
//        System.out.println(testStr);

//        File file = new File("C:\\db\\test.json");
//        BufferedReader reader = null;
//        StringBuffer jsonStr = new StringBuffer();
//        try {
//            reader = new BufferedReader(new FileReader(file));
//            String tempString = null;
//            // 一次读入一行，直到读入null为文件结束
//            while ((tempString = reader.readLine()) != null) {
//                jsonStr.append(tempString);
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e1) {
//                }
//            }
//        }

        //关闭此文件输入流并释放与此流有关的所有系统资源。
//		fileInputStream.close();
//		System.out.println(content);
//		AliyunFileUploadUtilsTest aliyunFileUploadUtilsTest = new AliyunFileUploadUtilsTest();
//		String fileUrl = aliyunFileUploadUtilsTest.uploadFile2OSS(fileInputStream,"test.json");
//		System.out.println("test URL：" + fileUrl);

//		Gson gson = new Gson();
    }
}
