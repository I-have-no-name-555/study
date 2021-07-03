package com.xuan.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author :Xuan
 * @date :Create in 2021/4/19 19:00
 * @description 其他部分
 */
@Controller
public class OtherController {
    @RequestMapping("/httpEntityTest")
    public String httpEntityTest(HttpEntity<String> entity) {
        System.out.println(entity);
        return "success";
    }

    @RequestMapping("/responseBodyTest")
    @ResponseBody
    public ResponseEntity<String> responseBodyTest() {
        return new ResponseEntity<String>("success",
                new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request) throws IOException {
        ServletContext context = request.getServletContext();
        String realPath = context.getRealPath("/helloWorld.html");
        FileInputStream is = new FileInputStream(realPath);
        byte[] temp = new byte[is.available()];
        is.read(temp);
        is.close();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "attachment;filename=" + realPath);
        return new ResponseEntity<>(temp, headers, HttpStatus.OK);
    }

    @RequestMapping("/upload")
    public String upload(String username, @RequestParam("headerimg") MultipartFile[] files, Model model) throws IOException {
        for (MultipartFile file : files) {
            if (!file.isEmpty())
                file.transferTo(new File("D:\\" + file.getOriginalFilename()));
        }
        model.addAttribute("msg", "上传成功");
        return "success";
    }
}
