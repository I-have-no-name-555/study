package com.xuan.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/4/21 10:53
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lock {
    private Integer id;
    private String lockName;
    private List<Key> keys;
}
