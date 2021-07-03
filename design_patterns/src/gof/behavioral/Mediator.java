package gof.behavioral;

import java.util.HashMap;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:54
 * @description 中介者
 */
public abstract class Mediator {
    /*
        中介者模式：
            多个类相互耦合会形成一个网，中介者模式将其改为星型结构进行解耦
            中介者承担了较多责任，中介者出现问题会影响整个系统
            设计不当会导致中介者过于复杂
     */
    public abstract void register(String furnitureName,Furniture furniture);
    public abstract void getMessage(int stateChange , String furnitureName);
    public abstract void sendMessage();
}
abstract class Furniture{
    private Mediator mediator;
    private String name;

    public Furniture(Mediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public abstract void sendMessage(int stateChange);
}
class Alarm extends Furniture{
    public Alarm(Mediator mediator, String name) {
        super(mediator, name);
        mediator.register(name,this);
    }
    public void sendAlarm(int stateChange){
        sendMessage(stateChange);
    }

    @Override
    public void sendMessage(int stateChange) {
        this.getMediator().getMessage(stateChange,this.getName());
    }
}
class TV extends Furniture{
    public TV(Mediator mediator, String name) {
        super(mediator, name);
        mediator.register(name,this);
    }

    @Override
    public void sendMessage(int stateChange) {
        this.getMediator().getMessage(stateChange,this.getName());
    }
    public void startTV(){
        System.out.println("TV start");
    }
    public void stopTV(){
        System.out.println("TV stop");
    }
}
class ConcreteMediator extends Mediator{
    private HashMap<String , Furniture> furnitureMap = new HashMap<>();

    public ConcreteMediator() {
    }

    @Override
    public void register(String furnitureName, Furniture furniture) {
        furnitureMap.put(furnitureName,furniture);
    }

    @Override
    public void getMessage(int stateChange, String furnitureName) {

    }

    @Override
    public void sendMessage() {

    }
}
//代码太多懒得写了