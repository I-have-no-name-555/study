package gof.behavioral;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:53
 * @description 职责链
 */
public class ChainOfResponsibility {
    /*
        SpringMVC中的职责链模式： HandlerExecutionChain
        职责链模式：
            将请求和处理解耦，提高了系统的灵活性
            简化了对象，使对象不需要知道链的结构
            性能会受到影响，特别是链较长的时候，因此需控制链的最大节点数量
            一般在Handler中设置一个最大节点数量，在setNext方法中判断是否已经超过阈值
            由于多层调用，不易调试
            应用场景：
                多级请求
                请假、加薪等审批流程
                JavaWeb中的Filter等
     */
}

abstract class Approver {
    Approver approver;
    String name;

    public Approver(String name) {
        this.name = name;
    }

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    public abstract void processRequest(PurchaseRequest purchaseRequest);
}

class DepartmentApprover extends Approver {
    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPrice() <= 5000)
            System.out.println("请求" + purchaseRequest.getId() + "已由" + this.name + "处理");
        else this.approver.processRequest(purchaseRequest);
    }

    public DepartmentApprover(String name) {
        super(name);
    }
}

class CollegeApprover extends Approver {
    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPrice() <= 10000)
            System.out.println("请求" + purchaseRequest.getId() + "已由" + this.name + "处理");
        else this.approver.processRequest(purchaseRequest);
    }

    public CollegeApprover(String name) {
        super(name);
    }
}

class ViceSchoolMasterApprover extends Approver {
    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPrice() <= 30000)
            System.out.println("请求" + purchaseRequest.getId() + "已由" + this.name + "处理");
        else this.approver.processRequest(purchaseRequest);
    }

    public ViceSchoolMasterApprover(String name) {
        super(name);
    }
}

class SchoolApprover extends Approver {
    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        System.out.println("请求" + purchaseRequest.getId() + "已由" + this.name + "处理");
    }

    public SchoolApprover(String name) {
        super(name);
    }
}

class PurchaseRequest {
    private int type;
    private float price;
    private int id;

    public PurchaseRequest(int type, float price, int id) {
        this.type = type;
        this.price = price;
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class PurchaseClient{
    public static void main(String[] args) {
        PurchaseRequest purchaseRequest = new PurchaseRequest(1,31000,1);
        PurchaseRequest purchaseRequest2 = new PurchaseRequest(1,2000,2);
        DepartmentApprover departmentApprover = new DepartmentApprover("张主任");
        CollegeApprover collegeApprover = new CollegeApprover("李院长");
        ViceSchoolMasterApprover viceSchoolMasterApprover = new ViceSchoolMasterApprover("王副校");
        SchoolApprover schoolApprover = new SchoolApprover("孙校长");
        departmentApprover.setApprover(collegeApprover);
        collegeApprover.setApprover(viceSchoolMasterApprover);
        viceSchoolMasterApprover.setApprover(schoolApprover);
        departmentApprover.processRequest(purchaseRequest2);
    }
}