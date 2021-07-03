import java.util.ArrayList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/2/11 13:16
 * @description 95. 不同的二叉搜索树 II 中等
 * @update
 */
public class UniqueBinarySearchTreesII {
    /*
        给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。：
    输入：3
    输出：
    [
      [1,null,3,2],
      [3,2,null,1],
      [3,1,null,null,2],
      [2,1,3],
      [1,null,2,null,3]
    ]
    解释：
    以上的输出对应以下 5 种不同结构的二叉搜索树：
       1         3     3      2      1
        \       /     /      / \      \
         3     2     1      1   3      2
        /     /       \                 \
       2     1         2                 3
    提示：
    0 <= n <= 8
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0)
            return new ArrayList<>();
        return getAns(1, n);

    }
    private List<TreeNode> getAns(int start, int end) {
        List<TreeNode> ans = new ArrayList<>();
        if (start > end) {
            ans.add(null);
            return ans;
        }
        if (start == end) {
            ans.add(new TreeNode(start));
            return ans;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = getAns(start, i - 1);
            List<TreeNode> rightTrees = getAns(i + 1, end);
            //左子树右子树两两组合
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    ans.add(root);
                }
            }
        }
        return ans;
    }

    public List<TreeNode> generateTrees2(int n) {
        ArrayList<TreeNode>[] dp = new ArrayList[n + 1];
        dp[0] = new ArrayList<>();
        if (n == 0) {
            return dp[0];
        }
        dp[0].add(null);
        for (int len = 1; len <= n; len++) {
            dp[len] = new ArrayList<>();
            for (int root = 1; root <= len; root++) {
                int left = root - 1;
                int right = len - root;
                for (TreeNode leftTree : dp[left]) {
                    for (TreeNode rightTree : dp[right]) {
                        TreeNode treeRoot = new TreeNode(root);
                        treeRoot.left = leftTree;
                        treeRoot.right = clone(rightTree, root);
                        dp[len].add(treeRoot);
                    }
                }
            }
        }
        return dp[n];
    }
    private TreeNode clone(TreeNode n, int offset) {
        if (n == null) {
            return null;
        }
        TreeNode node = new TreeNode(n.val + offset);
        node.left = clone(n.left, offset);
        node.right = clone(n.right, offset);
        return node;
    }

    public List<TreeNode> generateTrees3(int n) {
        List<TreeNode> pre = new ArrayList<>();
        if (n == 0) {
            return pre;
        }
        pre.add(null);
        for (int i = 1; i <= n; i++) {
            List<TreeNode> cur = new ArrayList<>();
            for (TreeNode root : pre) {
                TreeNode insert = new TreeNode(i);
                insert.left = root;
                cur.add(insert);
                for (int j = 0; j <= n; j++) {
                    TreeNode root_copy = treeCopy(root);
                    TreeNode right = root_copy;
                    for (int k = 0; k < j; k++) {
                        if (right == null)
                            break;
                        right = right.right;
                    }
                    if (right == null)
                        break;
                    TreeNode rightTree = right.right;
                    insert = new TreeNode(i);
                    right.right = insert;
                    insert.left = rightTree;
                    cur.add(root_copy);
                }
            }
            pre = cur;
        }
        return pre;
    }
    private TreeNode treeCopy(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode newRoot = new TreeNode(root.val);
        newRoot.left = treeCopy(root.left);
        newRoot.right = treeCopy(root.right);
        return newRoot;
    }
}
