import java.util.Deque;
import java.util.LinkedList;

/**
 * @author :Xuan
 * @date :Create in 2021/2/18 13:36
 * @description 106. 从中序与后序遍历序列构造二叉树 中等
 * @update
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    /*
        根据一棵树的中序遍历与后序遍历构造二叉树。
    注意:
    你可以假设树中没有重复的元素。
    例如，给出
    中序遍历 inorder = [9,3,15,20,7]
    后序遍历 postorder = [9,15,7,20,3]
    返回如下的二叉树：
        3
       / \
      9  20
        /  \
       15   7
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0)
            return null;
        return buildTree(inorder,0,inorder.length,postorder,postorder.length - 1,0);
    }
    //后序遍历中 start在end后
    public TreeNode buildTree(int[] inorder, int start1, int end1, int[] postorder,int start2, int end2) {
        if (end1 <= start1 || start1 >= inorder.length || start1 < 0)
            return null;
        TreeNode root = new TreeNode(postorder[start2]);
        int index = start1;
        while (index < end1){
            if (inorder[index] == postorder[start2])
                break;
            index++;
        }
        root.left = buildTree(inorder,start1,index,postorder,
                end2 - 1 + (index - start1),end2);
        root.right = buildTree(inorder,index + 1,end1,postorder
                ,start2 - 1,end2 + (index - start1));
        return root;
    }

    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        if (postorder == null || postorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = inorder.length - 1;
        for (int i = postorder.length - 2; i >= 0; i--) {
            int postorderVal = postorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.right = new TreeNode(postorderVal);
                stack.push(node.right);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex--;
                }
                node.left = new TreeNode(postorderVal);
                stack.push(node.left);
            }
        }
        return root;
    }

}
