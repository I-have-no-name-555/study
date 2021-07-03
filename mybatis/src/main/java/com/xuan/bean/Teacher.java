package com.xuan.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author :Xuan
 * @date :Create in 2021/4/21 17:08
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private Integer id;
    private String teacherName;
    private String className;
    private String address;
    private Date birthDate;
}
