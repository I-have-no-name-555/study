package gof.creational;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 16:56
 * @description 单例模式
 */
//推荐使用
//7.枚举
public enum  Singleton {
    INSTANCE;
}
// 5.懒汉式——双重检查（推荐使用
class Singleton5{
    private Singleton5(){}
    private static volatile Singleton5 instance;
    public static Singleton5 getInstance(){
        if (instance == null){
            synchronized (Singleton5.class){
                if (instance == null)
                    instance = new Singleton5();
            }
        }
        return instance;
    }
    //线程安全，延迟加载，效率较高
}
//6.懒汉式——静态内部类
class Singleton6{
    private Singleton6(){}
    private static class SingletonInstance{
        private static final Singleton6 instance = new Singleton6();
    }
    public static synchronized Singleton6 getInstance(){
        return SingletonInstance.instance;
    }
    /*
    优点：同5 ， 推荐使用
     */
}

//可用
//1.饿汉式——静态变量
class Singleton1 {
    private final static Singleton1 singleton = new Singleton1();

    private Singleton1() {
    }

    private static Singleton1 getInstance() {
        return singleton;
    }

    /*
    优点：没有多线程问题
    缺点：没有LazyLoading，可能造成内存浪费
     */
}
//2.饿汉式——静态代码块

//不推荐使用
class Singleton2 {
    private static Singleton2 instance;

    private Singleton2() {

    }

    static {
        instance = new Singleton2();
    }

    private static Singleton2 getInstance() {
        return instance;
    }

    /*
    优缺点：同1
     */
}
//3.懒汉式——线程不安全
class Singleton3{
    private Singleton3(){}
    private static Singleton3 instance;
    public static Singleton3 getInstance(){
        return instance == null ? (instance = new Singleton3()):instance;
    }
    /*
    优点：起到了懒加载的效果
    缺点：线程不安全
     */
}
//4.懒汉式——线程安全
class Singleton4{
    private Singleton4(){}
    private static Singleton4 instance;
    public static synchronized Singleton4 getInstance(){
        return instance == null ? (instance = new Singleton4()):instance;
    }
    /*
    优点：起到了懒加载的效果，解决了线程安全的问题
    缺点：同步方法效率低
     */
}

/*
jdk中的单例模式：
java.lang.Runtime 饿汉式——静态变量

对于需要频繁创建销毁的对象，使用单例模式提高性能

单例使用的场景：
    需要频繁创建销毁的对象
    创建对象耗时或耗资源过多又常用的对象：如工具类对象、数据源对象

 */