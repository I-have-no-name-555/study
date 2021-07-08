import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author :Xuan
 * @date :Create in 2021/7/8 9:54
 * @description 179. 最大数 中等
 */
public class LargestNumber {
    /*
    给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
    注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
    示例 1：
    输入：nums = [10,2]
    输出："210"
    示例 2：
    输入：nums = [3,30,34,5,9]
    输出："9534330"
    示例 3：
    输入：nums = [1]
    输出："1"
    示例 4：
    输入：nums = [10]
    输出："10"
    提示：
    1 <= nums.length <= 100
    0 <= nums[i] <= 109
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/largest-number
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public String largestNumber(int[] nums) {
        PriorityQueue<String> heap = new PriorityQueue<>((x, y) -> (y + x).compareTo(x + y));
        for(int x: nums) {
            heap.offer(String.valueOf(x));
        }
        StringBuilder sb = new StringBuilder();
        while(heap.size() > 0) {
            sb.append(heap.poll());
        }
        if(sb.charAt(0) == '0') {
            return "0";
        }
        return sb.toString();
    }
}
