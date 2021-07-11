/**
 * @author :Xuan
 * @date :Create in 2021/4/23 21:34
 * @description 剑指 Offer 53 - II. 0～n-1中缺失的数字 简单
 */
public class MissingNumber {
    /*
        一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
    在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
    示例 1:
    输入: [0,1,3]
    输出: 2
    示例 2:
    输入: [0,1,2,3,4,5,6,7,9]
    输出: 8
    限制：
    1 <= 数组长度 <= 10000
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int missingNumber(int[] nums) {
        int right = nums.length - 1;
        if (nums[right] == right)
            return right + 1;
        int left = 0;
        int mid;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (nums[mid] == mid)
                left = mid + 1;
            else right = mid;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1};
        System.out.println(new MissingNumber().missingNumber(nums));
    }

    /*
    268. 丢失的数字 简单
        给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
    进阶：
    你能否实现线性时间复杂度、仅使用额外常数空间的算法解决此问题?
    示例 1：
    输入：nums = [3,0,1]
    输出：2
    解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
    示例 2：
    输入：nums = [0,1]
    输出：2
    解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
    示例 3：
    输入：nums = [9,6,4,2,3,5,7,0,1]
    输出：8
    解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
    示例 4：
    输入：nums = [0]
    输出：1
    解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
    提示：
    n == nums.length
    1 <= n <= 104
    0 <= nums[i] <= n
    nums 中的所有数字都 独一无二
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/missing-number
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int missingNumber2(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }

    public int missingNumbe3(int[] nums) {
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for (int num : nums)
            actualSum += num;
        return expectedSum - actualSum;
    }


}
