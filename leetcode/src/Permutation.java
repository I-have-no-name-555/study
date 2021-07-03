import java.util.*;

/**
 * @author :Xuan
 * @date :Create in 2021/4/10 12:18
 * @description 剑指 Offer 38. 字符串的排列 中等
 */
public class Permutation {
    /*
        输入一个字符串，打印出该字符串中字符的所有排列。
    你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
    示例:
    输入：s = "abc"
    输出：["abc","acb","bac","bca","cab","cba"]
    限制：
    1 <= s 的长度 <= 8
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    List<String> res = new ArrayList<>();
    char[] chars;
    int len;

    public String[] permutation(String s) {
        chars = s.toCharArray();
        len = chars.length;
        dfs(0);
        return res.toArray(new String[0]);
    }

    private void swap(int a, int b) {
        if (chars[a] == chars[b])
            return;
        chars[a] = (char) (chars[a] ^ chars[b]);
        chars[b] = (char) (chars[a] ^ chars[b]);
        chars[a] = (char) (chars[a] ^ chars[b]);
    }

    private void dfs(int x) {
        if (x == len - 1) {
            res.add(new String(chars));
            return;
        }
        boolean[] used = new boolean[26];
        for (int i = x; i < len; i++) {
            if (used[chars[i] - 'a'])
                continue;
            used[chars[i] - 'a'] = true;
            swap(x, i);
            dfs(x + 1);
            swap(x, i);
        }

    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Permutation().permutation("aab")));
    }
}
