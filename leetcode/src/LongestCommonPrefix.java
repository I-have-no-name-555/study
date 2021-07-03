import sun.security.util.Length;

/**
 * @author :Xuan
 * @date :Create in 2020/12/21 9:41
 * @description 第14题 最长公共前缀 简单
 * @update
 */
public class LongestCommonPrefix {
    /*
        编写一个函数来查找字符串数组中的最长公共前缀。
    如果不存在公共前缀，返回空字符串 ""。
    示例 1:
    输入: ["flower","flow","flight"]
    输出: "fl"
    示例 2:
    输入: ["dog","racecar","car"]
    输出: ""
    解释: 输入不存在公共前缀。
    说明:
    所有输入只包含小写字母 a-z 。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/longest-common-prefix
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        LongestCommonPrefix test = new LongestCommonPrefix();
        String[] strs = new String[]{"flow","flower","fly"};
        System.out.println(test.longestCommonPrefix(strs));
    }
    //纵向扫描
    public String longestCommonPrefix(String[] strs) {
        int len = 0;
        int strsLen = strs.length;
        if (strsLen == 0)
            return "";
        String minStr = strs[0];
        int minLen = minStr.length();
        for (String s : strs) {
            if (s.length() < minLen) {
                minLen = s.length();
                minStr = s;
            }
        }
        char c;
        for (int i = 0; i < minLen; i++) {
            c = minStr.charAt(i);
            for (String str : strs) {
                if (str.charAt(i) != c)
                    return str.substring(0,len);
            }
            len++;
        }
        return strs[0].substring(0,len);
    }

}
