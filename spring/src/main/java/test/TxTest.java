package test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

/**
 * @author :Xuan
 * @date :Create in 2021/3/22 18:52
 * @description
 */
public class TxTest {
    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean15.xml");
        UserService userService = context.getBean("userService",UserService.class);
        userService.accountMoney();
    }


}
