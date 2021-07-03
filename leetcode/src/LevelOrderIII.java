import java.util.*;

/**
 * @author :Xuan
 * @date :Create in 2021/4/5 16:17
 * @description 剑指 Offer 32 - III. 从上到下打印二叉树 III 中等
 */
public class LevelOrderIII {
    /*
        请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，
    第三行再按照从左到右的顺序打印，其他行以此类推。
    例如:
    给定二叉树: [3,9,20,null,null,15,7],
        3
       / \
      9  20
        /  \
       15   7
    返回其层次遍历结果：
    [
      [3],
      [20,9],
      [15,7]
    ]
    提示：
    节点总数 <= 1000
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        int level = 1;

        TreeNode curNode;
        int curSize;
        stack1.push(root);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if ((level & 1) == 1) {
                curSize = stack1.size();
                for (int i = 0; i < curSize; i++) {
                    curNode = stack1.pop();
                    list.add(curNode.val);
                    if (curNode.left != null)
                        stack2.push(curNode.left);
                    if (curNode.right != null)
                        stack2.push(curNode.right);
                }
            } else {
                curSize = stack2.size();
                for (int i = 0; i < curSize; i++) {
                    curNode = stack2.pop();
                    list.add(curNode.val);
                    if (curNode.right != null)
                        stack1.push(curNode.right);
                    if (curNode.left != null)
                        stack1.push(curNode.left);
                }
            }
            level++;
            res.add(new ArrayList<>(list));
            list.clear();
        }
        return res;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        Deque<TreeNode> deque = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        deque.add(root);
        while (!deque.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            for (int i = deque.size(); i > 0; i--) {
                TreeNode node = deque.removeFirst();
                temp.add(node.val);
                if (node.left != null)
                    deque.addLast(node.left);
                if (node.right != null)
                    deque.addLast(node.right);
            }
            res.add(temp);
            if (deque.isEmpty())
                break;
            temp = new ArrayList<>();
            for (int i = deque.size(); i > 0; i--) {
                TreeNode node = deque.removeLast();
                temp.add(node.val);
                if (node.right != null)
                    deque.addFirst(node.right);
                if (node.left != null)
                    deque.addFirst(node.left);
            }
            res.add(temp);
        }
        return res;
    }


    public List<List<Integer>> levelOrder3(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode cur = queue.poll();
                tmp.add(cur.val);
                if (cur.left != null)
                    queue.add(cur.left);
                if (cur.right != null)
                    queue.add(cur.right);
            }
            if ((res.size() & 1) == 1)
                Collections.reverse(tmp);
            res.add(tmp);
        }
        return res;
    }

}
