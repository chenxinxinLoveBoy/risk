package com.shangyong.backend.utils;

/**
 * Auther: Corey
 * Date: 2018/4/11 21:11
 */
/**
 * @Description  黑名单车市工具类
 * @Author  Corey
 * @Date  2018/4/11 21:11
 */

public class BlackCityUtils {

    /**
     * 定义黑名单城市
     */
    public static final String[] BLICKCITY_ARR = { "莆田","盐城","大同","红河","兴安","攀枝花","六盘水","营口","毕节","凉山","南宁"};

    /**
     * 校验是否命中黑名单 命中返回true,否则返回false
     * @param area
     * @param array
     * @return
     */
    public static boolean checkBlackCity(String area,String[] array) {

        for(String arr :array){
            if(area.contains(arr)){
                return true;
            }
        }
        return false;
    }
}
