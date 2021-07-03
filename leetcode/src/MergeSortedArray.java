import java.util.Arrays;

/**
 * @author :Xuan
 * @date :Create in 2021/2/4 17:04
 * @description 88. 合并两个有序数组 简单
 * @update
 */
public class MergeSortedArray {
    /*
        给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
    初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有
    足够的空间保存来自 nums2 的元素。
    示例 1：
    输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    输出：[1,2,2,3,5,6]
    示例 2：
    输入：nums1 = [1], m = 1, nums2 = [], n = 0
    输出：[1]
    提示：
    nums1.length == m + n
    nums2.length == n
    0 <= m, n <= 200
    1 <= m + n <= 200
    -109 <= nums1[i], nums2[i] <= 109
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/merge-sorted-array
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0)
            return;
        int i = 0;
        int j = 0;
        int[] nums = Arrays.copyOf(nums1, m);
        for (int k = 0; k < m + n; k++) {
            if (i < m && (j >= n || nums[i] < nums2[j]))
                nums1[k] = nums[i++];
            else
                nums1[k] = nums2[j++];
        }
    }
    public void merge2(int[] nums1 , int m, int[] nums2,int n){
        if (n == 0)
            return;
        int i = m - 1;
        int j = n - 1;
        for (int k = m + n - 1; k >= 0; k--) {
            if (i >= 0 && (j < 0 || nums1[i] > nums2[j]))
                nums1[k] = nums1[i--];
            else nums1[k] = nums2[j--];
        }
    }
}
