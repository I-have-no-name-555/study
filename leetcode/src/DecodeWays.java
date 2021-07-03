/**
 * @author :Xuan
 * @date :Create in 2021/2/7 15:11
 * @description 91. 解码方法 中等
 * @update
 */
public class DecodeWays {
    /*
        一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
    'A' -> 1
    'B' -> 2
    ...
    'Z' -> 26
    要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，
    "111" 可以将 "1" 中的每个 "1" 映射为 "A" ，从而得到 "AAA" ，
    或者可以将 "11" 和 "1"（分别为 "K" 和 "A" ）映射为 "KA" 。
    注意，"06" 不能映射为 "F" ，因为 "6" 和 "06" 不同。
    给你一个只含数字的 非空 字符串 num ，请计算并返回 解码 方法的 总数 。
    题目数据保证答案肯定是一个 32 位 的整数。
    示例 1：
    输入：s = "12"
    输出：2
    解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
    示例 2：
    输入：s = "226"
    输出：3
    解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
    示例 3：
    输入：s = "0"
    输出：0
    解释：没有字符映射到以 0 开头的数字。含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
    示例 4：
    输入：s = "06"
    输出：0
    解释："06" 不能映射到 "F" ，因为字符串开头的 0 无法指向一个有效的字符。 
    提示：
    1 <= s.length <= 100
    s 只包含数字，并且可能包含前导零。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/decode-ways
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        String s = "06";
        System.out.println(new DecodeWays().numDecodings(s));

    }
    //re了，四个测试用例没问题，应该对吧
    public int numDecodings(String s) {
        return numDecodings(s,0);
    }
    private int numDecodings(String s,int startIndex) {
        int left = 0;
        int right = 0;
        if (startIndex == s.length() - 1)
            return s.charAt(startIndex) == '0' ? 0 : 1;
        if (startIndex == s.length())
            return 1;
        if (s.charAt(startIndex) != '0'){
            left = numDecodings(s,startIndex + 1);
            if (s.length() - startIndex >= 2 && Integer.parseInt(s.substring(startIndex,startIndex + 2)) <= 26)
                right = numDecodings(s,startIndex + 2);
        }
        return left + right;
    }
    //优化
    public int numDecodings2(String s) {
        char[] chars = s.toCharArray();
        if (chars[0] == '0')
            return 0;
        int pre = 1, cur = 1;
        int len = chars.length;
        for (int i = 1; i < len; i++) {
            int tmp = cur;
            if (chars[i] == '0')
                if (chars[i - 1] == '1' || chars[i - 1] == '2')
                    cur = pre;
                else return 0;
            else if (chars[i - 1] == '1' || (chars[i - 1] == '2'
                    && chars[i] >= '1' && chars[i] <= '6'))
                cur += pre;
            pre = tmp;
        }
        return cur;
    }

}
