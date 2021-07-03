import java.util.Arrays;

/**
 * @author :Xuan
 * @date :Create in 2021/1/1 16:05
 * @description 31. 下一个排列 中等
 * @update
 */
public class NextPermutation {
    /*
        实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
    如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
    必须 原地 修改，只允许使用额外常数空间。
    示例 1：
    输入：nums = [1,2,3]
    输出：[1,3,2]
    示例 2：
    输入：nums = [3,2,1]
    输出：[1,2,3]
    示例 3：
    输入：nums = [1,1,5]
    输出：[1,5,1]
    示例 4：
    输入：nums = [1]
    输出：[1]
    提示：
    1 <= nums.length <= 100
    0 <= nums[i] <= 100
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/next-permutation
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 1};
        new NextPermutation().nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len <= 1)
            return;
        int index = len - 1;
        int i = index - 1;
        int j = index;
        while (i >= 0 && nums[i] >= nums[j])
            j = i--;
        if (i >= 0) {
            while (nums[i] >= nums[index])
                index--;
            swap(nums, i, index);
        }
        reverse(nums, j, len);
    }

    private void reverse(int[] nums, int startIndex, int endIndex) {
        int len = nums.length;
        int reverseLen = ((endIndex - startIndex) >> 1) + startIndex;
        int count = 0;
        for (int i = startIndex; i < reverseLen; i++, count++) {
            swap(nums, i, len - count - 1);
        }
    }

    private void swap(int[] nums, int index1, int index2) {
        nums[index1] ^= nums[index2];
        nums[index2] ^= nums[index1];
        nums[index1] ^= nums[index2];
    }

}
