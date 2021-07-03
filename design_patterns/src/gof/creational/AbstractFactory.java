package gof.creational;

import java.util.Scanner;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:49
 * @description 抽象工厂
 */
//抽象工厂是简单工厂模式的进一步抽象
public interface AbstractFactory {
    Pizza createPizza(String orderType);
}
class BJFactory implements AbstractFactory{
    @Override
    public Pizza createPizza(String orderType) {
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
class NewNewOrderPizza{
    private AbstractFactory abstractFactory;

    public void orderPizza(){
        System.out.println("披萨种类");
        String orderType = new Scanner(System.in).nextLine();
        Pizza pizza = abstractFactory.createPizza(orderType);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
    }
    public NewNewOrderPizza(AbstractFactory abstractFactory) {
        setAbstractFactory(abstractFactory);
    }
    public void setAbstractFactory(AbstractFactory abstractFactory) {
        this.abstractFactory = abstractFactory;
    }

    public static void main(String[] args) {
        new NewNewOrderPizza(new BJFactory()).orderPizza();
    }
}

/*
jdk中的工厂模式:
    java.util.Calender 简单工厂模式
 */