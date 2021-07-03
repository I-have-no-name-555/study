package study;


import org.junit.Test;

/**
 * @author :Xuan
 * @date :Create in 2020/12/7 18:21
 * @description 第二章 算法分析
 * @update
 */
public class AlgorithmAnalysis {
    /*
    最大子序列和问题：给定一个整数序列（可能含负数），求出其中和最大的子序列之和
    若全为负数则返回0
     */
    /*
    算法一:穷举 复杂度O（n^3)
     */
    public int maximumSubsequenceSum1(int[] nums){
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int thisSum = 0;
                for (int k = i; k <= j ; k++) {
                    thisSum += nums[j];
                    if (thisSum > sum)
                        sum = thisSum;
                }
            }
        }
        return sum;
    }
    /*
    算法二：简单优化的穷举，O(n^2)
     */
    public int maximumSubsequenceSum2(int[] nums){
        int maxSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int thisSum = 0;
            for (int j = i;j < nums.length;j++){
                thisSum += nums[j];
                if (thisSum > maxSum)
                    maxSum = thisSum;
            }
        }
        return maxSum;
    }
    /*
    算法三：分治法 ， O(nlogn)
    将序列二分，最大子序列有三种情况：在左边序列、在右边序列、跨越两边，在第三种情况下，和为两边最大子序列之和的和
     */
    public int maximumSubsequenceSum(int[] nums, int left, int right) {
        if (left == right) {
            return Math.max(nums[left], 0);
        }
        int center = (left + right) / 2;
        int maxLeftSum = maximumSubsequenceSum(nums, left, center);
        int maxRightSum = maximumSubsequenceSum(nums, center + 1, right);
        int maxLeftBorderSum = 0;
        int leftBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += nums[i];
            if (leftBorderSum > maxLeftBorderSum)
                maxLeftBorderSum = leftBorderSum;
        }
        int maxRightBorderSum = 0;
        int rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += nums[i];
            if (rightBorderSum > maxRightBorderSum)
                maxRightBorderSum = rightBorderSum;
        }
        return Math.max(Math.max(maxLeftBorderSum + maxRightBorderSum, maxLeftSum), maxRightSum);
    }
    /*
    算法四：   O(n)
    任何以负数开始的子序列之和不可能大于以下一个数开始的子序列之和
     */
    public int maximumSubsequenceSum4(int[] nums){
        int maxSum = 0;
        int thisSum = 0;
        for (int num : nums) {
            thisSum += num;
            if (thisSum > maxSum)
                maxSum = thisSum;
            else if (thisSum < 0)
                thisSum = 0;
        }
        return maxSum;
    }
    @Test
    public void testMaximumSubsequenceSum(){
        int[] nums = new int[]{2,3325,2,-52435,4,2,5,7};
        int answer = 0;
        Long begin = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            answer = maximumSubsequenceSum1(nums);
        }
        Long end = System.currentTimeMillis();
        System.out.println(answer + " " + ( end - begin) );

        begin = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            answer = maximumSubsequenceSum2(nums);
        }
        end = System.currentTimeMillis();
        System.out.println(answer + " " + ( end - begin) );

        begin = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
//            answer = maximumSubsequenceSum3(nums , 0 ,nums.length - 1);
            //因爆栈无法测试
            //分治法尽可能使用非递归实现
        }
        end = System.currentTimeMillis();
        System.out.println(answer + " " + ( end - begin) );

        begin = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            answer = maximumSubsequenceSum4(nums);
        }
        end = System.currentTimeMillis();
        System.out.println(answer + " " + ( end - begin) );
    }
    
}
