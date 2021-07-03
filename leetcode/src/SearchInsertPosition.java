/**
 * @author :Xuan
 * @date :Create in 2021/1/4 16:23
 * @description 35. 搜索插入位置 简单
 * @update
 */
public class SearchInsertPosition {
    /*
        给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
        如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
        你可以假设数组中无重复元素。
    示例 1:
    输入: [1,3,5,6], 5
    输出: 2
    示例 2:
    输入: [1,3,5,6], 2
    输出: 1
    示例 3:
    输入: [1,3,5,6], 7
    输出: 4
    示例 4:
    输入: [1,3,5,6], 0
    输出: 0
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/search-insert-position
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        int target = 7;
        System.out.println(new SearchInsertPosition().searchInsert(nums, target));
        System.out.println(new SearchInsertPosition().searchInsert2(nums, target));
    }

    public int searchInsert(int[] nums, int target) {
        if (nums[0] >= target)
            return 0;
        if (nums[nums.length - 1] < target)
            return nums.length;
        for (int i = 0; i < nums.length - 1; i++)
            if (nums[i] < target && nums[i + 1] >= target)
                return i + 1;
        return 0;
    }

    public int searchInsert2(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] >= target)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}
