package practice;

import org.junit.Test;

import java.io.InputStream;
import java.util.*;

/**
 * @author :Xuan
 * @date :Create in 2020/12/8 18:04
 * @description 第三章 习题测试
 * @update
 */
public class ListsStacksAndQueuesTest {
    @Test
    public void test(){
        Map<Integer,Integer> map = new HashMap<>();
        map.put(1,2);
        System.out.println(map.remove(1,null));
    }

    private ListsStacksAndQueues test = new ListsStacksAndQueues();
    @Test
    public void test4And5(){
        List<Integer> l1 = new ArrayList<>(5);
        List<Integer> l2 = new ArrayList<>(5);
        l1.add(1);
        l1.add(2);
        l1.add(3);
        l1.add(4);
        l1.add(5);
        l2.add(1);
        l2.add(3);
        l2.add(5);
        l2.add(7);
        l2.add(9);
        System.out.println(test.test4(l1, l2));
        System.out.println(test.test5(l1, l2));
    }
    @Test
    public void test6(){
        System.out.println(test.test6(1, 5));
    }
    //因为Junit单元测试，无法进行Scanner输入,22、23均不进行测试
}
