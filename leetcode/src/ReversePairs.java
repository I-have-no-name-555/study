/**
 * @author :Xuan
 * @date :Create in 2021/4/22 10:18
 * @description 剑指 Offer 51. 数组中的逆序对 困难
 */
public class ReversePairs {
    /*
        在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
    输入一个数组，求出这个数组中的逆序对的总数。
    示例 1:
    输入: [7,5,6,4]
    输出: 5
    限制：
    0 <= 数组长度 <= 50000
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int reversePairs(int[] nums) {
        int len = nums.length;

        if (len < 2)
            return 0;

        int[] copy = new int[len];
        System.arraycopy(nums, 0, copy, 0, len);

        int[] temp = new int[len];
        return reversePairs(copy, 0, len - 1, temp);
    }

    private int reversePairs(int[] nums, int left, int right, int[] temp) {
        if (left == right)
            return 0;

        int mid = left + ((right - left) >> 1);
        int leftPairs = reversePairs(nums, left, mid, temp);
        int rightPairs = reversePairs(nums, mid + 1, right, temp);

        if (nums[mid] <= nums[mid + 1])
            return leftPairs + rightPairs;

        int crossPairs = mergeAndCount(nums, left, mid, right, temp);
        return leftPairs + rightPairs + crossPairs;
    }

    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
        System.arraycopy(nums, left, temp, left, right + 1 - left);

        int i = left;
        int j = mid + 1;

        int count = 0;
        for (int k = left; k <= right; k++) {

            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                nums[k] = temp[i];
                i++;
            } else {
                nums[k] = temp[j];
                j++;
                count += (mid - i + 1);
            }
        }
        return count;
    }

}
