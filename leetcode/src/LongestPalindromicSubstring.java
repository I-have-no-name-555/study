import sun.security.util.Length;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2020/12/16 16:53
 * @description 第五题 最长回文子串 中等
 * @update
 */
public class LongestPalindromicSubstring {
    /*
        给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
    示例 1：
    输入: "babad"
    输出: "bab"
    注意: "aba" 也是一个有效答案。
    示例 2：
    输入: "cbbd"
    输出: "bb"
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/longest-palindromic-substring
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        String s = "babad";
        LongestPalindromicSubstring test = new LongestPalindromicSubstring();
        System.out.println(test.longestPalindrome1(s));
        System.out.println(test.longestPalindrome2(s));
    }
    //动态规划
    public String longestPalindrome1(String s){
        int n = s.length();
        int maxLen = 0;
        int currentLen = 0;
        int start = 0;
        boolean[][] isPalindrome = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
        }
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                if (isPalindrome[i][j] = s.charAt(i) == s.charAt(j)
                        && (j - i < 3 || isPalindrome[i + 1][j - 1])){
                    if ((currentLen = j - i + 1) > maxLen){
                        start = i;
                        maxLen = currentLen;
                    }
                }
            }
        }
        return s.substring(start,maxLen);
    }
    //中心扩散
    public String longestPalindrome2(String s) {
        int length = s.length();
        if(length==1)
            return s;
        int maxLen = 0;
        String s1;
        String s2;
        String ss;
        String res="";
        for(int i = 0;i<length;i++){
            s1=centerSpread(s,i,i);
            s2=centerSpread(s,i,i+1);
            ss = s1.length()>s2.length()?s1:s2;
            res = res.length()>ss.length()?res:ss;
        }
        return res;
    }
    private String centerSpread(String s,int left,int right){
        int len = s.length();
        while(left!=-1&&right!=len){
            if(s.charAt(left)!=s.charAt(right))
                break;
            left--;
            right++;
        }
        return s.substring(left+1,right);
    }
}
