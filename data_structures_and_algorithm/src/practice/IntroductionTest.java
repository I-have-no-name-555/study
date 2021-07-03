package practice;

import org.junit.Test;

/**
 * @author :Xuan
 * @date :Create in 2020/12/5 17:33
 * @description 测试习题的运行结果
 * @update
 */
public class IntroductionTest {
    @Test
    public void test2(){
        char[][] chars = new char[][]{
                {'t','h','i','s'},
                {'w','a','t','s'},
                {'o','a','h','g'},
                {'f','g','d','t'}
        };
        String[] words = new String[]{"this","fat","two","that"};
        new Introduction().test2(chars,words);
    }
    @Test
    public void test3(){
        new Introduction().test3(-123.456);
    }
    @Test
    public void test5(){
        System.out.println(new Introduction().test5(3));
    }
    @Test
    public void test6(){
        new Introduction().permute("abc");
    }



}
