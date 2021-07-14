import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author :Xuan
 * @date :Create in 2021/7/14 16:30
 * @description 326. 3的幂 简单
 */
public class PowerOfThree {
    /*
        给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
    整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x
    示例 1：
    输入：n = 27
    输出：true
    示例 2：
    输入：n = 0
    输出：false
    示例 3：
    输入：n = 9
    输出：true
    示例 4：
    输入：n = 45
    输出：false
    提示：
    -231 <= n <= 231 - 1
    进阶：
    你能不使用循环或者递归来完成本题吗？
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/power-of-three
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    private static final Set<Integer> SET = new HashSet<>(Arrays.asList(1,3,9,27,81,243,729,2187,6561,19683,59049,177147,531441,1594323,4782969,14348907,43046721,129140163,387420489,1162261467));
    private static final int[] NUMS = {1,3,9,27,81,243,729,2187,6561,19683,59049,177147,531441,1594323,4782969,14348907,43046721,129140163,387420489,1162261467};
    public boolean isPowerOfThree(int n) {
//        return SET.contains(n);
        return Arrays.binarySearch(NUMS,n) >= 0;
    }

    public boolean isPowerOfThree2(int n) {
        return n > 0 && 1162261467 % n == 0;
    }


}
