import java.util.*;

/**
 * @author :Xuan
 * @date :Create in 2020/12/22 15:50
 * @description 15.三数之和 中等
 * @update
 */
public class ThreeSum {
    /*
        给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
        使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
    注意：答案中不可以包含重复的三元组。
    示例：
    给定数组 nums = [-1, 0, 1, 2, -1, -4]，
    满足要求的三元组集合为：
    [
      [-1, 0, 1],
      [-1, -1, 2]
    ]
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/3sum
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        ThreeSum test = new ThreeSum();
        int[] nums = new int[]{0,0,0};
        System.out.println(test.threeSum1(nums));
        System.out.println(test.threeSum2(nums));
    }
    //排序＋双指针
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list;
        if (nums == null || nums.length < 3)
            return lists;
        int len = nums.length;
        Arrays.sort(nums);
        int left;
        int right;
        for (int i = 0; i < len; i++) {
            left = i + 1;
            right = len - 1;
            if (nums[i] > 0)
                break;
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            while (left < right){
                if (nums[i] + nums[left] + nums[right] == 0){
                    list = new ArrayList<>(3);
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    lists.add(list);
                    while (left < right && nums[left] == nums[left + 1])
                        left++;
                    while (left < right && nums[right] == nums[right - 1])
                        right--;
                    left++;
                    right--;
                }else if (nums[i] + nums[left] + nums[right] < 0)
                    left++;
                else
                    right--;
            }
        }
        return lists;
    }

    //暴力遍历
    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        HashSet<List<Integer>> set = new HashSet<>();
        if (nums == null || nums.length < 3)
            return lists;
        int len = nums.length;
        for (int i = 0; i < len - 2; i++) {
            twoSum(nums,i + 1,-nums[i],set);
        }
        lists.addAll(set);
        return lists;
    }
    private void twoSum(int[] nums , int start,int sum , HashSet<List<Integer>> set){
        ArrayList<Integer> list;
        HashMap<Integer,Integer> map = new HashMap<>(nums.length - start);
        int[] indexes = new int[2];
        for (int i = start; i < nums.length;i++){
            if(map.containsKey(nums[i])){
                indexes[1] = i ;
                indexes[0] = map.get(nums[i]);
                list = new ArrayList<>(3);
                list.add(nums[indexes[0]]);
                list.add(nums[indexes[1]]);
                list.add(-sum);
                list.sort(Integer::compareTo);
                set.add(list);
            }else
                map.put(sum - nums[i] , i);
        }
    }
}
