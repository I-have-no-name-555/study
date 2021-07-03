/**
 * @author :Xuan
 * @date :Create in 2021/5/21 11:24
 * @description 200. 岛屿数量 中等
 */
public class NumberOfIslands {
    /*
        给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
    岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
    此外，你可以假设该网格的四条边均被水包围。
    示例 1：
    输入：grid = [
      ["1","1","1","1","0"],
      ["1","1","0","1","0"],
      ["1","1","0","0","0"],
      ["0","0","0","0","0"]
    ]
    输出：1
    示例 2：
    输入：grid = [
      ["1","1","0","0","0"],
      ["1","1","0","0","0"],
      ["0","0","1","0","0"],
      ["0","0","0","1","1"]
    ]
    输出：3
    提示：
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 300
    grid[i][j] 的值为 '0' 或 '1'
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/number-of-islands
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] reached = new boolean[m][n];
        int islands = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !reached[i][j]){
                    mark(grid,i,j,reached);
                    islands++;
                }
            }
        }
        return islands;
    }
    private void mark(char[][] grid, int i, int j, boolean[][] reached) {
        reached[i][j] = true;
        if (i > 0 && grid[i - 1][j] == '1' && !reached[i - 1][j])
            mark(grid, i - 1, j, reached);
        if (i < grid.length - 1 && grid[i + 1][j] == '1' && !reached[i + 1][j])
            mark(grid, i + 1, j, reached);
        if (j > 0 && grid[i][j - 1] == '1' && !reached[i][j - 1])
            mark(grid, i, j - 1, reached);
        if (j < grid[0].length - 1 && grid[i][j + 1] == '1' && !reached[i][j + 1])
            mark(grid, i, j + 1, reached);
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1','1','1'},
                {'0','1','0'},
                {'1','1','1'}
        };
        System.out.println(new NumberOfIslands().numIslands(grid));
    }
}
