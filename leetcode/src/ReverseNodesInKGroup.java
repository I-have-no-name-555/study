import java.util.ArrayList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/7/26 14:56
 * @description 25. K 个一组翻转链表 困难
 */
public class ReverseNodesInKGroup {
    /*
        给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
    k 是一个正整数，它的值小于或等于链表的长度。
    如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
    进阶：
    你可以设计一个只使用常数额外空间的算法来解决此问题吗？
    你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
    示例 1：
    输入：head = [1,2,3,4,5], k = 2
    输出：[2,1,4,3,5]
    示例 2：
    输入：head = [1,2,3,4,5], k = 3
    输出：[3,2,1,4,5]
    示例 3：
    输入：head = [1,2,3,4,5], k = 1
    输出：[1,2,3,4,5]
    示例 4：
    输入：head = [1], k = 1
    输出：[1]
    提示：
    列表中节点的数量在范围 sz 内
    1 <= sz <= 5000
    0 <= Node.val <= 1000
    1 <= k <= sz
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        System.out.println(new ReverseNodesInKGroup().reverseKGroup(new ListNode(1,
                new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2));
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        List<ListNode> nodeList = new ArrayList<>();
        while (head != null) {
            nodeList.add(head);
            head = head.next;
        }
        int size = nodeList.size();
        int times = size / k;
        ListNode[] nodeArray = nodeList.toArray(new ListNode[0]);
        for (int i = 0; i < times; i++) {
            reverse(nodeArray, i * k, (i + 1) * k - 1);
        }
        size--;
        for (int i = 0; i < size; i++) {
            nodeArray[i].next = nodeArray[i + 1];
        }
        nodeArray[size].next = null;
        return nodeArray[0];
    }

    private void reverse(ListNode[] nodeArray, int left, int right) {
        int times = ((left + right) >> 1) - left + 1;
        for (int i = 0; i < times; i++) {
            swap(nodeArray, left + i, right - i);
        }
    }

    private void swap(ListNode[] nodeArray, int i, int j) {
        ListNode cur = nodeArray[i];
        nodeArray[i] = nodeArray[j];
        nodeArray[j] = cur;
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = reverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }

        return hair.next;
    }

    private ListNode[] reverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
    }


}
