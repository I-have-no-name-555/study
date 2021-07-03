/**
 * @author :Xuan
 * @date :Create in 2021/3/19 11:10
 * @description 152. 乘积最大子数组 中等
 */
public class MaximumProductSubarray {
    /*
        给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），
        并返回该子数组所对应的乘积。
    示例 1:
    输入: [2,3,-2,4]
    输出: 6
    解释: 子数组 [2,3] 有最大乘积 6。
    示例 2:
    输入: [-2,0,-1]
    输出: 0
    解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/maximum-product-subarray
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[] nums = {-2, 0, -1};
        System.out.println(new MaximumProductSubarray().maxProduct(nums));
    }

    public int maxProduct(int[] nums) {
        int len = nums.length;
        int maxProduct = nums[0];
        for (int i = 0; i < len; i++) {
            int cur = 1;
            for (int j = i; j < len; j++) {
                if (nums[j] == 0 && maxProduct > 0)
                    break;
                cur *= nums[j];
                maxProduct = Math.max(cur, maxProduct);
            }
        }
        return maxProduct;
    }

    public int maxProduct2(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            int mx = max;
            int mn = min;
            max = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            min = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(max, ans);
        }
        return ans;
    }

    public int maxProduct3(int[] nums) {
        int res = Integer.MIN_VALUE;
        int max = 1;
        for (int num : nums) {
            max = max * num;
            if (max > res)
                res = max;
            if (num == 0)
                max = 1;
        }
        max = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            max = max * nums[i];
            if (max > res)
                res = max;
            if (nums[i] == 0)
                max = 1;
        }
        return res;
    }
}
