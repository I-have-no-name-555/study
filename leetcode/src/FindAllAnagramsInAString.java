import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/6/23 17:01
 * @description 438. 找到字符串中所有字母异位词 中等
 */
public class FindAllAnagramsInAString {
    /*
    给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，
    返回这些子串的起始索引。
    字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
    说明：
    字母异位词指字母相同，但排列不同的字符串。
    不考虑答案输出的顺序。
    示例 1:
    输入:
    s: "cbaebabacd" p: "abc"
    输出:
    [0, 6]
    解释:
    起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
    起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
     示例 2:
    输入:
    s: "abab" p: "ab"
    输出:
    [0, 1, 2]
    解释:
    起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
    起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
    起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || p == null){
            return res;
        }
        int n = s.length();
        int m = p.length();
        if(n < m) {
            return res;
        }
        int[] pCnt = new int[26];
        int[] sCnt = new int[26];
        for(int i = 0; i < m; i++){
            pCnt[p.charAt(i) - 'a']++;
            sCnt[s.charAt(i) - 'a']++;
        }
        if(Arrays.equals(sCnt, pCnt)){
            res.add(0);
        }
        for(int i = m; i < n; i++){
            sCnt[s.charAt(i - m) - 'a']--;
            sCnt[s.charAt(i) - 'a']++;
            if(Arrays.equals(sCnt, pCnt)){
                res.add(i - m + 1);
            }
        }
        return res;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s == null || p == null){
            return res;
        }
        int n = s.length();
        int m = p.length();
        if(n < m) {
            return res;
        }
        int[] pCnt = new int[26];
        int[] sCnt = new int[26];

        for(int i = 0; i < m; i++){
            pCnt[p.charAt(i) - 'a'] ++;
        }

        int left = 0;
        for(int right = 0; right < n; right++){
            int curRight = s.charAt(right) - 'a';
            sCnt[curRight]++;
            while(sCnt[curRight] > pCnt[curRight]){
                int curLeft = s.charAt(left) - 'a';
                sCnt[curLeft]--;
                left++;
            }
            if(right - left + 1 == m){
                res.add(left);
            }
        }
        return res;
    }


}
