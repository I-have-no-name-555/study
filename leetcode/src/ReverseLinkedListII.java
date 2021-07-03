import java.util.Stack;

/**
 * @author :Xuan
 * @date :Create in 2021/2/7 19:46
 * @description 92. 反转链表 II 中等
 * @update
 */
public class ReverseLinkedListII {
    /*
        反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
    说明:
    1 ≤ m ≤ n ≤ 链表长度。
    示例:
    输入: 1->2->3->4->5->NULL, m = 2, n = 4
    输出: 1->4->3->2->5->NULL
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n)
            return head;
        ListNode pre = new ListNode(0,head);
        ListNode node = pre;
        for (int i = 1; i < m; i++) {
            pre = pre.next;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = pre.next;
        for (int i = m; i <= n; i++) {
            stack.push(cur);
            cur = cur.next;
        }
        while (!stack.isEmpty()){
            pre.next = stack.pop();
            pre = pre.next;
        }
        pre.next = cur;
        return node.next;
    }
}
