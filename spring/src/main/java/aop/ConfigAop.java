package aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author :Xuan
 * @date :Create in 2021/3/16 17:14
 * @description
 */
@Configuration
@ComponentScan(basePackages = {"aop.xml"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ConfigAop {
}
