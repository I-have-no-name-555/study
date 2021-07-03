package test;

import aop.ConfigAop;
import aop.User;
import aop.xml.Book;
import com.google.gson.Gson;
import dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Proxy;
import java.util.ArrayList;

/**
 * @author :Xuan
 * @date :Create in 2021/3/11 17:15
 * @description
 */
public class AopTest {
    @Test
    public void test1(){
        UserDao userDao = (UserDao)Proxy.newProxyInstance(this.getClass().getClassLoader()
                , new Class[]{UserDao.class}, (proxy,method,args) -> method.invoke(proxy,args));
    }
    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean12.xml");
        User user = context.getBean("user", User.class);
        user.add();
    }
    @Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean13.xml");
        Book book = context.getBean("book",Book.class);
        book.buy();
    }
    @Test
    public void test4(){
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigAop.class);
        Book book = context.getBean("book",Book.class);
        book.buy();
    }
}
