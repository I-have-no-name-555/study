/**
 * @author :Xuan
 * @date :Create in 2021/4/14 10:44
 * @description 剑指 Offer 43. 1～n 整数中 1 出现的次数 困难
 */
public class CountDigitOne {
    /*
        输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
    例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
    示例 1：
    输入：n = 12
    输出：5
    示例 2：
    输入：n = 13
    输出：6
     
    限制：
    1 <= n < 2^31
    注意：本题与主站 233 题相同：https://leetcode-cn.com/problems/number-of-digit-one/
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int countDigitOne(int n) {
        if (n == 0)
            return 0;
        int digit = 1, times = 0;
        int high = n / 10, cur = n % 10, low = 0;
        while(high != 0 || cur != 0) {
            if(cur == 0)
                times += high * digit;
            else if(cur == 1)
                times += high * digit + low + 1;
            else times += (high + 1) * digit;
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return times;
    }

    public static void main(String[] args) {
        System.out.println(new CountDigitOne().countDigitOne(20));
    }

}
