package com.shangyong;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hc on 2017/5/12.
 */
public class BaseController {

    //request url参数解析
    public Map<String, Object> convertParseUrl(HttpServletRequest request){
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, String[]> map = request.getParameterMap();
        for(String key : map.keySet()){
            String[] paramValues = request.getParameterValues(key);
            if (paramValues.length == 1) {
                String value = paramValues[0];
                if (value.length() != 0) {
                    param.put(key, value);
                }
            }
        }
        return param;
    }

}
