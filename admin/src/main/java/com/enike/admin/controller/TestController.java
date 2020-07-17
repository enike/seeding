package com.enike.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Api(tags = "测试")
public class TestController extends BaseController{

    @PreAuthorize("hasAuthority('admin:user1')")
    @ApiOperation(value = "test")
    @RequestMapping("test")
    public String test(){
        return "ok";
    }
}
