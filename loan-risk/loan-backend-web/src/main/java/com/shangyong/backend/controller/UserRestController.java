package com.shangyong.backend.controller;
/*package com.honglu.backend.controller;
还原
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.honglu.backend.common.enums.CalfResultEnum;
import com.honglu.backend.entity.User;
import com.honglu.backend.service.UserService;
import com.honglu.entity.webBaseResult;
import com.honglu.exception.CalfException;
import com.honglu.utils.webResultUtils;

*//**
 * 用户controller
 * @author zhangze
 *
 *//*
@RestController
public class UserRestController {	

    @Autowired
    private UserService userService;
    

    *//**
     * 根据用户名，查询用户信息
     * @param username
     * @return
     *//*
    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public webBaseResult<User> getUserByUsername(@RequestParam(value = "username", required = true) String username) {
    	User user = userService.findByUserame(username);
        return webResultUtils.resultSuccess(user);
    }
    
    *//**
     * 查询所有用户信息
     * @return
     *//*
    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public webBaseResult<List<User>> getAllUser() {
    	List<User> users = userService.getAllUser();
        return webResultUtils.resultSuccess(users);
    }
    
    *//**
     * 新增用户信息
     * @return
     *//*
    @RequestMapping(value = "/api/user", method = RequestMethod.POST)
    public webBaseResult<User> saveUser(@RequestParam(value = "username", required = true) String username, @RequestParam(value = "pwd", required = true) String pwd, 
    		@RequestParam(value = "email", required = true) String email) throws Exception {
    	if ("123456".equals(pwd)) {
    		throw new CalfException(CalfResultEnum.FAIL_PWD_EASY.getCode(), CalfResultEnum.FAIL_PWD_EASY.getMessage());
    	}
    	User user = userService.saveUser(username, pwd, email);
        return webResultUtils.resultSuccess(user);
    }

}*/