import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author :Xuan
 * @date :Create in 2021/2/12 16:58
 * @description 96. 不同的二叉搜索树 中等
 * @update
 */
public class UniqueBinarySearchTrees {
    /*
        给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？

    示例:
    输入: 3
    输出: 5
    解释:
    给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

       1         3     3      2      1
        \       /     /      / \      \
         3     2     1      1   3      2
        /     /       \                 \
       2     1         2                 3

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/unique-binary-search-trees
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    static ArrayList<Integer> dp = new ArrayList<>();

    static {
        dp.add(1);
    }

    public int numTrees(int n) {
        if (n < dp.size())
            return dp.get(n);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = ans + (numTrees(i) * numTrees(n - i - 1));
        }
        dp.add(ans);
        return dp.get(n);
    }   
//究极打表
    static int[] DP = {0, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796, 58786, 208012, 742900, 2674440, 9694845, 35357670, 129644790, 477638700, 1767263190};
    public int numTrees2(int n){
        return DP[n];
    }
}

