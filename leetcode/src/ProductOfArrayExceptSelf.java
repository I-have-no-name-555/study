/**
 * @author :Xuan
 * @date :Create in 2021/6/5 10:48
 * @description 238. 除自身以外数组的乘积 中等
 */
public class ProductOfArrayExceptSelf {
    /*
        给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，
    其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
    示例:
    输入: [1,2,3,4]
    输出: [24,12,8,6]
    提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
    说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
    进阶：
    你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/product-of-array-except-self
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length <= 1)
            return new int[0];
        int len = nums.length;
        int[] leftToRight = new int[len];
        int[] rightToLeft = new int[len];
        leftToRight[0] = nums[0];
        rightToLeft[len - 1] = nums[len - 1];
        for (int i = 1; i < len; i++) {
            leftToRight[i] = nums[i] * leftToRight[i - 1];
        }
        for (int i = len - 2; i >= 0; i--){
            rightToLeft[i] = rightToLeft[i + 1] * nums[i];
        }
        int[] res = new int[len];
        for (int i = 1; i < len - 1; i++) {
            res[i] = leftToRight[i - 1] * rightToLeft[i + 1];
        }
        res[0] = rightToLeft[1];
        res[len - 1] = leftToRight[len - 2];
        return res;
    }

    public int[] productExceptSelf2(int[] nums) {
        if (nums == null || nums.length <= 1)
            return new int[0];
        int len = nums.length;
        int[] res = new int[len];
        int product = 1;
        int zeroIndex = -1;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0){
                if (zeroIndex == -1)
                    zeroIndex = i;
                else return res;
            } else product *= nums[i];
        }
        if (zeroIndex != -1){
            res[zeroIndex] = product;
            return res;
        }
        for (int i = 0; i < len; i++) {
                res[i] = myDivide(product,nums[i]);
        }
        return res;
    }
    private int myDivide(int dividend, int divisor) {
        int res = 0;
        boolean flag = (dividend ^ divisor) < 0;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        long k = getNearestPowerOfTwo(dividend);
        while (dividend != 0 && k != 0){
            if (k * divisor <= dividend){
                res += k;
                dividend -= k * divisor;
            }
            k >>= 1;
        }
        return flag ? -res : res;
    }
    private long getNearestPowerOfTwo(long n){
        n--;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }

    public int[] productExceptSelf3(int[] nums) {
        if (nums == null || nums.length <= 1)
            return new int[0];
        int len = nums.length;
        int[] res = new int[len];
        int init = 1;
        for (int i = 0; i < len; i++) {
            res[i] = init;
            init = init * nums[i];
        }
        init = 1;
        for (int i = len - 1; i >= 0; i--) {
            res[i] *= init;
            init *= nums[i];
        }
        return res;
    }
}

