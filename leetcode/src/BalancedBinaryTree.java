import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author :Xuan
 * @date :Create in 2021/2/21 15:33
 * @description 110. 平衡二叉树 简单
 * @update
 */
public class BalancedBinaryTree {
    /*
        给定一个二叉树，判断它是否是高度平衡的二叉树。
    本题中，一棵高度平衡二叉树定义为：
    一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
    示例 1：
    输入：root = [3,9,20,null,null,15,7]
    输出：true
    示例 2：
    输入：root = [1,2,2,3,3,null,null,4,4]
    输出：false
    示例 3：
    输入：root = []
    输出：true
    提示：
    树中的节点数在范围 [0, 5000] 内
    -104 <= Node.val <= 104
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/balanced-binary-tree
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1
                ,new TreeNode(2,new TreeNode(4,new TreeNode(8),null),new TreeNode(5))
                ,new TreeNode(3,new TreeNode(6),null));
        System.out.println(new BalancedBinaryTree().isBalanced(root));
    }
    public boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

}
