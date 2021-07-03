import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/3/13 21:44
 * @description 145. 二叉树的后序遍历 中等
 */
public class BinaryTreePostorderTraversal {
    /*
        给定一个二叉树，返回它的 后序 遍历。
    示例:
    输入: [1,null,2,3]
       1
        \
         2
        /
       3
    输出: [3,2,1]
    进阶: 递归算法很简单，你可以通过迭代算法完成吗？
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
    著作权归领扣网络所有。商业转载请联系 官方授权，非商业转载请注明出处。
     */
    List<Integer> ans = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        postorderTraversalMain(root);
        return ans;
    }
    public void postorderTraversalMain(TreeNode root) {
        if (root == null)
            return;
        postorderTraversalMain(root.left);
        postorderTraversalMain(root.right);
        ans.add(root.val);
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }
}
