import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/3/16 10:49
 * @description 148. 排序链表 中等
 */
public class SortList {
    /*
        给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
    进阶：
    你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
    示例 1：
    输入：head = [4,2,1,3]
    输出：[1,2,3,4]
    示例 2：
    输入：head = [-1,5,3,4,0]
    输出：[-1,0,3,4,5]
    示例 3：
    输入：head = []
    输出：[]
    提示：
    链表中节点的数目在范围 [0, 5 * 104] 内
    -105 <= Node.val <= 105
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/sort-list
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode sortList(ListNode head) {
        if (head == null)
            return head;
        int len = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }
        node = head;
        ListNode[] array = new ListNode[len];
        for (int i = 0; i < len; i++) {
            array[i] = node;
            node = node.next;
        }
        Arrays.sort(array, Comparator.comparingInt(a -> a.val));
        for (int i = 0; i < len - 1; i++) {
            array[i].next = array[i + 1];
        }
        array[len - 1].next = null;
        return array[0];
    }

    public ListNode sortList2(ListNode head) {
        if (head == null)
            return head;
        return sortList(head, null);
    }

    private ListNode sortList(ListNode left, ListNode right) {
        if (left.next == right) {
            left.next = null;
            return left;
        }
        ListNode mid = getMid(left, right);
        ListNode leftList = sortList(left, mid);
        ListNode rightList = sortList(mid, right);
        return merge(leftList, rightList);
    }

    private ListNode merge(ListNode leftList, ListNode rightList) {
        ListNode list = new ListNode();
        ListNode pre = list;
        while (leftList != null && rightList != null) {
            if (leftList.val > rightList.val) {
                list = list.next = leftList;
                leftList = leftList.next;
            } else {
                list = list.next = rightList;
                rightList = rightList.next;
            }
        }
        while (leftList != null) {
            list = list.next = leftList;
            leftList = leftList.next;
        }
        while (rightList != null) {
            list = list.next = rightList;
            rightList = rightList.next;
        }
        return pre.next;
    }

    private ListNode getMid(ListNode left, ListNode right) {
        ListNode slow = left;
        ListNode quick = left;
        while (quick != right) {
            quick = quick.next;
            slow = slow.next;
            if (quick != right)
                quick = quick.next;
        }
        return slow;
    }

    public ListNode sortList3(ListNode head) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (ListNode curr = head; curr != null; curr = curr.next) {
            max = Math.max(max, curr.val);
            min = Math.min(min, curr.val);
        }
        int[] countMap = new int[max - min + 1]; // 记录每个元素出现的次数。
        for (ListNode curr = head; curr != null; curr = curr.next) {
            countMap[curr.val - min]++;
        }
        ListNode curr = head;
        for (int i = 0; i < countMap.length; i++) {
            for (; countMap[i]-- > 0; curr = curr.next) {
                curr.val = i + min;
            }
        }
        return head;
    }

    public ListNode sortList4(ListNode head) {
        ListNode node = head;
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        int[] values = new int[count];
        node = head;
        int index = 0;
        while (node != null) {
            values[index++] = node.val;
            node = node.next;
        }
        Arrays.sort(values);
        node = head;
        index = 0;
        while (node != null) {
            node.val = values[index++];
            node = node.next;
        }
        return head;
    }
}
