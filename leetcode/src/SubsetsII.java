import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author :Xuan
 * @date :Create in 2021/2/5 22:12
 * @description 90. 子集 II 中等
 * @update
 */
public class SubsetsII {
    /*
        给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
    说明：解集不能包含重复的子集。
    示例:
    输入: [1,2,2]
    输出:
    [
      [2],
      [1],
      [1,2,2],
      [2,2],
      [1,2],
      []
    ]
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/subsets-ii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        return new Subsets().subsets(nums).stream().distinct().collect(Collectors.toList());
    }

    List<List<Integer>> powerSet;
    List<Integer> path;
    int[] nums;
    int len;

    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        Arrays.sort(nums);
        this.nums = nums;
        len = nums.length;
        powerSet = new ArrayList<>();
        path = new ArrayList<>(len);
        dfs(0);
        return powerSet;
    }

    private void dfs(int i) {
        powerSet.add(new ArrayList<>(path));
        for (int j = i; j < len; j++) {
            if (j > i && nums[j] == nums[j - 1])
                continue;
            path.add(nums[j]);
            dfs(j + 1);
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup3(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        Arrays.sort(nums);
        int start = 1;
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> ans_tmp = new ArrayList<>();
            for (int j = 0; j < ans.size(); j++) {
                if (i > 0 && nums[i] == nums[i - 1] && j < start) {
                    continue;
                }
                List<Integer> tmp = new ArrayList<>(ans.get(j));
                tmp.add(nums[i]);
                ans_tmp.add(tmp);
            }

            start = ans.size(); //更新新解的开始位置
            ans.addAll(ans_tmp);
        }
        return ans;
    }

    public List<List<Integer>> subsetsWithDup4(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int dupCount = 0;
            while (((i + 1) < nums.length) && nums[i + 1] == nums[i]) {
                dupCount++;
                i++;
            }
            int prevNum = result.size();
            for (int j = 0; j < prevNum; j++) {
                List<Integer> element = new ArrayList<>(result.get(j));
                for (int t = 0; t <= dupCount; t++) {
                    element.add(nums[i]);
                    result.add(new ArrayList<>(element));
                }
            }
        }
        return result;
    }

    public List<List<Integer>> subsetsWithDup5(int[] nums) {
        Arrays.sort(nums);
        int n = this.nums.length;
        List<List<Integer>> lists = new ArrayList<>();
        int subsetNum = 1 << n;
        for (int i = 0; i < subsetNum; i++) {
            List<Integer> list = new ArrayList<>();
            boolean illegal = false;
            for (int j = 0; j < n; j++) {
                if ((i >> j & 1) == 1) {
                    if (j > 0 && nums[j] == nums[j - 1] && (i >> (j - 1) & 1) == 0) {
                        illegal = true;
                        break;
                    } else {
                        list.add(nums[j]);
                    }
                }
            }
            if (!illegal) {
                lists.add(list);
            }
        }
        return lists;
    }
}
