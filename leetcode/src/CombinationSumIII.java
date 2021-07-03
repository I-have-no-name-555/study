import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/3/23 20:58
 * @description 216. 组合总和 III 中等
 */
public class CombinationSumIII {
    /*
        找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
    说明：
    所有数字都是正整数。
    解集不能包含重复的组合。 
    示例 1:
    输入: k = 3, n = 7
    输出: [[1,2,4]]
    示例 2:
    输入: k = 3, n = 9
    输出: [[1,2,6], [1,3,5], [2,3,4]]
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/combination-sum-iii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int k = 3;
        int n = 9;
        System.out.println(new CombinationSumIII().combinationSum3(k, n));
    }
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(k,n,0,1,0);
        return ans;
    }
    private void dfs(int k,int n,int curK,int start,int sum){
        if (curK == k){
            if (sum == n)
                ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < 10; i++) {
            if (sum + i > n)
                return;
            path.add(i);
            dfs(k,n,curK + 1,i + 1,sum + i);
            path.remove(path.size() - 1);
        }
    }
}
