package aop;

import org.springframework.stereotype.Component;

/**
 * @author :Xuan
 * @date :Create in 2021/3/11 20:59
 * @description
 */
@Component
public class User {
    public void add(){
        System.out.println("add");
    }
}
