import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2020/12/24 10:47
 * @description 17 电话号码的字符组合 中等
 * @update
 */
public class LetterCombinationsOfAPhoneNumber {
    /*
        给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
    给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
    示例:
    输入："23"
    输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    说明:
    尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber test = new LetterCombinationsOfAPhoneNumber();
        String digits = "23";
        System.out.println(test.letterCombinations1(digits));
        System.out.println(test.letterCombinations2(digits));
    }

    //回溯
    private static final String[] letter_map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    List<String> res = new ArrayList<>();

    public List<String> letterCombinations1(String digits) {
        if (digits == null || digits.length() == 0)
            return res;
        iterStr(digits, new StringBuilder(), 0);
        return res;
    }

    void iterStr(String str, StringBuilder letter, int index) {
        if (index == str.length()) {
            res.add(letter.toString());
            return;
        }
        char c = str.charAt(index);
        int pos = c - '2';
        String map_string = letter_map[pos];
        for (int i = 0; i < map_string.length(); i++) {
            letter.append(map_string.charAt(i));
            iterStr(str, letter, index + 1);
            letter.deleteCharAt(letter.length() - 1);
        }
    }

    //队列
    public List<String> letterCombinations2(String digits) {
        if (digits == null || digits.length() == 0) {
            return res;
        }
        List<String> res = new ArrayList<>();
        res.add("");
        int len = digits.length();
        int size;
        for (int i = 0; i < len; i++) {
            String letters = letter_map[digits.charAt(i) - '2'];size = res.size();
            for (int j = 0; j < size; j++) {
                String tmp = res.remove(0);
                for (int k = 0; k < letters.length(); k++) {
                    res.add(tmp + letters.charAt(k));
                }
            }
        }
        return res;
    }

}
