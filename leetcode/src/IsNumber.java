import java.util.HashMap;
import java.util.Map;

/**
 * @author :Xuan
 * @date :Create in 2021/4/2 10:31
 * @description 剑指 Offer 20. 表示数值的字符串 中等
 */
public class IsNumber {
    /*
        请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
    例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，
    但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        String s = "2e0";
        System.out.println(new IsNumber().isNumber(s));
    }
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0)
            return false;

        char[] num = s.toCharArray();
        int len = num.length;
        int index = 0;

        while (index < len && num[index] == ' ')
            index++;
        boolean hasNum = false;
        boolean hasPoint = false;
        boolean hasE = false;
        boolean hasSign = false;

        index--;
        while (++index < len){

            if (checkNum(num[index])){
                hasNum = true;
                continue;
            }

            if (num[index] == ' '){
                break;
            }

            if (num[index] == '+' || num[index] == '-'){
                if (hasSign || hasNum || hasPoint)
                    return false;
                hasSign = true;
                continue;
            }

            if (num[index] == '.'){
                if (hasPoint || hasE)
                    return false;
                hasPoint = true;
                continue;
            }

            if (num[index] == 'e' || num[index] == 'E'){
                if (hasE || !hasNum || index == len - 1)
                    return false;
                hasE = true;
                hasNum = false;
                hasSign = false;
                hasPoint = false;
                continue;
            }

            return false;
        }

        while (index < len){
            if (num[index++] != ' ')
                return false;
        }
        return hasNum && index == len;
    }
    private boolean checkNum(char c){
        return '0' <= c && c <= '9';
    }

}
