import java.nio.channels.NonReadableChannelException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author :Xuan
 * @date :Create in 2020/12/25 14:40
 * @description 第18题 四数之和 中等
 * @update
 */
public class FourSum {
    /*
    给定一个包含 n 个整数的数组 nums 和一个目标值 target，
    判断 nums 中是否存在四个元素 a，b，c 和 d ，
    使得 a + b + c + d 的值与 target 相等？
    找出所有满足条件且不重复的四元组。
    注意：
    答案中不可以包含重复的四元组。
    示例：
    给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
    满足要求的四元组集合为：
    [
      [-1,  0, 0, 1],
      [-2, -1, 1, 2],
      [-2,  0, 0, 2]
    ]
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/4sum
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        FourSum test = new FourSum();
        int target = -7;
        int[] nums = new int[]{-1, -5, -5, -3, 2, 5, 0, 4};
        System.out.println(test.fourSum1(nums, target));
        System.out.println(test.fourSum2(nums, target));
    }

    //遍历转三数之和
    public List<List<Integer>> fourSum1(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return lists;
        }

        List<List<Integer>> currentLists;
        Arrays.sort(nums);

        int len = nums.length;
        for (int i = 0; i < len - 3; i++) {
            currentLists = threeSum(nums, i + 1, target - nums[i]);
            for (List<Integer> list : currentLists) {
                list.add(nums[i]);
            }
            lists.addAll(currentLists);
        }

        return lists.stream().distinct().collect(Collectors.toList());
    }

    public List<List<Integer>> threeSum(int[] nums, int start, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list;
        int len = nums.length;
        int left;
        int right;
        for (int i = start; i < len; i++) {
            left = i + 1;
            right = len - 1;

            if (i > start && nums[i] == nums[i - 1])
                continue;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] == target) {
                    list = new ArrayList<>(3);
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    lists.add(list);
                    while (left < right && nums[left] == nums[left + 1])
                        left++;
                    while (left < right && nums[right] == nums[right - 1])
                        right--;
                    left++;
                    right--;
                } else if (nums[i] + nums[left] + nums[right] < target)
                    left++;
                else
                    right--;
            }
        }
        return lists;
    }

    //对去重做了优化
    public List<List<Integer>> fourSum2(int[] nums, int target) {

        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            if (nums[i] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) continue;

            for (int j = i + 1; j < n - 2; j++) {
                if (j != i + 1 && nums[j] == nums[j - 1]) continue;
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                if (nums[i] + nums[j] + nums[n - 2] + nums[n - 1] < target) continue;

                int p = j + 1, q = n - 1;
                while (p < q) {
                    int sum = nums[i] + nums[j] + nums[p] + nums[q];
                    if (sum < target) p++;
                    else if (sum > target) q--;
                    else {
                        List<Integer> cur = new ArrayList<>();
                        cur.add(nums[i]);
                        cur.add(nums[j]);
                        cur.add(nums[p]);
                        cur.add(nums[q]);
                        res.add(cur);
                        p++;
                        q--;
                        while (p < q && nums[p] == nums[p - 1]) p++;
                        while (p < q && nums[q] == nums[q + 1]) q--;
                    }
                }
            }
        }
        return res;
    }


}
