package com.xuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author :Xuan
 * @date :Create in 2021/4/7 17:36
 * @description
 */
@Controller
public class MyFirstController {
    @RequestMapping("hello")
    public String myFirstRequest(){
        return "success";
    }
}
