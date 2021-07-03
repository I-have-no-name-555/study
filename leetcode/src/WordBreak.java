import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author :Xuan
 * @date :Create in 2021/3/9 9:32
 * @description 139. 单词拆分 中等
 */
public class WordBreak {
    /*
        给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
    说明：
    拆分时可以重复使用字典中的单词。
    你可以假设字典中没有重复的单词。
    示例 1：
    输入: s = "leetcode", wordDict = ["leet", "code"]
    输出: true
    解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
    示例 2：
    输入: s = "applepenapple", wordDict = ["apple", "pen"]
    输出: true
    解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
         注意你可以重复使用字典中的单词。
    示例 3：
    输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
    输出: false
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/word-break
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        String s = "aaa";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("a");
        wordDict.add("");
        System.out.println(new WordBreak().wordBreak(s, wordDict));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] visited = new boolean[s.length() + 1];
        return dfs(s, 0, wordDict, visited);
    }
    private boolean dfs(String s, int start, List<String> wordDict, boolean[] visited) {
        for (String word : wordDict) {
            int nextStart = start + word.length();
            if (nextStart > s.length() || visited[nextStart]) {
                continue;
            }
            if (s.indexOf(word, start) == start) {
                if (nextStart == s.length() || dfs(s, nextStart, wordDict, visited))
                    return true;
                visited[nextStart] = true;
            }
        }
        return false;
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

}
