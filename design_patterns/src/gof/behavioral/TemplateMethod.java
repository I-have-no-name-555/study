package gof.behavioral;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:56
 * @description 模板方法
 */
public class TemplateMethod {
    /*
        Spring中的模板方法模式： ConfigurableApplicationContext
        模板方法模式：
            算法只存在于父类中，容易修改
            实现了最大化的代码复用
            既统一了算法，又提供了很大的灵活性
            模板方法一般以final修饰防止子类重写
            不足：  每一个不同的实现都需要一个子类实现，导致类的个数增加
            使用场景 ： 当要完成某个过程，需要执行一系列步骤，这些步骤基本相同时使用

     */
}
abstract class SoyMilk{
    public final void make(){
        select();
        if (condimentsWanted())
            addCondiments();
        soak();
        beat();
    }
    public void select(){
        System.out.println("选择新鲜的黄豆");
    }
    public abstract void addCondiments();
    public void soak(){
        System.out.println("将黄豆和配料浸泡好");
    }
    public void beat(){
        System.out.println("打豆浆");
    }
    public boolean condimentsWanted(){
        return true;
    }
}
class RedBeanSoyMilk extends SoyMilk{
    @Override
    public void addCondiments() {
        System.out.println("加入上好的红豆");
    }
}
class PeanutSoyMilk extends SoyMilk{
    @Override
    public void addCondiments() {
        System.out.println("加入上好的花生");
    }
}
class CommonSoyMilk extends SoyMilk{
    @Override
    public void addCondiments() {

    }

    @Override
    public boolean condimentsWanted() {
        return false;
    }
}
class SoyMilkClient{
    public static void main(String[] args) {
        SoyMilk redBeanSoyMilk = new RedBeanSoyMilk();
        SoyMilk peanutSoyMilk = new PeanutSoyMilk();
        SoyMilk soyMilk = new CommonSoyMilk();
        redBeanSoyMilk.make();
        peanutSoyMilk.make();
        soyMilk.make();
    }
}
