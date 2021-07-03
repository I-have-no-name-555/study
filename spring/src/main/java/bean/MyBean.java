package bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author :Xuan
 * @date :Create in 2021/3/9 18:25
 * @description
 */
public class MyBean implements FactoryBean<Course> {

    public Course getObject() throws Exception {
        Course course = new Course();
        course.setName("java");
        return course;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public Class<?> getObjectType() {
        return null;
    }
}
