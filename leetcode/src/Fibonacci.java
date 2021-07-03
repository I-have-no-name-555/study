import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author :Xuan
 * @date :Create in 2021/3/30 8:42
 * @description 剑指 Offer 10- I. 斐波那契数列 简单
 */
public class Fibonacci {
    /*
        写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
    F(0) = 0,   F(1) = 1
    F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
    斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
    答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
    示例 1：
    输入：n = 2
    输出：1
    示例 2：
    输入：n = 5
    输出：5
    提示：
    0 <= n <= 100
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    private static final int MOL = 1000000007;
    private static final int SIZE = 101;
    private static final int[] FIBS = new int[SIZE];
    private static int size = 0;
    static {
        FIBS[0] = 0;
        FIBS[1] = 1;
        size = 2;
    }
    public int fib(int n) {
        if (n >= size)
            updateFibs(n);
        return FIBS[n];
    }
    private void updateFibs(int n){
        int pre = FIBS[size - 2];
        int cur = FIBS[size - 1];
        while (size <= n){
            FIBS[size] = (pre + cur) % MOL;
            pre = cur;
            cur = FIBS[size++];
        }
    }

    public static void main(String[] args) {
        System.out.println(new Fibonacci().fib(100));
    }

    private static final int[] ANS;
    static {
        ANS = new int[]{0,1,1,2,3,5,8,13,21,34,
                55,89,144,233,377,610,987,1597,2584,4181,
                6765,10946,17711,28657,46368,75025,121393,196418,317811,514229,
                832040,1346269,2178309,3524578,5702887,9227465,14930352,24157817,39088169,63245986,
                102334155,165580141,267914296,433494437,701408733,134903163,836311896,971215059,807526948,778742000,
                586268941,365010934,951279875,316290802,267570670,583861472,851432142,435293607,286725742,722019349,
                8745084,730764433,739509517,470273943,209783453,680057396,889840849,569898238,459739080,29637311,
                489376391,519013702,8390086,527403788,535793874,63197655,598991529,662189184,261180706,923369890,
                184550589,107920472,292471061,400391533,692862594,93254120,786116714,879370834,665487541,544858368,
                210345902,755204270,965550172,720754435,686304600,407059028,93363621,500422649,593786270,94208912,
                687995182
        };
    }
    public int fib2(int n) {
        return ANS[n];
    }

}
