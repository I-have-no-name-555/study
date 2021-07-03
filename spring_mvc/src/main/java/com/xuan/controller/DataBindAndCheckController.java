package com.xuan.controller;

import com.xuan.bean.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author :Xuan
 * @date :Create in 2021/4/15 17:38
 * @description 数据绑定和校验的测试
 */
@Controller
@RequestMapping("/data")
public class DataBindAndCheckController {
    @RequestMapping("/quickSave")
    public String quickSave(@RequestParam("bookInfo") Book book){
        System.out.println(book);
        return "success";

    }
}
