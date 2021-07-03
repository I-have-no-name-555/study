
/**
 * @author :Xuan
 * @date :Create in 2021/5/17 15:27
 * @description 189. 旋转数组 中等
 */
public class RotateArray {
    /*
    给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
    进阶：
    尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
    你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
    示例 1:
    输入: nums = [1,2,3,4,5,6,7], k = 3
    输出: [5,6,7,1,2,3,4]
    解释:
    向右旋转 1 步: [7,1,2,3,4,5,6]
    向右旋转 2 步: [6,7,1,2,3,4,5]
    向右旋转 3 步: [5,6,7,1,2,3,4]
    示例 2:
    输入：nums = [-1,-100,3,99], k = 2
    输出：[3,99,-1,-100]
    解释:
    向右旋转 1 步: [99,-1,-100,3]
    向右旋转 2 步: [3,99,-1,-100]
    提示：
    1 <= nums.length <= 2 * 10^4
    -2^31 <= nums[i] <= 2^31 - 1
    0 <= k <= 10^5
    通过次数259,997提交次数568,923
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/rotate-array
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int k = 3;
        int[] nums = {1,2,3,4,5,6,7};
        new RotateArray().rotate2(nums,k);
    }
    public void rotate(int[] nums, int k) {
        if (nums == null || k < 0 || nums.length == 0)
            return;
        int len = nums.length;
        k %= len;
        int[] res = new int[len];
        System.arraycopy(nums,len - k,res,0,k);
        System.arraycopy(nums,0,res,k,len - k);
        System.arraycopy(res,0,nums,0,len);
    }
    //超时
    public void rotate2(int[] nums, int k) {
        if (nums == null || k < 0 || nums.length == 0)
            return;
        int len = nums.length;
        k %= len;
        for (int i = 0; i < k; i++) {
            int last = nums[len - 1];
            System.arraycopy(nums,0,nums,1,len - 1);
            nums[0] = last;
        }
    }

    public void rotate3(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        int count = gcd(k, n);
        for (int start = 0; start < count; ++start) {
            int cur = start;
            int pre = nums[start];
            do {
                int next = (cur + k) % n;
                int temp = nums[next];
                nums[next] = pre;
                pre = temp;
                cur = next;
            } while (start != cur);
        }
    }
    private int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }

    public void rotate4(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

}
