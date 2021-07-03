import java.util.List;
import java.util.Stack;

/**
 * @author :Xuan
 * @date :Create in 2021/2/13 9:43
 * @description 98. 验证二叉搜索树 中等
 * @update
 */
public class ValidateBinarySearchTree {
    /*
        给定一个二叉树，判断其是否是一个有效的二叉搜索树。
    假设一个二叉搜索树具有如下特征：
    节点的左子树只包含小于当前节点的数。
    节点的右子树只包含大于当前节点的数。
    所有左子树和右子树自身必须也是二叉搜索树。
    示例 1:
    输入:
        2
       / \
      1   3
    输出: true
    示例 2:
    输入:
        5
       / \
      1   4
         / \
        3   6
    输出: false
    解释: 输入为: [5,1,4,null,null,3,6]。
         根节点的值为 5 ，但是其右子节点值为 4 。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/validate-binary-search-tree
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean isValidBST(TreeNode root) {
        long cur = Long.MIN_VALUE;
        long pre;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            pre = cur;
            cur = root.val;
            if (pre >= cur)
                return false;
            root = root.right;
        }
        return true;
    }

    public boolean isValidBST2(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

}
