import java.util.ArrayList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/1/23 20:18
 * @description 67. 二进制求和 中等
 * @update
 */
public class AddBinary {
    /*
        给你两个二进制字符串，返回它们的和（用二进制表示）。
    输入为 非空 字符串且只包含数字 1 和 0。
    示例 1:
    输入: a = "11", b = "1"
    输出: "100"
    示例 2:
    输入: a = "1010", b = "1011"
    输出: "10101"
    提示：
    每个字符串仅由字符 '0' 或 '1' 组成。
    1 <= a.length, b.length <= 10^4
    字符串如果不是 "0" ，就都不含前导零。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/add-binary
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        String a = "11";
        String b = "1";
        System.out.println(new AddBinary().addBinary2(a,b));
    }
    public String addBinary(String a, String b) {
        int flag = 0;
        int m = a.length();
        int n = b.length();
        if (m > n)
            return addBinary(b,a);
        StringBuilder sb = new StringBuilder(n + 1);
        for (int i = 1; i <= m; i++) {
            switch (a.charAt(m - i) + b.charAt(n - i) + flag - '0' - '0'){
                case 0:
                    sb.append('0');
                    break;
                case 1:
                    sb.append('1');
                    flag = 0;
                    break;
                case 2:
                    sb.append('0');
                    flag = 1;
                    break;
                case 3:
                    sb.append(1);
                    flag = 1;
            }
        }
        for (int i = n - m - 1; i >= 0; i--) {
            switch (b.charAt(i) - '0' + flag){
                case 0:
                    sb.append('0');
                    break;
                case 1:
                    sb.append('1');
                    flag = 0;
                    break;
                case 2:
                    sb.append('0');
                    flag = 1;
            }
        }
        if (flag == 1)
            sb.append('1');
        return sb.reverse().toString();
    }
    public String addBinary2(String a, String b) {
        if(a.length() == 0 || b.length() == 0)
            return a.length() == 0 ? b : a;
        int sum = 0;
        StringBuilder s = new StringBuilder(Math.max(a.length(),b.length()) + 1);
        for (int m = a.length()-1, n = b.length()-1; m >= 0 || n >= 0 ; sum >>= 1) {
            if(m>=0){
                sum+=a.charAt(m)-'0';
                m--;
            }
            if(n>=0){
                sum+=b.charAt(n)-'0';
                n--;
            }
            s.append(sum%2);
        }
        if(sum == 1)
            s.append(1);
        return s.reverse().toString();
    }
}
