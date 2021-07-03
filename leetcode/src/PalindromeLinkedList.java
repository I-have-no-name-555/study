import java.util.Deque;
import java.util.LinkedList;

/**
 * @author :Xuan
 * @date :Create in 2021/6/3 20:37
 * @description 234. 回文链表 简单
 */
public class PalindromeLinkedList {
    /*
        请判断一个链表是否为回文链表。
    示例 1:
    输入: 1->2
    输出: false
    示例 2:
    输入: 1->2->2->1
    输出: true
    进阶：
    你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/palindrome-linked-list
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null)
            return false;
        Deque<Integer> stack = new LinkedList<>();
        ListNode node = head;
        while (node != null) {
            stack.push(node.val);
            node = node.next;
        }
        node = head;
        int len = stack.size() >> 1;
        for (int i = 0; i < len; i++) {
            if (node.val != stack.pop())
                return false;
            node = node.next;
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {
        if (head == null)
            return false;
        ListNode pre = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            ListNode temp = slow.next;
            if (pre != null) {
                slow.next = pre;
            }
            pre = slow;
            fast = fast.next.next;
            slow = temp;
        }
        if (fast != null)
            slow = slow.next;
        while (slow != null) {
            if (slow.val != pre.val)
                return false;
            slow = slow.next;
            pre = pre.next;
        }
        return true;
    }
}
