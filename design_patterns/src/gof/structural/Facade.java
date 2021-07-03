package gof.structural;

/**
 * @author :Xuan
 * @date :Create in 2020/12/20 17:51
 * @description 外观   又叫   过程模式
 */
public class Facade {
    /*
        Mybatis中的外观模式：Configuration创建MetaObject
        外观模式：
            对外屏蔽了子系统的细节，降低了客户端对子系统使用的复杂性
            在维护一个大型的、难以维护和扩展的系统时，可以开发一个Facade类来提供更清晰简单的接口
            不要滥用外观模式，要以让系统有层次、利于维护为目的
     */
}
class DVDPlayer{
    private static DVDPlayer instance = new DVDPlayer();
    private DVDPlayer(){}
    public static DVDPlayer getInstance(){
        return instance;
    }
    public void on(){
        System.out.println("dvd on");
    }
    public void off(){
        System.out.println("dvd off");
    }
    public void play(){
        System.out.println("dvd play");
    }
    public void pause(){
        System.out.println("dvd pause......");
    }

}
class Popcorn{
    private static Popcorn instance = new Popcorn();
    private Popcorn(){}
    public static Popcorn getInstance(){
        return instance;
    }
    public void on(){
        System.out.println("popcorn on");
    }
    public void off(){
        System.out.println("popcorn off");
    }
    public void pop(){
        System.out.println("popcorn popping......");
    }
}
class Projector{
    private static Projector instance = new Projector();
    private Projector(){}
    public static Projector getInstance(){
        return instance;
    }
    public void on(){
        System.out.println("projector on");
    }
    public void off(){
        System.out.println("projector off");
    }
    public void focus(){
        System.out.println("projector focusing......");
    }
}
class Screen{
    private static Screen instance = new Screen();
    private Screen(){}
    public static Screen getInstance(){
        return instance;
    }
    public void up(){
        System.out.println("screen on");
    }
    public void down(){
        System.out.println("screen down");
    }
}
class HomeTheater{
    private Popcorn popcorn;
    private Projector projector;
    private DVDPlayer dvdPlayer;
    private Screen screen;

    public HomeTheater() {
        popcorn = Popcorn.getInstance();
        projector = Projector.getInstance();
        dvdPlayer = DVDPlayer.getInstance();
        screen = Screen.getInstance();
    }

    public void ready(){
        popcorn.on();
        popcorn.pop();
        screen.down();
        projector.on();
        dvdPlayer.on();
    }
    public void play(){
        dvdPlayer.play();
    }
    public void pause(){
        dvdPlayer.pause();
    }
    public void end(){
        popcorn.off();
        screen.up();
        projector.off();
        dvdPlayer.off();
    }
}
class TheaterClient{
    public static void main(String[] args) {
        HomeTheater homeTheater = new HomeTheater();
        homeTheater.ready();
        homeTheater.play();
        homeTheater.pause();
        homeTheater.end();
    }
}