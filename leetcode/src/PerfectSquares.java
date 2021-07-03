import java.util.Arrays;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/6/9 19:18
 * @description 279. 完全平方数 中等
 */
public class PerfectSquares {
    /*

    给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。
    你需要让组成和的完全平方数的个数最少。
    给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
    完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
    例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
    示例 1：
    输入：n = 12
    输出：3
    解释：12 = 4 + 4 + 4
    示例 2：
    输入：n = 13
    输出：2
    解释：13 = 4 + 9
    提示：
    1 <= n <= 104
     */
    private static final int[] SQUARES = {0,1,4,9,16,25,36,49,64,81,100,121,144,169,196,225
            ,256,289,324,361,400,441,484,529,576,625,676,729,784,841,900,961,1024,1089,1156
            ,1225,1296,1369,1444,1521,1600,1681,1764,1849,1936,2025,2116,2209,2304,2401,2500
            ,2601,2704,2809,2916,3025,3136,3249,3364,3481,3600,3721,3844,3969,4096,4225,4356
            ,4489,4624,4761,4900,5041,5184,5329,5476,5625,5776,5929,6084,6241,6400,6561,6724
            ,6889,7056,7225,7396,7569,7744,7921,8100,8281,8464,8649,8836,9025,9216,9409,9604,9801,10000};
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int index = (int) Math.sqrt(n) + 1;
        for (int i = 1; i <= n; ++i) {
            for (int s = 1; s < index; ++s) {
                if (i < SQUARES[s]) {
                    break;
                }
                dp[i] = Math.min(dp[i], dp[i - SQUARES[s]] + 1);
            }
        }
        return dp[n];
    }



    public static void main(String[] args) {
        System.out.println(Arrays.binarySearch(SQUARES,99));
    }
}
