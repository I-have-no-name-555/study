/**
 * @author :Xuan
 * @date :Create in 2021/4/6 16:30
 * @description 剑指 Offer 33. 二叉搜索树的后序遍历序列 中等
 */
public class VerifyPostorder {
    /*
        输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。
    假设输入的数组的任意两个数字都互不相同。
    参考以下这颗二叉搜索树：
         5
        / \
       2   6
      / \
     1   3
    示例 1：
    输入: [1,6,3,2,5]
    输出: false
    示例 2：
    输入: [1,3,2,6,5]
    输出: true
    提示：
    数组长度 <= 1000
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[] postorder = {1,3,2,6,5};
        System.out.println(new VerifyPostorder().verifyPostorder(postorder));
    }

    public boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length == 0)
            return true;
        return verifyPostorder(postorder, 0, postorder.length);
    }
    private boolean verifyPostorder(int[] postorder, int start, int end) {
        if (end - start <= 1)
            return true;
        int index = end - 1;
        int root = postorder[index--];
        while (index >= start){
            if (postorder[index] < root){
                break;
            }
            index--;
        }
        for (int i = index; i >= start;i--){
            if (postorder[i] > root)
                return false;
        }
        if (index < start)
            return verifyPostorder(postorder,start,end - 1);
        return verifyPostorder(postorder,start,index + 1)
                && verifyPostorder(postorder,index + 1,end - 1);
    }
}
