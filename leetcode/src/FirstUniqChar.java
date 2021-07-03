/**
 * @author :Xuan
 * @date :Create in 2021/4/21 12:39
 * @description 剑指 Offer 50. 第一个只出现一次的字符    简单
 */
public class FirstUniqChar {
    /*
        在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
    示例:
    s = "abaccdeff"
    返回 "b"
    s = ""
    返回 " "
    限制：
    0 <= s 的长度 <= 50000
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public char firstUniqChar(String s) {
        if (s == null || s.length() == 0)
            return ' ';
        char[] str = s.toCharArray();
        int[] flags = new int[26];
        for (char c : str)
            flags[c - 'a']++;
        for (char c : str){
            if(flags[c - 'a'] == 1)
                return c;
        }
        return ' ';
    }
}
