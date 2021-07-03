package aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author :Xuan
 * @date :Create in 2021/3/16 17:01
 * @description
 */
@Component
@Aspect
@Order(1)
public class PersonProxy {
    @Before("execution(* aop.User.add(..))")
    public void before(){
        System.out.println("personProxy before");
    }
}
