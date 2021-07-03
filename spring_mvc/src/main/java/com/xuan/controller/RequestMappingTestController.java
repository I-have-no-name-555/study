package com.xuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author :Xuan
 * @date :Create in 2021/4/8 16:46
 * @description
 */
@Controller
@RequestMapping("/haha")
public class RequestMappingTestController {

    private final String SUCCESS = "success";

    @RequestMapping("/handle01")
    public String handle01() {
        return SUCCESS;
    }

    //人懒了，不测试了
    @RequestMapping(value = "/handle02", method = RequestMethod.POST,
            params = {"username", "!id", "email=1@qq.com", "password!=123456"},
            headers = {}, consumes = {},//规定请求头中的Content-Type
            produces = {}//在响应中告知浏览器返回的内容类型
    )
    public String handle02() {
        return SUCCESS;
    }

    @RequestMapping("/ant0?")
    public String ant01() {
        return SUCCESS;
    }

    @RequestMapping("/ant*")
    public String ant02() {
        return SUCCESS;
    }

    @RequestMapping("/user/{id}")
    public String pathVariableTest(@PathVariable("id") String id) {
        System.out.println(id);
        return SUCCESS;
    }

}
