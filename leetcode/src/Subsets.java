import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author :Xuan
 * @date :Create in 2021/1/28 16:04
 * @description 78. 子集 中等
 * @update
 */
public class Subsets {
    /*
        给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
    解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
    示例 1：
    输入：nums = [1,2,3]
    输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
    示例 2：
    输入：nums = [0]
    输出：[[],[0]]
    提示：
    1 <= nums.length <= 10
    -10 <= nums[i] <= 10
    nums 中的所有元素 互不相同
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/subsets
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //位图
    public List<List<Integer>> subsets(int[] nums) {
        int len = nums.length;
        List<List<Integer>> powerSet = new ArrayList<>(1 << len);
        int bitmapMaxNum = 2 << (len - 1);
        powerSet.add(new ArrayList<>());
        for (int i = 1; i < bitmapMaxNum; i++)
            powerSet.add(subset(nums, i));
        return powerSet;
    }
    private  List<Integer> subset(int[] set , int key){
        List<Integer> subset = new ArrayList<>();
        int len = set.length;
        for (int i = 0; i < len; i++) {
            if (((key >> i) & 1) == 1) { // 判断对应元素是否存在
                subset.add(set[i]);
            }
        }
        return subset;
    }
    //循环迭代
    public List<List<Integer>> subsets2(int[] nums) {
        int len = nums.length;
        List<List<Integer>> powerSet = new ArrayList<>((int)Math.pow(2,len));

        powerSet.add(new ArrayList<>());
        for (int num : nums) {
            int size = powerSet.size();
            for (int j = 0; j < size; j++) {
                List<Integer> list = new ArrayList<>(powerSet.get(j));
                list.add(num);
                powerSet.add(list);
            }
        }
        return powerSet;
    }
    //回溯
    List<List<Integer>> powerSet;
    List<Integer> path;
    int[] nums;
    int len;
    public List<List<Integer>> subsets3(int[] nums) {
        this.nums = nums;
        len = nums.length;
        powerSet = new ArrayList<>();
        path = new ArrayList<>(len);
        dfs(0);
        return powerSet;
    }
    private void dfs(int i){
        powerSet.add(new ArrayList<>(path));
        for (int j = i; j < len; j++) {
            path.add(nums[j]);
            dfs(j + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(new Subsets().subsets3(nums));
    }

}
