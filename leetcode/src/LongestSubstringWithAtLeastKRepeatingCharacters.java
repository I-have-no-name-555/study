/**
 * @author :Xuan
 * @date :Create in 2021/7/25 19:35
 * @description 395. 至少有 K 个重复字符的最长子串 中等
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {
    /*
        给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串，
    要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
    示例 1：
    输入：s = "aaabb", k = 3
    输出：3
    解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
    示例 2：
    输入：s = "ababbc", k = 2
    输出：5
    解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
    提示：
    1 <= s.length <= 104
    s 仅由小写英文字母组成
    1 <= k <= 105
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int longestSubstring(String s, int k) {
        return dfs(s, 0, s.length() - 1, k);
    }

    public int dfs(String s, int left, int right, int k) {
        if (right - left + 1 < k){
            return 0;
        }
        int[] cnt = new int[26];
        for (int i = left; i <= right; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        char split = 0;
        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0 && cnt[i] < k) {
                split = (char) (i + 'a');
                break;
            }
        }
        if (split == 0) {
            return right - left + 1;
        }

        int i = left;
        int ret = 0;
        while (i <= right) {
            while (i <= right && s.charAt(i) == split) {
                i++;
            }
            if (i > right) {
                break;
            }
            int start = i;
            while (i <= right && s.charAt(i) != split) {
                i++;
            }

            int length = dfs(s, start, i - 1, k);
            ret = Math.max(ret, length);
        }
        return ret;
    }

    public int longestSubstring2(String s, int k) {
        int ret = 0;
        int n = s.length();
        for (int t = 1; t <= 26; t++) {
            int l = 0, r = 0;
            int[] cnt = new int[26];
            int tot = 0;
            int less = 0;
            while (r < n) {
                cnt[s.charAt(r) - 'a']++;
                if (cnt[s.charAt(r) - 'a'] == 1) {
                    tot++;
                    less++;
                }
                if (cnt[s.charAt(r) - 'a'] == k) {
                    less--;
                }

                while (tot > t) {
                    cnt[s.charAt(l) - 'a']--;
                    if (cnt[s.charAt(l) - 'a'] == k - 1) {
                        less++;
                    }
                    if (cnt[s.charAt(l) - 'a'] == 0) {
                        tot--;
                        less--;
                    }
                    l++;
                }
                if (less == 0) {
                    ret = Math.max(ret, r - l + 1);
                }
                r++;
            }
        }
        return ret;
    }

}
