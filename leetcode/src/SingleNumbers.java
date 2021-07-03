import java.util.Arrays;

/**
 * @author :Xuan
 * @date :Create in 2021/4/26 15:00
 * @description 剑指 Offer 56 - I. 数组中数字出现的次数 中等
 */
public class SingleNumbers {
    /*
        一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
    请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
    示例 1：
    输入：nums = [4,1,4,6]
    输出：[1,6] 或 [6,1]
    示例 2：
    输入：nums = [1,2,10,4,1,4,3,3]
    输出：[2,10] 或 [10,2]
    限制：
    2 <= nums.length <= 10000
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[] singleNumbers(int[] nums) {
        int ret = 0;
        for (int n : nums) {
            ret ^= n;
        }
        //负数以补码表示
        int div = ret & -ret;
        int a = 0, b = 0;
        for (int n : nums) {
            if ((div & n) != 0) {
                a ^= n;
            } else {
                b ^= n;
            }
        }
        return new int[]{a, b};
    }

    /*
        剑指 Offer 56 - II. 数组中数字出现的次数 II 中等
    在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
    示例 1：
    输入：nums = [3,4,3,3]
    输出：4
    示例 2：
    输入：nums = [9,1,7,9,7,9,7]
    输出：1
    限制：
    1 <= nums.length <= 10000
    1 <= nums[i] < 2^31
     */
    public int singleNumber(int[] nums) {
        int seenOnce = 0, seenTwice = 0;
        for (int num : nums) {
            seenOnce = ~seenTwice & (seenOnce ^ num);
            seenTwice = ~seenOnce & (seenTwice ^ num);
        }
        return seenOnce;
    }
    public int singleNumber1(int[] nums) {
        return (Arrays.stream(nums).distinct().sum() * 3 - Arrays.stream(nums).sum()) >> 1;
    }

}
