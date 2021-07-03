/**
 * @author :Xuan
 * @date :Create in 2021/3/30 11:10
 * @description 剑指 Offer 14- II. 剪绳子 II 中等
 */
public class CuttingRopeII {
    /*
        给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。
    请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段,
    此时得到的最大乘积是18。
    答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
    示例 1：
    输入: 2
    输出: 1
    解释: 2 = 1 + 1, 1 × 1 = 1
    示例 2:
    输入: 10
    输出: 36
    解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
    提示：
    2 <= n <= 1000
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        System.out.println(new CuttingRopeII().cuttingRope(127));
        int m = 3;
        for (int i = 1; i < 1000; i++) {
            if (m > 1000000007){
                System.out.println(i);
                break;
            }
            m *= 3;
        }
        System.out.println(m % 1000000007);
    }

    public int cuttingRope(int n) {
        if (n <= 3)
            return n - 1;
        int a = n / 3;
        int b = n % 3;
        if (b == 0)
            return (int) get(a);
        if (b == 1)
            return (int) (get(a - 1) * 4 % mol);
        return (int) (get(a) * 2 % mol);
    }
    private static int base = 162261460;
    private static int mol = 1000000007;
    private long get(int a){
        int k = a / 19;
        int m = a % 19;
        long cur = 1;
        for (int i = 0; i < m; i++) {
            cur *= 3;
        }
        for (int i = 0; i < k; i++) {
            cur = (cur * base) % mol;
        }
        return (cur % mol);
    }
}
