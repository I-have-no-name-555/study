package gof.structural;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:50
 * @description 桥接
 */
public class Bridge {
    /*
        jdbc中的桥接模式：
            Driver

        桥接模式的注意事项与细节：
            将抽象与实现分离，极大提升了系统灵活性，利于系统分层设计，产生更好的结构化系统
            替代多层继承方案，减少了子类的个数
            应用场景：
                不希望使用继承或因为多层次继承导致类的个数急剧增加的系统
                如：
                    jdbc驱动系统
                    银行转账系统
                        转账分类：网上、柜台、ATM等
                        转账用户：普通、银卡、金卡等
                    消息管理：
                        消息类型：延时、即时
                        消息分类：qq、微信、短信、邮件等

     */
}
//功能接口
interface Brand{
    void open();
    void close();
    void call();
}
//功能实现类
class XiaoMi implements Brand{
    @Override
    public void open() {
        System.out.println("小米开机");
    }

    @Override
    public void close() {
        System.out.println("小米关机");
    }

    @Override
    public void call() {
        System.out.println("小米打电话");
    }
}
//抽象类  桥
abstract class AbstractPhone{
    private Brand brand;

    public AbstractPhone(Brand brand) {
        this.brand = brand;
    }
    protected void open(){
        brand.open();
    }
    protected void close(){
        brand.close();
    }
    protected void call(){
        brand.call();
    }
}
//具体子类
class FoldedPhone extends AbstractPhone{

    public FoldedPhone(Brand brand) {
        super(brand);
    }
    public void open(){
        super.open();
        System.out.println("折叠式手机");
    }
    public void close(){
        super.close();
        System.out.println("折叠式手机");
    }
    public void call(){
        super.call();
        System.out.println("折叠式手机");
    }
}