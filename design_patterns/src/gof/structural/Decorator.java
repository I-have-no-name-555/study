package gof.structural;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:51
 * @description 装饰者
 */
public class Decorator {
    /*
        jdk中的装饰者模式：IO
        装饰者模式：
            将新功能附加到对象上
     */
}
//被装饰者 抽象
abstract class Drink{
    private String description;
    private float price;

    public abstract float cost();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
//缓冲层
abstract class Coffee extends Drink{
    @Override
    public float cost() {
        return super.getPrice();
    }
}
//被装饰者 具体
class Espresso extends Coffee{
    public Espresso() {
        setPrice(6.0f);
        setDescription("意式咖啡");
    }
}
class LongBlack extends Coffee{
    public LongBlack() {
        setPrice(5.0f);
        setDescription("美式咖啡");
    }
}
//装饰者 抽象
abstract class Seasoning extends Drink {
    private Drink drink;
    public Seasoning(Drink drink) {
        this.drink = drink;
    }
    @Override
    public float cost() {
        return super.getPrice() + drink.cost();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " " + super.getPrice() + "&&" + drink.getDescription();
    }
}
//装饰者 具体
class Chocolate extends Seasoning{
    public Chocolate(Drink drink) {
        super(drink);
        setDescription("巧克力");
        setPrice(3.0f);
    }

}
class Milk extends Seasoning{
    public Milk(Drink drink) {
        super(drink);
        setPrice(2.0f);
        setDescription("牛奶");
    }
}
//使用
class Cafe{
    public static void main(String[] args) {
        //点一个2份巧克力和1份牛奶的美式咖啡
        Drink drink = new Chocolate(new Chocolate(new Milk(new LongBlack())));
        System.out.println(drink.getDescription());
        System.out.println(drink.cost());
    }
}
