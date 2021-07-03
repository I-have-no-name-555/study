package com.xuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author :Xuan
 * @date :Create in 2021/4/19 20:00
 * @description 拦截器测试
 */
@Controller
@RequestMapping("/interceptor")
public class InterceptorController {
    private static final String SUCCESS = "success";
    @RequestMapping("/test01")
    public String test01(){
        System.out.println("test01");
        return SUCCESS;
    }
}
