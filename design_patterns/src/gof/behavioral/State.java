package gof.behavioral;

import java.util.Random;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:55
 * @description 状态
 */
public interface State {
    /*
        状态模式：
            代码可读性强，方便维护
            会产生很多类
     */
    void deduceMoney();
    boolean raffle();
    void dispensePrize();
}
class NoRaffleState implements State{
    RaffleActivity activity;

    public NoRaffleState(RaffleActivity activity) {
        this.activity = activity;
    }

    @Override
    public void deduceMoney() {
        System.out.println("扣除50积分，可抽奖了");
        activity.setState(activity.getCanRaffleState());
    }

    @Override
    public boolean raffle() {
        System.out.println("扣除积分才能抽奖");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("中奖才能领钱");
    }
}
class CanRaffleState implements State{
    private RaffleActivity activity;

    @Override
    public void deduceMoney() {
        System.out.println("已扣除积分");
    }

    @Override
    public boolean raffle() {
        System.out.println("正在抽奖");
        if (new Random().nextInt(10) == 0){
            System.out.println("抽中了");
            activity.setState(activity.getDispensePrizeState());
            return true;
        }else {
            System.out.println("没抽中");
            activity.setState(activity.getNoRaffleState());
            return false;
        }
    }

    @Override
    public void dispensePrize() {
        System.out.println("没中奖");
    }

    public CanRaffleState(RaffleActivity activity) {
        this.activity = activity;
    }
}
class DispensePrizeState implements State{
    private RaffleActivity activity;

    @Override
    public void deduceMoney() {
        System.out.println("不能扣除积分");
    }

    @Override
    public boolean raffle() {
        System.out.println("不能抽奖");
        return false;
    }

    @Override
    public void dispensePrize() {
        if (activity.getCount() > 0){
            System.out.println("中奖");
            activity.setState(activity.getNoRaffleState());
        }else {
            System.out.println("很遗憾，奖品发完了");
            activity.setState(activity.getDispenseOutState());
        }
    }

    public DispensePrizeState(RaffleActivity activity) {
        this.activity = activity;
    }
}
class DispenseOutState implements State{
    private RaffleActivity activity;

    @Override
    public void deduceMoney() {
        System.out.println("奖品发完，下次参加");
    }

    @Override
    public boolean raffle() {
        System.out.println("奖品发完，下次参加");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("奖品发完，下次参加");
    }

    public DispenseOutState(RaffleActivity activity) {
        this.activity = activity;
    }
}

class RaffleActivity{
    private NoRaffleState noRaffleState = new NoRaffleState(this);
    private CanRaffleState canRaffleState = new CanRaffleState(this);
    private DispenseOutState dispenseOutState = new DispenseOutState(this);
    private DispensePrizeState dispensePrizeState = new DispensePrizeState(this);
    private State state;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public NoRaffleState getNoRaffleState() {
        return noRaffleState;
    }

    public void setNoRaffleState(NoRaffleState noRaffleState) {
        this.noRaffleState = noRaffleState;
    }

    public CanRaffleState getCanRaffleState() {
        return canRaffleState;
    }

    public void setCanRaffleState(CanRaffleState canRaffleState) {
        this.canRaffleState = canRaffleState;
    }

    public DispenseOutState getDispenseOutState() {
        return dispenseOutState;
    }

    public void setDispenseOutState(DispenseOutState dispenseOutState) {
        this.dispenseOutState = dispenseOutState;
    }

    public DispensePrizeState getDispensePrizeState() {
        return dispensePrizeState;
    }

    public void setDispensePrizeState(DispensePrizeState dispensePrizeState) {
        this.dispensePrizeState = dispensePrizeState;
    }
}
