package gof.behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:54
 * @description 备忘录
 */
public class Memento {
    /*
        备忘录模式：在不破坏封装性的前提下捕获一个对象内部的状态并在对象外部保存这个状态
            提供了一种恢复状态的机制
            实现了信息的封装
            如果类的成员变量过多，会占用较多资源
            适用场景：
                版本管理
                存档
                win中的ctrl + z
                数据库事务管理
            可以和原型模式配合使用节约内存
     */
}
class GameRole{
    private int vit;
    private int def;
    public GameRoleMemento createMemento(){
        return new GameRoleMemento(vit,def);
    }

    public void loadMemento(GameRoleMemento memento){
        vit = memento.getVit();
        def = memento.getDef();
    }
    @Override
    public String toString() {
        return "GameRole{" +
                "vit=" + vit +
                ", def=" + def +
                '}';
    }

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
}
class GameRoleMemento{
    private int vit;
    private int def;

    public GameRoleMemento(int vit, int def) {
        this.vit = vit;
        this.def = def;
    }

    public int getVit() {
        return vit;
    }

    public int getDef() {
        return def;
    }
}
class GameRoleCaretaker{
    private List<GameRoleMemento> mementos = new ArrayList<>();
    public void add(GameRoleMemento memento){
        mementos.add(memento);
    }
    public GameRoleMemento get(int i){
        return mementos.get(i);
    }
}
class GameRoleClient{
    public static void main(String[] args) {
        GameRole gameRole = new GameRole();
        gameRole.setDef(2);
        gameRole.setVit(2);
        System.out.println(gameRole);
        GameRoleCaretaker caretaker = new GameRoleCaretaker();
        caretaker.add(gameRole.createMemento());
        gameRole.setVit(1);
        gameRole.setDef(1);
        System.out.println(gameRole);
        gameRole.loadMemento(caretaker.get(0));
        System.out.println(gameRole);
    }
}