import java.util.Arrays;

/**
 * @author :Xuan
 * @date :Create in 2021/1/26 15:21
 * @description 73. 矩阵置零 中等
 * @update
 */
public class SetMatrixZeroes {
    /*
        给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
    示例 1:
    输入:
    [
      [1,1,1],
      [1,0,1],
      [1,1,1]
    ]
    输出:
    [
      [1,0,1],
      [0,0,0],
      [1,0,1]
    ]
    示例 2:
    输入:
    [
      [0,1,2,0],
      [3,4,5,2],
      [1,3,1,5]
    ]
    输出:
    [
      [0,0,0,0],
      [0,4,5,0],
      [0,3,1,0]
    ]
    进阶:
    一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
    一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
    你能想出一个常数空间的解决方案吗？
    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/set-matrix-zeroes
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] matrix2 = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        new SetMatrixZeroes().setZeroes2(matrix);
        System.out.println(Arrays.deepToString(matrix));
        new SetMatrixZeroes().setZeroes2(matrix2);
        System.out.println(Arrays.deepToString(matrix2));

    }

    //m + n
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] flags = new boolean[m + n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    flags[i] = true;
                    flags[m + j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            if (flags[i])
                Arrays.fill(matrix[i], 0);
        }
        for (int i = m; i < m + n; i++) {
            if (flags[i]) {
                for (int j = 0; j < m; j++) {
                    matrix[j][i - m] = 0;
                }
            }
        }
    }

    //const
    public void setZeroes2(int[][] matrix) {
        boolean col_flag = false;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0)
                col_flag = true;
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (col_flag)
                matrix[i][0] = 0;
        }
    }
}
