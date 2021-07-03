package gof.creational;

import java.util.Scanner;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:48
 * @description 简单工厂   又叫 静态工厂
 */
public class  SimpleFactory {
    public Pizza createPizza(String typeName){
        Pizza pizza = null;
        try {
            Class<?> pizzaClass = Class.forName("gof.creational." + typeName + "Pizza");
            pizza = (Pizza) pizzaClass.newInstance();
        } catch (ClassNotFoundException e) {
            System.out.println("不存在该pizza");
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return pizza;
    }
}
abstract class Pizza{
    protected String typeName;
    public void prepare(){
        System.out.println(typeName + " prepare");
    }
    public void bake(){
        System.out.println(typeName + "bake");
    }
    public void cut(){
        System.out.println(typeName + "cut");
    }
    public void box(){
        System.out.println(typeName + "box");
    }
}
class ChinaPizza extends Pizza{
    {
        typeName = "中国披萨";
    }
}
class OrderPizza{

    public static void main(String[] args) {
        OrderPizza orderPizza = new OrderPizza(new SimpleFactory());
        orderPizza.orderPizza();
    }


    private SimpleFactory simpleFactory;
    public OrderPizza(SimpleFactory simpleFactory){
        setSimpleFactory(simpleFactory);
    }
    public void setSimpleFactory(SimpleFactory simpleFactory) {
        this.simpleFactory = simpleFactory;
    }
    public void orderPizza(){
        String orderType = new Scanner(System.in).nextLine();
        Pizza pizza = simpleFactory.createPizza(orderType);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
    }
}
