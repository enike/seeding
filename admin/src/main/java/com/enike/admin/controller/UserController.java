package com.enike.admin.controller;


import com.enike.admin.AO.RegisterAO;
import com.enike.admin.config.security.JwtAuthenticatioToken;
import com.enike.admin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author enike
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户模块")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    @ApiOperation(value = "登陆")
    @CrossOrigin
    public JwtAuthenticatioToken login(String username, String password, HttpServletRequest request){

        String msg =  userService.login(username,password);
        if(msg.equals("SUCCESS")){
            return userService.authToken(request, username,password,authenticationManager);
        }
        return null;
    }

    @PostMapping("/register")
    @ApiOperation(value = "注册")
    @CrossOrigin
    public boolean register(@RequestBody RegisterAO user){
        return userService.register(user);
    }
}

