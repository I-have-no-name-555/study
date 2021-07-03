import java.util.HashMap;

/**
 * @author :Xuan
 * @date :Create in 2020/12/13 17:51
 * @description 第三题 无重复字符的最长子串 中等
 * @update
 */
public class LongestSubstringWithoutRepeatingCharacters {
    /*
        给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
    示例 1:
    输入: s = "abcabcbb"
    输出: 3
    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
    示例 2:
    输入: s = "bbbbb"
    输出: 1
    解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
    示例 3:
    输入: s = "pwwkew"
    输出: 3
    解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
         请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
    示例 4:
    输入: s = ""
    输出: 0
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //滑动窗口
    public int lengthOfLongestSubstring1(String s){
        int len = s.length();
        int start = 0;
        int end = 0;
        int index;
        int maxLen = 0;
        while (end != len){
            if ((index = s.substring(start,end + 1).indexOf((int)s.charAt(start),0)) > 0)
                start = index;
            else
                end++;
            maxLen = Math.max(maxLen,end - start);
        }
        return maxLen;
    }


    //HashMap优化
    public int lengthOfLongestSubstring2(String s) {
        int strLen = s.length();
        if(strLen==0||strLen==1)
            return strLen;
        int maxLen  = 0;
        HashMap<Character,Integer> map = new HashMap<>(s.length());
        int left = 0;
        int right = 0;
        while(right != strLen){
            if(map.containsKey(s.charAt(right))){
                left = Math.max(map.get(s.charAt(right))+1,left);

            }
            map.put(s.charAt(right),right);
            right++;
            maxLen = Math.max(maxLen,right-left);
        }
        return maxLen;
    }

    //桶优化
    //限制很大，不如HashMap好用，理解一下思想
    public int lengthOfLongestSubstring3(String s){
        //假设只有ascll码
        int len = s.length();
        int start = 0;
        int end = 0;
        int length = 0;
        int maxLen = 0;
        int[] ascll = new int[128];
        char current;
        while (end != len){
            current = s.charAt(end);
            if(ascll[(int)current] >= start - 1){
                start = ascll[(int)current];
                length = end - start;
            }
            ascll[(int)current] = end - 1;
            end++;
            length++;
            maxLen = Math.max(maxLen,length);
        }
        return maxLen;
    }
}
