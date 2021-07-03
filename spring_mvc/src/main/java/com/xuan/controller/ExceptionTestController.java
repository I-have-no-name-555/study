package com.xuan.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author :Xuan
 * @date :Create in 2021/4/19 20:36
 * @description 异常处理测试
 */
@ControllerAdvice
public class ExceptionTestController {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException01(Exception e){
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("ex",e);
        return modelAndView;
    }
}
