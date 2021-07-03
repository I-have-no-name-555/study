import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author :Xuan
 * @date :Create in 2021/2/15 11:23
 * @description 102. 二叉树的层序遍历 中等
 * @update
 */
public class BinaryTreeLevelOrderTraversal {
    /*
        给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
    示例：
    二叉树：[3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
    返回其层序遍历结果：
    [
      [3],
      [9,20],
      [15,7]
    ]
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;
        int num = 0;
        int nextNum = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> cur = new ArrayList<>();
            num = nextNum;
            nextNum = 0;
            for (int i = 0; i < num; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    cur.add(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                    nextNum += 2;
                }
            }
            if (!cur.isEmpty())
                ans.add(cur);
        }
        return ans;
    }
}
