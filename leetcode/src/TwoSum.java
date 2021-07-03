import java.util.Arrays;
import java.util.HashMap;

/**
 * @author :Xuan
 * @date :Create in 2020/12/13 16:20
 * @description 题库第一题 简单
 * @update
 */
public class TwoSum {
    /*
    给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
    示例:
    给定 nums = [2, 7, 11, 15], target = 9
    因为 nums[0] + nums[1] = 2 + 7 = 9
    所以返回 [0, 1]
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/two-sum
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int[] twoSum(int[] nums, int target) {
        int[] answer = new int[2];
        HashMap<Integer,Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length;i++){
            if(map.containsKey(nums[i])){
                answer[1] = i ;
                answer[0] = map.get(nums[i]);
            }else
                map.put(target - nums[i] , i);
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(new TwoSum().twoSum(nums, target)));
    }
}
