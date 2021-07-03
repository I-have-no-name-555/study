package gof.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:50
 * @description 组合   又叫  部分整体模式
 */
public class Composite {
    /*
        jdk中的组合模式：HashMap
        组合模式：
            简化客户端操作
            具有较强的扩展性
            方便创建出复杂的层次结构
            需要遍历组织结构或处理的对象具有树形结构时非常适合使用
            需要较高的抽象性，节点和叶子不应该差异过大
     */

    public static void main(String[] args) {
        OrganizationComponent university = new University("清华","世一大");
        OrganizationComponent computerCollege = new College("计院","秃头人");
        OrganizationComponent infoEngineerCollege = new College("信息工程学院","秃头人");
        computerCollege.add(new Department("软工","猝死"));
        computerCollege.add(new Department("cs","35岁辞退"));
        infoEngineerCollege.add(new Department("通信工程","不好学"));
        infoEngineerCollege.add(new Department("信息工程","好学"));
        university.add(computerCollege);
        university.add(infoEngineerCollege);
        university.print();
    }
}
abstract class OrganizationComponent{
    private String name;
    private String description;

    public OrganizationComponent(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public OrganizationComponent() {
    }

    protected void add(OrganizationComponent organizationComponent){
        throw new UnsupportedOperationException();
    }
    protected void remove(OrganizationComponent organizationComponent){
        throw new UnsupportedOperationException();
    }
    protected abstract void print();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
class University extends OrganizationComponent{
    private List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public University(List<OrganizationComponent> organizationComponents) {
        this.organizationComponents = organizationComponents;
    }

    public University(String name, String description) {
        super(name, description);
    }

    @Override
    protected void print() {
        System.out.println(getName());
        for(OrganizationComponent organizationComponent : organizationComponents){
            organizationComponent.print();
        }
    }

    @Override
    protected void add(OrganizationComponent organizationComponent) {
        organizationComponents.add(organizationComponent);
    }

    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponents.remove(organizationComponent);
    }
}
class College extends OrganizationComponent{
    private List<OrganizationComponent> organizationComponents = new ArrayList<>();

    public College(List<OrganizationComponent> organizationComponents) {
        this.organizationComponents = organizationComponents;
    }

    public College(String name, String description) {
        super(name, description);
    }

    @Override
    protected void print() {
        System.out.println(getName());
        for(OrganizationComponent organizationComponent : organizationComponents){
            organizationComponent.print();
        }
    }

    @Override
    protected void add(OrganizationComponent organizationComponent) {
        organizationComponents.add(organizationComponent);
    }

    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        organizationComponents.remove(organizationComponent);
    }
}
class Department extends OrganizationComponent{

    public Department(String name, String description) {
        super(name, description);
    }

    @Override
    protected void print() {
        System.out.println(getName());
    }

}
