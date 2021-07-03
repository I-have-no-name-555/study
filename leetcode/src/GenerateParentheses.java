import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2020/12/28 17:08
 * @description 22 括号生成 中等
 * @update
 */
public class GenerateParentheses {
    /*
        数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
    示例：
    输入：n = 3
    输出：[
           "((()))",
           "(()())",
           "(())()",
           "()(())",
           "()()()"
         ]
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/generate-parentheses
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        GenerateParentheses test = new GenerateParentheses();
        int n = 3;
        System.out.println(test.generateParenthesis1(n));
        System.out.println(test.generateParenthesis2(n));
    }

    //DFS 回溯
    public List<String> generateParenthesis1(int n) {
        List<String> list = new ArrayList<>();
        if (n == 0)
            return list;
        dfs("", n, n, list);
        return list;
    }

    private void dfs(String curStr, int left, int right, List<String> list) {
        if (left == 0 && right == 0) {
            list.add(curStr);
            return;
        }
        if (left > right)
            return;
        if (left > 0)
            dfs(curStr + "(", left - 1, right, list);
        if (right > 0)
            dfs(curStr + ")", left, right - 1, list);
    }

    //动态规划
    public List<String> generateParenthesis2(int n) {
        if (n == 0)
            return new ArrayList<>();
        LinkedList<LinkedList<String>> result = new LinkedList<>();
        LinkedList<String> list0 = new LinkedList<>();
        list0.add("");
        result.add(list0);
        LinkedList<String> list1 = new LinkedList<>();
        list1.add("()");
        result.add(list1);

        LinkedList<String> temp;
        List<String> str1;
        List<String> str2;
        StringBuilder sb;
        for (int i = 2; i <= n; i++) {
             temp = new LinkedList<>();
            for (int j = 0; j < i; j++) {
                 str1 = result.get(j);
                 str2 = result.get(i - 1 - j);
                for (String s1 : str1) {
                    for (String s2 : str2) {
                        sb = new StringBuilder(n * 2);
                        sb.append('(');
                        sb.append(s1);
                        sb.append(')');
                        sb.append(s2);
                        temp.add(sb.toString());
                    }
                }
            }
            result.add(temp);
        }
        return result.get(n);
    }

}
