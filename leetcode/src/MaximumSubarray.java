/**
 * @author :Xuan
 * @date :Create in 2021/1/14 10:15
 * @description 53. 最大子序和 简单
 * @update
 */
public class MaximumSubarray {
    /*
        给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
    示例:
    输入: [-2,1,-3,4,-1,2,1,-5,4]
    输出: 6
    解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
        进阶:
    如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/maximum-subarray
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //任何以负数开始的子序列之和不可能大于以下一个数开始的子序列之和
    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(new MaximumSubarray().maxSubArray2(nums));
    }

    public int maxSubArray(int[] nums) {
        int maxSum = 0;
        int currentSum = 0;
        int maxNum = Integer.MIN_VALUE;
        for (int num : nums) {
            maxNum = Math.max(maxNum, num);
            currentSum += num;
            if (currentSum < 0)
                currentSum = 0;
            else
                maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum == 0 ? maxNum : maxSum;
    }

    //官方题解 分治法
    static class Status {
        public int lSum, rSum, mSum, iSum;
        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }
    public int maxSubArray2(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }
    public Status getInfo(int[] a, int l, int r) {
        if (l == r)
            return new Status(a[l], a[l], a[l], a[l]);
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }
    public Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }
}
