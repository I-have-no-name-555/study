/**
 * @author :Xuan
 * @date :Create in 2021/1/13 15:17
 * @description 50. Pow(x, n) 中等
 * @update
 */
public class Pow {
    /*
        实现 pow(x, n) ，即计算 x 的 n 次幂函数。
    示例 1:
    输入: 2.00000, 10
    输出: 1024.00000
    示例 2:
    输入: 2.10000, 3
    输出: 9.26100
    示例 3:
    输入: 2.00000, -2
    输出: 0.25000
    解释: 2-2 = 1/22 = 1/4 = 0.25
    说明:
    -100.0 < x < 100.0
    n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/powx-n
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        double x = 2.0;
        int n = 0;
        System.out.println(new Pow().myPow3(x, n));
    }
    //暴力 超时
    public double myPow(double x, int n) {
        if ((n == 0 && x != 0) || x == 1.0)
            return 1.0;
        double ans = 1;
        for (int i = 0; i < n; i++){
            ans *= x;
        }
        for (int i = n; i < 0; i++) {
            ans /= x;
        }
        return ans;
    }
    //快速幂优化  还是超时
    public double myPow2(double x, int n) {
        if ((n == 0 && x != 0) || x == 1.0)
            return 1.0;
        boolean flag = n < 0;
        double ans = x;
        if (flag)
            n = -n;
        int a = 0;
        int b = 1;
        while (n >= (b << 1)){
            a++;
            b <<= 1;
        }
        for (int i = 0; i < a; i++)
            ans *= ans;
        n = n - b;
        for (int i = 0; i < n; i++)
            ans *= x;
        return flag ? 1 / ans : ans;
    }
    //迭代 + 快速幂
    private double quickMul(double x, long N) {
        double ans = 1.0;
        double x_contribute = x;
        while (N > 0) {
            if ((N & 1) == 1)
                ans *= x_contribute;
            x_contribute *= x_contribute;
            N >>= 1;
        }
        return ans;
    }

    public double myPow3(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

}
