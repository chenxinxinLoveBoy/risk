package com.shangyong.backend.controller;
/*package com.honglu.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.honglu.backend.service.RedisService;
import com.honglu.entity.webBaseResult;
import com.honglu.utils.webResultUtils;

*//**还原
 * Redis操作controller
 * @author zhangze
 *
 *//*
@RestController
public class RedisRestController {	

    @Autowired
    private RedisService redisService;
    

    *//**
     * 设置redis
     * @param username
     * @return
     *//*
    @RequestMapping(value = "/api/redis/set", method = RequestMethod.GET)
    public webBaseResult<String> setRedis(@RequestParam(value = "username", required = true) String username) {
    	redisService.setRedis(username);
        return webResultUtils.resultSuccess();
    }

    *//**
     * 查询redis
     * @return
     *//*
    @RequestMapping(value = "/api/redis/get", method = RequestMethod.GET)
    public webBaseResult<String> getRedis(@RequestParam(value = "username", required = true) String username) {
    	String result = redisService.getRedis(username);
        return webResultUtils.resultSuccess(result);
    }

}*/