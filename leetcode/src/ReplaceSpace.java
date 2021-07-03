/**
 * @author :Xuan
 * @date :Create in 2021/3/27 16:15
 * @description 剑指 Offer 05. 替换空格 简单
 */
public class ReplaceSpace {
    /*
        请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
    示例 1：
    输入：s = "We are happy."
    输出："We%20are%20happy."
    限制：
    0 <= s 的长度 <= 10000
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public String replaceSpace2(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char c : chars){
            if (c != ' ')
                sb.append(c);
            else sb.append("%20");
        }
        return sb.toString();
    }
    public String replaceSpace(String s) {
        return s.replace(" ","%20");
    }
}
