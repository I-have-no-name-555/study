package gof.behavioral;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:53
 * @description 命令
 */
public interface Command {
    /*
        Spring中的命令模式：Jdbc Template
        命令模式：
            把发起请求的对象和执行请求的对象解耦。调用者不必知道执行者是谁以及如何执行
            容易设计一个命令队列进行多线程操作
            容易实现命令撤销与重做
            不足： 在某些系统中可能出现过多的具体命令类，增加系统复杂度
            空命令可以省去判空操作简化编码
            经典应用场景：
                模拟cmd
                订单生成与撤销
                触发-反馈机制
     */
    void execute();
    void undo();
}
class LightReceiver{
    public void on(){
        System.out.println("电灯打开");
    }
    public void off(){
        System.out.println("电灯关闭");
    }
}
class LightOnCommand implements Command{
    private LightReceiver lightReceiver;

    @Override
    public void execute() {
        lightReceiver.on();
    }

    @Override
    public void undo() {
        lightReceiver.off();
    }

    public LightOnCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }
}
class LightOffCommand implements Command{
    private LightReceiver lightReceiver;

    @Override
    public void execute() {
        lightReceiver.off();
    }

    @Override
    public void undo() {
        lightReceiver.on();
    }

    public LightOffCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }
}
class NoCommand implements Command{
    private NoCommand(){

    }
    public static final NoCommand INSTANCE = new NoCommand();
    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
class RemoteController{
    private Command[] onCommands;
    private Command[] offCommands;
    private Command undoCommand;

    public RemoteController() {
        onCommands = new Command[5];
        offCommands = new Command[5];
        for (int i = 0; i < 4; i++) {
            onCommands[i] = NoCommand.INSTANCE;
            offCommands[i] = NoCommand.INSTANCE;
        }
    }

    public void setCommand(int no , Command onCommand , Command offCommand){
        onCommands[no] = onCommand;
        offCommands[no] = offCommand;
    }
    public void onButtonWasPushed(int no){
        onCommands[no].execute();
        undoCommand = onCommands[no];
    }
    public void offButtonWasPushed(int no){
        offCommands[no].execute();
        undoCommand = offCommands[no];
    }
    public void undoButtonWasPushed(){
        undoCommand.undo();
    }
}
class RemoteControllerClient{
    public static void main(String[] args) {
        RemoteController controller = new RemoteController();
        LightReceiver lightReceiver = new LightReceiver();
        controller.setCommand(0,new LightOnCommand(lightReceiver),
                new LightOffCommand(lightReceiver));
        controller.onButtonWasPushed(0);
        controller.undoButtonWasPushed();
        controller.offButtonWasPushed(0);
        controller.undoButtonWasPushed();
    }
}