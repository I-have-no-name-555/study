/**
 * @author :Xuan
 * @date :Create in 2021/7/19 17:13
 * @description 371. 两整数之和 中等
 */
public class SumOfTwoIntegers {
    /*
        不使用运算符 + 和-，计算两整数a 、b之和。
    示例 1:
    输入: a = 1, b = 2
    输出: 3
    示例 2:
    输入: a = -2, b = 3
    输出: 1
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/sum-of-two-integers
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int getSum(int a, int b) {
        return Math.addExact(a,b);
    }

    public int getSum2(int a, int b) {
        int answer = a ^ b;
        int carry = (a & b) << 1;
        while (0 != carry) {
            answer = a ^ b;
            carry = (a & b) << 1;
            a = answer;
            b = carry;
        }
        return answer;
    }
}
