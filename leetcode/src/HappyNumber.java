import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author :Xuan
 * @date :Create in 2021/5/22 21:30
 * @description 202. 快乐数 简单
 */
public class HappyNumber {
    /*
        编写一个算法来判断一个数 n 是不是快乐数。
    「快乐数」定义为：
    对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
    然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
    如果 可以变为  1，那么这个数就是快乐数。
    如果 n 是快乐数就返回 true ；不是，则返回 false 。
    示例 1：
    输入：19
    输出：true
    解释：
    12 + 92 = 82
    82 + 22 = 68
    62 + 82 = 100
    12 + 02 + 02 = 1
    示例 2：
    输入：n = 2
    输出：false
    提示：
    1 <= n <= 2^31 - 1
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/happy-number
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    private static Set<Integer> cycleMembers =
            new HashSet<>(Arrays.asList(4, 16, 37, 58, 89, 145, 42, 20));

    public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy(int n) {
        while (n != 1 && !cycleMembers.contains(n)) {
            n = getNext(n);
        }
        return n == 1;
    }

}
