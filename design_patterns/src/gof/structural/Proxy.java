package gof.structural;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:52
 * @description 代理
 */
public class Proxy {
    /*
        代理模式：为一个对象提供一个替身，以控制对这个对象的访问。即通过代理对象访问目标对象。
            可以在目标对象实现的基础上增强额外的功能操作，扩展目标对象的功能
            被代理的通常是远程对象、创建开销大的对象或需要安全控制的对象


     */
}
//静态代理
interface ITeacherDao{
    void teach();
}
class TeacherDao implements ITeacherDao{
    @Override
    public void teach() {
        System.out.println("teacher teach");
    }
}
class TeacherDaoProxy implements ITeacherDao{
    private ITeacherDao teacherDao;

    public TeacherDaoProxy(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public void teach() {
        System.out.print("代理");
        teacherDao.teach();
        System.out.println("代理结束");
    }
}
class TeacherClient{
    public static void main(String[] args) {
        TeacherDaoProxy proxy = new TeacherDaoProxy(new TeacherDao());
        proxy.teach();
    }
}
//动态代理   又叫   jdk代理   接口代理
class ProxyFactory{
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance(){
        return java.lang.reflect.Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("jdk代理开始");
                        return method.invoke(target, args);
                    }
                });
    }
}
class TeacherClient2{
    public static void main(String[] args) {
        ITeacherDao target = new TeacherDao();
        ITeacherDao proxyInstance = (ITeacherDao)new ProxyFactory(target).getProxyInstance();
        proxyInstance.teach();
        System.out.println(proxyInstance.getClass());
    }
}
//Cglib代理  动态代理的一种   又叫子类代理   不需要被代理类实现接口 不支持final类、final方法和static方法
//由于需要引入Maven依赖，降低项目运行速度，省略该部分

/*
    几种变体：
        防火墙代理：内网通过代理穿透防火墙，实现对公网的访问
        缓存代理：请求文件资源时，先从缓存代理中取，取不到再到公网或数据库取并加入缓存
        远程代理：把远程对象当本地对象使用
        同步代理：在多线程编程中，完成多线程同步工作
 */