/**
 * @author :Xuan
 * @date :Create in 2021/1/23 20:49
 * @description 69. x 的平方根 简单
 * @update
 */
public class SqrtX {
    /*
        实现 int sqrt(int x) 函数。
    计算并返回 x 的平方根，其中 x 是非负整数。
    由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
    示例 1:
    输入: 4
    输出: 2
    示例 2:
    输入: 8
    输出: 2
    说明: 8 的平方根是 2.82842...,
         由于返回类型是整数，小数部分将被舍去。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/sqrtx
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int x = 1978959248;
        System.out.println(new SqrtX().mySqrt2(x));
        System.out.println(new SqrtX().mySqrt(x));
    }
    public int mySqrt(int x) {
        return (int) Math.sqrt(x);
    }
    //二分
    public int mySqrt2(int x) {
        if (x == 0)
            return 0;
        if (x < 4)
            return 1;
        int a = 65536;
        int left = x >> 1;
        while (left >= a || left * left > x || left * left <= 0)
            left >>= 1;
        int right = (left << 1) + 1;
        int ans = 0;
        while (left < right){
            ans = (left + right) >> 1;
            if (ans * ans == x)
                return ans;
            if (ans * ans < x && ans * ans > 0)
                left = ans + 1;
            else right = ans;
        }
        return ans * ans > x ? ans - 1 : ans;
    }
    //牛顿迭代
    public int mySqrt3(int x) {
        long a = x;
        while (a * a > x) {
            a = (a + x / a) >> 1;
        }
        return (int) a;
    }

}
