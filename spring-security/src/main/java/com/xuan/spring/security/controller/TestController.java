package com.xuan.spring.security.controller;

import com.xuan.spring.security.entity.Users;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/5/4 17:11
 * @description
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello(){
        return "hello security";
    }

    @GetMapping("/index")
    public String index(){
        return "hello index";
    }

    @GetMapping("/update")
//    @Secured({"ROLE_sale","ROLE_manager"})
//    @PreAuthorize("hasAnyAuthority('admins')")
    @PostAuthorize("hasAnyAuthority('admin')")
    public String update(){
        System.out.println("update...");
        return "hello update";
    }

    @GetMapping("/getAll")
    @PostAuthorize("hasAnyAuthority('admins')")
    @PostFilter("filterObject.username == 'admin1'")
    public List<Users> getAllUser(){
        List<Users> list = new ArrayList<>();
        list.add(new Users(1,"6666","admin1"));
        list.add(new Users(2,"888","admin2"));
        return list;
    }
}
