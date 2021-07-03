package aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author :Xuan
 * @date :Create in 2021/3/11 20:59
 * @description
 */
@Component
@Aspect
@Order(2)
public class UserProxy {
    @Pointcut("execution(* aop.User.add(..))")
    private void pointCut(){}

    @Before("pointCut()")
    public void before(){
        System.out.println("before");
    }
    @After("pointCut()")
    public void after(){
        System.out.println("after");
    }
    @AfterReturning("pointCut()")
    public void afterReturning(){
        System.out.println("afterReturning");
    }
    @AfterThrowing("pointCut()")
    public void afterThrowing(){
        System.out.println("afterThrowing");
    }
    @Around("pointCut()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around before");
        pjp.proceed();
        System.out.println("around after");
    }
}
