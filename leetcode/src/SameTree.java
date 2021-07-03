/**
 * @author :Xuan
 * @date :Create in 2021/2/4 18:03
 * @description 100. 相同的树 简单
 * @update
 */
public class SameTree {
    /*
        给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
    如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
    示例 1：
    输入：p = [1,2,3], q = [1,2,3]
    输出：true
    示例 2：
    输入：p = [1,2], q = [1,null,2]
    输出：false
    示例 3：
    输入：p = [1,2,1], q = [1,1,2]
    输出：false
    提示：
    两棵树上的节点数目都在范围 [0, 100] 内
    -104 <= Node.val <= 104
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/same-tree
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null)
            return q == null;
        if (q == null || p.val != q.val)
            return false;
        return (isSameTree(p.left,q.left) && isSameTree(p.right,q.right));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreeNode node = (TreeNode) o;

        if (val != node.val) return false;
        if (left != null ? !left.equals(node.left) : node.left != null) return false;
        return right != null ? right.equals(node.right) : node.right == null;
    }

    @Override
    public int hashCode() {
        int result = val;
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new BinaryTreeLevelOrderTraversal().levelOrder(this).toString();
    }
}
