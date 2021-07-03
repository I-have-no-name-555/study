import java.util.*;

/**
 * @author :Xuan
 * @date :Create in 2021/6/6 10:30
 * @description 239. 滑动窗口最大值 困难
 */
public class SlidingWindowMaximum {
    /*
        给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
    你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    返回滑动窗口中的最大值。
    示例 1：
    输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
    输出：[3,3,5,5,6,7]
    解释：
    滑动窗口的位置                最大值
    ---------------               -----
    [1  3  -1] -3  5  3  6  7       3
     1 [3  -1  -3] 5  3  6  7       3
     1  3 [-1  -3  5] 3  6  7       5
     1  3  -1 [-3  5  3] 6  7       5
     1  3  -1  -3 [5  3  6] 7       6
     1  3  -1  -3  5 [3  6  7]      7
    示例 2：
    输入：nums = [1], k = 1
    输出：[1]
    示例 3：
    输入：nums = [1,-1], k = 1
    输出：[1,-1]
    示例 4：
    输入：nums = [9,11], k = 2
    输出：[11]
    示例 5：
    输入：nums = [4,-2], k = 2
    输出：[4]
    提示：
    1 <= nums.length <= 105
    -104 <= nums[i] <= 104
    1 <= k <= nums.length
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/sliding-window-maximum
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int len = nums.length;
        int[] res = new int[len - k + 1];
        int size = 0;
        for (int i = 0; i < len; i++) {
            if (size < k) {
                treeMap.put(nums[i], treeMap.getOrDefault(nums[i], 0) + 1);
                size++;
                continue;
            }
            res[i - k] = treeMap.lastKey();
            if (treeMap.get(nums[i - k]) == 1)
                treeMap.remove(nums[i - k]);
            else treeMap.put(nums[i - k], treeMap.get(nums[i - k]) - 1);
            treeMap.put(nums[i], treeMap.getOrDefault(nums[i], 0) + 1);
        }
        res[len - k] = treeMap.lastKey();
        return res;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (pair1, pair2) -> pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1]);
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }

    public int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }

    public int[] maxSlidingWindow4(int[] nums, int k) {
        int len = nums.length;
        int[] ans = new int[len - k + 1];
        int max = Integer.MIN_VALUE, index = 0;
        for (int i = 0; i < k; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
            }
        }
        ans[0] = max;
        for (int i = 1; k + i <= len; i++) {
            if (nums[k + i - 1] >= max) {
                index = k + i - 1;
                max = nums[k + i - 1];
            } else {
                if (index < i) {
                    max = Integer.MIN_VALUE;
                    for (int j = 0; j < k; j++) {
                        if (nums[j + i] > max) {
                            max = nums[j + i];
                            index = j + i;
                        }
                    }
                }
            }
            ans[i] = max;
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = {1, 3, 1, 2, 0, 5};
        int k = 3;
        System.out.println(Arrays.toString(new SlidingWindowMaximum().maxSlidingWindow(nums, k)));
    }
}
