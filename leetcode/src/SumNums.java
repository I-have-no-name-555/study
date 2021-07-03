/**
 * @author :Xuan
 * @date :Create in 2021/5/4 22:37
 * @description 剑指 Offer 64. 求1+2+…+n 中等
 */
public class SumNums {
    /*
        求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
    示例 1：
    输入: n = 3
    输出: 6
    示例 2：
    输入: n = 9
    输出: 45
    限制：
    1 <= n <= 10000
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/qiu-12n-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int sumNums(int n) {
        boolean x = n > 1 && (n += sumNums(n - 1)) > 0;
        return n;
    }

    public static void main(String[] args) {
        int i = 7;
        System.out.println(new SumNums().sumNums(i));
        System.out.println((i * (i + 1)) >> 1);
    }
}
