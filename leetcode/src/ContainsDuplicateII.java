import java.util.HashMap;
import java.util.Map;

/**
 * @author :Xuan
 * @date :Create in 2021/5/30 10:52
 * @description 219. 存在重复元素 II 简单
 */
public class ContainsDuplicateII {
    /*
        给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
    使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
    示例 1:
    输入: nums = [1,2,3,1], k = 3
    输出: true
    示例 2:
    输入: nums = [1,0,1,1], k = 1
    输出: true
    示例 3:
    输入: nums = [1,2,3,1,2,3], k = 2
    输出: false
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/contains-duplicate-ii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length < 1 || k == 0)
            return false;
        Map<Integer,Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k)
                    return true;
            map.put(nums[i],i);
        }
        return false;
    }
}
