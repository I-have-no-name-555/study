/**
 * @author :Xuan
 * @date :Create in 2021/2/26 23:01
 * @description 117. 填充每个节点的下一个右侧节点指针 II 中等
 */
public class PopulatingNextRightPointersInEachNodeII {
    /*
        给定一个二叉树
    struct Node {
      int val;
      Node *left;
      Node *right;
      Node *next;
    }
    填充它的每个 next 指针，让这个指针指向其下一个右侧节点。
    如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
    初始状态下，所有 next 指针都被设置为 NULL。
    进阶：
    你只能使用常量级额外空间。
    使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
    示例：
    输入：root = [1,2,3,4,5,null,7]
    输出：[1,#,2,3,#,4,5,7,#]
    解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
    提示：
    树中的节点数小于 6000
    -100 <= node.val <= 100
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public Node connect(Node root) {
        return new PopulatingNextRightPointersInEachNode().connect(root);
    }

    Node last = null, nextStart = null;
    public Node connect2(Node root) {
        if (root == null)
            return null;
        Node start = root;
        while (start != null) {
            last = null;
            nextStart = null;
            for (Node p = start; p != null; p = p.next) {
                if (p.left != null)
                    handle(p.left);
                if (p.right != null)
                    handle(p.right);
            }
            start = nextStart;
        }
        return root;
    }
    public void handle(Node p) {
        if (last != null)
            last.next = p;
        if (nextStart == null)
            nextStart = p;
        last = p;
    }
}
