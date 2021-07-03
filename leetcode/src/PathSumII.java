import java.util.ArrayList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/2/23 11:23
 * @description 113. 路径总和 II 中等
 */
public class PathSumII {
    /*
        给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
    说明: 叶子节点是指没有子节点的节点。
    示例:
    给定如下二叉树，以及目标和 sum = 22，
                  5
                 / \
                4   8
               /   / \
              11  13  4
             /  \    / \
            7    2  5   1
    返回:
    [
       [5,4,11,2],
       [5,8,4,5]
    ]
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/path-sum-ii
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root,targetSum);
        return ans;
    }
    private void dfs(TreeNode root , int targetSum){
        if (root == null)
            return;
        path.add(root.val);
        if (root.left == null && root.right == null){
            if (root.val == targetSum)
                ans.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }
        dfs(root.left,targetSum - root.val);
        dfs(root.right,targetSum - root.val);
        path.remove(path.size() - 1);
    }
}
