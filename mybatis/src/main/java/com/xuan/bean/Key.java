package com.xuan.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Xuan
 * @date :Create in 2021/4/21 10:52
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Key {
    private Integer id;
    private String keyName;
    private Lock lock;
}
