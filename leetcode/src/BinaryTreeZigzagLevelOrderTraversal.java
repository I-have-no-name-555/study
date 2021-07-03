import java.util.*;

/**
 * @author :Xuan
 * @date :Create in 2021/2/16 18:55
 * @description 103. 二叉树的锯齿形层序遍历 中等
 * @update
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    /*
        给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
    例如：
    给定二叉树 [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
    返回锯齿形层序遍历如下：
    [
      [3],
      [20,9],
      [15,7]
    ]
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;
        int num = 0;
        int nextNum = 1;
        int count = 1;
        Stack<TreeNode> stackA = new Stack<>();
        Stack<TreeNode> stackB = new Stack<>();
        stackA.add(root);
        while (!stackA.isEmpty() || !stackB.isEmpty()){
            List<Integer> cur = new ArrayList<>();
            num = nextNum;
            nextNum = 0;
            for (int i = 0; i < num; i++) {
                if ((count & 1) == 1){
                    TreeNode node = stackA.pop();
                    if (node != null) {
                        cur.add(node.val);
                        stackB.add(node.left);
                        stackB.add(node.right);
                        nextNum += 2;
                    }
                }else {
                    TreeNode node = stackB.pop();
                    if (node != null) {
                        cur.add(node.val);
                        stackA.add(node.right);
                        stackA.add(node.left);
                        nextNum += 2;
                    }
                }
            }
            count++;
            if (!cur.isEmpty())
                ans.add(cur);
        }
        return ans;
    }

}
