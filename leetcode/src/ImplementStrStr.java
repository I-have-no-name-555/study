/**
 * @author :Xuan
 * @date :Create in 2020/12/30 13:00
 * @description 28.实现strStr 简单
 * @update
 */
public class ImplementStrStr {
    /*
        实现 strStr() 函数。
    给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
    示例 1:
    输入: haystack = "hello", needle = "ll"
    输出: 2
    示例 2:
    输入: haystack = "aaaaa", needle = "bba"
    输出: -1
    说明:
    当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
    对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/implement-strstr
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //调库
    public int strStr1(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    //逐一比较子串，耗费空间
    public int strStr2(String haystack, String needle) {

        int n = needle.length();
        if (n == 0)
            return 0;
        int m = haystack.length();
        for (int i = 0; i < m - n + 1; i++) {
            if (haystack.substring(i, i + n).equals(needle))
                return i;
        }
        return -1;
    }

    //Rabin Karp
    private int charToInt(int idx, String s) {
        return (int) s.charAt(idx) - (int) 'a';
    }
    public int strStr3(String haystack, String needle) {
        int L = needle.length();
        int n = haystack.length();
        if (L > n)
            return -1;
        if (n == 0)
            return 0;
        int a = 26;
        long modulus = (long) Math.pow(2, 31);
        long h = 0, ref_h = 0;
        for (int i = 0; i < L; ++i) {
            h = (h * a + charToInt(i, haystack)) % modulus;
            ref_h = (ref_h * a + charToInt(i, needle)) % modulus;
        }
        if (h == ref_h)
            return 0;
        long aL = 1;
        for (int i = 1; i <= L; ++i)
            aL = (aL * a) % modulus;
        for (int start = 1; start < n - L + 1; ++start) {
            h = (h * a - charToInt(start - 1, haystack) * aL
                    + charToInt(start + L - 1, haystack)) % modulus;
            if (h == ref_h)
                return start;
        }
        return -1;
    }

    //KMP
    public int strStr4(String txt, String pat) {
        if (pat.length() == 0)
            return 0;
        int[][] dp;
        int M = pat.length();
        // dp[状态][字符] = 下个状态
        dp = new int[M][256];
        // base case
        dp[0][pat.charAt(0)] = 1;
        // 影子状态 X 初始为 0
        int X = 0;
        // 构建状态转移图（稍改的更紧凑了）
        for (int j = 1; j < M; j++) {
            System.arraycopy(dp[X], 0, dp[j], 0, 256);
            dp[j][pat.charAt(j)] = j + 1;
            // 更新影子状态
            X = dp[X][pat.charAt(j)];
        }
        int N = txt.length();
        // pat 的初始态为 0
        int j = 0;
        for (int i = 0; i < N; i++) {
            // 计算 pat 的下一个状态
            j = dp[j][txt.charAt(i)];
            // 到达终止态，返回结果
            if (j == M) return i - M + 1;
        }
        // 没到达终止态，匹配失败
        return -1;
    }


}
