
/**
 * @author :Xuan
 * @date :Create in 2021/7/2 15:38
 * @description 647. 回文子串 中等
 */
public class PalindromicSubstrings {
    /*
        给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
    具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
    示例 1：
    输入："abc"
    输出：3
    解释：三个回文子串: "a", "b", "c"
    示例 2：
    输入："aaa"
    输出：6
    解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
    提示：
    输入的字符串长度不会超过 1000 。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/palindromic-substrings
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int countSubstrings(String s) {
        if (s == null) {
            return 0;
        }
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int res = 0;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j <= len - i; j++) {
                if (i == 1)
                    dp[j][j] = true;
                else if (s.charAt(j) == s.charAt(j + i - 1)
                        && (i == 2 || dp[j + 1][j + i - 2])) {
                    dp[j][j + i - 1] = true;
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (dp[i][j]){
                    res++;
                }
            }
        }
        return res;
    }
    public int countSubstrings2(String s) {
        if (s == null) {
            return 0;
        }
        int n = s.length(), count = 0;
        for (int i = 0; i < (n << 1) - 1; i++) {
            int left = i >> 1, right = (i >> 1) + (i & 1);
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                --left;
                ++right;
                ++count;
            }
        }
        return count;
    }
}
