import java.util.Arrays;

/**
 * @author :Xuan
 * @date :Create in 2021/5/29 15:44
 * @description 215. 数组中的第K个最大元素 中等
 */
public class KthLargestElementInAnArray {
    /*
        在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，
    而不是第 k 个不同的元素。
    示例 1:
    输入: [3,2,1,5,6,4] 和 k = 2
    输出: 5
    示例 2:
    输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
    输出: 4
    说明:
    你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //排序
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k + 1];
    }

    //快速选择
    public int findKthLargest3(int[] nums, int k) {
        quickSelect(nums, 0, nums.length - 1, k);
        return nums[k - 1];
    }
    public void quickSelect(int[] a, int left, int right, int k) {
        if (left >= right)
            return;
        int i = left - 1;
        int j = right + 1;
        int mid = a[left + right >> 1];
        while (i < j) {
            while (a[++i] > mid);
            while (a[--j] < mid);
            if (i < j)
                swap(a, i, j);
        }
        if (j - left + 1 >= k)
            quickSelect(a, left, j, k);
        else
            quickSelect(a, j + 1, right, k - (j - left + 1));
    }
    private void swap(int[] nums, int i, int j) {
        if (nums[i] == nums[j])
            return;
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    public static void main(String[] args) {
        int[] nums = {3,4,1,2,5,6};
        int k = 2;
        System.out.println(new KthLargestElementInAnArray().findKthLargest3(nums, k));
        System.out.println(Arrays.toString(nums));
    }
}
