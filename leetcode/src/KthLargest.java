/**
 * @author :Xuan
 * @date :Create in 2021/4/24 20:26
 * @description 剑指 Offer 54. 二叉搜索树的第k大节点 简单
 */
public class KthLargest {
    int res, k;
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }
    private void dfs(TreeNode root) {
        if(root == null || res != 0)
            return;
        dfs(root.right);
        if(--k == 0)
            res = root.val;
        dfs(root.left);
    }
}
