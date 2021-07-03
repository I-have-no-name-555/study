package com.xuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author :Xuan
 * @date :Create in 2021/4/14 16:55
 * @description 视图解析测试
 */
@Controller
@RequestMapping("/view")
public class ViewResolverController {
    @RequestMapping("/hello")
    public String hello() {
        return "../../hello";
    }

    @RequestMapping("/handle01")
    public String handle01() {
        return "forward:/hello.jsp";
    }

    @RequestMapping("/handle02")
    public String handle02() {
        return "forward:/view/handle01";
    }

    @RequestMapping("/handle03")
    public String handle03() {
        return "redirect:/view/handle01";
    }


}
