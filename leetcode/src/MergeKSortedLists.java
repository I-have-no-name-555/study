import java.util.PriorityQueue;

/**
 * @author :Xuan
 * @date :Create in 2021/7/31 22:00
 * @description 23. 合并K个升序链表 困难
 */
public class MergeKSortedLists {
    /*
        给你一个链表数组，每个链表都已经按升序排列。
    请你将所有链表合并到一个升序链表中，返回合并后的链表。
    示例 1：
    输入：lists = [[1,4,5],[1,3,4],[2,6]]
    输出：[1,1,2,3,4,4,5,6]
    解释：链表数组如下：
    [
      1->4->5,
      1->3->4,
      2->6
    ]
    将它们合并到一个有序链表中得到。
    1->1->2->3->4->4->5->6
    示例 2：
    输入：lists = []
    输出：[]
    示例 3：
    输入：lists = [[]]
    输出：[]
    提示：
    k == lists.length
    0 <= k <= 10^4
    0 <= lists[i].length <= 500
    -10^4 <= lists[i][j] <= 10^4
    lists[i] 按 升序 排列
    lists[i].length 的总和不超过 10^4
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeLists(lists, 0, lists.length - 1);
    }
    private ListNode mergeLists(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        if (right - left == 1) {
            return mergeTwoLists(lists[left], lists[right]);
        }
        int mid = (left + right) >> 1;
        ListNode leftList = mergeLists(lists, left, mid);
        ListNode rightList = mergeLists(lists, mid + 1, right);
        return mergeTwoLists(leftList, rightList);
    }
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode();
        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return prehead.next;
    }


    class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        @Override
        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }
    PriorityQueue<Status> queue = new PriorityQueue<Status>();
    public ListNode mergeKLists2(ListNode[] lists) {
        for (ListNode node: lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null) {
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }

}
