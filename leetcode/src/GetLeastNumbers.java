import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author :Xuan
 * @date :Create in 2021/4/11 16:35
 * @description 剑指 Offer 40. 最小的k个数  简单
 */
public class GetLeastNumbers {
    /*
        输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，
    则最小的4个数字是1、2、3、4。
    示例 1：
    输入：arr = [3,2,1], k = 2
    输出：[1,2] 或者 [2,1]
    示例 2：
    输入：arr = [0,1,2,1], k = 1
    输出：[0]
    限制：
    0 <= k <= arr.length <= 10000
    0 <= arr[i]<= 10000
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[] arr = {3, 2, 1};
        int k = 2;
        System.out.println(Arrays.toString(new GetLeastNumbers().getLeastNumbers3(arr, k)));
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        int[] res = new int[k];
        System.arraycopy(arr, 0, res, 0, k);
        return res;
    }

    public int[] getLeastNumbers2(int[] arr, int k) {
        if (k == 0)
            return new int[0];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < k; i++) {
            priorityQueue.add(arr[i]);
        }
        int len = arr.length;
        for (int i = k; i < len; i++) {
            if (priorityQueue.peek() > arr[i]) {
                priorityQueue.remove();
                priorityQueue.add(arr[i]);
            }
        }
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            res[i] = priorityQueue.remove();
        }
        return res;

    }

    public int[] getLeastNumbers3(int[] arr, int k) {
        if (k == 0 || arr.length == 0)
            return new int[0];
        return quickSelect(arr, 0, arr.length - 1, k - 1);
    }
    private int[] quickSelect(int[] nums, int low, int high, int k) {
        int j = partition(nums, low, high);
        if (j == k)
            return Arrays.copyOf(nums, j + 1);
        return j > k ? quickSelect(nums, low, j - 1, k) :
                quickSelect(nums, j + 1, high, k);
    }
    private int partition(int[] nums, int low, int high) {
        int v = nums[low];
        int i = low;
        int j = high + 1;
        while (true) {
            while (++i <= high && nums[i] < v) ;
            while (--j >= low && nums[j] > v) ;
            if (i >= j)
                break;
            int t = nums[j];
            nums[j] = nums[i];
            nums[i] = t;
        }
        nums[low] = nums[j];
        nums[j] = v;
        return j;
    }

    public int[] getLeastNumbers4(int[] arr, int k) {
        if (k == 0 || arr.length == 0)
            return new int[0];
        int[] counter = new int[10001];
        for (int num : arr) {
            counter[num]++;
        }
        int[] res = new int[k];
        int idx = 0;
        for (int num = 0; num < counter.length; num++) {
            while (counter[num]-- > 0 && idx < k) {
                res[idx++] = num;
            }
            if (idx == k) {
                break;
            }
        }
        return res;
    }


}
