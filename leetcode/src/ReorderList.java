import java.util.ArrayList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/3/11 9:22
 * @description 143. 重排链表 中等
 */
public class ReorderList {
    /*
        给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
    将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
    你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
    示例 1:
    给定链表 1->2->3->4, 重新排列为 1->4->2->3.
    示例 2:
    给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/reorder-list
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public void reorderList2(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int left = 0;
        int right = list.size() - 1;
        ListNode[] nodes = new ListNode[list.size()];
        list.toArray(nodes);
        while (left < right){
            nodes[right].next = nodes[left].next;
            nodes[left++].next = nodes[right--];
        }
        nodes[left].next = null;
    }

    public void reorderList(ListNode head) {
        if (head == null)
            return;
        ListNode mid = getMidNode(head);
        ListNode l = mid.next;
        mid.next = null;
        l = reverseList(l);
        mergeList(head, l);
    }
    public ListNode getMidNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nextTemp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextTemp;
        }
        return pre;
    }
    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;
            l1.next = l2;
            l1 = l1_tmp;
            l2.next = l1;
            l2 = l2_tmp;
        }
    }
}
