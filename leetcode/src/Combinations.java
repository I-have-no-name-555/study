import java.util.ArrayList;
import java.util.List;

/**
 * @author :Xuan
 * @date :Create in 2021/1/27 21:00
 * @description 77. 组合 中等
 * @update
 */
public class Combinations {
    /*
        给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
    示例:
    输入: n = 4, k = 2
    输出:
    [
      [2,4],
      [3,4],
      [2,3],
      [1,2],
      [1,3],
      [1,4],
    ]
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/combinations
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int n = 4;
        int k = 3;
        System.out.println(new Combinations().combine(n, k));

    }
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    //回溯
    public List<List<Integer>> combine(int n, int k) {
        dfs(1,n,k);
        return ans;
    }
    private void dfs(int begin,int n,int k){
        if (path.size() + (n - begin + 1) < k)//就这一步从23ms变成了2ms
            return;

        if (path.size() == k){
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i <= n; ++i) {
            path.add(i);
            dfs(i + 1,n,k);
            path.remove(path.size() - 1);
        }
    }

    public List<List<Integer>> combine2(int n, int k) {
        for (int i = 1; i <= k; ++i)
            path.add(i);
        path.add(n + 1);
        int j = 0;
        while (j < k) {
            ans.add(new ArrayList<>(path.subList(0, k)));
            j = 0;
            while (j < k && path.get(j) + 1 == path.get(j + 1)) {
                path.set(j, j + 1);
                j++;
            }
            path.set(j, path.get(j) + 1);
        }
        return ans;
    }

}
