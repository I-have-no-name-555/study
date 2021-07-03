/**
 * @author :Xuan
 * @date :Create in 2021/6/11 16:39
 * @description 287. 寻找重复数 中等
 */
public class FindTheDuplicateNumber {
    /*
        给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
    假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
    你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。
    示例 1：
    输入：nums = [1,3,4,2,2]
    输出：2
    示例 2：
    输入：nums = [3,1,3,4,2]
    输出：3
    示例 3：
    输入：nums = [1,1]
    输出：1
    示例 4：
    输入：nums = [1,1,2]
    输出：1
    提示：
    1 <= n <= 105
    nums.length == n + 1
    1 <= nums[i] <= n
    nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
    进阶：
    如何证明 nums 中至少存在一个重复的数字?
    你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/find-the-duplicate-number
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int findDuplicate(int[] nums) {
        int len = nums.length;
        int left = 1;
        int right = len - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    cnt += 1;
                }
            }
            if (cnt > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    public int findDuplicate2(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

}
