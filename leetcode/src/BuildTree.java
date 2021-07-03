/**
 * @author :Xuan
 * @date :Create in 2021/3/30 8:28
 * @description 剑指 Offer 07. 重建二叉树 中等
 */
public class BuildTree {
    /*
        输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
    例如，给出
    前序遍历 preorder = [3,9,20,15,7]
    中序遍历 inorder = [9,3,15,20,7]
    返回如下的二叉树：
        3
       / \
      9  20
        /  \
       15   7
    限制：
    0 <= 节点个数 <= 5000
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || preorder.length != inorder.length)
            return null;
        return buildTree(preorder,0,preorder.length,inorder,0,inorder.length);
    }
    private TreeNode buildTree(int[] preorder,int startPre,int endPre,int[] inorder,int startIn,int endIn){
        if (startPre >= endPre)
            return null;
        TreeNode root = new TreeNode(preorder[startPre]);
        int index;
        for (index = startIn;index < endIn;index++){
            if (inorder[index] == preorder[startPre])
                break;
        }
        root.left = buildTree(preorder,startPre + 1,startPre + 1 + index - startIn
                ,inorder,startIn,index);
        root.right = buildTree(preorder,endPre - endIn + index + 1,endPre
                ,inorder,index + 1,endIn);
        return root;
    }

    private int cur = 0;
    private int pre = 0;
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return build(preorder, inorder, Integer.MIN_VALUE);
    }
    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (pre >= preorder.length)
            return null;

        if (inorder[cur] == stop) {
            cur++;
            return null;
        }

        TreeNode node = new TreeNode(preorder[pre++]);
        node.left = build(preorder, inorder, node.val);
        node.right = build(preorder, inorder, stop);

        return node;
    }
}
