import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/2/19 17:14
 * @description 107. 二叉树的层序遍历 II  简单
 * @update
 */
public class BinaryTreeLevelOrderTraversalII {
    /*
        给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
    例如：
    给定二叉树 [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
    返回其自底向上的层序遍历为：
    [
      [15,7],
      [9,20],
      [3]
    ]
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new BinaryTreeLevelOrderTraversal().levelOrder(root);
        int size = list.size();
        for (int i = 0; i < (size >> 1); i++) {
            List<Integer> curList = list.get(i);
            list.set(i,list.get(size - i - 1));
            list.set(size - i - 1,curList);
        }
        return list;
    }
}
