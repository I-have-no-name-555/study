/**
 * @author :Xuan
 * @date :Create in 2021/3/3 14:49
 * @description 130. 被围绕的区域 中等
 */
public class SurroundedRegions {
    /*
        给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
    找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
    示例:
    X X X X
    X O O X
    X X O X
    X O X X
    运行你的函数后，矩阵变为：
    X X X X
    X X X X
    X X X X
    X O X X
    解释:
    被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
    任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
    如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/surrounded-regions
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    int[][] visit;

    public void solve(char[][] board) {
        int row = board.length;
        if (row == 0)
            return;
        int col = board[0].length;
        if (col == 0)
            return;
        visit = new int[row][col];
        for (int i = 0; i < row; i++) {
            dfs(board, i, 0);
            dfs(board, i, col - 1);
        }
        for (int j = 0; j < col; j++) {
            dfs(board, 0, j);
            dfs(board, row - 1, j);
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visit[i][j] == 0)
                    board[i][j] = 'X';
            }
        }

    }

    public void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)
            return;
        if (visit[i][j] == 1)
            return;
        visit[i][j] = 1;
        if (board[i][j] != 'X') {
            dfs(board, i + 1, j);
            dfs(board, i - 1, j);
            dfs(board, i, j + 1);
            dfs(board, i, j - 1);
        }
    }

    public void solve2(char[][] board) {
        int row = board.length;
        if (row == 0) return;
        int col = board[0].length;
        int dummy = row * col;
        int[][] d = new int[][]{{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
        UF uf = new UF(dummy);

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                        uf.union(i * col + j, dummy);
                    } else {
                        for (int k = 0; k < 4; k++) {
                            int x = i + d[k][0];
                            int y = j + d[k][1];
                            if (board[x][y] == 'O') uf.union(x * col + y, i * col + j);
                        }
                    }
                }
            }
        }
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < col - 1; j++) {
                if (!uf.connect(i * col + j, dummy)) board[i][j] = 'X';
            }
        }
    }
}

class UF {
    private int[] parent;

    public UF(int dummy) {
        parent = new int[dummy + 1];
        for (int i = 0; i <= dummy; i++) {
            parent[i] = i;
        }
    }

    public void union(int x, int y) {
        int root_x = find(x);
        int root_y = find(y);
        if (root_x != root_y) parent[root_x] = root_y;
    }

    public int find(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public boolean connect(int x, int y) {
        return find(x) == find(y);
    }

}