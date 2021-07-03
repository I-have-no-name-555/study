/**
 * @author :Xuan
 * @date :Create in 2021/1/8 15:35
 * @description 43. 字符串相乘 中等
 * @update
 */
public class MultiplyStrings {
    /*
    给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
    示例 1:
    输入: num1 = "2", num2 = "3"
    输出: "6"
    示例 2:
    输入: num1 = "123", num2 = "456"
    输出: "56088"
    说明：
    num1 和 num2 的长度小于110。
    num1 和 num2 只包含数字 0-9。
    num1 和 num2 均不以零开头，除非是数字 0 本身。
    不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
     */
    public static void main(String[] args) {
        String num1 = "2";
        String num2 = "4";
        System.out.println(new MultiplyStrings().multiply(num1, num2));
    }

    public String multiply(String num1, String num2) {
        if (num1.length() > num2.length())
            return multiply(num2,num1);
        if (num1.charAt(0) == '0')
            return "0";
        StringBuilder ans = new StringBuilder("0");
        for (int i = num1.length() - 1; i >= 0; i--) {
            int a = num1.charAt(i) - '0';
            int c = 0;
            StringBuilder sb = new StringBuilder(num1.length() - i + num2.length());
            for (int j = 0; j < num1.length() - 1 - i; j++) {
                sb.append('0');
            }
            for (int j = num2.length() - 1; j >= 0; j--) {
                int b = num2.charAt(j) - '0';
                c = c + a * b;
                sb.append((char)('0' + (c % 10)));
                c /= 10;
            }
            if (c != 0)
                sb.append((char)('0' + c));
            ans = add(ans,sb.reverse());
        }

        return ans.toString();
    }
    private StringBuilder add(StringBuilder num1,StringBuilder num2){
        if (num1.length() > num2.length())
            return add(num2,num1);
        StringBuilder sb = new StringBuilder(num2.length() + 1);

        int sum = 0;
        for (int i = 0; i < num1.length(); i++) {
            int a = num1.charAt(num1.length() - 1 - i) - '0';
            int b = num2.charAt(num2.length() - i - 1) - '0';
            sum = sum + a + b;
            sb.append((char)('0' + (sum % 10)));
            sum /= 10;
        }
        for (int i = num2.length() - num1.length() - 1; i >= 0; i--) {
            int a = num2.charAt(i) - '0';
            sum += a;
            sb.append((char)('0' + (sum % 10)));
            sum /= 10;
        }
        if (sum != 0)
            sb.append((char)('0' + sum));
        sb.reverse();
        return sb;
    }
    //优化   num1索引为i与num2索引为j的两数之积在结果的索引i+j和i+j+1上
    public String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length();
        int n = num2.length();
        int[] ansArr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ansArr[i + j + 1] += x * y;
            }
        }
        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }
        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuilder ans = new StringBuilder();
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }
}
