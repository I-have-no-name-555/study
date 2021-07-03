import java.util.Arrays;

/**
 * @author :Xuan
 * @date :Create in 2020/12/23 15:56
 * @description 第16题 最接近的三数之和 中等
 * @update
 */
public class ThreeSumClosed {
    /*
        给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
        找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。
        假定每组输入只存在唯一答案。
    示例：
    输入：nums = [-1,2,1,-4], target = 1
    输出：2
    解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
    提示：
    3 <= nums.length <= 10^3
    -10^3 <= nums[i] <= 10^3
    -10^4 <= target <= 10^4
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/3sum-closest
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        ThreeSumClosed test = new ThreeSumClosed();
        int target = 1;
        int[] nums = new int[]{2,1,-3,0};
        System.out.println(test.threeSumClosest(nums, target));
    }
    //排序＋双指针
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        int start;
        int end;
        for(int i = 0;i < n;i++) {
            start = i + 1;
            end = n- 1;
            while(start < end) {
                int sum = nums[start] + nums[end] + nums[i];
                if(Math.abs(target - sum) < Math.abs(target - ans))
                    ans = sum;
                if(sum > target)
                    end--;
                else if(sum < target)
                    start++;
                else
                    return ans;
            }
        }
        return ans;
    }
}
