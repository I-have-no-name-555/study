package gof.creational;

import java.util.Scanner;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:48
 * @description 工厂方法
 */
public class FactoryMethod {
    public static void main(String[] args) {
        new BJOrderPizza();
    }
}
abstract class NewOrderPizza{
    abstract Pizza orderPizza(String orderType);
    public NewOrderPizza(){
        Pizza pizza;
        System.out.println("披萨种类：");
        String orderType = new Scanner(System.in).nextLine();
        pizza = orderPizza(orderType);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
    }
}
class BJOrderPizza extends NewOrderPizza{
    @Override
    Pizza orderPizza(String orderType) {
        Pizza pizza = null;
        try {
            Class<?> pizzaClass = Class.forName("gof.creational.BJ" + orderType + "Pizza");
            pizza = (Pizza) pizzaClass.newInstance();
        } catch (ClassNotFoundException e) {
            System.out.println("不存在该pizza");
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return pizza;
    }
}
class BJCheesePizza extends Pizza{
    {
        typeName = "北京奶酪披萨";
    }
}