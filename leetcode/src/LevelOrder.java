import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author :Xuan
 * @date :Create in 2021/4/5 15:56
 * @description 剑指 Offer 32 - I. 从上到下打印二叉树 中等
 */
public class LevelOrder {
    /*
        剑指 Offer 32 - I. 从上到下打印二叉树
    从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
    例如:
    给定二叉树: [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
    返回：
    [3,9,20,15,7]
    提示：
    节点总数 <= 1000
     */
    public int[] levelOrder(TreeNode root) {
        if (root == null)
            return new int[0];
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            list.add(cur.val);
            if (cur.left != null)
                queue.offer(cur.left);
            if (cur.right != null)
                queue.offer(cur.right);
        }
        int size = list.size();
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
