package com.xuan.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author :Xuan
 * @date :Create in 2021/4/10 16:53
 * @description rest风格的学习
 */
@Controller
@RequestMapping("/book")
public class BookController {
    private final String SUCCESS = "success";
    @RequestMapping(value = "/{id}",method = RequestMethod.POST)
    public String saveBook(@PathVariable Integer id){
        System.out.println("存储到" + id + "号图书");
        return SUCCESS;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String removeBook(@PathVariable Integer id){
        System.out.println("删除" + id + "号图书");
        return SUCCESS;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String updateBook(@PathVariable Integer id){
        System.out.println("更新" + id + "号图书");
        return SUCCESS;
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String getBook(@PathVariable Integer id){
        System.out.println("查询到" + id + "号图书");
        return SUCCESS;
    }

}
