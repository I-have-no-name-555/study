package com.xuan.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author :Xuan
 * @date :Create in 2021/4/20 9:52
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
    private Integer id;
    private String empName;
    private String email;
    private Integer gender;
}
//@Alias("Employee") 不推荐取别名