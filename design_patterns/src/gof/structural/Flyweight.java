package gof.structural;

import java.util.HashMap;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:51
 * @description 享元   又叫 蝇量模式
 */
public class Flyweight {
    /*
        享元模式：用共享技术有效地支持大量细粒度的对象
            常用于系统底层开发，解决系统的性能问题。
            能够解决重复对象的内存浪费问题，当系统中有大量相似对象，需要缓冲池时,可以在缓冲池中取得而不必创建新对象
            享元模式的经典应用场景就是各种池技术，如数据库连接池，String常量池，包装类的缓冲池
            享元模式会提高系统复杂度增强耦合，因此最好在内部状态和外部状态易分离时使用
     */

}
abstract class Website{
    public abstract void use(User user);
}
class ConcreteWebsite extends Website{
    private String type = "";

    public ConcreteWebsite(String type) {
        this.type = type;
    }

    @Override
    public void use(User user) {
        System.out.println("网站发布形式为" + type + "所使用者" + user.getName());
    }
}
class WebsiteFactory{
    private HashMap<String , ConcreteWebsite> pool = new HashMap<>();

    public Website getWebsiteCategory(String type){
        if (pool.containsKey(type))
            return pool.get(type);
        else{
            pool.put(type,new ConcreteWebsite(type));
            return pool.get(type);
        }

    }
    public int getWebsiteCount(){
        return pool.size();
    }
}
class User{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }
}
class WebsiteClient{
    public static void main(String[] args) {
        WebsiteFactory factory = new WebsiteFactory();
        Website website1 = factory.getWebsiteCategory("新闻");
        website1.use(new User("a"));
        System.out.println(factory.getWebsiteCount());
        Website website2 = factory.getWebsiteCategory("博客");
        website2.use(new User("b"));
        System.out.println(factory.getWebsiteCount());
        Website website3 = factory.getWebsiteCategory("博客");
        website3.use(new User("a"));
        System.out.println(factory.getWebsiteCount());
    }
}