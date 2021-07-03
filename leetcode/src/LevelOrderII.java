import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author :Xuan
 * @date :Create in 2021/4/5 16:11
 * @description 剑指 Offer 32 - II. 从上到下打印二叉树 II 简单
 */
public class LevelOrderII {
    /*
        从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
    例如:
    给定二叉树: [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
    返回其层次遍历结果：
    [
      [3],
      [9,20],
      [15,7]
    ]
    提示：
    节点总数 <= 1000
    注意：本题与主站 102 题相同：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        queue.offer(root);

        int curSize;
        TreeNode curNode;
        while (!queue.isEmpty()) {
            curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                curNode = queue.poll();
                list.add(curNode.val);
                if (curNode.left != null)
                    queue.offer(curNode.left);
                if (curNode.right != null)
                    queue.offer(curNode.right);
            }
            res.add(new ArrayList<>(list));
            list.clear();
        }
        return res;
    }
}
