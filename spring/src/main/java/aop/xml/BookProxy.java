package aop.xml;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author :Xuan
 * @date :Create in 2021/3/16 17:07
 * @description
 */
@Component
public class BookProxy {
    public void before(){
        System.out.println("before");
    }
}
