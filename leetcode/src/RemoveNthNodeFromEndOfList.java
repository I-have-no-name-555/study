import java.util.Stack;

/**
 * @author :Xuan
 * @date :Create in 2020/12/26 20:45
 * @description 第19题 删除链表的倒数第N个节点 中等
 * @update
 */
public class RemoveNthNodeFromEndOfList {
    /*
        给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
    示例：
    给定一个链表: 1->2->3->4->5, 和 n = 2.
    当删除了倒数第二个节点后，链表变为 1->2->3->5.
    说明：
    给定的 n 保证是有效的。
    进阶：
    你能尝试使用一趟扫描实现吗？
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode right = head;
        ListNode left = dummy;
        for (int i = 0; i < n; i++)
            right = right.next;
        while (right != null) {
            right = right.next;
            left = left.next;
        }
        left.next = left.next.next;
        return dummy.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        if (next == null)
            return val + "->" + null;
        return val + "->" + next.toString();
    }
}
