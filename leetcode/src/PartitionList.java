import java.util.ArrayList;

/**
 * @author :Xuan
 * @date :Create in 2021/2/3 15:13
 * @description 86. 分隔链表 中等
 * @update
 */
public class PartitionList {
    /*
        给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
    你应当保留两个分区中每个节点的初始相对位置。
    示例：
    输入：head = 1->4->3->2->5->2, x = 3
    输出：1->2->2->4->3->5
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/partition-list
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        ListNode head = new ListNode(1,new ListNode(1));
        int x = 0;
        System.out.println(new PartitionList().partition2(head, x));
    }
    public ListNode partition(ListNode head, int x) {
        head = new ListNode(0,head);
        ListNode cur = head;
        ListNode pre = head;
        ListNode firstBig = null;
        ListNode beforeFirstBig = null;
        while (cur.next != null){
            if (cur.next.val >= x){
                beforeFirstBig = cur;
                firstBig = cur.next;
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        cur = cur.next;
        while (cur != null){
            if (cur.val < x){
                beforeFirstBig = beforeFirstBig.next = cur;
                pre.next = cur.next;
                cur.next = firstBig;
                cur = pre.next;
            }else {
                pre = cur;
                cur = cur.next;
            }
        }
        return head.next;
    }
    public ListNode partition2(ListNode head, int x) {
        if (head == null || head.next == null)
            return head;
        ListNode smallHead = new ListNode();
        ListNode bigHead = new ListNode();
        ListNode small = smallHead;
        ListNode big = bigHead;
        while (head != null){
            if (head.val < x)
                small = small.next = head;
            else big = big.next = head;
            head = head.next;
        }
        if (smallHead.next != null)
            small.next = bigHead.next;
        big.next = null;
        return smallHead.next != null ? smallHead.next : bigHead.next;
    }

}
