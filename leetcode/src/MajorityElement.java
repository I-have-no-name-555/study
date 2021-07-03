import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author :Xuan
 * @date :Create in 2021/4/11 15:03
 * @description 剑指 Offer 39. 数组中出现次数超过一半的数字 简单
 */
public class MajorityElement {
    /*
        数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
    你可以假设数组是非空的，并且给定的数组总是存在多数元素。
    示例1:
    输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
    输出: 2
    限制：
    1 <= 数组长度 <= 50000
    注意：本题与主站 169 题相同：https://leetcode-cn.com/problems/majority-element/
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int majorityElement(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int value = map.getOrDefault(num, 0) + 1;
            if (value > (len >> 1))
                return num;
            map.put(num, value);
        }
        int res = nums[0];
        int value = 1;
        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            if (entry.getValue() > value) {
                value = entry.getValue();
                res = entry.getKey();
            }
        }
        return res;
    }

    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length >> 1];
    }
    public int majorityElement3(int[] nums) {
        int x = 0, votes = 0;
        for(int num : nums){
            if(votes == 0)
                x = num;
            votes += num == x ? 1 : -1;
        }
        return x;

    }


}
