/**
 * @author :Xuan
 * @date :Create in 2021/1/22 18:42
 * @description 64. 最小路径和 中等
 * @update
 */
public class MinimumPathSum {
    /*
    给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
    说明：每次只能向下或者向右移动一步。
    示例 1：
    输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
    输出：7
    解释：因为路径 1→3→1→1→1 的总和最小。
    示例 2：
    输入：grid = [[1,2,3],[4,5,6]]
    输出：12
    提示：
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 200
    0 <= grid[i][j] <= 100
     */
    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(new MinimumPathSum().minPathSum(grid));
    }
    //Dijkstra
    public int minPathSum(int[][] grid) {
        //滚动数组
        int[] f = grid[0];
        int m = grid.length;
        int n = f.length;
        for (int i = 1; i < n; i++)
            f[i] += f[i - 1];
        for (int i = 1; i < m; i++) {
            f[0] += grid[i][0];
            for (int j = 1; j < n; j++)
                f[j] = Math.min(f[j - 1],f[j]) + grid[i][j];
        }
        return f[n - 1];
    }

}
