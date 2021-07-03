import java.util.*;

/**
 * @author :Xuan
 * @date :Create in 2021/5/16 10:52
 * @description 187. 重复的DNA序列 中等
 */
public class RepeatedDnaSequences {
    /*
        所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，例如："ACGAATTCCG"。
    在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
    编写一个函数来找出所有目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。
    示例 1：
    输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
    输出：["AAAAACCCCC","CCCCCAAAAA"]
    示例 2：
    输入：s = "AAAAAAAAAAAAA"
    输出：["AAAAAAAAAA"]
    提示：
    0 <= s.length <= 105
    s[i] 为 'A'、'C'、'G' 或 'T'
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/repeated-dna-sequences
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<String> findRepeatedDnaSequences(String s) {
        if (s == null || s.length() <= 10)
            return new ArrayList<>();
        Set<String> set = new HashSet<>();
        Set<String> resSet = new HashSet<>();
        int len = s.length() - 10;
        for (int i = 0;i <= len;i++){
            String ss = s.substring(i,i + 10);
            if (set.contains(ss))
                resSet.add(ss);
            else set.add(ss);
        }
        return new ArrayList<>(resSet);
    }
    //题解的两种高级算法学了也没什么用，学个锤子
}
