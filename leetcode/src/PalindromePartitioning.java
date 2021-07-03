
import java.util.ArrayList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/3/4 10:22
 * @description 131. 分割回文串  中等
 */
public class PalindromePartitioning {
    /*
        给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
    返回 s 所有可能的分割方案。
    示例:
    输入: "aab"
    输出:
    [
      ["aa","b"],
      ["a","a","b"]
    ]
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/palindrome-partitioning
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>> ans = new ArrayList<>();
        List<String> stack = new ArrayList<>();
        dfs(s, 0, len, isPartition(s.toCharArray()), stack, ans);
        return ans;
    }
    private void dfs(String s, int index, int len, boolean[][] dp, List<String> path, List<List<String>> res) {
        if (index == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < len; i++) {
            if (dp[index][i]) {
                path.add(s.substring(index, i + 1));
                dfs(s, i + 1, len, dp, path, res);
                path.remove(path.size() - 1);
            }
        }
    }
    private boolean[][] isPartition(char[] charArray){
        int len = charArray.length;
        boolean[][] dp = new boolean[len][len];
        for (int right = 0; right < len; right++) {
            for (int left = 0; left <= right; left++) {
                if (charArray[left] == charArray[right]
                        && (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                }
            }
        }
        return dp;
    }

}
