/**
 * @author :Xuan
 * @date :Create in 2021/2/1 22:38
 * @description 82. 删除排序链表中的重复元素 II 中等
 * @update
 */
public class RemoveDuplicatesFromSortedListII {
    /*
        给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

    示例 1:

    输入: 1->2->3->3->4->4->5
    输出: 1->2->5
    示例 2:

    输入: 1->1->1->2->3
    输出: 2->3

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(1, new ListNode(1)));
        System.out.println(new RemoveDuplicatesFromSortedListII().deleteDuplicates(head));
    }

    //递归
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.val == head.next.val) {
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            return deleteDuplicates(head.next);
        } else {
            head.next = deleteDuplicates(head.next);
            return head;
        }
    }

    //三指针
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head;
        while (next != null) {
            while (next != null && next.val == cur.val)
                next = next.next;
            if (cur.next == next)
                pre = cur;
            else { //有重复
                if (pre == null) //头部重复
                    head = next;
                else
                    pre.next = next;
            }
            cur = next;
        }
        return head;
    }

    //双指针
    public ListNode deleteDuplicates3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode left = dummy;
        ListNode right = head;
        while (right != null && right.next != null) {
            if (left.next.val != right.next.val)
                left = left.next;
            else {
                //如果a、b指向的节点值相等，就不断移动b，直到a、b指向的值不相等
                while (right.next != null && left.next.val == right.next.val)
                    right = right.next;
                left.next = right.next;
            }
            right = right.next;
        }
        return dummy.next;
    }


}
