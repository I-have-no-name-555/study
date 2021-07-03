import java.util.Arrays;

/**
 * @author :Xuan
 * @date :Create in 2021/3/30 10:46
 * @description 剑指 Offer 11. 旋转数组的最小数字 简单
 */
public class MinArray {
    /*
        把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
    输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
    例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
    示例 1：
    输入：[3,4,5,1,2]
    输出：1
    示例 2：
    输入：[2,2,2,0,1]
    输出：0
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int minArray(int[] numbers) {
        int min = numbers[0];
        for (int num : numbers)
            if (num < min)
                min = num;
        return min;
    }

    public int minArray2(int[] numbers) {
        Arrays.sort(numbers);
        return numbers[0];
    }

    public int minArray3(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (numbers[mid] < numbers[right])
                right = mid;
            else if (numbers[mid] > numbers[right])
                left = mid + 1;
            else right--;
        }
        return numbers[left];
    }

    public static void main(String[] args) {
        int[] nums = {3,3,1,3};
        System.out.println(new MinArray().minArray3(nums));
    }
}
