import java.util.Arrays;

/**
 * @author :Xuan
 * @date :Create in 2021/6/21 16:10
 * @description 416. 分割等和子集 中等
 */
public class PartitionEqualSubsetSum {
    /*
        给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
    示例 1：
    输入：nums = [1,5,11,5]
    输出：true
    解释：数组可以分割成 [1, 5, 5] 和 [11] 。
    示例 2：
    输入：nums = [1,2,3,5]
    输出：false
    解释：数组不能分割成两个元素和相等的子集。
    提示：
    1 <= nums.length <= 200
    1 <= nums[i] <= 100
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean canPartition(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum >> 1;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        if (nums[0] <= target) {
            dp[nums[0]] = true;
        }
        for (int i = 1; i < len; i++) {
            for (int j = target; nums[i] <= j; j--) {
                if (dp[target]) {
                    return true;
                }
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }

    public boolean canPartition2(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int sum1 = 0;
        int sum2 = 0;
        if (len < 2) {
            return false;
        }
        for (int i = len - 1; i >= 0; i--) {
            if (sum1 < sum2) {
                sum1 += nums[i];
            } else {
                sum2 += nums[i];
            }
        }
        if (sum1 == sum2) {
            return true;
        }
        if (((sum1 + sum2) & 1) == 1) {
            return false;
        }
        sum1 = (sum1 + sum2) >> 1;
        sum2 = sum1;
        for (int i = len - 1; i >= 0; i--) {
            if (sum1 < nums[i]) {
                sum2 -= nums[i];
            } else {
                sum1 -= nums[i];
            }
        }
        return sum1 == sum2;
    }

    public static void main(String[] args) {
        int[] nums = {3,3,4,6};
        System.out.println(new PartitionEqualSubsetSum().canPartition2(nums));
    }
}
