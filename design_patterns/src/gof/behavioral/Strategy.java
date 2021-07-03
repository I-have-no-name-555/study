package gof.behavioral;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:56
 * @description 策略
 */
public class Strategy {

    /*
        jdk中的策略模式：如Comparator就是一个策略接口
        策略模式：
            策略过多会导致类数目庞大
     */
}
interface FlyBehavior{
    void fly();
}
 class NoFlyBehavior implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("不会飞");
    }
}
 class BadFlyBehavior implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("飞不好");
    }
}
 class GoodFlyBehavior implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("很会飞");
    }
}
interface QuackBehavior{
    void quack();
}
 class GeGeQuackBehavior implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("咯咯叫");
    }
}
 class GaGaQuackBehavior implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("嘎嘎叫");
    }
}
 class NoQuackBehavior implements QuackBehavior{
    @Override
    public void quack() {
        System.out.println("不会叫");
    }
}
abstract class Duck{
    protected FlyBehavior flyBehavior;
    protected QuackBehavior quackBehavior;

    public Duck() {
    }
    public abstract void display();
    public void quack(){
        if (quackBehavior != null)
            quackBehavior.quack();
    }
    public void swim(){
        System.out.println("鸭子会游泳");
    }
    public void fly(){
        if (flyBehavior != null)
            flyBehavior.fly();
    }

}
class PekingDuck extends Duck{
    public PekingDuck() {
        this.flyBehavior = new NoFlyBehavior();
        this.quackBehavior = new GaGaQuackBehavior();
    }

    @Override
    public void display() {

    }
}
class WildDuck extends Duck{
    public WildDuck() {
        flyBehavior = new GoodFlyBehavior();
        quackBehavior = new GeGeQuackBehavior();
    }

    @Override
    public void display() {

    }
}
class ToyDuck extends Duck{
    @Override
    public void display() {

    }

    public ToyDuck() {
        flyBehavior = new NoFlyBehavior();
        quackBehavior = new GaGaQuackBehavior();
    }
}
class DuckClient{

}
