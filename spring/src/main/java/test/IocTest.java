package test;

import bean.Orders;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author :Xuan
 * @date :Create in 2021/3/9 18:51
 * @description
 */
public class IocTest {
    @Test
    public void test1(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean8.xml");
        Orders orders = applicationContext.getBean("orders", Orders.class);
        applicationContext.close();

    }

}
