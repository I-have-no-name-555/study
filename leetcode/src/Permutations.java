import java.util.*;

/**
 * @author :Xuan
 * @date :Create in 2021/1/9 15:28
 * @description 46. 全排列 中等
 * @update
 */
public class Permutations {
    /*
        给定一个 没有重复 数字的序列，返回其所有可能的全排列。
    示例:
    输入: [1,2,3]
    输出:
    [
      [1,2,3],
      [1,3,2],
      [2,1,3],
      [2,3,1],
      [3,1,2],
      [3,2,1]
    ]
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/permutations
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(new Permutations().permute(nums));
        System.out.println(new Permutations().permute2(nums));
    }

    //动态规划
    public List<List<Integer>> permute(int[] nums) {
        Map<Integer,List<List<Integer>>> map = new HashMap<>(nums.length);
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new LinkedList<>();
        //初始化
        list.add(nums[0]);
        lists.add(list);
        map.put(0,lists);
        //动态规划
        for (int i = 1; i < nums.length; i++) {
            lists = new ArrayList<>();
            for (int j = 0; j < map.get(i - 1).size(); j++) {
                for (int k = 0; k <= map.get(i - 1).get(j).size(); k++) {
                    list = new LinkedList<>(map.get(i - 1).get(j));
                    list.add(k,nums[i]);
                    lists.add(list);
                }
            }
            map.put(i,lists);
        }
        return map.get(nums.length - 1);
    }
    //回溯
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        backtrack(nums.length, list, res, 0);
        return res;
    }

    public void backtrack(int n, List<Integer> list, List<List<Integer>> ans, int first) {
        if (first == n) {
            ans.add(new ArrayList<>(list));
        }
        for (int i = first; i < n; i++) {
            Collections.swap(list, first, i);
            backtrack(n, list, ans, first + 1);
            Collections.swap(list, first, i);
        }
    }
}
