import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/3/15 16:50
 * @description 147. 对链表进行插入排序 中等
 */
public class InsertionSortList {
    /*
        对链表进行插入排序。
    插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
    每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
    插入排序算法：
    插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
    每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
    重复直到所有输入数据插入完为止。
    示例 1：
    输入: 4->2->1->3
    输出: 1->2->3->4
    示例 2：
    输入: -1->5->3->4->0
    输出: -1->0->3->4->5
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/insertion-sort-list
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode insertionSortList(ListNode head) {
        ListNode[] listNodes = toArray(head);
        ListNode pre = new ListNode();
        ListNode res = pre;
        for (ListNode listNode : listNodes) {
            pre = pre.next = listNode;
        }
        pre.next = null;
        return res.next;
    }
    private ListNode[] toArray(ListNode head){
        ArrayList<ListNode> list = new ArrayList<>();
        while (head != null){
            list.add(head);
            head = head.next;
        }
        ListNode[] array = new ListNode[list.size()];
        list.sort(Comparator.comparingInt(a -> a.val));
        list.toArray(array);
        return array;
    }

    public ListNode insertionSortList2(ListNode head) {
        if (head == null)
            return head;
        final ListNode HEAD = new ListNode();
        HEAD.next = head;
        ListNode lastSorted = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (lastSorted.val <= cur.val)
                lastSorted = lastSorted.next;
            else {
                ListNode pre = HEAD;
                while (pre.next.val <= cur.val)
                    pre = pre.next;
                lastSorted.next = cur.next;
                cur.next = pre.next;
                pre.next = cur;
            }
            cur = lastSorted.next;
        }
        return HEAD.next;
    }
}
