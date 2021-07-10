import java.util.Arrays;

/**
 * @author :Xuan
 * @date :Create in 2021/7/10 17:10
 * @description 242. 有效的字母异位词 简单
 */
public class ValidAnagram {
    /*
        给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
    示例 1:
    输入: s = "anagram", t = "nagaram"
    输出: true
    示例 2:
    输入: s = "rat", t = "car"
    输出: false
    提示:
    1 <= s.length, t.length <= 5 * 104
    s 和 t 仅包含小写字母
    进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/valid-anagram
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

}
