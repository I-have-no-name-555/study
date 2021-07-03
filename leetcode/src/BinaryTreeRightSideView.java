import java.util.*;

/**
 * @author :Xuan
 * @date :Create in 2021/5/20 15:52
 * @description 199. 二叉树的右视图 中等
 */
public class BinaryTreeRightSideView {
    /*
        给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
    示例:
    输入: [1,2,3,null,5,null,4]
    输出: [1, 3, 4]
    解释:
       1            <---
     /   \
    2     3         <---
     \     \
      5     4       <---
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == size - 1) {  //将当前层的最后一个节点放入结果列表
                    res.add(node.val);
                }
            }
        }
        return res;
    }
}
