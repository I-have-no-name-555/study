package gof.creational;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:49
 * @description 原型
 */
public class Prototype implements Cloneable{
    /*
    实现Cloneable接口，重写clone方法，这就是原型模式

    原型模式在Spring中的应用：
        bean的创建
     */
    private String name;
    private int age;
    private String color;

    public Prototype() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}