import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author :Xuan
 * @date :Create in 2021/5/30 10:43
 * @description 217. 存在重复元素 简单
 */
public class ContainsDuplicate {
    /*
        给定一个整数数组，判断是否存在重复元素。
    如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
    示例 1:
    输入: [1,2,3,1]
    输出: true
    示例 2:
    输入: [1,2,3,4]
    输出: false
    示例 3:
    输入: [1,1,1,3,3,4,3,2,4,2]
    输出: true
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/contains-duplicate
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 1)
            return false;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num))
                return true;
            set.add(num);
        }
        return false;
    }
    public boolean containsDuplicate2(int[] nums) {
        if (nums == null || nums.length < 1)
            return false;
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (nums[i] == nums[i - 1])
                return true;
        }
        return false;
    }
}
