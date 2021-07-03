import java.util.*;

/**
 * @author :Xuan
 * @date :Create in 2021/1/5 21:06
 * @description 39. 组合总和  中等
 * @update
 */
public class CombinationSum {
    /*
        给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
    candidates 中的数字可以无限制重复被选取。
    说明：
    所有数字（包括 target）都是正整数。
    解集不能包含重复的组合。 
    示例 1：
    输入：candidates = [2,3,6,7], target = 7,
    所求解集为：
    [
      [7],
      [2,2,3]
    ]
    示例 2：
    输入：candidates = [2,3,5], target = 8,
    所求解集为：
    [
      [2,2,2,2],
      [2,3,3],
      [3,5]
    ]
    提示：
    1 <= candidates.length <= 30
    1 <= candidates[i] <= 200
    candidate 中的每个元素都是独一无二的。
    1 <= target <= 500
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/combination-sum
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[] candidates = new int[]{1};
        int target = 1;
        System.out.println(new CombinationSum().combinationSum2(candidates, target));
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(candidates);
        findSum(candidates, target, 0, new ArrayList<>(), results);
        return results;
    }
    private void findSum(int[] candidates, int target, int idx, List<Integer> list, List<List<Integer>> results) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            results.add(new ArrayList<>(list));
            return;
        }
        for (int i = idx; i < candidates.length; ++i) {
            int t = target - candidates[i];
            if (t < 0) {
                break;
            }
            list.add(candidates[i]);
            findSum(candidates, t, i, list, results);
            list.remove(list.size() - 1);
        }
    }
    //动态规划


    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Map<Integer,Set<List<Integer>>> map = new HashMap<>();
        Arrays.sort(candidates);
        int len = candidates.length;
        for(int i = 1;i <= target;i++){
            map.put(i,new HashSet<>());
            for(int j = 0;j < len && candidates[j] <= target;j++){
                if(i == candidates[j]){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    map.get(i).add(temp);
                }else if(i > candidates[j]){
                    int key = i-candidates[j];
                    for (List<Integer> integers : map.get(key)) {
                        List<Integer> tempList = new ArrayList<>(integers);
                        tempList.add(candidates[j]);
                        Collections.sort(tempList);
                        map.get(i).add(tempList);
                    }
                }
            }
        }
        return new ArrayList<>(map.get(target));
    }
}
