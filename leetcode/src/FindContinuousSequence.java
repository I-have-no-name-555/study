import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/4/27 10:06
 * @description 剑指 Offer 57 - II. 和为s的连续正数序列 简单
 */
public class FindContinuousSequence {
    /*
        输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
    序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
    示例 1：
    输入：target = 9
    输出：[[2,3,4],[4,5]]
    示例 2：
    输入：target = 15
    输出：[[1,2,3,4,5],[4,5,6],[7,8]]
    限制：
    1 <= target <= 10^5
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int[][] findContinuousSequence(int target) {
        List<int[]> vec = new ArrayList<>();
        int limit = (target - 1) >> 1;
        for (int x = 1; x <= limit; ++x) {
            long delta = 1 - 4 * (x - (long) x * x - 2L * target);
            if (delta < 0) {
                continue;
            }
            int delta_sqrt = (int) Math.sqrt(delta);
            if ((long) delta_sqrt * delta_sqrt == delta && ((delta_sqrt - 1) & 1) == 0) {
                int y = (-1 + delta_sqrt) >> 1;
                if (x < y) {
                    int[] res = new int[y - x + 1];
                    for (int i = x; i <= y; ++i) {
                        res[i - x] = i;
                    }
                    vec.add(res);
                }
            }
        }
        return vec.toArray(new int[0][]);
    }

    public int[][] findContinuousSequence2(int target) {
        List<int[]> vec = new ArrayList<>();
        int half = target >> 1;
        for (int l = 1, r = 2; l < r && l <= half;) {
            int sum = ((l + r) * (r - l + 1)) >> 1;
            if (sum == target) {
                int[] res = new int[r - l + 1];
                for (int i = l; i <= r; ++i) {
                    res[i - l] = i;
                }
                vec.add(res);
                l++;
            } else if (sum < target) {
                r++;
            } else {
                l++;
            }
        }
        return vec.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new FindContinuousSequence().findContinuousSequence2(9)));
    }
}
