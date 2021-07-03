package bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author :Xuan
 * @date :Create in 2021/4/6 11:05
 * @description
 */
@Component("man")
public class ManFactory implements FactoryBean<Man> {
    @Override
    public Man getObject() throws Exception {
        return new Man();
    }

    @Override
    public Class<?> getObjectType() {
        return Man.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
