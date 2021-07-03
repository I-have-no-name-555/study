package gof.behavioral;

import java.util.LinkedList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:56
 * @description 访问者
 */
public class Visitor {
    /*
        访问者模式：封装一些作用于某种数据结构各元素的操作，可以在不改变数据结构的前提下定义作用于这些元素的新操作
            将数据结构和数据操作分离，解决数据结构和操作耦合性的问题
            基本工作原理： 在被访问的类里加一个对外提供接待访问者的接口
            优点:
                可以对功能进行统一，可以做报表、UI、拦截器与过滤器，适用于数据结构相对稳定的系统
            缺点：
                具体元素对访问者公布细节，具体元素变更困难
                访问者依赖了具体而不是抽象
            如果一个系统有较为稳定的数据结构，又经常有变化的功能需求，适合使用访问者模式
     */
}
abstract class Action{
    public abstract void getManResult(Man man);
    public abstract void getWomanResult(Woman woman);
}
class Success extends Action{
    @Override
    public void getManResult(Man man) {
        System.out.println("男人评价成功");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("女人评价成功");
    }
}
class Fail extends Action{
    @Override
    public void getManResult(Man man) {
        System.out.println("男人评价失败");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("女人评价失败");
    }
}
abstract class Person{
    public abstract void accept(Action action);
}
class Man extends Person{
    @Override
    public void accept(Action action) {
        action.getManResult(this);
    }
}
class Woman extends Person{
    @Override
    public void accept(Action action) {
        action.getWomanResult(this);
    }
}
class PersonStructure{
    private List<Person> people = new LinkedList<>();
    public void attach(Person person){
        people.add(person);
    }
    public void detach(Person person){
        people.remove(person);
    }
    public void display(Action action){
        for(Person p : people)
            p.accept(action);
    }
}
class VisitorClient{
    public static void main(String[] args) {
        PersonStructure personStructure = new PersonStructure();
        personStructure.attach(new Man());
        personStructure.attach(new Woman());
        Success success = new Success();
        personStructure.display(success);
        Fail fail = new Fail();
        personStructure.display(fail);
    }
}