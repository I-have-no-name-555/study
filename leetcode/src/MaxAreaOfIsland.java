/**
 * @author :Xuan
 * @date :Create in 2021/8/9 21:57
 * @description 695. 岛屿的最大面积 中等
 */
public class MaxAreaOfIsland {
    /*
        给定一个包含了一些 0 和 1 的非空二维数组 grid 。
    一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，
    这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
    你可以假设 grid 的四个边缘都被 0（代表水）包围着。
    找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
    示例 1:
    [[0,0,1,0,0,0,0,1,0,0,0,0,0],
     [0,0,0,0,0,0,0,1,1,1,0,0,0],
     [0,1,1,0,1,0,0,0,0,0,0,0,0],
     [0,1,0,0,1,1,0,0,1,0,1,0,0],
     [0,1,0,0,1,1,0,0,1,1,1,0,0],
     [0,0,0,0,0,0,0,0,0,0,1,0,0],
     [0,0,0,0,0,0,0,1,1,1,0,0,0],
     [0,0,0,0,0,0,0,1,1,0,0,0,0]]
    对于上面这个给定矩阵应返回 6。注意答案不应该是 11 ，因为岛屿只能包含水平或垂直的四个方向的 1 。
    示例 2:
    [[0,0,0,0,0,0,0,0]]
    对于上面这个给定的矩阵, 返回 0。
    注意: 给定的矩阵grid 的长度和宽度都不超过 50。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/max-area-of-island
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
        public int maxAreaOfIsland(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int m = grid.length;
            int n = grid[0].length;
            int max = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    max = Math.max(dfs(grid, i, j), max);
                }
            }
            return max;
        }
        private int dfs(int[][] grid, int i, int j) {
            if (grid[i][j] == 0) {
                return 0;
            }
            int cur = 1;
            grid[i][j] = 0;
            if (i > 0) {
                cur += dfs(grid, i - 1, j);
            }
            if (i < grid.length - 1) {
                cur += dfs(grid, i + 1, j);
            }
            if (j > 0) {
                cur += dfs(grid, i, j - 1);
            }
            if (j < grid[0].length - 1) {
                cur += dfs(grid, i, j + 1);
            }
            return cur;
        }
}
