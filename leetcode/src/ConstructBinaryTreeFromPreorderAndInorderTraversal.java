import java.util.Deque;
import java.util.LinkedList;

/**
 * @author :Xuan
 * @date :Create in 2021/2/17 11:06
 * @description 105. 从前序与中序遍历序列构造二叉树 中等
 * @update
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    /*
        根据一棵树的前序遍历与中序遍历构造二叉树。
    注意:
    你可以假设树中没有重复的元素。
    例如，给出
    前序遍历 preorder = [3,9,20,15,7]
    中序遍历 inorder = [9,3,15,20,7]
    返回如下的二叉树：
        3
       / \
      9  20
        /  \
       15   7
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        System.out.println(new ConstructBinaryTreeFromPreorderAndInorderTraversal().buildTree(preorder, inorder));
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0)
            return null;
        return buildTree(preorder,0,preorder.length,inorder,0,inorder.length);
    }
    public TreeNode buildTree(int[] preorder, int start1, int end1, int[] inorder,int start2, int end2) {
        if (end1 <= start1 || start1 >= preorder.length || start1 < 0)
            return null;
        TreeNode root = new TreeNode(preorder[start1]);
        int index = start2;
        while (index < end2){
            if (inorder[index] == preorder[start1])
                break;
            index++;
        }
        root.left = buildTree(preorder,start1 + 1,start1 + 1 + (index - start2)
                , inorder,start2,index);
        root.right = buildTree(preorder,start1 + 1 + (index - start2)
                ,end1,inorder,index + 1,end2);
        return root;
    }

    //迭代
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;
    }
}
