import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author :Xuan
 * @date :Create in 2021/3/29 19:37
 * @description 剑指 Offer 06. 从尾到头打印链表 简单
 */
public class ReversePrint {
    /*
        输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
    示例 1：
    输入：head = [1,3,2]
    输出：[2,3,1]
    限制：
    0 <= 链表长度 <= 10000
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null){
            stack.push(head.val);
            head = head.next;
        }
        int size = stack.size();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = stack.pop();
        }
        return res;
    }
    public int[] reversePrint2(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null){
            list.add(head.val);
            head = head.next;
        }
        int size = list.size();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = list.get(size - i - 1);
        }
        return res;
    }

    public int[] reversePrint3(ListNode head) {
        int size = 0;
        ListNode last = null;
        while (head != null){
            ListNode next = head.next;
            head.next = last;
            last = head;
            head = next;
            size++;
        }
        int[] ans = new int[size];
        fill(ans,last);
        return ans;
    }
    private void fill(int[] array,ListNode head){
        int size = array.length;
        for (int i = 0; i < size; i++) {
            array[i] = head.val;
            head = head.next;
        }
    }
}
