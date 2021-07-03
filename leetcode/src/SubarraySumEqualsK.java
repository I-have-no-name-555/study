import java.util.HashMap;
import java.util.Map;

/**
 * @author :Xuan
 * @date :Create in 2021/6/28 10:38
 * @description 560. 和为K的子数组 中等
 */
public class SubarraySumEqualsK {
    /*
        给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
    示例 1 :
    输入:nums = [1,1,1], k = 2
    输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
    说明 :
    数组的长度为 [1, 20,000]。
    数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; ++start) {
            int sum = 0;
            for (int end = start; end >= 0; --end) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySum2(int[] nums, int k) {
        int count = 0, pre = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            pre += num;
            if (map.containsKey(pre - k)) {
                count += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return count;
    }

}
