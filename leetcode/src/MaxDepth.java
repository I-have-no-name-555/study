/**
 * @author :Xuan
 * @date :Create in 2021/4/25 8:38
 * @description 剑指 Offer 55 - I. 二叉树的深度 简单
 */
public class MaxDepth {
    /*
    输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，
    最长路径的长度为树的深度。
    例如：
    给定二叉树 [3,9,20,null,null,15,7]，
        3
       / \
      9  20
        /  \
       15   7
    返回它的最大深度 3 。
    提示：
    节点总数 <= 10000
    注意：本题与主站 104 题相同：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
     */
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return maxDepth(root,1);
    }
    private int maxDepth(TreeNode node, int curDepth){
        int left = curDepth;
        int right = curDepth;
        if (node.left != null)
            left = Math.max(curDepth,maxDepth(node.left,curDepth + 1));
        if (node.right != null)
            right = Math.max(curDepth,maxDepth(node.right,curDepth + 1));
        return Math.max(left,right);
    }
}
