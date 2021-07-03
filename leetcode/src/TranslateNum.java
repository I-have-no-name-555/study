/**
 * @author :Xuan
 * @date :Create in 2021/4/17 18:36
 * @description 剑指 Offer 46. 把数字翻译成字符串 中等
 */
public class TranslateNum {
    /*
        给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，
    1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。
    请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
    示例 1:
    输入: 12258
    输出: 5
    解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
    提示：
    0 <= num < 231
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int num = 506;
        System.out.println(new TranslateNum().translateNum(num));
    }
    //由于只用到dp[i - 1] 和dp[i - 2],可以优化为双变量，懒得写
    public int translateNum(int num) {
        if (num < 10)
            return 1;
        char[] n = String.valueOf(num).toCharArray();
        int len = n.length;
        int[] dp = new int[len];
        dp[0] = 1;
        dp[1] = (n[0] == '2' && n[1] < '6') || n[0] < '2' ? 2 : 1;
        for (int i = 2; i < len; i++) {
            dp[i] = (n[i - 1] == '2'  && n[i] < '6') || n[i - 1] == '1' ?
                    dp[i - 2] + dp[i - 1] : dp[i - 1];
        }
        return dp[len - 1];
    }
}
