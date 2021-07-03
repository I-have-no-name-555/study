import java.util.*;
import java.util.stream.Collectors;

/**
 * @author :Xuan
 * @date :Create in 2021/1/10 16:04
 * @description 47. 全排列 II 中等
 * @update
 */
public class PermutationsII {
    /*
        给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
    示例 1：
    输入：nums = [1,1,2]
    输出：
    [[1,1,2],
     [1,2,1],
     [2,1,1]]
    示例 2：
    输入：nums = [1,2,3]
    输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
    提示：
    1 <= nums.length <= 8
    -10 <= nums[i] <= 10
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/permutations-ii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public List<List<Integer>> permuteUnique(int[] nums) {
        return new Permutations().permute2(nums).stream().distinct().collect(Collectors.toList());
    }
    public List<List<Integer>> permuteUnique2(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0)
            return res;
        Arrays.sort(nums);
        boolean[] used = new boolean[len];
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(nums, len, 0, used, path, res);
        return res;
    }
    private void dfs(int[] nums, int len, int depth, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; ++i) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, len, depth + 1, used, path, res);
            used[i] = false;
            path.removeLast();
        }
    }
}
