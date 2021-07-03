import java.util.HashMap;

/**
 * @author :Xuan
 * @date :Create in 2021/4/7 10:39
 * @description 剑指 Offer 35. 复杂链表的复制 中等
 */
public class CopyRandomList {
    /*
        请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
    示例 1：
    输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
    输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
    示例 2：
    输入：head = [[1,1],[2,1]]
    输出：[[1,1],[2,1]]
    示例 3：
    输入：head = [[3,null],[3,0],[3,null]]
    输出：[[3,null],[3,0],[3,null]]
    示例 4：
    输入：head = []
    输出：[]
    解释：给定的链表为空（空指针），因此返回 null。
    提示：
    -10000 <= Node.val <= 10000
    Node.random 为空（null）或指向链表中的节点。
    节点数目不超过 1000 。
    注意：本题与主站 138 题相同：https://leetcode-cn.com/problems/copy-list-with-random-pointer/
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    HashMap<Node, Node> visitedHash = new HashMap<>();
    public Node copyRandomList(Node head) {
        if (head == null)
            return null;
        if (this.visitedHash.containsKey(head))
            return this.visitedHash.get(head);
        Node node = new Node(head.val);
        this.visitedHash.put(head, node);
        node.next = copyRandomList(head.next);
        node.random = copyRandomList(head.random);
        return node;
    }

    public Node copyRandomList2(Node head) {
        if (head == null)
            return null;

        Node ptr = head;
        while (ptr != null) {
            Node newNode = new Node(ptr.val);
            newNode.next = ptr.next;
            ptr.next = newNode;
            ptr = newNode.next;
        }

        ptr = head;
        while (ptr != null) {
            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
            ptr = ptr.next.next;
        }

        Node ptr_old_list = head;
        Node ptr_new_list = head.next;
        Node head_old = head.next;
        while (ptr_old_list != null) {
            ptr_old_list.next = ptr_old_list.next.next;
            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
            ptr_old_list = ptr_old_list.next;
            ptr_new_list = ptr_new_list.next;
        }
        return head_old;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
