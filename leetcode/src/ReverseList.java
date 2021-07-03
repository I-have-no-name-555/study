/**
 * @author :Xuan
 * @date :Create in 2021/4/3 10:18
 * @description 剑指 Offer 24. 反转链表 简单
 */
public class ReverseList {
    /*
        定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
    示例:
    输入: 1->2->3->4->5->NULL
    输出: 5->4->3->2->1->NULL
    限制：
    0 <= 节点个数 <= 5000
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode reverseList(ListNode head) {
        if (head == null)
            return null;
        ListNode pre = new ListNode();
        ListNode next;
        while (head != null){
            next = head.next;
            head.next = pre.next;
            pre.next = head;
            head = next;
        }
        return pre.next;
    }
}
