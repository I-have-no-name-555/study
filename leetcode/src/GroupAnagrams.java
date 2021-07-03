import java.util.*;
import java.util.stream.Collectors;

/**
 * @author :Xuan
 * @date :Create in 2021/1/12 16:43
 * @description 49. 字母异位词分组 中等
 * @update
 */
public class GroupAnagrams {
    /*
        给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
    示例:
    输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
    输出:
    [
      ["ate","eat","tea"],
      ["nat","tan"],
      ["bat"]
    ]
    说明：
    所有输入均为小写字母。
    不考虑答案输出的顺序。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/group-anagrams
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(new GroupAnagrams().groupAnagrams(strs));
        System.out.println(new GroupAnagrams().groupAnagrams5(strs));

    }

    //暴力
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Set<Map<Character, Integer>> stringSets = new HashSet<>();
        Map<Map<Character, Integer>, List<String>> listMap = new HashMap<>();
        for (String str : strs) {
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < str.length(); i++) {
                if (map.containsKey(str.charAt(i)))
                    map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
                else
                    map.put(str.charAt(i), 1);
            }
            if (stringSets.contains(map))
                listMap.get(map).add(str);
            else {
                List<String> list = new ArrayList<>();
                listMap.put(map, list);
                list.add(str);
                stringSets.add(map);
                ans.add(list);
            }
        }
        return ans;
    }

    //排序
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    //stream api
    public List<List<String>> groupAnagrams3(String[] strs) {
        return new ArrayList<>(Arrays.stream(strs).collect(Collectors
                .groupingBy(str -> str.chars().sorted()
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint
                                , StringBuilder::append).toString())).values());
    }

    //计数
    public List<List<String>> groupAnagrams4(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] counts = new int[26];
            int len = str.length();
            for (int i = 0; i < len; i++)
                counts[str.charAt(i) - 'a']++;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    //算术基本定理——一个正整数被唯一分解成质因数之积
    public List<List<String>> groupAnagrams5(String[] strs) {
        Map<Long, List<String>> map = new HashMap<>();
        int[] prime = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43,
                47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        for (String str : strs) {
            long product = 1;
            for (int j = 0; j < str.length(); j++)
                product *= prime[str.charAt(j) - 'a'];
            List<String> list = map.getOrDefault(product, new ArrayList<>());
            list.add(str);
            map.put(product, list);
        }
        return new ArrayList<>(map.values());
    }

}
