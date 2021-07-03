import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author :Xuan
 * @date :Create in 2021/4/28 10:58
 * @description 剑指 Offer 58 - I. 翻转单词顺序 简单
 */
public class ReverseWords {
    /*
    输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
    为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
    示例 1：
    输入: "the sky is blue"
    输出: "blue is sky the"
    示例 2：
    输入: "  hello world!  "
    输出: "world! hello"
    解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
    示例 3：
    输入: "a good   example"
    输出: "example good a"
    解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
    说明：
    无空格字符构成一个单词。
    输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
    如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
    注意：本题与主站 151 题相同：https://leetcode-cn.com/problems/reverse-words-in-a-string/
    注意：此题对比原题有改动
     */
    public String reverseWords(String s) {
        if (s == null || s.length() == 0)
            return s;
        char[] str = s.toCharArray();
        StringBuilder sb = new StringBuilder(str.length);
        int left = str.length - 1;
        while (left >= 0 && str[left] == ' ')
            left--;
        int right = left + 1;
        while (left >= 0){
            while (left >= 0 && str[left] != ' ')
                left--;
            left++;
            sb.append(str,left,right - left);
            left--;
            while (left >= 0 && str[left] == ' ')
                left--;
            if (left < 0)
                break;
            right = left + 1;
            sb.append(' ');
        }
        return sb.toString();
    }


    public String reverseWords1(String s) {
        return Arrays.stream(s.trim().split(" ")).filter((str) -> !"".equals(str))
                .collect(Collector.of(StringBuilder::new,
                        (sb, o) -> sb.insert(0, o + " "),
                        (r1, r2) -> r1.insert(0, r2),
                        StringBuilder::toString)).trim();
    }
    public static void main(String[] args) {
        String s = "  hello   world!  ";
        System.out.println(new ReverseWords().reverseWords1(s));
    }
}
