import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author :Xuan
 * @date :Create in 2020/12/27 16:48
 * @description 20.有效的括号 简单
 * @update
 */
public class ValidParentheses {
    /*
    给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
    有效字符串需满足：
    左括号必须用相同类型的右括号闭合。
    左括号必须以正确的顺序闭合。
    注意空字符串可被认为是有效字符串。
    示例 1:
    输入: "()"
    输出: true
    示例 2:
    输入: "()[]{}"
    输出: true
    示例 3:
    输入: "(]"
    输出: false
    示例 4:
    输入: "([)]"
    输出: false
    示例 5:
    输入: "{[]}"
    输出: true
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/valid-parentheses
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        ValidParentheses test = new ValidParentheses();
        String s = "(]";
        System.out.println(test.isValid(s));
    }
    public boolean isValid(String s) {
        int len = s.length();
        if (len == 0)
            return true;
        if ((len & 1) == 1)
            return false;
        Stack<Character> stack = new Stack<>();
        Map<Character,Character> map = new HashMap<>(3);
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');
        char c;
        for (int i = 0; i < len; i++) {
            c = s.charAt(i);
            if (map.containsKey(c))
                stack.push(c);
            else if (stack.isEmpty())
                return false;
            else if (map.get(stack.pop()) != c)
                return false;
        }

        return stack.isEmpty();
    }
}
