package gof.creational;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:49
 * @description 建造者      又叫 生成器
 */
public abstract class Builder {
    /*
    jdk中的建造者模式 ： StringBuilder
        抽象建造者：Appendable
        建造者：AbstractStringBuilder、StringBuilder
        指挥者：StringBuilder
     注意事项：
        建造者模式可以更加精细的创造对象，可以控制创造的过程
        建造者模式创建的产品差异性应当不大，否则需要大量的类，系统将会很复杂
        与工厂模式相比，工厂模式只关注产品，而建造者模式更注重建造的过程，指挥者可以根据需求按不同的步骤建造产品
     */
    protected House house;

    public abstract void buildBasic();
    public abstract void buildWalls();
    public abstract void roofed();
    public House build(){
        buildBasic();
        buildWalls();
        roofed();
        return house;
    }
}
abstract class House{

}
class CommonHouse extends House{

}
class CommonHouseBuild extends Builder{
    {
        house = new CommonHouse();
    }
    @Override
    public void buildBasic() {
        System.out.println("普通房子打地基");
    }

    @Override
    public void buildWalls() {
        System.out.println("普通房子砌墙");
    }

    @Override
    public void roofed() {
        System.out.println("普通房子砌墙");
    }
}
class HouseDirector{
    private Builder builder;

    public HouseDirector(Builder builder) {
        this.builder = builder;
    }

    public House constructHouse(){
        builder.buildBasic();
        builder.buildWalls();
        builder.roofed();
        return builder.house;
    }


    public Builder getBuilder() {
        return builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }
}