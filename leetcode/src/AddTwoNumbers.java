/**
 * @author :Xuan
 * @date :Create in 2020/12/13 17:28
 * @description 第二题 两数相加 中等
 * @update
 */
public class AddTwoNumbers {
    /*
        给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并
    且它们的每个节点只能存储 一位 数字。
    如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
    您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
    示例
    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    输出：7 -> 0 -> 8
    原因：342 + 465 = 807
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/add-two-numbers
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l = new ListNode();
        ListNode lReturn = l;
        boolean carry = false;//进位
        int num;
        while (l1 != null && l2 != null) {
            num = l1.val + l2.val;
            carry = domain(l, carry, num);
            l1 = l1.next;
            l2 = l2.next;
            if (l1 == null && l2 == null) {
                l.next = null;
                if (carry)
                    l.next = new ListNode(1);
            }
            l = l.next;
        }
        while (l1 == null && l2 != null) {
            num = l2.val;
            carry = domain(l, carry, num);
            l2 = l2.next;
            if (l2 == null) {
                l.next = null;
                if (carry)
                    l.next = new ListNode(1);
            }
            l = l.next;
        }
        while (l1 != null && l2 == null) {
            num = l1.val;
            carry = domain(l, carry, num);
            l1 = l1.next;
            if (l1 == null) {
                l.next = null;
                if (carry)
                    l.next = new ListNode(1);
            }
            l = l.next;
        }
        return lReturn;
    }

    private boolean domain(ListNode l, boolean carry, int num) {
        if (carry) {
            num++;
            carry = false;
        }
        if (num >= 10) {
            carry = true;
            l.val = num % 10;
        } else
            l.val = num;
        l.next = new ListNode();
        return carry;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public ListNode() {

        }
    }

}
