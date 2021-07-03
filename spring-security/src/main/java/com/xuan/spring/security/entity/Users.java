package com.xuan.spring.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author :Xuan
 * @date :Create in 2021/5/5 11:10
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private Integer id;

    private String password;

    private String username;
}
