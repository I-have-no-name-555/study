package com.xuan.controller;

import com.xuan.bean.Book;
import com.xuan.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author :Xuan
 * @date :Create in 2021/4/10 17:23
 * @description
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
    private final String SUCCESS = "success";

    @RequestMapping("/handle01")
    public String handle01(@RequestParam(value = "user", required = false, defaultValue = "default") String username,
                           @RequestHeader(value = "User-Agent", required = false) String userAgent,
                           @CookieValue(value = "JSESSIONID",required = false)String jid) {
        System.out.println("username=" + username);
        System.out.println(userAgent);
        System.out.println("JSESSIONID=" + jid);
        return SUCCESS;
    }

    @RequestMapping("/saveBook")
    public String saveBook(Book book){
        System.out.println(book);
        return SUCCESS;
    }

    @RequestMapping("/handle02")
    public String handle02(HttpSession session, HttpServletRequest request){
        request.setAttribute("reqParam","请求域参数");
        session.setAttribute("sessionParam","session参数");
        return SUCCESS;
    }
    @RequestMapping("/exception")
    public String exception(){
        throw new MyException();
    }
}
