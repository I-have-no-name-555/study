import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author :Xuan
 * @date :Create in 2021/6/29 15:22
 * @description 581. 最短无序连续子数组 中等
 */
public class ShortestUnsortedContinuousSubarray {
    /*
        给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
    请你找出符合题意的 最短 子数组，并输出它的长度。
    示例 1：
    输入：nums = [2,6,4,8,10,9,15]
    输出：5
    解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
    示例 2：
    输入：nums = [1,2,3,4]
    输出：0
    示例 3：
    输入：nums = [1]
    输出：0
    提示：
    1 <= nums.length <= 104
    -105 <= nums[i] <= 105
    进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int findUnsortedSubarray(int[] nums) {
        int[] numsCopy = nums.clone();
        Arrays.sort(numsCopy);
        int n = nums.length;
        int start = n, end = 0;
        for (int i = 0; i < n; i++) {
            if (numsCopy[i] != nums[i]) {
                start = Math.min(start, i);
                end = Math.max(end, i);
            }
        }
        return (end - start >= 0 ? end - start + 1 : 0);
    }

    public int findUnsortedSubarray2(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        int len = nums.length;
        int left = len, right = 0;
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                left = Math.min(left, stack.pop());
            stack.push(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                right = Math.max(right, stack.pop());
            stack.push(i);
        }
        return right - left > 0 ? right - left + 1 : 0;
    }

    public int findUnsortedSubarray3(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        boolean flag = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1])
                flag = true;
            if (flag)
                min = Math.min(min, nums[i]);
        }
        flag = false;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1])
                flag = true;
            if (flag)
                max = Math.max(max, nums[i]);
        }
        int l, r;
        for (l = 0; l < nums.length; l++) {
            if (min < nums[l])
                break;
        }
        for (r = nums.length - 1; r >= 0; r--) {
            if (max > nums[r])
                break;
        }
        return r - l < 0 ? 0 : r - l + 1;
    }

}
