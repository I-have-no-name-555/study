import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author :Xuan
 * @date :Create in 2020/12/14 18:52
 * @description 第四题 寻找两个正序数组的中位数 困难
 * @update
 */
public class MedianOfTwoSortedArrays {
    /*
        给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
    进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
    示例 1：
    输入：nums1 = [1,3], nums2 = [2]
    输出：2.00000
    解释：合并数组 = [1,2,3] ，中位数 2
    示例 2：
    输入：nums1 = [1,2], nums2 = [3,4]
    输出：2.50000
    解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
    示例 3：
    输入：nums1 = [0,0], nums2 = [0,0]
    输出：0.00000
    示例 4：
    输入：nums1 = [], nums2 = [1]
    输出：1.00000
    示例 5：
    输入：nums1 = [2], nums2 = []
    输出：2.00000
    提示：
    nums1.length == m
    nums2.length == n
    0 <= m <= 1000
    0 <= n <= 1000
    1 <= m + n <= 2000
    -10^6 <= nums1[i], nums2[i] <= 10^6

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[] nums2 = new int[]{10};
        int[] nums1 = new int[]{11};
        MedianOfTwoSortedArrays m = new MedianOfTwoSortedArrays();
        System.out.println(m.findMedianSortedArrays1(nums1, nums2));
        System.out.println(m.findMedianSortedArrays2(nums1, nums2));
        System.out.println(m.findMedianSortedArrays3(nums1, nums2));
        System.out.println(m.findMedianSortedArrays4(nums1, nums2));
    }

    //直接调库排序 极简的实现
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, nums, 0, nums1.length);
        System.arraycopy(nums2, 0, nums, nums1.length, nums2.length);
        Arrays.parallelSort(nums);
        return nums.length % 2 != 0 ? nums[nums.length / 2] :
                ((nums[(nums.length + 1) / 2]) + (nums[(nums.length - 1) / 2])) / 2.0;

    }

    //归并
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || nums1[aStart] < nums2[bStart])) {
                right = nums1[aStart++];
            } else {
                right = nums2[bStart++];
            }
        }
        return ((len & 1) == 0) ? (left + right) / 2.0 : right;
    }

    //寻找第k小的数
    //玛德这边界问题太复杂了
    //写了两天写不出来，各种边界问题
    //用答案了
    //    作者：windliang
    //    链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/
    //    来源：力扣（LeetCode）
    //    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

    //二分的
    public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n)
            return findMedianSortedArrays4(nums2, nums1);
        int iMin = 0, iMax = m;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = (m + n + 1) / 2 - i;
            if (j != 0 && i != m && nums2[j - 1] > nums1[i]) {
                iMin = i + 1;
            } else if (i != 0 && j != n && nums1[i - 1] > nums2[j]) {
                iMax = i - 1;
            } else {
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }
                int minRight = 0;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums2[j], nums1[i]);
                }
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
