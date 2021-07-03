/**
 * @author :Xuan
 * @date :Create in 2021/3/26 8:42
 * @description 97. 交错字符串 中等
 */
public class InterleavingString {
    /*
    给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
    两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
    s = s1 + s2 + ... + sn
    t = t1 + t2 + ... + tm
    |n - m| <= 1
    交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
    提示：a + b 意味着字符串 a 和 b 连接。
    示例 1：
    输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
    输出：true
    示例 2：
    输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
    输出：false
    示例 3：
    输入：s1 = "", s2 = "", s3 = ""
    输出：true
    提示：
    0 <= s1.length, s2.length <= 100
    0 <= s3.length <= 200
    s1、s2、和 s3 都由小写英文字母组成
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int len = s3.length();
        int m = s1.length();
        int n = s2.length();
        if (len != m + n)
            return false;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                int p = i + j - 1;
                if (i > 0)
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(p);
                if (j > 0)
                    dp[j] = dp[j] || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
            }
        }
        return dp[n];
    }

    boolean[][] dp;
    String ss1, ss2, ss3;
    int n, m, k;
    public boolean isInterleave2(String s1, String s2, String s3) {
        n = s1.length();
        m = s2.length();
        k = s3.length();
        if (k != (n + m)) return false;
        dp = new boolean[n + 1][m + 1];
        ss1 = s1;
        ss2 = s2;
        ss3 = s3;
        return DFS(0, 0);
    }
    public boolean DFS(int x, int y) {
        if (dp[x][y])
            return false;//不可达
        if (x == n && y == m)
            return true;
        if (x < n && ss1.charAt(x) == ss3.charAt(x + y) && DFS(x + 1, y))
            return true;
        if (y < m && ss2.charAt(y) == ss3.charAt(x + y) && DFS(x, y + 1))
            return true;
        dp[x][y] = true;
        return false;
    }
}

