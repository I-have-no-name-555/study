package com.xuan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author :Xuan
 * @date :Create in 2021/4/10 18:24
 * @description 学习数据输出
 */
@Controller
@SessionAttributes(value = {"msg"},types = {String.class})//不推荐使用，应使用原生api
@RequestMapping("/data")
public class DataOutputController {
    private final String SUCCESS = "success";

    @RequestMapping("/handle01")
    public String handle01(Map<String,Object> map, Model model, ModelMap modelMap){
        //数据都在请求域中，会覆盖
        //因为三者其实都是BindingAwareModelMap,且是同一个对象，即map == model == modelMap
        map.put("msg","map");
        model.addAttribute("msg","model");
        modelMap.addAttribute("msg","modelMap");
        return SUCCESS;
    }

    @RequestMapping("/handle02")
    public ModelAndView handle02(){
        //数据还是在请求域中
        ModelAndView mv = new ModelAndView(SUCCESS);
        mv.addObject("msg","hello!");
        return mv;
    }



}
