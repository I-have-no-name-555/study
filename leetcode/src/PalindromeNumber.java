/**
 * @author :Xuan
 * @date :Create in 2020/12/18 16:46
 * @description 第九题 回文数 简单
 * @update
 */
public class PalindromeNumber {
    /*
        判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
    示例 1:
    输入: 121
    输出: true
    示例 2:
    输入: -121
    输出: false
    解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
    示例 3:
    输入: 10
    输出: false
    解释: 从右向左读, 为 01 。因此它不是一个回文数。
    进阶:
    你能不将整数转为字符串来解决这个问题吗？
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/palindrome-number
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        PalindromeNumber test = new PalindromeNumber();
        int x = 10;
        System.out.println(test.isPalindrome1(x));
        System.out.println(test.isPalindrome2(x));
        System.out.println(test.isPalindrome3(x));
    }

    public boolean isPalindrome1(int x) {
        if (x < 0)
            return false;
        if (x == 0)
            return true;
        int count = 0;
        int xCopy = x;
        while (xCopy != 0) {
            xCopy /= 10;
            count++;
        }
        int left = 0;
        int right = 0;
        while (count > 0) {
            left = (int) (x / Math.pow(10, count - 1));
            right = x % 10;
            if (left != right)
                return false;
            x -= left * Math.pow(10, count - 1);
            x = (x - right) / 10;
            count -= 2;
        }
        return true;
    }

    //优化
    public boolean isPalindrome2(int x) {
        if (x < 0) return false;
        int div = 1;
        while (x / div >= 10)
            div *= 10;
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    //再优化
    public boolean isPalindrome3(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) //负数和末尾为0的数不可能是回文数
            return false;
        int revertedNum = 0;
        while (x > revertedNum) {
            revertedNum = revertedNum * 10 + x % 10;
            x /= 10;
        }
        //     x位数为偶数          x位数为奇数
        return x == revertedNum || x == revertedNum / 10;
    }


}
