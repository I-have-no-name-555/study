package practice;

import org.junit.Test;

/**
 * @author :Xuan
 * @date :Create in 2020/12/7 20:14
 * @description 第二章习题的测试
 * @update
 */
public class AlgorithmAnalysisTest {
    private static AlgorithmAnalysis test = new AlgorithmAnalysis();
    @Test
    public void test15(){
        int[] a = new int[]{-3,-1,1,2,5};
        System.out.println(test.test15(a));
    }
    @Test
    public void test17(){
        int[] a = new int[]{-3,-1,1,2,5};
        System.out.println(test.test17A(a));
        System.out.println(test.test17C(a));
    }
    @Test
    public void test27(){
        int[][] a = new int[][]{
                {2,3,4,5},
                {3,4,5,6},
                {4,5,6,7},
                {5,6,7,8}
        };
        System.out.println(test.test27(a,1));
        System.out.println(test.test27(a,5));
        System.out.println(test.test27(a,9));
    }
    @Test
    public void test28(){
        int[] a = new int[]{1,2,3,4,5,6};
        System.out.println(test.test28A(a));
        System.out.println(test.test28B(a));
        System.out.println(test.test28C(a));
        System.out.println(test.test28D(a));
    }
}
