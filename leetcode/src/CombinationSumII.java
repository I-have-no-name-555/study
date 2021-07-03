import java.util.*;

/**
 * @author :Xuan
 * @date :Create in 2021/1/7 10:32
 * @description 40. 组合总和 II 中等
 * @update
 */
public class CombinationSumII {
    /*
        给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
    candidates 中的每个数字在每个组合中只能使用一次。
    说明：
    所有数字（包括目标数）都是正整数。
    解集不能包含重复的组合。 
    示例 1:
    输入: candidates = [10,1,2,7,6,1,5], target = 8,
    所求解集为:
    [
      [1, 7],
      [1, 2, 5],
      [2, 6],
      [1, 1, 6]
    ]
    示例 2:
    输入: candidates = [2,5,2,1,2], target = 5,
    所求解集为:
    [
      [1,2,2],
      [5]
    ]
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/combination-sum-ii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[] candidates = new int[]{2,5,2,1,2};
        int target = 5;
        System.out.println(new CombinationSumII().combinationSum2(candidates, target));
    }
    //回溯
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0)
            return ANS;
        Arrays.sort(candidates);
        dfs(candidates,target,0,new ArrayList<>());
        return ANS;
    }
    private final List<List<Integer>> ANS = new ArrayList<>();
    private void dfs(int[] candidates , int target , int begin , List<Integer> path){
        if (target == 0) {
            ANS.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < candidates.length; ++i) {
            int t = target - candidates[i];
            if (t < 0) {
                break;
            }
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.add(candidates[i]);
            dfs(candidates, t, i + 1, path);
            path.remove(path.size() - 1);
        }
    }

}
