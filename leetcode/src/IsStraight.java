import java.util.Arrays;

/**
 * @author :Xuan
 * @date :Create in 2021/5/1 10:45
 * @description 剑指 Offer 61. 扑克牌中的顺子 简单
 */
public class IsStraight {
    /*
        从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
    2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
    示例 1:
    输入: [1,2,3,4,5]
    输出: True
    示例 2:
    输入: [0,0,1,2,5]
    输出: True
    限制：
    数组长度为 5
    数组的数取值为 [0, 13] .
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean isStraight(int[] nums) {
        boolean[] repeat = new boolean[14];
        int max = 0, min = 14;
        for (int num : nums) {
            if (num == 0)
                continue;
            max = Math.max(max, num);
            min = Math.min(min, num);
            if (repeat[num])
                return false;
            repeat[num] = true;
        }
        return max - min < 5;
    }

    public boolean isStraight2(int[] nums) {
        int joker = 0;
        Arrays.sort(nums);
        for (int i = 0; i < 4; i++) {
            if (nums[i] == 0)
                joker++;
            else if (nums[i] == nums[i + 1])
                return false;
        }
        return nums[4] - nums[joker] < 5;
    }

}
