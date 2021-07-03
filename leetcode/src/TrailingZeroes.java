/**
 * @author :Xuan
 * @date :Create in 2021/5/13 15:15
 * @description 172. 阶乘后的零 简单
 */
public class TrailingZeroes {
    /*
        给定一个整数 n，返回 n! 结果尾数中零的数量。
    示例 1:
    输入: 3
    输出: 0
    解释: 3! = 6, 尾数中没有零。
    示例 2:
    输入: 5
    输出: 1
    解释: 5! = 120, 尾数中有 1 个零.
    说明: 你算法的时间复杂度应为 O(log n) 。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        System.out.println(new TrailingZeroes().trailingZeroes(3));
    }
    public int trailingZeroes(int n) {
        int res = 0;
        for (int i = 5; i <= n; i += 5) {
            int currentFactor = i;
            while (currentFactor % 5 == 0) {
                res++;
                currentFactor /= 5;
            }
        }
        return res;
    }
    public int trailingZeroes2(int n) {
        int res = 0;
        while (n > 0) {
            n /= 5;
            res += n;
        }
        return res;
    }

}
