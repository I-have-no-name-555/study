package com.xuan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author :Xuan
 * @date :Create in 2021/4/19 20:49
 * @description
 */
@ResponseStatus(reason = "反正错了",value = HttpStatus.NOT_EXTENDED)
public class MyException extends RuntimeException{

}
