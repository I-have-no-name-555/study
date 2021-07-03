/**
 * @author :Xuan
 * @date :Create in 2021/2/22 18:38
 * @description 125. 验证回文串 简单
 * @update
 */
public class ValidPalindrome {
    /*
        给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
    说明：本题中，我们将空字符串定义为有效的回文串。
    示例 1:
    输入: "A man, a plan, a canal: Panama"
    输出: true
    示例 2:
    输入: "race a car"
    输出: false
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/valid-palindrome
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0)
            return true;
        s = s.toLowerCase();
        int left = 0;
        int right = s.length() - 1;
        while (left < right){
            while (left < right && !check(s.charAt(left)))
                left++;
            while (left < right && !check(s.charAt(right)))
                right--;
            if (left > right || s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }
    private boolean check(char c){
        return (c <= 'z' && c >= 'a') || (c <= '9' && c >= '0');
    }
}
