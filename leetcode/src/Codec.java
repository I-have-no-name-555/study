import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author :Xuan
 * @date :Create in 2021/4/9 19:00
 * @description 剑指 Offer 37. 序列化二叉树 困难
 */
public class Codec {
    /*
        请实现两个函数，分别用来序列化和反序列化二叉树。
    示例:
    你可以将以下二叉树：
        1
       / \
      2   3
         / \
        4   5
    序列化为 "[1,2,3,null,null,4,5]"
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    /*
    public String serialize(TreeNode root) {
        if(root == null)
            return "[]";
        StringBuilder res = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node != null) {
                res.append(node.val).append(",");
                queue.add(node.left);
                queue.add(node.right);
            }
            else res.append("null,");
        }
        res.deleteCharAt(res.length() - 1);
        res.append("]");
        return res.toString();
    }

    public TreeNode deserialize(String data) {
        if(data.equals("[]"))
            return null;
        String[] values = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(!values[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(values[i]));
                queue.add(node.left);
            }
            i++;
            if(!values[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(values[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }

     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        encode(root, sb);
        return sb.toString();
    }
    private void encode(TreeNode root, StringBuilder sb){
        if(root == null){
            sb.append("#").append(",");
            return;
        }
        sb.append(root.val).append(",");
        encode(root.left, sb);
        encode(root.right, sb);
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return decode(data);
    }
    private int u;
    private TreeNode decode(String data){
        if(data.charAt(u) == '#'){
            u += 2;
            return null;
        }
        int v = 0, sign = 1;
        if(data.charAt(u) == '-'){
            u++;
            sign = -1;
        }
        while(u < data.length() && data.charAt(u) != ','){
            v = v * 10 + (data.charAt(u) - '0');
            u++;
        }
        TreeNode root = new TreeNode(v * sign);
        u++; // skip ","
        root.left = decode(data);
        root.right = decode(data);
        return root;
    }
}


