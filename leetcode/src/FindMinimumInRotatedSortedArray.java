/**
 * @author :Xuan
 * @date :Create in 2021/3/20 20:25
 * @description 153. 寻找旋转排序数组中的最小值 中等
 */
public class FindMinimumInRotatedSortedArray {
    /*
        假设按照升序排序的数组在预先未知的某个点上进行了旋转。
    例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] 。
    请找出其中最小的元素。
    示例 1：
    输入：nums = [3,4,5,1,2]
    输出：1
    示例 2：
    输入：nums = [4,5,6,7,0,1,2]
    输出：0
    示例 3：
    输入：nums = [1]
    输出：1
    提示：
    1 <= nums.length <= 5000
    -5000 <= nums[i] <= 5000
    nums 中的所有整数都是 唯一 的
    nums 原来是一个升序排序的数组，但在预先未知的某个点上进行了旋转
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[] nums = {1,2};
        System.out.println(new FindMinimumInRotatedSortedArray().findMin(nums));
    }
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left <= right){
            mid = (right + left) >> 1;
            if (nums[mid] > nums[right])
                left = mid + 1;
            else if (nums[mid] < nums[right])
                right = mid;
            else break;
        }
        return nums[mid];
    }
}
