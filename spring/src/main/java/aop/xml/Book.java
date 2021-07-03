package aop.xml;

import org.springframework.stereotype.Component;

/**
 * @author :Xuan
 * @date :Create in 2021/3/16 17:06
 * @description
 */
@Component
public class Book {
    public void buy(){
        System.out.println("buy");
    }
}
