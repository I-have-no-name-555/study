package gof.structural;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:50
 * @description 适配器
 */
public class Adapter {
    /*

     */
}
//类适配器
/*
    Adapter继承source类实现dst接口，完成src到dst的适配
    注意事项：
        类适配器使用了继承，由于java的单继承机制，这实际上是一种缺点
        src类中的方法在Adapter中全部暴露，增加了使用成本
        由于继承了src类，可以根据需求重写其方法，使得Adapter更加灵活
 */
//被适配类
class Voltage220V{
    public int output220V(){
        System.out.println("电压220V");
        return 220;
    }
}
//适配接口
interface Voltage5V{
    int output5V();
}
class VoltageAdapter extends Voltage220V implements Voltage5V{
    @Override
    public int output5V() {
        int srcV = output220V();
        int dstV = srcV / 44;
        System.out.println("5V");
        return dstV;
    }
}
class Phone{
    public void charging(Voltage5V v){
        if (v.output5V() == 5)
            System.out.println("可以充电");
        else if (v.output5V() > 5)
            System.out.println("电压过高，爆了");
    }
}
class PhoneClient{
    public static void main(String[] args) {
        System.out.println("类适配器模式");
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter());
    }
}

//对象适配器
/*
    根据“合成复用法则”，使用关联替代了继承
    对象适配器模式是适配器模式中常用的一种
 */
class VoltageAdapter2 implements Voltage5V{
    private Voltage220V v;

    public VoltageAdapter2(Voltage220V v) {
        this.v = v;
    }

    @Override
    public int output5V() {
        int dstV = 0;
        if (v != null){
            int srcV = v.output220V();
            dstV = srcV / 44;
            System.out.println("5V 适配完成");
        }else
            System.out.println("适配失败");
        return dstV;
    }
}

//接口适配器模式    又叫  缺省适配器模式
/*
    使用一个抽象类实现接口并提供默认的空实现，使需要实现接口的类只需继承抽象类并按需要重写部分方法
 */
interface Interface{
    void m1();
    void m2();
    void m3();
    void m4();
}
abstract class AbsAdapter implements Interface{
    @Override
    public void m1() {

    }

    @Override
    public void m2() {

    }

    @Override
    public void m3() {

    }

    @Override
    public void m4() {

    }
}
class Client{
    public static void main(String[] args) {
        new AbsAdapter(){
            @Override
            public void m1() {
                System.out.println("新的m1");
            }
        }.m1();
    }
}

/*
    SpringMVC中的适配器模式
        HandlerAdapter
 */
