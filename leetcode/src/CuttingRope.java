import java.util.Arrays;

/**
 * @author :Xuan
 * @date :Create in 2021/3/30 11:09
 * @description 剑指 Offer 14- I. 剪绳子 中等
 */
public class CuttingRope {
    /*
        给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
    请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，
    此时得到的最大乘积是18。
    示例 1：
    输入: 2
    输出: 1
    解释: 2 = 1 + 1, 1 × 1 = 1
    示例 2:
    输入: 10
    输出: 36
    解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
    提示：
    2 <= n <= 58
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    static int[] dp = new int[59];

    /*
    0, 0, 1, 2, 4, 6, 9, 12, 18, 27, 36, 54, 81, 108, 162, 243, 324, 486, 729, 972, 1458, 2187
    , 2916, 4374, 6561, 8748, 13122, 19683, 26244, 39366, 59049, 78732, 118098, 177147, 236196
    , 354294, 531441, 708588, 1062882, 1594323, 2125764, 3188646, 4782969, 6377292, 9565938, 14348907
    , 19131876, 28697814, 43046721, 57395628, 86093442, 129140163, 172186884, 258280326
    , 387420489, 516560652, 774840978, 1162261467, 1549681956
     */
    static {
        dp[2] = 1;
        for (int i = 3; i < 59; i++) {
            for (int j = 2; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
    }

    public int cuttingRope(int n) {
        return dp[n];
    }

    public int cuttingRope2(int n) {
        if (n <= 3)
            return n - 1;
        int a = n / 3;
        int b = n % 3;
        if (b == 0)
            return (int) Math.pow(3, a);
        if (b == 1)
            return (int) Math.pow(3, a - 1) * 4;
        return (int) Math.pow(3, a) * 2;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(dp));
    }
}
